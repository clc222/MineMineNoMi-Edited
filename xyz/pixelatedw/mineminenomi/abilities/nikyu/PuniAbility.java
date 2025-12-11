/*     */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*     */ 
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PuniAbility
/*     */   extends Ability
/*     */ {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "puni", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("The user takes a defensive posture that uses their paw pads to repel and counter enemy attacks", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 80;
/*     */   private static final int HOLD_TIME = 40;
/*     */   private static final float RANGE = 2.5F;
/*     */   private static final int DAMAGE = 15;
/*  44 */   public static final AbilityCore<PuniAbility> INSTANCE = (new AbilityCore.Builder("Puni", AbilityCategory.DEVIL_FRUITS, PuniAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F), ContinuousComponent.getTooltip(40.0F), RangeComponent.getTooltip(2.5F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(15.0F)
/*  47 */       }).build();
/*     */   
/*  49 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  51 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  52 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  53 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*     */   
/*     */   public PuniAbility(AbilityCore<PuniAbility> core) {
/*  56 */     super(core);
/*     */     
/*  58 */     this.isNew = true;
/*  59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.damageTakenComponent });
/*     */     
/*  61 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*  62 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  66 */     this.continuousComponent.triggerContinuity(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  70 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  74 */     if (this.continuousComponent.isContinuous()) {
/*  75 */       Entity attacker = damageSource.func_76364_f();
/*  76 */       if (attacker != null && attacker instanceof LivingEntity) {
/*  77 */         LivingEntity target = (LivingEntity)attacker;
/*  78 */         boolean canSeeAttacker = TargetHelper.isEntityInView(entity, (Entity)target, 60.0F);
/*  79 */         boolean lastAttackerDistance = (target.func_70032_d((Entity)entity) < 6.25F);
/*  80 */         if (canSeeAttacker && lastAttackerDistance && this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 15.0F)) {
/*  81 */           Vector3d speed = WyHelper.propulsion(entity, 6.0D, 6.0D);
/*  82 */           AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 1.5D, speed.field_72449_c);
/*  83 */           return 0.0F;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     return damage;
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  92 */     if (canUse(entity).isFail()) {
/*  93 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  97 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */     
/*  99 */     List<Entity> targets = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 2.5D, null, new Class[0]);
/*     */     
/* 101 */     for (Entity target : targets) {
/* 102 */       if (target == entity) {
/*     */         continue;
/*     */       }
/*     */       
/* 106 */       if (!TargetHelper.isEntityInView(entity, target, 60.0F)) {
/*     */         continue;
/*     */       }
/*     */       
/* 110 */       if (target instanceof net.minecraft.entity.projectile.ProjectileEntity) {
/* 111 */         AbilityHelper.setDeltaMovement(target, -(target.func_213322_ci()).field_72450_a, -(target.func_213322_ci()).field_72448_b, -(target.func_213322_ci()).field_72449_c);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 117 */     this.animationComponent.stop(entity);
/* 118 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\PuniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */