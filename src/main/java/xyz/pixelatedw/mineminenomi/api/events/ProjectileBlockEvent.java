/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class ProjectileBlockEvent
/*    */   extends Event
/*    */ {
/*    */   private Entity projectile;
/*    */   private boolean canBlock;
/*    */   
/*    */   public ProjectileBlockEvent(Entity entity) {
/* 15 */     this.projectile = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getProjectile() {
/* 20 */     return this.projectile;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCanBlock(boolean flag) {
/* 25 */     this.canBlock = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlock() {
/* 30 */     return this.canBlock;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ProjectileBlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */