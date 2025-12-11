/*    */ package xyz.pixelatedw.mineminenomi.events.world;
/*    */ 
/*    */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.config.WorldFeaturesConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBiomes;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class WorldGenerationEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void biomeLoadingEvent(BiomeLoadingEvent event) {
/* 15 */     if (!((Boolean)WorldFeaturesConfig.SPAWN_BIOMES.get()).booleanValue()) {
/*    */       return;
/*    */     }
/*    */     
/* 19 */     ModBiomes.generateBiomes();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\world\WorldGenerationEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */