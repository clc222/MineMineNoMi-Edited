/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.items.SakeCupItem;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class SakeEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
/* 22 */     if (!(event.getTarget() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 25 */     PlayerEntity player = event.getPlayer();
/*    */     
/* 27 */     if (player.func_184614_ca().func_77973_b() != ModItems.SAKE_BOTTLE.get()) {
/*    */       return;
/*    */     }
/* 30 */     PlayerEntity target = (PlayerEntity)event.getTarget();
/*    */     
/* 32 */     if (target.func_184614_ca().func_77973_b() != ModItems.SAKE_CUP.get()) {
/*    */       return;
/*    */     }
/* 35 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)target);
/* 36 */     props.setFaction(ModValues.PIRATE);
/* 37 */     ItemStack itemStack = target.func_184614_ca();
/* 38 */     ((SakeCupItem)itemStack.func_77973_b()).setLeader(itemStack, player);
/* 39 */     player.func_184614_ca().func_222118_a(1, (LivingEntity)player, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*    */ 
/*    */     
/* 42 */     event.setCanceled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\SakeEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */