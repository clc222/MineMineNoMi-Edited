/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.loot.ConstantRange;
/*     */ import net.minecraft.loot.IRandomRange;
/*     */ import net.minecraft.loot.ItemLootEntry;
/*     */ import net.minecraft.loot.LootEntry;
/*     */ import net.minecraft.loot.LootPool;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.loot.RandomValueRange;
/*     */ import net.minecraft.loot.functions.ApplyBonus;
/*     */ import net.minecraft.loot.functions.ILootFunction;
/*     */ import net.minecraft.loot.functions.SetCount;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.LootTablesDataGen;
/*     */ 
/*     */ public class BlocksLootTablesDataGen extends LootTablesDataGen {
/*  25 */   private final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
/*     */   
/*     */   public BlocksLootTablesDataGen(DataGenerator dataGenerator) {
/*  28 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_200398_a(DirectoryCache cache) {
/*  34 */     addLootTable((Block)ModBlocks.KAIROSEKI_ORE.get(), new LootPool.Builder[] { createOreLootPool((Item)ModItems.KAIROSEKI.get(), 1, 1, 3.0F, 6.0F) });
/*     */ 
/*     */     
/*  37 */     addLootTable((Block)ModBlocks.AXE_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.AXE_DIAL.get()) });
/*  38 */     addLootTable((Block)ModBlocks.BREATH_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.BREATH_DIAL.get()) });
/*  39 */     addLootTable((Block)ModBlocks.FLAME_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.FLAME_DIAL.get()) });
/*  40 */     addLootTable((Block)ModBlocks.REJECT_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.REJECT_DIAL.get()) });
/*  41 */     addLootTable((Block)ModBlocks.IMPACT_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.IMPACT_DIAL.get()) });
/*  42 */     addLootTable((Block)ModBlocks.FLASH_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.FLASH_DIAL.get()) });
/*  43 */     addLootTable((Block)ModBlocks.EISEN_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.EISEN_DIAL.get()) });
/*  44 */     addLootTable((Block)ModBlocks.MILKY_DIAL.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.MILKY_DIAL.get()) });
/*  45 */     addLootTable((Block)ModBlocks.DEN_DEN_MUSHI.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.DEN_DEN_MUSHI.get()) });
/*  46 */     addLootTable((Block)ModBlocks.CANNON.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.CANNON.get()) });
/*  47 */     addLootTable((Block)ModBlocks.KAIROSEKI.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.KAIROSEKI.get()) });
/*  48 */     addLootTable((Block)ModBlocks.KAIROSEKI_BARS.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.KAIROSEKI_BARS.get()) });
/*  49 */     addLootTable((Block)ModBlocks.MANGROVE_LOG.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.MANGROVE_LOG.get()) });
/*  50 */     addLootTable((Block)ModBlocks.MANGROVE_PLANKS.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.MANGROVE_PLANKS.get()) });
/*  51 */     addLootTable((Block)ModBlocks.MANGROVE_WOOD.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.MANGROVE_WOOD.get()) });
/*  52 */     addLootTable((Block)ModBlocks.STRIPPED_MANGROVE_LOG.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.STRIPPED_MANGROVE_LOG.get()) });
/*  53 */     addLootTable((Block)ModBlocks.STRIPPED_MANGROVE_WOOD.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.STRIPPED_MANGROVE_WOOD.get()) });
/*  54 */     addLootTable((Block)ModBlocks.MOSS.get(), new LootPool.Builder[] { createSelfDropLootPool((Block)ModBlocks.MOSS.get()) });
/*     */     
/*  56 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/*  57 */     this.lootTables.forEach((block, builder) -> tables.put(block.func_220068_i(), builder.func_216038_b()));
/*     */ 
/*     */ 
/*     */     
/*  61 */     writeTables(cache, tables);
/*     */   }
/*     */   
/*     */   protected LootPool.Builder createBasicLootPool(Item drop, int roll, float count) {
/*  65 */     return createBasicLootPool(drop, roll, roll, count, count);
/*     */   }
/*     */   protected LootPool.Builder createBasicLootPool(Item drop, int rollMin, int rollMax, float countMin, float countMax) {
/*     */     RandomValueRange randomValueRange1;
/*     */     RandomValueRange randomValueRange2;
/*  70 */     if (rollMin == rollMax) {
/*  71 */       ConstantRange constantRange = ConstantRange.func_215835_a(rollMin);
/*     */     } else {
/*     */       
/*  74 */       randomValueRange1 = RandomValueRange.func_215837_a(rollMin, rollMax);
/*     */     } 
/*     */ 
/*     */     
/*  78 */     if (countMin == countMax) {
/*  79 */       ConstantRange constantRange = ConstantRange.func_215835_a((int)countMin);
/*     */     } else {
/*     */       
/*  82 */       randomValueRange2 = RandomValueRange.func_215837_a(countMin, countMax);
/*     */     } 
/*     */     
/*  85 */     return LootPool.func_216096_a().func_216046_a((IRandomRange)randomValueRange1)
/*  86 */       .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)drop)
/*  87 */         .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)randomValueRange2)));
/*     */   }
/*     */   
/*     */   protected LootPool.Builder createSelfDropLootPool(Block block) {
/*  91 */     return LootPool.func_216096_a().func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/*  92 */       .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)block.func_199767_j())
/*  93 */         .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1))));
/*     */   }
/*     */   
/*     */   protected LootPool.Builder createOreLootPool(Item ore, int roll, float count) {
/*  97 */     return createOreLootPool(ore, roll, roll, count, count);
/*     */   }
/*     */   protected LootPool.Builder createOreLootPool(Item ore, int rollMin, int rollMax, float countMin, float countMax) {
/*     */     RandomValueRange randomValueRange1;
/*     */     RandomValueRange randomValueRange2;
/* 102 */     if (rollMin == rollMax) {
/* 103 */       ConstantRange constantRange = ConstantRange.func_215835_a(rollMin);
/*     */     } else {
/*     */       
/* 106 */       randomValueRange1 = RandomValueRange.func_215837_a(rollMin, rollMax);
/*     */     } 
/*     */ 
/*     */     
/* 110 */     if (countMin == countMax) {
/* 111 */       ConstantRange constantRange = ConstantRange.func_215835_a((int)countMin);
/*     */     } else {
/*     */       
/* 114 */       randomValueRange2 = RandomValueRange.func_215837_a(countMin, countMax);
/*     */     } 
/*     */     
/* 117 */     return LootPool.func_216096_a().func_216046_a((IRandomRange)randomValueRange1)
/* 118 */       .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ore)
/* 119 */         .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)randomValueRange2))
/* 120 */         .func_212841_b_((ILootFunction.IBuilder)ApplyBonus.func_215869_a(Enchantments.field_185308_t)));
/*     */   }
/*     */   
/*     */   protected void addLootTable(Block block, LootPool.Builder... pools) {
/* 124 */     LootTable.Builder builder = LootTable.func_216119_b();
/* 125 */     for (LootPool.Builder pool : pools) {
/* 126 */       builder.func_216040_a(pool);
/*     */     }
/* 128 */     this.lootTables.put(block, builder);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_200397_b() {
/* 133 */     return "Blocks Loot Pools";
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\BlocksLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */