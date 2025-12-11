/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GrandeSablesAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "grande_sables", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Surrounds the user into a sand tornado, increasing their speed and pulling all nearby entities towards it damaging them.", null), 
/*  36 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s.", new Object[] {
/*  37 */             "§a" + Math.round(19.999998F) + "%§r" }) });
/*     */   
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int HOLD_TIME = 50;
/*     */   private static final float DAMAGE = 8.0F;
/*  43 */   public static final AbilityCore<GrandeSablesAbility> INSTANCE = (new AbilityCore.Builder("Grande Sables", AbilityCategory.DEVIL_FRUITS, GrandeSablesAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(50.0F), DealDamageComponent.getTooltip(8.0F)
/*  46 */       }).build();
/*     */   
/*  48 */   private final ContinuousComponent continuityComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  49 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  50 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  52 */   private Interval particleInterval = new Interval(15);
/*     */   
/*     */   public GrandeSablesAbility(AbilityCore<GrandeSablesAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.isNew = true;
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuityComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  60 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  61 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  63 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  67 */     this.continuityComponent.triggerContinuity(entity, 50.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  73 */     this.hitTrackerComponent.clearHits();
/*  74 */     Vector3d speed = WyHelper.propulsion(entity, 3.0D, 3.0D);
/*  75 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 2.0D, speed.field_72449_c);
/*     */     
/*  77 */     this.particleInterval.restartIntervalToZero();
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  81 */     if (this.continuityComponent.getContinueTime() < 3.0F) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     Vector3d speed = entity.func_70040_Z().func_216372_d(1.2D, 1.2D, 1.2D);
/*     */     
/*  87 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/*     */     
/*  89 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.VANISH.get(), 10, 0, false, false));
/*     */     
/*  91 */     if (this.particleInterval.canTick()) {
/*  92 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SABLES.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/*  95 */     float growthXZ = 8.0F;
/*  96 */     float growthY = 20.0F;
/*     */     
/*  98 */     AxisAlignedBB box = (new AxisAlignedBB(new BlockPos(entity.func_213303_ch()))).func_72314_b(growthXZ, growthY, growthXZ);
/*     */     
/* 100 */     for (Entity target : entity.field_70170_p.func_175647_a(Entity.class, box, e -> (e != entity))) {
/* 101 */       AbilityHelper.setDeltaMovement(target, (target.func_213322_ci()).field_72450_a + (entity.func_226277_ct_() - target.func_226277_ct_()) / 20.0D, 
/* 102 */           (target.func_213322_ci()).field_72448_b + (entity.func_226278_cu_() - target.func_226278_cu_()) / 20.0D, 
/* 103 */           (target.func_213322_ci()).field_72449_c + (entity.func_226281_cx_() - target.func_226281_cx_()) / 20.0D);
/*     */       
/* 105 */       ModDamageSource modDamageSource = ModDamageSource.causeAbilityDamage(entity, (IAbility)this, "flyIntoWall").setPiercing(1.0F);
/* 106 */       if (this.hitTrackerComponent.canHit(target) && target instanceof LivingEntity && entity.func_70032_d(target) < 2.0F) {
/* 107 */         this.dealDamageComponent.hurtTarget(entity, (LivingEntity)target, 8.0F, (DamageSource)modDamageSource);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 113 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 114 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 115 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 118 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\GrandeSablesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */