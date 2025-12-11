/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*     */ 
/*     */ import java.util.Deque;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentLinkedDeque;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ 
/*     */ public class BlockPilePart extends ArenaPart {
/*     */   private BlockState state;
/*     */   private Function<BlockState, BlockState> changeFunc;
/*     */   
/*     */   public BlockPilePart(InProgressChallenge challenge, BlockState state) {
/*  25 */     super(challenge); this.changeFunc = (state -> state);
/*  26 */     this.state = state;
/*     */   }
/*     */   
/*     */   public void setChangeFunc(Function<BlockState, BlockState> changeFUnc) {
/*  30 */     this.changeFunc = changeFUnc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/*  35 */     int i = 2 + world.field_73012_v.nextInt(2);
/*  36 */     int j = 2 + world.field_73012_v.nextInt(2);
/*     */     
/*  38 */     Deque<BlockQueue.BlockQueueData> haystackDeque = new ConcurrentLinkedDeque<>();
/*     */     
/*  40 */     for (BlockPos blockpos : BlockPos.func_218278_a(spawnPos.func_177982_a(-i, 1, -j), spawnPos.func_177982_a(i, 0, j))) {
/*  41 */       int k = spawnPos.func_177958_n() - blockpos.func_177958_n();
/*  42 */       int l = spawnPos.func_177952_p() - blockpos.func_177952_p();
/*  43 */       if ((k * k + l * l) <= world.field_73012_v.nextFloat() * 10.0F - world.field_73012_v.nextFloat() * 6.0F) {
/*  44 */         this.state = this.changeFunc.apply(this.state);
/*  45 */         BlockQueue.BlockQueueData queueData = new BlockQueue.BlockQueueData(blockpos.func_185334_h(), this.state, 515);
/*  46 */         queueData.setCanPlaceCheck((world2, pos, state) -> mayPlaceOn(world2, pos));
/*     */ 
/*     */         
/*  49 */         haystackDeque.add(queueData); continue;
/*     */       } 
/*  51 */       if (world.field_73012_v.nextFloat() < 0.031D) {
/*  52 */         this.state = this.changeFunc.apply(this.state);
/*  53 */         BlockQueue.BlockQueueData queueData = new BlockQueue.BlockQueueData(blockpos.func_185334_h(), this.state, 515);
/*  54 */         queueData.setCanPlaceCheck((world2, pos, state) -> mayPlaceOn(world2, pos));
/*     */ 
/*     */         
/*  57 */         haystackDeque.add(queueData);
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     while (!haystackDeque.isEmpty()) {
/*  62 */       BlockQueue.BlockQueueData queueData = haystackDeque.pollLast();
/*  63 */       queue.add(queueData);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/*  69 */     Set<BlockPos> blocks = new HashSet<>();
/*     */     
/*  71 */     int i = 2 + world.field_73012_v.nextInt(2);
/*  72 */     int j = 2 + world.field_73012_v.nextInt(2);
/*     */     
/*  74 */     for (BlockPos blockpos : BlockPos.func_218278_a(spawnPos.func_177982_a(-i, 0, -j), spawnPos.func_177982_a(i, 1, j))) {
/*  75 */       int k = spawnPos.func_177958_n() - blockpos.func_177958_n();
/*  76 */       int l = spawnPos.func_177952_p() - blockpos.func_177952_p();
/*  77 */       if ((k * k + l * l) <= world.field_73012_v.nextFloat() * 10.0F - world.field_73012_v.nextFloat() * 6.0F) {
/*  78 */         if (mayPlaceOn((IWorld)world, blockpos)) {
/*  79 */           this.state = this.changeFunc.apply(this.state);
/*  80 */           if (AbilityHelper.placeBlockIfAllowed((World)world, blockpos, this.state, 0, null))
/*  81 */             blocks.add(blockpos); 
/*     */         } 
/*     */         continue;
/*     */       } 
/*  85 */       if (world.field_73012_v.nextFloat() < 0.031D && 
/*  86 */         mayPlaceOn((IWorld)world, blockpos)) {
/*  87 */         this.state = this.changeFunc.apply(this.state);
/*  88 */         if (AbilityHelper.placeBlockIfAllowed((World)world, blockpos, this.state, 0, null)) {
/*  89 */           blocks.add(blockpos);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return blocks;
/*     */   }
/*     */   
/*     */   private boolean mayPlaceOn(IWorld level, BlockPos pos) {
/*  99 */     BlockPos blockpos = pos.func_177977_b();
/* 100 */     BlockState blockstate = level.func_180495_p(blockpos);
/* 101 */     return blockstate.func_224755_d((IBlockReader)level, blockpos, Direction.UP);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\BlockPilePart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */