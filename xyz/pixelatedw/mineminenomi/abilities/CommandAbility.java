/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.AttackCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.FollowCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.GuardCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.StayCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommandMarkParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ public class CommandAbility<E extends MobEntity & ICommandReceiver>
/*     */   extends Ability
/*     */ {
/*     */   private static final int COOLDOWN = 10;
/*     */   private static final float RANGE = 20.0F;
/*     */   private static final int LIMIT = 20;
/*     */   private static final float ATTACK_RAYTRACE_DISTANCE = 30.0F;
/*     */   private static final float ATTACK_RAYTRACE_WIDTH = 1.6F;
/*  56 */   private static final int[] COMMAND_COLORS = new int[] { Color.WHITE
/*  57 */       .getRGB(), Color.RED
/*  58 */       .getRGB(), Color.GREEN
/*  59 */       .getRGB(), Color.YELLOW
/*  60 */       .getRGB(), Color.BLUE
/*  61 */       .getRGB() };
/*     */ 
/*     */   
/*  64 */   private static final TranslationTextComponent IDLE_COMMAND_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.idle_command", "Command: Idle"));
/*  65 */   private static final TranslationTextComponent ATTACK_COMMAND_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.attack_command", "Command: Attack"));
/*  66 */   private static final TranslationTextComponent FOLLOW_COMMAND_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.follow_command", "Command: Follow"));
/*  67 */   private static final TranslationTextComponent STAY_COMMAND_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stay_command", "Command: Stay"));
/*  68 */   private static final TranslationTextComponent GUARD_COMMAND_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.guard_command", "Command: Guard"));
/*  69 */   private static final TranslationTextComponent[] COMMAND_NAMES = new TranslationTextComponent[] { IDLE_COMMAND_NAME, ATTACK_COMMAND_NAME, FOLLOW_COMMAND_NAME, STAY_COMMAND_NAME, GUARD_COMMAND_NAME };
/*     */   
/*  71 */   private static final ResourceLocation IDLE_ICON = new ResourceLocation("mineminenomi", "textures/abilities/idle_command.png");
/*  72 */   private static final ResourceLocation ATTACK_ICON = new ResourceLocation("mineminenomi", "textures/abilities/attack_command.png");
/*  73 */   private static final ResourceLocation FOLLOW_ICON = new ResourceLocation("mineminenomi", "textures/abilities/follow_command.png");
/*  74 */   private static final ResourceLocation STAY_ICON = new ResourceLocation("mineminenomi", "textures/abilities/stay_command.png");
/*  75 */   private static final ResourceLocation GUARD_ICON = new ResourceLocation("mineminenomi", "textures/abilities/guard_command.png");
/*  76 */   private static final ResourceLocation[] COMMAND_ICONS = new ResourceLocation[] { IDLE_ICON, ATTACK_ICON, FOLLOW_ICON, STAY_ICON, GUARD_ICON };
/*     */   
/*  78 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "command", new Pair[] {
/*  79 */         (Pair)ImmutablePair.of("Sends a command to all nearby entities that the player has control over.", null), 
/*  80 */         (Pair)ImmutablePair.of("  §aIDLE§r removes any previously issued command from all nearby entities", null), 
/*  81 */         (Pair)ImmutablePair.of("  §aATTACK§r either start attacking the entity the player is looking at (if any) or the closest one", null), 
/*  82 */         (Pair)ImmutablePair.of("  §aFOLLOW§r will start following the command sender", null), 
/*  83 */         (Pair)ImmutablePair.of("  §aSTAY§r will stay in place, attacking only after being hit first", null), 
/*  84 */         (Pair)ImmutablePair.of("  §aGUARD§r wil attack any nearby enemies but then return back to its guarding point", null)
/*     */       });
/*  86 */   public static final AbilityCore<CommandAbility> INSTANCE = (new AbilityCore.Builder("Command", "command", AbilityCategory.FACTION, AbilityType.ACTION, CommandAbility::new))
/*  87 */     .addDescriptionLine(DESCRIPTION)
/*  88 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), RangeComponent.getTooltip(20.0F, RangeComponent.RangeType.AOE)
/*  89 */       }).setUnlockCheck(CommandAbility::canUnlock)
/*  90 */     .setIcon(IDLE_ICON)
/*  91 */     .build();
/*     */   
/*  93 */   protected final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  94 */   private final AltModeComponent<NPCCommand> altModeComponent = (new AltModeComponent((IAbility)this, NPCCommand.class, (Enum)NPCCommand.IDLE)).addChangeModeEvent(100, this::onChangeModEvent);
/*     */   
/*     */   private CommandMarkParticleEffect.Details mainDetails;
/*     */   private CommandMarkParticleEffect.Details subDetails;
/*  98 */   private TargetsPredicate targetPredicate = (new TargetsPredicate()).testFriendlyFaction();
/*  99 */   private NPCCommand command = NPCCommand.IDLE;
/* 100 */   private int markColor = COMMAND_COLORS[0];
/*     */   
/*     */   public CommandAbility(AbilityCore core) {
/* 103 */     super(core);
/*     */     
/* 105 */     this.isNew = true;
/* 106 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/* 108 */     this.mainDetails = new CommandMarkParticleEffect.Details(getMarkColor(), true);
/* 109 */     this.subDetails = new CommandMarkParticleEffect.Details(getMarkColor(), false);
/*     */     
/* 111 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/* 115 */     this.targetPredicate.selector(living -> (living instanceof MobEntity && living instanceof ICommandReceiver && ((ICommandReceiver)living).canReceiveCommandFrom(entity)));
/*     */ 
/*     */     
/* 118 */     this.rangeComponent.getTargetsInArea(entity, 20.0F, this.targetPredicate)
/* 119 */       .stream()
/* 120 */       .limit(20L)
/* 121 */       .forEach(target -> {
/*     */           spawnCommandMark(entity, target);
/*     */           
/*     */           ((ICommandReceiver)target).setCurrentCommand(entity, getCommand());
/*     */           sendCommand(entity, (E)target);
/*     */         });
/* 127 */     spawnCommandMark(entity, entity);
/* 128 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void onChangeModEvent(LivingEntity entity, IAbility ability, NPCCommand newCommand) {
/* 132 */     this.command = newCommand;
/* 133 */     updateCommandDetails();
/*     */   }
/*     */   
/*     */   private void updateCommandDetails() {
/* 137 */     this.markColor = COMMAND_COLORS[this.command.ordinal()];
/* 138 */     setDisplayName((ITextComponent)COMMAND_NAMES[this.command.ordinal()]);
/* 139 */     setDisplayIcon(COMMAND_ICONS[this.command.ordinal()]);
/*     */     
/* 141 */     this.mainDetails = new CommandMarkParticleEffect.Details(getMarkColor(), true);
/* 142 */     this.subDetails = new CommandMarkParticleEffect.Details(getMarkColor(), false);
/*     */   }
/*     */   
/*     */   private void spawnCommandMark(LivingEntity entity, LivingEntity target) {
/* 146 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 147 */       WyHelper.spawnParticleEffectForOwner((ParticleEffect)ModParticleEffects.COMMAND_MARK.get(), (PlayerEntity)entity, target.func_226277_ct_(), target.func_226278_cu_() + target.func_213302_cg(), target.func_226281_cx_(), (entity == target) ? (ParticleEffect.Details)this.mainDetails : (ParticleEffect.Details)this.subDetails);
/*     */     }
/*     */   }
/*     */   
/*     */   public NPCCommand getCommand() {
/* 152 */     return this.command;
/*     */   }
/*     */   
/*     */   public int getMarkColor() {
/* 156 */     return this.markColor;
/*     */   }
/*     */   public void sendCommand(LivingEntity commandSender, E commandListener) {
/*     */     LivingEntity target;
/* 160 */     switch (this.command) {
/*     */       case ATTACK:
/* 162 */         target = this.rangeComponent.getTargetsInLine(commandSender, 30.0F, 1.6F).stream().findFirst().orElse(null);
/* 163 */         if (target != null && target.func_70089_S()) {
/* 164 */           commandListener.func_70624_b(target);
/*     */           break;
/*     */         } 
/* 167 */         this.rangeComponent.getTargetsInArea(commandSender, 30.0F)
/* 168 */           .stream()
/* 169 */           .sorted(TargetHelper.closestComparator(commandSender.func_213303_ch()))
/* 170 */           .findFirst()
/* 171 */           .ifPresent(aoeTarget -> commandListener.func_70624_b(aoeTarget));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canUnlock(LivingEntity entity) {
/* 184 */     if (!(entity instanceof PlayerEntity)) {
/* 185 */       return false;
/*     */     }
/*     */     
/* 188 */     PlayerEntity player = (PlayerEntity)entity;
/* 189 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 190 */     IDevilFruit dfProps = DevilFruitCapability.get(entity);
/*     */     
/* 192 */     if (props.isMarine() && props.hasMarineRank(FactionHelper.MarineRank.CAPTAIN)) {
/* 193 */       return true;
/*     */     }
/* 195 */     if (props.isRevolutionary() && props.hasRevolutionaryRank(FactionHelper.RevolutionaryRank.COMMANDER)) {
/* 196 */       return true;
/*     */     }
/* 198 */     if (dfProps.hasDevilFruit(ModAbilities.KAGE_KAGE_NO_MI) || dfProps.hasDevilFruit(ModAbilities.ITO_ITO_NO_MI)) {
/* 199 */       return true;
/*     */     }
/* 201 */     if (QuestDataCapability.get(player).hasFinishedQuest(ModQuests.COMMAND_TRIAL)) {
/* 202 */       return true;
/*     */     }
/*     */     
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 210 */     nbt = super.save(nbt);
/* 211 */     nbt.func_74768_a("command", this.command.ordinal());
/* 212 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 217 */     super.load(nbt);
/* 218 */     this.command = NPCCommand.values()[nbt.func_74762_e("command")];
/* 219 */     updateCommandDetails();
/*     */   }
/*     */   
/*     */   public static void addCommandGoals(MobEntity mob) {
/* 223 */     mob.field_70714_bg.func_75776_a(0, (Goal)new StayCommandGoal(mob));
/* 224 */     mob.field_70714_bg.func_75776_a(0, (Goal)new FollowCommandGoal(mob));
/* 225 */     mob.field_70714_bg.func_75776_a(0, (Goal)new GuardCommandGoal(mob));
/*     */     
/* 227 */     mob.field_70715_bh.func_75776_a(0, (Goal)new AttackCommandGoal(mob));
/* 228 */     mob.field_70715_bh.func_75776_a(0, (Goal)new StayCommandGoal(mob));
/* 229 */     mob.field_70715_bh.func_75776_a(0, (Goal)new FollowCommandGoal(mob));
/* 230 */     mob.field_70715_bh.func_75776_a(0, (Goal)new GuardCommandGoal(mob));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\CommandAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */