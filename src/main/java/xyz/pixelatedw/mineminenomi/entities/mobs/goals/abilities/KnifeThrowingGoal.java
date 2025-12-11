/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import xyz.pixelatedw.mineminenomi.entities.ThrowingWeaponEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*     */ 
/*     */ public class KnifeThrowingGoal
/*     */   extends CooldownGoal {
/*  14 */   private int amount = 1;
/*  15 */   private float speed = 1.0F;
/*  16 */   private double spread = 5.0D;
/*     */ 
/*     */   
/*     */   public KnifeThrowingGoal(OPEntity entity) {
/*  20 */     super(entity);
/*  21 */     setMaxCooldown(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public KnifeThrowingGoal setSpread(double spread) {
/*  26 */     this.spread = spread;
/*  27 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public KnifeThrowingGoal setSpeed(float speed) {
/*  32 */     this.speed = speed;
/*  33 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public KnifeThrowingGoal setAmount(int amount) {
/*  38 */     this.amount = amount;
/*  39 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  45 */     if (!super.func_75250_a()) {
/*  46 */       return false;
/*     */     }
/*  48 */     boolean hasTarget = (this.entity.func_70638_az() != null);
/*  49 */     if (!hasTarget) {
/*  50 */       return false;
/*     */     }
/*  52 */     if (this.entity.func_70032_d((Entity)this.entity.func_70638_az()) < 3.0F) {
/*  53 */       return false;
/*     */     }
/*  55 */     if (!this.entity.func_70685_l((Entity)this.entity.func_70638_az())) {
/*  56 */       return false;
/*     */     }
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  64 */     LivingEntity target = this.entity.func_70638_az();
/*  65 */     if (target == null) {
/*     */       return;
/*     */     }
/*  68 */     float chanceForFull = 0.6F;
/*     */     
/*  70 */     if (this.entity.func_70032_d((Entity)target) < 10.0F) {
/*     */       
/*  72 */       double distance = (this.entity.field_70170_p.func_175659_aa() == Difficulty.HARD) ? 15.0D : 10.0D;
/*  73 */       Vector3d dir = this.entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(distance, 0.0D, distance).func_72441_c(0.0D, (this.entity.func_213322_ci()).field_72448_b, 0.0D);
/*  74 */       this.entity.func_213315_a(MoverType.SELF, dir);
/*     */     } 
/*     */     
/*  77 */     if (this.entity.field_70170_p.func_175659_aa() == Difficulty.HARD) {
/*     */       
/*  79 */       this.speed += 0.5F;
/*  80 */       chanceForFull = 0.2F;
/*     */     } 
/*     */     
/*  83 */     int finalAmount = 1;
/*  84 */     if (this.entity.func_70681_au().nextFloat() > chanceForFull) {
/*  85 */       finalAmount = this.amount;
/*     */     }
/*  87 */     int min = finalAmount / 2;
/*  88 */     int max = finalAmount / 2;
/*     */     
/*  90 */     float x = (float)(target.func_226277_ct_() - this.entity.func_226277_ct_());
/*  91 */     float z = (float)(target.func_226281_cx_() - this.entity.func_226281_cx_());
/*  92 */     float angle = (float)Math.toDegrees(Math.atan2(x, z));
/*     */     
/*  94 */     for (int i = -min; i <= max; i++) {
/*     */       
/*  96 */       ThrowingWeaponEntity throwingWeaponEntity = new ThrowingWeaponEntity(this.entity.field_70170_p, (LivingEntity)this.entity);
/*  97 */       throwingWeaponEntity.func_70107_b(throwingWeaponEntity.func_226277_ct_(), this.entity.func_226278_cu_() + (this.entity.func_213302_cg() / 2.0F) + 1.0D, throwingWeaponEntity.func_226281_cx_());
/*  98 */       throwingWeaponEntity.func_234612_a_((Entity)this.entity, this.entity.field_70125_A - 7.0F, (float)(-angle + i * this.spread), 0.0F, this.speed, 0.0F);
/*  99 */       throwingWeaponEntity.setThrower((LivingEntity)this.entity);
/* 100 */       this.entity.field_70170_p.func_217376_c((Entity)throwingWeaponEntity);
/*     */     } 
/*     */     
/* 103 */     setOnCooldown(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\KnifeThrowingGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */