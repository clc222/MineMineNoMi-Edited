/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.loot.IRandomRange;
/*     */ import net.minecraft.loot.LootEntry;
/*     */ import net.minecraft.loot.LootPool;
/*     */ import net.minecraft.loot.LootTables;
/*     */ import net.minecraft.loot.RandomValueRange;
/*     */ import net.minecraft.loot.StandaloneLootEntry;
/*     */ import net.minecraft.loot.TableLootEntry;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.event.LootTableLoadEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ModLootTables {
/*  17 */   public static final ResourceLocation SMALL_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_small_ship/supplies");
/*     */   
/*  19 */   public static final ResourceLocation MEDIUM_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_medium_ship/supplies");
/*  20 */   public static final ResourceLocation MEDIUM_PIRATE_SHIP_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_medium_ship/food");
/*  21 */   public static final ResourceLocation MEDIUM_PIRATE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_medium_ship/captain");
/*     */   
/*  23 */   public static final ResourceLocation LARGE_PIRATE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_large_ship/supplies");
/*  24 */   public static final ResourceLocation LARGE_PIRATE_SHIP_WEAPONS_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_large_ship/weapons");
/*  25 */   public static final ResourceLocation LARGE_PIRATE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_large_ship/captain");
/*  26 */   public static final ResourceLocation LARGE_PIRATE_SHIP_TREASURE_CHEST = new ResourceLocation("mineminenomi", "chests/pirate_large_ship/treasure");
/*     */   
/*  28 */   public static final ResourceLocation GHOST_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/ghost_ship/supplies");
/*  29 */   public static final ResourceLocation GHOST_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/ghost_ship/captain");
/*     */   
/*  31 */   public static final ResourceLocation MARINE_CAMP_SMALL_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/marine_camp/small_tent");
/*  32 */   public static final ResourceLocation MARINE_CAMP_LARGE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/marine_camp/large_tent");
/*     */   
/*  34 */   public static final ResourceLocation BANDIT_CAMP_SMALL_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_camp/small_tent");
/*  35 */   public static final ResourceLocation BANDIT_CAMP_LARGE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_camp/large_tent");
/*     */   
/*  37 */   public static final ResourceLocation SMALL_BANDIT_BASE_GOLD_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_small_base/gold");
/*  38 */   public static final ResourceLocation SMALL_BANDIT_BASE_MINE_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_small_base/mine");
/*  39 */   public static final ResourceLocation SMALL_BANDIT_BASE_LAB_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_small_base/lab");
/*  40 */   public static final ResourceLocation SMALL_BANDIT_BASE_ORES_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_small_base/ores");
/*     */   
/*  42 */   public static final ResourceLocation SMALL_MARINE_BASE_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/marine_small_base/captain");
/*  43 */   public static final ResourceLocation SMALL_MARINE_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/marine_small_base/food");
/*  44 */   public static final ResourceLocation SMALL_MARINE_BASE_DORM_CHEST = new ResourceLocation("mineminenomi", "chests/marine_small_base/dorm");
/*     */   
/*  46 */   public static final ResourceLocation LARGE_MARINE_BASE_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/marine_large_base/captain");
/*  47 */   public static final ResourceLocation LARGE_MARINE_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/marine_large_base/food");
/*  48 */   public static final ResourceLocation LARGE_MARINE_BASE_GENERIC_CHEST = new ResourceLocation("mineminenomi", "chests/marine_large_base/generic");
/*  49 */   public static final ResourceLocation LARGE_MARINE_BASE_LAB_CHEST = new ResourceLocation("mineminenomi", "chests/marine_large_base/lab");
/*     */   
/*  51 */   public static final ResourceLocation LARGE_MARINE_SHIP_CAPTAIN_CHEST = new ResourceLocation("mineminenomi", "chests/marine_battleship/captain");
/*  52 */   public static final ResourceLocation LARGE_MARINE_SHIP_SUPPLIES_CHEST = new ResourceLocation("mineminenomi", "chests/marine_battleship/supplies");
/*  53 */   public static final ResourceLocation LARGE_MARINE_SHIP_RUM_CHEST = new ResourceLocation("mineminenomi", "chests/marine_battleship/rum");
/*     */   
/*  55 */   public static final ResourceLocation LARGE_BANDIT_BASE_TOWER_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_large_base/tower");
/*  56 */   public static final ResourceLocation LARGE_BANDIT_BASE_TENT_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_large_base/tent");
/*  57 */   public static final ResourceLocation LARGE_BANDIT_BASE_FOOD_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_large_base/food");
/*  58 */   public static final ResourceLocation LARGE_BANDIT_BASE_DORM_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_large_base/dorm");
/*  59 */   public static final ResourceLocation LARGE_BANDIT_BASE_SECRET_STASH_CHEST = new ResourceLocation("mineminenomi", "chests/bandit_large_base/secret_stash");
/*     */   
/*  61 */   public static final ResourceLocation EASY_CARAVAN_CHEST = new ResourceLocation("mineminenomi", "chests/caravan/easy");
/*  62 */   public static final ResourceLocation HARD_CARAVAN_CHEST = new ResourceLocation("mineminenomi", "chests/caravan/hard");
/*     */   
/*  64 */   public static final ResourceLocation TAVERN_CHEST = new ResourceLocation("mineminenomi", "chests/tavern/food");
/*     */ 
/*     */   
/*  67 */   public static final ResourceLocation SKYPIEAN_TRADER = new ResourceLocation("mineminenomi", "entities/trader/skypiean_trader");
/*  68 */   public static final ResourceLocation PIRATE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/pirate_trader");
/*  69 */   public static final ResourceLocation MARINE_TRADER = new ResourceLocation("mineminenomi", "entities/trader/marine_trader");
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onVanillaLootLoading(LootTableLoadEvent event) {
/*  73 */     if (event.getName().toString().equals("minecraft:blocks/brain_coral_block")) {
/*  74 */       StandaloneLootEntry.Builder lootEntry = TableLootEntry.func_216171_a(new ResourceLocation("mineminenomi", "blocks/inject/brain_coral_block"));
/*  75 */       event.getTable().removePool("main");
/*  76 */       event.getTable().addPool(LootPool.func_216096_a().func_216045_a((LootEntry.Builder)lootEntry).func_216044_b());
/*     */     } 
/*     */     
/*  79 */     addPoolInVanillaTable(event, LootTables.field_204115_q);
/*  80 */     addPoolInVanillaTable(event, LootTables.field_204114_p);
/*  81 */     addPoolInVanillaTable(event, LootTables.field_204773_u);
/*  82 */     addPoolInVanillaTable(event, LootTables.field_204772_t);
/*  83 */     addPoolInVanillaTable(event, LootTables.field_186424_f);
/*  84 */     addPoolInVanillaTable(event, LootTables.field_204312_r);
/*  85 */     addPoolInVanillaTable(event, LootTables.field_186429_k);
/*  86 */     addPoolInVanillaTable(event, LootTables.field_186430_l);
/*  87 */     addPoolInVanillaTable(event, LootTables.field_215813_K);
/*  88 */     addPoolInVanillaTable(event, LootTables.field_191192_o);
/*  89 */     addPoolInVanillaTable(event, LootTables.field_186420_b);
/*  90 */     addPoolInVanillaTable(event, LootTables.field_186428_j);
/*     */   }
/*     */   
/*     */   private static void addPoolInVanillaTable(LootTableLoadEvent event, ResourceLocation vanillaTableKey) {
/*  94 */     if (event.getName().equals(vanillaTableKey)) {
/*  95 */       ResourceLocation modKey = new ResourceLocation("mineminenomi", vanillaTableKey.func_110623_a());
/*  96 */       StandaloneLootEntry.Builder lootEntry = TableLootEntry.func_216171_a(modKey);
/*  97 */       event.getTable().addPool(LootPool.func_216096_a().func_216045_a((LootEntry.Builder)lootEntry).func_216044_b());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static LootPool constructLootPool(String name, float minRolls, float maxRolls, LootEntry.Builder<?>... lootEntries) {
/* 102 */     LootPool.Builder poolBuilder = LootPool.func_216096_a().name(name).func_216046_a((IRandomRange)RandomValueRange.func_215837_a(minRolls, maxRolls));
/* 103 */     if (lootEntries != null)
/* 104 */       for (LootEntry.Builder<?> e : lootEntries) {
/* 105 */         if (e != null) {
/* 106 */           poolBuilder.func_216045_a(e);
/*     */         }
/*     */       }  
/* 109 */     return poolBuilder.func_216044_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModLootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */