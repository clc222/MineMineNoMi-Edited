package xyz.pixelatedw.mineminenomi.data.entity.projectilesextra;

import net.minecraft.item.ItemStack;

public interface IProjectileExtras {
  void setProjectileBusoshokuImbued(boolean paramBoolean);
  
  boolean isProjectileBusoshokuImbued();
  
  void setProjectileBusoshokuShrouded(boolean paramBoolean);
  
  boolean isProjectileBusoshokuShrouded();
  
  void setProjectileHaoshokuInfused(boolean paramBoolean);
  
  boolean isProjectileHaoshokuInfused();
  
  void setPiercing(float paramFloat);
  
  float getPiercing();
  
  void setWeaponUsed(ItemStack paramItemStack);
  
  ItemStack getWeaponUsed();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\projectilesextra\IProjectileExtras.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */