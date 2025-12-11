/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class SSyncAbilityDataPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncAbilityDataPacket() {}
/*    */   
/*    */   public SSyncAbilityDataPacket(int entityId, IAbilityData abiltiyDataProps) {
/* 26 */     this.data = (INBT)new CompoundNBT();
/* 27 */     this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, abiltiyDataProps, null);
/* 28 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.entityId);
/* 34 */     buffer.func_150786_a((CompoundNBT)this.data);
/*    */   }
/*    */   
/*    */   public static SSyncAbilityDataPacket decode(PacketBuffer buffer) {
/* 38 */     SSyncAbilityDataPacket msg = new SSyncAbilityDataPacket();
/*    */     
/* 40 */     msg.entityId = buffer.readInt();
/* 41 */     msg.data = (INBT)buffer.func_150793_b();
/*    */     
/* 43 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SSyncAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 47 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 48 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */     
/* 51 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncAbilityDataPacket message) {
/* 57 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/*    */       
/* 59 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 63 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/*    */       
/* 65 */       AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, props, null, message.data);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncAbilityDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */