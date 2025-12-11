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
/*    */ public class JangoReward {
/* 20 */   public static final CompoundNBT HAT_NBT = createBicorneNbt();
/*    */   private static CompoundNBT createBicorneNbt() {
/* 22 */     CompoundNBT nbt = new CompoundNBT();
/* 23 */     CompoundNBT display = new CompoundNBT();
/* 24 */     display.func_74768_a("color", 3364244);
/* 25 */     nbt.func_218657_a("display", (INBT)display);
/* 26 */     return nbt;
/*    */   }
/*    */   
/* 29 */   private static final LootPool.Builder HARD_MODE = LootPool.func_216096_a()
/* 30 */     .name("mineminenomi:hard_mode")
/* 31 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 32 */     .func_212840_b_(FirstCompletionRewardCondition.builder())
/* 33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_190931_a)
/* 34 */       .func_212841_b_((ILootFunction.IBuilder)UnlockChallengesFunction.builder(new ChallengeCore[] { (ChallengeCore)ModChallenges.JANGO_HARD.get() })));
/*    */   
/* 36 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 37 */     .name("mineminenomi:items")
/* 38 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 39 */     .func_216045_a((LootEntry.Builder)EmptyLootEntry.func_216167_a()
/* 40 */       .func_216086_a(88))
/* 41 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CHAKRAM.get())
/* 42 */       .func_216086_a(2))
/* 43 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.WIDE_BRIM_HAT.get())
/* 44 */       .func_212841_b_((ILootFunction.IBuilder)SetNBT.func_215952_a(HAT_NBT))
/* 45 */       .func_216086_a(5))
/* 46 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModArmors.HEART_GLASSES.get())
/* 47 */       .func_216086_a(5));
/*    */ 
/*    */ 
/*    */   
/*    */   public static Pair<String, LootTable.Builder>[] getTables() {
/* 52 */     LootTable.Builder rewards = LootTable.func_216119_b().func_216040_a(HARD_MODE).func_216040_a(ITEMS);
/*    */     
/* 54 */     return (Pair<String, LootTable.Builder>[])new Pair[] {
/* 55 */         Pair.of("rewards", rewards)
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\rewards\JangoReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */