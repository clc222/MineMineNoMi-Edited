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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncHakiDataPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncHakiDataPacket() {}
/*    */   
/*    */   public SSyncHakiDataPacket(int entityId, IHakiData props) {
/* 27 */     this.data = (INBT)new CompoundNBT();
/* 28 */     this.data = HakiDataCapability.INSTANCE.getStorage().writeNBT(HakiDataCapability.INSTANCE, props, null);
/* 29 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.entityId);
/* 35 */     buffer.func_150786_a((CompoundNBT)this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncHakiDataPacket decode(PacketBuffer buffer) {
/* 40 */     SSyncHakiDataPacket msg = new SSyncHakiDataPacket();
/* 41 */     msg.entityId = buffer.readInt();
/* 42 */     msg.data = (INBT)buffer.func_150793_b();
/* 43 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncHakiDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 55 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncHakiDataPacket message) {
/* 63 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 64 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 67 */       IHakiData props = HakiDataCapability.get((LivingEntity)target);
/* 68 */       HakiDataCapability.INSTANCE.getStorage().readNBT(HakiDataCapability.INSTANCE, props, null, message.data);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncHakiDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */