/*     */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.potion.Potions;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShinokuniAbility extends MorphAbility2 {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shinokuni", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Transforms into a gas giant releasing an area of effect gas near the user based on whatever %s the user holds when transforming.", new Object[] { Items.field_151068_bn })
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 600;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 300;
/*  45 */   public static final AbilityCore<ShinokuniAbility> INSTANCE = (new AbilityCore.Builder("Shinokuni", AbilityCategory.DEVIL_FRUITS, ShinokuniAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 300.0F), ContinuousComponent.getTooltip(600.0F), ChangeStatsComponent.getTooltip()
/*  48 */       }).build();
/*     */   
/*  50 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Shinokuni Reach Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/*  51 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Shinokuni Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*  52 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Shinokuni Toughness Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*  53 */   private static final AbilityAttributeModifier HEALTH_BOOST = new AbilityAttributeModifier(AttributeHelper.MORPH_HEALTH_UUID, INSTANCE, "Shinokuni Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION);
/*  54 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Shinokuni Strength Modifier", 7.0D, AttributeModifier.Operation.ADDITION);
/*     */ 
/*     */   
/*     */   public Potion effect;
/*     */ 
/*     */   
/*     */   public ShinokuniAbility(AbilityCore<ShinokuniAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     Predicate<LivingEntity> isMorphed = entity -> this.morphComponent.isMorphed();
/*     */     
/*  65 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER, isMorphed);
/*  66 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER, isMorphed);
/*  67 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE, isMorphed);
/*  68 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphed);
/*  69 */     this.statsComponent.addAttributeModifier(Attributes.field_233818_a_, (AttributeModifier)HEALTH_BOOST, isMorphed);
/*  70 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isMorphed);
/*     */     
/*  72 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*  73 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*  74 */     this.continuousComponent.addEndEvent(this::stopContinuityEvent);
/*     */   }
/*     */   
/*     */   public void startContinuityEvent(LivingEntity player, IAbility ability) {
/*  78 */     List<LivingEntity> list = WyHelper.getNearbyLiving(player.func_213303_ch(), (IWorld)player.field_70170_p, 20.0D, ModEntityPredicates.getEnemyFactions(player));
/*  79 */     this.effect = PotionUtils.func_185191_c(player.func_184614_ca());
/*     */     
/*  81 */     if (!this.effect.equals(Potions.field_185229_a)) {
/*  82 */       player.func_184614_ca().func_190920_e(player.func_184614_ca().func_190916_E() - 1);
/*  83 */       list.forEach(target -> applyEffects(player, target));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(LivingEntity player, IAbility ability) {
/*  89 */     if (this.effect == null || this.effect.equals(Potions.field_185229_a)) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     if (this.continuousComponent.getContinueTime() % 100.0F == 0.0F) {
/*     */       
/*  95 */       List<LivingEntity> list = WyHelper.getNearbyLiving(player.func_213303_ch(), (IWorld)player.field_70170_p, 20.0D, ModEntityPredicates.getEnemyFactions(player));
/*  96 */       for (LivingEntity target : list) {
/*  97 */         applyEffects(player, target);
/*     */       }
/*     */     } 
/*     */     
/* 101 */     if (this.continuousComponent.getContinueTime() % 2.0F == 0.0F) {
/* 102 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SHINOKUNI.get(), (Entity)player, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyEffects(LivingEntity entity, LivingEntity target) {
/* 107 */     for (EffectInstance inst : this.effect.func_185170_a()) {
/* 108 */       boolean isAlly = ModEntityPredicates.getFriendlyFactions(entity).test(target);
/*     */       
/* 110 */       if (isAlly && inst.func_188419_a().func_188408_i()) {
/* 111 */         target.func_195064_c(new EffectInstance(inst.func_188419_a(), inst.func_76459_b(), inst.func_76458_c()));
/*     */       }
/*     */       
/* 114 */       if (!isAlly && !inst.func_188419_a().func_188408_i()) {
/* 115 */         target.func_195064_c(new EffectInstance(inst.func_188419_a(), inst.func_76459_b(), inst.func_76458_c()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/* 121 */     int cooldown = (int)(100L + Math.round(this.continuousComponent.getContinueTime() / 3.0D));
/* 122 */     this.cooldownComponent.startCooldown(entity, cooldown);
/* 123 */     this.effect = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getContinuityHoldTime() {
/* 128 */     return 600.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 133 */     return (MorphInfo)ModMorphs.SHINOKUNI.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\ShinokuniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */