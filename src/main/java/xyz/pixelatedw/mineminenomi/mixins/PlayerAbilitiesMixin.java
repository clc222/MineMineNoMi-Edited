/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerAbilities;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.IPlayerAbilities;
/*    */ 
/*    */ @Mixin({PlayerAbilities.class})
/*    */ public class PlayerAbilitiesMixin
/*    */   implements IPlayerAbilities
/*    */ {
/*    */   private boolean hasCustomFlight = false;
/*    */   
/*    */   public boolean hasCustomFlight() {
/* 17 */     return this.hasCustomFlight;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustomFlight(boolean hasCustomFlight) {
/* 22 */     this.hasCustomFlight = hasCustomFlight;
/*    */   }
/*    */   
/*    */   @Inject(method = {"getFlyingSpeed()F"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void getFlyingSpeed(CallbackInfoReturnable<Float> cir) {
/* 27 */     if (this.hasCustomFlight)
/* 28 */       cir.setReturnValue(Float.valueOf(0.0F)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\PlayerAbilitiesMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */