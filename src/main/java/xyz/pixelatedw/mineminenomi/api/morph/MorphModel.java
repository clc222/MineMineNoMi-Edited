/*    */ package xyz.pixelatedw.mineminenomi.api.morph;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public abstract class MorphModel<T extends LivingEntity>
/*    */   extends PlayerModel<T> {
/*    */   public MorphModel(float modelSize, boolean hasSmallArms) {
/* 16 */     super(modelSize, hasSmallArms);
/*    */   }
/*    */   
/*    */   public MorphModel(float modelSize) {
/* 20 */     this(modelSize, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void renderFirstPersonArm(MatrixStack paramMatrixStack, IVertexBuilder paramIVertexBuilder, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, HandSide paramHandSide);
/*    */   
/*    */   public abstract void renderFirstPersonLeg(MatrixStack paramMatrixStack, IVertexBuilder paramIVertexBuilder, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, HandSide paramHandSide);
/*    */   
/*    */   public ModelRenderer func_187074_a(HandSide side) {
/* 29 */     return super.func_187074_a(side);
/*    */   }
/*    */   
/*    */   public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
/* 33 */     ModelRenderer modelrenderer = func_187074_a(side);
/* 34 */     modelrenderer.func_228307_a_(matrixStack);
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\morph\MorphModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */