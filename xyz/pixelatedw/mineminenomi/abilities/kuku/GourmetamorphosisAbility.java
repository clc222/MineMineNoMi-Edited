/*    */ package xyz.pixelatedw.mineminenomi.abilities.kuku;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class GourmetamorphosisAbility extends Ability {
/* 18 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gourmetamorphosis", new Pair[] {
/* 19 */         (Pair)ImmutablePair.of("Makes all items in the user's inventory edible.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 1200;
/*    */   private static final int MIN_COOLDOWN = 20;
/*    */   private static final int MAX_COOLDOWN = 620;
/* 25 */   public static final AbilityCore<GourmetamorphosisAbility> INSTANCE = (new AbilityCore.Builder("Gourmetamorphosis", AbilityCategory.DEVIL_FRUITS, GourmetamorphosisAbility::new))
/* 26 */     .addDescriptionLine(DESCRIPTION)
/* 27 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 620.0F), ContinuousComponent.getTooltip(1200.0F)
/* 28 */       }).build();
/*    */   
/* 30 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(this::endContinuityEvent);
/*    */ 
/*    */   
/*    */   public GourmetamorphosisAbility(AbilityCore<GourmetamorphosisAbility> core) {
/* 34 */     super(core);
/*    */     
/* 36 */     this.isNew = true;
/* 37 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 39 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 43 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 47 */     float cooldown = 20.0F + this.continuousComponent.getContinueTime() / 2.0F;
/* 48 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kuku\GourmetamorphosisAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */