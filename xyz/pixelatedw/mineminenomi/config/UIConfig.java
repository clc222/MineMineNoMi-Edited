/*    */ package xyz.pixelatedw.mineminenomi.config;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.config.options.BooleanOption;
/*    */ import xyz.pixelatedw.mineminenomi.config.options.EnumOption;
/*    */ import xyz.pixelatedw.mineminenomi.config.options.IntegerOption;
/*    */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UIConfig
/*    */ {
/* 12 */   public static final BooleanOption DISPLAY_IN_SECONDS = new BooleanOption(Boolean.valueOf(false), "Display in Seconds", "Displays the cooldown, charge up time and any other number displayed in the ability icon as seconds instead of ticks\nDefault: false");
/* 13 */   public static final IntegerOption ABILITY_BARS_ON_SCREEN = new IntegerOption(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(3), "Ability Bars on Screen", "How many ability bars can be displayed on the user's screen, this is limited by the server's amount of ability bars a user can have, it cannot exceed that\nDefault: 1");
/* 14 */   public static final BooleanOption SHOW_KEYBIND = new BooleanOption(Boolean.valueOf(true), "Show Keybind", "Displays the keybind associated with the slot as text in the bottom-left part of it\nDefault: true");
/* 15 */   public static final BooleanOption MERGE_ABILITY_BONUSES = new BooleanOption(Boolean.valueOf(false), "Merge Ability Bonuses", "Merge the bonuses shown in the ability tooltip with the original value\nFor example a default damage of 2 with a bonus of 2 would be displayed as 4, instead of 2 (+2)\nDefault: true");
/* 16 */   public static final BooleanOption SIMPLE_DISPLAYS = new BooleanOption(Boolean.valueOf(false), "Simple Displays", "Simplifies some UI elements down to numbers instead of visuals, can provide help for people with color blindness for example.\nDefault: false");
/* 17 */   public static final BooleanOption USE_HEARTS_BAR = new BooleanOption(Boolean.valueOf(true), "Use Mod Hearts UI", "Use the mod's hearts bar showing only 1 line of hearts with the HP number on the right\nDefault: true");
/* 18 */   public static final BooleanOption HIDE_ABILITY_STATS = new BooleanOption(Boolean.valueOf(false), "Hide Ability Stats", "Hides all the ability stats by default, reveals then while holding the SHIFT key.\nDefault: false");
/* 19 */   public static final EnumOption<SelectHotbarAbilitiesScreen.Compactness> ABILITY_LIST_COMPACTNESS = new EnumOption((Enum)SelectHotbarAbilitiesScreen.Compactness.SPATIOUS, (Enum[])SelectHotbarAbilitiesScreen.Compactness.values(), "Ability List Compactness", "How close the abilities are from each other in the selection list.\nDefault: SPATIOUS");
/* 20 */   public static final EnumOption<SelectHotbarAbilitiesScreen.Selection> ABILITY_LIST_SELECTION = new EnumOption((Enum)SelectHotbarAbilitiesScreen.Selection.DRAG_AND_DROP, (Enum[])SelectHotbarAbilitiesScreen.Selection.values(), "Ability List Selection Mode", "Which selection mode to use.\nDefault: DRAG_AND_DROP");
/* 21 */   public static final BooleanOption ABILITY_LIST_SHOW_TOOLTIPS = new BooleanOption(Boolean.valueOf(true), "Ability List Show Tooltips", "Shows the ability tooltips when hovering them\nDefault: true");
/* 22 */   public static final BooleanOption ABILITY_LIST_SHOW_HELP = new BooleanOption(Boolean.valueOf(true), "Ability List Show Help", "Shows the help button\nDefault: true");
/*    */   
/* 24 */   public static final EnumOption<ClientConfig.SlotNumberVisuals> SLOT_NUMBER_DISPLAY = new EnumOption(ClientConfig.SlotNumberVisuals.SECONDS, (Enum[])ClientConfig.SlotNumberVisuals.values(), "Slot Number Display", "Changes how slow numbers are displayed (such as for cooldown or charging)\nDefault: TICKS");
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\UIConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */