/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*    */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProtectionClientEvents;
/*    */ 
/*    */ public class SViewProtectionPacket
/*    */ {
/*    */   private boolean state;
/* 18 */   private Map<String, ProtectedArea> areas = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SViewProtectionPacket(boolean state, Map<String, ProtectedArea> areas) {
/* 24 */     this.state = state;
/* 25 */     this.areas = areas;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 29 */     buffer.writeBoolean(this.state);
/* 30 */     buffer.writeInt(this.areas.size());
/* 31 */     for (ProtectedArea area : this.areas.values()) {
/* 32 */       buffer.func_179255_a(area.getCenter());
/* 33 */       buffer.writeInt(area.getSize());
/* 34 */       buffer.func_180714_a(area.getLabel());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SViewProtectionPacket decode(PacketBuffer buffer) {
/* 39 */     SViewProtectionPacket msg = new SViewProtectionPacket();
/* 40 */     msg.state = buffer.readBoolean();
/* 41 */     int areas = buffer.readInt();
/* 42 */     msg.areas.clear();
/* 43 */     for (int i = 0; i < areas; i++) {
/* 44 */       BlockPos pos = buffer.func_179259_c();
/* 45 */       int size = buffer.readInt();
/* 46 */       String label = buffer.func_218666_n();
/* 47 */       ProtectedArea area = new ProtectedArea(pos, size, label);
/* 48 */       msg.areas.put(label, area);
/*    */     } 
/* 50 */     return msg;
/*    */   }
/*    */   public SViewProtectionPacket() {}
/*    */   public static void handle(SViewProtectionPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 54 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 55 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 60 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SViewProtectionPacket message) {
/* 66 */       AbilityProtectionClientEvents.CLIENT_AREAS.clear();
/* 67 */       if (message.state)
/* 68 */         AbilityProtectionClientEvents.CLIENT_AREAS.putAll(message.areas); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SViewProtectionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */