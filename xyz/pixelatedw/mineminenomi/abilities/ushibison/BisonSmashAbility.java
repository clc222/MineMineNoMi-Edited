/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushibison;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.RunningSmashAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class BisonSmashAbility extends RunningSmashAbility {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bison_smash", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Running into enemies deals damage and knocks them back.", null)
/*    */       });
/* 26 */   public static final AbilityCore<BisonSmashAbility> INSTANCE = (new AbilityCore.Builder("Bison Smash", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, BisonSmashAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 29 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, DealDamageComponent.getTooltip(2.0F), RangeComponent.getTooltip(1.5F, RangeComponent.RangeType.LINE)
/* 30 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 31 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 32 */       }).build();
/*    */   
/* 34 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.BISON_WALK.get(), new MorphInfo[0]);
/*    */   
/*    */   public BisonSmashAbility(AbilityCore<BisonSmashAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSmash(LivingEntity entity) {
/* 44 */     return this.requireMorphComponent.checkRequirements(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushibison\BisonSmashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */