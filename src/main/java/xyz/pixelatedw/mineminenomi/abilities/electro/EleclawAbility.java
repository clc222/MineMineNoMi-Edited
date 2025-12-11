/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class EleclawAbility extends PunchAbility2 {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "eleclaw", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("The user coats their hands and weapons with lightning, enabling the use of other electric skills and giving the chance to stun foes.", null)
/*     */       });
/*     */   
/*     */   private static final int MAX_STACKS = 9;
/*     */   private static final int COOLDOWN = 200;
/*  43 */   public static final AbilityCore<EleclawAbility> INSTANCE = (new AbilityCore.Builder("Eleclaw", AbilityCategory.RACIAL, EleclawAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChangeStatsComponent.getTooltip(), StackComponent.getTooltip(9)
/*  46 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  47 */     .setSourceType(new SourceType[] { SourceType.PHYSICAL
/*  48 */       }).setSourceElement(SourceElement.LIGHTNING)
/*  49 */     .setUnlockCheck(EleclawAbility::canUnlock)
/*  50 */     .build();
/*     */   
/*  52 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), INSTANCE, "Eleclaw Attack Speed Modifier", 0.3499999940395355D, AttributeModifier.Operation.ADDITION);
/*     */ 
/*     */   
/*  55 */   private final StackComponent stackComponent = new StackComponent((IAbility)this, 9);
/*     */   
/*     */   public EleclawAbility(AbilityCore<EleclawAbility> core) {
/*  58 */     super(core);
/*     */     
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.stackComponent });
/*     */     
/*  62 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER);
/*     */     
/*  64 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*  65 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  69 */     if (!entity.field_70170_p.field_72995_K && (
/*  70 */       entity.func_208600_a((ITag)FluidTags.field_206959_a) || this.stackComponent.getStacks() == 0)) {
/*  71 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  77 */     this.stackComponent.revertStacksToDefault(entity, (IAbility)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchDamage() {
/*  82 */     return 5.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  87 */     if (entity.func_70681_au().nextInt(10) < 1) {
/*  88 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 10, 0, false, false, true));
/*  89 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GENERIC_LIGHTNING_USE.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */     } 
/*     */     
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/*  97 */     return entity -> this.continuousComponent.isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 102 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 107 */     return 200.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public void reduceUsage(LivingEntity entity, int number) {
/* 116 */     if (!entity.field_70170_p.field_72995_K) {
/* 117 */       SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get(entity).getEquippedAbility(SulongAbility.INSTANCE);
/* 118 */       boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/* 119 */       if (!sulongEnabled) {
/* 120 */         int newStacks = this.stackComponent.getStacks() - number;
/* 121 */         this.stackComponent.setStacks(entity, (IAbility)this, newStacks);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 127 */     IEntityStats props = EntityStatsCapability.get(user);
/* 128 */     return (props.isMink() && props.getDoriki() >= 500.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\EleclawAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */