/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.BlockPos;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.AnimationId;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShakushiAbility
/*     */   extends Ability {
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final int CHARGE_TIME = 40;
/*     */   private static final int RANGE = 10;
/*     */   private static final int DAMAGE = 30;
/*     */   private static final int STACKS = 5;
/*  47 */   private static final TargetsPredicate TARGETS_PREDICATE = new TargetsPredicate();
/*  48 */   private static final AnimationId[] HIT_ANIMATIONS = new AnimationId[] { ModAnimations.CROSSED_ATTACK, ModAnimations.BODY_ROTATION_WIDE_ARMS };
/*     */   
/*  50 */   public static final AbilityCore<ShakushiAbility> INSTANCE = (new AbilityCore.Builder("Shakushi", AbilityCategory.STYLE, ShakushiAbility::new))
/*  51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ChargeComponent.getTooltip(40.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(30.0F), StackComponent.getTooltip(5)
/*  52 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  53 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  54 */       }).build();
/*     */   
/*  56 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::tickChargeEvent).addEndEvent(this::endChargeEvent);
/*  57 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  58 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  59 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  60 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*     */   
/*     */   public ShakushiAbility(AbilityCore<ShakushiAbility> core) {
/*  63 */     super(core);
/*     */     
/*  65 */     this.isNew = true;
/*  66 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  68 */     addUseEvent(this::useEvent);
/*  69 */     addTickEvent(this::tickEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  73 */     if (this.chargeComponent.isCharging()) {
/*  74 */       this.chargeComponent.stopCharging(entity);
/*  75 */       this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */       
/*     */       return;
/*     */     } 
/*  79 */     this.stackComponent.setDefaultStacks(5);
/*  80 */     this.stackComponent.setStacks(entity, ability, 5);
/*     */     
/*  82 */     this.animationComponent.start(entity, ModAnimations.LOW_SWING_ARMS, 39);
/*  83 */     this.chargeComponent.startCharging(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void tickEvent(LivingEntity entity, IAbility ability) {
/*  87 */     if (!isCharging() && this.stackComponent.getStacks() > 0 && this.stackComponent.getStacks() < this.stackComponent.getDefaultStacks()) {
/*  88 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void tickChargeEvent(LivingEntity entity, IAbility ability) {
/*  93 */     if (this.stackComponent.getStacks() > 0 && this.stackComponent.getStacks() < this.stackComponent.getDefaultStacks() && this.chargeComponent.getChargePercentage() >= 0.5F && !entity.func_70644_a((Effect)ModEffects.VANISH.get())) {
/*  94 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.VANISH.get(), (int)this.chargeComponent.getMaxChargeTime(), 0, false, false));
/*  95 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), (int)this.chargeComponent.getMaxChargeTime(), 0, false, false));
/*     */       
/*  97 */       LivingEntity target = getNearbyTarget(entity);
/*  98 */       if (target != null) {
/*  99 */         BlockPos newPos = WyHelper.findValidGroundLocation((Entity)entity, entity.func_233580_cy_(), 5, 0);
/* 100 */         if (newPos != null) {
/* 101 */           entity.func_223102_j(newPos.func_177958_n(), newPos.func_177956_o(), newPos.func_177952_p());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 108 */     if (!entity.field_70170_p.field_72995_K) {
/* 109 */       this.stackComponent.addStacks(entity, ability, -1);
/* 110 */       entity.func_195063_d((Effect)ModEffects.VANISH.get());
/*     */       
/* 112 */       LivingEntity target = getNearbyTarget(entity);
/* 113 */       if (target != null && target.func_70089_S()) {
/*     */         
/* 115 */         Vector3d targetLook = VectorHelper.calculateViewVectorFromBodyRot(target.field_70125_A, target.field_70761_aq).func_216372_d(-2.0D, 0.0D, -2.0D);
/* 116 */         Vector3d newPos = target.func_213303_ch().func_178787_e(targetLook);
/*     */         
/* 118 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0, false, false));
/* 119 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GEPPO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_() + 0.5D, entity.func_226281_cx_());
/*     */         
/* 121 */         entity.func_223102_j(newPos.field_72450_a, newPos.field_72448_b, newPos.field_72449_c);
/* 122 */         entity.func_200602_a(EntityAnchorArgument.Type.EYES, target.func_213303_ch().func_72441_c(0.0D, target.func_70047_e(), 0.0D));
/*     */         
/* 124 */         List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, target.func_233580_cy_(), 2.0F);
/* 125 */         for (LivingEntity target2 : targets) {
/* 126 */           this.dealDamageComponent.hurtTarget(entity, target2, 30.0F);
/* 127 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)entity.field_70170_p, target2.func_226277_ct_(), target2.func_226278_cu_() + target2.func_70047_e(), target2.func_226281_cx_());
/*     */         } 
/*     */         
/* 130 */         AnimationId<?> anim = HIT_ANIMATIONS[entity.func_70681_au().nextInt(HIT_ANIMATIONS.length)];
/* 131 */         this.animationComponent.start(entity, anim, 7);
/* 132 */         entity.func_226292_a_(Hand.MAIN_HAND, true);
/*     */       } 
/*     */       
/* 135 */       if (this.stackComponent.getStacks() <= 0 || target == null) {
/* 136 */         this.stackComponent.revertStacksToDefault(entity, (IAbility)this);
/* 137 */         this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private LivingEntity getNearbyTarget(LivingEntity entity) {
/* 144 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F, TARGETS_PREDICATE);
/* 145 */     if (targets.size() > 0) {
/* 146 */       Collections.shuffle(targets);
/* 147 */       return targets.get(0);
/*     */     } 
/* 149 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ShakushiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */