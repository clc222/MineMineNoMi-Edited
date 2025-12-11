/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
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
/*    */ import xyz.pixelatedw.mineminenomi.events.CombatModeEvents;
/*    */ 
/*    */ public class SToggleCombatModePacket
/*    */ {
/*    */   private boolean combatMode = false;
/* 19 */   private int bars = 1;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SToggleCombatModePacket(boolean combatMode, int bars) {
/* 25 */     this.combatMode = combatMode;
/* 26 */     this.bars = bars;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeBoolean(this.combatMode);
/* 32 */     buffer.writeInt(this.bars);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SToggleCombatModePacket decode(PacketBuffer buffer) {
/* 37 */     SToggleCombatModePacket msg = new SToggleCombatModePacket();
/* 38 */     msg.combatMode = buffer.readBoolean();
/* 39 */     msg.bars = buffer.readInt();
/* 40 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SToggleCombatModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 45 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 47 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public SToggleCombatModePacket() {}
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SToggleCombatModePacket message) {
/* 60 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/* 61 */       if (clientPlayerEntity == null) {
/*    */         return;
/*    */       }
/* 64 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/* 65 */       props.setCombatMode(message.combatMode);
/* 66 */       CombatModeEvents.Client.ABILITY_BARS = message.bars;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SToggleCombatModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */