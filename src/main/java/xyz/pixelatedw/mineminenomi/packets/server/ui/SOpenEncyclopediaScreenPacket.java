/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.EncyclopediaScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SOpenEncyclopediaScreenPacket
/*    */ {
/*    */   private ItemStack book;
/*    */   
/*    */   public SOpenEncyclopediaScreenPacket() {}
/*    */   
/*    */   public SOpenEncyclopediaScreenPacket(ItemStack book) {
/* 21 */     this.book = book;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.func_150788_a(this.book);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SOpenEncyclopediaScreenPacket decode(PacketBuffer buffer) {
/* 31 */     SOpenEncyclopediaScreenPacket msg = new SOpenEncyclopediaScreenPacket();
/* 32 */     msg.book = buffer.func_150791_c();
/* 33 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SOpenEncyclopediaScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 40 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenEncyclopediaScreenPacket message) {
/* 48 */       EncyclopediaScreen.open(message.book);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenEncyclopediaScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */