/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.IHasHead;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanaHandsLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/* 25 */   private EntityArmModel model = new EntityArmModel();
/*    */   
/*    */   public HanaHandsLayer(IEntityRenderer renderer) {
/* 28 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 34 */     boolean hasEffect = (entity.func_70644_a((Effect)ModEffects.HANA_HANDS.get()) && entity.func_70660_b((Effect)ModEffects.HANA_HANDS.get()).func_76459_b() > 0);
/* 35 */     if (!hasEffect) {
/*    */       return;
/*    */     }
/*    */     
/* 39 */     HanaHandsEntity hands = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)((LivingEntity)entity).field_70170_p, 1.0D, null, new Class[] { HanaHandsEntity.class }).stream().findFirst().orElse(null);
/* 40 */     boolean inClutch = (hands != null);
/*    */     
/* 42 */     if (!inClutch || hands == null || hands.getCaster() == null) {
/*    */       return;
/*    */     }
/*    */     
/* 46 */     int type = hands.getHandsType();
/* 47 */     float progress = hands.getAnimationProgress(partialTicks);
/*    */     
/* 49 */     float animation = 1.0F;
/* 50 */     if (progress > 0.5F && progress < 0.6F) {
/* 51 */       animation += progress / 2.0F;
/* 52 */     } else if (progress > 0.6F) {
/* 53 */       animation = (float)(animation * (1.0D - progress) / 0.4000000059604645D);
/*    */     } 
/* 55 */     matrixStack.func_227860_a_();
/* 56 */     ModelRenderer pivotModel = null;
/* 57 */     float armsPos = -1.0F;
/* 58 */     if (func_215332_c() instanceof BipedModel) {
/* 59 */       pivotModel = ((BipedModel)func_215332_c()).field_78115_e;
/*    */     }
/* 61 */     else if (func_215332_c() instanceof IHasHead) {
/* 62 */       pivotModel = ((IHasHead)func_215332_c()).func_205072_a();
/*    */     } else {
/*    */       
/* 65 */       armsPos = entity.func_213302_cg() / 8.0F;
/*    */     } 
/*    */     
/* 68 */     if (pivotModel != null) {
/* 69 */       pivotModel.func_228307_a_(matrixStack);
/*    */     } else {
/*    */       
/* 72 */       matrixStack.func_227861_a_(0.0D, -armsPos, 0.0D);
/*    */     } 
/*    */     
/* 75 */     ResourceLocation skin = ((AbstractClientPlayerEntity)hands.getCaster()).func_110306_p();
/*    */     
/* 77 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 78 */     if (type == 0) {
/* 79 */       matrixStack.func_227862_a_(animation, animation, animation);
/* 80 */       matrixStack.func_227863_a_(Vector3f.field_229182_e_.func_229187_a_(90.0F));
/* 81 */       matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(90.0F));
/* 82 */       matrixStack.func_227861_a_(0.1D, 0.15D, 0.0D);
/* 83 */       this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 84 */       matrixStack.func_227861_a_(-0.5D, 0.0D, 0.0D);
/* 85 */       this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     }
/* 87 */     else if (type == 1) {
/* 88 */       matrixStack.func_227863_a_(Vector3f.field_229182_e_.func_229187_a_(120.0F));
/* 89 */       matrixStack.func_227863_a_(Vector3f.field_229182_e_.func_229187_a_(90.0F * animation));
/* 90 */       matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(90.0F));
/* 91 */       matrixStack.func_227863_a_(Vector3f.field_229180_c_.func_229187_a_(90.0F));
/* 92 */       matrixStack.func_227861_a_(-0.15D, 0.15D, -0.1D);
/* 93 */       matrixStack.func_227861_a_(-0.0D, 0.0D, -0.1D);
/* 94 */       this.model.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/* 96 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\HanaHandsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */