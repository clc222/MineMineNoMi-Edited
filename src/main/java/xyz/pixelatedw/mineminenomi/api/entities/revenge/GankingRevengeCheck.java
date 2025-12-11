/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ 
/*    */ public class GankingRevengeCheck
/*    */   implements IRevengeCheck
/*    */ {
/*    */   private final int revengeGain;
/*    */   private final float gankRadius;
/*    */   
/*    */   public GankingRevengeCheck(int revengeGain, float gankRadius) {
/* 14 */     this.revengeGain = revengeGain;
/* 15 */     this.gankRadius = gankRadius;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/* 20 */     List<LivingEntity> list = TargetHelper.getEntitiesInArea(entity, this.gankRadius, null, new Class[] { LivingEntity.class });
/*    */     
/* 22 */     if (list.size() > 1) {
/* 23 */       return true;
/*    */     }
/*    */     
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetMarkers() {}
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 35 */     return this.revengeGain;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\GankingRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */