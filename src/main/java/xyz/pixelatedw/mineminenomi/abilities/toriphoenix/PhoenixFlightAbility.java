/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.IPlayerAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class PhoenixFlightAbility extends PropelledFlightAbility {
/*  21 */   public static final AbilityCore<PhoenixFlightAbility> INSTANCE = (new AbilityCore.Builder("Phoenix Flight", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, PhoenixFlightAbility::new))
/*  22 */     .build();
/*     */   
/*     */   public PhoenixFlightAbility(AbilityCore<PhoenixFlightAbility> core) {
/*  25 */     super(core);
/*     */     
/*  27 */     addCanUseCheck(this::canFly);
/*     */     
/*  29 */     addDuringPassiveEvent(this::onDuringPassive);
/*     */   }
/*     */   
/*     */   private void onDuringPassive(LivingEntity entity) {
/*  33 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/*  37 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/*  39 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  41 */     PhoenixFlyPointAbility phoenixFlyPointAbility = (PhoenixFlyPointAbility)abilityDataProps.getEquippedAbility(PhoenixFlyPointAbility.INSTANCE);
/*     */     
/*  43 */     double difference = DevilFruitHelper.getDifferenceToFloor((Entity)player);
/*     */     
/*  45 */     boolean isPhoenixFlyPointActive = (phoenixFlyPointAbility != null && ((ContinuousComponent)phoenixFlyPointAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous());
/*     */     
/*  47 */     if (isPhoenixFlyPointActive) {
/*  48 */       if (((IPlayerAbilities)player.field_71075_bZ).hasCustomFlight() && !player.field_71075_bZ.field_75100_b) {
/*  49 */         player.field_71075_bZ.field_75100_b = true;
/*     */       }
/*     */       
/*  52 */       if (difference < 2.0D) {
/*  53 */         AbilityHelper.setDeltaMovement((Entity)player, player.func_213322_ci().func_72441_c(0.0D, 1.0D, 0.0D).func_216372_d(1.0D, 0.25D, 1.0D));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxSpeed(LivingEntity entity) {
/*  60 */     float maxSpeed = 0.0F;
/*     */     
/*  62 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  64 */     PhoenixAssaultPointAbility phoenixAssaultPointAbility = (PhoenixAssaultPointAbility)abilityDataProps.getEquippedAbility(PhoenixAssaultPointAbility.INSTANCE);
/*  65 */     PhoenixFlyPointAbility phoenixFlyPointAbility = (PhoenixFlyPointAbility)abilityDataProps.getEquippedAbility(PhoenixFlyPointAbility.INSTANCE);
/*     */     
/*  67 */     if (phoenixAssaultPointAbility != null && phoenixAssaultPointAbility.isContinuous()) {
/*  68 */       maxSpeed = entity.func_70051_ag() ? 1.0F : 0.625F;
/*  69 */     } else if (phoenixFlyPointAbility != null && phoenixFlyPointAbility.isContinuous()) {
/*  70 */       maxSpeed = entity.func_70051_ag() ? 2.0F : 1.25F;
/*     */     } 
/*     */     
/*  73 */     EffectInstance fatigueEffectInstance = entity.func_70660_b((Effect)ModEffects.FATIGUE_EFFECT.get());
/*     */     
/*  75 */     if (fatigueEffectInstance != null) {
/*  76 */       maxSpeed /= 1.0F + Math.min(fatigueEffectInstance.func_76458_c(), 3.0F);
/*     */     }
/*     */     
/*  79 */     return maxSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getAcceleration(LivingEntity entity) {
/*  84 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  86 */     PhoenixAssaultPointAbility phoenixAssaultPointAbility = (PhoenixAssaultPointAbility)abilityDataProps.getEquippedAbility(PhoenixAssaultPointAbility.INSTANCE);
/*  87 */     PhoenixFlyPointAbility phoenixFlyPointAbility = (PhoenixFlyPointAbility)abilityDataProps.getEquippedAbility(PhoenixFlyPointAbility.INSTANCE);
/*     */     
/*  89 */     if (phoenixAssaultPointAbility != null && phoenixAssaultPointAbility.isContinuous())
/*  90 */       return entity.func_70051_ag() ? 0.007F : 0.0035F; 
/*  91 */     if (phoenixFlyPointAbility != null && phoenixFlyPointAbility.isContinuous()) {
/*  92 */       return entity.func_70051_ag() ? 0.014F : 0.007F;
/*     */     }
/*     */     
/*  95 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getHeightDifference(LivingEntity entity) {
/* 100 */     return 128;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSpeed(LivingEntity entity) {
/* 105 */     FujiazamiAbility fujiazamiAbility = (FujiazamiAbility)AbilityDataCapability.get(entity).getEquippedAbility(FujiazamiAbility.INSTANCE);
/*     */     
/* 107 */     if (fujiazamiAbility != null && fujiazamiAbility.isContinuous()) {
/* 108 */       return 0.0F;
/*     */     }
/*     */     
/* 111 */     EffectInstance fatigueEffectInstance = entity.func_70660_b((Effect)ModEffects.FATIGUE_EFFECT.get());
/*     */     
/* 113 */     if (fatigueEffectInstance != null) {
/* 114 */       return this.speed * 1.0F - Math.min(fatigueEffectInstance.func_76458_c(), 3.0F);
/*     */     }
/*     */     
/* 117 */     return this.speed;
/*     */   }
/*     */   
/*     */   private AbilityUseResult canFly(LivingEntity entity, IAbility ability) {
/* 121 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 123 */     PhoenixAssaultPointAbility phoenixAssaultPointAbility = (PhoenixAssaultPointAbility)abilityDataProps.getEquippedAbility(PhoenixAssaultPointAbility.INSTANCE);
/* 124 */     PhoenixFlyPointAbility phoenixFlyPointAbility = (PhoenixFlyPointAbility)abilityDataProps.getEquippedAbility(PhoenixFlyPointAbility.INSTANCE);
/* 125 */     TenseiNoSoenAbility tenseiNoSoenAbility = (TenseiNoSoenAbility)abilityDataProps.getEquippedAbility(TenseiNoSoenAbility.INSTANCE);
/*     */     
/* 127 */     boolean isTenseiNoSoenActive = (tenseiNoSoenAbility != null && tenseiNoSoenAbility.isContinuous());
/*     */     
/* 129 */     if ((phoenixAssaultPointAbility != null && phoenixAssaultPointAbility.isContinuous()) || (phoenixFlyPointAbility != null && phoenixFlyPointAbility.isContinuous() && !isTenseiNoSoenActive)) {
/* 130 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 133 */     return AbilityUseResult.fail(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */