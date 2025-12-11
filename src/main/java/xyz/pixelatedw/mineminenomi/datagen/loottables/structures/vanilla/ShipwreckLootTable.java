/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class ShipwreckLootTable {
/* 15 */   private static final LootPool.Builder TREASURE = LootPool.func_216096_a()
/* 16 */     .name("mineminenomi:treasure")
/* 17 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 1.0F))
/* 18 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 19 */       .func_216086_a(200))
/* 20 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 21 */       .func_216086_a(100)
/* 22 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 50.0F))))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 24 */       .func_216086_a(6))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 26 */       .func_216086_a(3));
/*    */   
/*    */   public static LootTable.Builder getTreasurePool() {
/* 29 */     return LootTable.func_216119_b()
/* 30 */       .func_216040_a(TREASURE);
/*    */   }
/*    */   
/* 33 */   private static final LootPool.Builder SUPPLY = LootPool.func_216096_a()
/* 34 */     .name("mineminenomi:weapons")
/* 35 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 37 */       .func_216086_a(200))
/* 38 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BROADSWORD.get())
/* 39 */       .func_216086_a(40))
/* 40 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 41 */       .func_216086_a(40))
/* 42 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DAGGER.get())
/* 43 */       .func_216086_a(40))
/* 44 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CUTLASS.get())
/* 45 */       .func_216086_a(40))
/* 46 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.SPEAR.get())
/* 47 */       .func_216086_a(40));
/*    */   
/* 49 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 50 */     .name("mineminenomi:items")
/* 51 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 52 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 53 */       .func_216086_a(50))
/* 54 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 55 */       .func_216086_a(40)
/* 56 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 10.0F))))
/* 57 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KEY.get())
/* 58 */       .func_216086_a(20))
/* 59 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/* 60 */       .func_216086_a(20)
/* 61 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 8.0F))))
/* 62 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 63 */       .func_216086_a(30)
/* 64 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 65 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CANNON_BALL.get())
/* 66 */       .func_216086_a(40)
/* 67 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 7.0F))));
/*    */   
/*    */   public static LootTable.Builder getSupplyPool() {
/* 70 */     return LootTable.func_216119_b()
/* 71 */       .func_216040_a(SUPPLY)
/* 72 */       .func_216040_a(ITEMS);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\ShipwreckLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */