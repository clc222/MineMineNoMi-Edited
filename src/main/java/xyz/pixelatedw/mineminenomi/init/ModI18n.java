/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ public class ModI18n
/*     */ {
/*  11 */   public static final String CATEGORY_GENERAL = WyRegistry.registerName("category.mineminenomi.generic", "Mine Mine no Mi - Keys");
/*  12 */   public static final String CATEGORY_COMBATBAR = WyRegistry.registerName("category.mineminenomi.combatbar", "Mine Mine no Mi - Combat Bar");
/*     */   
/*  14 */   public static final String KEY_PLAYER = WyRegistry.registerName("key.playerui", "Player Stats");
/*     */   
/*  16 */   public static final String KEY_COMBATMODE = WyRegistry.registerName("key.combatmode", "Combat Mode");
/*     */   
/*  18 */   public static final String KEY_NEXT_COMBAT_BAR = WyRegistry.registerName("key.next_combat_bar", "Next Combat Bar");
/*  19 */   public static final String KEY_PREV_COMBAT_BAR = WyRegistry.registerName("key.prev_combat_bar", "Previous Combat Bar");
/*  20 */   public static final String KEY_LAST_COMBAT_BAR = WyRegistry.registerName("key.last_combat_bar", "Last Combat Bar");
/*  21 */   public static final String KEY_CHANGE_ABILITY_MODE = WyRegistry.registerName("key.change_ability_mode", "Change Ability Mode");
/*  22 */   public static final String KEY_VEHICLE_ALT_MODE = WyRegistry.registerName("key.vehicle_alt_mode", "Toggle Vehicle Mode");
/*  23 */   public static final String KEY_OPEN_ABILITIES = WyRegistry.registerName("key.open_abilities", "Open Abilities Menu");
/*  24 */   public static final String KEY_OPEN_CHALLENGES = WyRegistry.registerName("key.open_challenges", "Open Challenges Menu");
/*  25 */   public static final String KEY_OPEN_QUESTS = WyRegistry.registerName("key.open_quests", "Open Quests Menu");
/*  26 */   public static final String KEY_OPEN_CREW = WyRegistry.registerName("key.open_crew", "Open Crew Menu");
/*     */   
/*  28 */   public static final String KEY_BAR1_COMBATSLOT1 = WyRegistry.registerName("key.combatbar.1.slot.1", "Bar 1 - Ability 1");
/*  29 */   public static final String KEY_BAR1_COMBATSLOT2 = WyRegistry.registerName("key.combatbar.1.slot.2", "Bar 1 - Ability 2");
/*  30 */   public static final String KEY_BAR1_COMBATSLOT3 = WyRegistry.registerName("key.combatbar.1.slot.3", "Bar 1 - Ability 3");
/*  31 */   public static final String KEY_BAR1_COMBATSLOT4 = WyRegistry.registerName("key.combatbar.1.slot.4", "Bar 1 - Ability 4");
/*  32 */   public static final String KEY_BAR1_COMBATSLOT5 = WyRegistry.registerName("key.combatbar.1.slot.5", "Bar 1 - Ability 5");
/*  33 */   public static final String KEY_BAR1_COMBATSLOT6 = WyRegistry.registerName("key.combatbar.1.slot.6", "Bar 1 - Ability 6");
/*  34 */   public static final String KEY_BAR1_COMBATSLOT7 = WyRegistry.registerName("key.combatbar.1.slot.7", "Bar 1 - Ability 7");
/*  35 */   public static final String KEY_BAR1_COMBATSLOT8 = WyRegistry.registerName("key.combatbar.1.slot.8", "Bar 1 - Ability 8");
/*     */   
/*  37 */   public static final String KEY_BAR2_COMBATSLOT1 = WyRegistry.registerName("key.combatbar.2.slot.1", "Bar 2 - Ability 1");
/*  38 */   public static final String KEY_BAR2_COMBATSLOT2 = WyRegistry.registerName("key.combatbar.2.slot.2", "Bar 2 - Ability 2");
/*  39 */   public static final String KEY_BAR2_COMBATSLOT3 = WyRegistry.registerName("key.combatbar.2.slot.3", "Bar 2 - Ability 3");
/*  40 */   public static final String KEY_BAR2_COMBATSLOT4 = WyRegistry.registerName("key.combatbar.2.slot.4", "Bar 2 - Ability 4");
/*  41 */   public static final String KEY_BAR2_COMBATSLOT5 = WyRegistry.registerName("key.combatbar.2.slot.5", "Bar 2 - Ability 5");
/*  42 */   public static final String KEY_BAR2_COMBATSLOT6 = WyRegistry.registerName("key.combatbar.2.slot.6", "Bar 2 - Ability 6");
/*  43 */   public static final String KEY_BAR2_COMBATSLOT7 = WyRegistry.registerName("key.combatbar.2.slot.7", "Bar 2 - Ability 7");
/*  44 */   public static final String KEY_BAR2_COMBATSLOT8 = WyRegistry.registerName("key.combatbar.2.slot.8", "Bar 2 - Ability 8");
/*     */   
/*  46 */   public static final String KEY_BAR3_COMBATSLOT1 = WyRegistry.registerName("key.combatbar.3.slot.1", "Bar 3 - Ability 1");
/*  47 */   public static final String KEY_BAR3_COMBATSLOT2 = WyRegistry.registerName("key.combatbar.3.slot.2", "Bar 3 - Ability 2");
/*  48 */   public static final String KEY_BAR3_COMBATSLOT3 = WyRegistry.registerName("key.combatbar.3.slot.3", "Bar 3 - Ability 3");
/*  49 */   public static final String KEY_BAR3_COMBATSLOT4 = WyRegistry.registerName("key.combatbar.3.slot.4", "Bar 3 - Ability 4");
/*  50 */   public static final String KEY_BAR3_COMBATSLOT5 = WyRegistry.registerName("key.combatbar.3.slot.5", "Bar 3 - Ability 5");
/*  51 */   public static final String KEY_BAR3_COMBATSLOT6 = WyRegistry.registerName("key.combatbar.3.slot.6", "Bar 3 - Ability 6");
/*  52 */   public static final String KEY_BAR3_COMBATSLOT7 = WyRegistry.registerName("key.combatbar.3.slot.7", "Bar 3 - Ability 7");
/*  53 */   public static final String KEY_BAR3_COMBATSLOT8 = WyRegistry.registerName("key.combatbar.3.slot.8", "Bar 3 - Ability 8");
/*     */   
/*  55 */   public static final String KEY_BAR_SHORTCUT1 = WyRegistry.registerName("key.combatbar_shortcut.1", "Move to Combat Bar Set 1");
/*  56 */   public static final String KEY_BAR_SHORTCUT2 = WyRegistry.registerName("key.combatbar_shortcut.2", "Move to Combat Bar Set 2");
/*  57 */   public static final String KEY_BAR_SHORTCUT3 = WyRegistry.registerName("key.combatbar_shortcut.3", "Move to Combat Bar Set 3");
/*  58 */   public static final String KEY_BAR_SHORTCUT4 = WyRegistry.registerName("key.combatbar_shortcut.4", "Move to Combat Bar Set 4");
/*  59 */   public static final String KEY_BAR_SHORTCUT5 = WyRegistry.registerName("key.combatbar_shortcut.5", "Move to Combat Bar Set 5");
/*  60 */   public static final String KEY_BAR_SHORTCUT6 = WyRegistry.registerName("key.combatbar_shortcut.6", "Move to Combat Bar Set 6");
/*  61 */   public static final String KEY_BAR_SHORTCUT7 = WyRegistry.registerName("key.combatbar_shortcut.7", "Move to Combat Bar Set 7");
/*  62 */   public static final String KEY_BAR_SHORTCUT8 = WyRegistry.registerName("key.combatbar_shortcut.8", "Move to Combat Bar Set 8");
/*  63 */   public static final String KEY_BAR_SHORTCUT9 = WyRegistry.registerName("key.combatbar_shortcut.9", "Move to Combat Bar Set 9");
/*  64 */   public static final String KEY_BAR_SHORTCUT10 = WyRegistry.registerName("key.combatbar_shortcut.10", "Move to Combat Bar Set 10");
/*     */   
/*  66 */   public static final TranslationTextComponent FACTION_NAME = new TranslationTextComponent(WyRegistry.registerName("faction.name", "Faction"));
/*  67 */   public static final TranslationTextComponent FACTION_EMPTY = new TranslationTextComponent(WyRegistry.registerName("faction.empty", "No Faction"));
/*  68 */   public static final TranslationTextComponent FACTION_PIRATE = new TranslationTextComponent(WyRegistry.registerName("faction.pirate", "Pirate"));
/*  69 */   public static final TranslationTextComponent FACTION_MARINE = new TranslationTextComponent(WyRegistry.registerName("faction.marine", "Marine"));
/*  70 */   public static final TranslationTextComponent FACTION_BOUNTY_HUNTER = new TranslationTextComponent(WyRegistry.registerName("faction.bounty_hunter", "Bounty Hunter"));
/*  71 */   public static final TranslationTextComponent FACTION_REVOLUTIONARY = new TranslationTextComponent(WyRegistry.registerName("faction.revolutionary", "Revolutionary"));
/*     */   
/*  73 */   public static final TranslationTextComponent RACE_NAME = new TranslationTextComponent(WyRegistry.registerName("race.name", "Race"));
/*  74 */   public static final TranslationTextComponent RACE_EMPTY = new TranslationTextComponent(WyRegistry.registerName("race.empty", "No Race"));
/*  75 */   public static final TranslationTextComponent RACE_HUMAN = new TranslationTextComponent(WyRegistry.registerName("race.human", "Human"));
/*  76 */   public static final TranslationTextComponent RACE_FISHMAN = new TranslationTextComponent(WyRegistry.registerName("race.fishman", "Fishman"));
/*  77 */   public static final TranslationTextComponent RACE_CYBORG = new TranslationTextComponent(WyRegistry.registerName("race.cyborg", "Cyborg"));
/*  78 */   public static final TranslationTextComponent RACE_MINK = new TranslationTextComponent(WyRegistry.registerName("race.mink", "Mink"));
/*     */   
/*  80 */   public static final TranslationTextComponent STYLE_NAME = new TranslationTextComponent(WyRegistry.registerName("style.name", "Fighting Style"));
/*  81 */   public static final TranslationTextComponent STYLE_EMPTY = new TranslationTextComponent(WyRegistry.registerName("style.empty", "No Fighting Style"));
/*  82 */   public static final TranslationTextComponent STYLE_SWORDSMAN = new TranslationTextComponent(WyRegistry.registerName("style.swordsman", "Swordsman"));
/*  83 */   public static final TranslationTextComponent STYLE_SNIPER = new TranslationTextComponent(WyRegistry.registerName("style.sniper", "Sniper"));
/*  84 */   public static final TranslationTextComponent STYLE_DOCTOR = new TranslationTextComponent(WyRegistry.registerName("style.doctor", "Doctor"));
/*  85 */   public static final TranslationTextComponent STYLE_ART_OF_WEATHER = new TranslationTextComponent(WyRegistry.registerName("style.art_of_weather", "Art of Weather"));
/*  86 */   public static final TranslationTextComponent STYLE_BLACK_LEG = new TranslationTextComponent(WyRegistry.registerName("style.black_leg", "Black Leg"));
/*  87 */   public static final TranslationTextComponent STYLE_BRAWLER = new TranslationTextComponent(WyRegistry.registerName("style.brawler", "Brawler"));
/*     */   
/*  89 */   public static final String GUI_ABILITIES = WyRegistry.registerName("gui.abilities", "Abilities");
/*  90 */   public static final String GUI_COLA = WyRegistry.registerName("gui.cola", "Cola");
/*  91 */   public static final String GUI_DORIKI = WyRegistry.registerName("gui.doriki", "Doriki");
/*  92 */   public static final String GUI_LEAVE = WyRegistry.registerName("gui.leave", "Leave");
/*  93 */   public static final TranslationTextComponent GUI_RANDOM = new TranslationTextComponent(WyRegistry.registerName("gui.random", "Random"));
/*  94 */   public static final TranslationTextComponent GUI_KARMA = new TranslationTextComponent(WyRegistry.registerName("gui.karma", "Karma"));
/*     */   
/*  96 */   public static final String GUI_QUESTS = WyRegistry.registerName("gui.quests", "Quests");
/*  97 */   public static final String GUI_QUEST_PROGRESS = WyRegistry.registerName("gui.quests.progress", "Progress");
/*  98 */   public static final String GUI_QUEST_ACCEPT = WyRegistry.registerName("gui.quests.accept", "Accept this quest ?");
/*     */   
/* 100 */   public static final String GUI_CREW = WyRegistry.registerName("gui.crew", "Crew");
/* 101 */   public static final String GUI_CREW_JOLLY_ROGER = WyRegistry.registerName("gui.crew_jolly_roger", "Crew's Jolly Roger");
/* 102 */   public static final String GUI_CREW_MEMBERS = WyRegistry.registerName("gui.crew_members", "Crew's Members");
/* 103 */   public static final String GUI_CHANGE_JOLLY_ROGER = WyRegistry.registerName("gui.crew_change_jolly_roger", "Change Jolly Roger");
/* 104 */   public static final String GUI_DOWNLOAD_JOLLY_ROGER = WyRegistry.registerName("gui.crew_download_jolly_roger", "Download Jolly Roger");
/* 105 */   public static final String GUI_SAVED_JOLLY_ROGER = WyRegistry.registerName("gui.crew_saved_jolly_roger", "Saved Jolly Roger");
/* 106 */   public static final TranslationTextComponent GUI_CHALLENGES = new TranslationTextComponent(WyRegistry.registerName("gui.challenges", "Challenges"));
/* 107 */   public static final String GUI_INVITES_AMOUNT = WyRegistry.registerName("gui.invites_amount", "Invites: %s");
/*     */   
/* 109 */   public static final String GUI_ACCEPT = WyRegistry.registerName("gui.accept", "Accept");
/* 110 */   public static final String GUI_DECLINE = WyRegistry.registerName("gui.decline", "Decline");
/* 111 */   public static final String GUI_BUY = WyRegistry.registerName("gui.buy", "Buy");
/* 112 */   public static final String GUI_SELL = WyRegistry.registerName("gui.sell", "Sell");
/* 113 */   public static final String GUI_NAME = WyRegistry.registerName("gui.name", "Name");
/* 114 */   public static final String GUI_PRICE = WyRegistry.registerName("gui.price", "Price");
/* 115 */   public static final String GUI_EMPTY = WyRegistry.registerName("gui.empty", "Empty");
/* 116 */   public static final String GUI_RED = WyRegistry.registerName("gui.red", "Red");
/* 117 */   public static final String GUI_GREEN = WyRegistry.registerName("gui.green", "Green");
/* 118 */   public static final String GUI_BLUE = WyRegistry.registerName("gui.blue", "Blue");
/* 119 */   public static final String GUI_BASE = WyRegistry.registerName("gui.base", "Base");
/* 120 */   public static final String GUI_BACKGROUND = WyRegistry.registerName("gui.background", "Background");
/* 121 */   public static final String GUI_DETAIL = WyRegistry.registerName("gui.detail", "Detail");
/* 122 */   public static final String GUI_FINISH = WyRegistry.registerName("gui.finish", "Finish");
/* 123 */   public static final String GUI_CLICK_TO_SKIP = WyRegistry.registerName("gui.click_to_skip", "Click to Skip");
/* 124 */   public static final TranslationTextComponent GUI_COMBAT_CANNOT_USE = new TranslationTextComponent(WyRegistry.registerName("gui.cannot_use_during_combat", "Cannot use this during combat!"));
/* 125 */   public static final TranslationTextComponent GUI_CHALLENGE_DIM_ACCESS_MENU = new TranslationTextComponent(WyRegistry.registerName("gui.cannot_access_menu_during_challenge", "Cannot access this menu during a challenge!"));
/* 126 */   public static final TranslationTextComponent GUI_NO_CHALLENGES_AVAILABLE = new TranslationTextComponent(WyRegistry.registerName("gui.no_challenges", "No challenges available!"));
/* 127 */   public static final TranslationTextComponent GUI_ALL = new TranslationTextComponent(WyRegistry.registerName("gui.all", "All"));
/* 128 */   public static final TranslationTextComponent GUI_TIME_LIMIT = new TranslationTextComponent(WyRegistry.registerName("gui.time_limit", "Time Limit"));
/* 129 */   public static final TranslationTextComponent GUI_PERSONAL_BEST = new TranslationTextComponent(WyRegistry.registerName("gui.personal_best", "Personal Best"));
/* 130 */   public static final TranslationTextComponent GUI_DIFFICULTY = new TranslationTextComponent(WyRegistry.registerName("gui.difficulty", "Difficulty"));
/* 131 */   public static final TranslationTextComponent GUI_RESTRICTIONS = new TranslationTextComponent(WyRegistry.registerName("gui.restrictions", "Restrictions"));
/* 132 */   public static final TranslationTextComponent GUI_START = new TranslationTextComponent(WyRegistry.registerName("gui.start", "Start"));
/* 133 */   public static final TranslationTextComponent GUI_SELECT_ONE = new TranslationTextComponent(WyRegistry.registerName("gui.select_one", "Select One"));
/* 134 */   public static final TranslationTextComponent GUI_TRAINING = new TranslationTextComponent(WyRegistry.registerName("gui.training", "Training"));
/* 135 */   public static final TranslationTextComponent GUI_SHOW_ABILITY_STATS = new TranslationTextComponent(WyRegistry.registerName("gui.show_ability_stats", "Hold <SHIFT> to show ability stats."));
/*     */   
/* 137 */   public static final String CONTAINER_WHITE_WALKIE_STORAGE = WyRegistry.registerName("container.white_walkie_storage", "White Walkie Storage");
/*     */   
/* 139 */   public static final String TRAINER_NO_QUESTS_AVAILABLE = WyRegistry.registerName("trainer.no_quests_available", "I don't have any quests for you at the moment.");
/* 140 */   public static final String TRAINER_NO_TRIALS_AVAILABLE = WyRegistry.registerName("trainer.no_trials_available", "I don't have any trials for you at the moment.");
/* 141 */   public static final String TRAINER_TOO_MANY_QUESTS = WyRegistry.registerName("trainer.too_many_quests", "You cannot accept any more quests");
/* 142 */   public static final String TRAINER_ALREADY_IN_PROGRESS = WyRegistry.registerName("trainer.already_in_progress", "Already in progress!");
/* 143 */   public static final String TRAINER_NO_OBJECTIVES_LEFT = WyRegistry.registerName("trainer.no_objectives_left", "No objectives left!");
/* 144 */   public static final String TRAINER_CANT_LEARN_HAKI = WyRegistry.registerName("trainer.cant_learn_haki", "You're not strong enough yet to start training your haki.");
/* 145 */   public static final String TRAINER_HOW_TO_BUSOSHOKU = WyRegistry.registerName("trainer.how_to_hardening", "How to train my Busoshoku Haki ?");
/* 146 */   public static final String TRAINER_HOW_TO_OBSERVATION = WyRegistry.registerName("trainer.how_to_observation", "How to train my Observation Haki ?");
/* 147 */   public static final String TRAINER_HOW_TO_BUSOSHOKU_MESSAGE = WyRegistry.registerName("trainer.how_to_hardening.message", "Killing enemies is the best way to train your Busoshoku Haki.");
/* 148 */   public static final String TRAINER_HOW_TO_OBSERVATION_MESSAGE = WyRegistry.registerName("trainer.how_to_observation.message", "The best way to train your Observation Haki is by getting hit, the more you get hit the more you'll learn how to avoid it.");
/* 149 */   public static final String TRAINER_HOW_TO_OBSERVATION_MESSAGE_2 = WyRegistry.registerName("trainer.how_to_observation.message_2", "The best way to train your Observation Haki further is by using Aura while fighting.");
/* 150 */   public static final String TRAINER_MY_HAKI = WyRegistry.registerName("trainer.my_haki", "Check my haki progress!");
/* 151 */   public static final String TRAINER_HAKI_RANK = WyRegistry.registerName("trainer.haki_rank", "Your current level is %s.");
/* 152 */   public static final String TRAINER_KEEP_TRAINING = WyRegistry.registerName("trainer.keep_training", "You're about %s experience points away from unlocking %s. Keep training!");
/* 153 */   public static final String TRAINER_UNUSUAL_TRAINING = WyRegistry.registerName("trainer.unusual_training", "It's unusual for somebody to manifast their %s so late but you're extremely close!");
/* 154 */   public static final String TRAINER_FINISHED_HARDENING_TRAINING = WyRegistry.registerName("trainer.finished_hardening_training", "You've successfully mastered all the Hardening Haki moves!");
/* 155 */   public static final String TRAINER_FINISHED_IMBUING_TRAINING = WyRegistry.registerName("trainer.finished_imbuing_training", "You've successfully mastered all the Imbuing Haki moves!");
/* 156 */   public static final String TRAINER_FINISHED_OBSERVATION_TRAINING = WyRegistry.registerName("trainer.finished_observation_training", "You've successfully mastered all the Observation Haki moves!");
/*     */   
/* 158 */   public static final String TRADER_WELCOME_MESSAGE = WyRegistry.registerName("trader.welcome_message", "Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.");
/* 159 */   public static final String TRADER_NO_PIRATE = WyRegistry.registerName("trader.no_pirate", "I don't trade to Pirate scum");
/* 160 */   public static final String TRADER_NO_MARINE = WyRegistry.registerName("trader.no_marine", "I don't support the Marines.");
/* 161 */   public static final String TRADER_MINIMUM_EXTOL = WyRegistry.registerName("trader.minimum_extol", "You need a minimum of %s extol in order to convert it to belly");
/* 162 */   public static final String TRADER_SKYPIEAN_VEARTH = WyRegistry.registerName("trader.skypiean_vearth", "I'll give you %s extol per Vearth. How does that sound ?\nI've got only %s extol left in my pockets however so be fast!");
/* 163 */   public static final String TRADER_SKYPIEAN_NO_EXTOL = WyRegistry.registerName("trader.skypiean_no_extol", "I don't have any more extol to trade with you.");
/*     */   
/* 165 */   public static final String CREW_MESSAGE_ALREADY_IN_CREW = WyRegistry.registerName("crew.message.already_in_crew", "You're already in a Crew!");
/* 166 */   public static final String CREW_MESSAGE_BOUNTY_REQUIREMENT = WyRegistry.registerName("crew.message.bounty_requirement", "Bounty requirement not met!");
/* 167 */   public static final String CREW_MESSAGE_NEW_JOIN = WyRegistry.registerName("crew.message.new_join", "%s joined the crew.");
/* 168 */   public static final String CREW_MESSAGE_KICKED = WyRegistry.registerName("crew.message.kicked", "%s was kicked out of the crew.");
/* 169 */   public static final String CREW_MESSAGE_LEFT = WyRegistry.registerName("crew.message.left", "%s left the crew.");
/* 170 */   public static final String CREW_MESSAGE_NEW_CAPTAIN = WyRegistry.registerName("crew.message.new_captain", "%s is now the new captain of the crew.");
/* 171 */   public static final String CREW_MESSAGE_NEW_CREW = WyRegistry.registerName("crew.message.new_crew", "A new crew just formed, %s!");
/* 172 */   public static final String CREW_CAPTAIN = WyRegistry.registerName("gui.captain", "Captain");
/*     */   
/* 174 */   public static final String MARINE_TITLE_CHORE_BOY = WyRegistry.registerName("gui.marine_title.chore_boy", "Chore Boy");
/* 175 */   public static final String MARINE_TITLE_SEAMAN = WyRegistry.registerName("gui.marine_title.seaman", "Seaman");
/* 176 */   public static final String MARINE_TITLE_PETTY_OFFICER = WyRegistry.registerName("gui.marine_title.petty_officer", "Petty Officer");
/* 177 */   public static final String MARINE_TITLE_LIEUTENANT = WyRegistry.registerName("gui.marine_title.lieutenant", "Lieutenant");
/* 178 */   public static final String MARINE_TITLE_COMMANDER = WyRegistry.registerName("gui.marine_title.commander", "Commander");
/* 179 */   public static final String MARINE_TITLE_CAPTAIN = WyRegistry.registerName("gui.marine_title.captain", "Captain");
/* 180 */   public static final String MARINE_TITLE_COMMODORE = WyRegistry.registerName("gui.marine_title.commmodore", "Commodore");
/* 181 */   public static final String MARINE_TITLE_VICE_ADMIRAL = WyRegistry.registerName("gui.marine_title.vice_admiral", "Vice Admiral");
/* 182 */   public static final String MARINE_TITLE_ADMIRAL = WyRegistry.registerName("gui.marine_title.admiral", "Admiral");
/* 183 */   public static final String MARINE_TITLE_FLEET_ADMIRAL = WyRegistry.registerName("gui.marine_title.fleet_admiral", "Fleet Admiral");
/*     */   
/* 185 */   public static final String REVOLUTIONARY_TITLE_MEMBER = WyRegistry.registerName("gui.revolutionary_title.member", "Member");
/* 186 */   public static final String REVOLUTIONARY_TITLE_OFFICER = WyRegistry.registerName("gui.revolutionary_title.officer", "Officer");
/* 187 */   public static final String REVOLUTIONARY_TITLE_COMMANDER = WyRegistry.registerName("gui.revolutionary_title.commander", "Commander");
/* 188 */   public static final String REVOLUTIONARY_TITLE_CHIEF_OF_STAFF = WyRegistry.registerName("gui.revolutionary_title.chief_of_staff", "Chief of Staff");
/* 189 */   public static final String REVOLUTIONARY_TITLE_SUPREME_COMMANDER = WyRegistry.registerName("gui.revolutionary_title.supreme_commander", "Supreme Commander");
/*     */   
/* 191 */   public static final TranslationTextComponent ABILITY_NAME_RIDEABLE = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.rideable", "Rideable"));
/*     */   
/* 193 */   public static final String ABILITY_MESSAGE_NEED_FIST = WyRegistry.registerName("ability.item.message.need_fist", "You cannot use this ability while holding an item!");
/* 194 */   public static final String ABILITY_MESSAGE_NEED_SWORD = WyRegistry.registerName("ability.item.message.need_sword", "You need a sword to use this ability!");
/* 195 */   public static final String ABILITY_MESSAGE_NEED_BLUNT = WyRegistry.registerName("ability.item.message.need_blunt", "You need a blunt weapon to use this ability!");
/* 196 */   public static final String ABILITY_MESSAGE_NEED_TONFA = WyRegistry.registerName("ability.item.message.need_tonfa", "You need a tonfa to use this ability!");
/* 197 */   public static final String ABILITY_MESSAGE_NEED_MELEE_WEAPON = WyRegistry.registerName("ability.item.message.need_melee_weapon", "You need a melee weapon to use this ability!");
/* 198 */   public static final String ABILITY_MESSAGE_ONLY_IN_ROOM = WyRegistry.registerName("ability.item.message.only_in_room", "%s can only be used inside ROOM!");
/* 199 */   public static final String ABILITY_MESSAGE_NEED_MEDIC_BAG = WyRegistry.registerName("ability.item.message.need_medic_bag", "You need a medic bag equipped to use this ability!");
/* 200 */   public static final TranslationTextComponent ABILITY_MESSAGE_NEED_CLIMA_TACT = new TranslationTextComponent(WyRegistry.registerName("ability.item.message.need_clima_tact", "You need a clima tact to use this ability!"));
/* 201 */   public static final TranslationTextComponent ABILITY_MESSAGE_NOT_ENOUGH_COLA = new TranslationTextComponent(WyRegistry.registerName("ability.message.not_enough_cola", "Not enough Cola!"));
/* 202 */   public static final String ABILITY_MESSAGE_NOT_ENOUGH_SHADOWS = WyRegistry.registerName("ability.message.no_enough_shadows", "Not enough Shadows!");
/* 203 */   public static final TranslationTextComponent ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS = new TranslationTextComponent(WyRegistry.registerName("ability.message.not_enough_blocks", "Not enough blocks in the inventory!"));
/* 204 */   public static final TranslationTextComponent ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS = new TranslationTextComponent(WyRegistry.registerName("ability.message.not_enough_materials", "Not enough materials in the inventory!"));
/* 205 */   public static final String ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE = WyRegistry.registerName("ability.message.missing_dependency_single", "%s can only be used while %s is active!");
/* 206 */   public static final String ABILITY_MESSAGE_MISSING_DEPENDENCY_DOUBLE = WyRegistry.registerName("ability.message.missing_dependency_double", "%s can only be used while %s or %s is active!");
/* 207 */   public static final String ABILITY_MESSAGE_ONLY_IN_AIR = WyRegistry.registerName("ability.message.only_in_air", "%s can only be used while airborne!");
/* 208 */   public static final String ABILITY_MESSAGE_ONLY_IN_GROUND = WyRegistry.registerName("ability.message.only_in_ground", "%s can only be used while on ground!");
/* 209 */   public static final String ABILITY_MESSAGE_ONLY_WHEN_DRY = WyRegistry.registerName("ability.message.only_when_dry", "%s cannot be used while the user is wet!");
/* 210 */   public static final TranslationTextComponent ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT = new TranslationTextComponent(WyRegistry.registerName("ability.item.message.need_sorcery_clima_tact", "You need a Sorcery or higher grade clima tact to use this ability!"));
/* 211 */   public static final String ABILITY_MESSAGE_ONE_HAKI_TYPE = WyRegistry.registerName("ability.message.one_haki_type", "%s can't be used while another same type haki is active!");
/* 212 */   public static final String ABILITY_MESSAGE_CANNOT_USE_HERE = WyRegistry.registerName("ability.message.cannot_use_here", "Cannot use abilites in a restricted area!");
/* 213 */   public static final TranslationTextComponent ABILITY_MESSAGE_SUVIVAL_ONLY = new TranslationTextComponent(WyRegistry.registerName("ability.message.survival_only", "Cannot use this ability in creative!"));
/* 214 */   public static final String ABILITY_MESSAGE_NEED_HAKI = WyRegistry.registerName("ability.message.need_haki", "This ability can only be used while Busoshoku Full Body Haki is active!");
/* 215 */   public static final TranslationTextComponent ABILITY_MESSAGE_NEED_ELECLAW = new TranslationTextComponent(WyRegistry.registerName("ability.message.need_eleclaw", "This ability can only be used while Eleclaw is active!"));
/* 216 */   public static final String ABILITY_MESSAGE_NEED_SULONG = WyRegistry.registerName("ability.message.need_sulong", "This ability can only be used while Sulong is active!");
/* 217 */   public static final String ABILITY_MESSAGE_BODY = WyRegistry.registerName("ability.message.body", "Your body feels incapable of using this ability!");
/* 218 */   public static final TranslationTextComponent ABILITY_MESSAGE_NEED_MOON = new TranslationTextComponent(WyRegistry.registerName("ability.message.need_moon", "This ability can only be used while you are able to watch a full moon"));
/* 219 */   public static final String ABILITY_MESSAGE_NEED_DIABLE_JAMBE = WyRegistry.registerName("ability.message.need_diable_jambe", "This ability can only be used while Diable Jambe is active!");
/* 220 */   public static final String ABILITY_MESSAGE_NO_TARGET = WyRegistry.registerName("ability.message.no_target", "No valid target found!");
/* 221 */   public static final String ABILITY_MESSAGE_NOT_ENOUGH_SPACE = WyRegistry.registerName("ability.message.not_enough_space", "%s can't be used here because there is not enough space to transform!");
/* 222 */   public static final TranslationTextComponent ABILITY_MESSAGE_NO_LIMBS = new TranslationTextComponent(WyRegistry.registerName("ability.message.no_limbs", "Ability cannot be used because there are no limbs available!"));
/* 223 */   public static final String ABILITY_MESSAGE_STRONGER_HAKI = WyRegistry.registerName("ability.message.stronger_haki", "%s can't be used because your target's haki is stronger than yours!");
/* 224 */   public static final String ABILITY_MESSAGE_CANNOT_MOUNT_ZOAN = WyRegistry.registerName("ability.message.cannot_mount_zoan", "Cannot mount in this form!");
/* 225 */   public static final String ABILITY_MESSAGE_MANE_SWITCH_MEMORY = WyRegistry.registerName("ability.message.mane_switch_memory", "Changed Memory to %s (%s/%s)");
/* 226 */   public static final String ABILITY_MESSAGE_MANE_ADDED_MEMORY = WyRegistry.registerName("ability.message.mane_added_memory", "Added %s as a Memory");
/* 227 */   public static final TranslationTextComponent ABILITY_MESSAGE_GEAR_ACTIVE = new TranslationTextComponent(WyRegistry.registerName("ability.message.gear_active", "Cannot be used while another Gear ability is in use!"));
/* 228 */   public static final String ABILITY_MESSAGE_EMPTY_STACK = WyRegistry.registerName("ability.item.message.empty_stack", "Cannot equip because it's an empty stack!");
/* 229 */   public static final String ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND = WyRegistry.registerName("ability.item.message.another_item_in_hand", "Cannot equip while holding another item in hand!");
/* 230 */   public static final String ABILITY_MESSAGE_POOL_ALREADY_IN_USE = WyRegistry.registerName("ability.message.pool_already_in_use", "An ability linked with this one is already in use.");
/* 231 */   public static final String ABILITY_MESSAGE_CANT_MOVE = WyRegistry.registerName("ability.message.cant_move", "%s can't be used while stuck");
/* 232 */   public static final String ABILITY_MESSAGE_NEED_FIST_OR_CANNONBALLS = WyRegistry.registerName("ability.message.need_fist_or_cannonballs", "This ability can only be used when you have cannonballs in your inventory while your hand either is empty or contains cannonballs!");
/* 233 */   public static final String ABILITY_MESSAGE_ALT_MODE_CHANGE = WyRegistry.registerName("ability.message.alt_mode_change", "%s's mode set to %s");
/* 234 */   public static final String ABILITY_MESSAGE_NEED_NOT_DAI_ENKAI = WyRegistry.registerName("ability.message.need_not_dai_enkai", "You cannot use this ability while Dai Enkai: Entei or Dai Enkai: Onibi is active!");
/* 235 */   public static final String ABILITY_MESSAGE_NEED_THUNDERSTORM = WyRegistry.registerName("ability.message.need_thunderstorm", "This ability can only be used during a thunderstorm!");
/* 236 */   public static final String ABILITY_MESSAGE_NOT_ENOUGH_HAKI = WyRegistry.registerName("ability.message.not_enough_haki", "You do not have enough haki to use this ability!");
/* 237 */   public static final String ABILITY_MESSAGE_CANNOT_USE_TOGETHER = WyRegistry.registerName("ability.message.cannot_use_together", "%s cannot be used while %s is also in use!");
/* 238 */   public static final String ABILITY_MESSAGE_NOT_ENOUGH_ABILITY_STACKS = WyRegistry.registerName("ability.message.not_enough_ability_stacks", "This ability requires at least %s %s stacks!");
/* 239 */   public static final String ABILITY_MESSAGE_NO_FOCUS = WyRegistry.registerName("ability.message.no_focus", "Ability cannot be used while you are focusing on something else!");
/*     */   
/* 241 */   public static final TranslationTextComponent ABILITY_MESSAGE_NEED_SNIPER_GOGGLES = new TranslationTextComponent(WyRegistry.registerName("ability.item.message.need_sniper_goggles_equipped", "This ability can only be used while Sniper Goggles are equipped!"));
/*     */   
/* 243 */   public static final String ABILITY_DEPENDENCY_SINGLE_ACTIVE = WyRegistry.registerName("ability.mineminenomi.message.dependency_single_active", "Requires %s to be active.");
/* 244 */   public static final String ABILITY_DEPENDENCY_DOUBLE_ACTIVE = WyRegistry.registerName("ability.mineminenomi.message.dependency_double_active", "Requires %s or %s to be active.");
/*     */   
/* 246 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_COOLDOWN = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.cooldown", "Cooldown"));
/* 247 */   public static final String ABILITY_DESCRIPTION_STAT_NAME_CONTINUE = WyRegistry.registerName("ability.mineminenomi.stat.name.continue", "Hold");
/* 248 */   public static final String ABILITY_DESCRIPTION_STAT_NAME_CHARGE = WyRegistry.registerName("ability.mineminenomi.stat.name.charge", "Charge");
/* 249 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_DAMAGE = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.damage", "Damage"));
/* 250 */   public static final String ABILITY_DESCRIPTION_STAT_NAME_RANGE = WyRegistry.registerName("ability.mineminenomi.stat.name.range", "Range");
/* 251 */   public static final String ABILITY_DESCRIPTION_STAT_NAME_STACKS = WyRegistry.registerName("ability.mineminenomi.stat.name.stacks", "Stacks");
/* 252 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_STATS = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.stats", "Stats"));
/* 253 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_PIERCING = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.piercing", "Piercing"));
/* 254 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_PROJECTILE = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.projectile", "Projectile"));
/* 255 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_SHORT_COOLDOWN = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.short_cooldown", "Short Cooldown"));
/* 256 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_LONG_COOLDOWN = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.long_cooldown", "Long Cooldown"));
/* 257 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_BASE_DAMAGE = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.base_damage", "Base Damage"));
/* 258 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_WATER_DAMAGE = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.water_damage", "Water Damage"));
/* 259 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_HEAL = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.heal", "Heal"));
/* 260 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_COLA = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.cola", "Cola"));
/* 261 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_NAME_TELEPORT_DISTANCE = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.name.teleport_distance", "Teleport Distance"));
/*     */   
/* 263 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_UNIT_SECONDS = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.unit.seconds", "second(s)"));
/* 264 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_UNIT_BLOCKS = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.unit.blocks", "block(s)"));
/* 265 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_UNIT_AOE_BLOCKS = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.unit.aoe_blocks", "block(s) ◎"));
/* 266 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_UNIT_CONE_BLOCKS = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.unit.cone_blocks", "block(s) ▽"));
/* 267 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_UNIT_LINE_BLOCKS = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.unit.line_blocks", "block(s) |"));
/* 268 */   public static final String ABILITY_DESCRIPTION_STAT_UNIT_DPS = WyRegistry.registerName("ability.mineminenomi.stat.unit.dps", "dps");
/* 269 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_STAT_UNIT_PER_STACK = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.stat.unit.per_stack", "per stack"));
/* 270 */   public static final StringTextComponent ABILITY_DESCRIPTION_STAT_UNIT_X3 = new StringTextComponent("x3");
/*     */   
/* 272 */   public static final String ABILITY_DESCRIPTION_LOGIA_PROTECTION = WyRegistry.registerName("ability.mineminenomi.logia_protection", "Allows the user to avoid attacks by instinctively transforming parts of their body into their specific element");
/* 273 */   public static final String ABILITY_DESCRIPTION_CHARGED_TEMPO_COMBINATION = WyRegistry.registerName("ability.mineminenomi.charged_tempo_combination", "§aCharged Tempo:§r %s + %s + %s");
/* 274 */   public static final String ABILITY_DESCRIPTION_TEMPO_COMBINATION_2 = WyRegistry.registerName("ability.mineminenomi.tempo_combination_2", "§aTempo:§r: %s + %s");
/* 275 */   public static final String ABILITY_DESCRIPTION_TEMPO_COMBINATION_3 = WyRegistry.registerName("ability.mineminenomi.tempo_combination_3", "§aTempo:§r: %s + %s + %s");
/* 276 */   public static final String ABILITY_DESCRIPTION_TEMPO_SAME_TYPE_COMBINATION = WyRegistry.registerName("ability.mineminenomi.tempo_same_type_combination", "§aTempo:§r: %s %s");
/* 277 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_TEMPO_REQUIRES_WEATHER_CLOUD = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.tempo_requires_weather_cloud", "§aRequires an active Weather Cloud§r"));
/* 278 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_WITHOUT_THUNDERSTORM = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.without_thunderstorm", "Without Thunderstorm"));
/* 279 */   public static final TranslationTextComponent ABILITY_DESCRIPTION_DURING_THUNDERSTORM = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.during_thunderstorm", "During Thunderstorm"));
/*     */   
/* 281 */   public static final String ABILITY_PUPPET_STATE = WyRegistry.registerName("ability.message.puppet_state", "Your %s is now %s");
/* 282 */   public static final String ABILITY_PUPPET_STATE_AGGRESSIVE = WyRegistry.registerName("ability.message.puppet_state.aggressive", "Aggressive");
/* 283 */   public static final String ABILITY_PUPPET_STATE_DEFENSIVE = WyRegistry.registerName("ability.message.puppet_state.defensive", "Defensive");
/*     */ 
/*     */   
/* 286 */   public static final TranslationTextComponent PERK_FISHMAN_SWIM_SPEED_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.fishman_swim_speed_bonus", "Increases swimming speed by 300%."));
/* 287 */   public static final TranslationTextComponent PERK_FISHMAN_DAMAGE_SPEED_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.fishman_damage_bonus", "Increases punch damage by 3."));
/*     */ 
/*     */   
/* 290 */   public static final TranslationTextComponent PERK_CYBORG_ARMOR_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.cyborg_armor_bonus", "Gains a permanent extra 10 armor points as well as 2 extra punch damage."));
/*     */ 
/*     */   
/* 293 */   public static final TranslationTextComponent PERK_MINK_SPEED_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.mink_speed_bonus", "Increases movement by 25%\n§cThis bonus is lost in really hot climates (such as deserts).§r"));
/* 294 */   public static final TranslationTextComponent PERK_MINK_JUMP_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.mink_jump_bonus", "Increases jump height by 0.75 blocks.\n§cThis bonus is lost in really hot climates (such as deserts).§r"));
/*     */ 
/*     */   
/* 297 */   public static final TranslationTextComponent PERK_SWORDSMAN_DAMAGE_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.swordsman_damage_bonus", "Swords deal 20% more damage."));
/*     */ 
/*     */   
/* 300 */   public static final TranslationTextComponent PERK_SNIPER_ACCURACY_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.sniper_accuracy_bonus", "Projectiles shot by snipers are twice as accurate."));
/* 301 */   public static final TranslationTextComponent PERK_SNIPER_GOGGLES_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.sniper_goggles_bonus", "While equipped with Sniper Goggles projectiles shot by snipers are 4 times as accurate."));
/*     */ 
/*     */   
/* 304 */   public static final TranslationTextComponent PERK_BLACK_LEG_DAMAGE_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.black_leg_damage_bonus", "Gain extra kick damage based on their doriki up to 4 extra damage."));
/* 305 */   public static final TranslationTextComponent PERK_BLACK_LEG_ATTACK_SPEED_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.black_leg_attack_speed_bonus", "Kicks are 30% faster."));
/*     */ 
/*     */   
/* 308 */   public static final TranslationTextComponent PERK_BRAWLER_DAMAGE_BONUS = new TranslationTextComponent(WyRegistry.registerName("perk.mineminenomi.brawler_damage_bonus", "Gain extra punch damage based on their doriki up to 5 extra damage."));
/*     */   
/* 310 */   public static final String ITEM_KAIROSEKI_ITEM = WyRegistry.registerName("item.kairoseki_item", "Kairoseki Item");
/* 311 */   public static final String ITEM_GOURMETAMORPHOSIS_FOOD = WyRegistry.registerName("item.gourmetamorphosis_food", "Gourmetamorphosis - You can eat this item!");
/* 312 */   public static final String ITEM_MESSAGE_NEED_KEY = WyRegistry.registerName("item.message.need_key", "You need a key!");
/* 313 */   public static final String ITEM_MESSAGE_POUCH_BELLY_GAINED = WyRegistry.registerName("item.message.pouch_belly_gained", "You've obtained %s belly!");
/* 314 */   public static final String ITEM_MESSAGE_POUCH_EXTOL_GAINED = WyRegistry.registerName("item.message.pouch_extol_gained", "You've obtained %s extol!");
/* 315 */   public static final TranslationTextComponent ITEM_MESSAGE_BELLY_FULL = new TranslationTextComponent(WyRegistry.registerName("item.message.belly_full", "Cannot carry any more belly!"));
/* 316 */   public static final TranslationTextComponent ITEM_MESSAGE_EXTOL_FULL = new TranslationTextComponent(WyRegistry.registerName("item.message.extol_full", "Cannot carry any more extol!"));
/* 317 */   public static final String ITEM_MESSAGE_BELLY_POUCH_AMOUNT = WyRegistry.registerName("item.message.belly_pouch_amount", "Belly: %s");
/* 318 */   public static final String ITEM_MESSAGE_EXTOL_POUCH_AMOUNT = WyRegistry.registerName("item.message.extol_pouch_amount", "Extol: %s");
/* 319 */   public static final String ITEM_PONEGLYPH_ENCRYPTED_NOTE = WyRegistry.registerName("item.poneglyph_encrypted_note", "Encrypted Poneglyph Note");
/* 320 */   public static final String ITEM_PONEGLYPH_DECRYPTED_NOTE = WyRegistry.registerName("item.poneglyph_decrypted_note", "Decrypted Poneglyph Note");
/* 321 */   public static final String ITEM_BUSTER_CALL_REQUIREMENT = WyRegistry.registerName("item.buster_call.requirement", "Only Admirals and above can summon a Buster Call!");
/* 322 */   public static final String ITEM_BUSTER_CALL_INFO = WyRegistry.registerName("item.buster_call.info", "Launch a buster call at your position. Must be in your inventory for the whole countdown!");
/* 323 */   public static final String ITEM_BUSTER_CALL_LAUNCHED = WyRegistry.registerName("item.buster_call.launched", "A Buster Call has been launched around %s %s by %s");
/* 324 */   public static final String ITEM_GENERIC_FRUIT = WyRegistry.registerName("item.generic_fruit", "Devil Fruit");
/* 325 */   public static final String ITEM_FRUIT_CLUE = WyRegistry.registerName("item.fruit_clue", "Devil Fruit Clue");
/* 326 */   public static final String ITEM_DF_ENCYCLOPEDIA_ENTRIES = WyRegistry.registerName("item.df_encyclopedia_entries", "This encyclopedia contains %s entries");
/* 327 */   public static final String ITEM_DF_ENCYCLOPEDIA_COMPLETION = WyRegistry.registerName("item.df_encyclopedia_completion", "%s Completed");
/* 328 */   public static final String ITEM_SWORDSMAN_BONUS = WyRegistry.registerName("item.swordsman_bonus", "Swordsman Bonus applies");
/* 329 */   public static final String ITEM_STRAW_DOLL = WyRegistry.registerName("item.straw_doll", "%s's Straw Doll");
/*     */   
/* 331 */   public static final String INFO_FUEL_LEFT = WyRegistry.registerName("info.mineminenomi.message.fuel_left", "Fuel Left: %s");
/* 332 */   public static final String INFO_NEEDS_GUNPOWDER_LOADED = WyRegistry.registerName("info.mineminenomi.message.needs_gunpowder_loaded", "No gunpowder found");
/* 333 */   public static final String INFO_NEEDS_CANNONBALL_LOADED = WyRegistry.registerName("info.mineminenomi.message.needs_cannonball_loaded", "No cannon ball found");
/* 334 */   public static final ITextComponent INFO_ENTERING_COMBAT = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("info.mineminenomi.message.entering_combat", "§cEntering combat.§r"));
/* 335 */   public static final ITextComponent INFO_LEAVING_COMBAT = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("info.mineminenomi.message.leaving_combat", "§aLeaving combat.§r"));
/* 336 */   public static final ITextComponent INFO_LOYALTY_DROPPED = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("info.mineminenomi.message.loyalty_dropped", "§cYour loyalty has dropped§r"));
/* 337 */   public static final String INFO_FACTION_CHANGE = WyRegistry.registerName("info.mineminenomi.message.faction_changed", "Your faction was changed to %s");
/*     */   
/* 339 */   public static final ITextComponent HELP_ABILITY_SELECTOR_DRAG_AND_DROP = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("help.mineminenomi.ability_selector.drag_and_drop", "§bDrag and Drop§r\n- §6Left click§r and §6drag§r abilities from the list to the lower bar to §6equip§r them.\n- §6Right click§r an ability in the lower bar to §6remove§r it."));
/*     */ 
/*     */ 
/*     */   
/* 343 */   public static final ITextComponent HELP_ABILITY_SELECTOR_KEYBIND = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("help.mineminenomi.ability_selector.keybind", "§bKeybind§r\n- §6Left click§r on a slot in the lower bar to select it then §6left click§r on an ability from the list to §6equip§r it.\n- §6Hover§r over an ability in the list and §6press the keybind§r for the slot you want to §6equip§r the ability in.\n- §6Right click§r an ability in the lower bar to §6remove§r it."));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public static final ITextComponent HELP_ABILITY_SELECTOR_SHOW_TOOLTIPS = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("help.mineminenomi.ability_selector.show_tooltips", "§bShow Tooltips§r\n- Hold §6alt§r to temporarily §6show§r ability tooltips.\n"));
/*     */ 
/*     */   
/* 351 */   public static final ITextComponent HELP_ABILITY_SELECTOR_HIDE_TOOLTIPS = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("help.mineminenomi.ability_selector.hide_tooltips", "§bHide Tooltips§r\n- Hold §6alt§r to temporarily §6hide§r ability tooltips.\n"));
/*     */ 
/*     */ 
/*     */   
/* 355 */   public static final String CHALLENGE_MESSAGE_INSCRIPTION = WyRegistry.registerName("challenge.message.inscription", "You've noted the characters inscribed on the Poneglyph on a piece of paper.");
/* 356 */   public static final String CHALLENGE_MESSAGE_INSCRIPTION_NO_PAPER = WyRegistry.registerName("challenge.message.inscription_no_paper", "You need a piece of paper in order to note the characters.");
/* 357 */   public static final String CHALLENGE_MESSAGE_INSCRIPTION_ALREADY_COPIED = WyRegistry.registerName("challenge.message.inscription_already_copied", "You've already copied this inscription.");
/* 358 */   public static final String CHALLENGE_MESSAGE_UNLOCKED = WyRegistry.registerName("challenge.message.unlocked", "New Challenge unlocked: %s!");
/* 359 */   public static final String CHALLENGE_MESSAGE_ALREADY_UNLOCKED = WyRegistry.registerName("challenge.message.already_unlocked", "%s challenge is already unlocked!");
/* 360 */   public static final String CHALLENGE_MESSAGE_ARENA_IN_USE = WyRegistry.registerName("challenge.message.arena_in_use", "This arena is currently used by anothe player, please wait!");
/* 361 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_CANNOT_USE_ITEM = new TranslationTextComponent(WyRegistry.registerName("challenge.message.cannot_use_item", "Cannot use this item at the moment."));
/* 362 */   public static final String CHALLENGE_MESSAGE_NOT_UNLOCKED = WyRegistry.registerName("challenge.message.not_unlocked", "Challenge not unlocked!");
/* 363 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_START_TITLE = new TranslationTextComponent(WyRegistry.registerName("challenge.message.start_title", "Starting Challenge"));
/* 364 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_START_SUBTITLE = new TranslationTextComponent(WyRegistry.registerName("challenge.message.start_subtitle", "Please wait"));
/* 365 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_START_FIGHT = new TranslationTextComponent(WyRegistry.registerName("challenge.message.start_fight", "§a§oFIGHT!!!§r"));
/* 366 */   public static final String CHALLENGE_MESSAGE_END_COUNTDOWN = WyRegistry.registerName("challenge.message.end_countdown", "Challenge will end in %s seconds");
/* 367 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_COMPLETION_REPORT = new TranslationTextComponent(WyRegistry.registerName("challenge.message.completion_report", "Completion Time: "));
/* 368 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_FAILURE_REPORT = new TranslationTextComponent(WyRegistry.registerName("challenge.message.failure_report", "Challenge Failed"));
/* 369 */   public static final TranslationTextComponent CHALLENGE_MESSAGE_TRAINING_INFO = new TranslationTextComponent(WyRegistry.registerName("challenge.message.training_info", "During Training challenge restrictions are ignored however there will be no rewards, no records and the challenge won't be marked as completed."));
/* 370 */   public static final String CHALLENGE_MESSAGE_INVITATION = WyRegistry.registerName("challenge.message.invitation", "You've been invited by %s to %s.");
/* 371 */   public static final String CHALLENGE_MESSAGE_GROUP_JOIN = WyRegistry.registerName("challenge.message.group_join", "%s has joined your group.");
/* 372 */   public static final String CHALLENGE_MESSAGE_GROUP_DISBAND = WyRegistry.registerName("challenge.message.group_disband", "%s's group has disbanded.");
/*     */   
/* 374 */   public static final TranslationTextComponent CHALLENGE_RESTRICTION_NO_FOODS = new TranslationTextComponent(WyRegistry.registerName("challenge.restriction.no_foods", "No Foods"));
/* 375 */   public static final TranslationTextComponent CHALLENGE_RESTRICTION_NO_POTIONS = new TranslationTextComponent(WyRegistry.registerName("challenge.restriction.no_potions", "No Potions"));
/* 376 */   public static final TranslationTextComponent CHALLENGE_RESTRICTION_NO_ADVANCED_BUSO_HAKI = new TranslationTextComponent(WyRegistry.registerName("challenge.restriction.no_advanced_buso_haki", "No Advanced Busoshoku Haki"));
/*     */   
/* 378 */   public static final String ITEM_MESSAGE_FRUIT_ALREADY_USED = WyRegistry.registerName("item.message.fruit_already_used", "This fruit is already owned by somebody else!");
/* 379 */   public static final String ITEM_MESSAGE_GAINED_ENLIGHTENMENT = WyRegistry.registerName("item.message.gained_enlightenment", "You've gained some enlightenment!");
/* 380 */   public static final String ITEM_MESSAGE_GAINED_FRUIT_CLUE = WyRegistry.registerName("item.message.gained_fruit_clue", "You've gained new information about %s.");
/*     */   
/* 382 */   public static final String COMMAND_ISSUEBOUNTY_MESSAGE_REQUIREMENTS = WyRegistry.registerName("command.issuebounty.message.requirements", "Only Marine Captains can use this command!");
/* 383 */   public static final String COMMAND_ISSUEBOUNTY_MESSAGE_ONLY_UP = WyRegistry.registerName("command.issuebounty.message.only_up", "Bounties can only be increased!");
/* 384 */   public static final String COMMAND_ISSUEBOUNTY_MESSAGE_NOT_ENOUGH_BELLY = WyRegistry.registerName("command.issuebounty.message.not_enough_belly", "You don't have enough belly");
/* 385 */   public static final String COMMAND_ISSUEBOUNTY_MESSAGE_TARGET_REQUIREMENTS = WyRegistry.registerName("command.issuebounty.message.target_requirements", "Bounties can only be issued for Pirates, Bandits or Revolutionaries!");
/* 386 */   public static final String COMMAND_ISSUEBOUNTY_MESSAGE_SUCCESS = WyRegistry.registerName("command.issuebounty.message.success", "A bounty of %s has been issued for %s");
/* 387 */   public static final String COMMAND_POUCH_MESSAGE_INVENTORY_FULL = WyRegistry.registerName("command.pouch.message.inventory_full", "Your inventory is full!");
/*     */   
/* 389 */   public static final TranslationTextComponent COMMAND_CHECK_FRUIT_OFPW_ONLY = new TranslationTextComponent(WyRegistry.registerName("command.check_fruit.message.ofpw_only", "This command can only be used when the One Fruit per World config option is enabled."));
/* 390 */   public static final TranslationTextComponent COMMAND_CHECK_FRUIT_ERROR_EXPORTING = new TranslationTextComponent(WyRegistry.registerName("command.check_fruit.message.error_exporting", "Error occured while trying to export the one fruit report, send a log to a dev."));
/*     */   
/* 392 */   public static final String COMMAND_CREW_NO_CREW_FOUND = WyRegistry.registerName("command.crew.message.no_crew_found", "No crews found!");
/* 393 */   public static final String COMMAND_CREW_DELETE_ALL_CONFIRM = WyRegistry.registerName("command.crew.message.confirm_delete", "This will delete all crews on your server! If you are absolutely sure you want to do this insert \\\"true\\\" after the command!");
/* 394 */   public static final String COMMAND_CREW_DELETED_ALL_CREWS = WyRegistry.registerName("command.crew.message.removed_all", "Successfully deleted all crews!");
/* 395 */   public static final String COMMAND_CREW_DELETED_CREW = WyRegistry.registerName("command.crew.message.deleted", "Successfully deleted crew!");
/* 396 */   public static final String COMMAND_CREW_CREATED_CREW = WyRegistry.registerName("command.crew.message.created", "Successfully created crew!");
/* 397 */   public static final String COMMAND_CREW_PLAYER_NO_PIRATE = WyRegistry.registerName("command.crew.message.player_no_pirate", "Player is not a Pirate!");
/* 398 */   public static final String COMMAND_CREW_PLAYER_ADDED_TO_CREW = WyRegistry.registerName("command.crew.message.player_added_to_crew", "Player successfully added to crew!");
/* 399 */   public static final String COMMAND_CREW_PLAYER_REMOVED_FROM_CREW = WyRegistry.registerName("command.crew.message.player_removed_from_crew", "Player successfully removed from crew!");
/* 400 */   public static final String COMMAND_CREW_CAPTAIN_CHANGE = WyRegistry.registerName("command.crew.message.captain_change", "Successfully changed captain of crew!");
/* 401 */   public static final String COMMAND_CREW_ALREADY_IN_CREW = WyRegistry.registerName("command.crew.message.player_in_crew", "Player already in a crew!");
/* 402 */   public static final String COMMAND_CREW_NOT_IN_CREW = WyRegistry.registerName("command.crew.message.not_in_crew", "Player not in that crew!");
/* 403 */   public static final String COMMAND_CREW_NAME_ALREADY_EXISTS = WyRegistry.registerName("command.crew.message.already_exists", "Crew with that name already exists!");
/*     */   
/* 405 */   public static final String SYSTEM_MESSAGE_OFPW_INACTIVITY = WyRegistry.registerName("system.message.ofpw_inactivity", "Due to your inactivity all of your Devil Fruits were removed!");
/* 406 */   public static final TranslationTextComponent SYSTEM_MESSAGE_RANDOMIZED_FRUITS = new TranslationTextComponent(WyRegistry.registerName("system.message.randomized_fruits", "This item can only be used when 'Randomized Fruits' config option is enabled!"));
/*     */ 
/*     */   
/*     */   public static void init() {
/* 410 */     WyRegistry.registerName("curios.identifier.face", "Face");
/*     */ 
/*     */     
/* 413 */     WyRegistry.registerName("itemGroup.devil_fruits", "Devil Fruits");
/* 414 */     WyRegistry.registerName("itemGroup.weapons", "Weapons");
/* 415 */     WyRegistry.registerName("itemGroup.equipment", "Equipment");
/*     */ 
/*     */     
/* 418 */     WyRegistry.registerName("death.attack.ability", "%1$s was killed by %2$s using %3$s");
/* 419 */     WyRegistry.registerName("death.attack.ability.player", "%1$s was killed by %2$s using %3$s");
/* 420 */     WyRegistry.registerName("death.attack.ability_projectile", "%1$s was killed by %2$s");
/* 421 */     WyRegistry.registerName("death.attack.ability_projectile.player", "%1$s was killed by %2$s");
/* 422 */     WyRegistry.registerName("death.attack.devils_curse", "%1$s died while trying to eat multiple Devil Fruits");
/* 423 */     WyRegistry.registerName("death.attack.devils_curse.player", "%1$s died while trying to eat multiple Devil Fruits");
/* 424 */     WyRegistry.registerName("death.attack.reject_dial", "%1$s was killed by %2$s using a Reject Dial");
/* 425 */     WyRegistry.registerName("death.attack.reject_dial.player", "%1$s was killed by %2$s using a Reject Dial");
/* 426 */     WyRegistry.registerName("death.attack.frost", "%1$s died frozen");
/* 427 */     WyRegistry.registerName("death.attack.out_of_body", "Your body was killed");
/* 428 */     WyRegistry.registerName("death.attack.out_of_body.player", "Your body was killed");
/* 429 */     WyRegistry.registerName("death.attack.soulbound_damage", "%1$s was killed by their soulbound item");
/* 430 */     WyRegistry.registerName("death.attack.soulbound_damage.player", "%1$s was killed when their soulbound item got destroyed by %2$");
/* 431 */     WyRegistry.registerName("death.attack.heart_damage", "%1$s was killed by a heart attack");
/* 432 */     WyRegistry.registerName("death.attack.heart_damage.player", "%1$s was killed by a heart attack");
/* 433 */     WyRegistry.registerName("death.attack.mh5_gas", "%1$s died while trying to breath MH5 gas");
/* 434 */     WyRegistry.registerName("death.attack.mh5_gas.player", "%1$s died while trying to breath MH5 gas");
/* 435 */     WyRegistry.registerName("death.attack.stored_damage", "%1$s got killed after waking up from their drunkenness");
/* 436 */     WyRegistry.registerName("death.attack.stored_damage.player", "%1$s got killed after waking up from their drunkenness");
/* 437 */     WyRegistry.registerName("death.attack.genocide_raid", "%1$s got killed by Genocide Raid");
/* 438 */     WyRegistry.registerName("death.attack.genocide_raid.player", "%1$s got killed by Genocide Raid");
/* 439 */     WyRegistry.registerName("death.attack.too_much_gravity", "%1$s got killed by gravity");
/* 440 */     WyRegistry.registerName("death.attack.too_much_gravity.player", "%1$s got killed by gravity");
/* 441 */     WyRegistry.registerName("death.attack.sun_incineration", "%1$s got killed by the sun");
/* 442 */     WyRegistry.registerName("death.attack.sun_incineration.player", "%1$s got killed by the sun");
/*     */ 
/*     */     
/* 445 */     WyRegistry.registerName("scancode.0", "No Key");
/* 446 */     WyRegistry.registerName("key.keyboard.0", "No Key");
/*     */ 
/*     */     
/* 449 */     WyRegistry.registerName("ability.mineminenomi.logia_invulnerability", "Logia Invulnerability");
/*     */     
/* 451 */     WyRegistry.registerName("ability.mineminenomi.poêle_à_frire", "Poêle à Frire");
/* 452 */     WyRegistry.registerName("ability.mineminenomi.bien_cuit_grill_shot", "Bien Cuit: Grill Shot");
/*     */     
/* 454 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_culverin", "Gomu Gomu no Culverin");
/* 455 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_elephant_gun", "Gomu Gomu no Elephant Gun");
/* 456 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_pistol", "Gomu Gomu no Jet Pistol");
/*     */     
/* 458 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_leo_bazooka", "Gomu Gomu no Leo Bazooka");
/* 459 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_grizzly_magnum", "Gomu Gomu no Grizzly Magnum");
/* 460 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_bazooka", "Gomu Gomu no Jet Bazooka");
/*     */     
/* 462 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_kong_gatling", "Gomu Gomu no Kong Gatling");
/* 463 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_elephant_gatling", "Gomu Gomu no Elephant Gatling");
/* 464 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_gatling", "Gomu Gomu no Jet Gatling");
/*     */     
/* 466 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_kong_gatling", "Gomu Gomu no Kong Gatling");
/* 467 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_elephant_gatling", "Gomu Gomu no Elephant Gatling");
/* 468 */     WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_jet_gatling", "Gomu Gomu no Jet Gatling");
/*     */     
/* 470 */     WyRegistry.registerName("ability.mineminenomi.king_kong_gun", "King Kong Gun");
/*     */     
/* 472 */     WyRegistry.registerName("ability.mineminenomi.demon_chloro_ball", "Demon Chloro Ball");
/* 473 */     WyRegistry.registerName("ability.mineminenomi.demon_hydra", "Demon Hydra");
/* 474 */     WyRegistry.registerName("ability.mineminenomi.demon_road", "Demon Road");
/*     */     
/* 476 */     WyRegistry.registerName("ability.mineminenomi.elemental_flight", "Elemental Flight");
/*     */     
/* 478 */     WyRegistry.registerName("ability.mineminenomi.mil_fleur_clutch", "Mil Fleur Clutch");
/* 479 */     WyRegistry.registerName("ability.mineminenomi.mil_fleur_slap", "Mil Fleur Slap");
/* 480 */     WyRegistry.registerName("ability.mineminenomi.mil_fleur_twist", "Mil Fleur Twist");
/*     */     
/* 482 */     WyRegistry.registerName("ability.mineminenomi.mamaragan", "Mamaragan");
/*     */     
/* 484 */     WyRegistry.registerName("ability.mineminenomi.fishman_swim_speed", "Fishman Swim Speed");
/* 485 */     WyRegistry.registerName("ability.mineminenomi.fishman_damage", "Fishman Damage");
/* 486 */     WyRegistry.registerName("ability.mineminenomi.cyborg_armor", "Cyborg Armor");
/* 487 */     WyRegistry.registerName("ability.mineminenomi.mink_speed", "Mink Speed");
/* 488 */     WyRegistry.registerName("ability.mineminenomi.mink_jump", "Mink Jump");
/* 489 */     WyRegistry.registerName("ability.mineminenomi.swordsman_damage", "Swordsman Damage");
/* 490 */     WyRegistry.registerName("ability.mineminenomi.sniper_accuracy", "Sniper Accuracy");
/* 491 */     WyRegistry.registerName("ability.mineminenomi.sniper_goggles", "Sniper Goggles");
/* 492 */     WyRegistry.registerName("ability.mineminenomi.black_leg_damage", "Black Leg Damage");
/* 493 */     WyRegistry.registerName("ability.mineminenomi.black_leg_attack_speed", "Black Leg Attack Speed");
/* 494 */     WyRegistry.registerName("ability.mineminenomi.brawler_damage", "Brawler Damage");
/*     */     
/* 496 */     WyRegistry.registerName("quest.objective.mineminenomi.brew_%s_healing_splash_potions", "Brew %s healing splash potions");
/* 497 */     WyRegistry.registerName("quest.objective.mineminenomi.brew_%s_splash_potions", "Brew %s splash potions");
/* 498 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_bones", "Collect %s bones");
/* 499 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_breath_dials", "Collect %s breath dials");
/* 500 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_cacti", "Collect %s cacti");
/* 501 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_cannon_balls", "Collect %s cannon balls");
/* 502 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_dried_kelp", "Collect %s dried kelp");
/* 503 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_glistering_melon_slices", "Collect %s glistering melon slices");
/* 504 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_gunpowder", "Collect %s gunpowder");
/* 505 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_impact_dials", "Collect %s impact dials");
/* 506 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_ink_sacs", "Collect %s ink sacs");
/* 507 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_iron_ingots", "Collect %s iron ingots");
/* 508 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_nether_warts", "Collect %s nether warts");
/* 509 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_rabbit_feet", "Collect %s rabbit feet");
/* 510 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_spider_eyes", "Collect %s spider eyes");
/* 511 */     WyRegistry.registerName("quest.objective.mineminenomi.collect_%s_strings", "Collect %s strings");
/* 512 */     WyRegistry.registerName("quest.objective.mineminenomi.cure_yourself_of_poison", "Cure yourself of Poison");
/* 513 */     WyRegistry.registerName("quest.objective.mineminenomi.damage_%s_enemies_with_sweeping_attacks", "Damage %s enemies with sweeping attacks");
/* 514 */     WyRegistry.registerName("quest.objective.mineminenomi.detonate_%s_creepers_using_kaen_boshi", "Detonate %s Creepers using Hi no Tori Boshi");
/* 515 */     WyRegistry.registerName("quest.objective.mineminenomi.equip_a_%s", "Equip a %s");
/* 516 */     WyRegistry.registerName("quest.objective.mineminenomi.heal_%s_villagers_using_first_aid", "Heal %s Villagers using First Aid");
/* 517 */     WyRegistry.registerName("quest.objective.mineminenomi.hit_%s_enemies_at_the_same_time", "Hit %s enemies at the same time");
/* 518 */     WyRegistry.registerName("quest.objective.mineminenomi.hit_%s_enemies_using_extra_hachis_at_the_same_time", "Hit %s enemies using Extra Hachis at the same time");
/* 519 */     WyRegistry.registerName("quest.objective.mineminenomi.hit_all_%s_targets_before_they_touch_the_ground", "Hit all %s targets before they touch the ground");
/* 520 */     WyRegistry.registerName("quest.objective.mineminenomi.hit_at_least_%s_enemies_using_spinning_brawl", "Hit at least %s enemies using Spinning Brawl");
/* 521 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies", "Kill %s enemies");
/* 522 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_airborne_enemies_using_a_bow", "Kill %s airborne enemies using a bow");
/* 523 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_airborne_enemies_using_a_sword", "Kill %s airborne enemies using a sword");
/* 524 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_from_at_least_30_blocks_away_using_genkotsu_meteor", "Kill %s enemies from at least 30 blocks away using Genkotsu Meteor");
/* 525 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_from_at_least_30_blocks_away_using_a_bow", "Kill %s enemies from at least 30 blocks away using a bow");
/* 526 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_in_less_than_%s_seconds", "Kill %s enemies in less than %s seconds");
/* 527 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_in_less_than_%s_seconds_using_a_sword", "Kill %s enemies in less than %s seconds using a sword");
/* 528 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_concasse", "Kill %s enemies using Concasse");
/* 529 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_critical_hits", "Kill %s enemies using critical hits");
/* 530 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_hakai_ho", "Kill %s enemies using Hakai Ho");
/* 531 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_jishin_ho_in_%s_seconds_or_less", "Kill %s enemies using Jishin Ho in %s seconds or less");
/* 532 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_shi_shishi_sonson", "Kill %s enemies using Shi Shishi Sonson");
/* 533 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_suplex", "Kill %s enemies using Suplex");
/* 534 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_thunderbolt_tempo", "Kill %s enemies using Thunderbolt Tempo");
/* 535 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_thunderlance_tempo", "Kill %s enemies using Thunderlance Tempo");
/* 536 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_yakkodori", "Kill %s enemies using Yakkodori");
/* 537 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_your_fists", "Kill %s enemies using your fists");
/* 538 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_a_sword", "Kill %s enemies using a sword");
/* 539 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_your_kicks", "Kill %s enemies using your kicks");
/* 540 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_while_running_towards_them", "Kill %s enemies while running towards them");
/* 541 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_while_theyre_on_fire", "Kill %s enemies while they're on fire");
/* 542 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_while_youre_on_fire", "Kill %s enemies while you're on fire");
/* 543 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_with_critical_hits", "Kill %s enemies with critical hits");
/* 544 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_with_critical_hits_using_a_sword", "Kill %s enemies with critical hits using a sword");
/* 545 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_with_jump_shots", "Kill %s enemies with jump shots");
/* 546 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_on_fire_enemies_using_your_kicks", "Kill %s on fire enemies using your kicks");
/* 547 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_%s_enemies_using_your_fists_while_theyre_in_air", "Kill %s enemies using your fists while they're in air");
/* 548 */     WyRegistry.registerName("quest.objective.mineminenomi.catch_at_least_2_enemies_in_medic_bag_explosions_radius", "Catch at least 2 enemies in Medic Bag Explosion's radius");
/* 549 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_an_enemy_afflicted_with_3+_debuffs", "Kill an enemy afflicted with 3+ debuffs");
/* 550 */     WyRegistry.registerName("quest.objective.mineminenomi.kill_at_least_%s_enemies_using_thunderstorm_tempo_at_the_same_time", "Kill at least %s enemies using Thunderstorm Tempo at the same time");
/* 551 */     WyRegistry.registerName("quest.objective.mineminenomi.tame_%s_animals", "Tame %s animals");
/* 552 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_bow", "Obtain a bow");
/* 553 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_bow_with_punch_ii", "Obtain a bow with Punch II");
/* 554 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_clima_tact", "Obtain a Clima Tact");
/* 555 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_perfect_clima_tact", "Obtain a Perfect Clima Tact");
/* 556 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_sword_with_over_7_damage", "Obtain a sword with over 7 damage");
/* 557 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_sword_with_sharpness_ii", "Obtain a sword with Sharpness II");
/* 558 */     WyRegistry.registerName("quest.objective.mineminenomi.obtain_a_sword_with_unbreaking", "Obtain a sword with Unbreaking");
/* 559 */     WyRegistry.registerName("quest.objective.mineminenomi.perform_3_unique_tempos", "Perform 3 unique Tempos");
/* 560 */     WyRegistry.registerName("quest.objective.mineminenomi.reach_%s_doriki", "Reach %s doriki");
/* 561 */     WyRegistry.registerName("quest.objective.mineminenomi.set_%s_enemies_on_fire_using_kaen_boshi", "Set %s enemies on fire using Hi no Tori Boshi");
/* 562 */     WyRegistry.registerName("quest.objective.mineminenomi.succesfully_perform_weather_cloud_tempo_3_times", "Succesfully perform Weather Cloud Tempo 3 times");
/* 563 */     WyRegistry.registerName("quest.objective.mineminenomi.survive_for_%s_seconds_without_getting_hit", "Survive for %s seconds without getting hit");
/*     */     
/* 565 */     Advancements.init();
/*     */     
/* 567 */     WyRegistry.registerName("item.mineminenomi.mochi_mochi_no_mi", "Mochi Mochi no Mi");
/* 568 */     WyRegistry.registerName("item.mineminenomi.lollipop", "Lollipop");
/*     */   }
/*     */   
/*     */   public static class Advancements {
/*     */     public static void init() {
/* 573 */       register("challenges.root", "Challenges", "Unlock a challenge");
/*     */       
/* 575 */       register("adventure.meat", "MEAT!!!", "Consume Sea King Meat");
/* 576 */       register("adventure.cat_burglar", "Cat Burglar", "Collect a total of 100.000 belly");
/* 577 */       register("adventure.my_treasure", "My Treasure", "Collect a total of 10.000.000 belly");
/* 578 */       register("adventure.whats_kairoseki", "What's Kairoseki?", "Unlock one Haki ability (Haoshoku Haki not counting)");
/* 579 */       register("adventure.haki_master", "Haki Master", "Unlock all Haki abilities (Haoshoku Haki not counting)");
/* 580 */       register("adventure.why_is_the_rum_gone", "Why is the Rum Gone ?", "Reach the highest state of Drunkenness");
/* 581 */       register("adventure.all_for_me_grog", "All for me Grog", "Continue drinking Rum while in the highest state of Drunkenness");
/* 582 */       register("adventure.priceless_blade", "Priceless Blade", "Obtain a Supreme Grade blade");
/* 583 */       register("adventure.halfway_there", "Halfway There", "Complete 50% from a Devil Fruit Encyclopedia");
/* 584 */       register("adventure.one_for_the_books", "One for the Books", "Complete 100% from a Devil Fruit Encyclopedia");
/* 585 */       register("adventure.subtle_tweaks", "Just some subtle tweaks", "Slightly modify a ship's design");
/* 586 */       register("adventure.drunken_fist", "Master of the Drunken Fist", "Kill any entity using only your fists while drunk");
/* 587 */       register("adventure.past_memories", "Past Memories", "Find a buried poneglyph and note down its inscriptions using a piece of paper");
/*     */       
/* 589 */       register("devil_fruits.root", "Devil Fruits", "Eat a Devil Fruit");
/* 590 */       register("devil_fruits.black_hole", "Black Hole", "Eat a 2nd Devil Fruit as the Yami Yami no Mi user");
/* 591 */       register("devil_fruits.mooteorologist", "Mooteorologist", "Use Hiso Hiso no Mi's Animal Forewarning ability and talk with a Cow about the upcoming weather");
/* 592 */       register("devil_fruits.the_donut", "The Donut", "Turn a Mera Mera no Mi user into a donut");
/* 593 */       register("devil_fruits.second_chance", "Second Chance", "Get a second chance at life due to Yomi Yomi no Mi's effect");
/*     */     }
/*     */     
/*     */     private static void register(String id, String title, String desc) {
/* 597 */       WyRegistry.registerName("advancements.mineminenomi." + id + ".title", title);
/* 598 */       WyRegistry.registerName("advancements.mineminenomi." + id + ".description", desc);
/*     */     }
/*     */     
/*     */     public static TranslationTextComponent title(String id) {
/* 602 */       return new TranslationTextComponent("advancements.mineminenomi." + id + ".title");
/*     */     }
/*     */     
/*     */     public static TranslationTextComponent description(String id) {
/* 606 */       return new TranslationTextComponent("advancements.mineminenomi." + id + ".description");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModI18n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */