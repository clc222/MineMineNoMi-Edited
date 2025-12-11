/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Util;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.GomuSmokeModel;
/*    */ 
/*    */ public class GomuSmokeLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 23 */   private static final ResourceLocation SMOKE_0 = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/g5/smoke_0.png");
/* 24 */   private static final ResourceLocation SMOKE_1 = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/g5/smoke_1.png");
/* 25 */   private static final ResourceLocation SMOKE_2 = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/g5/smoke_2.png");
/* 26 */   private static final ResourceLocation SMOKE_3 = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/g5/smoke_3.png");
/* 27 */   private static final ResourceLocation[] SMOKE_ANIM = new ResourceLocation[] { SMOKE_0, SMOKE_1, SMOKE_2, SMOKE_3 };
/*    */   
/*    */   private static final float SCALE = 1.3F;
/* 30 */   private GomuSmokeModel model = new GomuSmokeModel();
/*    */   
/*    */   public GomuSmokeLayer(IEntityRenderer renderer) {
/* 33 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 40 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/* 41 */     if (!GomuHelper.hasGearFifthActive(abilityData)) {
/*    */       return;
/*    */     }
/*    */     
/* 45 */     float speed = 1000.0F;
/* 46 */     float anim = (float)Util.func_211177_b() % speed / speed / SMOKE_ANIM.length;
/* 47 */     matrixStack.func_227860_a_();
/* 48 */     matrixStack.func_227862_a_(1.3F, 1.3F, 1.3F);
/* 49 */     IVertexBuilder ivb = buffer.getBuffer(RenderType.func_228644_e_(SMOKE_ANIM[(int)Math.floor(anim)]));
/* 50 */     this.model.func_225597_a_((Entity)entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 51 */     this.model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/* 52 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\GomuSmokeLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */