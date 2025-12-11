/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DashDodgeProjectilesGoal
/*     */   extends TickedGoal<MobEntity> {
/*     */   private static final float CHECK_AREA = 40.0F;
/*     */   private float ticksBetweenDashes;
/*     */   private float dashDistance;
/*     */   private ProjectileEntity projectileTarget;
/*     */   private int hits;
/*  25 */   private int triggerHits = 3;
/*  26 */   private double previousDistance = 0.0D;
/*  27 */   private Predicate<LivingEntity> canUseTest = (Predicate<LivingEntity>)Predicates.alwaysTrue();
/*     */   
/*     */   public DashDodgeProjectilesGoal(MobEntity entity, float ticksBetweenDashes, float dashDistance) {
/*  30 */     super(entity);
/*  31 */     this.ticksBetweenDashes = ticksBetweenDashes;
/*  32 */     this.dashDistance = dashDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  37 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     if (!hasTimePassedSinceLastEnd(this.ticksBetweenDashes)) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     if (!AbilityHelper.canUseMomentumAbilities((LivingEntity)this.entity)) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     List<ProjectileEntity> projectiles = WyHelper.getNearbyEntities(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 40.0D, null, new Class[] { ProjectileEntity.class });
/*  50 */     for (ProjectileEntity proj : projectiles) {
/*  51 */       boolean isEnemyProjectile = ModEntityPredicates.getEnemyFactions((LivingEntity)this.entity).test(proj.func_234616_v_());
/*  52 */       if (isEnemyProjectile) {
/*  53 */         this.projectileTarget = proj;
/*  54 */         double distance = proj.func_70068_e((Entity)this.entity);
/*  55 */         if (this.previousDistance == 0.0D) {
/*  56 */           this.previousDistance = distance;
/*     */         }
/*  58 */         if (distance < this.previousDistance) {
/*  59 */           this.hits++;
/*  60 */           this.previousDistance = distance;
/*     */         } 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  66 */     if (this.hits < this.triggerHits) {
/*  67 */       return false;
/*     */     }
/*     */     
/*  70 */     if (this.projectileTarget == null) {
/*  71 */       return false;
/*     */     }
/*     */     
/*  74 */     if (!this.canUseTest.test(this.entity)) {
/*  75 */       return false;
/*     */     }
/*     */     
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  88 */     super.func_75249_e();
/*     */     
/*  90 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.projectileTarget);
/*  91 */     boolean dodgeRight = this.entity.func_70681_au().nextBoolean();
/*  92 */     double angle = dodgeRight ? -45.0D : 135.0D;
/*  93 */     double yRot = MathHelper.func_76138_g(this.entity.field_70177_z * 0.017453292519943295D) + Math.toRadians(angle);
/*  94 */     double xp = this.dashDistance * Math.cos(yRot) - this.dashDistance * Math.sin(yRot);
/*  95 */     double zp = this.dashDistance * Math.cos(yRot) + this.dashDistance * Math.sin(yRot);
/*  96 */     AbilityHelper.setDeltaMovement((Entity)this.entity, -xp, 0.1D, -zp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 101 */     super.func_75246_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 106 */     super.func_75251_c();
/* 107 */     this.hits = 0;
/* 108 */     this.previousDistance = 0.0D;
/*     */   }
/*     */   
/*     */   public DashDodgeProjectilesGoal setCanUseTest(Predicate<LivingEntity> test) {
/* 112 */     this.canUseTest = test;
/* 113 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\DashDodgeProjectilesGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */