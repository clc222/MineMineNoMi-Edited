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
/*    */ public class HumanoidSniperModel<T extends OPEntity>
/*    */   extends HumanoidModel<T> {
/*    */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 15 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 17 */     if (!entity.func_184614_ca().func_190926_b()) {
/* 18 */       (func_187074_a(HandSide.LEFT)).field_78795_f = (func_187074_a(HandSide.LEFT)).field_78795_f * 0.5F - 0.31415927F;
/* 19 */       (func_187074_a(HandSide.LEFT)).field_78796_g = 0.0F;
/* 20 */       this.hasItem = true;
/*    */     } 
/*    */   }
/*    */   private boolean hasItem;
/*    */   
/*    */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 26 */     super.func_225599_a_(side, matrixStack);
/*    */     
/* 28 */     if (this.hasItem) {
/* 29 */       matrixStack.func_227861_a_(-0.3D, 0.7D, 0.0D);
/* 30 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, -75.0F, true));
/* 31 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-5.0F));
/* 32 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, -85.0F, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidSniperModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */