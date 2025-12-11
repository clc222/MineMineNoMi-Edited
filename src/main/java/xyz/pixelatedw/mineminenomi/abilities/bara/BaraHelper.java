/*    */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class BaraHelper {
/*    */   public static AbilityUseResult hasLimbs(LivingEntity entity, IAbility ability) {
/* 12 */     if (entity.func_70644_a((Effect)ModEffects.NO_HANDS.get())) {
/* 13 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NO_LIMBS);
/*    */     }
/*    */     
/* 16 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */