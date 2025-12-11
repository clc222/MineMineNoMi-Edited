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
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.ArlongParkPart;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*    */ 
/*    */ public class ArlongParkSimpleArena extends ChallengeArena {
/* 19 */   public static final ArlongParkSimpleArena INSTANCE = new ArlongParkSimpleArena();
/*    */   
/*    */   private static final int ARENA_XZ_SIZE_RADIUS = 50;
/*    */   private static final int ARENA_Y_SIZE_RADIUS = 50;
/*    */   private static final int ARENA_XZ_SIZE = 100;
/*    */   
/*    */   private ArlongParkSimpleArena() {
/* 26 */     super(ArenaStyle.SIMPLE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/* 31 */     int bottomLayerOffset = 49;
/* 32 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*    */     
/* 34 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 2, 100, Blocks.field_150354_m.func_176223_P(), queue);
/*    */     
/* 36 */     (new ArlongParkPart(challenge, 50, bottomLayer)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*    */     
/* 38 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 43 */     Set<BlockPos> blocks = Sets.newHashSet();
/*    */     
/* 45 */     int bottomLayerOffset = 49;
/* 46 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*    */     
/* 48 */     blocks.addAll((new ArlongParkPart(challenge, 50, bottomLayer)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*    */     
/* 50 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 100, 2, 100, Blocks.field_150354_m.func_176223_P(), 0, null));
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
/* 63 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 30 - posId + 1, challenge.getArenaPos().func_177956_o() - 12, challenge.getArenaPos().func_177952_p() - 40);
/* 64 */     return new ChallengeArena.SpawnPosition(pos, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 68 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 30, challenge.getArenaPos().func_177956_o() - 12, challenge.getArenaPos().func_177952_p() + 40);
/* 69 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 180.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\ArlongParkSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */