/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.util.SharedSeedRandom;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.util.math.vector.Vector3i;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.biome.provider.BiomeProvider;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.structure.Structure;
/*    */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class OPStructure<C extends IFeatureConfig>
/*    */   extends Structure<C>
/*    */ {
/*    */   public OPStructure(Codec<C> pCodec) {
/* 23 */     super(pCodec);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract boolean biomeCheck(BiomeLoadingEvent paramBiomeLoadingEvent);
/*    */ 
/*    */   
/*    */   public abstract StructuresHelper.StructureFaction getFaction();
/*    */   
/*    */   protected boolean func_230363_a_(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, C featureConfig) {
/* 33 */     BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
/* 34 */     int oceanLevel = chunkGenerator.func_230356_f_();
/*    */     
/* 36 */     int dist = (biome.func_201856_r() == Biome.Category.OCEAN) ? 64 : 32;
/* 37 */     Iterator<BlockPos> iter = StructuresHelper.SPAWNED_STRUCTURES.iterator();
/* 38 */     while (iter.hasNext()) {
/*    */       
/* 40 */       BlockPos pos = iter.next();
/* 41 */       if (Math.sqrt(centerOfChunk.func_177951_i((Vector3i)pos)) < dist)
/*    */       {
/* 43 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 47 */     for (Biome biomeCheck : biomeSource.func_225530_a_(chunkX * 16 + 9, oceanLevel, chunkZ * 16 + 9, 32)) {
/*    */       
/* 49 */       if (!biomeCheck.func_242440_e().func_242493_a(this))
/*    */       {
/* 51 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\OPStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */