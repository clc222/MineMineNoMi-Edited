/*    */ package xyz.pixelatedw.mineminenomi.packets.client;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenAbilitySelectionScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CRequestSyncAbilityDataPacket
/*    */ {
/*    */   private boolean openScreen;
/*    */   
/*    */   public CRequestSyncAbilityDataPacket(boolean openScreen) {
/* 22 */     this.openScreen = openScreen;
/*    */   }
/*    */   public CRequestSyncAbilityDataPacket() {}
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeBoolean(this.openScreen);
/*    */   }
/*    */   
/*    */   public static CRequestSyncAbilityDataPacket decode(PacketBuffer buffer) {
/* 30 */     CRequestSyncAbilityDataPacket msg = new CRequestSyncAbilityDataPacket();
/*    */     
/* 32 */     msg.openScreen = buffer.readBoolean();
/*    */     
/* 34 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CRequestSyncAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.func_145782_y(), props), (PlayerEntity)serverPlayerEntity);
/*    */             
/*    */             if (message.openScreen) {
/*    */               WyNetwork.sendTo(new SOpenAbilitySelectionScreenPacket(CommonConfig.INSTANCE.getAbilityBars()), (PlayerEntity)serverPlayerEntity);
/*    */             }
/*    */           });
/*    */     }
/*    */     
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\CRequestSyncAbilityDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */