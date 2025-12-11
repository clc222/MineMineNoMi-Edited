/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.PropelledFlightAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class PhoenixFlyPointAbility
/*    */   extends MorphAbility2 {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "phoenix_fly_point", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Transforms the user into a phoenix, which focuses on speed and healing.", null)
/*    */       });
/* 34 */   public static final AbilityCore<PhoenixFlyPointAbility> INSTANCE = (new AbilityCore.Builder("Phoenix Fly Point", AbilityCategory.DEVIL_FRUITS, PhoenixFlyPointAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 37 */       }).build();
/*    */   
/* 39 */   private static final AbilityAttributeModifier REGEN_RATE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_REGEN_RATE_UUID, INSTANCE, "Phoenix Fly Point Health Regeneration Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/* 40 */   private static final AbilityAttributeModifier FALL_DAMAGE_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_FALL_RESISTANCE_UUID, INSTANCE, "Phoenix Fly Point Fall Damage Modifier", 500.0D, AttributeModifier.Operation.ADDITION);
/* 41 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_TOUGHNESS_UUID, INSTANCE, "Phoenix Fly Point Toughness Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/*    */   public PhoenixFlyPointAbility(AbilityCore<PhoenixFlyPointAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     Predicate<LivingEntity> isMorphed = entity -> this.morphComponent.isMorphed();
/*    */     
/* 48 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.REGEN_RATE, (AttributeModifier)REGEN_RATE_MODIFIER, isMorphed);
/* 49 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_DAMAGE_MODIFIER, isMorphed);
/* 50 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER, isMorphed);
/*    */     
/* 52 */     this.continuousComponent.addStartEvent(this::onContinuityStart).addEndEvent(this::onContinuityEnd);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 56 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 60 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 62 */     if (abilityDataProps == null) {
/*    */       return;
/*    */     }
/*    */     
/* 66 */     PropelledFlightAbility flightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PhoenixFlightAbility.INSTANCE);
/*    */     
/* 68 */     if (flightAbility != null && !flightAbility.isPaused()) {
/* 69 */       PropelledFlightAbility.enableFlight((PlayerEntity)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 76 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 80 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 82 */     if (abilityDataProps == null) {
/*    */       return;
/*    */     }
/*    */     
/* 86 */     PropelledFlightAbility flightAbility = (PropelledFlightAbility)abilityDataProps.getPassiveAbility(PhoenixFlightAbility.INSTANCE);
/*    */     
/* 88 */     if (flightAbility != null) {
/* 89 */       PropelledFlightAbility.disableFlight((PlayerEntity)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 95 */     return (MorphInfo)ModMorphs.PHOENIX_FLY.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixFlyPointAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */