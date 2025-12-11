/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class PteranodonFlightAbility extends PropelledFlightAbility {
/*  20 */   public static final AbilityCore<PteranodonFlightAbility> INSTANCE = (new AbilityCore.Builder("Pteranodon Flight", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, PteranodonFlightAbility::new))
/*  21 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  22 */       }).build();
/*     */   
/*  24 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PTERA_ASSAULT.get(), new MorphInfo[] { (MorphInfo)ModMorphs.PTERA_FLY.get() });
/*     */   
/*     */   public PteranodonFlightAbility(AbilityCore<PteranodonFlightAbility> core) {
/*  27 */     super(core);
/*     */     
/*  29 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  31 */     addDuringPassiveEvent(this::onDuringPassive);
/*     */   }
/*     */   
/*     */   private void onDuringPassive(LivingEntity entity) {
/*  35 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/*  39 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/*  41 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  43 */     PteranodonFlyPointAbility pteranodonFlyPointAbility = (PteranodonFlyPointAbility)abilityDataProps.getEquippedAbility(PteranodonFlyPointAbility.INSTANCE);
/*     */     
/*  45 */     double difference = DevilFruitHelper.getDifferenceToFloor((Entity)player);
/*     */     
/*  47 */     boolean isPteranodonFlyPointActive = (pteranodonFlyPointAbility != null && ((ContinuousComponent)pteranodonFlyPointAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous());
/*     */     
/*  49 */     if (isPteranodonFlyPointActive) {
/*  50 */       if (((IPlayerAbilities)player.field_71075_bZ).hasCustomFlight() && !player.field_71075_bZ.field_75100_b) {
/*  51 */         player.field_71075_bZ.field_75100_b = true;
/*     */       }
/*     */       
/*  54 */       if (difference < 2.0D) {
/*  55 */         AbilityHelper.setDeltaMovement((Entity)player, player.func_213322_ci().func_72441_c(0.0D, 1.0D, 0.0D).func_216372_d(1.0D, 0.25D, 1.0D));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxSpeed(LivingEntity entity) {
/*  62 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  64 */     PteranodonAssaultPointAbility pteranodonAssaultPointAbility = (PteranodonAssaultPointAbility)abilityDataProps.getEquippedAbility(PteranodonAssaultPointAbility.INSTANCE);
/*  65 */     PteranodonFlyPointAbility pteranodonFlyPointAbility = (PteranodonFlyPointAbility)abilityDataProps.getEquippedAbility(PteranodonFlyPointAbility.INSTANCE);
/*     */     
/*  67 */     if (pteranodonAssaultPointAbility != null && ((ContinuousComponent)pteranodonAssaultPointAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous())
/*  68 */       return entity.func_70051_ag() ? 1.05F : 0.75F; 
/*  69 */     if (pteranodonFlyPointAbility != null && ((ContinuousComponent)pteranodonFlyPointAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/*  70 */       return entity.func_70051_ag() ? 2.1F : 1.5F;
/*     */     }
/*     */     
/*  73 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getAcceleration(LivingEntity entity) {
/*  78 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  80 */     PteranodonAssaultPointAbility pteranodonAssaultPointAbility = (PteranodonAssaultPointAbility)abilityDataProps.getEquippedAbility(PteranodonAssaultPointAbility.INSTANCE);
/*  81 */     PteranodonFlyPointAbility pteranodonFlyPointAbility = (PteranodonFlyPointAbility)abilityDataProps.getEquippedAbility(PteranodonFlyPointAbility.INSTANCE);
/*     */     
/*  83 */     if (pteranodonAssaultPointAbility != null && ((ContinuousComponent)pteranodonAssaultPointAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous())
/*  84 */       return entity.func_70051_ag() ? 0.006F : 0.003F; 
/*  85 */     if (pteranodonFlyPointAbility != null && ((ContinuousComponent)pteranodonFlyPointAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/*  86 */       return entity.func_70051_ag() ? 0.012F : 0.006F;
/*     */     }
/*     */     
/*  89 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getHeightDifference(LivingEntity entity) {
/*  94 */     return 128;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSpeed(LivingEntity entity) {
/*  99 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 101 */     TempuraudonAbility tempuraudonAbility = (TempuraudonAbility)abilityDataProps.getEquippedAbility(TempuraudonAbility.INSTANCE);
/* 102 */     TankyudonAbility tankyudonAbility = (TankyudonAbility)abilityDataProps.getEquippedAbility(TankyudonAbility.INSTANCE);
/* 103 */     BeakGrabAbility beakGrabAbility = (BeakGrabAbility)abilityDataProps.getEquippedAbility(BeakGrabAbility.INSTANCE);
/*     */     
/* 105 */     if (tempuraudonAbility != null && ((ChargeComponent)tempuraudonAbility.getComponent(ModAbilityKeys.CHARGE).get()).isCharging())
/* 106 */       return 0.0F; 
/* 107 */     if (tankyudonAbility != null && ((ContinuousComponent)tankyudonAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/* 108 */       entity.field_191988_bg = 0.98F;
/*     */       
/* 110 */       return ++this.speed;
/* 111 */     }  if (beakGrabAbility != null && ((ContinuousComponent)beakGrabAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/* 112 */       entity.field_191988_bg = 0.98F;
/*     */       
/* 114 */       return ++this.speed;
/*     */     } 
/*     */     
/* 117 */     return this.speed;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */