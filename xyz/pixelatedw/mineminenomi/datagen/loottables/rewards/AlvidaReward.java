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
/*    */ import xyz.pixelatedw.mineminenomi.data.conditions.FirstCompletionRewardCondition;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.UnlockChallengesFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ 
/*    */ public class AlvidaReward {
/* 20 */   public static final CompoundNBT PLUME_HAT_NBT = createBicorneNbt();
/*    */   private static CompoundNBT createBicorneNbt() {
/* 22 */     CompoundNBT nbt = new CompoundNBT();
/* 23 */     CompoundNBT display = new CompoundNBT();
/* 24 */     display.func_74768_a("color", 12788538);
/* 25 */     nbt.func_218657_a("display", (INBT)display);
/* 26 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   private static final LootPool.Builder HARD_MODE = LootPool.func_216096_a()
/* 39 */     .name("mineminenomi:hard_mode")
/* 40 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 41 */     .func_212840_b_(FirstCompletionRewardCondition.builder())
/* 42 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_190931_a)
/* 43 */       .func_212841_b_((ILootFunction.IBuilder)UnlockChallengesFunction.builder(new ChallengeCore[] { (ChallengeCore)ModChallenges.ALVIDA_HARD.get() })));
/*    */   
/* 45 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 46 */     .name("mineminenomi:items")
/* 47 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 48 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 49 */       .func_216086_a(93))
/* 50 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.MACE.get())
/* 51 */       .func_216086_a(2))
/* 52 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.PLUME_HAT.get())
/* 53 */       .func_212841_b_((ILootFunction.IBuilder)SetNBT.func_215952_a(PLUME_HAT_NBT))
/* 54 */       .func_216086_a(5));
/*    */ 
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 59 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(HARD_MODE).func_216040_a(ITEMS);
/*    */     
/* 61 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 62 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\AlvidaReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */