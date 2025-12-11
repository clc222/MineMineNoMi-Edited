/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.vector.Quaternion;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ public class HumanoidBomberModel
/*    */   extends HumanoidModel<OPEntity> {
/*    */   public void setupAnim(OPEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 15 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 17 */     if (!entity.func_184614_ca().func_190926_b()) {
/*    */       
/* 19 */       this.field_178723_h.field_78796_g = 1.2F;
/* 20 */       this.field_178724_i.field_78796_g = 0.8F;
/* 21 */       this.field_178723_h.field_78795_f = -2.243995F;
/* 22 */       this.field_178724_i.field_78795_f = -1.7453294F;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 29 */     super.func_225599_a_(side, matrixStack);
/* 30 */     matrixStack.func_227861_a_(-0.1D, -0.15D, -0.25D);
/* 31 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 35.0F, true));
/* 32 */     matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, -75.0F, true));
/* 33 */     matrixStack.func_227862_a_(1.25F, 1.25F, 1.25F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidBomberModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */