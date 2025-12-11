/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.util.registry.WorldGenRegistries;
/*     */ import net.minecraft.world.gen.DimensionSettings;
/*     */ import net.minecraft.world.gen.FlatGenerationSettings;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.StructureFeature;
/*     */ import net.minecraft.world.gen.feature.structure.IStructurePieceType;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.settings.DimensionStructuresSettings;
/*     */ import net.minecraft.world.gen.settings.StructureSeparationSettings;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
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
/*     */ public class ModStructures
/*     */ {
/*  67 */   private static final DeferredRegister<Structure<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, "mineminenomi");
/*  68 */   public static final HashMap<Structure<?>, StructureFeature<?, ?>> REGISTERED_STRUCTURES = new HashMap<>();
/*     */ 
/*     */   
/*  71 */   public static final RegistryObject<Structure<NoFeatureConfig>> PIRATE_SMALL_SHIP = REGISTRY.register("pirate_small_ship", xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipStructure::new);
/*  72 */   public static final RegistryObject<Structure<NoFeatureConfig>> PIRATE_MEDIUM_SHIP = REGISTRY.register("pirate_medium_ship", xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate.PirateMediumShipStructure::new);
/*  73 */   public static final RegistryObject<Structure<NoFeatureConfig>> PIRATE_LARGE_SHIP = REGISTRY.register("pirate_large_ship", xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipStructure::new);
/*  74 */   public static final RegistryObject<Structure<NoFeatureConfig>> MARINE_BATTLESHIP = REGISTRY.register("marine_battleship", xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine.MarineBattleshipStructure::new);
/*  75 */   public static final RegistryObject<Structure<NoFeatureConfig>> GHOST_SHIP = REGISTRY.register("ghost_ship", xyz.pixelatedw.mineminenomi.world.features.structures.ghostship.GhostShipStructure::new);
/*     */ 
/*     */   
/*  78 */   public static final RegistryObject<Structure<NoFeatureConfig>> MARINE_SMALL_BASE = REGISTRY.register("marine_small_base", xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine.MarineSmallBaseStructure::new);
/*  79 */   public static final RegistryObject<Structure<NoFeatureConfig>> BANDIT_SMALL_BASE = REGISTRY.register("bandit_small_base", xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBaseStructure::new);
/*  80 */   public static final RegistryObject<Structure<NoFeatureConfig>> MARINE_LARGE_BASE = REGISTRY.register("marine_large_base", xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBaseStructure::new);
/*  81 */   public static final RegistryObject<Structure<NoFeatureConfig>> BANDIT_LARGE_BASE = REGISTRY.register("bandit_large_base", xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit.BanditLargeBaseStructure::new);
/*  82 */   public static final RegistryObject<Structure<NoFeatureConfig>> MARINE_CAMP = REGISTRY.register("marine_camp", xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampStructure::new);
/*  83 */   public static final RegistryObject<Structure<NoFeatureConfig>> BANDIT_CAMP = REGISTRY.register("bandit_camp", xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampStructure::new);
/*  84 */   public static final RegistryObject<Structure<NoFeatureConfig>> MARINE_WATCH_TOWER = REGISTRY.register("marine_watch_tower", xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine.MarineWatchTowerStructure::new);
/*     */ 
/*     */   
/*  87 */   public static final RegistryObject<Structure<NoFeatureConfig>> SWORDSMAN_DOJO = REGISTRY.register("swordsman_dojo", xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo.SwordsmanDojoStructure::new);
/*  88 */   public static final RegistryObject<Structure<NoFeatureConfig>> BRAWLER_RING = REGISTRY.register("brawler_ring", xyz.pixelatedw.mineminenomi.world.features.structures.training.brawlerring.BrawlerRingStructure::new);
/*  89 */   public static final RegistryObject<Structure<NoFeatureConfig>> BLACKLEG_KITCHEN = REGISTRY.register("blackleg_kitchen", xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen.BlacklegKitchenStructure::new);
/*  90 */   public static final RegistryObject<Structure<NoFeatureConfig>> MEDIC_TENT = REGISTRY.register("medic_tent", xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent.MedicTentStructure::new);
/*  91 */   public static final RegistryObject<Structure<NoFeatureConfig>> SNIPER_RANGE = REGISTRY.register("sniper_range", xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange.SniperRangeStructure::new);
/*  92 */   public static final RegistryObject<Structure<NoFeatureConfig>> TAVERN = REGISTRY.register("tavern", xyz.pixelatedw.mineminenomi.world.features.structures.tavern.TavernStructure::new);
/*     */ 
/*     */   
/*  95 */   public static final RegistryObject<Structure<NoFeatureConfig>> SKY_ISLAND_CAMP = REGISTRY.register("sky_island_camp", xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp.SkyIslandCampStructure::new);
/*  96 */   public static final RegistryObject<Structure<NoFeatureConfig>> SKY_ISLAND_HOUSE = REGISTRY.register("sky_island_house", xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house.SkyIslandHouseStructure::new);
/*  97 */   public static final RegistryObject<Structure<NoFeatureConfig>> SKY_ISLAND_TOWN = REGISTRY.register("sky_island_town", xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.town.SkyIslandTownStructure::new);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setupStructures() {
/* 108 */     setupMapSpacingAndLand(((Structure)PIRATE_SMALL_SHIP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(8, 4, 54036900), false);
/* 109 */     setupMapSpacingAndLand(((Structure)PIRATE_MEDIUM_SHIP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(16, 4, 14036660), false);
/* 110 */     setupMapSpacingAndLand(((Structure)PIRATE_LARGE_SHIP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(16, 8, 991036900), false);
/* 111 */     setupMapSpacingAndLand(((Structure)MARINE_BATTLESHIP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(20, 16, 995474911), false);
/* 112 */     setupMapSpacingAndLand(((Structure)GHOST_SHIP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(16, 8, 115474911), false);
/*     */ 
/*     */     
/* 115 */     setupMapSpacingAndLand(((Structure)MARINE_SMALL_BASE.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(25, 16, 150718788), true);
/* 116 */     setupMapSpacingAndLand(((Structure)BANDIT_SMALL_BASE.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(20, 10, 908718788), false);
/* 117 */     setupMapSpacingAndLand(((Structure)MARINE_LARGE_BASE.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(25, 16, 450718615), false);
/* 118 */     setupMapSpacingAndLand(((Structure)BANDIT_LARGE_BASE.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(25, 16, 1207542615), false);
/* 119 */     setupMapSpacingAndLand(((Structure)MARINE_CAMP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(16, 8, 258711788), true);
/* 120 */     setupMapSpacingAndLand(((Structure)BANDIT_CAMP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(16, 8, 358711788), true);
/* 121 */     setupMapSpacingAndLand(((Structure)MARINE_WATCH_TOWER.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(16, 10, 810715715), true);
/*     */ 
/*     */     
/* 124 */     setupMapSpacingAndLand(((Structure)SWORDSMAN_DOJO.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(32, 16, 165132894), true);
/* 125 */     setupMapSpacingAndLand(((Structure)BRAWLER_RING.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(32, 16, 265132894), true);
/* 126 */     setupMapSpacingAndLand(((Structure)BLACKLEG_KITCHEN.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(32, 16, 315132894), true);
/* 127 */     setupMapSpacingAndLand(((Structure)MEDIC_TENT.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(32, 16, 465152894), true);
/* 128 */     setupMapSpacingAndLand(((Structure)SNIPER_RANGE.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(32, 16, 111132894), true);
/* 129 */     setupMapSpacingAndLand(((Structure)TAVERN.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(32, 16, 115532894), true);
/*     */ 
/*     */     
/* 132 */     setupMapSpacingAndLand(((Structure)SKY_ISLAND_CAMP.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(40, 30, 235132894), false);
/* 133 */     setupMapSpacingAndLand(((Structure)SKY_ISLAND_HOUSE.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(40, 30, 335123894), false);
/* 134 */     setupMapSpacingAndLand(((Structure)SKY_ISLAND_TOWN.get()).func_236391_a_((IFeatureConfig)IFeatureConfig.field_202429_e), new StructureSeparationSettings(80, 50, 435132894), false);
/*     */     
/* 136 */     Pieces.registerAllPieces();
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
/*     */   public static <F extends Structure<?>, S extends StructureFeature<?, ?>> void setupMapSpacingAndLand(S configuredStructure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
/* 150 */     Structure<?> structure = ((StructureFeature)configuredStructure).field_236268_b_;
/*     */     
/* 152 */     REGISTERED_STRUCTURES.put(structure, (StructureFeature<?, ?>)configuredStructure);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     Structure.field_236365_a_.put(structure.getRegistryName().toString(), structure);
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
/* 173 */     if (transformSurroundingLand)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       Structure.field_236384_t_ = (List)ImmutableList.builder().addAll(Structure.field_236384_t_).add(structure).build();
/*     */     }
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
/* 199 */     DimensionStructuresSettings.field_236191_b_ = ImmutableMap.builder().putAll((Map)DimensionStructuresSettings.field_236191_b_).put(structure, structureSeparationSettings).build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     WorldGenRegistries.field_243658_j.func_239659_c_().forEach(settings -> {
/*     */           Map<Structure<?>, StructureSeparationSettings> structureMap = ((DimensionSettings)settings.getValue()).func_236108_a_().func_236195_a_();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           if (structureMap instanceof ImmutableMap) {
/*     */             Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             tempMap.put(structure, structureSeparationSettings);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             ((DimensionSettings)settings.getValue()).func_236108_a_().func_236195_a_();
/*     */           } else {
/*     */             structureMap.put(structure, structureSeparationSettings);
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.field_243654_f;
/* 239 */     Registry.func_218322_a(registry, structure.getRegistryName(), configuredStructure);
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
/* 254 */     FlatGenerationSettings.field_202247_j.put(structure, configuredStructure);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 259 */     REGISTRY.register(eventBus);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Pieces
/*     */   {
/* 265 */     public static final IStructurePieceType PIRATE_SMALL_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate.PirateSmallShipPiece::new;
/* 266 */     public static final IStructurePieceType PIRATE_MEDIUM_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.mediumship.pirate.PirateMediumShipPiece::new;
/* 267 */     public static final IStructurePieceType PIRATE_LARGE_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate.PirateLargeShipPiece::new;
/* 268 */     public static final IStructurePieceType MARINE_BATTLESHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine.MarineBattleshipPiece::new;
/* 269 */     public static final IStructurePieceType GHOST_SHIP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.ghostship.GhostShipPiece::new;
/*     */ 
/*     */     
/* 272 */     public static final IStructurePieceType MARINE_SMALL_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine.MarineSmallBasePiece::new;
/* 273 */     public static final IStructurePieceType BANDIT_SMALL_BASE_HOUSE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBasePieces.HousePiece::new;
/* 274 */     public static final IStructurePieceType BANDIT_SMALL_BASE_UNDERGROUND_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit.BanditSmallBasePieces.UndergroundPiece::new;
/* 275 */     public static final IStructurePieceType MARINE_LARGE_BASE_BODY_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBasePieces.MainBodyPiece::new;
/* 276 */     public static final IStructurePieceType MARINE_LARGE_BASE_PRISON_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine.MarineLargeBasePieces.PrisonPiece::new;
/* 277 */     public static final IStructurePieceType BANDIT_LARGE_BASE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit.BanditLargeBasePiece::new;
/* 278 */     public static final IStructurePieceType MARINE_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine.MarineCampPieces.Piece::new;
/* 279 */     public static final IStructurePieceType BANDIT_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit.BanditCampPieces.Piece::new;
/* 280 */     public static final IStructurePieceType MARINE_WATCH_TOWER_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine.MarineWatchTowerPiece::new;
/*     */ 
/*     */     
/* 283 */     public static final IStructurePieceType SWORDSMAN_DOJO_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo.SwordsmanDojoPiece::new;
/* 284 */     public static final IStructurePieceType BRAWLER_RING_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.brawlerring.BrawlerRingPiece::new;
/* 285 */     public static final IStructurePieceType BLACKLEG_KITCHEN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.blacklegkitchen.BlacklegKitchenPiece::new;
/* 286 */     public static final IStructurePieceType MEDIC_TENT_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.medictent.MedicTentPiece::new;
/* 287 */     public static final IStructurePieceType SNIPER_RANGE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange.SniperRangePiece::new;
/* 288 */     public static final IStructurePieceType TAVERN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.tavern.TavernPiece::new;
/*     */ 
/*     */     
/* 291 */     public static final IStructurePieceType SKY_ISLAND_CAMP_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp.SkyIslandCampPiece::new;
/* 292 */     public static final IStructurePieceType SKY_ISLAND_HOUSE_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house.SkyIslandHousePiece::new;
/* 293 */     public static final IStructurePieceType SKY_ISLAND_TOWN_PIECE = xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.town.SkyIslandTownPiece::new;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static void registerAllPieces() {
/* 303 */       registerStructurePiece(PIRATE_SMALL_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_small_ship_piece"));
/* 304 */       registerStructurePiece(PIRATE_MEDIUM_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_medium_ship_piece"));
/* 305 */       registerStructurePiece(PIRATE_LARGE_SHIP_PIECE, new ResourceLocation("mineminenomi", "pirate_large_ship_piece"));
/* 306 */       registerStructurePiece(MARINE_BATTLESHIP_PIECE, new ResourceLocation("mineminenomi", "marine_battleship_piece"));
/* 307 */       registerStructurePiece(GHOST_SHIP_PIECE, new ResourceLocation("mineminenomi", "ghost_ship_piece"));
/*     */       
/* 309 */       registerStructurePiece(MARINE_LARGE_BASE_BODY_PIECE, new ResourceLocation("mineminenomi", "marine_large_base_body_piece"));
/* 310 */       registerStructurePiece(MARINE_LARGE_BASE_PRISON_PIECE, new ResourceLocation("mineminenomi", "marine_large_base_prison_piece"));
/* 311 */       registerStructurePiece(MARINE_SMALL_BASE_PIECE, new ResourceLocation("mineminenomi", "marine_small_base_piece"));
/* 312 */       registerStructurePiece(BANDIT_SMALL_BASE_HOUSE_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_house_piece"));
/* 313 */       registerStructurePiece(BANDIT_SMALL_BASE_UNDERGROUND_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_underground_piece"));
/* 314 */       registerStructurePiece(BANDIT_LARGE_BASE_PIECE, new ResourceLocation("mineminenomi", "bandit_small_base_piece"));
/* 315 */       registerStructurePiece(MARINE_CAMP_PIECE, new ResourceLocation("mineminenomi", "marine_camp_piece"));
/* 316 */       registerStructurePiece(BANDIT_CAMP_PIECE, new ResourceLocation("mineminenomi", "bandit_camp_piece"));
/* 317 */       registerStructurePiece(MARINE_WATCH_TOWER_PIECE, new ResourceLocation("mineminenomi", "marine_watch_tower_piece"));
/*     */       
/* 319 */       registerStructurePiece(SWORDSMAN_DOJO_PIECE, new ResourceLocation("mineminenomi", "swordsman_dojo_piece"));
/* 320 */       registerStructurePiece(BRAWLER_RING_PIECE, new ResourceLocation("mineminenomi", "brawler_ring_piece"));
/* 321 */       registerStructurePiece(BLACKLEG_KITCHEN_PIECE, new ResourceLocation("mineminenomi", "black_leg_kitchen_piece"));
/* 322 */       registerStructurePiece(MEDIC_TENT_PIECE, new ResourceLocation("mineminenomi", "medic_tent_piece"));
/* 323 */       registerStructurePiece(SNIPER_RANGE_PIECE, new ResourceLocation("mineminenomi", "sniper_range_piece"));
/* 324 */       registerStructurePiece(TAVERN_PIECE, new ResourceLocation("mineminenomi", "tavern_piece"));
/*     */       
/* 326 */       registerStructurePiece(SKY_ISLAND_CAMP_PIECE, new ResourceLocation("mineminenomi", "sky_island_camp_piece"));
/* 327 */       registerStructurePiece(SKY_ISLAND_HOUSE_PIECE, new ResourceLocation("mineminenomi", "sky_island_house_piece"));
/* 328 */       registerStructurePiece(SKY_ISLAND_TOWN_PIECE, new ResourceLocation("mineminenomi", "sky_island_town_piece"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static void registerStructurePiece(IStructurePieceType structurePiece, ResourceLocation res) {
/* 337 */       Registry.func_218322_a(Registry.field_218362_C, res, structurePiece);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModStructures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */