/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class AbandonedMineshaftLootTable {
/* 13 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 14 */     .name("mineminenomi:items")
/* 15 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 16 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 17 */       .func_216086_a(20))
/* 18 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get())
/* 19 */       .func_216086_a(5))
/* 20 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_HANDCUFFS.get())
/* 21 */       .func_216086_a(3))
/* 22 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.DAGGER.get())
/* 23 */       .func_216086_a(5))
/* 24 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KEY.get())
/* 25 */       .func_216086_a(5));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 28 */     return LootTable.func_216119_b()
/* 29 */       .func_216040_a(ITEMS);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\AbandonedMineshaftLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */