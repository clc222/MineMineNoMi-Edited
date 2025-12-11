/*     */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DugongModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BodyCoatingLayer<T extends LivingEntity, M extends EntityModel<T>>
/*     */   extends LayerRenderer<T, M>
/*     */ {
/*     */   public BodyCoatingLayer(IEntityRenderer renderer) {
/*  38 */     super(renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/*  44 */     IVertexBuilder vertex = null;
/*  45 */     Optional<AbilityOverlay> overlay = Optional.empty();
/*     */ 
/*     */     
/*  48 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*  49 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)entity);
/*     */     
/*  51 */     GearFourthAbility g4Ability = (GearFourthAbility)abilityProps.getEquippedAbility(GearFourthAbility.INSTANCE);
/*  52 */     boolean hasG4 = (g4Ability != null && g4Ability.isContinuous());
/*     */     
/*  54 */     for (IAbility ability : abilityProps.getEquippedAbilities()) {
/*  55 */       Optional<SkinOverlayComponent> comp = ability.getComponent(ModAbilityKeys.SKIN_OVERLAY);
/*     */       
/*  57 */       if (comp == null) {
/*     */         continue;
/*     */       }
/*  60 */       if (comp.isPresent()) {
/*  61 */         overlay = ((SkinOverlayComponent)comp.get()).getShownOverlay(new AbilityOverlay.OverlayPart[] { AbilityOverlay.OverlayPart.LIMB, AbilityOverlay.OverlayPart.LEG, AbilityOverlay.OverlayPart.ARM });
/*  62 */         if (overlay.isPresent() && func_215332_c() instanceof net.minecraft.client.renderer.entity.model.IHasArm) {
/*  63 */           float red = ((AbilityOverlay)overlay.get()).getColor().getRed() / 255.0F;
/*  64 */           float green = ((AbilityOverlay)overlay.get()).getColor().getGreen() / 255.0F;
/*  65 */           float blue = ((AbilityOverlay)overlay.get()).getColor().getBlue() / 255.0F;
/*  66 */           float alpha = ((AbilityOverlay)overlay.get()).getColor().getAlpha() / 255.0F;
/*     */           
/*  68 */           if (((AbilityOverlay)overlay.get()).getTexture() != null) {
/*  69 */             vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(((AbilityOverlay)overlay.get()).getTexture()));
/*     */           } else {
/*     */             
/*  72 */             vertex = ((AbilityOverlay)overlay.get()).getRenderType().equals(AbilityOverlay.RenderType.ENERGY) ? buffer.getBuffer(ModRenderTypes.ENERGY) : buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */           } 
/*  74 */           boolean isLeftHanded = (entity.func_184591_cq() == HandSide.LEFT);
/*  75 */           ModelRenderer limbToRender = null;
/*     */           
/*  77 */           boolean useLegModel = (((AbilityOverlay)overlay.get()).getOverlayPart().equals(AbilityOverlay.OverlayPart.LEG) || (props.isBlackLeg() && ((AbilityOverlay)overlay.get()).getOverlayPart().equals(AbilityOverlay.OverlayPart.LIMB)));
/*  78 */           boolean useArmModel = (((AbilityOverlay)overlay.get()).getOverlayPart().equals(AbilityOverlay.OverlayPart.ARM) || (!props.isBlackLeg() && ((AbilityOverlay)overlay.get()).getOverlayPart().equals(AbilityOverlay.OverlayPart.LIMB)));
/*     */           
/*  80 */           if (useLegModel) {
/*  81 */             if (func_215332_c() instanceof xyz.pixelatedw.mineminenomi.api.morph.MorphModel) {
/*     */               break;
/*     */             }
/*  84 */             if (func_215332_c() instanceof PlayerModel) {
/*  85 */               limbToRender = isLeftHanded ? ((PlayerModel)func_215332_c()).field_178733_c : ((PlayerModel)func_215332_c()).field_178731_d;
/*     */             }
/*  87 */             else if (func_215332_c() instanceof BipedModel) {
/*  88 */               limbToRender = isLeftHanded ? ((BipedModel)func_215332_c()).field_178722_k : ((BipedModel)func_215332_c()).field_178721_j;
/*     */             }
/*  90 */             else if (func_215332_c() instanceof DugongModel) {
/*  91 */               limbToRender = ((DugongModel)func_215332_c()).tailBase;
/*     */             }
/*     */           
/*  94 */           } else if (useArmModel) {
/*     */ 
/*     */             
/*  97 */             if (func_215332_c() instanceof xyz.pixelatedw.mineminenomi.api.morph.MorphModel) {
/*     */               break;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 103 */             if (func_215332_c() instanceof PlayerModel) {
/* 104 */               limbToRender = isLeftHanded ? ((PlayerModel)func_215332_c()).field_178734_a : ((PlayerModel)func_215332_c()).field_178732_b;
/*     */             }
/* 106 */             else if (func_215332_c() instanceof BipedModel) {
/* 107 */               limbToRender = isLeftHanded ? ((BipedModel)func_215332_c()).field_178724_i : ((BipedModel)func_215332_c()).field_178723_h;
/*     */             }
/* 109 */             else if (func_215332_c() instanceof DugongModel) {
/* 110 */               limbToRender = isLeftHanded ? ((DugongModel)func_215332_c()).leftArm : ((DugongModel)func_215332_c()).rightArm;
/*     */             } 
/*     */           } 
/*     */           
/* 114 */           if (limbToRender != null) {
/* 115 */             limbToRender.func_228309_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */           }
/*     */         } 
/* 118 */         overlay = ((SkinOverlayComponent)comp.get()).getShownOverlay(new AbilityOverlay.OverlayPart[] { AbilityOverlay.OverlayPart.BODY });
/* 119 */         if (overlay.isPresent()) {
/* 120 */           float red = ((AbilityOverlay)overlay.get()).getColor().getRed() / 255.0F;
/* 121 */           float green = ((AbilityOverlay)overlay.get()).getColor().getGreen() / 255.0F;
/* 122 */           float blue = ((AbilityOverlay)overlay.get()).getColor().getBlue() / 255.0F;
/* 123 */           float alpha = ((AbilityOverlay)overlay.get()).getColor().getAlpha() / 255.0F;
/*     */           
/* 125 */           if (hasG4 && ability instanceof xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility) {
/* 126 */             alpha = 0.0F;
/*     */           }
/* 128 */           boolean equalDepthTest = false;
/* 129 */           boolean culling = false;
/* 130 */           MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)entity);
/* 131 */           if (info != null) {
/* 132 */             equalDepthTest = info.hasEqualDepthTest();
/* 133 */             culling = info.hasCulling();
/*     */           } 
/*     */           
/* 136 */           if (((AbilityOverlay)overlay.get()).getTexture() != null) {
/* 137 */             vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(((AbilityOverlay)overlay.get()).getTexture(), equalDepthTest, culling));
/*     */           }
/*     */           else {
/*     */             
/* 141 */             vertex = ((AbilityOverlay)overlay.get()).getRenderType().equals(AbilityOverlay.RenderType.ENERGY) ? buffer.getBuffer(ModRenderTypes.ENERGY) : buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */           } 
/*     */           
/* 144 */           if (func_215332_c() instanceof PlayerModel) {
/* 145 */             ((PlayerModel)func_215332_c()).func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */             continue;
/*     */           } 
/* 148 */           matrixStack.func_227860_a_();
/* 149 */           matrixStack.func_227862_a_(1.06F, 1.06F, 1.06F);
/* 150 */           matrixStack.func_227861_a_(0.0D, -0.03999999910593033D, 0.0D);
/* 151 */           func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/* 152 */           matrixStack.func_227865_b_();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\BodyCoatingLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */