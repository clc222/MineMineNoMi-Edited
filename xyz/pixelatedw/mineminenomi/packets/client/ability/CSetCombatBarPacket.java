/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class CSetCombatBarPacket {
/*    */   private int bar;
/*    */   
/*    */   public CSetCombatBarPacket() {}
/*    */   
/*    */   public CSetCombatBarPacket(int bar) {
/* 19 */     this.bar = bar;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 23 */     buffer.writeInt(this.bar);
/*    */   }
/*    */   
/*    */   public static CSetCombatBarPacket decode(PacketBuffer buffer) {
/* 27 */     CSetCombatBarPacket msg = new CSetCombatBarPacket();
/*    */     
/* 29 */     msg.bar = buffer.readInt();
/*    */     
/* 31 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CSetCombatBarPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 35 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 36 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             int maxBars = CommonConfig.INSTANCE.getAbilityBars();
/*    */             
/*    */             if (maxBars < message.bar + 1) {
/*    */               return;
/*    */             }
/*    */             
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityProps = (IAbilityData)AbilityDataCapability.getLazy((LivingEntity)serverPlayerEntity).orElse(null);
/*    */             
/*    */             if (abilityProps == null) {
/*    */               return;
/*    */             }
/*    */             
/*    */             abilityProps.setCombatBarSet(message.bar);
/*    */           });
/*    */     }
/*    */     
/* 55 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CSetCombatBarPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */