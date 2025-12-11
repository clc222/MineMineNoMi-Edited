package xyz.pixelatedw.mineminenomi.api.entities.revenge;

import net.minecraft.entity.LivingEntity;

public interface IRevengeCheck {
  int revengeMeterGain();
  
  boolean check(LivingEntity paramLivingEntity);
  
  void resetMarkers();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\IRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */