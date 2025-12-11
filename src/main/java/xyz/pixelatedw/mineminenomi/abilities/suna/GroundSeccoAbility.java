/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GroundSeccoAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ground_secco", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("Dries out the area around the user.", null), 
/*  44 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s.", new Object[] {
/*  45 */             "§a" + Math.round(19.999998F) + "%§r"
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int HOLD_TIME = 200;
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int DISTANCE = 20;
/*     */   private static final int SIZE = 15;
/*  53 */   public static final AbilityCore<GroundSeccoAbility> INSTANCE = (new AbilityCore.Builder("Ground Secco", AbilityCategory.DEVIL_FRUITS, GroundSeccoAbility::new))
/*  54 */     .addDescriptionLine(DESCRIPTION)
/*  55 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(200.0F), ChargeComponent.getTooltip(60.0F)
/*  56 */       }).build();
/*     */   
/*  58 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  59 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(this::endContinuityEvent);
/*  60 */   private final AltModeComponent<Pattern> altModeComponent = new AltModeComponent((IAbility)this, Pattern.class, Pattern.CENTER);
/*     */   
/*  62 */   private Map<BlockPos, Set<BlockPos>> pools = new HashMap<>();
/*  63 */   private Set<BlockPos> addedBlocks = new HashSet<>();
/*     */   
/*  65 */   private int distance = 20;
/*  66 */   private int size = 15;
/*  67 */   private float sizeMod = 1.0F;
/*     */   
/*     */   public GroundSeccoAbility(AbilityCore<GroundSeccoAbility> core) {
/*  70 */     super(core);
/*     */     
/*  72 */     this.isNew = true;
/*  73 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.chargeComponent });
/*     */     
/*  75 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  76 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  77 */     addCanUseCheck(SunaHelper::requiresInactiveDesertGirasole);
/*     */     
/*  79 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  83 */     if (this.continuousComponent.isContinuous()) {
/*  84 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  88 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  92 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  96 */     BlockPos arenaCenter = entity.func_233580_cy_();
/*  97 */     ChallengesWorldData worldData = ChallengesWorldData.get();
/*     */     
/*  99 */     InProgressChallenge ch = worldData.getInProgressChallengeFor((ServerWorld)entity.field_70170_p);
/* 100 */     if (ch != null) {
/* 101 */       arenaCenter = new BlockPos(ch.getArenaPos().func_177958_n(), entity.func_233580_cy_().func_177956_o(), ch.getArenaPos().func_177952_p());
/* 102 */       this.distance = 30;
/*     */     } 
/*     */     
/* 105 */     Pattern pattern = (Pattern)this.altModeComponent.getCurrentMode();
/* 106 */     pattern.create(this, entity.field_70170_p, arenaCenter);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 110 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */     
/* 116 */     if (this.chargeComponent.getChargeTime() % 5.0F == 0.0F) {
/* 117 */       for (Map.Entry<BlockPos, Set<BlockPos>> pools : this.pools.entrySet()) {
/* 118 */         if (((Set)pools.getValue()).size() <= 0) {
/*     */           continue;
/*     */         }
/* 121 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DESERT_ENCIERRO.get(), (Entity)entity, ((BlockPos)pools.getKey()).func_177958_n(), ((BlockPos)pools.getKey()).func_177956_o(), ((BlockPos)pools.getKey()).func_177952_p());
/* 122 */         int toReplace = 200;
/* 123 */         for (Iterator<BlockPos> iterator = ((Set<BlockPos>)pools.getValue()).iterator(); iterator.hasNext(); ) {
/* 124 */           BlockPos blockPos = iterator.next();
/* 125 */           if (toReplace-- < 0) {
/*     */             break;
/*     */           }
/* 128 */           if (AbilityHelper.placeBlockIfAllowed(entity, blockPos, ((Block)ModBlocks.SUNA_SAND.get()).func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID)) {
/* 129 */             this.addedBlocks.add(blockPos);
/*     */           }
/* 131 */           iterator.remove();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 138 */     this.continuousComponent.startContinuity(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 142 */     for (BlockPos pos : this.addedBlocks) {
/* 143 */       entity.field_70170_p.func_180501_a(pos, Blocks.field_150354_m.func_176223_P(), 2);
/*     */     }
/*     */     
/* 146 */     this.pools.clear();
/*     */     
/* 148 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private static void setupCornersPattern(GroundSeccoAbility ability, World world, BlockPos arenaCenter) {
/* 152 */     BlockPos pos1 = arenaCenter.func_177965_g(ability.distance).func_177964_d(ability.distance);
/* 153 */     Set<BlockPos> blocks1 = createCircle(ability, world, pos1, ability.size);
/* 154 */     BlockPos pos2 = arenaCenter.func_177985_f(ability.distance).func_177964_d(ability.distance);
/* 155 */     Set<BlockPos> blocks2 = createCircle(ability, world, pos2, ability.size);
/* 156 */     BlockPos pos3 = arenaCenter.func_177965_g(ability.distance).func_177970_e(ability.distance);
/* 157 */     Set<BlockPos> blocks3 = createCircle(ability, world, pos3, ability.size);
/* 158 */     BlockPos pos4 = arenaCenter.func_177985_f(ability.distance).func_177970_e(ability.distance);
/* 159 */     Set<BlockPos> blocks4 = createCircle(ability, world, pos4, ability.size);
/*     */     
/* 161 */     ability.pools.put(pos1, blocks1);
/* 162 */     ability.pools.put(pos2, blocks2);
/* 163 */     ability.pools.put(pos3, blocks3);
/* 164 */     ability.pools.put(pos4, blocks4);
/*     */   }
/*     */   
/*     */   private static void setupCirclePattern(GroundSeccoAbility ability, World world, BlockPos arenaCenter) {
/* 168 */     BlockPos pos1 = arenaCenter.func_177964_d(ability.distance);
/* 169 */     Set<BlockPos> blocks1 = createCircle(ability, world, pos1, ability.size);
/* 170 */     BlockPos pos2 = arenaCenter.func_177964_d(ability.distance).func_177965_g(ability.distance);
/* 171 */     Set<BlockPos> blocks2 = createCircle(ability, world, pos2, ability.size);
/* 172 */     BlockPos pos3 = arenaCenter.func_177965_g(ability.distance);
/* 173 */     Set<BlockPos> blocks3 = createCircle(ability, world, pos3, ability.size);
/* 174 */     BlockPos pos4 = arenaCenter.func_177970_e(ability.distance).func_177965_g(ability.distance);
/* 175 */     Set<BlockPos> blocks4 = createCircle(ability, world, pos4, ability.size);
/* 176 */     BlockPos pos5 = arenaCenter.func_177970_e(ability.distance);
/* 177 */     Set<BlockPos> blocks5 = createCircle(ability, world, pos5, ability.size);
/* 178 */     BlockPos pos6 = arenaCenter.func_177970_e(ability.distance).func_177985_f(ability.distance);
/* 179 */     Set<BlockPos> blocks6 = createCircle(ability, world, pos6, ability.size);
/* 180 */     BlockPos pos7 = arenaCenter.func_177985_f(ability.distance);
/* 181 */     Set<BlockPos> blocks7 = createCircle(ability, world, pos7, ability.size);
/* 182 */     BlockPos pos8 = arenaCenter.func_177964_d(ability.distance).func_177985_f(ability.distance);
/* 183 */     Set<BlockPos> blocks8 = createCircle(ability, world, pos8, ability.size);
/*     */     
/* 185 */     ability.pools.put(pos1, blocks1);
/* 186 */     ability.pools.put(pos2, blocks2);
/* 187 */     ability.pools.put(pos3, blocks3);
/* 188 */     ability.pools.put(pos4, blocks4);
/* 189 */     ability.pools.put(pos5, blocks5);
/* 190 */     ability.pools.put(pos6, blocks6);
/* 191 */     ability.pools.put(pos7, blocks7);
/* 192 */     ability.pools.put(pos8, blocks8);
/*     */   }
/*     */   
/*     */   private static void setupCenterPattern(GroundSeccoAbility ability, World world, BlockPos arenaCenter) {
/* 196 */     int size = ability.size * 2;
/* 197 */     Set<BlockPos> blocks = createCircle(ability, world, arenaCenter, size);
/* 198 */     ability.pools.put(arenaCenter, blocks);
/*     */   }
/*     */   
/*     */   private static void setupCrossPattern(GroundSeccoAbility ability, World world, BlockPos arenaCenter) {
/* 202 */     Set<BlockPos> blocks1 = createCircle(ability, world, arenaCenter, ability.size);
/* 203 */     BlockPos pos2 = arenaCenter.func_177964_d(ability.distance);
/* 204 */     Set<BlockPos> blocks2 = createCircle(ability, world, pos2, ability.size);
/* 205 */     BlockPos pos3 = arenaCenter.func_177970_e(ability.distance);
/* 206 */     Set<BlockPos> blocks3 = createCircle(ability, world, pos3, ability.size);
/* 207 */     BlockPos pos4 = arenaCenter.func_177965_g(ability.distance);
/* 208 */     Set<BlockPos> blocks4 = createCircle(ability, world, pos4, ability.size);
/* 209 */     BlockPos pos5 = arenaCenter.func_177985_f(ability.distance);
/* 210 */     Set<BlockPos> blocks5 = createCircle(ability, world, pos5, ability.size);
/*     */     
/* 212 */     ability.pools.put(arenaCenter, blocks1);
/* 213 */     ability.pools.put(pos2, blocks2);
/* 214 */     ability.pools.put(pos3, blocks3);
/* 215 */     ability.pools.put(pos4, blocks4);
/* 216 */     ability.pools.put(pos5, blocks5);
/*     */   }
/*     */   
/*     */   private static Set<BlockPos> createCircle(GroundSeccoAbility ability, World world, BlockPos pos, int size) {
/* 220 */     int finalSize = Math.round(size * ability.sizeMod);
/*     */     
/* 222 */     BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/* 223 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 224 */     BlockState state = ((Block)ModBlocks.SUNA_SAND.get()).func_176223_P();
/* 225 */     for (int i = -finalSize; i < finalSize; i++) {
/* 226 */       for (int j = -3; j < 3; j++) {
/* 227 */         for (int k = -finalSize; k < finalSize; k++) {
/* 228 */           if (i * i + k * k <= finalSize * finalSize) {
/*     */ 
/*     */ 
/*     */             
/* 232 */             mutpos.func_181079_c(pos.func_177958_n() + i, pos.func_177956_o() + j, pos.func_177952_p() + k);
/*     */             
/* 234 */             if (AbilityHelper.canPlaceBlock(world, mutpos.func_177958_n(), mutpos.func_177956_o(), mutpos.func_177952_p(), state, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID))
/* 235 */               blockPlacingHelper.addBlockPos(mutpos.func_185334_h(), i * i + j * j + k * k); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 240 */     return blockPlacingHelper.getBlockList();
/*     */   }
/*     */   
/*     */   public void changePattern(LivingEntity entity, Pattern pattern) {
/* 244 */     this.altModeComponent.setMode(entity, pattern);
/*     */   }
/*     */   
/*     */   public void changeRandomPattern(LivingEntity entity) {
/* 248 */     Pattern pattern = Pattern.values()[entity.func_70681_au().nextInt((Pattern.values()).length - 1)];
/* 249 */     this.altModeComponent.setMode(entity, pattern);
/*     */   } @FunctionalInterface
/*     */   private static interface IPatternFunction {
/*     */     void create(GroundSeccoAbility param1GroundSeccoAbility, World param1World, BlockPos param1BlockPos); } public void setSizeModifier(float mod) {
/* 253 */     this.sizeMod = mod;
/*     */   }
/*     */   
/*     */   public enum Pattern { static {
/* 257 */       CIRCLE = new Pattern("CIRCLE", 0, (x$0, x$1, x$2) -> GroundSeccoAbility.setupCirclePattern(x$0, x$1, x$2));
/* 258 */       CROSS = new Pattern("CROSS", 1, (x$0, x$1, x$2) -> GroundSeccoAbility.setupCrossPattern(x$0, x$1, x$2));
/* 259 */       CORNERS = new Pattern("CORNERS", 2, (x$0, x$1, x$2) -> GroundSeccoAbility.setupCornersPattern(x$0, x$1, x$2));
/* 260 */       CENTER = new Pattern("CENTER", 3, (x$0, x$1, x$2) -> GroundSeccoAbility.setupCenterPattern(x$0, x$1, x$2));
/*     */     }
/*     */     CIRCLE, CROSS, CORNERS, CENTER;
/*     */     private GroundSeccoAbility.IPatternFunction func;
/*     */     
/*     */     Pattern(GroundSeccoAbility.IPatternFunction func) {
/* 266 */       this.func = func;
/*     */     }
/*     */     
/*     */     public void create(GroundSeccoAbility ability, World world, BlockPos arenaCenter) {
/* 270 */       this.func.create(ability, world, arenaCenter);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\GroundSeccoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */