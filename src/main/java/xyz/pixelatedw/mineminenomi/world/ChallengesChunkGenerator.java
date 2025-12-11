/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.util.registry.Registry;
/*    */ import net.minecraft.util.registry.RegistryLookupCodec;
/*    */ import net.minecraft.world.Blockreader;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.biome.Biomes;
/*    */ import net.minecraft.world.biome.provider.BiomeProvider;
/*    */ import net.minecraft.world.biome.provider.SingleBiomeProvider;
/*    */ import net.minecraft.world.chunk.IChunk;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import net.minecraft.world.gen.WorldGenRegion;
/*    */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*    */ import net.minecraft.world.gen.settings.DimensionStructuresSettings;
/*    */ 
/*    */ public class ChallengesChunkGenerator
/*    */   extends ChunkGenerator {
/* 23 */   public static final Codec<ChallengesChunkGenerator> CODEC = RegistryLookupCodec.func_244331_a(Registry.field_239720_u_).xmap(ChallengesChunkGenerator::new, ChallengesChunkGenerator::biomes).stable().codec();
/*    */   
/*    */   private final Registry<Biome> registry;
/*    */ 
/*    */   
/*    */   public ChallengesChunkGenerator(Registry<Biome> registry) {
/* 29 */     super((BiomeProvider)new SingleBiomeProvider((Biome)registry.func_243576_d(Biomes.field_185440_P)), new DimensionStructuresSettings(false));
/* 30 */     this.registry = registry;
/*    */   }
/*    */ 
/*    */   
/*    */   public Registry<Biome> biomes() {
/* 35 */     return this.registry;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Codec<? extends ChunkGenerator> func_230347_a_() {
/* 41 */     return (Codec)CODEC;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ChunkGenerator func_230349_a_(long seed) {
/* 47 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225551_a_(WorldGenRegion pLevel, IChunk pChunk) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_230352_b_(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type p_222529_3_) {
/* 65 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockReader func_230348_a_(int x, int z) {
/* 71 */     return (IBlockReader)new Blockreader(new net.minecraft.block.BlockState[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\ChallengesChunkGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */