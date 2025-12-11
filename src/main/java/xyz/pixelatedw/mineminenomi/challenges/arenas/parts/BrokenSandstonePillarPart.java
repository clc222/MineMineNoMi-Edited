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
/*    */ public class BrokenSandstonePillarPart extends ArenaPart {
/*    */   public BrokenSandstonePillarPart(InProgressChallenge challenge) {
/* 16 */     super(challenge);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 21 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 23 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)world, spawnPos, 3, 1, 3, Blocks.field_150322_A.func_176223_P(), 0, null));
/* 24 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)world, spawnPos.func_177984_a(), 2, 6, 2, Blocks.field_150322_A.func_176223_P(), 0, null));
/*    */     
/* 26 */     BlockPos airGap = spawnPos.func_177981_b(6);
/* 27 */     BlockPos airGap2 = spawnPos.func_177981_b(6).func_177978_c();
/* 28 */     BlockPos airGap3 = spawnPos.func_177981_b(5);
/*    */     
/* 30 */     world.func_180501_a(airGap, Blocks.field_150350_a.func_176223_P(), 0);
/* 31 */     world.func_180501_a(airGap2, Blocks.field_150350_a.func_176223_P(), 0);
/* 32 */     world.func_180501_a(airGap3, Blocks.field_150350_a.func_176223_P(), 0);
/*    */     
/* 34 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 39 */     StructuresHelper.calcCenteredFilledCube((World)world, spawnPos, 3, 1, 3, Blocks.field_150322_A.func_176223_P(), queue);
/* 40 */     StructuresHelper.calcCenteredFilledCube((World)world, spawnPos.func_177984_a(), 2, 6, 2, Blocks.field_150322_A.func_176223_P(), queue);
/*    */     
/* 42 */     BlockPos airGap = spawnPos.func_177981_b(6);
/* 43 */     BlockPos airGap2 = spawnPos.func_177981_b(6).func_177978_c();
/* 44 */     BlockPos airGap3 = spawnPos.func_177981_b(5);
/*    */     
/* 46 */     queue.add(airGap, Blocks.field_150350_a.func_176223_P());
/* 47 */     queue.add(airGap2, Blocks.field_150350_a.func_176223_P());
/* 48 */     queue.add(airGap3, Blocks.field_150350_a.func_176223_P());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\BrokenSandstonePillarPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */