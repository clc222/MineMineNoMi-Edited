/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.pirate;
/*     */ 
/*     */ import net.minecraft.item.ItemStack;
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
/*     */ public class PirateMediumShipLootTables {
/*  24 */   private static final LootPool.Builder SUPPLIES_WEAPONS = LootPool.func_216096_a()
/*  25 */     .name("mineminenomi:weapons")
/*  26 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/*  27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CUTLASS.get())
/*  28 */       .func_216086_a(90)
/*  29 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/*  31 */       .func_216086_a(90)
/*  32 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.AXE.get())
/*  34 */       .func_216086_a(90)
/*  35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.PIPE.get())
/*  37 */       .func_216086_a(40))
/*  38 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/*  39 */       .func_216086_a(70)
/*  40 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  41 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CLEAVER.get())
/*  42 */       .func_216086_a(70)
/*  43 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  44 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/*  45 */       .func_216086_a(100)
/*  46 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 20.0F))))
/*  47 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/*  48 */       .func_216086_a(30)
/*  49 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 20.0F))))
/*  50 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CANNON_BALL.get())
/*  51 */       .func_216086_a(50)
/*  52 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  53 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  54 */       .func_216086_a(70)
/*  55 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 15.0F))))
/*  56 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  57 */       .func_216086_a(70)
/*  58 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SENRIKU.get())
/*  60 */       .func_216086_a(30))
/*  61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.EISEN_DIAL.get())
/*  62 */       .func_216086_a(20)
/*  63 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  64 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.BREATH_DIAL.get())
/*  65 */       .func_216086_a(15)
/*  66 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  67 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.IMPACT_DIAL.get())
/*  68 */       .func_216086_a(15)
/*  69 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))));
/*     */ 
/*     */   
/*  72 */   private static final LootPool.Builder SUPPLIES_RANDOM = LootPool.func_216096_a()
/*  73 */     .name("mineminenomi:supplies")
/*  74 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))
/*  75 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  76 */       .func_216086_a(100)
/*  77 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 50.0F)))
/*  78 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  79 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  80 */       .func_216086_a(50)
/*  81 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 100.0F))))
/*  82 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151042_j)
/*  83 */       .func_216086_a(70)
/*  84 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))))
/*  85 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151007_F)
/*  86 */       .func_216086_a(70)
/*  87 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))))
/*  88 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  89 */       .func_216086_a(70)
/*  90 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))))
/*  91 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get())
/*  92 */       .func_216086_a(10)
/*  93 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  94 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/*  95 */       .func_216086_a(5));
/*     */ 
/*     */   
/*  98 */   private static final LootPool.Builder FOOD_STANDARD = LootPool.func_216096_a()
/*  99 */     .name("mineminenomi:standard_food")
/* 100 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/* 101 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/* 102 */       .func_216086_a(100)
/* 103 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/* 104 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151009_A)
/* 105 */       .func_216086_a(100)
/* 106 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 107 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185165_cW)
/* 108 */       .func_216086_a(100)
/* 109 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 110 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_203180_bP)
/* 111 */       .func_216086_a(80)
/* 112 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 20.0F))))
/* 113 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196086_aW)
/* 114 */       .func_216086_a(50)
/* 115 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 116 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/* 117 */       .func_216086_a(50)
/* 118 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 119 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196087_aX)
/* 120 */       .func_216086_a(50)
/* 121 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 122 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196088_aY)
/* 123 */       .func_216086_a(30)
/* 124 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 125 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/* 126 */       .func_216086_a(30)
/* 127 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))));
/*     */ 
/*     */   
/* 130 */   private static final LootPool.Builder FOOD_PREMIUM = LootPool.func_216096_a()
/* 131 */     .name("mineminenomi:premium_food")
/* 132 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 4.0F))
/* 133 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196102_ba)
/* 134 */       .func_216086_a(100)
/* 135 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 136 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196104_bb)
/* 137 */       .func_216086_a(100)
/* 138 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 139 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ULTRA_COLA.get())
/* 140 */       .func_216086_a(50)
/* 141 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 142 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 143 */       .func_216086_a(70)
/* 144 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 145 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SAKE_BOTTLE.get())
/* 146 */       .func_216086_a(70))
/* 147 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SEA_KING_MEAT.get())
/* 148 */       .func_216086_a(30)
/* 149 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*     */ 
/*     */   
/* 152 */   private static final LootPool.Builder CAPTAIN_TREASURE = LootPool.func_216096_a()
/* 153 */     .name("mineminenomi:treasure")
/* 154 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 4.0F))
/* 155 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 156 */       .func_216086_a(100)
/* 157 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 250.0F)))
/* 158 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 159 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 160 */       .func_216086_a(20)
/* 161 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 500.0F))))
/* 162 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/* 163 */       .func_216086_a(40))
/* 164 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/* 165 */       .func_216086_a(70))
/* 166 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ULTRA_COLA.get())
/* 167 */       .func_216086_a(50)
/* 168 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 169 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 170 */       .func_216086_a(50)
/* 171 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 172 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.THREE_CIGARS.get())
/* 173 */       .func_216086_a(40))
/* 174 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SMOKING_PIPE.get())
/* 175 */       .func_216086_a(20))
/* 176 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 177 */       .func_216086_a(45))
/* 178 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 179 */       .func_216086_a(25));
/*     */ 
/*     */   
/* 182 */   private static final LootPool.Builder CAPTAIN_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 183 */     .name("mineminenomi:randomized_fruits")
/* 184 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 185 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 186 */       .func_216086_a(20))
/* 187 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 188 */       .func_216086_a(5)
/* 189 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 190 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 191 */       .func_216086_a(4)
/* 192 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 193 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 199 */     LootTable.Builder supplies = LootTable.func_216119_b().func_216040_a(SUPPLIES_WEAPONS).func_216040_a(SUPPLIES_RANDOM);
/*     */ 
/*     */ 
/*     */     
/* 203 */     LootTable.Builder food = LootTable.func_216119_b().func_216040_a(FOOD_STANDARD).func_216040_a(FOOD_PREMIUM);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     LootTable.Builder captain = LootTable.func_216119_b().func_216040_a(SUPPLIES_WEAPONS).func_216040_a(CAPTAIN_TREASURE).func_216040_a(CAPTAIN_RANDOMIZED_FRUITS);
/*     */     
/* 210 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 211 */         Pair.of("supplies", supplies), 
/* 212 */         Pair.of("food", food), 
/* 213 */         Pair.of("captain", captain)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\pirate\PirateMediumShipLootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */