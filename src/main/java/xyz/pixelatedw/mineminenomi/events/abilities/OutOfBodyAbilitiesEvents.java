/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.OutOfBodyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
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
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class OutOfBodyAbilitiesEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onAbilityUse(AbilityUseEvent.Pre event) {
/*  39 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  41 */     Optional<OutOfBodyAbility> ability = getOOBAbility(entity);
/*     */ 
/*     */     
/*  44 */     if (ability != null && ability.isPresent()) {
/*     */       
/*  46 */       if (event.getAbility().equals(ability.get())) {
/*     */         return;
/*     */       }
/*     */       
/*  50 */       if (ability.get() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility)
/*     */       {
/*  52 */         if (event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.MiniHollowAbility || event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.NegativeHollowAbility || event.getAbility() instanceof xyz.pixelatedw.mineminenomi.abilities.horo.TokuHollowAbility) {
/*     */           return;
/*     */         }
/*     */       }
/*     */     } 
/*  57 */     if (isSpirit(entity)) {
/*  58 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
/*  66 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  68 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*  71 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickup(EntityItemPickupEvent event) {
/*  77 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  79 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*  82 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/*  88 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  90 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*  93 */     event.setCanceled(true);
/*  94 */     player.func_191521_c(event.getEntityItem().func_92059_d());
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityHits(AttackEntityEvent event) {
/* 100 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 102 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 105 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
/* 111 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 113 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 116 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
/* 122 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 124 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 127 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
/* 133 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 135 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 138 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
/* 144 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 147 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 149 */     if (!isSpirit((LivingEntity)player)) {
/*     */       return;
/*     */     }
/* 152 */     event.setCanceled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(WyLivingAttackEvent event) {
/* 158 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/* 161 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 163 */     if (isSpirit((LivingEntity)player) && !event.getSource().equals(ModDamageSource.OUT_OF_BODY)) {
/* 164 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntitySetAttackTargetEvent(LivingSetAttackTargetEvent event) {
/* 170 */     if (event.getTarget() instanceof PlayerEntity && event.getEntityLiving() instanceof MobEntity) {
/*     */       
/* 172 */       PlayerEntity player = (PlayerEntity)event.getTarget();
/* 173 */       MobEntity mob = (MobEntity)event.getEntityLiving();
/*     */       
/* 175 */       if (!isSpirit((LivingEntity)player)) {
/*     */         return;
/*     */       }
/* 178 */       mob.func_70624_b(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Optional<OutOfBodyAbility> getOOBAbility(LivingEntity entity) {
/* 185 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 187 */     if (abilityProps == null) {
/* 188 */       return Optional.empty();
/*     */     }
/*     */     
/* 191 */     Optional<OutOfBodyAbility> optional = abilityProps.getEquippedAbilities(abl -> (abl instanceof OutOfBodyAbility && ((OutOfBodyAbility)abl).isContinuous())).stream().map(abl -> (OutOfBodyAbility)abl).findFirst();
/*     */     
/* 193 */     return optional;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSpirit(LivingEntity entity) {
/* 198 */     if (entity instanceof PlayerEntity && (((PlayerEntity)entity).func_184812_l_() || entity.func_175149_v())) {
/* 199 */       return false;
/*     */     }
/* 201 */     Optional<OutOfBodyAbility> ability = getOOBAbility(entity);
/*     */     
/* 203 */     if (ability == null || !ability.isPresent() || ((OutOfBodyAbility)ability.get()).isPhysical()) {
/* 204 */       return false;
/*     */     }
/* 206 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\OutOfBodyAbilitiesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */