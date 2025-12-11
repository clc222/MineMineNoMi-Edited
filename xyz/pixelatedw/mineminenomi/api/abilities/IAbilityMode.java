package xyz.pixelatedw.mineminenomi.api.abilities;

@Deprecated
public interface IAbilityMode {
  AbilityCore[] getParents();
  
  void enableMode(Ability paramAbility);
  
  void disableMode(Ability paramAbility);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IAbilityMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */