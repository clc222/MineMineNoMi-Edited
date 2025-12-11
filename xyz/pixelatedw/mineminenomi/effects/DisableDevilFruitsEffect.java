/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.DisableAbilitiesEffect;
/*    */ 
/*    */ public class DisableDevilFruitsEffect extends DisableAbilitiesEffect {
/*    */   static {
/* 10 */     DEVIL_FRUIT_ABILITIES = (abl -> (abl.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*    */   } private static final Predicate<IAbility> DEVIL_FRUIT_ABILITIES;
/*    */   public DisableDevilFruitsEffect() {
/* 13 */     super(DEVIL_FRUIT_ABILITIES);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DisableDevilFruitsEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */