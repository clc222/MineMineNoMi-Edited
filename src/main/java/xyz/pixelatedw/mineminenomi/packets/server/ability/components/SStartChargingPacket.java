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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SStartChargingPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private float chargeTime;
/*    */   
/*    */   public SStartChargingPacket() {}
/*    */   
/*    */   public SStartChargingPacket(LivingEntity entity, IAbility ability, float chargeTime) {
/* 27 */     this.entityId = entity.func_145782_y();
/* 28 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 29 */     this.chargeTime = chargeTime;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.entityId);
/* 34 */     buffer.writeInt(this.abilityId);
/* 35 */     buffer.writeFloat(this.chargeTime);
/*    */   }
/*    */   
/*    */   public static SStartChargingPacket decode(PacketBuffer buffer) {
/* 39 */     SStartChargingPacket msg = new SStartChargingPacket();
/* 40 */     msg.entityId = buffer.readInt();
/* 41 */     msg.abilityId = buffer.readInt();
/* 42 */     msg.chargeTime = buffer.readFloat();
/* 43 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SStartChargingPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 47 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 48 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 52 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SStartChargingPacket message) {
/* 58 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 59 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 63 */       LivingEntity entity = (LivingEntity)target;
/* 64 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 65 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 66 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 70 */       abl.getComponent(ModAbilityKeys.CHARGE).ifPresent(c -> c.startCharging(entity, message.chargeTime));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SStartChargingPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */