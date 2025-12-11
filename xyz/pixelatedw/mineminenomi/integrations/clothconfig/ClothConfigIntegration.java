/*     */ package xyz.pixelatedw.mineminenomi.integrations.clothconfig;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import me.shedaniel.clothconfig2.forge.api.AbstractConfigListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.api.ConfigBuilder;
/*     */ import me.shedaniel.clothconfig2.forge.api.ConfigCategory;
/*     */ import me.shedaniel.clothconfig2.forge.api.ConfigEntryBuilder;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.BooleanListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.ColorEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.DoubleListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.DropdownBoxEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.EnumListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.IntegerListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.IntegerListListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.IntegerSliderEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.LongListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.StringListListEntry;
/*     */ import me.shedaniel.clothconfig2.forge.impl.builders.DropdownMenuBuilder;
/*     */ import me.shedaniel.clothconfig2.forge.impl.builders.SubCategoryBuilder;
/*     */ import me.shedaniel.math.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mera.HikenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.config.AbilitiesConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.ChallengesConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.FactionsConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.GeneralConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.IntegrationConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.SystemConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.UIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.WorldEventsConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.WorldFeaturesConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.BooleanOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.ColorOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.DoubleOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.EnumOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.IntegerListOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.IntegerOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.LongOption;
/*     */ import xyz.pixelatedw.mineminenomi.config.options.StringListOption;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18nConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ClothConfigIntegration {
/*     */   static {
/*  59 */     CONFIG_FACTORY = ((client, parent) -> {
/*     */         ConfigBuilder builder = ConfigBuilder.create();
/*     */         int i = 1 + (new Random()).nextInt(3);
/*     */         builder.setTitle((ITextComponent)new TranslationTextComponent(ModI18nConfig.CONFIG_TITLE)).setDefaultBackgroundTexture(new ResourceLocation("mineminenomi:textures/blocks/poneglyph" + i + ".png"));
/*     */         ConfigEntryBuilder entryBuilder = builder.entryBuilder();
/*     */         ConfigCategory general = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.GENERAL_CATEGORY));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.EXTRA_HEARTS));
/*     */         general.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, GeneralConfig.HEALTH_GAIN_FREQUENCY));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.RACE_RANDOMIZER));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.ALLOW_MINK_RACE_SELECT));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.PUBLIC_REMOVEDF));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.PUBLIC_CHECK_FRUITS));
/*     */         general.addEntry((AbstractConfigListEntry)addIntField(entryBuilder, GeneralConfig.DORIKI_LIMIT));
/*     */         general.addEntry((AbstractConfigListEntry)addIntField(entryBuilder, GeneralConfig.HAKI_EXP_LIMIT));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.DESTROY_SPAWNER));
/*     */         general.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.DESTROY_WATER));
/*     */         SubCategoryBuilder npcs = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.NPCS_CATEGORY)).setExpanded(false);
/*     */         npcs.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.MOB_REWARDS));
/*     */         npcs.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.MINIMUM_DORIKI_PER_KILL));
/*     */         npcs.add((AbstractConfigListEntry)addDoubleField(entryBuilder, GeneralConfig.DORIKI_REWARD_MULTIPLIER));
/*     */         npcs.add((AbstractConfigListEntry)addDoubleField(entryBuilder, GeneralConfig.BELLY_REWARD_MULTIPLIER));
/*     */         npcs.add((AbstractConfigListEntry)addDoubleField(entryBuilder, GeneralConfig.BOUNTY_REWARD_MULTIPLIER));
/*     */         npcs.add((AbstractConfigListEntry)addDoubleField(entryBuilder, GeneralConfig.HAKI_EXP_MULTIPLIER));
/*     */         npcs.add((AbstractConfigListEntry)addDoubleField(entryBuilder, GeneralConfig.LOYALTY_MULTIPLIER));
/*     */         npcs.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.DESPAWN_WITH_NAMETAG));
/*     */         npcs.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.NATIVE_HAKI));
/*     */         general.addEntry((AbstractConfigListEntry)npcs.build());
/*     */         SubCategoryBuilder keepStats = entryBuilder.startSubCategory((ITextComponent)new StringTextComponent("Keep Stats")).setTooltip(new ITextComponent[] { (ITextComponent)new TranslationTextComponent(ModI18nConfig.KEEP_STATS_SUB_CATEGORY_TOOLTIP) }).setExpanded(false);
/*     */         keepStats.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.RACE_KEEP));
/*     */         keepStats.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.FACTION_KEEP));
/*     */         keepStats.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.FIGHTING_STYLE_KEEP));
/*     */         keepStats.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.DEVIL_FRUIT_KEEP));
/*     */         keepStats.add((AbstractConfigListEntry)addIntSlider(entryBuilder, GeneralConfig.DORIKI_KEEP_PERCENTAGE));
/*     */         keepStats.add((AbstractConfigListEntry)addIntSlider(entryBuilder, GeneralConfig.BOUNTY_KEEP_PERCENTAGE));
/*     */         keepStats.add((AbstractConfigListEntry)addIntSlider(entryBuilder, GeneralConfig.BELLY_KEEP_PERCENTAGE));
/*     */         keepStats.add((AbstractConfigListEntry)addIntSlider(entryBuilder, GeneralConfig.HAKI_EXP_KEEP_PERCENTAGE));
/*     */         keepStats.add((AbstractConfigListEntry)addIntSlider(entryBuilder, GeneralConfig.LOYALTY_KEEP_PERCENTAGE));
/*     */         general.addEntry((AbstractConfigListEntry)keepStats.build());
/*     */         SubCategoryBuilder quests = entryBuilder.startSubCategory((ITextComponent)new StringTextComponent("Quests & Trials")).setExpanded(false);
/*     */         quests.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.ENABLE_TRIALS));
/*     */         quests.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, GeneralConfig.ENABLE_STYLES_PROGRESSION));
/*     */         general.addEntry((AbstractConfigListEntry)quests.build());
/*     */         ConfigCategory abilities = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.ABILITIES_CATEGORY));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.ABILITY_GRIEFING));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.ABILITY_FRAUD_CHECKS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.ABILITY_BARS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.YAMI_POWER));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.ENABLE_AWAKENINGS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.OPEN_WORLD_FRUIT_USERS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.WATER_CHECKS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.SHARED_COOLDOWNS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.REMOVE_Y_RESTRICTION));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.RANDOMIZED_FRUITS));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.COMBAT_STATE_UPDATE_CHAT_MESSAGGE));
/*     */         abilities.addEntry((AbstractConfigListEntry)addDoubleField(entryBuilder, AbilitiesConfig.DEVIL_FRUIT_DROP_FROM_LEAVES));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.LOGIA_INVULNERABILITY));
/*     */         abilities.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.LOGIA_RETURN_EFFECT));
/*     */         abilities.addEntry((AbstractConfigListEntry)addEnumSelector(entryBuilder, AbilitiesConfig.LOGIA_PROJECTILE_HIT_LOGIC));
/*     */         abilities.addEntry((AbstractConfigListEntry)addStringList(entryBuilder, AbilitiesConfig.BANNED_ABILITIES));
/*     */         SubCategoryBuilder haki = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.HAKI_CATEGORY)).setExpanded(true);
/*     */         haki.add((AbstractConfigListEntry)addEnumSelector(entryBuilder, AbilitiesConfig.HAOSHOKU_HAKI_UNLOCK_LOGIC));
/*     */         haki.add((AbstractConfigListEntry)addEnumSelector(entryBuilder, AbilitiesConfig.HAOSHOKU_HAKI_COLORING_LOGIC));
/*     */         haki.add((AbstractConfigListEntry)addColorField(entryBuilder, AbilitiesConfig.HAKI_COLOR));
/*     */         abilities.addEntry((AbstractConfigListEntry)haki.build());
/*     */         SubCategoryBuilder onefruit = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.ONE_FRUIT_CATEGORY)).setExpanded(false);
/*     */         onefruit.add((AbstractConfigListEntry)addEnumSelector(entryBuilder, AbilitiesConfig.ONE_FRUIT_PER_WORLD));
/*     */         onefruit.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, AbilitiesConfig.UNABLE_TO_PICKUP_DF));
/*     */         onefruit.add((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.FRUITS_LIMIT_INVENTORY));
/*     */         onefruit.add((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.DAYS_FOR_INACTIVITY));
/*     */         abilities.addEntry((AbstractConfigListEntry)onefruit.build());
/*     */         SubCategoryBuilder ablprot = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.ABL_PROTECTION_CATEGORY)).setExpanded(false);
/*     */         ablprot.add((AbstractConfigListEntry)addStringList(entryBuilder, AbilitiesConfig.GLOBAL_PROTECTION_WHITELIST));
/*     */         ablprot.add((AbstractConfigListEntry)addLongField(entryBuilder, AbilitiesConfig.GLOBAL_PROTECTION_RESTORATION_GRACE));
/*     */         abilities.addEntry((AbstractConfigListEntry)ablprot.build());
/*     */         SubCategoryBuilder dfrespawns = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.DF_RESPAWNS_CATEGORY)).setExpanded(false);
/*     */         dfrespawns.add((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.DROPPED_APPLES_RESPAWN_CHANCE));
/*     */         dfrespawns.add((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.ENTITY_INVENTORY_APPLES_RESPAWN_CHANCE));
/*     */         dfrespawns.add((AbstractConfigListEntry)addIntSlider(entryBuilder, AbilitiesConfig.CHESTS_APPLES_RESPAWN_CHANCE));
/*     */         abilities.addEntry((AbstractConfigListEntry)dfrespawns.build());
/*     */         ConfigCategory factions = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.FACTION_CATEGORY));
/*     */         factions.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, FactionsConfig.DISABLE_FRIENDLY_FIRE));
/*     */         SubCategoryBuilder bounty = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.BOUNTY_SUB_CATEGORY)).setExpanded(false);
/*     */         bounty.add((AbstractConfigListEntry)addIntSlider(entryBuilder, FactionsConfig.TIME_BETWEEN_PACKAGE_DROPS));
/*     */         factions.addEntry((AbstractConfigListEntry)bounty.build());
/*     */         SubCategoryBuilder crew = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.CREW_SUB_CATEGORY)).setExpanded(false);
/*     */         crew.add((AbstractConfigListEntry)addIntField(entryBuilder, FactionsConfig.CREW_BOUNTY_REQUIREMENT));
/*     */         crew.add((AbstractConfigListEntry)addBooleanToggle(entryBuilder, FactionsConfig.WORLD_MESSAGE_ON_CREW_CREATE));
/*     */         factions.addEntry((AbstractConfigListEntry)crew.build());
/*     */         ConfigCategory challenges = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.CHALLENGES_CATEGORY));
/*     */         challenges.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, ChallengesConfig.ENABLE_CHALLENGES));
/*     */         challenges.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, ChallengesConfig.CHALLENGE_CACHING));
/*     */         challenges.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, ChallengesConfig.RETURN_TO_SAFETY));
/*     */         challenges.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, ChallengesConfig.ARENA_CONSTRUCTION_SPEED));
/*     */         challenges.addEntry((AbstractConfigListEntry)addIntList(entryBuilder, ChallengesConfig.DORIKI_REWARD_POOL));
/*     */         challenges.addEntry((AbstractConfigListEntry)addIntList(entryBuilder, ChallengesConfig.BELLY_REWARD_POOL));
/*     */         challenges.addEntry((AbstractConfigListEntry)addIntList(entryBuilder, ChallengesConfig.HAKI_REWARD_POOL));
/*     */         ConfigCategory worldEvents = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.WORLD_EVENTS_CATEGORY));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, WorldEventsConfig.SPAWN_WORLD_HUMANOIDS));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, WorldEventsConfig.SPAWN_WORLD_ANIMALS));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, WorldEventsConfig.SPAWN_NOTORIOUS_TARGETS));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, WorldEventsConfig.SPAWN_CARAVANS));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, WorldEventsConfig.SPAWN_CELESTIAL_VISITS));
/*     */         SubCategoryBuilder traders = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.TRADERS_SUB_CATEGORY)).setExpanded(false);
/*     */         traders.add((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldEventsConfig.TIME_BETWEEN_TRADER_SPAWNS));
/*     */         traders.add((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldEventsConfig.SPAWN_CHANCE_TRADER));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)traders.build());
/*     */         SubCategoryBuilder trainers = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.TRAINERS_SUB_CATEGORY)).setExpanded(false);
/*     */         trainers.add((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldEventsConfig.TIME_BETWEEN_TRAINER_SPAWNS));
/*     */         trainers.add((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldEventsConfig.SPAWN_CHANCE_TRAINER));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)trainers.build());
/*     */         SubCategoryBuilder ambushes = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.AMBUSHES_SUB_CATEGORY)).setExpanded(false);
/*     */         ambushes.add((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldEventsConfig.TIME_BETWEEN_AMBUSH_SPAWNS));
/*     */         ambushes.add((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldEventsConfig.SPAWN_CHANCE_AMBUSH));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)ambushes.build());
/*     */         SubCategoryBuilder spawnChances = entryBuilder.startSubCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.SPAWN_CHANCES_CATEGORY)).setExpanded(false);
/*     */         spawnChances.add((AbstractConfigListEntry)addDoubleField(entryBuilder, WorldEventsConfig.GRUNT_SPAWN_CHANCE));
/*     */         spawnChances.add((AbstractConfigListEntry)addDoubleField(entryBuilder, WorldEventsConfig.BRUTE_SPAWN_CHANCE));
/*     */         spawnChances.add((AbstractConfigListEntry)addDoubleField(entryBuilder, WorldEventsConfig.SNIPER_SPAWN_CHANCE));
/*     */         spawnChances.add((AbstractConfigListEntry)addDoubleField(entryBuilder, WorldEventsConfig.CAPTAIN_SPAWN_CHANCE));
/*     */         spawnChances.add((AbstractConfigListEntry)addDoubleField(entryBuilder, WorldEventsConfig.PACIFISTA_SPAWN_CHANCE));
/*     */         worldEvents.addEntry((AbstractConfigListEntry)spawnChances.build());
/*     */         ConfigCategory worldFeatures = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.STRUCTURES_CATEGORY));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, WorldFeaturesConfig.SPAWN_BIOMES));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_TRAINING_STRUCTURES));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_SKY_ISLANDS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_GHOST_SHIPS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_SMALL_SHIPS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_MEDIUM_SHIPS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_LARGE_SHIPS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_CAMPS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_SMALL_BASES));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_LARGE_BASES));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_WATCH_TOWERS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.SPAWN_CHANCE_PONEGLYPHS));
/*     */         worldFeatures.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, WorldFeaturesConfig.KAIROSEKI_SPAWN_COUNT));
/*     */         ConfigCategory ui = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.UI_CATEGORY));
/*     */         ui.addEntry((AbstractConfigListEntry)addIntSlider(entryBuilder, UIConfig.ABILITY_BARS_ON_SCREEN));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.SHOW_KEYBIND));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.MERGE_ABILITY_BONUSES));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.HIDE_ABILITY_STATS));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.SIMPLE_DISPLAYS));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.USE_HEARTS_BAR));
/*     */         ui.addEntry((AbstractConfigListEntry)addEnumSelector(entryBuilder, UIConfig.SLOT_NUMBER_DISPLAY));
/*     */         ui.addEntry((AbstractConfigListEntry)addEnumSelector(entryBuilder, UIConfig.ABILITY_LIST_COMPACTNESS));
/*     */         ui.addEntry((AbstractConfigListEntry)addEnumSelector(entryBuilder, UIConfig.ABILITY_LIST_SELECTION));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.ABILITY_LIST_SHOW_TOOLTIPS));
/*     */         ui.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, UIConfig.ABILITY_LIST_SHOW_HELP));
/*     */         ConfigCategory integrations = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.INTEGRATIONS_CATEGORY));
/*     */         integrations.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, IntegrationConfig.VISUAL_ONLY_CURIO));
/*     */         ConfigCategory system = builder.getOrCreateCategory((ITextComponent)new TranslationTextComponent(ModI18nConfig.SYSTEM_CATEGORY));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.MASTER_COMMAND));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.UPDATE_MESSAGE));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.MOD_SPLASH_TEXT));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.BLUE_GORO));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.EXPERIMENTAL_SPHERES));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.EXPERIMENTAL_TIMERS));
/*     */         system.addEntry((AbstractConfigListEntry)addBooleanToggle(entryBuilder, SystemConfig.VILLAGE_COMPAT));
/*     */         builder.setParentScreen(parent);
/*     */         return builder.build();
/*     */       });
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final BiFunction<Minecraft, Screen, Screen> CONFIG_FACTORY;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ColorEntry addColorField(ConfigEntryBuilder entryBuilder, ColorOption option) {
/* 252 */     int color = WyHelper.hexToRGB((String)option.getValue().get()).getRGB();
/* 253 */     return entryBuilder.startColorField(option.getTitleComponent(), Color.ofOpaque(color))
/* 254 */       .setDefaultValue(16711680)
/* 255 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 256 */         }).setSaveConsumer(o -> option.saveColor(Color.ofOpaque(o.intValue())))
/* 257 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static StringListListEntry addStringList(ConfigEntryBuilder entryBuilder, StringListOption option) {
/* 262 */     return entryBuilder.startStrList(option.getTitleComponent(), (List)option.getValue().get())
/* 263 */       .setDefaultValue(option.getDefault())
/* 264 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 265 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 266 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static IntegerListListEntry addIntList(ConfigEntryBuilder entryBuilder, IntegerListOption option) {
/* 271 */     return entryBuilder.startIntList(option.getTitleComponent(), (List)option.getValue().get())
/* 272 */       .setDefaultValue(option.getDefault())
/* 273 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 274 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 275 */       .build();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static DropdownBoxEntry addItemDropdown(ConfigEntryBuilder entryBuilder) {
/* 281 */     DropdownBoxEntry.SelectionTopCellElement topCell = DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(Items.field_151034_e);
/* 282 */     return entryBuilder.startDropdownMenu((ITextComponent)new StringTextComponent("Title"), topCell, DropdownMenuBuilder.CellCreatorBuilder.ofItemObject())
/* 283 */       .setDefaultValue(null)
/* 284 */       .setSelections((Iterable)ForgeRegistries.ITEMS.getValues().stream()
/* 285 */         .sorted(Comparator.comparing(Item::toString))
/* 286 */         .collect(Collectors.toCollection(java.util.LinkedHashSet::new)))
/* 287 */       .setSaveConsumer(item -> System.out.println("save this " + item))
/* 288 */       .build();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static DropdownBoxEntry addAbilityDropdown(ConfigEntryBuilder entryBuilder) {
/* 294 */     DropdownBoxEntry.SelectionTopCellElement<AbilityCore> topCell = AbilityDropdownEntry.ofAbilityObject(HikenAbility.INSTANCE);
/* 295 */     return entryBuilder.startDropdownMenu((ITextComponent)new StringTextComponent("Title"), topCell, AbilityDropdownEntry.ofAbilityObject())
/* 296 */       .setDefaultValue(null)
/* 297 */       .setSelections((Iterable)ModRegistries.ABILITIES.getValues().stream()
/* 298 */         .sorted(Comparator.comparing(AbilityCore::toString))
/* 299 */         .collect(Collectors.toCollection(java.util.LinkedHashSet::new)))
/* 300 */       .setSaveConsumer(item -> System.out.println("save this " + item))
/* 301 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends Enum<T>> EnumListEntry<T> addEnumSelector(ConfigEntryBuilder entryBuilder, EnumOption<T> option) {
/* 306 */     return entryBuilder
/* 307 */       .startEnumSelector(option.getTitleComponent(), option.getEnumClass(), (Enum)option.getValue().get())
/* 308 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 309 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 310 */       .setDefaultValue((Enum)option.getDefault())
/* 311 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static BooleanListEntry addBooleanToggle(ConfigEntryBuilder entryBuilder, BooleanOption option) {
/* 316 */     return entryBuilder
/* 317 */       .startBooleanToggle(option.getTitleComponent(), ((Boolean)option.getValue().get()).booleanValue())
/* 318 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 319 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 320 */       .setDefaultValue(((Boolean)option.getDefault()).booleanValue())
/* 321 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static DoubleListEntry addDoubleField(ConfigEntryBuilder entryBuilder, DoubleOption option) {
/* 326 */     return entryBuilder
/* 327 */       .startDoubleField(option.getTitleComponent(), ((Double)option.getValue().get()).doubleValue())
/* 328 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 329 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 330 */       .setMin(((Double)option.getMin()).doubleValue())
/* 331 */       .setMax(((Double)option.getMax()).doubleValue())
/* 332 */       .setDefaultValue(((Double)option.getDefault()).doubleValue())
/* 333 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static IntegerListEntry addIntField(ConfigEntryBuilder entryBuilder, IntegerOption option) {
/* 338 */     return entryBuilder
/* 339 */       .startIntField(option.getTitleComponent(), ((Integer)option.getValue().get()).intValue())
/* 340 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 341 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 342 */       .setMin(((Integer)option.getMin()).intValue())
/* 343 */       .setMax(((Integer)option.getMax()).intValue())
/* 344 */       .setDefaultValue(((Integer)option.getDefault()).intValue())
/* 345 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static IntegerSliderEntry addIntSlider(ConfigEntryBuilder entryBuilder, IntegerOption option) {
/* 350 */     return entryBuilder
/* 351 */       .startIntSlider(option.getTitleComponent(), ((Integer)option.getValue().get()).intValue(), ((Integer)option.getMin()).intValue(), ((Integer)option.getMax()).intValue())
/* 352 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 353 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 354 */       .setMin(((Integer)option.getMin()).intValue())
/* 355 */       .setMax(((Integer)option.getMax()).intValue())
/* 356 */       .setDefaultValue(((Integer)option.getDefault()).intValue())
/* 357 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   private static LongListEntry addLongField(ConfigEntryBuilder entryBuilder, LongOption option) {
/* 362 */     return entryBuilder
/* 363 */       .startLongField(option.getTitleComponent(), ((Long)option.getValue().get()).longValue())
/* 364 */       .setTooltip(new ITextComponent[] { option.getDescriptionComponent()
/* 365 */         }).setSaveConsumer(o -> option.getValue().set(o))
/* 366 */       .setMin(((Long)option.getMin()).longValue())
/* 367 */       .setMax(((Long)option.getMax()).longValue())
/* 368 */       .setDefaultValue(((Long)option.getDefault()).longValue())
/* 369 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\clothconfig\ClothConfigIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */