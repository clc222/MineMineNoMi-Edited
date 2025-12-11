/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.AbstractArrowEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDamageEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiInfusionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.ISweep;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HitTriggerBlockedEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HitTriggerEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ProjectileHitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetOnFireEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingAttackEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilitiesEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class FactionEvents
/*     */ {
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void onEntityAttack(WyLivingAttackEvent event) {
/*  84 */     if ((event.getEntityLiving()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     DamageSource source = event.getSource();
/*     */     
/*  90 */     Entity sourceEntity = source.func_76346_g();
/*     */     
/*  92 */     LivingEntity target = event.getEntityLiving();
/*     */     
/*  94 */     float amount = event.getAmount();
/*     */     
/*  96 */     ProtectedArea area = ProtectedAreasData.get(target.field_70170_p).getProtectedArea((int)target.func_226277_ct_(), (int)target.func_226278_cu_(), (int)target.func_226281_cx_());
/*  97 */     if (area != null) {
/*  98 */       boolean canBypass = (source instanceof ModDamageSource && ((ModDamageSource)source).canBypassFriendlyDamage());
/*     */       
/* 100 */       if (!area.canHurtEntities() && !canBypass) {
/* 101 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 106 */     if (isDamageSourceBlockedByShield(source, target)) {
/* 107 */       ItemsHelper.hurtCurrentlyUsedShield(target, amount);
/*     */       
/* 109 */       if (sourceEntity != null) {
/* 110 */         target.func_233627_a_(0.5F, sourceEntity.func_226277_ct_() - target.func_226277_ct_(), sourceEntity.func_226281_cx_() - target.func_226281_cx_());
/* 111 */         target.field_70133_I = true;
/*     */       } 
/*     */       
/* 114 */       event.setCanceled(true);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 119 */     if (sourceEntity instanceof LivingEntity && (!(source instanceof EntityDamageSource) || !((EntityDamageSource)source).func_180139_w())) {
/* 120 */       LivingEntity attacker = (LivingEntity)sourceEntity;
/*     */ 
/*     */       
/* 123 */       FriendlyFireHitResult result = tryFriendlyFireHit(attacker, target, source, amount);
/*     */       
/* 125 */       if (result.cancel) {
/* 126 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 131 */       source = result.source;
/* 132 */       amount = result.damage;
/*     */       
/* 134 */       event.setSource(source);
/* 135 */       event.setAmount(amount);
/*     */     
/*     */     }
/* 138 */     else if (sourceEntity == null) {
/* 139 */       float damageTaken = AbilitiesEvents.handleDamageTakenComponents(target, source, event.getAmount(), DamageTakenComponent.DamageState.ATTACK);
/*     */       
/* 141 */       event.setAmount(damageTaken);
/*     */       
/* 143 */       boolean isBlocked = (damageTaken <= 0.0F);
/*     */       
/* 145 */       if (isBlocked) {
/* 146 */         event.setCanceled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void onLivingHurt(LivingHurtEvent event) {
/* 153 */     if (ModMain.hasShitInstalled()) {
/* 154 */       WyLivingHurtEvent newEvent = new WyLivingHurtEvent(event.getEntityLiving(), event.getSource(), event.getAmount());
/*     */       
/* 156 */       MinecraftForge.EVENT_BUS.post((Event)newEvent);
/*     */       
/* 158 */       if (Float.isNaN(newEvent.getAmount())) {
/*     */         return;
/*     */       }
/*     */       
/* 162 */       if (!newEvent.isCanceled()) {
/* 163 */         ModDamageSource newSource = new ModDamageSource(newEvent.getSource());
/* 164 */         newSource.ignore();
/* 165 */         event.setAmount(newEvent.getAmount());
/*     */         try {
/* 167 */           Field field = event.getClass().getDeclaredField("source");
/* 168 */           field.setAccessible(true);
/* 169 */           field.set(event, newSource);
/*     */         }
/* 171 */         catch (Exception e) {
/* 172 */           e.printStackTrace();
/*     */         } 
/*     */       } else {
/*     */         
/* 176 */         event.setAmount(0.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void onLivingDamage(LivingDamageEvent event) {
/* 183 */     if (ModMain.hasShitInstalled()) {
/* 184 */       WyLivingDamageEvent newEvent = new WyLivingDamageEvent(event.getEntityLiving(), event.getSource(), event.getAmount());
/*     */       
/* 186 */       MinecraftForge.EVENT_BUS.post((Event)newEvent);
/*     */       
/* 188 */       if (Float.isNaN(newEvent.getAmount())) {
/*     */         return;
/*     */       }
/*     */       
/* 192 */       if (!newEvent.isCanceled()) {
/* 193 */         ModDamageSource newSource = new ModDamageSource(newEvent.getSource());
/* 194 */         newSource.ignore();
/* 195 */         event.setAmount(newEvent.getAmount());
/*     */         try {
/* 197 */           Field field = event.getClass().getDeclaredField("source");
/* 198 */           field.setAccessible(true);
/* 199 */           field.set(event, newSource);
/*     */         }
/* 201 */         catch (Exception e) {
/* 202 */           e.printStackTrace();
/*     */         } 
/*     */       } else {
/*     */         
/* 206 */         event.setAmount(0.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void onWyLivingHurt(WyLivingHurtEvent event) {
/* 213 */     if ((event.getEntityLiving()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 217 */     DamageSource source = event.getSource();
/*     */     
/* 219 */     Entity sourceEntity = source.func_76346_g();
/* 220 */     Entity directEntity = source.func_76364_f();
/*     */     
/* 222 */     if (!(sourceEntity instanceof LivingEntity) || (source instanceof EntityDamageSource && ((EntityDamageSource)source).func_180139_w())) {
/*     */       return;
/*     */     }
/*     */     
/* 226 */     LivingEntity attacker = (LivingEntity)sourceEntity;
/* 227 */     LivingEntity target = event.getEntityLiving();
/*     */     
/* 229 */     float amount = event.getAmount();
/*     */ 
/*     */ 
/*     */     
/* 233 */     List<Effect> preExistingBreakableEffects = (List<Effect>)target.func_193076_bZ().keySet().stream().filter(effect -> effect instanceof xyz.pixelatedw.mineminenomi.api.effects.IBreakableEffect).collect(Collectors.toList());
/*     */     
/* 235 */     if (source instanceof ModDamageSource) {
/* 236 */       IAbilityData abilityProps = AbilityDataCapability.get(attacker);
/*     */       
/* 238 */       for (IAbility abl : abilityProps.getEquippedAbilitiesWith(new AbilityComponentKey[] { ModAbilityKeys.HIT_TRIGGER })) {
/* 239 */         HitTriggerComponent comp = abl.getComponent(ModAbilityKeys.HIT_TRIGGER).get();
/*     */         
/* 241 */         if (comp.getResult() == HitTriggerComponent.HitResult.HIT) {
/* 242 */           boolean result = comp.onHit(attacker, target, (ModDamageSource)source);
/*     */           
/* 244 */           if (!result) {
/* 245 */             event.setCanceled(true);
/*     */           }
/*     */         } 
/*     */         
/* 249 */         HitTriggerEvent.Post postTryHitEvent = new HitTriggerEvent.Post(attacker, target, amount, (ModDamageSource)source, abl);
/*     */         
/* 251 */         MinecraftForge.EVENT_BUS.post((Event)postTryHitEvent);
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
/* 265 */     amount = EffectsEvents.Common.applyBreakableEffectDamage((Entity)attacker, target, amount, preExistingBreakableEffects);
/* 266 */     amount = DamageCalcsEvents.calcExtraDamage(target, source, directEntity, amount);
/*     */     
/* 268 */     if (amount < 0.0F) {
/* 269 */       event.setCanceled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 273 */     event.setAmount(amount);
/*     */   }
/*     */   
/*     */   private static class FriendlyFireHitResult
/*     */   {
/*     */     private boolean cancel = false;
/*     */     private DamageSource source;
/* 280 */     private float damage = 0.0F;
/*     */     
/*     */     public FriendlyFireHitResult(DamageSource defaultSource) {
/* 283 */       this.source = defaultSource;
/*     */     }
/*     */     
/*     */     public void setCancel(boolean flag) {
/* 287 */       this.cancel = flag;
/*     */     }
/*     */     
/*     */     public void setSource(DamageSource source) {
/* 291 */       this.source = source;
/*     */     }
/*     */     
/*     */     public void setDamage(float damage) {
/* 295 */       this.damage = damage;
/*     */     }
/*     */   }
/*     */   
/*     */   public static FriendlyFireHitResult tryFriendlyFireHit(LivingEntity attacker, LivingEntity target, DamageSource source, float amount) {
/* 300 */     FriendlyFireHitResult returnResult = new FriendlyFireHitResult(source);
/*     */     
/* 302 */     returnResult.damage = amount;
/*     */     
/* 304 */     boolean sameGroup = ModEntityPredicates.getFriendlyFactions(attacker).test(target);
/*     */     
/* 306 */     if (!CommonConfig.INSTANCE.isFriendlyDamageDisabled()) {
/* 307 */       sameGroup = false;
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
/* 318 */     HitTriggerEvent.Pre preTryHitEvent = null;
/*     */     
/* 320 */     IAbilityData ablProps = AbilityDataCapability.get(attacker);
/*     */     
/* 322 */     ArrayList<IAbility> successfulTryHits = new ArrayList<>();
/*     */     
/* 324 */     if (isDirectHit(source) && ablProps != null) {
/* 325 */       ModDamageSource punchSource = null;
/*     */       
/* 327 */       boolean sourceSet = false;
/*     */       
/* 329 */       for (IAbility abl : ablProps.getEquippedAbilitiesWith(new AbilityComponentKey[] { ModAbilityKeys.HIT_TRIGGER })) {
/* 330 */         ModIndirectEntityDamageSource modIndirectEntityDamageSource; if (punchSource == null) {
/* 331 */           if (attacker == source.func_76364_f()) {
/* 332 */             if (source instanceof AbilityDamageSource) {
/* 333 */               punchSource = (ModDamageSource)source;
/*     */             } else {
/*     */               
/* 336 */               AbilityDamageSource abilityDamageSource = ModDamageSource.causeAbilityDamage(attacker, abl, "ability");
/*     */             } 
/*     */           } else {
/*     */             
/* 340 */             modIndirectEntityDamageSource = new ModIndirectEntityDamageSource("ability", source.func_76364_f(), (Entity)attacker);
/*     */           } 
/*     */         }
/*     */         
/* 344 */         boolean canHit = false;
/*     */         
/* 346 */         HitTriggerComponent comp = abl.getComponent(ModAbilityKeys.HIT_TRIGGER).get();
/* 347 */         HitTriggerComponent.HitResult tryHitResult = comp.tryHit(attacker, target, (ModDamageSource)modIndirectEntityDamageSource);
/*     */         
/* 349 */         if (sameGroup && modIndirectEntityDamageSource.bypassesSameGroupProtection()) {
/* 350 */           canHit = true;
/* 351 */         } else if (!sameGroup) {
/* 352 */           canHit = true;
/*     */         } 
/*     */         
/* 355 */         if (!canHit) {
/*     */           continue;
/*     */         }
/*     */         
/* 359 */         preTryHitEvent = new HitTriggerEvent.Pre(attacker, target, returnResult.damage, (ModDamageSource)modIndirectEntityDamageSource, abl, tryHitResult);
/*     */         
/* 361 */         if (MinecraftForge.EVENT_BUS.post((Event)preTryHitEvent)) {
/* 362 */           returnResult.setCancel(true);
/*     */           
/* 364 */           return returnResult;
/*     */         } 
/*     */         
/* 367 */         switch (preTryHitEvent.getHitResult()) {
/*     */           case HIT:
/* 369 */             returnResult.setSource((DamageSource)preTryHitEvent.getSource());
/* 370 */             returnResult.setDamage(preTryHitEvent.getBonusDamage());
/*     */             
/* 372 */             successfulTryHits.add(abl);
/*     */             
/* 374 */             sourceSet = true;
/*     */ 
/*     */           
/*     */           case FAIL:
/* 378 */             returnResult.setCancel(true);
/*     */             
/* 380 */             return returnResult;
/*     */           case PASS:
/* 382 */             if (!sourceSet) {
/* 383 */               modIndirectEntityDamageSource = null;
/*     */             }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 393 */     returnResult.damage = applyDamageBonus(attacker, source, returnResult.damage);
/*     */     
/* 395 */     if (((ISweep)source).isSweeping()) {
/* 396 */       float bonusDamage = Math.max(returnResult.damage - amount, 0.0F);
/* 397 */       float sweepDamage = bonusDamage * EnchantmentHelper.func_191527_a(attacker);
/*     */       
/* 399 */       returnResult.damage = sweepDamage + amount;
/*     */     } 
/*     */     
/* 402 */     float damageTaken = AbilitiesEvents.handleDamageTakenComponents(target, returnResult.source, returnResult.damage, DamageTakenComponent.DamageState.ATTACK);
/*     */     
/* 404 */     returnResult.setDamage(damageTaken);
/*     */     
/* 406 */     boolean isBlocked = (damageTaken <= 0.0F);
/*     */     
/* 408 */     if (isBlocked) {
/* 409 */       for (IAbility abl : successfulTryHits) {
/* 410 */         HitTriggerComponent comp = abl.getComponent(ModAbilityKeys.HIT_TRIGGER).get();
/*     */         
/* 412 */         comp.resetHitResult();
/*     */         
/* 414 */         HitTriggerBlockedEvent hitTriggerBlockedEvent = new HitTriggerBlockedEvent(abl);
/*     */         
/* 416 */         MinecraftForge.EVENT_BUS.post((Event)hitTriggerBlockedEvent);
/*     */       } 
/*     */     }
/*     */     
/* 420 */     returnResult.setCancel(isBlocked);
/*     */     
/* 422 */     boolean canBypass = (preTryHitEvent != null && preTryHitEvent.getSource().bypassesSameGroupProtection());
/* 423 */     if (sameGroup && !canBypass) {
/* 424 */       returnResult.setCancel(true);
/*     */     }
/*     */     
/* 427 */     return returnResult;
/*     */   }
/*     */   
/*     */   public static float applyDamageBonus(LivingEntity attacker, DamageSource source, float originalAmount) {
/* 431 */     float amount = originalAmount;
/*     */     
/* 433 */     amount += applyPunchBonuses(attacker, source);
/* 434 */     amount += applyHakiBonuses(attacker, source, amount);
/*     */     
/* 436 */     return amount;
/*     */   }
/*     */   
/*     */   public static float applyPunchBonuses(LivingEntity attacker, DamageSource source) {
/* 440 */     float amount = 0.0F;
/*     */     
/* 442 */     if (source == null) {
/* 443 */       return amount;
/*     */     }
/*     */     
/* 446 */     Entity directEntity = source.func_76364_f();
/*     */     
/* 448 */     if (directEntity == null) {
/* 449 */       return amount;
/*     */     }
/*     */     
/* 452 */     if (isDirectHit(source)) {
/* 453 */       if (AbilityHelper.canUseBrawlerAbilities(attacker) || MorphHelper.getZoanInfo(attacker) != null) {
/* 454 */         ModifiableAttributeInstance punchAttribute = attacker.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get());
/*     */         
/* 456 */         if (punchAttribute != null) {
/* 457 */           amount = (float)(amount + punchAttribute.func_111126_e());
/*     */         }
/*     */       } 
/* 460 */     } else if (directEntity instanceof AbilityProjectileEntity) {
/* 461 */       ModDamageSource projSource = ((AbilityProjectileEntity)directEntity).getDamageSource();
/*     */       
/* 463 */       ItemStack projectileWeaponUsed = ItemsHelper.getProjectileUsedWeapon((ProjectileEntity)directEntity);
/*     */       
/* 465 */       if ((projSource.isFistDamage() && (projectileWeaponUsed == null || projectileWeaponUsed.func_190926_b())) || projSource.isPhysical()) {
/* 466 */         ModifiableAttributeInstance punchAttribute = attacker.func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get());
/*     */         
/* 468 */         if (punchAttribute != null) {
/* 469 */           amount = (float)(amount + punchAttribute.func_111126_e());
/*     */         }
/* 471 */       } else if (projSource.isSlash()) {
/* 472 */         amount += ItemsHelper.getItemDamage(ItemsHelper.getProjectileUsedWeapon((ProjectileEntity)directEntity)) + 1.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 476 */     return amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float applyHakiBonuses(LivingEntity attacker, DamageSource source, float originalAmount) {
/* 481 */     if (originalAmount <= 0.0F) {
/* 482 */       return 0.0F;
/*     */     }
/*     */     
/* 485 */     if (source == null) {
/* 486 */       return 0.0F;
/*     */     }
/*     */     
/* 489 */     float amount = 0.0F;
/*     */     
/* 491 */     Entity directEntity = source.func_76364_f();
/*     */     
/* 493 */     boolean hasHardeningActive = HakiHelper.hasHardeningActive(attacker, false, false);
/* 494 */     boolean hasImbuingActive = ((directEntity instanceof ProjectileEntity && HakiHelper.isProjectileBusoshokuImbued((ProjectileEntity)directEntity)) || HakiHelper.hasImbuingActive(attacker, false, false));
/* 495 */     boolean hasAdvancedBusoActive = ((directEntity instanceof ProjectileEntity && HakiHelper.isProjectileBusoshokuShrouded((ProjectileEntity)directEntity)) || HakiHelper.hasAdvancedBusoActive(attacker));
/* 496 */     boolean hasInfusionActive = ((directEntity instanceof ProjectileEntity && HakiHelper.isProjectileHaoshokuInfused((ProjectileEntity)directEntity)) || HakiHelper.hasInfusionActive(attacker));
/* 497 */     boolean hasFullbodyActive = HakiHelper.hasBusoFullbodyActive(attacker);
/* 498 */     boolean hasEmissionActive = HakiHelper.hasBusoEmissionActive(attacker);
/*     */     
/* 500 */     float basicBusoReducer = 1.0F;
/* 501 */     float advancedBusoReducer = 1.0F;
/*     */     
/* 503 */     if (hasFullbodyActive) {
/* 504 */       basicBusoReducer = (HakiHelper.getHakiRank(HakiType.BUSOSHOKU, attacker) == HakiHelper.HakiRank.MASTER) ? 1.0F : 0.75F;
/*     */     }
/*     */     
/* 507 */     if (hasEmissionActive) {
/* 508 */       advancedBusoReducer = (HakiHelper.getHakiRank(HakiType.BUSOSHOKU, attacker) == HakiHelper.HakiRank.MASTER) ? 1.0F : 0.95F;
/*     */     }
/*     */     
/* 511 */     if (isDirectHit(source)) {
/* 512 */       if ((hasHardeningActive && AbilityHelper.canUseBrawlerAbilities(attacker)) || (hasImbuingActive && ItemsHelper.isSword(attacker.func_184614_ca()))) {
/* 513 */         amount = (float)(amount + HakiHelper.getBasicBusoshokuHakiDamageBoost(attacker, originalAmount) * basicBusoReducer);
/*     */       }
/*     */       
/* 516 */       if (hasAdvancedBusoActive) {
/* 517 */         amount = (float)(amount + HakiHelper.getAdvancedBusoshokuHakiDamageBoost(attacker) * advancedBusoReducer);
/*     */       }
/*     */       
/* 520 */       if (hasInfusionActive) {
/* 521 */         amount = (float)(amount + HaoshokuHakiInfusionAbility.getDamageBoost(attacker, originalAmount));
/*     */       }
/* 523 */     } else if (directEntity instanceof ProjectileEntity && !(source instanceof AbilityDamageSource)) {
/* 524 */       if (hasImbuingActive) {
/* 525 */         amount = (float)(amount + HakiHelper.getBasicBusoshokuHakiDamageBoost(attacker, originalAmount) * basicBusoReducer);
/*     */       }
/*     */       
/* 528 */       if (hasAdvancedBusoActive) {
/* 529 */         amount = (float)(amount + HakiHelper.getAdvancedBusoshokuHakiDamageBoost(attacker) * advancedBusoReducer);
/*     */         
/* 531 */         if (HakiHelper.hasBusoInternalDestructionActive(attacker)) {
/* 532 */           source.func_76348_h();
/*     */         }
/*     */       } 
/*     */       
/* 536 */       if (hasInfusionActive) {
/* 537 */         amount = (float)(amount + HaoshokuHakiInfusionAbility.getDamageBoost(attacker, originalAmount));
/*     */       }
/* 539 */     } else if (source instanceof AbilityDamageSource) {
/* 540 */       AbilityCore<? extends IAbility> ability = ((AbilityDamageSource)source).getAbilitySource();
/*     */       
/* 542 */       boolean hasHardeningBonus = (ability.getSourceHakiNature() == SourceHakiNature.HARDENING && hasHardeningActive);
/* 543 */       boolean hasImbuingBonus = (ability.getSourceHakiNature() == SourceHakiNature.IMBUING && hasImbuingActive);
/* 544 */       boolean hasAdvancedBusoBonus = ((ability.getSourceHakiNature() == SourceHakiNature.HARDENING || ability.getSourceHakiNature() == SourceHakiNature.IMBUING) && hasAdvancedBusoActive);
/* 545 */       boolean hasSpecialBonus = (ability.getSourceHakiNature() == SourceHakiNature.SPECIAL && (HakiHelper.hasHardeningActive(attacker, false, false) || HakiHelper.hasImbuingActive(attacker, false, false) || HakiHelper.hasAdvancedBusoActive(attacker)));
/* 546 */       boolean hasInfusionBonus = (ability.getSourceHakiNature() != SourceHakiNature.UNKNOWN && hasInfusionActive);
/*     */       
/* 548 */       if (hasSpecialBonus) {
/* 549 */         if (hasHardeningActive || hasImbuingActive) {
/* 550 */           amount = (float)(amount + HakiHelper.getBasicBusoshokuHakiDamageBoost(attacker, originalAmount) * basicBusoReducer);
/*     */         }
/*     */         
/* 553 */         if (hasAdvancedBusoActive) {
/* 554 */           amount = (float)(amount + HakiHelper.getAdvancedBusoshokuHakiDamageBoost(attacker) * advancedBusoReducer);
/*     */           
/* 556 */           if (HakiHelper.hasBusoInternalDestructionActive(attacker)) {
/* 557 */             ((AbilityDamageSource)source).setPiercing(1.0F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 562 */       if (hasHardeningBonus || hasImbuingBonus) {
/* 563 */         amount = (float)(amount + HakiHelper.getBasicBusoshokuHakiDamageBoost(attacker, originalAmount) * basicBusoReducer);
/*     */       }
/*     */       
/* 566 */       if (hasAdvancedBusoBonus) {
/* 567 */         amount = (float)(amount + HakiHelper.getAdvancedBusoshokuHakiDamageBoost(attacker) * advancedBusoReducer);
/*     */         
/* 569 */         if (HakiHelper.hasBusoInternalDestructionActive(attacker)) {
/* 570 */           ((AbilityDamageSource)source).setPiercing(1.0F);
/*     */         }
/*     */       } 
/*     */       
/* 574 */       if (hasInfusionBonus) {
/* 575 */         amount = (float)(amount + HaoshokuHakiInfusionAbility.getDamageBoost(attacker, originalAmount));
/*     */       }
/*     */     } 
/*     */     
/* 579 */     return amount;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onProjectileHit(ProjectileHitEvent event) {
/* 584 */     if (!(event.getProjectile()).field_70170_p.field_72995_K && CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event
/* 585 */       .getProjectile().func_234616_v_() instanceof LivingEntity && event.getHit().func_216346_c() == RayTraceResult.Type.ENTITY) {
/* 586 */       LivingEntity attacker = (LivingEntity)event.getProjectile().func_234616_v_();
/* 587 */       EntityRayTraceResult hitResult = (EntityRayTraceResult)event.getHit();
/*     */       
/* 589 */       if (hitResult.func_216348_a() instanceof LivingEntity) {
/* 590 */         LivingEntity target = (LivingEntity)hitResult.func_216348_a();
/* 591 */         boolean sameGroup = ModEntityPredicates.getFriendlyFactions(attacker).test(target);
/* 592 */         if (sameGroup) {
/* 593 */           event.setCanceled(true);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onSetFire(SetOnFireEvent event) {
/* 602 */     if (!(event.getEntity()).field_70170_p.field_72995_K && CommonConfig.INSTANCE.isFriendlyDamageDisabled()) {
/* 603 */       LivingEntity attacker = event.getAttacker();
/* 604 */       if (attacker == null) {
/*     */         return;
/*     */       }
/*     */       
/* 608 */       Entity target = event.getEntity();
/*     */       
/* 610 */       boolean sameGroup = ModEntityPredicates.getFriendlyFactions(attacker).test(target);
/*     */       
/* 612 */       if (sameGroup) {
/* 613 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onTargetChange(LivingEvent.LivingUpdateEvent event) {
/* 622 */     if (!(event.getEntityLiving()).field_70170_p.field_72995_K && event.getEntityLiving() instanceof MobEntity && ((MobEntity)event
/* 623 */       .getEntityLiving()).func_70638_az() != null && CommonConfig.INSTANCE.isFriendlyDamageDisabled()) {
/* 624 */       MobEntity mob = (MobEntity)event.getEntityLiving();
/* 625 */       LivingEntity target = ((MobEntity)event.getEntityLiving()).func_70638_az();
/*     */       
/* 627 */       boolean sameGroup = ModEntityPredicates.getFriendlyFactions(event.getEntityLiving()).test(target);
/*     */       
/* 629 */       if (sameGroup) {
/* 630 */         mob.func_70624_b(null);
/* 631 */         mob.func_230246_e_(null);
/* 632 */         mob.func_70604_c(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoinsWorld(EntityJoinWorldEvent event) {
/* 639 */     if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).field_72995_K && event.getWorld().func_234923_W_() == World.field_234918_g_) {
/* 640 */       if (((Boolean)CommonConfig.INSTANCE.forceSelection.get()).booleanValue()) {
/* 641 */         IEntityStats props = EntityStatsCapability.get((LivingEntity)event.getEntity());
/* 642 */         if (!props.hasRace() || !props.hasFaction() || !props.hasFightingStyle()) {
/* 643 */           boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
/* 644 */           boolean allowMinkRaceSelect = CommonConfig.INSTANCE.getAllowMinkRaceSelect();
/* 645 */           WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace, allowMinkRaceSelect), (PlayerEntity)event.getEntity());
/*     */         } 
/*     */       } 
/* 648 */       FactionHelper.validateFaction((PlayerEntity)event.getEntity());
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsChoose(SetPlayerDetailsEvent event) {
/* 654 */     FactionHelper.validateFaction(event.getPlayer());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPreHitTrigger(HitTriggerEvent.Pre event) {
/* 659 */     IAbility ability = event.getAbility();
/*     */     
/* 661 */     if (ability instanceof PunchAbility2 && event.getHitResult() == HitTriggerComponent.HitResult.HIT) {
/* 662 */       event.setBonusDamage(event.getBonusDamage() + ((PunchAbility2)ability).getPunchDamage());
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHitTriggerBlocked(HitTriggerBlockedEvent event) {
/* 668 */     IAbility abl = event.getAbility();
/*     */     
/* 670 */     if (abl instanceof PunchAbility2) {
/* 671 */       ((PunchAbility2)abl).increaseUses();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isDirectHit(DamageSource source) {
/* 679 */     Entity sourceEntity = source.func_76346_g();
/* 680 */     Entity directEntity = source.func_76364_f();
/*     */     
/* 682 */     boolean isNormalFist = (!(source instanceof AbilityDamageSource) && sourceEntity == directEntity);
/* 683 */     boolean isAbilityFist = (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).isFistDamage());
/* 684 */     boolean isNotExplosion = (!source.func_94541_c() && (!(source instanceof AbilityDamageSource) || (((AbilityDamageSource)source).getElement() != SourceElement.EXPLOSION && ((AbilityDamageSource)source).getElement() != SourceElement.SHOCKWAVE)));
/* 685 */     boolean isNotIgnored = (!(source instanceof ModDamageSource) || !((ModDamageSource)source).isIgnored());
/* 686 */     boolean isIndirect = (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).hasType(SourceType.INDIRECT));
/*     */     
/* 688 */     return ((isNormalFist || isAbilityFist) && isNotExplosion && isNotIgnored && !isIndirect);
/*     */   }
/*     */   
/*     */   private static boolean isDamageSourceBlockedByShield(DamageSource source, LivingEntity target) {
/* 692 */     Entity sourceEntity = source.func_76346_g();
/* 693 */     Entity entity = source.func_76364_f();
/* 694 */     boolean isPiercing = false;
/* 695 */     if (entity instanceof AbstractArrowEntity) {
/* 696 */       AbstractArrowEntity abstractarrowentity = (AbstractArrowEntity)entity;
/* 697 */       if (abstractarrowentity.func_213874_s() > 0) {
/* 698 */         isPiercing = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 703 */     boolean isPhysical = (source instanceof ModDamageSource && ((ModDamageSource)source).isPhysical() && source.func_76352_a() && !((ModDamageSource)source).hasType(SourceType.INDIRECT));
/* 704 */     boolean isDirect = isDirectHit(source);
/*     */     
/* 706 */     if (!source.func_76363_c() && target.func_184585_cz() && !isPiercing && (isPhysical || isDirect)) {
/* 707 */       Vector3d sourcePos = source.func_188404_v();
/* 708 */       if (sourcePos == null && sourceEntity != null) {
/* 709 */         sourcePos = sourceEntity.func_213303_ch();
/*     */       }
/* 711 */       if (sourcePos != null) {
/* 712 */         Vector3d vector3d = target.func_70676_i(1.0F);
/* 713 */         Vector3d vector3d1 = sourcePos.func_72444_a(target.func_213303_ch()).func_72432_b();
/* 714 */         vector3d1 = new Vector3d(vector3d1.field_72450_a, 0.0D, vector3d1.field_72449_c);
/* 715 */         if (vector3d1.func_72430_b(vector3d) < 0.0D) {
/* 716 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 721 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\FactionEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */