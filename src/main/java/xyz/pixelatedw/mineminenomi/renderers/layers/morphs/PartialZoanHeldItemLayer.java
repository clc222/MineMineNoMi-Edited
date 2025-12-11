/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PartialZoanHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/*    */   private MorphModel partialModel;
/*    */   
/*    */   public PartialZoanHeldItemLayer(IEntityRenderer<T, M> entityRenderer, MorphModel partialModel) {
/* 27 */     super(entityRenderer);
/* 28 */     this.partialModel = partialModel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 34 */     boolean flag = (entity.func_184591_cq() == HandSide.RIGHT);
/* 35 */     ItemStack itemstack = flag ? entity.func_184592_cb() : entity.func_184614_ca();
/* 36 */     ItemStack itemstack1 = flag ? entity.func_184614_ca() : entity.func_184592_cb();
/* 37 */     if (!itemstack.func_190926_b() || !itemstack1.func_190926_b()) {
/*    */       
/* 39 */       matrixStack.func_227860_a_();
/* 40 */       renderItem((LivingEntity)entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStack, buffer, packedLight);
/* 41 */       renderItem((LivingEntity)entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStack, buffer, packedLight);
/* 42 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void renderItem(LivingEntity entity, ItemStack itemStack, ItemCameraTransforms.TransformType cameraTransform, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 48 */     if (!itemStack.func_190926_b() && this.partialModel instanceof IHasArm) {
/*    */       
/* 50 */       matrixStack.func_227860_a_();
/* 51 */       if (!this.partialModel.renderItemInHand(entity, handSide, matrixStack)) {
/*    */         
/* 53 */         matrixStack.func_227865_b_();
/*    */         return;
/*    */       } 
/* 56 */       matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(-90.0F));
/* 57 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/* 58 */       boolean flag = (handSide == HandSide.LEFT);
/* 59 */       matrixStack.func_227861_a_(((flag ? -1 : true) / 16.0F), 0.125D, -0.625D);
/* 60 */       Minecraft.func_71410_x().func_175597_ag().func_228397_a_(entity, itemStack, cameraTransform, flag, matrixStack, buffer, packedLight);
/* 61 */       matrixStack.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\PartialZoanHeldItemLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */