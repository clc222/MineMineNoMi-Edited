/*    */ package xyz.pixelatedw.mineminenomi.packets.server.quest;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.world.ClientWorld;
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
/*    */ 
/*    */ public class SDespawnQuestObjectivePacket
/*    */ {
/*    */   private UUID ownerUUID;
/*    */   private int targetId;
/*    */   
/*    */   public SDespawnQuestObjectivePacket() {}
/*    */   
/*    */   public SDespawnQuestObjectivePacket(UUID uuid, int targetId) {
/* 27 */     this.ownerUUID = uuid;
/* 28 */     this.targetId = targetId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.func_179252_a(this.ownerUUID);
/* 34 */     buffer.writeInt(this.targetId);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SDespawnQuestObjectivePacket decode(PacketBuffer buffer) {
/* 39 */     SDespawnQuestObjectivePacket msg = new SDespawnQuestObjectivePacket();
/* 40 */     msg.ownerUUID = buffer.func_179253_g();
/* 41 */     msg.targetId = buffer.readInt();
/* 42 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SDespawnQuestObjectivePacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SDespawnQuestObjectivePacket message) {
/* 62 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/* 63 */       ClientWorld clientWorld = (Minecraft.func_71410_x()).field_71441_e;
/* 64 */       Entity target = clientWorld.func_73045_a(message.targetId);
/*    */       
/* 66 */       if (clientPlayerEntity.func_110124_au().equals(message.ownerUUID)) {
/*    */         return;
/*    */       }
/* 69 */       target.func_70106_y();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\quest\SDespawnQuestObjectivePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */