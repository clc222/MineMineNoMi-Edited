/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.electro.ElectricalLunaProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ElectricalLunaAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.3F;
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "electrical_luna", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("Charges a lightning ball in the user's hand which will be used whenever the user swings their arm, shooting the ball of electricity towards their target. On impact the ball will cause a small area of effect that stuns all nearby enemies but only damages the main target.", null), 
/*  39 */         (Pair)ImmutablePair.of("While %s is active the cooldown of this ability is reduced by %s and the damage is increased by %s.", new Object[] {
/*  40 */             AbilityHelper.mentionAbility(SulongAbility.INSTANCE), 
/*  41 */             AbilityHelper.mentionText(Math.round(70.0F) + "%"), 
/*  42 */             AbilityHelper.mentionText(Math.round(Math.abs(-1.0F) * 100.0F) + "%") }) });
/*     */   
/*     */   private static final float DAMAGE_BONUS = 2.0F;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int CHARGE_TIME = 40;
/*     */   public static final int RANGE = 6;
/*     */   private static final int DAMAGE = 40;
/*     */   private static final int ELECLAW_STACKS = 2;
/*  50 */   public static final AbilityCore<ElectricalLunaAbility> INSTANCE = (new AbilityCore.Builder("Electrical Luna", AbilityCategory.RACIAL, ElectricalLunaAbility::new))
/*  51 */     .addDescriptionLine(new ITextComponent[] { DESCRIPTION[0]
/*  52 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.IDescriptionLine.of(DESCRIPTION[1])
/*  53 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(40.0F), RangeComponent.getTooltip(6.0F, RangeComponent.RangeType.AOE)
/*  54 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  55 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  56 */     .setSourceElement(SourceElement.LIGHTNING)
/*  57 */     .setUnlockCheck(ElectricalLunaAbility::canUnlock)
/*  58 */     .build();
/*     */   
/*  60 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  61 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(this::endContinuityEvent);
/*  62 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(this::swingEvent);
/*  63 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   public ElectricalLunaAbility(AbilityCore<ElectricalLunaAbility> core) {
/*  66 */     super(core);
/*     */     
/*  68 */     this.isNew = true;
/*  69 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.swingTriggerComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  71 */     addCanUseCheck(ElectroHelper.requireEleclaw(2));
/*     */     
/*  73 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  77 */     if (this.chargeComponent.isCharging() || this.continuousComponent.isContinuous()) {
/*     */       return;
/*     */     }
/*  80 */     this.chargeComponent.startCharging(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  84 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0, false, false));
/*  85 */     if (this.chargeComponent.getChargeTime() % 2.0F == 0.0F) {
/*  86 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ELECTRO_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  91 */     boolean hasSulongActive = ElectroHelper.hasSulongActive(entity);
/*     */     
/*  93 */     this.projectileComponent.getDamageBonusManager().removeBonus(ElectroHelper.SULONG_DAMAGE_BONUS);
/*  94 */     this.cooldownComponent.getBonusManager().removeBonus(ElectroHelper.SULONG_COOLDOWN_BONUS);
/*  95 */     if (hasSulongActive) {
/*  96 */       this.projectileComponent.getDamageBonusManager().addBonus(ElectroHelper.SULONG_DAMAGE_BONUS, "Sulong Damage Bonus", BonusOperation.MUL, 2.0F);
/*  97 */       this.cooldownComponent.getBonusManager().addBonus(ElectroHelper.SULONG_COOLDOWN_BONUS, "Sulong Cooldown Bonus", BonusOperation.MUL, 0.3F);
/*     */     } 
/*     */     
/* 100 */     this.continuousComponent.startContinuity(entity);
/*     */     
/* 102 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get(entity).getEquippedAbility(EleclawAbility.INSTANCE);
/* 103 */     if (eleclawAbility != null) {
/* 104 */       eleclawAbility.reduceUsage(entity, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 109 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void swingEvent(LivingEntity entity, IAbility ability) {
/* 113 */     if (this.continuousComponent.isContinuous()) {
/* 114 */       this.continuousComponent.stopContinuity(entity);
/* 115 */       this.projectileComponent.shoot(entity, 3.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ElectricalLunaProjectile createProjectile(LivingEntity entity) {
/* 120 */     ElectricalLunaProjectile proj = new ElectricalLunaProjectile(entity.field_70170_p, entity, this);
/* 121 */     proj.setDamage(40.0F);
/* 122 */     proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 3.0F, 0.0F);
/* 123 */     return proj;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 127 */     IEntityStats props = EntityStatsCapability.get(user);
/* 128 */     return (props.isMink() && props.getDoriki() >= 3600.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalLunaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */