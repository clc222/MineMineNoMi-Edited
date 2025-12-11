/*    */ package xyz.pixelatedw.mineminenomi.abilities.buki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchTriggerAbility;
/*    */ 
/*    */ public class HandmorphoseAbility
/*    */   extends PunchTriggerAbility {
/* 12 */   public static final AbilityCore<HandmorphoseAbility> INSTANCE = (new AbilityCore.Builder("Handmorphose", AbilityCategory.DEVIL_FRUITS, HandmorphoseAbility::new))
/* 13 */     .build();
/*    */ 
/*    */   
/*    */   public HandmorphoseAbility(AbilityCore<HandmorphoseAbility> core) {
/* 17 */     super(core);
/*    */     
/* 19 */     this.onStartContinuityEvent = this::onStartContinuity;
/* 20 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 21 */     this.beforeContinuityStopEvent = this::beforeContinuityStopEvent;
/*    */     
/* 23 */     stopAfterUsage(false);
/* 24 */     this.onSwingEvent = this::onSwingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onSwingEvent(PlayerEntity player) {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean beforeContinuityStopEvent(PlayerEntity player) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity playerEntity, int i) {}
/*    */ 
/*    */   
/*    */   private boolean onStartContinuity(PlayerEntity player) {
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public enum Mode
/*    */   {
/* 47 */     SWORD,
/* 48 */     CANNON,
/* 49 */     REVOLVER;
/*    */ 
/*    */     
/*    */     public Mode getNext() {
/* 53 */       return (ordinal() == Arrays.<Mode>stream(values()).count() - 1L) ? SWORD : values()[ordinal() + 1];
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\buki\HandmorphoseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */