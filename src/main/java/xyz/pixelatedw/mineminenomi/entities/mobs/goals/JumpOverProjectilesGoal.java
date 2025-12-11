/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class JumpOverProjectilesGoal
/*     */   extends TickedGoal<MobEntity>
/*     */ {
/*     */   private static final float CHECK_AREA = 60.0F;
/*     */   private float cooldown;
/*     */   private float jumpHeight;
/*     */   private ProjectileEntity projectileTarget;
/*     */   private int hits;
/*  27 */   private int triggerHits = 3;
/*  28 */   private double previousDistance = 0.0D;
/*  29 */   private Predicate<LivingEntity> canUseTest = (Predicate<LivingEntity>)Predicates.alwaysTrue();
/*     */   
/*     */   public JumpOverProjectilesGoal(MobEntity entity, float cooldown, float jumpHeight) {
/*  32 */     super(entity);
/*  33 */     this.cooldown = cooldown;
/*  34 */     this.jumpHeight = jumpHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  39 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     if (!hasTimePassedSinceLastEnd(this.cooldown)) {
/*  44 */       return false;
/*     */     }
/*     */     
/*  47 */     if (!AbilityHelper.canUseMomentumAbilities((LivingEntity)this.entity)) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     List<ProjectileEntity> projectiles = WyHelper.getNearbyEntities(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 60.0D, null, new Class[] { ProjectileEntity.class });
/*  52 */     for (ProjectileEntity proj : projectiles) {
/*  53 */       boolean isEnemyProjectile = ModEntityPredicates.getEnemyFactions((LivingEntity)this.entity).test(proj.func_234616_v_());
/*  54 */       if (isEnemyProjectile) {
/*  55 */         this.projectileTarget = proj;
/*  56 */         double distance = proj.func_70068_e((Entity)this.entity);
/*  57 */         if (this.previousDistance == 0.0D) {
/*  58 */           this.previousDistance = distance;
/*     */         }
/*  60 */         if (distance < this.previousDistance) {
/*  61 */           this.hits++;
/*  62 */           this.previousDistance = distance;
/*     */         } 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  68 */     if (this.hits < this.triggerHits) {
/*  69 */       return false;
/*     */     }
/*     */     
/*  72 */     if (this.projectileTarget == null) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     if (!this.canUseTest.test(this.entity)) {
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  90 */     super.func_75249_e();
/*     */     
/*  92 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.projectileTarget);
/*     */     
/*  94 */     Vector3d lookVec = this.entity.func_70040_Z().func_216372_d(3.0D, 0.0D, 3.0D);
/*  95 */     AbilityHelper.setDeltaMovement((Entity)this.entity, lookVec.field_72450_a, this.jumpHeight, lookVec.field_72449_c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 100 */     super.func_75246_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 105 */     super.func_75251_c();
/* 106 */     this.hits = 0;
/* 107 */     this.previousDistance = 0.0D;
/*     */   }
/*     */   
/*     */   public void setCanUseTest(Predicate<LivingEntity> test) {
/* 111 */     this.canUseTest = test;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\JumpOverProjectilesGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */