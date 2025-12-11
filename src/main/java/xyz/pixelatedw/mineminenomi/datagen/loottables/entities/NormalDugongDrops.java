/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ 
/*    */ public class NormalDugongDrops {
/* 15 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 16 */     .name("mineminenomi:items")
/* 17 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 18 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_221600_aB)
/* 19 */       .func_216086_a(100)
/* 20 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 5.0F))))
/* 21 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_222066_kO)
/* 22 */       .func_216086_a(100)
/* 23 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 5.0F))))
/* 24 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_205157_eZ)
/* 25 */       .func_216086_a(86));
/*    */   
/* 27 */   private static final LootPool.Builder ITEMS2 = LootPool.func_216096_a()
/* 28 */     .name("mineminenomi:items2")
/* 29 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 30 */     .func_212840_b_(KilledByPlayer.func_215994_b())
/* 31 */     .func_212840_b_(RandomChanceWithLooting.func_216003_a(0.4F, 0.04F))
/* 32 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_203183_eM)
/* 33 */       .func_216086_a(100)
/* 34 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*    */ 
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 39 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS).func_216040_a(ITEMS2);
/*    */     
/* 41 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\NormalDugongDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */