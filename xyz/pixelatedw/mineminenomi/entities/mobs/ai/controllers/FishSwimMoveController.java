/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.controller.MovementController;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class FishSwimMoveController<T extends MobEntity>
/*    */   extends MovementController {
/*    */   public FishSwimMoveController(T entity) {
/* 13 */     super((MobEntity)entity);
/* 14 */     this.entity = entity;
/*    */   }
/*    */   private final T entity;
/*    */   
/*    */   public void func_75641_c() {
/* 19 */     if (this.entity.func_70090_H()) {
/* 20 */       AbilityHelper.setDeltaMovement((Entity)this.entity, this.entity.func_213322_ci().func_72441_c(0.0D, 0.005D, 0.0D));
/*    */     }
/*    */     
/* 23 */     if (this.field_188491_h == MovementController.Action.MOVE_TO && !this.entity.func_70661_as().func_75500_f()) {
/* 24 */       double d0 = this.field_75646_b - this.entity.func_226277_ct_();
/* 25 */       double d1 = this.field_75647_c - this.entity.func_226278_cu_();
/* 26 */       double d2 = this.field_75644_d - this.entity.func_226281_cx_();
/* 27 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 28 */       if (d3 < 2.500000277905201E-7D) {
/* 29 */         this.field_75648_a.func_191989_p(0.0F);
/*    */       } else {
/*    */         
/* 32 */         float f = (float)(MathHelper.func_181159_b(d2, d0) * 57.2957763671875D) - 90.0F;
/* 33 */         ((MobEntity)this.entity).field_70177_z = func_75639_a(((MobEntity)this.entity).field_70177_z, f, 10.0F);
/* 34 */         ((MobEntity)this.entity).field_70761_aq = ((MobEntity)this.entity).field_70177_z;
/* 35 */         ((MobEntity)this.entity).field_70759_as = ((MobEntity)this.entity).field_70177_z;
/* 36 */         float f1 = (float)(this.field_75645_e * this.entity.func_233637_b_(Attributes.field_233821_d_));
/* 37 */         if (this.entity.func_70090_H()) {
/* 38 */           this.entity.func_70659_e(f1 * 0.02F);
/* 39 */           float f2 = -((float)(MathHelper.func_181159_b(d1, MathHelper.func_76133_a(d0 * d0 + d2 * d2)) * 57.2957763671875D));
/* 40 */           f2 = MathHelper.func_76131_a(MathHelper.func_76142_g(f2), -85.0F, 85.0F);
/* 41 */           ((MobEntity)this.entity).field_70125_A = func_75639_a(((MobEntity)this.entity).field_70125_A, f2, 5.0F);
/* 42 */           float f3 = MathHelper.func_76134_b(((MobEntity)this.entity).field_70125_A * 0.017453292F);
/* 43 */           float f4 = MathHelper.func_76126_a(((MobEntity)this.entity).field_70125_A * 0.017453292F);
/* 44 */           ((MobEntity)this.entity).field_191988_bg = f3 * f1;
/* 45 */           ((MobEntity)this.entity).field_70701_bs = -f4 * f1;
/*    */         } else {
/*    */           
/* 48 */           this.entity.func_70659_e(f1 * 0.1F);
/*    */         }
/*    */       
/*    */       } 
/*    */     } else {
/*    */       
/* 54 */       this.entity.func_70659_e(0.0F);
/* 55 */       this.entity.func_184646_p(0.0F);
/* 56 */       this.entity.func_70657_f(0.0F);
/* 57 */       this.entity.func_191989_p(0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ai\controllers\FishSwimMoveController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */