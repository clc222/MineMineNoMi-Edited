/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public interface IHitTrackerAbility
/*    */ {
/*    */   default void clearHits() {
/* 18 */     getHits().clear();
/*    */   }
/*    */   
/*    */   default boolean canHit(LivingEntity target) {
/* 22 */     if (getHits().contains(target.func_110124_au())) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     getHits().add(target.func_110124_au());
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   HashSet<UUID> getHits();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IHitTrackerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */