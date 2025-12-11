/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DamageAbsorptionAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "damage_absorption", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Allows the user to absorb all the incoming damage granting a damage buff at the end based on the total damage received.", null)
/*     */       });
/*  34 */   private static final UUID DAMAGE_BONUS_UUID = UUID.fromString("9f88f925-1627-4ae8-98e0-6e45c588d70b");
/*     */   
/*     */   private static final float HOLD_TIME = 100.0F;
/*     */   
/*     */   private static final float COOLDOWN = 300.0F;
/*     */   private static final float RANGE = 10.0F;
/*  40 */   public static final AbilityCore<DamageAbsorptionAbility> INSTANCE = (new AbilityCore.Builder("Damage Absorption", AbilityCategory.STYLE, DamageAbsorptionAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .build();
/*     */   
/*  44 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::duringContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/*  45 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   private float prevHealth;
/*     */   private int hits;
/*     */   private float receivedDamage;
/*     */   
/*     */   public DamageAbsorptionAbility(AbilityCore<DamageAbsorptionAbility> core) {
/*  53 */     super(core);
/*     */     
/*  55 */     this.isNew = true;
/*  56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  58 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  62 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  66 */     ModifiableAttributeInstance attr = entity.func_110148_a(Attributes.field_233823_f_);
/*  67 */     if (attr != null) {
/*  68 */       attr.func_188479_b(DAMAGE_BONUS_UUID);
/*     */     }
/*     */     
/*  71 */     this.hits = 0;
/*  72 */     this.prevHealth = entity.func_110143_aJ();
/*  73 */     this.receivedDamage = 0.0F;
/*     */     
/*  75 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*  76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 20, 0));
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  80 */     if (entity.field_70173_aa % 10 == 0) {
/*  81 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 20, 0));
/*     */     }
/*     */     
/*  84 */     if (entity.func_110143_aJ() < this.prevHealth) {
/*  85 */       this.hits++;
/*  86 */       this.receivedDamage += this.prevHealth - entity.func_110143_aJ();
/*  87 */       this.prevHealth = entity.func_110143_aJ();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  92 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/*  93 */     for (LivingEntity target : targets) {
/*  94 */       Vector3d dirVec = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_216372_d(3.5D, 1.0D, 3.5D);
/*  95 */       AbilityHelper.setDeltaMovement((Entity)target, dirVec.field_72450_a, 0.0D, dirVec.field_72449_c);
/*     */     } 
/*     */     
/*  98 */     if (!entity.field_70170_p.field_72995_K) {
/*  99 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BERSERKER_POWERUP.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 102 */     ModifiableAttributeInstance attr = entity.func_110148_a(Attributes.field_233823_f_);
/* 103 */     if (attr != null) {
/* 104 */       attr.func_188479_b(DAMAGE_BONUS_UUID);
/* 105 */       attr.func_233767_b_(getModifier());
/*     */     } 
/*     */     
/* 108 */     this.animationComponent.stop(entity);
/* 109 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private AttributeModifier getModifier() {
/* 113 */     this.receivedDamage = Math.min(this.receivedDamage, 30.0F) / 5.0F;
/* 114 */     return new AttributeModifier(DAMAGE_BONUS_UUID, "Damage Absorption Bonus", this.receivedDamage, AttributeModifier.Operation.ADDITION);
/*     */   }
/*     */   
/*     */   public int getHits() {
/* 118 */     return this.hits;
/*     */   }
/*     */   
/*     */   public float getReceivedDamage() {
/* 122 */     return this.receivedDamage;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\DamageAbsorptionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */