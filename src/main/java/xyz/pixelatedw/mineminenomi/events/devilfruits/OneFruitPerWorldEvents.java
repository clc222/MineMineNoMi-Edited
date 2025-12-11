/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ 
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.inventory.ContainerScreen;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.PlayerInventory;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.container.PlayerContainer;
/*     */ import net.minecraft.inventory.container.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerContainerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.DroppedDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.InventoryDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class OneFruitPerWorldEvents
/*     */ {
/*  75 */   private static final Pair<ResourceLocation, ResourceLocation> OFFHAND_TEXTURE = Pair.of(PlayerContainer.field_226615_c_, PlayerContainer.field_226620_h_);
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onRightClickEntity(PlayerInteractEvent.EntityInteractSpecific event) {
/*  79 */     if (!(event.getWorld()).field_72995_K && event.getTarget() instanceof net.minecraft.entity.item.ArmorStandEntity && event.getItemStack().func_77973_b() instanceof AkumaNoMiItem && CommonConfig.INSTANCE
/*  80 */       .hasOneFruitPerWorldExtendedLogic()) {
/*  81 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onBreak(BlockEvent.BreakEvent event) {
/*  87 */     boolean hasShears = (event.getPlayer().func_184586_b(event.getPlayer().func_184600_cs()).func_77973_b() == Items.field_151097_aZ);
/*     */     
/*  89 */     if (CommonConfig.INSTANCE.getDevilFruitDropsFromLeavesChance() > 0.0D && event.getState().func_177230_c() instanceof net.minecraft.block.LeavesBlock && !hasShears) {
/*  90 */       double chance = event.getWorld().func_201674_k().nextDouble() * 100.0D;
/*     */       
/*  92 */       if (chance < CommonConfig.INSTANCE.getDevilFruitDropsFromLeavesChance()) {
/*  93 */         AkumaNoMiItem df = ModValues.DEVIL_FRUITS.get((int)WyHelper.randomWithRange(0, ModValues.DEVIL_FRUITS.size() - 1));
/*     */         
/*  95 */         df = DevilFruitHelper.findAvailableOneFruit((World)event.getWorld(), (Item)df);
/*     */         
/*  97 */         if (df != null) {
/*  98 */           boolean flag = (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() || OFPWWorldData.get().updateOneFruit(df.getRegistryName(), null, OneFruitEntry.Status.DROPPED, "Dropped from leaves. Sheared by " + event.getPlayer().func_145748_c_()));
/*  99 */           if (flag) {
/* 100 */             ItemStack stack = df.func_190903_i();
/* 101 */             ItemEntity item = new ItemEntity((World)event.getWorld(), event.getPos().func_177958_n(), event.getPos().func_177956_o(), event.getPos().func_177952_p(), stack);
/* 102 */             event.getWorld().func_217376_c((Entity)item);
/*     */           } 
/*     */           
/* 105 */           DroppedDevilFruitEvent postEvent = new DroppedDevilFruitEvent((LivingEntity)event.getPlayer(), (Item)df, "Dropped from leaves. Sheared by " + event.getPlayer().func_145748_c_());
/* 106 */           MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void mouseClickEvent(GuiScreenEvent.MouseClickedEvent.Pre event) {
/* 115 */     if ((Minecraft.func_71410_x()).field_71439_g == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */       return;
/*     */     }
/*     */     
/* 119 */     if (event.getGui() instanceof ContainerScreen) {
/* 120 */       Slot slot = ((ContainerScreen)event.getGui()).getSlotUnderMouse();
/* 121 */       if (slot != null && slot.func_225517_c_() != null && slot.func_225517_c_().equals(OFFHAND_TEXTURE)) {
/* 122 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 127 */     ItemStack cap = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.func_70445_o();
/* 128 */     if (!(cap.func_77973_b() instanceof AkumaNoMiItem) || event.getGui() instanceof net.minecraft.client.gui.screen.inventory.CreativeScreen) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 133 */     if (!(event.getGui() instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen)) {
/* 134 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void mouseReleaseEvent(GuiScreenEvent.MouseReleasedEvent.Pre event) {
/* 142 */     if ((Minecraft.func_71410_x()).field_71439_g == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */       return;
/*     */     }
/*     */     
/* 146 */     if (event.getGui() instanceof ContainerScreen) {
/*     */       
/* 148 */       Slot slot = ((ContainerScreen)event.getGui()).getSlotUnderMouse();
/* 149 */       if (slot != null && slot.func_225517_c_() != null && slot.func_225517_c_().equals(OFFHAND_TEXTURE)) {
/*     */         
/* 151 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 156 */     ItemStack cap = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.func_70445_o();
/* 157 */     if (!(cap.func_77973_b() instanceof AkumaNoMiItem) || event.getGui() instanceof net.minecraft.client.gui.screen.inventory.CreativeScreen) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 162 */     if (!(event.getGui() instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen)) {
/* 163 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onContainerOpen(PlayerContainerEvent.Open event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onContainerClose(PlayerContainerEvent.Close event) {
/* 191 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && !(event.getContainer() instanceof PlayerContainer)) {
/*     */       
/* 193 */       int containerSlots = (event.getContainer()).field_75151_b.size() - (event.getPlayer()).field_71071_by.field_70462_a.size();
/* 194 */       for (int i = 0; i < containerSlots; i++) {
/*     */         
/* 196 */         Slot slot = (event.getContainer()).field_75151_b.get(i);
/* 197 */         if (slot.func_75216_d() && slot.func_75211_c().func_77973_b() instanceof AkumaNoMiItem) {
/*     */ 
/*     */ 
/*     */           
/* 201 */           if (!(event.getContainer() instanceof net.minecraft.inventory.container.RepairContainer)) {
/* 202 */             event.getPlayer().func_71019_a(slot.func_75211_c().func_77946_l(), true);
/*     */           }
/* 204 */           slot.func_75209_a(1);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 209 */       dropFruitsFromNearbyContainers(event.getPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
/* 216 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && event.getTarget() instanceof net.minecraft.entity.item.ItemFrameEntity && !event.getItemStack().func_190926_b() && event.getItemStack().func_77973_b() instanceof AkumaNoMiItem)
/*     */     {
/* 218 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerDeath(LivingDeathEvent event) {
/* 224 */     LivingEntity entity = event.getEntityLiving();
/* 225 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 229 */     IDevilFruit fruitProps = DevilFruitCapability.get(entity);
/*     */     
/* 231 */     OFPWWorldData worldData = OFPWWorldData.get();
/*     */     
/* 233 */     IDevilFruit props = DevilFruitCapability.get(entity);
/*     */     
/* 235 */     boolean fruitRespawned = DevilFruitHelper.respawnDevilFruit(entity, props);
/*     */     
/* 237 */     if (entity instanceof PlayerEntity) {
/* 238 */       PlayerEntity player = (PlayerEntity)entity;
/*     */       
/* 240 */       if (!fruitRespawned && CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */ 
/*     */ 
/*     */         
/* 244 */         if ((!fruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)player)) && 
/* 245 */           DevilFruitHelper.canDevilFruitRespawn() && fruitProps.getDevilFruit().isPresent()) {
/* 246 */           worldData.lostOneFruit(fruitProps.getDevilFruit().get(), (LivingEntity)player, "User's death");
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 252 */         if (fruitProps.hasYamiPower() && 
/* 253 */           DevilFruitHelper.canDevilFruitRespawn()) {
/* 254 */           worldData.lostOneFruit(ModAbilities.YAMI_YAMI_NO_MI.getRegistryName(), (LivingEntity)player, "User died");
/*     */         }
/*     */ 
/*     */         
/* 258 */         ArrayList<ItemStack> slots = new ArrayList<>();
/*     */         
/* 260 */         slots.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_70462_a);
/* 261 */         slots.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_184439_c);
/*     */         
/* 263 */         for (ItemStack invStack : slots) {
/* 264 */           if (invStack != null && invStack.func_77973_b() instanceof AkumaNoMiItem) {
/* 265 */             ResourceLocation key = invStack.func_77973_b().getRegistryName();
/*     */             
/* 267 */             if (worldData.isFruitInUse(key)) {
/* 268 */               invStack.func_190918_g(invStack.func_190916_E());
/*     */               continue;
/*     */             } 
/* 271 */             if (!player.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223600_c)) {
/* 272 */               worldData.updateOneFruit(key, player.func_110124_au(), OneFruitEntry.Status.DROPPED);
/*     */               
/* 274 */               DroppedDevilFruitEvent postEvent = new DroppedDevilFruitEvent((LivingEntity)player, DevilFruitHelper.getDevilFruitItem(key), "User died");
/* 275 */               MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 287 */     if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).field_72995_K)
/*     */     {
/* 289 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */         
/* 291 */         PlayerEntity player = (PlayerEntity)event.getEntity();
/* 292 */         OFPWWorldData worldData = OFPWWorldData.get();
/* 293 */         IDevilFruit fruitData = DevilFruitCapability.get((LivingEntity)player);
/* 294 */         IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */         
/* 296 */         if (CommonConfig.INSTANCE.getDaysForInactivity() > 0)
/*     */         {
/* 298 */           for (OneFruitEntry entry : worldData.getOneFruitEntries()) {
/*     */             
/* 300 */             Date date = entry.getLastUpdate();
/* 301 */             long diff = WyHelper.getDaysSince(date);
/*     */ 
/*     */             
/* 304 */             if (diff >= CommonConfig.INSTANCE.getDaysForInactivity()) {
/* 305 */               worldData.lostOneFruit(entry.getKey(), null, "Inactivity for " + diff + " days");
/*     */             }
/*     */ 
/*     */             
/* 309 */             boolean somebodyElseHasFruit = (entry.getOwner().isPresent() && !((UUID)entry.getOwner().get()).equals(player.func_110124_au()));
/* 310 */             boolean nobodyHasFruit = (!entry.getOwner().isPresent() && entry.getStatus() == OneFruitEntry.Status.LOST);
/*     */             
/* 312 */             if ((somebodyElseHasFruit || nobodyHasFruit) && 
/* 313 */               fruitData.getDevilFruit().isPresent() && entry.getKey().equals(fruitData.getDevilFruit().get())) {
/* 314 */               fruitData.removeDevilFruit();
/* 315 */               abilityData.clearUnlockedAbilities(AbilityCategory.DEVIL_FRUITS.isCorePartofCategory());
/* 316 */               abilityData.clearPassiveAbilities(AbilityCategory.DEVIL_FRUITS.isAbilityPartofCategory());
/* 317 */               abilityData.clearEquippedAbilities(AbilityCategory.DEVIL_FRUITS.isAbilityPartofCategory());
/* 318 */               WyNetwork.sendTo(new SSyncDevilFruitPacket(player.func_145782_y(), fruitData), player);
/* 319 */               player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_OFPW_INACTIVITY), Util.field_240973_b_);
/*     */             } 
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 325 */         if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 326 */           dropFruitsFromNearbyContainers((PlayerEntity)event.getEntity());
/*     */         }
/*     */         
/* 329 */         checkPlayerInventory(player);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeavesWorld(PlayerEvent.PlayerLoggedOutEvent event) {
/* 336 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 337 */       PlayerEntity player = event.getPlayer();
/* 338 */       IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)player);
/* 339 */       OFPWWorldData worldData = OFPWWorldData.get();
/*     */       
/* 341 */       List<ResourceLocation> fruits = new ArrayList<>();
/*     */       
/* 343 */       if (dfProps.hasAnyDevilFruit()) {
/* 344 */         fruits.add(dfProps.getDevilFruit().get());
/*     */       }
/*     */       
/* 347 */       if (dfProps.hasYamiPower()) {
/* 348 */         fruits.add(ModAbilities.YAMI_YAMI_NO_MI.getRegistryName());
/*     */       }
/*     */       
/* 351 */       PlayerInventory inv = player.field_71071_by;
/*     */       
/* 353 */       for (ItemStack stack : inv.field_70462_a) {
/* 354 */         if (stack.func_77973_b() instanceof AkumaNoMiItem) {
/* 355 */           fruits.add(stack.func_77973_b().getRegistryName());
/*     */         }
/*     */       } 
/*     */       
/* 359 */       if (!fruits.isEmpty()) {
/* 360 */         for (ResourceLocation fruit : fruits) {
/* 361 */           worldData.forceUpdateOneFruit(fruit);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 366 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 367 */       dropFruitsFromNearbyContainers((PlayerEntity)event.getEntity());
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickedUp(EntityItemPickupEvent event) {
/* 373 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 375 */     ItemStack stack = event.getItem().func_92059_d();
/*     */     
/* 377 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 378 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 379 */         if (stack.func_77973_b() == Items.field_221970_gq && stack.func_77942_o()) {
/* 380 */           ListNBT items = stack.func_196082_o().func_74775_l("BlockEntityTag").func_150295_c("Items", 10);
/*     */           
/* 382 */           for (int i = 0; i < items.size(); i++) {
/* 383 */             CompoundNBT itemNBT = items.func_150305_b(i);
/*     */             
/* 385 */             String itemId = itemNBT.func_74779_i("id");
/*     */             
/* 387 */             Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
/*     */             
/* 389 */             if (item != null)
/*     */             {
/*     */ 
/*     */               
/* 393 */               if (item instanceof AkumaNoMiItem)
/* 394 */                 items.remove(i); 
/*     */             }
/*     */           } 
/* 397 */         } else if (stack.func_77973_b() instanceof AkumaNoMiItem) {
/* 398 */           IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 403 */           int fruitsFound = ((List)ItemsHelper.getAllInventoryItems((LivingEntity)player).stream().filter(itemStack -> itemStack.func_77973_b() instanceof AkumaNoMiItem).collect(Collectors.toList())).size();
/*     */           
/* 405 */           boolean canYamiPickupFruit = (devilFruitProps.getDevilFruit().equals("yami_yami") && CommonConfig.INSTANCE.isYamiPowerEnabled() && fruitsFound == 0);
/*     */           
/* 407 */           if (devilFruitProps.hasAnyDevilFruit() && CommonConfig.INSTANCE.getUnableToPickDFAsUser() && !canYamiPickupFruit) {
/* 408 */             event.setCanceled(true);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 413 */           if (DevilFruitHelper.hasDFLimitInInventory(player)) {
/* 414 */             event.setCanceled(true);
/*     */           } else {
/* 416 */             OFPWWorldData worldProps = OFPWWorldData.get();
/*     */             
/* 418 */             boolean flag = worldProps.updateOneFruit(stack.func_77973_b().getRegistryName(), player.func_110124_au(), OneFruitEntry.Status.INVENTORY);
/*     */             
/* 420 */             event.setCanceled(!flag);
/* 421 */             InventoryDevilFruitEvent inventoryEvent = new InventoryDevilFruitEvent((LivingEntity)player, stack.func_77973_b(), "Picked up from ground");
/* 422 */             MinecraftForge.EVENT_BUS.post((Event)inventoryEvent);
/*     */           } 
/*     */         } else {
/* 425 */           checkPlayerInventory(player);
/*     */         }
/*     */       
/* 428 */       } else if (stack.func_77973_b() instanceof AkumaNoMiItem) {
/* 429 */         OFPWWorldData worldProps = OFPWWorldData.get();
/*     */         
/* 431 */         boolean flag = worldProps.updateOneFruit(stack.func_77973_b().getRegistryName(), player.func_110124_au(), OneFruitEntry.Status.INVENTORY, "Picked up from ground");
/*     */         
/* 433 */         event.setCanceled(!flag);
/*     */         
/* 435 */         InventoryDevilFruitEvent inventoryEvent = new InventoryDevilFruitEvent((LivingEntity)player, stack.func_77973_b(), "Picked up from ground");
/* 436 */         MinecraftForge.EVENT_BUS.post((Event)inventoryEvent);
/*     */         
/* 438 */         checkPlayerInventory(player);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDevilFruitDropped(EntityJoinWorldEvent event) {
/* 446 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && 
/* 447 */       event.getEntity() instanceof ItemEntity) {
/* 448 */       ItemEntity entity = (ItemEntity)event.getEntity();
/* 449 */       ItemStack stack = entity.func_92059_d();
/*     */       
/* 451 */       UUID thrower = entity.func_200214_m();
/*     */       
/* 453 */       PlayerEntity player = null;
/* 454 */       if (thrower != null) {
/* 455 */         player = event.getWorld().func_217371_b(thrower);
/*     */       }
/*     */       
/* 458 */       if (!stack.func_190926_b() && stack.func_77973_b() instanceof AkumaNoMiItem) {
/* 459 */         OFPWWorldData worldProps = OFPWWorldData.get();
/* 460 */         String position = "" + entity.func_233580_cy_().func_177958_n() + ", " + entity.func_233580_cy_().func_177956_o() + ", " + entity.func_233580_cy_().func_177952_p();
/*     */         
/* 462 */         boolean flag = worldProps.updateOneFruit(stack.func_77973_b().getRegistryName(), entity.func_200214_m(), OneFruitEntry.Status.DROPPED, "Fruit got dopped " + ((player == null) ? "" : ("by " + player.func_145748_c_().getString() + " at " + position)), true);
/*     */         
/* 464 */         event.setCanceled(!flag);
/*     */ 
/*     */         
/* 467 */         DroppedDevilFruitEvent postEvent = new DroppedDevilFruitEvent((LivingEntity)player, stack.func_77973_b(), "Fruit got added to world " + ((player == null) ? "" : ("by " + player.func_145748_c_().getString())));
/* 468 */         MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onExpire(ItemExpireEvent event) {
/* 494 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && event.getEntityItem().func_92059_d().func_77973_b() instanceof AkumaNoMiItem) {
/* 495 */       ItemStack itemStack = event.getEntityItem().func_92059_d();
/*     */       
/* 497 */       AkumaNoMiItem item = (AkumaNoMiItem)itemStack.func_77973_b();
/*     */       
/* 499 */       OFPWWorldData worldData = OFPWWorldData.get();
/*     */       
/* 501 */       worldData.lostOneFruit(item.getRegistryName(), null, "Item Entity expired on ground");
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void dropFruitsFromNearbyContainers(PlayerEntity player) {
/* 506 */     List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities((Entity)player, 40);
/*     */     
/* 508 */     for (BlockPos pos : blockPosList) {
/* 509 */       TileEntity te = player.field_70170_p.func_175625_s(pos);
/*     */       
/* 511 */       if (te instanceof IInventory) {
/* 512 */         for (int i = 0; i < ((IInventory)te).func_70302_i_(); i++) {
/* 513 */           ItemStack stack = ((IInventory)te).func_70301_a(i);
/*     */           
/* 515 */           if (stack != null && stack.func_77973_b() instanceof AkumaNoMiItem) {
/* 516 */             player.func_71019_a(stack.func_77946_l(), false);
/*     */             
/* 518 */             stack.func_190918_g(stack.func_190916_E());
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void checkPlayerInventory(PlayerEntity player) {
/* 526 */     OFPWWorldData worldProps = OFPWWorldData.get();
/*     */     
/* 528 */     IDevilFruit fruitData = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 530 */     ArrayList<ItemStack> slots = new ArrayList<>();
/*     */     
/* 532 */     slots.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_70462_a);
/* 533 */     slots.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_184439_c);
/*     */     
/* 535 */     int fruitsFound = 0;
/*     */     
/* 537 */     for (ItemStack stack : slots) {
/* 538 */       if (stack != null && stack.func_77973_b() instanceof AkumaNoMiItem) {
/* 539 */         ResourceLocation key = stack.func_77973_b().getRegistryName();
/*     */         
/* 541 */         fruitsFound++;
/*     */ 
/*     */         
/* 544 */         if (worldProps.isFruitInUse(key)) {
/* 545 */           stack.func_190918_g(stack.func_190916_E());
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 551 */         if (worldProps.isFruitDuped(key, player.func_110124_au())) {
/* 552 */           stack.func_190918_g(stack.func_190916_E());
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 558 */         if (fruitData.hasAnyDevilFruit() && CommonConfig.INSTANCE.getUnableToPickDFAsUser()) {
/* 559 */           if (fruitData.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI) && CommonConfig.INSTANCE.isYamiPowerEnabled() && fruitsFound == 1) {
/*     */             continue;
/*     */           }
/*     */           
/* 563 */           if (!worldProps.isFruitDuped(key, player.func_110124_au())) {
/* 564 */             worldProps.lostOneFruit(key, null, "Cannot pick up extra fruits");
/*     */           }
/*     */           
/* 567 */           stack.func_190918_g(stack.func_190916_E());
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 573 */         if (fruitsFound > CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
/* 574 */           if (!worldProps.isFruitDuped(key, player.func_110124_au())) {
/* 575 */             worldProps.lostOneFruit(key, null, "Cannot pick up more than " + CommonConfig.INSTANCE.getInventoryLimitForFruits() + " fruits");
/*     */           }
/*     */           
/* 578 */           stack.func_190918_g(stack.func_190916_E());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\OneFruitPerWorldEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */