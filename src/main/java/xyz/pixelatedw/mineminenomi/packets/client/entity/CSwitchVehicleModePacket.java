/*    */ package xyz.pixelatedw.mineminenomi.packets.client.entity;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IVehicleAltMode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CSwitchVehicleModePacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static CSwitchVehicleModePacket decode(PacketBuffer buffer) {
/* 20 */     CSwitchVehicleModePacket msg = new CSwitchVehicleModePacket();
/* 21 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CSwitchVehicleModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 25 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 26 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             if (player != null && player.func_70089_S() && player.func_184218_aH() && player.func_184187_bx().func_184179_bs().equals(player) && player.func_184187_bx() instanceof IVehicleAltMode) {
/*    */               ((IVehicleAltMode)player.func_184187_bx()).changeVehicleMode();
/*    */             }
/*    */           });
/*    */     }
/*    */     
/* 35 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\entity\CSwitchVehicleModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */