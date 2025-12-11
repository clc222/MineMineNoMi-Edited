/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.data.conditions.RandomizedFruitsCondition;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.EncyclopediaCompletionFunction;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetFruitClueFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class GhostShipLootTables {
/* 21 */   private static final LootPool.Builder SUPPLIES_LEFTOVERS = LootPool.func_216096_a()
/* 22 */     .name("mineminenomi:leftovers")
/* 23 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 5.0F))
/* 24 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CUTLASS.get())
/* 25 */       .func_216086_a(100)
/* 26 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 28 */       .func_216086_a(20)
/* 29 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.AXE.get())
/* 31 */       .func_216086_a(20)
/* 32 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 34 */       .func_216086_a(20))
/* 35 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 36 */       .func_216086_a(70)
/* 37 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 38 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/* 39 */       .func_216086_a(70)
/* 40 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 41 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222115_pz)
/* 42 */       .func_216086_a(100)
/* 43 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))));
/*    */   
/* 45 */   private static final LootPool.Builder CAPTAIN_CHEST = LootPool.func_216096_a()
/* 46 */     .name("mineminenomi:captain_chest")
/* 47 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 3.0F))
/* 48 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222115_pz)
/* 49 */       .func_216086_a(100)
/* 50 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 51 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CUTLASS.get())
/* 52 */       .func_216086_a(20))
/* 53 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 54 */       .func_216086_a(20))
/* 55 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.AXE.get())
/* 56 */       .func_216086_a(20))
/* 57 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get())
/* 58 */       .func_216086_a(18))
/* 59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 60 */       .func_216086_a(3))
/* 61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 62 */       .func_216086_a(80)
/* 63 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))));
/*    */   
/* 65 */   private static final LootPool.Builder CAPTAIN_RANDOMIZED_FRUITS = LootPool.func_216096_a()
/* 66 */     .name("mineminenomi:randomized_fruits")
/* 67 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 68 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 69 */       .func_216086_a(10))
/* 70 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151121_aF)
/* 71 */       .func_216086_a(3)
/* 72 */       .func_212841_b_((ILootFunction.IBuilder)SetFruitClueFunction.builder()))
/* 73 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())
/* 74 */       .func_216086_a(1)
/* 75 */       .func_212841_b_((ILootFunction.IBuilder)EncyclopediaCompletionFunction.builder()))
/* 76 */     .func_212840_b_(RandomizedFruitsCondition.builder());
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 80 */     LootTable.Builder supplies = LootTable.func_216119_b().func_216040_a(SUPPLIES_LEFTOVERS);
/*    */ 
/*    */ 
/*    */     
/* 84 */     LootTable.Builder captain = LootTable.func_216119_b().func_216040_a(CAPTAIN_CHEST).func_216040_a(CAPTAIN_RANDOMIZED_FRUITS);
/*    */     
/* 86 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 87 */         Pair.of("supplies", supplies), 
/* 88 */         Pair.of("captain", captain)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\GhostShipLootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */