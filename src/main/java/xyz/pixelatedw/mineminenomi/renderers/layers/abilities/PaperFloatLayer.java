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
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ 
/*    */ public class PaperFloatLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/* 22 */   private CubeModel model = new CubeModel();
/*    */   
/*    */   public PaperFloatLayer(IEntityRenderer renderer) {
/* 25 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 31 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/* 32 */     if (abilityData == null) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     MiniMiniAbility ability = (MiniMiniAbility)abilityData.getEquippedAbility(MiniMiniAbility.INSTANCE);
/* 37 */     boolean hasAbility = (ability != null && ability.isContinuous());
/* 38 */     boolean hasPaper = (entity.func_184614_ca().func_77973_b() == Items.field_151121_aF || entity.func_184592_cb().func_77973_b() == Items.field_151121_aF);
/* 39 */     boolean inAir = (!entity.func_233570_aj_() && (entity.func_213322_ci()).field_72448_b < 0.0D);
/*    */     
/* 41 */     if (hasAbility && hasPaper && inAir) {
/* 42 */       matrixStack.func_227861_a_(0.0D, -0.7D, 0.0D);
/* 43 */       matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(netHeadYaw));
/*    */       
/* 45 */       matrixStack.func_227862_a_(2.4F, 0.5F, 2.5F);
/*    */       
/* 47 */       RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 48 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 49 */       this.model.func_225598_a_(matrixStack, ivertexbuilder, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\PaperFloatLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */