/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCrewScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class COpenCrewScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static COpenCrewScreenPacket decode(PacketBuffer buffer) {
/* 24 */     COpenCrewScreenPacket msg = new COpenCrewScreenPacket();
/*    */     
/* 26 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(COpenCrewScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 31 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 33 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */             
/*    */             Crew crew = worldData.getCrewWithMember(serverPlayerEntity.func_110124_au());
/*    */             
/*    */             if (crew == null) {
/*    */               return;
/*    */             }
/*    */             
/*    */             WyNetwork.sendTo(new SOpenCrewScreenPacket(crew), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/* 47 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\COpenCrewScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */