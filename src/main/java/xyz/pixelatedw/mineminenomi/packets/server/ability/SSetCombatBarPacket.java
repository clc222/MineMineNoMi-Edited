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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class SSetCombatBarPacket {
/*    */   private int bar;
/*    */   
/*    */   public SSetCombatBarPacket() {}
/*    */   
/*    */   public SSetCombatBarPacket(int bar) {
/* 21 */     this.bar = bar;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeInt(this.bar);
/*    */   }
/*    */   
/*    */   public static SSetCombatBarPacket decode(PacketBuffer buffer) {
/* 29 */     SSetCombatBarPacket msg = new SSetCombatBarPacket();
/*    */     
/* 31 */     msg.bar = buffer.readInt();
/*    */     
/* 33 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetCombatBarPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 38 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 41 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetCombatBarPacket message) {
/* 47 */       Minecraft mc = Minecraft.func_71410_x();
/*    */       
/* 49 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*    */       
/* 51 */       IAbilityData abilityProps = (IAbilityData)AbilityDataCapability.getLazy((LivingEntity)clientPlayerEntity).orElse(null);
/*    */       
/* 53 */       if (abilityProps == null) {
/*    */         return;
/*    */       }
/*    */       
/* 57 */       abilityProps.setCombatBarSet(message.bar);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SSetCombatBarPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */