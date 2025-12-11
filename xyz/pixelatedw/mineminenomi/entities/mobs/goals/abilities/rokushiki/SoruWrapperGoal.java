/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class SoruWrapperGoal extends AbilityWrapperGoal<MobEntity, SoruAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 10.0D;
/*    */   
/*    */   public SoruWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, SoruAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     if (!GoalUtil.canMove((LivingEntity)this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     this.target = this.entity.func_70638_az();
/*    */     
/* 30 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 44 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 49 */     this.entity.func_70661_as().func_75499_g();
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\rokushiki\SoruWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */