/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;
/*    */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*    */ 
/*    */ 
/*    */ public class SOpenChallengesScreenPacket
/*    */ {
/*    */   private List<Challenge> challenges;
/*    */   private List<ChallengeInvitation> invites;
/*    */   
/*    */   public SOpenChallengesScreenPacket() {}
/*    */   
/*    */   public SOpenChallengesScreenPacket(List<Challenge> challenges, List<ChallengeInvitation> invites) {
/* 28 */     this.challenges = challenges;
/* 29 */     this.invites = invites;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 33 */     buffer.writeInt(this.challenges.size());
/* 34 */     for (Challenge ch : this.challenges) {
/* 35 */       buffer.func_192572_a(ch.getCore().getRegistryName());
/* 36 */       buffer.func_150786_a(ch.save(new CompoundNBT()));
/*    */     } 
/* 38 */     buffer.writeInt(this.invites.size());
/* 39 */     for (ChallengeInvitation invite : this.invites) {
/* 40 */       buffer.func_179252_a(invite.getSenderId());
/* 41 */       buffer.func_192572_a(invite.getChallenge().getRegistryName());
/* 42 */       buffer.writeLong(invite.getSendTime());
/* 43 */       buffer.writeInt(invite.getGroupSlot());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static SOpenChallengesScreenPacket decode(PacketBuffer buffer) {
/* 48 */     SOpenChallengesScreenPacket msg = new SOpenChallengesScreenPacket();
/* 49 */     msg.challenges = new ArrayList<>();
/* 50 */     int size = buffer.readInt(); int i;
/* 51 */     for (i = 0; i < size; i++) {
/* 52 */       ChallengeCore core = (ChallengeCore)ModRegistries.CHALLENGES.getValue(buffer.func_192575_l());
/* 53 */       if (core != null) {
/*    */ 
/*    */         
/* 56 */         Challenge challenge = core.createChallenge();
/* 57 */         challenge.load(buffer.func_150793_b());
/* 58 */         msg.challenges.add(challenge);
/*    */       } 
/* 60 */     }  msg.invites = new ArrayList<>();
/* 61 */     size = buffer.readInt();
/* 62 */     for (i = 0; i < size; i++) {
/* 63 */       UUID uuid = buffer.func_179253_g();
/* 64 */       ChallengeCore<?> challenge = (ChallengeCore)ModRegistries.CHALLENGES.getValue(buffer.func_192575_l());
/* 65 */       long gameTime = buffer.readLong();
/* 66 */       int groupSlot = buffer.readInt();
/* 67 */       ChallengeInvitation invite = new ChallengeInvitation(uuid, challenge, gameTime, groupSlot);
/* 68 */       msg.invites.add(invite);
/*    */     } 
/* 70 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenChallengesScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 74 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 75 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 77 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenChallengesScreenPacket message) {
/* 83 */       ChallengesScreen.open(message.challenges, message.invites);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenChallengesScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */