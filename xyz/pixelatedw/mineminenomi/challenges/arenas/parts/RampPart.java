/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*    */ 
/*    */ public class RampPart extends ArenaPart {
/*    */   private final int width;
/*    */   private final int height;
/*    */   private final WeightedList<BlockState> slabs;
/*    */   private final WeightedList<BlockState> fills;
/*    */   private final Direction direction;
/*    */   
/*    */   public RampPart(InProgressChallenge challenge, int width, int height, Block slabBlock, Block fillBlock, Direction direction) {
/* 25 */     this(challenge, width, height, new WeightedList(new Object[] { slabBlock.func_176223_P() }, ), new WeightedList(new Object[] { fillBlock.func_176223_P() }, ), direction);
/*    */   }
/*    */   
/*    */   public RampPart(InProgressChallenge challenge, int width, int height, WeightedList<BlockState> slabs, WeightedList<BlockState> fills, Direction direction) {
/* 29 */     super(challenge);
/* 30 */     this.width = width;
/* 31 */     this.height = height;
/* 32 */     this.slabs = slabs;
/* 33 */     this.fills = fills;
/* 34 */     this.direction = direction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 39 */     int xOffset = this.direction.func_176730_m().func_177958_n();
/* 40 */     int zOffset = this.direction.func_176730_m().func_177952_p();
/*    */     
/* 42 */     for (int i = 1; i < 1 + this.width; i++) {
/* 43 */       float slabsRowY = 0.0F;
/* 44 */       for (int row = 1; row <= this.height; row++) {
/* 45 */         for (int j = 1; j <= row; j++) {
/* 46 */           if (j == row) {
/* 47 */             if (j % 2 == 1) {
/* 48 */               BlockPos pos2 = spawnPos.func_177963_a((xOffset * j), Math.floor(-slabsRowY), (zOffset * j)).func_177982_a(zOffset * i, 0, xOffset * i);
/* 49 */               BlockState state = (BlockState)this.slabs.pick(world.func_201674_k());
/* 50 */               queue.add(pos2, state);
/*    */             } 
/*    */           } else {
/*    */             
/* 54 */             BlockPos pos2 = spawnPos.func_177963_a((xOffset * j), Math.floor(-slabsRowY), (zOffset * j)).func_177982_a(zOffset * i, 0, xOffset * i);
/* 55 */             BlockState state = (BlockState)this.fills.pick(world.func_201674_k());
/* 56 */             queue.add(pos2, state);
/*    */           } 
/*    */         } 
/* 59 */         slabsRowY += 0.5F;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 66 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 68 */     int xOffset = this.direction.func_176730_m().func_177958_n();
/* 69 */     int zOffset = this.direction.func_176730_m().func_177952_p();
/*    */     
/* 71 */     for (int i = 1; i < 1 + this.width; i++) {
/* 72 */       float slabsRowY = 0.0F;
/* 73 */       for (int row = 1; row <= this.height; row++) {
/* 74 */         for (int j = 1; j <= row; j++) {
/* 75 */           if (j == row) {
/* 76 */             if (j % 2 == 1) {
/* 77 */               BlockPos pos2 = spawnPos.func_177963_a((xOffset * j), Math.floor(-slabsRowY), (zOffset * j)).func_177982_a(zOffset * i, 0, xOffset * i);
/* 78 */               BlockState state = (BlockState)this.slabs.pick(world.func_201674_k());
/* 79 */               if (AbilityHelper.placeBlockIfAllowed((World)world, pos2, state, 0, null)) {
/* 80 */                 blocks.add(pos2);
/*    */               }
/*    */             } 
/*    */           } else {
/*    */             
/* 85 */             BlockPos pos2 = spawnPos.func_177963_a((xOffset * j), Math.floor(-slabsRowY), (zOffset * j)).func_177982_a(zOffset * i, 0, xOffset * i);
/* 86 */             BlockState state = (BlockState)this.fills.pick(world.func_201674_k());
/* 87 */             if (AbilityHelper.placeBlockIfAllowed((World)world, pos2, state, 0, null)) {
/* 88 */               blocks.add(pos2);
/*    */             }
/*    */           } 
/*    */         } 
/* 92 */         slabsRowY += 0.5F;
/*    */       } 
/*    */     } 
/*    */     
/* 96 */     return blocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\RampPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */