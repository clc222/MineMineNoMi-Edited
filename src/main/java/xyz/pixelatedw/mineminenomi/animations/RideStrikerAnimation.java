/*    */ package xyz.pixelatedw.mineminenomi.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.Animation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public class RideStrikerAnimation
/*    */   extends Animation<LivingEntity, BipedModel>
/*    */ {
/*    */   public RideStrikerAnimation(AnimationId<RideStrikerAnimation> animId) {
/* 18 */     super(animId);
/* 19 */     setAnimationSetup(this::setup);
/* 20 */     setAnimationAngles(this::angles);
/*    */   }
/*    */   
/*    */   public void angles(LivingEntity entity, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     IDevilFruit props = DevilFruitCapability.get(entity);
/* 25 */     if (!props.hasDevilFruit(ModAbilities.MERA_MERA_NO_MI)) {
/*    */       return;
/*    */     }
/* 28 */     model.field_78116_c.field_78796_g = (float)(model.field_78116_c.field_78796_g + Math.toRadians(45.0D));
/* 29 */     model.field_178722_k.field_78795_f = 0.0F;
/* 30 */     model.field_178721_j.field_78795_f = 0.0F;
/* 31 */     model.field_178721_j.field_78796_g = (float)(model.field_178721_j.field_78796_g + Math.toRadians(40.0D));
/*    */   }
/*    */   
/*    */   public void setup(LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 35 */     IDevilFruit props = DevilFruitCapability.get(entity);
/* 36 */     if (!props.hasDevilFruit(ModAbilities.MERA_MERA_NO_MI)) {
/*    */       return;
/*    */     }
/* 39 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-50.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\animations\RideStrikerAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */