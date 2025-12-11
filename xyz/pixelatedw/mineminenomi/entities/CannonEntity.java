/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IVehicleAltMode;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CannonEntity extends Entity implements IVehicleAltMode {
/*  54 */   private static final DataParameter<Byte> MODE = EntityDataManager.func_187226_a(CannonEntity.class, DataSerializers.field_187191_a);
/*     */   
/*  56 */   private static final TranslationTextComponent GUNPOWDER_INFO = new TranslationTextComponent(ModI18n.INFO_NEEDS_GUNPOWDER_LOADED);
/*  57 */   private static final TranslationTextComponent CANNONBALL_INFO = new TranslationTextComponent(ModI18n.INFO_NEEDS_CANNONBALL_LOADED);
/*     */   
/*     */   private static final float MOVE_SPEED = 0.05F;
/*     */   
/*     */   private static final float BACKWARDS_MOVE_SPEED = -0.03F;
/*     */   private static final float YAW_AIM_SPEED = 1.0F;
/*     */   private static final float PITCH_AIM_SPEED = 1.0F;
/*     */   private static final float MOVEMENT_FRICTION = 0.1F;
/*     */   private static final float ROTATION_FRICTION = 0.1F;
/*     */   private static final float GRAVITY = -0.4F;
/*     */   private static final float DEFAULT_COOLDOWN = 10.0F;
/*     */   private static final byte HURT_EVENT = 100;
/*     */   private float deltaYaw;
/*     */   private float deltaPitch;
/*     */   private int lerpSteps;
/*     */   private double lerpX;
/*     */   private double lerpY;
/*     */   private double lerpZ;
/*     */   private double lerpYRot;
/*     */   private double lerpXRot;
/*     */   private float cooldown;
/*     */   private float damage;
/*     */   private int hurtTime;
/*     */   
/*     */   public CannonEntity(World level) {
/*  82 */     super((EntityType)ModEntities.CANNON.get(), level);
/*  83 */     this.field_70156_m = true;
/*     */   }
/*     */   
/*     */   public CannonEntity(EntityType<?> type, World level) {
/*  87 */     super(type, level);
/*  88 */     this.field_70156_m = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  93 */     this.field_70180_af.func_187214_a(MODE, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeightForge(Pose pose, EntitySize size) {
/*  98 */     return size.field_220316_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241849_j(Entity entity) {
/* 103 */     return canVehicleCollide(this, entity);
/*     */   }
/*     */   
/*     */   public static boolean canVehicleCollide(Entity vehicle, Entity entity) {
/* 107 */     if (entity.func_226278_cu_() < vehicle.func_226278_cu_()) {
/* 108 */       return false;
/*     */     }
/* 110 */     return ((entity.func_241845_aY() || entity.func_70104_M()) && !vehicle.func_184223_x(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 125 */     return func_70089_S();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRiderSit() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225502_at_() {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 140 */     return 0.35D;
/*     */   }
/*     */ 
/*     */   
/*     */   public Direction func_184172_bi() {
/* 145 */     return func_174811_aO().func_176746_e();
/*     */   }
/*     */   
/*     */   private void tickLerp() {
/* 149 */     if (func_184186_bw()) {
/* 150 */       this.lerpSteps = 0;
/* 151 */       func_213312_b(func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*     */     } 
/*     */     
/* 154 */     if (this.lerpSteps > 0) {
/* 155 */       double d0 = func_226277_ct_() + (this.lerpX - func_226277_ct_()) / this.lerpSteps;
/* 156 */       double d1 = func_226278_cu_() + (this.lerpY - func_226278_cu_()) / this.lerpSteps;
/* 157 */       double d2 = func_226281_cx_() + (this.lerpZ - func_226281_cx_()) / this.lerpSteps;
/* 158 */       double d3 = MathHelper.func_76138_g(this.lerpYRot - this.field_70177_z);
/* 159 */       this.field_70177_z = (float)(this.field_70177_z + d3 / this.lerpSteps);
/* 160 */       this.field_70125_A = (float)(this.field_70125_A + (this.lerpXRot - this.field_70125_A) / this.lerpSteps);
/* 161 */       this.lerpSteps--;
/* 162 */       func_70107_b(d0, d1, d2);
/* 163 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 170 */     super.func_70071_h_();
/* 171 */     tickLerp();
/*     */     
/* 173 */     if (this.cooldown > 0.0F) {
/* 174 */       this.cooldown--;
/*     */     }
/*     */     
/* 177 */     Entity passanger = func_184179_bs();
/*     */     
/* 179 */     if (!this.field_70170_p.field_72995_K && passanger != null && passanger instanceof LivingEntity) {
/* 180 */       LivingEntity rider = (LivingEntity)passanger;
/* 181 */       if (canShoot(rider)) {
/* 182 */         shootCannonball(rider);
/*     */       }
/*     */     } 
/*     */     
/* 186 */     if (getHurtTime() > 0) {
/* 187 */       setHurtTime(getHurtTime() - 1);
/*     */     }
/*     */     
/* 190 */     if (getDamage() > 0.0F) {
/* 191 */       setDamage(getDamage() - 1.0F);
/*     */     }
/*     */     
/* 194 */     double gravity = func_189652_ae() ? 0.0D : -0.4000000059604645D;
/* 195 */     Vector3d motion = func_213322_ci();
/* 196 */     AbilityHelper.setDeltaMovement(this, motion.field_72450_a * 0.10000000149011612D, motion.field_72448_b + gravity, motion.field_72449_c * 0.10000000149011612D);
/*     */     
/* 198 */     if (func_184186_bw()) {
/* 199 */       this.deltaYaw *= 0.1F;
/* 200 */       this.deltaPitch *= 0.1F;
/*     */       
/* 202 */       if (func_184179_bs() != null && func_184179_bs() instanceof LivingEntity) {
/* 203 */         controlVehicle();
/*     */       }
/*     */     } 
/*     */     
/* 207 */     func_213315_a(MoverType.SELF, func_213322_ci());
/*     */     
/* 209 */     if (!func_233570_aj_()) {
/* 210 */       List<Entity> list = Lists.newArrayList(this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72317_d(0.0D, -1.0D, 0.0D)));
/* 211 */       for (Entity entity : list) {
/* 212 */         entity.func_70097_a(DamageSource.field_82728_o, 20.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void controlVehicle() {
/* 218 */     if (func_184207_aI()) {
/* 219 */       Entity passanger = func_184179_bs();
/* 220 */       if (passanger == null || !(passanger instanceof LivingEntity)) {
/*     */         return;
/*     */       }
/* 223 */       LivingEntity player = (LivingEntity)passanger;
/*     */ 
/*     */       
/* 226 */       if (player.field_70702_br > 0.0F) {
/* 227 */         this.deltaYaw--;
/*     */       }
/* 229 */       else if (player.field_70702_br < 0.0F) {
/* 230 */         this.deltaYaw++;
/*     */       } 
/*     */       
/* 233 */       float speed = 0.0F;
/* 234 */       if (isMoveMode()) {
/*     */         
/* 236 */         if (player.field_191988_bg > 0.0F) {
/* 237 */           speed = 0.05F;
/*     */         }
/* 239 */         else if (player.field_191988_bg < 0.0F) {
/* 240 */           speed = -0.03F;
/*     */         }
/*     */       
/* 243 */       } else if (isAimMode()) {
/*     */         
/* 245 */         if (player.field_191988_bg > 0.0F && this.field_70125_A > -50.0F) {
/* 246 */           this.deltaPitch--;
/*     */         }
/* 248 */         else if (player.field_191988_bg < 0.0F && this.field_70125_A < 0.0F) {
/* 249 */           this.deltaPitch++;
/*     */         } 
/*     */       } 
/*     */       
/* 253 */       this.field_70177_z += this.deltaYaw;
/* 254 */       this.field_70125_A += this.deltaPitch;
/* 255 */       AbilityHelper.setDeltaMovement(this, func_213322_ci().func_72441_c((MathHelper.func_76126_a(-this.field_70177_z * 0.017453292F) * speed), 0.0D, (
/* 256 */             MathHelper.func_76134_b(this.field_70177_z * 0.017453292F) * speed)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canShoot(LivingEntity rider) {
/* 261 */     if (func_184207_aI()) {
/* 262 */       boolean canShoot = false;
/* 263 */       boolean hasInfiniteAmmo = false;
/* 264 */       ItemStack cannonballStack = ItemStack.field_190927_a;
/* 265 */       int cannonballCount = 0;
/* 266 */       ItemStack gunpowderStack = ItemStack.field_190927_a;
/* 267 */       int gunpowderCount = 0;
/*     */       
/* 269 */       if (rider instanceof PlayerEntity) {
/* 270 */         hasInfiniteAmmo = ((PlayerEntity)rider).field_71075_bZ.field_75098_d;
/*     */       } else {
/*     */         
/* 273 */         hasInfiniteAmmo = true;
/*     */       } 
/*     */       
/* 276 */       if (rider.field_110158_av == 1 && this.cooldown <= 0.0F) {
/* 277 */         if (hasInfiniteAmmo) {
/* 278 */           canShoot = true;
/*     */         } else {
/*     */           
/* 281 */           List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(rider); int j;
/* 282 */           for (j = 0; j < inventory.size(); j++) {
/* 283 */             ItemStack itemstack = inventory.get(j);
/* 284 */             if (itemstack.func_77973_b().equals(ModItems.CANNON_BALL.get())) {
/* 285 */               cannonballStack = itemstack;
/* 286 */               cannonballCount += itemstack.func_190916_E();
/*     */             } 
/*     */           } 
/*     */           
/* 290 */           if (cannonballCount <= 0 || cannonballStack.func_190926_b()) {
/* 291 */             rider.func_145747_a((ITextComponent)CANNONBALL_INFO, Util.field_240973_b_);
/* 292 */             return false;
/*     */           } 
/*     */           
/* 295 */           for (j = 0; j < inventory.size(); j++) {
/* 296 */             ItemStack itemstack = inventory.get(j);
/* 297 */             if (itemstack.func_77973_b().equals(Items.field_151016_H)) {
/* 298 */               gunpowderStack = itemstack;
/* 299 */               gunpowderCount += itemstack.func_190916_E();
/*     */             } 
/*     */           } 
/*     */           
/* 303 */           if (gunpowderCount <= 0 || gunpowderStack.func_190926_b()) {
/* 304 */             rider.func_145747_a((ITextComponent)GUNPOWDER_INFO, Util.field_240973_b_);
/* 305 */             return false;
/*     */           } 
/*     */           
/* 308 */           if (cannonballCount > 0 && gunpowderCount > 0 && !cannonballStack.func_190926_b() && !gunpowderStack.func_190926_b()) {
/* 309 */             canShoot = true;
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 314 */       if (canShoot) {
/* 315 */         if (!hasInfiniteAmmo) {
/* 316 */           cannonballStack.func_190918_g(1);
/* 317 */           gunpowderStack.func_190918_g(1);
/*     */         } 
/* 319 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 323 */     return false;
/*     */   }
/*     */   
/*     */   public void shootCannonball(LivingEntity rider) {
/* 327 */     CannonBallProjectile proj = new CannonBallProjectile(this.field_70170_p, rider);
/* 328 */     proj.func_70107_b((func_213303_ch()).field_72450_a, (func_213303_ch()).field_72448_b + 0.25D, (func_213303_ch()).field_72449_c);
/* 329 */     proj.setDamage(30.0F);
/* 330 */     proj.setMaxLife(180);
/* 331 */     proj.setGravity(0.04F);
/* 332 */     this.field_70170_p.func_217376_c((Entity)proj);
/* 333 */     proj.func_234612_a_(this, this.field_70125_A, this.field_70177_z, 0.0F, 3.0F, 0.0F);
/* 334 */     rider.field_70170_p.func_184133_a(null, func_233580_cy_(), (SoundEvent)ModSounds.GENERIC_EXPLOSION.get(), SoundCategory.PLAYERS, 2.5F, 0.75F + rider.func_70681_au().nextFloat() / 4.0F);
/* 335 */     this.cooldown = 10.0F;
/* 336 */     Vector3d lookVec = func_70040_Z().func_186678_a(4.0D);
/* 337 */     for (int i = 0; i < 10; i++) {
/* 338 */       double offsetX = WyHelper.randomDouble() / 2.0D + lookVec.field_72450_a;
/* 339 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 340 */       double offsetZ = WyHelper.randomDouble() / 2.0D + lookVec.field_72449_c;
/*     */       
/* 342 */       ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197598_I, func_233580_cy_().func_177958_n() + offsetX, (func_233580_cy_().func_177956_o() + 1) + offsetY, 
/* 343 */           func_233580_cy_().func_177952_p() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184232_k(Entity passenger) {
/* 349 */     if (func_184196_w(passenger)) {
/* 350 */       float f = -1.0F;
/* 351 */       float f1 = (float)((this.field_70128_L ? 0.009999999776482582D : func_70042_X()) + passenger.func_70033_W());
/* 352 */       if (func_184188_bt().size() > 1) {
/* 353 */         int i = func_184188_bt().indexOf(passenger);
/* 354 */         if (i == 0) {
/* 355 */           f = 0.2F;
/*     */         } else {
/*     */           
/* 358 */           f = -0.6F;
/*     */         } 
/*     */         
/* 361 */         if (passenger instanceof AnimalEntity) {
/* 362 */           f = (float)(f + 0.2D);
/*     */         }
/*     */       } 
/*     */       
/* 366 */       Vector3d vector3d = (new Vector3d(f, 0.0D, 0.0D)).func_178785_b(-this.field_70177_z * 0.017453292F - 1.5707964F);
/* 367 */       passenger.func_70107_b(func_226277_ct_() + vector3d.field_72450_a, func_226278_cu_() + f1, func_226281_cx_() + vector3d.field_72449_c);
/* 368 */       passenger.field_70177_z += this.deltaYaw;
/* 369 */       passenger.func_70034_d(passenger.func_70079_am() + this.deltaYaw);
/* 370 */       clampRotation(passenger);
/* 371 */       if (passenger instanceof AnimalEntity && func_184188_bt().size() > 1) {
/* 372 */         int j = (passenger.func_145782_y() % 2 == 0) ? 90 : 270;
/* 373 */         passenger.func_181013_g(((AnimalEntity)passenger).field_70761_aq + j);
/* 374 */         passenger.func_70034_d(passenger.func_70079_am() + j);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector3d func_230268_c_(LivingEntity entity) {
/* 381 */     Vector3d lookVec = func_70040_Z().func_216371_e();
/* 382 */     return entity.func_213303_ch().func_72441_c(0.0D, 0.5D, 0.0D).func_178787_e(lookVec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeVehicleMode() {
/* 387 */     if (isAimMode()) {
/* 388 */       setMoveMode();
/*     */     } else {
/*     */       
/* 391 */       setAimMode();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 398 */     List<Entity> list = func_184188_bt();
/* 399 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_184230_a(PlayerEntity player, Hand hand) {
/* 404 */     if (hand.equals(Hand.OFF_HAND)) {
/* 405 */       return ActionResultType.PASS;
/*     */     }
/* 407 */     if (!this.field_70170_p.field_72995_K) {
/* 408 */       if (player.func_184220_m(this)) {
/* 409 */         this.cooldown = 10.0F;
/* 410 */         return ActionResultType.CONSUME;
/*     */       } 
/*     */       
/* 413 */       return ActionResultType.PASS;
/*     */     } 
/*     */ 
/*     */     
/* 417 */     return ActionResultType.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 423 */     if (func_180431_b(source)) {
/* 424 */       return false;
/*     */     }
/* 426 */     if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
/* 427 */       this.field_70170_p.func_72960_a(this, (byte)100);
/* 428 */       setDamage(getDamage() + amount * 10.0F);
/* 429 */       func_70018_K();
/*     */       
/* 431 */       boolean flag = (source.func_76346_g() instanceof PlayerEntity && ((PlayerEntity)source.func_76346_g()).field_71075_bZ.field_75098_d);
/* 432 */       if (flag || getDamage() > 50.0F) {
/* 433 */         if (!flag && this.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223604_g)) {
/* 434 */           func_199701_a_(((Block)ModBlocks.CANNON.get()).func_199767_j().func_190903_i());
/*     */         }
/*     */         
/* 437 */         func_70106_y();
/*     */       } 
/*     */       
/* 440 */       return true;
/*     */     } 
/*     */     
/* 443 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_203002_i(boolean isDownwards) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70108_f(Entity entity) {
/* 453 */     if (entity instanceof CannonEntity) {
/* 454 */       if ((entity.func_174813_aQ()).field_72338_b < (func_174813_aQ()).field_72337_e) {
/* 455 */         super.func_70108_f(entity);
/*     */       }
/*     */     }
/* 458 */     else if ((entity.func_174813_aQ()).field_72338_b <= (func_174813_aQ()).field_72338_b) {
/* 459 */       super.func_70108_f(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 466 */     switch (id) {
/*     */       case 100:
/* 468 */         this.hurtTime = 10;
/*     */         break;
/*     */     } 
/* 471 */     super.func_70103_a(id);
/*     */   }
/*     */   
/*     */   protected void clampRotation(Entity pEntityToUpdate) {
/* 475 */     pEntityToUpdate.func_181013_g(this.field_70177_z);
/* 476 */     float f = MathHelper.func_76142_g(pEntityToUpdate.field_70177_z - this.field_70177_z);
/* 477 */     float f1 = MathHelper.func_76131_a(f, -105.0F, 105.0F);
/* 478 */     pEntityToUpdate.field_70126_B += f1 - f;
/* 479 */     pEntityToUpdate.field_70177_z += f1 - f;
/* 480 */     pEntityToUpdate.func_70034_d(pEntityToUpdate.field_70177_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getPickedResult(RayTraceResult target) {
/* 485 */     return ((Block)ModBlocks.CANNON.get()).func_199767_j().func_190903_i();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_184190_l(Entity pEntityToUpdate) {
/* 491 */     clampRotation(pEntityToUpdate);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT nbt) {
/* 496 */     setModeByte(nbt.func_74771_c("mode"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT nbt) {
/* 501 */     nbt.func_74774_a("mode", getModeByte());
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 506 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */   
/*     */   public void setAimMode() {
/* 510 */     setMode(Mode.AIM);
/*     */   }
/*     */   
/*     */   public void setMoveMode() {
/* 514 */     setMode(Mode.MOVE);
/*     */   }
/*     */   
/*     */   private void setMode(Mode mode) {
/* 518 */     setModeByte((byte)mode.ordinal());
/*     */   }
/*     */   
/*     */   private void setModeByte(byte mode) {
/* 522 */     this.field_70180_af.func_187227_b(MODE, Byte.valueOf(mode));
/*     */   }
/*     */   
/*     */   public boolean isAimMode() {
/* 526 */     return getMode().equals(Mode.AIM);
/*     */   }
/*     */   
/*     */   public boolean isMoveMode() {
/* 530 */     return getMode().equals(Mode.MOVE);
/*     */   }
/*     */   
/*     */   private byte getModeByte() {
/* 534 */     return ((Byte)this.field_70180_af.func_187225_a(MODE)).byteValue();
/*     */   }
/*     */   
/*     */   public Mode getMode() {
/* 538 */     return Mode.values()[((Byte)this.field_70180_af.func_187225_a(MODE)).byteValue()];
/*     */   }
/*     */   
/*     */   public float getCooldown() {
/* 542 */     return this.cooldown;
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 546 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setDamage(float damage) {
/* 550 */     this.damage = damage;
/*     */   }
/*     */   
/*     */   public int getHurtTime() {
/* 554 */     return this.hurtTime;
/*     */   }
/*     */   
/*     */   public void setHurtTime(int hurtTime) {
/* 558 */     this.hurtTime = hurtTime;
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 562 */     AIM,
/* 563 */     MOVE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\CannonEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */