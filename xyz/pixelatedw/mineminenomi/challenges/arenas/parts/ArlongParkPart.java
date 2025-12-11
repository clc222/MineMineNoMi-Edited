/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.FourWayBlock;
/*     */ import net.minecraft.block.WallBlock;
/*     */ import net.minecraft.block.WallHeight;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ 
/*     */ public class ArlongParkPart extends ArenaPart {
/*     */   private static final int GROUND_LEVEL = 35;
/*     */   private static final int PIER_DISTANCE = 40;
/*     */   private static final int PIER_LENGTH = 45;
/*     */   private static final int PIER_WIDTH = 10;
/*     */   private static final int PIER_HEIGHT = 35;
/*     */   private static final int FENCE_HEIGHT = 7;
/*     */   private final int arenaSizeRadius;
/*     */   private final BlockPos bottomLayer;
/*     */   
/*     */   public ArlongParkPart(InProgressChallenge challenge, int arenaSizeRadius, BlockPos bottomLayer) {
/*  30 */     super(challenge);
/*  31 */     this.arenaSizeRadius = arenaSizeRadius;
/*  32 */     this.bottomLayer = bottomLayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/*  37 */     BlockPos bottomNECorner = this.bottomLayer.func_177964_d(this.arenaSizeRadius - 1).func_177965_g(this.arenaSizeRadius - 2);
/*  38 */     BlockPos splitSWCorner = this.bottomLayer.func_177981_b(35).func_177985_f(10).func_177970_e(this.arenaSizeRadius - 2);
/*  39 */     BlockPos splitNWCorner = this.bottomLayer.func_177981_b(35).func_177985_f(10).func_177964_d(this.arenaSizeRadius - 1);
/*  40 */     BlockPos bottomNWCorner = this.bottomLayer.func_177964_d(this.arenaSizeRadius - 1).func_177985_f(this.arenaSizeRadius - 1);
/*     */     
/*  42 */     BlockPos groundLayer = this.bottomLayer.func_177981_b(36);
/*     */ 
/*     */     
/*  45 */     BlockPos firstPierStart = splitSWCorner.func_177964_d(this.arenaSizeRadius / 3 + 3);
/*  46 */     BlockPos firstPierEnd = firstPierStart.func_177965_g(45).func_177964_d(10);
/*     */     
/*  48 */     StructuresHelper.calcFillCube((World)world, firstPierStart, firstPierEnd.func_177979_c(35), Blocks.field_150348_b.func_176223_P(), queue);
/*     */     
/*  50 */     BlockPos secondPierStart = firstPierStart.func_177964_d(50);
/*  51 */     BlockPos secondPierEnd = secondPierStart.func_177965_g(45).func_177964_d(10);
/*     */     
/*  53 */     StructuresHelper.calcFillCube((World)world, secondPierStart, secondPierEnd.func_177979_c(35), Blocks.field_150348_b.func_176223_P(), queue);
/*     */ 
/*     */     
/*  56 */     BlockPos southFenceStart = groundLayer.func_177970_e(this.arenaSizeRadius - 2).func_177985_f(this.arenaSizeRadius - 1);
/*  57 */     BlockPos southFenceEnd = southFenceStart.func_177965_g(this.arenaSizeRadius * 2 - 2).func_177981_b(7);
/*     */     
/*  59 */     BlockState nsWallState = (BlockState)((BlockState)Blocks.field_222413_lB.func_176223_P().func_206870_a((Property)WallBlock.field_235612_b_, (Comparable)WallHeight.TALL)).func_206870_a((Property)WallBlock.field_235615_e_, (Comparable)WallHeight.TALL);
/*  60 */     BlockState nsFenceState = (BlockState)((BlockState)Blocks.field_150411_aY.func_176223_P().func_206870_a((Property)FourWayBlock.field_196411_b, Boolean.valueOf(true))).func_206870_a((Property)FourWayBlock.field_196414_y, Boolean.valueOf(true));
/*     */     
/*  62 */     StructuresHelper.calcFillCube((World)world, southFenceStart, southFenceEnd, nsWallState, queue);
/*  63 */     StructuresHelper.calcFillCube((World)world, southFenceStart.func_177981_b(8), southFenceEnd.func_177981_b(1), nsFenceState, queue);
/*  64 */     StructuresHelper.calcFillCube((World)world, southFenceStart.func_177977_b(), southFenceEnd.func_177978_c().func_177979_c(42), Blocks.field_150348_b.func_176223_P(), queue);
/*     */     
/*  66 */     BlockPos northFenceStart = groundLayer.func_177964_d(this.arenaSizeRadius - 1).func_177985_f(this.arenaSizeRadius - 1);
/*  67 */     BlockPos northFenceEnd = northFenceStart.func_177965_g(this.arenaSizeRadius * 2 - 2).func_177981_b(7);
/*     */     
/*  69 */     StructuresHelper.calcFillCube((World)world, northFenceStart, northFenceEnd, nsWallState, queue);
/*  70 */     StructuresHelper.calcFillCube((World)world, northFenceStart.func_177981_b(8), northFenceEnd.func_177981_b(1), nsFenceState, queue);
/*  71 */     StructuresHelper.calcFillCube((World)world, northFenceStart.func_177977_b(), northFenceEnd.func_177968_d().func_177979_c(42), Blocks.field_150348_b.func_176223_P(), queue);
/*     */     
/*  73 */     BlockPos westFenceStart = southFenceStart.func_177981_b(7);
/*  74 */     BlockPos westFenceEnd = northFenceStart;
/*     */     
/*  76 */     BlockState westWallState = (BlockState)((BlockState)Blocks.field_222413_lB.func_176223_P().func_206870_a((Property)WallBlock.field_235613_c_, (Comparable)WallHeight.TALL)).func_206870_a((Property)WallBlock.field_235614_d_, (Comparable)WallHeight.TALL);
/*  77 */     BlockState westFenceState = (BlockState)((BlockState)Blocks.field_150411_aY.func_176223_P().func_206870_a((Property)FourWayBlock.field_196409_a, Boolean.valueOf(true))).func_206870_a((Property)FourWayBlock.field_196413_c, Boolean.valueOf(true));
/*     */     
/*  79 */     StructuresHelper.calcFillCube((World)world, westFenceStart, westFenceEnd, westWallState, queue);
/*  80 */     StructuresHelper.calcFillCube((World)world, westFenceStart.func_177981_b(1), northFenceStart.func_177981_b(8), westFenceState, queue);
/*     */ 
/*     */     
/*  83 */     StructuresHelper.calcFillCube((World)world, splitSWCorner, bottomNWCorner, Blocks.field_150348_b.func_176223_P(), queue);
/*  84 */     StructuresHelper.calcFillCube((World)world, bottomNECorner, splitSWCorner, Blocks.field_150355_j.func_176223_P(), queue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 111 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     
/* 113 */     BlockPos bottomNECorner = this.bottomLayer.func_177964_d(this.arenaSizeRadius - 1).func_177965_g(this.arenaSizeRadius - 2);
/* 114 */     BlockPos splitSWCorner = this.bottomLayer.func_177981_b(35).func_177985_f(10).func_177970_e(this.arenaSizeRadius - 2);
/* 115 */     BlockPos splitNWCorner = this.bottomLayer.func_177981_b(35).func_177985_f(10).func_177964_d(this.arenaSizeRadius - 1);
/* 116 */     BlockPos bottomNWCorner = this.bottomLayer.func_177964_d(this.arenaSizeRadius - 1).func_177985_f(this.arenaSizeRadius - 1);
/*     */     
/* 118 */     BlockPos groundLayer = this.bottomLayer.func_177981_b(36);
/*     */ 
/*     */     
/* 121 */     blocks.addAll(StructuresHelper.fillCube((World)world, bottomNECorner, splitSWCorner, Blocks.field_150355_j.func_176223_P(), 0, null));
/* 122 */     blocks.addAll(StructuresHelper.fillCube((World)world, splitSWCorner, bottomNWCorner, Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */ 
/*     */     
/* 125 */     BlockPos firstPierStart = splitSWCorner.func_177964_d(this.arenaSizeRadius / 3 + 3);
/* 126 */     BlockPos firstPierEnd = firstPierStart.func_177965_g(45).func_177964_d(10);
/*     */     
/* 128 */     blocks.addAll(StructuresHelper.fillCube((World)world, firstPierStart, firstPierEnd.func_177979_c(35), Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */     
/* 130 */     BlockPos secondPierStart = firstPierStart.func_177964_d(50);
/* 131 */     BlockPos secondPierEnd = secondPierStart.func_177965_g(45).func_177964_d(10);
/*     */     
/* 133 */     blocks.addAll(StructuresHelper.fillCube((World)world, secondPierStart, secondPierEnd.func_177979_c(35), Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */ 
/*     */     
/* 136 */     BlockPos southFenceStart = groundLayer.func_177970_e(this.arenaSizeRadius - 2).func_177985_f(this.arenaSizeRadius - 1);
/* 137 */     BlockPos southFenceEnd = southFenceStart.func_177965_g(this.arenaSizeRadius * 2 - 2).func_177981_b(7);
/*     */     
/* 139 */     blocks.addAll(StructuresHelper.fillCube((World)world, southFenceStart, southFenceEnd, Blocks.field_222413_lB.func_176223_P(), 256, null));
/* 140 */     blocks.addAll(StructuresHelper.fillCube((World)world, southFenceStart.func_177981_b(8), southFenceEnd.func_177981_b(1), Blocks.field_150411_aY.func_176223_P(), 256, null));
/* 141 */     blocks.addAll(StructuresHelper.fillCube((World)world, southFenceStart.func_177977_b(), southFenceEnd.func_177978_c().func_177979_c(42), Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */     
/* 143 */     BlockPos northFenceStart = groundLayer.func_177964_d(this.arenaSizeRadius - 1).func_177985_f(this.arenaSizeRadius - 1);
/* 144 */     BlockPos northFenceEnd = northFenceStart.func_177965_g(this.arenaSizeRadius * 2 - 2).func_177981_b(7);
/*     */     
/* 146 */     blocks.addAll(StructuresHelper.fillCube((World)world, northFenceStart, northFenceEnd, Blocks.field_222413_lB.func_176223_P(), 256, null));
/* 147 */     blocks.addAll(StructuresHelper.fillCube((World)world, northFenceStart.func_177981_b(8), northFenceEnd.func_177981_b(1), Blocks.field_150411_aY.func_176223_P(), 256, null));
/* 148 */     blocks.addAll(StructuresHelper.fillCube((World)world, northFenceStart.func_177977_b(), northFenceEnd.func_177968_d().func_177979_c(42), Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */     
/* 150 */     BlockPos westFenceStart = southFenceStart.func_177981_b(7);
/* 151 */     BlockPos westFenceEnd = northFenceStart;
/*     */     
/* 153 */     blocks.addAll(StructuresHelper.fillCube((World)world, westFenceStart, westFenceEnd, Blocks.field_222413_lB.func_176223_P(), 256, null));
/* 154 */     blocks.addAll(StructuresHelper.fillCube((World)world, westFenceStart.func_177981_b(1), northFenceStart.func_177981_b(8), Blocks.field_150411_aY.func_176223_P(), 256, null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     return blocks;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\ArlongParkPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */