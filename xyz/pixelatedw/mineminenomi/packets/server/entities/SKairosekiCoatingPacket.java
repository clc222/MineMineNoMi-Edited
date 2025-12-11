/*    */ package xyz.pixelatedw.mineminenomi.packets.server.entities;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ 
/*    */ public class SKairosekiCoatingPacket {
/*    */   private int entityId;
/*    */   private int coatingLevel;
/*    */   
/*    */   public SKairosekiCoatingPacket() {}
/*    */   
/*    */   public SKairosekiCoatingPacket(int entityId, int coatingLevel) {
/* 22 */     this.entityId = entityId;
/* 23 */     this.coatingLevel = coatingLevel;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.writeInt(this.entityId);
/* 28 */     buffer.writeInt(this.coatingLevel);
/*    */   }
/*    */   
/*    */   public static SKairosekiCoatingPacket decode(PacketBuffer buffer) {
/* 32 */     SKairosekiCoatingPacket msg = new SKairosekiCoatingPacket();
/* 33 */     msg.entityId = buffer.readInt();
/* 34 */     msg.coatingLevel = buffer.readInt();
/* 35 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SKairosekiCoatingPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 39 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 40 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 44 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SKairosekiCoatingPacket message) {
/* 50 */       ClientWorld clientWorld = (Minecraft.func_71410_x()).field_71441_e;
/* 51 */       Entity entity = clientWorld.func_73045_a(message.entityId);
/* 52 */       if (entity == null) {
/*    */         return;
/*    */       }
/*    */       
/* 56 */       IKairosekiCoating coatingData = (IKairosekiCoating)KairosekiCoatingCapability.get(entity).orElse(null);
/* 57 */       if (coatingData == null) {
/*    */         return;
/*    */       }
/*    */       
/* 61 */       coatingData.setCoatingLevel(message.coatingLevel);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SKairosekiCoatingPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */