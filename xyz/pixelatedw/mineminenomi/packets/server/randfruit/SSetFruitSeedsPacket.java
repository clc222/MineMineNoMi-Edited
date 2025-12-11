/*    */ package xyz.pixelatedw.mineminenomi.packets.server.randfruit;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*    */ 
/*    */ 
/*    */ public class SSetFruitSeedsPacket
/*    */ {
/* 16 */   private HashMap<Integer, Long> seeds = new HashMap<>();
/*    */ 
/*    */   
/*    */   private long worldSeed;
/*    */ 
/*    */   
/*    */   public SSetFruitSeedsPacket(HashMap<Integer, Long> seeds, long worldSeed) {
/* 23 */     this.seeds = seeds;
/* 24 */     this.worldSeed = worldSeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 29 */     buffer.writeLong(this.worldSeed);
/* 30 */     buffer.writeInt(this.seeds.entrySet().size());
/* 31 */     for (Map.Entry<Integer, Long> entry : this.seeds.entrySet()) {
/*    */       
/* 33 */       buffer.writeInt(((Integer)entry.getKey()).intValue());
/* 34 */       buffer.writeLong(((Long)entry.getValue()).longValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSetFruitSeedsPacket decode(PacketBuffer buffer) {
/* 40 */     SSetFruitSeedsPacket msg = new SSetFruitSeedsPacket();
/* 41 */     msg.worldSeed = buffer.readLong();
/* 42 */     HashMap<Integer, Long> seeds = new HashMap<>();
/* 43 */     int size = buffer.readInt();
/* 44 */     for (int i = 0; i < size; i++) {
/*    */       
/* 46 */       int key = buffer.readInt();
/* 47 */       long seed = buffer.readLong();
/* 48 */       seeds.put(Integer.valueOf(key), Long.valueOf(seed));
/*    */     } 
/* 50 */     msg.seeds = seeds;
/* 51 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSetFruitSeedsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 56 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 58 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 63 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public SSetFruitSeedsPacket() {}
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetFruitSeedsPacket message) {
/* 71 */       RandomFruitEvents.Client.FRUIT_SEEDS = message.seeds;
/* 72 */       RandomFruitEvents.Client.DIRTY = true;
/* 73 */       RandomFruitEvents.Common.SEED = message.worldSeed;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\randfruit\SSetFruitSeedsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */