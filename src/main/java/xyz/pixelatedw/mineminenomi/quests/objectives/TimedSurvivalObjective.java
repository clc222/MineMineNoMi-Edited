/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ISurviveObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class TimedSurvivalObjective
/*    */   extends Objective
/*    */   implements ISurviveObjective {
/*    */   private int timeToSurvive;
/*    */   private float initialHP;
/*    */   
/*    */   public TimedSurvivalObjective(String title, int seconds) {
/* 14 */     super(title);
/* 15 */     this.timeToSurvive = seconds;
/* 16 */     setMaxProgress(this.timeToSurvive);
/*    */     
/* 18 */     this.onStartEvent = this::onStartEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartEvent(PlayerEntity player) {
/* 23 */     this.initialHP = player.func_110143_aJ();
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkTime(PlayerEntity player) {
/* 30 */     if (player.func_110143_aJ() > this.initialHP) {
/* 31 */       this.initialHP = player.func_110143_aJ();
/*    */     }
/* 33 */     if (player.func_110143_aJ() < this.initialHP) {
/*    */       
/* 35 */       setProgress(player, 0.0D, false);
/* 36 */       this.initialHP = player.func_110143_aJ();
/* 37 */       return false;
/*    */     } 
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\TimedSurvivalObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */