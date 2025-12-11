/*    */ package xyz.pixelatedw.mineminenomi.packets.server.trade;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SUpdateTraderOffersPacket
/*    */ {
/*    */   private int traderEntity;
/*    */   private List<TradeEntry> tradeEntries;
/*    */   private int vearthTrades;
/*    */   
/*    */   public SUpdateTraderOffersPacket() {}
/*    */   
/*    */   public SUpdateTraderOffersPacket(int traderEntity, List<TradeEntry> tradeEntries) {
/* 30 */     this.traderEntity = traderEntity;
/* 31 */     this.tradeEntries = tradeEntries;
/*    */   }
/*    */ 
/*    */   
/*    */   public SUpdateTraderOffersPacket(int traderEntity, List<TradeEntry> tradeEntries, int vearthTrades) {
/* 36 */     this.traderEntity = traderEntity;
/* 37 */     this.tradeEntries = tradeEntries;
/* 38 */     this.vearthTrades = vearthTrades;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 43 */     buffer.writeInt(this.traderEntity);
/* 44 */     buffer.writeInt(this.tradeEntries.size());
/* 45 */     for (TradeEntry entry : this.tradeEntries)
/*    */     {
/* 47 */       buffer.writeItemStack(entry.getItemStack(), false);
/*    */     }
/* 49 */     buffer.writeInt(this.vearthTrades);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SUpdateTraderOffersPacket decode(PacketBuffer buffer) {
/* 54 */     SUpdateTraderOffersPacket msg = new SUpdateTraderOffersPacket();
/* 55 */     msg.traderEntity = buffer.readInt();
/* 56 */     int size = buffer.readInt();
/* 57 */     List<TradeEntry> entries = new ArrayList<>();
/* 58 */     for (int i = 0; i < size; i++)
/*    */     {
/* 60 */       entries.add(new TradeEntry(buffer.func_150791_c()));
/*    */     }
/* 62 */     msg.tradeEntries = entries;
/* 63 */     msg.vearthTrades = buffer.readInt();
/* 64 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SUpdateTraderOffersPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 69 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 71 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 76 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateTraderOffersPacket message) {
/* 84 */       Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.traderEntity);
/* 85 */       if (entity instanceof TraderEntity) {
/*    */         
/* 87 */         ((TraderEntity)entity).setTradingItems(message.tradeEntries);
/* 88 */         if (entity instanceof SkypieanTraderEntity)
/*    */         {
/* 90 */           ((SkypieanTraderEntity)entity).setTradesLeft(message.vearthTrades);
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\trade\SUpdateTraderOffersPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */