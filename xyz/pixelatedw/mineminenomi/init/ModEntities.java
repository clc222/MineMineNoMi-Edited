/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.EntitySpawnPlacementRegistry;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemGroup;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.MobSpawnInfo;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.ForgeSpawnEggItem;
/*     */ import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
/*     */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.config.WorldEventsConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BombEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BonChariEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.CannonEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.ChakramEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.DFItemEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.EntityCollectorEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.JangoEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.NetEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.SpearEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.SphereEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.StrikerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.ThrowingWeaponEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.TornadoEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.UnicycleEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.dummies.DummyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.BarkeeperEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.BruteEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.CaptainEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.GruntEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanCivilianEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SniperEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BananawaniEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BigDuckEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BlagoriEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BlugoriEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.BoxingDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.DenDenMushiEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FightingFishEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.FlyingFishEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LegendaryMasterDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.PandaSharkEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaCowEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WanderingDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WhiteWalkieEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.WrestlingDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.HigumaEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.PacifistaEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AlvidaEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBomberEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.ArlongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.ChewEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates.KuroobiEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissMerryChristmasEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissValentineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr0Entity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr1Entity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr3Entity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr4Entity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr5Entity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates.BuchiEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates.KuroEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates.ShamEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.CabajiEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.PearlEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.ArtOfWeatherTrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BlackLegTrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BrawlerTrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DoctorTrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.SniperTrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.SwordsmanTrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.worldgov.CelestialDragonEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModEntities
/*     */ {
/* 121 */   public static final EntityClassification MARINES = EntityClassification.create("marines", "marines", 25, false, true, 128);
/* 122 */   public static final EntityClassification PIRATES = EntityClassification.create("pirates", "pirates", 25, false, true, 128);
/* 123 */   public static final EntityClassification BANDITS = EntityClassification.create("bandits", "bandits", 25, false, true, 128);
/*     */ 
/*     */   
/* 126 */   public static final ArrayList<BiomeDictionary.Type> GENERIC_ONES = Lists.newArrayList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SWAMP });
/* 127 */   public static final ArrayList<BiomeDictionary.Type> PIRATE_BIOMES = Lists.newArrayList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.FOREST });
/* 128 */   public static final ArrayList<BiomeDictionary.Type> MARINE_BIOMES = Lists.newArrayList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP });
/* 129 */   public static final ArrayList<BiomeDictionary.Type> BANDIT_BIOMES = Lists.newArrayList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MESA });
/*     */ 
/*     */ 
/*     */   
/* 133 */   public static final RegistryObject<EntityType<GruntEntity>> MARINE_GRUNT = registerMarineWithSpawnEgg("Marine Grunt", () -> WyRegistry.createEntityType(GruntEntity::createMarineGrunt, MARINES).func_206830_a("mineminenomi:marine_grunt"));
/* 134 */   public static final RegistryObject<EntityType<BruteEntity>> MARINE_BRUTE = registerMarineWithSpawnEgg("Marine Brute", () -> WyRegistry.createEntityType(BruteEntity::createMarineBrute, MARINES).func_220321_a(0.8F, 2.3F).func_206830_a("mineminenomi:marine_brute"));
/* 135 */   public static final RegistryObject<EntityType<SniperEntity>> MARINE_SNIPER = registerMarineWithSpawnEgg("Marine Sniper", () -> WyRegistry.createEntityType(SniperEntity::createMarineSniper, MARINES).func_206830_a("mineminenomi:marine_sniper"));
/* 136 */   public static final RegistryObject<EntityType<CaptainEntity>> MARINE_CAPTAIN = registerMarineWithSpawnEgg("Marine Captain", () -> WyRegistry.createEntityType(CaptainEntity::createMarineCaptain, MARINES).func_206830_a("mineminenomi:marine_captain"));
/* 137 */   public static final RegistryObject<EntityType<MarineTraderEntity>> MARINE_TRADER = registerMarineWithSpawnEgg("Marine Trader", () -> WyRegistry.createEntityType(MarineTraderEntity::new, MARINES).func_206830_a("mineminenomi:marine_trader"));
/* 138 */   public static final RegistryObject<EntityType<PacifistaEntity>> PACIFISTA = registerMarineWithSpawnEgg("Pacifista", () -> WyRegistry.createEntityType(PacifistaEntity::new, MARINES).func_220321_a(1.5F, 3.5F).func_206830_a("mineminenomi:pacifista"));
/* 139 */   public static final RegistryObject<EntityType<NotoriousEntity>> MARINE_VICE_ADMIRAL = WyRegistry.registerEntityType("Marine Vice Admiral", () -> WyRegistry.createEntityType(NotoriousEntity::createMarine, MARINES).func_220321_a(0.6F, 2.0F).func_206830_a("mineminenomi:marine_vice_admiral"));
/* 140 */   public static final RegistryObject<EntityType<MorganEntity>> MORGAN = WyRegistry.registerEntityType("Morgan", () -> WyRegistry.createEntityType(MorganEntity::new, MARINES).func_220321_a(0.8F, 2.7F).func_206830_a("mineminenomi:morgan"));
/*     */ 
/*     */   
/* 143 */   public static final RegistryObject<EntityType<CelestialDragonEntity>> CELESTIAL_DRAGON = registerWorldGovWithSpawnEgg("Celestial Dragon", () -> WyRegistry.createEntityType(CelestialDragonEntity::new).func_206830_a("mineminenomi:celestial_dragon"));
/*     */ 
/*     */   
/* 146 */   public static final RegistryObject<EntityType<GruntEntity>> PIRATE_GRUNT = registerPirateWithSpawnEgg("Pirate Grunt", () -> WyRegistry.createEntityType(GruntEntity::createPirateGrunt, PIRATES).func_206830_a("mineminenomi:pirate_grunt"));
/* 147 */   public static final RegistryObject<EntityType<BruteEntity>> PIRATE_BRUTE = registerPirateWithSpawnEgg("Pirate Brute", () -> WyRegistry.createEntityType(BruteEntity::createPirateBrute, PIRATES).func_220321_a(0.8F, 2.3F).func_206830_a("mineminenomi:pirate_brute"));
/* 148 */   public static final RegistryObject<EntityType<CaptainEntity>> PIRATE_CAPTAIN = registerPirateWithSpawnEgg("Pirate Captain", () -> WyRegistry.createEntityType(CaptainEntity::createPirateCaptain, PIRATES).func_206830_a("mineminenomi:pirate_captain"));
/* 149 */   public static final RegistryObject<EntityType<PirateTraderEntity>> PIRATE_TRADER = registerPirateWithSpawnEgg("Pirate Trader", () -> WyRegistry.createEntityType(PirateTraderEntity::new, PIRATES).func_206830_a("mineminenomi:pirate_trader"));
/* 150 */   public static final RegistryObject<EntityType<PirateBomberEntity>> PIRATE_BOMBER = registerPirateWithSpawnEgg("Pirate Bomber", () -> WyRegistry.createEntityType(PirateBomberEntity::new, PIRATES).func_206830_a("mineminenomi:pirate_bomber"));
/* 151 */   public static final RegistryObject<EntityType<NotoriousEntity>> PIRATE_NOTORIOUS_CAPTAIN = WyRegistry.registerEntityType("Pirate Notorious Captain", () -> WyRegistry.createEntityType(NotoriousEntity::createPirate, MARINES).func_220321_a(0.6F, 2.0F).func_206830_a("mineminenomi:pirate_notorious_captain"));
/*     */ 
/*     */   
/* 154 */   public static final RegistryObject<EntityType<BuggyEntity>> BUGGY = WyRegistry.registerEntityType("Buggy", () -> WyRegistry.createEntityType(BuggyEntity::new).func_206830_a("mineminenomi:buggy"));
/* 155 */   public static final RegistryObject<EntityType<AlvidaEntity>> ALVIDA = WyRegistry.registerEntityType("Alvida", () -> WyRegistry.createEntityType(AlvidaEntity::new).func_206830_a("mineminenomi:alvida"));
/* 156 */   public static final RegistryObject<EntityType<AlvidaEntity>> ALVIDA_SLIM = WyRegistry.registerEntityType("Alvida", "alvida2", () -> WyRegistry.createEntityType(AlvidaEntity::new).func_206830_a("mineminenomi:alvida2"));
/* 157 */   public static final RegistryObject<EntityType<CabajiEntity>> CABAJI = WyRegistry.registerEntityType("Cabaji", () -> WyRegistry.createEntityType(CabajiEntity::new).func_206830_a("mineminenomi:cabaji"));
/*     */ 
/*     */   
/* 160 */   public static final RegistryObject<EntityType<ShamEntity>> SHAM = WyRegistry.registerEntityType("Sham", () -> WyRegistry.createEntityType(ShamEntity::new).func_206830_a("mineminenomi:sham"));
/* 161 */   public static final RegistryObject<EntityType<BuchiEntity>> BUCHI = WyRegistry.registerEntityType("Buchi", () -> WyRegistry.createEntityType(BuchiEntity::new).func_206830_a("mineminenomi:buchi"));
/* 162 */   public static final RegistryObject<EntityType<JangoEntity>> JANGO = WyRegistry.registerEntityType("Jango", () -> WyRegistry.createEntityType(JangoEntity::new).func_206830_a("mineminenomi:jango"));
/* 163 */   public static final RegistryObject<EntityType<KuroEntity>> KURO = WyRegistry.registerEntityType("Kuro", () -> WyRegistry.createEntityType(KuroEntity::new).func_206830_a("mineminenomi:kuro"));
/*     */ 
/*     */   
/* 166 */   public static final RegistryObject<EntityType<GinEntity>> GIN = WyRegistry.registerEntityType("Gin", () -> WyRegistry.createEntityType(GinEntity::new).func_206830_a("mineminenomi:gin"));
/* 167 */   public static final RegistryObject<EntityType<PearlEntity>> PEARL = WyRegistry.registerEntityType("Pearl", () -> WyRegistry.createEntityType(PearlEntity::new).func_220321_a(0.8F, 2.5F).func_206830_a("mineminenomi:pearl"));
/* 168 */   public static final RegistryObject<EntityType<DonKriegEntity>> DON_KRIEG = WyRegistry.registerEntityType("Don Krieg", () -> WyRegistry.createEntityType(DonKriegEntity::new).func_220321_a(0.8F, 2.5F).func_206830_a("mineminenomi:don_krieg"));
/*     */ 
/*     */   
/* 171 */   public static final RegistryObject<EntityType<ChewEntity>> CHEW = WyRegistry.registerEntityType("Chew", () -> WyRegistry.createEntityType(ChewEntity::new).func_220321_a(0.75F, 2.75F).func_206830_a("mineminenomi:chew"));
/* 172 */   public static final RegistryObject<EntityType<KuroobiEntity>> KUROOBI = WyRegistry.registerEntityType("Kuroobi", () -> WyRegistry.createEntityType(KuroobiEntity::new).func_220321_a(0.75F, 2.7F).func_206830_a("mineminenomi:kuroobi"));
/*     */   
/* 174 */   public static final RegistryObject<EntityType<ArlongEntity>> ARLONG = WyRegistry.registerEntityType("Arlong", () -> WyRegistry.createEntityType(ArlongEntity::new).func_220321_a(0.75F, 2.75F).func_206830_a("mineminenomi:arlong"));
/*     */ 
/*     */   
/* 177 */   public static final RegistryObject<EntityType<Mr5Entity>> MR5 = WyRegistry.registerEntityType("Mr 5", () -> WyRegistry.createEntityType(Mr5Entity::new).func_206830_a("mineminenomi:mr5"));
/* 178 */   public static final RegistryObject<EntityType<MissValentineEntity>> MISS_VALENTINE = WyRegistry.registerEntityType("Miss Valentine", () -> WyRegistry.createEntityType(MissValentineEntity::new).func_206830_a("mineminenomi:miss_valentine"));
/* 179 */   public static final RegistryObject<EntityType<Mr4Entity>> MR4 = WyRegistry.registerEntityType("Mr 4", () -> WyRegistry.createEntityType(Mr4Entity::new).func_206830_a("mineminenomi:mr4"));
/* 180 */   public static final RegistryObject<EntityType<MissMerryChristmasEntity>> MISS_MERRY_CHRISTMAS = WyRegistry.registerEntityType("Miss Merry Christmas", () -> WyRegistry.createEntityType(MissMerryChristmasEntity::new).func_206830_a("mineminenomi:miss_merry_christmas"));
/* 181 */   public static final RegistryObject<EntityType<Mr3Entity>> MR3 = WyRegistry.registerEntityType("Mr 3", () -> WyRegistry.createEntityType(Mr3Entity::new).func_206830_a("mineminenomi:mr3"));
/* 182 */   public static final RegistryObject<EntityType<Mr1Entity>> MR1 = WyRegistry.registerEntityType("Mr 1", () -> WyRegistry.createEntityType(Mr1Entity::new).func_220321_a(0.9F, 2.3F).func_206830_a("mineminenomi:mr1"));
/* 183 */   public static final RegistryObject<EntityType<Mr0Entity>> MR0 = WyRegistry.registerEntityType("Mr 0", () -> WyRegistry.createEntityType(Mr0Entity::new).func_220321_a(0.7F, 2.0F).func_206830_a("mineminenomi:mr0"));
/*     */ 
/*     */   
/* 186 */   public static final RegistryObject<EntityType<GruntEntity>> BANDIT_GRUNT = registerBanditWithSpawnEgg("Bandit Grunt", () -> WyRegistry.createEntityType(GruntEntity::createBanditGrunt, BANDITS).func_206830_a("mineminenomi:bandit_grunt"));
/* 187 */   public static final RegistryObject<EntityType<BruteEntity>> BANDIT_BRUTE = registerBanditWithSpawnEgg("Bandit Brute", () -> WyRegistry.createEntityType(BruteEntity::createBanditBrute, BANDITS).func_220321_a(0.8F, 2.3F).func_206830_a("mineminenomi:bandit_brute"));
/* 188 */   public static final RegistryObject<EntityType<SniperEntity>> BANDIT_SNIPER = registerBanditWithSpawnEgg("Bandit Sniper", () -> WyRegistry.createEntityType(SniperEntity::createBanditSniper, BANDITS).func_206830_a("mineminenomi:bandit_sniper"));
/* 189 */   public static final RegistryObject<EntityType<CaptainEntity>> BANDIT_CAPTAIN = registerBanditWithSpawnEgg("Bandit Leader", () -> WyRegistry.createEntityType(CaptainEntity::createBanditCaptain, BANDITS).func_206830_a("mineminenomi:bandit_captain"));
/* 190 */   public static final RegistryObject<EntityType<HigumaEntity>> HIGUMA = WyRegistry.registerEntityType("Higuma", () -> WyRegistry.createEntityType(HigumaEntity::new, BANDITS).func_220321_a(0.8F, 1.9F).func_206830_a("mineminenomi:higuma"));
/*     */ 
/*     */   
/* 193 */   public static final RegistryObject<EntityType<SwordsmanTrainerEntity>> SWORDSMAN_TRAINER = registerFactionlessWithSpawnEgg("Swordsman Trainer", () -> WyRegistry.createEntityType(SwordsmanTrainerEntity::new).func_206830_a("mineminenomi:swordsman_trainer"));
/* 194 */   public static final RegistryObject<EntityType<SniperTrainerEntity>> SNIPER_TRAINER = registerFactionlessWithSpawnEgg("Sniper Trainer", () -> WyRegistry.createEntityType(SniperTrainerEntity::new).func_206830_a("mineminenomi:sniper_trainer"));
/* 195 */   public static final RegistryObject<EntityType<ArtOfWeatherTrainerEntity>> ART_OF_WEATHER_TRAINER = registerFactionlessWithSpawnEgg("Art of Weather Trainer", () -> WyRegistry.createEntityType(ArtOfWeatherTrainerEntity::new).func_206830_a("mineminenomi:art_of_weather_trainer"));
/* 196 */   public static final RegistryObject<EntityType<DoctorTrainerEntity>> DOCTOR_TRAINER = registerFactionlessWithSpawnEgg("Doctor Trainer", () -> WyRegistry.createEntityType(DoctorTrainerEntity::new).func_206830_a("mineminenomi:doctor_trainer"));
/* 197 */   public static final RegistryObject<EntityType<BrawlerTrainerEntity>> BRAWLER_TRAINER = registerFactionlessWithSpawnEgg("Brawler Trainer", () -> WyRegistry.createEntityType(BrawlerTrainerEntity::new).func_206830_a("mineminenomi:brawler_trainer"));
/* 198 */   public static final RegistryObject<EntityType<BlackLegTrainerEntity>> BLACK_LEG_TRAINER = registerFactionlessWithSpawnEgg("Black Leg Trainer", () -> WyRegistry.createEntityType(BlackLegTrainerEntity::new).func_206830_a("mineminenomi:black_leg_trainer"));
/* 199 */   public static final RegistryObject<EntityType<SkypieanCivilianEntity>> SKYPIEAN_CIVILIAN = registerFactionlessWithSpawnEgg("Skypiean Civilian", () -> WyRegistry.createEntityType(SkypieanCivilianEntity::new).func_206830_a("mineminenomi:skypiean_civilian"));
/* 200 */   public static final RegistryObject<EntityType<SkypieanTraderEntity>> SKYPIEAN_TRADER = registerFactionlessWithSpawnEgg("Skypiean Trader", () -> WyRegistry.createEntityType(SkypieanTraderEntity::new).func_206830_a("mineminenomi:skypiean_trader"));
/* 201 */   public static final RegistryObject<EntityType<BarkeeperEntity>> BARKEEPER = registerFactionlessWithSpawnEgg("Barkeeper", () -> WyRegistry.createEntityType(BarkeeperEntity::new).func_206830_a("mineminenomi:barkeeper"));
/*     */ 
/*     */   
/* 204 */   public static final RegistryObject<EntityType<DenDenMushiEntity>> DEN_DEN_MUSHI = registerAnimalWithSpawnEgg("Den Den Mushi", () -> WyRegistry.createEntityType(DenDenMushiEntity::new, EntityClassification.CREATURE).func_220321_a(0.8F, 0.8F).func_206830_a("mineminenomi:den_den_mushi"));
/* 205 */   public static final RegistryObject<EntityType<LapahnEntity>> LAPAHN = registerAnimalWithSpawnEgg("Lapahn", () -> WyRegistry.createEntityType(LapahnEntity::new, EntityClassification.CREATURE).func_220321_a(1.5F, 2.3F).func_206830_a("mineminenomi:lapahn"));
/* 206 */   public static final RegistryObject<EntityType<YagaraBullEntity>> YAGARA_BULL = registerAnimalWithSpawnEgg("Yagara Bull", () -> WyRegistry.createEntityType(YagaraBullEntity::new, EntityClassification.WATER_CREATURE).func_220321_a(1.4F, 1.6F).func_233606_a_(10).func_206830_a("mineminenomi:yagara_bull"));
/* 207 */   public static final RegistryObject<EntityType<HumandrillEntity>> HUMANDRILL = registerAnimalWithSpawnEgg("Humandrill", () -> WyRegistry.createEntityType(HumandrillEntity::new, EntityClassification.CREATURE).func_220321_a(1.0F, 2.5F).func_206830_a("mineminenomi:humandrill"));
/* 208 */   public static final RegistryObject<EntityType<FightingFishEntity>> FIGHTING_FISH = registerAnimalWithSpawnEgg("Fighting Fish", () -> WyRegistry.createEntityType(FightingFishEntity::new, EntityClassification.WATER_CREATURE).func_220321_a(3.0F, 3.0F).func_206830_a("mineminenomi:fighting_fish"));
/* 209 */   public static final RegistryObject<EntityType<BananawaniEntity>> BANANAWANI = registerAnimalWithSpawnEgg("Bananawani", () -> WyRegistry.createEntityType(BananawaniEntity::new, EntityClassification.CREATURE).func_220321_a(2.0F, 1.75F).func_206830_a("mineminenomi:bananwani"));
/* 210 */   public static final RegistryObject<EntityType<BigDuckEntity>> BIG_DUCK = registerAnimalWithSpawnEgg("Super Spot-Billed Duck", () -> WyRegistry.createEntityType(BigDuckEntity::new, EntityClassification.CREATURE).func_220321_a(1.25F, 1.75F).func_206830_a("mineminenomi:big_duck"));
/* 211 */   public static final RegistryObject<EntityType<SeaCowEntity>> SEA_COW = registerAnimalWithSpawnEgg("Sea Cow", () -> WyRegistry.createEntityType(SeaCowEntity::new, EntityClassification.WATER_CREATURE).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:sea_cow"));
/* 212 */   public static final RegistryObject<EntityType<WhiteWalkieEntity>> WHITE_WALKIE = registerAnimalWithSpawnEgg("White Walkie", () -> WyRegistry.createEntityType(WhiteWalkieEntity::new, EntityClassification.CREATURE).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:white_walkie"));
/* 213 */   public static final RegistryObject<EntityType<PandaSharkEntity>> PANDA_SHARK = registerAnimalWithSpawnEgg("Panda Shark", () -> WyRegistry.createEntityType(PandaSharkEntity::new, EntityClassification.WATER_CREATURE).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:panda_shark"));
/* 214 */   public static final RegistryObject<EntityType<FlyingFishEntity>> FLYING_FISH = registerAnimalWithSpawnEgg("Flying Fish", () -> WyRegistry.createEntityType(FlyingFishEntity::new, EntityClassification.WATER_CREATURE).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:flying_fish"));
/*     */ 
/*     */   
/* 217 */   public static final RegistryObject<EntityType<BlugoriEntity>> BLUGORI = registerAnimalWithSpawnEgg("Blugori", () -> WyRegistry.createEntityType(BlugoriEntity::new, EntityClassification.CREATURE).func_220321_a(1.0F, 1.5F).func_206830_a("mineminenomi:blugori"));
/* 218 */   public static final RegistryObject<EntityType<BlagoriEntity>> BLAGORI = registerAnimalWithSpawnEgg("Blagori", () -> WyRegistry.createEntityType(BlagoriEntity::new, EntityClassification.CREATURE).func_220321_a(1.0F, 1.5F).func_206830_a("mineminenomi:blagori"));
/*     */ 
/*     */   
/* 221 */   public static final RegistryObject<EntityType<KungFuDugongEntity>> KUNG_FU_DUGONG = registerAnimalWithSpawnEgg("Kung Fu Dugong", () -> WyRegistry.createEntityType(KungFuDugongEntity::new, EntityClassification.CREATURE).func_220321_a(0.6F, 1.2F).func_206830_a("mineminenomi:kung_fu_dugong"));
/*     */   
/* 223 */   public static final RegistryObject<EntityType<WrestlingDugongEntity>> WRESTLING_DUGONG = registerAnimalWithSpawnEgg("Wrestling Dugong", () -> WyRegistry.createEntityType(WrestlingDugongEntity::new, EntityClassification.CREATURE).func_220321_a(0.6F, 1.2F).func_206830_a("mineminenomi:wrestling_dugong"));
/* 224 */   public static final RegistryObject<EntityType<BoxingDugongEntity>> BOXING_DUGONG = registerAnimalWithSpawnEgg("Boxing Dugong", () -> WyRegistry.createEntityType(BoxingDugongEntity::new, EntityClassification.CREATURE).func_220321_a(0.6F, 1.2F).func_206830_a("mineminenomi:boxing_dugong"));
/* 225 */   public static final RegistryObject<EntityType<LegendaryMasterDugongEntity>> LEGENDARY_MASTER_DUGONG = registerAnimalWithSpawnEgg("Legendary Master Dugong", () -> WyRegistry.createEntityType(LegendaryMasterDugongEntity::new, EntityClassification.CREATURE).func_220321_a(0.6F, 1.2F).func_206830_a("mineminenomi:legendary_master_dugong"));
/* 226 */   public static final RegistryObject<EntityType<WanderingDugongEntity>> WANDERING_DUGONG = registerAnimalWithSpawnEgg("Wandering Dugong", () -> WyRegistry.createEntityType(WanderingDugongEntity::new, EntityClassification.CREATURE).func_220321_a(0.6F, 1.2F).func_206830_a("mineminenomi:wandering_dugong"));
/*     */ 
/*     */   
/* 229 */   public static final RegistryObject<EntityType<WantedPosterPackageEntity>> WANTED_POSTER_PACKAGE = WyRegistry.registerEntityType("Wanted Poster Package", () -> WyRegistry.createEntityType(WantedPosterPackageEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:wanted_poster_package"));
/* 230 */   public static final RegistryObject<EntityType<VivreCardEntity>> VIVRE_CARD = WyRegistry.registerEntityType("Vivre Card", () -> WyRegistry.createEntityType(VivreCardEntity::new).func_220321_a(0.4F, 0.4F).func_206830_a("mineminenomi:vivre_card"));
/* 231 */   public static final RegistryObject<EntityType<PhysicalBodyEntity>> PHYSICAL_BODY = WyRegistry.registerEntityType("Physical Body", () -> WyRegistry.createEntityType(PhysicalBodyEntity::new).func_206830_a("mineminenomi:physical_body"));
/* 232 */   public static final RegistryObject<EntityType<SniperTargetEntity>> SNIPER_TARGET = WyRegistry.registerEntityType("Sniper Target", () -> WyRegistry.createEntityType(SniperTargetEntity::new).func_206830_a("mineminenomi:sniper_target"));
/* 233 */   public static final RegistryObject<EntityType<BombEntity>> BOMB = WyRegistry.registerEntityType("Bomb", () -> WyRegistry.createEntityType(BombEntity::new).func_220321_a(0.8F, 0.8F).func_206830_a("mineminenomi:bomb"));
/* 234 */   public static final RegistryObject<EntityType<DFItemEntity>> DEVIL_FRUIT_ITEM = WyRegistry.registerEntityType("Devil Fruit", () -> WyRegistry.createEntityType(DFItemEntity::new).func_220321_a(0.25F, 0.25F).func_206830_a("mineminenomi:df_item"));
/* 235 */   public static final RegistryObject<EntityType<SpikeEntity>> SPIKE = WyRegistry.registerEntityType("Spike", () -> WyRegistry.createEntityType(SpikeEntity::new).func_220321_a(0.2F, 0.2F).func_206830_a("mineminenomi:spike"));
/* 236 */   public static final RegistryObject<EntityType<BottomHalfBodyEntity>> BOTTOM_HALF_BODY = WyRegistry.registerEntityType("Bottom Half Body", () -> WyRegistry.createEntityType(BottomHalfBodyEntity::new).func_220321_a(0.6F, 0.9F).func_206830_a("mineminenomi:bottom_half_body"));
/* 237 */   public static final RegistryObject<EntityType<EntityCollectorEntity>> ENTITY_COLLECTOR = WyRegistry.registerEntityType("Entity Collector", () -> WyRegistry.createEntityType(EntityCollectorEntity::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:entity_collector"));
/* 238 */   public static final RegistryObject<EntityType<TornadoEntity>> TORNADO = WyRegistry.registerEntityType("Tornado", () -> WyRegistry.createEntityType(TornadoEntity::new).func_220321_a(1.5F, 3.0F).func_206830_a("mineminenomi:tornado"));
/* 239 */   public static final RegistryObject<EntityType<NetEntity>> ROPE_NET = WyRegistry.registerEntityType("Rope Net", () -> WyRegistry.createEntityType(NetEntity::new).func_220321_a(1.2F, 0.5F).func_206830_a("mineminenomi:rope_net"));
/* 240 */   public static final RegistryObject<EntityType<NetEntity>> KAIROSEKI_NET = WyRegistry.registerEntityType("Kairoseki Net", () -> WyRegistry.createEntityType(NetEntity::new).func_220321_a(1.2F, 0.5F).func_206830_a("mineminenomi:kairoseki_net"));
/* 241 */   public static final RegistryObject<EntityType<SphereEntity>> SPHERE = WyRegistry.registerEntityType("Sphere", () -> WyRegistry.createEntityType(SphereEntity::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:sphere"));
/*     */ 
/*     */   
/* 244 */   public static final RegistryObject<EntityType<BonChariEntity>> BON_CHARI = WyRegistry.registerEntityType("Bon Chari", () -> WyRegistry.createEntityType(BonChariEntity::new).func_220321_a(1.5F, 1.5F).func_233606_a_(10).func_206830_a("mineminenomi:bon_chari"));
/* 245 */   public static final RegistryObject<EntityType<CannonEntity>> CANNON = WyRegistry.registerEntityType("Cannon", () -> WyRegistry.createEntityType(CannonEntity::new).func_220321_a(1.25F, 1.25F).func_233606_a_(10).func_206830_a("mineminenomi:cannot"));
/* 246 */   public static final RegistryObject<EntityType<StrikerEntity>> STRIKER = WyRegistry.registerEntityType("Striker", () -> WyRegistry.createEntityType(StrikerEntity::new).func_220321_a(1.75F, 0.5625F).func_233606_a_(10).func_206830_a("mineminenomi:striker"));
/* 247 */   public static final RegistryObject<EntityType<UnicycleEntity>> UNICYCLE = WyRegistry.registerEntityType("Unicycle", () -> WyRegistry.createEntityType(UnicycleEntity::new).func_220321_a(0.75F, 0.75F).func_233606_a_(10).func_206830_a("mineminenomi:unicycle"));
/*     */ 
/*     */   
/* 250 */   public static final RegistryObject<EntityType<ThrowingWeaponEntity>> THROWING_WEAPON = WyRegistry.registerEntityType("Throwing Knife", () -> WyRegistry.createEntityType(ThrowingWeaponEntity::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:throwing_knife"));
/* 251 */   public static final RegistryObject<EntityType<SpearEntity>> THROWING_SPEAR = WyRegistry.registerEntityType("Throwing Spear", () -> WyRegistry.createEntityType(SpearEntity::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:throwing_spear"));
/* 252 */   public static final RegistryObject<EntityType<ChakramEntity>> CHAKRAM = WyRegistry.registerEntityType("Chakram", () -> WyRegistry.createEntityType(ChakramEntity::new).func_220321_a(0.75F, 0.5F).func_206830_a("mineminenomi:chakram"));
/*     */ 
/*     */   
/* 255 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_NEW_PHOENIX = WyRegistry.registerEntityType("Dummy: New Phoenix", () -> WyRegistry.createEntityType(DummyEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:dummy_new_phoenix"));
/*     */   
/* 257 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_CABAJI = WyRegistry.registerEntityType("Dummy Cabaji", () -> WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.dummies.CabajiDummyEntity::new).func_206830_a("mineminenomi:dummy_cabaji"));
/*     */   
/* 259 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_JANGO = WyRegistry.registerEntityType("Dummy: Jango", () -> WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.dummies.JangoDummyEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:dummy_jango"));
/* 260 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_ALVIDA_SLIM = WyRegistry.registerEntityType("Dummy: Alvida Slim", () -> WyRegistry.createEntityType(DummyEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:dummy_alvida_slim"));
/* 261 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_NOTORIOUS = WyRegistry.registerEntityType("Dummy: Notorious", () -> WyRegistry.createEntityType(DummyEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:dummy_notorious"));
/* 262 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_BARKEEPER = WyRegistry.registerEntityType("Dummy: Barkeeper", () -> WyRegistry.createEntityType(DummyEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:dummy_barkeeper"));
/* 263 */   public static final RegistryObject<EntityType<DummyEntity>> DUMMY_FLYING_FISH = WyRegistry.registerEntityType("Dummy: Flying Fish", () -> WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.dummies.FlyingFishDummyEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:dummy_flying_fish"));
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerMarineWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 267 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 268 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, WyHelper.hexToRGB("#024a81").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB(), (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 269 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerWorldGovWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 274 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 275 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, 12434877, 3092271, (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 276 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerPirateWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 281 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 282 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, WyHelper.hexToRGB("#c11c1c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB(), (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 283 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerBanditWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 288 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 289 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, WyHelper.hexToRGB("#785355").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB(), (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 290 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerFactionlessWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 295 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 296 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, WyHelper.hexToRGB("#fbbf4c").getRGB(), WyHelper.hexToRGB("#F7F7F7").getRGB(), (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 297 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerAnimalWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 302 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 303 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, WyHelper.hexToRGB("#a7ca34").getRGB(), WyHelper.hexToRGB("#a2f7c8").getRGB(), (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 304 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends net.minecraft.entity.Entity> RegistryObject<EntityType<T>> registerDummyWithSpawnEgg(String name, Supplier<EntityType<T>> supp) {
/* 309 */     RegistryObject<EntityType<T>> reg = WyRegistry.registerEntityType(name, supp);
/* 310 */     WyRegistry.registerSpawnEggItem(name, () -> new ForgeSpawnEggItem((Supplier)reg, WyHelper.hexToRGB("#FF00DD").getRGB(), WyHelper.hexToRGB("#EEFF00").getRGB(), (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f)));
/* 311 */     return reg;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
/* 317 */     event.put((EntityType)MARINE_GRUNT.get(), GruntEntity.createAttributes().func_233813_a_());
/* 318 */     event.put((EntityType)MARINE_BRUTE.get(), BruteEntity.createAttributes().func_233813_a_());
/* 319 */     event.put((EntityType)MARINE_CAPTAIN.get(), CaptainEntity.createAttributes().func_233813_a_());
/* 320 */     event.put((EntityType)MARINE_SNIPER.get(), SniperEntity.createAttributes().func_233813_a_());
/* 321 */     event.put((EntityType)MARINE_TRADER.get(), MarineTraderEntity.createAttributes().func_233813_a_());
/* 322 */     event.put((EntityType)PACIFISTA.get(), PacifistaEntity.createAttributes().func_233813_a_());
/* 323 */     event.put((EntityType)MARINE_VICE_ADMIRAL.get(), NotoriousEntity.createAttributes().func_233813_a_());
/* 324 */     event.put((EntityType)MORGAN.get(), MorganEntity.createAttributes().func_233813_a_());
/*     */     
/* 326 */     event.put((EntityType)CELESTIAL_DRAGON.get(), CelestialDragonEntity.createAttributes().func_233813_a_());
/*     */     
/* 328 */     event.put((EntityType)PIRATE_GRUNT.get(), GruntEntity.createAttributes().func_233813_a_());
/* 329 */     event.put((EntityType)PIRATE_BRUTE.get(), BruteEntity.createAttributes().func_233813_a_());
/* 330 */     event.put((EntityType)PIRATE_CAPTAIN.get(), CaptainEntity.createAttributes().func_233813_a_());
/* 331 */     event.put((EntityType)PIRATE_TRADER.get(), PirateTraderEntity.createAttributes().func_233813_a_());
/* 332 */     event.put((EntityType)PIRATE_BOMBER.get(), PirateBomberEntity.createAttributes().func_233813_a_());
/* 333 */     event.put((EntityType)PIRATE_NOTORIOUS_CAPTAIN.get(), NotoriousEntity.createAttributes().func_233813_a_());
/*     */     
/* 335 */     event.put((EntityType)BUGGY.get(), BuggyEntity.createAttributes().func_233813_a_());
/* 336 */     event.put((EntityType)ALVIDA.get(), AlvidaEntity.createAttributes().func_233813_a_());
/* 337 */     event.put((EntityType)ALVIDA_SLIM.get(), AlvidaEntity.createAttributes().func_233813_a_());
/* 338 */     event.put((EntityType)CABAJI.get(), CabajiEntity.createAttributes().func_233813_a_());
/*     */     
/* 340 */     event.put((EntityType)SHAM.get(), ShamEntity.createAttributes().func_233813_a_());
/* 341 */     event.put((EntityType)BUCHI.get(), BuchiEntity.createAttributes().func_233813_a_());
/* 342 */     event.put((EntityType)JANGO.get(), JangoEntity.createAttributes().func_233813_a_());
/* 343 */     event.put((EntityType)KURO.get(), KuroEntity.createAttributes().func_233813_a_());
/*     */     
/* 345 */     event.put((EntityType)GIN.get(), GinEntity.createAttributes().func_233813_a_());
/* 346 */     event.put((EntityType)PEARL.get(), PearlEntity.createAttributes().func_233813_a_());
/* 347 */     event.put((EntityType)DON_KRIEG.get(), DonKriegEntity.createAttributes().func_233813_a_());
/*     */     
/* 349 */     event.put((EntityType)CHEW.get(), ChewEntity.createAttributes().func_233813_a_());
/* 350 */     event.put((EntityType)KUROOBI.get(), KuroobiEntity.createAttributes().func_233813_a_());
/* 351 */     event.put((EntityType)ARLONG.get(), ArlongEntity.createAttributes().func_233813_a_());
/*     */     
/* 353 */     event.put((EntityType)MR5.get(), Mr5Entity.createAttributes().func_233813_a_());
/* 354 */     event.put((EntityType)MISS_VALENTINE.get(), MissValentineEntity.createAttributes().func_233813_a_());
/* 355 */     event.put((EntityType)MR4.get(), Mr4Entity.createAttributes().func_233813_a_());
/* 356 */     event.put((EntityType)MISS_MERRY_CHRISTMAS.get(), MissMerryChristmasEntity.createAttributes().func_233813_a_());
/* 357 */     event.put((EntityType)MR3.get(), Mr3Entity.createAttributes().func_233813_a_());
/* 358 */     event.put((EntityType)MR1.get(), Mr1Entity.createAttributes().func_233813_a_());
/* 359 */     event.put((EntityType)MR0.get(), Mr0Entity.createAttributes().func_233813_a_());
/*     */     
/* 361 */     event.put((EntityType)BANDIT_GRUNT.get(), GruntEntity.createAttributes().func_233813_a_());
/* 362 */     event.put((EntityType)BANDIT_BRUTE.get(), BruteEntity.createAttributes().func_233813_a_());
/* 363 */     event.put((EntityType)BANDIT_SNIPER.get(), SniperEntity.createAttributes().func_233813_a_());
/* 364 */     event.put((EntityType)BANDIT_CAPTAIN.get(), CaptainEntity.createAttributes().func_233813_a_());
/* 365 */     event.put((EntityType)HIGUMA.get(), HigumaEntity.createAttributes().func_233813_a_());
/*     */     
/* 367 */     event.put((EntityType)SWORDSMAN_TRAINER.get(), SwordsmanTrainerEntity.createAttributes().func_233813_a_());
/* 368 */     event.put((EntityType)SNIPER_TRAINER.get(), SniperTrainerEntity.createAttributes().func_233813_a_());
/* 369 */     event.put((EntityType)DOCTOR_TRAINER.get(), DoctorTrainerEntity.createAttributes().func_233813_a_());
/* 370 */     event.put((EntityType)ART_OF_WEATHER_TRAINER.get(), ArtOfWeatherTrainerEntity.createAttributes().func_233813_a_());
/* 371 */     event.put((EntityType)BRAWLER_TRAINER.get(), BrawlerTrainerEntity.createAttributes().func_233813_a_());
/* 372 */     event.put((EntityType)BLACK_LEG_TRAINER.get(), BlackLegTrainerEntity.createAttributes().func_233813_a_());
/* 373 */     event.put((EntityType)SKYPIEAN_CIVILIAN.get(), SkypieanCivilianEntity.createAttributes().func_233813_a_());
/* 374 */     event.put((EntityType)SKYPIEAN_TRADER.get(), SkypieanTraderEntity.createAttributes().func_233813_a_());
/* 375 */     event.put((EntityType)BARKEEPER.get(), BarkeeperEntity.createAttributes().func_233813_a_());
/*     */     
/* 377 */     event.put((EntityType)DEN_DEN_MUSHI.get(), DenDenMushiEntity.createAttributes().func_233813_a_());
/* 378 */     event.put((EntityType)LAPAHN.get(), LapahnEntity.createAttributes().func_233813_a_());
/* 379 */     event.put((EntityType)YAGARA_BULL.get(), YagaraBullEntity.createAttributes().func_233813_a_());
/* 380 */     event.put((EntityType)HUMANDRILL.get(), HumandrillEntity.createAttributes().func_233813_a_());
/* 381 */     event.put((EntityType)FIGHTING_FISH.get(), FightingFishEntity.createAttributes().func_233813_a_());
/* 382 */     event.put((EntityType)BANANAWANI.get(), BananawaniEntity.createAttributes().func_233813_a_());
/* 383 */     event.put((EntityType)BIG_DUCK.get(), BigDuckEntity.createAttributes().func_233813_a_());
/* 384 */     event.put((EntityType)SEA_COW.get(), SeaCowEntity.createAttributes().func_233813_a_());
/* 385 */     event.put((EntityType)WHITE_WALKIE.get(), WhiteWalkieEntity.createAttributes().func_233813_a_());
/* 386 */     event.put((EntityType)PANDA_SHARK.get(), PandaSharkEntity.createAttributes().func_233813_a_());
/* 387 */     event.put((EntityType)FLYING_FISH.get(), FlyingFishEntity.createAttributes().func_233813_a_());
/*     */     
/* 389 */     event.put((EntityType)BLUGORI.get(), BlugoriEntity.createAttributes().func_233813_a_());
/* 390 */     event.put((EntityType)BLAGORI.get(), BlagoriEntity.createAttributes().func_233813_a_());
/*     */     
/* 392 */     event.put((EntityType)KUNG_FU_DUGONG.get(), KungFuDugongEntity.createAttributes().func_233813_a_());
/* 393 */     event.put((EntityType)WRESTLING_DUGONG.get(), WrestlingDugongEntity.createAttributes().func_233813_a_());
/* 394 */     event.put((EntityType)BOXING_DUGONG.get(), BoxingDugongEntity.createAttributes().func_233813_a_());
/* 395 */     event.put((EntityType)LEGENDARY_MASTER_DUGONG.get(), LegendaryMasterDugongEntity.createAttributes().func_233813_a_());
/* 396 */     event.put((EntityType)WANDERING_DUGONG.get(), WanderingDugongEntity.createAttributes().func_233813_a_());
/*     */     
/* 398 */     event.put((EntityType)WANTED_POSTER_PACKAGE.get(), WantedPosterPackageEntity.func_233666_p_().func_233813_a_());
/* 399 */     event.put((EntityType)PHYSICAL_BODY.get(), PhysicalBodyEntity.createAttributes().func_233813_a_());
/* 400 */     event.put((EntityType)SNIPER_TARGET.get(), SniperTargetEntity.createAttributes().func_233813_a_());
/* 401 */     event.put((EntityType)BOTTOM_HALF_BODY.get(), BottomHalfBodyEntity.createAttributes().func_233813_a_());
/*     */     
/* 403 */     event.put((EntityType)DUMMY_NEW_PHOENIX.get(), OPEntity.createAttributes().func_233813_a_());
/* 404 */     event.put((EntityType)DUMMY_CABAJI.get(), OPEntity.createAttributes().func_233813_a_());
/* 405 */     event.put((EntityType)DUMMY_JANGO.get(), OPEntity.createAttributes().func_233813_a_());
/* 406 */     event.put((EntityType)DUMMY_ALVIDA_SLIM.get(), OPEntity.createAttributes().func_233813_a_());
/* 407 */     event.put((EntityType)DUMMY_NOTORIOUS.get(), OPEntity.createAttributes().func_233813_a_());
/* 408 */     event.put((EntityType)DUMMY_BARKEEPER.get(), OPEntity.createAttributes().func_233813_a_());
/* 409 */     event.put((EntityType)DUMMY_FLYING_FISH.get(), OPEntity.createAttributes().func_233813_a_());
/*     */   }
/*     */   
/*     */   public static void setupSpawnRules() {
/* 413 */     if (((Boolean)WorldEventsConfig.SPAWN_WORLD_HUMANOIDS.get()).booleanValue()) {
/*     */       
/* 415 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)MARINE_GRUNT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GruntEntity::checkSpawnRules);
/* 416 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)MARINE_BRUTE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::checkSpawnRules);
/* 417 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)MARINE_SNIPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SniperEntity::checkSpawnRules);
/* 418 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)MARINE_CAPTAIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CaptainEntity::checkSpawnRules);
/* 419 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)MARINE_TRADER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OPEntity::checkSpawnRules);
/* 420 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PACIFISTA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PacifistaEntity::checkSpawnRules);
/* 421 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)MARINE_VICE_ADMIRAL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OPEntity::checkSpawnRules);
/*     */ 
/*     */       
/* 424 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PIRATE_GRUNT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GruntEntity::checkSpawnRules);
/* 425 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PIRATE_BRUTE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::checkSpawnRules);
/* 426 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PIRATE_CAPTAIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CaptainEntity::checkSpawnRules);
/* 427 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PIRATE_TRADER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OPEntity::checkSpawnRules);
/* 428 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PIRATE_BOMBER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::checkSpawnRules);
/* 429 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PIRATE_NOTORIOUS_CAPTAIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OPEntity::checkSpawnRules);
/*     */ 
/*     */       
/* 432 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BANDIT_GRUNT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GruntEntity::checkSpawnRules);
/* 433 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BANDIT_SNIPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SniperEntity::checkSpawnRules);
/* 434 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BANDIT_BRUTE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::checkSpawnRules);
/*     */     } 
/*     */     
/* 437 */     if (((Boolean)WorldEventsConfig.SPAWN_WORLD_ANIMALS.get()).booleanValue()) {
/*     */       
/* 439 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)DEN_DEN_MUSHI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::func_223316_b);
/* 440 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)LAPAHN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 441 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)YAGARA_BULL.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, YagaraBullEntity::checkSpawnRules);
/* 442 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)HUMANDRILL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 443 */       EntitySpawnPlacementRegistry.PlacementType ffPlacement = WyHelper.isAprilFirst() ? EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS : EntitySpawnPlacementRegistry.PlacementType.IN_WATER;
/* 444 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)FIGHTING_FISH.get(), ffPlacement, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FightingFishEntity::checkSpawnRules);
/* 445 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BANANAWANI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 446 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BIG_DUCK.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 447 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)WHITE_WALKIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 448 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)PANDA_SHARK.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PandaSharkEntity::checkSpawnRules);
/* 449 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)FLYING_FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingFishEntity::checkSpawnRules);
/*     */ 
/*     */       
/* 452 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BLUGORI.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 453 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BLAGORI.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/*     */ 
/*     */       
/* 456 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)KUNG_FU_DUGONG.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 457 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)WRESTLING_DUGONG.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 458 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)BOXING_DUGONG.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 459 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)LEGENDARY_MASTER_DUGONG.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
/* 460 */       EntitySpawnPlacementRegistry.func_209343_a((EntityType)WANDERING_DUGONG.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WanderingDugongEntity::checkSpawnRules);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setupCategorySpawns(BiomeLoadingEvent event) {
/* 465 */     if (event.getName() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 469 */     RegistryKey<Biome> key = RegistryKey.func_240903_a_(Registry.field_239720_u_, event.getName());
/* 470 */     Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
/* 471 */     Biome biome = (Biome)ForgeRegistries.BIOMES.getValue(key.func_240901_a_());
/*     */     
/* 473 */     if (((Boolean)WorldEventsConfig.SPAWN_WORLD_HUMANOIDS.get()).booleanValue()) {
/* 474 */       if (MARINE_BIOMES.stream().anyMatch(type -> types.contains(type))) {
/* 475 */         event.getSpawns().func_242575_a(MARINES, new MobSpawnInfo.Spawners((EntityType)MARINE_GRUNT.get(), 940, 2, 4)).func_242577_b();
/* 476 */         event.getSpawns().func_242575_a(MARINES, new MobSpawnInfo.Spawners((EntityType)MARINE_BRUTE.get(), 50, 1, 2)).func_242577_b();
/* 477 */         event.getSpawns().func_242575_a(MARINES, new MobSpawnInfo.Spawners((EntityType)MARINE_SNIPER.get(), 10, 1, 1)).func_242577_b();
/* 478 */         event.getSpawns().func_242575_a(MARINES, new MobSpawnInfo.Spawners((EntityType)PACIFISTA.get(), 1, 1, 1)).func_242577_b();
/*     */       } 
/*     */       
/* 481 */       if (PIRATE_BIOMES.stream().anyMatch(type -> types.contains(type))) {
/* 482 */         event.getSpawns().func_242575_a(PIRATES, new MobSpawnInfo.Spawners((EntityType)PIRATE_GRUNT.get(), 96, 2, 4)).func_242577_b();
/* 483 */         event.getSpawns().func_242575_a(PIRATES, new MobSpawnInfo.Spawners((EntityType)PIRATE_BRUTE.get(), 2, 1, 2)).func_242577_b();
/* 484 */         event.getSpawns().func_242575_a(PIRATES, new MobSpawnInfo.Spawners((EntityType)PIRATE_BOMBER.get(), 2, 1, 1)).func_242577_b();
/*     */       } 
/*     */       
/* 487 */       if (BANDIT_BIOMES.stream().anyMatch(type -> types.contains(type))) {
/* 488 */         event.getSpawns().func_242575_a(BANDITS, new MobSpawnInfo.Spawners((EntityType)BANDIT_GRUNT.get(), 94, 1, 4)).func_242577_b();
/* 489 */         event.getSpawns().func_242575_a(BANDITS, new MobSpawnInfo.Spawners((EntityType)BANDIT_BRUTE.get(), 5, 1, 2)).func_242577_b();
/* 490 */         event.getSpawns().func_242575_a(BANDITS, new MobSpawnInfo.Spawners((EntityType)BANDIT_SNIPER.get(), 1, 1, 1)).func_242577_b();
/*     */       } 
/*     */     } 
/*     */     
/* 494 */     if (((Boolean)WorldEventsConfig.SPAWN_WORLD_ANIMALS.get()).booleanValue()) {
/* 495 */       if (biome == ModBiomes.RAIGO.get()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 500 */       if (types.contains(BiomeDictionary.Type.PLAINS) || types.contains(BiomeDictionary.Type.HILLS) || types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.SWAMP)) {
/* 501 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)DEN_DEN_MUSHI.get(), 8, 1, 4)).func_242577_b();
/* 502 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)BIG_DUCK.get(), 5, 1, 3)).func_242577_b();
/*     */       } 
/*     */       
/* 505 */       if (types.contains(BiomeDictionary.Type.COLD) || types.contains(BiomeDictionary.Type.SNOWY)) {
/* 506 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)LAPAHN.get(), 8, 1, 3)).func_242577_b();
/* 507 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)WHITE_WALKIE.get(), 5, 1, 1)).func_242577_b();
/*     */       } 
/*     */       
/* 510 */       if (types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.OCEAN)) {
/* 511 */         event.getSpawns().func_242575_a(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners((EntityType)YAGARA_BULL.get(), 8, 2, 3)).func_242577_b();
/* 512 */         event.getSpawns().func_242575_a(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners((EntityType)SEA_COW.get(), 1, 1, 1)).func_242577_b();
/*     */       } 
/*     */       
/* 515 */       if (types.contains(BiomeDictionary.Type.OCEAN) && !types.contains(BiomeDictionary.Type.COLD)) {
/* 516 */         event.getSpawns().func_242575_a(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners((EntityType)PANDA_SHARK.get(), 4, 1, 2)).func_242577_b();
/* 517 */         int ffWeight = WyHelper.isAprilFirst() ? 10 : 1;
/* 518 */         event.getSpawns().func_242575_a(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners((EntityType)FIGHTING_FISH.get(), ffWeight, 1, 1)).func_242577_b();
/*     */       } 
/*     */       
/* 521 */       if (types.contains(BiomeDictionary.Type.SWAMP) || biome == ModBiomes.SABAODY.get()) {
/* 522 */         event.getSpawns().func_242575_a(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners((EntityType)FLYING_FISH.get(), 4, 1, 2)).func_242577_b();
/*     */       }
/*     */       
/* 525 */       if ((types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.SANDY)) && !types.contains(BiomeDictionary.Type.PLATEAU)) {
/* 526 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)BANANAWANI.get(), 7, 1, 2)).func_242577_b();
/*     */       }
/*     */       
/* 529 */       if (types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.JUNGLE)) {
/* 530 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)HUMANDRILL.get(), 8, 1, 3)).func_242577_b();
/*     */       }
/*     */ 
/*     */       
/* 534 */       if (types.contains(BiomeDictionary.Type.COLD) && types.contains(BiomeDictionary.Type.FOREST)) {
/* 535 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)BLUGORI.get(), 8, 1, 3)).func_242577_b();
/* 536 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)BLAGORI.get(), 3, 1, 1)).func_242577_b();
/*     */       } 
/*     */ 
/*     */       
/* 540 */       if (types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.SANDY)) {
/* 541 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)KUNG_FU_DUGONG.get(), 12, 3, 5)).func_242577_b();
/* 542 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)BOXING_DUGONG.get(), 7, 1, 3)).func_242577_b();
/* 543 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)WRESTLING_DUGONG.get(), 7, 1, 3)).func_242577_b();
/* 544 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)LEGENDARY_MASTER_DUGONG.get(), 2, 1, 1)).func_242577_b();
/*     */       } 
/* 546 */       if (types.contains(BiomeDictionary.Type.BEACH) || types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.SWAMP) || types
/* 547 */         .contains(BiomeDictionary.Type.PLAINS)) {
/* 548 */         event.getSpawns().func_242575_a(EntityClassification.CREATURE, new MobSpawnInfo.Spawners((EntityType)WANDERING_DUGONG.get(), 2, 1, 1)).func_242577_b();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 554 */     WyRegistry.ENTITY_TYPES.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */