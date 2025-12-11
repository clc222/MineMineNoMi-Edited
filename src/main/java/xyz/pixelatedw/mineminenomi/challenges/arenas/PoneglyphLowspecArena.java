/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.PoneglyphBlock;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class PoneglyphLowspecArena extends ChallengeArena {
/* 20 */   public static final PoneglyphLowspecArena INSTANCE = new PoneglyphLowspecArena();
/*    */   
/*    */   private static final int ARENA_XZ_SIZE_RADIUS = 50;
/*    */   
/*    */   private static final int ARENA_Y_SIZE_RADIUS = 50;
/* 25 */   private final WeightedList<BlockState> list = new WeightedList(new Object[0]);
/*    */   
/*    */   private PoneglyphLowspecArena() {
/* 28 */     super(ArenaStyle.LOWSPEC);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/* 33 */     boolean isUlti = challenge.isUltimateDifficulty();
/*    */     
/* 35 */     this.list.clear();
/* 36 */     this.list.addEntry(((Block)ModBlocks.PONEGLYPH.get()).func_176223_P().func_206870_a((Property)PoneglyphBlock.TEXTURE, Integer.valueOf(0)), 100.0F);
/* 37 */     this.list.addEntry(((Block)ModBlocks.PONEGLYPH.get()).func_176223_P().func_206870_a((Property)PoneglyphBlock.TEXTURE, Integer.valueOf(1)), 100.0F);
/* 38 */     this.list.addEntry(((Block)ModBlocks.PONEGLYPH.get()).func_176223_P().func_206870_a((Property)PoneglyphBlock.TEXTURE, Integer.valueOf(2)), 100.0F);
/*    */     
/* 40 */     int bottomLayerOffset = 49;
/* 41 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*    */     
/* 43 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), bottomLayer, 50, 1, this.list, queue);
/*    */     
/* 45 */     StructuresHelper.calcHollowSphere((World)challenge.getShard(), bottomLayer, 50, 50, 2, Blocks.field_180401_cv.func_176223_P(), queue);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 50 */     Set<BlockPos> blocks = Sets.newHashSet();
/*    */     
/* 52 */     boolean isUlti = challenge.isUltimateDifficulty();
/*    */     
/* 54 */     this.list.clear();
/* 55 */     this.list.addEntry(((Block)ModBlocks.PONEGLYPH.get()).func_176223_P().func_206870_a((Property)PoneglyphBlock.TEXTURE, Integer.valueOf(0)), 100.0F);
/* 56 */     this.list.addEntry(((Block)ModBlocks.PONEGLYPH.get()).func_176223_P().func_206870_a((Property)PoneglyphBlock.TEXTURE, Integer.valueOf(1)), 100.0F);
/* 57 */     this.list.addEntry(((Block)ModBlocks.PONEGLYPH.get()).func_176223_P().func_206870_a((Property)PoneglyphBlock.TEXTURE, Integer.valueOf(2)), 100.0F);
/*    */     
/* 59 */     int bottomLayerOffset = 49;
/* 60 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*    */     
/* 62 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), bottomLayer, 50, 1, this.list, 0, null));
/*    */     
/* 64 */     blocks.addAll(StructuresHelper.createHollowSphere((World)challenge.getShard(), bottomLayer, 50, 50, 2, Blocks.field_180401_cv.func_176223_P(), 0, null));
/*    */     
/* 66 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public MutableBoundingBox getArenaLimits() {
/* 71 */     return new MutableBoundingBox(-50, -50, -50, 50, 50, 50);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 75 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 30 - posId + 1, challenge.getArenaPos().func_177956_o() - 45, challenge.getArenaPos().func_177952_p());
/* 76 */     return new ChallengeArena.SpawnPosition(pos, -90.0F, 23.0F);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 80 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 30, challenge.getArenaPos().func_177956_o() - 45, challenge.getArenaPos().func_177952_p());
/* 81 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 180.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\PoneglyphLowspecArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */