/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ public class ShinokuniFaceLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 22 */   private final PlayerModel playerModel = new PlayerModel(1.0F, false);
/*    */ 
/*    */   
/*    */   public ShinokuniFaceLayer(IEntityRenderer renderer) {
/* 26 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 32 */     if (entity.func_82150_aj()) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     ResourceLocation skin = DefaultPlayerSkin.func_177334_a(entity.func_110124_au());
/* 37 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 39 */       PlayerEntity player = ((LivingEntity)entity).field_70170_p.func_217371_b(entity.func_110124_au());
/* 40 */       if (player != null) {
/* 41 */         skin = ((AbstractClientPlayerEntity)player).func_110306_p();
/*    */       }
/*    */     } 
/* 44 */     matrixStack.func_227860_a_();
/* 45 */     matrixStack.func_227862_a_(0.85F, 0.85F, 0.85F);
/* 46 */     matrixStack.func_227861_a_(0.0D, -0.95D, -0.35D);
/*    */ 
/*    */     
/* 49 */     headPitch = MathHelper.func_76131_a(headPitch, -17.0F, 60.0F);
/* 50 */     netHeadYaw = MathHelper.func_76131_a(netHeadYaw, -27.0F, 27.0F);
/*    */     
/* 52 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 53 */     this.playerModel.func_178719_a(false);
/* 54 */     this.playerModel.field_78116_c.field_78806_j = true;
/* 55 */     this.playerModel.func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 56 */     this.playerModel.func_225598_a_(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 57 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\ShinokuniFaceLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */