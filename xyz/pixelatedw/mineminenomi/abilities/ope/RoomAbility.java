/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.SphereEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ 
/*     */ public class RoomAbility extends Ability {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "room", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Creates a spherical space around the user in which they can manipulate anything or use other skills", null)
/*     */       });
/*  41 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new RegistryObject[] { ModBlocks.OPE }).setBypassGriefRule().build();
/*     */   
/*     */   public static final int MIN_ROOM_SIZE = 8;
/*     */   
/*     */   public static final int MAX_ROOM_SIZE = 45;
/*     */   public static final int MAX_THRESHOLD = 2;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final float MIN_COOLDOWN = 10.0F;
/*     */   private static final float MAX_COOLDOWN = 50.0F;
/*  50 */   public static final AbilityCore<RoomAbility> INSTANCE = (new AbilityCore.Builder("ROOM", AbilityCategory.DEVIL_FRUITS, RoomAbility::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F, 50.0F), ChargeComponent.getTooltip(20.0F)
/*  53 */       }).build();
/*     */   
/*  55 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  56 */     .addTickEvent(this::onChargeTick)
/*  57 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  59 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true))
/*  60 */     .addTickEvent(this::onContinuityTick)
/*  61 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*     */   private SphereEntity roomEntity;
/*     */   
/*  65 */   private List<BlockPos> blockList = new ArrayList<>();
/*  66 */   private List<BlockPos> placedBlockList = new ArrayList<>();
/*     */   
/*  68 */   private int roomSize = 0;
/*     */   
/*     */   private BlockPos centerPos;
/*     */   private boolean isShrinking = false;
/*  72 */   private Interval checkPositionInterval = new Interval(20);
/*  73 */   private Interval playSoundInterval = new Interval(18);
/*     */   
/*     */   public RoomAbility(AbilityCore<RoomAbility> core) {
/*  76 */     super(core);
/*     */     
/*  78 */     this.isNew = true;
/*     */     
/*  80 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent });
/*     */     
/*  82 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  86 */     if (this.chargeComponent.isCharging()) {
/*  87 */       this.chargeComponent.stopCharging(entity);
/*  88 */     } else if (this.continuousComponent.isContinuous()) {
/*  89 */       if (CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*  90 */         this.isShrinking = true;
/*  91 */         this.chargeComponent.startCharging(entity, 10.0F);
/*     */         
/*     */         return;
/*     */       } 
/*  95 */       this.continuousComponent.stopContinuity(entity);
/*     */     } else {
/*     */       
/*  98 */       this.checkPositionInterval.restartIntervalToZero();
/*     */       
/* 100 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.ROOM_CREATE_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */       
/* 102 */       this.isShrinking = false;
/* 103 */       setupSphere(entity);
/*     */       
/* 105 */       float entityHpDebuff = 2.0F - entity.func_110143_aJ() / entity.func_110138_aP();
/* 106 */       float chargeTime = 20.0F * entityHpDebuff;
/*     */       
/* 108 */       this.chargeComponent.startCharging(entity, chargeTime);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 113 */     chargeTickSphere(entity);
/*     */     
/* 115 */     if (this.playSoundInterval.canTick()) {
/* 116 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.ROOM_CHARGE_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 121 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 125 */     endChargeSphere(entity);
/* 126 */     endChargeBlocks(entity);
/*     */     
/* 128 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.ROOM_EXPAND_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */     
/* 130 */     this.continuousComponent.startContinuity(entity, -1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 134 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 138 */     if (!continuityTickSphere(entity)) {
/*     */       return;
/*     */     }
/*     */     
/* 142 */     if (!continuityTickBlocks(entity)) {
/*     */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 148 */     continuityEndBlocks(entity);
/* 149 */     continuityEndSphere(entity);
/*     */     
/* 151 */     this.centerPos = null;
/*     */     
/* 153 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.ROOM_CLOSE_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */     
/* 155 */     float entityHpDebuff = 2.0F - entity.func_110143_aJ() / entity.func_110138_aP();
/* 156 */     float roomSizeDebuff = (this.roomSize / 45);
/* 157 */     float cooldown = 10.0F * entityHpDebuff * roomSizeDebuff;
/* 158 */     cooldown = MathHelper.func_76131_a(cooldown, 10.0F, 50.0F);
/*     */     
/* 160 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupSphere(LivingEntity entity) {
/* 166 */     if (!CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 170 */     this.roomEntity = new SphereEntity(entity.field_70170_p, entity);
/* 171 */     this.roomEntity.setColor(new Color(0, 255, 255, 50));
/* 172 */     this.roomEntity.setRadius(0.0F);
/* 173 */     this.roomEntity.setDetailLevel(32);
/* 174 */     this.roomEntity.setAnimationSpeed(1);
/* 175 */     entity.field_70170_p.func_217376_c((Entity)this.roomEntity);
/* 176 */     this.centerPos = this.roomEntity.func_233580_cy_();
/*     */   }
/*     */   
/*     */   private void chargeTickSphere(LivingEntity entity) {
/* 180 */     if (!CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 184 */     if (this.roomEntity != null) {
/* 185 */       float radius = 0.0F;
/* 186 */       if (this.isShrinking) {
/* 187 */         radius = this.roomSize * (1.0F - this.chargeComponent.getChargePercentage()) / 45.0F;
/* 188 */         radius = EasingFunctionHelper.easeOutCubic(Float.valueOf(radius)) * 45.0F;
/*     */       } else {
/* 190 */         radius = 45.0F * this.chargeComponent.getChargePercentage() / 45.0F;
/* 191 */         radius = EasingFunctionHelper.easeInCubic(Float.valueOf(radius)) * 45.0F;
/*     */       } 
/* 193 */       this.roomEntity.setRadius(radius);
/* 194 */       this.roomSize = (int)radius;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargeSphere(LivingEntity entity) {
/* 199 */     if (!CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 203 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 207 */     if (this.isShrinking) {
/* 208 */       if (this.roomEntity != null) {
/* 209 */         this.roomEntity.func_70106_y();
/*     */       }
/* 211 */       this.roomEntity = null;
/*     */     } else {
/*     */       
/* 214 */       float radius = MathHelper.func_76131_a(45.0F * this.chargeComponent.getChargePercentage(), 8.0F, 45.0F);
/* 215 */       this.roomEntity.setRadius(radius);
/* 216 */       this.roomSize = (int)radius;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean continuityTickSphere(LivingEntity entity) {
/* 221 */     if (!CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/* 222 */       return true;
/*     */     }
/*     */     
/* 225 */     if (this.checkPositionInterval.canTick() && !isEntityInRoom((Entity)entity)) {
/* 226 */       this.continuousComponent.stopContinuity(entity);
/* 227 */       return false;
/*     */     } 
/*     */     
/* 230 */     if (this.roomEntity == null || !this.roomEntity.func_70089_S()) {
/* 231 */       this.continuousComponent.stopContinuity(entity);
/* 232 */       return false;
/*     */     } 
/*     */     
/* 235 */     return true;
/*     */   }
/*     */   
/*     */   private void continuityEndSphere(LivingEntity entity) {
/* 239 */     if (!CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 243 */     if (this.roomEntity != null) {
/* 244 */       this.roomEntity.func_70106_y();
/*     */     }
/* 246 */     this.roomEntity = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void endChargeBlocks(LivingEntity entity) {
/* 252 */     if (CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 256 */     if (this.blockList.isEmpty()) {
/* 257 */       this.roomSize = Math.max(8, (int)(45.0F * this.chargeComponent.getChargeTime() / this.chargeComponent.getMaxChargeTime()));
/*     */       
/* 259 */       this.centerPos = new BlockPos(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       
/* 261 */       this.blockList.addAll(AbilityHelper.createSphere(entity.field_70170_p, entity.func_233580_cy_(), this.roomSize, true, (Block)ModBlocks.OPE.get(), 0, GRIEF_RULE));
/*     */       
/* 263 */       this.placedBlockList.addAll(this.blockList);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean continuityTickBlocks(LivingEntity entity) {
/* 268 */     if (CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/* 269 */       return true;
/*     */     }
/*     */     
/* 272 */     if (this.centerPos != null && this.checkPositionInterval.canTick() && !isPositionInRoom(entity.func_233580_cy_())) {
/* 273 */       this.continuousComponent.stopContinuity(entity);
/* 274 */       return false;
/*     */     } 
/*     */     
/* 277 */     int placedBlocks = 0;
/*     */     
/* 279 */     Iterator<BlockPos> iter = this.placedBlockList.iterator();
/*     */     
/* 281 */     while (iter.hasNext()) {
/* 282 */       BlockPos pos = iter.next();
/*     */       
/* 284 */       entity.field_70170_p.func_184138_a(pos, Blocks.field_150350_a.func_176223_P(), ((Block)ModBlocks.OPE.get()).func_176223_P(), 0);
/*     */       
/* 286 */       iter.remove();
/*     */       
/* 288 */       placedBlocks++;
/*     */       
/* 290 */       if (placedBlocks > 512) {
/* 291 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 295 */     return true;
/*     */   }
/*     */   
/*     */   private void continuityEndBlocks(LivingEntity entity) {
/* 299 */     if (CommonConfig.INSTANCE.isExperiementalSpheresEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 303 */     for (BlockPos pos : this.blockList) {
/* 304 */       Block currentBlock = entity.field_70170_p.func_180495_p(pos).func_177230_c();
/*     */       
/* 306 */       if (currentBlock == ModBlocks.OPE.get()) {
/* 307 */         entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*     */       }
/*     */     } 
/*     */     
/* 311 */     this.blockList.clear();
/* 312 */     this.placedBlockList.clear();
/*     */   }
/*     */   
/*     */   public int getROOMSize() {
/* 316 */     return this.roomSize;
/*     */   }
/*     */   
/*     */   public BlockPos getCenterBlock() {
/* 320 */     return this.centerPos;
/*     */   }
/*     */   
/*     */   public boolean isEntityInRoom(Entity entity) {
/* 324 */     if (isPositionInRoom(entity.func_233580_cy_())) {
/* 325 */       return true;
/*     */     }
/*     */     
/* 328 */     if (this.roomEntity == null) {
/* 329 */       return false;
/*     */     }
/*     */     
/* 332 */     return (entity.func_70068_e((Entity)this.roomEntity) <= (this.roomSize * this.roomSize));
/*     */   }
/*     */   
/*     */   public boolean isPositionInRoom(BlockPos pos) {
/* 336 */     if (this.centerPos == null) {
/* 337 */       return false;
/*     */     }
/*     */     
/* 340 */     return pos.func_218141_a((Vector3i)this.centerPos, this.roomSize);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\RoomAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */