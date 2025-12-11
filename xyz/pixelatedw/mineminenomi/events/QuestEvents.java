/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.AnimalTameEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerContainerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.LivingHealByEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.stats.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IBrewPotionObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ICureEffectObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEntityInteractObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEquipItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHealEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHitEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IKillEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IReachDorikiObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ISurviveObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseAbilityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class QuestEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/*  59 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  62 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  63 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/*  65 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*  68 */     WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPotionRemoved(LivingEntityUseItemEvent.Stop event) {
/*  74 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  77 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  78 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/*  80 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/*  82 */       if (obj instanceof IUseItemObjective)
/*     */       {
/*  84 */         if (((IUseItemObjective)obj).checkItem(player, event.getItem(), event.getDuration())) {
/*     */           
/*  86 */           obj.alterProgress(player, 1.0D);
/*  87 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerAbilityUse(AbilityUseEvent.Post event) {
/*  96 */     if (event.getEntityLiving() instanceof PlayerEntity && !(event.getEntityLiving()).field_70170_p.field_72995_K) {
/*     */       
/*  98 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  99 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/* 101 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/* 103 */         if (obj instanceof IUseAbilityObjective)
/*     */         {
/* 105 */           if (((IUseAbilityObjective)obj).checkAbility(player, event.getAbility())) {
/*     */             
/* 107 */             obj.alterProgress(player, 1.0D);
/* 108 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */           } 
/*     */         }
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
/*     */   public static void onPlayerBrews(PlayerBrewedPotionEvent event) {
/* 122 */     if ((event.getPlayer()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 125 */     PlayerEntity player = event.getPlayer();
/* 126 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 128 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 130 */       if (obj instanceof IBrewPotionObjective)
/*     */       {
/* 132 */         if (((IBrewPotionObjective)obj).checkPotion(player, event.getStack())) {
/*     */           
/* 134 */           obj.alterProgress(player, 1.0D);
/* 135 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
/* 144 */     if (event.phase != TickEvent.Phase.END || (event.side == LogicalSide.SERVER && event.player.field_70173_aa % 20 == 0)) {
/*     */       
/* 146 */       PlayerEntity player = event.player;
/* 147 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/* 149 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/* 151 */         if (obj instanceof ISurviveObjective)
/*     */         {
/* 153 */           if (((ISurviveObjective)obj).checkTime(player)) {
/*     */             
/* 155 */             obj.alterProgress(player, 1.0D);
/* 156 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEquipmentChanged(LivingEquipmentChangeEvent event) {
/* 166 */     if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 169 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 170 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 172 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 174 */       if (obj instanceof IEquipItemObjective && ((IEquipItemObjective)obj).checkSlot(event.getSlot())) {
/*     */         
/* 176 */         if (((IEquipItemObjective)obj).checkEquippedItem(player, event.getTo())) {
/*     */           
/* 178 */           obj.alterProgress(player, 1.0D);
/* 179 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */           
/*     */           continue;
/*     */         } 
/* 183 */         obj.alterProgress(player, -1.0D, true);
/* 184 */         WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityDies(LivingDeathEvent event) {
/* 193 */     Entity entity = event.getSource().func_76346_g();
/* 194 */     if (entity == null || entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 198 */     PlayerEntity player = null;
/*     */     
/* 200 */     if (entity instanceof PlayerEntity) {
/* 201 */       player = (PlayerEntity)entity;
/*     */     }
/* 203 */     else if (entity instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)entity).getThrower() instanceof PlayerEntity) {
/* 204 */       player = (PlayerEntity)((AbilityProjectileEntity)entity).getThrower();
/*     */     } 
/*     */     
/* 207 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */     
/* 211 */     LivingEntity target = event.getEntityLiving();
/* 212 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 214 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 216 */       if (obj instanceof IKillEntityObjective)
/*     */       {
/* 218 */         if (((IKillEntityObjective)obj).checkKill(player, target, event.getSource())) {
/*     */           
/* 220 */           obj.alterProgress(player, 1.0D);
/* 221 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityAttack(WyLivingHurtEvent event) {
/* 230 */     Entity entity = event.getSource().func_76346_g();
/* 231 */     if (entity == null || entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 235 */     PlayerEntity player = null;
/*     */     
/* 237 */     if (entity instanceof PlayerEntity) {
/* 238 */       player = (PlayerEntity)entity;
/*     */     }
/* 240 */     else if (entity instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)entity).getThrower() instanceof PlayerEntity) {
/* 241 */       player = (PlayerEntity)((AbilityProjectileEntity)entity).getThrower();
/*     */     } 
/*     */     
/* 244 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */     
/* 248 */     LivingEntity target = event.getEntityLiving();
/* 249 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 251 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 253 */       if (obj instanceof IHitEntityObjective)
/*     */       {
/* 255 */         if (((IHitEntityObjective)obj).checkHit(player, target, event.getSource(), event.getAmount())) {
/*     */           
/* 257 */           obj.alterProgress(player, 1.0D);
/* 258 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityHealed(LivingHealByEvent event) {
/* 267 */     if (event.getHealer() instanceof PlayerEntity && !(event.getHealer()).field_70170_p.field_72995_K && event.getEntityLiving() instanceof LivingEntity) {
/*     */       
/* 269 */       PlayerEntity player = (PlayerEntity)event.getHealer();
/* 270 */       LivingEntity target = event.getEntityLiving();
/* 271 */       IQuestData questProps = QuestDataCapability.get(player);
/*     */       
/* 273 */       for (Objective obj : questProps.getInProgressObjectives()) {
/*     */         
/* 275 */         if (obj instanceof IHealEntityObjective)
/*     */         {
/* 277 */           if (((IHealEntityObjective)obj).checkHeal(player, target, event.getAmount())) {
/*     */             
/* 279 */             obj.alterProgress(player, 1.0D);
/* 280 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickedUp(EntityItemPickupEvent event) {
/* 290 */     PlayerEntity player = event.getPlayer();
/* 291 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 293 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 296 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 298 */       if (obj instanceof IObtainItemObjective)
/*     */       {
/* 300 */         if (((IObtainItemObjective)obj).checkItem(event.getItem().func_92059_d())) {
/*     */           
/* 302 */           obj.alterProgress(player, event.getItem().func_92059_d().func_190916_E());
/* 303 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/* 312 */     PlayerEntity player = event.getPlayer();
/* 313 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 315 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 318 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 320 */       if (obj instanceof IObtainItemObjective)
/*     */       {
/* 322 */         if (((IObtainItemObjective)obj).checkItem(event.getEntityItem().func_92059_d())) {
/*     */           
/* 324 */           obj.alterProgress(player, -event.getEntityItem().func_92059_d().func_190916_E(), true);
/* 325 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
/* 334 */     if (event.getHand() != Hand.MAIN_HAND) {
/*     */       return;
/*     */     }
/* 337 */     PlayerEntity player = event.getPlayer();
/* 338 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 340 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 342 */       if (obj instanceof IEntityInteractObjective)
/*     */       {
/* 344 */         if (((IEntityInteractObjective)obj).checkInteraction(player, event.getTarget())) {
/*     */           
/* 346 */           obj.alterProgress(player, 1.0D);
/* 347 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */           return;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDorikiGained(DorikiEvent.Post event) {
/* 357 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 360 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/* 361 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 363 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 365 */       if (obj instanceof IReachDorikiObjective)
/*     */       {
/* 367 */         if (((IReachDorikiObjective)obj).checkDoriki(player)) {
/*     */           
/* 369 */           obj.alterProgress(player, 1.0D);
/* 370 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPotionRemoved(PotionEvent.PotionRemoveEvent event) {
/* 379 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 382 */     EffectInstance inst = event.getPotionEffect();
/* 383 */     if (inst == null) {
/*     */       return;
/*     */     }
/* 386 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 387 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 389 */     for (Objective obj : questProps.getInProgressObjectives()) {
/*     */       
/* 391 */       if (obj instanceof ICureEffectObjective)
/*     */       {
/* 393 */         if (((ICureEffectObjective)obj).checkEffect(player, inst)) {
/*     */           
/* 395 */           obj.alterProgress(player, 1.0D);
/* 396 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onTameEvent(AnimalTameEvent event) {
/* 405 */     PlayerEntity player = event.getTamer();
/* 406 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 408 */     for (Objective obj : questProps.getInProgressObjectives()) {
/* 409 */       if (obj instanceof xyz.pixelatedw.mineminenomi.quests.objectives.TameEntityObjective) {
/* 410 */         obj.alterProgress(player, 1.0D);
/* 411 */         WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onContainerClose(PlayerContainerEvent.Close event) {
/* 418 */     PlayerEntity player = event.getPlayer();
/* 419 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 421 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 425 */     for (Objective obj : questProps.getInProgressObjectives()) {
/* 426 */       if (obj instanceof IObtainItemObjective && obj.getProgress() > 0.0D) {
/* 427 */         boolean noItemFound = true;
/* 428 */         for (ItemStack stack : ItemsHelper.getAllInventoryItems((LivingEntity)player)) {
/* 429 */           if (((IObtainItemObjective)obj).checkItem(stack)) {
/* 430 */             obj.setProgress(player, stack.func_190916_E(), true);
/* 431 */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/* 432 */             noItemFound = false;
/*     */           } 
/*     */         } 
/*     */         
/* 436 */         if (noItemFound) {
/* 437 */           obj.setProgress(player, 0.0D, true);
/* 438 */           WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), questProps), player);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\QuestEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */