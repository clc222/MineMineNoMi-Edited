/*    */ package xyz.pixelatedw.mineminenomi.packets.server.entities;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ 
/*    */ public class SUnpinCameraPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static SUnpinCameraPacket decode(PacketBuffer buffer) {
/* 21 */     SUnpinCameraPacket msg = new SUnpinCameraPacket();
/* 22 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUnpinCameraPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 26 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 27 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 31 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUnpinCameraPacket message) {
/* 37 */       Minecraft mc = Minecraft.func_71410_x();
/* 38 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 40 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 41 */       if (props == null) {
/*    */         return;
/*    */       }
/*    */       
/* 45 */       props.unpinCamera();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\entities\SUnpinCameraPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */