/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ 
/*    */ public class CrewEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private Crew crew;
/*    */   
/*    */   public CrewEvent(PlayerEntity player, Crew crew) {
/* 14 */     super(player);
/* 15 */     this.crew = crew;
/*    */   }
/*    */ 
/*    */   
/*    */   public Crew getCrew() {
/* 20 */     return this.crew;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Create
/*    */     extends CrewEvent
/*    */   {
/*    */     public Create(PlayerEntity player, Crew crew) {
/* 28 */       super(player, crew);
/*    */     }
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Join
/*    */     extends CrewEvent
/*    */   {
/*    */     public Join(PlayerEntity player, Crew crew) {
/* 37 */       super(player, crew);
/*    */     }
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Leave
/*    */     extends CrewEvent
/*    */   {
/*    */     public Leave(PlayerEntity player, Crew crew) {
/* 46 */       super(player, crew);
/*    */     }
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Kick
/*    */     extends CrewEvent
/*    */   {
/*    */     public Kick(PlayerEntity player, Crew crew) {
/* 55 */       super(player, crew);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\CrewEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */