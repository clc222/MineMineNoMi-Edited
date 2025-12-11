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
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.Deadmau5HeadLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
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
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderNameplateEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EleclawRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ZoanMorphRenderer2<T extends LivingEntity, M extends MorphModel<T>> extends LivingRenderer<T, M> {
/*     */   private LivingRenderer<T, BipedModel<T>> originalRenderer;
/*     */   
/*     */   public ZoanMorphRenderer2(EntityRendererManager rendererManager, MorphInfo info) {
/*  52 */     super(rendererManager, (EntityModel)info.getModel(), 0.5F);
/*  53 */     if (info.getModel() != null && !info.isPartial()) {
/*  54 */       this.field_77045_g = (EntityModel)info.getModel();
/*     */     }
/*  56 */     this.field_76989_e = info.getShadowSize();
/*  57 */     this.info = info;
/*  58 */     func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/*  59 */     func_177094_a((LayerRenderer)new EleclawRenderer((IEntityRenderer)this));
/*  60 */     func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)this));
/*  61 */     removeLayer((Class)BipedArmorLayer.class);
/*  62 */     removeLayer((Class)CapeLayer.class);
/*  63 */     removeLayer((Class)ElytraLayer.class);
/*  64 */     removeLayer((Class)Deadmau5HeadLayer.class);
/*     */   }
/*     */   protected MorphInfo info; private boolean hasCulling = false;
/*     */   
/*     */   public void func_225623_a_(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  69 */     setModelVisibilities(entity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     matrixStack.func_227860_a_();
/*  76 */     ((MorphModel)this.field_77045_g).field_205061_a = func_77040_d((LivingEntity)entity, partialTicks);
/*     */     
/*  78 */     boolean shouldSit = (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx().shouldRiderSit());
/*  79 */     ((MorphModel)this.field_77045_g).field_217113_d = shouldSit;
/*  80 */     ((MorphModel)this.field_77045_g).field_217114_e = entity.func_70631_g_();
/*  81 */     float headYawOffset = MathHelper.func_219805_h(partialTicks, ((LivingEntity)entity).field_70760_ar, ((LivingEntity)entity).field_70761_aq);
/*  82 */     float headYawRotation = MathHelper.func_219805_h(partialTicks, ((LivingEntity)entity).field_70758_at, ((LivingEntity)entity).field_70759_as);
/*  83 */     float netHeadYaw = headYawRotation - headYawOffset;
/*  84 */     if (shouldSit && entity.func_184187_bx() instanceof LivingEntity) {
/*     */       
/*  86 */       LivingEntity livingentity = (LivingEntity)entity.func_184187_bx();
/*  87 */       headYawOffset = MathHelper.func_219805_h(partialTicks, livingentity.field_70760_ar, livingentity.field_70761_aq);
/*  88 */       netHeadYaw = headYawRotation - headYawOffset;
/*  89 */       float f3 = MathHelper.func_76142_g(netHeadYaw);
/*  90 */       if (f3 < -85.0F)
/*     */       {
/*  92 */         f3 = -85.0F;
/*     */       }
/*     */       
/*  95 */       if (f3 >= 85.0F)
/*     */       {
/*  97 */         f3 = 85.0F;
/*     */       }
/*     */       
/* 100 */       headYawOffset = headYawRotation - f3;
/* 101 */       if (f3 * f3 > 2500.0F)
/*     */       {
/* 103 */         headYawOffset += f3 * 0.2F;
/*     */       }
/*     */       
/* 106 */       netHeadYaw = headYawRotation - headYawOffset;
/*     */     } 
/*     */     
/* 109 */     float headPitch = MathHelper.func_219799_g(partialTicks, ((LivingEntity)entity).field_70127_C, ((LivingEntity)entity).field_70125_A);
/* 110 */     if (entity.func_213283_Z() == Pose.SLEEPING) {
/*     */       
/* 112 */       Direction direction = entity.func_213376_dz();
/* 113 */       if (direction != null) {
/*     */         
/* 115 */         float eyeHeight = entity.func_213307_e(Pose.STANDING) - 0.1F;
/* 116 */         matrixStack.func_227861_a_((-direction.func_82601_c() * eyeHeight), 0.0D, (-direction.func_82599_e() * eyeHeight));
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     float ageInTicks = func_77044_a((LivingEntity)entity, partialTicks);
/* 121 */     func_225621_a_(entity, matrixStack, ageInTicks, headYawOffset, partialTicks);
/* 122 */     matrixStack.func_227862_a_(-1.0F, -1.0F, 1.0F);
/* 123 */     this.info.preRenderCallback((LivingEntity)entity, matrixStack, partialTicks);
/* 124 */     func_225620_a_((LivingEntity)entity, matrixStack, partialTicks);
/* 125 */     matrixStack.func_227861_a_(0.0D, -1.5010000467300415D, 0.0D);
/* 126 */     float limbSwingAmount = 0.0F;
/* 127 */     float limbSwing = 0.0F;
/* 128 */     if (!shouldSit && entity.func_70089_S()) {
/*     */       
/* 130 */       limbSwingAmount = MathHelper.func_219799_g(partialTicks, ((LivingEntity)entity).field_184618_aE, ((LivingEntity)entity).field_70721_aZ);
/* 131 */       limbSwing = ((LivingEntity)entity).field_184619_aG - ((LivingEntity)entity).field_70721_aZ * (1.0F - partialTicks);
/* 132 */       if (entity.func_70631_g_())
/*     */       {
/* 134 */         limbSwing *= 3.0F;
/*     */       }
/*     */       
/* 137 */       if (limbSwingAmount > 1.0F)
/*     */       {
/* 139 */         limbSwingAmount = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/* 143 */     if (func_225622_a_((LivingEntity)entity)) {
/*     */       
/* 145 */       AnimationComponent ability = AbilityHelper.getActiveAnimation((LivingEntity)entity);
/* 146 */       if (ability != null) {
/*     */         
/* 148 */         ability.getAnimation().tick((LivingEntity)entity);
/* 149 */         ability.getAnimation().setupAnimation((LivingEntity)entity, matrixStack, buffer, packedLight, netHeadYaw, partialTicks);
/* 150 */         ability.getAnimation().setAnimationAngles((LivingEntity)entity, this.field_77045_g, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 156 */     if (!entity.func_175149_v()) {
/* 157 */       for (LayerRenderer layerrenderer : this.field_177097_h) {
/* 158 */         layerrenderer.func_225628_a_(matrixStack, buffer, packedLight, (Entity)entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
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
/* 182 */     matrixStack.func_227865_b_();
/*     */ 
/*     */ 
/*     */     
/* 186 */     RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent((Entity)entity, entity.func_145748_c_(), (EntityRenderer)this, matrixStack, buffer, packedLight, partialTicks);
/* 187 */     MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
/* 188 */     if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || func_177070_b((LivingEntity)entity)))
/*     */     {
/* 190 */       func_225629_a_((Entity)entity, renderNameplateEvent.getContent(), matrixStack, buffer, packedLight);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_225621_a_(T entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 197 */     boolean isRotationBlocked = entity.func_70651_bq().stream().anyMatch(instance -> (instance.func_188419_a() instanceof ModEffect && ((ModEffect)instance.func_188419_a()).isBlockingRotations()));
/* 198 */     if (isRotationBlocked) {
/*     */       return;
/*     */     }
/* 201 */     if (this.info == ModMorphs.ZOU_GUARD.get()) {
/*     */       
/* 203 */       Pose pose = entity.func_213283_Z();
/* 204 */       if (pose != Pose.SLEEPING) {
/* 205 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - rotationYaw));
/*     */       }
/* 207 */       if (((LivingEntity)entity).field_70725_aQ > 0)
/*     */       {
/* 209 */         float f = (((LivingEntity)entity).field_70725_aQ + partialTicks - 1.0F) / 20.0F * 1.6F;
/* 210 */         f = MathHelper.func_76129_c(f);
/* 211 */         if (f > 1.0F)
/*     */         {
/* 213 */           f = 1.0F;
/*     */         }
/*     */         
/* 216 */         matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(f * func_77037_a((LivingEntity)entity)));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 221 */       super.func_225621_a_((LivingEntity)entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderModel(T entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 227 */     ((MorphModel)func_217764_d()).func_212843_a_((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/*     */     
/* 229 */     boolean isRotationBlocked = entity.func_70651_bq().stream().anyMatch(instance -> (instance.func_188419_a() instanceof ModEffect && ((ModEffect)instance.func_188419_a()).isBlockingRotations()));
/* 230 */     if (!isRotationBlocked) {
/* 231 */       ((MorphModel)func_217764_d()).func_225597_a_((LivingEntity)entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     }
/*     */     
/* 234 */     boolean flag = func_225622_a_((LivingEntity)entity);
/* 235 */     boolean flag1 = (!flag && !entity.func_98034_c((PlayerEntity)(Minecraft.func_71410_x()).field_71439_g));
/* 236 */     RenderType renderType = ModRenderTypes.getZoanRenderType(getTextureLocation(entity));
/* 237 */     if (this.hasCulling) {
/* 238 */       renderType = ModRenderTypes.getZoanWithCullingRenderType(getTextureLocation(entity));
/*     */     }
/* 240 */     if (renderType != null && flag) {
/*     */       
/* 242 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 243 */       int i = func_229117_c_((LivingEntity)entity, func_225625_b_((LivingEntity)entity, partialTicks));
/* 244 */       ((MorphModel)this.field_77045_g).func_225598_a_(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setModelVisibilities(T clientPlayer) {
/* 250 */     MorphModel morphModel = (MorphModel)func_217764_d();
/* 251 */     if (clientPlayer.func_175149_v()) {
/*     */       
/* 253 */       morphModel.func_178719_a(false);
/* 254 */       morphModel.field_78116_c.field_78806_j = true;
/* 255 */       morphModel.field_178720_f.field_78806_j = true;
/*     */     }
/*     */     else {
/*     */       
/* 259 */       ItemStack itemstack = clientPlayer.func_184614_ca();
/* 260 */       ItemStack itemstack1 = clientPlayer.func_184592_cb();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 268 */       morphModel.field_228270_o_ = clientPlayer.func_213453_ef();
/* 269 */       BipedModel.ArmPose bipedmodel$armpose = getArmPose(clientPlayer, itemstack, itemstack1, Hand.MAIN_HAND);
/* 270 */       BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(clientPlayer, itemstack, itemstack1, Hand.OFF_HAND);
/* 271 */       if (clientPlayer.func_184591_cq() == HandSide.RIGHT) {
/*     */         
/* 273 */         morphModel.field_187076_m = bipedmodel$armpose;
/* 274 */         morphModel.field_187075_l = bipedmodel$armpose1;
/*     */       }
/*     */       else {
/*     */         
/* 278 */         morphModel.field_187076_m = bipedmodel$armpose1;
/* 279 */         morphModel.field_187075_l = bipedmodel$armpose;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeLayer(Class<? extends LayerRenderer> clz) {
/* 286 */     this.field_177097_h.removeIf(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   private BipedModel.ArmPose getArmPose(T playerIn, ItemStack itemStackMain, ItemStack itemStackOff, Hand handIn) {
/* 291 */     BipedModel.ArmPose bipedmodel$armpose = BipedModel.ArmPose.EMPTY;
/* 292 */     ItemStack itemstack = (handIn == Hand.MAIN_HAND) ? itemStackMain : itemStackOff;
/* 293 */     if (!itemstack.func_190926_b()) {
/*     */       
/* 295 */       bipedmodel$armpose = BipedModel.ArmPose.ITEM;
/* 296 */       if (playerIn.func_184605_cv() > 0) {
/*     */         
/* 298 */         UseAction useaction = itemstack.func_77975_n();
/* 299 */         if (useaction == UseAction.BLOCK)
/*     */         {
/* 301 */           bipedmodel$armpose = BipedModel.ArmPose.BLOCK;
/*     */         }
/* 303 */         else if (useaction == UseAction.BOW)
/*     */         {
/* 305 */           bipedmodel$armpose = BipedModel.ArmPose.BOW_AND_ARROW;
/*     */         }
/* 307 */         else if (useaction == UseAction.SPEAR)
/*     */         {
/* 309 */           bipedmodel$armpose = BipedModel.ArmPose.THROW_SPEAR;
/*     */         }
/* 311 */         else if (useaction == UseAction.CROSSBOW && handIn == playerIn.func_184600_cs())
/*     */         {
/* 313 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_CHARGE;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 318 */         boolean flag3 = (itemStackMain.func_77973_b() == Items.field_222114_py);
/* 319 */         boolean flag = CrossbowItem.func_220012_d(itemStackMain);
/* 320 */         boolean flag1 = (itemStackOff.func_77973_b() == Items.field_222114_py);
/* 321 */         boolean flag2 = CrossbowItem.func_220012_d(itemStackOff);
/* 322 */         if (flag3 && flag)
/*     */         {
/* 324 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
/*     */         }
/*     */         
/* 327 */         if (flag1 && flag2 && itemStackMain.func_77973_b().func_77661_b(itemStackMain) == UseAction.NONE)
/*     */         {
/* 329 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 334 */     return bipedmodel$armpose;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(T entity) {
/* 339 */     if (this.info.getTexture((LivingEntity)entity) != null && !this.info.isPartial()) {
/* 340 */       return this.info.getTexture((LivingEntity)entity);
/*     */     }
/*     */     
/* 343 */     if (entity instanceof AbstractClientPlayerEntity) {
/* 344 */       return ((AbstractClientPlayerEntity)entity).func_110306_p();
/*     */     }
/*     */     
/* 347 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getMorphInfo() {
/* 352 */     return this.info;
/*     */   }
/*     */   
/*     */   public void setCullingState(boolean flag) {
/* 356 */     this.hasCulling = flag;
/*     */   }
/*     */   
/*     */   public void setOriginalRenderer(LivingRenderer<T, BipedModel<T>> original) {
/* 360 */     this.originalRenderer = original;
/*     */   }
/*     */   
/*     */   public LivingRenderer<T, BipedModel<T>> getOriginalRenderer() {
/* 364 */     return this.originalRenderer;
/*     */   }
/*     */   
/*     */   public static class Factory<T extends LivingEntity> implements IRenderFactory<T> {
/*     */     private MorphInfo info;
/*     */     private boolean hasCulling;
/*     */     
/*     */     public Factory(MorphInfo info) {
/* 372 */       this.info = info;
/*     */     }
/*     */     
/*     */     public Factory(MorphInfo info, boolean hasCulling) {
/* 376 */       this.info = info;
/* 377 */       this.hasCulling = hasCulling;
/*     */     }
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 382 */       ZoanMorphRenderer2<LivingEntity, MorphModel<LivingEntity>> renderer = new ZoanMorphRenderer2<>(manager, this.info);
/* 383 */       renderer.setCullingState(this.hasCulling);
/* 384 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\ZoanMorphRenderer2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */