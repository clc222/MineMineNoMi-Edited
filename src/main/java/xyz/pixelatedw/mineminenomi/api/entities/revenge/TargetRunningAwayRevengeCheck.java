/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ 
/*    */ public class TargetRunningAwayRevengeCheck implements IRevengeCheck {
/*    */   private final int revengeGain;
/*    */   private final float distance;
/*    */   
/*    */   public TargetRunningAwayRevengeCheck(int revengeGain, float distance) {
/* 12 */     this.revengeGain = revengeGain;
/* 13 */     this.distance = distance;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/* 18 */     if (entity instanceof MobEntity) {
/* 19 */       MobEntity mob = (MobEntity)entity;
/* 20 */       LivingEntity target = mob.func_70638_az();
/*    */       
/* 22 */       if (target == null || !target.func_70089_S()) {
/* 23 */         return false;
/*    */       }
/*    */       
/* 26 */       if (entity.func_70032_d((Entity)target) > this.distance * this.distance) {
/* 27 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetMarkers() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 41 */     return this.revengeGain;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\TargetRunningAwayRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */