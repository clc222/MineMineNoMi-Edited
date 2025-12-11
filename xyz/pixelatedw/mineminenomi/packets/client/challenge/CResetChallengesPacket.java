/*    */ package xyz.pixelatedw.mineminenomi.packets.client.challenge;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CResetChallengesPacket
/*    */ {
/*    */   private String category;
/*    */   
/*    */   public CResetChallengesPacket() {}
/*    */   
/*    */   public CResetChallengesPacket(String category) {
/* 17 */     this.category = category;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 22 */     buffer.writeInt(this.category.length());
/* 23 */     buffer.func_180714_a(this.category);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CResetChallengesPacket decode(PacketBuffer buffer) {
/* 28 */     CResetChallengesPacket msg = new CResetChallengesPacket();
/* 29 */     int len = buffer.readInt();
/* 30 */     msg.category = buffer.func_150789_c(len);
/* 31 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CResetChallengesPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 38 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */           
/*    */           });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\challenge\CResetChallengesPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */