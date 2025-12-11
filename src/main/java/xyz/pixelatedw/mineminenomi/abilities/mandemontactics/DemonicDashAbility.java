/*     */ package xyz.pixelatedw.mineminenomi.abilities.mandemontactics;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ 
/*     */ 
/*     */ public class DemonicDashAbility
/*     */   extends Ability
/*     */ {
/*     */   private static final int COOLDOWN = 160;
/*     */   private static final float RANGE = 1.6F;
/*     */   private static final float DAMAGE = 30.0F;
/*     */   private static final int CHARGE_TIME = 20;
/*  35 */   public static final TargetsPredicate TARGET_TEST = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*     */   
/*  37 */   public static final AbilityCore<DemonicDashAbility> INSTANCE = (new AbilityCore.Builder("Demonic Dash", AbilityCategory.STYLE, DemonicDashAbility::new))
/*  38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ChargeComponent.getTooltip(20.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(30.0F)
/*  39 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  40 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  41 */       }).build();
/*     */   
/*  43 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  44 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::endChargeEvent);
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::tickContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  47 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  48 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   @Nullable
/*     */   private LivingEntity target;
/*     */   
/*     */   public DemonicDashAbility(AbilityCore<DemonicDashAbility> core) {
/*  54 */     super(core);
/*     */     
/*  56 */     this.isNew = true;
/*  57 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.chargeComponent });
/*     */     
/*  59 */     addCanUseCheck(AbilityHelper::requiresTonfaWeapon);
/*     */     
/*  61 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  65 */     this.chargeComponent.startCharging(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  69 */     this.animationComponent.start(entity, ModAnimations.DEMONIC_DASH);
/*     */   }
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*     */     Vector3d speed;
/*  73 */     this.hitTrackerComponent.clearHits();
/*     */ 
/*     */     
/*  76 */     if (this.target == null) {
/*  77 */       speed = entity.func_70040_Z().func_216372_d(5.5D, 0.0D, 5.5D);
/*     */     } else {
/*     */       
/*  80 */       speed = entity.func_213303_ch().func_178788_d(this.target.func_213303_ch()).func_72432_b().func_216372_d(5.5D, 2.5D, 5.5D);
/*     */     } 
/*  82 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.15D, speed.field_72449_c);
/*     */     
/*  84 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  88 */     if (entity.func_70089_S()) {
/*  89 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.6F, 1.5F);
/*  90 */       for (LivingEntity target : targets) {
/*  91 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/*  92 */           this.dealDamageComponent.hurtTarget(entity, target, 30.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  99 */     this.target = null;
/* 100 */     this.animationComponent.stop(entity);
/* 101 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*     */   }
/*     */   
/*     */   public void setTarget(LivingEntity entity) {
/* 105 */     this.target = entity;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mandemontactics\DemonicDashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */