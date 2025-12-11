/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateHakiColorPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CSetHakiColorPacket
/*    */ {
/*    */   private int color;
/*    */   
/*    */   public CSetHakiColorPacket(int color) {
/* 20 */     this.color = color;
/*    */   }
/*    */   public CSetHakiColorPacket() {}
/*    */   public void encode(PacketBuffer buffer) {
/* 24 */     buffer.writeInt(this.color);
/*    */   }
/*    */   
/*    */   public static CSetHakiColorPacket decode(PacketBuffer buffer) {
/* 28 */     CSetHakiColorPacket msg = new CSetHakiColorPacket();
/*    */     
/* 30 */     msg.color = buffer.readInt();
/*    */     
/* 32 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CSetHakiColorPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 36 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 37 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IHakiData props = HakiDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             props.setHaoshokuHakiColour(message.color);
/*    */             
/*    */             WyNetwork.sendToAllTracking(new SUpdateHakiColorPacket(serverPlayerEntity.func_145782_y(), message.color), (Entity)serverPlayerEntity);
/*    */           });
/*    */     }
/*    */     
/* 48 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CSetHakiColorPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */