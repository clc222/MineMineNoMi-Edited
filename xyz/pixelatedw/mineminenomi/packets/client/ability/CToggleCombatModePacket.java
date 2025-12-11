/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SToggleCombatModePacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CToggleCombatModePacket
/*    */ {
/*    */   private boolean combatMode = false;
/*    */   
/*    */   public CToggleCombatModePacket(boolean combatMode) {
/* 21 */     this.combatMode = combatMode;
/*    */   }
/*    */   public CToggleCombatModePacket() {}
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeBoolean(this.combatMode);
/*    */   }
/*    */   
/*    */   public static CToggleCombatModePacket decode(PacketBuffer buffer) {
/* 29 */     CToggleCombatModePacket msg = new CToggleCombatModePacket();
/*    */     
/* 31 */     msg.combatMode = buffer.readBoolean();
/*    */     
/* 33 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CToggleCombatModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 38 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             props.setCombatMode(message.combatMode);
/*    */             
/*    */             WyNetwork.sendTo(new SToggleCombatModePacket(props.isInCombatMode(), CommonConfig.INSTANCE.getAbilityBars()), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/*    */     
/* 49 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CToggleCombatModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */