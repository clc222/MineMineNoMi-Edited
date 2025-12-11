/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ 
/*    */ public class SimpleBasePart extends ArenaPart {
/*    */   private boolean hasBedrock;
/*    */   
/*    */   public SimpleBasePart(InProgressChallenge challenge) {
/* 19 */     this(challenge, true);
/*    */   }
/*    */   
/*    */   public SimpleBasePart(InProgressChallenge challenge, boolean hasBedrock) {
/* 23 */     super(challenge);
/* 24 */     this.hasBedrock = hasBedrock;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 29 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 31 */     BlockPos bottomLayer = spawnPos.func_185334_h().func_177979_c(Math.abs((getParent().getArenaLimits()).field_78895_b) - 1);
/*    */     
/* 33 */     int xSize = getParent().getArenaLimits().func_78883_b() - 1;
/* 34 */     int zSize = getParent().getArenaLimits().func_78880_d() - 1;
/*    */     
/* 36 */     if (this.hasBedrock)
/*    */     {
/* 38 */       blocks.addAll(StructuresHelper.createCenteredFilledCube((World)world, bottomLayer, xSize, 1, zSize, Blocks.field_150357_h.func_176223_P(), 0, null));
/*    */     }
/*    */ 
/*    */     
/* 42 */     MutableBoundingBox bb = getParent().getArenaLimits();
/* 43 */     int barrierLimit = 4;
/* 44 */     for (int i = -barrierLimit; i < 1; i++) {
/* 45 */       BlockPos from = spawnPos.func_177982_a(bb.field_78897_a + i, bb.field_78895_b + i, bb.field_78896_c + i);
/* 46 */       BlockPos to = spawnPos.func_177982_a(bb.field_78893_d - 1 - i, bb.field_78894_e - 1 - i, bb.field_78892_f - 1 - i);
/* 47 */       blocks.addAll(StructuresHelper.hollowCube((World)world, from, to, 1, Blocks.field_180401_cv.func_176223_P(), 0, null));
/*    */     } 
/*    */     
/* 50 */     return blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 55 */     BlockPos bottomLayer = spawnPos.func_185334_h().func_177979_c(Math.abs((getParent().getArenaLimits()).field_78895_b) - 1);
/*    */     
/* 57 */     int xSize = getParent().getArenaLimits().func_78883_b() - 1;
/* 58 */     int zSize = getParent().getArenaLimits().func_78880_d() - 1;
/*    */     
/* 60 */     if (this.hasBedrock)
/*    */     {
/* 62 */       StructuresHelper.calcCenteredFilledCube((World)world, bottomLayer, xSize, 1, zSize, Blocks.field_150357_h.func_176223_P(), queue);
/*    */     }
/*    */ 
/*    */     
/* 66 */     MutableBoundingBox bb = getParent().getArenaLimits();
/* 67 */     int barrierLimit = 4;
/* 68 */     for (int i = -barrierLimit; i < 1; i++) {
/* 69 */       BlockPos from = spawnPos.func_177982_a(bb.field_78897_a + i, bb.field_78895_b + i, bb.field_78896_c + i);
/* 70 */       BlockPos to = spawnPos.func_177982_a(bb.field_78893_d - 1 - i, bb.field_78894_e - 1 - i, bb.field_78892_f - 1 - i);
/* 71 */       StructuresHelper.calcHollowCube((World)world, from, to, 1, Blocks.field_180401_cv.func_176223_P(), queue);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\SimpleBasePart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */