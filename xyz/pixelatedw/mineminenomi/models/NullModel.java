/*    */ package xyz.pixelatedw.mineminenomi.models;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class NullModel<T extends LivingEntity>
/*    */   extends BipedModel<T>
/*    */ {
/*    */   public NullModel() {
/* 13 */     super(1.0F);
/*    */   }
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {}
/*    */   
/*    */   public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\NullModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */