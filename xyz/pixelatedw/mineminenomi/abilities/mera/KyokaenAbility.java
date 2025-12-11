/*     */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KyokaenAbility
/*     */   extends Ability
/*     */ {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kyokaen", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Creates a wall of fire protecting the user", null)
/*     */       });
/*     */   
/*     */   private static final float DAMAGE = 3.0F;
/*     */   private static final int ON_HOLD = 100;
/*     */   private static final int MIN_COOLDOWN = 40;
/*     */   private static final int MAX_COOLDOWN = 140;
/*  43 */   public static final AbilityCore<KyokaenAbility> INSTANCE = (new AbilityCore.Builder("Kyokaen", AbilityCategory.DEVIL_FRUITS, KyokaenAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 140.0F), ContinuousComponent.getTooltip(100.0F), DealDamageComponent.getTooltip(3.0F)
/*  46 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  47 */     .setSourceElement(SourceElement.FIRE)
/*  48 */     .build();
/*     */   
/*  50 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  51 */     .addStartEvent(this::onContinuityStart)
/*  52 */     .addTickEvent(this::onContinuityTick)
/*  53 */     .addEndEvent(this::onContinuityEnd);
/*  54 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  55 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  57 */   private final Interval particleInterval = new Interval(2);
/*  58 */   private final Interval clearHitsInterval = new Interval(20);
/*     */   
/*     */   public KyokaenAbility(AbilityCore<KyokaenAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*     */     
/*  65 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  67 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*     */     
/*  69 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  73 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  77 */     this.particleInterval.restartIntervalToZero();
/*  78 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  82 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     if (canUse(entity).isFail()) {
/*  87 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/*  90 */     int range = 2;
/*     */     
/*  92 */     double boxSize = 1.1D;
/*     */     
/*  94 */     for (int i = 0; i < range * 2; i++) {
/*  95 */       double distance = i / 2.0D;
/*     */       
/*  97 */       Vector3d lookVec = entity.func_70040_Z();
/*  98 */       Vector3d pos = new Vector3d(entity.func_226277_ct_() + lookVec.field_72450_a * distance, entity.func_226278_cu_() + entity.func_70047_e() + lookVec.field_72448_b * distance, entity.func_226281_cx_() + lookVec.field_72449_c * distance);
/*     */       
/* 100 */       AxisAlignedBB aabb = new AxisAlignedBB(pos.field_72450_a - boxSize, pos.field_72448_b - boxSize, pos.field_72449_c - boxSize, pos.field_72450_a + boxSize, pos.field_72448_b + boxSize * 2.0D, pos.field_72449_c + boxSize);
/*     */       
/* 102 */       List<Entity> targets = entity.field_70170_p.func_175674_a((Entity)entity, aabb, target -> (target != entity));
/*     */ 
/*     */ 
/*     */       
/* 106 */       for (Entity target : targets) {
/* 107 */         if (target instanceof LivingEntity) {
/* 108 */           if (!target.func_70027_ad() && this.hitTrackerComponent.canHit(target)) {
/* 109 */             AbilityHelper.setSecondsOnFireBy(target, 3, entity);
/*     */             
/* 111 */             this.dealDamageComponent.hurtTarget(entity, (LivingEntity)target, 3.0F);
/*     */           } 
/*     */           
/* 114 */           Vector3d dir = entity.func_70040_Z().func_72432_b().func_186678_a(3.0D);
/*     */           
/* 116 */           AbilityHelper.setDeltaMovement(target, dir.field_72450_a, 0.5D, dir.field_72449_c); continue;
/*     */         } 
/* 118 */         if (target instanceof net.minecraft.entity.projectile.AbstractArrowEntity || target instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile || target instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile) {
/* 119 */           target.func_70106_y(); continue;
/* 120 */         }  if (target instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/* 121 */           AbilityHelper.setDeltaMovement(target, target.func_213322_ci().func_216372_d(-1.35D, 1.0D, -1.35D));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 127 */     if (this.particleInterval.canTick()) {
/* 128 */       EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, range);
/*     */       
/* 130 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KYOKAEN.get(), (Entity)entity, trace.func_216347_e().func_82615_a(), entity.func_226278_cu_(), trace.func_216347_e().func_82616_c());
/*     */     } 
/*     */     
/* 133 */     if (this.clearHitsInterval.canTick()) {
/* 134 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 139 */     float cooldown = 40.0F + this.continuousComponent.getContinueTime();
/*     */     
/* 141 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\KyokaenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */