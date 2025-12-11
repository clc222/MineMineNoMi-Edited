/*    */ package xyz.pixelatedw.mineminenomi.packets.server.entities;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SUpdateEntityOwnerPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int ownerId;
/*    */   
/*    */   public SUpdateEntityOwnerPacket() {}
/*    */   
/*    */   public SUpdateEntityOwnerPacket(int entityId, int ownerId) {
/* 27 */     this.entityId = entityId;
/* 28 */     this.ownerId = ownerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.entityId);
/* 34 */     buffer.writeInt(this.ownerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SUpdateEntityOwnerPacket decode(PacketBuffer buffer) {
/* 39 */     SUpdateEntityOwnerPacket msg = new SUpdateEntityOwnerPacket();
/* 40 */     msg.entityId = buffer.readInt();
/* 41 */     msg.ownerId = buffer.readInt();
/* 42 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SUpdateEntityOwnerPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 47 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 49 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 54 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateEntityOwnerPacket message) {
/* 62 */       ClientWorld clientWorld = (Minecraft.func_71410_x()).field_71441_e;
/* 63 */       Entity entity = clientWorld.func_73045_a(message.entityId);
/*    */ 
/*    */       
/* 66 */       if (entity == null || !(entity instanceof HanaHandsEntity)) {
/*    */         return;
/*    */       }
/* 69 */       HanaHandsEntity clutch = (HanaHandsEntity)entity;
/* 70 */       Entity owner = clientWorld.func_73045_a(message.ownerId);
/*    */       
/* 72 */       if (owner == null || !(owner instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 75 */       clutch.setCaster((LivingEntity)owner);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SUpdateEntityOwnerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */