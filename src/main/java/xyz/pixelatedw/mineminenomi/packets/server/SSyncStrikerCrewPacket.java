/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.entities.StrikerEntity;
/*    */ 
/*    */ public class SSyncStrikerCrewPacket
/*    */ {
/*    */   private int entityId;
/*    */   private Crew crew;
/*    */   
/*    */   public SSyncStrikerCrewPacket() {}
/*    */   
/*    */   public SSyncStrikerCrewPacket(StrikerEntity entity, Crew crew) {
/* 23 */     this.entityId = entity.func_145782_y();
/* 24 */     this.crew = crew;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.entityId);
/* 29 */     buffer.func_150786_a(this.crew.write());
/*    */   }
/*    */   
/*    */   public static SSyncStrikerCrewPacket decode(PacketBuffer buffer) {
/* 33 */     SSyncStrikerCrewPacket msg = new SSyncStrikerCrewPacket();
/* 34 */     msg.entityId = buffer.readInt();
/* 35 */     msg.crew = Crew.from(buffer.func_244273_m());
/* 36 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSyncStrikerCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 41 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 43 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncStrikerCrewPacket message) {
/* 49 */       Minecraft mc = Minecraft.func_71410_x();
/* 50 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 52 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 56 */       Entity entity = mc.field_71441_e.func_73045_a(message.entityId);
/* 57 */       if (entity == null) {
/*    */         return;
/*    */       }
/*    */       
/* 61 */       if (entity instanceof StrikerEntity)
/* 62 */         ((StrikerEntity)entity).setCrew(message.crew); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncStrikerCrewPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */