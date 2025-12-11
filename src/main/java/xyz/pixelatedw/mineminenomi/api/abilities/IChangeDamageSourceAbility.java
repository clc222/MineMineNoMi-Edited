package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

@Deprecated
public interface IChangeDamageSourceAbility {
  float damageToEntityWithSource(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity);
  
  DamageSource getSourceToUse(PlayerEntity paramPlayerEntity);
  
  boolean cancelsOriginalDamage();
  
  boolean isSourceChangeEnabled();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IChangeDamageSourceAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */