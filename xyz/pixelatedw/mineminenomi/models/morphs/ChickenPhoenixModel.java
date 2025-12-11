/*    */ package xyz.pixelatedw.mineminenomi.models.morphs;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ 
/*    */ public class ChickenPhoenixModel<T extends LivingEntity>
/*    */   extends MorphModel<T> {
/*    */   private final ModelRenderer head;
/*    */   private final ModelRenderer body;
/*    */   private final ModelRenderer leg0;
/*    */   private final ModelRenderer leg1;
/*    */   private final ModelRenderer wing0;
/*    */   private final ModelRenderer wing1;
/*    */   private final ModelRenderer beak;
/*    */   private final ModelRenderer redThing;
/*    */   
/*    */   public ChickenPhoenixModel() {
/* 26 */     super(1.0F);
/* 27 */     this.field_78090_t = 64;
/* 28 */     this.field_78089_u = 32;
/* 29 */     this.head = new ModelRenderer((Model)this, 0, 0);
/* 30 */     this.head.func_228301_a_(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, 0.0F);
/* 31 */     this.head.func_78793_a(0.0F, 15.0F, -4.0F);
/* 32 */     this.beak = new ModelRenderer((Model)this, 14, 0);
/* 33 */     this.beak.func_228301_a_(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, 0.0F);
/* 34 */     this.beak.func_78793_a(0.0F, 15.0F, -4.0F);
/* 35 */     this.redThing = new ModelRenderer((Model)this, 14, 4);
/* 36 */     this.redThing.func_228301_a_(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F);
/* 37 */     this.redThing.func_78793_a(0.0F, 15.0F, -4.0F);
/* 38 */     this.body = new ModelRenderer((Model)this, 0, 9);
/* 39 */     this.body.func_228301_a_(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F);
/* 40 */     this.body.func_78793_a(0.0F, 16.0F, 0.0F);
/* 41 */     this.leg0 = new ModelRenderer((Model)this, 26, 0);
/* 42 */     this.leg0.func_228300_a_(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
/* 43 */     this.leg0.func_78793_a(-2.0F, 19.0F, 1.0F);
/* 44 */     this.leg1 = new ModelRenderer((Model)this, 26, 0);
/* 45 */     this.leg1.func_228300_a_(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
/* 46 */     this.leg1.func_78793_a(1.0F, 19.0F, 1.0F);
/* 47 */     this.wing0 = new ModelRenderer((Model)this, 24, 13);
/* 48 */     this.wing0.func_228300_a_(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F);
/* 49 */     this.wing0.func_78793_a(-4.0F, 13.0F, 0.0F);
/* 50 */     this.wing1 = new ModelRenderer((Model)this, 24, 13);
/* 51 */     this.wing1.func_228300_a_(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F);
/* 52 */     this.wing1.func_78793_a(4.0F, 13.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Iterable<ModelRenderer> func_225602_a_() {
/* 58 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.head, this.beak, this.redThing);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Iterable<ModelRenderer> func_225600_b_() {
/* 64 */     return (Iterable<ModelRenderer>)ImmutableList.of(this.body, this.leg0, this.leg1, this.wing0, this.wing1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225597_a_(T pEntity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 73 */     this.head.field_78795_f = pHeadPitch * 0.017453292F;
/* 74 */     this.head.field_78796_g = pNetHeadYaw * 0.017453292F;
/* 75 */     this.beak.field_78795_f = this.head.field_78795_f;
/* 76 */     this.beak.field_78796_g = this.head.field_78796_g;
/* 77 */     this.redThing.field_78795_f = this.head.field_78795_f;
/* 78 */     this.redThing.field_78796_g = this.head.field_78796_g;
/* 79 */     this.body.field_78795_f = 1.5707964F;
/* 80 */     this.leg0.field_78795_f = MathHelper.func_76134_b(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
/* 81 */     this.leg1.field_78795_f = MathHelper.func_76134_b(pLimbSwing * 0.6662F + 3.1415927F) * 1.4F * pLimbSwingAmount;
/*    */     
/* 83 */     this.wing0.field_78800_c++;
/* 84 */     this.wing1.field_78800_c--;
/*    */     
/* 86 */     this.wing0.field_78808_h = 1.3F + MathHelper.func_76134_b(ageInTicks * 0.5F) * 0.9F;
/* 87 */     this.wing1.field_78808_h = -1.3F + MathHelper.func_76134_b(ageInTicks * 0.5F + 3.1415927F) * 0.9F;
/*    */   }
/*    */   
/*    */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */   
/*    */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\morphs\ChickenPhoenixModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */