/*     */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.PoneglyphLowspecArena;
/*     */ 
/*     */ public class ChallengeCore<T extends Challenge>
/*     */   extends ForgeRegistryEntry<ChallengeCore<?>> {
/*     */   private String id;
/*     */   @Nullable
/*     */   private IFormattableTextComponent title;
/*     */   private final String unlocalizedTitle;
/*     */   private IFormattableTextComponent objective;
/*     */   private final String unlocalizedObjective;
/*     */   private final String category;
/*     */   private int timeLimit;
/*     */   private ResourceLocation rewards;
/*     */   private ChallengeDifficulty difficulty;
/*     */   private int difficultyStars;
/*     */   private float rewardsFactor;
/*  36 */   private HashMultimap<ArenaStyle, ArenaEntry> arenas = HashMultimap.create();
/*     */   private ITargetShowcase[] targetShowcase;
/*     */   private boolean isTargetCacheDirty = false;
/*  39 */   private LivingEntity[] targetsCache = new LivingEntity[4];
/*     */   private String[] bannedFactions;
/*     */   private final IFactory<T> factory;
/*     */   private ICanStart startCheck;
/*     */   private IEnemySpawns enemySpawns;
/*     */   @Nullable
/*     */   private String titleI18nId;
/*     */   @Nullable
/*     */   private String objectiveI18nId;
/*     */   private int order;
/*     */   
/*     */   public ChallengeCore(String id, String unlocalizedTitle, String unlocalizedObjective, String category, IFactory<T> factory) {
/*  51 */     this.id = id;
/*  52 */     this.unlocalizedTitle = unlocalizedTitle;
/*  53 */     this.unlocalizedObjective = unlocalizedObjective;
/*  54 */     this.category = category;
/*  55 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T createChallenge() {
/*  60 */     return this.factory.create(this);
/*     */   }
/*     */   
/*     */   private void setTargetShowcase(ITargetShowcase... targets) {
/*  64 */     this.targetShowcase = targets;
/*  65 */     this.isTargetCacheDirty = true;
/*     */   }
/*     */   
/*     */   private void setRewardFactor(float factor) {
/*  69 */     this.rewardsFactor = factor;
/*     */   }
/*     */   
/*     */   private void setRewards(ResourceLocation rewards) {
/*  73 */     this.rewards = rewards;
/*     */   }
/*     */   
/*     */   private void setArenas(HashMultimap<ArenaStyle, ArenaEntry> map) {
/*  77 */     this.arenas = map;
/*     */   }
/*     */   
/*     */   private void setDifficulty(ChallengeDifficulty difficulty) {
/*  81 */     this.difficulty = difficulty;
/*     */   }
/*     */   
/*     */   private void setDifficultyStars(int stars) {
/*  85 */     this.difficultyStars = stars;
/*     */   }
/*     */   
/*     */   private void setTimeLimit(int minutes) {
/*  89 */     this.timeLimit = minutes;
/*     */   }
/*     */   
/*     */   public void setBannedFactions(String... factions) {
/*  93 */     this.bannedFactions = factions;
/*     */   }
/*     */   
/*     */   public void setStartCheck(ICanStart startCheck) {
/*  97 */     this.startCheck = startCheck;
/*     */   }
/*     */   
/*     */   public void setEnemySpawns(IEnemySpawns spawns) {
/* 101 */     this.enemySpawns = spawns;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 105 */     return this.id;
/*     */   }
/*     */   
/*     */   public float getRewardsFactor() {
/* 109 */     return this.rewardsFactor;
/*     */   }
/*     */   
/*     */   public ResourceLocation getRewards() {
/* 113 */     return this.rewards;
/*     */   }
/*     */   
/*     */   public IFormattableTextComponent getLocalizedTitle() {
/* 117 */     if (this.title == null) {
/* 118 */       this.title = (IFormattableTextComponent)new TranslationTextComponent(getUnlocalizedTitle());
/*     */     }
/* 120 */     return this.title;
/*     */   }
/*     */   
/*     */   public IFormattableTextComponent getLocalizedObjective() {
/* 124 */     if (this.objective == null) {
/* 125 */       this.objective = (IFormattableTextComponent)new TranslationTextComponent(getUnlocalizedObjective());
/*     */     }
/* 127 */     return this.objective;
/*     */   }
/*     */   
/*     */   private String getTitleLocalizationId() {
/* 131 */     if (this.titleI18nId == null) {
/* 132 */       ResourceLocation key = ModRegistries.CHALLENGES.getKey((IForgeRegistryEntry)this);
/* 133 */       if (key != null) {
/* 134 */         this.titleI18nId = Util.func_200697_a("challenge", key);
/*     */       }
/*     */     } 
/* 137 */     return this.titleI18nId;
/*     */   }
/*     */   
/*     */   private String getObjectiveLocalizationId() {
/* 141 */     if (this.titleI18nId == null) {
/* 142 */       ResourceLocation key = ModRegistries.CHALLENGES.getKey((IForgeRegistryEntry)this);
/* 143 */       if (key != null) {
/* 144 */         this.objectiveI18nId = Util.func_200697_a("challenge", key) + ".objective";
/*     */       }
/*     */     } 
/* 147 */     return this.objectiveI18nId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity[] getTargetShowcase(World world) {
/* 161 */     if (this.isTargetCacheDirty && this.targetShowcase != null) {
/* 162 */       int i = 0;
/* 163 */       for (ITargetShowcase showcase : this.targetShowcase) {
/* 164 */         this.targetsCache[i] = showcase.createShowcase(world);
/* 165 */         i++;
/*     */       } 
/* 167 */       this.isTargetCacheDirty = false;
/*     */     } 
/* 169 */     return this.targetsCache;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ArenaEntry getArenaFromStyle(ArenaStyle style, String clzId) {
/* 174 */     for (ArenaEntry entry : this.arenas.get(style)) {
/* 175 */       if (entry.arena.getClass().getName().equals(clzId)) {
/* 176 */         return entry;
/*     */       }
/*     */     } 
/* 179 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ArenaEntry pickRandomArena() {
/* 184 */     ArenaStyle configStyle = ArenaStyle.SIMPLE;
/* 185 */     Iterator<ArenaEntry> iterator = this.arenas.get(configStyle).iterator(); if (iterator.hasNext()) { ArenaEntry entry = iterator.next();
/* 186 */       return entry; }
/*     */     
/* 188 */     return null;
/*     */   }
/*     */   
/*     */   private void setOrder(int order) {
/* 192 */     this.order = order;
/*     */   }
/*     */   
/*     */   public int getOrder() {
/* 196 */     return this.order;
/*     */   }
/*     */   
/*     */   public ChallengeDifficulty getDifficulty() {
/* 200 */     return this.difficulty;
/*     */   }
/*     */   
/*     */   public int getDifficultyStars() {
/* 204 */     return this.difficultyStars;
/*     */   }
/*     */   
/*     */   public String getUnlocalizedTitle() {
/* 208 */     return this.unlocalizedTitle;
/*     */   }
/*     */   
/*     */   public String getUnlocalizedObjective() {
/* 212 */     return this.unlocalizedObjective;
/*     */   }
/*     */   
/*     */   public IFactory<? extends T> getFactory() {
/* 216 */     return this.factory;
/*     */   }
/*     */   
/*     */   public ITextComponent getObjective() {
/* 220 */     return (ITextComponent)this.objective;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 224 */     return this.category;
/*     */   }
/*     */   
/*     */   public int getTimeLimit() {
/* 228 */     return this.timeLimit;
/*     */   }
/*     */   
/*     */   public String[] getBannedFactions() {
/* 232 */     return this.bannedFactions;
/*     */   }
/*     */   
/*     */   public ICanStart getStartCheck() {
/* 236 */     return this.startCheck;
/*     */   }
/*     */   
/*     */   public IEnemySpawns getEnemySpawns() {
/* 240 */     return this.enemySpawns;
/*     */   }
/*     */   
/*     */   public static class Builder<T extends Challenge> {
/*     */     private String id;
/*     */     private String unlocalizedTitle;
/*     */     private String unlocalizedObjective;
/* 247 */     private String category = "No Category";
/* 248 */     private int timeLimit = 10;
/*     */     private ResourceLocation rewards;
/* 250 */     private ChallengeDifficulty difficulty = ChallengeDifficulty.STANDARD;
/* 251 */     private int difficultyStars = 1;
/* 252 */     private float rewardsFactor = 1.0F;
/* 253 */     private HashMultimap<ArenaStyle, ChallengeCore.ArenaEntry> arenas = HashMultimap.create();
/*     */     private ChallengeCore.ITargetShowcase[] targetShowcase;
/*     */     private String[] bannedFactions;
/*     */     private ChallengeCore.IFactory<T> factory;
/*     */     private ChallengeCore.ICanStart startCheck = entity -> true;
/*     */     private ChallengeCore.IEnemySpawns enemySpawns;
/* 259 */     private int order = Integer.MAX_VALUE;
/*     */     
/*     */     public Builder(String id, String title, String objective, String category, ChallengeCore.IFactory<T> factory) {
/* 262 */       this.id = id;
/* 263 */       this.unlocalizedTitle = title;
/* 264 */       this.unlocalizedObjective = objective;
/* 265 */       this.category = category;
/* 266 */       this.factory = factory;
/*     */     }
/*     */     
/*     */     public Builder<T> setOrder(int order) {
/* 270 */       this.order = order;
/* 271 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setTimeLimit(int minutes) {
/* 275 */       this.timeLimit = minutes;
/* 276 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setRewardsFactor(float factor) {
/* 280 */       this.rewardsFactor = factor;
/* 281 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setRewards(ResourceLocation rewards) {
/* 285 */       this.rewards = rewards;
/* 286 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setBannedFactions(String... factions) {
/* 290 */       this.bannedFactions = factions;
/* 291 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setStartCheck(ChallengeCore.ICanStart startCheck) {
/* 295 */       this.startCheck = startCheck;
/* 296 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setDifficulty(ChallengeDifficulty difficulty) {
/* 300 */       this.difficulty = difficulty;
/* 301 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setDifficultyStars(int stars) {
/* 305 */       this.difficultyStars = Math.min(stars, 10);
/* 306 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> addArena(ArenaStyle style, ChallengeArena arena, ChallengeCore.IChallengerPosition challengerPosition, ChallengeCore.IEnemyPosition enemyPosition) {
/* 310 */       ChallengeCore.ArenaEntry arenaEntry = new ChallengeCore.ArenaEntry(arena, challengerPosition, enemyPosition);
/* 311 */       this.arenas.put(style, arenaEntry);
/* 312 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setEnemySpawns(ChallengeCore.IEnemySpawns spawns) {
/* 316 */       this.enemySpawns = spawns;
/* 317 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setTargetShowcase(Supplier<EntityType<?>>... targets) {
/* 321 */       this.targetShowcase = new ChallengeCore.ITargetShowcase[targets.length];
/* 322 */       for (int i = 0; i < targets.length; i++) {
/* 323 */         int j = i;
/* 324 */         this.targetShowcase[j] = (world -> (LivingEntity)((EntityType)targets[j].get()).func_200721_a(world));
/*     */       } 
/* 326 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> setTargetShowcase(ChallengeCore.ITargetShowcase... targets) {
/* 330 */       this.targetShowcase = targets;
/* 331 */       return this;
/*     */     }
/*     */     
/*     */     public ChallengeCore build() {
/* 335 */       addArena(ArenaStyle.LOWSPEC, (ChallengeArena)PoneglyphLowspecArena.INSTANCE, PoneglyphLowspecArena::getChallengerSpawnPos, PoneglyphLowspecArena::getEnemySpawnPos);
/*     */       
/* 337 */       ChallengeCore<T> challenge = new ChallengeCore<>(this.id, this.unlocalizedTitle, this.unlocalizedObjective, this.category, this.factory);
/* 338 */       challenge.setTimeLimit(this.timeLimit);
/* 339 */       challenge.setRewardFactor(this.rewardsFactor);
/* 340 */       challenge.setRewards(this.rewards);
/* 341 */       challenge.setBannedFactions(this.bannedFactions);
/* 342 */       challenge.setStartCheck(this.startCheck);
/* 343 */       challenge.setDifficulty(this.difficulty);
/*     */       
/* 345 */       int difficultyStars = this.difficultyStars;
/* 346 */       if (this.difficulty == ChallengeDifficulty.HARD) {
/* 347 */         difficultyStars += 10;
/*     */       }
/* 349 */       else if (this.difficulty == ChallengeDifficulty.ULTIMATE) {
/* 350 */         difficultyStars += 20;
/*     */       } 
/*     */       
/* 353 */       challenge.setDifficultyStars(difficultyStars);
/* 354 */       challenge.setArenas(this.arenas);
/* 355 */       challenge.setTargetShowcase(this.targetShowcase);
/* 356 */       challenge.setEnemySpawns(this.enemySpawns);
/*     */       
/* 358 */       if (this.difficulty == ChallengeDifficulty.HARD) {
/* 359 */         this.order++;
/*     */       }
/* 361 */       else if (this.difficulty == ChallengeDifficulty.ULTIMATE) {
/* 362 */         this.order += 2;
/*     */       } 
/*     */       
/* 365 */       challenge.setOrder(this.order);
/* 366 */       return challenge;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArenaEntry {
/*     */     public final ChallengeArena arena;
/*     */     public final ChallengeCore.IChallengerPosition challengerPosition;
/*     */     public final ChallengeCore.IEnemyPosition enemyPosition;
/*     */     
/*     */     public ArenaEntry(ChallengeArena arena, ChallengeCore.IChallengerPosition challengerPosition, ChallengeCore.IEnemyPosition enemyPosition) {
/* 376 */       this.arena = arena;
/* 377 */       this.challengerPosition = challengerPosition;
/* 378 */       this.enemyPosition = enemyPosition;
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IFactory<A extends Challenge> {
/*     */     A create(ChallengeCore<A> param1ChallengeCore);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ICanStart {
/*     */     boolean canStart(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IChallengerPosition {
/*     */     ChallengeArena.SpawnPosition getChallengerSpawnPos(int param1Int, InProgressChallenge param1InProgressChallenge);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IEnemyPosition {
/*     */     ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge param1InProgressChallenge);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IEnemySpawns {
/*     */     Set<ChallengeArena.EnemySpawn> getEnemySpawns(InProgressChallenge param1InProgressChallenge, ChallengeArena.SpawnPosition[] param1ArrayOfSpawnPosition);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ITargetShowcase {
/*     */     LivingEntity createShowcase(World param1World);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */