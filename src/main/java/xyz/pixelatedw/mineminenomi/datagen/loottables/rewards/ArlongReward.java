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
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.UnlockChallengesFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ 
/*    */ public class ArlongReward {
/* 18 */   private static final LootPool.Builder HARD_MODE = LootPool.func_216096_a()
/* 19 */     .name("mineminenomi:hard_mode")
/* 20 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 21 */     .func_212840_b_(FirstCompletionRewardCondition.builder())
/* 22 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_190931_a)
/* 23 */       .func_212841_b_((ILootFunction.IBuilder)UnlockChallengesFunction.builder(new ChallengeCore[] { (ChallengeCore)ModChallenges.ARLONG_HARD.get() })));
/*    */   
/* 25 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 26 */     .name("mineminenomi:items")
/* 27 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 28 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 29 */       .func_216086_a(78))
/* 30 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KIRIBACHI.get())
/* 31 */       .func_216086_a(2))
/* 32 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.ARLONG_HAT.get())
/* 33 */       .func_216086_a(5))
/* 34 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.ARLONG_CHEST.get())
/* 35 */       .func_216086_a(5))
/* 36 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.ARLONG_LEGS.get())
/* 37 */       .func_216086_a(5))
/* 38 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.ARLONG_FEET.get())
/* 39 */       .func_216086_a(5));
/*    */ 
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 44 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(HARD_MODE).func_216040_a(ITEMS);
/*    */     
/* 46 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 47 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\ArlongReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */