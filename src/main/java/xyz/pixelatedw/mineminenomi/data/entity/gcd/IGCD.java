package xyz.pixelatedw.mineminenomi.data.entity.gcd;

import net.minecraft.entity.LivingEntity;

public interface IGCD {
  IGCD setOwner(LivingEntity paramLivingEntity);
  
  void startGCD();
  
  boolean isOnGCD();
  
  void tickGCD();
  
  int getCurrentGCD();
  
  int getDefaultGCD();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\gcd\IGCD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */