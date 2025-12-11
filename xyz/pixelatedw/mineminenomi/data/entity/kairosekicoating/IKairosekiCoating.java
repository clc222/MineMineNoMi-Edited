package xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating;

import net.minecraft.entity.Entity;

public interface IKairosekiCoating {
  void setOwner(Entity paramEntity);
  
  boolean isFullyCoated();
  
  int getCoatingLevel();
  
  boolean addCoatingLevel(int paramInt);
  
  void setCoatingLevel(int paramInt);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\kairosekicoating\IKairosekiCoating.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */