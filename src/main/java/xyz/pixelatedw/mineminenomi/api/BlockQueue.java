/*     */ package xyz.pixelatedw.mineminenomi.api;
/*     */ 
/*     */ import java.util.Deque;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedDeque;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BlockQueue
/*     */ {
/*  19 */   private static Map<World, BlockQueue> queueMap = new ConcurrentHashMap<>();
/*     */   
/*  21 */   private Deque<BlockQueueData> queue = new ConcurrentLinkedDeque<>();
/*     */   
/*     */   private final Supplier<Boolean> queueAction;
/*     */   private final ServerWorld world;
/*  25 */   private int speed = 50;
/*     */   private boolean isRunning = false;
/*     */   
/*     */   public void add(BlockQueueData block) {
/*  29 */     this.queue.add(block);
/*     */   }
/*     */   
/*     */   public void add(BlockPos pos, BlockState state) {
/*  33 */     add(new BlockQueueData(pos, state, 0));
/*     */   }
/*     */   
/*     */   public void add(BlockPos pos, BlockState state, int flag) {
/*  37 */     add(new BlockQueueData(pos, state, flag));
/*     */   }
/*     */   
/*     */   public BlockQueue(ServerWorld world) {
/*  41 */     this.world = world;
/*  42 */     this.queueAction = (() -> {
/*     */         BlockQueueData block = null;
/*     */         
/*     */         boolean hasTime = true;
/*     */         
/*     */         long start = System.currentTimeMillis();
/*     */         while (hasTime && (block = this.queue.pollLast()) != null) {
/*     */           hasTime = (System.currentTimeMillis() - start < this.speed);
/*  50 */           boolean canPlace = (block.canPlaceCheck == null || block.canPlaceCheck.check((IWorld)world, block.pos, block.state));
/*     */           if (!canPlace) {
/*     */             continue;
/*     */           }
/*     */           int flag = block.flag;
/*     */           if (block.state.func_177230_c() instanceof net.minecraft.block.FenceBlock) {
/*     */             flag |= 0x10;
/*     */           }
/*     */           WyHelper.setBlockStateInChunk((World)world, block.pos, block.state, flag);
/*     */           if (block.tileEntity != null) {
/*     */             TileEntity blockEntity = world.func_175625_s(block.pos);
/*     */             block.tileEntity.accept(blockEntity);
/*     */           } 
/*     */         } 
/*     */         return Boolean.valueOf(true);
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockQueue setSpeed(int speed) {
/*  74 */     this.speed = speed;
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public static BlockQueue getQueue(ServerWorld world) {
/*  79 */     if (!queueMap.containsKey(world)) {
/*  80 */       BlockQueue blockQueue = new BlockQueue(world);
/*  81 */       queueMap.put(world, blockQueue);
/*     */       
/*  83 */       return blockQueue;
/*     */     } 
/*  85 */     return queueMap.get(world);
/*     */   }
/*     */   
/*     */   public void start() {
/*  89 */     this.isRunning = true;
/*     */   }
/*     */   
/*     */   public void stop() {
/*  93 */     this.isRunning = false;
/*     */   }
/*     */   
/*     */   public boolean isRunning() {
/*  97 */     return this.isRunning;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 101 */     return (this.isRunning && this.queue.size() <= 0);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 105 */     return (this.queue.size() > 0);
/*     */   }
/*     */   
/*     */   public int getQueueSize() {
/* 109 */     return this.queue.size();
/*     */   }
/*     */   
/*     */   public void tick() {
/* 113 */     if (this.isRunning && 
/* 114 */       hasNext())
/* 115 */       this.queueAction.get(); 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ICanPlaceBlock {
/*     */     boolean check(IWorld param1IWorld, BlockPos param1BlockPos, BlockState param1BlockState); }
/*     */   
/*     */   public static class BlockQueueData {
/*     */     private final BlockPos pos;
/* 124 */     private Consumer<TileEntity> tileEntity = null; private final BlockState state; private final int flag;
/* 125 */     private BlockQueue.ICanPlaceBlock canPlaceCheck = null;
/*     */     
/*     */     public BlockQueueData(BlockPos pos, BlockState state, int flag) {
/* 128 */       this.pos = pos;
/* 129 */       this.state = state;
/* 130 */       this.flag = flag;
/*     */     }
/*     */     
/*     */     public BlockQueueData setTileEntity(Consumer<TileEntity> consumer) {
/* 134 */       this.tileEntity = consumer;
/* 135 */       return this;
/*     */     }
/*     */     
/*     */     public BlockQueueData setCanPlaceCheck(BlockQueue.ICanPlaceBlock canPlace) {
/* 139 */       this.canPlaceCheck = canPlace;
/* 140 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\BlockQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */