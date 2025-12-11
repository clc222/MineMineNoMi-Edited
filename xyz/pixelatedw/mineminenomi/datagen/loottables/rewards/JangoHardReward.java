/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.rewards;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.EmptyLootEntry;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ 
/*    */ public class JangoHardReward {
/* 15 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 16 */     .name("mineminenomi:items")
/* 17 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 18 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 19 */       .func_216086_a(91))
/* 20 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CHAKRAM.get())
/* 21 */       .func_216086_a(5))
/* 22 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.WIDE_BRIM_HAT.get())
/* 23 */       .func_212841_b_((ILootFunction.IBuilder)SetNBT.func_215952_a(JangoReward.HAT_NBT))
/* 24 */       .func_216086_a(2))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.HEART_GLASSES.get())
/* 26 */       .func_216086_a(2));
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 30 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(ITEMS);
/*    */     
/* 32 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 33 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\JangoHardReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */