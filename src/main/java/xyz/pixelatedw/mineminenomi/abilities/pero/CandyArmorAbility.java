/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CandyArmorAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candy_armor", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Coats the user with a hard candy armor, which boosts defense, but reduces mobility.", null)
/*    */       });
/* 32 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setTexture(ModResources.CANDY_ARMOR).setColor(WyHelper.hexToRGB("#FFFFFF99")).build();
/*    */   
/*    */   private static final int HOLD_TIME = 300;
/*    */   
/*    */   private static final int MIN_COOLDOWN = 40;
/*    */   private static final float MAX_COOLDOWN = 200.0F;
/* 38 */   public static final AbilityCore<CandyArmorAbility> INSTANCE = (new AbilityCore.Builder("Candy Armor", AbilityCategory.DEVIL_FRUITS, CandyArmorAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 200.0F), ContinuousComponent.getTooltip(300.0F), ChangeStatsComponent.getTooltip()
/* 41 */       }).build();
/*    */   
/* 43 */   private static final AbilityAttributeModifier MINING_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("bde32ad3-5bf4-4b3b-8bf1-4947afbee0ad"), INSTANCE, "Candy Armor Mining Speed Modifier", -0.05000000074505806D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 44 */   private static final AbilityAttributeModifier MOVEMENT_SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("df1e3c16-eaf5-4a65-8664-4ac073f14ed5"), INSTANCE, "Candy Armor Movement Speed Modifier", -0.05000000074505806D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/* 45 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(UUID.fromString("35c7d00c-2876-4ec5-801c-914677539ef5"), INSTANCE, "Candy Armor Armor Modifier", 18.0D, AttributeModifier.Operation.ADDITION);
/* 46 */   private static final AbilityAttributeModifier ARMOR_TOUGHNESS_MODIFIER = new AbilityAttributeModifier(UUID.fromString("bfc760c0-698d-4362-8bab-42b86ad008b9"), INSTANCE, "Candy Armor Toughness Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 49 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/* 50 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public CandyArmorAbility(AbilityCore<CandyArmorAbility> core) {
/* 53 */     super(core);
/*    */     
/* 55 */     this.isNew = true;
/* 56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 58 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.MINING_SPEED, (AttributeModifier)MINING_SPEED_MODIFIER);
/* 59 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)MOVEMENT_SPEED_MODIFIER);
/* 60 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 61 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233827_j_, (AttributeModifier)ARMOR_TOUGHNESS_MODIFIER);
/*    */     
/* 63 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 67 */     this.continuousComponent.triggerContinuity(entity, 300.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 71 */     this.changeStatsComponent.applyModifiers(entity);
/* 72 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 76 */     this.changeStatsComponent.removeModifiers(entity);
/* 77 */     this.skinOverlayComponent.hideAll(entity);
/*    */     
/* 79 */     float cooldown = Math.max(40.0F, this.continuousComponent.getContinueTime() / 1.5F);
/* 80 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyArmorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */