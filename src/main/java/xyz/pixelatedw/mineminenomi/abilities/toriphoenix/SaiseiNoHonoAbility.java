/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HealComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SaiseiNoHonoAbility extends Ability {
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "saisei_no_hono", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Uses the blue flames to heal the target by hitting them. Using it while crouching will heal friendly entities around the user including the user themselves at the expense of a longer cooldown based on the number of healed entities.", null)
/*     */       });
/*  36 */   private static final TargetsPredicate TARGETS_CHECK = (new TargetsPredicate()).testFriendlyFaction();
/*     */   
/*     */   private static final float COOLDOWN = 300.0F;
/*     */   
/*     */   private static final int RANGE = 10;
/*     */   private static final int HEAL = 10;
/*  42 */   public static final AbilityCore<SaiseiNoHonoAbility> INSTANCE = (new AbilityCore.Builder("Saisei No Hono", AbilityCategory.DEVIL_FRUITS, SaiseiNoHonoAbility::new))
/*  43 */     .addDescriptionLine(DESCRIPTION)
/*  44 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  45 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(), HealComponent.getTooltip(10.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/*  46 */       }).build();
/*     */   
/*  48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  49 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PHOENIX_ASSAULT.get(), new MorphInfo[0]);
/*  50 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::hitEvent).addTryHitEvent(this::tryHitEvent);
/*  51 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  52 */   private final HealComponent healComponent = new HealComponent((IAbility)this);
/*     */   
/*     */   private int healed;
/*     */   
/*     */   public SaiseiNoHonoAbility(AbilityCore<SaiseiNoHonoAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.healComponent });
/*     */     
/*  62 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*     */     
/*  64 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.healed = 0;
/*  73 */     if (entity.func_213453_ef()) {
/*  74 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F, TARGETS_CHECK);
/*  75 */       targets.add(entity);
/*  76 */       int healed = 0;
/*  77 */       for (LivingEntity target : targets) {
/*  78 */         healed++;
/*  79 */         this.healComponent.healTarget(entity, target, 10.0F);
/*  80 */         target.func_195064_c(new EffectInstance(Effects.field_76428_l, 400, 1));
/*  81 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLUE_FLAMES.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */       } 
/*  83 */       this.healed = Math.max(1, healed);
/*  84 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.TENSEI_NO_SOEN_2.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*  85 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  90 */     this.cooldownComponent.startCooldown(entity, 300.0F + (this.healed * 120));
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  94 */     if (!this.continuousComponent.isContinuous()) {
/*  95 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  98 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 102 */     source.setBypassFriendlyDamage();
/*     */     
/* 104 */     this.healComponent.healTarget(entity, target, 10.0F);
/*     */     
/* 106 */     target.func_195064_c(new EffectInstance(Effects.field_76428_l, 400, 1));
/*     */     
/* 108 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLUE_FLAMES.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */     
/* 110 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\SaiseiNoHonoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */