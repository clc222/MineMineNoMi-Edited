/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ 
/*    */ public class ArtOfWeatherHelper {
/*    */   public static AbilityUseResult needsClimaTact(LivingEntity entity, IAbility ability) {
/* 14 */     ItemStack heldItem = entity.func_184614_ca();
/*    */     
/* 16 */     if (heldItem.func_190926_b() || !ItemsHelper.isClimaTact(heldItem)) {
/* 17 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT);
/*    */     }
/*    */     
/* 20 */     return AbilityUseResult.success();
/*    */   }
/*    */   
/*    */   public static AbilityUseResult needsSorceryClimaTact(LivingEntity entity, IAbility ability) {
/* 24 */     ItemStack heldItem = entity.func_184614_ca();
/*    */     
/* 26 */     AbilityUseResult result = needsClimaTact(entity, ability);
/* 27 */     if (result.isFail()) {
/* 28 */       return result;
/*    */     }
/*    */     
/* 31 */     if (((ClimaTactItem)heldItem.func_77973_b()).getLevel() < 3) {
/* 32 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT);
/*    */     }
/*    */     
/* 35 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\ArtOfWeatherHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */