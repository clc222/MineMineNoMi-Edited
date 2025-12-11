/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class YakkodoriWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, YakkodoriAbility> {
/*    */   public YakkodoriWrapperGoal(MobEntity entity) {
/* 13 */     super(entity, YakkodoriAbility.INSTANCE);
/*    */   }
/*    */   private LivingEntity target;
/*    */   
/*    */   public boolean canUseWrapper() {
/* 18 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 19 */       return false;
/*    */     }
/*    */     
/* 22 */     this.target = this.entity.func_70638_az();
/*    */     
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 38 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\swordsman\YakkodoriWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */