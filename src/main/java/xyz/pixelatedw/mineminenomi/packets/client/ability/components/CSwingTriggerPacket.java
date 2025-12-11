/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability.components;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class CSwingTriggerPacket {
/*    */   private int slot;
/*    */   
/*    */   public CSwingTriggerPacket() {}
/*    */   
/*    */   public CSwingTriggerPacket(LivingEntity entity, IAbility ability) {
/* 23 */     this.slot = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 27 */     buffer.writeInt(this.slot);
/*    */   }
/*    */   
/*    */   public static CSwingTriggerPacket decode(PacketBuffer buffer) {
/* 31 */     CSwingTriggerPacket msg = new CSwingTriggerPacket();
/* 32 */     msg.slot = buffer.readInt();
/* 33 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CSwingTriggerPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 38 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             Ability abl = (Ability)abilityProps.getEquippedAbility(message.slot);
/*    */             
/*    */             if (abl == null || serverPlayerEntity.func_175149_v()) {
/*    */               return;
/*    */             }
/*    */             
/*    */             abl.getComponent(ModAbilityKeys.SWING_TRIGGER).ifPresent(());
/*    */           });
/*    */     }
/*    */     
/* 53 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\components\CSwingTriggerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */