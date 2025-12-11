/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.EmptyLootEntry;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.conditions.KilledByPlayer;
/*    */ import net.minecraft.loot.conditions.RandomChanceWithLooting;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class CaptainDrops {
/* 17 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 18 */     .name("mineminenomi:items")
/* 19 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 20 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 21 */       .func_216086_a(100))
/* 22 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 23 */       .func_216086_a(100)
/* 24 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1)))
/* 25 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(50.0F, 100.0F))));
/*    */   
/* 27 */   private static final LootPool.Builder RARE_ITEMS = LootPool.func_216096_a()
/* 28 */     .name("mineminenomi:items2")
/* 29 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 30 */     .func_212840_b_(KilledByPlayer.func_215994_b())
/* 31 */     .func_212840_b_(RandomChanceWithLooting.func_216003_a(0.1F, 0.02F))
/* 32 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 33 */       .func_216086_a(100)
/* 34 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1))))
/* 35 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CIGAR.get())
/* 36 */       .func_216086_a(43)
/* 37 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1))));
/*    */   
/* 39 */   private static final LootPool.Builder KEY = LootPool.func_216096_a()
/* 40 */     .name("mineminenomi:key")
/* 41 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 42 */     .func_212840_b_(KilledByPlayer.func_215994_b())
/* 43 */     .func_212840_b_(RandomChanceWithLooting.func_216003_a(0.5F, 0.1F))
/* 44 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KEY.get())
/* 45 */       .func_216086_a(100)
/* 46 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1))));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 52 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS).func_216040_a(RARE_ITEMS).func_216040_a(KEY);
/*    */     
/* 54 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\CaptainDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */