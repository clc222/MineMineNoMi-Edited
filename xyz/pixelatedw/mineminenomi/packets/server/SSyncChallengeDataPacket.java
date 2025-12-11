/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncChallengeDataPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncChallengeDataPacket() {}
/*    */   
/*    */   public SSyncChallengeDataPacket(int entityId, IChallengesData props) {
/* 29 */     this.data = (INBT)new CompoundNBT();
/* 30 */     this.data = ChallengesDataCapability.INSTANCE.getStorage().writeNBT(ChallengesDataCapability.INSTANCE, props, null);
/* 31 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 36 */     buffer.writeInt(this.entityId);
/* 37 */     buffer.func_150786_a((CompoundNBT)this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncChallengeDataPacket decode(PacketBuffer buffer) {
/* 42 */     SSyncChallengeDataPacket msg = new SSyncChallengeDataPacket();
/* 43 */     msg.entityId = buffer.readInt();
/* 44 */     msg.data = (INBT)buffer.func_150793_b();
/* 45 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncChallengeDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 50 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 52 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 57 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncChallengeDataPacket message) {
/* 65 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 66 */       if (target == null || !(target instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/* 69 */       IChallengesData props = ChallengesDataCapability.get((PlayerEntity)target);
/* 70 */       ChallengesDataCapability.INSTANCE.getStorage().readNBT(ChallengesDataCapability.INSTANCE, props, null, message.data);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncChallengeDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */