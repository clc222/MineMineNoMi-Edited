/*     */ package xyz.pixelatedw.mineminenomi.api.animations;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.HandSide;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ 
/*     */ 
/*     */ public abstract class Animation<E extends LivingEntity, M extends EntityModel>
/*     */   implements IAnimation<E, M>
/*     */ {
/*     */   private long startTime;
/*     */   private long runTime;
/*     */   private int animDuration;
/*     */   private AnimationId<? extends Animation<E, M>> animId;
/*  23 */   private AnimationComponent.State state = AnimationComponent.State.STOP;
/*     */   
/*     */   private IAnimationSetup<E> animationSetup;
/*     */   
/*     */   private IAnimationAngles<E, M> animationAngles;
/*     */   private IAnimationHeldItem<E> animationHeldItem;
/*     */   @Nullable
/*     */   private M model;
/*     */   
/*     */   public <A extends Animation<E, M>> Animation(AnimationId<A> animId) {
/*  33 */     this.animId = animId;
/*     */   }
/*     */   
/*     */   public void setAnimationSetup(IAnimationSetup<E> func) {
/*  37 */     this.animationSetup = func;
/*     */   }
/*     */   
/*     */   public void setAnimationAngles(IAnimationAngles<E, M> func) {
/*  41 */     this.animationAngles = func;
/*     */   }
/*     */   
/*     */   public void setAnimationHeldItem(IAnimationHeldItem<E> func) {
/*  45 */     this.animationHeldItem = func;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupAnimation(E entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/*  50 */     if (this.animationSetup != null) {
/*     */       try {
/*  52 */         this.animationSetup.setupAnimation(entity, matrixStack, buffer, packedLight, rotationYaw, partialTicks);
/*     */       }
/*  54 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnimationAngles(E entity, M entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  61 */     this.model = entityModel;
/*  62 */     if (this.animationAngles != null) {
/*     */       try {
/*  64 */         this.animationAngles.setAnimationAngles(entity, entityModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*  65 */         RendererHelper.hasModelChanged = true;
/*     */       }
/*  67 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupHeldItem(E entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/*  73 */     if (this.animationHeldItem != null) {
/*     */       try {
/*  75 */         this.animationHeldItem.setupHeldItem(entity, stack, transformType, handSide, matrixStack, renderBuffer, packedLight);
/*     */       }
/*  77 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void start(LivingEntity entity, int animDuration) {
/*  83 */     if (this.model != null) {
/*  84 */       RendererHelper.resetHumanoidModelToDefaultPivots((EntityModel)this.model);
/*     */     }
/*     */     
/*  87 */     this.startTime = entity.field_70173_aa;
/*  88 */     this.runTime = 0L;
/*  89 */     this.animDuration = animDuration;
/*  90 */     this.state = AnimationComponent.State.PLAY;
/*     */   }
/*     */   
/*     */   public void stop(LivingEntity entity) {
/*  94 */     this.runTime = 0L;
/*  95 */     this.state = AnimationComponent.State.STOP;
/*     */     
/*  97 */     if (this.model != null) {
/*  98 */       RendererHelper.resetHumanoidModelToDefaultPivots((EntityModel)this.model);
/*     */     }
/*     */   }
/*     */   
/*     */   public void tick(LivingEntity entity) {
/* 103 */     if (Minecraft.func_71410_x().func_147113_T()) {
/*     */       return;
/*     */     }
/*     */     
/* 107 */     if (isPlaying()) {
/* 108 */       this.runTime = entity.field_70173_aa - this.startTime;
/* 109 */       if (this.animDuration != -1 && this.animDuration <= 0) {
/* 110 */         stop(entity);
/*     */       }
/* 112 */       else if (this.animDuration != -1) {
/* 113 */         this.animDuration--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getTime() {
/* 119 */     return this.runTime;
/*     */   }
/*     */   
/*     */   public int getAnimationDuration() {
/* 123 */     return this.animDuration;
/*     */   }
/*     */   
/*     */   public AnimationComponent.State getState() {
/* 127 */     return this.state;
/*     */   }
/*     */   
/*     */   public boolean isPlaying() {
/* 131 */     return (this.state == AnimationComponent.State.PLAY);
/*     */   }
/*     */   
/*     */   public boolean isStopped() {
/* 135 */     return (this.state == AnimationComponent.State.STOP);
/*     */   }
/*     */   
/*     */   public AnimationId<? extends Animation<E, M>> getId() {
/* 139 */     return this.animId;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IAnimationHeldItem<E extends LivingEntity> {
/*     */     void setupHeldItem(E param1E, ItemStack param1ItemStack, ItemCameraTransforms.TransformType param1TransformType, HandSide param1HandSide, MatrixStack param1MatrixStack, IRenderTypeBuffer param1IRenderTypeBuffer, int param1Int);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IAnimationAngles<E extends LivingEntity, M extends EntityModel> {
/*     */     void setAnimationAngles(E param1E, M param1M, float param1Float1, float param1Float2, float param1Float3, float param1Float4, float param1Float5);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IAnimationSetup<E extends LivingEntity> {
/*     */     void setupAnimation(E param1E, MatrixStack param1MatrixStack, IRenderTypeBuffer param1IRenderTypeBuffer, int param1Int, float param1Float1, float param1Float2);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\animations\Animation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */