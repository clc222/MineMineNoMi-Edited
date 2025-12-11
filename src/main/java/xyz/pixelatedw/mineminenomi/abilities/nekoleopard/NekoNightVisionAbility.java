/*    */ package xyz.pixelatedw.mineminenomi.abilities.nekoleopard;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class NekoNightVisionAbility extends PassiveAbility2 {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "neko_night_vision", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Grants the user night vision.", null)
/*    */       });
/* 23 */   public static final AbilityCore<NekoNightVisionAbility> INSTANCE = (new AbilityCore.Builder("Night Vision", "neko_night_vision", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, NekoNightVisionAbility::new))
/* 24 */     .addDescriptionLine(DESCRIPTION)
/* 25 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 26 */       }).setHidden()
/* 27 */     .build();
/*    */   
/* 29 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.LEOPARD_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.LEOPARD_WALK.get() });
/*    */   
/*    */   public NekoNightVisionAbility(AbilityCore<NekoNightVisionAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 36 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 40 */     if (entity.field_70170_p.func_226690_K_() && (!entity.func_70644_a(Effects.field_76439_r) || entity.func_70660_b(Effects.field_76439_r).func_76459_b() <= 210))
/* 41 */       entity.func_195064_c(new EffectInstance(Effects.field_76439_r, 500, 0, false, false)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nekoleopard\NekoNightVisionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */