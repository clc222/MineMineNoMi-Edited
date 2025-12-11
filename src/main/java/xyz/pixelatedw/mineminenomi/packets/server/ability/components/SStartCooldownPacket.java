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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ 
/*    */ public class SStartCooldownPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private float cooldown;
/*    */   
/*    */   public SStartCooldownPacket() {}
/*    */   
/*    */   public SStartCooldownPacket(LivingEntity entity, IAbility ability, float cooldown) {
/* 28 */     this.entityId = entity.func_145782_y();
/* 29 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 30 */     this.cooldown = cooldown;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.entityId);
/* 35 */     buffer.writeInt(this.abilityId);
/* 36 */     buffer.writeFloat(this.cooldown);
/*    */   }
/*    */   
/*    */   public static SStartCooldownPacket decode(PacketBuffer buffer) {
/* 40 */     SStartCooldownPacket msg = new SStartCooldownPacket();
/* 41 */     msg.entityId = buffer.readInt();
/* 42 */     msg.abilityId = buffer.readInt();
/* 43 */     msg.cooldown = buffer.readFloat();
/* 44 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SStartCooldownPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 49 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 53 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SStartCooldownPacket message) {
/* 59 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 60 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 64 */       LivingEntity entity = (LivingEntity)target;
/* 65 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 66 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/* 67 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 71 */       abl.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(c -> c.startCooldown(entity, message.cooldown));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SStartCooldownPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */