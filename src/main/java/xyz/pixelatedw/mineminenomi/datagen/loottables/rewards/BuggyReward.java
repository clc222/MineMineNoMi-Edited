/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.rewards;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.EmptyLootEntry;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.UnlockChallengesFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ 
/*    */ public class BuggyReward {
/* 19 */   public static final CompoundNBT BICORNE_NBT = createBicorneNbt();
/*    */   private static CompoundNBT createBicorneNbt() {
/* 21 */     CompoundNBT nbt = new CompoundNBT();
/* 22 */     CompoundNBT display = new CompoundNBT();
/* 23 */     display.func_74768_a("color", 13459968);
/* 24 */     nbt.func_218657_a("display", (INBT)display);
/* 25 */     return nbt;
/*    */   }
/*    */   
/* 28 */   private static final LootPool.Builder HARD_MODE = LootPool.func_216096_a()
/* 29 */     .name("mineminenomi:hard_mode")
/* 30 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 31 */     .func_212840_b_(FirstCompletionRewardCondition.builder())
/* 32 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_190931_a)
/* 33 */       .func_212841_b_((ILootFunction.IBuilder)UnlockChallengesFunction.builder(new ChallengeCore[] { (ChallengeCore)ModChallenges.BUGGY_HARD.get() })));
/*    */   
/* 35 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 36 */     .name("mineminenomi:items")
/* 37 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 38 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 39 */       .func_216086_a(94))
/* 40 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.BIG_RED_NOSE.get())
/* 41 */       .func_216086_a(1))
/* 42 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.BICORNE.get())
/* 43 */       .func_212841_b_((ILootFunction.IBuilder)SetNBT.func_215952_a(BICORNE_NBT))
/* 44 */       .func_216086_a(5));
/*    */ 
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 49 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(HARD_MODE).func_216040_a(ITEMS);
/*    */     
/* 51 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 52 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\BuggyReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */