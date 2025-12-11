/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.LootingEnchantBonus;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class SniperDrops {
/* 16 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 17 */     .name("mineminenomi:items")
/* 18 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 19 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 20 */       .func_216086_a(100))
/* 21 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 22 */       .func_216086_a(100)
/* 23 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1)))
/* 24 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(10.0F, 50.0F))))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 26 */       .func_216086_a(80)
/* 27 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 6.0F)))
/* 28 */       .func_212841_b_((ILootFunction.IBuilder)LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 4.0F))))
/* 29 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 30 */       .func_216086_a(40)
/* 31 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F)))
/* 32 */       .func_212841_b_((ILootFunction.IBuilder)LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 2.0F))));
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 36 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS);
/*    */     
/* 38 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\SniperDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */