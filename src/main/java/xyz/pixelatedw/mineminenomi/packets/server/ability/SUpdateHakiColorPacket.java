/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ 
/*    */ public class SUpdateHakiColorPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int color;
/*    */   
/*    */   public SUpdateHakiColorPacket() {}
/*    */   
/*    */   public SUpdateHakiColorPacket(int entityId, int color) {
/* 23 */     this.entityId = entityId;
/* 24 */     this.color = color;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.entityId);
/* 29 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public static SUpdateHakiColorPacket decode(PacketBuffer buffer) {
/* 33 */     SUpdateHakiColorPacket msg = new SUpdateHakiColorPacket();
/*    */     
/* 35 */     msg.entityId = buffer.readInt();
/* 36 */     msg.color = buffer.readInt();
/*    */     
/* 38 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateHakiColorPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 43 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 46 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateHakiColorPacket message) {
/* 52 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 54 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 58 */       IHakiData props = HakiDataCapability.get((LivingEntity)target);
/*    */       
/* 60 */       props.setHaoshokuHakiColour(message.color);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateHakiColorPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */