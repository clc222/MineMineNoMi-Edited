/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.morphs;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhoenixFlamesLayer<E extends LivingEntity, M extends EntityModel<E>>
/*    */   extends LayerRenderer<E, M>
/*    */ {
/* 22 */   private static final ResourceLocation[][] TEXTURES = new ResourceLocation[3][8]; static {
/* 23 */     for (int set = 0; set < 3; set++) {
/* 24 */       for (int tex = 0; tex < 8; tex++) {
/* 25 */         ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/phoenix_flames/phoenix_flame_" + (set + 1) + "_" + (tex + 1) + ".png");
/* 26 */         TEXTURES[set][tex] = texture;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public PhoenixFlamesLayer(IEntityRenderer<E, M> renderer) {
/* 32 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, E entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 37 */     matrixStack.func_227860_a_();
/*    */     
/* 39 */     int frame = (int)(ageInTicks / FGCommand.ANIM_SPEED % 8.0F);
/* 40 */     ResourceLocation texture = TEXTURES[0][frame];
/* 41 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(ModRenderTypes.getAbilityBody(texture));
/* 42 */     func_215332_c().func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 44 */     matrixStack.func_227862_a_(1.001F, 1.001F, 1.001F);
/* 45 */     texture = TEXTURES[1][frame];
/* 46 */     ivertexbuilder = buffer.getBuffer(ModRenderTypes.getAbilityBody(texture));
/* 47 */     func_215332_c().func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 49 */     matrixStack.func_227862_a_(1.001F, 1.001F, 1.001F);
/* 50 */     texture = TEXTURES[2][frame];
/* 51 */     ivertexbuilder = buffer.getBuffer(ModRenderTypes.getAbilityBody(texture));
/* 52 */     func_215332_c().func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 54 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\morphs\PhoenixFlamesLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */