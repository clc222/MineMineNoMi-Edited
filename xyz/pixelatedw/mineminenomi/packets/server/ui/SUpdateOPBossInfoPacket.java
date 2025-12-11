/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ClientBossExtraInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class SUpdateOPBossInfoPacket
/*    */ {
/*    */   private UUID id;
/*    */   private int totalBars;
/*    */   private int activeBars;
/*    */   
/*    */   public SUpdateOPBossInfoPacket() {}
/*    */   
/*    */   public SUpdateOPBossInfoPacket(UUID id, int total, int active) {
/* 26 */     this.id = id;
/* 27 */     this.totalBars = total;
/* 28 */     this.activeBars = active;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 32 */     buffer.func_179252_a(this.id);
/* 33 */     buffer.writeInt(this.totalBars);
/* 34 */     buffer.writeInt(this.activeBars);
/*    */   }
/*    */   
/*    */   public static SUpdateOPBossInfoPacket decode(PacketBuffer buffer) {
/* 38 */     SUpdateOPBossInfoPacket msg = new SUpdateOPBossInfoPacket();
/* 39 */     msg.id = buffer.func_179253_g();
/* 40 */     msg.totalBars = buffer.readInt();
/* 41 */     msg.activeBars = buffer.readInt();
/* 42 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateOPBossInfoPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 46 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/* 47 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
/* 48 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateOPBossInfoPacket message) {
/* 54 */       Minecraft mc = Minecraft.func_71410_x();
/* 55 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 57 */       ClientBossExtraInfo extraInfo = new ClientBossExtraInfo();
/* 58 */       extraInfo.setTotalBars(message.totalBars);
/* 59 */       extraInfo.setActiveBars(message.activeBars);
/*    */       
/* 61 */       IEntityStats playerData = EntityStatsCapability.get((LivingEntity)player);
/* 62 */       playerData.addExtraBossInfo(message.id, extraInfo);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SUpdateOPBossInfoPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */