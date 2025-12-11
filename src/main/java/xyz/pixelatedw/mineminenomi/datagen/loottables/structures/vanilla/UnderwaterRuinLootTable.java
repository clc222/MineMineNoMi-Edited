/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class UnderwaterRuinLootTable {
/* 12 */   private static final LootPool.Builder TREASURE = LootPool.func_216096_a()
/* 13 */     .name("mineminenomi:items")
/* 14 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 1.0F))
/* 15 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 16 */       .func_216086_a(384))
/* 17 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 18 */       .func_216086_a(2))
/* 19 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_2_BOX.get())
/* 20 */       .func_216086_a(1));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 23 */     return LootTable.func_216119_b()
/* 24 */       .func_216040_a(TREASURE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\UnderwaterRuinLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */