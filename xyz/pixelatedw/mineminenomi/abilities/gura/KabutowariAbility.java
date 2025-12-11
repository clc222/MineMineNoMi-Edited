/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
/*     */ 
/*     */ public class KabutowariAbility extends Ability {
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kabutowari", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("The user grabs their opponent's head and concentrates their vibrations around it, resulting in crushing damage", null)
/*     */       });
/*  45 */   private static final AirCrackParticleEffect PARTICLES = new AirCrackParticleEffect();
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   
/*     */   private static final int CHARGE_TIME = 40;
/*     */   private static final int DAMAGE = 60;
/*     */   private static final int PULL_TIME = 200;
/*  52 */   public static final AbilityCore<KabutowariAbility> INSTANCE = (new AbilityCore.Builder("Kabutowari", AbilityCategory.DEVIL_FRUITS, KabutowariAbility::new))
/*  53 */     .addDescriptionLine(DESCRIPTION)
/*  54 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(40.0F), DealDamageComponent.getTooltip(60.0F)
/*  55 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  56 */     .setSourceElement(SourceElement.SHOCKWAVE)
/*  57 */     .build();
/*     */   
/*  59 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  60 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  61 */     .addStartEvent(this::onChargeStart)
/*  62 */     .addTickEvent(this::onChargeTick)
/*  63 */     .addEndEvent(this::onChargeEnd);
/*  64 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  65 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(this::onContinuityEnd);
/*  66 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, false, true, 1.0F)).addPullStartEvent(this::onPullStart).addPullEndEvent(this::onPullEnd);
/*  67 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  68 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public KabutowariAbility(AbilityCore<KabutowariAbility> core) {
/*  71 */     super(core);
/*     */     
/*  73 */     this.isNew = true;
/*     */     
/*  75 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  77 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */     
/*  79 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  83 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  87 */     if (this.continuousComponent.isContinuous()) {
/*  88 */       this.grabComponent.release(entity);
/*  89 */       this.continuousComponent.stopContinuity(entity);
/*  90 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/*  91 */       this.grabComponent.triggerPulling(entity);
/*     */     } else {
/*  93 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  98 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  99 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 102 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 106 */     if (this.grabComponent.grabManually(entity, target)) {
/* 107 */       this.grabComponent.startPulling(entity);
/*     */     }
/*     */     
/* 110 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   public void onPullStart(LivingEntity entity, IAbility ability) {
/* 116 */     this.continuousComponent.setThresholdTime(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public void onPullEnd(LivingEntity entity, IAbility ability) {
/* 120 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 122 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 123 */       this.chargeComponent.startCharging(entity, 40.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 128 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.GRABBED) {
/* 133 */       this.grabComponent.release(entity);
/*     */     }
/*     */     
/* 136 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 137 */       this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 142 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 146 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/*     */     
/* 148 */     LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */     
/* 150 */     int chargeTime = (int)this.chargeComponent.getMaxChargeTime();
/*     */     
/* 152 */     if (grabbedTarget != null) {
/* 153 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GURA_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */       
/* 155 */       grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.SEISMIC_BUBBLE.get(), chargeTime, 0));
/*     */       
/* 157 */       AbilityHelper.setDeltaMovement((Entity)grabbedTarget, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 162 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 166 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 167 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 172 */     LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */     
/* 174 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/* 175 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */     
/* 177 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, -target.func_70047_e(), target.func_213311_cf() - 0.2D);
/*     */     
/* 179 */     AbilityHelper.setDeltaMovement((Entity)target, pos.func_178788_d(target.func_213303_ch()), true);
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 183 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 187 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 188 */       LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */       
/* 190 */       ModDamageSource source = ModDamageSource.causeAbilityDamage(entity, getCore()).setPiercing(0.75F);
/*     */       
/* 192 */       this.dealDamageComponent.hurtTarget(entity, grabbedTarget, 60.0F, (DamageSource)source);
/*     */       
/* 194 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, grabbedTarget.func_226277_ct_(), grabbedTarget.func_226278_cu_(), grabbedTarget.func_226281_cx_(), 2.0F);
/*     */       
/* 196 */       explosion.setStaticDamage(20.0F);
/* 197 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 198 */       explosion.doExplosion();
/*     */       
/* 200 */       ItemStack stack = grabbedTarget.func_184582_a(EquipmentSlotType.HEAD);
/*     */       
/* 202 */       stack.func_222118_a(15, grabbedTarget, user -> user.func_213361_c(EquipmentSlotType.HEAD));
/*     */       
/* 204 */       PARTICLES.spawn(grabbedTarget.field_70170_p, grabbedTarget.func_226277_ct_(), grabbedTarget.func_226280_cw_(), grabbedTarget.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*     */       
/* 206 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GURA_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */       
/* 208 */       grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0));
/*     */       
/* 210 */       this.grabComponent.release(entity);
/*     */     } 
/*     */     
/* 213 */     this.animationComponent.stop(entity);
/*     */     
/* 215 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\KabutowariAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */