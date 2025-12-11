/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class WoodlandMansionLootTable {
/* 15 */   private static final LootPool.Builder TREASURE = LootPool.func_216096_a()
/* 16 */     .name("mineminenomi:treasure")
/* 17 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 18 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 19 */       .func_216086_a(20))
/* 20 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 21 */       .func_216086_a(10)
/* 22 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(300.0F, 500.0F))))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 24 */       .func_216086_a(10))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 26 */       .func_216086_a(5))
/* 27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_3_BOX.get())
/* 28 */       .func_216086_a(2));
/*    */   
/* 30 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 31 */     .name("mineminenomi:items")
/* 32 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 34 */       .func_216086_a(20))
/* 35 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BROADSWORD.get())
/* 36 */       .func_216086_a(20))
/* 37 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CUTLASS.get())
/* 38 */       .func_216086_a(20))
/* 39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 40 */       .func_216086_a(20))
/* 41 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ULTRA_COLA.get())
/* 42 */       .func_216086_a(10))
/* 43 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 44 */       .func_216086_a(10));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 47 */     return LootTable.func_216119_b()
/* 48 */       .func_216040_a(ITEMS)
/* 49 */       .func_216040_a(TREASURE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\WoodlandMansionLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */