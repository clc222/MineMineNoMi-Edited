/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.JollyRogerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ public class CUpdateJollyRogerPacket
/*    */ {
/* 18 */   private JollyRoger jollyRoger = new JollyRoger();
/*    */ 
/*    */ 
/*    */   
/*    */   public CUpdateJollyRogerPacket() {}
/*    */ 
/*    */   
/*    */   public CUpdateJollyRogerPacket(JollyRoger jollyRoger) {
/* 26 */     this.jollyRoger = jollyRoger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.func_150786_a(this.jollyRoger.write());
/*    */   }
/*    */ 
/*    */   
/*    */   public static CUpdateJollyRogerPacket decode(PacketBuffer buffer) {
/* 36 */     CUpdateJollyRogerPacket msg = new CUpdateJollyRogerPacket();
/* 37 */     msg.jollyRoger.read(buffer.func_150793_b());
/* 38 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CUpdateJollyRogerPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 45 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             UUID uuid = serverPlayerEntity.func_110124_au();
/*    */             
/*    */             ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */             
/*    */             Crew crew = worldData.getCrewWithCaptain(uuid);
/*    */             
/*    */             JollyRogerEvent.Update event = new JollyRogerEvent.Update(message.jollyRoger, crew);
/*    */             MinecraftForge.EVENT_BUS.post((Event)event);
/*    */             if (crew != null) {
/*    */               worldData.updateCrewJollyRoger(crew, message.jollyRoger);
/*    */             }
/*    */           });
/*    */     }
/* 61 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CUpdateJollyRogerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */