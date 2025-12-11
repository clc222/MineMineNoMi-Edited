/*    */ package xyz.pixelatedw.mineminenomi.api.animations;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class TimeAnimation<E extends LivingEntity, M extends BipedModel> implements IAnimation<E, M> {
/*    */   private long startTime;
/*    */   private long time;
/* 11 */   private State state = State.STOP;
/*    */   
/*    */   public void start(LivingEntity entity) {
/* 14 */     this.startTime = entity.field_70173_aa;
/* 15 */     this.time = 0L;
/* 16 */     this.state = State.PLAY;
/*    */   }
/*    */   
/*    */   public void stop(LivingEntity entity) {
/* 20 */     this.time = 0L;
/* 21 */     this.state = State.STOP;
/*    */   }
/*    */   
/*    */   public void tick(LivingEntity entity) {
/* 25 */     if (Minecraft.func_71410_x().func_147113_T()) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     if (this.state != State.PLAY) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     this.time = entity.field_70173_aa - this.startTime - 8L;
/*    */   }
/*    */   
/*    */   public long getTime() {
/* 37 */     return this.time;
/*    */   }
/*    */   
/*    */   public State getState() {
/* 41 */     return this.state;
/*    */   }
/*    */   
/*    */   public boolean isPlaying() {
/* 45 */     return (this.state == State.PLAY);
/*    */   }
/*    */   
/*    */   public boolean isStopped() {
/* 49 */     return (this.state == State.STOP);
/*    */   }
/*    */   
/*    */   public boolean isPaused() {
/* 53 */     return (this.state == State.PAUSE);
/*    */   }
/*    */   
/*    */   public enum State {
/* 57 */     PLAY, STOP, PAUSE;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\animations\TimeAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */