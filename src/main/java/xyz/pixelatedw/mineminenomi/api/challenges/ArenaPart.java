/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ 
/*    */ public abstract class ArenaPart
/*    */ {
/*    */   public static final int BLOCK_FLAG = 515;
/*    */   private final ChallengeArena parent;
/*    */   private final InProgressChallenge challenge;
/*    */   
/*    */   public ArenaPart(InProgressChallenge challenge) {
/* 16 */     this.parent = challenge.getArena();
/* 17 */     this.challenge = challenge;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public abstract Set<BlockPos> buildPart(ServerWorld paramServerWorld, BlockPos paramBlockPos);
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 24 */     buildPart(world, spawnPos);
/*    */   }
/*    */   
/*    */   public InProgressChallenge getChallenge() {
/* 28 */     return this.challenge;
/*    */   }
/*    */   
/*    */   public ChallengeArena getParent() {
/* 32 */     return this.parent;
/*    */   }
/*    */   
/*    */   public Random getRNG() {
/* 36 */     return this.challenge.getRNG();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ArenaPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */