/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.ISeedReader;
/*     */ import net.minecraft.world.gen.IWorldGenerationReader;
/*     */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.ConfiguredFeature;
/*     */ import net.minecraft.world.gen.feature.Features;
/*     */ import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
/*     */ import net.minecraft.world.gen.treedecorator.TreeDecorator;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class JungleBorderPart extends ArenaPart {
/*     */   public JungleBorderPart(InProgressChallenge challenge, int arenaSizeRadius) {
/*  27 */     super(challenge);
/*  28 */     this.arenaSizeRadius = arenaSizeRadius;
/*     */   }
/*     */   
/*     */   private final int arenaSizeRadius;
/*     */   
/*     */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/*  34 */     buildPart(world, spawnPos);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/*  39 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     int i;
/*  41 */     for (i = 0; i < 4; i++) {
/*  42 */       int circleLayer = 4 + 2 * i; double phi;
/*  43 */       for (phi = 0.0D; phi <= 6.283185307179586D; phi += 0.09817477042468103D) {
/*  44 */         double x = (this.arenaSizeRadius - circleLayer) * Math.cos(phi) + WyHelper.randomDouble() / 5.0D;
/*  45 */         double z = (this.arenaSizeRadius - circleLayer) * Math.sin(phi) + WyHelper.randomDouble() / 5.0D;
/*     */         
/*  47 */         int randX = (int)WyHelper.randomWithRange(world.field_73012_v, -5, 5);
/*  48 */         int randZ = (int)WyHelper.randomWithRange(world.field_73012_v, -5, 5);
/*     */         
/*  50 */         BlockPos pos = spawnPos.func_177963_a(x + randX, 0.0D, z + randZ);
/*     */         
/*  52 */         boolean isSmallTree = world.field_73012_v.nextBoolean();
/*  53 */         ConfiguredFeature<BaseTreeFeatureConfig, ?> feature = isSmallTree ? Features.field_243868_bN : Features.field_243871_bQ;
/*  54 */         int maxHeight = isSmallTree ? 15 : 25;
/*  55 */         maxHeight += world.field_73012_v.nextInt(maxHeight / 2);
/*     */         
/*  57 */         blocks.addAll(spawnTree(world, pos, feature, maxHeight));
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     for (i = 0; i < 6; i++) {
/*  62 */       int circleLayer = 4 + 3 * i; double phi;
/*  63 */       for (phi = 0.0D; phi <= 6.283185307179586D; phi += 0.04908738521234052D) {
/*  64 */         double x = (this.arenaSizeRadius - circleLayer) * Math.cos(phi) + WyHelper.randomDouble() / 5.0D;
/*  65 */         double z = (this.arenaSizeRadius - circleLayer) * Math.sin(phi) + WyHelper.randomDouble() / 5.0D;
/*     */         
/*  67 */         int randX = (int)WyHelper.randomWithRange(world.field_73012_v, -2, 2);
/*  68 */         int randZ = (int)WyHelper.randomWithRange(world.field_73012_v, -2, 2);
/*     */         
/*  70 */         BlockPos pos = spawnPos.func_177963_a(x + randX, 0.0D, z + randZ);
/*     */         
/*  72 */         blocks.addAll(spawnTree(world, pos, Features.field_243876_bV, 1));
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     return blocks;
/*     */   }
/*     */   
/*     */   private Set<BlockPos> spawnTree(ServerWorld world, BlockPos spawnPos, ConfiguredFeature<?, ?> feature, int maxHeight) {
/*  80 */     if (!(feature.field_222738_b instanceof BaseTreeFeatureConfig)) {
/*  81 */       return new HashSet<>();
/*     */     }
/*     */     
/*  84 */     Set<BlockPos> trunkBlocks = new HashSet<>();
/*  85 */     Set<BlockPos> foliageBlocks = new HashSet<>();
/*  86 */     Set<BlockPos> decoratorBlocks = new HashSet<>();
/*     */     
/*  88 */     MutableBoundingBox bb = getChallenge().getArena().getArenaLimits();
/*  89 */     BaseTreeFeatureConfig config = (BaseTreeFeatureConfig)feature.field_222738_b;
/*     */     
/*  91 */     int trunkHeight = config.field_236678_g_.func_236917_a_(world.field_73012_v);
/*  92 */     int foliageHeight = config.field_236677_f_.func_230374_a_(world.field_73012_v, trunkHeight, config);
/*  93 */     int k = trunkHeight - foliageHeight;
/*  94 */     int foliageRadius = config.field_236677_f_.func_230376_a_(world.field_73012_v, k);
/*  95 */     int minHeight = 5;
/*  96 */     if (maxHeight >= trunkHeight || maxHeight >= minHeight) {
/*  97 */       List<FoliagePlacer.Foliage> list = config.field_236678_g_.func_230382_a_((IWorldGenerationReader)world, world.field_73012_v, maxHeight, spawnPos, trunkBlocks, bb, config);
/*  98 */       list.forEach(foliage -> config.field_236677_f_.func_236752_a_((IWorldGenerationReader)world, world.field_73012_v, config, maxHeight, foliage, foliageHeight, foliageRadius, foliageBlocks, bb));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (!trunkBlocks.isEmpty() && !foliageBlocks.isEmpty() && !config.field_227370_o_.isEmpty()) {
/* 104 */       List<BlockPos> trunkList = Lists.newArrayList(trunkBlocks);
/* 105 */       List<BlockPos> foliageList = Lists.newArrayList(foliageBlocks);
/* 106 */       trunkList.sort(Comparator.comparingInt(Vector3i::func_177956_o));
/* 107 */       foliageList.sort(Comparator.comparingInt(Vector3i::func_177956_o));
/* 108 */       config.field_227370_o_.forEach(decorator -> decorator.func_225576_a_((ISeedReader)world, world.field_73012_v, trunkList, foliageList, decoratorBlocks, bb));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     Set<BlockPos> treeBlocks = new HashSet<>();
/* 114 */     treeBlocks.addAll(trunkBlocks);
/* 115 */     treeBlocks.addAll(foliageBlocks);
/* 116 */     treeBlocks.addAll(decoratorBlocks);
/*     */     
/* 118 */     return treeBlocks;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\JungleBorderPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */