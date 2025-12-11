/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SRecalculateEyeHeightPacket
/*    */ {
/*    */   private int entityId;
/*    */   
/*    */   public SRecalculateEyeHeightPacket() {}
/*    */   
/*    */   public SRecalculateEyeHeightPacket(int entityId) {
/* 22 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.writeInt(this.entityId);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SRecalculateEyeHeightPacket decode(PacketBuffer buffer) {
/* 32 */     SRecalculateEyeHeightPacket msg = new SRecalculateEyeHeightPacket();
/* 33 */     msg.entityId = buffer.readInt();
/* 34 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SRecalculateEyeHeightPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 39 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 41 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 46 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SRecalculateEyeHeightPacket message) {
/* 54 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 56 */       if (target == null || !(target instanceof net.minecraft.entity.LivingEntity)) {
/*    */         return;
/*    */       }
/* 59 */       target.func_213323_x_();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SRecalculateEyeHeightPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */