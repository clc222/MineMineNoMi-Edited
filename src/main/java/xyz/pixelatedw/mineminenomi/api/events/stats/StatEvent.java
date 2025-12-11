/*    */ package xyz.pixelatedw.mineminenomi.api.events.stats;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ 
/*    */ public abstract class StatEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private StatChangeSource source;
/*    */   
/*    */   public StatEvent(PlayerEntity entity, StatChangeSource source) {
/* 13 */     super(entity);
/* 14 */     this.source = source;
/*    */   }
/*    */ 
/*    */   
/*    */   public StatChangeSource getSource() {
/* 19 */     return this.source;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\stats\StatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */