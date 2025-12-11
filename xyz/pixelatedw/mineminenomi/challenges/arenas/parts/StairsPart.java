/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.HorizontalBlock;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class StairsPart
/*    */   extends ArenaPart
/*    */ {
/*    */   private final int width;
/*    */   private final int height;
/*    */   
/*    */   public StairsPart(InProgressChallenge challenge, int width, int height, Block stairsBlock, Block fillBlock, Direction direction) {
/* 25 */     super(challenge);
/* 26 */     this.width = width;
/* 27 */     this.height = height;
/* 28 */     this.stairsBlock = stairsBlock.func_176223_P();
/* 29 */     if (this.stairsBlock.func_235901_b_((Property)HorizontalBlock.field_185512_D)) {
/* 30 */       this.stairsBlock = (BlockState)this.stairsBlock.func_206870_a((Property)HorizontalBlock.field_185512_D, (Comparable)direction.func_176734_d());
/*    */     }
/* 32 */     this.fillBlock = fillBlock.func_176223_P();
/* 33 */     this.direction = direction;
/*    */   }
/*    */   private BlockState stairsBlock; private final BlockState fillBlock; private final Direction direction;
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 38 */     int xOffset = this.direction.func_176730_m().func_177958_n();
/* 39 */     int zOffset = this.direction.func_176730_m().func_177952_p();
/*    */     
/* 41 */     for (int i = 1; i < 1 + this.width; i++) {
/* 42 */       int stairsRowY = 0;
/* 43 */       for (int row = 1; row <= this.height; row++) {
/* 44 */         for (int j = 1; j <= row; j++) {
/* 45 */           if (j == row) {
/* 46 */             BlockPos pos2 = spawnPos.func_177982_a(xOffset * j, -stairsRowY, zOffset * j).func_177982_a(zOffset * i, 0, xOffset * i);
/* 47 */             queue.add(pos2, this.stairsBlock);
/*    */           } else {
/*    */             
/* 50 */             BlockPos pos2 = spawnPos.func_177982_a(xOffset * j, -stairsRowY, zOffset * j).func_177982_a(zOffset * i, 0, xOffset * i);
/* 51 */             queue.add(pos2, this.fillBlock);
/*    */           } 
/*    */         } 
/* 54 */         stairsRowY++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 61 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 63 */     int xOffset = this.direction.func_176730_m().func_177958_n();
/* 64 */     int zOffset = this.direction.func_176730_m().func_177952_p();
/*    */     
/* 66 */     for (int i = 1; i < 1 + this.width; i++) {
/* 67 */       int stairsRowY = 0;
/* 68 */       for (int row = 1; row <= this.height; row++) {
/* 69 */         for (int j = 1; j <= row; j++) {
/* 70 */           if (j == row) {
/* 71 */             BlockPos pos2 = spawnPos.func_177982_a(xOffset * j, -stairsRowY, zOffset * j).func_177982_a(zOffset * i, 0, xOffset * i);
/* 72 */             if (AbilityHelper.placeBlockIfAllowed((World)world, pos2, this.stairsBlock, 0, null)) {
/* 73 */               blocks.add(pos2);
/*    */             }
/*    */           } else {
/*    */             
/* 77 */             BlockPos pos2 = spawnPos.func_177982_a(xOffset * j, -stairsRowY, zOffset * j).func_177982_a(zOffset * i, 0, xOffset * i);
/* 78 */             if (AbilityHelper.placeBlockIfAllowed((World)world, pos2, this.fillBlock, 0, null)) {
/* 79 */               blocks.add(pos2);
/*    */             }
/*    */           } 
/*    */         } 
/* 83 */         stairsRowY++;
/*    */       } 
/*    */     } 
/*    */     
/* 87 */     return blocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\StairsPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */