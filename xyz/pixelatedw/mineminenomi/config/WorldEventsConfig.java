/*    */ package xyz.pixelatedw.mineminenomi.config;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.config.options.BooleanOption;
/*    */ import xyz.pixelatedw.mineminenomi.config.options.DoubleOption;
/*    */ import xyz.pixelatedw.mineminenomi.config.options.IntegerOption;
/*    */ 
/*    */ 
/*    */ public class WorldEventsConfig
/*    */ {
/* 10 */   public static final BooleanOption SPAWN_WORLD_HUMANOIDS = new BooleanOption(Boolean.valueOf(true), "World Humanoids Spawns", "Allows random aggressive faction humans to spawn in the world (Marines, Pirates, Bandits)\nDefault: true");
/* 11 */   public static final BooleanOption SPAWN_WORLD_ANIMALS = new BooleanOption(Boolean.valueOf(true), "World Animals Spawns", "Allows random animal NPCs to spawn in the world (Dugongs, Bananwanis, Gorillas etc.)\nDefault: true");
/* 12 */   public static final BooleanOption SPAWN_NOTORIOUS_TARGETS = new BooleanOption(Boolean.valueOf(true), "Spawn Notorious Targets", "Allows Notorious Target events to spawn in the world\nDefault: true");
/* 13 */   public static final BooleanOption SPAWN_CARAVANS = new BooleanOption(Boolean.valueOf(true), "Spawn Caravans", "Allows Caravan events to spawn in the world\nDefault: true");
/* 14 */   public static final BooleanOption SPAWN_CELESTIAL_VISITS = new BooleanOption(Boolean.valueOf(true), "Spawn Celestial Visits", "Allows Celestial Visit events to spawn in the world\nDefault: true");
/*    */   
/* 16 */   public static final IntegerOption TIME_BETWEEN_TRADER_SPAWNS = new IntegerOption(Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(30), "Time Between Trader Spawns", "Determines the time (in minutes) between two spawn attempts\nDefault: 2 (minutes)");
/* 17 */   public static final IntegerOption SPAWN_CHANCE_TRADER = new IntegerOption(Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(100), "Chance for Trader Spawns", "Determines the % chance for a trader to spawn\nDefault: 1");
/*    */   
/* 19 */   public static final IntegerOption TIME_BETWEEN_TRAINER_SPAWNS = new IntegerOption(Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(30), "Time Between Trainer Spawns", "Determines the time (in minutes) between two spawn attempts\nDefault: 2 (minutes)");
/* 20 */   public static final IntegerOption SPAWN_CHANCE_TRAINER = new IntegerOption(Integer.valueOf(15), Integer.valueOf(0), Integer.valueOf(100), "Chance for Trainer Spawns", "Determines the % chance for a trainer to spawn\nDefault: 15");
/*    */   
/* 22 */   public static final IntegerOption TIME_BETWEEN_AMBUSH_SPAWNS = new IntegerOption(Integer.valueOf(15), Integer.valueOf(0), Integer.valueOf(30), "Time Between Ambush Spawns", "Determines the time (in minutes) between two spawn attempts\nDefault: 15 (minutes)");
/* 23 */   public static final IntegerOption SPAWN_CHANCE_AMBUSH = new IntegerOption(Integer.valueOf(15), Integer.valueOf(0), Integer.valueOf(100), "Chance for Ambush Spawns", "Determines the % chance for an ambush to spawn\nDefault: 15");
/*    */   
/* 25 */   public static final DoubleOption GRUNT_SPAWN_CHANCE = new DoubleOption(Double.valueOf(100.0D), Double.valueOf(0.0D), Double.valueOf(100.0D), "Grunts Spawn Chance", "Chance for grunts to spawn in world\nDefault: 100");
/* 26 */   public static final DoubleOption BRUTE_SPAWN_CHANCE = new DoubleOption(Double.valueOf(100.0D), Double.valueOf(0.0D), Double.valueOf(100.0D), "Brutes Spawn Chance", "Chance for brutes to spawn in world\nDefault: 100");
/* 27 */   public static final DoubleOption SNIPER_SPAWN_CHANCE = new DoubleOption(Double.valueOf(100.0D), Double.valueOf(0.0D), Double.valueOf(100.0D), "Snipers Spawn Chance", "Chance for snipers to spawn in world\nDefault: 100");
/* 28 */   public static final DoubleOption CAPTAIN_SPAWN_CHANCE = new DoubleOption(Double.valueOf(100.0D), Double.valueOf(0.0D), Double.valueOf(100.0D), "Captains Spawn Chance", "Chance for captains to spawn in world\nDefault: 100");
/* 29 */   public static final DoubleOption PACIFISTA_SPAWN_CHANCE = new DoubleOption(Double.valueOf(100.0D), Double.valueOf(0.0D), Double.valueOf(100.0D), "Pacifistas Spawn Chance", "Chance for pacifistas to spawn in world\nDefault: 100");
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\WorldEventsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */