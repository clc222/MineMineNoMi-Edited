/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.mr0;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr0Entity;
/*    */ 
/*    */ public class Mr0PhaseSwitcherGoal extends TickedGoal<Mr0Entity> {
/*    */   public Mr0PhaseSwitcherGoal(Mr0Entity entity) {
/*  9 */     super((MobEntity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 14 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 15 */       return false;
/*    */     }
/*    */     
/* 18 */     if (((Mr0Entity)this.entity).isFirstPhaseActive() && trySwitchToSecondPhase()) {
/* 19 */       ((Mr0Entity)this.entity).startSecondPhase();
/* 20 */       return true;
/*    */     } 
/* 22 */     if (((Mr0Entity)this.entity).isSecondPhaseActive() && trySwitchToThirdPhase()) {
/* 23 */       ((Mr0Entity)this.entity).startThirdPhase();
/* 24 */       return true;
/*    */     } 
/*    */     
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   private boolean trySwitchToSecondPhase() {
/* 31 */     if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 70.0D)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   private boolean trySwitchToThirdPhase() {
/* 39 */     if (!((Mr0Entity)this.entity).isDifficultyHardOrAbove()) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     if (((Mr0Entity)this.entity).getPainMeter().getRevengePercentage() > 0.5F) {
/* 44 */       return true;
/*    */     }
/*    */     
/* 47 */     if (GoalUtil.hasHealthAbovePercentage((LivingEntity)this.entity, 50.0D)) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\mr0\Mr0PhaseSwitcherGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */