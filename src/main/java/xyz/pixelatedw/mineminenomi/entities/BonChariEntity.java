/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBiomes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BonChariEntity extends Entity {
/*  35 */   private static final DataParameter<Integer> BURST_TIME = EntityDataManager.func_187226_a(BonChariEntity.class, DataSerializers.field_187192_b);
/*     */   
/*     */   private static final int BUBBLE_BURST_TICKS = 30;
/*     */   
/*     */   private float deltaRotation;
/*     */   private int lerpSteps;
/*     */   private double lerpX;
/*     */   private double lerpY;
/*     */   private double lerpZ;
/*     */   private double lerpYRot;
/*     */   private double lerpXRot;
/*     */   private float burstingAngle;
/*     */   private float burstingAngleO;
/*     */   
/*     */   public BonChariEntity(World level) {
/*  50 */     super((EntityType)ModEntities.BON_CHARI.get(), level);
/*  51 */     this.field_70156_m = true;
/*     */   }
/*     */   
/*     */   public BonChariEntity(EntityType<?> type, World level) {
/*  55 */     super(type, level);
/*  56 */     this.field_70156_m = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  61 */     this.field_70180_af.func_187214_a(BURST_TIME, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeightForge(Pose pPose, EntitySize pSize) {
/*  66 */     return pSize.field_220316_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241849_j(Entity entity) {
/*  71 */     return canVehicleCollide(this, entity);
/*     */   }
/*     */   
/*     */   public static boolean canVehicleCollide(Entity vehicle, Entity entity) {
/*  75 */     return ((entity.func_241845_aY() || entity.func_70104_M()) && !vehicle.func_184223_x(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/*  90 */     return func_70089_S();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225502_at_() {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 100 */     return -0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184232_k(Entity passenger) {
/* 105 */     if (func_184196_w(passenger)) {
/* 106 */       double y = func_226278_cu_() + func_70042_X() + passenger.func_70033_W();
/* 107 */       Vector3d posVec = (new Vector3d(-0.3D, 0.0D, 0.0D)).func_178785_b(-this.field_70177_z * 0.017453292F - 1.5707964F);
/* 108 */       passenger.func_70107_b(func_226277_ct_() + posVec.field_72450_a, y, func_226281_cx_() + posVec.field_72449_c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Direction func_184172_bi() {
/* 114 */     return func_174811_aO().func_176746_e();
/*     */   }
/*     */   
/*     */   private void tickLerp() {
/* 118 */     if (func_184186_bw()) {
/* 119 */       this.lerpSteps = 0;
/* 120 */       func_213312_b(func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*     */     } 
/*     */     
/* 123 */     if (this.lerpSteps > 0) {
/* 124 */       double d0 = func_226277_ct_() + (this.lerpX - func_226277_ct_()) / this.lerpSteps;
/* 125 */       double d1 = func_226278_cu_() + (this.lerpY - func_226278_cu_()) / this.lerpSteps;
/* 126 */       double d2 = func_226281_cx_() + (this.lerpZ - func_226281_cx_()) / this.lerpSteps;
/* 127 */       double d3 = MathHelper.func_76138_g(this.lerpYRot - this.field_70177_z);
/* 128 */       this.field_70177_z = (float)(this.field_70177_z + d3 / this.lerpSteps);
/* 129 */       this.field_70125_A = (float)(this.field_70125_A + (this.lerpXRot - this.field_70125_A) / this.lerpSteps);
/* 130 */       this.lerpSteps--;
/* 131 */       func_70107_b(d0, d1, d2);
/* 132 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 138 */     int burstTime = getBurstTime();
/*     */     
/* 140 */     if (this.field_70170_p.field_72995_K) {
/* 141 */       float mul = 1.0F;
/* 142 */       if (burstTime > 0) {
/* 143 */         mul += 0.05F;
/*     */       } else {
/*     */         
/* 146 */         mul = 0.0F;
/*     */       } 
/*     */       
/* 149 */       mul = MathHelper.func_76131_a(mul, 0.0F, 1.0F);
/* 150 */       this.burstingAngleO = this.burstingAngle;
/* 151 */       this.burstingAngle = 10.0F * (float)Math.sin((0.5F * (float)this.field_70170_p.func_82737_E())) * mul;
/*     */     } 
/*     */     
/* 154 */     if (!this.field_70170_p.field_72995_K) {
/* 155 */       boolean isInSaboandy = (this.field_70170_p.func_226691_t_(func_233580_cy_()).getRegistryName().equals(((Biome)ModBiomes.SABAODY.get()).getRegistryName()) && func_226278_cu_() < 140.0D);
/* 156 */       if (!isInSaboandy) {
/* 157 */         burstTime++;
/* 158 */         setBurstTime(burstTime);
/* 159 */         if (burstTime > 30) {
/* 160 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BON_CHARI_POPPING.get(), this, func_226277_ct_(), func_226278_cu_() + 0.5D, func_226281_cx_());
/* 161 */           func_184226_ay();
/* 162 */           func_70106_y();
/*     */         }
/*     */       
/*     */       }
/* 166 */       else if (burstTime > 0) {
/* 167 */         setBurstTime(0);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 172 */     tickLerp();
/* 173 */     super.func_70071_h_();
/* 174 */     if (func_184186_bw()) {
/* 175 */       Vector3d vector3d = func_213322_ci();
/* 176 */       if (func_184179_bs() == null) {
/* 177 */         double d1 = func_189652_ae() ? 0.0D : -0.0010000000474974513D;
/* 178 */         AbilityHelper.setDeltaMovement(this, vector3d.field_72450_a, vector3d.field_72448_b + d1, vector3d.field_72449_c);
/*     */       } else {
/*     */         
/* 181 */         controlVehicle();
/*     */       } 
/*     */       
/* 184 */       func_213315_a(MoverType.SELF, func_213322_ci());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void controlVehicle() {
/* 189 */     if (func_184207_aI()) {
/* 190 */       Entity passanger = func_184179_bs();
/* 191 */       if (passanger == null || !(passanger instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/* 194 */       PlayerEntity player = (PlayerEntity)passanger;
/*     */       
/* 196 */       float speed = 0.5F;
/* 197 */       int isMoving = (player.field_191988_bg > 0.0F) ? 0 : 1;
/* 198 */       if (isMoving == 0) {
/* 199 */         Vector3d vec = player.func_70040_Z();
/* 200 */         double x = vec.field_72450_a * speed;
/* 201 */         double y = vec.field_72448_b * speed;
/* 202 */         double z = vec.field_72449_c * speed;
/* 203 */         AbilityHelper.setDeltaMovement(this, x, y, z);
/*     */       } else {
/*     */         
/* 206 */         AbilityHelper.setDeltaMovement(this, Vector3d.field_186680_a);
/*     */       } 
/*     */       
/* 209 */       if (player.field_191988_bg > 0.0F) {
/* 210 */         double yRot = MathHelper.func_76138_g(this.field_70177_z * 0.017453292519943295D) + 0.8D;
/* 211 */         double dist = 1.2D;
/* 212 */         double xp = dist * Math.cos(yRot) - dist * Math.sin(yRot);
/* 213 */         double zp = dist * Math.cos(yRot) + dist * Math.sin(yRot);
/* 214 */         for (int i = 0; i < 5; i++) {
/* 215 */           double offsetX = WyHelper.randomDouble() / 2.5D - xp;
/* 216 */           double offsetY = WyHelper.randomDouble() / 2.5D;
/* 217 */           double offsetZ = WyHelper.randomDouble() / 2.5D - zp;
/*     */           
/* 219 */           SimpleParticleData proj = new SimpleParticleData((ParticleType)ModParticleTypes.AWA.get());
/* 220 */           proj.setLife(10);
/* 221 */           proj.setSize(2.0F);
/* 222 */           proj.setMotion(-(func_213322_ci()).field_72450_a, -(func_213322_ci()).field_72448_b, -(func_213322_ci()).field_72449_c);
/* 223 */           this.field_70170_p.func_195594_a((IParticleData)proj, func_226277_ct_() + offsetX, func_226278_cu_() + 0.65D + offsetY, func_226281_cx_() + offsetZ, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 232 */     List<Entity> list = func_184188_bt();
/* 233 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_184230_a(PlayerEntity player, Hand hand) {
/* 238 */     if (!this.field_70170_p.field_72995_K) {
/* 239 */       return player.func_184220_m(this) ? ActionResultType.CONSUME : ActionResultType.PASS;
/*     */     }
/*     */     
/* 242 */     return ActionResultType.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 248 */     if (func_180431_b(source)) {
/* 249 */       return false;
/*     */     }
/* 251 */     if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
/* 252 */       func_70018_K();
/* 253 */       func_70106_y();
/* 254 */       return true;
/*     */     } 
/*     */     
/* 257 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70108_f(Entity entity) {
/* 263 */     if (entity instanceof BonChariEntity) {
/* 264 */       if ((entity.func_174813_aQ()).field_72338_b < (func_174813_aQ()).field_72337_e) {
/* 265 */         super.func_70108_f(entity);
/*     */       }
/*     */     }
/* 268 */     else if ((entity.func_174813_aQ()).field_72338_b <= (func_174813_aQ()).field_72338_b) {
/* 269 */       super.func_70108_f(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void clampRotation(Entity entity) {
/* 274 */     entity.func_181013_g(this.field_70177_z);
/* 275 */     float yRot = MathHelper.func_76142_g(entity.field_70177_z - this.field_70177_z);
/* 276 */     float yRotClamp = MathHelper.func_76131_a(yRot, -105.0F, 105.0F);
/* 277 */     entity.field_70126_B += yRotClamp - yRot;
/* 278 */     entity.field_70177_z += yRotClamp - yRot;
/* 279 */     entity.func_70034_d(entity.field_70177_z);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_184190_l(Entity entity) {
/* 285 */     this.field_70177_z = entity.field_70177_z;
/* 286 */     this.field_70125_A = entity.field_70125_A;
/* 287 */     clampRotation(entity);
/*     */   }
/*     */   
/*     */   private void setBurstTime(int burstTime) {
/* 291 */     this.field_70180_af.func_187227_b(BURST_TIME, Integer.valueOf(burstTime));
/*     */   }
/*     */   
/*     */   private int getBurstTime() {
/* 295 */     return ((Integer)this.field_70180_af.func_187225_a(BURST_TIME)).intValue();
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getBurstingAngle(float partialTicks) {
/* 300 */     return MathHelper.func_219799_g(partialTicks, this.burstingAngleO, this.burstingAngle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 315 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\BonChariEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */