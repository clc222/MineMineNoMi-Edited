/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.client.ILivingRendererMixin;
/*     */ 
/*     */ public class MorphHelper
/*     */ {
/*     */   public static <T extends LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel<T>> boolean hasLayerFor(LivingRenderer renderer, Class<? extends LayerRenderer> clz) {
/*  49 */     return ((ILivingRendererMixin)renderer).getLayers().stream().anyMatch(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addLayerFor(LivingRenderer renderer, LayerRenderer layer) {
/*  54 */     if (!hasLayerFor(renderer, (Class)layer.getClass())) {
/*  55 */       ((ILivingRendererMixin)renderer).getLayers().add(layer);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removeLayerFor(LivingRenderer renderer, Class<? extends LayerRenderer> clz) {
/*  60 */     ((ILivingRendererMixin)renderer).getLayers().removeIf(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void renderLimbFirstPerson(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, Optional<AbilityOverlay> overlay, @Nullable MorphInfo info) {
/*  66 */     Minecraft mc = Minecraft.func_71410_x();
/*  67 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*  68 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)mc.field_71439_g);
/*     */     
/*  70 */     if (props.isBlackLeg()) {
/*     */       
/*  72 */       renderLeg((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, Optional.empty(), info);
/*  73 */       renderLeg((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, overlay, info);
/*     */     }
/*  75 */     else if (!props.isBlackLeg()) {
/*     */       
/*  77 */       renderArm((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, Optional.empty(), info);
/*  78 */       renderArm((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, overlay, info);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private static void renderLeg(AbstractClientPlayerEntity clientPlayer, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, Optional<AbilityOverlay> overlay, @Nullable MorphInfo info) {
/*  85 */     Minecraft mc = Minecraft.func_71410_x();
/*  86 */     EntityRendererManager renderManager = mc.func_175598_ae();
/*  87 */     EntityRenderer renderer = renderManager.func_78713_a((Entity)clientPlayer);
/*  88 */     IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)mc.field_71439_g);
/*     */     
/*  90 */     if (!(renderer instanceof PlayerRenderer)) {
/*     */       return;
/*     */     }
/*  93 */     matrixStack.func_227860_a_();
/*     */     
/*  95 */     boolean flag = (side != HandSide.LEFT);
/*  96 */     float f = flag ? 1.0F : -1.0F;
/*     */     
/*  98 */     float f1 = MathHelper.func_76129_c(swingProgress) / 1.55F;
/*  99 */     float f2 = -0.3F * MathHelper.func_76126_a(f1 * 3.1415927F);
/* 100 */     float f3 = 0.4F * MathHelper.func_76126_a(f1 * 6.2831855F);
/* 101 */     float f4 = -0.4F * MathHelper.func_76126_a(swingProgress * 3.1415927F);
/* 102 */     matrixStack.func_227861_a_((f * (f2 + 0.64000005F)), (f3 + -0.6F + equippedProgress * -0.6F), (f4 + -0.71999997F));
/* 103 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229182_e_, f * 85.0F, true));
/* 104 */     float f6 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 105 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * f6 * 120.0F, true));
/* 106 */     matrixStack.func_227861_a_((f * -1.0F), 4.0D, 3.5999999046325684D);
/* 107 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * 120.0F, true));
/* 108 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 200.0F, true));
/* 109 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * -135.0F, true));
/* 110 */     matrixStack.func_227861_a_((f * 5.6F), 0.0D, 0.0D);
/*     */     
/* 112 */     IVertexBuilder vertex = null;
/* 113 */     float red = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getRed() / 255.0F) : 1.0F;
/* 114 */     float green = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getGreen() / 255.0F) : 1.0F;
/* 115 */     float blue = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getBlue() / 255.0F) : 1.0F;
/* 116 */     float alpha = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getAlpha() / 255.0F) : 1.0F;
/*     */     
/* 118 */     if (overlay.isPresent()) {
/*     */       
/* 120 */       if (((AbilityOverlay)overlay.get()).getTexture() != null) {
/* 121 */         vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(((AbilityOverlay)overlay.get()).getTexture()));
/*     */       } else {
/*     */         
/* 124 */         vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 129 */       vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(clientPlayer.func_110306_p()));
/*     */     } 
/*     */     
/* 132 */     MorphModel morphModel = null;
/* 133 */     boolean renderLimb = false;
/* 134 */     boolean isPartial = false;
/*     */     
/* 136 */     if (info != null) {
/*     */       
/* 138 */       morphModel = info.getModel();
/* 139 */       isPartial = info.isPartial();
/* 140 */       renderLimb = info.shouldRenderFirstPersonLeg();
/*     */     } 
/*     */     
/* 143 */     if (Strings.isNullOrEmpty(dfProps.getZoanPoint()) || (isPartial && !renderLimb)) {
/*     */       
/* 145 */       PlayerModel model = (PlayerModel)((PlayerRenderer)renderer).func_217764_d();
/*     */       
/* 147 */       if (flag)
/*     */       {
/* 149 */         model.field_178721_j.field_78795_f = 0.0F;
/* 150 */         model.field_178721_j.field_78796_g = 0.0F;
/* 151 */         model.field_178721_j.field_78808_h = 0.0F;
/*     */         
/* 153 */         model.field_178721_j.field_78800_c = -2.0F;
/* 154 */         model.field_178721_j.field_78797_d = 0.0F;
/* 155 */         model.field_178721_j.field_78798_e = 0.0F;
/*     */         
/* 157 */         model.field_178731_d.func_217177_a(model.field_178721_j);
/*     */         
/* 159 */         model.field_178721_j.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/* 160 */         model.field_178731_d.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */       }
/*     */       else
/*     */       {
/* 164 */         model.field_178722_k.field_78795_f = 0.0F;
/* 165 */         model.field_178722_k.field_78796_g = 0.0F;
/* 166 */         model.field_178722_k.field_78808_h = 0.0F;
/*     */         
/* 168 */         model.field_178722_k.field_78800_c = 2.0F;
/* 169 */         model.field_178722_k.field_78797_d = 0.0F;
/* 170 */         model.field_178722_k.field_78798_e = 0.0F;
/*     */         
/* 172 */         model.field_178733_c.func_217177_a(model.field_178722_k);
/*     */         
/* 174 */         model.field_178722_k.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/* 175 */         model.field_178733_c.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 180 */     else if (morphModel != null && renderLimb) {
/*     */       
/* 182 */       ResourceLocation texture = info.getTexture((LivingEntity)clientPlayer);
/*     */       
/* 184 */       if (!overlay.isPresent() || ((AbilityOverlay)overlay.get()).getTexture() == null)
/*     */       {
/* 186 */         if (texture != null) {
/* 187 */           vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(texture));
/*     */         } else {
/* 189 */           vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */         } 
/*     */       }
/* 192 */       morphModel.renderFirstPersonLeg(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha, side);
/*     */     } 
/*     */ 
/*     */     
/* 196 */     matrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private static void renderArm(AbstractClientPlayerEntity clientPlayer, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, Optional<AbilityOverlay> overlay, @Nullable MorphInfo info) {
/* 202 */     Minecraft mc = Minecraft.func_71410_x();
/* 203 */     EntityRendererManager renderManager = mc.func_175598_ae();
/* 204 */     EntityRenderer renderer = renderManager.func_78713_a((Entity)clientPlayer);
/* 205 */     IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)mc.field_71439_g);
/*     */     
/* 207 */     if (!(renderer instanceof PlayerRenderer)) {
/*     */       return;
/*     */     }
/* 210 */     matrixStack.func_227860_a_();
/*     */     
/* 212 */     boolean flag = (side != HandSide.LEFT);
/* 213 */     float f = flag ? 1.0F : -1.0F;
/*     */     
/* 215 */     float f1 = MathHelper.func_76129_c(swingProgress);
/* 216 */     float f2 = -0.3F * MathHelper.func_76126_a(f1 * 3.1415927F);
/* 217 */     float f3 = 0.4F * MathHelper.func_76126_a(f1 * 6.2831855F);
/* 218 */     float f4 = -0.4F * MathHelper.func_76126_a(swingProgress * 3.1415927F);
/* 219 */     matrixStack.func_227861_a_((f * (f2 + 0.64000005F)), (f3 + -0.6F + equippedProgress * -0.6F), (f4 + -0.71999997F));
/* 220 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * 45.0F, true));
/* 221 */     float f5 = MathHelper.func_76126_a(swingProgress * swingProgress * 3.1415927F);
/* 222 */     float f6 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 223 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * f6 * 70.0F, true));
/* 224 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * f5 * -20.0F, true));
/*     */     
/* 226 */     matrixStack.func_227861_a_((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 227 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * 120.0F, true));
/* 228 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 200.0F, true));
/* 229 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * -135.0F, true));
/* 230 */     matrixStack.func_227861_a_((f * 5.6F), 0.0D, 0.0D);
/*     */     
/* 232 */     IVertexBuilder vertex = null;
/* 233 */     float red = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getRed() / 255.0F) : 1.0F;
/* 234 */     float green = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getGreen() / 255.0F) : 1.0F;
/* 235 */     float blue = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getBlue() / 255.0F) : 1.0F;
/* 236 */     float alpha = overlay.isPresent() ? (((AbilityOverlay)overlay.get()).getColor().getAlpha() / 255.0F) : 1.0F;
/*     */     
/* 238 */     MorphModel morphModel = null;
/* 239 */     boolean renderLimb = false;
/* 240 */     boolean isPartial = false;
/*     */     
/* 242 */     if (info != null) {
/*     */       
/* 244 */       morphModel = info.getModel();
/* 245 */       isPartial = info.isPartial();
/* 246 */       renderLimb = info.shouldRenderFirstPersonHand();
/*     */     } 
/*     */     
/* 249 */     if (overlay.isPresent()) {
/*     */       
/* 251 */       if (((AbilityOverlay)overlay.get()).getTexture() != null) {
/* 252 */         vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(((AbilityOverlay)overlay.get()).getTexture()));
/*     */       } else {
/* 254 */         vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */       } 
/*     */     } else {
/*     */       
/* 258 */       vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(clientPlayer.func_110306_p()));
/*     */     } 
/*     */     
/* 261 */     if (Strings.isNullOrEmpty(dfProps.getZoanPoint()) || (isPartial && !renderLimb)) {
/*     */       
/* 263 */       PlayerModel model = (PlayerModel)((PlayerRenderer)renderer).func_217764_d();
/*     */       
/* 265 */       if (flag)
/*     */       {
/* 267 */         model.field_178723_h.field_78795_f = 0.0F;
/* 268 */         model.field_178723_h.field_78796_g = 0.02F;
/* 269 */         model.field_178723_h.field_78808_h = 0.1F;
/*     */         
/* 271 */         model.field_178723_h.field_78800_c = -5.0F;
/* 272 */         model.field_178723_h.field_78797_d = 2.0F;
/* 273 */         model.field_178723_h.field_78798_e = 0.0F;
/*     */         
/* 275 */         model.field_178732_b.func_217177_a(model.field_178723_h);
/*     */         
/* 277 */         model.field_178723_h.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/* 278 */         model.field_178732_b.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */       }
/*     */       else
/*     */       {
/* 282 */         model.field_178724_i.field_78795_f = 0.0F;
/* 283 */         model.field_178724_i.field_78796_g = 0.04F;
/* 284 */         model.field_178724_i.field_78808_h = -0.1F;
/*     */         
/* 286 */         model.field_178724_i.field_78800_c = 5.0F;
/* 287 */         model.field_178724_i.field_78797_d = 2.0F;
/* 288 */         model.field_178724_i.field_78798_e = 0.0F;
/*     */         
/* 290 */         model.field_178734_a.func_217177_a(model.field_178724_i);
/*     */         
/* 292 */         model.field_178724_i.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/* 293 */         model.field_178734_a.func_228309_a_(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 298 */     else if (morphModel != null && renderLimb) {
/*     */       
/* 300 */       ResourceLocation texture = info.getTexture((LivingEntity)clientPlayer);
/*     */       
/* 302 */       if (!overlay.isPresent() || ((AbilityOverlay)overlay.get()).getTexture() == null)
/*     */       {
/* 304 */         if (texture != null) {
/* 305 */           vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(texture));
/*     */         } else {
/* 307 */           vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */         } 
/*     */       }
/* 310 */       morphModel.renderFirstPersonArm(matrixStack, vertex, combinedLight, OverlayTexture.field_229196_a_, red, green, blue, alpha, side);
/*     */     } 
/*     */ 
/*     */     
/* 314 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MorphInfo getZoanInfo(LivingEntity entity) {
/* 319 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
/* 320 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 322 */     if (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity)) {
/* 323 */       return (MorphInfo)ModMorphs.YOMI_SKELETON.get();
/*     */     }
/*     */     
/* 326 */     Optional<MorphInfo> morph = devilFruitProps.getCurrentMorph();
/* 327 */     if (morph.isPresent()) {
/* 328 */       return morph.get();
/*     */     }
/*     */     
/* 331 */     for (IAbility ability : abilityProps.getEquippedAbilities()) {
/* 332 */       if (!ability.hasComponent(ModAbilityKeys.MORPH)) {
/*     */         continue;
/*     */       }
/*     */       
/* 336 */       MorphComponent comp = ability.getComponent(ModAbilityKeys.MORPH).get();
/* 337 */       boolean isMorphed = comp.isMorphed();
/* 338 */       if (isMorphed) {
/* 339 */         return comp.getMorphInfo();
/*     */       }
/*     */     } 
/*     */     
/* 343 */     for (IAbility ability : abilityProps.getEquippedAbilities()) {
/* 344 */       if (ability == null || !(ability instanceof IMorphAbility)) {
/*     */         continue;
/*     */       }
/* 347 */       IMorphAbility morphAbility = (IMorphAbility)ability;
/*     */       
/* 349 */       if (!morphAbility.isTransformationActive(entity)) {
/*     */         continue;
/*     */       }
/* 352 */       return morphAbility.getTransformation();
/*     */     } 
/*     */     
/* 355 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\MorphHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */