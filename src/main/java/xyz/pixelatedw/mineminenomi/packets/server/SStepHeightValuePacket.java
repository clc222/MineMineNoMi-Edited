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
/*    */ 
/*    */ 
/*    */ public class SStepHeightValuePacket
/*    */ {
/*    */   private float value;
/*    */   
/*    */   public SStepHeightValuePacket() {}
/*    */   
/*    */   public SStepHeightValuePacket(float value) {
/* 23 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeFloat(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SStepHeightValuePacket decode(PacketBuffer buffer) {
/* 33 */     SStepHeightValuePacket msg = new SStepHeightValuePacket();
/* 34 */     msg.value = buffer.readFloat();
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SStepHeightValuePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 47 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SStepHeightValuePacket message) {
/* 55 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*    */       
/* 57 */       ((PlayerEntity)clientPlayerEntity).field_70138_W = message.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SStepHeightValuePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */