/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.util.RegistryKey;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.biome.BiomeMaker;
/*    */ import net.minecraftforge.common.BiomeDictionary;
/*    */ import net.minecraftforge.common.BiomeManager;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModBiomes {
/* 15 */   public static final RegistryObject<Biome> DRUM = WyRegistry.registerBiome("Drum", BiomeMaker::func_244252_r);
/* 16 */   public static final RegistryObject<Biome> RAIGO = WyRegistry.registerBiome("Thunder Plains", BiomeMaker::func_244252_r);
/* 17 */   public static final RegistryObject<Biome> SABAODY = WyRegistry.registerBiome("Sabaody", BiomeMaker::func_244252_r);
/*    */ 
/*    */   
/*    */   public static void generateBiomes() {
/* 21 */     addBiome((Biome)DRUM.get(), BiomeManager.BiomeType.ICY, 1, new BiomeDictionary.Type[] { BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD });
/* 22 */     addBiome((Biome)RAIGO.get(), BiomeManager.BiomeType.WARM, 1, new BiomeDictionary.Type[] { BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.RARE, BiomeDictionary.Type.OVERWORLD });
/* 23 */     addBiome((Biome)SABAODY.get(), BiomeManager.BiomeType.WARM, 1, new BiomeDictionary.Type[] { BiomeDictionary.Type.WET, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.OVERWORLD });
/*    */   }
/*    */ 
/*    */   
/*    */   private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
/* 28 */     RegistryKey<Biome> key = RegistryKey.func_240903_a_(ForgeRegistries.Keys.BIOMES, 
/* 29 */         Objects.<ResourceLocation>requireNonNull(ForgeRegistries.BIOMES.getKey((IForgeRegistryEntry)biome)));
/*    */     
/* 31 */     BiomeDictionary.addTypes(key, types);
/* 32 */     BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
/*    */   }
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 36 */     WyRegistry.BIOMES.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModBiomes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */