/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.IScreenEventReceiver;
/*    */ 
/*    */ public class SOpenChallengeGroupSelectorPacket
/*    */ {
/*    */   private int eventId;
/*    */   private List<Integer> ids;
/*    */   
/*    */   public SOpenChallengeGroupSelectorPacket() {}
/*    */   
/*    */   public SOpenChallengeGroupSelectorPacket(int eventId, List<Integer> ids) {
/* 23 */     this.eventId = eventId;
/* 24 */     this.ids = ids;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.eventId);
/* 29 */     buffer.writeInt(this.ids.size());
/* 30 */     for (Iterator<Integer> iterator = this.ids.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 31 */       buffer.writeInt(id); }
/*    */   
/*    */   }
/*    */   
/*    */   public static SOpenChallengeGroupSelectorPacket decode(PacketBuffer buffer) {
/* 36 */     SOpenChallengeGroupSelectorPacket msg = new SOpenChallengeGroupSelectorPacket();
/* 37 */     msg.eventId = buffer.readInt();
/* 38 */     int size = buffer.readInt();
/* 39 */     msg.ids = new ArrayList<>();
/* 40 */     for (int i = 0; i < size; i++) {
/* 41 */       int id = buffer.readInt();
/* 42 */       msg.ids.add(Integer.valueOf(id));
/*    */     } 
/* 44 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenChallengeGroupSelectorPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 49 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 51 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenChallengeGroupSelectorPacket message) {
/* 57 */       Minecraft mc = Minecraft.func_71410_x();
/*    */       
/* 59 */       if (!(mc.field_71462_r instanceof IScreenEventReceiver)) {
/*    */         return;
/*    */       }
/*    */       
/* 63 */       IScreenEventReceiver screen = (IScreenEventReceiver)mc.field_71462_r;
/*    */       
/* 65 */       screen.handleEvent(message.eventId, message.ids);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenChallengeGroupSelectorPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */