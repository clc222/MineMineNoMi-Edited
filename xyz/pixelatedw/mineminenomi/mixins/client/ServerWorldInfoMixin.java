/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.serialization.Lifecycle;
/*    */ import net.minecraft.world.storage.ServerWorldInfo;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ServerWorldInfo.class})
/*    */ public class ServerWorldInfoMixin
/*    */ {
/*    */   @Inject(method = {"worldGenSettingsLifecycle"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void forceStable(CallbackInfoReturnable<Lifecycle> callback) {
/* 18 */     callback.setReturnValue(Lifecycle.stable());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ServerWorldInfoMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */