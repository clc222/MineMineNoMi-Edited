/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ 
/*    */ public class TargetFlyStallingRevengeCheck implements IRevengeCheck {
/*    */   private final int revengeGain;
/*    */   
/*    */   public TargetFlyStallingRevengeCheck(int revengeGain) {
/* 13 */     this.revengeGain = revengeGain;
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
/* 26 */       if (target instanceof PlayerEntity && ((PlayerEntity)target).field_71075_bZ.field_75100_b) {
/* 27 */         return true;
/*    */       }
/*    */       
/* 30 */       if (DevilFruitHelper.getDifferenceToFloor((Entity)target) > 10.0D) {
/* 31 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetMarkers() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 45 */     return this.revengeGain;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\TargetFlyStallingRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */