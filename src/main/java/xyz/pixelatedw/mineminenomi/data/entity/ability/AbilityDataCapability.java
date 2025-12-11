/*     */ package xyz.pixelatedw.mineminenomi.data.entity.ability;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Callable;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*     */ 
/*     */ 
/*     */ public class AbilityDataCapability
/*     */ {
/*     */   @CapabilityInject(IAbilityData.class)
/*  30 */   public static final Capability<IAbilityData> INSTANCE = null;
/*     */   
/*     */   public static void register() {
/*  33 */     CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
/*     */         {
/*     */           public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side) {
/*  36 */             CompoundNBT props = new CompoundNBT();
/*     */             try {
/*  38 */               ListNBT unlockedAbilities = new ListNBT();
/*  39 */               for (AbilityCoreUnlockWrapper unlockedCore : instance.getUnlockedAbilities()) {
/*  40 */                 unlockedAbilities.add(unlockedCore.save());
/*     */               }
/*  42 */               props.func_218657_a("unlocked_abilities", (INBT)unlockedAbilities);
/*     */               
/*  44 */               ListNBT passiveAbilities = new ListNBT();
/*  45 */               for (IAbility abl : instance.<IAbility>getPassiveAbilities()) {
/*  46 */                 CompoundNBT nbt = new CompoundNBT();
/*  47 */                 nbt.func_74778_a("id", abl.getCore().getRegistryName().toString());
/*  48 */                 nbt = abl.save(nbt);
/*     */                 
/*  50 */                 CompoundNBT components = new CompoundNBT();
/*  51 */                 for (Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>> entry : (Iterable<Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>>>)abl.getComponents().entrySet()) {
/*  52 */                   CompoundNBT compNbt = ((AbilityComponent)entry.getValue()).save();
/*  53 */                   if (compNbt != null) {
/*  54 */                     components.func_218657_a(((AbilityComponentKey)entry.getKey()).getId().toString(), (INBT)compNbt);
/*     */                   }
/*     */                 } 
/*  57 */                 nbt.func_218657_a("components", (INBT)components);
/*     */                 
/*  59 */                 passiveAbilities.add(nbt);
/*     */               } 
/*  61 */               props.func_218657_a("passive_abilities", (INBT)passiveAbilities);
/*     */               
/*  63 */               ListNBT equippedAbilities = new ListNBT();
/*  64 */               int slotId = 0;
/*  65 */               for (IAbility abl : instance.<IAbility>getRawEquippedAbilities()) {
/*  66 */                 if (abl == null) {
/*  67 */                   CompoundNBT nbt = new CompoundNBT();
/*  68 */                   nbt.func_74768_a("slot", slotId);
/*  69 */                   equippedAbilities.add(nbt);
/*     */                 } else {
/*     */                   
/*  72 */                   CompoundNBT nbt = new CompoundNBT();
/*  73 */                   nbt.func_74768_a("slot", slotId);
/*  74 */                   nbt.func_74778_a("id", abl.getCore().getRegistryName().toString());
/*  75 */                   nbt = abl.save(nbt);
/*     */                   
/*  77 */                   CompoundNBT components = new CompoundNBT();
/*  78 */                   for (Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>> entry : (Iterable<Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>>>)abl.getComponents().entrySet()) {
/*  79 */                     CompoundNBT compNbt = ((AbilityComponent)entry.getValue()).save();
/*  80 */                     if (compNbt != null) {
/*  81 */                       components.func_218657_a(((AbilityComponentKey)entry.getKey()).getId().toString(), (INBT)compNbt);
/*     */                     }
/*     */                   } 
/*  84 */                   nbt.func_218657_a("components", (INBT)components);
/*     */                   
/*  86 */                   equippedAbilities.add(nbt);
/*     */                 } 
/*  88 */                 slotId++;
/*     */               } 
/*  90 */               props.func_218657_a("equipped_abilities", (INBT)equippedAbilities);
/*     */               
/*  92 */               props.func_74768_a("combat_bar_set", instance.getCombatBarSet());
/*     */             }
/*  94 */             catch (Exception ex) {
/*  95 */               ex.printStackTrace();
/*     */             } 
/*     */             
/*  98 */             return (INBT)props;
/*     */           }
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbtData) {
/* 103 */             CompoundNBT props = (CompoundNBT)nbtData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 110 */               Set<AbilityCore<?>> updatedUnlockSet = new HashSet<>();
/* 111 */               ListNBT unlockedAbilities = props.func_150295_c("unlocked_abilities", 10);
/* 112 */               for (int i = 0; i < unlockedAbilities.size(); i++) {
/* 113 */                 CompoundNBT nbt = unlockedAbilities.func_150305_b(i);
/* 114 */                 AbilityCoreUnlockWrapper wrapper = AbilityCoreUnlockWrapper.of(nbt);
/* 115 */                 instance.addUnlockedAbility(wrapper);
/* 116 */                 updatedUnlockSet.add(wrapper.getAbilityCore());
/*     */               } 
/* 118 */               instance.clearUnlockedAbilities(core -> !updatedUnlockSet.contains(core));
/*     */               
/* 120 */               Set<AbilityCore<?>> updatedPassiveSet = new HashSet<>();
/* 121 */               ListNBT passiveAbilities = props.func_150295_c("passive_abilities", 10);
/* 122 */               for (int j = 0; j < passiveAbilities.size(); j++) {
/* 123 */                 CompoundNBT nbt = passiveAbilities.func_150305_b(j);
/* 124 */                 String coreId = nbt.func_74779_i("id");
/* 125 */                 AbilityCore core = (AbilityCore)ModRegistries.ABILITIES.getValue(new ResourceLocation(coreId));
/* 126 */                 if (core != null) {
/*     */ 
/*     */                   
/* 129 */                   IAbility ability = core.createAbility();
/* 130 */                   if (ability != null)
/*     */                   
/*     */                   { 
/* 133 */                     ability.load(nbt);
/*     */                     
/* 135 */                     CompoundNBT components = nbt.func_74775_l("components");
/* 136 */                     for (Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>> entry : (Iterable<Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>>>)ability.getComponents().entrySet()) {
/* 137 */                       CompoundNBT compNbt = components.func_74775_l(((AbilityComponentKey)entry.getKey()).getId().toString());
/* 138 */                       if (compNbt != null) {
/* 139 */                         ((AbilityComponent)entry.getValue()).load(compNbt);
/*     */                       }
/*     */                     } 
/*     */                     
/* 143 */                     instance.addPassiveAbility(ability);
/* 144 */                     updatedPassiveSet.add(ability.getCore()); } 
/*     */                 } 
/* 146 */               }  instance.clearPassiveAbilities(abl -> !updatedPassiveSet.contains(abl.getCore()));
/*     */               
/* 148 */               Set<AbilityCore<?>> updatedEquipSet = new HashSet<>();
/* 149 */               ListNBT equippedAbilities = props.func_150295_c("equipped_abilities", 10);
/* 150 */               for (int k = 0; k < equippedAbilities.size(); k++) {
/* 151 */                 CompoundNBT nbt = equippedAbilities.func_150305_b(k);
/* 152 */                 int slotId = nbt.func_74762_e("slot");
/*     */ 
/*     */                 
/* 155 */                 if (nbt.func_74764_b("id")) {
/* 156 */                   String coreId = nbt.func_74779_i("id");
/* 157 */                   AbilityCore core = (AbilityCore)ModRegistries.ABILITIES.getValue(new ResourceLocation(coreId));
/* 158 */                   if (core != null) {
/*     */ 
/*     */                     
/* 161 */                     IAbility ability = core.createAbility();
/* 162 */                     ability.load(nbt);
/*     */                     
/* 164 */                     CompoundNBT components = nbt.func_74775_l("components");
/* 165 */                     for (Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>> entry : (Iterable<Map.Entry<AbilityComponentKey<?>, AbilityComponent<?>>>)ability.getComponents().entrySet()) {
/* 166 */                       CompoundNBT compNbt = components.func_74775_l(((AbilityComponentKey)entry.getKey()).getId().toString());
/* 167 */                       if (compNbt != null) {
/* 168 */                         ((AbilityComponent)entry.getValue()).load(compNbt);
/*     */                       }
/*     */                     } 
/*     */                     
/* 172 */                     instance.setEquippedAbility(slotId, ability);
/* 173 */                     updatedEquipSet.add(ability.getCore());
/*     */                   } 
/*     */                 } else {
/* 176 */                   instance.setEquippedAbility(slotId, null);
/*     */                 } 
/*     */               } 
/* 179 */               instance.clearEquippedAbilities(abl -> (abl != null && !updatedEquipSet.contains(abl.getCore())));
/*     */               
/* 181 */               instance.setCombatBarSet(props.func_74762_e("combat_bar_set"));
/*     */             }
/* 183 */             catch (Exception ex) {
/* 184 */               ex.printStackTrace();
/*     */             } 
/*     */           }
/*     */         }AbilityDataBase::new);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static IAbilityData get(@Nonnull LivingEntity entity) {
/* 193 */     return (IAbilityData)getLazy(entity).orElse(new AbilityDataBase());
/*     */   }
/*     */   
/*     */   public static LazyOptional<IAbilityData> getLazy(@Nonnull LivingEntity entity) {
/* 197 */     LazyOptional<IAbilityData> lazyGCD = entity.getCapability(INSTANCE, null);
/* 198 */     lazyGCD.ifPresent(data -> data.initDataOwner(entity));
/* 199 */     return lazyGCD;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\ability\AbilityDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */