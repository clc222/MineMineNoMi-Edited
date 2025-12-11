/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.eventbus.api.Event.HasResult;
/*    */ 
/*    */ @HasResult
/*    */ public class SetCameraOffsetEvent
/*    */   extends Event {
/*    */   private ClientPlayerEntity player;
/*    */   private Vector3d cameraPos;
/*    */   
/*    */   public SetCameraOffsetEvent(ClientPlayerEntity player, Vector3d cameraPos) {
/* 15 */     this.player = player;
/* 16 */     this.cameraPos = cameraPos;
/*    */   }
/*    */   
/*    */   public ClientPlayerEntity getPlayer() {
/* 20 */     return this.player;
/*    */   }
/*    */   
/*    */   public Vector3d getCameraPosition() {
/* 24 */     return this.cameraPos;
/*    */   }
/*    */   
/*    */   public void setCameraPosition(Vector3d cameraPos) {
/* 28 */     this.cameraPos = cameraPos;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\SetCameraOffsetEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */