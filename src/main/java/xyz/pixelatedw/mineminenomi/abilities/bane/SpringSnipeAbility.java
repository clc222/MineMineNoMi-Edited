/*     */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ 
/*     */ public class SpringSnipeAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spring_snipe", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Turning the user's forelegs into springs, they can launch themselves directly at the opponent", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 10;
/*     */   private static final int COOLDOWN = 280;
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final float DAMAGE = 50.0F;
/*  39 */   public static final AbilityCore<SpringSnipeAbility> INSTANCE = (new AbilityCore.Builder("Spring Snipe", AbilityCategory.DEVIL_FRUITS, SpringSnipeAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(280.0F), ChargeComponent.getTooltip(10.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(50.0F)
/*  42 */       }).setSourceType(new SourceType[] { SourceType.PHYSICAL
/*  43 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  44 */     .build();
/*     */   
/*  46 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuousEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  48 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  49 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  51 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*     */   
/*     */   public SpringSnipeAbility(AbilityCore<SpringSnipeAbility> core) {
/*  54 */     super(core);
/*     */     
/*  56 */     this.isNew = true;
/*  57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.continuousComponent });
/*     */     
/*  59 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  60 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.chargeComponent.startCharging(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  70 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  72 */     SpringHopperAbility springHopper = (SpringHopperAbility)abilityDataProps.getEquippedAbility(SpringHopperAbility.INSTANCE);
/*     */     
/*  74 */     if (springHopper == null || springHopper.canUse(entity).isFail()) {
/*     */       return;
/*     */     }
/*     */     
/*  78 */     springHopper.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> {
/*     */           if (!comp.isContinuous()) {
/*     */             comp.startContinuity(entity);
/*     */           }
/*     */         });
/*     */     
/*  84 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  88 */     AbilityHelper.setDeltaMovement((Entity)entity, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  92 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*  93 */     this.animationComponent.start(entity, ModAnimations.SHOOT_SELF_FORWARD);
/*     */   }
/*     */   
/*     */   private void startContinuousEvent(LivingEntity entity, IAbility ability) {
/*  97 */     Vector3d speed = entity.func_70040_Z().func_216372_d(6.0D, 6.0D, 6.0D);
/*  98 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 102 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.6F, 1.25F);
/* 103 */     for (LivingEntity target : targets) {
/* 104 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 105 */         this.dealDamageComponent.hurtTarget(entity, target, 50.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 111 */     this.animationComponent.stop(entity);
/* 112 */     this.cooldownComponent.startCooldown(entity, 280.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringSnipeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */