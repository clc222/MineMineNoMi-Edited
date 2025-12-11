/*     */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SouthlandSuplexAbility extends Ability {
/*  46 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "southland_suplex", new Pair[] {
/*  47 */         (Pair)ImmutablePair.of("The user grabs a person with both of their arms and smashes the opponent's head by throwing them backwards", null)
/*     */       });
/*     */   
/*     */   private static final int PULL_TIME = 200;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int COOLDOWN = 140;
/*     */   private static final int DAMAGE = 20;
/*     */   private static final int COLLISION_DAMAGE = 10;
/*  55 */   public static final AbilityCore<SouthlandSuplexAbility> INSTANCE = (new AbilityCore.Builder("Southland Suplex", AbilityCategory.RACIAL, SouthlandSuplexAbility::new))
/*  56 */     .addDescriptionLine(DESCRIPTION)
/*  57 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ChargeComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(20.0F)
/*  58 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  59 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  60 */       }).setUnlockCheck(SouthlandSuplexAbility::canUnlock)
/*  61 */     .build();
/*     */   
/*  63 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(this::onContinuityEnd);
/*  64 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  65 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  66 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  67 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, false, true, 2.0F)).addPullStartEvent(this::onPullStart).addPullEndEvent(this::onPullEnd);
/*  68 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  69 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public SouthlandSuplexAbility(AbilityCore<SouthlandSuplexAbility> core) {
/*  72 */     super(core);
/*     */     
/*  74 */     this.isNew = true;
/*  75 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  77 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*  78 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  82 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     if (this.continuousComponent.isContinuous()) {
/*  87 */       this.grabComponent.release(entity);
/*  88 */       this.continuousComponent.stopContinuity(entity);
/*  89 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/*  90 */       this.grabComponent.triggerPulling(entity);
/*     */     } else {
/*  92 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  97 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  98 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 101 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 105 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/* 106 */       return true;
/*     */     }
/*     */     
/* 109 */     if (this.grabComponent.grabManually(entity, target)) {
/* 110 */       this.grabComponent.startPulling(entity);
/*     */     }
/*     */     
/* 113 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public void onPullStart(LivingEntity entity, IAbility ability) {
/* 119 */     this.continuousComponent.setThresholdTime(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public void onPullEnd(LivingEntity entity, IAbility ability) {
/* 123 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 125 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 126 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 131 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 135 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.GRABBED) {
/* 136 */       this.grabComponent.release(entity);
/*     */     }
/*     */     
/* 139 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 140 */       this.cooldownComponent.startCooldown(entity, 140.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 145 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*     */   }
/*     */   
/*     */   public void onChargeTick(LivingEntity entity, IAbility ability) {
/* 149 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 153 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 154 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 159 */     LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */     
/* 161 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/* 162 */     grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */     
/* 164 */     float distance = 1.0F;
/*     */     
/* 166 */     Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/* 167 */     Vector3d pos = (new Vector3d(lookVec.field_72450_a * distance, entity.func_213302_cg(), lookVec.field_72449_c * distance)).func_186678_a(this.chargeComponent.getChargePercentage());
/*     */     
/* 169 */     AbilityHelper.setDeltaMovement((Entity)grabbedTarget, entity.func_213303_ch().func_178786_a(pos.field_72450_a, -EasingFunctionHelper.easeInOutSine(Float.valueOf((float)pos.field_72448_b)), pos.field_72449_c).func_178788_d(grabbedTarget.func_213303_ch()), true);
/*     */   }
/*     */   
/*     */   public void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 173 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 174 */       LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */       
/* 176 */       DamageSource source = this.dealDamageComponent.getDamageSource(entity);
/*     */       
/* 178 */       if (this.dealDamageComponent.hurtTarget(entity, grabbedTarget, 20.0F)) {
/* 179 */         grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0));
/*     */       }
/*     */       
/* 182 */       List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, grabbedTarget.func_213311_cf(), grabbedTarget.func_213302_cg(), grabbedTarget.func_213311_cf(), ModEntityPredicates.getEnemyFactions(entity));
/*     */       
/* 184 */       targets.remove(grabbedTarget);
/*     */       
/* 186 */       if (!HakiHelper.hasHardeningActive(entity)) {
/* 187 */         targets.removeIf(target -> DevilFruitCapability.get(entity).isLogia());
/*     */       }
/*     */       
/* 190 */       for (LivingEntity target : targets) {
/* 191 */         if (this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/* 192 */           AbilityHelper.setDeltaMovement((Entity)target, 0.0D, -1.0D, 0.0D);
/*     */         }
/*     */       } 
/*     */       
/* 196 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, grabbedTarget.func_226277_ct_(), grabbedTarget.func_226278_cu_(), grabbedTarget.func_226281_cx_(), 1.0F);
/*     */       
/* 198 */       explosion.setStaticDamage(6.0F);
/* 199 */       explosion.setExplosionSound(true);
/* 200 */       explosion.setDamageOwner(false);
/* 201 */       explosion.setDestroyBlocks(false);
/* 202 */       explosion.setFireAfterExplosion(false);
/* 203 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 204 */       explosion.setDamageEntities(true);
/* 205 */       explosion.setDamageSource(source);
/*     */ 
/*     */       
/* 208 */       this.grabComponent.release(entity);
/*     */     } 
/*     */     
/* 211 */     this.animationComponent.stop(entity);
/*     */     
/* 213 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 217 */     IEntityStats props = EntityStatsCapability.get(entity);
/*     */     
/* 219 */     return props.isCyborg();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\SouthlandSuplexAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */