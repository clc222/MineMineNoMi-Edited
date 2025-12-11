/*    */ package xyz.pixelatedw.mineminenomi.world.features.trees;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.gen.IWorldGenerationReader;
/*    */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*    */ import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
/*    */ import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
/*    */ import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
/*    */ 
/*    */ public class SabaodyTrunkPlacer extends AbstractTrunkPlacer {
/*    */   static {
/* 22 */     CODEC = RecordCodecBuilder.create(inst -> inst.group((App)Codec.intRange(0, 64).fieldOf("base_height").forGetter(()), (App)Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(()), (App)Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(()), (App)Codec.BOOL.fieldOf("is_chonky").forGetter(())).apply((Applicative)inst, SabaodyTrunkPlacer::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<SabaodyTrunkPlacer> CODEC;
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean isChonky;
/*    */ 
/*    */   
/* 35 */   private int width = 2;
/*    */   
/*    */   public SabaodyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, boolean isChonky) {
/* 38 */     super(baseHeight, heightRandA, heightRandB);
/* 39 */     this.isChonky = isChonky;
/* 40 */     this.width = this.isChonky ? 5 : 2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected TrunkPlacerType<?> func_230381_a_() {
/* 45 */     return ModFeatures.SABAODY_TRUNK_PLACER;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader world, Random rand, int height, BlockPos pos, Set<BlockPos> set, MutableBoundingBox bb, BaseTreeFeatureConfig config) {
/* 50 */     List<FoliagePlacer.Foliage> list = Lists.newArrayList();
/* 51 */     list.addAll(placeTrunkCustom(world, rand, height, pos, set, bb, config));
/*    */     int i;
/* 53 */     for (i = 0; i < height / 2; ) {
/* 54 */       float f = rand.nextFloat() * 6.2831855F;
/* 55 */       int j = 0;
/* 56 */       int k = 0;
/*    */       
/* 58 */       double rootLength = ((this.isChonky ? 10 : 5) + rand.nextInt(5));
/* 59 */       int l = 0; for (;; i += 2 + rand.nextInt(1)) { if (l < (this.isChonky ? 70 : 40)) {
/* 60 */           double x = Math.min(l, rootLength);
/* 61 */           j = (int)(0.5D + MathHelper.func_76134_b(f) * x);
/* 62 */           k = (int)(0.5D + MathHelper.func_76126_a(f) * x);
/* 63 */           BlockPos blockpos = pos.func_177982_a(j, i - 3 - l / 2, k);
/* 64 */           func_236911_a_(world, rand, blockpos, set, bb, config);
/* 65 */           func_236911_a_(world, rand, blockpos.func_177974_f(), set, bb, config);
/* 66 */           func_236911_a_(world, rand, blockpos.func_177976_e(), set, bb, config);
/* 67 */           func_236911_a_(world, rand, blockpos.func_177984_a(), set, bb, config);
/* 68 */           func_236911_a_(world, rand, blockpos.func_177978_c(), set, bb, config);
/* 69 */           func_236911_a_(world, rand, blockpos.func_177968_d(), set, bb, config);
/* 70 */           func_236911_a_(world, rand, blockpos.func_177977_b(), set, bb, config); l++; continue;
/*    */         }  }
/*    */     
/*    */     } 
/* 74 */     return list;
/*    */   }
/*    */   
/*    */   public List<FoliagePlacer.Foliage> placeTrunkCustom(IWorldGenerationReader world, Random rand, int height, BlockPos pos, Set<BlockPos> set, MutableBoundingBox bb, BaseTreeFeatureConfig config) {
/* 78 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 79 */     for (int i = 0; i < height; i++) {
/* 80 */       placeLogIfFreeWithOffset(world, rand, mutpos, set, bb, config, pos, 0, i, 0);
/* 81 */       if (i < height - 1) {
/* 82 */         for (int xSize = -this.width; xSize <= this.width; xSize++) {
/* 83 */           for (int zSize = -this.width; zSize <= this.width; zSize++) {
/* 84 */             int distance = Math.max(Math.abs(xSize), Math.abs(zSize));
/* 85 */             if (distance <= this.width && (xSize != 0 || zSize != 0)) {
/* 86 */               placeLogIfFreeWithOffset(world, rand, mutpos, set, bb, config, pos, xSize, i, zSize);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       }
/*    */     } 
/*    */     
/* 93 */     return (List<FoliagePlacer.Foliage>)ImmutableList.of(new FoliagePlacer.Foliage(pos.func_177981_b(height - (this.isChonky ? 0 : 2)), 0, true));
/*    */   }
/*    */   
/*    */   private static void placeLogIfFreeWithOffset(IWorldGenerationReader world, Random rand, BlockPos.Mutable mutpos, Set<BlockPos> set, MutableBoundingBox bb, BaseTreeFeatureConfig config, BlockPos pos, int x, int y, int z) {
/* 97 */     mutpos.func_239621_a_((Vector3i)pos, x, y, z);
/* 98 */     func_236910_a_(world, rand, mutpos, set, bb, config);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\trees\SabaodyTrunkPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */