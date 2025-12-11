/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.controller.MovementController;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class HumanoidSwimMoveController extends MovementController {
/*    */   private final MobEntity entity;
/*    */   
/*    */   public HumanoidSwimMoveController(MobEntity entity) {
/* 14 */     this(entity, Style.AGGRESSIVE);
/*    */   }
/*    */   private final Style style;
/*    */   public HumanoidSwimMoveController(MobEntity entity, Style style) {
/* 18 */     super(entity);
/* 19 */     this.entity = entity;
/* 20 */     this.style = style;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75641_c() {
/* 25 */     AbilityHelper.setDeltaMovement((Entity)this.entity, this.entity.func_213322_ci().func_72441_c(0.0D, 0.005D, 0.0D));
/*    */     
/* 27 */     if (this.entity.func_70638_az() != null) {
/* 28 */       if (this.entity.func_70638_az().func_226278_cu_() - this.entity.func_226278_cu_() > 2.0D) {
/* 29 */         AbilityHelper.setDeltaMovement((Entity)this.entity, this.entity.func_213322_ci().func_72441_c(0.0D, 0.05D, 0.0D));
/*    */       }
/* 31 */       else if (this.entity.func_226278_cu_() - this.entity.func_70638_az().func_226278_cu_() > 2.0D) {
/* 32 */         AbilityHelper.setDeltaMovement((Entity)this.entity, this.entity.func_213322_ci().func_72441_c(0.0D, -0.05D, 0.0D));
/*    */       } 
/*    */     }
/*    */     
/* 36 */     if (this.field_188491_h == MovementController.Action.MOVE_TO && !this.entity.func_70661_as().func_75500_f()) {
/* 37 */       double d0 = this.field_75646_b - this.entity.func_226277_ct_();
/* 38 */       double d1 = this.field_75647_c - this.entity.func_226278_cu_();
/* 39 */       double d2 = this.field_75644_d - this.entity.func_226281_cx_();
/* 40 */       double distanceSqr = d0 * d0 + d1 * d1 + d2 * d2;
/*    */       
/* 42 */       if (this.style == Style.AGGRESSIVE) {
/* 43 */         if (distanceSqr < 2.5D) {
/* 44 */           this.field_75648_a.func_191989_p(0.0F);
/*    */         } else {
/*    */           
/* 47 */           GoalUtil.lookAtEntity(this.entity, (Entity)this.entity.func_70638_az());
/* 48 */           float speed = (float)(this.field_75645_e * this.entity.func_233637_b_((Attribute)ForgeMod.SWIM_SPEED.get()));
/* 49 */           this.entity.func_70659_e(speed);
/*    */         }
/*    */       
/* 52 */       } else if (this.style == Style.STALLING) {
/*    */ 
/*    */       
/*    */       } 
/*    */     } else {
/* 57 */       this.entity.func_70659_e(0.0F);
/* 58 */       this.entity.func_184646_p(0.0F);
/* 59 */       this.entity.func_70657_f(0.0F);
/* 60 */       this.entity.func_191989_p(0.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public enum Style {
/* 65 */     AGGRESSIVE,
/* 66 */     STALLING;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ai\controllers\HumanoidSwimMoveController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */