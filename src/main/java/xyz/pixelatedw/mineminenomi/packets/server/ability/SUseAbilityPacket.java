/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class SUseAbilityPacket {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   
/*    */   public SUseAbilityPacket() {}
/*    */   
/*    */   public SUseAbilityPacket(LivingEntity entity, IAbility ability) {
/* 23 */     this.entityId = entity.func_145782_y();
/* 24 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.entityId);
/* 29 */     buffer.writeInt(this.abilityId);
/*    */   }
/*    */   
/*    */   public static SUseAbilityPacket decode(PacketBuffer buffer) {
/* 33 */     SUseAbilityPacket msg = new SUseAbilityPacket();
/*    */     
/* 35 */     msg.entityId = buffer.readInt();
/* 36 */     msg.abilityId = buffer.readInt();
/*    */     
/* 38 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUseAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 43 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 48 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUseAbilityPacket message) {
/* 54 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 56 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 60 */       LivingEntity entity = (LivingEntity)target;
/*    */       
/* 62 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 63 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/*    */       
/* 65 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 69 */       abl.use(entity);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUseAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */