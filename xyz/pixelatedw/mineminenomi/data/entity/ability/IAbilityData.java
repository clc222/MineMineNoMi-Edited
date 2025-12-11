package xyz.pixelatedw.mineminenomi.data.entity.ability;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;

public interface IAbilityData {
  boolean addUnlockedAbility(AbilityCore paramAbilityCore, AbilityUnlock paramAbilityUnlock);
  
  boolean addUnlockedAbility(AbilityCoreUnlockWrapper paramAbilityCoreUnlockWrapper);
  
  boolean removeUnlockedAbility(AbilityCore paramAbilityCore);
  
  boolean hasUnlockedAbility(AbilityCore paramAbilityCore);
  
  AbilityUnlock getUnlockTypeForAbility(AbilityCore paramAbilityCore);
  
  Set<AbilityCoreUnlockWrapper> getUnlockedAbilities();
  
  Set<AbilityCoreUnlockWrapper> getUnlockedAbilities(Predicate<AbilityCore> paramPredicate);
  
  <A extends IAbility> Stream<AbilityCoreUnlockWrapper<A>> getUnlockedAbilitiesStream();
  
  Set<AbilityCore> clearUnlockedAbilities();
  
  Set<AbilityCore> clearUnlockedAbilities(Predicate<AbilityCore> paramPredicate);
  
  int countUnlockedAbilities();
  
  int countUnlockedAbilities(Predicate<AbilityCore> paramPredicate);
  
  boolean addPassiveAbility(IAbility paramIAbility);
  
  boolean removePassiveAbility(IAbility paramIAbility);
  
  boolean removePassiveAbility(AbilityCore paramAbilityCore);
  
  boolean hasPassiveAbility(IAbility paramIAbility);
  
  boolean hasPassiveAbility(AbilityCore paramAbilityCore);
  
  @Nullable
  <T extends IAbility> T getPassiveAbility(AbilityCore<T> paramAbilityCore);
  
  <T extends IAbility> Set<T> getPassiveAbilities();
  
  <T extends IAbility> Set<T> getPassiveAbilities(Predicate<IAbility> paramPredicate);
  
  void clearPassiveAbilities();
  
  void clearPassiveAbilities(Predicate<IAbility> paramPredicate);
  
  int countPassiveAbilities();
  
  int countPassiveAbilities(Predicate<IAbility> paramPredicate);
  
  boolean setEquippedAbility(int paramInt, @Nullable IAbility paramIAbility);
  
  boolean removeEquippedAbility(IAbility paramIAbility);
  
  boolean removeEquippedAbility(AbilityCore paramAbilityCore);
  
  boolean hasEquippedAbility(IAbility paramIAbility);
  
  boolean hasEquippedAbility(AbilityCore paramAbilityCore);
  
  int getEquippedAbilitySlot(IAbility paramIAbility);
  
  int getEquippedAbilitySlot(AbilityCore paramAbilityCore);
  
  @Nullable
  <T extends IAbility> T getEquippedAbility(T paramT);
  
  @Nullable
  <T extends IAbility> T getEquippedAbility(AbilityCore<T> paramAbilityCore);
  
  @Nullable
  <T extends IAbility> T getEquippedAbility(int paramInt);
  
  <T extends IAbility> List<T> getRawEquippedAbilities();
  
  <T extends IAbility> Set<T> getEquippedAbilities();
  
  <T extends IAbility> Set<T> getEquippedAbilities(Predicate<IAbility> paramPredicate);
  
  <T extends IAbility> Set<T> getEquippedAbilitiesWith(AbilityComponentKey... paramVarArgs);
  
  void clearEquippedAbilities();
  
  void clearEquippedAbilities(Predicate<IAbility> paramPredicate);
  
  int countEquippedAbilities();
  
  int countEquippedAbilities(Predicate<IAbility> paramPredicate);
  
  @Nullable
  <T extends IAbility> T getEquippedOrPassiveAbility(AbilityCore<T> paramAbilityCore);
  
  Set<IAbility> getEquippedAndPassiveAbilities();
  
  Set<IAbility> getEquippedAndPassiveAbilities(Predicate<IAbility> paramPredicate);
  
  <T extends IAbility> T getPreviouslyUsedAbility();
  
  void setPreviouslyUsedAbility(IAbility paramIAbility);
  
  int getCombatBarSet();
  
  void nextCombatBarSet(int paramInt);
  
  void prevCombatBarSet(int paramInt);
  
  void setCombatBarSet(int paramInt);
  
  int getLastCombatBarSet();
  
  void initDataOwner(LivingEntity paramLivingEntity);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\ability\IAbilityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */