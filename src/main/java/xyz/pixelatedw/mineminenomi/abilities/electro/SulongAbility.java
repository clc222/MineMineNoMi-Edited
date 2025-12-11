/*    */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SulongAbility
/*    */   extends Ability {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sulong", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("The user reveals their true power during the full moon, enhancing their physical and electrical power. While active %s stacks are not consumed.", new Object[] { AbilityHelper.mentionAbility(EleclawAbility.INSTANCE) })
/*    */       });
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final int MAX_COOLDOWN = 1200;
/* 37 */   private static final Color COLOR = WyHelper.hexToRGB("#B0E9F255");
/*    */   
/* 39 */   public static final AbilityCore<SulongAbility> INSTANCE = (new AbilityCore.Builder("Sulong", AbilityCategory.RACIAL, SulongAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 1200.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 42 */       }).setUnlockCheck(SulongAbility::canUnlock)
/* 43 */     .build();
/*    */   
/* 45 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.BODY).setColor(COLOR).build();
/* 46 */   private static final AbilityAttributeModifier SPEED_MODIFIER = new AbilityAttributeModifier(UUID.fromString("e158d542-5644-4921-9d5f-895f0b0a164c"), INSTANCE, "Sulong Speed Modifier", 1.5D, AttributeModifier.Operation.MULTIPLY_BASE);
/* 47 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), INSTANCE, "Sulong Damage Modifier", 10.0D, AttributeModifier.Operation.ADDITION);
/* 48 */   private static final AbilityAttributeModifier JUMP_MODIFIER = new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), INSTANCE, "Sulong Jump Modifier", 1.05D, AttributeModifier.Operation.ADDITION);
/* 49 */   private static final AbilityAttributeModifier FALL_RESISTANCE = new AbilityAttributeModifier(UUID.fromString("00bd47ca-63b1-4040-b942-d9857231c9da"), INSTANCE, "Sulong Fall Resistance Modifier", 6.25D, AttributeModifier.Operation.ADDITION);
/* 50 */   private static final AbilityAttributeModifier STEP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("eab680cd-a6dc-438a-99d8-46f9eb53a950"), INSTANCE, "Sulong Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 52 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::tickContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 53 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/* 54 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*    */   
/*    */   public SulongAbility(AbilityCore<SulongAbility> core) {
/* 57 */     super(core);
/*    */     
/* 59 */     this.isNew = true;
/* 60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent, (AbilityComponent)this.skinOverlayComponent });
/*    */     
/* 62 */     this.statsComponent.addAttributeModifier(Attributes.field_233821_d_, (AttributeModifier)SPEED_MODIFIER);
/* 63 */     this.statsComponent.addAttributeModifier((Attribute)ModAttributes.PUNCH_DAMAGE.get(), (AttributeModifier)STRENGTH_MODIFIER);
/* 64 */     this.statsComponent.addAttributeModifier((Attribute)ModAttributes.JUMP_HEIGHT.get(), (AttributeModifier)JUMP_MODIFIER);
/* 65 */     this.statsComponent.addAttributeModifier((Attribute)ModAttributes.FALL_RESISTANCE.get(), (AttributeModifier)FALL_RESISTANCE);
/* 66 */     this.statsComponent.addAttributeModifier((Attribute)ModAttributes.STEP_HEIGHT.get(), (AttributeModifier)STEP_HEIGHT);
/*    */     
/* 68 */     addCanUseCheck(ElectroHelper::canTransformInSulong);
/*    */     
/* 70 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 74 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 78 */     this.statsComponent.applyModifiers(entity);
/* 79 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 83 */     if (!entity.field_70170_p.field_72995_K && ElectroHelper.canTransformInSulong(entity, ability).isFail()) {
/* 84 */       this.continuousComponent.stopContinuity(entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 89 */     this.statsComponent.removeModifiers(entity);
/* 90 */     this.skinOverlayComponent.hideAll(entity);
/*    */     
/* 92 */     float cooldown = Math.max(100.0F, this.continuousComponent.getContinueTime() * 2.0F);
/* 93 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 97 */     IEntityStats props = EntityStatsCapability.get(user);
/* 98 */     return (props.isMink() && props.getDoriki() >= 1200.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\SulongAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */