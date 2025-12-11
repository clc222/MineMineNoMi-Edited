/*     */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.NoMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.MinkFeaturesLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.PaperFloatLayer;
/*     */ 
/*     */ public class MiniRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*     */   public MiniRenderer(EntityRendererManager rendererManager, MorphInfo info, boolean hasSmallHands) {
/*  33 */     super(rendererManager, info, hasSmallHands);
/*  34 */     this.field_77045_g = (EntityModel)new NoMorphModel(hasSmallHands);
/*  35 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*  36 */     func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)this));
/*  37 */     func_177094_a((LayerRenderer)new PaperFloatLayer((IEntityRenderer)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  43 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/*  49 */     ((PlayerModel)this.field_77045_g).func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/*  50 */     ((PlayerModel)this.field_77045_g).func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  52 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/*  53 */     Ability ability = (Ability)abilityData.getEquippedAbility(MiniMiniAbility.INSTANCE);
/*  54 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*  55 */     boolean hasPaper = (entity.func_184614_ca().func_77973_b() == Items.field_151121_aF || entity.func_184592_cb().func_77973_b() == Items.field_151121_aF);
/*  56 */     boolean inAir = (!entity.func_233570_aj_() && (entity.func_213322_ci()).field_72448_b < 0.0D);
/*     */     
/*  58 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  59 */     if (shouldSit) {
/*  60 */       matrixStack.func_227861_a_(0.0D, -2.5D, 0.0D);
/*     */     }
/*  62 */     if (entity.func_213453_ef()) {
/*  63 */       matrixStack.func_227861_a_(0.0D, -0.5D, 0.0D);
/*     */     }
/*  65 */     if (hasAbility && hasPaper && inAir) {
/*  66 */       removeLayer((Class)HeldItemLayer.class);
/*     */     }
/*  68 */     boolean flag = func_225622_a_((LivingEntity)entity);
/*  69 */     boolean flag1 = (!flag && !entity.func_98034_c((PlayerEntity)(Minecraft.func_71410_x()).field_71439_g));
/*  70 */     RenderType renderType = ModRenderTypes.getZoanRenderType(func_110775_a(entity));
/*  71 */     if (renderType != null && flag) {
/*     */       
/*  73 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/*  74 */       int i = func_229117_c_((LivingEntity)entity, func_225625_b_((LivingEntity)entity, partialTicks));
/*  75 */       ((PlayerModel)this.field_77045_g).func_225598_a_(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_225620_a_(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/*  82 */     matrixStack.func_227862_a_(0.2F, 0.2F, 0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/*  88 */     return entity.func_110306_p();
/*     */   }
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     private MorphInfo info;
/*     */     private boolean hasSmallHands;
/*     */     
/*     */     public Factory(MorphInfo info, boolean hasSmallHands) {
/*  98 */       this.info = info;
/*  99 */       this.hasSmallHands = hasSmallHands;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 105 */       MiniRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new MiniRenderer<>(manager, this.info, this.hasSmallHands);
/* 106 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\MiniRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */