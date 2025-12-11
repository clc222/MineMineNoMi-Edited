/*    */ package xyz.pixelatedw.mineminenomi.abilities.sube;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.SlidingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class SubeSubeSpurAbility extends SlidingAbility {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sube_sube_spur", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Allows the user to slide on the ground around using their feet.", null)
/*    */       });
/*    */   
/*    */   private static final double MAX_SPEED = 0.3D;
/*    */   private static final double SLIDE_POWER = 1.74D;
/* 28 */   public static final AbilityCore<SubeSubeSpurAbility> INSTANCE = (new AbilityCore.Builder("Sube Sube Spur", AbilityCategory.DEVIL_FRUITS, SubeSubeSpurAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, ContinuousComponent.getTooltip()
/* 31 */       }).build();
/*    */   
/* 33 */   private static final AbilityAttributeModifier STEP_HEIGHT_MODIFIER = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Sube Sube Defelct Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 35 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*    */   
/*    */   public SubeSubeSpurAbility(AbilityCore<SubeSubeSpurAbility> core) {
/* 38 */     super(core);
/*    */     
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.changeStatsComponent });
/*    */     
/* 42 */     this.continuousComponent.addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */     
/* 44 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT_MODIFIER);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 48 */     this.changeStatsComponent.applyModifiers(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 52 */     this.changeStatsComponent.removeModifiers(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMaxSpeed() {
/* 57 */     return 0.3D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getSlidingPower() {
/* 62 */     return 1.74D;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sube\SubeSubeSpurAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */