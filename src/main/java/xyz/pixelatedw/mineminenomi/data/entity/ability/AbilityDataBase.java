/*     */ package xyz.pixelatedw.mineminenomi.data.entity.ability;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.EquipAbilityEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CSetCombatBarPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class AbilityDataBase
/*     */   implements IAbilityData
/*     */ {
/*     */   private LivingEntity dataOwner;
/*  38 */   private Set<AbilityCoreUnlockWrapper<?>> unlockedAbilities = new LinkedHashSet<>();
/*  39 */   private Set<IAbility> passiveAbilities = new LinkedHashSet<>();
/*  40 */   private IAbility[] activeAbilities = new IAbility[80];
/*     */   
/*     */   private IAbility previouslyUsedAbility;
/*  43 */   private int currentCombatBarSet = 0;
/*  44 */   private int lastCombatBarSet = 0;
/*     */ 
/*     */   
/*     */   public void initDataOwner(LivingEntity owner) {
/*  48 */     this.dataOwner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addUnlockedAbility(AbilityCore core, AbilityUnlock unlockMethod) {
/*  57 */     if (core == null || hasUnlockedAbility(core)) {
/*  58 */       return false;
/*     */     }
/*     */     
/*  61 */     AbilityCoreUnlockWrapper unlockedCore = new AbilityCoreUnlockWrapper(this.dataOwner, core, unlockMethod);
/*  62 */     addUnlockedAbility(unlockedCore);
/*     */ 
/*     */     
/*  65 */     if (core.getType() == AbilityType.PASSIVE) {
/*  66 */       addPassiveAbility(core.createAbility());
/*     */     }
/*     */     
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addUnlockedAbility(AbilityCoreUnlockWrapper<?> unlockedCore) {
/*  74 */     AbilityCore core = unlockedCore.getAbilityCore();
/*  75 */     if (core == null || hasUnlockedAbility(core)) {
/*  76 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.unlockedAbilities.add(unlockedCore);
/*     */ 
/*     */     
/*  88 */     if (this.dataOwner != null && this.dataOwner instanceof ServerPlayerEntity) {
/*  89 */       ModAdvancements.UNLOCK_ABILITY.trigger((ServerPlayerEntity)this.dataOwner, core);
/*     */     }
/*     */     
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbilityUnlock getUnlockTypeForAbility(AbilityCore core) {
/*  97 */     return this.unlockedAbilities.stream().filter(pair -> pair.getAbilityCore().equals(core)).map(pair -> pair.getUnlockType()).findFirst().orElse(AbilityUnlock.NONE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeUnlockedAbility(AbilityCore core) {
/* 102 */     if (!hasUnlockedAbility(core)) {
/* 103 */       return false;
/*     */     }
/* 105 */     boolean updateClient = false;
/* 106 */     updateClient |= removeEquippedAbility(core);
/* 107 */     updateClient |= removePassiveAbility(core);
/*     */     
/* 109 */     if (updateClient);
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.unlockedAbilities.removeIf(pair -> pair.getAbilityCore().equals(core));
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasUnlockedAbility(AbilityCore core) {
/* 119 */     return getUnlockedAbilities().stream().anyMatch(wrapper -> wrapper.getAbilityCore().equals(core));
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<AbilityCoreUnlockWrapper> getUnlockedAbilities() {
/* 124 */     return (Set<AbilityCoreUnlockWrapper>)this.unlockedAbilities.stream().filter(Objects::nonNull).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<AbilityCoreUnlockWrapper> getUnlockedAbilities(Predicate<AbilityCore> check) {
/* 129 */     return (Set<AbilityCoreUnlockWrapper>)getUnlockedAbilities().stream().filter(pair -> check.test(pair.getAbilityCore())).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<AbilityCoreUnlockWrapper<?>> getUnlockedAbilitiesStream() {
/* 134 */     return this.unlockedAbilities.stream().filter(Objects::nonNull);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<AbilityCore> clearUnlockedAbilities() {
/* 139 */     Set<AbilityCore> removed = (Set<AbilityCore>)this.unlockedAbilities.stream().map(AbilityCoreUnlockWrapper::getAbilityCore).collect(Collectors.toSet());
/* 140 */     removed.forEach(core -> removeUnlockedAbility(core));
/* 141 */     this.unlockedAbilities.clear();
/* 142 */     return removed;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<AbilityCore> clearUnlockedAbilities(Predicate<AbilityCore> check) {
/* 147 */     Set<AbilityCoreUnlockWrapper> removed = (Set<AbilityCoreUnlockWrapper>)this.unlockedAbilities.stream().filter(pair -> check.test(pair.getAbilityCore())).collect(Collectors.toSet());
/* 148 */     removed.forEach(pair -> removeUnlockedAbility(pair.getAbilityCore()));
/* 149 */     this.unlockedAbilities.removeAll(removed);
/* 150 */     return (Set<AbilityCore>)removed.stream().map(AbilityCoreUnlockWrapper::getAbilityCore).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public int countUnlockedAbilities() {
/* 155 */     this.unlockedAbilities.removeIf(ability -> (ability == null));
/* 156 */     return this.unlockedAbilities.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int countUnlockedAbilities(Predicate<AbilityCore> check) {
/* 161 */     return getUnlockedAbilities(core -> check.test(core)).stream().mapToInt(a -> 1).sum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addPassiveAbility(IAbility abl) {
/* 170 */     if (hasPassiveAbility(abl)) {
/* 171 */       return false;
/*     */     }
/*     */     
/* 174 */     if (!hasUnlockedAbility(abl.getCore())) {
/* 175 */       return false;
/*     */     }
/*     */     
/* 178 */     abl.onEquip(this.dataOwner);
/*     */     
/* 180 */     this.passiveAbilities.add(abl);
/*     */     
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removePassiveAbility(IAbility abl) {
/* 187 */     return removePassiveAbility(abl.getCore());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removePassiveAbility(AbilityCore core) {
/* 192 */     return this.passiveAbilities.removeIf(abl -> {
/*     */           if (abl.getCore().equals(core)) {
/*     */             abl.onRemove(this.dataOwner);
/*     */             return true;
/*     */           } 
/*     */           return false;
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPassiveAbility(IAbility abl) {
/* 203 */     return hasPassiveAbility(abl.getCore());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPassiveAbility(AbilityCore core) {
/* 208 */     return getPassiveAbilities().stream().anyMatch(abl -> abl.getCore().equals(core));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends IAbility> T getPassiveAbility(AbilityCore<T> core) {
/* 214 */     return (T)getPassiveAbilities().stream().filter(otherAbl -> otherAbl.getCore().equals(core)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends IAbility> Set<T> getPassiveAbilities() {
/* 219 */     return (Set<T>)this.passiveAbilities.stream().filter(Objects::nonNull).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends IAbility> Set<T> getPassiveAbilities(Predicate<IAbility> check) {
/* 224 */     return (Set<T>)getPassiveAbilities().stream().filter(abl -> (check.test(abl) && check.test(abl))).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearPassiveAbilities() {
/* 229 */     for (IAbility ability : this.passiveAbilities) {
/* 230 */       ability.onRemove(this.dataOwner);
/*     */     }
/* 232 */     this.passiveAbilities.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearPassiveAbilities(Predicate<IAbility> predicate) {
/* 237 */     for (IAbility ability : this.passiveAbilities) {
/* 238 */       if (predicate.test(ability)) {
/* 239 */         ability.onRemove(this.dataOwner);
/*     */       }
/*     */     } 
/* 242 */     this.passiveAbilities.removeIf(abl -> predicate.test(abl));
/*     */   }
/*     */ 
/*     */   
/*     */   public int countPassiveAbilities() {
/* 247 */     return getPassiveAbilities().size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int countPassiveAbilities(Predicate<IAbility> check) {
/* 252 */     return getPassiveAbilities().stream().filter(abl -> check.test(abl)).mapToInt(a -> 1).sum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setEquippedAbility(int slot, @Nullable IAbility abl) {
/* 261 */     if (slot < 0) {
/* 262 */       return false;
/*     */     }
/*     */     
/* 265 */     if (abl != null) {
/* 266 */       if (hasEquippedAbility(abl)) {
/* 267 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 275 */       EquipAbilityEvent event = new EquipAbilityEvent(this.dataOwner, abl);
/*     */       
/* 277 */       if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/* 278 */         return false;
/*     */       }
/*     */       
/* 281 */       abl.onEquip(this.dataOwner);
/*     */     }
/* 283 */     else if (this.activeAbilities[slot] != null) {
/* 284 */       this.activeAbilities[slot].onRemove(this.dataOwner);
/*     */     } 
/*     */     
/* 287 */     this.activeAbilities[slot] = abl;
/*     */     
/* 289 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeEquippedAbility(IAbility abl) {
/* 294 */     if (abl == null) {
/* 295 */       return false;
/*     */     }
/* 297 */     return removeEquippedAbility(abl.getCore());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeEquippedAbility(AbilityCore core) {
/* 302 */     for (int i = 0; i < this.activeAbilities.length; i++) {
/* 303 */       IAbility ability = this.activeAbilities[i];
/* 304 */       if (ability != null && ability.getCore().equals(core)) {
/* 305 */         ability.onRemove(this.dataOwner);
/* 306 */         this.activeAbilities[i] = null;
/* 307 */         return true;
/*     */       } 
/*     */     } 
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEquippedAbility(IAbility abl) {
/* 315 */     if (abl == null) {
/* 316 */       return false;
/*     */     }
/* 318 */     return hasEquippedAbility(abl.getCore());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEquippedAbility(AbilityCore core) {
/* 323 */     return Arrays.<IAbility>stream(this.activeAbilities).filter(ability -> (ability != null)).anyMatch(ability -> ability.getCore().equals(core));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEquippedAbilitySlot(IAbility abl) {
/* 328 */     if (abl == null) {
/* 329 */       return -1;
/*     */     }
/* 331 */     return getEquippedAbilitySlot(abl.getCore());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEquippedAbilitySlot(AbilityCore core) {
/* 336 */     if (core == null) {
/* 337 */       return -1;
/*     */     }
/* 339 */     for (int i = 0; i < this.activeAbilities.length; i++) {
/* 340 */       IAbility ability = this.activeAbilities[i];
/* 341 */       if (ability != null && ability.getCore().equals(core)) {
/* 342 */         return i;
/*     */       }
/*     */     } 
/* 345 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends IAbility> T getEquippedAbility(T abl) {
/* 351 */     return (T)Arrays.<IAbility>stream(this.activeAbilities).filter(ability -> (ability != null && ability.equals(abl))).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends IAbility> T getEquippedAbility(AbilityCore<T> core) {
/* 357 */     return (T)Arrays.<IAbility>stream(this.activeAbilities).filter(ability -> (ability != null && ability.getCore().equals(core))).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends IAbility> T getEquippedAbility(int slot) {
/* 363 */     if (slot < 0) {
/* 364 */       return null;
/*     */     }
/* 366 */     if (this.activeAbilities.length < slot) {
/* 367 */       return null;
/*     */     }
/* 369 */     return (T)this.activeAbilities[slot];
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IAbility> getRawEquippedAbilities() {
/* 374 */     LinkedList<IAbility> list = Lists.newLinkedList();
/* 375 */     Collections.addAll(list, this.activeAbilities);
/* 376 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends IAbility> Set<T> getEquippedAbilities() {
/* 381 */     return (Set<T>)Arrays.<IAbility>stream(this.activeAbilities).filter(Objects::nonNull).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends IAbility> Set<T> getEquippedAbilities(Predicate<IAbility> check) {
/* 386 */     return (Set<T>)Arrays.<IAbility>stream(this.activeAbilities).filter(abl -> (abl != null && check.test(abl))).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends IAbility> Set<T> getEquippedAbilitiesWith(AbilityComponentKey... keys) {
/* 391 */     return (Set<T>)Arrays.<IAbility>stream(this.activeAbilities).filter(abl -> (abl != null && Arrays.<AbilityComponentKey>stream(keys).allMatch(())))
/* 392 */       .collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearEquippedAbilities() {
/* 397 */     for (int i = 0; i < this.activeAbilities.length; i++) {
/* 398 */       IAbility ability = this.activeAbilities[i];
/* 399 */       if (ability != null) {
/* 400 */         ability.onRemove(this.dataOwner);
/*     */       }
/* 402 */       this.activeAbilities[i] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearEquippedAbilities(Predicate<IAbility> predicate) {
/* 408 */     for (int i = 0; i < this.activeAbilities.length; i++) {
/* 409 */       IAbility ability = this.activeAbilities[i];
/* 410 */       if (ability != null && predicate.test(ability)) {
/* 411 */         ability.onRemove(this.dataOwner);
/* 412 */         this.activeAbilities[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int countEquippedAbilities() {
/* 419 */     return Arrays.<IAbility>stream(this.activeAbilities).filter(ability -> (ability != null)).mapToInt(a -> 1).sum();
/*     */   }
/*     */ 
/*     */   
/*     */   public int countEquippedAbilities(Predicate<IAbility> check) {
/* 424 */     return Arrays.<IAbility>stream(this.activeAbilities).filter(ability -> (ability != null && check.test(ability))).mapToInt(a -> 1).sum();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends IAbility> T getEquippedOrPassiveAbility(AbilityCore<T> core) {
/* 434 */     return (T)getEquippedAndPassiveAbilities().stream().filter(abl -> abl.getCore().equals(core)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IAbility> getEquippedAndPassiveAbilities() {
/* 439 */     Set<IAbility> set = new HashSet<>();
/* 440 */     set.addAll(getPassiveAbilities());
/* 441 */     set.addAll(getEquippedAbilities());
/* 442 */     return set;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IAbility> getEquippedAndPassiveAbilities(Predicate<IAbility> check) {
/* 447 */     Set<IAbility> set = new HashSet<>();
/* 448 */     set.addAll(getPassiveAbilities(check));
/* 449 */     set.addAll(getEquippedAbilities(check));
/* 450 */     return set;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends IAbility> T getPreviouslyUsedAbility() {
/* 459 */     return (T)this.previouslyUsedAbility;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreviouslyUsedAbility(IAbility abl) {
/* 464 */     this.previouslyUsedAbility = abl;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCombatBarSet() {
/* 469 */     return Math.max(0, this.currentCombatBarSet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void nextCombatBarSet(int amount) {
/* 474 */     this.lastCombatBarSet = this.currentCombatBarSet;
/* 475 */     this.currentCombatBarSet = MathHelper.func_76125_a(this.currentCombatBarSet + amount, 0, CommonConfig.INSTANCE.getAbilityBars() - 1);
/*     */     
/* 477 */     if (this.dataOwner != null && this.dataOwner.field_70170_p.field_72995_K) {
/* 478 */       WyNetwork.sendToServer(new CSetCombatBarPacket(getCombatBarSet()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void prevCombatBarSet(int amount) {
/* 484 */     this.lastCombatBarSet = this.currentCombatBarSet;
/* 485 */     this.currentCombatBarSet = MathHelper.func_76125_a(this.currentCombatBarSet - amount, 0, CommonConfig.INSTANCE.getAbilityBars() - 1);
/*     */     
/* 487 */     if (this.dataOwner != null && this.dataOwner.field_70170_p.field_72995_K) {
/* 488 */       WyNetwork.sendToServer(new CSetCombatBarPacket(getCombatBarSet()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastCombatBarSet() {
/* 494 */     return this.lastCombatBarSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCombatBarSet(int set) {
/* 499 */     this.lastCombatBarSet = this.currentCombatBarSet;
/* 500 */     this.currentCombatBarSet = set;
/*     */     
/* 502 */     if (this.dataOwner != null && this.dataOwner.field_70170_p.field_72995_K)
/* 503 */       WyNetwork.sendToServer(new CSetCombatBarPacket(getCombatBarSet())); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\ability\AbilityDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */