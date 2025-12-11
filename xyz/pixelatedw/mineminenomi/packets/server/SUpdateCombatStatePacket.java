/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.events.CombatModeEvents;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class SUpdateCombatStatePacket
/*    */ {
/* 25 */   private Optional<Integer> attackerId = Optional.empty();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SUpdateCombatStatePacket(@Nullable LivingEntity attacker) {
/* 31 */     this.attackerId = (attacker == null) ? Optional.<Integer>empty() : Optional.<Integer>of(Integer.valueOf(attacker.func_145782_y()));
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 35 */     buffer.writeBoolean(this.attackerId.isPresent());
/* 36 */     if (this.attackerId.isPresent()) {
/* 37 */       buffer.writeInt(((Integer)this.attackerId.get()).intValue());
/*    */     }
/*    */   }
/*    */   
/*    */   public static SUpdateCombatStatePacket decode(PacketBuffer buffer) {
/* 42 */     SUpdateCombatStatePacket msg = new SUpdateCombatStatePacket();
/* 43 */     boolean hasAttacker = buffer.readBoolean();
/* 44 */     msg.attackerId = Optional.empty();
/* 45 */     if (hasAttacker) {
/* 46 */       msg.attackerId = Optional.of(Integer.valueOf(buffer.readInt()));
/*    */     }
/* 48 */     return msg;
/*    */   }
/*    */   public SUpdateCombatStatePacket() {}
/*    */   public static void handle(SUpdateCombatStatePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 53 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 57 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateCombatStatePacket message) {
/* 63 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*    */       
/* 65 */       boolean hasAttacker = message.attackerId.isPresent();
/*    */       
/* 67 */       if (ClientConfig.INSTANCE.hasCombatUpdateChatMessageEnabled()) {
/* 68 */         clientPlayerEntity.func_145747_a(hasAttacker ? ModI18n.INFO_ENTERING_COMBAT : ModI18n.INFO_LEAVING_COMBAT, Util.field_240973_b_);
/*    */       }
/*    */       
/* 71 */       CombatModeEvents.Client.isInCombat = hasAttacker;
/* 72 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*    */       
/* 74 */       if (hasAttacker) {
/* 75 */         Entity attacker = ((PlayerEntity)clientPlayerEntity).field_70170_p.func_73045_a(((Integer)message.attackerId.get()).intValue());
/*    */         
/* 77 */         if (attacker != null && attacker instanceof LivingEntity) {
/* 78 */           props.setInCombatCache((LivingEntity)attacker);
/*    */         }
/*    */       } else {
/*    */         
/* 82 */         props.setInCombatCache(null);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SUpdateCombatStatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */