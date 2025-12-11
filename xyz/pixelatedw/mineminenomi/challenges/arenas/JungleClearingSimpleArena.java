/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ChallengesHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.JungleBorderPart;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*    */ 
/*    */ public class JungleClearingSimpleArena extends ChallengeArena {
/* 19 */   public static final JungleClearingSimpleArena INSTANCE = new JungleClearingSimpleArena();
/*    */   
/*    */   private static final int ARENA_SIZE_RADIUS = 50;
/*    */   
/*    */   private static final int ARENA_SIZE = 100;
/* 24 */   private int groundLevel = 32;
/*    */   
/*    */   private JungleClearingSimpleArena() {
/* 27 */     super(ArenaStyle.SIMPLE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/* 32 */     int bottomLayerOffset = 49;
/* 33 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 34 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*    */     
/* 36 */     this.groundLevel = groundLayer.func_177956_o();
/*    */ 
/*    */     
/* 39 */     (new JungleBorderPart(challenge, 50)).buildPart(challenge.getShard(), groundLayer, queue);
/*    */     
/* 41 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 100, 1, 100, Blocks.field_196658_i.func_176223_P(), queue);
/*    */     
/* 43 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 17, 100, Blocks.field_150348_b.func_176223_P(), queue);
/*    */     
/* 45 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 50 */     Set<BlockPos> blocks = Sets.newHashSet();
/*    */     
/* 52 */     int bottomLayerOffset = 49;
/* 53 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 54 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*    */     
/* 56 */     this.groundLevel = groundLayer.func_177956_o();
/*    */     
/* 58 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 17, 100, Blocks.field_150348_b.func_176223_P(), 0, null));
/*    */     
/* 60 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 100, 1, 100, Blocks.field_196658_i.func_176223_P(), 0, null));
/*    */ 
/*    */     
/* 63 */     blocks.addAll((new JungleBorderPart(challenge, 50)).buildPart(challenge.getShard(), groundLayer));
/*    */     
/* 65 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*    */     
/* 67 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGroundLevel() {
/* 72 */     return this.groundLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public MutableBoundingBox getArenaLimits() {
/* 77 */     return new MutableBoundingBox(-50, -50, -50, 50, 50, 50);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 81 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 15 - posId + 1, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() + 15);
/* 82 */     return new ChallengeArena.SpawnPosition(pos, 135.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 86 */     BlockPos pos1 = new BlockPos(challenge.getArenaPos().func_177958_n() - 15, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() - 15);
/* 87 */     return ChallengesHelper.get4DefaultSpawnPoints(pos1, 140.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\JungleClearingSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */