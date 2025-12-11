/*     */ package xyz.pixelatedw.mineminenomi.packets.client;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEquipItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestTrackerScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CRequestSyncQuestDataPacket {
/*     */   private boolean openScreen;
/*     */   
/*     */   public CRequestSyncQuestDataPacket() {}
/*     */   
/*     */   public CRequestSyncQuestDataPacket(boolean openScreen) {
/*  31 */     this.openScreen = openScreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  36 */     buffer.writeBoolean(this.openScreen);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CRequestSyncQuestDataPacket decode(PacketBuffer buffer) {
/*  41 */     CRequestSyncQuestDataPacket msg = new CRequestSyncQuestDataPacket();
/*  42 */     msg.openScreen = buffer.readBoolean();
/*  43 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CRequestSyncQuestDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */ 
/*     */             
/*     */             IQuestData props = QuestDataCapability.get((PlayerEntity)serverPlayerEntity);
/*     */ 
/*     */             
/*     */             for (Quest qst : props.getInProgressQuests()) {
/*     */               if (qst != null) {
/*     */                 for (Objective obj : qst.getObjectives()) {
/*     */                   if (obj instanceof xyz.pixelatedw.mineminenomi.api.quests.objectives.IReachDorikiObjective) {
/*     */                     obj.setProgress((PlayerEntity)serverPlayerEntity, EntityStatsCapability.get((LivingEntity)serverPlayerEntity).getDoriki(), false);
/*     */                   }
/*     */ 
/*     */                   
/*     */                   if (obj instanceof IEquipItemObjective) {
/*     */                     for (EquipmentSlotType slot : EquipmentSlotType.values()) {
/*     */                       boolean slotCheck = ((IEquipItemObjective)obj).checkSlot(slot);
/*     */                       
/*     */                       if (slotCheck) {
/*     */                         boolean itemCheck = ((IEquipItemObjective)obj).checkEquippedItem((PlayerEntity)serverPlayerEntity, serverPlayerEntity.func_184582_a(slot));
/*     */                         
/*     */                         if (itemCheck) {
/*     */                           obj.alterProgress((PlayerEntity)serverPlayerEntity, 1.0D);
/*     */                         } else {
/*     */                           obj.alterProgress((PlayerEntity)serverPlayerEntity, -1.0D);
/*     */                         } 
/*     */                       } 
/*     */                     } 
/*     */                   }
/*     */                   
/*     */                   if (obj instanceof IObtainItemObjective) {
/*     */                     ArrayList<ItemStack> slots = new ArrayList<>();
/*     */                     
/*     */                     slots.addAll((Collection<? extends ItemStack>)((PlayerEntity)serverPlayerEntity).field_71071_by.field_70462_a);
/*     */                     
/*     */                     slots.addAll((Collection<? extends ItemStack>)((PlayerEntity)serverPlayerEntity).field_71071_by.field_184439_c);
/*     */                     
/*     */                     int items = ((IObtainItemObjective)obj).checkItems(slots);
/*     */                     
/*     */                     if (items > 0) {
/*     */                       obj.setProgress((PlayerEntity)serverPlayerEntity, items, false);
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */             
/*     */             WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.func_145782_y(), props), (PlayerEntity)serverPlayerEntity);
/*     */             
/*     */             if (message.openScreen) {
/*     */               if (props.countInProgressQuests() <= 0) {
/*     */                 return;
/*     */               }
/*     */               
/*     */               WyNetwork.sendTo(new SOpenQuestTrackerScreenPacket(), (PlayerEntity)serverPlayerEntity);
/*     */             } 
/*     */           });
/*     */     }
/*     */     
/* 110 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\CRequestSyncQuestDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */