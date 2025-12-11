/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.interactions.BarkeeperBuyRumInteraction;
/*    */ import xyz.pixelatedw.mineminenomi.interactions.BarkeeperInteraction;
/*    */ import xyz.pixelatedw.mineminenomi.interactions.BarkeeperNewsInteraction;
/*    */ import xyz.pixelatedw.mineminenomi.interactions.BarkeeperRumorInteraction;
/*    */ import xyz.pixelatedw.mineminenomi.interactions.NotoriousTargetBountyInteraction;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModInteractions {
/* 14 */   public static final RegistryObject<Interaction> BARKEEPER = WyRegistry.registerInteraction("barkeeper", BarkeeperInteraction::interaction);
/*    */   
/* 16 */   public static final RegistryObject<Interaction> BARKEEPER_RUMOR = WyRegistry.registerInteraction("barkeeper_rumor", BarkeeperRumorInteraction::rumor);
/* 17 */   public static final RegistryObject<Interaction> BARKEEPER_RUMOR_CONTINUE = WyRegistry.registerInteraction("barkeeper_rumor_continue", BarkeeperRumorInteraction::close);
/* 18 */   public static final RegistryObject<Interaction> BARKEEPER_RUMOR_NONE = WyRegistry.registerInteraction("barkeeper_rumor_none", BarkeeperRumorInteraction::noEvent);
/* 19 */   public static final RegistryObject<Interaction> BARKEEPER_RUMOR_NO_BELLY = WyRegistry.registerInteraction("barkeeper_rumor_no_belly", BarkeeperRumorInteraction::noBelly);
/*    */   
/* 21 */   public static final RegistryObject<Interaction> BARKEEPER_NEWS = WyRegistry.registerInteraction("barkeeper_news", BarkeeperNewsInteraction::news);
/*    */   
/* 23 */   public static final RegistryObject<Interaction> BARKEEPER_BUY_RUM = WyRegistry.registerInteraction("barkeeper_buy_rum", BarkeeperBuyRumInteraction::buyRum);
/* 24 */   public static final RegistryObject<Interaction> BARKEEPER_BUY_RUM_CONTINUE = WyRegistry.registerInteraction("barkeeper_buy_rum_continue", BarkeeperBuyRumInteraction::close);
/* 25 */   public static final RegistryObject<Interaction> BARKEEPER_BUY_RUM_NO_BELLY = WyRegistry.registerInteraction("barkeeper_buy_rum_no_belly", BarkeeperRumorInteraction::noBelly);
/*    */   
/* 27 */   public static final RegistryObject<Interaction> NOTORIOUS_TARGET_BOUNTY = WyRegistry.registerInteraction("notorious_target_bounty", NotoriousTargetBountyInteraction::start);
/* 28 */   public static final RegistryObject<Interaction> NOTORIOUS_TARGET_BOUNTY_ACCEPT = WyRegistry.registerInteraction("notorious_target_bounty_accept", NotoriousTargetBountyInteraction::accept);
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 31 */     WyRegistry.INTERACTIONS.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModInteractions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */