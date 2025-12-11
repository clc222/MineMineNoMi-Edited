/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.caravan;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.loot.ConstantRange;
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
/*     */ 
/*     */ public class HardCaravanLootTable {
/*  22 */   private static final LootPool.Builder FOOD = LootPool.func_216096_a()
/*  23 */     .name("mineminenomi:food")
/*  24 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))
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
/*  48 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  49 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/*  50 */       .func_216086_a(20)
/*  51 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  52 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/*  53 */       .func_216086_a(20)
/*  54 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*     */   
/*  56 */   private static final LootPool.Builder TREASURE = LootPool.func_216096_a()
/*  57 */     .name("mineminenomi:treasure")
/*  58 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))
/*  59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  60 */       .func_216086_a(100)
/*  61 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 100.0F)))
/*  62 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  63 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  64 */       .func_216086_a(20)
/*  65 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 500.0F)))
/*  66 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  67 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  68 */       .func_216086_a(80)
/*  69 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  70 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/*  71 */       .func_216086_a(80)
/*  72 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  73 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/*  74 */       .func_216086_a(50))
/*  75 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/*  76 */       .func_216086_a(50))
/*  77 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151111_aL)
/*  78 */       .func_216086_a(50))
/*  79 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151141_av)
/*  80 */       .func_216086_a(40));
/*     */   
/*  82 */   private static final LootPool.Builder FRUIT_BOX = LootPool.func_216096_a()
/*  83 */     .name("mineminenomi:fruit_box")
/*  84 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/*  85 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/*  86 */       .func_216086_a(50))
/*  87 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/*  88 */       .func_216086_a(35))
/*  89 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_3_BOX.get())
/*  90 */       .func_216086_a(15));
/*     */   
/*  92 */   private static final LootPool.Builder RANDOMIZED_FRUITS = LootPool.func_216096_a()
/*  93 */     .name("mineminenomi:randomized_fruits")
/*  94 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/*  95 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/*  96 */       .func_216086_a(20))
/*  97 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/*  98 */       .func_216086_a(2)
/*  99 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 100 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 101 */       .func_216086_a(1)
/* 102 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 103 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 110 */     LootTable.Builder caravan = LootTable.func_216119_b().func_216040_a(FOOD).func_216040_a(TREASURE).func_216040_a(FRUIT_BOX).func_216040_a(RANDOMIZED_FRUITS);
/*     */     
/* 112 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 113 */         Pair.of("hard", caravan)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\caravan\HardCaravanLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */