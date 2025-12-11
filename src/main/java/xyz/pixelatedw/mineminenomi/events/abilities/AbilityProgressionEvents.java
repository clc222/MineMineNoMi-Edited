/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ 
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.UnlockAbilityEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.stats.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.stats.HakiExpEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.armors.CaptainCapeItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class AbilityProgressionEvents {
/*     */   @SubscribeEvent
/*     */   public static void onHakiExpGained(HakiExpEvent.Post event) {
/*  45 */     checkForHakiUnlocks(event.getPlayer());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDorikiGained(DorikiEvent.Post event) {
/*  50 */     checkForRacialUnlocks(event.getPlayer());
/*  51 */     checkForHakiUnlocks(event.getPlayer());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsSet(SetPlayerDetailsEvent event) {
/*  56 */     checkForStyleUnlocks(event.getPlayer());
/*  57 */     checkForRacialUnlocks(event.getPlayer());
/*  58 */     checkForHakiUnlocks(event.getPlayer());
/*  59 */     AbilityValidationEvents.validateUnlockedAbilities((LivingEntity)event.getPlayer());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEquipmentUpdate(LivingEquipmentChangeEvent event) {
/*  64 */     updateEquipmentProgression(event.getEntityLiving(), event.getTo());
/*     */   }
/*     */   
/*     */   public static void updateEquipmentProgression(LivingEntity entity, ItemStack stack) {
/*  68 */     if (entity instanceof PlayerEntity) {
/*  69 */       checkForEquipmentUnlocks(entity);
/*     */       
/*  71 */       if (stack.func_77973_b().equals(ModArmors.PIRATE_CAPTAIN_CAPE.get())) {
/*  72 */         Crew crew = ExtendedWorldData.get().getCrewWithMember(entity.func_110124_au());
/*  73 */         CaptainCapeItem.setCapeCrew(stack, crew);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onFruitAbilityUnlocked(UnlockAbilityEvent event) {
/*  85 */     if (event.getAbilityCore().getCategory() == AbilityCategory.DEVIL_FRUITS) {
/*  86 */       IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getEntityLiving());
/*     */       
/*  88 */       if (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(event.getEntityLiving())) {
/*  89 */         event.setResult(Event.Result.DENY);
/*     */         
/*     */         return;
/*     */       } 
/*  93 */       ItemStack df = new ItemStack((IItemProvider)devilFruitProps.getDevilFruitItem());
/*     */       
/*  95 */       if (!df.func_190926_b()) {
/*  96 */         if (devilFruitProps.hasYamiPower()) {
/*  97 */           ItemStack yami = new ItemStack((IItemProvider)ModAbilities.YAMI_YAMI_NO_MI);
/*  98 */           for (AbilityCore<?> core : ((AkumaNoMiItem)yami.func_77973_b()).getAbilities()) {
/*  99 */             if (event.getAbilityCore().equals(core)) {
/* 100 */               event.setResult(Event.Result.ALLOW);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 105 */         for (AbilityCore<?> core : ((AkumaNoMiItem)df.func_77973_b()).getAbilities()) {
/* 106 */           if (event.getAbilityCore().equals(core)) {
/* 107 */             boolean canUnlock = core.hasUnlockCheck() ? core.canUnlock(event.getEntityLiving()) : true;
/* 108 */             if (canUnlock) {
/* 109 */               event.setResult(Event.Result.ALLOW);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 115 */         event.setResult(Event.Result.DENY);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean checkCategoryForNewUnlocks(LivingEntity entity, AbilityCategory category) {
/* 121 */     long updates = ((Long)ModValues.abilityCategoryMap.get(category).stream().map(core -> Boolean.valueOf(AbilityHelper.checkAndUnlockAbility(entity, core))).filter(b -> b.booleanValue()).collect(Collectors.counting())).longValue();
/* 122 */     return (updates != 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkAllForNewUnlocks(PlayerEntity player) {
/* 131 */     long updates = ((Long)ModRegistries.ABILITIES.getValues().stream().map(core -> Boolean.valueOf(AbilityHelper.checkAndUnlockAbility((LivingEntity)player, core))).filter(b -> b.booleanValue()).collect(Collectors.counting())).longValue();
/*     */     
/* 133 */     if (updates != 0L) {
/* 134 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkForDevilFruitUnlocks(PlayerEntity player) {
/* 139 */     if (checkCategoryForNewUnlocks((LivingEntity)player, AbilityCategory.DEVIL_FRUITS)) {
/* 140 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkForEquipmentUnlocks(LivingEntity entity) {
/* 145 */     if (checkCategoryForNewUnlocks(entity, AbilityCategory.EQUIPMENT) && 
/* 146 */       entity instanceof ServerPlayerEntity) {
/* 147 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(entity.func_145782_y(), AbilityDataCapability.get(entity)), (PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void checkForFactionUnlocks(PlayerEntity player) {
/* 153 */     if (checkCategoryForNewUnlocks((LivingEntity)player, AbilityCategory.FACTION)) {
/* 154 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkForStyleUnlocks(PlayerEntity player) {
/* 159 */     if (checkCategoryForNewUnlocks((LivingEntity)player, AbilityCategory.STYLE)) {
/* 160 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkForRacialUnlocks(PlayerEntity player) {
/* 165 */     if (checkCategoryForNewUnlocks((LivingEntity)player, AbilityCategory.RACIAL)) {
/* 166 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void checkForHakiUnlocks(PlayerEntity player) {
/* 171 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/* 172 */     boolean updates = checkCategoryForNewUnlocks((LivingEntity)player, AbilityCategory.HAKI);
/*     */     
/* 174 */     if (updates) {
/* 175 */       WyNetwork.sendTo(new SSyncHakiDataPacket(player.func_145782_y(), hakiProps), player);
/* 176 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), AbilityDataCapability.get((LivingEntity)player)), player);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void forceAdvancementChecks(ServerPlayerEntity player) {
/* 181 */     for (AbilityCoreUnlockWrapper unlockWrapper : AbilityDataCapability.get((LivingEntity)player).getUnlockedAbilities())
/* 182 */       ModAdvancements.UNLOCK_ABILITY.trigger(player, unlockWrapper.getAbilityCore()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityProgressionEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */