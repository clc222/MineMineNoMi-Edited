/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenChallengeGroupSelectorPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CScreenEventPacket {
/* 21 */   private static final TargetsPredicate TARGET_PICKER = (new TargetsPredicate()).testFriendlyFaction();
/*    */   
/*    */   private int eventId;
/*    */   
/*    */   public CScreenEventPacket(int eventId) {
/* 26 */     this.eventId = eventId;
/*    */   }
/*    */ 
/*    */   
/*    */   public CScreenEventPacket() {}
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.eventId);
/*    */   }
/*    */   
/*    */   public static CScreenEventPacket decode(PacketBuffer buffer) {
/* 37 */     CScreenEventPacket msg = new CScreenEventPacket();
/* 38 */     msg.eventId = buffer.readInt();
/* 39 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(CScreenEventPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 43 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 44 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             IChallengesData props;
/*    */             List<LivingEntity> nearbyGroupMembers;
/*    */             List<Integer> ids;
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             switch (message.eventId) {
/*    */               case 100:
/*    */               case 101:
/*    */               case 102:
/*    */                 props = ChallengesDataCapability.get((PlayerEntity)serverPlayerEntity);
/*    */                 nearbyGroupMembers = TargetHelper.getEntitiesInArea((LivingEntity)serverPlayerEntity, 20.0D, 20.0D, 20.0D, TARGET_PICKER, new Class[] { LivingEntity.class });
/*    */                 nearbyGroupMembers.removeIf(());
/*    */                 nearbyGroupMembers.removeIf(());
/*    */                 ids = (List<Integer>)nearbyGroupMembers.stream().map(()).collect(Collectors.toList());
/*    */                 WyNetwork.sendTo(new SOpenChallengeGroupSelectorPacket(message.eventId, ids), (PlayerEntity)serverPlayerEntity);
/*    */                 break;
/*    */             } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           });
/*    */     }
/* 73 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\CScreenEventPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */