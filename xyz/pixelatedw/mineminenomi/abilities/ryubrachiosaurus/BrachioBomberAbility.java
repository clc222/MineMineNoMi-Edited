/*     */ package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.DropHitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BrachioBomberAbility extends DropHitAbility {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "brachio_bomber", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Dives from a high place and lands on his opponent, crushing them under the user's weight.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final float DAMAGE = 20.0F;
/*     */   private static final float RANGE = 5.5F;
/*  38 */   public static final AbilityCore<BrachioBomberAbility> INSTANCE = (new AbilityCore.Builder("Brachio Bomber", AbilityCategory.DEVIL_FRUITS, BrachioBomberAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  41 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), DealDamageComponent.getTooltip(20.0F), RangeComponent.getTooltip(5.5F, RangeComponent.RangeType.AOE)
/*  42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  43 */     .build();
/*     */   
/*  45 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  47 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  48 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.BRACHIO_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.BRACHIO_GUARD.get() });
/*     */   
/*     */   public BrachioBomberAbility(AbilityCore<BrachioBomberAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.requireMorphComponent });
/*  54 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  55 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLanding(LivingEntity entity) {
/*  60 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GREAT_STOMP.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     
/*  62 */     int size = 7;
/*  63 */     if (((MorphInfo)ModMorphs.BRACHIO_GUARD.get()).isActive(entity)) {
/*  64 */       size = 15;
/*     */     }
/*  66 */     else if (((MorphInfo)ModMorphs.BRACHIO_HEAVY.get()).isActive(entity)) {
/*  67 */       this.animationComponent.stop(entity);
/*     */     } 
/*     */     
/*  70 */     if (!entity.field_70170_p.field_72995_K) {
/*  71 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), size);
/*  72 */       explosion.setDestroyBlocks(true);
/*  73 */       explosion.setStaticDamage((size * 2));
/*  74 */       explosion.doExplosion();
/*     */     } 
/*     */     
/*  77 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 5.5F);
/*     */     
/*  79 */     for (LivingEntity target : targets) {
/*  80 */       if (this.hitTrackerComponent.canHit((Entity)target)) {
/*  81 */         this.dealDamageComponent.hurtTarget(entity, target, 20.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  91 */     double jump = 1.3D;
/*  92 */     if (((MorphInfo)ModMorphs.BRACHIO_GUARD.get()).isActive(entity)) {
/*  93 */       jump = 1.6D;
/*     */     }
/*  95 */     else if (((MorphInfo)ModMorphs.BRACHIO_HEAVY.get()).isActive(entity)) {
/*  96 */       this.animationComponent.start(entity, ModAnimations.BELLY_FLOP);
/*     */     } 
/*     */     
/*  99 */     Vector3d speed = WyHelper.propulsion(entity, 1.0D, 1.0D);
/* 100 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, jump, speed.field_72449_c);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryubrachiosaurus\BrachioBomberAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */