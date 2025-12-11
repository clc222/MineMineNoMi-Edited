/*     */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg.ExtraHachisProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg.PoeleAFrireProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class ExtraHachisAbility extends Ability {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "extra_hachi", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("Launches a rapid barrage of kicks", null)
/*     */       });
/*  40 */   private static final TranslationTextComponent POELE_A_FRIRE_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.poele_a_frire", "Poêle à Frire"));
/*     */   
/*  42 */   private static final ResourceLocation POELE_A_FRIRE_ICON = new ResourceLocation("mineminenomi", "textures/abilities/poele_a_frire.png");
/*     */   
/*     */   private static final float NORMAL_COOLDOWN = 240.0F;
/*     */   
/*     */   private static final float POELE_A_FRIRE_COOLDOWN = 300.0F;
/*  47 */   public static final AbilityCore<ExtraHachisAbility> INSTANCE = (new AbilityCore.Builder("Extra Hachis", AbilityCategory.STYLE, ExtraHachisAbility::new))
/*  48 */     .addDescriptionLine(DESCRIPTION)
/*  49 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/*  50 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  51 */     .setUnlockCheck(ExtraHachisAbility::canUnlock)
/*  52 */     .build();
/*     */   
/*  54 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/*  55 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/*  56 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/*     */   public ExtraHachisAbility(AbilityCore<ExtraHachisAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     this.isNew = true;
/*     */     
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.altModeComponent });
/*     */     
/*  65 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  69 */     if (this.continuousComponent.isContinuous()) {
/*  70 */       this.repeaterComponent.stop(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  75 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  79 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  83 */     this.repeaterComponent.start(entity, 20, 2);
/*     */   }
/*     */   
/*     */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/*  87 */     int projectileSpace = 2;
/*     */     
/*  89 */     float speed = 2.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     Vector3d pos = new Vector3d(entity.func_226277_ct_() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), entity.func_226278_cu_() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), entity.func_226281_cx_() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble());
/*     */     
/*  96 */     ExtraHachisProjectile extraHachisProjectile = new ExtraHachisProjectile(entity.field_70170_p, entity, this);
/*     */     
/*  98 */     if (this.altModeComponent.getCurrentMode() == Mode.POELE_A_FRIRE) {
/*  99 */       speed = 3.0F;
/*     */       
/* 101 */       PoeleAFrireProjectile poeleAFrireProjectile = new PoeleAFrireProjectile(entity.field_70170_p, entity, this);
/*     */       
/* 103 */       poeleAFrireProjectile.func_70012_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 0.0F);
/*     */       
/* 105 */       entity.field_70170_p.func_217376_c((Entity)poeleAFrireProjectile);
/*     */       
/* 107 */       poeleAFrireProjectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, speed, 3.0F);
/*     */     } 
/*     */     
/* 110 */     extraHachisProjectile.func_70012_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 0.0F);
/*     */     
/* 112 */     entity.field_70170_p.func_217376_c((Entity)extraHachisProjectile);
/*     */     
/* 114 */     extraHachisProjectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, speed, 3.0F);
/*     */     
/* 116 */     ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */   }
/*     */   
/*     */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 120 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 122 */     if (this.altModeComponent.getCurrentMode() == Mode.NORMAL) {
/* 123 */       this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */     }
/* 125 */     else if (this.altModeComponent.getCurrentMode() == Mode.POELE_A_FRIRE) {
/* 126 */       this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 131 */     if (mode == Mode.NORMAL) {
/* 132 */       setDisplayIcon(INSTANCE);
/* 133 */       setDisplayName((ITextComponent)INSTANCE.getLocalizedName());
/*     */     }
/* 135 */     else if (mode == Mode.POELE_A_FRIRE) {
/* 136 */       setDisplayName((ITextComponent)POELE_A_FRIRE_NAME);
/* 137 */       setDisplayIcon(POELE_A_FRIRE_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 142 */     if (!(entity instanceof PlayerEntity)) {
/* 143 */       return false;
/*     */     }
/*     */     
/* 146 */     PlayerEntity player = (PlayerEntity)entity;
/* 147 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 148 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 150 */     return (props.isBlackLeg() && questProps.hasFinishedQuest(ModQuests.BLACK_LEG_TRIAL_02));
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 154 */     NORMAL, POELE_A_FRIRE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\ExtraHachisAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */