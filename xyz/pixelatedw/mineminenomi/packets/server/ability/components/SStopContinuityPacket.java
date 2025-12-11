/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ 
/*    */ public class SStopContinuityPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   
/*    */   public SStopContinuityPacket() {}
/*    */   
/*    */   public SStopContinuityPacket(LivingEntity entity, IAbility ability) {
/* 27 */     this.entityId = entity.func_145782_y();
/* 28 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 32 */     buffer.writeInt(this.entityId);
/* 33 */     buffer.writeInt(this.abilityId);
/*    */   }
/*    */   
/*    */   public static SStopContinuityPacket decode(PacketBuffer buffer) {
/* 37 */     SStopContinuityPacket msg = new SStopContinuityPacket();
/* 38 */     msg.entityId = buffer.readInt();
/* 39 */     msg.abilityId = buffer.readInt();
/* 40 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SStopContinuityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 44 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 45 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 49 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SStopContinuityPacket message) {
/* 55 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 56 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 60 */       LivingEntity entity = (LivingEntity)target;
/* 61 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 62 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 63 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 67 */       abl.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(c -> c.stopContinuity(entity));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SStopContinuityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */