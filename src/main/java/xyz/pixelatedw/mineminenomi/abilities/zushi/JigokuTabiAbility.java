/*     */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ 
/*     */ public class JigokuTabiAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "jigoku_tabi", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Causes a powerful downward force of gravity, sending the enemies down in a crater. The longer its used the more damage it'll deal and the larger the crater will become.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 120;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 340;
/*     */   private static final int RANGE = 24;
/*     */   private static final int DEFAULT_FORCE = 4;
/*     */   private static final int FORCE_INCREASE = 60;
/*     */   private static final int MIN_DAMAGE = 8;
/*     */   private static final int MAX_DAMAGE = 12;
/*  42 */   public static final AbilityCore<JigokuTabiAbility> INSTANCE = (new AbilityCore.Builder("Jigoku Tabi", AbilityCategory.DEVIL_FRUITS, JigokuTabiAbility::new))
/*  43 */     .addDescriptionLine(DESCRIPTION)
/*  44 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 340.0F), ContinuousComponent.getTooltip(120.0F), RangeComponent.getTooltip(24.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(8.0F, 12.0F)
/*  45 */       }).setSourceElement(SourceElement.GRAVITY)
/*  46 */     .build();
/*     */   
/*  48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  49 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  50 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  52 */   private int force = 4;
/*  53 */   private Interval damageInterval = new Interval(20);
/*  54 */   private Interval addForceInterval = new Interval(60);
/*     */   
/*     */   public JigokuTabiAbility(AbilityCore<JigokuTabiAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  62 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  66 */     this.continuousComponent.triggerContinuity(entity, 120.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  70 */     this.force = 4;
/*  71 */     this.damageInterval.restartIntervalToZero();
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 24.0F);
/*     */     
/*  77 */     ModDamageSource source = ModDamageSource.causeAbilityDamage(entity, (IAbility)this).bypassLogia().setPiercing(1.0F).setUnavoidable();
/*  78 */     for (LivingEntity target : targets) {
/*  79 */       AbilityHelper.setDeltaMovement((Entity)target, 0.0D, (target.func_213322_ci()).field_72448_b - 4.0D, 0.0D);
/*     */       
/*  81 */       if (this.damageInterval.canTick()) {
/*  82 */         EffectInstance instance = new EffectInstance(Effects.field_76421_d, 25, 5, false, false);
/*     */         
/*  84 */         target.func_195064_c(instance);
/*     */ 
/*     */ 
/*     */         
/*  88 */         this.dealDamageComponent.hurtTarget(entity, target, (this.force * 2), (DamageSource)source);
/*     */         
/*  90 */         GraviZoneAbility.gravityRing(target, 3, 2, false);
/*     */         
/*  92 */         AbilityHelper.createSphere(entity.field_70170_p, target.func_233580_cy_(), this.force, 2, false, Blocks.field_150350_a, 2, DefaultProtectionRules.CORE_FOLIAGE_ORE);
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     if (this.addForceInterval.canTick()) {
/*  97 */       this.force++;
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 102 */     float cooldown = 100.0F + this.continuousComponent.getContinueTime() * 2.0F;
/* 103 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\JigokuTabiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */