/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.loot.ConstantRange;
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
/*    */ 
/*    */ public class DenDenMushiDrops {
/* 16 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 17 */     .name("mineminenomi:items")
/* 18 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 19 */     .func_212840_b_(KilledByPlayer.func_215994_b())
/* 20 */     .func_212840_b_(RandomChanceWithLooting.func_216003_a(0.2F, 0.015F))
/* 21 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_205157_eZ)
/* 22 */       .func_216086_a(100)
/* 23 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)ConstantRange.func_215835_a(1)))
/* 24 */       .func_212841_b_((ILootFunction.IBuilder)LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 1.0F))));
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 28 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS);
/*    */     
/* 30 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\DenDenMushiDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */