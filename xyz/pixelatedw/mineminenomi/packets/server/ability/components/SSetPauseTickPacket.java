/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability.components;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
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
/*    */ 
/*    */ 
/*    */ public class SSetPauseTickPacket
/*    */ {
/*    */   private int entityId;
/*    */   private ResourceLocation abilityKey;
/*    */   private boolean pauseFlag;
/*    */   
/*    */   public SSetPauseTickPacket() {}
/*    */   
/*    */   public SSetPauseTickPacket(LivingEntity entity, AbilityCore core, boolean pauseFlag) {
/* 32 */     this.entityId = entity.func_145782_y();
/* 33 */     this.abilityKey = core.getRegistryName();
/* 34 */     this.pauseFlag = pauseFlag;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 38 */     buffer.writeInt(this.entityId);
/* 39 */     buffer.func_192572_a(this.abilityKey);
/* 40 */     buffer.writeBoolean(this.pauseFlag);
/*    */   }
/*    */   
/*    */   public static SSetPauseTickPacket decode(PacketBuffer buffer) {
/* 44 */     SSetPauseTickPacket msg = new SSetPauseTickPacket();
/* 45 */     msg.entityId = buffer.readInt();
/* 46 */     msg.abilityKey = buffer.func_192575_l();
/* 47 */     msg.pauseFlag = buffer.readBoolean();
/* 48 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSetPauseTickPacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SSetPauseTickPacket message) {
/* 63 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 64 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 68 */       AbilityCore core = (AbilityCore)ModRegistries.ABILITIES.getValue(message.abilityKey);
/* 69 */       if (core == null || core.getType() != AbilityType.PASSIVE) {
/*    */         return;
/*    */       }
/*    */       
/* 73 */       LivingEntity entity = (LivingEntity)target;
/* 74 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 75 */       IAbility ability = props.getPassiveAbility(core);
/* 76 */       if (ability == null) {
/*    */         return;
/*    */       }
/*    */       
/* 80 */       ability.getComponent(ModAbilityKeys.PAUSE_TICK).ifPresent(c -> c.setPause(entity, message.pauseFlag));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\components\SSetPauseTickPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */