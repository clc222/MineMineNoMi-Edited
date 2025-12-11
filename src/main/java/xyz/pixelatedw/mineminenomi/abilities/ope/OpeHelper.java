/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ 
/*    */ public class OpeHelper {
/*    */   public static AbilityUseResult hasRoomActive(LivingEntity entity, IAbility ability) {
/* 12 */     RoomAbility roomAbility = (RoomAbility)AbilityDataCapability.get(entity).getEquippedAbility(RoomAbility.INSTANCE);
/*    */     
/* 14 */     if (roomAbility == null || !roomAbility.isEntityInRoom((Entity)entity)) {
/* 15 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, new Object[] { ability.getDisplayName() }));
/*    */     }
/*    */     
/* 18 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\OpeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */