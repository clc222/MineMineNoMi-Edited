/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PauseTickComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SSetPauseTickPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CTogglePassiveAbilityPacket
/*    */ {
/*    */   private ResourceLocation abilityKey;
/*    */   
/*    */   public CTogglePassiveAbilityPacket(AbilityCore ability) {
/* 28 */     this.abilityKey = ability.getRegistryName();
/*    */   }
/*    */   public CTogglePassiveAbilityPacket() {}
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.func_192572_a(this.abilityKey);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CTogglePassiveAbilityPacket decode(PacketBuffer buffer) {
/* 38 */     CTogglePassiveAbilityPacket msg = new CTogglePassiveAbilityPacket();
/* 39 */     msg.abilityKey = buffer.func_192575_l();
/* 40 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CTogglePassiveAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 45 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 47 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             AbilityCore core = (AbilityCore)ModRegistries.ABILITIES.getValue(message.abilityKey);
/*    */             
/*    */             if (core == null || core.getType() != AbilityType.PASSIVE) {
/*    */               return;
/*    */             }
/*    */             
/*    */             IAbility ability = abilityDataProps.getPassiveAbility(core);
/*    */             
/*    */             if (ability == null) {
/*    */               return;
/*    */             }
/*    */             
/*    */             ability.getComponent(ModAbilityKeys.PAUSE_TICK).ifPresent(());
/*    */           });
/*    */     }
/*    */     
/* 68 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CTogglePassiveAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */