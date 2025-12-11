package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;

@Deprecated
public interface IFallDamageBlockingAbility {
  void resetFallDamage(LivingEntity paramLivingEntity);
  
  boolean hasFallDamage();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IFallDamageBlockingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */