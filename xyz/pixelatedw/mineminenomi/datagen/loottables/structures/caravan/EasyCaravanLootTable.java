/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.caravan;
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
/*     */ 
/*     */ public class EasyCaravanLootTable {
/*  21 */   private static final LootPool.Builder FOOD = LootPool.func_216096_a()
/*  22 */     .name("mineminenomi:food")
/*  23 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))
/*  24 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/*  25 */       .func_216086_a(100)
/*  26 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/*  27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  28 */       .func_216086_a(100)
/*  29 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/*  30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222112_pR)
/*  31 */       .func_216086_a(100)
/*  32 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/*  33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/*  34 */       .func_216086_a(80)
/*  35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 10.0F))))
/*  36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151054_z)
/*  37 */       .func_216086_a(80)
/*  38 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151172_bF)
/*  40 */       .func_216086_a(50)
/*  41 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  42 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185164_cV)
/*  43 */       .func_216086_a(50)
/*  44 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  45 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/*  46 */       .func_216086_a(40)
/*  47 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  48 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/*  49 */       .func_216086_a(20)
/*  50 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  51 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/*  52 */       .func_216086_a(20)
/*  53 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*     */   
/*  55 */   private static final LootPool.Builder TREASURE = LootPool.func_216096_a()
/*  56 */     .name("mineminenomi:treasure")
/*  57 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/*  58 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  59 */       .func_216086_a(100)
/*  60 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 100.0F)))
/*  61 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  62 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  63 */       .func_216086_a(20)
/*  64 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 500.0F)))
/*  65 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  66 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  67 */       .func_216086_a(80)
/*  68 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  69 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/*  70 */       .func_216086_a(80)
/*  71 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  72 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/*  73 */       .func_216086_a(70))
/*  74 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/*  75 */       .func_216086_a(70))
/*  76 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151111_aL)
/*  77 */       .func_216086_a(70))
/*  78 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151141_av)
/*  79 */       .func_216086_a(60))
/*  80 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/*  81 */       .func_216086_a(70))
/*  82 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/*  83 */       .func_216086_a(30));
/*     */   
/*  85 */   private static final LootPool.Builder RANDOMIZED_FRUITS = LootPool.func_216096_a()
/*  86 */     .name("mineminenomi:randomized_fruits")
/*  87 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/*  88 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/*  89 */       .func_216086_a(40))
/*  90 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/*  91 */       .func_216086_a(2)
/*  92 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/*  93 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/*  94 */       .func_216086_a(1)
/*  95 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/*  96 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 102 */     LootTable.Builder caravan = LootTable.func_216119_b().func_216040_a(FOOD).func_216040_a(TREASURE).func_216040_a(RANDOMIZED_FRUITS);
/*     */     
/* 104 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 105 */         Pair.of("easy", caravan)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\caravan\EasyCaravanLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */