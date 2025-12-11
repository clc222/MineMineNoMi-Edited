/*     */ package xyz.pixelatedw.mineminenomi.datagen.loottables;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.DirectoryCache;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.AlvidaHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.AlvidaReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.ArlongHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.ArlongReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.BuggyHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.BuggyReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.CabajiHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.CabajiReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.ChewHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.ChewReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.DonKriegHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.DonKriegReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.GinHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.GinReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.HigumaHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.HigumaReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.JangoHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.JangoReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.KuroHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.KuroReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.KuroobiHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.KuroobiReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.MorganHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.MorganReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr0HardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr0Reward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr1HardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr1Reward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr3HardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr3Reward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr5MissValentineHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.Mr5MissValentineReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.NyanbanBrothersHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.NyanbanBrothersReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.PearlHardReward;
/*     */ import xyz.pixelatedw.mineminenomi.datagen.loottables.rewards.PearlReward;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.LootTablesDataGen;
/*     */ 
/*     */ public class RewardsLootTablesDataGen extends LootTablesDataGen {
/*  53 */   private final Multimap<ChallengeCore<?>, Pair<String, LootTable.Builder>> lootTables = (Multimap<ChallengeCore<?>, Pair<String, LootTable.Builder>>)HashMultimap.create();
/*     */   
/*     */   public RewardsLootTablesDataGen(DataGenerator dataGenerator) {
/*  56 */     super(dataGenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_200398_a(DirectoryCache cache) {
/*  61 */     addLootTable((ChallengeCore)ModChallenges.HIGUMA.get(), (Pair<String, LootTable.Builder>[])HigumaReward.getTables());
/*  62 */     addLootTable((ChallengeCore)ModChallenges.HIGUMA_HARD.get(), (Pair<String, LootTable.Builder>[])HigumaHardReward.getTables());
/*     */     
/*  64 */     addLootTable((ChallengeCore)ModChallenges.MORGAN.get(), (Pair<String, LootTable.Builder>[])MorganReward.getTables());
/*  65 */     addLootTable((ChallengeCore)ModChallenges.MORGAN_HARD.get(), (Pair<String, LootTable.Builder>[])MorganHardReward.getTables());
/*     */ 
/*     */     
/*  68 */     addLootTable((ChallengeCore)ModChallenges.BUGGY.get(), (Pair<String, LootTable.Builder>[])BuggyReward.getTables());
/*  69 */     addLootTable((ChallengeCore)ModChallenges.BUGGY_HARD.get(), (Pair<String, LootTable.Builder>[])BuggyHardReward.getTables());
/*  70 */     addLootTable((ChallengeCore)ModChallenges.ALVIDA.get(), (Pair<String, LootTable.Builder>[])AlvidaReward.getTables());
/*  71 */     addLootTable((ChallengeCore)ModChallenges.ALVIDA_HARD.get(), (Pair<String, LootTable.Builder>[])AlvidaHardReward.getTables());
/*  72 */     addLootTable((ChallengeCore)ModChallenges.CABAJI.get(), (Pair<String, LootTable.Builder>[])CabajiReward.getTables());
/*  73 */     addLootTable((ChallengeCore)ModChallenges.CABAJI_HARD.get(), (Pair<String, LootTable.Builder>[])CabajiHardReward.getTables());
/*     */ 
/*     */     
/*  76 */     addLootTable((ChallengeCore)ModChallenges.KURO.get(), (Pair<String, LootTable.Builder>[])KuroReward.getTables());
/*  77 */     addLootTable((ChallengeCore)ModChallenges.KURO_HARD.get(), (Pair<String, LootTable.Builder>[])KuroHardReward.getTables());
/*  78 */     addLootTable((ChallengeCore)ModChallenges.JANGO.get(), (Pair<String, LootTable.Builder>[])JangoReward.getTables());
/*  79 */     addLootTable((ChallengeCore)ModChallenges.JANGO_HARD.get(), (Pair<String, LootTable.Builder>[])JangoHardReward.getTables());
/*  80 */     addLootTable((ChallengeCore)ModChallenges.NYANBAN_BROTHERS.get(), (Pair<String, LootTable.Builder>[])NyanbanBrothersReward.getTables());
/*  81 */     addLootTable((ChallengeCore)ModChallenges.NYANBAN_BROTHERS_HARD.get(), (Pair<String, LootTable.Builder>[])NyanbanBrothersHardReward.getTables());
/*     */ 
/*     */     
/*  84 */     addLootTable((ChallengeCore)ModChallenges.DON_KRIEG.get(), (Pair<String, LootTable.Builder>[])DonKriegReward.getTables());
/*  85 */     addLootTable((ChallengeCore)ModChallenges.DON_KRIEG_HARD.get(), (Pair<String, LootTable.Builder>[])DonKriegHardReward.getTables());
/*  86 */     addLootTable((ChallengeCore)ModChallenges.GIN.get(), (Pair<String, LootTable.Builder>[])GinReward.getTables());
/*  87 */     addLootTable((ChallengeCore)ModChallenges.GIN_HARD.get(), (Pair<String, LootTable.Builder>[])GinHardReward.getTables());
/*  88 */     addLootTable((ChallengeCore)ModChallenges.PEARL.get(), (Pair<String, LootTable.Builder>[])PearlReward.getTables());
/*  89 */     addLootTable((ChallengeCore)ModChallenges.PEARL_HARD.get(), (Pair<String, LootTable.Builder>[])PearlHardReward.getTables());
/*     */ 
/*     */     
/*  92 */     addLootTable((ChallengeCore)ModChallenges.ARLONG.get(), (Pair<String, LootTable.Builder>[])ArlongReward.getTables());
/*  93 */     addLootTable((ChallengeCore)ModChallenges.ARLONG_HARD.get(), (Pair<String, LootTable.Builder>[])ArlongHardReward.getTables());
/*  94 */     addLootTable((ChallengeCore)ModChallenges.CHEW.get(), (Pair<String, LootTable.Builder>[])ChewReward.getTables());
/*  95 */     addLootTable((ChallengeCore)ModChallenges.CHEW_HARD.get(), (Pair<String, LootTable.Builder>[])ChewHardReward.getTables());
/*  96 */     addLootTable((ChallengeCore)ModChallenges.KUROOBI.get(), (Pair<String, LootTable.Builder>[])KuroobiReward.getTables());
/*  97 */     addLootTable((ChallengeCore)ModChallenges.KUROOBI_HARD.get(), (Pair<String, LootTable.Builder>[])KuroobiHardReward.getTables());
/*     */ 
/*     */     
/* 100 */     addLootTable((ChallengeCore)ModChallenges.MR_0.get(), (Pair<String, LootTable.Builder>[])Mr0Reward.getTables());
/* 101 */     addLootTable((ChallengeCore)ModChallenges.MR_0_HARD.get(), (Pair<String, LootTable.Builder>[])Mr0HardReward.getTables());
/* 102 */     addLootTable((ChallengeCore)ModChallenges.MR_1.get(), (Pair<String, LootTable.Builder>[])Mr1Reward.getTables());
/* 103 */     addLootTable((ChallengeCore)ModChallenges.MR_1_HARD.get(), (Pair<String, LootTable.Builder>[])Mr1HardReward.getTables());
/* 104 */     addLootTable((ChallengeCore)ModChallenges.MR_3.get(), (Pair<String, LootTable.Builder>[])Mr3Reward.getTables());
/* 105 */     addLootTable((ChallengeCore)ModChallenges.MR_3_HARD.get(), (Pair<String, LootTable.Builder>[])Mr3HardReward.getTables());
/* 106 */     addLootTable((ChallengeCore)ModChallenges.MR_5_AND_MISS_VALENTINE.get(), (Pair<String, LootTable.Builder>[])Mr5MissValentineReward.getTables());
/* 107 */     addLootTable((ChallengeCore)ModChallenges.MR_5_AND_MISS_VALENTINE_HARD.get(), (Pair<String, LootTable.Builder>[])Mr5MissValentineHardReward.getTables());
/*     */     
/* 109 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/* 110 */     this.lootTables.forEach((key, pair) -> {
/*     */           ResourceLocation res = new ResourceLocation(key.getRegistryName().func_110624_b(), "rewards/" + key.getRegistryName().func_110623_a());
/*     */           
/*     */           tables.put(res, ((LootTable.Builder)pair.getRight()).func_216038_b());
/*     */         });
/* 115 */     writeTables(cache, tables);
/*     */   }
/*     */   
/*     */   protected void addLootTable(ChallengeCore<?> challenge, Pair<String, LootTable.Builder>[] pairs) {
/* 119 */     for (Pair<String, LootTable.Builder> pair : pairs) {
/* 120 */       this.lootTables.put(challenge, pair);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_200397_b() {
/* 126 */     return "Rewards Loot Pools";
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\RewardsLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */