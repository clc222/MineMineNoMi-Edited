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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*    */ 
/*    */ public class HigumaSimpleArena extends ChallengeArena {
/* 18 */   public static final HigumaSimpleArena INSTANCE = new HigumaSimpleArena();
/*    */   
/*    */   private static final int ARENA_SIZE_RADIUS = 50;
/*    */   private static final int ARENA_SIZE = 100;
/*    */   
/*    */   private HigumaSimpleArena() {
/* 24 */     super(ArenaStyle.SIMPLE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/* 29 */     int bottomLayerOffset = 49;
/* 30 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 31 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*    */     
/* 33 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 100, 1, 100, Blocks.field_196658_i.func_176223_P(), queue);
/*    */     
/* 35 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 17, 100, Blocks.field_150346_d.func_176223_P(), queue);
/*    */     
/* 37 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 42 */     Set<BlockPos> blocks = Sets.newHashSet();
/*    */     
/* 44 */     int bottomLayerOffset = 49;
/* 45 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 46 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*    */     
/* 48 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 17, 100, Blocks.field_150346_d.func_176223_P(), 0, null));
/*    */     
/* 50 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 100, 1, 100, Blocks.field_196658_i.func_176223_P(), 0, null));
/*    */     
/* 52 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*    */     
/* 54 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public MutableBoundingBox getArenaLimits() {
/* 59 */     return new MutableBoundingBox(-50, -50, -50, 50, 50, 50);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 63 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 15 - posId + 1, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() + 15);
/* 64 */     return new ChallengeArena.SpawnPosition(pos, 135.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 68 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 15, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() - 15);
/* 69 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 140.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\HigumaSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */