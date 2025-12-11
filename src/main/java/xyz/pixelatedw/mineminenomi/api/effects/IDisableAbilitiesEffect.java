package xyz.pixelatedw.mineminenomi.api.effects;

import java.util.function.Predicate;
import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;

public interface IDisableAbilitiesEffect {
  Predicate<IAbility> getDisabledAbilities();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\IDisableAbilitiesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */