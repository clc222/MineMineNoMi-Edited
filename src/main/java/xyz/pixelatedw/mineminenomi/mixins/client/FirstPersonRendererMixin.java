/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.FirstPersonRenderer;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({FirstPersonRenderer.class})
/*    */ public class FirstPersonRendererMixin
/*    */ {
/*    */   @Inject(method = {"renderArmWithItem"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAnimation()Lnet/minecraft/item/UseAction;", shift = At.Shift.BEFORE)})
/*    */   public void renderArmWithItem(AbstractClientPlayerEntity pPlayer, float pPartialTicks, float pPitch, Hand hand, float pSwingProgress, ItemStack stack, float equippedProgress, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, CallbackInfo ci) {
/* 29 */     boolean flag = (hand == Hand.MAIN_HAND);
/* 30 */     HandSide handside = flag ? pPlayer.func_184591_cq() : pPlayer.func_184591_cq().func_188468_a();
/*    */     
/* 32 */     boolean flag3 = (handside == HandSide.RIGHT);
/* 33 */     int k = flag3 ? 1 : -1;
/*    */     
/* 35 */     if (stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSpearItem) {
/* 36 */       int i = (handside == HandSide.RIGHT) ? 1 : -1;
/* 37 */       matrixStack.func_227861_a_((i * 0.56F), (-0.52F + equippedProgress * -0.6F), -0.7200000286102295D);
/* 38 */       matrixStack.func_227861_a_((k * -0.5F), 0.699999988079071D, 0.10000000149011612D);
/* 39 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-55.0F));
/* 40 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(k * 35.3F));
/* 41 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(k * -9.785F));
/*    */       
/* 43 */       Minecraft mc = Minecraft.func_71410_x();
/*    */       
/* 45 */       float f7 = stack.func_77988_m() - mc.field_71439_g.func_184605_cv() - pPartialTicks + 1.0F;
/* 46 */       float f11 = f7 / 10.0F;
/* 47 */       if (f11 > 1.0F) {
/* 48 */         f11 = 1.0F;
/*    */       }
/*    */       
/* 51 */       if (f11 > 0.1F) {
/* 52 */         float f14 = MathHelper.func_76126_a((f7 - 0.1F) * 1.3F);
/* 53 */         float f17 = f11 - 0.1F;
/* 54 */         float f19 = f14 * f17;
/* 55 */         matrixStack.func_227861_a_((f19 * 0.0F), (f19 * 0.004F), (f19 * 0.0F));
/*    */       } 
/*    */       
/* 58 */       matrixStack.func_227861_a_(0.0D, 0.0D, (f11 * 0.2F));
/* 59 */       matrixStack.func_227862_a_(1.0F, 1.0F, 1.0F + f11 * 0.2F);
/* 60 */       matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(k * 45.0F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\FirstPersonRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */