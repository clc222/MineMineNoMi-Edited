/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ 
/*    */ public class SmallSandPilePart extends ArenaPart {
/*    */   public SmallSandPilePart(InProgressChallenge challenge) {
/* 16 */     super(challenge);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 21 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 23 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)world, spawnPos, 4, 1, 4, Blocks.field_150354_m.func_176223_P(), 0, null));
/*    */     
/* 25 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 30 */     StructuresHelper.calcCenteredFilledCube((World)world, spawnPos, 4, 1, 4, Blocks.field_150354_m.func_176223_P(), queue);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\SmallSandPilePart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */