/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.buggy;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*     */ 
/*     */ public class BuggyDashGoal extends TickedGoal<BuggyEntity> {
/*     */   private static final int COOLDOWN = 200;
/*     */   private LivingEntity target;
/*     */   private boolean hasReachedTarget;
/*     */   private int stopAccel;
/*     */   private Vector3d targetPos;
/*     */   private double speed;
/*     */   private float damage;
/*  27 */   private Set<LivingEntity> alreadyHurt = new HashSet<>();
/*     */   
/*     */   public BuggyDashGoal(BuggyEntity entity, double speed, float damage) {
/*  30 */     super((MobEntity)entity);
/*  31 */     this.speed = speed;
/*  32 */     this.damage = damage;
/*  33 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  38 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     this.target = ((BuggyEntity)this.entity).func_70638_az();
/*     */     
/*  44 */     if (!hasTimePassedSinceLastEnd(200.0F)) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     if (!this.target.func_233570_aj_()) {
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  57 */     if (((BuggyEntity)this.entity).func_195048_a(this.targetPos) < 4.0D) {
/*  58 */       return false;
/*     */     }
/*     */     
/*  61 */     if (this.stopAccel >= 20) {
/*  62 */       return false;
/*     */     }
/*     */     
/*  65 */     if (hasTimePassedSinceStart(200.0F)) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  74 */     super.func_75249_e();
/*     */     
/*  76 */     this.alreadyHurt.clear();
/*  77 */     this.hasReachedTarget = false;
/*  78 */     this.stopAccel = 0;
/*     */     
/*  80 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*     */     
/*  82 */     Vector3d extraVec = this.target.func_213303_ch().func_178788_d(((BuggyEntity)this.entity).func_213303_ch()).func_186678_a(0.5D);
/*  83 */     this.targetPos = this.target.func_213303_ch().func_178787_e(extraVec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  88 */     super.func_75246_d();
/*     */     
/*  90 */     if (this.hasReachedTarget) {
/*  91 */       this.stopAccel++;
/*     */     }
/*     */     
/*  94 */     if (this.target.func_233570_aj_()) {
/*  95 */       GoalUtil.lookAt(this.entity, this.targetPos);
/*  96 */       Vector3d look = ((BuggyEntity)this.entity).func_70040_Z();
/*  97 */       Vector3d speed = look.func_216372_d(this.speed, 0.0D, this.speed);
/*  98 */       ((BuggyEntity)this.entity).func_213315_a(MoverType.SELF, speed);
/*     */     } 
/*     */     
/* 101 */     List<LivingEntity> targets = TargetHelper.getEntitiesInArea((LivingEntity)this.entity, 1.5D, null, new Class[] { LivingEntity.class });
/* 102 */     for (LivingEntity target : targets) {
/* 103 */       if (this.alreadyHurt.contains(target)) {
/*     */         continue;
/*     */       }
/*     */       
/* 107 */       target.func_70097_a(DamageSource.func_76358_a((LivingEntity)this.entity), this.damage);
/*     */       
/* 109 */       this.alreadyHurt.add(target);
/* 110 */       this.hasReachedTarget = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\buggy\BuggyDashGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */