package xyz.pixelatedw.mineminenomi.api.abilities;

import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

@Deprecated
public interface IHitAbility {
  boolean isActive(PlayerEntity paramPlayerEntity);
  
  boolean isStoppingAfterHit();
  
  <T extends xyz.pixelatedw.mineminenomi.init.ModDamageSource> T getDamageSource(PlayerEntity paramPlayerEntity, @Nullable T paramT);
  
  float hitEntity(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IHitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */