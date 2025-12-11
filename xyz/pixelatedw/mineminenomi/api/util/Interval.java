/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ public class Interval {
/*    */   protected int interval;
/*  5 */   protected float tick = 0.0F;
/*    */ 
/*    */   
/*    */   private boolean trackTPS = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public Interval(int interval) {
/* 13 */     this.interval = interval;
/*    */   }
/*    */   
/*    */   public static Interval startAtZero(int interval) {
/* 17 */     return new Interval(interval);
/*    */   }
/*    */   
/*    */   public static Interval startAtMax(int interval) {
/* 21 */     Interval intervalObj = new Interval(interval);
/*    */     
/* 23 */     intervalObj.tick = interval;
/*    */     
/* 25 */     return intervalObj;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> T trackTPS() {
/* 33 */     this.trackTPS = true;
/* 34 */     return (T)this;
/*    */   }
/*    */   
/*    */   public boolean canTick() {
/* 38 */     if (this.trackTPS) {
/* 39 */       this.tick -= TPSDelta.INSTANCE.getDeltaTime();
/*    */     } else {
/* 41 */       this.tick--;
/*    */     } 
/*    */     
/* 44 */     if (this.tick <= 0.0F) {
/* 45 */       this.tick = this.interval;
/*    */       
/* 47 */       return true;
/*    */     } 
/*    */     
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public void restartIntervalToZero() {
/* 54 */     this.tick = 0.0F;
/*    */   }
/*    */   
/*    */   public void restartIntervalToMax() {
/* 58 */     this.tick = this.interval;
/*    */   }
/*    */   
/*    */   public int getTick() {
/* 62 */     return Math.round(this.tick);
/*    */   }
/*    */   
/*    */   public static class Mutable extends Interval {
/*    */     public Mutable(int interval) {
/* 67 */       super(interval);
/*    */     }
/*    */     
/*    */     public void setInterval(int interval) {
/* 71 */       this.interval = interval;
/*    */       
/* 73 */       restartIntervalToMax();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\Interval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */