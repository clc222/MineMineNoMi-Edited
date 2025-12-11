/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.bandit;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.loot.IRandomRange;
/*     */ import net.minecraft.loot.ItemLootEntry;
/*     */ import net.minecraft.loot.LootEntry;
/*     */ import net.minecraft.loot.LootPool;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.loot.RandomValueRange;
/*     */ import net.minecraft.loot.functions.ILootFunction;
/*     */ import net.minecraft.loot.functions.SetCount;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.data.conditions.RandomizedFruitsCondition;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.EncyclopediaCompletionFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetFruitClueFunction;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ public class BanditSmallBaseLootTable {
/*  21 */   private static final LootPool.Builder MINE_CHEST = LootPool.func_216096_a()
/*  22 */     .name("mineminenomi:mine")
/*  23 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 3.0F))
/*  24 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151035_b)
/*  25 */       .func_216086_a(100))
/*  26 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151037_a)
/*  27 */       .func_216086_a(90))
/*  28 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151046_w)
/*  29 */       .func_216086_a(50))
/*  30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Blocks.field_150335_W)
/*  31 */       .func_216086_a(30)
/*  32 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))))
/*  33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151044_h)
/*  34 */       .func_216086_a(60)
/*  35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))));
/*     */   
/*  37 */   private static final LootPool.Builder ORES_CHEST = LootPool.func_216096_a()
/*  38 */     .name("mineminenomi:ores")
/*  39 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))
/*  40 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151042_j)
/*  41 */       .func_216086_a(100)
/*  42 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  43 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Blocks.field_150346_d)
/*  44 */       .func_216086_a(100)
/*  45 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 15.0F))))
/*  46 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_191525_da)
/*  47 */       .func_216086_a(100)
/*  48 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 15.0F))))
/*  49 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151074_bl)
/*  50 */       .func_216086_a(100)
/*  51 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  52 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151166_bC)
/*  53 */       .func_216086_a(50)
/*  54 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 8.0F))))
/*  55 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151045_i)
/*  56 */       .func_216086_a(50)
/*  57 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 10.0F))))
/*  58 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151137_ax)
/*  59 */       .func_216086_a(90)
/*  60 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 20.0F))))
/*  61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI.get())
/*  62 */       .func_216086_a(50)
/*  63 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 10.0F))));
/*     */   
/*  65 */   private static final LootPool.Builder LAB_LEVER = LootPool.func_216096_a()
/*  66 */     .name("mineminenomi:lever")
/*  67 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_221746_ci));
/*     */   
/*  69 */   private static final LootPool.Builder LAB_BASIC = LootPool.func_216096_a()
/*  70 */     .name("mineminenomi:lab_basic")
/*  71 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/*  72 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196128_bn)
/*  73 */       .func_216086_a(100)
/*  74 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 10.0F))))
/*  75 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  76 */       .func_216086_a(80)
/*  77 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 8.0F))))
/*  78 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_191525_da)
/*  79 */       .func_216086_a(80)
/*  80 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))));
/*     */   
/*  82 */   private static final LootPool.Builder LAB_PREMIUM = LootPool.func_216096_a()
/*  83 */     .name("mineminenomi:lab_premium")
/*  84 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))
/*  85 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151065_br)
/*  86 */       .func_216086_a(100)
/*  87 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/*  88 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151070_bp)
/*  89 */       .func_216086_a(80)
/*  90 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 6.0F))))
/*  91 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_204840_eX)
/*  92 */       .func_216086_a(70)
/*  93 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  94 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151074_bl)
/*  95 */       .func_216086_a(60)
/*  96 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(4.0F, 6.0F))));
/*     */   
/*  98 */   private static final LootPool.Builder GOLD_CHEST = LootPool.func_216096_a()
/*  99 */     .name("mineminenomi:gold")
/* 100 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/* 101 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 102 */       .func_216086_a(100)
/* 103 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 300.0F)))
/* 104 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 105 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 106 */       .func_216086_a(70)
/* 107 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(300.0F, 500.0F)))
/* 108 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 109 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 110 */       .func_216086_a(30)
/* 111 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 2000.0F)))
/* 112 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 113 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Blocks.field_150340_R)
/* 114 */       .func_216086_a(80)
/* 115 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 116 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Blocks.field_150340_R)
/* 117 */       .func_216086_a(30)
/* 118 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))))
/* 119 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Blocks.field_150340_R)
/* 120 */       .func_216086_a(10)
/* 121 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))));
/*     */   
/* 123 */   private static final LootPool.Builder RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 124 */     .name("mineminenomi:randomized_fruits")
/* 125 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 126 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 127 */       .func_216086_a(20))
/* 128 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 129 */       .func_216086_a(2)
/* 130 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 131 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 132 */       .func_216086_a(4)
/* 133 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 134 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 138 */     LootTable.Builder mine = LootTable.func_216119_b().func_216040_a(MINE_CHEST);
/*     */ 
/*     */     
/* 141 */     LootTable.Builder ores = LootTable.func_216119_b().func_216040_a(ORES_CHEST);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     LootTable.Builder lab = LootTable.func_216119_b().func_216040_a(LAB_LEVER).func_216040_a(LAB_BASIC).func_216040_a(LAB_PREMIUM);
/*     */ 
/*     */ 
/*     */     
/* 150 */     LootTable.Builder gold = LootTable.func_216119_b().func_216040_a(GOLD_CHEST).func_216040_a(RANDOMIZED_FRUITS);
/*     */     
/* 152 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 153 */         Pair.of("mine", mine), 
/* 154 */         Pair.of("lab", lab), 
/* 155 */         Pair.of("ores", ores), 
/* 156 */         Pair.of("gold", gold)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\bandit\BanditSmallBaseLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */