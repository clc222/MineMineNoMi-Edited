/*     */ package xyz.pixelatedw.mineminenomi.animations.tori;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.PhoenixAssaultPartialModel;
/*     */ 
/*     */ public class PhoenixAssaultFlyAnimation
/*     */   extends Animation<LivingEntity, BipedModel>
/*     */ {
/*     */   private static final double THRESHOLD_1 = 0.2D;
/*     */   private static final double THRESHOLD_2 = 0.6D;
/*     */   
/*     */   public PhoenixAssaultFlyAnimation(AnimationId<PhoenixAssaultFlyAnimation> animId) {
/*  19 */     super(animId);
/*  20 */     setAnimationSetup(this::setup);
/*  21 */     setAnimationAngles(this::angles);
/*     */   }
/*     */   
/*     */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/*  25 */     double posXDiff = Math.abs(entity.field_70169_q - entity.func_226277_ct_());
/*  26 */     double posZDiff = Math.abs(entity.field_70166_s - entity.func_226281_cx_());
/*  27 */     Vector3d look = entity.func_70040_Z();
/*     */     
/*  29 */     Vector3d oldPos = new Vector3d(entity.field_70169_q, entity.field_70167_r, entity.field_70166_s);
/*  30 */     double movementDot = entity.func_213303_ch().func_178788_d(oldPos).func_72432_b().func_72430_b(look);
/*     */     
/*  32 */     float angles = 0.0F;
/*     */ 
/*     */     
/*  35 */     if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/*  36 */       angles = (float)Math.toRadians((movementDot > 0.0D) ? 45.0D : -45.0D);
/*     */     }
/*     */     
/*  39 */     if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/*  40 */       angles = (float)Math.toRadians((movementDot > 0.0D) ? 60.0D : -60.0D);
/*     */     }
/*     */     
/*  43 */     model.field_78115_e.field_78795_f = angles;
/*     */     
/*  45 */     if (entity.field_70733_aJ <= 0.0F) {
/*  46 */       model.field_178723_h.field_78795_f = angles;
/*  47 */       model.field_178724_i.field_78795_f = angles;
/*     */     } 
/*     */     
/*  50 */     if (model instanceof PhoenixAssaultPartialModel) {
/*  51 */       PhoenixAssaultPartialModel model2 = (PhoenixAssaultPartialModel)model;
/*     */       
/*  53 */       if (movementDot > 0.0D) {
/*  54 */         if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/*  55 */           model2.tail3.field_78797_d = 6.2F;
/*  56 */           model2.tail3.field_78798_e = 8.0F;
/*     */           
/*  58 */           model.field_178722_k.field_78797_d = 8.0F;
/*  59 */           model.field_178722_k.field_78798_e = 8.0F;
/*  60 */           model.field_178722_k.field_78795_f = angles + 0.4F;
/*     */         } 
/*     */         
/*  63 */         if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/*  64 */           model2.tail3.field_78797_d = 4.0F;
/*  65 */           model2.tail3.field_78798_e = 8.0F;
/*     */           
/*  67 */           model.field_178722_k.field_78797_d = 5.6F;
/*  68 */           model.field_178722_k.field_78798_e = 10.0F;
/*  69 */           model.field_178722_k.field_78795_f = angles + 0.4F;
/*     */         } 
/*  71 */       } else if (movementDot < 0.0D) {
/*  72 */         if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/*  73 */           model2.tail3.field_78797_d = 11.5F;
/*  74 */           model2.tail3.field_78798_e = -8.0F;
/*     */           
/*  76 */           model.field_178722_k.field_78797_d = 8.0F;
/*  77 */           model.field_178722_k.field_78798_e = -8.0F;
/*  78 */           model.field_178722_k.field_78795_f = angles - 0.4F;
/*     */         } 
/*     */         
/*  81 */         if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/*  82 */           model2.tail3.field_78797_d = 9.3F;
/*  83 */           model2.tail3.field_78798_e = -8.0F;
/*     */           
/*  85 */           model.field_178722_k.field_78797_d = 5.6F;
/*  86 */           model.field_178722_k.field_78798_e = -10.0F;
/*  87 */           model.field_178722_k.field_78795_f = angles - 0.4F;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/*  94 */     double posXDiff = Math.abs(entity.field_70169_q - entity.func_226277_ct_());
/*  95 */     double posZDiff = Math.abs(entity.field_70166_s - entity.func_226281_cx_());
/*     */     
/*  97 */     double drop = 0.0D;
/*     */     
/*  99 */     if (posXDiff >= 0.2D || posZDiff >= 0.2D) {
/* 100 */       drop = 0.3D;
/* 101 */     } else if (posXDiff >= 0.6D || posZDiff >= 0.6D) {
/* 102 */       drop = 0.4D;
/*     */     } 
/*     */     
/* 105 */     matrixStack.func_227861_a_(0.0D, drop, 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\tori\PhoenixAssaultFlyAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */