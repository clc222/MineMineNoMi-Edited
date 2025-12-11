/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruNoYakataAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class DoruDoruNoYakataWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, DoruDoruNoYakataAbility> {
/*    */   private LivingEntity target;
/* 13 */   private double distance = 10.0D;
/*    */   
/*    */   public DoruDoruNoYakataWrapperGoal(MobEntity entity) {
/* 16 */     super(entity, DoruDoruNoYakataAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 21 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     if (!this.entity.func_233570_aj_()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     this.target = this.entity.func_70638_az();
/*    */     
/* 31 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 44 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 45 */       return false;
/*    */     }
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 53 */     BlockPos targetPos = this.target.func_233580_cy_();
/* 54 */     int targetPosX = (int)(this.entity.func_226277_ct_() - targetPos.func_177958_n() - this.entity.func_226277_ct_());
/* 55 */     int targetPosY = (int)(this.entity.func_226278_cu_() - targetPos.func_177956_o() - this.entity.func_226278_cu_());
/* 56 */     int targetPosZ = (int)(this.entity.func_226281_cx_() - targetPos.func_177952_p() - this.entity.func_226281_cx_());
/* 57 */     this.entity.func_70661_as().func_75492_a(targetPosX, targetPosY, targetPosZ, 1.25D);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\doru\DoruDoruNoYakataWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */