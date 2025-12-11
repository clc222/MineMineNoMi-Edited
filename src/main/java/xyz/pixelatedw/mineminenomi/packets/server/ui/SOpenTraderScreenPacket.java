/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenTraderScreenPacket
/*    */ {
/*    */   private int traderEntity;
/*    */   
/*    */   public SOpenTraderScreenPacket() {}
/*    */   
/*    */   public SOpenTraderScreenPacket(int traderEntity) {
/* 25 */     this.traderEntity = traderEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.traderEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenTraderScreenPacket decode(PacketBuffer buffer) {
/* 35 */     SOpenTraderScreenPacket msg = new SOpenTraderScreenPacket();
/* 36 */     msg.traderEntity = buffer.readInt();
/* 37 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenTraderScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 43 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 44 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenTraderScreenPacket message) {
/* 52 */       Entity trader = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.traderEntity);
/* 53 */       if (trader instanceof TraderEntity)
/* 54 */         TraderScreen.open((TraderEntity)trader); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenTraderScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */