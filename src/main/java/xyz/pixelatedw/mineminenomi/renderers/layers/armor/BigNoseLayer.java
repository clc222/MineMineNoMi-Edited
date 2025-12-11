/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*    */ import xyz.pixelatedw.mineminenomi.items.armors.BigRedNoseItem;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.BigNoseModel;
/*    */ 
/*    */ public class BigNoseLayer<T extends LivingEntity, M extends BipedModel<T>> extends LayerRenderer<T, M> {
/* 17 */   private final BigNoseModel<T> model = new BigNoseModel();
/*    */   
/*    */   public BigNoseLayer(IEntityRenderer renderer) {
/* 20 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 25 */     if (entity instanceof BuggyEntity && 
/* 26 */       ((BuggyEntity)entity).clientCarState == 1) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 31 */     boolean flag = !entity.func_82150_aj();
/* 32 */     boolean flag1 = (!flag && !entity.func_98034_c((PlayerEntity)(Minecraft.func_71410_x()).field_71439_g));
/*    */     
/* 34 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(((BipedModel)func_215332_c()).func_228282_a_(BigRedNoseItem.TEXTURE));
/* 35 */     int overlay = LivingRenderer.func_229117_c_((LivingEntity)entity, 0.0F);
/*    */     
/* 37 */     matrixStack.func_227860_a_();
/* 38 */     ((BipedModel)func_215332_c()).func_217148_a((BipedModel)this.model);
/* 39 */     matrixStack.func_227861_a_(0.0D, -0.03D, 0.0D);
/* 40 */     this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, overlay, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/* 41 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\BigNoseLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */