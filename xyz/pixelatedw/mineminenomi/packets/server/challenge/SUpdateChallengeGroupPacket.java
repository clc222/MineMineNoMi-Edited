/*    */ package xyz.pixelatedw.mineminenomi.packets.server.challenge;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*    */ 
/*    */ public class SUpdateChallengeGroupPacket
/*    */ {
/*    */   private UUID memberId;
/*    */   private int groupSlot;
/*    */   
/*    */   public SUpdateChallengeGroupPacket() {}
/*    */   
/*    */   public SUpdateChallengeGroupPacket(UUID memberId, int groupSlot) {
/* 24 */     this.memberId = memberId;
/* 25 */     this.groupSlot = groupSlot;
/*    */   }
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 29 */     buffer.func_179252_a(this.memberId);
/* 30 */     buffer.writeInt(this.groupSlot);
/*    */   }
/*    */   
/*    */   public static SUpdateChallengeGroupPacket decode(PacketBuffer buffer) {
/* 34 */     SUpdateChallengeGroupPacket msg = new SUpdateChallengeGroupPacket();
/* 35 */     msg.memberId = buffer.func_179253_g();
/* 36 */     msg.groupSlot = buffer.readInt();
/* 37 */     return msg;
/*    */   }
/*    */   
/*    */   public static void handle(SUpdateChallengeGroupPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 41 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/* 44 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateChallengeGroupPacket message) {
/* 50 */       Minecraft mc = Minecraft.func_71410_x();
/* 51 */       ClientPlayerEntity player = mc.field_71439_g;
/*    */       
/* 53 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */       
/* 57 */       PlayerEntity member = mc.field_71441_e.func_217371_b(message.memberId);
/*    */       
/* 59 */       if (member == null) {
/*    */         return;
/*    */       }
/*    */       
/* 63 */       if (!(mc.field_71462_r instanceof ChallengesScreen)) {
/*    */         return;
/*    */       }
/*    */       
/* 67 */       ChallengesScreen screen = (ChallengesScreen)mc.field_71462_r;
/* 68 */       screen.setGroupMember(message.groupSlot, (LivingEntity)member);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\server\challenge\SUpdateChallengeGroupPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */