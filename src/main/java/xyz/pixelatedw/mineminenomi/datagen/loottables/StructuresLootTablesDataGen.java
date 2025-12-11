/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.loot.LootTables;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.CampLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.GhostShipLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.TavernLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.bandit.BanditLargeBaseLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.bandit.BanditSmallBaseLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.caravan.EasyCaravanLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.caravan.HardCaravanLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.marine.MarineBattleshipLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.marine.MarineLargeBaseLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.marine.MarineSmallBaseLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.pirate.PirateLargeShipLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.pirate.PirateMediumShipLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.pirate.PirateSmallShipLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.AbandonedMineshaftLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.BuriedTreasureLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.DesertPyramidLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.JungleTempleLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.PillagerOutpostLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.ShipwreckLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.SpawnBonusChestLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.StrongholdLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.UnderwaterRuinLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.WoodlandMansionLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageArmorerLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageButcherLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageFisherLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageFletcherLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageShepherdLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageTanneryLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageTempleLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village.VillageWeaponsmithLootTable;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.LootTablesDataGen;
/*     */ 
/*     */ public class StructuresLootTablesDataGen extends LootTablesDataGen {
/*  51 */   private final Multimap<ResourceLocation, Pair<String, LootTable.Builder>> lootTables = (Multimap<ResourceLocation, Pair<String, LootTable.Builder>>)HashMultimap.create();
/*  52 */   private final Multimap<ResourceLocation, LootTable.Builder> vanillaTables = (Multimap<ResourceLocation, LootTable.Builder>)HashMultimap.create();
/*     */   
/*  54 */   public static final ResourceLocation CARAVAN = new ResourceLocation("mineminenomi", "caravan");
/*     */   
/*     */   public StructuresLootTablesDataGen(DataGenerator dataGenerator) {
/*  57 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_200398_a(DirectoryCache cache) {
/*  63 */     addLootTable((Structure)ModStructures.PIRATE_SMALL_SHIP.get(), (Pair<String, LootTable.Builder>[])PirateSmallShipLootTables.getTables());
/*  64 */     addLootTable((Structure)ModStructures.PIRATE_MEDIUM_SHIP.get(), (Pair<String, LootTable.Builder>[])PirateMediumShipLootTables.getTables());
/*  65 */     addLootTable((Structure)ModStructures.PIRATE_LARGE_SHIP.get(), (Pair<String, LootTable.Builder>[])PirateLargeShipLootTables.getTables());
/*  66 */     addLootTable((Structure)ModStructures.MARINE_BATTLESHIP.get(), (Pair<String, LootTable.Builder>[])MarineBattleshipLootTables.getTables());
/*  67 */     addLootTable((Structure)ModStructures.GHOST_SHIP.get(), (Pair<String, LootTable.Builder>[])GhostShipLootTables.getTables());
/*     */ 
/*     */     
/*  70 */     addLootTable((Structure)ModStructures.MARINE_SMALL_BASE.get(), (Pair<String, LootTable.Builder>[])MarineSmallBaseLootTable.getTables());
/*  71 */     addLootTable((Structure)ModStructures.MARINE_LARGE_BASE.get(), (Pair<String, LootTable.Builder>[])MarineLargeBaseLootTable.getTables());
/*  72 */     addLootTable((Structure)ModStructures.BANDIT_SMALL_BASE.get(), (Pair<String, LootTable.Builder>[])BanditSmallBaseLootTable.getTables());
/*  73 */     addLootTable((Structure)ModStructures.BANDIT_LARGE_BASE.get(), (Pair<String, LootTable.Builder>[])BanditLargeBaseLootTable.getTables());
/*  74 */     addLootTable((Structure)ModStructures.MARINE_CAMP.get(), (Pair<String, LootTable.Builder>[])CampLootTable.getTables());
/*  75 */     addLootTable((Structure)ModStructures.BANDIT_CAMP.get(), (Pair<String, LootTable.Builder>[])CampLootTable.getTables());
/*     */ 
/*     */     
/*  78 */     addLootTable(CARAVAN, (Pair<String, LootTable.Builder>[])EasyCaravanLootTable.getTables());
/*  79 */     addLootTable(CARAVAN, (Pair<String, LootTable.Builder>[])HardCaravanLootTable.getTables());
/*     */ 
/*     */     
/*  82 */     addLootTable((Structure)ModStructures.TAVERN.get(), (Pair<String, LootTable.Builder>[])TavernLootTable.getTables());
/*     */ 
/*     */     
/*  85 */     addVanillaLootTable(LootTables.field_204115_q, UnderwaterRuinLootTable.getPool());
/*  86 */     addVanillaLootTable(LootTables.field_204114_p, UnderwaterRuinLootTable.getPool());
/*  87 */     addVanillaLootTable(LootTables.field_204773_u, ShipwreckLootTable.getTreasurePool());
/*  88 */     addVanillaLootTable(LootTables.field_204772_t, ShipwreckLootTable.getSupplyPool());
/*  89 */     addVanillaLootTable(LootTables.field_186424_f, AbandonedMineshaftLootTable.getPool());
/*  90 */     addVanillaLootTable(LootTables.field_204312_r, BuriedTreasureLootTable.getPool());
/*  91 */     addVanillaLootTable(LootTables.field_186429_k, DesertPyramidLootTable.getPool());
/*  92 */     addVanillaLootTable(LootTables.field_186430_l, JungleTempleLootTable.getPool());
/*  93 */     addVanillaLootTable(LootTables.field_215813_K, PillagerOutpostLootTable.getPool());
/*  94 */     addVanillaLootTable(LootTables.field_191192_o, WoodlandMansionLootTable.getPool());
/*  95 */     addVanillaLootTable(LootTables.field_186420_b, SpawnBonusChestLootTable.getPool());
/*  96 */     addVanillaLootTable(LootTables.field_186428_j, StrongholdLootTable.getPool());
/*  97 */     addVanillaLootTable(LootTables.field_215816_g, VillageArmorerLootTable.getPool());
/*  98 */     addVanillaLootTable(LootTables.field_215820_k, VillageButcherLootTable.getPool());
/*  99 */     addVanillaLootTable(LootTables.field_215822_m, VillageFisherLootTable.getPool());
/* 100 */     addVanillaLootTable(LootTables.field_215821_l, VillageFletcherLootTable.getPool());
/* 101 */     addVanillaLootTable(LootTables.field_215823_n, VillageTanneryLootTable.getPool());
/* 102 */     addVanillaLootTable(LootTables.field_215824_o, VillageTempleLootTable.getPool());
/* 103 */     addVanillaLootTable(LootTables.field_215814_e, VillageWeaponsmithLootTable.getPool());
/* 104 */     addVanillaLootTable(LootTables.field_215819_j, VillageShepherdLootTable.getPool());
/*     */     
/* 106 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/* 107 */     this.lootTables.forEach((key, pair) -> {
/*     */           ResourceLocation res = new ResourceLocation(key.func_110624_b(), "chests/" + key.func_110623_a() + "/" + (String)pair.getLeft());
/*     */           tables.put(res, ((LootTable.Builder)pair.getRight()).func_216038_b());
/*     */         });
/* 111 */     writeTables(cache, tables);
/*     */     
/* 113 */     Map<ResourceLocation, LootTable> vanillaTables = new HashMap<>();
/* 114 */     this.vanillaTables.forEach((key, builder) -> vanillaTables.put(key, builder.func_216038_b()));
/*     */ 
/*     */     
/* 117 */     writeTables(cache, vanillaTables);
/*     */   }
/*     */   
/*     */   protected void addLootTable(Structure<?> structure, Pair<String, LootTable.Builder>[] pairs) {
/* 121 */     ResourceLocation key = ForgeRegistries.STRUCTURE_FEATURES.getKey((IForgeRegistryEntry)structure);
/* 122 */     addLootTable(key, pairs);
/*     */   }
/*     */   
/*     */   protected void addLootTable(ResourceLocation id, Pair<String, LootTable.Builder>[] pairs) {
/* 126 */     for (Pair<String, LootTable.Builder> pair : pairs) {
/* 127 */       this.lootTables.put(id, pair);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void addVanillaLootTable(ResourceLocation key, LootTable.Builder builder) {
/* 132 */     this.vanillaTables.put(key, builder);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_200397_b() {
/* 137 */     return "Structures Loot Pools";
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\StructuresLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */