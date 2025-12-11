/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.SoulboundMark;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.SoulboundItemHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class SoulboundItemsEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 34 */     if (event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity && !(event.getWorld()).field_72995_K) {
/* 35 */       UUID uuid = event.getEntity().func_110124_au();
/* 36 */       ExtendedWorldData worldData = ExtendedWorldData.get();
/* 37 */       if (worldData.isOnDeathList(uuid)) {
/* 38 */         AbilityHelper.resetSpawnInvulnerability((ServerPlayerEntity)event.getEntity());
/* 39 */         if (event.getEntity().func_70097_a(ModDamageSource.SOULBOUND_DAMAGE, Float.MAX_VALUE)) {
/* 40 */           worldData.removeMark(uuid);
/*    */         }
/*    */       }
/* 43 */       else if (worldData.isOnRestoreList(uuid, SoulboundMark.RESTORE_HEART)) {
/* 44 */         EntityStatsCapability.get((LivingEntity)event.getEntity()).setHeart(true);
/* 45 */         worldData.removeMark(uuid);
/*    */       }
/* 47 */       else if (worldData.isOnRestoreList(uuid, SoulboundMark.RESTORE_DOLL)) {
/* 48 */         EntityStatsCapability.get((LivingEntity)event.getEntity()).setStrawDoll(true);
/* 49 */         worldData.removeMark(uuid);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onItemExpire(ItemExpireEvent event) {
/* 62 */     ItemEntity itemEntity = event.getEntityItem();
/* 63 */     ItemStack stack = itemEntity.func_92059_d();
/* 64 */     if (stack.func_77973_b() == ModItems.HEART.get()) {
/* 65 */       Pair<UUID, LivingEntity> owner = SoulboundItemHelper.getOwner(itemEntity.field_70170_p, stack);
/* 66 */       if (owner.getValue() != null) {
/* 67 */         EntityStatsCapability.get((LivingEntity)owner.getValue()).setHeart(true);
/*    */       } else {
/*    */         
/* 70 */         ExtendedWorldData.get().markRestore((UUID)owner.getKey(), SoulboundMark.RESTORE_HEART);
/*    */       }
/*    */     
/* 73 */     } else if (stack.func_77973_b() == ModItems.STRAW_DOLL.get()) {
/* 74 */       Pair<UUID, LivingEntity> owner = SoulboundItemHelper.getOwner(itemEntity.field_70170_p, stack);
/* 75 */       if (owner.getValue() != null) {
/* 76 */         EntityStatsCapability.get((LivingEntity)owner.getValue()).setStrawDoll(true);
/*    */       } else {
/*    */         
/* 79 */         ExtendedWorldData.get().markRestore((UUID)owner.getKey(), SoulboundMark.RESTORE_DOLL);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\SoulboundItemsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */