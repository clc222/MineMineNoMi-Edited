/*     */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.events.FactionEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SamehadaShoteiAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "samehada_shotei", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("The user concentrates their power to their palms, sending melee damage back to its owner and pushing them a few blocks back", null)
/*     */       });
/*     */   
/*     */   private static final float HOLD_TIME = 200.0F;
/*     */   private static final float INTRERUPT_COOLDOWN = 200.0F;
/*     */   private static final float MIN_COOLDOWN = 100.0F;
/*     */   private static final float MAX_COOLDOWN = 400.0F;
/*     */   private static final float DAMAGE = 15.0F;
/*  43 */   public static final AbilityCore<SamehadaShoteiAbility> INSTANCE = (new AbilityCore.Builder("Samehada Shotei", AbilityCategory.RACIAL, SamehadaShoteiAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 400.0F), ContinuousComponent.getTooltip(200.0F)
/*  46 */       }).setUnlockCheck(SamehadaShoteiAbility::canUnlock)
/*  47 */     .build();
/*     */   
/*  49 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::onStartContinuityEvent).addTickEvent(100, this::onTickContinuityEvent).addEndEvent(100, this::onEndContinuityEvent);
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  51 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  52 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTakenEvent);
/*  53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public SamehadaShoteiAbility(AbilityCore<SamehadaShoteiAbility> core) {
/*  56 */     super(core);
/*     */     
/*  58 */     this.isNew = true;
/*  59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.poolComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  61 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  65 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/*  69 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*     */   }
/*     */   
/*     */   private void onTickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  73 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, entity.func_70090_H() ? 2 : 1, false, false));
/*  74 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SAMEHADA.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void onEndContinuityEvent(LivingEntity entity, IAbility ability) {
/*  78 */     this.animationComponent.stop(entity);
/*  79 */     this.cooldownComponent.startCooldown(entity, 100.0F + this.continuousComponent.getContinueTime() * 2.0F);
/*     */   }
/*     */   
/*     */   private float onDamageTakenEvent(LivingEntity entity, IAbility ability, DamageSource source, float damage) {
/*  83 */     if (!this.continuousComponent.isContinuous()) {
/*  84 */       return damage;
/*     */     }
/*     */     
/*  87 */     Entity sourceEntity = source.func_76346_g();
/*     */     
/*  89 */     if (sourceEntity == null) {
/*  90 */       return damage;
/*     */     }
/*     */     
/*  93 */     if (sourceEntity instanceof LivingEntity && FactionEvents.isDirectHit(source) && 
/*  94 */       this.dealDamageComponent.hurtTarget(entity, (LivingEntity)sourceEntity, 15.0F)) {
/*  95 */       Vector3d knockback = entity.func_213303_ch().func_178788_d(sourceEntity.func_213303_ch()).func_72432_b().func_186678_a(-2.0D);
/*     */       
/*  97 */       AbilityHelper.setDeltaMovement(sourceEntity, knockback);
/*     */     } 
/*     */ 
/*     */     
/* 101 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/* 102 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 104 */     return 0.0F;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 108 */     IEntityStats props = EntityStatsCapability.get(user);
/*     */     
/* 110 */     return (props.isFishman() && props.getDoriki() >= 3000.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\SamehadaShoteiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */