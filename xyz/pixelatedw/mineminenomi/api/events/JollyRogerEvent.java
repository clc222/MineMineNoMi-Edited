/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*    */ 
/*    */ public class JollyRogerEvent
/*    */   extends Event
/*    */ {
/*    */   private JollyRoger jollyRoger;
/*    */   private Crew crew;
/*    */   
/*    */   public JollyRogerEvent(JollyRoger jollyRoger, Crew crew) {
/* 14 */     this.jollyRoger = jollyRoger;
/* 15 */     this.crew = crew;
/*    */   }
/*    */ 
/*    */   
/*    */   public Crew getCrew() {
/* 20 */     return this.crew;
/*    */   }
/*    */ 
/*    */   
/*    */   public JollyRoger getJollyRoger() {
/* 25 */     return this.jollyRoger;
/*    */   }
/*    */   
/*    */   public static class Update
/*    */     extends JollyRogerEvent {
/*    */     public Update(JollyRoger jollyRoger, Crew crew) {
/* 31 */       super(jollyRoger, crew);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\JollyRogerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */