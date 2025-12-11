/*    */ package xyz.pixelatedw.mineminenomi.packets.server.randfruit;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSetRandomizedFruitsPacket
/*    */ {
/*    */   private boolean flag;
/*    */   
/*    */   public SSetRandomizedFruitsPacket() {}
/*    */   
/*    */   public SSetRandomizedFruitsPacket(boolean flag) {
/* 20 */     this.flag = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeBoolean(this.flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSetRandomizedFruitsPacket decode(PacketBuffer buffer) {
/* 30 */     SSetRandomizedFruitsPacket msg = new SSetRandomizedFruitsPacket();
/* 31 */     msg.flag = buffer.readBoolean();
/* 32 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSetRandomizedFruitsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 44 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetRandomizedFruitsPacket message) {
/* 52 */       RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT = message.flag;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\randfruit\SSetRandomizedFruitsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */