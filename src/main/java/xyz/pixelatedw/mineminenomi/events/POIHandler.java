/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.poi.INotoriousTarget;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*    */ 
/*    */ public class POIHandler {
/*    */   public static void killNotoriousTarget(ServerWorld world, INotoriousTarget target) {
/* 10 */     NPCWorldData npcWorldData = NPCWorldData.get();
/* 11 */     EventsWorldData eventsWorldData = EventsWorldData.get();
/*    */     
/* 13 */     npcWorldData.removeTrackedMob(world, target.getTrackedData());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\POIHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */