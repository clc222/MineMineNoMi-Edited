/*     */ package xyz.pixelatedw.mineminenomi.abilities.kilo;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KiloPress10000Ability extends Ability {
/*  40 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "10_000_kilo_press", new Pair[] {
/*  41 */         (Pair)ImmutablePair.of("Makes the user become extremely heavy, crashing down on enemies from above crushes them, damage is calculated based on the fall distance.", null)
/*     */       });
/*     */   
/*     */   private static final float HOLD_TIME = 1200.0F;
/*     */   private static final float MIN_COOLDOWN = 20.0F;
/*     */   private static final float MAX_COOLDOWN = 1220.0F;
/*     */   private static final float MIN_DAMAGE = 1.0F;
/*     */   private static final float MAX_DAMAGE = 80.0F;
/*     */   private static final float RANGE = 5.0F;
/*  50 */   public static final AbilityCore<KiloPress10000Ability> INSTANCE = (new AbilityCore.Builder("10,000 Kilo Press", AbilityCategory.DEVIL_FRUITS, KiloPress10000Ability::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*  53 */         AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 1220.0F), ContinuousComponent.getTooltip(1200.0F), DealDamageComponent.getTooltip(1.0F, 80.0F), 
/*  54 */         RangeComponent.getTooltip(5.0F, RangeComponent.RangeType.AOE), ChangeStatsComponent.getTooltip()
/*  55 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  56 */     .build();
/*     */   
/*  58 */   private static final AbilityAttributeModifier KILO_PRESS_JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), INSTANCE, "Kilo Press Jump Height Modifier", -10.0D, AttributeModifier.Operation.ADDITION);
/*  59 */   private static final AbilityAttributeModifier KILO_PRESS_KNOCKBACK = new AbilityAttributeModifier(UUID.fromString("f3597992-9268-4a40-9363-555cf06c7771"), INSTANCE, "Kilo Press Knockback Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*  60 */   private static final AbilityAttributeModifier KILO_PRESS_MOVEMENT_SPEED = new AbilityAttributeModifier(UUID.fromString("d668cefb-4e31-4e7b-842b-7a1c8de82f69"), INSTANCE, "Kilo Press Movement Speed Modifier", -0.02D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  62 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  63 */     .addStartEvent(100, this::startContinuityEvent)
/*  64 */     .addTickEvent(100, this::tickContinuityEvent)
/*  65 */     .addEndEvent(100, this::endContinuityEvent);
/*  66 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  67 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  68 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  69 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*  70 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  72 */   private double initialPosY = 0.0D;
/*     */   
/*     */   public KiloPress10000Ability(AbilityCore<KiloPress10000Ability> core) {
/*  75 */     super(core);
/*     */     
/*  77 */     this.isNew = true;
/*     */     
/*  79 */     Predicate<LivingEntity> isContinuityActive = entity -> this.continuousComponent.isContinuous();
/*  80 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.JUMP_HEIGHT, (AttributeModifier)KILO_PRESS_JUMP_HEIGHT, isContinuityActive);
/*  81 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KILO_PRESS_KNOCKBACK, isContinuityActive);
/*  82 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)KILO_PRESS_MOVEMENT_SPEED, isContinuityActive);
/*     */     
/*  84 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  86 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  90 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  94 */     this.animationComponent.start(entity, ModAnimations.KILO_PRESS, -1, user -> Boolean.valueOf((isContinuous() && !user.func_233570_aj_())));
/*  95 */     this.changeStatsComponent.applyModifiers(entity);
/*  96 */     AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, -5.0D, (entity.func_213322_ci()).field_72449_c);
/*     */     
/*  98 */     if (!entity.func_233570_aj_()) {
/*  99 */       this.initialPosY = entity.func_226278_cu_();
/*     */     } else {
/*     */       
/* 102 */       this.initialPosY = 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 107 */     if (entity.func_233570_aj_() && this.initialPosY > 0.0D && entity.func_226278_cu_() < this.initialPosY) {
/* 108 */       float damage = (float)MathHelper.func_151237_a(this.initialPosY - entity.func_226278_cu_(), 1.0D, 80.0D);
/* 109 */       if (damage > 0.0F) {
/*     */         
/* 111 */         List<LivingEntity> nearTargets = this.rangeComponent.getTargetsInArea(entity, 1.0F);
/* 112 */         for (LivingEntity target : nearTargets) {
/* 113 */           this.dealDamageComponent.hurtTarget(entity, target, damage);
/*     */         }
/*     */ 
/*     */         
/* 117 */         List<LivingEntity> farTargets = this.rangeComponent.getTargetsInArea(entity, 5.0F);
/* 118 */         farTargets.removeAll(nearTargets);
/*     */         
/* 120 */         ModDamageSource farSource = ((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).markIndirectDamage();
/* 121 */         for (LivingEntity target : farTargets) {
/* 122 */           this.dealDamageComponent.hurtTarget(entity, target, damage, (DamageSource)farSource);
/*     */         }
/* 124 */         this.initialPosY = 0.0D;
/*     */       } 
/*     */       
/* 127 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GREAT_STOMP.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 132 */     this.animationComponent.stop(entity);
/* 133 */     this.changeStatsComponent.removeModifiers(entity);
/* 134 */     float cooldown = 20.0F + this.continuousComponent.getContinueTime();
/* 135 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 139 */     if (this.continuousComponent.isContinuous() && damageSource == DamageSource.field_76379_h) {
/* 140 */       return 0.0F;
/*     */     }
/*     */     
/* 143 */     return damage;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kilo\KiloPress10000Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */