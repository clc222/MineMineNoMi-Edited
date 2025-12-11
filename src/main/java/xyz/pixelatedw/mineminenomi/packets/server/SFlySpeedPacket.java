/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ public class SFlySpeedPacket
/*    */ {
/*    */   private float speed;
/*    */   
/*    */   public SFlySpeedPacket() {}
/*    */   
/*    */   public SFlySpeedPacket(float speed) {
/* 21 */     this.speed = speed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeFloat(this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SFlySpeedPacket decode(PacketBuffer buffer) {
/* 31 */     SFlySpeedPacket msg = new SFlySpeedPacket();
/* 32 */     msg.speed = buffer.readFloat();
/* 33 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SFlySpeedPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 40 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 45 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SFlySpeedPacket message) {
/* 53 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*    */       
/* 55 */       ((PlayerEntity)clientPlayerEntity).field_71075_bZ.func_195931_a(message.speed);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SFlySpeedPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */