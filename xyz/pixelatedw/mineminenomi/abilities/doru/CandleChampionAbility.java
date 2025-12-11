/*     */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class CandleChampionAbility
/*     */   extends MorphAbility2
/*     */ {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candle_champion", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("The user covers themselves in a thick wax coating, creating a battle suit protecting the user and increasing their offensive capabilities.", null), 
/*  33 */         (Pair)ImmutablePair.of("While active transforms §2Doru Doru Arts: Mori§r into §2Champ Fight§r", null)
/*     */       });
/*     */   
/*     */   private static final float HOLD_TIME = 1200.0F;
/*     */   private static final float MIN_COOLDOWN = 40.0F;
/*     */   private static final float MAX_COOLDOWN = 1240.0F;
/*  39 */   public static final AbilityCore<CandleChampionAbility> INSTANCE = (new AbilityCore.Builder("Candle Champion", AbilityCategory.DEVIL_FRUITS, CandleChampionAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 1240.0F), ContinuousComponent.getTooltip(1200.0F)
/*  42 */       }).build();
/*     */   
/*  44 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STRENGTH_UUID, INSTANCE, "Candle Champion Attack Damage Modifier", 4.0D, AttributeModifier.Operation.ADDITION);
/*  45 */   private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_SPEED_UUID, INSTANCE, "Candle Champion Attack Speed Modifier", 0.10000000149011612D, AttributeModifier.Operation.ADDITION);
/*  46 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ATTACK_REACH_UUID, INSTANCE, "Candle Champion Reach Modifier", 0.3D, AttributeModifier.Operation.ADDITION);
/*  47 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_ARMOR_UUID, INSTANCE, "Candle Champion Armor Modifier", 15.0D, AttributeModifier.Operation.ADDITION);
/*  48 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = new AbilityAttributeModifier(AttributeHelper.MORPH_KNOCKBACK_RESISTANCE_UUID, INSTANCE, "Candle Champion Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*  49 */   private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Candle Champion Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   public CandleChampionAbility(AbilityCore<CandleChampionAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     this.continuousComponent.addStartEvent(100, this::startContinuityEvent);
/*  55 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*  56 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*     */     
/*  58 */     Predicate<LivingEntity> isContinuityActive = entity -> this.continuousComponent.isContinuous();
/*  59 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER, isContinuityActive);
/*  60 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)ATTACK_SPEED_MODIFIER, isContinuityActive);
/*  61 */     this.statsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER, isContinuityActive);
/*  62 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER, isContinuityActive);
/*  63 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER, isContinuityActive);
/*  64 */     this.statsComponent.addAttributeModifier(Attributes.field_233820_c_, (AttributeModifier)KNOCKBACK_RESISTANCE, isContinuityActive);
/*  65 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER, isContinuityActive);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  69 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  70 */     DoruDoruArtsMoriAbility doruArtsMori = (DoruDoruArtsMoriAbility)props.getEquippedAbility(DoruDoruArtsMoriAbility.INSTANCE);
/*  71 */     if (doruArtsMori != null) {
/*  72 */       doruArtsMori.changeToChampFight(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  77 */     if (entity.func_70027_ad()) {
/*  78 */       this.continuousComponent.increaseContinuityTime(5.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  83 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  84 */     DoruDoruArtsMoriAbility doruArtsMori = (DoruDoruArtsMoriAbility)props.getEquippedAbility(DoruDoruArtsMoriAbility.INSTANCE);
/*  85 */     if (doruArtsMori != null) {
/*  86 */       doruArtsMori.changeToDefault(entity);
/*     */     }
/*     */     
/*  89 */     float cooldown = 40.0F + this.continuousComponent.getContinueTime();
/*  90 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getContinuityHoldTime() {
/*  95 */     return 1200.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 100 */     return (MorphInfo)ModMorphs.CANDLE_CHAMPION.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleChampionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */