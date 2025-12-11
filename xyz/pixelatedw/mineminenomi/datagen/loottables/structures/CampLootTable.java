/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures;
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
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class CampLootTable {
/*  22 */   private static final LootPool.Builder SMALL_TENT_FOOD = LootPool.func_216096_a()
/*  23 */     .name("mineminenomi:food")
/*  24 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))
/*  25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/*  26 */       .func_216086_a(100)
/*  27 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/*  28 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  29 */       .func_216086_a(100)
/*  30 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/*  31 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222112_pR)
/*  32 */       .func_216086_a(100)
/*  33 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/*  34 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/*  35 */       .func_216086_a(80)
/*  36 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 10.0F))))
/*  37 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151054_z)
/*  38 */       .func_216086_a(80)
/*  39 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  40 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151172_bF)
/*  41 */       .func_216086_a(50)
/*  42 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  43 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185164_cV)
/*  44 */       .func_216086_a(50)
/*  45 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  46 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/*  47 */       .func_216086_a(40)
/*  48 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))));
/*     */   
/*  50 */   private static final LootPool.Builder SMALL_TENT_WEAPONS = LootPool.func_216096_a()
/*  51 */     .name("mineminenomi:weapons")
/*  52 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 3.0F))
/*  53 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/*  54 */       .func_216086_a(100)
/*  55 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 15.0F))))
/*  56 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  57 */       .func_216086_a(80)
/*  58 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/*  59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.PIPE.get())
/*  60 */       .func_216086_a(90))
/*  61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  62 */       .func_216086_a(90))
/*  63 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/*  64 */       .func_216086_a(40))
/*  65 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/*  66 */       .func_216086_a(20)
/*  67 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))));
/*     */   
/*  69 */   private static final LootPool.Builder LARGE_TENT_FOOD = LootPool.func_216096_a()
/*  70 */     .name("mineminenomi:food")
/*  71 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/*  72 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/*  73 */       .func_216086_a(100)
/*  74 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/*  75 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151009_A)
/*  76 */       .func_216086_a(100)
/*  77 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  78 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185165_cW)
/*  79 */       .func_216086_a(100)
/*  80 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  81 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  82 */       .func_216086_a(100)
/*  83 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))))
/*  84 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179558_bo)
/*  85 */       .func_216086_a(50)
/*  86 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  87 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151147_al)
/*  88 */       .func_216086_a(50)
/*  89 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/*  90 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/*  91 */       .func_216086_a(20)
/*  92 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  93 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/*  94 */       .func_216086_a(20)
/*  95 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))));
/*     */   
/*  97 */   private static final LootPool.Builder LARGE_TENT_TREASURE = LootPool.func_216096_a()
/*  98 */     .name("mineminenomi:treasure")
/*  99 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/* 100 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 101 */       .func_216086_a(100)
/* 102 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 100.0F)))
/* 103 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 104 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 105 */       .func_216086_a(20)
/* 106 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 500.0F)))
/* 107 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 108 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 109 */       .func_216086_a(80)
/* 110 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 111 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/* 112 */       .func_216086_a(80)
/* 113 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 114 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/* 115 */       .func_216086_a(50))
/* 116 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/* 117 */       .func_216086_a(50))
/* 118 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151111_aL)
/* 119 */       .func_216086_a(50))
/* 120 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SENRIKU.get())
/* 121 */       .func_216086_a(20))
/* 122 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151141_av)
/* 123 */       .func_216086_a(40));
/*     */   
/* 125 */   private static final LootPool.Builder LARGE_TENT_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 126 */     .name("mineminenomi:randomized_fruits")
/* 127 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 128 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 129 */       .func_216086_a(40))
/* 130 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 131 */       .func_216086_a(2)
/* 132 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 133 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 134 */       .func_216086_a(1)
/* 135 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 136 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 141 */     LootTable.Builder small_tent = LootTable.func_216119_b().func_216040_a(SMALL_TENT_WEAPONS).func_216040_a(SMALL_TENT_FOOD);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     LootTable.Builder large_tent = LootTable.func_216119_b().func_216040_a(LARGE_TENT_FOOD).func_216040_a(LARGE_TENT_TREASURE).func_216040_a(LARGE_TENT_RANDOMIZED_FRUITS);
/*     */     
/* 148 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 149 */         Pair.of("small_tent", small_tent), 
/* 150 */         Pair.of("large_tent", large_tent)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\CampLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */