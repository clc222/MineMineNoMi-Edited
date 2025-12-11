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
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.SetBellyInPouchFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class BuriedTreasureLootTable {
/* 15 */   private static final LootPool.Builder TREASURE = LootPool.func_216096_a()
/* 16 */     .name("mineminenomi:treasure")
/* 17 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 18 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 19 */       .func_216086_a(5))
/* 20 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 21 */       .func_216086_a(10)
/* 22 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 1250.0F))))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BELLY_POUCH.get())
/* 24 */       .func_216086_a(3)
/* 25 */       .func_212841_b_((ILootFunction.IBuilder)SetBellyInPouchFunction.builder((IRandomRange)RandomValueRange.func_215837_a(1000.0F, 2000.0F))))
/* 26 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BUBBLY_CORAL.get())
/* 27 */       .func_216086_a(3))
/* 28 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 29 */       .func_216086_a(6)
/* 30 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 3.0F))))
/* 31 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.TIER_1_BOX.get())
/* 32 */       .func_216086_a(2));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 35 */     return LootTable.func_216119_b()
/* 36 */       .func_216040_a(TREASURE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\BuriedTreasureLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */