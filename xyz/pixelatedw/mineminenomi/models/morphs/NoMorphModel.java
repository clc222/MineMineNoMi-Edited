/*    */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ public class NoMorphModel<T extends LivingEntity>
/*    */   extends MorphModel<T>
/*    */   implements IHasArm
/*    */ {
/*    */   public NoMorphModel(boolean hasSmallArms) {
/* 15 */     super(0.0F, hasSmallArms);
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\NoMorphModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */