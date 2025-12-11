/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.BigDuckDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.BruteDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.CaptainDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.DenDenMushiDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.GorillaDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.GruntDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.LapahnDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.NormalDugongDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.RareDugongDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.SeaCowDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.SkypieanDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.SkypieanTraderDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.SniperDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.TraderDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.WhiteWalkieDrops;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.entities.YagaraBullDrops;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.LootTablesDataGen;
/*     */ 
/*     */ public class EntitiesLootTablesDataGen
/*     */   extends LootTablesDataGen
/*     */ {
/*  34 */   private final Multimap<EntityType<?>, LootTable.Builder> lootTables = (Multimap<EntityType<?>, LootTable.Builder>)HashMultimap.create();
/*     */   
/*     */   public EntitiesLootTablesDataGen(DataGenerator dataGenerator) {
/*  37 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_200398_a(DirectoryCache cache) {
/*  42 */     addLootTable((EntityType)ModEntities.KUNG_FU_DUGONG.get(), NormalDugongDrops.getTable());
/*  43 */     addLootTable((EntityType)ModEntities.BOXING_DUGONG.get(), NormalDugongDrops.getTable());
/*  44 */     addLootTable((EntityType)ModEntities.WRESTLING_DUGONG.get(), NormalDugongDrops.getTable());
/*  45 */     addLootTable((EntityType)ModEntities.LEGENDARY_MASTER_DUGONG.get(), RareDugongDrops.getTable());
/*  46 */     addLootTable((EntityType)ModEntities.WANDERING_DUGONG.get(), RareDugongDrops.getTable());
/*     */     
/*  48 */     addLootTable((EntityType)ModEntities.LAPAHN.get(), LapahnDrops.getTable());
/*     */     
/*  50 */     addLootTable((EntityType)ModEntities.WHITE_WALKIE.get(), WhiteWalkieDrops.getTable());
/*     */     
/*  52 */     addLootTable((EntityType)ModEntities.SEA_COW.get(), SeaCowDrops.getTable());
/*     */     
/*  54 */     addLootTable((EntityType)ModEntities.DEN_DEN_MUSHI.get(), DenDenMushiDrops.getTable());
/*     */     
/*  56 */     addLootTable((EntityType)ModEntities.YAGARA_BULL.get(), YagaraBullDrops.getTable());
/*     */     
/*  58 */     addLootTable((EntityType)ModEntities.BIG_DUCK.get(), BigDuckDrops.getTable());
/*     */     
/*  60 */     addLootTable((EntityType)ModEntities.BLUGORI.get(), GorillaDrops.getTable());
/*  61 */     addLootTable((EntityType)ModEntities.BLAGORI.get(), GorillaDrops.getTable());
/*     */     
/*  63 */     addLootTable((EntityType)ModEntities.MARINE_GRUNT.get(), GruntDrops.getTable());
/*  64 */     addLootTable((EntityType)ModEntities.PIRATE_GRUNT.get(), GruntDrops.getTable());
/*  65 */     addLootTable((EntityType)ModEntities.BANDIT_GRUNT.get(), GruntDrops.getTable());
/*     */     
/*  67 */     addLootTable((EntityType)ModEntities.MARINE_SNIPER.get(), SniperDrops.getTable());
/*  68 */     addLootTable((EntityType)ModEntities.BANDIT_SNIPER.get(), SniperDrops.getTable());
/*     */     
/*  70 */     addLootTable((EntityType)ModEntities.MARINE_BRUTE.get(), BruteDrops.getTable());
/*  71 */     addLootTable((EntityType)ModEntities.PIRATE_BRUTE.get(), BruteDrops.getTable());
/*  72 */     addLootTable((EntityType)ModEntities.BANDIT_BRUTE.get(), BruteDrops.getTable());
/*  73 */     addLootTable((EntityType)ModEntities.PIRATE_BOMBER.get(), BruteDrops.getTable());
/*     */     
/*  75 */     addLootTable((EntityType)ModEntities.MARINE_CAPTAIN.get(), CaptainDrops.getTable());
/*  76 */     addLootTable((EntityType)ModEntities.PIRATE_CAPTAIN.get(), CaptainDrops.getTable());
/*  77 */     addLootTable((EntityType)ModEntities.BANDIT_CAPTAIN.get(), CaptainDrops.getTable());
/*     */     
/*  79 */     addLootTable((EntityType)ModEntities.MARINE_TRADER.get(), TraderDrops.getTable());
/*  80 */     addLootTable((EntityType)ModEntities.PIRATE_TRADER.get(), TraderDrops.getTable());
/*  81 */     addLootTable((EntityType)ModEntities.SKYPIEAN_TRADER.get(), SkypieanTraderDrops.getTable());
/*     */     
/*  83 */     addLootTable((EntityType)ModEntities.SKYPIEAN_CIVILIAN.get(), SkypieanDrops.getTable());
/*     */     
/*  85 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/*  86 */     this.lootTables.forEach((key, value) -> {
/*     */           ResourceLocation res = new ResourceLocation(key.getRegistryName().func_110624_b(), "entities/" + key.getRegistryName().func_110623_a());
/*     */           
/*     */           tables.put(res, value.func_216038_b());
/*     */         });
/*  91 */     writeTables(cache, tables);
/*     */   }
/*     */   
/*     */   protected void addLootTable(EntityType<?> entityType, LootTable.Builder table) {
/*  95 */     this.lootTables.put(entityType, table);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_200397_b() {
/* 100 */     return "Entities Loot Pools";
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\EntitiesLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */