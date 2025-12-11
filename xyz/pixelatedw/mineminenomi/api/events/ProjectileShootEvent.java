/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ @Cancelable
/*    */ public class ProjectileShootEvent
/*    */   extends Event
/*    */ {
/*    */   private AbilityProjectileEntity projectile;
/*    */   private float velocity;
/*    */   private float inaccuracy;
/*    */   
/*    */   public ProjectileShootEvent(AbilityProjectileEntity abilityProjectileEntity, float velocity, float inaccuracy) {
/* 16 */     this.projectile = abilityProjectileEntity;
/* 17 */     this.velocity = velocity;
/* 18 */     this.inaccuracy = inaccuracy;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityProjectileEntity getProjectile() {
/* 23 */     return this.projectile;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getVelocity() {
/* 28 */     return this.velocity;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getInaccuracy() {
/* 33 */     return this.inaccuracy;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ProjectileShootEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */