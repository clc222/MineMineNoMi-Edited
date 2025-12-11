/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DesertEncierroAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*  39 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "desert_encierro", new Pair[] {
/*  40 */         (Pair)ImmutablePair.of("Quickly drains the enemy in front of the user of their moisture, leaving them weak for a few seconds.", null), 
/*  41 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s.", new Object[] {
/*  42 */             "§a" + Math.round(19.999998F) + "%§r"
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int CHARGE_TIME = 100;
/*     */   private static final int PULL_TIME = 200;
/*     */   private static final float DAMAGE = 20.0F;
/*  49 */   public static final AbilityCore<DesertEncierroAbility> INSTANCE = (new AbilityCore.Builder("Desert Encierro", AbilityCategory.DEVIL_FRUITS, DesertEncierroAbility::new))
/*  50 */     .addDescriptionLine(DESCRIPTION)
/*  51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ChargeComponent.getTooltip(100.0F), DealDamageComponent.getTooltip(20.0F)
/*  52 */       }).build();
/*     */   
/*  54 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(this::onContinuityEnd);
/*  55 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  56 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  57 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  58 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, true, true, 2.0F)).addPullStartEvent(this::onPullStart).addPullEndEvent(this::onPullEnd);
/*  59 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  60 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*  62 */   private final Interval particleInterval = new Interval(6);
/*     */   
/*     */   public DesertEncierroAbility(AbilityCore<DesertEncierroAbility> core) {
/*  65 */     super(core);
/*     */     
/*  67 */     this.isNew = true;
/*     */     
/*  69 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  71 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */     
/*  73 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  77 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     if (this.continuousComponent.isContinuous()) {
/*  82 */       this.grabComponent.release(entity);
/*  83 */       this.continuousComponent.stopContinuity(entity);
/*  84 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/*  85 */       this.grabComponent.triggerPulling(entity);
/*     */     } else {
/*  87 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  92 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  93 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  96 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 100 */     if (this.grabComponent.grabManually(entity, target)) {
/* 101 */       this.grabComponent.startPulling(entity);
/*     */     }
/*     */     
/* 104 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public void onPullStart(LivingEntity entity, IAbility ability) {
/* 110 */     this.continuousComponent.setThresholdTime(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public void onPullEnd(LivingEntity entity, IAbility ability) {
/* 114 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 116 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 117 */       this.chargeComponent.startCharging(entity, 100.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 122 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 126 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.GRABBED) {
/* 127 */       this.grabComponent.release(entity);
/*     */     }
/*     */     
/* 130 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 131 */       this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onChargeStart(LivingEntity entity, IAbility ability) {
/* 136 */     SunaHelper.drainLiquids(this.grabComponent.getGrabbedEntity(), (int)WyHelper.randomWithRange(0, 1), (int)WyHelper.randomWithRange(1, 3), (int)WyHelper.randomWithRange(0, 1));
/*     */     
/* 138 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/* 139 */     this.particleInterval.restartIntervalToZero();
/*     */   }
/*     */   
/*     */   public void onChargeTick(LivingEntity entity, IAbility ability) {
/* 143 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 147 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 148 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 153 */     LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */     
/* 155 */     if (this.particleInterval.canTick()) {
/* 156 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DESERT_ENCIERRO.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */     }
/*     */     
/* 159 */     DamageSource source = ModDamageSource.causeAbilityDamage(entity, (IAbility)this).setInternal().func_151518_m();
/*     */     
/* 161 */     this.dealDamageComponent.hurtTarget(entity, target, 2.0F, source);
/*     */   }
/*     */   
/*     */   public void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 165 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 166 */       LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */       
/* 168 */       DamageSource source = ModDamageSource.causeAbilityDamage(entity, (IAbility)this).setInternal().func_151518_m();
/*     */       
/* 170 */       if (this.dealDamageComponent.hurtTarget(entity, target, 20.0F, source)) {
/* 171 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.DEHYDRATION.get(), 300, 2, false, true));
/* 172 */         target.func_195064_c(new EffectInstance(Effects.field_76437_t, 300, 1, false, false));
/* 173 */         target.func_195064_c(new EffectInstance(Effects.field_76421_d, 300, 1, false, false));
/* 174 */         target.func_195064_c(new EffectInstance(Effects.field_76419_f, 300, 1, false, false));
/*     */       } 
/*     */       
/* 177 */       this.grabComponent.release(entity);
/*     */     } 
/*     */     
/* 180 */     this.animationComponent.stop(entity);
/*     */     
/* 182 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/*     */     
/* 184 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 185 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 188 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertEncierroAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */