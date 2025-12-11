/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ public interface ITamableEntity
/*    */ {
/*    */   LivingEntity getOwner();
/*    */   
/*    */   default LivingEntity getOwnerIfAlive() {
/* 10 */     LivingEntity owner = getOwner();
/* 11 */     return (owner != null && owner.func_70089_S()) ? owner : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\ITamableEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */