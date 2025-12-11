/*     */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TokudaiCandleAbility extends Ability {
/*  42 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).setCoordsOnly().build();
/*     */   
/*     */   private static final float COOLDOWN = 6000.0F;
/*     */   private static final float HOLD_TIME = 1200.0F;
/*     */   private static final int FIRST_LAYER_SIZE = 15;
/*     */   private static final int SECOND_LAYER_SIZE = 10;
/*     */   private static final int THIRD_LAYER_XZ_SIZE = 5;
/*     */   private static final int THIRD_LAYER_Y_SIZE = 10;
/*     */   private static final int FOURTH_LAYER_XZ_SIZE = 13;
/*     */   private static final int FOURTH_LAYER_Y_SIZE = 10;
/*     */   private static final float RANGE = 100.0F;
/*  53 */   public static final AbilityCore<TokudaiCandleAbility> INSTANCE = (new AbilityCore.Builder("Tokudai Candle", AbilityCategory.DEVIL_FRUITS, TokudaiCandleAbility::new))
/*  54 */     .build();
/*     */   
/*  56 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(100, this::onTickContinuityEvent).addEndEvent(100, this::onEndContinuityEvent);
/*  57 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(100, this::onTickChargeEvent).addEndEvent(100, this::onEndChargeEvent);
/*  58 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   private BlockPos centerPos;
/*     */   private Vector3d centerPosVec;
/*  62 */   private int blocksPerTick = 200;
/*  63 */   private List<BlockPos> posList = new ArrayList<>();
/*  64 */   private List<BlockPos> toPlaceList = new ArrayList<>();
/*     */   
/*     */   public TokudaiCandleAbility(AbilityCore<TokudaiCandleAbility> core) {
/*  67 */     super(core);
/*     */     
/*  69 */     this.isNew = true;
/*  70 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  72 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  76 */     if (this.continuousComponent.isContinuous()) {
/*  77 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     if (this.centerPos == null) {
/*  86 */       Vector3d startVec = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*  87 */       Vector3d endVec = startVec.func_178787_e(entity.func_70040_Z().func_186678_a(128.0D));
/*  88 */       BlockRayTraceResult result = entity.field_70170_p.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)entity));
/*  89 */       setCenterPos(result.func_216350_a());
/*     */     } 
/*     */     
/*  92 */     if (!this.posList.isEmpty()) {
/*  93 */       clearPlacedBlocks(entity.field_70170_p);
/*     */     }
/*     */     
/*  96 */     for (LivingEntity target : this.rangeComponent.getTargetsInArea(entity, 100.0F, null)) {
/*  97 */       Vector3d pushVec = target.func_213303_ch().func_178788_d(this.centerPosVec).func_72432_b().func_216372_d(10.0D, 1.0D, 10.0D);
/*  98 */       AbilityHelper.setDeltaMovement((Entity)target, pushVec.field_72450_a, (target.func_213322_ci()).field_72448_b, pushVec.field_72449_c);
/*     */     } 
/*     */     
/* 101 */     this.blocksPerTick = 100;
/*     */     
/* 103 */     BlockState defaultWaxState = ((Block)ModBlocks.WAX.get()).func_176223_P();
/* 104 */     this.posList.addAll(AbilityHelper.getFilledCubePositions(entity.field_70170_p, this.centerPos.func_177958_n(), this.centerPos.func_177956_o(), this.centerPos.func_177952_p(), 15, 15, 15, defaultWaxState, 0, GRIEF_RULE));
/* 105 */     this.posList.addAll(AbilityHelper.getFilledCubePositions(entity.field_70170_p, this.centerPos.func_177958_n(), (this.centerPos.func_177956_o() + 15), this.centerPos.func_177952_p(), 10, 10, 10, defaultWaxState, 0, GRIEF_RULE));
/* 106 */     this.posList.addAll(AbilityHelper.getFilledCubePositions(entity.field_70170_p, this.centerPos.func_177958_n(), (this.centerPos.func_177956_o() + 15 + 10), this.centerPos.func_177952_p(), 5, 10, 5, defaultWaxState, 0, GRIEF_RULE));
/* 107 */     this.posList.addAll(AbilityHelper.getFilledSpherePositions(entity.field_70170_p, this.centerPos.func_177958_n(), (this.centerPos.func_177956_o() + 15 + 10 + 10 + 6), this.centerPos.func_177952_p(), 13, 10, defaultWaxState, 0, GRIEF_RULE));
/*     */     
/* 109 */     int candleSplitDist = 9;
/* 110 */     Vector3i candleSize = new Vector3i(0, 4, 0);
/*     */     
/* 112 */     BlockPos candle1b = this.centerPos.func_177982_a(13, 15, 13);
/* 113 */     BlockPos candle1t = candle1b.func_177971_a(candleSize);
/* 114 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle1b, candle1t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 116 */     BlockPos candle2b = candle1b.func_177982_a(-9, 0, 0);
/* 117 */     BlockPos candle2t = candle2b.func_177971_a(candleSize);
/* 118 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle2b, candle2t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 120 */     BlockPos candle3b = candle2b.func_177982_a(-9, 0, 0);
/* 121 */     BlockPos candle3t = candle3b.func_177971_a(candleSize);
/* 122 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle3b, candle3t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 124 */     BlockPos candle4b = candle3b.func_177982_a(-9, 0, 0);
/* 125 */     BlockPos candle4t = candle4b.func_177971_a(candleSize);
/* 126 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle4b, candle4t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 128 */     BlockPos candle5b = candle4b.func_177982_a(0, 0, -9);
/* 129 */     BlockPos candle5t = candle5b.func_177971_a(candleSize);
/* 130 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle5b, candle5t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 132 */     BlockPos candle6b = candle5b.func_177982_a(0, 0, -9);
/* 133 */     BlockPos candle6t = candle6b.func_177971_a(candleSize);
/* 134 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle6b, candle6t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 136 */     BlockPos candle7b = candle6b.func_177982_a(0, 0, -9);
/* 137 */     BlockPos candle7t = candle7b.func_177971_a(candleSize);
/* 138 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle7b, candle7t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 140 */     BlockPos candle8b = candle7b.func_177982_a(9, 0, 0);
/* 141 */     BlockPos candle8t = candle8b.func_177971_a(candleSize);
/* 142 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle8b, candle8t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 144 */     BlockPos candle9b = candle8b.func_177982_a(9, 0, 0);
/* 145 */     BlockPos candle9t = candle9b.func_177971_a(candleSize);
/* 146 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle9b, candle9t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 148 */     BlockPos candle10b = candle9b.func_177982_a(9, 0, 0);
/* 149 */     BlockPos candle10t = candle10b.func_177971_a(candleSize);
/* 150 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle10b, candle10t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 152 */     BlockPos candle11b = candle10b.func_177982_a(0, 0, 9);
/* 153 */     BlockPos candle11t = candle11b.func_177971_a(candleSize);
/* 154 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle11b, candle11t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 156 */     BlockPos candle12b = candle11b.func_177982_a(0, 0, 9);
/* 157 */     BlockPos candle12t = candle12b.func_177971_a(candleSize);
/* 158 */     this.posList.addAll(StructuresHelper.fillCube(entity.field_70170_p, candle12b, candle12t, defaultWaxState, 3, GRIEF_RULE));
/*     */     
/* 160 */     this.posList = (List<BlockPos>)this.posList.stream().distinct().collect(Collectors.toList());
/* 161 */     this.toPlaceList.clear();
/* 162 */     this.toPlaceList.addAll(this.posList);
/*     */     
/* 164 */     float chargeTime = this.posList.size() / this.blocksPerTick;
/*     */     
/* 166 */     this.chargeComponent.startCharging(entity, chargeTime);
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickChargeEvent(LivingEntity entity, IAbility ability) {
/* 171 */     if (!entity.field_70170_p.field_72995_K && entity.field_70173_aa % 5 == 0) {
/* 172 */       Vector3d motion = entity.func_213303_ch().func_178787_e(entity.func_70040_Z().func_216372_d(5.0D, 5.0D, 5.0D)).func_178788_d(this.centerPosVec.func_72441_c(0.0D, 10.0D, 0.0D)).func_72432_b().func_216372_d(1.149999976158142D, 1.149999976158142D, 1.149999976158142D).func_216371_e();
/*     */       
/* 174 */       for (int i = 0; i < 15; i++) {
/* 175 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 176 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 177 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/* 179 */         double offsetMotionX = WyHelper.randomDouble() / 5.0D;
/* 180 */         double offsetMotionY = WyHelper.randomDouble() / 5.0D;
/* 181 */         double offsetMotionZ = WyHelper.randomDouble() / 5.0D;
/*     */         
/* 183 */         SimpleParticleData particle = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/*     */         
/* 185 */         particle.setLife(30);
/* 186 */         particle.setSize(3.0F);
/* 187 */         particle.setMotion(motion.field_72450_a + offsetMotionX, motion.field_72448_b + offsetMotionY, motion.field_72449_c + offsetMotionZ);
/*     */         
/* 189 */         WyHelper.spawnParticles((IParticleData)particle, (ServerWorld)entity.field_70170_p, entity.func_226277_ct_() + offsetX, entity.func_226278_cu_() + entity.func_70047_e() + offsetY, entity.func_226281_cx_() + offsetZ);
/*     */       } 
/*     */     } 
/*     */     
/* 193 */     Iterator<BlockPos> iter = this.toPlaceList.iterator();
/* 194 */     int placedBlocks = 0;
/* 195 */     while (iter.hasNext()) {
/* 196 */       BlockPos pos = iter.next();
/* 197 */       entity.field_70170_p.func_175656_a(pos, ((Block)ModBlocks.WAX.get()).func_176223_P());
/* 198 */       iter.remove();
/* 199 */       placedBlocks++;
/* 200 */       if (placedBlocks > this.blocksPerTick) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onEndChargeEvent(LivingEntity entity, IAbility ability) {
/* 207 */     if (this.centerPos != null) {
/* 208 */       int candleSplitDist = 9;
/*     */       
/* 210 */       BlockPos candle1 = this.centerPos.func_177982_a(13, 20, 13);
/* 211 */       entity.field_70170_p.func_180501_a(candle1, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 213 */       BlockPos candle2 = candle1.func_177982_a(-9, 0, 0);
/* 214 */       entity.field_70170_p.func_180501_a(candle2, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 216 */       BlockPos candle3 = candle2.func_177982_a(-9, 0, 0);
/* 217 */       entity.field_70170_p.func_180501_a(candle3, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 219 */       BlockPos candle4 = candle3.func_177982_a(-9, 0, 0);
/* 220 */       entity.field_70170_p.func_180501_a(candle4, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 222 */       BlockPos candle5 = candle4.func_177982_a(0, 0, -9);
/* 223 */       entity.field_70170_p.func_180501_a(candle5, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 225 */       BlockPos candle6 = candle5.func_177982_a(0, 0, -9);
/* 226 */       entity.field_70170_p.func_180501_a(candle6, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 228 */       BlockPos candle7 = candle6.func_177982_a(0, 0, -9);
/* 229 */       entity.field_70170_p.func_180501_a(candle7, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 231 */       BlockPos candle8 = candle7.func_177982_a(9, 0, 0);
/* 232 */       entity.field_70170_p.func_180501_a(candle8, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 234 */       BlockPos candle9 = candle8.func_177982_a(9, 0, 0);
/* 235 */       entity.field_70170_p.func_180501_a(candle9, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 237 */       BlockPos candle10 = candle9.func_177982_a(9, 0, 0);
/* 238 */       entity.field_70170_p.func_180501_a(candle10, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 240 */       BlockPos candle11 = candle10.func_177982_a(0, 0, 9);
/* 241 */       entity.field_70170_p.func_180501_a(candle11, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */       
/* 243 */       BlockPos candle12 = candle11.func_177982_a(0, 0, 9);
/* 244 */       entity.field_70170_p.func_180501_a(candle12, Blocks.field_150480_ab.func_176223_P(), 3);
/*     */     } 
/*     */     
/* 247 */     this.continuousComponent.startContinuity(entity, 1200.0F);
/* 248 */     if (!entity.field_70170_p.field_72995_K) {
/* 249 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.TOKUDAI_CANDLE.get(), (Entity)entity, this.centerPos.func_177958_n(), this.centerPos.func_177956_o(), this.centerPos.func_177952_p());
/*     */     }
/*     */   }
/*     */   
/*     */   private void onTickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 254 */     if (!entity.field_70170_p.field_72995_K && entity.field_70173_aa % 100 == 0) {
/* 255 */       for (LivingEntity target : this.rangeComponent.getTargetsInArea(entity, 100.0F, null)) {
/* 256 */         target.func_195064_c(new EffectInstance(Effects.field_76421_d, 100, 0));
/* 257 */         target.func_195064_c(new EffectInstance(Effects.field_76437_t, 100, 0));
/* 258 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.TOKUDAI_CANDLE_POISON.get(), 100, 0));
/*     */       } 
/* 260 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.TOKUDAI_CANDLE.get(), (Entity)entity, this.centerPos.func_177958_n(), this.centerPos.func_177956_o(), this.centerPos.func_177952_p());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onEndContinuityEvent(LivingEntity entity, IAbility ability) {
/* 265 */     clearPlacedBlocks(entity.field_70170_p);
/* 266 */     this.centerPos = null;
/* 267 */     this.cooldownComponent.startCooldown(entity, 6000.0F);
/*     */   }
/*     */   
/*     */   private void clearPlacedBlocks(World level) {
/* 271 */     for (BlockPos pos : this.posList) {
/* 272 */       Block currentBlock = level.func_180495_p(pos).func_177230_c();
/* 273 */       if (currentBlock == ModBlocks.WAX.get()) {
/* 274 */         level.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*     */       }
/*     */     } 
/* 277 */     this.posList.clear();
/*     */   }
/*     */   
/*     */   public void setBlocksPerTick(int blocks) {
/* 281 */     this.blocksPerTick = blocks;
/*     */   }
/*     */   
/*     */   public void setCenterPos(BlockPos centerPos) {
/* 285 */     this.centerPos = centerPos;
/* 286 */     this.centerPosVec = Vector3d.func_237489_a_((Vector3i)centerPos);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\TokudaiCandleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */