/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla.village;
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
/*    */ 
/*    */ public class VillageButcherLootTable {
/* 14 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 15 */     .name("mineminenomi:items")
/* 16 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 17 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 18 */       .func_216086_a(30))
/* 19 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TANGERINE.get())
/* 20 */       .func_216086_a(10)
/* 21 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(3.0F, 5.0F))))
/* 22 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.COLA.get())
/* 23 */       .func_216086_a(10)
/* 24 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.SEA_KING_MEAT.get())
/* 26 */       .func_216086_a(1))
/* 27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DAGGER.get())
/* 28 */       .func_216086_a(20));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 31 */     return LootTable.func_216119_b()
/* 32 */       .func_216040_a(ITEMS);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\village\VillageButcherLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */