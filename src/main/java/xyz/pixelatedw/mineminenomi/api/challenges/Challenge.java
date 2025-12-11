/*     */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootParameterSets;
/*     */ import net.minecraft.loot.LootParameters;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public abstract class Challenge {
/*     */   private final ChallengeCore<?> core;
/*     */   private int completions;
/*     */   private int bestTime;
/*     */   
/*     */   public Challenge(ChallengeCore<?> core) {
/*  37 */     this.core = core;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/*  41 */     return this.core.getCategory();
/*     */   }
/*     */   
/*     */   public void resetBestTime() {
/*  45 */     this.bestTime = 0;
/*     */   }
/*     */   
/*     */   public void tryUpdateBestTime(int time) {
/*  49 */     if (getBestTimeTick() == 0) {
/*  50 */       this.bestTime = time;
/*     */       
/*     */       return;
/*     */     } 
/*  54 */     if (isPersonalBest(time)) {
/*  55 */       this.bestTime = time;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isPersonalBest(int time) {
/*  60 */     int pb = getBestTimeTick();
/*  61 */     if (pb > 0 && time < pb) {
/*  62 */       return true;
/*     */     }
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   public int getBestTimeTick() {
/*  68 */     return this.bestTime;
/*     */   }
/*     */   
/*     */   public String getFormattedBestTime() {
/*  72 */     return WyHelper.formatTimeMMSS(this.bestTime);
/*     */   }
/*     */   
/*     */   public void setComplete(PlayerEntity player, boolean isComplete) {
/*  76 */     if (isComplete) {
/*  77 */       complete(player);
/*     */     } else {
/*  79 */       this.completions = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete(PlayerEntity player) {
/*  84 */     this.completions++;
/*     */     
/*  86 */     if (isFirstCompletion() && player instanceof ServerPlayerEntity) {
/*  87 */       ModAdvancements.COMPLETE_CHALLENGE.trigger((ServerPlayerEntity)player, this.core);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFirstCompletion() {
/*  92 */     return (this.completions == 1);
/*     */   }
/*     */   
/*     */   public boolean isComplete() {
/*  96 */     return (this.completions > 0);
/*     */   }
/*     */   
/*     */   public int getCompletions() {
/* 100 */     return this.completions;
/*     */   }
/*     */   
/*     */   public ChallengeCore getCore() {
/* 104 */     return this.core;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getRewards(PlayerEntity player) {
/* 109 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 110 */     IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
/*     */     
/* 112 */     List<ItemStack> stacks = Lists.newArrayList();
/* 113 */     if (this.core.getRewards() != null) {
/* 114 */       LootTable lootTable = player.field_70170_p.func_73046_m().func_200249_aQ().func_186521_a(this.core.getRewards());
/* 115 */       if (lootTable == LootTable.field_186464_a) {
/* 116 */         ModMain.LOGGER.warn(this.core.getRewards() + " reward could not be found.");
/* 117 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 122 */       LootContext.Builder builder = (new LootContext.Builder((ServerWorld)player.field_70170_p)).func_216015_a(LootParameters.field_216281_a, player).func_216015_a(ModLootTypes.COMPLETED_CHALLENGE, this.core);
/* 123 */       LootContext context = builder.func_216022_a(LootParameterSets.field_216260_a);
/* 124 */       stacks.addAll(lootTable.func_216113_a(context));
/*     */     } 
/*     */     
/* 127 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 129 */     boolean hasAtLeastOneReward = false;
/* 130 */     if (stacks.size() > 0) {
/* 131 */       sb.append("\n§aRewards§r\n");
/* 132 */       hasAtLeastOneReward = true;
/*     */     } 
/*     */     
/* 135 */     boolean canReceiveStats = false;
/* 136 */     if (this.completions == 0) {
/* 137 */       canReceiveStats = true;
/*     */     
/*     */     }
/* 140 */     else if (this.core.getDifficulty() == ChallengeDifficulty.HARD) {
/* 141 */       canReceiveStats = true;
/*     */     } 
/*     */ 
/*     */     
/* 145 */     if (canReceiveStats) {
/*     */ 
/*     */ 
/*     */       
/* 149 */       float registeredChallengesTotalScale = ((Float)ModRegistries.CHALLENGES.getValues().stream().filter(oc -> oc.getDifficulty().equals(this.core.getDifficulty())).map(cc -> Float.valueOf(cc.getRewardsFactor())).reduce(Float.valueOf(0.0F), (acc, val) -> Float.valueOf(acc.floatValue() + val.floatValue()))).floatValue();
/* 150 */       float challengeRewardFactor = this.core.getRewardsFactor() / registeredChallengesTotalScale;
/* 151 */       float rawDorikiAmount = challengeRewardFactor * CommonConfig.INSTANCE.getDorikiRewardPoolForDifficulty(this.core.getDifficulty());
/* 152 */       float rawBellyAmount = challengeRewardFactor * CommonConfig.INSTANCE.getBellyRewardPoolForDifficulty(this.core.getDifficulty());
/* 153 */       float rawHakiAmount = challengeRewardFactor * CommonConfig.INSTANCE.getHakiRewardPoolForDifficulty(this.core.getDifficulty());
/*     */       
/* 155 */       int dorikiAmount = scaleValueWithRounding(rawDorikiAmount);
/* 156 */       int bellyAmount = scaleValueWithRounding(rawBellyAmount);
/* 157 */       float hakiAmount = scaleValueFromCompletions(rawHakiAmount);
/* 158 */       hakiAmount = Math.round(hakiAmount * 100.0F) / 100.0F;
/*     */       
/* 160 */       if (dorikiAmount > 0) {
/* 161 */         props.alterDoriki(dorikiAmount, StatChangeSource.CHALLENGE);
/* 162 */         sb.append("  " + dorikiAmount + " Doriki\n");
/*     */       } 
/*     */       
/* 165 */       if (bellyAmount > 0) {
/* 166 */         props.alterBelly(bellyAmount, StatChangeSource.CHALLENGE);
/* 167 */         sb.append("  " + bellyAmount + " Belly\n");
/*     */       } 
/*     */       
/* 170 */       if (hakiAmount > 0.0F) {
/* 171 */         if (player.func_70681_au().nextBoolean()) {
/* 172 */           hakiData.alterBusoshokuHakiExp(hakiAmount, StatChangeSource.CHALLENGE);
/* 173 */           sb.append("  " + hakiAmount + " Busoshoku Haki Experience\n");
/*     */         } else {
/*     */           
/* 176 */           hakiData.alterKenbunshokuHakiExp(hakiAmount, StatChangeSource.CHALLENGE);
/* 177 */           sb.append("  " + hakiAmount + " Kenbunshoku Haki Experience\n");
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 182 */     for (ItemStack stack : stacks) {
/* 183 */       if (stack.func_77978_p() != null && stack.func_200301_q().getString().contains("_rewards")) {
/* 184 */         int dorikiAmount = stack.func_77978_p().func_74762_e("_rewardsDoriki");
/* 185 */         int bellyAmount = stack.func_77978_p().func_74762_e("_rewardsBelly");
/* 186 */         int extolAmount = stack.func_77978_p().func_74762_e("_rewardsExtol");
/* 187 */         int bountyAmount = stack.func_77978_p().func_74762_e("_rewardsBounty");
/* 188 */         int busoHakiAmount = stack.func_77978_p().func_74762_e("_rewardsBusoHaki");
/* 189 */         int obsHakiAmount = stack.func_77978_p().func_74762_e("_rewardsKenHaki");
/*     */         
/* 191 */         if (dorikiAmount > 0) {
/* 192 */           sb.append("  " + dorikiAmount + " Doriki\n");
/*     */         }
/*     */         
/* 195 */         if (bellyAmount > 0) {
/* 196 */           sb.append("  " + bellyAmount + " Belly\n");
/*     */         }
/*     */         
/* 199 */         if (extolAmount > 0) {
/* 200 */           sb.append("  " + extolAmount + " Extol\n");
/*     */         }
/*     */         
/* 203 */         if (bountyAmount > 0) {
/* 204 */           sb.append("  " + bountyAmount + " Bounty\n");
/*     */         }
/*     */         
/* 207 */         if (bountyAmount > 0) {
/* 208 */           sb.append("  " + bountyAmount + " Bounty\n");
/*     */         }
/*     */         
/* 211 */         if (busoHakiAmount > 0) {
/* 212 */           sb.append("  " + busoHakiAmount + " Busoshoku Haki Experience\n");
/*     */         }
/*     */         
/* 215 */         if (obsHakiAmount > 0) {
/* 216 */           sb.append("  " + obsHakiAmount + " Kenbunshoku Haki Experience\n");
/*     */         }
/*     */         
/* 219 */         int newUnlocks = stack.func_77978_p().func_74762_e("_unlocksAmount");
/* 220 */         if (newUnlocks > 0) {
/* 221 */           ListNBT unlocksList = stack.func_77978_p().func_150295_c("_unlocks", 8);
/* 222 */           for (int i = 0; i < unlocksList.size(); i++) {
/* 223 */             String title = unlocksList.func_150307_f(i);
/* 224 */             sb.append("  New Challenge unlocked " + title + "\n");
/*     */           } 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 229 */       ItemStack stackCopy = stack.func_77946_l();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 235 */       sb.append("  " + ((stackCopy.func_190916_E() > 1) ? (stackCopy.func_190916_E() + " ") : "") + stackCopy.func_200301_q().getString() + "\n");
/* 236 */       player.func_191521_c(stackCopy);
/*     */     } 
/*     */ 
/*     */     
/* 240 */     if (hasAtLeastOneReward) {
/* 241 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/* 242 */       sb.append("\n");
/*     */     } 
/*     */     
/* 245 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public int scaleValueWithRounding(float amount) {
/* 249 */     amount = scaleValueFromCompletions(amount);
/* 250 */     if (amount % 10.0F != 0.0F && amount > 10.0F) {
/* 251 */       amount = WyHelper.roundToNiceNumber(amount);
/*     */     }
/* 253 */     return (int)amount;
/*     */   }
/*     */   
/*     */   public float scaleValueFromCompletions(float amount) {
/* 257 */     int completions = getCompletions();
/* 258 */     completions = Math.min(completions, 10);
/* 259 */     if (completions > 0) {
/* 260 */       float d = completions * 1.5F;
/* 261 */       amount /= d;
/*     */     } 
/* 263 */     return amount;
/*     */   }
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 267 */     nbt.func_74768_a("completions", this.completions);
/* 268 */     nbt.func_74768_a("bestTime", this.bestTime);
/* 269 */     return nbt;
/*     */   }
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 273 */     if (nbt.func_74764_b("isComplete")) {
/* 274 */       this.completions = nbt.func_74767_n("isComplete") ? 1 : 0;
/*     */     } else {
/* 276 */       this.completions = nbt.func_74762_e("completions");
/*     */     } 
/* 278 */     this.bestTime = nbt.func_74762_e("bestTime");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object challenge) {
/* 283 */     if (challenge instanceof ChallengeCore && challenge.equals(getCore())) {
/* 284 */       return true;
/*     */     }
/*     */     
/* 287 */     if (!(challenge instanceof Challenge)) {
/* 288 */       return false;
/*     */     }
/*     */     
/* 291 */     if (getCore() == null || ((Challenge)challenge).getCore() == null) {
/* 292 */       return false;
/*     */     }
/*     */     
/* 295 */     if (getCore().equals(((Challenge)challenge).getCore())) {
/* 296 */       return true;
/*     */     }
/*     */     
/* 299 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\Challenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */