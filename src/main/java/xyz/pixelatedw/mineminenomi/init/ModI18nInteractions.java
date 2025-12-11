/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModI18nInteractions {
/*  7 */   public static final TranslationTextComponent TALK_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.talk", "Talk"));
/*  8 */   public static final TranslationTextComponent HELP_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.help", "Help!"));
/*  9 */   public static final TranslationTextComponent YES_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.yes", "Yes"));
/* 10 */   public static final TranslationTextComponent NO_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.no", "No"));
/* 11 */   public static final TranslationTextComponent RUMORS_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.rumors", "Rumors"));
/* 12 */   public static final String RUMORS_WITH_PRICE_TITLE = WyRegistry.registerName("gui.rumors_with_price", "Rumors (%s belly)");
/* 13 */   public static final String BUY_RUM_TITLE = WyRegistry.registerName("gui.buy_rum", "Buy Rum (%s belly)");
/* 14 */   public static final TranslationTextComponent NEWS_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.news", "News"));
/* 15 */   public static final TranslationTextComponent CONTINUE_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.continue", "Continue"));
/* 16 */   public static final TranslationTextComponent BOUNTIES_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.bounties", "Bounties"));
/* 17 */   public static final TranslationTextComponent ACCEPT_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.accept", "Accept"));
/*    */   
/* 19 */   public static final TranslationTextComponent RANDOM_INTRO_1_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.interaction.random_intro_1", "How can I help you stranger ?"));
/* 20 */   public static final TranslationTextComponent RANDOM_INTRO_2_TITLE = new TranslationTextComponent(WyRegistry.registerName("gui.interaction.random_intro_2", "Want anything ?"));
/*    */   
/* 22 */   public static final TranslationTextComponent MARINE_HELP_PIRATE_INVASION_MESSAGE = new TranslationTextComponent(WyRegistry.registerName("gui.interaction.help_pirate_invasion.message", "We urgently need your help with an incoming pirate invasion, will you help us ?"));
/*    */   
/* 24 */   public static final TranslationTextComponent BARKEEPER_NO_RUMOR_MESSAGE = new TranslationTextComponent(WyRegistry.registerName("gui.interaction.barkeeper_no_rumor.message", "Nothing that I am aware of."));
/* 25 */   public static final TranslationTextComponent BARKEEPER_NO_BELLY_MESSAGE = new TranslationTextComponent(WyRegistry.registerName("gui.interaction.barkeeper_no_belly.message", "You think I work for free here ?"));
/*    */   
/* 27 */   public static final String NOTORIOUS_PIRATE_RUMOR_1_MESSAGE = WyRegistry.registerName("gui.interaction.notorious_pirate_rumor_1.message", "There's a rumor about a pirate named %2$s being spotted around %1$s. Might want to avoid that area until they leave if you know whats best for you.");
/* 28 */   public static final String NOTORIOUS_PIRATE_RUMOR_2_MESSAGE = WyRegistry.registerName("gui.interaction.notorious_pirate_rumor_2.message", "Stay away from %1$s for now, overheard some marines talking about how they plan to ambush %2$s over there.");
/* 29 */   public static final String NOTORIOUS_MARINE_RUMOR_1_MESSAGE = WyRegistry.registerName("gui.interaction.notorious_marine_rumor_1.message", "Heard some marines talk about how %2$s was at %1$s waiting to ambush some pirates, poor lads are done for.");
/* 30 */   public static final String NOTORIOUS_MARINE_RUMOR_2_MESSAGE = WyRegistry.registerName("gui.interaction.notorious_marine_rumor_2.message", "There's a rumor about %2$s camping around %1$s. Who knows what they're doing there.");
/*    */   
/* 32 */   public static final String CARAVAN_RUMOR_1_MESSAGE = WyRegistry.registerName("gui.interaction.caravan_rumor_1.message", "Apparently a caravan of marines was sighted around %s, might even transport some rare goods with them.");
/*    */   
/* 34 */   public static final String PIRATE_NEWS_1_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_1.message", "This pirate named %1$s is quite a popular subject these days. Apparently he's a savage one destroying %2$s towns so far.");
/* 35 */   public static final String PIRATE_NEWS_2_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_2.message", "Heard about %1$s ? Today's newspaper got his new wanted poster his bounty went up to %2$s.");
/* 36 */   public static final String PIRATE_NEWS_3_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_3.message", "This %1$s fella roaming the seas taking on any Marine battleships they catch. %2$s so far and I don't think he's going to stop.");
/* 37 */   public static final String PIRATE_NEWS_4_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_4.message", "%1$s's bounty went up again, currently it sits at %2$s.");
/* 38 */   public static final String PIRATE_NEWS_5_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_5.message", "A Marine base got destroyed by %1$s singlehandledly, No new bounty was issued in his name but I doubt it'll stay like that for long.");
/* 39 */   public static final String PIRATE_NEWS_6_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_6.message", "%1$s attacked yet another town %2$s killed only in this one.");
/* 40 */   public static final String PIRATE_NEWS_7_MESSAGE = WyRegistry.registerName("gui.interaction.pirate_news_7.message", "%2$s more Marine battleships sunk by %1$s and his crew yesterday.");
/*    */   
/* 42 */   public static final String MARINE_NEWS_1_MESSAGE = WyRegistry.registerName("gui.interaction.marine_news_1.message", "%1$s is on a rampage, sinking %2$s pirate ships this week alone.");
/* 43 */   public static final String MARINE_NEWS_2_MESSAGE = WyRegistry.registerName("gui.interaction.marine_news_2.message", "%2$s more pirates arrested by %1$s.");
/* 44 */   public static final String MARINE_NEWS_3_MESSAGE = WyRegistry.registerName("gui.interaction.marine_news_3.message", "An attack on a pirate town lead by %1$s managed to arrest %2$s of them.");
/* 45 */   public static final String MARINE_NEWS_4_MESSAGE = WyRegistry.registerName("gui.interaction.marine_news_4.message", "The Marines are finally doing some work. %1$s took down %2$s pirate ships yesterday.");
/* 46 */   public static final String MARINE_NEWS_5_MESSAGE = WyRegistry.registerName("gui.interaction.marine_news_5.message", "%1$s just took down another pirate ship, bringing their total number of ships sunk to %2$s.");
/* 47 */   public static final String MARINE_NEWS_6_MESSAGE = WyRegistry.registerName("gui.interaction.marine_news_6.message", "Have you heard about %1$s ? Today's newspaper says they've received a commendation for taking down an entire pirate fleet");
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModI18nInteractions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */