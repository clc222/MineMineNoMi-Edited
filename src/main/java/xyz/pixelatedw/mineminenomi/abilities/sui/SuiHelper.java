/*    */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class SuiHelper {
/*    */   public static AbilityUseResult isFreeSwimming(LivingEntity entity, IAbility ability) {
/* 13 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 14 */     FreeSwimmingAbility freeSwimming = (FreeSwimmingAbility)props.getEquippedAbility(FreeSwimmingAbility.INSTANCE);
/* 15 */     if (freeSwimming == null || !freeSwimming.isContinuous() || !freeSwimming.isSwimming()) {
/* 16 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { ability.getCore().getLocalizedName().getString(), FreeSwimmingAbility.INSTANCE.getLocalizedName().getString() }));
/*    */     }
/*    */     
/* 19 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\SuiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */