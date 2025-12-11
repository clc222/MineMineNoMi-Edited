/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.supa.SpiralHollowAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class SpiralHollowWrapperGoal extends AbilityWrapperGoal<MobEntity, SpiralHollowAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 10.0D;
/*    */   
/*    */   public SpiralHollowWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, SpiralHollowAbility.INSTANCE);
/* 16 */     ((SpiralHollowAbility)getAbility()).setDouble();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 21 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     this.target = this.entity.func_70638_az();
/*    */     
/* 27 */     if (this.entity.func_70068_e((Entity)this.target) > this.distance * this.distance) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 41 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\supa\SpiralHollowWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */