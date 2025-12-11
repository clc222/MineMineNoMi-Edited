/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ 
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class BlueSwordAbility
/*    */   extends PassiveAbility2 {
/* 18 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "blue_sword", new Pair[] {
/* 19 */         (Pair)ImmutablePair.of("Fills a %s hilt with flamable gas, then sets it on fire to create a sword", new Object[] { ModWeapons.BLUE_SWORD })
/*    */       });
/* 21 */   public static final AbilityCore<BlueSwordAbility> INSTANCE = (new AbilityCore.Builder("Blue Sword", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, BlueSwordAbility::new))
/* 22 */     .addDescriptionLine(DESCRIPTION)
/* 23 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 24 */     .setSourceElement(SourceElement.FIRE)
/* 25 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 26 */       }).build();
/*    */   
/*    */   public BlueSwordAbility(AbilityCore<BlueSwordAbility> core) {
/* 29 */     super(core);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\BlueSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */