/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ 
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusHeavyPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AncientBiteAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AncientTailSpinAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.AncientStompAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.AncientSweepAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.AncientTrunkShotAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.MammothGuardPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SEquipAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class TestCommand {
/*     */   private static AbilityCore<?>[] abilityTestsSet;
/*     */   private static UUID abilityTester;
/*     */   public static boolean runAbilityTests = false;
/*     */   private static boolean isRunningAbilityTest = false;
/*  54 */   private static long abilityTestTimer = 0L;
/*  55 */   private static int abilityTestIndex = 0;
/*  56 */   private static int abilityTestsErrors = 0;
/*     */   
/*  58 */   private static final HashMap<AbilityCore<?>, IPreTestHook> PRE_TEST_HOOKS = new HashMap<>();
/*     */   static {
/*  60 */     PRE_TEST_HOOKS.put(AncientBiteAbility.INSTANCE, requiresMorph(AllosaurusHeavyPointAbility.INSTANCE));
/*  61 */     PRE_TEST_HOOKS.put(AncientTailSpinAbility.INSTANCE, requiresMorph(AllosaurusHeavyPointAbility.INSTANCE));
/*     */     
/*  63 */     PRE_TEST_HOOKS.put(AncientStompAbility.INSTANCE, requiresMorph(MammothGuardPointAbility.INSTANCE));
/*  64 */     PRE_TEST_HOOKS.put(AncientSweepAbility.INSTANCE, requiresMorph(MammothGuardPointAbility.INSTANCE));
/*  65 */     PRE_TEST_HOOKS.put(AncientTrunkShotAbility.INSTANCE, requiresMorph(MammothGuardPointAbility.INSTANCE));
/*     */   }
/*     */   
/*     */   private static IPreTestHook requiresMorph(AbilityCore<?> morph) {
/*  69 */     return (entity, ability) -> {
/*     */         IAbility abl = setAbilityInSlot(entity, morph, 1);
/*     */         abl.use(entity);
/*     */       };
/*     */   }
/*     */   
/*     */   public static void register(LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  76 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("test").requires(source -> source.func_197034_c(0));
/*     */     
/*  78 */     builder.then(Commands.func_197057_a("particles").executes(ctx -> testParticles(ctx, ((CommandSource)ctx.getSource()).func_197035_h())));
/*  79 */     builder.then(Commands.func_197057_a("abilities").executes(ctx -> testNewAbilityCores(ctx, ((CommandSource)ctx.getSource()).func_197035_h())));
/*  80 */     builder.then(Commands.func_197057_a("missing_stats").executes(ctx -> testMissingStatDescriptions(ctx, ((CommandSource)ctx.getSource()).func_197035_h())));
/*  81 */     builder.then(Commands.func_197057_a("unported_abilities").executes(ctx -> testUnportedAbilities(ctx, ((CommandSource)ctx.getSource()).func_197035_h())));
/*     */     
/*  83 */     masterBuilder.then((ArgumentBuilder)builder);
/*     */   }
/*     */   
/*     */   private static int testUnportedAbilities(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/*  87 */     StringBuilder sb = new StringBuilder();
/*  88 */     int count = 0;
/*  89 */     for (AbilityCore<?> core : (Iterable<AbilityCore<?>>)ModRegistries.ABILITIES.getValues()) {
/*  90 */       IAbility abl = core.createAbility();
/*  91 */       if (abl instanceof Ability && !((Ability)abl).isNew) {
/*  92 */         sb.append("§6" + core.toString() + "§r\n");
/*  93 */         count++;
/*     */       } 
/*     */     } 
/*  96 */     player.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/*  97 */     player.func_145747_a((ITextComponent)new StringTextComponent(count + " unported abilities"), Util.field_240973_b_);
/*  98 */     float portingPercent = (1.0F - count / ModRegistries.ABILITIES.getValues().size()) * 100.0F;
/*  99 */     player.func_145747_a((ITextComponent)new StringTextComponent("Porting Progress: " + portingPercent + "%"), Util.field_240973_b_);
/* 100 */     player.func_145747_a((ITextComponent)new StringTextComponent("§2Test Completed"), Util.field_240973_b_);
/* 101 */     return 1;
/*     */   }
/*     */   
/*     */   private static int testMissingStatDescriptions(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 105 */     StringBuilder sb = new StringBuilder();
/* 106 */     int count = 0;
/* 107 */     for (AbilityCore<?> core : (Iterable<AbilityCore<?>>)ModRegistries.ABILITIES.getValues()) {
/* 108 */       long advancedLines = core.getDescription().stream().filter(line -> !line.isAdvanced()).count();
/* 109 */       if (advancedLines <= 0L) {
/* 110 */         sb.append("§6" + core.toString() + "§r\n");
/* 111 */         count++;
/*     */       } 
/*     */     } 
/* 114 */     player.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/* 115 */     player.func_145747_a((ITextComponent)new StringTextComponent(count + " abilities with missing advanced descriptions"), Util.field_240973_b_);
/* 116 */     player.func_145747_a((ITextComponent)new StringTextComponent("§2Test Completed"), Util.field_240973_b_);
/* 117 */     return 1;
/*     */   }
/*     */   
/*     */   private static int testNewAbilityCores(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 121 */     Collection<AbilityCore<?>> entries = ModRegistries.ABILITIES.getValues();
/*     */     
/* 123 */     EntityStatsCapability.get((LivingEntity)player).setDoriki(10000.0D);
/* 124 */     HakiDataCapability.get((LivingEntity)player).setBusoshokuHakiExp(100.0F);
/* 125 */     HakiDataCapability.get((LivingEntity)player).setKenbunshokuHakiExp(100.0F);
/*     */     
/* 127 */     abilityTestsSet = (AbilityCore<?>[])entries.<AbilityCore>toArray(new AbilityCore[entries.size()]);
/* 128 */     abilityTester = player.func_110124_au();
/* 129 */     runAbilityTests = true;
/* 130 */     isRunningAbilityTest = false;
/* 131 */     abilityTestIndex = 0;
/* 132 */     return 1;
/*     */   }
/*     */   
/*     */   public static void runAllAbilityTests(LivingEntity entity) {
/* 136 */     if (!runAbilityTests || entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 140 */     if (!(entity instanceof net.minecraft.entity.player.PlayerEntity) || !abilityTester.equals(entity.func_110124_au())) {
/*     */       return;
/*     */     }
/*     */     
/* 144 */     if (isRunningAbilityTest) {
/* 145 */       abilityTestTimer++;
/*     */       
/*     */       return;
/*     */     } 
/* 149 */     AbilityCore<?> core = abilityTestsSet[abilityTestIndex];
/*     */     try {
/* 151 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 152 */       props.clearUnlockedAbilities();
/* 153 */       props.clearEquippedAbilities();
/* 154 */       if (entity instanceof ServerPlayerEntity) {
/* 155 */         ServerPlayerEntity player = (ServerPlayerEntity)entity;
/* 156 */         WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.func_145782_y(), props), (Entity)player);
/*     */       } 
/*     */       
/* 159 */       System.out.println("Executing #" + abilityTestIndex + ": " + core.getUnlocalizedName());
/*     */       
/* 161 */       IAbility abl = setAbilityInSlot(entity, core, 0);
/*     */       
/* 163 */       if (abl == null) {
/* 164 */         nextAbilityTest(entity, null);
/*     */         
/*     */         return;
/*     */       } 
/* 168 */       isRunningAbilityTest = true;
/* 169 */       runAbilityTest(entity, abl);
/*     */       
/* 171 */       if (abilityTestIndex >= abilityTestsSet.length) {
/* 172 */         entity.func_145747_a((ITextComponent)new StringTextComponent("§2Test Completed"), Util.field_240973_b_);
/* 173 */         runAbilityTests = false;
/*     */         
/*     */         return;
/*     */       } 
/* 177 */     } catch (Exception e) {
/* 178 */       nextAbilityTest(entity, null);
/* 179 */       abilityTestsErrors++;
/* 180 */       if (!(e instanceof java.io.FileNotFoundException)) {
/* 181 */         e.printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static IAbility setAbilityInSlot(LivingEntity entity, AbilityCore<?> core, int slot) {
/* 188 */     IAbility abl = core.createAbility();
/*     */     
/* 190 */     if (abl == null) {
/* 191 */       return null;
/*     */     }
/*     */     
/* 194 */     if (core.getType() == AbilityType.PASSIVE) {
/* 195 */       return null;
/*     */     }
/*     */     
/* 198 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 199 */     props.addUnlockedAbility(abl.getCore(), AbilityUnlock.COMMAND);
/* 200 */     props.setEquippedAbility(slot, abl);
/* 201 */     if (entity instanceof ServerPlayerEntity) {
/* 202 */       ServerPlayerEntity player = (ServerPlayerEntity)entity;
/* 203 */       WyNetwork.sendToAllTrackingAndSelf(new SEquipAbilityPacket(player.func_145782_y(), slot, abl.getCore()), (Entity)player);
/*     */     } 
/*     */     
/* 206 */     return abl;
/*     */   }
/*     */   
/*     */   private static void runAbilityTest(LivingEntity entity, IAbility iability) {
/* 210 */     abilityTestTimer = 0L;
/*     */     
/* 212 */     if (iability instanceof Ability) {
/* 213 */       Ability ability = (Ability)iability;
/*     */       
/* 215 */       IPreTestHook hook = PRE_TEST_HOOKS.get(iability.getCore());
/* 216 */       if (hook != null) {
/* 217 */         hook.run(entity, (IAbility)ability);
/*     */       }
/*     */       
/* 220 */       ability.use(entity);
/* 221 */       Optional<CooldownComponent> cooldownOpt = iability.getComponent(ModAbilityKeys.COOLDOWN);
/* 222 */       if (cooldownOpt.isPresent()) {
/* 223 */         CooldownComponent comp = cooldownOpt.get();
/* 224 */         comp.getBonusManager().addBonus(UUID.fromString("05d6189f-d191-4c30-b5df-173fb1896e2d"), "Cooldown Test", BonusOperation.MUL, 0.01F);
/* 225 */         comp.addEndEvent(1000, (e, a) -> nextAbilityTest(entity, ability.getCore()));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 230 */       Optional<ContinuousComponent> contOpt = iability.getComponent(ModAbilityKeys.CONTINUOUS);
/* 231 */       if (contOpt.isPresent()) {
/* 232 */         ContinuousComponent comp = contOpt.get();
/* 233 */         comp.addTickEvent(1000, (e, a) -> {
/*     */               if (abilityTestTimer > 20L) {
/*     */                 comp.stopContinuity(entity);
/*     */               }
/*     */             });
/*     */       } 
/*     */       
/* 240 */       ability.addTickEvent((e, a) -> {
/*     */             if (!ability.isContinuous() && !ability.isCharging() && abilityTestTimer > 40L) {
/*     */               nextAbilityTest(entity, ability.getCore());
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void nextAbilityTest(LivingEntity entity, @Nullable AbilityCore<?> core) {
/* 251 */     isRunningAbilityTest = false;
/* 252 */     abilityTestIndex++;
/*     */   }
/*     */   
/*     */   private static int testParticles(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 256 */     HashMap<String, Double> times = new HashMap<>();
/* 257 */     Collection<ParticleEffect<?>> effects = ModRegistries.PARTICLE_EFFECTS.getValues();
/* 258 */     for (ParticleEffect<?> effect : effects) {
/* 259 */       long now = System.nanoTime();
/* 260 */       WyHelper.spawnParticleEffect(effect, (Entity)player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_());
/* 261 */       double end = (System.nanoTime() - now) / 1000000.0D;
/* 262 */       String[] split = effect.getClass().getCanonicalName().split("\\.");
/* 263 */       times.put(split[split.length - 1], Double.valueOf(end));
/*     */     } 
/*     */     
/* 266 */     String[] severities = { "§7", "§e", "§4", "§5" };
/* 267 */     StringBuilder sb = new StringBuilder();
/* 268 */     int errs = 0;
/*     */     
/* 270 */     for (Map.Entry<String, Double> time : times.entrySet()) {
/* 271 */       if (((Double)time.getValue()).doubleValue() > 10.0D) {
/* 272 */         String severity = severities[(int)Math.min((severities.length - 1), Math.round(((Double)time.getValue()).doubleValue() / 100.0D * severities.length))];
/* 273 */         sb.append("§6" + (String)time.getKey() + "§r took " + severity + time.getValue() + "ms§r\n");
/* 274 */         errs++;
/*     */       } 
/*     */     } 
/*     */     
/* 278 */     if (errs > 0) {
/* 279 */       player.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/*     */     } else {
/*     */       
/* 282 */       player.func_145747_a((ITextComponent)new StringTextComponent("§2Test Completed"), Util.field_240973_b_);
/*     */     } 
/*     */     
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   private static interface IPreTestHook {
/*     */     void run(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\TestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */