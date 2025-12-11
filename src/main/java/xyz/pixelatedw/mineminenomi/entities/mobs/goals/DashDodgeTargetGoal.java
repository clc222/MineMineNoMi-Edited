/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import com.google.common.base.Predicates;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ 
/*    */ public class DashDodgeTargetGoal extends TickedGoal<MobEntity> {
/*    */   private LivingEntity target;
/*    */   private float ticksBetweenDashes;
/*    */   private float dashDistance;
/*    */   private boolean isSurrounded;
/* 19 */   private Predicate<LivingEntity> canUseTest = (Predicate<LivingEntity>)Predicates.alwaysTrue();
/*    */   
/*    */   public DashDodgeTargetGoal(MobEntity entity, float ticksBetweenDashes, float dashDistance) {
/* 22 */     super(entity);
/* 23 */     this.ticksBetweenDashes = ticksBetweenDashes;
/* 24 */     this.dashDistance = dashDistance;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 29 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     this.target = this.entity.func_70638_az();
/*    */     
/* 35 */     if (!hasTimePassedSinceLastEnd(this.ticksBetweenDashes)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (!AbilityHelper.canUseMomentumAbilities((LivingEntity)this.entity)) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 10.0D)) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)this.entity) > 3.0D) {
/* 52 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 56 */     if (!GoalUtil.isEntityInView(this.target, (Entity)this.entity)) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     if (!this.canUseTest.test(this.entity)) {
/* 61 */       return false;
/*    */     }
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 74 */     super.func_75249_e();
/*    */     
/* 76 */     if (!this.isSurrounded) {
/*    */ 
/*    */ 
/*    */       
/* 80 */       GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 81 */       Vector3d dir = this.target.func_213303_ch().func_178788_d(this.entity.func_213303_ch()).func_72432_b().func_216372_d(this.dashDistance, 1.0D, this.dashDistance);
/* 82 */       AbilityHelper.setDeltaMovement((Entity)this.entity, -dir.field_72450_a, 0.1D, -dir.field_72449_c);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 88 */     super.func_75246_d();
/*    */   }
/*    */   
/*    */   public DashDodgeTargetGoal setCanUseTest(Predicate<LivingEntity> test) {
/* 92 */     this.canUseTest = test;
/* 93 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\DashDodgeTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */