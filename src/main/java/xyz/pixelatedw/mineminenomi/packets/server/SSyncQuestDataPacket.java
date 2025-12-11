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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncQuestDataPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncQuestDataPacket() {}
/*    */   
/*    */   public SSyncQuestDataPacket(int entityId, IQuestData props) {
/* 29 */     this.data = (INBT)new CompoundNBT();
/* 30 */     this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
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
/*    */   public static SSyncQuestDataPacket decode(PacketBuffer buffer) {
/* 42 */     SSyncQuestDataPacket msg = new SSyncQuestDataPacket();
/* 43 */     msg.entityId = buffer.readInt();
/* 44 */     msg.data = (INBT)buffer.func_150793_b();
/* 45 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncQuestDataPacket message, Supplier<NetworkEvent.Context> ctx) {
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
/*    */     public static void handle(SSyncQuestDataPacket message) {
/* 65 */       Entity target = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityId);
/* 66 */       if (target == null || !(target instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/* 69 */       IQuestData props = QuestDataCapability.get((PlayerEntity)target);
/*    */       
/* 71 */       QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, props, null, message.data);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncQuestDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */