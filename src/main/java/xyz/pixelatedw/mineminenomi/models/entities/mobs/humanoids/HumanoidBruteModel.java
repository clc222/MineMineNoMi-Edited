/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ 
/*    */ public class HumanoidBruteModel<T extends CreatureEntity> extends HumanoidModel<T> {
/*    */   private boolean hasItem;
/*    */   private boolean hasAnimationPlaying;
/*    */   
/*    */   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 20 */     super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 22 */     AnimationComponent anim = AbilityHelper.getActiveAnimation((LivingEntity)entity);
/* 23 */     if (!entity.func_184614_ca().func_190926_b() && ItemsHelper.isSword(entity.func_184614_ca())) {
/* 24 */       this.hasItem = true;
/* 25 */       this.hasAnimationPlaying = (anim != null && anim.isPlaying());
/*    */       
/* 27 */       if (!this.hasAnimationPlaying) {
/* 28 */         (func_187074_a(entity.func_184591_cq())).field_78800_c += 2.0F;
/* 29 */         (func_187074_a(entity.func_184591_cq())).field_78798_e += 0.65F;
/* 30 */         (func_187074_a(entity.func_184591_cq())).field_78795_f += (float)Math.toRadians(160.0D);
/* 31 */         (func_187074_a(entity.func_184591_cq())).field_78808_h += (float)Math.toRadians(-30.0D);
/* 32 */         if (this.field_217112_c > 0.0F) {
/* 33 */           (func_187074_a(entity.func_184591_cq())).field_78800_c -= 2.0F;
/* 34 */           (func_187074_a(entity.func_184591_cq())).field_78798_e -= 0.65F;
/* 35 */           (func_187074_a(entity.func_184591_cq())).field_78808_h -= (float)Math.toRadians(-60.0D);
/*    */         } 
/*    */       } 
/*    */     } else {
/*    */       
/* 40 */       this.hasItem = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setupAttackAnimation(T entity, float ageInTicks) {
/* 46 */     if (entity.func_184614_ca().func_190926_b()) {
/* 47 */       super.setupAttackAnimation(entity, ageInTicks);
/*    */       
/*    */       return;
/*    */     } 
/* 51 */     if (this.field_217112_c > 0.0F) {
/* 52 */       HandSide handside = func_217147_a((LivingEntity)entity);
/* 53 */       ModelRenderer modelrenderer = func_187074_a(handside);
/* 54 */       float f = this.field_217112_c;
/* 55 */       this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f) * 6.2831855F) * 0.2F;
/* 56 */       if (handside == HandSide.LEFT) {
/* 57 */         this.field_78115_e.field_78796_g *= -1.0F;
/*    */       }
/*    */       
/* 60 */       this.field_178723_h.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 61 */       this.field_178723_h.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 62 */       this.field_178724_i.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 63 */       this.field_178724_i.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 64 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 65 */       this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 66 */       this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/* 67 */       modelrenderer.field_78795_f += MathHelper.func_76126_a(this.field_217112_c * 3.1415927F) * 4.0F;
/* 68 */       modelrenderer.field_78796_g += 0.1F * this.field_217112_c * 3.1415927F;
/* 69 */       modelrenderer.field_78808_h -= 0.9F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_225599_a_(HandSide side, MatrixStack matrixStack) {
/* 75 */     super.func_225599_a_(side, matrixStack);
/*    */     
/* 77 */     if (this.hasItem)
/* 78 */       if (this.field_217112_c > 0.0F) {
/* 79 */         matrixStack.func_227861_a_(0.0D, 0.0D, -0.1D);
/* 80 */         matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(15.0F));
/* 81 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-15.0F));
/*    */       
/*    */       }
/* 84 */       else if (!this.hasAnimationPlaying) {
/* 85 */         matrixStack.func_227861_a_(-0.27D, 0.0D, -0.1D);
/* 86 */         matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(15.0F));
/* 87 */         matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(-30.0F));
/* 88 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-85.0F));
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\HumanoidBruteModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */