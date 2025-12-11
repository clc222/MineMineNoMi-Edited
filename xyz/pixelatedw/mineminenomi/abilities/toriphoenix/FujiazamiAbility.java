/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FujiazamiAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "fujiazami", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("While midair, the user forms a protective swirl of fire in front of them capable of blocking most attacks.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 80;
/*     */   private static final int MIN_COOLDOWN = 80;
/*     */   private static final float MAX_COOLDOWN = 200.0F;
/*  42 */   public static final AbilityCore<FujiazamiAbility> INSTANCE = (new AbilityCore.Builder("Fujiazami", AbilityCategory.DEVIL_FRUITS, FujiazamiAbility::new))
/*  43 */     .addDescriptionLine(DESCRIPTION)
/*  44 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  45 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F, 200.0F), ContinuousComponent.getTooltip(80.0F)
/*  46 */       }).build();
/*     */   
/*  48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  49 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PHOENIX_ASSAULT.get(), new MorphInfo[] { (MorphInfo)ModMorphs.PHOENIX_FLY.get() });
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  52 */   private Interval particleInterval = new Interval(2);
/*     */   
/*     */   public FujiazamiAbility(AbilityCore<FujiazamiAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.isNew = true;
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  60 */     addCanUseCheck(AbilityHelper::requiresInAir);
/*  61 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  65 */     this.continuousComponent.triggerContinuity(entity, 80.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  69 */     this.particleInterval.restartIntervalToZero();
/*  70 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  74 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.PHYSICAL_MOVING_GUARD.get(), 4, 8, false, false));
/*  75 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.REDUCED_FALL.get(), 4, 1, false, false));
/*  76 */     boolean flyForm = ((MorphInfo)ModMorphs.PHOENIX_FLY.get()).isActive(entity);
/*     */     
/*  78 */     Vector3d lookVec = entity.func_70040_Z();
/*     */     
/*  80 */     int range = flyForm ? 3 : 2;
/*  81 */     double boxSize = flyForm ? 1.25D : 0.8D;
/*  82 */     for (int i = 0; i < range * 2; i++) {
/*  83 */       double distance = i / 2.0D;
/*  84 */       Vector3d pos = new Vector3d(entity.func_226277_ct_() + lookVec.field_72450_a * distance, entity.func_226278_cu_() + entity.func_70047_e() + lookVec.field_72448_b * distance, entity.func_226281_cx_() + lookVec.field_72449_c * distance);
/*  85 */       List<Entity> targets = entity.field_70170_p.func_175674_a((Entity)entity, new AxisAlignedBB(pos.field_72450_a - boxSize, pos.field_72448_b - boxSize, pos.field_72449_c - boxSize, pos.field_72450_a + boxSize, pos.field_72448_b + boxSize * 2.0D, pos.field_72449_c + boxSize), target -> (target != entity));
/*     */       
/*  87 */       for (Entity target : targets) {
/*  88 */         if (target instanceof LivingEntity) {
/*  89 */           Vector3d speed = WyHelper.propulsion(entity, 3.0D, 3.0D);
/*  90 */           AbilityHelper.setDeltaMovement(target, speed.field_72450_a, 0.5D, speed.field_72449_c); continue;
/*     */         } 
/*  92 */         if (target instanceof net.minecraft.entity.projectile.AbstractArrowEntity || target instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/*  93 */           if (target instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)target).getDamage() > (flyForm ? 50 : 30)) {
/*     */             return;
/*     */           }
/*  96 */           target.func_70106_y();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     if (this.particleInterval.canTick() && 
/* 102 */       this.continuousComponent.getContinueTime() % 5.0F == 0.0F) {
/* 103 */       lookVec.func_216372_d(2.5D, 1.0D, 2.5D);
/* 104 */       Vector3d particlesPos = entity.func_213303_ch().func_178787_e(lookVec);
/* 105 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FUJIZAMI.get(), (Entity)entity, particlesPos.field_72450_a, entity.func_226278_cu_() + (entity.func_70047_e() / 2.0F), particlesPos.field_72449_c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 111 */     this.animationComponent.stop(entity);
/*     */     
/* 113 */     float cooldown = 80.0F + this.continuousComponent.getContinueTime() * 1.5F;
/* 114 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\FujiazamiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */