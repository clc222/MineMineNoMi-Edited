/*     */ package xyz.pixelatedw.mineminenomi.config;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.INextEnum;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class CommonConfig
/*     */ {
/*  27 */   public static final Path CONFIG_PATH = Paths.get("config", new String[] { "mineminenomi-common.toml" });
/*     */   
/*     */   public static final CommonConfig INSTANCE;
/*     */   
/*     */   public static final ForgeConfigSpec SPEC;
/*     */   
/*     */   public ForgeConfigSpec.ConfigValue<List<? extends String>> bannedItemsFromImbuing;
/*     */   
/*     */   public ForgeConfigSpec.BooleanValue forceSelection;
/*     */   public ForgeConfigSpec.BooleanValue abilityInvulnerability;
/*     */   public ForgeConfigSpec.BooleanValue specialSourceEvents;
/*     */   public ForgeConfigSpec.BooleanValue stopContinuousAbilities;
/*     */   public ForgeConfigSpec.BooleanValue animeScreaming;
/*  40 */   private List<AbilityCore> protectionWhitelistedAbilities = new ArrayList<>();
/*  41 */   private List<Item> bannedImbueableItems = new ArrayList<>();
/*  42 */   private List<AbilityCore> bannedAbilities = new ArrayList<>();
/*  43 */   private List<ResourceLocation> bannedDimensionsForStructures = new ArrayList<>();
/*     */   
/*     */   public enum HaoshokuUnlockLogic
/*     */     implements INextEnum {
/*  47 */     NONE, RANDOM, EXPERIENCE, COMBINED, TRUE_RANDOM;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends INextEnum> T next() {
/*  52 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OneFruitPerWorldLogic
/*     */     implements INextEnum {
/*  58 */     NONE, SIMPLE, EXTENDED;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends INextEnum> T next() {
/*  63 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum LogiaProjectileHitLogic
/*     */     implements INextEnum {
/*  69 */     NONE, HAKI, EXTENDED;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends INextEnum> T next() {
/*  74 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum HaoshokuColoringLogic
/*     */     implements INextEnum {
/*  80 */     STANDARD, CUSTOM, RANDOM;
/*     */ 
/*     */ 
/*     */     
/*     */     public <T extends INextEnum> T next() {
/*  85 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/*  91 */     Pair<CommonConfig, ForgeConfigSpec> pair = (new ForgeConfigSpec.Builder()).configure(CommonConfig::new);
/*  92 */     SPEC = (ForgeConfigSpec)pair.getRight();
/*  93 */     INSTANCE = (CommonConfig)pair.getLeft();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void save() {
/* 102 */     ModMain.LOGGER.warn("save() method got accessed server side! This is bad!");
/*     */   }
/*     */   
/*     */   public void clearCachedLists() {
/* 106 */     this.bannedAbilities.clear();
/* 107 */     this.bannedImbueableItems.clear();
/* 108 */     this.protectionWhitelistedAbilities.clear();
/* 109 */     this.bannedDimensionsForStructures.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public CommonConfig(ForgeConfigSpec.Builder builder) {
/* 114 */     builder.push("General");
/*     */     
/* 116 */     GeneralConfig.EXTRA_HEARTS.createValue(builder);
/* 117 */     GeneralConfig.MOB_REWARDS.createValue(builder);
/* 118 */     GeneralConfig.MINIMUM_DORIKI_PER_KILL.createValue(builder);
/* 119 */     GeneralConfig.DESTROY_SPAWNER.createValue(builder);
/* 120 */     GeneralConfig.DESTROY_WATER.createValue(builder);
/* 121 */     GeneralConfig.RACE_RANDOMIZER.createValue(builder);
/* 122 */     GeneralConfig.ALLOW_MINK_RACE_SELECT.createValue(builder);
/* 123 */     GeneralConfig.NATIVE_HAKI.createValue(builder);
/* 124 */     GeneralConfig.PUBLIC_REMOVEDF.createValue(builder);
/* 125 */     GeneralConfig.PUBLIC_CHECK_FRUITS.createValue(builder);
/* 126 */     GeneralConfig.DESPAWN_WITH_NAMETAG.createValue(builder);
/*     */     
/* 128 */     this.forceSelection = builder.comment("Forces new players to select their race, faction and fighting style upon joining the world\nDefault: false").define("Force Selection", false);
/*     */     
/* 130 */     Predicate<Object> bannedItemsTest = new Predicate()
/*     */       {
/*     */         public boolean test(Object t)
/*     */         {
/* 134 */           if (!(t instanceof String)) {
/* 135 */             return false;
/*     */           }
/*     */           
/* 138 */           String str = (String)t;
/*     */           
/* 140 */           if (Strings.isNullOrEmpty(str)) {
/* 141 */             return false;
/*     */           }
/*     */           
/* 144 */           return true;
/*     */         }
/*     */       };
/* 147 */     List<String> defaultBannedItems = new ArrayList<>();
/*     */     
/* 149 */     defaultBannedItems.add("mineminenomi:bubbly_coral");
/* 150 */     defaultBannedItems.add("mineminenomi:medic_bag");
/*     */     
/* 152 */     this.bannedItemsFromImbuing = builder.comment("List with item ids that will not get the durability protection of Imbuing Haki").defineList("Banned Items from Imbuing", defaultBannedItems, bannedItemsTest);
/*     */     
/* 154 */     GeneralConfig.DORIKI_LIMIT.createValue(builder);
/* 155 */     GeneralConfig.HAKI_EXP_LIMIT.createValue(builder);
/* 156 */     GeneralConfig.HEALTH_GAIN_FREQUENCY.createValue(builder);
/*     */     
/* 158 */     GeneralConfig.DORIKI_REWARD_MULTIPLIER.createValue(builder);
/* 159 */     GeneralConfig.BELLY_REWARD_MULTIPLIER.createValue(builder);
/* 160 */     GeneralConfig.BOUNTY_REWARD_MULTIPLIER.createValue(builder);
/* 161 */     GeneralConfig.HAKI_EXP_MULTIPLIER.createValue(builder);
/* 162 */     GeneralConfig.LOYALTY_MULTIPLIER.createValue(builder);
/*     */     
/* 164 */     builder.push("Stats to Keep");
/*     */     
/* 166 */     GeneralConfig.RACE_KEEP.createValue(builder);
/* 167 */     GeneralConfig.FACTION_KEEP.createValue(builder);
/* 168 */     GeneralConfig.FIGHTING_STYLE_KEEP.createValue(builder);
/* 169 */     GeneralConfig.DEVIL_FRUIT_KEEP.createValue(builder);
/*     */     
/* 171 */     GeneralConfig.DORIKI_KEEP_PERCENTAGE.createValue(builder);
/* 172 */     GeneralConfig.BOUNTY_KEEP_PERCENTAGE.createValue(builder);
/* 173 */     GeneralConfig.BELLY_KEEP_PERCENTAGE.createValue(builder);
/* 174 */     GeneralConfig.HAKI_EXP_KEEP_PERCENTAGE.createValue(builder);
/* 175 */     GeneralConfig.LOYALTY_KEEP_PERCENTAGE.createValue(builder);
/*     */     
/* 177 */     builder.pop();
/*     */     
/* 179 */     builder.push("Quests & Trials");
/*     */     
/* 181 */     GeneralConfig.ENABLE_TRIALS.createValue(builder);
/* 182 */     GeneralConfig.ENABLE_STYLES_PROGRESSION.createValue(builder);
/*     */     
/* 184 */     builder.pop();
/*     */     
/* 186 */     builder.pop();
/*     */     
/* 188 */     builder.push("Devil Fruits / Abilities");
/*     */     
/* 190 */     AbilitiesConfig.ABILITY_GRIEFING.createValue(builder);
/* 191 */     AbilitiesConfig.ABILITY_FRAUD_CHECKS.createValue(builder);
/* 192 */     AbilitiesConfig.ABILITY_BARS.createValue(builder);
/* 193 */     AbilitiesConfig.OPEN_WORLD_FRUIT_USERS.createValue(builder);
/*     */     
/* 195 */     AbilitiesConfig.YAMI_POWER.createValue(builder);
/* 196 */     AbilitiesConfig.ENABLE_AWAKENINGS.createValue(builder);
/* 197 */     AbilitiesConfig.WATER_CHECKS.createValue(builder);
/* 198 */     AbilitiesConfig.SHARED_COOLDOWNS.createValue(builder);
/* 199 */     AbilitiesConfig.REMOVE_Y_RESTRICTION.createValue(builder);
/*     */     
/* 201 */     AbilitiesConfig.RANDOMIZED_FRUITS.createValue(builder);
/* 202 */     AbilitiesConfig.DEVIL_FRUIT_DROP_FROM_LEAVES.createValue(builder);
/*     */     
/* 204 */     AbilitiesConfig.LOGIA_INVULNERABILITY.createValue(builder);
/* 205 */     AbilitiesConfig.LOGIA_RETURN_EFFECT.createValue(builder);
/* 206 */     AbilitiesConfig.LOGIA_PROJECTILE_HIT_LOGIC.createValue(builder);
/*     */     
/* 208 */     this.abilityInvulnerability = builder.comment("Invulnerability to avoid attacks\nDefault: true").define("Ability Invulnerability", true);
/* 209 */     this.specialSourceEvents = builder.comment("Makes the fire and lava damage source to reduce fire resistance; only applies to move attacks from fruits \nDefault: true").define("Special Source Events", true);
/* 210 */     this.stopContinuousAbilities = builder.comment("Used to determine the logic for when a continuous ability is used while another continuous ability is being used;\n true - Currently used ability is stopped and the newly used ability starts its process\n false - The current ability is NOT stopped and the used ability has no effect\nDefault: true").define("Stop Continuous Abilities", true);
/* 211 */     this.animeScreaming = builder.comment("Will send a chat message to nearby players with the used ability's name\nDefault: false").define("Anime Scream", false);
/*     */     
/* 213 */     AbilitiesConfig.BANNED_ABILITIES.createValue(builder);
/*     */     
/* 215 */     builder.comment("These options only work when \"One Fruit per World\" option is set to EXTENDED!").push("One Fruit Per World");
/*     */     
/* 217 */     AbilitiesConfig.ONE_FRUIT_PER_WORLD.createValue(builder);
/* 218 */     AbilitiesConfig.UNABLE_TO_PICKUP_DF.createValue(builder);
/* 219 */     AbilitiesConfig.FRUITS_LIMIT_INVENTORY.createValue(builder);
/* 220 */     AbilitiesConfig.DAYS_FOR_INACTIVITY.createValue(builder);
/*     */     
/* 222 */     builder.pop();
/*     */     
/* 224 */     builder.push("Devil Fruits Reincarnation");
/*     */     
/* 226 */     AbilitiesConfig.DROPPED_APPLES_RESPAWN_CHANCE.createValue(builder);
/* 227 */     AbilitiesConfig.ENTITY_INVENTORY_APPLES_RESPAWN_CHANCE.createValue(builder);
/* 228 */     AbilitiesConfig.CHESTS_APPLES_RESPAWN_CHANCE.createValue(builder);
/*     */     
/* 230 */     builder.pop();
/*     */     
/* 232 */     builder.push("Ability Protection");
/*     */     
/* 234 */     AbilitiesConfig.GLOBAL_PROTECTION_WHITELIST.createValue(builder);
/* 235 */     AbilitiesConfig.GLOBAL_PROTECTION_RESTORATION_GRACE.createValue(builder);
/*     */     
/* 237 */     builder.pop();
/*     */     
/* 239 */     builder.push("Haki");
/*     */     
/* 241 */     AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.createValue(builder);
/* 242 */     AbilitiesConfig.HAOSHOKU_HAKI_COLORING_LOGIC.createValue(builder);
/*     */     
/* 244 */     builder.pop();
/*     */     
/* 246 */     builder.pop();
/*     */     
/* 248 */     builder.push("World Features");
/*     */     
/* 250 */     WorldFeaturesConfig.SPAWN_BIOMES.createValue(builder);
/* 251 */     WorldFeaturesConfig.DIMENSION_STRUCTURES_BANLIST.createValue(builder);
/* 252 */     WorldFeaturesConfig.SPAWN_CHANCE_TRAINING_STRUCTURES.createValue(builder);
/* 253 */     WorldFeaturesConfig.SPAWN_CHANCE_GHOST_SHIPS.createValue(builder);
/* 254 */     WorldFeaturesConfig.SPAWN_CHANCE_SKY_ISLANDS.createValue(builder);
/* 255 */     WorldFeaturesConfig.SPAWN_CHANCE_SMALL_SHIPS.createValue(builder);
/* 256 */     WorldFeaturesConfig.SPAWN_CHANCE_MEDIUM_SHIPS.createValue(builder);
/* 257 */     WorldFeaturesConfig.SPAWN_CHANCE_LARGE_SHIPS.createValue(builder);
/* 258 */     WorldFeaturesConfig.SPAWN_CHANCE_CAMPS.createValue(builder);
/* 259 */     WorldFeaturesConfig.SPAWN_CHANCE_SMALL_BASES.createValue(builder);
/* 260 */     WorldFeaturesConfig.SPAWN_CHANCE_LARGE_BASES.createValue(builder);
/* 261 */     WorldFeaturesConfig.SPAWN_CHANCE_WATCH_TOWERS.createValue(builder);
/* 262 */     WorldFeaturesConfig.SPAWN_CHANCE_PONEGLYPHS.createValue(builder);
/* 263 */     WorldFeaturesConfig.KAIROSEKI_SPAWN_COUNT.createValue(builder);
/*     */     
/* 265 */     builder.pop();
/*     */     
/* 267 */     builder.push("Challenges");
/*     */     
/* 269 */     ChallengesConfig.ENABLE_CHALLENGES.createValue(builder);
/* 270 */     ChallengesConfig.CHALLENGE_CACHING.createValue(builder);
/* 271 */     ChallengesConfig.RETURN_TO_SAFETY.createValue(builder);
/* 272 */     ChallengesConfig.DORIKI_REWARD_POOL.createValue(builder);
/* 273 */     ChallengesConfig.BELLY_REWARD_POOL.createValue(builder);
/* 274 */     ChallengesConfig.HAKI_REWARD_POOL.createValue(builder);
/* 275 */     ChallengesConfig.ARENA_CONSTRUCTION_SPEED.createValue(builder);
/*     */     
/* 277 */     builder.pop();
/*     */     
/* 279 */     builder.push("World Events");
/*     */     
/* 281 */     WorldEventsConfig.SPAWN_WORLD_HUMANOIDS.createValue(builder);
/* 282 */     WorldEventsConfig.SPAWN_WORLD_ANIMALS.createValue(builder);
/* 283 */     WorldEventsConfig.SPAWN_NOTORIOUS_TARGETS.createValue(builder);
/* 284 */     WorldEventsConfig.SPAWN_CARAVANS.createValue(builder);
/* 285 */     WorldEventsConfig.SPAWN_CELESTIAL_VISITS.createValue(builder);
/*     */     
/* 287 */     builder.push("Traders");
/* 288 */     WorldEventsConfig.TIME_BETWEEN_TRADER_SPAWNS.createValue(builder);
/* 289 */     WorldEventsConfig.SPAWN_CHANCE_TRADER.createValue(builder);
/* 290 */     builder.pop();
/*     */     
/* 292 */     builder.push("Trainers");
/* 293 */     WorldEventsConfig.TIME_BETWEEN_TRAINER_SPAWNS.createValue(builder);
/* 294 */     WorldEventsConfig.SPAWN_CHANCE_TRAINER.createValue(builder);
/* 295 */     builder.pop();
/*     */     
/* 297 */     builder.push("Ambushes");
/* 298 */     WorldEventsConfig.TIME_BETWEEN_AMBUSH_SPAWNS.createValue(builder);
/* 299 */     WorldEventsConfig.SPAWN_CHANCE_AMBUSH.createValue(builder);
/* 300 */     builder.pop();
/*     */     
/* 302 */     builder.push("Spawn Chances");
/* 303 */     WorldEventsConfig.GRUNT_SPAWN_CHANCE.createValue(builder);
/* 304 */     WorldEventsConfig.BRUTE_SPAWN_CHANCE.createValue(builder);
/* 305 */     WorldEventsConfig.SNIPER_SPAWN_CHANCE.createValue(builder);
/* 306 */     WorldEventsConfig.CAPTAIN_SPAWN_CHANCE.createValue(builder);
/* 307 */     WorldEventsConfig.PACIFISTA_SPAWN_CHANCE.createValue(builder);
/* 308 */     builder.pop();
/*     */     
/* 310 */     builder.pop();
/*     */     
/* 312 */     builder.push("Factions");
/*     */     
/* 314 */     FactionsConfig.DISABLE_FRIENDLY_FIRE.createValue(builder);
/*     */     
/* 316 */     builder.push("Bounty");
/*     */     
/* 318 */     FactionsConfig.TIME_BETWEEN_PACKAGE_DROPS.createValue(builder);
/*     */     
/* 320 */     builder.pop();
/*     */     
/* 322 */     builder.push("Crews");
/*     */     
/* 324 */     FactionsConfig.CREW_BOUNTY_REQUIREMENT.createValue(builder);
/* 325 */     FactionsConfig.WORLD_MESSAGE_ON_CREW_CREATE.createValue(builder);
/*     */     
/* 327 */     builder.pop();
/*     */     
/* 329 */     builder.pop();
/*     */     
/* 331 */     builder.push("Integrations");
/*     */     
/* 333 */     if (ModMain.hasCuriosInstalled()) {
/* 334 */       IntegrationConfig.VISUAL_ONLY_CURIO.createValue(builder);
/*     */     }
/*     */     
/* 337 */     builder.pop();
/*     */     
/* 339 */     builder.push("System");
/*     */     
/* 341 */     SystemConfig.MASTER_COMMAND.createValue(builder);
/* 342 */     SystemConfig.EXPERIMENTAL_SPHERES.createValue(builder);
/* 343 */     SystemConfig.ENABLE_PERMISSIONS.createValue(builder);
/* 344 */     SystemConfig.EXPERIMENTAL_TIMERS.createValue(builder);
/* 345 */     SystemConfig.VILLAGE_COMPAT.createValue(builder);
/*     */     
/* 347 */     builder.pop();
/*     */   }
/*     */   
/*     */   public boolean hasVillageCompat() {
/* 351 */     return ((Boolean)SystemConfig.VILLAGE_COMPAT.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public int getArenaGenerationSpeed() {
/* 355 */     return ((Integer)ChallengesConfig.ARENA_CONSTRUCTION_SPEED.get()).intValue();
/*     */   }
/*     */   
/*     */   public boolean hasVisualOnlyCurio() {
/* 359 */     return ((Boolean)IntegrationConfig.VISUAL_ONLY_CURIO.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean hasExperimentalTimers() {
/* 363 */     return ((Boolean)SystemConfig.EXPERIMENTAL_TIMERS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean canSpawnCaravans() {
/* 367 */     return ((Boolean)WorldEventsConfig.SPAWN_CARAVANS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean canSpawnNotoriousTargets() {
/* 371 */     return ((Boolean)WorldEventsConfig.SPAWN_NOTORIOUS_TARGETS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean canSpawnCelestialVisits() {
/* 375 */     return ((Boolean)WorldEventsConfig.SPAWN_CELESTIAL_VISITS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public int getHakiRewardPoolForDifficulty(ChallengeDifficulty diff) {
/* 379 */     List<Integer> list = (List<Integer>)ChallengesConfig.HAKI_REWARD_POOL.get();
/* 380 */     if (list.isEmpty() || list.size() < diff.ordinal()) {
/* 381 */       return 0;
/*     */     }
/* 383 */     int value = ((Integer)list.get(diff.ordinal())).intValue();
/* 384 */     value = Math.min(value, getHakiExpLimit());
/* 385 */     return value;
/*     */   }
/*     */   
/*     */   public int getBellyRewardPoolForDifficulty(ChallengeDifficulty diff) {
/* 389 */     List<Integer> list = (List<Integer>)ChallengesConfig.BELLY_REWARD_POOL.get();
/* 390 */     if (list.isEmpty() || list.size() < diff.ordinal()) {
/* 391 */       return 0;
/*     */     }
/* 393 */     int value = ((Integer)list.get(diff.ordinal())).intValue();
/* 394 */     value = Math.min(value, 999999999);
/* 395 */     return value;
/*     */   }
/*     */   
/*     */   public int getDorikiRewardPoolForDifficulty(ChallengeDifficulty diff) {
/* 399 */     List<Integer> list = (List<Integer>)ChallengesConfig.DORIKI_REWARD_POOL.get();
/* 400 */     if (list.isEmpty() || list.size() < diff.ordinal()) {
/* 401 */       return 0;
/*     */     }
/* 403 */     int value = ((Integer)list.get(diff.ordinal())).intValue();
/* 404 */     value = Math.min(value, getDorikiLimit());
/* 405 */     return value;
/*     */   }
/*     */   
/*     */   public double getPacifistaSpawnChance() {
/* 409 */     return ((Double)WorldEventsConfig.PACIFISTA_SPAWN_CHANCE.get()).doubleValue();
/*     */   }
/*     */   
/*     */   public double getCaptainSpawnChance() {
/* 413 */     return ((Double)WorldEventsConfig.CAPTAIN_SPAWN_CHANCE.get()).doubleValue();
/*     */   }
/*     */   
/*     */   public double getSniperSpawnChance() {
/* 417 */     return ((Double)WorldEventsConfig.SNIPER_SPAWN_CHANCE.get()).doubleValue();
/*     */   }
/*     */   
/*     */   public double getBruteSpawnChance() {
/* 421 */     return ((Double)WorldEventsConfig.BRUTE_SPAWN_CHANCE.get()).doubleValue();
/*     */   }
/*     */   
/*     */   public double getGruntSpawnChance() {
/* 425 */     return ((Double)WorldEventsConfig.GRUNT_SPAWN_CHANCE.get()).doubleValue();
/*     */   }
/*     */   
/*     */   public boolean hasPermissionsEnabled() {
/* 429 */     return ((Boolean)SystemConfig.ENABLE_PERMISSIONS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isExperiementalSpheresEnabled() {
/* 433 */     return ((Boolean)SystemConfig.EXPERIMENTAL_SPHERES.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean hasAwakeningsEnabled() {
/* 437 */     return ((Boolean)AbilitiesConfig.ENABLE_AWAKENINGS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public List<ResourceLocation> getBannedDimensionsForStructures() {
/* 441 */     if (this.bannedDimensionsForStructures.isEmpty()) {
/* 442 */       List<? extends String> list = (List<? extends String>)WorldFeaturesConfig.DIMENSION_STRUCTURES_BANLIST.get();
/* 443 */       for (String o : list) {
/* 444 */         if (!(o instanceof String)) {
/*     */           continue;
/*     */         }
/*     */         
/* 448 */         String s = o;
/* 449 */         String[] arr = s.split(":");
/* 450 */         if (arr.length == 0) {
/*     */           continue;
/*     */         }
/* 453 */         if (arr.length == 1) {
/* 454 */           arr = new String[] { "minecraft", s };
/*     */         }
/* 456 */         this.bannedDimensionsForStructures.add(new ResourceLocation(arr[0], arr[1]));
/*     */       } 
/*     */     } 
/* 459 */     return this.bannedDimensionsForStructures;
/*     */   }
/*     */   
/*     */   public boolean hasPublicCheckFruitsCommand() {
/* 463 */     return ((Boolean)GeneralConfig.PUBLIC_CHECK_FRUITS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public long getGlobalProtectionGraceTime() {
/* 467 */     return ((Long)AbilitiesConfig.GLOBAL_PROTECTION_RESTORATION_GRACE.get()).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemBannedFromImbuing(Item item) {
/* 472 */     return getBannedImbuingItems().contains(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Item> getBannedImbuingItems() {
/* 477 */     if (this.bannedImbueableItems.isEmpty()) {
/* 478 */       for (String str : this.bannedItemsFromImbuing.get()) {
/*     */         
/* 480 */         ResourceLocation res = new ResourceLocation(str);
/* 481 */         Item item = (Item)GameRegistry.findRegistry(Item.class).getValue(res);
/* 482 */         if (item != null) {
/* 483 */           this.bannedImbueableItems.add(item);
/*     */         }
/*     */       } 
/*     */     }
/* 487 */     return this.bannedImbueableItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHealthGainFrequency() {
/* 492 */     return ((Integer)GeneralConfig.HEALTH_GAIN_FREQUENCY.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasYRestrictionRemoved() {
/* 497 */     return ((Boolean)AbilitiesConfig.REMOVE_Y_RESTRICTION.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoColoringRandom() {
/* 502 */     return (AbilitiesConfig.HAOSHOKU_HAKI_COLORING_LOGIC.get() == HaoshokuColoringLogic.RANDOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoColoringCustom() {
/* 507 */     return (AbilitiesConfig.HAOSHOKU_HAKI_COLORING_LOGIC.get() == HaoshokuColoringLogic.CUSTOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoColoringStandard() {
/* 512 */     return (AbilitiesConfig.HAOSHOKU_HAKI_COLORING_LOGIC.get() == HaoshokuColoringLogic.STANDARD);
/*     */   }
/*     */ 
/*     */   
/*     */   public HaoshokuColoringLogic getHaoshokuColoringLogic() {
/* 517 */     return (HaoshokuColoringLogic)AbilitiesConfig.HAOSHOKU_HAKI_COLORING_LOGIC.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNativeHakiEnabled() {
/* 522 */     return ((Boolean)GeneralConfig.NATIVE_HAKI.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isAbilityProtectionWhitelisted(AbilityCore core) {
/* 526 */     return getProtectionWhitelistedAbilities().contains(core);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AbilityCore> getProtectionWhitelistedAbilities() {
/* 531 */     if (this.protectionWhitelistedAbilities.isEmpty()) {
/* 532 */       List<? extends String> list = (List<? extends String>)AbilitiesConfig.GLOBAL_PROTECTION_WHITELIST.get();
/* 533 */       for (String o : list) {
/*     */         
/* 535 */         if (!(o instanceof String)) {
/*     */           continue;
/*     */         }
/*     */         
/* 539 */         String s = o;
/* 540 */         String[] arr = s.split(":");
/* 541 */         if (arr.length == 0) {
/*     */           continue;
/*     */         }
/* 544 */         if (arr.length == 1) {
/* 545 */           arr = new String[] { "mineminenomi", s };
/*     */         }
/* 547 */         ResourceLocation res = new ResourceLocation(arr[0], arr[1]);
/* 548 */         AbilityCore ability = AbilityCore.get(res);
/* 549 */         if (ability != null) {
/* 550 */           this.protectionWhitelistedAbilities.add(ability);
/*     */         }
/*     */       } 
/*     */     } 
/* 554 */     return this.protectionWhitelistedAbilities;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDespawnWithNametag() {
/* 559 */     return ((Boolean)GeneralConfig.DESPAWN_WITH_NAMETAG.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRandomizedFruits() {
/* 564 */     return ((Boolean)AbilitiesConfig.RANDOMIZED_FRUITS.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRaceRandomizer() {
/* 569 */     return ((Boolean)GeneralConfig.RACE_RANDOMIZER.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAllowMinkRaceSelect() {
/* 574 */     return ((Boolean)GeneralConfig.ALLOW_MINK_RACE_SELECT.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForSkyIslandSpawn() {
/* 579 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_SKY_ISLANDS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnSkyIslands() {
/* 584 */     return (getChanceForSkyIslandSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForGhostShipSpawn() {
/* 589 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_GHOST_SHIPS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnGhostShips() {
/* 594 */     return (getChanceForGhostShipSpawn() > 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isChallengesEnabled() {
/* 598 */     return ((Boolean)ChallengesConfig.ENABLE_CHALLENGES.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isChallengesCachingEnabled() {
/* 602 */     return ((Boolean)ChallengesConfig.CHALLENGE_CACHING.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isReturnToSafetyEnabled() {
/* 606 */     return ((Boolean)ChallengesConfig.RETURN_TO_SAFETY.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForPoneglyphSpawn() {
/* 611 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_PONEGLYPHS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnPoneglyphs() {
/* 616 */     return (getChanceForPoneglyphSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDaysForInactivity() {
/* 621 */     return ((Integer)AbilitiesConfig.DAYS_FOR_INACTIVITY.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBellyKeepPercentage() {
/* 626 */     return ((Integer)GeneralConfig.BELLY_KEEP_PERCENTAGE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHakiExpKeepPercentage() {
/* 631 */     return ((Integer)GeneralConfig.HAKI_EXP_KEEP_PERCENTAGE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBountyKeepPercentage() {
/* 636 */     return ((Integer)GeneralConfig.BOUNTY_KEEP_PERCENTAGE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDorikiKeepPercentage() {
/* 641 */     return ((Integer)GeneralConfig.DORIKI_KEEP_PERCENTAGE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryLimitForFruits() {
/* 646 */     return ((Integer)AbilitiesConfig.FRUITS_LIMIT_INVENTORY.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getUnableToPickDFAsUser() {
/* 651 */     return ((Boolean)AbilitiesConfig.UNABLE_TO_PICKUP_DF.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAbilityBars() {
/* 656 */     return ((Integer)AbilitiesConfig.ABILITY_BARS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getStopContinuousAbility() {
/* 661 */     return ((Boolean)this.stopContinuousAbilities.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHakiExpLimit() {
/* 666 */     return ((Integer)GeneralConfig.HAKI_EXP_LIMIT.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDorikiLimit() {
/* 671 */     return ((Integer)GeneralConfig.DORIKI_LIMIT.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDestroySpawner() {
/* 676 */     return ((Boolean)GeneralConfig.DESTROY_SPAWNER.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDestroyWater() {
/* 681 */     return ((Boolean)GeneralConfig.DESTROY_WATER.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForChestAppleReincarnation() {
/* 686 */     return ((Integer)AbilitiesConfig.CHESTS_APPLES_RESPAWN_CHANCE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForInventoryAppleReincarnation() {
/* 691 */     return ((Integer)AbilitiesConfig.ENTITY_INVENTORY_APPLES_RESPAWN_CHANCE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForDroppedAppleReincarnation() {
/* 696 */     return ((Integer)AbilitiesConfig.DROPPED_APPLES_RESPAWN_CHANCE.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFriendlyDamageDisabled() {
/* 701 */     return ((Boolean)FactionsConfig.DISABLE_FRIENDLY_FIRE.get()).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasOneFruitPerWorldExtendedLogic() {
/* 712 */     return ((OneFruitPerWorldLogic)AbilitiesConfig.ONE_FRUIT_PER_WORLD.get()).equals(OneFruitPerWorldLogic.EXTENDED);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOneFruitPerWorldSimpleLogic() {
/* 717 */     return (((OneFruitPerWorldLogic)AbilitiesConfig.ONE_FRUIT_PER_WORLD.get()).equals(OneFruitPerWorldLogic.SIMPLE) || hasOneFruitPerWorldExtendedLogic());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForLargeBasesSpawn() {
/* 722 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_LARGE_BASES.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnLargeBases() {
/* 727 */     return (getChanceForLargeBasesSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForWatchTowersSpawn() {
/* 732 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_WATCH_TOWERS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnWatchTowers() {
/* 737 */     return (getChanceForWatchTowersSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForLargeShipsSpawn() {
/* 742 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_LARGE_SHIPS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnLargeShips() {
/* 747 */     return (getChanceForLargeShipsSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForSmallBasesSpawn() {
/* 752 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_SMALL_BASES.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnSmallBases() {
/* 757 */     return (getChanceForSmallBasesSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForCampsSpawn() {
/* 762 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_CAMPS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnCamps() {
/* 767 */     return (getChanceForCampsSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForSmallShipsSpawn() {
/* 772 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_SMALL_SHIPS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnSmallShips() {
/* 777 */     return (getChanceForSmallShipsSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForMediumShipsSpawn() {
/* 782 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_MEDIUM_SHIPS.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnMediumShips() {
/* 787 */     return (getChanceForMediumShipsSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChanceForTrainingStructureSpawn() {
/* 792 */     return ((Integer)WorldFeaturesConfig.SPAWN_CHANCE_TRAINING_STRUCTURES.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnTrainingStructures() {
/* 797 */     return (getChanceForTrainingStructureSpawn() > 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrewWorldMessageEnabled() {
/* 802 */     return ((Boolean)FactionsConfig.WORLD_MESSAGE_ON_CREW_CREATE.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBountyRequirementForCrews() {
/* 807 */     return ((Integer)FactionsConfig.CREW_BOUNTY_REQUIREMENT.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoshokuUnlockLogicExpBased() {
/* 812 */     return (AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.get() == HaoshokuUnlockLogic.EXPERIENCE || AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.get() == HaoshokuUnlockLogic.COMBINED);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHaoshokuUnlockLogicChanceBased() {
/* 817 */     return (AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.get() == HaoshokuUnlockLogic.RANDOM || AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.get() == HaoshokuUnlockLogic.COMBINED || AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.get() == HaoshokuUnlockLogic.TRUE_RANDOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public HaoshokuUnlockLogic getHaoshokuUnlockLogic() {
/* 822 */     return (HaoshokuUnlockLogic)AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChanceForAmbushSpawn() {
/* 827 */     return ((Integer)WorldEventsConfig.SPAWN_CHANCE_AMBUSH.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenAmbushSpawns() {
/* 832 */     return ((Integer)WorldEventsConfig.TIME_BETWEEN_AMBUSH_SPAWNS.get()).intValue() * 60 * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnAmbushes() {
/* 837 */     return (getChanceForAmbushSpawn() > 0 && getTimeBetweenAmbushSpawns() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChanceForTrainerSpawn() {
/* 842 */     return ((Integer)WorldEventsConfig.SPAWN_CHANCE_TRAINER.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenTrainerSpawns() {
/* 847 */     return ((Integer)WorldEventsConfig.TIME_BETWEEN_TRAINER_SPAWNS.get()).intValue() * 60 * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnTrainers() {
/* 852 */     return (getChanceForTrainerSpawn() > 0 && getTimeBetweenTrainerSpawns() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChanceForTraderSpawn() {
/* 857 */     return ((Integer)WorldEventsConfig.SPAWN_CHANCE_TRADER.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenTraderSpawns() {
/* 862 */     return ((Integer)WorldEventsConfig.TIME_BETWEEN_TRADER_SPAWNS.get()).intValue() * 60 * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnTraders() {
/* 867 */     return (getChanceForTraderSpawn() > 0 && getTimeBetweenTraderSpawns() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTimeBetweenPackages() {
/* 872 */     return ((Integer)FactionsConfig.TIME_BETWEEN_PACKAGE_DROPS.get()).intValue() * 20 * 60;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWantedPosterPackagesEnabled() {
/* 877 */     return (getTimeBetweenPackages() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDorikiRewardMultiplier() {
/* 882 */     return ((Double)GeneralConfig.DORIKI_REWARD_MULTIPLIER.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBellyRewardMultiplier() {
/* 887 */     return ((Double)GeneralConfig.BELLY_REWARD_MULTIPLIER.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBountyRewardMultiplier() {
/* 892 */     return ((Double)GeneralConfig.BOUNTY_REWARD_MULTIPLIER.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHakiExpMultiplier() {
/* 897 */     return ((Double)GeneralConfig.HAKI_EXP_MULTIPLIER.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLoyaltyMultiplier() {
/* 902 */     return ((Double)GeneralConfig.LOYALTY_MULTIPLIER.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuestProgressionEnabled() {
/* 907 */     return ((Boolean)GeneralConfig.ENABLE_STYLES_PROGRESSION.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuestsEnabled() {
/* 912 */     return ((Boolean)GeneralConfig.ENABLE_TRIALS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public List<AbilityCore> getBannedAbilities() {
/* 916 */     if (this.bannedAbilities.isEmpty()) {
/* 917 */       for (String str : AbilitiesConfig.BANNED_ABILITIES.get()) {
/* 918 */         ResourceLocation res = new ResourceLocation(str);
/* 919 */         AbilityCore ability = AbilityCore.get(res);
/* 920 */         if (ability != null) {
/* 921 */           this.bannedAbilities.add(ability);
/*     */         }
/*     */       } 
/*     */     }
/* 925 */     return this.bannedAbilities;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbilityInvulnerabilityEnabled() {
/* 930 */     return ((Boolean)this.abilityInvulnerability.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areExtraWaterChecksEnabled() {
/* 935 */     return ((Boolean)AbilitiesConfig.WATER_CHECKS.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLogiaDamageEffectEnabled() {
/* 940 */     return ((Boolean)AbilitiesConfig.LOGIA_RETURN_EFFECT.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doLogiasHaveHurtHakiLogic() {
/* 945 */     return (((LogiaProjectileHitLogic)AbilitiesConfig.LOGIA_PROJECTILE_HIT_LOGIC.get()).equals(LogiaProjectileHitLogic.HAKI) || ((LogiaProjectileHitLogic)AbilitiesConfig.LOGIA_PROJECTILE_HIT_LOGIC.get()).equals(LogiaProjectileHitLogic.EXTENDED));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doLogiasHaveHurtExtendedLogic() {
/* 950 */     return ((LogiaProjectileHitLogic)AbilitiesConfig.LOGIA_PROJECTILE_HIT_LOGIC.get()).equals(LogiaProjectileHitLogic.EXTENDED);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDevilFruitDropsFromLeavesChance() {
/* 955 */     return ((Double)AbilitiesConfig.DEVIL_FRUIT_DROP_FROM_LEAVES.get()).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExtraHeartsEnabled() {
/* 960 */     return ((Boolean)GeneralConfig.EXTRA_HEARTS.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMobRewardsEnabled() {
/* 965 */     return ((Boolean)GeneralConfig.MOB_REWARDS.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbilityGriefingEnabled() {
/* 970 */     return ((Boolean)AbilitiesConfig.ABILITY_GRIEFING.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpecialSourceEventsEnabled() {
/* 975 */     return ((Boolean)this.specialSourceEvents.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isYamiPowerEnabled() {
/* 980 */     return ((Boolean)AbilitiesConfig.YAMI_POWER.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAbilityFraudChecksEnabled() {
/* 985 */     return ((Boolean)AbilitiesConfig.ABILITY_FRAUD_CHECKS.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimumDorikiPerKillEnabled() {
/* 990 */     return ((Boolean)GeneralConfig.MINIMUM_DORIKI_PER_KILL.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKairosekiSpawnCount() {
/* 995 */     return ((Integer)WorldFeaturesConfig.KAIROSEKI_SPAWN_COUNT.get()).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\CommonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */