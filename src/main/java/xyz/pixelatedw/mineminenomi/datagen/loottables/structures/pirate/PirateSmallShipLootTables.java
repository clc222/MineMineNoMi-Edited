/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.pirate;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class PirateSmallShipLootTables {
/* 20 */   private static final LootPool.Builder SUPPLIES_WEAPONS = LootPool.func_216096_a()
/* 21 */     .name("mineminenomi:weapons")
/* 22 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CUTLASS.get())
/* 24 */       .func_216086_a(90))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 26 */       .func_216086_a(90))
/* 27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.AXE.get())
/* 28 */       .func_216086_a(90))
/* 29 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.PIPE.get())
/* 30 */       .func_216086_a(70))
/* 31 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/* 32 */       .func_216086_a(30))
/* 33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 34 */       .func_216086_a(100)
/* 35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151016_H)
/* 37 */       .func_216086_a(70)
/* 38 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))))
/* 39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.FLINTLOCK.get())
/* 40 */       .func_216086_a(70));
/*    */ 
/*    */   
/* 43 */   private static final LootPool.Builder SUPPLIES_FOOD = LootPool.func_216096_a()
/* 44 */     .name("mineminenomi:food")
/* 45 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))
/* 46 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151025_P)
/* 47 */       .func_216086_a(100)
/* 48 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/* 49 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151174_bG)
/* 50 */       .func_216086_a(80)
/* 51 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 52 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151034_e)
/* 53 */       .func_216086_a(90)
/* 54 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 6.0F))))
/* 55 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_203180_bP)
/* 56 */       .func_216086_a(50)
/* 57 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(10.0F, 20.0F))))
/* 58 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151009_A)
/* 59 */       .func_216086_a(50)
/* 60 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 6.0F))))
/* 61 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_185165_cW)
/* 62 */       .func_216086_a(50)
/* 63 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F))))
/* 64 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/* 65 */       .func_216086_a(20)
/* 66 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))));
/*    */ 
/*    */   
/* 69 */   private static final LootPool.Builder SUPPLIES_TREASURE = LootPool.func_216096_a()
/* 70 */     .name("mineminenomi:treasure")
/* 71 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 2.0F))
/* 72 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 73 */       .func_216086_a(100)
/* 74 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 50.0F))))
/* 75 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 76 */       .func_216086_a(30)
/* 77 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 100.0F))))
/* 78 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModBlocks.WANTED_POSTER_PACKAGE.get())
/* 79 */       .func_216086_a(10));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 86 */     LootTable.Builder supplies = LootTable.func_216119_b().func_216040_a(SUPPLIES_WEAPONS).func_216040_a(SUPPLIES_FOOD).func_216040_a(SUPPLIES_TREASURE);
/*    */     
/* 88 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 89 */         Pair.of("supplies", supplies)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\pirate\PirateSmallShipLootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */