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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.AlabastaDesertDetailsPart;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*    */ 
/*    */ public class AlabastaDesertSimpleArena extends ChallengeArena {
/* 19 */   public static final AlabastaDesertSimpleArena INSTANCE = new AlabastaDesertSimpleArena();
/*    */   
/*    */   private static final int ARENA_SIZE_RADIUS = 50;
/*    */   private static final int ARENA_SIZE = 100;
/*    */   
/*    */   private AlabastaDesertSimpleArena() {
/* 25 */     super(ArenaStyle.SIMPLE);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 30 */     Set<BlockPos> blocks = Sets.newHashSet();
/*    */     
/* 32 */     int bottomLayerOffset = 49;
/* 33 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 34 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*    */     
/* 36 */     blocks.addAll((new AlabastaDesertDetailsPart(challenge, 50)).buildPart(challenge.getShard(), groundLayer));
/*    */     
/* 38 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 17, 100, Blocks.field_150322_A.func_176223_P(), 0, null));
/*    */     
/* 40 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 100, 1, 100, Blocks.field_150354_m.func_176223_P(), 0, null));
/*    */     
/* 42 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*    */     
/* 44 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/* 49 */     int bottomLayerOffset = 49;
/* 50 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 51 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*    */     
/* 53 */     (new AlabastaDesertDetailsPart(challenge, 50)).buildPart(challenge.getShard(), groundLayer, queue);
/*    */     
/* 55 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 100, 1, 100, Blocks.field_150354_m.func_176223_P(), queue);
/*    */     
/* 57 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 17, 100, Blocks.field_150322_A.func_176223_P(), queue);
/*    */     
/* 59 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*    */   }
/*    */ 
/*    */   
/*    */   public MutableBoundingBox getArenaLimits() {
/* 64 */     return new MutableBoundingBox(-50, -50, -50, 50, 50, 50);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 68 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 15 - posId + 1, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() + 15);
/* 69 */     return new ChallengeArena.SpawnPosition(pos, 135.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 73 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 15, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() - 15);
/* 74 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 140.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\AlabastaDesertSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */