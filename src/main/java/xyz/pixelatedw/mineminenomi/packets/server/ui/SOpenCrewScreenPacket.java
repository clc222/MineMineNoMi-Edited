/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.screens.CrewDetailsScreen;
/*    */ 
/*    */ public class SOpenCrewScreenPacket
/*    */ {
/*    */   private Crew crew;
/*    */   
/*    */   public SOpenCrewScreenPacket() {}
/*    */   
/*    */   public SOpenCrewScreenPacket(Crew crew) {
/* 20 */     this.crew = crew;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 24 */     CompoundNBT crewData = this.crew.write();
/* 25 */     buffer.func_150786_a(crewData);
/*    */   }
/*    */   
/*    */   public static SOpenCrewScreenPacket decode(PacketBuffer buffer) {
/* 29 */     SOpenCrewScreenPacket msg = new SOpenCrewScreenPacket();
/* 30 */     CompoundNBT crewData = buffer.func_150793_b();
/* 31 */     msg.crew = Crew.from(crewData);
/* 32 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenCrewScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 37 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 39 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenCrewScreenPacket message) {
/* 45 */       CrewDetailsScreen.open(message.crew);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenCrewScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */