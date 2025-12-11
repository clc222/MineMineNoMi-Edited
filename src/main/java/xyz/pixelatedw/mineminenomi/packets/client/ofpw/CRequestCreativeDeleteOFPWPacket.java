/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ofpw;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*    */ 
/*    */ public class CRequestCreativeDeleteOFPWPacket
/*    */ {
/*    */   private ResourceLocation fruitId;
/*    */   
/*    */   public CRequestCreativeDeleteOFPWPacket() {}
/*    */   
/*    */   public CRequestCreativeDeleteOFPWPacket(ResourceLocation fruitId) {
/* 21 */     this.fruitId = fruitId;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.func_192572_a(this.fruitId);
/*    */   }
/*    */   
/*    */   public static CRequestCreativeDeleteOFPWPacket decode(PacketBuffer buffer) {
/* 29 */     CRequestCreativeDeleteOFPWPacket msg = new CRequestCreativeDeleteOFPWPacket();
/* 30 */     msg.fruitId = buffer.func_192575_l();
/* 31 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CRequestCreativeDeleteOFPWPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 35 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/* 36 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */ 
/*    */ 
/*    */             
/*    */             if (player == null) {
/*    */               return;
/*    */             }
/*    */ 
/*    */             
/*    */             OFPWWorldData worldProps = OFPWWorldData.get();
/*    */ 
/*    */             
/*    */             if (!player.func_184812_l_() || !player.func_211513_k(2)) {
/*    */               return;
/*    */             }
/*    */ 
/*    */             
/*    */             OneFruitEntry entry = worldProps.getOneFruitEntry(message.fruitId);
/*    */ 
/*    */             
/*    */             if (entry != null) {
/* 58 */               boolean isSenderOwner = (entry.getOwner().isPresent() && ((UUID)entry.getOwner().get()).equals(player.func_110124_au()));
/*    */               if (entry.getStatus() != OneFruitEntry.Status.INVENTORY || !isSenderOwner)
/*    */                 return; 
/*    */             } 
/*    */             worldProps.lostOneFruit(message.fruitId, (LivingEntity)player, "Deleted using creative inventory");
/*    */           }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ofpw\CRequestCreativeDeleteOFPWPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */