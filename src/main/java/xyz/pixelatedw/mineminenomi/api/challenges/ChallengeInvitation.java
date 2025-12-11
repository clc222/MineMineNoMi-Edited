/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChallengeInvitation
/*    */ {
/*    */   private static final int INVITE_TIME = 2400;
/*    */   private UUID senderId;
/*    */   private ChallengeCore<?> challenge;
/*    */   private long gameTime;
/*    */   private int groupSlot;
/*    */   
/*    */   public ChallengeInvitation(UUID senderId, ChallengeCore<?> challenge, long gameTime, int groupSlot) {
/* 20 */     this.senderId = senderId;
/* 21 */     this.challenge = challenge;
/* 22 */     this.gameTime = gameTime;
/* 23 */     this.groupSlot = MathHelper.func_76125_a(groupSlot, 0, 2);
/*    */   }
/*    */   
/*    */   public boolean isExpired(World world) {
/* 27 */     return (this.gameTime + 2400L < world.func_82737_E());
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public PlayerEntity getSender(World world) {
/* 32 */     return world.func_217371_b(this.senderId);
/*    */   }
/*    */   
/*    */   public UUID getSenderId() {
/* 36 */     return this.senderId;
/*    */   }
/*    */   
/*    */   public ChallengeCore<?> getChallenge() {
/* 40 */     return this.challenge;
/*    */   }
/*    */   
/*    */   public long getSendTime() {
/* 44 */     return this.gameTime;
/*    */   }
/*    */   
/*    */   public int getGroupSlot() {
/* 48 */     return this.groupSlot;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeInvitation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */