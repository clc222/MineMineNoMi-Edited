/*    */ package xyz.pixelatedw.mineminenomi.abilities.suke;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SkattingAbility extends Ability {
/* 19 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "skatting", new Pair[] {
/* 20 */         (Pair)ImmutablePair.of("Turns the user's entire body invisible.", null)
/*    */       });
/* 22 */   public static final AbilityCore<SkattingAbility> INSTANCE = (new AbilityCore.Builder("Skatting", AbilityCategory.DEVIL_FRUITS, SkattingAbility::new))
/* 23 */     .addDescriptionLine(DESCRIPTION)
/* 24 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, ContinuousComponent.getTooltip()
/* 25 */       }).build();
/*    */   
/* 27 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(100, this::tickContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/*    */   
/*    */   public SkattingAbility(AbilityCore<SkattingAbility> core) {
/* 30 */     super(core);
/*    */     
/* 32 */     this.isNew = true;
/* 33 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 35 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 39 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 43 */     if (!entity.func_70644_a((Effect)ModEffects.SUKE_INVISIBILITY.get())) {
/* 44 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.SUKE_INVISIBILITY.get(), 2147483647, 0, false, false, true));
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 49 */     entity.func_195063_d((Effect)ModEffects.SUKE_INVISIBILITY.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suke\SkattingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */