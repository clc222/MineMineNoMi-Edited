/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class StealthFootAbility
/*     */   extends Ability {
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int COOLDOWN = 100;
/*  38 */   public static final AbilityCore<StealthFootAbility> INSTANCE = (new AbilityCore.Builder("Stealth Foot", AbilityCategory.STYLE, StealthFootAbility::new))
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ChargeComponent.getTooltip(20.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
/*  40 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  41 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  42 */       }).build(); private static final int RANGE = 10;
/*     */   private static final int DAMAGE = 20;
/*  44 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addEndEvent(this::endChargeEvent);
/*  45 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  46 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  47 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   private LivingEntity target;
/*     */   
/*     */   public StealthFootAbility(AbilityCore<StealthFootAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     this.isNew = true;
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  57 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  61 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  65 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 10.0F, 2.0F);
/*  66 */     if (targets.size() > 0) {
/*  67 */       this.target = targets.get(0);
/*     */     }
/*     */     
/*  70 */     if (this.target != null) {
/*  71 */       Vector3d targetLook = VectorHelper.calculateViewVectorFromBodyRot(this.target.field_70125_A, this.target.field_70761_aq).func_216372_d(-2.0D, 0.0D, -2.0D);
/*  72 */       Vector3d newPos = this.target.func_213303_ch().func_178787_e(targetLook);
/*     */       
/*  74 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.VANISH.get(), 10, 0, false, false));
/*  75 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), (int)this.chargeComponent.getMaxChargeTime(), 0, false, false));
/*  76 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GEPPO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_() + 0.5D, entity.func_226281_cx_());
/*     */       
/*  78 */       entity.func_223102_j(newPos.field_72450_a, newPos.field_72448_b, newPos.field_72449_c);
/*  79 */       entity.func_200602_a(EntityAnchorArgument.Type.EYES, this.target.func_213303_ch().func_72441_c(0.0D, this.target.func_70047_e(), 0.0D));
/*     */       
/*  81 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     } else {
/*     */       
/*  84 */       this.cooldownComponent.startCooldown(entity, 100.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  89 */     if (!entity.field_70170_p.field_72995_K && 
/*  90 */       this.target != null && this.target.func_70089_S() && Math.abs(this.target.func_70032_d((Entity)entity)) < 5.0F) {
/*  91 */       this.animationComponent.start(entity, ModAnimations.CROSSED_ATTACK, 7);
/*  92 */       entity.func_226292_a_(Hand.MAIN_HAND, true);
/*  93 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, this.target.func_233580_cy_(), 2.0F);
/*  94 */       for (LivingEntity target : targets) {
/*  95 */         this.dealDamageComponent.hurtTarget(entity, target, 20.0F);
/*  96 */         WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_() + target.func_70047_e(), target.func_226281_cx_());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 101 */     this.target = null;
/* 102 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\StealthFootAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */