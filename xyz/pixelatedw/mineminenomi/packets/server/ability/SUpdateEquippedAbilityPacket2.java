/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class SUpdateEquippedAbilityPacket2
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private CompoundNBT nbtData;
/*    */   
/*    */   public SUpdateEquippedAbilityPacket2() {}
/*    */   
/*    */   public SUpdateEquippedAbilityPacket2(LivingEntity entity, IAbility ability) {
/* 27 */     IAbilityData props = AbilityDataCapability.get(entity);
/*    */     
/* 29 */     this.entityId = entity.func_145782_y();
/* 30 */     this.abilityId = props.getEquippedAbilitySlot(ability);
/* 31 */     this.nbtData = ability.save(new CompoundNBT());
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 35 */     buffer.writeInt(this.entityId);
/* 36 */     buffer.writeInt(this.abilityId);
/* 37 */     buffer.func_150786_a(this.nbtData);
/*    */   }
/*    */   
/*    */   public static SUpdateEquippedAbilityPacket2 decode(PacketBuffer buffer) {
/* 41 */     SUpdateEquippedAbilityPacket2 msg = new SUpdateEquippedAbilityPacket2();
/* 42 */     msg.entityId = buffer.readInt();
/* 43 */     msg.abilityId = buffer.readInt();
/* 44 */     msg.nbtData = buffer.func_150793_b();
/* 45 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateEquippedAbilityPacket2 message, Supplier<NetworkEvent.Context> ctx) {
/* 49 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */     
/* 54 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateEquippedAbilityPacket2 message) {
/* 60 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 61 */       if (target == null || !(target instanceof LivingEntity) || message.abilityId < 0) {
/*    */         return;
/*    */       }
/*    */       
/* 65 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 66 */       IAbility ability = props.getEquippedAbility(message.abilityId);
/*    */       
/* 68 */       if (ability == null) {
/*    */         return;
/*    */       }
/*    */       
/* 72 */       ability.load(message.nbtData);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateEquippedAbilityPacket2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */