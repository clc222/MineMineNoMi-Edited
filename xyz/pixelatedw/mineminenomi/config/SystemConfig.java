/*    */ package xyz.pixelatedw.mineminenomi.config;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.config.options.BooleanOption;
/*    */ 
/*    */ public class SystemConfig
/*    */ {
/*  7 */   public static final BooleanOption MASTER_COMMAND = new BooleanOption(Boolean.valueOf(false), "Master Command", "Merges all the commands added by this mod under a generic /mmnm command, used for compatibility reasons in case some other mod adds similarly named commands\nDefault: false");
/*  8 */   public static final BooleanOption ENABLE_PERMISSIONS = new BooleanOption(Boolean.valueOf(false), "Enable Permissions", "Allows the usage of bukkit style permissions for certain mod features. Will override the default checks based on vanilla's 1-4 levels of permissions but might also bypass some mod specific requirements.\nDefault: false");
/*  9 */   public static final BooleanOption UPDATE_MESSAGE = new BooleanOption(Boolean.valueOf(true), "Update Message", "Allows the game to show a text message when the installed mod is outdated\nDefault: true");
/* 10 */   public static final BooleanOption MOD_SPLASH_TEXT = new BooleanOption(Boolean.valueOf(true), "Mod Splash Text", "Allows the game to show mod specific splash texts on the main menu\nDefault: true");
/* 11 */   public static final BooleanOption EXPERIMENTAL_SPHERES = new BooleanOption(Boolean.valueOf(true), "Experimental Spheres", "Replaces the block based abilities like ROOM and Torikago with 3D rendered spheres\nDefault: true");
/* 12 */   public static final BooleanOption EXPERIMENTAL_TIMERS = new BooleanOption(Boolean.valueOf(true), "Experimental Timers", "Replaces some of the timers in the mod with real life based timers, could slightly improve the accuracy of some, however on large servers with big TPS spikes it could also have the opposite effect\nDefault: true");
/* 13 */   public static final BooleanOption BLUE_GORO = new BooleanOption(Boolean.valueOf(false), "Blue Goro", "Turns Goro Goro no Mi, its abilities and special effects blue\nRestarting the game is recommended after changing this option\nDefault: false");
/* 14 */   public static final BooleanOption VILLAGE_COMPAT = new BooleanOption(Boolean.valueOf(false), "Village Compatibility", "Adjusts the weights of buildings added to villages by the mod in order to better fit the extended village sizes added by other mods\nDefault: false");
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\SystemConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */