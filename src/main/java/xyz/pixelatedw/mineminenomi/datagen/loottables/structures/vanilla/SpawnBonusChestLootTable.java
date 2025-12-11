/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class SpawnBonusChestLootTable {
/* 14 */   private static final LootPool.Builder BELLY_BONUS = LootPool.func_216096_a()
/* 15 */     .name("mineminenomi:belly_bonus")
/* 16 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 17 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 18 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)ConstantRange.func_215835_a(1000))));
/*    */   
/* 20 */   private static final LootPool.Builder KEY_BONUS = LootPool.func_216096_a()
/* 21 */     .name("mineminenomi:key_bonus")
/* 22 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KEY.get())
/* 24 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 27 */     return LootTable.func_216119_b()
/* 28 */       .func_216040_a(BELLY_BONUS)
/* 29 */       .func_216040_a(KEY_BONUS);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\SpawnBonusChestLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */