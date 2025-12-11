/*    */ package xyz.pixelatedw.mineminenomi.packets.client.entity;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CDoctorHealPacket {
/*    */   private int entityId;
/*    */   
/*    */   public CDoctorHealPacket() {}
/*    */   
/*    */   public CDoctorHealPacket(int entityId) {
/* 24 */     this.entityId = entityId;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.entityId);
/*    */   }
/*    */   
/*    */   public static CDoctorHealPacket decode(PacketBuffer buffer) {
/* 32 */     CDoctorHealPacket msg = new CDoctorHealPacket();
/* 33 */     msg.entityId = buffer.readInt();
/* 34 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CDoctorHealPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */             
/*    */             int payment = (int)(player.func_110138_aP() - player.func_110143_aJ()) * 10;
/*    */             
/*    */             if (props.getBelly() < payment) {
/*    */               return;
/*    */             }
/*    */             if (WyHelper.isInCombat((LivingEntity)player)) {
/*    */               return;
/*    */             }
/*    */             props.alterBelly(-payment, StatChangeSource.STORE);
/*    */             player.func_70691_i(player.func_110138_aP());
/*    */             player.func_195063_d(Effects.field_76436_u);
/*    */             WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*    */           });
/*    */     }
/* 58 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\entity\CDoctorHealPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */