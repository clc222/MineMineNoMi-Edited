/*    */ package xyz.pixelatedw.mineminenomi.abilities.mini;
/*    */ 
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class MiniMiniAbility
/*    */   extends MorphAbility2 {
/* 15 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mini_mini", new Pair[] {
/* 16 */         (Pair)ImmutablePair.of("Allows the user to become smaller than their actual size.", null)
/*    */       });
/* 18 */   public static final AbilityCore<MiniMiniAbility> INSTANCE = (new AbilityCore.Builder("Mini Mini", AbilityCategory.DEVIL_FRUITS, MiniMiniAbility::new))
/* 19 */     .addDescriptionLine(DESCRIPTION)
/* 20 */     .build();
/*    */   
/*    */   public MiniMiniAbility(AbilityCore<MiniMiniAbility> core) {
/* 23 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public MorphInfo getTransformation() {
/* 28 */     return (MorphInfo)ModMorphs.MINI.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mini\MiniMiniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */