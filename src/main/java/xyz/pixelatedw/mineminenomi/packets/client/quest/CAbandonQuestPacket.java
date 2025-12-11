/*    */ package xyz.pixelatedw.mineminenomi.packets.client.quest;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CAbandonQuestPacket
/*    */ {
/*    */   private String questId;
/*    */   
/*    */   public CAbandonQuestPacket() {}
/*    */   
/*    */   public CAbandonQuestPacket(QuestId questId) {
/* 28 */     this.questId = questId.getRegistryName().toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     int len = this.questId.length();
/* 34 */     buffer.writeInt(len);
/* 35 */     buffer.func_211400_a(this.questId, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CAbandonQuestPacket decode(PacketBuffer buffer) {
/* 40 */     CAbandonQuestPacket msg = new CAbandonQuestPacket();
/* 41 */     int len = buffer.readInt();
/* 42 */     msg.questId = buffer.func_150789_c(len);
/* 43 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CAbandonQuestPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             if (Strings.isNullOrEmpty(message.questId)) {
/*    */               return;
/*    */             }
/*    */             
/*    */             QuestId questId = (QuestId)GameRegistry.findRegistry(QuestId.class).getValue(new ResourceLocation(message.questId));
/*    */             
/*    */             if (questId == null) {
/*    */               return;
/*    */             }
/*    */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*    */             Quest current = props.getInProgressQuest(questId);
/*    */             props.removeInProgressQuest(current);
/*    */             WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*    */           });
/*    */     }
/* 67 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\quest\CAbandonQuestPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */