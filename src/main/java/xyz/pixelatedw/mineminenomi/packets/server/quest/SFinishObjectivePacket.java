/*    */ package xyz.pixelatedw.mineminenomi.packets.server.quest;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.SimpleSound;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SFinishObjectivePacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static SFinishObjectivePacket decode(PacketBuffer buffer) {
/* 21 */     SFinishObjectivePacket msg = new SFinishObjectivePacket();
/* 22 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SFinishObjectivePacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SFinishObjectivePacket message) {
/* 37 */       Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)SimpleSound.func_184371_a(SoundEvents.field_187604_bf, 1.0F));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\quest\SFinishObjectivePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */