/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.PunkGibsonModel;
/*    */ 
/*    */ public class PunkGibsonLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private PunkGibsonModel model = new PunkGibsonModel();
/* 20 */   public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/punk_gibson.png");
/*    */ 
/*    */   
/*    */   public PunkGibsonLayer(IEntityRenderer renderer) {
/* 24 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 30 */     if (entity.func_82150_aj()) {
/*    */       return;
/*    */     }
/*    */     
/* 34 */     BipedModel ogModel = (BipedModel)func_215332_c();
/*    */     
/* 36 */     matrixStack.func_227860_a_();
/*    */ 
/*    */     
/* 39 */     matrixStack.func_227861_a_(0.0D, 0.0D, -0.1D);
/* 40 */     this.model.rightArm.func_217177_a(ogModel.field_178723_h);
/* 41 */     RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(TEXTURE);
/* 42 */     this.model.rightArm.field_78800_c = (float)(this.model.rightArm.field_78800_c + 2.3D);
/* 43 */     this.model.rightArm.field_78797_d -= 2.0F;
/* 44 */     this.model.rightArm.field_78795_f = (float)(this.model.rightArm.field_78795_f + Math.toRadians(-120.0D) + ((LivingEntity)entity).field_70733_aJ);
/* 45 */     this.model.rightArm.field_78796_g = (float)(this.model.rightArm.field_78796_g + Math.toRadians(20.0D) - ((LivingEntity)entity).field_70733_aJ);
/* 46 */     this.model.rightArm.field_78808_h = (float)(this.model.rightArm.field_78808_h + Math.toRadians(-20.0D) - ((LivingEntity)entity).field_70733_aJ);
/* 47 */     this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).field_70177_z, partialTicks, packedLight);
/*    */     
/* 49 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ 
/*    */   
/*    */   public PunkGibsonModel getPartialModel() {
/* 54 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\PunkGibsonLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */