/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TenseiNoSoenAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tensei_no_soen", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave.", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int COOLDOWN = 600;
/*     */   private static final int RANGE = 30;
/*     */   private static final int DAMAGE = 50;
/*  39 */   public static final AbilityCore<TenseiNoSoenAbility> INSTANCE = (new AbilityCore.Builder("Tensei no Soen", AbilityCategory.DEVIL_FRUITS, TenseiNoSoenAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  42 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ChargeComponent.getTooltip(60.0F), RangeComponent.getTooltip(30.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(50.0F)
/*  43 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  44 */     .build();
/*     */   
/*  46 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PHOENIX_FLY.get(), new MorphInfo[0]);
/*  47 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  49 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  50 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  52 */   private Interval particleInterval = new Interval(2);
/*     */   
/*     */   public TenseiNoSoenAbility(AbilityCore<TenseiNoSoenAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.isNew = true;
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.continuousComponent });
/*     */     
/*  60 */     addCanUseCheck(AbilityHelper::requiresInAir);
/*  61 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  65 */     if (!this.continuousComponent.isContinuous()) {
/*  66 */       this.particleInterval.restartIntervalToZero();
/*  67 */       this.chargeComponent.startCharging(entity, 60.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  72 */     if (this.particleInterval.canTick()) {
/*  73 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.TENSEI_NO_SOEN_1.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  78 */     this.continuousComponent.startContinuity(entity);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  82 */     if (!entity.func_233570_aj_()) {
/*  83 */       AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, -10.0D, (entity.func_213322_ci()).field_72449_c);
/*     */       
/*  85 */       entity.field_70143_R = 0.0F;
/*  86 */     } else if (entity.func_233570_aj_() || DevilFruitHelper.getDifferenceToFloor((Entity)entity) < 2.0D) {
/*  87 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  92 */     if (entity.field_70170_p.field_72995_K) {
/*  93 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.TENSEI_NO_SOEN_2.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/*  96 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 30.0F);
/*     */     
/*  98 */     for (LivingEntity target : targets) {
/*  99 */       this.dealDamageComponent.hurtTarget(entity, target, 50.0F);
/*     */     }
/*     */     
/* 102 */     this.cooldownComponent.startCooldown(entity, 600.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\TenseiNoSoenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */