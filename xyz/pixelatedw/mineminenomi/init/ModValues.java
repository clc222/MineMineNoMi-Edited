/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMultimap;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModValues
/*    */ {
/* 18 */   public static final ArrayList<AkumaNoMiItem> DEVIL_FRUITS = new ArrayList<>();
/* 19 */   public static final ArrayList<AkumaNoMiItem> LOGIA_FRUITS = new ArrayList<>();
/*    */   
/*    */   public static ImmutableMultimap<AbilityCategory, AbilityCore<?>> abilityCategoryMap;
/*    */   
/*    */   public static final String API_URL = "https://pixelatedw.xyz/api/v1";
/* 24 */   public static final Gson GSON = (new GsonBuilder())
/* 25 */     .disableHtmlEscaping()
/* 26 */     .setPrettyPrinting()
/* 27 */     .create();
/*    */ 
/*    */ 
/*    */   
/* 31 */   public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
/* 32 */   public static final ResourceLocation NIL_LOCATION = new ResourceLocation("empty");
/*    */   
/*    */   public static final int MAX_QUESTS = 4;
/*    */   
/*    */   public static final int MAX_ULTRACOLA = 20;
/*    */   
/*    */   public static final int MAX_COLA = 1000;
/*    */   
/*    */   public static final int MAX_GENERAL = 999999999;
/*    */   
/*    */   public static final int MAX_CREW = 50;
/*    */   public static final long MAX_CURRENCY = 999999999L;
/*    */   public static final int MAX_LOYALTY = 100;
/*    */   public static final int MIN_LOYALTY = -5;
/*    */   public static final int COMBAT_TIME_CACHE = 300;
/*    */   public static final int MAX_SELECTED_ABILITIES = 8;
/*    */   public static final int MAX_IN_PROGRESS_QUESTS = 4;
/*    */   public static final int CHARACTER_CREATOR = 0;
/*    */   public static final int WANTED_POSTER = 1;
/*    */   public static final int MAX_DIFFICULTY_STARS = 10;
/* 52 */   public static final int MAX_DIFFICULTY = 10 * (ChallengeDifficulty.values()).length;
/*    */ 
/*    */ 
/*    */   
/*    */   public static final long MAX_BOUNTY = 100000000000L;
/*    */ 
/*    */ 
/*    */   
/*    */   public static final int HAOSHOKU_HAKI_COLOR = 16711680;
/*    */ 
/*    */   
/* 63 */   public static final ResourceLocation EMPTY = new ResourceLocation("mineminenomi:empty");
/*    */   
/* 65 */   public static final ResourceLocation PIRATE = new ResourceLocation("mineminenomi:pirate");
/* 66 */   public static final ResourceLocation MARINE = new ResourceLocation("mineminenomi:marine");
/* 67 */   public static final ResourceLocation BOUNTY_HUNTER = new ResourceLocation("mineminenomi:bounty_hunter");
/* 68 */   public static final ResourceLocation REVOLUTIONARY = new ResourceLocation("mineminenomi:revolutionary");
/* 69 */   public static final ResourceLocation BANDIT = new ResourceLocation("mineminenomi:bandit");
/* 70 */   public static final ResourceLocation CIVILIAN = new ResourceLocation("mineminenomi:civilian");
/* 71 */   public static final ResourceLocation WORLD_GOVT = new ResourceLocation("mineminenomi:world_government");
/*    */   
/* 73 */   public static final ResourceLocation HUMAN = new ResourceLocation("mineminenomi:human");
/* 74 */   public static final ResourceLocation FISHMAN = new ResourceLocation("mineminenomi:fishman");
/* 75 */   public static final ResourceLocation CYBORG = new ResourceLocation("mineminenomi:cyborg");
/* 76 */   public static final ResourceLocation MINK = new ResourceLocation("mineminenomi:mink");
/*    */   
/*    */   public static final String MINK_BUNNY = "mink_bunny";
/*    */   
/*    */   public static final String MINK_DOG = "mink_dog";
/*    */   public static final String MINK_LION = "mink_lion";
/* 82 */   public static final ResourceLocation SWORDSMAN = new ResourceLocation("mineminenomi:swordsman");
/* 83 */   public static final ResourceLocation SNIPER = new ResourceLocation("mineminenomi:sniper");
/* 84 */   public static final ResourceLocation DOCTOR = new ResourceLocation("mineminenomi:doctor");
/* 85 */   public static final ResourceLocation ART_OF_WEATHER = new ResourceLocation("mineminenomi:art_of_weather");
/* 86 */   public static final ResourceLocation BLACK_LEG = new ResourceLocation("mineminenomi:black_leg");
/* 87 */   public static final ResourceLocation BRAWLER = new ResourceLocation("mineminenomi:brawler");
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */