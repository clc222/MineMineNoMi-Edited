/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.rewards;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ 
/*    */ public class NyanbanBrothersReward {
/* 15 */   private static final LootPool.Builder HARD_MODE = LootPool.func_216096_a()
/* 16 */     .name("mineminenomi:hard_mode")
/* 17 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 18 */     .func_212840_b_(FirstCompletionRewardCondition.builder())
/* 19 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_190931_a)
/* 20 */       .func_212841_b_((ILootFunction.IBuilder)UnlockChallengesFunction.builder(new ChallengeCore[] { (ChallengeCore)ModChallenges.NYANBAN_BROTHERS_HARD.get() })));
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 24 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(HARD_MODE);
/*    */     
/* 26 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 27 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\NyanbanBrothersReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */