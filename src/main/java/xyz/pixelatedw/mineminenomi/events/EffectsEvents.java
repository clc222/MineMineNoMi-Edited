/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.settings.PointOfView;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.PotionEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.potion.Potions;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.entity.EntityMountEvent;
/*     */ import net.minecraftforge.event.entity.ProjectileImpactEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.PotionEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IBreakableEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IChangeSwingSpeedEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IDisableAbilitiesEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IIgnoreMilkEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IScreenShaderEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ITransmisibleByProximityEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ITransmisibleByTouchEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IVanishEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.EntityCarryEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.PotionAfterAddedEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingHurtEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.effects.GuardingEffect;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SAddScreenShaderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SRemoveScreenShaderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class EffectsEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common
/*     */   {
/*     */     public static void launchedCollision(LivingEntity entity) {
/*  79 */       if (entity.func_70644_a((Effect)ModEffects.LAUNCHED.get()) && (
/*  80 */         entity.field_70123_F || entity.field_70124_G)) {
/*  81 */         if (!entity.field_70170_p.func_180495_p(entity.func_233580_cy_().func_177977_b()).func_196958_f())
/*     */         {
/*  83 */           entity.func_70097_a(DamageSource.field_76379_h, 10.0F);
/*     */         }
/*  85 */         entity.func_195063_d((Effect)ModEffects.LAUNCHED.get());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onMountEffects(EntityMountEvent event) {
/*  92 */       if (event.getEntityBeingMounted() instanceof LivingEntity) {
/*  93 */         LivingEntity entity = (LivingEntity)event.getEntityBeingMounted();
/*  94 */         if (event.isMounting()) {
/*  95 */           tryLinkEffectsWithPassangers(entity);
/*     */         } else {
/*     */           
/*  98 */           tryUnlinkEffectsWithPassangers(entity);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onCarryEffects(EntityCarryEvent event) {
/* 105 */       if (event.isCarrying()) {
/* 106 */         tryLinkEffectsWithPassangers(event.getEntityLiving());
/*     */       } else {
/*     */         
/* 109 */         tryUnlinkEffectsWithPassangers(event.getEntityLiving());
/*     */       } 
/*     */     }
/*     */     
/*     */     private static void tryUnlinkEffectsWithPassangers(LivingEntity entity) {
/* 114 */       for (EffectInstance effect : entity.func_70651_bq()) {
/* 115 */         if (effect.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.api.effects.ILinkedEffect) {
/* 116 */           for (Entity passanger : entity.func_184188_bt()) {
/* 117 */             if (passanger != null && passanger.func_70089_S() && passanger instanceof LivingEntity) {
/* 118 */               ((LivingEntity)passanger).func_195063_d(effect.func_188419_a());
/*     */             }
/*     */           } 
/*     */           
/* 122 */           IEntityStats props = EntityStatsCapability.get(entity);
/* 123 */           if (props.isCarrying()) {
/* 124 */             props.getCarry().func_195063_d(effect.func_188419_a());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private static void tryLinkEffectsWithPassangers(LivingEntity entity) {
/* 131 */       for (EffectInstance effect : entity.func_70651_bq()) {
/* 132 */         boolean isTransmisibleByTouch = (effect.func_188419_a() instanceof ITransmisibleByTouchEffect && ((ITransmisibleByTouchEffect)effect.func_188419_a()).isTransmisibleByTouch());
/* 133 */         boolean isLinked = effect.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.api.effects.ILinkedEffect;
/*     */         
/* 135 */         if (isTransmisibleByTouch || isLinked) {
/* 136 */           EffectInstance newEffect = new EffectInstance(effect.func_188419_a(), effect.func_76459_b(), effect.func_76458_c(), effect.func_82720_e(), effect.func_188418_e(), effect.func_205348_f());
/*     */           
/* 138 */           for (Entity passanger : entity.func_184188_bt()) {
/* 139 */             if (passanger != null && passanger.func_70089_S() && passanger instanceof LivingEntity) {
/* 140 */               ((LivingEntity)passanger).func_195064_c(newEffect);
/*     */             }
/*     */           } 
/*     */           
/* 144 */           IEntityStats props = EntityStatsCapability.get(entity);
/* 145 */           if (props.isCarrying()) {
/* 146 */             props.getCarry().func_195064_c(newEffect);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void projectileImpactEvent(ProjectileImpactEvent.Throwable event) {
/* 154 */       if (event.getRayTraceResult().func_216346_c() == RayTraceResult.Type.ENTITY) {
/* 155 */         EntityRayTraceResult entityHit = (EntityRayTraceResult)event.getRayTraceResult();
/* 156 */         if (entityHit.func_216348_a() instanceof LivingEntity && event.getThrowable() instanceof PotionEntity) {
/* 157 */           ItemStack potion = ((PotionEntity)event.getThrowable()).func_184543_l();
/* 158 */           List<EffectInstance> effects = PotionUtils.func_185189_a(potion);
/*     */           
/* 160 */           if (effects.isEmpty()) {
/* 161 */             LivingEntity entity = (LivingEntity)entityHit.func_216348_a();
/* 162 */             entity.func_195064_c(new EffectInstance((Effect)ModEffects.WET.get(), 60, 0, false, false));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onItemConsumed(LivingEntityUseItemEvent.Finish event) {
/* 170 */       if (!event.getItem().func_190926_b() && event.getEntityLiving().func_70644_a((Effect)ModEffects.DEHYDRATION.get())) {
/* 171 */         Item item = event.getItem().func_77973_b();
/* 172 */         boolean isWaterBottle = (PotionUtils.func_185191_c(event.getItem()) == Potions.field_185230_b);
/* 173 */         if (item == Items.field_151117_aB || item == ModItems.COLA.get() || item == ModItems.ULTRA_COLA.get() || isWaterBottle) {
/* 174 */           EffectInstance inst = event.getEntityLiving().func_70660_b((Effect)ModEffects.DEHYDRATION.get());
/*     */           
/* 176 */           if (inst.func_76458_c() == 0) {
/* 177 */             event.getEntityLiving().func_195063_d((Effect)ModEffects.DEHYDRATION.get());
/*     */           } else {
/*     */             
/* 180 */             int newAmp = inst.func_76458_c() - 1;
/* 181 */             ((EffectInstanceMixin)inst).setAmplifier(newAmp);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPotionAdded(PotionAfterAddedEvent event) {
/* 189 */       LivingEntity entity = event.getEntityLiving();
/* 190 */       EffectInstance inst = event.getPotionEffect();
/*     */       
/* 192 */       if (inst.func_188419_a() instanceof IDisableAbilitiesEffect) {
/* 193 */         Predicate<IAbility> check = ((IDisableAbilitiesEffect)inst.func_188419_a()).getDisabledAbilities();
/* 194 */         AbilityHelper.disableAbilities(entity, inst.func_76459_b(), check);
/*     */       } 
/*     */       
/* 197 */       tryLinkEffectsWithPassangers(entity);
/*     */       
/* 199 */       trySpreadProximityEffect(entity, inst);
/*     */       
/* 201 */       applyScreenShader(entity, inst);
/*     */     }
/*     */     
/*     */     public static void applyScreenShader(LivingEntity entity, EffectInstance inst) {
/* 205 */       if (!entity.field_70170_p.field_72995_K && entity instanceof net.minecraft.entity.player.ServerPlayerEntity && inst.func_188419_a() instanceof IScreenShaderEffect) {
/* 206 */         WyNetwork.sendTo(new SAddScreenShaderPacket(((IScreenShaderEffect)inst.func_188419_a()).getScreenShader()), (PlayerEntity)entity);
/*     */       }
/*     */     }
/*     */     
/*     */     public static void trySpreadProximityEffect(LivingEntity entity, EffectInstance inst) {
/* 211 */       if (inst.func_188419_a() instanceof ITransmisibleByProximityEffect && ((ITransmisibleByProximityEffect)inst.func_188419_a()).isTransmisibleByProximity()) {
/* 212 */         float distance = ((ITransmisibleByProximityEffect)inst.func_188419_a()).poximityTransmisionDistance();
/* 213 */         List<LivingEntity> targets = TargetHelper.getEntitiesInArea(entity, distance, TargetsPredicate.EVERYTHING, new Class[] { LivingEntity.class });
/* 214 */         for (LivingEntity target : targets) {
/* 215 */           if (!target.func_70644_a(inst.func_188419_a())) {
/* 216 */             EffectInstance newEffect = new EffectInstance(inst.func_188419_a().getEffect(), inst.func_76459_b(), inst.func_76458_c(), inst.func_82720_e(), inst.func_188418_e(), inst.func_205348_f());
/* 217 */             target.func_195064_c(newEffect);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPotionExpires(PotionEvent.PotionExpiryEvent event) {
/* 225 */       LivingEntity entity = event.getEntityLiving();
/* 226 */       EffectInstance effect = event.getPotionEffect();
/*     */       
/* 228 */       if (effect == null) {
/*     */         return;
/*     */       }
/*     */       
/* 232 */       if (effect.func_188419_a() == ModEffects.DRUNK.get()) {
/* 233 */         IEntityStats props = EntityStatsCapability.get(entity);
/* 234 */         entity.func_70097_a((DamageSource)ModDamageSource.STORED_DAMAGE, props.getStoredDamage());
/* 235 */         props.setStoredDamage(0.0F);
/*     */       } 
/*     */       
/* 238 */       if (effect.func_188419_a() instanceof IDisableAbilitiesEffect) {
/* 239 */         Predicate<IAbility> check = ((IDisableAbilitiesEffect)effect.func_188419_a()).getDisabledAbilities();
/* 240 */         AbilityHelper.enableAbilities(entity, check);
/*     */       } 
/*     */       
/* 243 */       tryUnlinkEffectsWithPassangers(entity);
/*     */       
/* 245 */       removeScreenShader(entity, effect);
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPotionRemoved(PotionEvent.PotionRemoveEvent event) {
/* 250 */       LivingEntity entity = event.getEntityLiving();
/* 251 */       EffectInstance effect = event.getPotionEffect();
/*     */       
/* 253 */       if (entity.func_184600_cs() == null) {
/*     */         return;
/*     */       }
/*     */       
/* 257 */       boolean isMilkBucket = (entity.func_184586_b(entity.func_184600_cs()).func_77973_b() == Items.field_151117_aB);
/*     */       
/* 259 */       if (isMilkBucket && 
/* 260 */         event.getPotion() instanceof IIgnoreMilkEffect && !((IIgnoreMilkEffect)event.getPotion()).isRemoveable()) {
/* 261 */         event.setCanceled(true);
/*     */       }
/*     */ 
/*     */       
/* 265 */       if (effect == null) {
/*     */         return;
/*     */       }
/*     */       
/* 269 */       if (effect.func_188419_a() instanceof IDisableAbilitiesEffect) {
/* 270 */         Predicate<IAbility> check = ((IDisableAbilitiesEffect)effect.func_188419_a()).getDisabledAbilities();
/* 271 */         AbilityHelper.enableAbilities(entity, check);
/*     */       } 
/*     */       
/* 274 */       tryUnlinkEffectsWithPassangers(entity);
/*     */       
/* 276 */       removeScreenShader(entity, effect);
/*     */     }
/*     */     
/*     */     public static void removeScreenShader(LivingEntity entity, EffectInstance inst) {
/* 280 */       if (!entity.field_70170_p.field_72995_K && entity instanceof net.minecraft.entity.player.ServerPlayerEntity && inst.func_188419_a() instanceof IScreenShaderEffect) {
/* 281 */         WyNetwork.sendTo(new SRemoveScreenShaderPacket(((IScreenShaderEffect)inst.func_188419_a()).getScreenShader()), (PlayerEntity)entity);
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/* 287 */       PlayerEntity player = event.getPlayer();
/* 288 */       for (EffectInstance effectInstance : player.func_70651_bq()) {
/* 289 */         if (effectInstance.func_188419_a() instanceof IChangeSwingSpeedEffect) {
/* 290 */           float newSwingSpeed = ((IChangeSwingSpeedEffect)effectInstance.func_188419_a()).swingSpeedModifier(effectInstance.func_76459_b(), effectInstance.func_76458_c());
/* 291 */           event.setNewSpeed(event.getOriginalSpeed() / newSwingSpeed);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static float applyBreakableEffectDamage(Entity sourceEntity, LivingEntity target, float amount, List<Effect> breakableEffects) {
/* 297 */       float highestMultiplier = 1.0F;
/*     */       
/* 299 */       if (sourceEntity != null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 307 */         for (Effect effect : breakableEffects) {
/* 308 */           if (effect != null && effect instanceof IBreakableEffect && target.func_70644_a(effect)) {
/* 309 */             target.func_195063_d(effect);
/*     */             
/* 311 */             float currentMultiplier = ((IBreakableEffect)effect.getEffect()).getBreakingMultiplier();
/*     */             
/* 313 */             if (currentMultiplier > highestMultiplier) {
/* 314 */               highestMultiplier = currentMultiplier;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 320 */       return amount * highestMultiplier;
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityHurt(WyLivingHurtEvent event) {
/* 325 */       LivingEntity entity = event.getEntityLiving();
/*     */       
/* 327 */       for (EffectInstance effectInstance : entity.func_70651_bq()) {
/* 328 */         if (effectInstance.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.FragileEffect) {
/* 329 */           event.setAmount(event.getAmount() * (1.0F + 0.16F * (effectInstance.func_76458_c() + 1)));
/*     */         }
/*     */         
/* 332 */         if (effectInstance.func_188419_a() instanceof GuardingEffect) {
/* 333 */           GuardingEffect instance = (GuardingEffect)effectInstance.func_188419_a();
/*     */           
/* 335 */           if (instance.useOnlySources && !instance.acceptableSources.contains(event.getSource().func_76355_l())) {
/*     */             return;
/*     */           }
/*     */           
/* 339 */           if (event.getSource().func_151517_h()) {
/*     */             return;
/*     */           }
/*     */           
/* 343 */           if (instance.reduceSpeedAfterHit) {
/* 344 */             entity.getClass(); entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 20 / 2, 1, false, false));
/*     */           } 
/*     */           
/* 347 */           float power = (effectInstance.func_76458_c() + 1.0F) * 2.5F;
/*     */           
/* 349 */           power *= event.getSource().func_76363_c() ? 0.8F : 1.0F;
/*     */           
/* 351 */           if (event.getAmount() < power) {
/* 352 */             AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 0.0D, 0.0D);
/*     */             
/* 354 */             event.setAmount(0.0F);
/* 355 */             event.setCanceled(true);
/*     */           } else {
/* 357 */             event.setAmount(event.getAmount() - power);
/*     */           } 
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
/*     */           return;
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
/* 382 */       Iterator<EffectInstance> iter = entity.func_70651_bq().iterator();
/* 383 */       while (iter.hasNext()) {
/* 384 */         EffectInstance effectInstance = iter.next();
/*     */         
/* 386 */         if (effectInstance.func_188419_a() == ModEffects.OIL_COVERED.get() && event.getSource().func_76347_k()) {
/* 387 */           ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 4.0F);
/*     */           
/* 389 */           explosion.setStaticDamage(40.0F);
/* 390 */           explosion.setFireAfterExplosion(true);
/* 391 */           explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 392 */           explosion.doExplosion();
/*     */           
/* 394 */           iter.remove();
/*     */           
/* 396 */           entity.func_184102_h().func_184103_al().func_148543_a(null, (entity.func_213303_ch()).field_72450_a, (entity.func_213303_ch()).field_72448_b, (entity.func_213303_ch()).field_72449_c, 100.0D, entity.func_130014_f_().func_234923_W_(), (IPacket)new SRemoveEntityEffectPacket(entity.func_145782_y(), (Effect)ModEffects.OIL_COVERED.get()));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 407 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 409 */       ClientPlayerEntity clientPlayer = mc.field_71439_g;
/*     */       
/* 411 */       boolean isAuraHakiActive = false;
/*     */       
/* 413 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)clientPlayer);
/*     */       
/* 415 */       if (abilityProps != null) {
/* 416 */         KenbunshokuHakiAuraAbility auraHaki = (KenbunshokuHakiAuraAbility)abilityProps.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
/*     */         
/* 418 */         isAuraHakiActive = (auraHaki != null && auraHaki.isContinuous());
/*     */       } 
/*     */       
/* 421 */       LivingEntity entity = event.getEntity();
/*     */       
/* 423 */       Iterator<EffectInstance> iter = entity.func_70651_bq().iterator();
/*     */       
/* 425 */       while (iter.hasNext()) {
/* 426 */         EffectInstance eff = iter.next();
/*     */         
/* 428 */         if (eff.func_76459_b() <= 0) {
/* 429 */           iter.remove();
/*     */         }
/*     */         
/* 432 */         if (eff.func_188419_a() instanceof IVanishEffect && ((IVanishEffect)eff.func_188419_a()).isVanished(entity, eff.func_76459_b(), eff.func_76458_c()) && (!isAuraHakiActive || entity == clientPlayer)) {
/* 433 */           event.setCanceled(true);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onMouseClicked(InputEvent.ClickInputEvent event) {
/* 442 */       if (event.getHand() == Hand.MAIN_HAND) {
/*     */         
/* 444 */         Minecraft mc = Minecraft.func_71410_x();
/*     */         
/* 446 */         ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*     */         
/* 448 */         clientPlayerEntity.func_70651_bq().stream().map(EffectInstance::func_188419_a).filter(IBindHandsEffect.class::isInstance).filter(eff -> ((IBindHandsEffect)eff).isBlockingSwings()).forEach(eff -> {
/*     */               event.setCanceled(true);
/*     */               event.setSwingHand(false);
/*     */             });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHandRendering(RenderHandEvent event) {
/* 459 */       if (event.getHand() != Hand.MAIN_HAND) {
/*     */         return;
/*     */       }
/*     */       
/* 463 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 465 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*     */       
/* 467 */       if (clientPlayerEntity.func_70644_a((Effect)ModEffects.NO_HANDS.get())) {
/* 468 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onShocked(EntityViewRenderEvent.CameraSetup event) {
/* 475 */       Minecraft mc = Minecraft.func_71410_x();
/*     */       
/* 477 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 478 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 480 */       if (props != null && props.hasCameraPinned()) {
/* 481 */         props.tickCameraPin();
/* 482 */         float pitch = props.getCameraRotations()[0];
/* 483 */         float yaw = props.getCameraRotations()[1];
/*     */         
/* 485 */         if (props.hasCameraPitchClamped()) {
/* 486 */           pitch = MathHelper.func_76131_a(((PlayerEntity)clientPlayerEntity).field_70125_A % 360.0F, props.getCameraInitialPitch() - props.getCameraMaxPitch(), props.getCameraInitialPitch() + props.getCameraMaxPitch());
/*     */         }
/*     */         
/* 489 */         if (props.hasCameraYawClamped()) {
/* 490 */           yaw = MathHelper.func_76131_a(((PlayerEntity)clientPlayerEntity).field_70177_z % 360.0F, props.getCameraInitialYaw() - props.getCameraMaxYaw(), props.getCameraInitialYaw() + props.getCameraMaxYaw());
/*     */         }
/*     */         
/* 493 */         ((PlayerEntity)clientPlayerEntity).field_70177_z = yaw;
/* 494 */         ((PlayerEntity)clientPlayerEntity).field_70126_B = yaw;
/* 495 */         ((PlayerEntity)clientPlayerEntity).field_70125_A = pitch;
/* 496 */         ((PlayerEntity)clientPlayerEntity).field_70127_C = pitch;
/* 497 */         if (mc.field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON) {
/* 498 */           event.setYaw(yaw);
/* 499 */           event.setPitch(pitch);
/*     */         } 
/*     */       } 
/*     */       
/* 503 */       if (!clientPlayerEntity.func_70644_a((Effect)ModEffects.DIZZY.get())) {
/*     */         return;
/*     */       }
/*     */       
/* 507 */       if (clientPlayerEntity.func_70660_b((Effect)ModEffects.DIZZY.get()).func_76459_b() <= 0) {
/*     */         
/* 509 */         clientPlayerEntity.func_195063_d((Effect)ModEffects.DIZZY.get());
/*     */         
/*     */         return;
/*     */       } 
/* 513 */       if (mc.field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON)
/*     */       {
/* 515 */         event.setRoll((float)Math.sin((((PlayerEntity)clientPlayerEntity).field_70173_aa % 100)));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\EffectsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */