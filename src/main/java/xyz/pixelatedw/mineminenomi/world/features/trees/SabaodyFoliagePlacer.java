/*    */ package xyz.pixelatedw.mineminenomi.world.features.trees;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.FeatureSpread;
/*    */ import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
/*    */ 
/*    */ public class SabaodyFoliagePlacer extends FoliagePlacer {
/*    */   static {
/* 17 */     CODEC = RecordCodecBuilder.create(inst -> inst.group((App)FeatureSpread.func_242254_a(0, 32, 8).fieldOf("radius").forGetter(()), (App)FeatureSpread.func_242254_a(0, 8, 8).fieldOf("offset").forGetter(()), (App)Codec.intRange(0, 16).fieldOf("height").forGetter(())).apply((Applicative)inst, SabaodyFoliagePlacer::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<SabaodyFoliagePlacer> CODEC;
/*    */ 
/*    */   
/*    */   protected final int height;
/*    */ 
/*    */ 
/*    */   
/*    */   public SabaodyFoliagePlacer(FeatureSpread radius, FeatureSpread offset, int height) {
/* 30 */     super(radius, offset);
/* 31 */     this.height = height;
/*    */   }
/*    */ 
/*    */   
/*    */   protected FoliagePlacerType<?> func_230371_a_() {
/* 36 */     return (FoliagePlacerType)ModFeatures.SABAODY_FOLIAGE_PLACER.get();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_230372_a_(IWorldGenerationReader world, Random rand, BaseTreeFeatureConfig config, int i1, FoliagePlacer.Foliage foliage, int height, int radius, Set<BlockPos> set, int offset, MutableBoundingBox bb) {
/* 41 */     for (int j = offset; j >= offset - height; j--) {
/* 42 */       int k = radius + foliage.func_236764_b_() + 1 - j;
/* 43 */       func_236753_a_(world, rand, config, foliage.func_236763_a_(), k, set, j, true, bb);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_230374_a_(Random random, int height, BaseTreeFeatureConfig config) {
/* 49 */     return this.height;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_230373_a_(Random pRandom, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
/* 54 */     if (p_230373_2_ + p_230373_4_ >= 32) {
/* 55 */       return true;
/*    */     }
/* 57 */     return (p_230373_2_ * p_230373_2_ + p_230373_4_ * p_230373_4_ > p_230373_5_ * p_230373_5_);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\trees\SabaodyFoliagePlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */