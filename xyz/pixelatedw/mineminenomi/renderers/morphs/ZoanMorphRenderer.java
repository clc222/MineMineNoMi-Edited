/*     */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.Deadmau5HeadLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.CrossbowItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.RenderNameplateEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.RenderMorphEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EleclawRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ZoanMorphRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends PlayerRenderer {
/*     */   protected MorphInfo info;
/*     */   
/*     */   public ZoanMorphRenderer(EntityRendererManager rendererManager, MorphInfo info) {
/*  58 */     this(rendererManager, info, false);
/*     */   }
/*     */   private boolean hasCulling = false;
/*     */   
/*     */   public ZoanMorphRenderer(EntityRendererManager rendererManager, MorphInfo info, boolean smallHands) {
/*  63 */     super(rendererManager, smallHands);
/*  64 */     if (info.getModel() != null && !info.isPartial())
/*  65 */       this.field_77045_g = (EntityModel)info.getModel(); 
/*  66 */     this.field_76989_e = info.getShadowSize();
/*  67 */     this.info = info;
/*  68 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/*  69 */     func_177094_a((LayerRenderer)new EleclawRenderer((IEntityRenderer)this));
/*  70 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/*  71 */     removeLayer((Class)BipedArmorLayer.class);
/*  72 */     removeLayer((Class)CapeLayer.class);
/*  73 */     removeLayer((Class)ElytraLayer.class);
/*  74 */     removeLayer((Class)Deadmau5HeadLayer.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  80 */     setModelVisibilities(entity);
/*     */     
/*  82 */     if (MinecraftForge.EVENT_BUS.post((Event)new RenderMorphEvent.Pre((LivingEntity)entity, this, partialTicks, matrixStack, buffer, packedLight))) {
/*     */       return;
/*     */     }
/*  85 */     matrixStack.func_227860_a_();
/*  86 */     ((PlayerModel)this.field_77045_g).field_205061_a = func_77040_d((LivingEntity)entity, partialTicks);
/*     */     
/*  88 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  89 */     ((PlayerModel)this.field_77045_g).field_217113_d = shouldSit;
/*  90 */     ((PlayerModel)this.field_77045_g).field_217114_e = entity.func_70631_g_();
/*  91 */     float headYawOffset = MathHelper.func_219805_h(partialTicks, entity.field_70760_ar, entity.field_70761_aq);
/*  92 */     float headYawRotation = MathHelper.func_219805_h(partialTicks, entity.field_70758_at, entity.field_70759_as);
/*  93 */     float netHeadYaw = headYawRotation - headYawOffset;
/*  94 */     if (shouldSit && entity.func_184187_bx() instanceof LivingEntity) {
/*     */       
/*  96 */       LivingEntity livingentity = (LivingEntity)entity.func_184187_bx();
/*  97 */       headYawOffset = MathHelper.func_219805_h(partialTicks, livingentity.field_70760_ar, livingentity.field_70761_aq);
/*  98 */       netHeadYaw = headYawRotation - headYawOffset;
/*  99 */       float f3 = MathHelper.func_76142_g(netHeadYaw);
/* 100 */       if (f3 < -85.0F)
/*     */       {
/* 102 */         f3 = -85.0F;
/*     */       }
/*     */       
/* 105 */       if (f3 >= 85.0F)
/*     */       {
/* 107 */         f3 = 85.0F;
/*     */       }
/*     */       
/* 110 */       headYawOffset = headYawRotation - f3;
/* 111 */       if (f3 * f3 > 2500.0F)
/*     */       {
/* 113 */         headYawOffset += f3 * 0.2F;
/*     */       }
/*     */       
/* 116 */       netHeadYaw = headYawRotation - headYawOffset;
/*     */     } 
/*     */     
/* 119 */     float headPitch = MathHelper.func_219799_g(partialTicks, entity.field_70127_C, entity.field_70125_A);
/* 120 */     if (entity.func_213283_Z() == Pose.SLEEPING) {
/*     */       
/* 122 */       Direction direction = entity.func_213376_dz();
/* 123 */       if (direction != null) {
/*     */         
/* 125 */         float eyeHeight = entity.func_213307_e(Pose.STANDING) - 0.1F;
/* 126 */         matrixStack.func_227861_a_((-direction.func_82601_c() * eyeHeight), 0.0D, (-direction.func_82599_e() * eyeHeight));
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     float ageInTicks = func_77044_a((LivingEntity)entity, partialTicks);
/* 131 */     func_225621_a_(entity, matrixStack, ageInTicks, headYawOffset, partialTicks);
/* 132 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 133 */     this.info.preRenderCallback((LivingEntity)entity, matrixStack, partialTicks);
/* 134 */     func_225620_a_(entity, matrixStack, partialTicks);
/* 135 */     matrixStack.func_227861_a_(0.0D, -1.5010000467300415D, 0.0D);
/* 136 */     float limbSwingAmount = 0.0F;
/* 137 */     float limbSwing = 0.0F;
/* 138 */     if (!shouldSit && entity.func_70089_S()) {
/*     */       
/* 140 */       limbSwingAmount = MathHelper.func_219799_g(partialTicks, entity.field_184618_aE, entity.field_70721_aZ);
/* 141 */       limbSwing = entity.field_184619_aG - entity.field_70721_aZ * (1.0F - partialTicks);
/* 142 */       if (entity.func_70631_g_())
/*     */       {
/* 144 */         limbSwing *= 3.0F;
/*     */       }
/*     */       
/* 147 */       if (limbSwingAmount > 1.0F)
/*     */       {
/* 149 */         limbSwingAmount = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/* 153 */     if (func_225622_a_((LivingEntity)entity)) {
/* 154 */       AnimationComponent ability = AbilityHelper.getActiveAnimation((LivingEntity)entity);
/* 155 */       if (ability != null) {
/* 156 */         ability.getAnimation().tick((LivingEntity)entity);
/* 157 */         ability.getAnimation().setupAnimation((LivingEntity)entity, matrixStack, buffer, packedLight, netHeadYaw, partialTicks);
/* 158 */         ability.getAnimation().setAnimationAngles((LivingEntity)entity, this.field_77045_g, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */       } 
/*     */     } 
/*     */     
/* 162 */     renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 164 */     if (!entity.func_175149_v())
/*     */     {
/* 166 */       for (LayerRenderer layerrenderer : this.field_177097_h)
/*     */       {
/* 168 */         layerrenderer.func_225628_a_(matrixStack, buffer, packedLight, (Entity)entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     matrixStack.func_227865_b_();
/*     */     
/* 194 */     MinecraftForge.EVENT_BUS.post((Event)new RenderMorphEvent.Post((LivingEntity)entity, this, partialTicks, matrixStack, buffer, packedLight));
/*     */     
/* 196 */     RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent((Entity)entity, entity.func_145748_c_(), (EntityRenderer)this, matrixStack, buffer, packedLight, partialTicks);
/* 197 */     MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
/* 198 */     if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || func_177070_b((LivingEntity)entity)))
/*     */     {
/* 200 */       func_225629_a_(entity, renderNameplateEvent.getContent(), matrixStack, buffer, packedLight);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225621_a_(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 207 */     boolean isRotationBlocked = entity.func_70651_bq().stream().anyMatch(instance -> (instance.func_188419_a() instanceof ModEffect && ((ModEffect)instance.func_188419_a()).isBlockingRotations()));
/* 208 */     if (isRotationBlocked) {
/*     */       return;
/*     */     }
/* 211 */     if (this.info == ModMorphs.ZOU_GUARD.get()) {
/*     */       
/* 213 */       Pose pose = entity.func_213283_Z();
/* 214 */       if (pose != Pose.SLEEPING) {
/* 215 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - rotationYaw));
/*     */       }
/* 217 */       if (entity.field_70725_aQ > 0)
/*     */       {
/* 219 */         float f = (entity.field_70725_aQ + partialTicks - 1.0F) / 20.0F * 1.6F;
/* 220 */         f = MathHelper.func_76129_c(f);
/* 221 */         if (f > 1.0F)
/*     */         {
/* 223 */           f = 1.0F;
/*     */         }
/*     */         
/* 226 */         matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(f * func_77037_a((LivingEntity)entity)));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 231 */       super.func_225621_a_(entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 237 */     ((PlayerModel)func_217764_d()).func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/*     */     
/* 239 */     boolean isRotationBlocked = entity.func_70651_bq().stream().anyMatch(instance -> (instance.func_188419_a() instanceof ModEffect && ((ModEffect)instance.func_188419_a()).isBlockingRotations()));
/* 240 */     if (!isRotationBlocked) {
/* 241 */       ((PlayerModel)func_217764_d()).func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     }
/*     */     
/* 244 */     boolean flag = func_225622_a_((LivingEntity)entity);
/* 245 */     boolean flag1 = (!flag && !entity.func_98034_c((PlayerEntity)(Minecraft.func_71410_x()).field_71439_g));
/* 246 */     RenderType renderType = ModRenderTypes.getZoanRenderType(func_110775_a(entity));
/* 247 */     if (this.hasCulling) {
/* 248 */       renderType = ModRenderTypes.getZoanWithCullingRenderType(func_110775_a(entity));
/*     */     }
/* 250 */     if (renderType != null && flag) {
/*     */       
/* 252 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 253 */       int i = func_229117_c_((LivingEntity)entity, func_225625_b_((LivingEntity)entity, partialTicks));
/* 254 */       ((PlayerModel)this.field_77045_g).func_225598_a_(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setModelVisibilities(AbstractClientPlayerEntity clientPlayer) {
/* 260 */     PlayerModel<AbstractClientPlayerEntity> playermodel = (PlayerModel<AbstractClientPlayerEntity>)func_217764_d();
/* 261 */     if (clientPlayer.func_175149_v()) {
/*     */       
/* 263 */       playermodel.func_178719_a(false);
/* 264 */       playermodel.field_78116_c.field_78806_j = true;
/* 265 */       playermodel.field_178720_f.field_78806_j = true;
/*     */     }
/*     */     else {
/*     */       
/* 269 */       ItemStack itemstack = clientPlayer.func_184614_ca();
/* 270 */       ItemStack itemstack1 = clientPlayer.func_184592_cb();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 278 */       playermodel.field_228270_o_ = clientPlayer.func_213453_ef();
/* 279 */       BipedModel.ArmPose bipedmodel$armpose = getArmPose(clientPlayer, itemstack, itemstack1, Hand.MAIN_HAND);
/* 280 */       BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(clientPlayer, itemstack, itemstack1, Hand.OFF_HAND);
/* 281 */       if (clientPlayer.func_184591_cq() == HandSide.RIGHT) {
/*     */         
/* 283 */         playermodel.field_187076_m = bipedmodel$armpose;
/* 284 */         playermodel.field_187075_l = bipedmodel$armpose1;
/*     */       }
/*     */       else {
/*     */         
/* 288 */         playermodel.field_187076_m = bipedmodel$armpose1;
/* 289 */         playermodel.field_187075_l = bipedmodel$armpose;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeLayer(Class<? extends LayerRenderer> clz) {
/* 296 */     this.field_177097_h.removeIf(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   private BipedModel.ArmPose getArmPose(AbstractClientPlayerEntity playerIn, ItemStack itemStackMain, ItemStack itemStackOff, Hand handIn) {
/* 301 */     BipedModel.ArmPose bipedmodel$armpose = BipedModel.ArmPose.EMPTY;
/* 302 */     ItemStack itemstack = (handIn == Hand.MAIN_HAND) ? itemStackMain : itemStackOff;
/* 303 */     if (!itemstack.func_190926_b()) {
/*     */       
/* 305 */       bipedmodel$armpose = BipedModel.ArmPose.ITEM;
/* 306 */       if (playerIn.func_184605_cv() > 0) {
/*     */         
/* 308 */         UseAction useaction = itemstack.func_77975_n();
/* 309 */         if (useaction == UseAction.BLOCK)
/*     */         {
/* 311 */           bipedmodel$armpose = BipedModel.ArmPose.BLOCK;
/*     */         }
/* 313 */         else if (useaction == UseAction.BOW)
/*     */         {
/* 315 */           bipedmodel$armpose = BipedModel.ArmPose.BOW_AND_ARROW;
/*     */         }
/* 317 */         else if (useaction == UseAction.SPEAR)
/*     */         {
/* 319 */           bipedmodel$armpose = BipedModel.ArmPose.THROW_SPEAR;
/*     */         }
/* 321 */         else if (useaction == UseAction.CROSSBOW && handIn == playerIn.func_184600_cs())
/*     */         {
/* 323 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_CHARGE;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 328 */         boolean flag3 = (itemStackMain.func_77973_b() == Items.field_222114_py);
/* 329 */         boolean flag = CrossbowItem.func_220012_d(itemStackMain);
/* 330 */         boolean flag1 = (itemStackOff.func_77973_b() == Items.field_222114_py);
/* 331 */         boolean flag2 = CrossbowItem.func_220012_d(itemStackOff);
/* 332 */         if (flag3 && flag)
/*     */         {
/* 334 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
/*     */         }
/*     */         
/* 337 */         if (flag1 && flag2 && itemStackMain.func_77973_b().func_77661_b(itemStackMain) == UseAction.NONE)
/*     */         {
/* 339 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 344 */     return bipedmodel$armpose;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 350 */     if (this.info instanceof MorphInfo && this.info.getTexture((LivingEntity)entity) != null && !this.info.isPartial()) {
/* 351 */       return this.info.getTexture((LivingEntity)entity);
/*     */     }
/* 353 */     return entity.func_110306_p();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCullingState(boolean flag) {
/* 358 */     this.hasCulling = flag;
/*     */   }
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     private MorphInfo info;
/*     */     private boolean hasCulling;
/*     */     
/*     */     public Factory(MorphInfo info) {
/* 368 */       this.info = info;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(MorphInfo info, boolean hasCulling) {
/* 373 */       this.info = info;
/* 374 */       this.hasCulling = hasCulling;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 380 */       ZoanMorphRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new ZoanMorphRenderer<>(manager, this.info);
/* 381 */       renderer.setCullingState(this.hasCulling);
/* 382 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\ZoanMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */