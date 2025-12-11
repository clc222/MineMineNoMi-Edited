/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.bandit;
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
/*     */ public class BanditLargeBaseLootTable {
/*  23 */   private static final LootPool.Builder TOWER_SUPPLIES = LootPool.func_216096_a()
/*  24 */     .name("mineminenomi:tower_supplies")
/*  25 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))
/*  26 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151032_g)
/*  27 */       .func_216086_a(100)
/*  28 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  29 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/*  30 */       .func_216086_a(100)
/*  31 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  32 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  33 */       .func_216086_a(80)
/*  34 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 20.0F))))
/*  35 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151031_f)
/*  36 */       .func_216086_a(80))
/*  37 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  38 */       .func_216086_a(80))
/*  39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222114_py)
/*  40 */       .func_216086_a(20))
/*  41 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SENRIKU.get())
/*  42 */       .func_216086_a(20))
/*  43 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ROPE_NET.get())
/*  44 */       .func_216086_a(10))
/*  45 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151113_aN)
/*  46 */       .func_216086_a(80))
/*  47 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151036_c)
/*  48 */       .func_216086_a(30))
/*  49 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151040_l)
/*  50 */       .func_216086_a(30))
/*  51 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DAGGER.get())
/*  52 */       .func_216086_a(30))
/*  53 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/*  54 */       .func_216086_a(70)
/*  55 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 10.0F))))
/*  56 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  57 */       .func_216086_a(70)
/*  58 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/*  59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/*  60 */       .func_216086_a(70)
/*  61 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 15.0F))))
/*  62 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.SNIPER_GOGGLES.get())
/*  63 */       .func_216086_a(10));
/*     */   
/*  65 */   private static final LootPool.Builder TENT_SUPPLIES = LootPool.func_216096_a()
/*  66 */     .name("mineminenomi:tent_supplies")
/*  67 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))
/*  68 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/*  69 */       .func_216086_a(100)
/*  70 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 50.0F)))
/*  71 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  72 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222112_pR)
/*  73 */       .func_216086_a(100)
/*  74 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/*  75 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/*  76 */       .func_216086_a(50)
/*  77 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/*  78 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151055_y)
/*  79 */       .func_216086_a(100)
/*  80 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F))))
/*  81 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/*  82 */       .func_216086_a(60))
/*  83 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BANDIT_KNIFE.get())
/*  84 */       .func_216086_a(80))
/*  85 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/*  86 */       .func_216086_a(60))
/*  87 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  88 */       .func_216086_a(80))
/*  89 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/*  90 */       .func_216086_a(100)
/*  91 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 7.0F))))
/*  92 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/*  93 */       .func_216086_a(30)
/*  94 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/*  95 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_226638_pX_)
/*  96 */       .func_216086_a(30)
/*  97 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/*  98 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151141_av)
/*  99 */       .func_216086_a(60));
/*     */   
/* 101 */   private static final LootPool.Builder TENT_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 102 */     .name("mineminenomi:randomized_fruits")
/* 103 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 104 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 105 */       .func_216086_a(30))
/* 106 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 107 */       .func_216086_a(3)
/* 108 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 109 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 110 */       .func_216086_a(1)
/* 111 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 112 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */   
/* 114 */   private static final LootPool.Builder SECRET_STASH = LootPool.func_216096_a()
/* 115 */     .name("mineminenomi:tent_supplies")
/* 116 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))
/* 117 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 118 */       .func_216086_a(100)
/* 119 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 200.0F)))
/* 120 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 121 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 122 */       .func_216086_a(20)
/* 123 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 2000.0F))))
/* 124 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151045_i)
/* 125 */       .func_216086_a(70)
/* 126 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 7.0F))))
/* 127 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151166_bC)
/* 128 */       .func_216086_a(60)
/* 129 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 130 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151065_br)
/* 131 */       .func_216086_a(30)
/* 132 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 133 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_221696_bj)
/* 134 */       .func_216086_a(30)
/* 135 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 136 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.EISEN_DIAL.get())
/* 137 */       .func_216086_a(20)
/* 138 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 139 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.BREATH_DIAL.get())
/* 140 */       .func_216086_a(15)
/* 141 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 142 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.IMPACT_DIAL.get())
/* 143 */       .func_216086_a(15)
/* 144 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 145 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 146 */       .func_216086_a(45))
/* 147 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 148 */       .func_216086_a(25));
/*     */   
/* 150 */   private static final LootPool.Builder SECRET_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 151 */     .name("mineminenomi:randomized_fruits")
/* 152 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 153 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 154 */       .func_216086_a(19))
/* 155 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 156 */       .func_216086_a(4)
/* 157 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 158 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 159 */       .func_216086_a(6)
/* 160 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 161 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*     */   
/* 163 */   private static final LootPool.Builder FOOD_SUPPLIES = LootPool.func_216096_a()
/* 164 */     .name("mineminenomi:food_supplies")
/* 165 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))
/* 166 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/* 167 */       .func_216086_a(100)
/* 168 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 7.0F))))
/* 169 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151106_aX)
/* 170 */       .func_216086_a(100)
/* 171 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 12.0F))))
/* 172 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/* 173 */       .func_216086_a(100)
/* 174 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/* 175 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/* 176 */       .func_216086_a(50)
/* 177 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 178 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/* 179 */       .func_216086_a(100)
/* 180 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 7.0F))))
/* 181 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151168_bH)
/* 182 */       .func_216086_a(60)
/* 183 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 7.0F))))
/* 184 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 185 */       .func_216086_a(60)
/* 186 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/* 187 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179560_bq)
/* 188 */       .func_216086_a(60)
/* 189 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 190 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179557_bn)
/* 191 */       .func_216086_a(60)
/* 192 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 7.0F))))
/* 193 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/* 194 */       .func_216086_a(60)
/* 195 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/* 196 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222070_lD)
/* 197 */       .func_216086_a(30))
/* 198 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ULTRA_COLA.get())
/* 199 */       .func_216086_a(20)
/* 200 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 201 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SAKE_BOTTLE.get())
/* 202 */       .func_216086_a(20)
/* 203 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 204 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SEA_KING_MEAT.get())
/* 205 */       .func_216086_a(10));
/*     */   
/* 207 */   private static final LootPool.Builder DORM_SUPPLIES = LootPool.func_216096_a()
/* 208 */     .name("mineminenomi:dorm_supplies")
/* 209 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))
/* 210 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 211 */       .func_216086_a(100)
/* 212 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 70.0F)))
/* 213 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 214 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CIGARETTE.get())
/* 215 */       .func_216086_a(100)
/* 216 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F))))
/* 217 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DAGGER.get())
/* 218 */       .func_216086_a(80))
/* 219 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.AXE.get())
/* 220 */       .func_216086_a(80))
/* 221 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 222 */       .func_216086_a(80))
/* 223 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CLEAVER.get())
/* 224 */       .func_216086_a(60))
/* 225 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/* 226 */       .func_216086_a(60))
/* 227 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 228 */       .func_216086_a(80))
/* 229 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SENRIKU.get())
/* 230 */       .func_216086_a(60))
/* 231 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185159_cQ)
/* 232 */       .func_216086_a(60))
/* 233 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ROPE_NET.get())
/* 234 */       .func_216086_a(10))
/* 235 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151040_l)
/* 236 */       .func_216086_a(60))
/* 237 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222070_lD)
/* 238 */       .func_216086_a(20));
/*     */ 
/*     */   
/*     */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 242 */     LootTable.Builder tower = LootTable.func_216119_b().func_216040_a(TOWER_SUPPLIES);
/*     */ 
/*     */ 
/*     */     
/* 246 */     LootTable.Builder tent = LootTable.func_216119_b().func_216040_a(TENT_SUPPLIES).func_216040_a(TENT_RANDOMIZED_FRUITS);
/*     */ 
/*     */ 
/*     */     
/* 250 */     LootTable.Builder secret_stash = LootTable.func_216119_b().func_216040_a(SECRET_STASH).func_216040_a(SECRET_RANDOMIZED_FRUITS);
/*     */ 
/*     */     
/* 253 */     LootTable.Builder food = LootTable.func_216119_b().func_216040_a(FOOD_SUPPLIES);
/*     */ 
/*     */     
/* 256 */     LootTable.Builder dorm = LootTable.func_216119_b().func_216040_a(DORM_SUPPLIES);
/*     */     
/* 258 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 259 */         Pair.of("tower", tower), 
/* 260 */         Pair.of("tent", tent), 
/* 261 */         Pair.of("secret_stash", secret_stash), 
/* 262 */         Pair.of("food", food), 
/* 263 */         Pair.of("dorm", dorm)
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\bandit\BanditLargeBaseLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */