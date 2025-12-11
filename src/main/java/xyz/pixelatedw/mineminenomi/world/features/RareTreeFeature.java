/*     */ package xyz.pixelatedw.mineminenomi.world.features;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
/*     */ import net.minecraft.util.math.shapes.VoxelShapePart;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.ISeedReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldWriter;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraft.world.gen.IWorldGenerationBaseReader;
/*     */ import net.minecraft.world.gen.IWorldGenerationReader;
/*     */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*     */ import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
/*     */ import net.minecraft.world.gen.treedecorator.TreeDecorator;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.StructuresWorldData;
/*     */ 
/*     */ public class RareTreeFeature extends Feature<BaseTreeFeatureConfig> {
/*     */   public RareTreeFeature() {
/*  37 */     super(BaseTreeFeatureConfig.field_236676_a_);
/*     */   }
/*     */   
/*     */   public static boolean isFree(IWorldGenerationBaseReader pLevel, BlockPos pPos) {
/*  41 */     return (validTreePos(pLevel, pPos) || pLevel.func_217375_a(pPos, p_236417_0_ -> p_236417_0_.func_235714_a_((ITag)BlockTags.field_200031_h)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isVine(IWorldGenerationBaseReader pLevel, BlockPos pPos) {
/*  47 */     return pLevel.func_217375_a(pPos, p_236415_0_ -> p_236415_0_.func_203425_a(Blocks.field_150395_bd));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isBlockWater(IWorldGenerationBaseReader pLevel, BlockPos pPos) {
/*  53 */     return pLevel.func_217375_a(pPos, p_236413_0_ -> p_236413_0_.func_203425_a(Blocks.field_150355_j));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAirOrLeaves(IWorldGenerationBaseReader pLevel, BlockPos pPos) {
/*  59 */     return pLevel.func_217375_a(pPos, p_236411_0_ -> 
/*  60 */         (p_236411_0_.func_196958_f() || p_236411_0_.func_235714_a_((ITag)BlockTags.field_206952_E)));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isGrassOrDirtOrFarmland(IWorldGenerationBaseReader p_236418_0_, BlockPos p_236418_1_) {
/*  65 */     return p_236418_0_.func_217375_a(p_236418_1_, p_236409_0_ -> {
/*     */           Block block = p_236409_0_.func_177230_c();
/*  67 */           return (func_227250_b_(block) || block == Blocks.field_150458_ak);
/*     */         });
/*     */   }
/*     */   
/*     */   private static boolean isReplaceablePlant(IWorldGenerationBaseReader pLevel, BlockPos pPos) {
/*  72 */     return pLevel.func_217375_a(pPos, p_236406_0_ -> {
/*     */           Material material = p_236406_0_.func_185904_a();
/*     */           return (material == Material.field_151582_l);
/*     */         });
/*     */   }
/*     */   
/*     */   public static void setBlockKnownShape(IWorldWriter pLevel, BlockPos pPos, BlockState pState) {
/*  79 */     pLevel.func_180501_a(pPos, pState, 19);
/*     */   }
/*     */   
/*     */   public static boolean validTreePos(IWorldGenerationBaseReader pLevel, BlockPos pPos) {
/*  83 */     return (isAirOrLeaves(pLevel, pPos) || isReplaceablePlant(pLevel, pPos) || isBlockWater(pLevel, pPos));
/*     */   }
/*     */   
/*     */   private boolean doPlace(IWorldGenerationReader p_225557_1_, Random p_225557_2_, BlockPos p_225557_3_, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig p_225557_7_) {
/*     */     BlockPos blockpos;
/*  88 */     int i = p_225557_7_.field_236678_g_.func_236917_a_(p_225557_2_);
/*  89 */     int j = p_225557_7_.field_236677_f_.func_230374_a_(p_225557_2_, i, p_225557_7_);
/*  90 */     int k = i - j;
/*  91 */     int l = p_225557_7_.field_236677_f_.func_230376_a_(p_225557_2_, k);
/*     */     
/*  93 */     if (!p_225557_7_.field_227372_q_) {
/*  94 */       int k1, i1 = p_225557_1_.func_205770_a(Heightmap.Type.OCEAN_FLOOR, p_225557_3_).func_177956_o();
/*  95 */       int j1 = p_225557_1_.func_205770_a(Heightmap.Type.WORLD_SURFACE, p_225557_3_).func_177956_o();
/*  96 */       if (j1 - i1 > p_225557_7_.field_236680_i_) {
/*  97 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 101 */       if (p_225557_7_.field_236682_l_ == Heightmap.Type.OCEAN_FLOOR) {
/* 102 */         k1 = i1;
/* 103 */       } else if (p_225557_7_.field_236682_l_ == Heightmap.Type.WORLD_SURFACE) {
/* 104 */         k1 = j1;
/*     */       } else {
/* 106 */         k1 = p_225557_1_.func_205770_a(p_225557_7_.field_236682_l_, p_225557_3_).func_177956_o();
/*     */       } 
/*     */       
/* 109 */       blockpos = new BlockPos(p_225557_3_.func_177958_n(), k1, p_225557_3_.func_177952_p());
/*     */     } else {
/* 111 */       blockpos = p_225557_3_;
/*     */     } 
/*     */     
/* 114 */     if (blockpos.func_177956_o() >= 1 && blockpos.func_177956_o() + i + 1 <= 256) {
/* 115 */       if (!isGrassOrDirtOrFarmland((IWorldGenerationBaseReader)p_225557_1_, blockpos.func_177977_b())) {
/* 116 */         return false;
/*     */       }
/* 118 */       OptionalInt optionalint = p_225557_7_.field_236679_h_.func_236710_c_();
/* 119 */       int l1 = getMaxFreeTreeHeight((IWorldGenerationBaseReader)p_225557_1_, i, blockpos, p_225557_7_);
/* 120 */       if (l1 >= i || (optionalint.isPresent() && l1 >= optionalint.getAsInt())) {
/* 121 */         List<FoliagePlacer.Foliage> list = p_225557_7_.field_236678_g_.func_230382_a_(p_225557_1_, p_225557_2_, l1, blockpos, p_225557_4_, p_225557_6_, p_225557_7_);
/* 122 */         list.forEach(p_236407_8_ -> p_225557_7_.field_236677_f_.func_236752_a_(p_225557_1_, p_225557_2_, p_225557_7_, l1, p_236407_8_, j, l, p_225557_5_, p_225557_6_));
/*     */ 
/*     */         
/* 125 */         return true;
/*     */       } 
/* 127 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMaxFreeTreeHeight(IWorldGenerationBaseReader pLevel, int pTrunkHeight, BlockPos pTopPosition, BaseTreeFeatureConfig pConfig) {
/* 136 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 138 */     for (int i = 0; i <= pTrunkHeight + 1; i++) {
/* 139 */       int j = pConfig.field_236679_h_.func_230369_a_(pTrunkHeight, i);
/*     */       
/* 141 */       for (int k = -j; k <= j; k++) {
/* 142 */         for (int l = -j; l <= j; l++) {
/* 143 */           blockpos$mutable.func_239621_a_((Vector3i)pTopPosition, k, i, l);
/* 144 */           if (!isFree(pLevel, (BlockPos)blockpos$mutable) || (!pConfig.field_236681_j_ && isVine(pLevel, (BlockPos)blockpos$mutable))) {
/* 145 */             return i - 2;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     return pTrunkHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_230367_a_(IWorldWriter pLevel, BlockPos pPos, BlockState pState) {
/* 156 */     setBlockKnownShape(pLevel, pPos, pState);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {
/* 162 */     StructuresWorldData structData = StructuresWorldData.get();
/*     */     
/* 164 */     int x0 = pos.func_177958_n();
/* 165 */     int z0 = pos.func_177952_p();
/* 166 */     int y0 = world.func_201676_a(Heightmap.Type.WORLD_SURFACE_WG, x0, z0);
/* 167 */     BlockPos pos0 = new BlockPos(x0, y0, z0);
/*     */     
/* 169 */     if (structData.isNearHugeSabaodyTree(pos0, 1000.0D)) {
/* 170 */       return false;
/*     */     }
/*     */     
/* 173 */     Set<BlockPos> set = Sets.newHashSet();
/* 174 */     Set<BlockPos> set1 = Sets.newHashSet();
/* 175 */     Set<BlockPos> set2 = Sets.newHashSet();
/* 176 */     MutableBoundingBox mutableboundingbox = MutableBoundingBox.func_78887_a();
/* 177 */     boolean flag = doPlace((IWorldGenerationReader)world, rand, pos, set, set1, mutableboundingbox, config);
/* 178 */     if (mutableboundingbox.field_78897_a <= mutableboundingbox.field_78893_d && flag && !set.isEmpty()) {
/* 179 */       if (!config.field_227370_o_.isEmpty()) {
/* 180 */         List<BlockPos> list = Lists.newArrayList(set);
/* 181 */         List<BlockPos> list1 = Lists.newArrayList(set1);
/* 182 */         list.sort(Comparator.comparingInt(Vector3i::func_177956_o));
/* 183 */         list1.sort(Comparator.comparingInt(Vector3i::func_177956_o));
/* 184 */         config.field_227370_o_.forEach(p_236405_6_ -> p_236405_6_.func_225576_a_(world, rand, list, list1, set2, mutableboundingbox));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 189 */       VoxelShapePart voxelshapepart = updateLeaves((IWorld)world, mutableboundingbox, set, set2);
/* 190 */       Template.func_222857_a((IWorld)world, 3, voxelshapepart, mutableboundingbox.field_78897_a, mutableboundingbox.field_78895_b, mutableboundingbox.field_78896_c);
/*     */       
/* 192 */       structData.addHugeSabaodyTree(pos0);
/*     */       
/* 194 */       return true;
/*     */     } 
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private VoxelShapePart updateLeaves(IWorld pLevel, MutableBoundingBox pBoundingBox, Set<BlockPos> pLogPositions, Set<BlockPos> pFoliagePositions) {
/* 201 */     List<Set<BlockPos>> list = Lists.newArrayList();
/* 202 */     BitSetVoxelShapePart bitSetVoxelShapePart = new BitSetVoxelShapePart(pBoundingBox.func_78883_b(), pBoundingBox.func_78882_c(), pBoundingBox.func_78880_d());
/* 203 */     int i = 6;
/*     */     
/* 205 */     for (int j = 0; j < 6; j++) {
/* 206 */       list.add(Sets.newHashSet());
/*     */     }
/*     */     
/* 209 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 211 */     for (BlockPos blockpos : Lists.newArrayList(pFoliagePositions)) {
/* 212 */       if (pBoundingBox.func_175898_b((Vector3i)blockpos)) {
/* 213 */         bitSetVoxelShapePart.func_199625_a(blockpos.func_177958_n() - pBoundingBox.field_78897_a, blockpos.func_177956_o() - pBoundingBox.field_78895_b, blockpos.func_177952_p() - pBoundingBox.field_78896_c, true, true);
/*     */       }
/*     */     } 
/*     */     
/* 217 */     for (BlockPos blockpos1 : Lists.newArrayList(pLogPositions)) {
/* 218 */       if (pBoundingBox.func_175898_b((Vector3i)blockpos1)) {
/* 219 */         bitSetVoxelShapePart.func_199625_a(blockpos1.func_177958_n() - pBoundingBox.field_78897_a, blockpos1.func_177956_o() - pBoundingBox.field_78895_b, blockpos1.func_177952_p() - pBoundingBox.field_78896_c, true, true);
/*     */       }
/*     */       
/* 222 */       for (Direction direction : Direction.values()) {
/* 223 */         blockpos$mutable.func_239622_a_((Vector3i)blockpos1, direction);
/* 224 */         if (!pLogPositions.contains(blockpos$mutable)) {
/* 225 */           BlockState blockstate = pLevel.func_180495_p((BlockPos)blockpos$mutable);
/* 226 */           if (blockstate.func_235901_b_((Property)BlockStateProperties.field_208514_aa)) {
/* 227 */             ((Set<BlockPos>)list.get(0)).add(blockpos$mutable.func_185334_h());
/* 228 */             setBlockKnownShape((IWorldWriter)pLevel, (BlockPos)blockpos$mutable, (BlockState)blockstate.func_206870_a((Property)BlockStateProperties.field_208514_aa, Integer.valueOf(1)));
/* 229 */             if (pBoundingBox.func_175898_b((Vector3i)blockpos$mutable)) {
/* 230 */               bitSetVoxelShapePart.func_199625_a(blockpos$mutable.func_177958_n() - pBoundingBox.field_78897_a, blockpos$mutable.func_177956_o() - pBoundingBox.field_78895_b, blockpos$mutable.func_177952_p() - pBoundingBox.field_78896_c, true, true);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 238 */     for (int l = 1; l < 6; l++) {
/* 239 */       Set<BlockPos> set = list.get(l - 1);
/* 240 */       Set<BlockPos> set1 = list.get(l);
/*     */       
/* 242 */       for (BlockPos blockpos2 : set) {
/* 243 */         if (pBoundingBox.func_175898_b((Vector3i)blockpos2)) {
/* 244 */           bitSetVoxelShapePart.func_199625_a(blockpos2.func_177958_n() - pBoundingBox.field_78897_a, blockpos2.func_177956_o() - pBoundingBox.field_78895_b, blockpos2.func_177952_p() - pBoundingBox.field_78896_c, true, true);
/*     */         }
/*     */         
/* 247 */         for (Direction direction1 : Direction.values()) {
/* 248 */           blockpos$mutable.func_239622_a_((Vector3i)blockpos2, direction1);
/* 249 */           if (!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) {
/* 250 */             BlockState blockstate1 = pLevel.func_180495_p((BlockPos)blockpos$mutable);
/* 251 */             if (blockstate1.func_235901_b_((Property)BlockStateProperties.field_208514_aa)) {
/* 252 */               int k = ((Integer)blockstate1.func_177229_b((Property)BlockStateProperties.field_208514_aa)).intValue();
/* 253 */               if (k > l + 1) {
/* 254 */                 BlockState blockstate2 = (BlockState)blockstate1.func_206870_a((Property)BlockStateProperties.field_208514_aa, Integer.valueOf(l + 1));
/* 255 */                 setBlockKnownShape((IWorldWriter)pLevel, (BlockPos)blockpos$mutable, blockstate2);
/* 256 */                 if (pBoundingBox.func_175898_b((Vector3i)blockpos$mutable)) {
/* 257 */                   bitSetVoxelShapePart.func_199625_a(blockpos$mutable.func_177958_n() - pBoundingBox.field_78897_a, blockpos$mutable.func_177956_o() - pBoundingBox.field_78895_b, blockpos$mutable
/* 258 */                       .func_177952_p() - pBoundingBox.field_78896_c, true, true);
/*     */                 }
/*     */                 
/* 261 */                 set1.add(blockpos$mutable.func_185334_h());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     return (VoxelShapePart)bitSetVoxelShapePart;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\RareTreeFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */