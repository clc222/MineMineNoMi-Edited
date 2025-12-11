/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Comparator;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.util.math.vector.Vector3i;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.gen.IWorldGenerationReader;
/*    */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.ConfiguredFeature;
/*    */ import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
/*    */ import net.minecraft.world.gen.treedecorator.TreeDecorator;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ 
/*    */ public class TreePart
/*    */   extends ArenaPart {
/*    */   public TreePart(InProgressChallenge challenge, ConfiguredFeature<?, ?> feature, int maxHeight) {
/* 25 */     super(challenge);
/* 26 */     this.feature = feature;
/* 27 */     this.maxHeight = maxHeight;
/*    */   }
/*    */   private final ConfiguredFeature<?, ?> feature;
/*    */   private final int maxHeight;
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 33 */     buildPart(world, spawnPos);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 38 */     if (!(this.feature.field_222738_b instanceof BaseTreeFeatureConfig)) {
/* 39 */       return new HashSet<>();
/*    */     }
/*    */     
/* 42 */     Set<BlockPos> trunkBlocks = new HashSet<>();
/* 43 */     Set<BlockPos> foliageBlocks = new HashSet<>();
/* 44 */     Set<BlockPos> decoratorBlocks = new HashSet<>();
/*    */     
/* 46 */     MutableBoundingBox bb = getChallenge().getArena().getArenaLimits();
/* 47 */     BaseTreeFeatureConfig config = (BaseTreeFeatureConfig)this.feature.field_222738_b;
/*    */     
/* 49 */     int trunkHeight = config.field_236678_g_.func_236917_a_(world.field_73012_v);
/* 50 */     int foliageHeight = config.field_236677_f_.func_230374_a_(world.field_73012_v, trunkHeight, config);
/* 51 */     int k = trunkHeight - foliageHeight;
/* 52 */     int foliageRadius = config.field_236677_f_.func_230376_a_(world.field_73012_v, k);
/* 53 */     int minHeight = 5;
/* 54 */     if (this.maxHeight >= trunkHeight || this.maxHeight >= minHeight) {
/* 55 */       List<FoliagePlacer.Foliage> list = config.field_236678_g_.func_230382_a_((IWorldGenerationReader)world, world.field_73012_v, this.maxHeight, spawnPos, trunkBlocks, bb, config);
/* 56 */       list.forEach(foliage -> config.field_236677_f_.func_236752_a_((IWorldGenerationReader)world, world.field_73012_v, config, this.maxHeight, foliage, foliageHeight, foliageRadius, foliageBlocks, bb));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 61 */     if (!trunkBlocks.isEmpty() && !foliageBlocks.isEmpty() && !config.field_227370_o_.isEmpty()) {
/* 62 */       List<BlockPos> trunkList = Lists.newArrayList(trunkBlocks);
/* 63 */       List<BlockPos> foliageList = Lists.newArrayList(foliageBlocks);
/* 64 */       trunkList.sort(Comparator.comparingInt(Vector3i::func_177956_o));
/* 65 */       foliageList.sort(Comparator.comparingInt(Vector3i::func_177956_o));
/* 66 */       config.field_227370_o_.forEach(decorator -> decorator.func_225576_a_((ISeedReader)world, world.field_73012_v, trunkList, foliageList, decoratorBlocks, bb));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 71 */     Set<BlockPos> treeBlocks = new HashSet<>();
/* 72 */     treeBlocks.addAll(trunkBlocks);
/* 73 */     treeBlocks.addAll(foliageBlocks);
/* 74 */     treeBlocks.addAll(decoratorBlocks);
/*    */     
/* 76 */     return treeBlocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\TreePart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */