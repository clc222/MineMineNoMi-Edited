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
/*    */ public class SSetContinuityThresholdPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private float thresholdTime;
/*    */   
/*    */   public SSetContinuityThresholdPacket() {}
/*    */   
/*    */   public SSetContinuityThresholdPacket(LivingEntity entity, IAbility ability, float thresholdTime) {
/* 27 */     this.entityId = entity.func_145782_y();
/* 28 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 29 */     this.thresholdTime = thresholdTime;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.entityId);
/* 34 */     buffer.writeInt(this.abilityId);
/* 35 */     buffer.writeFloat(this.thresholdTime);
/*    */   }
/*    */   
/*    */   public static SSetContinuityThresholdPacket decode(PacketBuffer buffer) {
/* 39 */     SSetContinuityThresholdPacket msg = new SSetContinuityThresholdPacket();
/*    */     
/* 41 */     msg.entityId = buffer.readInt();
/* 42 */     msg.abilityId = buffer.readInt();
/* 43 */     msg.thresholdTime = buffer.readFloat();
/*    */     
/* 45 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetContinuityThresholdPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 49 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 53 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetContinuityThresholdPacket message) {
/* 59 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 61 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 65 */       LivingEntity entity = (LivingEntity)target;
/*    */       
/* 67 */       IAbilityData props = AbilityDataCapability.get(entity);
/*    */       
/* 69 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/*    */       
/* 71 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 75 */       abl.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.setThresholdTime(entity, message.thresholdTime));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SSetContinuityThresholdPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */