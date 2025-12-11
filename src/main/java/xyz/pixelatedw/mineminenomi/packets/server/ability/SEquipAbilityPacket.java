/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.world.ClientWorld;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class SEquipAbilityPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int slot;
/*    */   private ResourceLocation abilityId;
/*    */   
/*    */   public SEquipAbilityPacket() {}
/*    */   
/*    */   public SEquipAbilityPacket(int entityId, int slot, AbilityCore<?> core) {
/* 30 */     this.entityId = entityId;
/* 31 */     this.slot = slot;
/* 32 */     this.abilityId = core.getRegistryName();
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.writeInt(this.slot);
/* 38 */     buffer.writeBoolean((this.abilityId != null));
/* 39 */     if (this.abilityId != null) {
/* 40 */       buffer.func_192572_a(this.abilityId);
/*    */     }
/*    */   }
/*    */   
/*    */   public static SEquipAbilityPacket decode(PacketBuffer buffer) {
/* 45 */     SEquipAbilityPacket msg = new SEquipAbilityPacket();
/* 46 */     msg.entityId = buffer.readInt();
/* 47 */     msg.slot = buffer.readInt();
/* 48 */     boolean hasValidId = buffer.readBoolean();
/* 49 */     if (hasValidId) {
/* 50 */       msg.abilityId = buffer.func_192575_l();
/*    */     }
/* 52 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SEquipAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 56 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 57 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 61 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SEquipAbilityPacket message) {
/*    */       try {
/* 68 */         ClientWorld world = (Minecraft.func_71410_x()).field_71441_e;
/* 69 */         Entity target = world.func_73045_a(message.entityId);
/* 70 */         if (target == null || !(target instanceof LivingEntity)) {
/*    */           return;
/*    */         }
/*    */         
/* 74 */         if (message.abilityId == null) {
/* 75 */           LogManager.getLogger(SEquipAbilityPacket.class).warn(target.func_145748_c_().getString() + " tried equipping an invalid ability!");
/*    */           
/*    */           return;
/*    */         } 
/* 79 */         IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)target);
/* 80 */         AbilityCore core = (AbilityCore)ModRegistries.ABILITIES.getValue(message.abilityId);
/* 81 */         IAbility ability = core.createAbility();
/* 82 */         abilityDataProps.setEquippedAbility(message.slot, ability);
/*    */       }
/* 84 */       catch (Exception e) {
/* 85 */         e.printStackTrace();
/*    */         return;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SEquipAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */