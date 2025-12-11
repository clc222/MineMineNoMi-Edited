/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @Deprecated
/*    */ public class PunchTriggerAbility
/*    */   extends ContinuousAbility
/*    */ {
/*    */   protected IOnHit onSwingEvent = player -> true;
/*    */   public boolean released = false;
/*    */   private boolean shouldStopAfterUsing = false;
/*    */   
/*    */   public PunchTriggerAbility(AbilityCore core) {
/* 17 */     super(core);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startContinuity(PlayerEntity player) {
/* 23 */     this.released = false;
/* 24 */     super.startContinuity(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(PlayerEntity player) {
/* 30 */     super.tick(player);
/* 31 */     if (isOnCooldown() || !isContinuous()) {
/* 32 */       this.released = false;
/*    */     }
/* 34 */     player.field_70170_p.func_217381_Z().func_76320_a(WyHelper.getResourceName(getName()));
/*    */     
/* 36 */     if (isContinuous()) {
/*    */       
/* 38 */       this.continueTime++;
/* 39 */       if ((isClientSideLegacy() || !player.field_70170_p.field_72995_K) && !isStateForced()) {
/* 40 */         this.duringContinuityEvent.duringContinuity(player, this.continueTime);
/*    */       }
/* 42 */       if (player.field_82175_bq && !this.released) {
/*    */         
/* 44 */         this.released = true;
/* 45 */         this.onSwingEvent.onHitEntity(player);
/* 46 */         if (this.shouldStopAfterUsing) {
/* 47 */           tryStoppingContinuity(player);
/*    */         }
/*    */       } 
/* 50 */       if (this.released && !player.field_82175_bq && !this.shouldStopAfterUsing) {
/* 51 */         this.released = false;
/*    */       }
/* 53 */       if (this.threshold > 0 && this.continueTime >= this.threshold) {
/* 54 */         tryStoppingContinuity(player);
/*    */       }
/*    */     } 
/* 57 */     player.field_70170_p.func_217381_Z().func_76319_b();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopAfterUsage(boolean value) {
/* 67 */     this.shouldStopAfterUsing = value;
/*    */   }
/*    */   
/*    */   public static interface IOnHit extends Serializable {
/*    */     boolean onHitEntity(PlayerEntity param1PlayerEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PunchTriggerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */