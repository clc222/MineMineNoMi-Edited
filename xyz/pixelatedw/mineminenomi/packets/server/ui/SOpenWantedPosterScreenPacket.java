/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*    */ import xyz.pixelatedw.mineminenomi.screens.WantedPosterScreen;
/*    */ 
/*    */ public class SOpenWantedPosterScreenPacket {
/*    */   private CompoundNBT wantedData;
/*    */   
/*    */   public SOpenWantedPosterScreenPacket() {}
/*    */   
/*    */   public SOpenWantedPosterScreenPacket(WantedPosterData wantedData) {
/* 19 */     this.wantedData = wantedData.write();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 23 */     buffer.func_150786_a(this.wantedData);
/*    */   }
/*    */   
/*    */   public static SOpenWantedPosterScreenPacket decode(PacketBuffer buffer) {
/* 27 */     SOpenWantedPosterScreenPacket msg = new SOpenWantedPosterScreenPacket();
/*    */     
/* 29 */     msg.wantedData = buffer.func_244273_m();
/*    */     
/* 31 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenWantedPosterScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 35 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 36 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 38 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenWantedPosterScreenPacket message) {
/* 44 */       WantedPosterData wantedPosterData = WantedPosterData.from(message.wantedData);
/* 45 */       WantedPosterScreen.open(wantedPosterData);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenWantedPosterScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */