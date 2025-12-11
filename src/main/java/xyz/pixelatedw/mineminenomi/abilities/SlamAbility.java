/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SlamAbility extends DropHitAbility2 {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "slam", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("The user jumps in the air and uses their fists or weapon to smash the target and the ground around it.", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float RANGE = 2.0F;
/*     */   private static final float DAMAGE = 20.0F;
/*  41 */   public static final TargetsPredicate TARGET_TEST = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*     */   
/*  43 */   public static final AbilityCore<SlamAbility> INSTANCE = (new AbilityCore.Builder("Slam", AbilityCategory.STYLE, SlamAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), RangeComponent.getTooltip(2.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
/*  46 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  47 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  48 */       }).build();
/*     */   
/*  50 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  51 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  52 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  54 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(40);
/*     */   
/*     */   public SlamAbility(AbilityCore<SlamAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  61 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  62 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*  63 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  67 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  69 */     Vector3d speed = entity.func_70040_Z();
/*  70 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 1.5D, speed.field_72449_c);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  74 */     if (entity.field_70143_R > 0.0F && !hasLanded()) {
/*  75 */       this.animationComponent.start(entity, ModAnimations.WEAPON_SLAM);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  80 */     this.animationComponent.stop(entity);
/*  81 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/*  86 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.0F, TARGET_TEST);
/*     */     
/*  88 */     for (LivingEntity target : targets) {
/*  89 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 20.0F)) {
/*  90 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0, false, false));
/*     */       }
/*     */     } 
/*     */     
/*  94 */     List<Vector3d> positions = new ArrayList<>();
/*  95 */     int range = (int)Math.ceil(this.rangeComponent.getRange());
/*     */     
/*  97 */     for (int x = -range; x < range; x++) {
/*  98 */       for (int z = -range; z < range; z++) {
/*  99 */         double posX = entity.func_226277_ct_() + x;
/* 100 */         double posY = entity.func_226278_cu_() - 1.0D;
/* 101 */         double posZ = entity.func_226281_cx_() + z;
/* 102 */         Vector3d pos = new Vector3d(posX, posY, posZ);
/* 103 */         positions.add(pos);
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     if (positions.size() > 0) {
/* 108 */       this.details.setVecPositions(positions);
/* 109 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */     } 
/*     */     
/* 112 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\SlamAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */