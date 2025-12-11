/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ui;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenChallengesScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class COpenChallengesScreenPacket
/*    */ {
/*    */   public void encode(PacketBuffer buffer) {}
/*    */   
/*    */   public static COpenChallengesScreenPacket decode(PacketBuffer buffer) {
/* 24 */     COpenChallengesScreenPacket msg = new COpenChallengesScreenPacket();
/* 25 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(COpenChallengesScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 29 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
/* 30 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IChallengesData props = ChallengesDataCapability.get((PlayerEntity)serverPlayerEntity);
/*    */             
/*    */             if (props.countChallenges() <= 0) {
/*    */               return;
/*    */             }
/*    */             
/*    */             List<Challenge> challenges = props.getChallenges();
/*    */             
/*    */             ImmutableList immutableList = props.getInvitations();
/*    */             
/*    */             WyNetwork.sendTo(new SOpenChallengesScreenPacket(challenges, (List)immutableList), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/* 46 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\clien\\ui\COpenChallengesScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */