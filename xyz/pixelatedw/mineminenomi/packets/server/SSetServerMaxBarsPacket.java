/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*    */ 
/*    */ 
/*    */ public class SSetServerMaxBarsPacket
/*    */ {
/*    */   private int bars;
/*    */   
/*    */   public SSetServerMaxBarsPacket() {}
/*    */   
/*    */   public SSetServerMaxBarsPacket(int bars) {
/* 19 */     this.bars = bars;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 23 */     buffer.writeInt(this.bars);
/*    */   }
/*    */   
/*    */   public static SSetServerMaxBarsPacket decode(PacketBuffer buffer) {
/* 27 */     SSetServerMaxBarsPacket msg = new SSetServerMaxBarsPacket();
/* 28 */     msg.bars = buffer.readInt();
/* 29 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetServerMaxBarsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 33 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 34 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 38 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetServerMaxBarsPacket message) {
/* 44 */       ModKeybindings.serverMaxBars = message.bars;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSetServerMaxBarsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */