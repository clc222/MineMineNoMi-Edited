/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.eventbus.api.Event.HasResult;
/*    */ 
/*    */ @HasResult
/*    */ public class SetCameraZoomEvent
/*    */   extends Event {
/*    */   private ClientPlayerEntity player;
/*    */   private double zoom;
/*    */   
/*    */   public SetCameraZoomEvent(ClientPlayerEntity player, double zoom) {
/* 14 */     this.player = player;
/* 15 */     this.zoom = zoom;
/*    */   }
/*    */   
/*    */   public ClientPlayerEntity getPlayer() {
/* 19 */     return this.player;
/*    */   }
/*    */   
/*    */   public double getZoom() {
/* 23 */     return this.zoom;
/*    */   }
/*    */   
/*    */   public void setZoom(double zoom) {
/* 27 */     this.zoom = zoom;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\SetCameraZoomEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */