/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ 
/*    */ 
/*    */ @Mixin({Minecraft.class})
/*    */ public abstract class MinecraftMixin
/*    */ {
/*    */   @Shadow
/*    */   private boolean field_71445_n;
/*    */   
/*    */   @Inject(method = {"runTick"}, at = {@At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;pause:Z", opcode = 181, shift = At.Shift.AFTER)})
/*    */   private void runTickReturn(boolean pRenderLevel, CallbackInfo ci) {
/* 20 */     if (this.field_71445_n) {
/* 21 */       ModMain.PAUSABLE_TIMER.pause();
/*    */     } else {
/* 23 */       ModMain.PAUSABLE_TIMER.start();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\MinecraftMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */