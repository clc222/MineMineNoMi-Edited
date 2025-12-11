/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public class HumanoidFatModel<T extends CreatureEntity>
/*    */   extends HumanoidBruteModel<T>
/*    */ {
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 14 */     func_225602_a_().forEach(model -> model.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
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
/*    */     
/* 26 */     this.field_178723_h.field_78800_c = -6.5F;
/* 27 */     this.field_178723_h.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 28 */     this.field_178724_i.field_78800_c = 6.5F;
/* 29 */     this.field_178724_i.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 30 */     this.field_178721_j.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 31 */     this.field_178722_k.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 32 */     this.field_178720_f.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */ 
/*    */ 
/*    */     
/* 36 */     matrixStack.func_227860_a_();
/* 37 */     matrixStack.func_227862_a_(1.5F, 1.0F, 1.75F);
/* 38 */     this.field_78115_e.field_78798_e = -0.5F;
/* 39 */     this.field_78115_e.func_228309_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 40 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 45 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidFatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */