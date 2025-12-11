/*    */ package xyz.pixelatedw.mineminenomi.packets.client.quest;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CStartObjectiveEventPacket
/*    */ {
/*    */   private int questId;
/*    */   private int objId;
/*    */   
/*    */   public CStartObjectiveEventPacket() {}
/*    */   
/*    */   public CStartObjectiveEventPacket(int questId, int objId) {
/* 25 */     this.questId = questId;
/* 26 */     this.objId = objId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 31 */     buffer.writeInt(this.questId);
/* 32 */     buffer.writeInt(this.objId);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CStartObjectiveEventPacket decode(PacketBuffer buffer) {
/* 37 */     CStartObjectiveEventPacket msg = new CStartObjectiveEventPacket();
/* 38 */     msg.questId = buffer.readInt();
/* 39 */     msg.objId = buffer.readInt();
/* 40 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CStartObjectiveEventPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 45 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 47 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*    */             
/*    */             Quest current = props.getInProgressQuest(message.questId);
/*    */             
/*    */             if (current != null) {
/*    */               Objective obj = current.getObjectives().get(message.objId);
/*    */               if (obj != null) {
/*    */                 if (obj.hasStartedEvent()) {
/*    */                   obj.triggerRestartEvent((PlayerEntity)player);
/*    */                 } else {
/*    */                   obj.triggerStartEvent((PlayerEntity)player);
/*    */                 } 
/*    */                 WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*    */               } 
/*    */             } 
/*    */           });
/*    */     }
/* 67 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\quest\CStartObjectiveEventPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */