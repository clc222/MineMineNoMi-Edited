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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SChangeAbilityAltModePacket<E extends Enum<E>>
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private int mode;
/*    */   
/*    */   public SChangeAbilityAltModePacket() {}
/*    */   
/*    */   public SChangeAbilityAltModePacket(LivingEntity entity, IAbility ability, E mode) {
/* 30 */     this.entityId = entity.func_145782_y();
/* 31 */     this.abilityId = AbilityDataCapability.get(entity).getEquippedAbilitySlot(ability);
/* 32 */     this.mode = mode.ordinal();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.writeInt(this.abilityId);
/* 38 */     buffer.writeInt(this.mode);
/*    */   }
/*    */   
/*    */   public static SChangeAbilityAltModePacket decode(PacketBuffer buffer) {
/* 42 */     SChangeAbilityAltModePacket<Enum> msg = new SChangeAbilityAltModePacket<>();
/*    */     
/* 44 */     msg.entityId = buffer.readInt();
/* 45 */     msg.abilityId = buffer.readInt();
/* 46 */     msg.mode = buffer.readInt();
/*    */     
/* 48 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SChangeAbilityAltModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 53 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 56 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static <E extends Enum<E>> void handle(SChangeAbilityAltModePacket message) {
/* 62 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 64 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 68 */       LivingEntity entity = (LivingEntity)target;
/*    */       
/* 70 */       IAbilityData props = AbilityDataCapability.get(entity);
/*    */       
/* 72 */       IAbility abl = props.getEquippedAbility(message.abilityId);
/*    */       
/* 74 */       if (abl == null) {
/*    */         return;
/*    */       }
/*    */       
/* 78 */       abl.getComponent(ModAbilityKeys.ALT_MODE).ifPresent(c -> {
/*    */             Enum[] arrayOfEnum = c.getAltMode().getEnumConstants();
/*    */             c.setMode(entity, arrayOfEnum[message.mode]);
/*    */           });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SChangeAbilityAltModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */