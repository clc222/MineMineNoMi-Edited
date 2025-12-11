/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.advancements.Advancement;
/*    */ import net.minecraft.client.gui.toasts.AdvancementToast;
/*    */ import net.minecraft.client.gui.toasts.IToast;
/*    */ import net.minecraft.client.gui.toasts.ToastGui;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.AbilityDisplayInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({AdvancementToast.class})
/*    */ public class AdvancementToastMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private Advancement field_193679_c;
/*    */   
/*    */   @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;renderAndDecorateFakeItem(Lnet/minecraft/item/ItemStack;II)V", shift = At.Shift.BEFORE)}, cancellable = true)
/*    */   public void render(MatrixStack matrixStack, ToastGui pToastComponent, long p_230444_3_, CallbackInfoReturnable<IToast.Visibility> callback) {
/* 35 */     if (this.field_193679_c.func_192068_c() instanceof AbilityDisplayInfo) {
/*    */       
/* 37 */       AbilityDisplayInfo ablInfo = (AbilityDisplayInfo)this.field_193679_c.func_192068_c();
/* 38 */       if (ablInfo.getAbility() != null) {
/*    */         
/* 40 */         RendererHelper.drawAbilityIcon(ablInfo.getAbility(), matrixStack, 8.0F, 8.0F, 1, 16.0F, 16.0F);
/* 41 */         callback.setReturnValue((p_230444_3_ >= 5000L) ? IToast.Visibility.HIDE : IToast.Visibility.SHOW);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\AdvancementToastMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */