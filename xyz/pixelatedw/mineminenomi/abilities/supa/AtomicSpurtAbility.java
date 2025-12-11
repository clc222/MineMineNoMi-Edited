/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.SlidingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class AtomicSpurtAbility
/*    */   extends SlidingAbility {
/* 15 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "atomic_spurt", new Pair[] {
/* 16 */         (Pair)ImmutablePair.of("Allows the user to skate around using bladed feet", null)
/*    */       });
/*    */   
/*    */   private static final double MAX_SPEED = 0.5D;
/*    */   private static final double SLIDE_POWER = 1.6D;
/* 21 */   public static final AbilityCore<AtomicSpurtAbility> INSTANCE = (new AbilityCore.Builder("Atomic Spurt", AbilityCategory.DEVIL_FRUITS, AtomicSpurtAbility::new))
/* 22 */     .addDescriptionLine(DESCRIPTION)
/* 23 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, ContinuousComponent.getTooltip()
/* 24 */       }).build();
/*    */ 
/*    */   
/*    */   public AtomicSpurtAbility(AbilityCore<AtomicSpurtAbility> core) {
/* 28 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMaxSpeed() {
/* 33 */     return 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getSlidingPower() {
/* 38 */     return 1.6D;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\AtomicSpurtAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */