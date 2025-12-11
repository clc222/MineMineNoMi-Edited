/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.HashMap;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.util.registry.WorldGenRegistries;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.ConfiguredFeature;
/*     */ import net.minecraft.world.gen.feature.Feature;
/*     */ import net.minecraft.world.gen.feature.Features;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.OreFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.ProbabilityConfig;
/*     */ import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
/*     */ import net.minecraft.world.gen.placement.ConfiguredPlacement;
/*     */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*     */ import net.minecraft.world.gen.placement.Placement;
/*     */ import net.minecraft.world.gen.placement.TopSolidRangeConfig;
/*     */ import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
/*     */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.configs.SizedBlockStateFeatureConfig;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.trees.SabaodyFoliagePlacer;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.trees.SabaodyTrunkPlacer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
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
/*     */ public class ModFeatures
/*     */ {
/*  50 */   public static final HashMap<Feature<?>, ConfiguredFeature<?, ?>> REGISTERED_FEATURES = new HashMap<>();
/*     */   
/*  52 */   public static final RegistryObject<Feature<NoFeatureConfig>> PONEGLYPH = WyRegistry.registerFeature("poneglyph", xyz.pixelatedw.mineminenomi.world.features.PoneglyphFeature::new);
/*  53 */   public static final RegistryObject<Feature<NoFeatureConfig>> OCEAN_FLOOR = WyRegistry.registerFeature("ocean_floor", xyz.pixelatedw.mineminenomi.world.features.ocean.OceanFloorModifierFeature::new);
/*  54 */   public static final RegistryObject<Feature<NoFeatureConfig>> SNOW_FLOOR = WyRegistry.registerFeature("snow_floor", xyz.pixelatedw.mineminenomi.world.features.SnowFloorFeature::new);
/*  55 */   public static final RegistryObject<Feature<NoFeatureConfig>> SKY_ISLANDS_NATURAL_DIAL = WyRegistry.registerFeature("sky_natural_dial", xyz.pixelatedw.mineminenomi.world.features.NaturalDialFeature::new);
/*  56 */   public static final RegistryObject<Feature<NoFeatureConfig>> BEACH_NATURAL_DIAL = WyRegistry.registerFeature("beach_natural_dial", xyz.pixelatedw.mineminenomi.world.features.NaturalDialFeature::new);
/*  57 */   public static final RegistryObject<Feature<ProbabilityConfig>> LARGE_ICE_SPIKE = WyRegistry.registerFeature("large_ice_spike", xyz.pixelatedw.mineminenomi.world.features.LargeIceSpikeFeature::new);
/*  58 */   public static final RegistryObject<Feature<ProbabilityConfig>> SNOW_MOUNTAIN = WyRegistry.registerFeature("snow_mountain", xyz.pixelatedw.mineminenomi.world.features.SnowMountainFeature::new);
/*  59 */   public static final RegistryObject<Feature<BaseTreeFeatureConfig>> RARE_TREE = WyRegistry.registerFeature("rare_tree", xyz.pixelatedw.mineminenomi.world.features.RareTreeFeature::new);
/*  60 */   public static final RegistryObject<Feature<SizedBlockStateFeatureConfig>> BOULDER = WyRegistry.registerFeature("boulder", xyz.pixelatedw.mineminenomi.world.features.BoulderFeature::new);
/*  61 */   public static final RegistryObject<Feature<BlockStateFeatureConfig>> LARGE_LAKE = WyRegistry.registerFeature("large_lake", xyz.pixelatedw.mineminenomi.world.features.LargeLakesFeature::new);
/*     */   
/*  63 */   public static final TrunkPlacerType<SabaodyTrunkPlacer> SABAODY_TRUNK_PLACER = registerTrunkPlacer("sabaody_trunk_placer", SabaodyTrunkPlacer.CODEC);
/*     */   
/*  65 */   public static final RegistryObject<FoliagePlacerType<SabaodyFoliagePlacer>> SABAODY_FOLIAGE_PLACER = WyRegistry.registerFoliagePlacer("sabaody_foliage_placer", () -> new FoliagePlacerType(SabaodyFoliagePlacer.CODEC));
/*     */   
/*  67 */   public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SABAODY_MANGROVE = getConfiguredTreeFeature("sabaody_tree");
/*     */   
/*     */   public static void setupFeatures(BiomeLoadingEvent event) {
/*  70 */     ConfiguredFeature<?, ?> poneglyph = setupConfiguredFeature(((Feature)PONEGLYPH.get())
/*  71 */         .func_225566_b_((IFeatureConfig)IFeatureConfig.field_202429_e)
/*  72 */         .func_227228_a_((ConfiguredPlacement)((ConfiguredPlacement)Placement.field_242907_l.func_227446_a_((IPlacementConfig)new TopSolidRangeConfig(1, 1, 100)).func_242728_a()).func_242731_b(1)));
/*     */     
/*  74 */     ConfiguredFeature<?, ?> skyNaturalDial = setupConfiguredFeature(((Feature)SKY_ISLANDS_NATURAL_DIAL.get())
/*  75 */         .func_225566_b_((IFeatureConfig)IFeatureConfig.field_202429_e)
/*  76 */         .func_227228_a_((ConfiguredPlacement)((ConfiguredPlacement)Placement.field_242907_l.func_227446_a_((IPlacementConfig)new TopSolidRangeConfig(150, 100, 256)).func_242728_a()).func_242731_b(50)));
/*     */     
/*  78 */     ConfiguredFeature<?, ?> beachNaturalDial = setupConfiguredFeature(((Feature)SKY_ISLANDS_NATURAL_DIAL.get())
/*  79 */         .func_225566_b_((IFeatureConfig)IFeatureConfig.field_202429_e)
/*  80 */         .func_227228_a_((ConfiguredPlacement)Features.Placements.field_243996_g.func_242731_b(1)));
/*     */     
/*  82 */     event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, poneglyph);
/*     */     
/*  84 */     if (event.getCategory() == Biome.Category.BEACH || event.getCategory() == Biome.Category.OCEAN) {
/*  85 */       event.getGeneration().func_242513_a(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, skyNaturalDial);
/*  86 */       event.getGeneration().func_242513_a(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, beachNaturalDial);
/*     */       
/*  88 */       ConfiguredFeature<?, ?> kairosekiOre = registerOre((Block)ModBlocks.KAIROSEKI_ORE
/*  89 */           .get(), new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, ((Block)ModBlocks.KAIROSEKI_ORE
/*     */ 
/*     */             
/*  92 */             .get()).func_176223_P(), 6), Placement.field_242907_l
/*     */           
/*  94 */           .func_227446_a_((IPlacementConfig)new TopSolidRangeConfig(1, 1, 100)), CommonConfig.INSTANCE
/*  95 */           .getKairosekiSpawnCount());
/*     */ 
/*     */       
/*  98 */       event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, kairosekiOre);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredTreeFeature(String featureName) {
/* 103 */     RegistryKey<ConfiguredFeature<?, ?>> key = RegistryKey.func_240903_a_(Registry.field_243552_au, new ResourceLocation("mineminenomi", featureName));
/* 104 */     return (ConfiguredFeature<BaseTreeFeatureConfig, ?>)WorldGenRegistries.field_243653_e.func_230516_a_(key);
/*     */   }
/*     */   
/*     */   private static <F extends Feature<?>, S extends ConfiguredFeature<?, ?>> S setupConfiguredFeature(S feature) {
/* 108 */     Feature feature1 = ((ConfiguredFeature)feature).field_222737_a;
/*     */     
/* 110 */     return (S)Registry.func_218322_a(WorldGenRegistries.field_243653_e, feature1
/* 111 */         .getRegistryName(), feature);
/*     */   }
/*     */ 
/*     */   
/*     */   private static ConfiguredFeature<?, ?> registerOre(Block ore, OreFeatureConfig oreFeatureConfig, ConfiguredPlacement configuredPlacement, int count) {
/* 116 */     return (ConfiguredFeature<?, ?>)Registry.func_218322_a(WorldGenRegistries.field_243653_e, ore
/* 117 */         .getRegistryName(), ((ConfiguredFeature)Feature.field_202290_aj
/*     */         
/* 119 */         .func_225566_b_((IFeatureConfig)oreFeatureConfig)
/* 120 */         .func_227228_a_(configuredPlacement)
/* 121 */         .func_242728_a())
/* 122 */         .func_242731_b(count));
/*     */   }
/*     */ 
/*     */   
/*     */   private static <P extends net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String key, Codec<P> codec) {
/* 127 */     key = "mineminenomi:" + key;
/* 128 */     TrunkPlacerType<P> trunkPlacerType = null;
/*     */     try {
/* 130 */       trunkPlacerType = ObfuscationReflectionHelper.findConstructor(TrunkPlacerType.class, new Class[] { Codec.class }).newInstance(new Object[] { codec });
/* 131 */     } catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/* 132 */       e.printStackTrace();
/*     */     } 
/* 134 */     return (TrunkPlacerType<P>)Registry.func_218325_a(Registry.field_239701_aw_, key, trunkPlacerType);
/*     */   }
/*     */   
/*     */   private static <P extends net.minecraft.world.gen.foliageplacer.FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacer(String key, Codec<P> codec) {
/* 138 */     key = "mineminenomi:" + key;
/* 139 */     FoliagePlacerType<P> foliagePlacerType = null;
/* 140 */     return (FoliagePlacerType<P>)Registry.func_218325_a(Registry.field_229389_v_, key, foliagePlacerType);
/*     */   }
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 144 */     WyRegistry.FEATURES.register(eventBus);
/* 145 */     WyRegistry.FOLIAGE_PLACER_TYPES.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModFeatures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */