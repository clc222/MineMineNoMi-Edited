/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnicycleEntity
/*     */   extends Entity
/*     */ {
/*     */   private static final float MOVE_SPEED = 0.25F;
/*     */   private static final float BACKWARDS_MOVE_SPEED = -0.25F;
/*     */   private static final float YAW_AIM_SPEED = 1.0F;
/*     */   private static final float MOVEMENT_FRICTION = 0.55F;
/*     */   private static final float ROTATION_FRICTION = 0.55F;
/*     */   private static final float GRAVITY = -0.098F;
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
/*     */   public UnicycleEntity(World level) {
/*  59 */     super((EntityType)ModEntities.UNICYCLE.get(), level);
/*  60 */     this.field_70156_m = true;
/*  61 */     this.field_70138_W = 1.0F;
/*     */   }
/*     */   
/*     */   public UnicycleEntity(EntityType<?> type, World level) {
/*  65 */     super(type, level);
/*  66 */     this.field_70156_m = true;
/*  67 */     this.field_70138_W = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */   
/*     */   public float getEyeHeightForge(Pose pose, EntitySize size) {
/*  76 */     return size.field_220316_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241849_j(Entity entity) {
/*  81 */     return canVehicleCollide(this, entity);
/*     */   }
/*     */   
/*     */   public static boolean canVehicleCollide(Entity vehicle, Entity entity) {
/*  85 */     if (entity.func_226278_cu_() < vehicle.func_226278_cu_()) {
/*  86 */       return false;
/*     */     }
/*  88 */     return ((entity.func_241845_aY() || entity.func_70104_M()) && !vehicle.func_184223_x(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 103 */     return func_70089_S();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRiderSit() {
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225502_at_() {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 118 */     return 0.55D;
/*     */   }
/*     */ 
/*     */   
/*     */   public Direction func_184172_bi() {
/* 123 */     return func_174811_aO().func_176746_e();
/*     */   }
/*     */   
/*     */   private void tickLerp() {
/* 127 */     if (func_184186_bw()) {
/* 128 */       this.lerpSteps = 0;
/* 129 */       func_213312_b(func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*     */     } 
/*     */     
/* 132 */     if (this.lerpSteps > 0) {
/* 133 */       double d0 = func_226277_ct_() + (this.lerpX - func_226277_ct_()) / this.lerpSteps;
/* 134 */       double d1 = func_226278_cu_() + (this.lerpY - func_226278_cu_()) / this.lerpSteps;
/* 135 */       double d2 = func_226281_cx_() + (this.lerpZ - func_226281_cx_()) / this.lerpSteps;
/* 136 */       double d3 = MathHelper.func_76138_g(this.lerpYRot - this.field_70177_z);
/* 137 */       this.field_70177_z = (float)(this.field_70177_z + d3 / this.lerpSteps);
/* 138 */       this.field_70125_A = (float)(this.field_70125_A + (this.lerpXRot - this.field_70125_A) / this.lerpSteps);
/* 139 */       this.lerpSteps--;
/* 140 */       func_70107_b(d0, d1, d2);
/* 141 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 147 */     super.func_70071_h_();
/* 148 */     tickLerp();
/*     */     
/* 150 */     if (this.cooldown > 0.0F) {
/* 151 */       this.cooldown--;
/*     */     }
/*     */     
/* 154 */     if (getHurtTime() > 0) {
/* 155 */       setHurtTime(getHurtTime() - 1);
/*     */     }
/*     */     
/* 158 */     if (getDamage() > 0.0F) {
/* 159 */       setDamage(getDamage() - 1.0F);
/*     */     }
/*     */     
/* 162 */     double gravity = func_189652_ae() ? 0.0D : -0.09799999743700027D;
/* 163 */     Vector3d motion = func_213322_ci();
/* 164 */     AbilityHelper.setDeltaMovement(this, motion.field_72450_a * 0.550000011920929D, motion.field_72448_b + gravity, motion.field_72449_c * 0.550000011920929D);
/*     */     
/* 166 */     if (func_184186_bw()) {
/* 167 */       this.deltaYaw *= 0.55F;
/* 168 */       this.deltaPitch *= 0.55F;
/*     */       
/* 170 */       if (func_184179_bs() != null && func_184179_bs() instanceof LivingEntity) {
/* 171 */         controlVehicle();
/*     */       }
/*     */     } 
/*     */     
/* 175 */     func_213315_a(MoverType.SELF, func_213322_ci());
/*     */     
/* 177 */     if (!func_233570_aj_()) {
/* 178 */       List<Entity> list = Lists.newArrayList(this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72317_d(0.0D, -1.0D, 0.0D)));
/* 179 */       for (Entity entity : list) {
/* 180 */         entity.func_70097_a(DamageSource.field_82728_o, 20.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void controlVehicle() {
/* 186 */     if (func_184207_aI()) {
/* 187 */       Entity passanger = func_184179_bs();
/* 188 */       if (passanger == null || !(passanger instanceof LivingEntity)) {
/*     */         return;
/*     */       }
/* 191 */       LivingEntity player = (LivingEntity)passanger;
/*     */ 
/*     */       
/* 194 */       if (player.field_70702_br > 0.0F) {
/* 195 */         this.deltaYaw--;
/*     */       }
/* 197 */       else if (player.field_70702_br < 0.0F) {
/* 198 */         this.deltaYaw++;
/*     */       } 
/*     */       
/* 201 */       float speed = 0.0F;
/*     */       
/* 203 */       if (player.field_191988_bg > 0.0F) {
/* 204 */         speed = 0.25F;
/*     */       }
/* 206 */       else if (player.field_191988_bg < 0.0F) {
/* 207 */         speed = -0.25F;
/*     */       } 
/*     */       
/* 210 */       this.field_70177_z += this.deltaYaw;
/* 211 */       this.field_70125_A += this.deltaPitch;
/* 212 */       AbilityHelper.setDeltaMovement(this, func_213322_ci().func_72441_c((MathHelper.func_76126_a(-this.field_70177_z * 0.017453292F) * speed), 0.0D, (
/* 213 */             MathHelper.func_76134_b(this.field_70177_z * 0.017453292F) * speed)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184232_k(Entity passenger) {
/* 219 */     if (func_184196_w(passenger)) {
/* 220 */       float f1 = (float)((this.field_70128_L ? 0.009999999776482582D : func_70042_X()) + passenger.func_70033_W());
/* 221 */       passenger.func_70107_b(func_226277_ct_(), func_226278_cu_() + f1, func_226281_cx_());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector3d func_230268_c_(LivingEntity entity) {
/* 227 */     Vector3d lookVec = func_70040_Z().func_216371_e();
/* 228 */     return entity.func_213303_ch().func_72441_c(0.0D, 0.5D, 0.0D).func_178787_e(lookVec);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 234 */     List<Entity> list = func_184188_bt();
/* 235 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_184230_a(PlayerEntity player, Hand hand) {
/* 240 */     if (hand.equals(Hand.OFF_HAND)) {
/* 241 */       return ActionResultType.PASS;
/*     */     }
/* 243 */     if (!this.field_70170_p.field_72995_K) {
/* 244 */       if (player.func_184220_m(this)) {
/* 245 */         this.cooldown = 10.0F;
/* 246 */         return ActionResultType.CONSUME;
/*     */       } 
/*     */       
/* 249 */       return ActionResultType.PASS;
/*     */     } 
/*     */ 
/*     */     
/* 253 */     return ActionResultType.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 259 */     if (func_180431_b(source)) {
/* 260 */       return false;
/*     */     }
/* 262 */     if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
/* 263 */       this.field_70170_p.func_72960_a(this, (byte)100);
/* 264 */       setDamage(getDamage() + amount * 10.0F);
/* 265 */       func_70018_K();
/*     */       
/* 267 */       boolean flag = (source.func_76346_g() instanceof PlayerEntity && ((PlayerEntity)source.func_76346_g()).field_71075_bZ.field_75098_d);
/* 268 */       if (flag || getDamage() > 20.0F) {
/* 269 */         if (!flag && this.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223604_g)) {
/* 270 */           func_199701_a_(((Item)ModItems.UNICYCLE.get()).func_199767_j().func_190903_i());
/*     */         }
/*     */         
/* 273 */         func_70106_y();
/*     */       } 
/*     */       
/* 276 */       return true;
/*     */     } 
/*     */     
/* 279 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 286 */     switch (id) {
/*     */       case 100:
/* 288 */         this.hurtTime = 10;
/*     */         break;
/*     */     } 
/* 291 */     super.func_70103_a(id);
/*     */   }
/*     */   
/*     */   protected void clampRotation(Entity pEntityToUpdate) {
/* 295 */     pEntityToUpdate.func_181013_g(this.field_70177_z);
/* 296 */     float f = MathHelper.func_76142_g(pEntityToUpdate.field_70177_z - this.field_70177_z);
/* 297 */     float f1 = MathHelper.func_76131_a(f, -105.0F, 105.0F);
/* 298 */     pEntityToUpdate.field_70126_B += f1 - f;
/* 299 */     pEntityToUpdate.field_70177_z += f1 - f;
/* 300 */     pEntityToUpdate.func_70034_d(pEntityToUpdate.field_70177_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getPickedResult(RayTraceResult target) {
/* 305 */     return ((Item)ModItems.UNICYCLE.get()).func_190903_i();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_184190_l(Entity pEntityToUpdate) {
/* 311 */     clampRotation(pEntityToUpdate);
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 315 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setDamage(float damage) {
/* 319 */     this.damage = damage;
/*     */   }
/*     */   
/*     */   public int getHurtTime() {
/* 323 */     return this.hurtTime;
/*     */   }
/*     */   
/*     */   public void setHurtTime(int hurtTime) {
/* 327 */     this.hurtTime = hurtTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 342 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\UnicycleEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */