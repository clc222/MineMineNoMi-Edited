/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.rewards;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.EmptyLootEntry;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class GinHardReward {
/* 13 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 14 */     .name("mineminenomi:items")
/* 15 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 16 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 17 */       .func_216086_a(95))
/* 18 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.TONFA.get())
/* 19 */       .func_216086_a(5));
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 23 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(ITEMS);
/*    */     
/* 25 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 26 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\GinHardReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */