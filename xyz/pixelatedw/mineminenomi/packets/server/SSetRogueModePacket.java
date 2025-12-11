/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
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
/*    */ public class SSetRogueModePacket {
/*    */   private boolean isInRogue;
/*    */   
/*    */   public SSetRogueModePacket() {}
/*    */   
/*    */   public SSetRogueModePacket(boolean isInRogue) {
/* 21 */     this.isInRogue = isInRogue;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeBoolean(this.isInRogue);
/*    */   }
/*    */   
/*    */   public static SSetRogueModePacket decode(PacketBuffer buffer) {
/* 29 */     SSetRogueModePacket msg = new SSetRogueModePacket();
/* 30 */     msg.isInRogue = buffer.readBoolean();
/* 31 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetRogueModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 35 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 36 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 38 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetRogueModePacket message) {
/* 44 */       Minecraft mc = Minecraft.func_71410_x();
/* 45 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 47 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 51 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 53 */       props.setRogue(message.isInRogue);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSetRogueModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */