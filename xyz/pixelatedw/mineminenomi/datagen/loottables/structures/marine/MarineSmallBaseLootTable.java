/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.marine;
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
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetMarineColorFunction;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class MarineSmallBaseLootTable {
/*  24 */   private static final LootPool.Builder SUPPLIES_FOOD = LootPool.func_216096_a()
/*  25 */     .name("mineminenomi:food")
/*  26 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))
/*  27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/*  28 */       .func_216086_a(50)
/*  29 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/*  31 */       .func_216086_a(80)
/*  32 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/*  34 */       .func_216086_a(80)
/*  35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/*  36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  37 */       .func_216086_a(70)
/*  38 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151117_aB)
/*  40 */       .func_216086_a(80))
/*  41 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/*  42 */       .func_216086_a(60)
/*  43 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  44 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_226638_pX_)
/*  45 */       .func_216086_a(50)
/*  46 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  47 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151157_am)
/*  48 */       .func_216086_a(30)
/*  49 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  50 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196087_aX)
/*  51 */       .func_216086_a(20)
/*  52 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  53 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196086_aW)
/*  54 */       .func_216086_a(50)
/*  55 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  56 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179560_bq)
/*  57 */       .func_216086_a(30)
/*  58 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222070_lD)
/*  60 */       .func_216086_a(30))
/*  61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/*  62 */       .func_216086_a(30)
/*  63 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  64 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SAKE_BOTTLE.get())
/*  65 */       .func_216086_a(20)
/*  66 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))));
/*     */   
/*  68 */   private static final LootPool.Builder DORM_CHEST = LootPool.func_216096_a()
/*  69 */     .name("mineminenomi:dorm")
/*  70 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))
/*  71 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/*  72 */       .func_216086_a(100)
/*  73 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 15.0F))))
/*  74 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  75 */       .func_216086_a(50)
/*  76 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 150.0F)))
/*  77 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  78 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BROADSWORD.get())
/*  79 */       .func_216086_a(90)
/*  80 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/*  81 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/*  82 */       .func_216086_a(90)
/*  83 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/*  84 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SPEAR.get())
/*  85 */       .func_216086_a(90)
/*  86 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/*  87 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CLEAVER.get())
/*  88 */       .func_216086_a(90)
/*  89 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/*  90 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  91 */       .func_216086_a(90))
/*  92 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  93 */       .func_216086_a(60)
/*  94 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 15.0F))))
/*  95 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151040_l)
/*  96 */       .func_216086_a(70))
/*  97 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185159_cQ)
/*  98 */       .func_216086_a(70))
/*  99 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get())
/* 100 */       .func_216086_a(40)
/* 101 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 102 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.MEDIC_BAG.get())
/* 103 */       .func_216086_a(20))
/* 104 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/* 105 */       .func_216086_a(80))
/* 106 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222070_lD)
/* 107 */       .func_216086_a(30))
/* 108 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151141_av)
/* 109 */       .func_216086_a(60)
/* 110 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*     */   
/* 112 */   private static final LootPool.Builder CAPTAIN_CHEST = LootPool.func_216096_a()
/* 113 */     .name("mineminenomi:captain")
/* 114 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/* 115 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 116 */       .func_216086_a(100)
/* 117 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(20.0F, 200.0F)))
/* 118 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 119 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/* 120 */       .func_216086_a(30))
/* 121 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get())
/* 122 */       .func_216086_a(40)
/* 123 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 124 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/* 125 */       .func_216086_a(55))
/* 126 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 127 */       .func_216086_a(90))
/* 128 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CIGAR.get())
/* 129 */       .func_216086_a(50)
/* 130 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/* 131 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SMOKING_PIPE.get())
/* 132 */       .func_216086_a(40)
/* 133 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 134 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/* 135 */       .func_216086_a(60)
/* 136 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 10.0F))))
/* 137 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SAKE_BOTTLE.get())
/* 138 */       .func_216086_a(30))
/* 139 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 140 */       .func_216086_a(30))
/* 141 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 142 */       .func_216086_a(50))
/* 143 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 144 */       .func_216086_a(25));
/*     */   
/* 146 */   private static final LootPool.Builder CAPTAIN_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 147 */     .name("mineminenomi:randomized_fruits")
/* 148 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 149 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 150 */       .func_216086_a(21))
/* 151 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 152 */       .func_216086_a(6)
/* 153 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 154 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 155 */       .func_216086_a(5)
/* 156 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 157 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 161 */     LootTable.Builder supplies = LootTable.func_216119_b().func_216040_a(SUPPLIES_FOOD);
/*     */ 
/*     */     
/* 164 */     LootTable.Builder dorm = LootTable.func_216119_b().func_216040_a(DORM_CHEST);
/*     */ 
/*     */ 
/*     */     
/* 168 */     LootTable.Builder captain = LootTable.func_216119_b().func_216040_a(CAPTAIN_CHEST).func_216040_a(CAPTAIN_RANDOMIZED_FRUITS);
/*     */     
/* 170 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 171 */         Pair.of("supplies", supplies), 
/* 172 */         Pair.of("dorm", dorm), 
/* 173 */         Pair.of("captain", captain)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\marine\MarineSmallBaseLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */