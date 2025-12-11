/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.item.Item;
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
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.FakeWeaponFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetInfiniteStockFunction;
/*     */ import xyz.pixelatedw.mineminenomi.data.functions.SetPriceFunction;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class TraderLootTablesDataGen extends LootTablesDataGen {
/*  30 */   private final Map<EntityType, LootTable.Builder> lootTables = new HashMap<>();
/*     */ 
/*     */   
/*  33 */   private static final LootPool.Builder INFINITE_AMMO_BUILDER = LootPool.func_216096_a()
/*  34 */     .name("mineminenomi:infinite_ammo")
/*  35 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 1.0F))
/*  36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/*  37 */       .func_216086_a(100)
/*  38 */       .func_212841_b_((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  39 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 20.0F))))
/*  40 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/*  41 */       .func_216086_a(50)
/*  42 */       .func_212841_b_((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  43 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(20.0F, 50.0F))))
/*  44 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151032_g)
/*  45 */       .func_216086_a(90)
/*  46 */       .func_212841_b_((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  47 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(3.0F, 8.0F))))
/*  48 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.POP_GREEN.get())
/*  49 */       .func_216086_a(50)
/*  50 */       .func_212841_b_((ILootFunction.IBuilder)SetInfiniteStockFunction.builder())
/*  51 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(15.0F, 20.0F))));
/*     */ 
/*     */   
/*  54 */   private static final LootPool.Builder FINITE_AMMO_BUILDER = LootPool.func_216096_a()
/*  55 */     .name("mineminenomi:finite_ammo")
/*  56 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/*  57 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/*  58 */       .func_216086_a(100)
/*  59 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 64.0F)))
/*  60 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(8.0F, 12.0F))))
/*  61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CANNON_BALL.get())
/*  62 */       .func_216086_a(70)
/*  63 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 30.0F)))
/*  64 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(20.0F, 30.0F))));
/*     */ 
/*     */   
/*  67 */   private static final LootPool.Builder GUNS_BUILDER = LootPool.func_216096_a()
/*  68 */     .name("mineminenomi:guns")
/*  69 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 1.0F))
/*  70 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/*  71 */       .func_216086_a(100)
/*  72 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F)))
/*  73 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 150.0F))))
/*  74 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.GINGA_PACHINKO.get())
/*  75 */       .func_216086_a(70)
/*  76 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(500.0F, 750.0F))))
/*  77 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151031_f)
/*  78 */       .func_216086_a(100)
/*  79 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/*  80 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(80.0F, 120.0F))))
/*  81 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SENRIKU.get())
/*  82 */       .func_216086_a(20)
/*  83 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/*  84 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 2000.0F))))
/*  85 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KABUTO.get())
/*  86 */       .func_216086_a(20)
/*  87 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(3000.0F, 4000.0F))))
/*  88 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BLACK_KABUTO.get())
/*  89 */       .func_216086_a(5)
/*  90 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(3000.0F, 5000.0F))))
/*  91 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.WALKER.get())
/*  92 */       .func_216086_a(20)
/*  93 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))));
/*     */ 
/*     */   
/*  96 */   private static final LootPool.Builder MELEE_WEAPONS_BUILDER = LootPool.func_216096_a()
/*  97 */     .name("mineminenomi:melee_weapons")
/*  98 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 2.0F))
/*  99 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/* 100 */       .func_216086_a(30)
/* 101 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/* 102 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(500.0F, 700.0F))))
/* 103 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.PIPE.get())
/* 104 */       .func_216086_a(30)
/* 105 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F)))
/* 106 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(300.0F, 500.0F))))
/* 107 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.TONFA.get())
/* 108 */       .func_216086_a(80)
/* 109 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/* 110 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(500.0F, 600.0F))))
/* 111 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.UMBRELLA.get())
/* 112 */       .func_216086_a(70)
/* 113 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(300.0F, 500.0F))))
/* 114 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.JITTE.get())
/* 115 */       .func_216086_a(70)
/* 116 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1500.0F, 3000.0F))))
/* 117 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT.get())
/* 118 */       .func_216086_a(70)
/* 119 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1500.0F, 3000.0F))))
/* 120 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KIKOKU.get())
/* 121 */       .func_216086_a(30)
/* 122 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 123 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(4000.0F, 5000.0F))))
/* 124 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KIRIBACHI.get())
/* 125 */       .func_216086_a(50)
/* 126 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 127 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(3000.0F, 5000.0F))))
/* 128 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MURAKUMOGIRI.get())
/* 129 */       .func_216086_a(30)
/* 130 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 131 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 6000.0F))))
/* 132 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.WADO_ICHIMONJI.get())
/* 133 */       .func_216086_a(30)
/* 134 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 135 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 6000.0F))))
/* 136 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SANDAI_KITETSU.get())
/* 137 */       .func_216086_a(30)
/* 138 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 139 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 6000.0F))))
/* 140 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.NIDAI_KITETSU.get())
/* 141 */       .func_216086_a(30)
/* 142 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 143 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 6000.0F))))
/* 144 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.WADO_ICHIMONJI.get())
/* 145 */       .func_216086_a(30)
/* 146 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 147 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 6000.0F))))
/* 148 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.YORU.get())
/* 149 */       .func_216086_a(10)
/* 150 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 151 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(6000.0F, 8000.0F))))
/* 152 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.ENMA.get())
/* 153 */       .func_216086_a(5)
/* 154 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 155 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(6000.0F, 7000.0F))))
/* 156 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.AME_NO_HABAKIRI.get())
/* 157 */       .func_216086_a(10)
/* 158 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 159 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(6000.0F, 7000.0F))))
/* 160 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SHUSUI.get())
/* 161 */       .func_216086_a(10)
/* 162 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 163 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 7000.0F))))
/* 164 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DURANDAL.get())
/* 165 */       .func_216086_a(50)
/* 166 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 167 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(3000.0F, 4000.0F))))
/* 168 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SOUL_SOLID.get())
/* 169 */       .func_216086_a(50)
/* 170 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 171 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 3500.0F))))
/* 172 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.ACE.get())
/* 173 */       .func_216086_a(1)
/* 174 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 175 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(7000.0F, 10000.0F))))
/* 176 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.HASSAIKAI.get())
/* 177 */       .func_216086_a(1)
/* 178 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 179 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(7000.0F, 10000.0F))))
/* 180 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.GRYPHON.get())
/* 181 */       .func_216086_a(1)
/* 182 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 183 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(7000.0F, 10000.0F))))
/* 184 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MIHAWKS_KNIFE.get())
/* 185 */       .func_216086_a(1)
/* 186 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 187 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 5000.0F))))
/* 188 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MOGURA.get())
/* 189 */       .func_216086_a(30)
/* 190 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 191 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(5000.0F, 8000.0F))))
/* 192 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DALTONS_SPADE.get())
/* 193 */       .func_216086_a(50)
/* 194 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 195 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))))
/* 196 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SAMEKIRI_BOCHO.get())
/* 197 */       .func_216086_a(40)
/* 198 */       .func_212841_b_((ILootFunction.IBuilder)FakeWeaponFunction.builder())
/* 199 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))))
/* 200 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BLUE_SWORD.get())
/* 201 */       .func_216086_a(60)
/* 202 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 7000.0F))));
/*     */ 
/*     */   
/* 205 */   private static final LootPool.Builder MATERIALS_BUILDER = LootPool.func_216096_a()
/* 206 */     .name("mineminenomi:materials")
/* 207 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 208 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_196128_bn)
/* 209 */       .func_216086_a(100)
/* 210 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 15.0F)))
/* 211 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(20.0F, 50.0F))))
/* 212 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151042_j)
/* 213 */       .func_216086_a(100)
/* 214 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 40.0F)))
/* 215 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(30.0F, 70.0F))))
/* 216 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151065_br)
/* 217 */       .func_216086_a(80)
/* 218 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 10.0F)))
/* 219 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 100.0F))))
/* 220 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SEA_KING_MEAT.get())
/* 221 */       .func_216086_a(10)
/* 222 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 2.0F)))
/* 223 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1500.0F, 3000.0F))))
/* 224 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SAKE_BOTTLE.get())
/* 225 */       .func_216086_a(40)
/* 226 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F)))
/* 227 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(200.0F, 500.0F))))
/* 228 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 229 */       .func_216086_a(60)
/* 230 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 20.0F)))
/* 231 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 300.0F))))
/* 232 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222070_lD)
/* 233 */       .func_216086_a(60)
/* 234 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(400.0F, 600.0F))))
/* 235 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.THREE_CIGARS.get())
/* 236 */       .func_216086_a(50)
/* 237 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 100.0F))))
/* 238 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SMOKING_PIPE.get())
/* 239 */       .func_216086_a(20)
/* 240 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(300.0F, 400.0F))))
/* 241 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CIGAR.get())
/* 242 */       .func_216086_a(30)
/* 243 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(150.0F, 300.0F))))
/* 244 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BUBBLY_CORAL.get())
/* 245 */       .func_216086_a(10)
/* 246 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 2000.0F))))
/* 247 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLOR_PALETTE.get())
/* 248 */       .func_216086_a(20)
/* 249 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))))
/* 250 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.WATERING_CAN.get())
/* 251 */       .func_216086_a(20)
/* 252 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))));
/*     */ 
/*     */   
/* 255 */   private static final LootPool.Builder BELLY_DIALS_BUILDER = LootPool.func_216096_a()
/* 256 */     .name("mineminenomi:belly_dials")
/* 257 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 3.0F))
/* 258 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.EISEN_DIAL.get()).func_199767_j())
/* 259 */       .func_216086_a(100)
/* 260 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F)))
/* 261 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(500.0F, 700.0F))))
/* 262 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.FLAME_DIAL.get()).func_199767_j())
/* 263 */       .func_216086_a(80)
/* 264 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F)))
/* 265 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(700.0F, 1200.0F))))
/* 266 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.MILKY_DIAL.get()).func_199767_j())
/* 267 */       .func_216086_a(20)
/* 268 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F)))
/* 269 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 3000.0F))))
/* 270 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.FLASH_DIAL.get()).func_199767_j())
/* 271 */       .func_216086_a(30)
/* 272 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F)))
/* 273 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 1500.0F))))
/* 274 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j())
/* 275 */       .func_216086_a(30)
/* 276 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F)))
/* 277 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 1500.0F))))
/* 278 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j())
/* 279 */       .func_216086_a(10)
/* 280 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/* 281 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 3000.0F))))
/* 282 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.AXE_DIAL.get()).func_199767_j())
/* 283 */       .func_216086_a(10)
/* 284 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/* 285 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 3000.0F))));
/*     */ 
/*     */   
/* 288 */   private static final LootPool.Builder EXTOL_DIALS_BUILDER = LootPool.func_216096_a()
/* 289 */     .name("mineminenomi:extol_dials")
/* 290 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))
/* 291 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.EISEN_DIAL.get()).func_199767_j())
/* 292 */       .func_216086_a(100)
/* 293 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 15.0F)))
/* 294 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(700000.0F, 1000000.0F))))
/* 295 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.FLAME_DIAL.get()).func_199767_j())
/* 296 */       .func_216086_a(90)
/* 297 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 15.0F)))
/* 298 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1500000.0F, 2000000.0F))))
/* 299 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.MILKY_DIAL.get()).func_199767_j())
/* 300 */       .func_216086_a(40)
/* 301 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 10.0F)))
/* 302 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000000.0F, 3000000.0F))))
/* 303 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.FLASH_DIAL.get()).func_199767_j())
/* 304 */       .func_216086_a(40)
/* 305 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 15.0F)))
/* 306 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000000.0F, 1500000.0F))))
/* 307 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j())
/* 308 */       .func_216086_a(50)
/* 309 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F)))
/* 310 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000000.0F, 2500000.0F))))
/* 311 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j())
/* 312 */       .func_216086_a(30)
/* 313 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F)))
/* 314 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(3000000.0F, 4000000.0F))))
/* 315 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.AXE_DIAL.get()).func_199767_j())
/* 316 */       .func_216086_a(80)
/* 317 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F)))
/* 318 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000000.0F, 2500000.0F))))
/* 319 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)((Block)ModBlocks.REJECT_DIAL.get()).func_199767_j())
/* 320 */       .func_216086_a(1)
/* 321 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1.0E8F, 2.0E8F))));
/*     */ 
/*     */   
/* 324 */   private static final LootPool.Builder GENERIC_EQUIPMENT_BUILDER = LootPool.func_216096_a()
/* 325 */     .name("mineminenomi:generic_equipment")
/* 326 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 2.0F))
/* 327 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.BANDANA.get())
/* 328 */       .func_216086_a(100)
/* 329 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 500.0F))))
/* 330 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.STRAW_HAT.get())
/* 331 */       .func_216086_a(100)
/* 332 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(100.0F, 500.0F))))
/* 333 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.SNIPER_GOGGLES.get())
/* 334 */       .func_216086_a(80)
/* 335 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(4000.0F, 5000.0F))))
/* 336 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.MEDIC_BAG.get())
/* 337 */       .func_216086_a(80)
/* 338 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))))
/* 339 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.COLA_BACKPACK.get())
/* 340 */       .func_216086_a(70)
/* 341 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(4000.0F, 6000.0F))))
/* 342 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLOR_PALETTE.get())
/* 343 */       .func_216086_a(70)
/* 344 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))))
/* 345 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.WATERING_CAN.get())
/* 346 */       .func_216086_a(70)
/* 347 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(2000.0F, 4000.0F))))
/* 348 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.TOMOE_DRUMS.get())
/* 349 */       .func_216086_a(5)
/* 350 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(25000.0F, 50000.0F))))
/* 351 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151141_av)
/* 352 */       .func_216086_a(80)
/* 353 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/* 354 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(250.0F, 400.0F))));
/*     */ 
/*     */   
/* 357 */   private static final LootPool.Builder MARINE_CLOTHES_BUILDER = LootPool.func_216096_a()
/* 358 */     .name("mineminenomi:marine_clothes")
/* 359 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 5.0F))
/* 360 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.MARINE_HEAD.get()))
/* 361 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.MARINE_CHEST.get()))
/* 362 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.MARINE_FEET.get()))
/* 363 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.MARINE_LEGS.get()))
/* 364 */     .func_216045_a(setupClothingPrice(ClothesTier.CAPE, (Item)ModArmors.MARINE_CAPTAIN_CAPE.get()))
/* 365 */     .func_216045_a(setupClothingPrice(ClothesTier.RARE, (Item)ModArmors.VICE_ADMIRAL_CHEST.get()))
/* 366 */     .func_216045_a(setupClothingPrice(ClothesTier.RARE, (Item)ModArmors.VICE_ADMIRAL_FEET.get()))
/* 367 */     .func_216045_a(setupClothingPrice(ClothesTier.RARE, (Item)ModArmors.VICE_ADMIRAL_LEGS.get()))
/* 368 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.KIZARU_GLASSES.get()))
/* 369 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SMOKER_CHEST.get()))
/* 370 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SMOKER_FEET.get()))
/* 371 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SMOKER_LEGS.get()))
/* 372 */     .func_216045_a(setupClothingPrice(ClothesTier.RARE, (Item)ModArmors.CP9_CHEST.get()))
/* 373 */     .func_216045_a(setupClothingPrice(ClothesTier.RARE, (Item)ModArmors.CP9_FEET.get()))
/* 374 */     .func_216045_a(setupClothingPrice(ClothesTier.RARE, (Item)ModArmors.CP9_LEGS.get()));
/*     */ 
/*     */   
/* 377 */   private static final LootPool.Builder PIRATE_CLOTHES_BUILDER = LootPool.func_216096_a()
/* 378 */     .name("mineminenomi:pirate_clothes")
/* 379 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 5.0F))
/* 380 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.PIRATE_CHEST.get()))
/* 381 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.PIRATE_FEET.get()))
/* 382 */     .func_216045_a(setupClothingPrice(ClothesTier.TRASH, (Item)ModArmors.PIRATE_LEGS.get()))
/* 383 */     .func_216045_a(setupClothingPrice(ClothesTier.CAPE, (Item)ModArmors.PIRATE_CAPTAIN_CAPE.get()))
/* 384 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.LUFFY_CHEST.get()))
/* 385 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.LUFFY_FEET.get()))
/* 386 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.LUFFY_LEGS.get()))
/* 387 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.ZORO_CHEST.get()))
/* 388 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.ZORO_FEET.get()))
/* 389 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.ZORO_LEGS.get()))
/* 390 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SANJI_CHEST.get()))
/* 391 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SANJI_FEET.get()))
/* 392 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SANJI_LEGS.get()))
/* 393 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.FRANKY_CHEST.get()))
/* 394 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.FRANKY_GLASSES.get()))
/* 395 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SENOR_PINK_CHEST.get()))
/* 396 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.SENOR_PINK_HEAD.get()))
/* 397 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.USOPP_CHEST.get()))
/* 398 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.USOPP_FEET.get()))
/* 399 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.USOPP_LEGS.get()))
/* 400 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.KUMA_CHEST.get()))
/* 401 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.KUMA_FEET.get()))
/* 402 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.KUMA_LEGS.get()))
/* 403 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.ACES_HAT.get()))
/* 404 */     .func_216045_a(setupClothingPrice(ClothesTier.NAMED, (Item)ModArmors.CHOPPERS_HAT.get()));
/*     */   
/*     */   public TraderLootTablesDataGen(DataGenerator dataGenerator) {
/* 407 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_200398_a(DirectoryCache cache) {
/* 412 */     addLootTable((EntityType)ModEntities.MARINE_TRADER.get(), new LootPool.Builder[] { INFINITE_AMMO_BUILDER, FINITE_AMMO_BUILDER, GUNS_BUILDER, MELEE_WEAPONS_BUILDER, MATERIALS_BUILDER, BELLY_DIALS_BUILDER, GENERIC_EQUIPMENT_BUILDER, MARINE_CLOTHES_BUILDER });
/* 413 */     addLootTable((EntityType)ModEntities.PIRATE_TRADER.get(), new LootPool.Builder[] { INFINITE_AMMO_BUILDER, FINITE_AMMO_BUILDER, GUNS_BUILDER, MELEE_WEAPONS_BUILDER, MATERIALS_BUILDER, BELLY_DIALS_BUILDER, GENERIC_EQUIPMENT_BUILDER, PIRATE_CLOTHES_BUILDER });
/* 414 */     addLootTable((EntityType)ModEntities.SKYPIEAN_TRADER.get(), new LootPool.Builder[] { EXTOL_DIALS_BUILDER });
/*     */     
/* 416 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/* 417 */     this.lootTables.forEach((entityType, builder) -> {
/*     */           ResourceLocation resourcelocation = ForgeRegistries.ENTITIES.getKey((IForgeRegistryEntry)entityType);
/*     */           
/*     */           ResourceLocation traderRes = new ResourceLocation(resourcelocation.func_110624_b(), "entities/trader/" + resourcelocation.func_110623_a());
/*     */           tables.put(traderRes, builder.func_216038_b());
/*     */         });
/* 423 */     writeTables(cache, tables);
/*     */   }
/*     */   
/*     */   private static LootEntry.Builder<?> setupClothingPrice(ClothesTier tier, Item item) {
/* 427 */     return (LootEntry.Builder<?>)ItemLootEntry.func_216168_a((IItemProvider)item)
/* 428 */       .func_216086_a(tier.getWeight())
/* 429 */       .func_212841_b_((ILootFunction.IBuilder)SetPriceFunction.builder((IRandomRange)RandomValueRange.func_215837_a(tier.getMinPrice(), tier.getMaxPrice())));
/*     */   }
/*     */   
/*     */   protected void addLootTable(EntityType entityType, LootPool.Builder... pools) {
/* 433 */     LootTable.Builder builder = LootTable.func_216119_b();
/* 434 */     for (LootPool.Builder pool : pools) {
/* 435 */       builder.func_216040_a(pool);
/*     */     }
/* 437 */     this.lootTables.put(entityType, builder);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_200397_b() {
/* 442 */     return "Traders Loot Pools";
/*     */   }
/*     */   
/*     */   private enum ClothesTier {
/* 446 */     TRASH(100, 50.0F, 100.0F),
/* 447 */     NAMED(30, 2000.0F, 3000.0F),
/* 448 */     CAPE(50, 4000.0F, 5000.0F),
/* 449 */     RARE(20, 5000.0F, 7000.0F);
/*     */     
/*     */     private int weight;
/*     */     private float minPrice;
/*     */     private float maxPrice;
/*     */     
/*     */     ClothesTier(int weight, float minPrice, float maxPrice) {
/* 456 */       this.weight = weight;
/* 457 */       this.minPrice = minPrice;
/* 458 */       this.maxPrice = maxPrice;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getWeight() {
/* 463 */       return this.weight;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getMinPrice() {
/* 468 */       return this.minPrice;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getMaxPrice() {
/* 473 */       return this.maxPrice;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\TraderLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */