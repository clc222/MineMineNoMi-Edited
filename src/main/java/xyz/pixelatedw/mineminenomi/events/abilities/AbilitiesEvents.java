/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Food;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SMountEntityPacket;
/*     */ import net.minecraft.network.play.server.SSetPassengersPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.KnockdownAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.RideableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IChangeDamageSourceAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IHitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageTakenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimeScreamComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BowTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ConsumptionComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PauseTickComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SlotDecorationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HitTriggerEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.EquipAbilityEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.INotoriousTarget;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*     */ import xyz.pixelatedw.mineminenomi.events.CombatModeEvents;
/*     */ import xyz.pixelatedw.mineminenomi.events.EffectsEvents;
/*     */ import xyz.pixelatedw.mineminenomi.events.POIHandler;
/*     */ import xyz.pixelatedw.mineminenomi.events.StatsGainEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.components.CSwingTriggerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class AbilitiesEvents {
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onEquipAbility(EquipAbilityEvent event) {
/* 100 */     IAbility ability = event.getAbility();
/*     */     
/* 102 */     ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(component -> {
/*     */           component.addStartEvent(AuraLayer::addAbilityIcon);
/*     */           
/*     */           component.addEndEvent(AuraLayer::addAbilityIcon);
/*     */         });
/* 107 */     ability.getComponent(ModAbilityKeys.CHARGE).ifPresent(component -> {
/*     */           component.addStartEvent(AuraLayer::addAbilityIcon);
/*     */           
/*     */           component.addEndEvent(AuraLayer::addAbilityIcon);
/*     */         });
/* 112 */     ability.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(component -> {
/*     */           component.addStartEvent(AuraLayer::addAbilityIcon);
/*     */           
/*     */           component.addEndEvent(AuraLayer::addAbilityIcon);
/*     */         });
/* 117 */     ability.getComponent(ModAbilityKeys.ANIME_SCREAM).ifPresent(component -> component.setupDefaultScreams(ability));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/* 124 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/* 126 */     if (!entity.func_70089_S()) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     IAbilityData ablProps = AbilityDataCapability.get(entity);
/* 131 */     IEntityStats statsProps = EntityStatsCapability.get(entity);
/*     */     
/* 133 */     if (ablProps == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 138 */     GCDCapability.tickGCD(event.getEntityLiving());
/* 139 */     if (GCDCapability.isOnGCD(entity) && ((Integer)GCDCapability.getGCD(entity).getKey()).intValue() <= 0) {
/* 140 */       for (IAbility ability : ablProps.getEquippedAndPassiveAbilities()) {
/* 141 */         ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(comp -> comp.resetDecoration());
/*     */       }
/*     */     }
/*     */     
/* 145 */     if (!entity.field_70170_p.field_72995_K && statsProps.getInvulnerableTime() > 0) {
/* 146 */       statsProps.alterInvulnerableTime(-1);
/*     */     }
/*     */     
/* 149 */     if (!entity.field_70170_p.field_72995_K) {
/* 150 */       statsProps.tickLastAttack();
/*     */     }
/*     */     
/* 153 */     if (entity.field_70170_p.field_72995_K) {
/* 154 */       AnimationDataCapability.get(entity).tickAnimations();
/*     */     }
/*     */     
/* 157 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/* 158 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 159 */       WyDebug.showTPSInChat(player);
/*     */       
/* 161 */       if (!entity.field_70170_p.field_72995_K) {
/* 162 */         IChallengesData challengeProps = ChallengesDataCapability.get(player);
/* 163 */         challengeProps.tickInvitations();
/*     */       } 
/*     */     } 
/*     */     
/* 167 */     for (IAbility ability : ablProps.getEquippedAndPassiveAbilities()) {
/*     */       try {
/* 169 */         ability.tick(entity);
/*     */       }
/* 171 */       catch (Exception e) {
/* 172 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 177 */     for (ItemStack stack : ItemsHelper.getAllInventoryItems(entity)) {
/* 178 */       if (stack.func_77942_o() && stack.func_77978_p().func_74779_i("spawnedByAbility") != null) {
/* 179 */         ResourceLocation rs = new ResourceLocation(stack.func_77978_p().func_74779_i("spawnedByAbility"));
/* 180 */         AbilityCore core = AbilityCore.get(rs);
/*     */         
/* 182 */         if (core == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 186 */         IAbility abl = AbilityDataCapability.get(entity).getEquippedAbility(core);
/*     */         
/* 188 */         if (abl == null) {
/*     */           return;
/*     */         }
/*     */         
/* 192 */         if (abl.hasComponent(ModAbilityKeys.ITEM_SPAWN) && !((ItemSpawnComponent)abl.getComponent(ModAbilityKeys.ITEM_SPAWN).get()).isActive()) {
/* 193 */           stack.func_190918_g(stack.func_77976_d());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     if (entity.field_70173_aa % 10 == 0) {
/* 199 */       for (EffectInstance inst : entity.func_70651_bq()) {
/* 200 */         EffectsEvents.Common.trySpreadProximityEffect(entity, inst);
/*     */       }
/*     */     }
/*     */     
/* 204 */     TestCommand.runAllAbilityTests(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerDies(LivingDeathEvent event) {
/* 210 */     LivingEntity living = event.getEntityLiving();
/* 211 */     DamageSource source = event.getSource();
/* 212 */     Entity directEntity = source.func_76364_f();
/* 213 */     Entity trueEntity = source.func_76346_g();
/*     */     
/* 215 */     LivingEntity attacker = null;
/*     */     
/* 217 */     if (directEntity != null && directEntity instanceof LivingEntity) {
/* 218 */       attacker = (LivingEntity)directEntity;
/*     */     }
/* 220 */     else if (trueEntity != null && trueEntity instanceof LivingEntity) {
/* 221 */       attacker = (LivingEntity)trueEntity;
/*     */     } 
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
/*     */     
/* 244 */     if (!living.field_70170_p.field_72995_K && living instanceof INotoriousTarget) {
/* 245 */       POIHandler.killNotoriousTarget((ServerWorld)living.field_70170_p, (INotoriousTarget)living);
/*     */     }
/*     */     
/* 248 */     StatsGainEvents.giveStats(living, source);
/* 249 */     BountyEvents.onBountyKilled(event);
/* 250 */     StatsGainEvents.removeIssuedBounty(living);
/*     */     
/* 252 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/* 253 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */       
/* 255 */       IAbilityData ablProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 257 */       if (ablProps == null) {
/*     */         return;
/*     */       }
/*     */     } 
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
/*     */     
/* 302 */     AbilityHelper.disableAbilities(living, 10, (Predicate)Predicates.alwaysTrue());
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityAttackEvent(WyLivingHurtEvent event) {
/* 307 */     if (event.getEntityLiving() != null && !(event.getEntityLiving()).field_70170_p.field_72995_K) {
/* 308 */       LivingEntity entity = event.getEntityLiving();
/*     */       
/* 310 */       Entity attacker = event.getSource().func_76346_g();
/*     */       
/* 312 */       DamageSource damageSource = event.getSource();
/*     */       
/* 314 */       IAbilityData ablProps = AbilityDataCapability.get(entity);
/* 315 */       IEntityStats props = EntityStatsCapability.get(entity);
/*     */       
/* 317 */       float amount = event.getAmount();
/*     */       
/* 319 */       if (ablProps == null) {
/*     */         return;
/*     */       }
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
/* 340 */       if (entity.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get()) && amount <= 1.0F) {
/* 341 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 345 */       if (attacker instanceof LivingEntity) {
/* 346 */         props.setInCombatCache((LivingEntity)attacker);
/*     */         
/* 348 */         IAbilityData attackerProps = AbilityDataCapability.get((LivingEntity)attacker);
/*     */         
/* 350 */         boolean isFatalHit = (entity.func_110143_aJ() - amount <= 0.0F);
/*     */         
/* 352 */         if (isFatalHit) {
/* 353 */           boolean isKnockdownActive = false;
/* 354 */           if (attackerProps != null) {
/*     */             
/* 356 */             KnockdownAbility abl = (KnockdownAbility)attackerProps.getPassiveAbility(KnockdownAbility.INSTANCE);
/* 357 */             if (abl != null)
/*     */             {
/* 359 */               isKnockdownActive = !((PauseTickComponent)abl.getComponent(ModAbilityKeys.PAUSE_TICK).get()).isPaused();
/*     */             }
/*     */           } 
/*     */           
/* 363 */           if (isKnockdownActive) {
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
/* 377 */             if (attackerProps != null && damageSource instanceof ModDamageSource) {
/* 378 */               for (IAbility abl : attackerProps.getEquippedAbilitiesWith(new AbilityComponentKey[] { ModAbilityKeys.HIT_TRIGGER })) {
/* 379 */                 HitTriggerComponent comp = abl.getComponent(ModAbilityKeys.HIT_TRIGGER).get();
/*     */                 
/* 381 */                 if (comp.getResult() == HitTriggerComponent.HitResult.HIT) {
/* 382 */                   boolean result = comp.onHit((LivingEntity)attacker, entity, (ModDamageSource)damageSource);
/*     */                   
/* 384 */                   if (!result) {
/* 385 */                     event.setCanceled(true);
/*     */                   }
/*     */                 } 
/*     */                 
/* 389 */                 HitTriggerEvent.Post postTryHitEvent = new HitTriggerEvent.Post((LivingEntity)attacker, entity, amount, (ModDamageSource)damageSource, abl);
/*     */                 
/* 391 */                 MinecraftForge.EVENT_BUS.post((Event)postTryHitEvent);
/*     */               } 
/*     */             }
/*     */             
/* 395 */             entity.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 1800, 1));
/* 396 */             entity.func_70606_j(5.0F);
/* 397 */             entity.func_70066_B();
/* 398 */             event.setCanceled(true);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 404 */       if (attacker instanceof PlayerEntity) {
/* 405 */         PlayerEntity player = (PlayerEntity)attacker;
/*     */         
/* 407 */         IAbilityData attackerAbilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */         
/* 409 */         if (attackerAbilityProps == null) {
/*     */           return;
/*     */         }
/*     */         
/* 413 */         boolean entityPunch = (damageSource.func_76346_g() == player && (damageSource.func_76355_l().equalsIgnoreCase("player") || (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isFistDamage())));
/* 414 */         boolean meleeProjectile = (damageSource.func_76364_f() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity && damageSource instanceof AbilityDamageSource && ((AbilityDamageSource)damageSource).isFistDamage());
/*     */         
/* 416 */         double lastAttackThreshold = 8.0D;
/* 417 */         ModifiableAttributeInstance attackSpeedAttr = player.func_110148_a(Attributes.field_233825_h_);
/* 418 */         if (attackSpeedAttr != null && !player.func_184614_ca().func_190926_b()) {
/* 419 */           double attackSpeed = attackSpeedAttr.func_111125_b() - attackSpeedAttr.func_111126_e();
/* 420 */           lastAttackThreshold = attackSpeed * 4.0D;
/* 421 */           lastAttackThreshold = Math.max(lastAttackThreshold, 8.0D);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 428 */         int lastAttackTime = player.field_70173_aa - player.func_142013_aG();
/*     */         
/* 430 */         if ((entityPunch || meleeProjectile) && lastAttackTime >= lastAttackThreshold) {
/* 431 */           Predicate<IAbility> hitAbilityCheck = a -> 
/* 432 */             (a instanceof IHitAbility && ((IHitAbility)a).isActive(player));
/*     */ 
/*     */           
/* 435 */           float piercing = 0.0F;
/*     */           
/* 437 */           for (IAbility ability : attackerAbilityProps.getEquippedAbilities(hitAbilityCheck)) {
/* 438 */             ModDamageSource modDamageSource = ((IHitAbility)ability).getDamageSource(player, null);
/* 439 */             if (modDamageSource instanceof ModDamageSource) {
/* 440 */               piercing += ((AbilityDamageSource)modDamageSource).getPierce();
/*     */             }
/*     */           } 
/*     */           
/* 444 */           piercing = MathHelper.func_76131_a(piercing, 0.0F, 1.0F);
/*     */           
/* 446 */           boolean bonusApplied = false;
/*     */           
/* 448 */           for (IAbility ability : attackerAbilityProps.getEquippedAbilities(hitAbilityCheck)) {
/* 449 */             float damage = ((IHitAbility)ability).hitEntity(player, entity);
/*     */             
/* 451 */             ModDamageSource modDamageSource = ((IHitAbility)ability).getDamageSource(player, null);
/*     */             
/* 453 */             boolean isTargetLogia = DevilFruitCapability.get(entity).isLogia();
/* 454 */             boolean isHardeningActive = HakiHelper.hasHardeningActive((LivingEntity)player);
/* 455 */             boolean isDamageBypassingLogia = (modDamageSource instanceof ModDamageSource && modDamageSource.isBypassingLogia());
/* 456 */             boolean canDamageLogias = (!isTargetLogia || (isTargetLogia && (isHardeningActive || isDamageBypassingLogia)));
/*     */             
/* 458 */             if (modDamageSource instanceof ModDamageSource) {
/* 459 */               modDamageSource.setPiercing(piercing);
/*     */             }
/*     */             
/* 462 */             if (!canDamageLogias) {
/*     */               continue;
/*     */             }
/*     */             
/* 466 */             if (damage < 0.0F) {
/* 467 */               event.setCanceled(true);
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 472 */             if (damage > 0.0F) {
/* 473 */               entity.field_70737_aN = entity.field_70172_ad = 0;
/*     */               
/* 475 */               if (((IHitAbility)ability).isStoppingAfterHit() && ability instanceof ContinuousAbility) {
/* 476 */                 ((ContinuousAbility)ability).stopContinuity(player);
/*     */               }
/*     */               
/* 479 */               if (!bonusApplied) {
/*     */                 
/* 481 */                 damage = ((AbilityDamageSource)modDamageSource).getBonusDamage(damage) * (float)EntityStatsCapability.get((LivingEntity)player).getDamageMultiplier();
/* 482 */                 bonusApplied = true;
/*     */               } 
/*     */               
/* 485 */               entity.func_70097_a((DamageSource)modDamageSource, damage);
/*     */               
/* 487 */               event.setCanceled(true);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 536 */       float damageTaken = handleDamageTakenComponents(entity, damageSource, event.getAmount(), DamageTakenComponent.DamageState.HURT);
/*     */       
/* 538 */       event.setAmount(damageTaken);
/*     */       
/* 540 */       if (event.getAmount() <= 0.0F) {
/* 541 */         event.setCanceled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public static void onEntityAttackEvent(WyLivingAttackEvent event) {
/* 548 */     DamageSource damageSource = event.getSource();
/*     */     
/* 550 */     LivingEntity sourceOwner = (damageSource.func_76346_g() instanceof LivingEntity) ? (LivingEntity)damageSource.func_76346_g() : null;
/* 551 */     LivingEntity entity = event.getEntityLiving();
/*     */ 
/*     */ 
/*     */     
/* 555 */     if (entity != null && !entity.field_70170_p.field_72995_K) {
/* 556 */       IEntityStats entityStatsProps = EntityStatsCapability.get(entity);
/*     */       
/* 558 */       if (entityStatsProps.getInvulnerableTime() > 0) {
/* 559 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 564 */       IAbilityData ablProps = AbilityDataCapability.get(entity);
/*     */       
/* 566 */       if (ablProps == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 575 */       if (event.getSource() == ModDamageSource.DEVILS_CURSE) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 580 */       ModDamageSource punchSource = null;
/* 581 */       if (sourceOwner instanceof PlayerEntity) {
/* 582 */         PlayerEntity playerOwner = (PlayerEntity)sourceOwner;
/* 583 */         Predicate<IAbility> hitAbilityCheck = a -> 
/* 584 */           (a instanceof IHitAbility && ((IHitAbility)a).isActive(playerOwner));
/*     */         
/* 586 */         for (IAbility ability : AbilityDataCapability.get(sourceOwner).getEquippedAbilities(hitAbilityCheck)) {
/* 587 */           punchSource = ((IHitAbility)ability).getDamageSource(playerOwner, punchSource);
/*     */         }
/*     */       } 
/*     */       
/* 591 */       if (entity instanceof PlayerEntity) {
/* 592 */         for (IAbility ability : ablProps.getEquippedAndPassiveAbilities()) {
/*     */           
/* 594 */           if (ability instanceof IOnDamageTakenAbility && ability.canUse(entity).isSuccess()) {
/* 595 */             boolean result = ((IOnDamageTakenAbility)ability).isDamageTaken(entity, (punchSource == null) ? damageSource : (DamageSource)punchSource, event.getAmount());
/*     */             
/* 597 */             if (!result) {
/* 598 */               event.setCanceled(true);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 603 */           if (ability instanceof IFallDamageBlockingAbility && damageSource == DamageSource.field_76379_h && ability.canUse(entity).isSuccess()) {
/* 604 */             boolean blockFallDamage = !((IFallDamageBlockingAbility)ability).hasFallDamage();
/* 605 */             if (blockFallDamage) {
/* 606 */               entity.field_70143_R = 0.0F;
/* 607 */               ((IFallDamageBlockingAbility)ability).resetFallDamage(entity);
/* 608 */               event.setCanceled(true);
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 617 */       if (event.getSource().func_76346_g() instanceof PlayerEntity && (event
/* 618 */         .getSource().func_76355_l().equals("player") || event.getSource().func_76355_l().equals("mob")) && event.getAmount() > 0.0F) {
/* 619 */         PlayerEntity attacker = (PlayerEntity)event.getSource().func_76346_g();
/*     */         
/* 621 */         ablProps = AbilityDataCapability.get((LivingEntity)attacker);
/*     */         
/* 623 */         if (ablProps == null) {
/*     */           return;
/*     */         }
/*     */         
/* 627 */         for (IAbility ability : ablProps.getEquippedAbilities()) {
/* 628 */           if (ability == null) {
/*     */             continue;
/*     */           }
/*     */           
/*     */           try {
/* 633 */             if (ability instanceof IChangeDamageSourceAbility) {
/* 634 */               IChangeDamageSourceAbility abl = (IChangeDamageSourceAbility)ability;
/* 635 */               if (abl.isSourceChangeEnabled()) {
/* 636 */                 boolean sameGroup = ModEntityPredicates.getFriendlyFactions((LivingEntity)attacker).test(entity);
/* 637 */                 if (sameGroup) {
/*     */                   return;
/*     */                 }
/*     */                 
/* 641 */                 double strength = attacker.func_110148_a(Attributes.field_233823_f_).func_111126_e();
/* 642 */                 if (strength == 0.0D) {
/*     */                   return;
/*     */                 }
/*     */                 
/* 646 */                 float damage = (float)(abl.damageToEntityWithSource(attacker, entity) * event.getAmount() / strength);
/* 647 */                 DamageSource source = abl.getSourceToUse(attacker);
/* 648 */                 boolean damaged = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 654 */                 if (entity.field_70172_ad == 0 || abl.cancelsOriginalDamage()) {
/* 655 */                   damaged = entity.func_70097_a(source, damage);
/* 656 */                   entity.field_70737_aN = entity.field_70172_ad = 0;
/*     */                 } 
/*     */                 
/* 659 */                 if (!damaged || abl.cancelsOriginalDamage()) {
/* 660 */                   event.setCanceled(true);
/*     */                 }
/*     */               }
/*     */             
/*     */             } 
/* 665 */           } catch (Exception e) {
/* 666 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(WyLivingDamageEvent event) {
/* 676 */     if (event.getEntityLiving() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 680 */     LivingEntity entity = event.getEntityLiving();
/* 681 */     DamageSource source = event.getSource();
/*     */     
/* 683 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 687 */     if (entity instanceof PlayerEntity) {
/* 688 */       boolean isFlying = ((PlayerEntity)entity).field_71075_bZ.field_75100_b;
/* 689 */       if (isFlying && source instanceof ModDamageSource && ((ModDamageSource)source).hasType(SourceType.BULLET)) {
/* 690 */         AbilityHelper.setDeltaMovement((Entity)entity, Vector3d.field_186680_a);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 695 */     float damageTaken = handleDamageTakenComponents(entity, source, event.getAmount(), DamageTakenComponent.DamageState.DAMAGE);
/*     */     
/* 697 */     event.setAmount(damageTaken);
/*     */     
/* 699 */     if (event.getAmount() <= 0.0F) {
/* 700 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayarLogsOut(PlayerEvent.PlayerLoggedOutEvent event) {
/* 706 */     if ((event.getPlayer()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 710 */     PlayerEntity player = event.getPlayer();
/*     */     
/* 712 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 714 */     if (props == null) {
/*     */       return;
/*     */     }
/*     */     
/* 718 */     for (IAbility ability : props.getEquippedAbilities()) {
/* 719 */       if (ability == null);
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
/*     */   @SubscribeEvent
/*     */   public static void onPotionEvent(PotionEvent.PotionApplicableEvent event) {
/* 739 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 743 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */     
/* 745 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 747 */     if (props == null) {
/*     */       return;
/*     */     }
/*     */     
/* 751 */     for (IAbility ability : props.getEquippedAndPassiveAbilities()) {
/*     */       try {
/* 753 */         if (ability instanceof PotionPassiveAbility) {
/* 754 */           boolean applied = ((PotionPassiveAbility)ability).check(player, event.getPotionEffect());
/* 755 */           if (applied) {
/* 756 */             event.setResult(Event.Result.ALLOW);
/*     */             continue;
/*     */           } 
/* 759 */           event.setResult(Event.Result.DENY);
/*     */         }
/*     */       
/*     */       }
/* 763 */       catch (Exception e) {
/* 764 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityStoppedUsingBow(LivingEntityUseItemEvent.Stop event) {
/* 771 */     if (ItemsHelper.isBow(event.getItem())) {
/* 772 */       IAbilityData props = AbilityDataCapability.get(event.getEntityLiving());
/*     */       
/* 774 */       for (IAbility abl : props.getEquippedAbilities()) {
/* 775 */         Optional<BowTriggerComponent> opt = abl.getComponent(ModAbilityKeys.BOW_TRIGGER);
/* 776 */         if (opt.isPresent() && ((BowTriggerComponent)opt.get()).tryShoot(event.getEntityLiving()) && 
/* 777 */           !event.isCanceled()) {
/* 778 */           event.setCanceled(true);
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
/*     */   public static void onItemTossed(ItemTossEvent event) {
/* 805 */     ItemStack stack = event.getEntityItem().func_92059_d();
/* 806 */     if (stack != null && !stack.func_190926_b() && stack.func_77942_o() && stack.func_77978_p().func_74767_n("spawnedByAbility")) {
/* 807 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityClick(PlayerInteractEvent.EntityInteract event) {
/* 813 */     if (event.getWorld().func_201670_d()) {
/*     */       return;
/*     */     }
/*     */     
/* 817 */     if (event.getHand() == Hand.OFF_HAND) {
/*     */       return;
/*     */     }
/*     */     
/* 821 */     if (event.getTarget() instanceof LivingEntity) {
/* 822 */       if (event.getTarget() instanceof PlayerEntity) {
/* 823 */         boolean bool = CombatModeEvents.Common.tryLeashTarget(event.getPlayer(), (PlayerEntity)event.getTarget());
/* 824 */         if (bool) {
/* 825 */           event.setCanceled(true);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 830 */       boolean flag = CombatModeEvents.Common.tryPickupTarget(event.getPlayer(), (LivingEntity)event.getTarget());
/* 831 */       if (flag) {
/* 832 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 837 */     if (event.getTarget() instanceof PlayerEntity) {
/* 838 */       startRidingZoan(event.getPlayer(), (PlayerEntity)event.getTarget());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void startRidingZoan(PlayerEntity player, PlayerEntity mount) {
/* 843 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)mount);
/*     */     
/* 845 */     if (abilityProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 849 */     boolean hasRideable = (abilityProps.getPassiveAbilities(RideableAbility.class::isInstance).stream().filter(abl -> abl.canUse((LivingEntity)mount).isSuccess()).count() > 0L);
/* 850 */     if (hasRideable) {
/* 851 */       player.func_184220_m((Entity)mount);
/* 852 */       ((ServerWorld)player.field_70170_p).func_72863_F().func_217216_a((Entity)player, (IPacket)new SMountEntityPacket((Entity)player, (Entity)mount));
/* 853 */       ((ServerWorld)player.field_70170_p).func_72863_F().func_217216_a((Entity)player, (IPacket)new SSetPassengersPacket((Entity)mount));
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemUsed(LivingEntityUseItemEvent.Finish event) {
/* 859 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/* 861 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 863 */     if (abilityProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 867 */     ItemStack itemStack = event.getItem();
/*     */     
/* 869 */     if (itemStack.func_222117_E()) {
/* 870 */       Food food = itemStack.func_77973_b().func_219967_s();
/*     */       
/* 872 */       int nutrition = food.func_221466_a();
/*     */       
/* 874 */       float saturationModifier = food.func_221469_b();
/*     */       
/* 876 */       for (IAbility abl : abilityProps.getEquippedAndPassiveAbilities()) {
/* 877 */         abl.getComponent(ModAbilityKeys.CONSUMPTION).ifPresent(component -> component.tryConsumption(entity, nutrition, saturationModifier));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onMouseClicked(InputEvent.ClickInputEvent event) {
/* 887 */     if (event.getHand() == Hand.MAIN_HAND) {
/* 888 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 890 */       ClientPlayerEntity player = mc.field_71439_g;
/*     */       
/* 892 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 894 */       if (abilityProps == null) {
/*     */         return;
/*     */       }
/*     */       
/* 898 */       if (event.shouldSwingHand() && event.isAttack() && !player.func_184838_M() && player.func_184825_o(0.0F) >= 1.0D) {
/* 899 */         for (IAbility abl : abilityProps.getEquippedAndPassiveAbilities()) {
/* 900 */           abl.getComponent(ModAbilityKeys.SWING_TRIGGER).ifPresent(component -> WyNetwork.sendToServer(new CSwingTriggerPacket((LivingEntity)player, abl)));
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerLeavesWorld(PlayerEvent.PlayerLoggedOutEvent event) {
/* 910 */     if (!(event.getEntity()).field_70170_p.field_72995_K) {
/* 911 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)event.getPlayer());
/* 912 */       for (IAbility ability : props.getEquippedAbilities()) {
/* 913 */         AbilityHelper.emergencyStopAbility((LivingEntity)event.getPlayer(), ability);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onChangeDimension(EntityTravelToDimensionEvent event) {
/* 920 */     if (event.getEntity() instanceof LivingEntity) {
/* 921 */       LivingEntity entity = (LivingEntity)event.getEntity();
/* 922 */       IAbilityData props = AbilityDataCapability.get(entity);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 927 */       for (IAbility abl : props.getEquippedAbilities()) {
/* 928 */         AbilityHelper.emergencyStopAbility(entity, abl);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float handleDamageTakenComponents(LivingEntity entity, DamageSource source, float amount, DamageTakenComponent.DamageState state) {
/* 934 */     IAbilityData props = (IAbilityData)AbilityDataCapability.getLazy(entity).orElse(null);
/*     */     
/* 936 */     if (props == null) {
/* 937 */       return amount;
/*     */     }
/*     */     
/* 940 */     Set<IAbility> abilities = props.getEquippedAndPassiveAbilities(core -> core.hasComponent(ModAbilityKeys.DAMAGE_TAKEN));
/*     */     
/* 942 */     for (IAbility ability : abilities) {
/*     */       
/* 944 */       boolean isOutOfBodyDamage = (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.OutOfBodyAbility && source.equals(ModDamageSource.OUT_OF_BODY));
/*     */       
/* 946 */       if (isOutOfBodyDamage) {
/* 947 */         return amount;
/*     */       }
/*     */       
/* 950 */       DamageTakenComponent damageTakenComponent = ability.getComponent(ModAbilityKeys.DAMAGE_TAKEN).get();
/*     */       
/* 952 */       amount = damageTakenComponent.checkDamageTaken(entity, source, amount, state);
/*     */     } 
/*     */     
/* 955 */     return amount;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilitiesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */