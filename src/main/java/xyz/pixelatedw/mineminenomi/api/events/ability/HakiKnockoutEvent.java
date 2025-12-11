/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HakiKnockoutEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   private LivingEntity source;
/*    */   private boolean isInitialBurst;
/*    */   private int knockoutTimer;
/*    */   
/*    */   public HakiKnockoutEvent(LivingEntity entity, LivingEntity source, boolean isInitialBurst, int knockoutTimer) {
/* 20 */     super(entity);
/* 21 */     this.source = source;
/* 22 */     this.isInitialBurst = isInitialBurst;
/* 23 */     this.knockoutTimer = knockoutTimer;
/*    */   }
/*    */   
/*    */   public LivingEntity getSource() {
/* 27 */     return this.source;
/*    */   }
/*    */   
/*    */   public boolean isInitialBurst() {
/* 31 */     return this.isInitialBurst;
/*    */   }
/*    */   
/*    */   public int getKnockdownTimer() {
/* 35 */     return this.knockoutTimer;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre extends HakiKnockoutEvent {
/*    */     public Pre(LivingEntity entity, LivingEntity source, boolean isInitialBurst, int knockoutTimer) {
/* 41 */       super(entity, source, isInitialBurst, knockoutTimer);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post extends HakiKnockoutEvent {
/*    */     public Post(LivingEntity entity, LivingEntity source, boolean isInitialBurst, int knockoutTimer) {
/* 47 */       super(entity, source, isInitialBurst, knockoutTimer);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\HakiKnockoutEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */