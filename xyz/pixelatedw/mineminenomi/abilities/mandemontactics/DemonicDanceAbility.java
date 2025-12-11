/*     */ package xyz.pixelatedw.mineminenomi.abilities.mandemontactics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DemonicDanceAbility
/*     */   extends DropHitAbility2
/*     */ {
/*     */   private static final int COOLDOWN = 260;
/*     */   private static final float RANGE = 1.25F;
/*     */   private static final float DAMAGE = 30.0F;
/*     */   private static final float CHARGE_TIME = 40.0F;
/*  42 */   public static final TargetsPredicate TARGET_TEST = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*     */   
/*  44 */   public static final AbilityCore<DemonicDanceAbility> INSTANCE = (new AbilityCore.Builder("Demonic Dance", AbilityCategory.STYLE, DemonicDanceAbility::new))
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(260.0F), ChargeComponent.getTooltip(40.0F), RangeComponent.getTooltip(1.25F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(30.0F)
/*  46 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  47 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  48 */       }).build();
/*     */   
/*  50 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addEndEvent(this::endChargeEvent);
/*  51 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  52 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  53 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  55 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(40);
/*     */   
/*     */   @Nullable
/*     */   private Entity target;
/*     */   private boolean isStandard;
/*     */   
/*     */   public DemonicDanceAbility(AbilityCore<DemonicDanceAbility> core) {
/*  62 */     super(core);
/*     */     
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  66 */     addCanUseCheck(AbilityHelper::requiresTonfaWeapon);
/*     */     
/*  68 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  69 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*  70 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onUseEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.chargeComponent.startCharging(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  79 */     this.animationComponent.start(entity, ModAnimations.DEMONIC_DANCE_CHARGE, (int)this.chargeComponent.getMaxChargeTime());
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  83 */     this.animationComponent.stop(entity);
/*  84 */     this.continuousComponent.startContinuity(entity);
/*  85 */     this.animationComponent.start(entity, ModAnimations.DEMONIC_DANCE_LEAP);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  89 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  91 */     if (this.target != null) {
/*  92 */       Vector3d speed = this.target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_216372_d(2.0D, 1.0D, 2.0D);
/*  93 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.5D, speed.field_72449_c);
/*     */     } else {
/*     */       
/*  96 */       Vector3d speed = entity.func_70040_Z().func_216372_d(2.0D, 0.0D, 2.0D);
/*  97 */       AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 0.5D, speed.field_72449_c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 102 */     if (entity.field_70143_R > 0.0F && !hasLanded()) {
/* 103 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.25F, TARGET_TEST);
/*     */       
/* 105 */       for (LivingEntity target : targets) {
/* 106 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 107 */           ItemsHelper.stopShieldAndStartCooldown(target, 100);
/* 108 */           this.dealDamageComponent.hurtTarget(entity, target, 15.0F);
/* 109 */           setLanded();
/*     */         } 
/*     */       } 
/*     */       
/* 113 */       for (Entity target : this.hitTrackerComponent.getHits()) {
/* 114 */         if (DevilFruitHelper.getDifferenceToFloor(target) > 1.5D) {
/* 115 */           target.func_70634_a(entity.func_226277_ct_(), entity.func_226278_cu_() - 1.0D, entity.func_226281_cx_());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 122 */     this.target = null;
/* 123 */     this.animationComponent.stop(entity);
/* 124 */     this.cooldownComponent.startCooldown(entity, 260.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/* 129 */     for (Entity target : this.hitTrackerComponent.getHits()) {
/* 130 */       if (target instanceof LivingEntity) {
/* 131 */         LivingEntity livingTarget = (LivingEntity)target;
/* 132 */         if (!this.isStandard) {
/* 133 */           livingTarget.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 20, 0, false, false));
/*     */         }
/* 135 */         this.dealDamageComponent.hurtTarget(entity, livingTarget, 15.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     List<Vector3d> positions = new ArrayList<>();
/* 140 */     int range = (int)Math.ceil(1.25D);
/*     */     
/* 142 */     for (int x = -range; x < range; x++) {
/* 143 */       for (int z = -range; z < range; z++) {
/* 144 */         double posX = entity.func_226277_ct_() + x;
/* 145 */         double posY = entity.func_226278_cu_() - 1.0D;
/* 146 */         double posZ = entity.func_226281_cx_() + z;
/* 147 */         Vector3d pos = new Vector3d(posX, posY, posZ);
/* 148 */         positions.add(pos);
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     if (positions.size() > 0) {
/* 153 */       this.details.setVecPositions(positions);
/* 154 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */     } 
/*     */     
/* 157 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   public void setStandardMode() {
/* 161 */     this.isStandard = true;
/*     */   }
/*     */   
/*     */   public void setTarget(Entity target) {
/* 165 */     this.target = target;
/*     */   }
/*     */   
/*     */   public Entity getTarget() {
/* 169 */     return this.target;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mandemontactics\DemonicDanceAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */