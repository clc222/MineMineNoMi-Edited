/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.BaraCarPartialModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.morphs.BaraCarWheelsPartialModel;
/*    */ 
/*    */ public class BaraCarLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/* 23 */   private final BaraCarPartialModel bodyModel = new BaraCarPartialModel();
/* 24 */   private final BaraCarWheelsPartialModel wheelsModel = new BaraCarWheelsPartialModel();
/* 25 */   private static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/bara_car_wheels.png");
/*    */   private final IEntityRenderer<T, M> renderer;
/*    */   
/*    */   public BaraCarLayer(IEntityRenderer<T, M> renderer) {
/* 29 */     super(renderer);
/* 30 */     this.renderer = renderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 35 */     matrixStack.func_227860_a_();
/* 36 */     if (entity instanceof BuggyEntity) {
/* 37 */       if (((BuggyEntity)entity).clientCarState != 1) {
/* 38 */         matrixStack.func_227865_b_();
/*    */         
/*    */         return;
/*    */       } 
/* 42 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(netHeadYaw));
/*    */ 
/*    */     
/*    */     }
/* 46 */     else if (entity.func_82150_aj()) {
/* 47 */       matrixStack.func_227865_b_();
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 52 */     ResourceLocation skin = DefaultPlayerSkin.func_177334_a(entity.func_110124_au());
/* 53 */     if (entity instanceof PlayerEntity) {
/* 54 */       PlayerEntity player = ((LivingEntity)entity).field_70170_p.func_217371_b(entity.func_110124_au());
/* 55 */       if (player != null)
/* 56 */         skin = ((AbstractClientPlayerEntity)player).func_110306_p(); 
/*    */     } else {
/* 58 */       skin = this.renderer.func_110775_a((Entity)entity);
/*    */     } 
/*    */     
/* 61 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 62 */     this.bodyModel.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 63 */     this.bodyModel.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 65 */     renderType = ModRenderTypes.getZoanRenderType(TEXTURE);
/* 66 */     this.wheelsModel.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 67 */     this.wheelsModel.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 68 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\BaraCarLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */