/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SAnimeScreamPacket
/*    */ {
/*    */   private int entityId;
/*    */   private String message;
/*    */   
/*    */   public SAnimeScreamPacket() {}
/*    */   
/*    */   public SAnimeScreamPacket(int entityId, String message) {
/* 25 */     this.entityId = entityId;
/* 26 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeInt(this.entityId);
/* 32 */     buffer.writeInt(this.message.length());
/* 33 */     buffer.func_211400_a(this.message, this.message.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public static SAnimeScreamPacket decode(PacketBuffer buffer) {
/* 38 */     SAnimeScreamPacket msg = new SAnimeScreamPacket();
/* 39 */     msg.entityId = buffer.readInt();
/* 40 */     int len = buffer.readInt();
/* 41 */     msg.message = buffer.func_150789_c(len);
/* 42 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SAnimeScreamPacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SAnimeScreamPacket message) {
/* 62 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 63 */       if (target == null || !(target instanceof net.minecraft.entity.player.PlayerEntity)) {
/*    */         return;
/*    */       }
/* 66 */       (Minecraft.func_71410_x()).field_71439_g.func_145747_a((ITextComponent)new StringTextComponent("<" + target.func_145748_c_().getString() + "> " + message.message.toUpperCase()), Util.field_240973_b_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SAnimeScreamPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */