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
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuGomuNoDawnWhipAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.effects.SpiralEffectModel;
/*    */ 
/*    */ public class GomuDawnWhipLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 25 */   private SpiralEffectModel model = new SpiralEffectModel();
/*    */   
/*    */   public GomuDawnWhipLayer(IEntityRenderer renderer) {
/* 28 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 33 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/* 34 */     if (!GomuHelper.hasGearFifthActive(abilityData)) {
/*    */       return;
/*    */     }
/*    */     
/* 38 */     Ability ability = (Ability)abilityData.getEquippedAbility(GomuGomuNoDawnWhipAbility.INSTANCE);
/* 39 */     boolean isActive = (ability != null && ability.isContinuous());
/* 40 */     if (!isActive) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     matrixStack.func_227860_a_();
/* 45 */     matrixStack.func_227861_a_(0.0D, 1.55D, 0.25D);
/* 46 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(90.0F));
/* 47 */     float scale = 2.5F;
/* 48 */     matrixStack.func_227862_a_(scale, scale, scale);
/* 49 */     IVertexBuilder ivb = buffer.getBuffer(RenderType.func_228644_e_(ModResources.PROJ_EFFECT_2));
/* 50 */     this.model.func_225597_a_((Entity)entity, 0.0F, 0.0F, ageInTicks * 1.2F, 0.0F, 0.0F);
/* 51 */     this.model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.5F);
/* 52 */     matrixStack.func_227865_b_();
/*    */     
/* 54 */     matrixStack.func_227860_a_();
/* 55 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(45.0F));
/* 56 */     ivb = buffer.getBuffer(ModRenderTypes.ENERGY);
/* 57 */     func_215332_c().func_225597_a_((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 58 */     func_215332_c().func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.1F);
/* 59 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(90.0F));
/* 60 */     func_215332_c().func_225597_a_((Entity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 61 */     func_215332_c().func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.1F);
/* 62 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\GomuDawnWhipLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */