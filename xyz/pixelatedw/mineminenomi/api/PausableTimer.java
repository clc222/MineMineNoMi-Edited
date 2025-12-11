/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PausableTimer
/*    */ {
/* 11 */   private long startTime = 0L;
/* 12 */   private long elapsed = 0L;
/* 13 */   private long pauseTime = 0L;
/*    */   
/*    */   private boolean paused = false;
/*    */ 
/*    */   
/*    */   public void start() {
/* 19 */     if (this.startTime == 0L) {
/* 20 */       this.startTime = System.currentTimeMillis();
/* 21 */     } else if (this.paused) {
/* 22 */       this.elapsed += System.currentTimeMillis() - this.pauseTime;
/*    */       
/* 24 */       this.paused = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void pause() {
/* 29 */     if (!this.paused && this.startTime != 0L) {
/* 30 */       this.pauseTime = System.currentTimeMillis();
/*    */       
/* 32 */       this.paused = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void reset() {
/* 37 */     this.startTime = 0L;
/* 38 */     this.elapsed = 0L;
/* 39 */     this.pauseTime = 0L;
/*    */     
/* 41 */     this.paused = false;
/*    */   }
/*    */   
/*    */   public long getElapsedTime() {
/* 45 */     if (this.startTime == 0L) {
/* 46 */       return 0L;
/*    */     }
/*    */     
/* 49 */     if (this.paused) {
/* 50 */       return this.pauseTime - this.startTime - this.elapsed;
/*    */     }
/*    */     
/* 53 */     return System.currentTimeMillis() - this.startTime - this.elapsed;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\PausableTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */