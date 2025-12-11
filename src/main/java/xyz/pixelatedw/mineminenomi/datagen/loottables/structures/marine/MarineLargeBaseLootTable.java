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
/*     */ public class MarineLargeBaseLootTable {
/*  24 */   private static final LootPool.Builder FOOD_CHEST = LootPool.func_216096_a()
/*  25 */     .name("mineminenomi:food")
/*  26 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))
/*  27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/*  28 */       .func_216086_a(100)
/*  29 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/*  30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/*  31 */       .func_216086_a(100)
/*  32 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/*  34 */       .func_216086_a(100)
/*  35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  37 */       .func_216086_a(80)
/*  38 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_203180_bP)
/*  40 */       .func_216086_a(80)
/*  41 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 15.0F))))
/*  42 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151009_A)
/*  43 */       .func_216086_a(80)
/*  44 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  45 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179560_bq)
/*  46 */       .func_216086_a(80)
/*  47 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/*  48 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/*  49 */       .func_216086_a(70)
/*  50 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/*  51 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151127_ba)
/*  52 */       .func_216086_a(45)
/*  53 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  54 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151168_bH)
/*  55 */       .func_216086_a(45)
/*  56 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  57 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222070_lD)
/*  58 */       .func_216086_a(45))
/*  59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/*  60 */       .func_216086_a(45)
/*  61 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  62 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/*  63 */       .func_216086_a(20)
/*  64 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/*  65 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_226638_pX_)
/*  66 */       .func_216086_a(20))
/*  67 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151077_bg)
/*  68 */       .func_216086_a(10)
/*  69 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/*  70 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196102_ba)
/*  71 */       .func_216086_a(10)
/*  72 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/*  73 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ULTRA_COLA.get())
/*  74 */       .func_216086_a(20)
/*  75 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  76 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SEA_KING_MEAT.get())
/*  77 */       .func_216086_a(10)
/*  78 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))));
/*     */   
/*  80 */   private static final LootPool.Builder LAB_BASIC_CHEST = LootPool.func_216096_a()
/*  81 */     .name("mineminenomi:lab_basic")
/*  82 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/*  83 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196128_bn)
/*  84 */       .func_216086_a(100)
/*  85 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 10.0F))))
/*  86 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  87 */       .func_216086_a(80)
/*  88 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 8.0F))))
/*  89 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_191525_da)
/*  90 */       .func_216086_a(80)
/*  91 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 7.0F))))
/*  92 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151065_br)
/*  93 */       .func_216086_a(20)
/*  94 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 3.0F))));
/*     */   
/*  96 */   private static final LootPool.Builder LAB_PREMIUM_CHEST = LootPool.func_216096_a()
/*  97 */     .name("mineminenomi:lab_basic")
/*  98 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/*  99 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151065_br)
/* 100 */       .func_216086_a(80)
/* 101 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 10.0F))))
/* 102 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151070_bp)
/* 103 */       .func_216086_a(70)
/* 104 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))))
/* 105 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_204840_eX)
/* 106 */       .func_216086_a(5)
/* 107 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/* 108 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151074_bl)
/* 109 */       .func_216086_a(60)
/* 110 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))));
/*     */   
/* 112 */   private static final LootPool.Builder MELEE_WEAPONS_CHEST = LootPool.func_216096_a()
/* 113 */     .name("mineminenomi:melee_weapons")
/* 114 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 3.0F))
/* 115 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BROADSWORD.get())
/* 116 */       .func_216086_a(90)
/* 117 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/* 118 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 119 */       .func_216086_a(90)
/* 120 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/* 121 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SPEAR.get())
/* 122 */       .func_216086_a(90)
/* 123 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/* 124 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CLEAVER.get())
/* 125 */       .func_216086_a(90)
/* 126 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/* 127 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.PIPE.get())
/* 128 */       .func_216086_a(70))
/* 129 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.TONFA.get())
/* 130 */       .func_216086_a(70))
/* 131 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/* 132 */       .func_216086_a(30))
/* 133 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get())
/* 134 */       .func_216086_a(10));
/*     */   
/* 136 */   private static final LootPool.Builder RANGE_WEAPONS_CHEST = LootPool.func_216096_a()
/* 137 */     .name("mineminenomi:range_weapons")
/* 138 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 3.0F))
/* 139 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 140 */       .func_216086_a(100)
/* 141 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 142 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 143 */       .func_216086_a(70)
/* 144 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 30.0F))))
/* 145 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/* 146 */       .func_216086_a(100)
/* 147 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 148 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/* 149 */       .func_216086_a(30)
/* 150 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))))
/* 151 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 152 */       .func_216086_a(70)
/* 153 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 154 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SENRIKU.get())
/* 155 */       .func_216086_a(20))
/* 156 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.EISEN_DIAL.get())
/* 157 */       .func_216086_a(20)
/* 158 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 159 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.BREATH_DIAL.get())
/* 160 */       .func_216086_a(15)
/* 161 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 162 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.IMPACT_DIAL.get())
/* 163 */       .func_216086_a(15)
/* 164 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 165 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ROPE_NET.get())
/* 166 */       .func_216086_a(5))
/* 167 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_NET.get())
/* 168 */       .func_216086_a(2));
/*     */   
/* 170 */   private static final LootPool.Builder GENERIC_TREASURE_CHEST = LootPool.func_216096_a()
/* 171 */     .name("mineminenomi:riches")
/* 172 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 3.0F))
/* 173 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 174 */       .func_216086_a(100)
/* 175 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 176 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 177 */       .func_216086_a(20)
/* 178 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 179 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/* 180 */       .func_216086_a(30))
/* 181 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CIGARETTE.get())
/* 182 */       .func_216086_a(30)
/* 183 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 184 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.MEDIC_BAG.get())
/* 185 */       .func_216086_a(20)
/* 186 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*     */   
/* 188 */   private static final LootPool.Builder CAPTAIN_FOOD = LootPool.func_216096_a()
/* 189 */     .name("mineminenomi:food")
/* 190 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/* 191 */       .func_216086_a(100)
/* 192 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 193 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 194 */       .func_216086_a(20)
/* 195 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 196 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/* 197 */       .func_216086_a(20)
/* 198 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 199 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ULTRA_COLA.get())
/* 200 */       .func_216086_a(5)
/* 201 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 202 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/* 203 */       .func_216086_a(10)
/* 204 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))));
/*     */   
/* 206 */   private static final LootPool.Builder CAPTAIN_BELLY = LootPool.func_216096_a()
/* 207 */     .name("mineminenomi:belly")
/* 208 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 209 */       .func_216086_a(100)
/* 210 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 3000.0F))));
/*     */   
/* 212 */   private static final LootPool.Builder CAPTAIN_TREASURE = LootPool.func_216096_a()
/* 213 */     .name("mineminenomi:treasure")
/* 214 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 215 */       .func_216086_a(100)
/* 216 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 300.0F))))
/* 217 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.JITTE.get())
/* 218 */       .func_216086_a(60)
/* 219 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/* 220 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BISENTO.get())
/* 221 */       .func_216086_a(70)
/* 222 */       .func_212841_b_((ILootFunction.IBuilder)SetMarineColorFunction.builder()))
/* 223 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.EXPLOSIVE_HANDCUFFS.get())
/* 224 */       .func_216086_a(2))
/* 225 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 226 */       .func_216086_a(60)
/* 227 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 228 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/* 229 */       .func_216086_a(50)
/* 230 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(7.0F, 10.0F))))
/* 231 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/* 232 */       .func_216086_a(50))
/* 233 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/* 234 */       .func_216086_a(30))
/* 235 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.TONFA.get())
/* 236 */       .func_216086_a(60))
/* 237 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 238 */       .func_216086_a(65))
/* 239 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 240 */       .func_216086_a(40))
/* 241 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_3_BOX.get())
/* 242 */       .func_216086_a(30))
/* 243 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.GOLD_DEN_DEN_MUSHI.get())
/* 244 */       .func_216086_a(10));
/*     */   
/* 246 */   private static final LootPool.Builder CAPTAIN_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 247 */     .name("mineminenomi:randomized_fruits")
/* 248 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 249 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 250 */       .func_216086_a(9))
/* 251 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 252 */       .func_216086_a(5)
/* 253 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 254 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 255 */       .func_216086_a(6)
/* 256 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 257 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 261 */     LootTable.Builder food = LootTable.func_216119_b().func_216040_a(FOOD_CHEST);
/*     */ 
/*     */ 
/*     */     
/* 265 */     LootTable.Builder lab = LootTable.func_216119_b().func_216040_a(LAB_BASIC_CHEST).func_216040_a(LAB_PREMIUM_CHEST);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     LootTable.Builder generic = LootTable.func_216119_b().func_216040_a(MELEE_WEAPONS_CHEST).func_216040_a(RANGE_WEAPONS_CHEST).func_216040_a(GENERIC_TREASURE_CHEST);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     LootTable.Builder captain = LootTable.func_216119_b().func_216040_a(CAPTAIN_FOOD).func_216040_a(CAPTAIN_BELLY).func_216040_a(CAPTAIN_TREASURE).func_216040_a(CAPTAIN_RANDOMIZED_FRUITS);
/*     */     
/* 278 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 279 */         Pair.of("food", food), 
/* 280 */         Pair.of("lab", lab), 
/* 281 */         Pair.of("generic", generic), 
/* 282 */         Pair.of("captain", captain)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\marine\MarineLargeBaseLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */