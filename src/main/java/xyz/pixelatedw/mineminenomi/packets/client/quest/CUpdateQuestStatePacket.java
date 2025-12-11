/*     */ package xyz.pixelatedw.mineminenomi.packets.client.quest;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ITrainer;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class CUpdateQuestStatePacket
/*     */ {
/*     */   private INBT data;
/*     */   private ResourceLocation questId;
/*     */   
/*     */   public CUpdateQuestStatePacket() {}
/*     */   
/*     */   public CUpdateQuestStatePacket(QuestId quest) {
/*  36 */     this.questId = quest.getRegistryName();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public CUpdateQuestStatePacket(IQuestData props) {
/*  42 */     this.data = (INBT)new CompoundNBT();
/*  43 */     this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  48 */     buffer.func_192572_a(this.questId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CUpdateQuestStatePacket decode(PacketBuffer buffer) {
/*  53 */     CUpdateQuestStatePacket msg = new CUpdateQuestStatePacket();
/*  54 */     msg.questId = buffer.func_192575_l();
/*  55 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CUpdateQuestStatePacket message, Supplier<NetworkEvent.Context> ctx) {
/*  60 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  62 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             if (message.questId == null) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             Optional<ITrainer> trainer = WyHelper.getNearbyLiving(serverPlayerEntity.func_213303_ch(), (IWorld)((PlayerEntity)serverPlayerEntity).field_70170_p, 10.0D, null).stream().filter(ITrainer.class::isInstance).map(ITrainer.class::cast).findFirst();
/*     */             
/*     */             if (!trainer.isPresent()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             IQuestData props = QuestDataCapability.get((PlayerEntity)serverPlayerEntity);
/*     */             
/*     */             QuestId questId = (QuestId)GameRegistry.findRegistry(QuestId.class).getValue(message.questId);
/*     */             
/*     */             if (questId == null || questId.isLocked(props)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (!((ITrainer)trainer.get()).getAvailableQuests((PlayerEntity)serverPlayerEntity).stream().anyMatch(())) {
/*     */               return;
/*     */             }
/*     */             
/*     */             boolean updateClient = false;
/*     */             
/*     */             if (props.hasInProgressQuest(questId) && props.getInProgressQuest(questId).isComplete() && props.getInProgressQuest(questId).triggerCompleteEvent((PlayerEntity)serverPlayerEntity)) {
/*     */               props.addFinishedQuest(questId);
/*     */               
/*     */               props.removeInProgressQuest(questId);
/*     */               
/*     */               updateClient = true;
/*     */             } else if (!props.hasInProgressQuest(questId)) {
/*     */               Quest quest = questId.createQuest();
/*     */               
/*     */               if (quest.triggerStartEvent((PlayerEntity)serverPlayerEntity)) {
/*     */                 props.addInProgressQuest(quest);
/*     */                 updateClient = true;
/*     */               } 
/*     */             } 
/*     */             if (updateClient) {
/*     */               WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.func_145782_y(), props), (PlayerEntity)serverPlayerEntity);
/*     */               WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.func_145782_y(), AbilityDataCapability.get((LivingEntity)serverPlayerEntity)), (PlayerEntity)serverPlayerEntity);
/*     */             } 
/*     */           });
/*     */     }
/* 109 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\quest\CUpdateQuestStatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */