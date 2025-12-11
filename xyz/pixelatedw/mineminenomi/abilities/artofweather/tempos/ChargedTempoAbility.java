/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public abstract class ChargedTempoAbility extends TempoAbility {
/*    */   static {
/* 11 */     CHARGED_TEMPO_DESCRIPTION = ((entity, ability) -> {
/*    */         String ball1 = "";
/*    */         String ball2 = "";
/*    */         String ball3 = "";
/*    */         if (ability instanceof ChargedTempoAbility) {
/*    */           ChargedTempoAbility tempo = (ChargedTempoAbility)ability;
/*    */           ball1 = tempo.getTempoOrder()[0].toString();
/*    */           ball2 = tempo.getTempoOrder()[1].toString();
/*    */           ball3 = tempo.getTempoOrder()[2].toString();
/*    */         } 
/*    */         return (ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_CHARGED_TEMPO_COMBINATION, new Object[] { ball1, ball2, ball3 });
/*    */       });
/*    */   } public static final AbilityDescriptionLine.IDescriptionLine CHARGED_TEMPO_DESCRIPTION; public abstract WeatherBallKind[] getTempoOrder();
/*    */   public ChargedTempoAbility(AbilityCore core) {
/* 25 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ChargedTempoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */