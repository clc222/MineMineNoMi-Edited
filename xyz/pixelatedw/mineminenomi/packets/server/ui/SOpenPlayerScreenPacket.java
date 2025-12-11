/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ui;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen;
/*    */ 
/*    */ public class SOpenPlayerScreenPacket {
/*    */   private double doriki;
/*    */   private boolean hasQuests;
/*    */   private int questAmount;
/*    */   private boolean hasChallenges;
/*    */   private int challengeAmount;
/*    */   private boolean isInCombat;
/*    */   private boolean isInChallengeDimension;
/*    */   private int invites;
/*    */   private boolean hasCrew;
/*    */   
/*    */   public SOpenPlayerScreenPacket() {}
/*    */   
/*    */   public SOpenPlayerScreenPacket(double doriki, boolean hasQuests, int questAmount, boolean hasChallenges, int challengeAmount, boolean isInCombat, boolean isInChallengeDimension, int invites, boolean hasCrew) {
/* 25 */     this.doriki = doriki;
/* 26 */     this.hasQuests = hasQuests;
/* 27 */     this.questAmount = questAmount;
/* 28 */     this.hasChallenges = hasChallenges;
/* 29 */     this.challengeAmount = challengeAmount;
/* 30 */     this.isInCombat = isInCombat;
/* 31 */     this.isInChallengeDimension = isInChallengeDimension;
/* 32 */     this.invites = invites;
/* 33 */     this.hasCrew = hasCrew;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 37 */     buffer.writeDouble(this.doriki);
/* 38 */     buffer.writeBoolean(this.hasQuests);
/* 39 */     buffer.writeInt(this.questAmount);
/* 40 */     buffer.writeBoolean(this.hasChallenges);
/* 41 */     buffer.writeInt(this.challengeAmount);
/* 42 */     buffer.writeBoolean(this.isInCombat);
/* 43 */     buffer.writeBoolean(this.isInChallengeDimension);
/* 44 */     buffer.writeInt(this.invites);
/* 45 */     buffer.writeBoolean(this.hasCrew);
/*    */   }
/*    */   
/*    */   public static SOpenPlayerScreenPacket decode(PacketBuffer buffer) {
/* 49 */     SOpenPlayerScreenPacket msg = new SOpenPlayerScreenPacket();
/* 50 */     msg.doriki = buffer.readDouble();
/* 51 */     msg.hasQuests = buffer.readBoolean();
/* 52 */     msg.questAmount = buffer.readInt();
/* 53 */     msg.hasChallenges = buffer.readBoolean();
/* 54 */     msg.challengeAmount = buffer.readInt();
/* 55 */     msg.isInCombat = buffer.readBoolean();
/* 56 */     msg.isInChallengeDimension = buffer.readBoolean();
/* 57 */     msg.invites = buffer.readInt();
/* 58 */     msg.hasCrew = buffer.readBoolean();
/* 59 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SOpenPlayerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 63 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 64 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 66 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SOpenPlayerScreenPacket message) {
/* 72 */       PlayerStatsScreen.open(message.doriki, message.hasQuests, message.questAmount, message.hasChallenges, message.challengeAmount, message.isInCombat, message.isInChallengeDimension, message.invites, message.hasCrew);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\serve\\ui\SOpenPlayerScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */