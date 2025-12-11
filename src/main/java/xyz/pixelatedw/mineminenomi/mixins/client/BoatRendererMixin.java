/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.BoatRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BoatModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ @Mixin({BoatRenderer.class})
/*    */ public class BoatRendererMixin {
/*    */   @Shadow
/*    */   @Final
/*    */   public BoatModel field_76998_a;
/*    */   
/*    */   @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/model/BoatModel;renderToBuffer(Lcom/mojang/blaze3d/matrix/MatrixStack;Lcom/mojang/blaze3d/vertex/IVertexBuilder;IIFFFF)V", shift = At.Shift.AFTER)})
/*    */   public void render(BoatEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo ci) {
/* 30 */     BoatRenderer renderer = (BoatRenderer)this;
/* 31 */     IKairosekiCoating coatingData = (IKairosekiCoating)KairosekiCoatingCapability.get((Entity)entity).orElse(null);
/*    */     
/* 33 */     int coatingLevel = coatingData.getCoatingLevel();
/* 34 */     if (coatingLevel > 0) {
/* 35 */       float coatingVisibility = coatingLevel / 5.0F * 0.8F;
/* 36 */       IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.func_228644_e_(ModResources.KAIROSEKI_BLOCK));
/* 37 */       this.field_76998_a.func_225598_a_(matrixStack, vertexBuilder, packedLight, OverlayTexture.field_229196_a_, 0.8F, 0.8F, 0.8F, coatingVisibility);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\BoatRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */