/*    */ package xyz.pixelatedw.mineminenomi.animations.fishmen;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.effects.SpiralEffectModel;
/*    */ 
/*    */ public class SharkOnToothAnimation
/*    */   extends Animation<LivingEntity, BipedModel> {
/* 19 */   private SpiralEffectModel model = new SpiralEffectModel();
/*    */   
/*    */   public SharkOnToothAnimation(AnimationId<SharkOnToothAnimation> animId) {
/* 22 */     super(animId);
/* 23 */     setAnimationSetup(this::setup);
/* 24 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 28 */     entityModel.field_78116_c.field_78795_f = (float)Math.toRadians(-90.0D);
/* 29 */     entityModel.field_78116_c.field_78796_g = (float)Math.toRadians(0.0D);
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 33 */     matrixStack.func_227860_a_();
/* 34 */     matrixStack.func_227861_a_(0.0D, 0.2D, 0.0D);
/* 35 */     matrixStack.func_227862_a_(2.0F, 2.0F, 2.0F);
/* 36 */     IVertexBuilder ivb = buffer.getBuffer(RenderType.func_228644_e_(ModResources.PROJ_EFFECT_2));
/* 37 */     this.model.func_225597_a_((Entity)entity, 0.0F, 0.0F, entity.field_70173_aa, 0.0F, 0.0F);
/* 38 */     this.model.func_225598_a_(matrixStack, ivb, packedLight, OverlayTexture.field_229196_a_, 1.0F, 1.0F, 1.0F, 0.5F);
/* 39 */     matrixStack.func_227865_b_();
/*    */     
/* 41 */     matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(90.0F));
/* 42 */     float rot = (float)(getTime() * Math.PI * 10.0D);
/* 43 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(rot));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\fishmen\SharkOnToothAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */