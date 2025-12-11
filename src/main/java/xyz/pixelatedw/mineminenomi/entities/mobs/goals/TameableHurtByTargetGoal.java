/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import net.minecraft.entity.passive.TameableEntity;
/*    */ 
/*    */ public class TameableHurtByTargetGoal extends HurtByTargetGoal {
/*    */   private TameableEntity entity;
/*    */   
/*    */   public TameableHurtByTargetGoal(TameableEntity entity, Class<?>... clzz) {
/* 11 */     super((CreatureEntity)entity, clzz);
/* 12 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 17 */     if (this.entity.func_70909_n() && this.entity.func_70902_q() != null) {
/* 18 */       if (this.entity.func_70638_az() != null && this.entity.func_70638_az().equals(this.entity.func_70902_q())) {
/* 19 */         return false;
/*    */       }
/* 21 */       if (this.field_188509_g != null && this.field_188509_g.equals(this.entity.func_70902_q())) {
/* 22 */         return false;
/*    */       }
/*    */     } 
/* 25 */     return super.func_75253_b();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\TameableHurtByTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */