/*    */ package xyz.pixelatedw.mineminenomi.data.entity.projectilesextra;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ProjectileExtrasBase
/*    */   implements IProjectileExtras
/*    */ {
/*    */   private boolean isBusoshokuImbued;
/*    */   private boolean isBusoshokuShrouded;
/*    */   private boolean isHaoshokuInfused;
/*    */   private float piercing;
/*    */   private ItemStack weaponUsed;
/*    */   
/*    */   public void setProjectileBusoshokuImbued(boolean flag) {
/* 16 */     this.isBusoshokuImbued = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProjectileBusoshokuImbued() {
/* 21 */     return this.isBusoshokuImbued;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProjectileBusoshokuShrouded(boolean flag) {
/* 26 */     this.isBusoshokuShrouded = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProjectileBusoshokuShrouded() {
/* 31 */     return this.isBusoshokuShrouded;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProjectileHaoshokuInfused(boolean flag) {
/* 36 */     this.isHaoshokuInfused = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isProjectileHaoshokuInfused() {
/* 41 */     return this.isHaoshokuInfused;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPiercing(float piercing) {
/* 46 */     this.piercing = piercing;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPiercing() {
/* 51 */     return this.piercing;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWeaponUsed(ItemStack stack) {
/* 56 */     this.weaponUsed = stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getWeaponUsed() {
/* 61 */     return this.weaponUsed;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\projectilesextra\ProjectileExtrasBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */