/*     */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class NyanNyanSuplexBabyBusterAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nyan_nyan_suplex_baby_buster", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("While swimming, grabs the nearest enemy from the back and launches them into the sky on when hitting the ground it creates a big crater.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int CHARGE_TIME = 200;
/*     */   private static final float RANGE = 1.3F;
/*     */   private static final float DAMAGE = 40.0F;
/*  44 */   public static final AbilityCore<NyanNyanSuplexBabyBusterAbility> INSTANCE = (new AbilityCore.Builder("Nyan Nyan Suplex: Baby Buster", AbilityCategory.DEVIL_FRUITS, NyanNyanSuplexBabyBusterAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ChargeComponent.getTooltip(200.0F), RangeComponent.getTooltip(1.3F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(40.0F)
/*  47 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  48 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  49 */       }).build();
/*     */   
/*  51 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::tickContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  52 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::tickChargingEvent).addEndEvent(this::endChargingEvent);
/*  53 */   private final GrabEntityComponent grabEntityComponent = new GrabEntityComponent((IAbility)this, true, true, 1.0F);
/*  54 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  55 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  56 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  57 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public NyanNyanSuplexBabyBusterAbility(AbilityCore<NyanNyanSuplexBabyBusterAbility> core) {
/*  60 */     super(core);
/*     */     
/*  62 */     this.isNew = true;
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.grabEntityComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  65 */     addCanUseCheck(SuiHelper::isFreeSwimming);
/*  66 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  70 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void tickChargingEvent(LivingEntity entity, IAbility ability) {
/*  74 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  78 */     if (!this.grabEntityComponent.canContinueGrab(entity)) {
/*  79 */       this.grabEntityComponent.release(entity);
/*  80 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  84 */     LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */     
/*  86 */     if (this.chargeComponent.getChargePercentage() >= 0.5D && (entity.func_233570_aj_() || target.func_233570_aj_())) {
/*  87 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  91 */     target.field_70143_R = 0.0F;
/*  92 */     entity.field_70143_R = 0.0F;
/*  93 */     if (this.chargeComponent.getChargePercentage() < 0.5D) {
/*  94 */       AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, 1.2D, (entity.func_213322_ci()).field_72449_c, true);
/*     */     } else {
/*     */       
/*  97 */       AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, -1.2D, (entity.func_213322_ci()).field_72449_c, true);
/*     */     } 
/*     */     
/* 100 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/* 101 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 5, 3));
/*     */   }
/*     */   
/*     */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/* 105 */     if (this.grabEntityComponent.canContinueGrab(entity)) {
/* 106 */       LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */       
/* 108 */       this.dealDamageComponent.hurtTarget(entity, target, 40.0F);
/*     */       
/* 110 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 4.0F);
/* 111 */       explosion.setStaticDamage(70.0F);
/* 112 */       explosion.setExplosionSound(true);
/* 113 */       explosion.setDamageOwner(false);
/* 114 */       explosion.setDestroyBlocks(true);
/* 115 */       explosion.setFireAfterExplosion(false);
/* 116 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 117 */       explosion.setDamageEntities(true);
/* 118 */       explosion.doExplosion();
/*     */       
/* 120 */       this.grabEntityComponent.release(entity);
/*     */     } 
/*     */     
/* 123 */     this.animationComponent.stop(entity);
/* 124 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 128 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     if (SuiHelper.isFreeSwimming(entity, ability).isFail() && !this.chargeComponent.isCharging()) {
/* 133 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 137 */     if (!this.grabEntityComponent.hasGrabbedEntity()) {
/* 138 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.3F);
/* 139 */       for (LivingEntity target : targets) {
/* 140 */         if (this.grabEntityComponent.grabManually(entity, target)) {
/* 141 */           Vector3d lookVec = target.func_70040_Z().func_216371_e().func_216372_d(1.1D, 1.0D, 1.1D);
/* 142 */           Vector3d moveVec = target.func_213303_ch().func_178787_e(lookVec);
/* 143 */           entity.func_70634_a(moveVec.field_72450_a, moveVec.field_72448_b, moveVec.field_72449_c);
/* 144 */           this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/* 145 */           this.chargeComponent.startCharging(entity, 200.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 152 */     this.grabEntityComponent.release(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\NyanNyanSuplexBabyBusterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */