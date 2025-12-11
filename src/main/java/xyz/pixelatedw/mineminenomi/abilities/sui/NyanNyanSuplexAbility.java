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
/*     */ public class NyanNyanSuplexAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nyan_nyan_suplex", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("While swimming, grabs the nearest enemy from the back and launches them into the ground, dealing moderate damage and creating a small crater.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final float RANGE = 1.3F;
/*     */   private static final float DAMAGE = 20.0F;
/*  44 */   public static final AbilityCore<NyanNyanSuplexAbility> INSTANCE = (new AbilityCore.Builder("Nyan Nyan Suplex", AbilityCategory.DEVIL_FRUITS, NyanNyanSuplexAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(20.0F), RangeComponent.getTooltip(1.3F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
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
/*     */   public NyanNyanSuplexAbility(AbilityCore<NyanNyanSuplexAbility> core) {
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
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  74 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  78 */     if (SuiHelper.isFreeSwimming(entity, ability).isFail()) {
/*  79 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  83 */     if (!this.grabEntityComponent.hasGrabbedEntity()) {
/*  84 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.3F);
/*     */       
/*  86 */       for (LivingEntity target : targets) {
/*  87 */         if (this.grabEntityComponent.grabManually(entity, target)) {
/*  88 */           Vector3d lookVec = target.func_70040_Z().func_72432_b().func_216371_e().func_216372_d(1.1D, 1.0D, 1.1D);
/*  89 */           Vector3d moveVec = target.func_213303_ch().func_178787_e(lookVec).func_178788_d(entity.func_213303_ch());
/*     */           
/*  91 */           AbilityHelper.setDeltaMovement((Entity)entity, moveVec, true);
/*     */           
/*  93 */           this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*  94 */           this.chargeComponent.startCharging(entity, 20.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tickChargingEvent(LivingEntity entity, IAbility ability) {
/* 101 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 105 */     if (!this.grabEntityComponent.canContinueGrab(entity)) {
/* 106 */       this.grabEntityComponent.release(entity);
/* 107 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 111 */     LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */     
/* 113 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/* 114 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 5, 3));
/*     */   }
/*     */   
/*     */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/* 118 */     if (this.grabEntityComponent.canContinueGrab(entity)) {
/* 119 */       LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */       
/* 121 */       this.dealDamageComponent.hurtTarget(entity, target, 20.0F);
/*     */       
/* 123 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 2.0F);
/*     */       
/* 125 */       explosion.setStaticDamage(30.0F);
/* 126 */       explosion.setExplosionSound(true);
/* 127 */       explosion.setDamageOwner(false);
/* 128 */       explosion.setDestroyBlocks(true);
/* 129 */       explosion.setFireAfterExplosion(false);
/* 130 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 131 */       explosion.setDamageEntities(true);
/* 132 */       explosion.doExplosion();
/*     */       
/* 134 */       this.grabEntityComponent.release(entity);
/*     */     } 
/* 136 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 140 */     this.animationComponent.stop(entity);
/* 141 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\NyanNyanSuplexAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */