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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ 
/*    */ public class SSetPoolInUsePacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private boolean isPoolInUse;
/*    */   private int ticksLocked;
/*    */   
/*    */   public SSetPoolInUsePacket() {}
/*    */   
/*    */   public SSetPoolInUsePacket(LivingEntity entity, IAbility ability, boolean isPoolInUse, int ticks) {
/* 29 */     this.entityId = entity.func_145782_y();
/* 30 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 31 */     this.isPoolInUse = isPoolInUse;
/* 32 */     this.ticksLocked = ticks;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.writeInt(this.abilityId);
/* 38 */     buffer.writeBoolean(this.isPoolInUse);
/* 39 */     buffer.writeInt(this.ticksLocked);
/*    */   }
/*    */   
/*    */   public static SSetPoolInUsePacket decode(PacketBuffer buffer) {
/* 43 */     SSetPoolInUsePacket msg = new SSetPoolInUsePacket();
/* 44 */     msg.entityId = buffer.readInt();
/* 45 */     msg.abilityId = buffer.readInt();
/* 46 */     msg.isPoolInUse = buffer.readBoolean();
/* 47 */     msg.ticksLocked = buffer.readInt();
/* 48 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetPoolInUsePacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SSetPoolInUsePacket message) {
/* 63 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 64 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 68 */       LivingEntity entity = (LivingEntity)target;
/* 69 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 70 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 71 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 75 */       abl.getComponent(ModAbilityKeys.POOL).ifPresent(c -> c.setAbilityFromPoolInUse(entity, message.isPoolInUse));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SSetPoolInUsePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */