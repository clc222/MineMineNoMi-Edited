/*    */ package xyz.pixelatedw.mineminenomi.world.features.trees;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.trees.Tree;
/*    */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.ConfiguredFeature;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*    */ 
/*    */ public class MangroveTree
/*    */   extends Tree {
/*    */   @Nullable
/*    */   protected ConfiguredFeature<BaseTreeFeatureConfig, ?> func_225546_b_(Random random, boolean largeHive) {
/* 14 */     return ModFeatures.SABAODY_MANGROVE;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\trees\MangroveTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */