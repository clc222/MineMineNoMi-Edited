/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*    */ 
/*    */ public class SStartGCDPacket
/*    */ {
/*    */   private int entityId;
/*    */   
/*    */   public SStartGCDPacket() {}
/*    */   
/*    */   public SStartGCDPacket(int entityId) {
/* 22 */     this.entityId = entityId;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeInt(this.entityId);
/*    */   }
/*    */   
/*    */   public static SStartGCDPacket decode(PacketBuffer buffer) {
/* 30 */     SStartGCDPacket msg = new SStartGCDPacket();
/* 31 */     msg.entityId = buffer.readInt();
/* 32 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SStartGCDPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 37 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 41 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SStartGCDPacket message) {
/* 47 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 48 */       if (target == null || !(target instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/* 51 */       PlayerEntity player = (PlayerEntity)target;
/*    */       
/* 53 */       GCDCapability.startGCD((LivingEntity)player);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SStartGCDPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */