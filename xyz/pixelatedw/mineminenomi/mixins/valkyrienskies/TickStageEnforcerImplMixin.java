/*    */ package xyz.pixelatedw.mineminenomi.mixins.valkyrienskies;
/*    */ 
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.valkyrienskies.core.util.assertions.stages.TickStageEnforcerImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({TickStageEnforcerImpl.class})
/*    */ public class TickStageEnforcerImplMixin<S>
/*    */ {
/*    */   @Inject(method = {"stage"}, at = {@At("HEAD")}, cancellable = true, remap = false)
/*    */   private void stage(S stage, CallbackInfo ci) {
/* 22 */     ci.cancel();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\valkyrienskies\TickStageEnforcerImplMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */