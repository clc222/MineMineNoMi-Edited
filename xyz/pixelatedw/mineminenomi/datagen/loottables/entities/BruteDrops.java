/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.EmptyLootEntry;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.conditions.KilledByPlayer;
/*    */ import net.minecraft.loot.conditions.RandomChanceWithLooting;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class BruteDrops {
/* 18 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 19 */     .name("mineminenomi:items")
/* 20 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 21 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 22 */       .func_216086_a(100))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 24 */       .func_216086_a(100)
/* 25 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1)))
/* 26 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 50.0F))))
/* 27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.CANNON_BALL.get())
/* 28 */       .func_216086_a(80)
/* 29 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F)))
/* 30 */       .func_212841_b_((ILootFunction.IBuilder)LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 2.0F))));
/*    */   
/* 32 */   private static final LootPool.Builder ITEMS2 = LootPool.func_216096_a()
/* 33 */     .name("mineminenomi:items2")
/* 34 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 35 */     .func_212840_b_(KilledByPlayer.func_215994_b())
/* 36 */     .func_212840_b_(RandomChanceWithLooting.func_216003_a(0.1F, 0.015F))
/* 37 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 38 */       .func_216086_a(100)
/* 39 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1))));
/*    */ 
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 44 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS).func_216040_a(ITEMS2);
/*    */     
/* 46 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\BruteDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */