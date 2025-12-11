/*    */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.EffectImmunityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class YomiImmunityAbility extends EffectImmunityAbility {
/* 14 */   public static final AbilityCore<YomiImmunityAbility> INSTANCE = (new AbilityCore.Builder("Yomi Immunities", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, YomiImmunityAbility::new))
/* 15 */     .setUnlockCheck(YomiImmunityAbility::canUnlock)
/* 16 */     .setHidden()
/* 17 */     .build();
/*    */   
/* 19 */   private static final EffectImmunityAbility.ImmunityInfo IMMUNITY_INFO = (new EffectImmunityAbility.ImmunityInfo()).addImmunityEffects(new Supplier[] { (Supplier)ModEffects.FROSTBITE, (Supplier)ModEffects.DIZZY, (Supplier)ModEffects.DRUNK, (Supplier)ModEffects.UNCONSCIOUS }).addImmunityEffects(new Effect[] { Effects.field_76433_i, Effects.field_76428_l, Effects.field_76438_s, Effects.field_76436_u }).addResistanceEffect((Supplier)ModEffects.FROZEN, 2).addResistanceEffect((Supplier)ModEffects.PARALYSIS, 2);
/*    */   
/*    */   public YomiImmunityAbility(AbilityCore core) {
/* 22 */     super(core, IMMUNITY_INFO);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 26 */     return ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\YomiImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */