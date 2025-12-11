/*     */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.events.FactionEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SoulParadeAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "soul_parade", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("The user blocks incoming attacks, an enemy hitting them gets frozen immediately.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 300;
/*     */   private static final int MIN_COOLDOWN = 60;
/*     */   private static final int MAX_COOLDOWN = 360;
/*  43 */   public static final AbilityCore<SoulParadeAbility> INSTANCE = (new AbilityCore.Builder("Soul Parade", AbilityCategory.DEVIL_FRUITS, SoulParadeAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F, 360.0F), ContinuousComponent.getTooltip(300.0F)
/*  46 */       }).setSourceType(new SourceType[] { SourceType.SLASH
/*  47 */       }).setSourceElement(SourceElement.ICE)
/*  48 */     .setUnlockCheck(SoulParadeAbility::canUnlock)
/*  49 */     .build();
/*     */   
/*  51 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  52 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*  53 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.TEKKAI_LIKE, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public SoulParadeAbility(AbilityCore<SoulParadeAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.poolComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  62 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*     */     
/*  64 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.continuousComponent.triggerContinuity(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 2, 3, false, false));
/*     */   }
/*     */ 
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  81 */     this.animationComponent.stop(entity);
/*  82 */     float cooldown = 60.0F + this.continuousComponent.getContinueTime();
/*  83 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   public float damageTakenEvent(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  87 */     if (this.continuousComponent.isContinuous() && ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity)) {
/*  88 */       Entity sourceEntity = damageSource.func_76346_g();
/*     */       
/*  90 */       if (sourceEntity == null) {
/*  91 */         return damage;
/*     */       }
/*     */       
/*  94 */       if (sourceEntity instanceof LivingEntity && FactionEvents.isDirectHit(damageSource)) {
/*  95 */         LivingEntity attacker = (LivingEntity)sourceEntity;
/*     */         
/*  97 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SOUL_PARADE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */         
/*  99 */         attacker.func_195064_c(new EffectInstance(Effects.field_76421_d, 70, 1));
/* 100 */         attacker.func_195064_c(new EffectInstance(Effects.field_76419_f, 70, 1));
/* 101 */         attacker.func_195064_c(new EffectInstance((Effect)ModEffects.FROZEN.get(), 70, 0));
/*     */         
/* 103 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, attacker.func_226277_ct_(), attacker.func_226278_cu_(), attacker.func_226281_cx_(), 2.0F);
/*     */         
/* 105 */         explosion.setDamageOwner(false);
/* 106 */         explosion.setDestroyBlocks(false);
/* 107 */         explosion.setDamageEntities(false);
/*     */         
/* 109 */         explosion.doExplosion();
/*     */       } 
/*     */       
/* 112 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/* 114 */       return 0.0F;
/*     */     } 
/*     */     
/* 117 */     return damage;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 121 */     return ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\SoulParadeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */