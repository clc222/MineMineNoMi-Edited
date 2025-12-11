/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability.components;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class CChangeAbilityAltModePacket {
/*    */   private int slot;
/*    */   
/*    */   public CChangeAbilityAltModePacket(int slot) {
/* 22 */     this.slot = slot;
/*    */   }
/*    */   public CChangeAbilityAltModePacket() {}
/*    */   public void encode(PacketBuffer buffer) {
/* 26 */     buffer.writeInt(this.slot);
/*    */   }
/*    */   
/*    */   public static CChangeAbilityAltModePacket decode(PacketBuffer buffer) {
/* 30 */     CChangeAbilityAltModePacket msg = new CChangeAbilityAltModePacket();
/*    */     
/* 32 */     msg.slot = buffer.readInt();
/*    */     
/* 34 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CChangeAbilityAltModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 38 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */ 
/*    */             
/*    */             IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */ 
/*    */             
/*    */             Ability abl = (Ability)abilityProps.getEquippedAbility(message.slot);
/*    */ 
/*    */             
/*    */             if (abl == null || serverPlayerEntity.func_175149_v()) {
/*    */               return;
/*    */             }
/*    */ 
/*    */             
/*    */             AbilityUseResult result = abl.canUse((PlayerEntity)serverPlayerEntity);
/*    */ 
/*    */             
/*    */             if (result.isFail()) {
/*    */               if (result.getMessage() != null) {
/*    */                 serverPlayerEntity.func_145747_a(result.getMessage(), Util.field_240973_b_);
/*    */               }
/*    */ 
/*    */               
/*    */               return;
/*    */             } 
/*    */             
/*    */             abl.getComponent(ModAbilityKeys.ALT_MODE).ifPresent(());
/*    */           });
/*    */     }
/*    */     
/* 70 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\components\CChangeAbilityAltModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */