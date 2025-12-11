/*    */ package xyz.pixelatedw.mineminenomi.abilities.sai;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class SaiRideableAbility extends RideableAbility {
/* 18 */   private static final ResourceLocation ICON = new ResourceLocation("mineminenomi", "textures/abilities/sai_walk_point.png");
/* 19 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sai_rideable", new Pair[] {
/* 20 */         (Pair)ImmutablePair.of("Allows other players to ride on your back.", null)
/*    */       });
/* 22 */   public static final AbilityCore<SaiRideableAbility> INSTANCE = (new AbilityCore.Builder("Sai Rideable", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, SaiRideableAbility::new))
/* 23 */     .setIcon(ICON)
/* 24 */     .addDescriptionLine(DESCRIPTION)
/* 25 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 26 */       }).build();
/*    */   
/* 28 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.SAI_WALK.get(), new MorphInfo[0]);
/*    */   
/*    */   public SaiRideableAbility(AbilityCore<SaiRideableAbility> core) {
/* 31 */     super(core);
/*    */     
/* 33 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sai\SaiRideableAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */