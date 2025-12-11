/*    */ package xyz.pixelatedw.mineminenomi.abilities.kira;
/*    */ import java.awt.Color;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class DiamondBodyAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "diamond_body", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Allows the user's body to become diamond, gaining high strength and defence.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 2400;
/*    */   private static final int MIN_COOLDOWN = 40;
/*    */   private static final int MAX_COOLDOWN = 840;
/* 37 */   public static final AbilityCore<DiamondBodyAbility> INSTANCE = (new AbilityCore.Builder("Diamond Body", AbilityCategory.DEVIL_FRUITS, DiamondBodyAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 840.0F), ContinuousComponent.getTooltip(2400.0F), ChangeStatsComponent.getTooltip()
/* 40 */       }).build();
/*    */   
/* 42 */   private static final AbilityAttributeModifier ARMOR_MODIFIER = new AbilityAttributeModifier(UUID.fromString("547a5eaa-a969-4328-9364-a40638876d54"), INSTANCE, "Diamond Body Armor Modifier", 20.0D, AttributeModifier.Operation.ADDITION);
/* 43 */   private static final AbilityAttributeModifier ARMOR_TOUGHNESS_MODIFIER = new AbilityAttributeModifier(UUID.fromString("764f6317-2d9f-4c54-8906-0201f1521212"), INSTANCE, "Diamond Body Armor Toughness Modifier", 12.0D, AttributeModifier.Operation.ADDITION);
/* 44 */   private static final AbilityAttributeModifier ATTACK_MODIFIER = new AbilityAttributeModifier(UUID.fromString("f139d3ce-ac49-42d6-bb47-e93a6b89e44b"), INSTANCE, "Diamond Body Attack Modifier", 6.0D, AttributeModifier.Operation.ADDITION);
/* 45 */   private static final AbilityAttributeModifier TOUGHNESS_MODIFIER = new AbilityAttributeModifier(UUID.fromString("7db0de61-b5a2-40d9-ab0e-42d6afb5bece"), INSTANCE, "Diamond Body Toughness Modifier", 8.0D, AttributeModifier.Operation.ADDITION);
/* 46 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.BODY).setTexture(ModResources.DIAMOND_BODY).setColor(new Color(255, 255, 255, 165)).build();
/*    */   
/* 48 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 49 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/* 50 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*    */   
/*    */   public DiamondBodyAbility(AbilityCore<DiamondBodyAbility> core) {
/* 53 */     super(core);
/*    */     
/* 55 */     this.isNew = true;
/* 56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.changeStatsComponent });
/*    */     
/* 58 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233826_i_, (AttributeModifier)ARMOR_MODIFIER);
/* 59 */     this.changeStatsComponent.addAttributeModifier(Attributes.field_233827_j_, (AttributeModifier)ARMOR_TOUGHNESS_MODIFIER);
/* 60 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)ATTACK_MODIFIER);
/* 61 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.TOUGHNESS, (AttributeModifier)TOUGHNESS_MODIFIER);
/*    */     
/* 63 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 67 */     this.continuousComponent.triggerContinuity(entity, 2400.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 71 */     this.changeStatsComponent.applyModifiers(entity);
/* 72 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.PHYSICAL_MOVING_GUARD.get(), 2, 3, false, false));
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 80 */     this.changeStatsComponent.removeModifiers(entity);
/* 81 */     this.skinOverlayComponent.hideAll(entity);
/* 82 */     float cooldown = 40.0F + this.continuousComponent.getContinueTime() / 3.0F;
/* 83 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kira\DiamondBodyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */