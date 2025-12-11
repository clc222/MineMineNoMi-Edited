package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;

@Deprecated
public interface IAbilityModeSwitcher {
  default void enableModes(PlayerEntity player, Ability switcher) {}
  
  default void disableModes(PlayerEntity player, Ability switcher) {}
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IAbilityModeSwitcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */