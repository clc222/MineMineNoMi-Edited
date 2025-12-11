/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ofpw;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.InventoryDevilFruitEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*    */ 
/*    */ public class CRequestCreativeSpawnOFPWPacket
/*    */ {
/*    */   private ResourceLocation fruitId;
/*    */   
/*    */   public CRequestCreativeSpawnOFPWPacket() {}
/*    */   
/*    */   public CRequestCreativeSpawnOFPWPacket(ResourceLocation fruitId) {
/* 24 */     this.fruitId = fruitId;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.func_192572_a(this.fruitId);
/*    */   }
/*    */   
/*    */   public static CRequestCreativeSpawnOFPWPacket decode(PacketBuffer buffer) {
/* 32 */     CRequestCreativeSpawnOFPWPacket msg = new CRequestCreativeSpawnOFPWPacket();
/* 33 */     msg.fruitId = buffer.func_192575_l();
/* 34 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CRequestCreativeSpawnOFPWPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             if (player == null)
/*    */               return; 
/*    */             OFPWWorldData worldProps = OFPWWorldData.get();
/*    */             if (!player.func_184812_l_() || !player.func_211513_k(2))
/*    */               return; 
/*    */             OneFruitEntry entry = worldProps.getOneFruitEntry(message.fruitId);
/*    */             if (entry != null && entry.getStatus() != OneFruitEntry.Status.LOST)
/*    */               return; 
/*    */             worldProps.updateOneFruit(message.fruitId, player.func_110124_au(), OneFruitEntry.Status.INVENTORY, "Spawned using creative inventory");
/*    */             InventoryDevilFruitEvent event = new InventoryDevilFruitEvent((LivingEntity)player, DevilFruitHelper.getDevilFruitItem(message.fruitId), "Spawned using creative inventory");
/*    */             MinecraftForge.EVENT_BUS.post((Event)event);
/*    */           }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ofpw\CRequestCreativeSpawnOFPWPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */