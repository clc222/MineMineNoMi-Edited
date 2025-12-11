/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ 
/*    */ public abstract class ChallengeArena
/*    */ {
/*    */   private ArenaStyle style;
/*    */   
/*    */   public ChallengeArena(ArenaStyle style) {
/* 14 */     this.style = style;
/*    */   }
/*    */   @Deprecated
/*    */   public abstract Set<BlockPos> buildArena(InProgressChallenge paramInProgressChallenge);
/*    */   public abstract MutableBoundingBox getArenaLimits();
/*    */   
/*    */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/* 21 */     buildArena(challenge);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGroundLevel() {
/* 26 */     return 32;
/*    */   }
/*    */   public ArenaStyle getStyle() {
/* 29 */     return this.style;
/*    */   }
/*    */   
/*    */   public static class SpawnPosition {
/*    */     private BlockPos pos;
/*    */     private float pitch;
/*    */     private float yaw;
/*    */     
/*    */     public SpawnPosition(BlockPos pos, float yaw, float pitch) {
/* 38 */       this.pos = pos;
/* 39 */       this.pitch = pitch;
/* 40 */       this.yaw = yaw;
/*    */     }
/*    */     
/*    */     public BlockPos getPos() {
/* 44 */       return this.pos;
/*    */     }
/*    */     
/*    */     public float getPitch() {
/* 48 */       return this.pitch;
/*    */     }
/*    */     
/*    */     public float getYaw() {
/* 52 */       return this.yaw;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class EnemySpawn {
/*    */     private final LivingEntity entity;
/*    */     private final ChallengeArena.SpawnPosition spawnPos;
/*    */     
/*    */     public EnemySpawn(LivingEntity entity, ChallengeArena.SpawnPosition pos) {
/* 61 */       this.entity = entity;
/* 62 */       this.spawnPos = pos;
/*    */     }
/*    */     
/*    */     public LivingEntity getEntity() {
/* 66 */       return this.entity;
/*    */     }
/*    */     
/*    */     public ChallengeArena.SpawnPosition getSpawnPos() {
/* 70 */       return this.spawnPos;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */