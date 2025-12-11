/*    */ package xyz.pixelatedw.mineminenomi.api.util;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ public class TPSDelta {
/*  7 */   public static final TPSDelta INSTANCE = new TPSDelta();
/*    */ 
/*    */   
/*    */   private boolean init = false;
/*    */ 
/*    */   
/*    */   private boolean hasExpTimers = true;
/*    */   
/* 15 */   private long lastTickTime = ModMain.PAUSABLE_TIMER.getElapsedTime();
/*    */   
/* 17 */   private float deltaTime = 0.0F;
/*    */   
/*    */   public void init() {
/* 20 */     this.init = true;
/* 21 */     this.hasExpTimers = CommonConfig.INSTANCE.hasExperimentalTimers();
/*    */   }
/*    */   
/*    */   public void tick() {
/* 25 */     if (!this.hasExpTimers) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     long currentTime = ModMain.PAUSABLE_TIMER.getElapsedTime();
/* 30 */     long timeElapsed = currentTime - this.lastTickTime;
/*    */     
/* 32 */     this.lastTickTime = currentTime;
/* 33 */     this.deltaTime = Math.max((float)timeElapsed / 50.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public float getDeltaTime() {
/* 37 */     if (!this.hasExpTimers) {
/* 38 */       return 1.0F;
/*    */     }
/*    */     
/* 41 */     if (!this.init) {
/* 42 */       throw new RuntimeException("TPSDelta was never initialized!");
/*    */     }
/*    */     
/* 45 */     return this.deltaTime;
/*    */   }
/*    */   
/*    */   public long getLastTickTime() {
/* 49 */     if (!this.init) {
/* 50 */       throw new RuntimeException("TPSDelta was never initialized!");
/*    */     }
/*    */     
/* 53 */     return this.lastTickTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\TPSDelta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */