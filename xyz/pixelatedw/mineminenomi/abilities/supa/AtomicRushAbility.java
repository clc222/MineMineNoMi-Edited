/*     */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AtomicRushAbility extends Ability {
/*  30 */   private static final ResourceLocation ICON = new ResourceLocation("mineminenomi", "textures/abilities/atomic_spurt.png");
/*     */   
/*     */   private static final float COOLDOWN = 400.0F;
/*     */   
/*  34 */   public static final AbilityCore<AtomicRushAbility> INSTANCE = (new AbilityCore.Builder("Atomic Rush", AbilityCategory.DEVIL_FRUITS, AtomicRushAbility::new))
/*  35 */     .setIcon(ICON)
/*  36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F)
/*  37 */       }).build();
/*     */   
/*  39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  40 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  41 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  43 */   private Interval newPosInterval = new Interval(20);
/*  44 */   private Interval dashWait = new Interval(5);
/*  45 */   private int damageTimer = 0;
/*     */   private LivingEntity target;
/*     */   
/*     */   public AtomicRushAbility(AbilityCore<AtomicRushAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     this.isNew = true;
/*     */     
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  55 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  59 */     this.target = null;
/*  60 */     this.hitTrackerComponent.clearHits();
/*  61 */     this.continuousComponent.triggerContinuity(entity, WyHelper.secondsToTicks(10.0F));
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  65 */     this.dashWait.restartIntervalToZero();
/*  66 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  70 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  74 */     if (this.target != null && this.dashWait.canTick()) {
/*  75 */       this.hitTrackerComponent.clearHits();
/*  76 */       Vector3d dir = entity.func_213303_ch().func_178788_d(this.target.func_213303_ch()).func_72432_b().func_216372_d(7.0D, 0.0D, 7.0D);
/*  77 */       AbilityHelper.setDeltaMovement((Entity)entity, -dir.field_72450_a, 0.15D, -dir.field_72449_c);
/*  78 */       this.target = null;
/*  79 */       this.damageTimer = 5;
/*     */     } 
/*     */     
/*  82 */     if (this.damageTimer > 0) {
/*  83 */       this.damageTimer--;
/*  84 */       List<LivingEntity> list = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 1.6D, ModEntityPredicates.getEnemyFactions(entity));
/*  85 */       list.remove(entity);
/*  86 */       for (LivingEntity livingEntity : list) {
/*  87 */         if (this.hitTrackerComponent.canHit((Entity)livingEntity)) {
/*  88 */           livingEntity.func_70097_a((DamageSource)ModDamageSource.causeAbilityDamage(entity, (IAbility)this), 25.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     if (!this.newPosInterval.canTick()) {
/*     */       return;
/*     */     }
/*     */     
/*  97 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 40.0D, 40.0D, 40.0D, ModEntityPredicates.getEnemyFactions(entity));
/*  98 */     targets.remove(entity);
/*  99 */     if (targets.size() <= 0) {
/* 100 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 104 */     LivingEntity target = targets.stream().sorted((e1, e2) -> (int)(e1.func_195048_a(entity.func_213303_ch()) - e2.func_195048_a(entity.func_213303_ch()))).findFirst().orElse(null);
/* 105 */     if (target == null) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     EffectInstance vanishEffect = new EffectInstance((Effect)ModEffects.VANISH.get(), 5, 0);
/* 110 */     entity.func_195064_c(vanishEffect);
/* 111 */     WyHelper.sendApplyEffectToAllNearby(entity, entity.func_213303_ch(), 100, vanishEffect);
/*     */     
/* 113 */     BlockPos pos = WyHelper.findValidGroundLocation((Entity)entity, target.func_233580_cy_(), 5, 5);
/* 114 */     entity.func_223102_j(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 115 */     this.target = target;
/* 116 */     entity.func_200602_a(EntityAnchorArgument.Type.EYES, target.func_213303_ch().func_72441_c(0.0D, target.func_70047_e(), 0.0D));
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 120 */     this.animationComponent.stop(entity);
/* 121 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */   }
/*     */   
/*     */   public void setDashWaitTime(int time) {
/* 125 */     this.dashWait = new Interval(time);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\AtomicRushAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */