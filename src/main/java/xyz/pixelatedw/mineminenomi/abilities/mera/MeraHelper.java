/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class MeraHelper {
/*    */   public static AbilityUseResult canUseMeraAbilities(LivingEntity entity, IAbility ability) {
/* 13 */     DaiEnkaiAbility daiEnkaiAbility = (DaiEnkaiAbility)AbilityDataCapability.get(entity).getEquippedAbility(DaiEnkaiAbility.INSTANCE);
/*    */     
/* 15 */     if (daiEnkaiAbility != null && (((ChargeComponent)daiEnkaiAbility.getComponent(ModAbilityKeys.CHARGE).get()).isCharging() || ((ContinuousComponent)daiEnkaiAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous())) {
/* 16 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_NOT_DAI_ENKAI, new Object[] { ability.getDisplayName() }));
/*    */     }
/*    */     
/* 19 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\MeraHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */