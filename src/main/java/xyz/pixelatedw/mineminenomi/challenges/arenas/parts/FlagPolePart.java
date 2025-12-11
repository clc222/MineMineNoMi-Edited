/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.FlagBlock;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class FlagPolePart
/*    */   extends ArenaPart {
/*    */   private int height;
/*    */   
/*    */   public FlagPolePart(InProgressChallenge challenge, int height, CanvasSize canvasSize, ResourceLocation factionName) {
/* 28 */     super(challenge);
/* 29 */     this.height = height;
/* 30 */     this.canvasSize = canvasSize;
/* 31 */     this.factionName = factionName;
/*    */   }
/*    */   private CanvasSize canvasSize; private ResourceLocation factionName;
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 36 */     BlockPos poleTop = spawnPos.func_177981_b(this.height);
/*    */     
/* 38 */     BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/* 39 */     state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)this.canvasSize);
/*    */     
/* 41 */     BlockQueue.BlockQueueData blockData = new BlockQueue.BlockQueueData(poleTop.func_177978_c(), state, 515);
/* 42 */     blockData.setTileEntity(te -> {
/*    */           if (te != null && te instanceof FlagTileEntity) {
/*    */             ((FlagTileEntity)te).setFaction(this.factionName);
/*    */           }
/*    */         });
/* 47 */     queue.add(blockData);
/*    */     
/* 49 */     StructuresHelper.calcFillCube((World)world, spawnPos, poleTop, Blocks.field_150411_aY.func_176223_P(), queue);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 56 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 58 */     BlockPos poleTop = spawnPos.func_177981_b(this.height);
/*    */     
/* 60 */     blocks.addAll(StructuresHelper.fillCube((World)world, spawnPos, poleTop, Blocks.field_150411_aY.func_176223_P(), 0, null));
/*    */     
/* 62 */     BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*    */     
/* 64 */     state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)this.canvasSize);
/*    */     
/* 66 */     world.func_180501_a(poleTop.func_177978_c(), state, 0);
/* 67 */     blocks.add(poleTop.func_177978_c());
/*    */     
/* 69 */     TileEntity flagTile = world.func_175625_s(poleTop.func_177978_c());
/* 70 */     if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 71 */       ((FlagTileEntity)flagTile).setFaction(this.factionName);
/*    */     }
/*    */     
/* 74 */     return blocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\FlagPolePart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */