/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket2;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CRemoveAbilityPacket {
/*    */   public CRemoveAbilityPacket(int id) {
/* 25 */     this.slot = id;
/*    */   }
/*    */   private int slot;
/*    */   public CRemoveAbilityPacket() {}
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.slot);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CRemoveAbilityPacket decode(PacketBuffer buffer) {
/* 35 */     CRemoveAbilityPacket msg = new CRemoveAbilityPacket();
/* 36 */     msg.slot = buffer.readInt();
/* 37 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CRemoveAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 41 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             Ability ability = (Ability)abilityDataProps.getEquippedAbility(message.slot);
/*    */             
/*    */             if (ability == null) {
/*    */               return;
/*    */             }
/*    */             if (ability.hasComponent(ModAbilityKeys.COOLDOWN) && ((CooldownComponent)ability.getComponent(ModAbilityKeys.COOLDOWN).get()).isOnCooldown()) {
/*    */               return;
/*    */             }
/*    */             if (ability.hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)ability.getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*    */               return;
/*    */             }
/*    */             if (ability.hasComponent(ModAbilityKeys.CONTINUOUS) && ((ContinuousComponent)ability.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous()) {
/*    */               return;
/*    */             }
/*    */             if (ability.hasComponent(ModAbilityKeys.CHARGE) && ((ChargeComponent)ability.getComponent(ModAbilityKeys.CHARGE).get()).isCharging()) {
/*    */               return;
/*    */             }
/*    */             abilityDataProps.setEquippedAbility(message.slot, null);
/*    */             if (abilityDataProps.getEquippedAbilitySlot((IAbility)ability) >= 0) {
/*    */               if (ability.isNew) {
/*    */                 WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket2((LivingEntity)serverPlayerEntity, (IAbility)ability), (Entity)serverPlayerEntity);
/*    */               } else {
/*    */                 WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((LivingEntity)serverPlayerEntity, ability), (Entity)serverPlayerEntity);
/*    */               } 
/*    */             }
/*    */           });
/*    */     }
/* 74 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CRemoveAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */