/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ 
/*     */ public class GomuGomuNoDawnWhipAbility
/*     */   extends Ability {
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gomu_gomu_no_dawn_whip", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Launches the user forward hitting everybody in their path.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 140;
/*     */   private static final int HOLD_TIME = 100;
/*     */   private static final float RANGE = 2.5F;
/*     */   private static final int DAMAGE = 30;
/*  41 */   public static final AbilityCore<GomuGomuNoDawnWhipAbility> INSTANCE = (new AbilityCore.Builder("Gomu Gomu no Dawn Whip", AbilityCategory.DEVIL_FRUITS, GomuGomuNoDawnWhipAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip(100.0F), RangeComponent.getTooltip(2.5F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(30.0F)
/*  44 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  45 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  46 */       }).setUnlockCheck(GomuGomuNoDawnWhipAbility::canUnlock)
/*  47 */     .build();
/*     */   
/*  49 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  50 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  51 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  52 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  53 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   public GomuGomuNoDawnWhipAbility(AbilityCore core) {
/*  56 */     super(core);
/*     */     
/*  58 */     this.isNew = true;
/*  59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  61 */     addCanUseCheck(this::canUse);
/*  62 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  66 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  70 */     this.hitTrackerComponent.clearHits();
/*  71 */     this.animationComponent.start(entity, ModAnimations.GOMU_DAWN_WHIP);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     if (this.continuousComponent.getContinueTime() % 20.0F == 0.0F) {
/*  76 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */     
/*  79 */     Vector3d speed = entity.func_70040_Z().func_216372_d(1.35D, 1.0D, 1.35D);
/*  80 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, (entity.func_213322_ci()).field_72448_b, speed.field_72449_c);
/*     */     
/*  82 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.5F);
/*     */     
/*  84 */     for (LivingEntity target : targets) {
/*  85 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/*  86 */         this.dealDamageComponent.hurtTarget(entity, target, 30.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  92 */     this.animationComponent.stop(entity);
/*     */     
/*  94 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/*  98 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  99 */     GearFifthAbility gearFifth = (GearFifthAbility)props.getEquippedAbility(GearFifthAbility.INSTANCE);
/* 100 */     if (gearFifth == null || !gearFifth.isContinuous()) {
/* 101 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 104 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 108 */     return DevilFruitCapability.get(user).hasAwakenedFruit();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoDawnWhipAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */