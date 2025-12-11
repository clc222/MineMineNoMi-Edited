/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class HumanoidModel<T extends CreatureEntity> extends BipedModel<T> {
/* 16 */   private ItemStack heldItem = ItemStack.field_190927_a;
/*    */   
/*    */   public HumanoidModel() {
/* 19 */     this(64, 64);
/*    */   }
/*    */   
/*    */   public HumanoidModel(int texWidth, int texHeight) {
/* 23 */     super(0.0F, 0.0F, texWidth, texHeight);
/* 24 */     this.field_178720_f.field_78806_j = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 29 */     super.func_225598_a_(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 34 */     super.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
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
/*    */ 
/*    */   
/*    */   public void setupAttackAnimation(T entity, float ageInTicks) {
/* 50 */     super.func_230486_a_((LivingEntity)entity, ageInTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 55 */     super.func_225599_a_(side, matrixStack);
/*    */   }
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
/*    */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 68 */     modelRenderer.field_78795_f = x;
/* 69 */     modelRenderer.field_78796_g = y;
/* 70 */     modelRenderer.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */