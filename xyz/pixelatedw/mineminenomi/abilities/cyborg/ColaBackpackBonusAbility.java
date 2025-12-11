/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class ColaBackpackBonusAbility extends PassiveAbility2 {
/*    */   public static final int EXTRA_COLA = 500;
/* 22 */   public static final AbilityCore<ColaBackpackBonusAbility> INSTANCE = (new AbilityCore.Builder("Cola Backpack Bonus", AbilityCategory.EQUIPMENT, AbilityType.PASSIVE, ColaBackpackBonusAbility::new))
/* 23 */     .setHidden()
/* 24 */     .setUnlockCheck(ColaBackpackBonusAbility::canUnlock)
/* 25 */     .build();
/*    */   
/*    */   public ColaBackpackBonusAbility(AbilityCore<ColaBackpackBonusAbility> ability) {
/* 28 */     super(ability);
/*    */     
/* 30 */     addRemoveEvent(this::removeEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   private void removeEvent(LivingEntity entity, IAbility ability) {
/* 35 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 36 */       IEntityStats props = EntityStatsCapability.get(entity);
/*    */       
/* 38 */       int cola = MathHelper.func_76125_a(props.getCola(), 0, props.getMaxCola() - 500);
/*    */       
/* 40 */       props.setCola(cola);
/*    */       
/* 42 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 47 */     if (ItemsHelper.hasItemInSlot(entity, EquipmentSlotType.CHEST, (Item)ModArmors.COLA_BACKPACK.get())) {
/* 48 */       return true;
/*    */     }
/*    */     
/* 51 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\ColaBackpackBonusAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */