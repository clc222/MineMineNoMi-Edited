/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import com.google.common.base.Strings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SFlightValuePacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public abstract class FlyAbility extends PassiveAbility2 {
/*  32 */   private static final ITextComponent ELEMENTAL_FLIGHT_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.elemental_flight", "Elemental Flight"));
/*  33 */   private static final ResourceLocation ELEMENTAL_FLIGHT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/special_fly.png");
/*     */   
/*     */   private boolean isFlying;
/*     */   
/*     */   private boolean previouslyFlying = false;
/*     */   private boolean previouslyMayFly = false;
/*  39 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public FlyAbility(AbilityCore ability) {
/*  42 */     super(ability);
/*     */     
/*  44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*     */     
/*  46 */     setDisplayName(ELEMENTAL_FLIGHT_NAME);
/*  47 */     setDisplayIcon(ELEMENTAL_FLIGHT_ICON);
/*     */     
/*  49 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*  50 */     addEquipEvent(this::onEquipEvent);
/*  51 */     addRemoveEvent(this::onRemoveEvent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(LivingEntity entity) {
/*  56 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/*  60 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/*  62 */     if (AbilityHelper.isInCreativeOrSpectator((LivingEntity)player)) {
/*  63 */       this.isFlying = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  68 */     if (!AbilityHelper.canUseMomentumAbilities((LivingEntity)player)) {
/*  69 */       player.field_71075_bZ.field_75101_c = false;
/*  70 */       player.field_71075_bZ.field_75100_b = false;
/*     */       
/*  72 */       this.isFlying = false;
/*     */       
/*  74 */       if (player instanceof ServerPlayerEntity) {
/*  75 */         ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  81 */     boolean canFly = canUse((LivingEntity)player).isSuccess();
/*  82 */     if (!canFly && player.field_71075_bZ.field_75101_c) {
/*  83 */       player.field_71075_bZ.field_75101_c = false;
/*  84 */       player.field_71075_bZ.field_75100_b = false;
/*     */       
/*  86 */       this.isFlying = false;
/*     */       
/*  88 */       if (player instanceof ServerPlayerEntity) {
/*  89 */         ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */       }
/*     */     } 
/*     */     
/*  93 */     if (!canFly || player.func_233570_aj_()) {
/*  94 */       this.isFlying = false;
/*     */     }
/*     */     
/*  97 */     if (!canFly) {
/*  98 */       this.animationComponent.stop(entity);
/*     */     }
/*     */     
/* 101 */     super.tick(entity);
/*     */   }
/*     */   
/*     */   public void duringPassiveEvent(LivingEntity entity) {
/* 105 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 111 */     this.isFlying = player.field_71075_bZ.field_75100_b;
/*     */     
/* 113 */     boolean flight = (!player.func_70644_a((Effect)ModEffects.ABILITY_OFF.get()) && !AbilityHelper.isWeakenedByKairosekiOrWater((LivingEntity)player));
/*     */     
/* 115 */     if (!player.field_70170_p.field_72995_K) {
/* 116 */       WyNetwork.sendTo(new SFlightValuePacket(flight), player);
/*     */       
/* 118 */       player.field_71075_bZ.field_75101_c = flight;
/*     */       
/* 120 */       boolean isNormal = Strings.isNullOrEmpty(DevilFruitCapability.get((LivingEntity)player).getZoanPoint());
/*     */       
/* 122 */       if (this.isFlying && isNormal) {
/* 123 */         this.animationComponent.start(entity, ModAnimations.SPECIAL_FLY);
/* 124 */         WyHelper.spawnParticleEffect(getParticleEffects().get(), (Entity)player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_());
/*     */       } else {
/*     */         
/* 127 */         this.animationComponent.stop(entity);
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     if (this.isFlying) {
/* 132 */       double maxDifference = getHeightDifference(player);
/*     */       
/* 134 */       float speedMultiplier = getSpeedMultiplier(player);
/*     */       
/* 136 */       AbilityHelper.setDeltaMovement((Entity)player, player.func_213322_ci().func_216372_d(speedMultiplier, (speedMultiplier - 0.25F), speedMultiplier));
/*     */       
/* 138 */       if (!player.field_70170_p.field_72995_K && maxDifference != 0.0D && !CommonConfig.INSTANCE.hasYRestrictionRemoved()) {
/* 139 */         boolean heightCheck = DevilFruitHelper.flyingAtMaxHeight((LivingEntity)player, maxDifference);
/*     */         
/* 141 */         DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, heightCheck ? 256 : ((int)player.func_226278_cu_() - 1));
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     if (!flight) {
/* 146 */       player.field_71075_bZ.field_75100_b = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void onEquipEvent(LivingEntity entity, IAbility ability) {
/* 151 */     if (entity instanceof PlayerEntity && !AbilityHelper.isInCreativeOrSpectator(entity)) {
/* 152 */       this.previouslyFlying = ((PlayerEntity)entity).field_71075_bZ.field_75100_b;
/* 153 */       this.previouslyMayFly = ((PlayerEntity)entity).field_71075_bZ.field_75101_c;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onRemoveEvent(LivingEntity entity, IAbility ability) {
/* 158 */     if (entity instanceof PlayerEntity && !AbilityHelper.isInCreativeOrSpectator(entity)) {
/* 159 */       ((PlayerEntity)entity).field_71075_bZ.field_75100_b = this.previouslyFlying;
/* 160 */       ((PlayerEntity)entity).field_71075_bZ.field_75101_c = this.previouslyMayFly;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract Supplier<ParticleEffect<?>> getParticleEffects();
/*     */   
/*     */   public abstract int getHeightDifference(PlayerEntity paramPlayerEntity);
/*     */   
/*     */   public abstract float getSpeedMultiplier(PlayerEntity paramPlayerEntity);
/*     */   
/*     */   public AbilityUseResult canUse(LivingEntity entity) {
/* 172 */     if (AbilityHelper.isInCreativeOrSpectator(entity)) {
/* 173 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 176 */     if (entity.func_110148_a((Attribute)ModAttributes.JUMP_HEIGHT.get()).func_111126_e() < 0.0D) {
/* 177 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 180 */     return super.canUse(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\FlyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */