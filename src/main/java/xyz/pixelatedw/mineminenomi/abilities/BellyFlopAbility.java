/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
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
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BellyFlopAbility extends DropHitAbility2 {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "belly_flop", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("The user jumps in the air and uses their whole body to hit all the targets while falling, targets hit on ground will also get dizzy for a few seconds.", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float RANGE = 1.5F;
/*     */   private static final float DAMAGE = 30.0F;
/*  43 */   public static final TargetsPredicate TARGET_TEST = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*     */   
/*  45 */   public static final AbilityCore<BellyFlopAbility> INSTANCE = (new AbilityCore.Builder("Belly Flop", AbilityCategory.STYLE, BellyFlopAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), RangeComponent.getTooltip(1.5F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(30.0F)
/*  48 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  49 */     .setSourceType(new SourceType[] { SourceType.PHYSICAL
/*  50 */       }).build();
/*     */   
/*  52 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  53 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  56 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(40);
/*     */   
/*     */   public BellyFlopAbility(AbilityCore<BellyFlopAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  63 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  64 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*  65 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  69 */     this.hitTrackerComponent.clearHits();
/*     */     
/*  71 */     Vector3d speed = entity.func_70040_Z();
/*  72 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, 1.5D, speed.field_72449_c);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  76 */     if (entity.field_70143_R > 0.0F && !hasLanded()) {
/*  77 */       this.animationComponent.start(entity, ModAnimations.BELLY_FLOP);
/*  78 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.5F, TARGET_TEST);
/*     */       
/*  80 */       for (LivingEntity target : targets) {
/*  81 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/*  82 */           this.dealDamageComponent.hurtTarget(entity, target, 15.0F);
/*     */         }
/*     */       } 
/*     */       
/*  86 */       for (Entity target : this.hitTrackerComponent.getHits()) {
/*  87 */         if (DevilFruitHelper.getDifferenceToFloor(target) > 1.5D) {
/*  88 */           target.func_70634_a(entity.func_226277_ct_(), entity.func_226278_cu_() - 1.0D, entity.func_226281_cx_());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  95 */     this.animationComponent.stop(entity);
/*  96 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/* 101 */     for (Entity target : this.hitTrackerComponent.getHits()) {
/* 102 */       if (target instanceof LivingEntity) {
/* 103 */         LivingEntity livingTarget = (LivingEntity)target;
/* 104 */         livingTarget.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 40, 0, false, false));
/* 105 */         this.dealDamageComponent.hurtTarget(entity, livingTarget, 15.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     List<Vector3d> positions = new ArrayList<>();
/* 110 */     int range = (int)Math.ceil(1.5D);
/*     */     
/* 112 */     for (int x = -range; x < range; x++) {
/* 113 */       for (int z = -range; z < range; z++) {
/* 114 */         double posX = entity.func_226277_ct_() + x;
/* 115 */         double posY = entity.func_226278_cu_() - 1.0D;
/* 116 */         double posZ = entity.func_226281_cx_() + z;
/* 117 */         Vector3d pos = new Vector3d(posX, posY, posZ);
/* 118 */         positions.add(pos);
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     if (positions.size() > 0) {
/* 123 */       this.details.setVecPositions(positions);
/* 124 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */     } 
/*     */     
/* 127 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\BellyFlopAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */