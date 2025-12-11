/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class SuplexAbility extends Ability {
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "suplex", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Grabs an opponent from the back and launches it into the ground", null)
/*     */       });
/*     */   
/*     */   private static final int PULL_TIME = 200;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final float COOLDOWN = 140.0F;
/*     */   private static final float DAMAGE = 20.0F;
/*  52 */   public static final AbilityCore<SuplexAbility> INSTANCE = (new AbilityCore.Builder("Suplex", AbilityCategory.STYLE, SuplexAbility::new))
/*  53 */     .addDescriptionLine(DESCRIPTION)
/*  54 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ChargeComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(20.0F)
/*  55 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  56 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  57 */       }).setUnlockCheck(SuplexAbility::canUnlock)
/*  58 */     .build();
/*     */   
/*  60 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(this::onContinuityEnd);
/*  61 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  62 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  63 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  64 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, false, true, 2.0F)).addPullStartEvent(this::onPullStart).addPullEndEvent(this::onPullEnd);
/*  65 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  66 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public SuplexAbility(AbilityCore<SuplexAbility> core) {
/*  69 */     super(core);
/*     */     
/*  71 */     this.isNew = true;
/*  72 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  74 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*  75 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  79 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  83 */     if (this.continuousComponent.isContinuous()) {
/*  84 */       this.grabComponent.release(entity);
/*  85 */       this.continuousComponent.stopContinuity(entity);
/*  86 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/*  87 */       this.grabComponent.triggerPulling(entity);
/*     */     } else {
/*  89 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  94 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  95 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  98 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 102 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/* 103 */       return true;
/*     */     }
/*     */     
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
/* 123 */       this.chargeComponent.startCharging(entity, 20.0F);
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
/* 137 */       this.cooldownComponent.startCooldown(entity, 140.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 142 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*     */   }
/*     */   
/*     */   public void onChargeTick(LivingEntity entity, IAbility ability) {
/* 146 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 150 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 151 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 156 */     LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */     
/* 158 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/* 159 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */     
/* 161 */     float distance = 1.0F;
/*     */     
/* 163 */     Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/* 164 */     Vector3d pos = (new Vector3d(lookVec.field_72450_a * distance, entity.func_213302_cg(), lookVec.field_72449_c * distance)).func_186678_a(this.chargeComponent.getChargePercentage());
/*     */     
/* 166 */     AbilityHelper.setDeltaMovement((Entity)target, entity.func_213303_ch().func_178786_a(pos.field_72450_a, -EasingFunctionHelper.easeInOutSine(Float.valueOf((float)pos.field_72448_b)), pos.field_72449_c).func_178788_d(target.func_213303_ch()), true);
/*     */   }
/*     */   
/*     */   public void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 170 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 171 */       LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */       
/* 173 */       DamageSource source = this.dealDamageComponent.getDamageSource(entity);
/*     */       
/* 175 */       if (this.dealDamageComponent.hurtTarget(entity, target, 20.0F)) {
/* 176 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0));
/*     */       }
/*     */       
/* 179 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 1.0F);
/*     */       
/* 181 */       explosion.setStaticDamage(6.0F);
/* 182 */       explosion.setExplosionSound(true);
/* 183 */       explosion.setDamageOwner(false);
/* 184 */       explosion.setDestroyBlocks(false);
/* 185 */       explosion.setFireAfterExplosion(false);
/* 186 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 187 */       explosion.setDamageEntities(true);
/* 188 */       explosion.setDamageSource(source);
/*     */ 
/*     */       
/* 191 */       this.grabComponent.release(entity);
/*     */     } 
/*     */     
/* 194 */     this.animationComponent.stop(entity);
/*     */     
/* 196 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 200 */     if (!(entity instanceof PlayerEntity)) {
/* 201 */       return false;
/*     */     }
/*     */     
/* 204 */     PlayerEntity player = (PlayerEntity)entity;
/* 205 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 206 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 208 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_01));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\SuplexAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */