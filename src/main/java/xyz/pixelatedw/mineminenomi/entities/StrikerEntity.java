/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.shapes.IBooleanFunction;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.shapes.VoxelShapes;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncStrikerCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class StrikerEntity extends Entity {
/*  62 */   private static final DataParameter<Float> FUEL = EntityDataManager.func_187226_a(StrikerEntity.class, DataSerializers.field_187193_c);
/*     */   
/*     */   private static final float MAX_FUEL = 100.0F;
/*     */   private static final float SPEED = 0.07F;
/*     */   private static final float BACKWARDS_SPEED = -0.014F;
/*     */   private static final float TURN_SPEED = 0.5F;
/*     */   private static final byte HURT_EVENT = 100;
/*  69 */   private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   
/*     */   private float deltaRotation;
/*     */   private int lerpSteps;
/*     */   private double lerpX;
/*     */   private double lerpY;
/*     */   private double lerpZ;
/*     */   private double lerpYRot;
/*     */   private double lerpXRot;
/*     */   private double waterLevel;
/*     */   private Status status;
/*     */   private Status oldStatus;
/*     */   private double lastYd;
/*     */   private float invFriction;
/*     */   private float landFriction;
/*     */   @Nullable
/*     */   private UUID lastRiderUUID;
/*     */   @Nullable
/*     */   private PlayerEntity lastRider;
/*     */   private float damage;
/*     */   private int hurtTime;
/*     */   @Nullable
/*     */   private Crew crew;
/*     */   
/*     */   public StrikerEntity(World level) {
/*  94 */     super((EntityType)ModEntities.STRIKER.get(), level);
/*  95 */     this.field_70156_m = true;
/*     */   }
/*     */   
/*     */   public StrikerEntity(EntityType<?> type, World level) {
/*  99 */     super(type, level);
/* 100 */     this.field_70156_m = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 105 */     this.field_70180_af.func_187214_a(FUEL, Float.valueOf(0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeightForge(Pose pose, EntitySize size) {
/* 110 */     return size.field_220316_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241849_j(Entity entity) {
/* 115 */     return canVehicleCollide(this, entity);
/*     */   }
/*     */   
/*     */   public static boolean canVehicleCollide(Entity vehicle, Entity entity) {
/* 119 */     return ((entity.func_241845_aY() || entity.func_70104_M()) && !vehicle.func_184223_x(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 134 */     return func_70089_S();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRiderSit() {
/* 139 */     if (func_184179_bs() != null && func_184179_bs() instanceof LivingEntity && 
/* 140 */       isMeraUser((LivingEntity)func_184179_bs())) {
/* 141 */       return false;
/*     */     }
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225502_at_() {
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 153 */     if (func_184179_bs() != null && func_184179_bs() instanceof LivingEntity && 
/* 154 */       isMeraUser((LivingEntity)func_184179_bs())) {
/* 155 */       return 0.7D;
/*     */     }
/* 157 */     return 0.15D;
/*     */   }
/*     */ 
/*     */   
/*     */   public Direction func_184172_bi() {
/* 162 */     return func_174811_aO().func_176746_e();
/*     */   }
/*     */   
/*     */   private void tickLerp() {
/* 166 */     if (func_184186_bw()) {
/* 167 */       this.lerpSteps = 0;
/* 168 */       func_213312_b(func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*     */     } 
/*     */     
/* 171 */     if (this.lerpSteps > 0) {
/* 172 */       double d0 = func_226277_ct_() + (this.lerpX - func_226277_ct_()) / this.lerpSteps;
/* 173 */       double d1 = func_226278_cu_() + (this.lerpY - func_226278_cu_()) / this.lerpSteps;
/* 174 */       double d2 = func_226281_cx_() + (this.lerpZ - func_226281_cx_()) / this.lerpSteps;
/* 175 */       double d3 = MathHelper.func_76138_g(this.lerpYRot - this.field_70177_z);
/* 176 */       this.field_70177_z = (float)(this.field_70177_z + d3 / this.lerpSteps);
/* 177 */       this.field_70125_A = (float)(this.field_70125_A + (this.lerpXRot - this.field_70125_A) / this.lerpSteps);
/* 178 */       this.lerpSteps--;
/* 179 */       func_70107_b(d0, d1, d2);
/* 180 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 186 */     this.oldStatus = this.status;
/* 187 */     this.status = getStatus();
/*     */     
/* 189 */     super.func_70071_h_();
/* 190 */     tickLerp();
/*     */     
/* 192 */     if (func_184179_bs() != null && (this.lastRiderUUID == null || 
/* 193 */       !this.lastRiderUUID.equals(func_184179_bs().func_110124_au()))) {
/* 194 */       this.lastRiderUUID = func_184179_bs().func_110124_au();
/*     */     }
/*     */     
/* 197 */     if (!this.field_70170_p.field_72995_K) {
/* 198 */       consumeFuel();
/*     */     }
/*     */     
/* 201 */     if (getHurtTime() > 0) {
/* 202 */       setHurtTime(getHurtTime() - 1);
/*     */     }
/*     */     
/* 205 */     if (getDamage() > 0.0F) {
/* 206 */       setDamage(getDamage() - 1.0F);
/*     */     }
/*     */     
/* 209 */     if (func_184186_bw()) {
/* 210 */       this.invFriction = 0.05F;
/* 211 */       double gravity = func_189652_ae() ? 0.0D : -0.03999999910593033D;
/* 212 */       double d2 = 0.0D;
/* 213 */       if (this.oldStatus == Status.IN_AIR && this.status != Status.IN_AIR && this.status != Status.ON_LAND) {
/* 214 */         this.waterLevel = func_226283_e_(1.0D);
/* 215 */         func_70107_b(func_226277_ct_(), (getWaterLevelAbove() - func_213302_cg()) + 0.101D, func_226281_cx_());
/* 216 */         AbilityHelper.setDeltaMovement(this, func_213322_ci().func_216372_d(1.0D, 0.0D, 1.0D));
/* 217 */         this.lastYd = 0.0D;
/* 218 */         this.status = Status.IN_WATER;
/*     */       
/*     */       }
/* 221 */       else if (this.status == Status.IN_WATER) {
/* 222 */         d2 = (this.waterLevel - func_226278_cu_()) / func_213302_cg();
/* 223 */         this.invFriction = 0.9F;
/*     */       }
/* 225 */       else if (this.status == Status.UNDER_FLOWING_WATER) {
/* 226 */         gravity = -7.0E-4D;
/* 227 */         this.invFriction = 0.9F;
/*     */       }
/* 229 */       else if (this.status == Status.UNDER_WATER) {
/* 230 */         d2 = 0.009999999776482582D;
/* 231 */         this.invFriction = 0.45F;
/*     */       }
/* 233 */       else if (this.status == Status.IN_AIR) {
/* 234 */         this.invFriction = 0.9F;
/*     */       }
/* 236 */       else if (this.status == Status.ON_LAND) {
/* 237 */         this.invFriction = this.landFriction;
/* 238 */         if (func_184179_bs() instanceof PlayerEntity) {
/* 239 */           this.landFriction /= 2.0F;
/*     */         }
/*     */       } 
/*     */       
/* 243 */       Vector3d motion = func_213322_ci();
/* 244 */       AbilityHelper.setDeltaMovement(this, motion.field_72450_a * this.invFriction, motion.field_72448_b + gravity, motion.field_72449_c * this.invFriction);
/* 245 */       this.deltaRotation *= this.invFriction;
/* 246 */       if (d2 > 0.0D) {
/* 247 */         motion = func_213322_ci();
/* 248 */         AbilityHelper.setDeltaMovement(this, motion.field_72450_a, (motion.field_72448_b + d2 * 0.061D) * 0.75D, motion.field_72449_c);
/*     */       } 
/*     */       
/* 251 */       if (func_184179_bs() != null && func_184179_bs() instanceof LivingEntity && hasFuel((LivingEntity)func_184179_bs())) {
/* 252 */         controlVehicle();
/*     */       }
/*     */       
/* 255 */       func_213315_a(MoverType.SELF, func_213322_ci());
/*     */     } else {
/* 257 */       AbilityHelper.setDeltaMovement(this, Vector3d.field_186680_a);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 264 */     switch (id) {
/*     */       case 100:
/* 266 */         this.hurtTime = 10;
/*     */         break;
/*     */     } 
/* 269 */     super.func_70103_a(id);
/*     */   }
/*     */   
/*     */   private void consumeFuel() {
/* 273 */     Entity passanger = func_184179_bs();
/* 274 */     if (passanger != null && passanger instanceof PlayerEntity) {
/* 275 */       PlayerEntity player = (PlayerEntity)passanger;
/* 276 */       if (isMeraUser((LivingEntity)player)) {
/*     */         return;
/*     */       }
/* 279 */       float speed = 0.0F;
/* 280 */       if (player.field_191988_bg > 0.0F) {
/* 281 */         speed = 0.07F;
/*     */       }
/* 283 */       else if (player.field_191988_bg < 0.0F) {
/* 284 */         speed = -0.014F;
/*     */       } 
/* 286 */       float fuel = getFuel() - Math.abs(speed / 4.0F);
/* 287 */       fuel = MathHelper.func_76131_a(fuel, 0.0F, 100.0F);
/* 288 */       setFuel(fuel);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184232_k(Entity passenger) {
/* 294 */     if (func_184196_w(passenger)) {
/* 295 */       float f = 0.0F;
/* 296 */       float f1 = (float)((this.field_70128_L ? 0.009999999776482582D : func_70042_X()) + passenger.func_70033_W());
/* 297 */       if (func_184188_bt().size() > 1) {
/* 298 */         int i = func_184188_bt().indexOf(passenger);
/* 299 */         if (i == 0) {
/* 300 */           f = 0.2F;
/*     */         } else {
/*     */           
/* 303 */           f = -0.6F;
/*     */         } 
/*     */         
/* 306 */         if (passenger instanceof AnimalEntity) {
/* 307 */           f = (float)(f + 0.2D);
/*     */         }
/*     */       } 
/*     */       
/* 311 */       Vector3d vector3d = (new Vector3d(f, 0.0D, 0.0D)).func_178785_b(-this.field_70177_z * 0.017453292F - 1.5707964F);
/* 312 */       passenger.func_70107_b(func_226277_ct_() + vector3d.field_72450_a, func_226278_cu_() + f1, func_226281_cx_() + vector3d.field_72449_c);
/* 313 */       passenger.field_70177_z += this.deltaRotation;
/* 314 */       passenger.func_70034_d(passenger.func_70079_am() + this.deltaRotation);
/* 315 */       clampRotation(passenger);
/* 316 */       if (passenger instanceof AnimalEntity && func_184188_bt().size() > 1) {
/* 317 */         int j = (passenger.func_145782_y() % 2 == 0) ? 90 : 270;
/* 318 */         passenger.func_181013_g(((AnimalEntity)passenger).field_70761_aq + j);
/* 319 */         passenger.func_70034_d(passenger.func_70079_am() + j);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Status getStatus() {
/* 325 */     Status status = isUnderwater();
/* 326 */     if (status != null) {
/* 327 */       this.waterLevel = (func_174813_aQ()).field_72337_e;
/* 328 */       return status;
/*     */     } 
/* 330 */     if (checkInWater()) {
/* 331 */       return Status.IN_WATER;
/*     */     }
/*     */     
/* 334 */     float f = getGroundFriction();
/* 335 */     if (f > 0.0F) {
/* 336 */       this.landFriction = f;
/* 337 */       return Status.ON_LAND;
/*     */     } 
/*     */     
/* 340 */     return Status.IN_AIR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void controlVehicle() {
/* 346 */     if (func_184207_aI()) {
/* 347 */       Entity passanger = func_184179_bs();
/* 348 */       if (passanger == null || !(passanger instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/* 351 */       PlayerEntity player = (PlayerEntity)passanger;
/*     */       
/* 353 */       if (player.field_70702_br > 0.0F) {
/* 354 */         this.deltaRotation -= 0.5F;
/*     */       }
/* 356 */       else if (player.field_70702_br < 0.0F) {
/* 357 */         this.deltaRotation += 0.5F;
/*     */       } 
/*     */       
/* 360 */       this.field_70177_z += this.deltaRotation;
/* 361 */       float speed = 0.0F;
/* 362 */       if (player.field_191988_bg > 0.0F) {
/* 363 */         speed = 0.07F;
/*     */       }
/* 365 */       else if (player.field_191988_bg < 0.0F) {
/* 366 */         speed = -0.014F;
/*     */       } 
/*     */       
/* 369 */       AbilityHelper.setDeltaMovement(this, func_213322_ci().func_72441_c((MathHelper.func_76126_a(-this.field_70177_z * 0.017453292F) * speed), 0.0D, (MathHelper.func_76134_b(this.field_70177_z * 0.017453292F) * speed)));
/*     */       
/* 371 */       if (isMeraUser((LivingEntity)player)) {
/* 372 */         for (int i = 0; i < 5; i++) {
/* 373 */           double offsetX = WyHelper.randomDouble() / 2.5D;
/* 374 */           double offsetY = WyHelper.randomDouble() / 4.0D;
/* 375 */           double offsetZ = WyHelper.randomDouble() / 2.5D;
/*     */           
/* 377 */           SimpleParticleData proj = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 378 */           proj.setLife(5);
/* 379 */           proj.setSize(3.0F);
/* 380 */           player.field_70170_p.func_195594_a((IParticleData)proj, func_226277_ct_() + offsetX, func_226278_cu_() + 0.5D + offsetY, func_226281_cx_() + offsetZ, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */       
/* 384 */       if (speed > 0.0F) {
/* 385 */         double yRot = MathHelper.func_76138_g(this.field_70177_z * 0.017453292519943295D) + 0.8D;
/* 386 */         double dist = 1.5D;
/* 387 */         double xp = dist * Math.cos(yRot) - dist * Math.sin(yRot);
/* 388 */         double zp = dist * Math.cos(yRot) + dist * Math.sin(yRot);
/* 389 */         for (int i = 0; i < 20; i++) {
/* 390 */           double offsetX = WyHelper.randomDouble() / 2.5D - xp;
/* 391 */           double offsetY = WyHelper.randomDouble() / 2.5D;
/* 392 */           double offsetZ = WyHelper.randomDouble() / 2.5D - zp;
/*     */           
/* 394 */           SimpleParticleData proj = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 395 */           proj.setLife(10);
/* 396 */           proj.setSize(4.0F);
/* 397 */           proj.setMotion(-(func_213322_ci()).field_72450_a, -(func_213322_ci()).field_72448_b, -(func_213322_ci()).field_72449_c);
/* 398 */           player.field_70170_p.func_195594_a((IParticleData)proj, func_226277_ct_() + offsetX, func_226278_cu_() + 0.5D + offsetY, func_226281_cx_() + offsetZ, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 407 */     List<Entity> list = func_184188_bt();
/* 408 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_184230_a(PlayerEntity player, Hand hand) {
/* 413 */     ItemStack heldItem = player.func_184614_ca();
/* 414 */     if (hand.equals(Hand.OFF_HAND)) {
/* 415 */       return ActionResultType.PASS;
/*     */     }
/* 417 */     if (player.func_213453_ef() && !player.field_70170_p.field_72995_K) {
/* 418 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.INFO_FUEL_LEFT, new Object[] { String.format("%.2f", new Object[] { Float.valueOf(getFuel()) }) + "%" }), Util.field_240973_b_);
/* 419 */       return ActionResultType.CONSUME;
/*     */     } 
/* 421 */     if (heldItem != null && !heldItem.func_190926_b() && heldItem.func_77973_b().equals(((Block)ModBlocks.FLAME_DIAL.get()).func_199767_j()) && getFuel() < 100.0F) {
/* 422 */       while (getFuel() < 100.0F && 
/* 423 */         !heldItem.func_190926_b()) {
/*     */ 
/*     */         
/* 426 */         if (!player.field_71075_bZ.field_75098_d) {
/* 427 */           heldItem.func_190918_g(1);
/*     */         }
/* 429 */         setFuel(getFuel() + 25.0F);
/*     */       } 
/* 431 */       return ActionResultType.CONSUME;
/*     */     } 
/* 433 */     if (!this.field_70170_p.field_72995_K) {
/* 434 */       if (player.func_184220_m(this)) {
/* 435 */         Crew crew = ExtendedWorldData.get().getCrewWithMember(player.func_110124_au());
/* 436 */         if (crew != null) {
/* 437 */           this.crew = crew;
/* 438 */           WyNetwork.sendToAllTracking(new SSyncStrikerCrewPacket(this, crew), this);
/*     */         } 
/* 440 */         return ActionResultType.CONSUME;
/*     */       } 
/* 442 */       return ActionResultType.PASS;
/*     */     } 
/*     */     
/* 445 */     return ActionResultType.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 451 */     if (func_180431_b(source)) {
/* 452 */       return false;
/*     */     }
/* 454 */     if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
/* 455 */       this.field_70170_p.func_72960_a(this, (byte)100);
/* 456 */       setDamage(getDamage() + amount * 10.0F);
/* 457 */       func_70018_K();
/*     */       
/* 459 */       boolean flag = (source.func_76346_g() instanceof PlayerEntity && ((PlayerEntity)source.func_76346_g()).field_71075_bZ.field_75098_d);
/* 460 */       if (flag || getDamage() > 30.0F) {
/* 461 */         if (!flag && this.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223604_g)) {
/* 462 */           func_199701_a_(((Item)ModItems.STRIKER.get()).func_190903_i());
/*     */         }
/*     */         
/* 465 */         func_70106_y();
/*     */       } 
/*     */       
/* 468 */       return true;
/*     */     } 
/*     */     
/* 471 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_203002_i(boolean isDownwards) {}
/*     */ 
/*     */   
/*     */   private boolean isMeraUser(LivingEntity entity) {
/* 480 */     IDevilFruit props = DevilFruitCapability.get(entity);
/* 481 */     if (props.hasDevilFruit(ModAbilities.MERA_MERA_NO_MI)) {
/* 482 */       return true;
/*     */     }
/* 484 */     return false;
/*     */   }
/*     */   
/*     */   private boolean hasFuel(LivingEntity entity) {
/* 488 */     return isMeraUser(entity) ? true : ((getFuel() > 0.0F));
/*     */   }
/*     */   
/*     */   public float getGroundFriction() {
/* 492 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 493 */     AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b - 0.001D, axisalignedbb.field_72339_c, axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f);
/*     */     
/* 495 */     int i = MathHelper.func_76128_c(axisalignedbb1.field_72340_a) - 1;
/* 496 */     int j = MathHelper.func_76143_f(axisalignedbb1.field_72336_d) + 1;
/* 497 */     int k = MathHelper.func_76128_c(axisalignedbb1.field_72338_b) - 1;
/* 498 */     int l = MathHelper.func_76143_f(axisalignedbb1.field_72337_e) + 1;
/* 499 */     int i1 = MathHelper.func_76128_c(axisalignedbb1.field_72339_c) - 1;
/* 500 */     int j1 = MathHelper.func_76143_f(axisalignedbb1.field_72334_f) + 1;
/* 501 */     VoxelShape voxelshape = VoxelShapes.func_197881_a(axisalignedbb1);
/* 502 */     float f = 0.0F;
/* 503 */     int k1 = 0;
/* 504 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 506 */     for (int l1 = i; l1 < j; l1++) {
/* 507 */       for (int i2 = i1; i2 < j1; i2++) {
/* 508 */         int j2 = ((l1 != i && l1 != j - 1) ? 0 : 1) + ((i2 != i1 && i2 != j1 - 1) ? 0 : 1);
/* 509 */         if (j2 != 2) {
/* 510 */           for (int k2 = k; k2 < l; k2++) {
/* 511 */             if (j2 <= 0 || (k2 != k && k2 != l - 1)) {
/* 512 */               blockpos$mutable.func_181079_c(l1, k2, i2);
/* 513 */               BlockState blockstate = this.field_70170_p.func_180495_p((BlockPos)blockpos$mutable);
/* 514 */               if (!(blockstate.func_177230_c() instanceof net.minecraft.block.LilyPadBlock) && 
/* 515 */                 VoxelShapes.func_197879_c(blockstate.func_196952_d((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable).func_197751_a(l1, k2, i2), voxelshape, IBooleanFunction.field_223238_i_)) {
/* 516 */                 f += blockstate.getSlipperiness((IWorldReader)this.field_70170_p, (BlockPos)blockpos$mutable, this);
/* 517 */                 k1++;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 525 */     return f / k1;
/*     */   }
/*     */   
/*     */   public float getWaterLevelAbove() {
/* 529 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 530 */     int i = MathHelper.func_76128_c(axisalignedbb.field_72340_a);
/* 531 */     int j = MathHelper.func_76143_f(axisalignedbb.field_72336_d);
/* 532 */     int k = MathHelper.func_76128_c(axisalignedbb.field_72337_e);
/* 533 */     int l = MathHelper.func_76143_f(axisalignedbb.field_72337_e - this.lastYd);
/* 534 */     int i1 = MathHelper.func_76128_c(axisalignedbb.field_72339_c);
/* 535 */     int j1 = MathHelper.func_76143_f(axisalignedbb.field_72334_f);
/* 536 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/*     */     int k1;
/* 539 */     label24: for (k1 = k; k1 < l; k1++) {
/* 540 */       float f = 0.0F;
/*     */       
/* 542 */       for (int l1 = i; l1 < j; l1++) {
/* 543 */         for (int i2 = i1; i2 < j1; i2++) {
/* 544 */           blockpos$mutable.func_181079_c(l1, k1, i2);
/* 545 */           FluidState fluidstate = this.field_70170_p.func_204610_c((BlockPos)blockpos$mutable);
/* 546 */           if (fluidstate.func_206884_a((ITag)FluidTags.field_206959_a)) {
/* 547 */             f = Math.max(f, fluidstate.func_215679_a((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable));
/*     */           }
/*     */           
/* 550 */           if (f >= 1.0F) {
/*     */             continue label24;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 556 */       if (f < 1.0F) {
/* 557 */         return blockpos$mutable.func_177956_o() + f;
/*     */       }
/*     */     } 
/*     */     
/* 561 */     return (l + 1);
/*     */   }
/*     */   private boolean checkInWater() {
/*     */     int m;
/* 565 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 566 */     int i = MathHelper.func_76128_c(axisalignedbb.field_72340_a);
/* 567 */     int j = MathHelper.func_76143_f(axisalignedbb.field_72336_d);
/* 568 */     int k = MathHelper.func_76128_c(axisalignedbb.field_72338_b);
/* 569 */     int l = MathHelper.func_76143_f(axisalignedbb.field_72338_b + 0.001D);
/* 570 */     int i1 = MathHelper.func_76128_c(axisalignedbb.field_72339_c);
/* 571 */     int j1 = MathHelper.func_76143_f(axisalignedbb.field_72334_f);
/* 572 */     boolean flag = false;
/* 573 */     this.waterLevel = Double.MIN_VALUE;
/* 574 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 576 */     for (int k1 = i; k1 < j; k1++) {
/* 577 */       for (int l1 = k; l1 < l; l1++) {
/* 578 */         for (int i2 = i1; i2 < j1; i2++) {
/* 579 */           blockpos$mutable.func_181079_c(k1, l1, i2);
/* 580 */           FluidState fluidstate = this.field_70170_p.func_204610_c((BlockPos)blockpos$mutable);
/* 581 */           if (fluidstate.func_206884_a((ITag)FluidTags.field_206959_a)) {
/* 582 */             float f = l1 + fluidstate.func_215679_a((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable);
/* 583 */             this.waterLevel = Math.max(f, this.waterLevel);
/* 584 */             m = flag | ((axisalignedbb.field_72338_b < f) ? 1 : 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 590 */     return m;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Status isUnderwater() {
/* 595 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 596 */     double d0 = axisalignedbb.field_72337_e + 0.001D;
/* 597 */     int i = MathHelper.func_76128_c(axisalignedbb.field_72340_a);
/* 598 */     int j = MathHelper.func_76143_f(axisalignedbb.field_72336_d);
/* 599 */     int k = MathHelper.func_76128_c(axisalignedbb.field_72337_e);
/* 600 */     int l = MathHelper.func_76143_f(d0);
/* 601 */     int i1 = MathHelper.func_76128_c(axisalignedbb.field_72339_c);
/* 602 */     int j1 = MathHelper.func_76143_f(axisalignedbb.field_72334_f);
/* 603 */     boolean flag = false;
/* 604 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 606 */     for (int k1 = i; k1 < j; k1++) {
/* 607 */       for (int l1 = k; l1 < l; l1++) {
/* 608 */         for (int i2 = i1; i2 < j1; i2++) {
/* 609 */           blockpos$mutable.func_181079_c(k1, l1, i2);
/* 610 */           FluidState fluidstate = this.field_70170_p.func_204610_c((BlockPos)blockpos$mutable);
/* 611 */           if (fluidstate.func_206884_a((ITag)FluidTags.field_206959_a) && d0 < (blockpos$mutable.func_177956_o() + fluidstate.func_215679_a((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable))) {
/* 612 */             if (!fluidstate.func_206889_d()) {
/* 613 */               return Status.UNDER_FLOWING_WATER;
/*     */             }
/*     */             
/* 616 */             flag = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 622 */     return flag ? Status.UNDER_WATER : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70108_f(Entity entity) {
/* 627 */     if (entity instanceof StrikerEntity) {
/* 628 */       if ((entity.func_174813_aQ()).field_72338_b < (func_174813_aQ()).field_72337_e) {
/* 629 */         super.func_70108_f(entity);
/*     */       }
/*     */     }
/* 632 */     else if ((entity.func_174813_aQ()).field_72338_b <= (func_174813_aQ()).field_72338_b) {
/* 633 */       super.func_70108_f(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void clampRotation(Entity pEntityToUpdate) {
/* 638 */     pEntityToUpdate.func_181013_g(this.field_70177_z);
/* 639 */     float f = MathHelper.func_76142_g(pEntityToUpdate.field_70177_z - this.field_70177_z);
/* 640 */     float f1 = MathHelper.func_76131_a(f, -105.0F, 105.0F);
/* 641 */     pEntityToUpdate.field_70126_B += f1 - f;
/* 642 */     pEntityToUpdate.field_70177_z += f1 - f;
/* 643 */     pEntityToUpdate.func_70034_d(pEntityToUpdate.field_70177_z);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_145775_I() {
/* 648 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 649 */     BlockPos blockpos = new BlockPos(axisalignedbb.field_72340_a + 0.001D, axisalignedbb.field_72338_b + 0.001D, axisalignedbb.field_72339_c + 0.001D);
/* 650 */     BlockPos blockpos1 = new BlockPos(axisalignedbb.field_72336_d - 0.001D, axisalignedbb.field_72337_e - 0.001D, axisalignedbb.field_72334_f - 0.001D);
/* 651 */     BlockPos.Mutable pos = new BlockPos.Mutable();
/* 652 */     if (this.field_70170_p.func_175707_a(blockpos, blockpos1)) {
/* 653 */       for (int i = blockpos.func_177958_n(); i <= blockpos1.func_177958_n(); i++) {
/* 654 */         for (int j = blockpos.func_177956_o(); j <= blockpos1.func_177956_o(); j++) {
/* 655 */           for (int k = blockpos.func_177952_p(); k <= blockpos1.func_177952_p(); k++) {
/* 656 */             pos.func_181079_c(i, j, k);
/* 657 */             BlockState state = this.field_70170_p.func_180495_p((BlockPos)pos);
/*     */             
/* 659 */             if (state.func_177230_c() == Blocks.field_196651_dG) {
/* 660 */               this.field_70170_p.func_225521_a_((BlockPos)pos, true, this);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_184190_l(Entity entity) {
/* 671 */     clampRotation(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getPickedResult(RayTraceResult target) {
/* 676 */     return ((Item)ModItems.STRIKER.get()).func_199767_j().func_190903_i();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public AxisAlignedBB func_184177_bl() {
/* 682 */     AxisAlignedBB aabb = super.func_184177_bl();
/* 683 */     aabb = aabb.func_72314_b(0.0D, 3.0D, 0.0D);
/* 684 */     return aabb;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT nbt) {
/* 689 */     setFuel(nbt.func_74760_g("fuel"));
/* 690 */     if (nbt.func_186855_b("lastRiderId")) {
/* 691 */       this.lastRiderUUID = nbt.func_186857_a("lastRiderId");
/*     */     }
/* 693 */     if (nbt.func_150297_b("crew", 10)) {
/* 694 */       Crew crew = Crew.from(nbt.func_74775_l("crew"));
/* 695 */       if (crew != null) {
/* 696 */         this.crew = crew;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT nbt) {
/* 703 */     nbt.func_74776_a("fuel", getFuel());
/* 704 */     if (getLastRiderId() != null) {
/* 705 */       nbt.func_186854_a("lastRiderId", getLastRiderId());
/*     */     }
/* 707 */     if (this.crew != null) {
/* 708 */       nbt.func_218657_a("crew", (INBT)this.crew.write());
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getLastRiderId() {
/* 714 */     return this.lastRiderUUID;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PlayerEntity getLastRider() {
/* 719 */     if (this.lastRider == null && this.lastRiderUUID != null) {
/* 720 */       this.lastRider = this.field_70170_p.func_217371_b(this.lastRiderUUID);
/*     */     }
/* 722 */     return this.lastRider;
/*     */   }
/*     */   
/*     */   public void setFuel(float fuel) {
/* 726 */     this.field_70180_af.func_187227_b(FUEL, Float.valueOf(fuel));
/*     */   }
/*     */   
/*     */   public float getFuel() {
/* 730 */     return ((Float)this.field_70180_af.func_187225_a(FUEL)).floatValue();
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 734 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setDamage(float damage) {
/* 738 */     this.damage = damage;
/*     */   }
/*     */   
/*     */   public int getHurtTime() {
/* 742 */     return this.hurtTime;
/*     */   }
/*     */   
/*     */   public void setHurtTime(int hurtTime) {
/* 746 */     this.hurtTime = hurtTime;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Crew getCrew() {
/* 751 */     return this.crew;
/*     */   }
/*     */   
/*     */   public void setCrew(Crew crew) {
/* 755 */     this.crew = crew;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 760 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */   
/*     */   public enum Status {
/* 764 */     IN_WATER,
/* 765 */     UNDER_WATER,
/* 766 */     UNDER_FLOWING_WATER,
/* 767 */     ON_LAND,
/* 768 */     IN_AIR;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\StrikerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */