/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetExtolInPouchFunction;
/*    */ 
/*    */ public class SkypieanDrops {
/* 13 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 14 */     .name("mineminenomi:items")
/* 15 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 1.0F))
/* 16 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.EXTOL_POUCH.get())
/* 17 */       .func_216086_a(100)
/* 18 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 1.0F)))
/* 19 */       .func_212841_b_((ILootFunction.IBuilder)SetExtolInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10000.0F, 50000.0F))));
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 23 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS);
/*    */     
/* 25 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\SkypieanDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */