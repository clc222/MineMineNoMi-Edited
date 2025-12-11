/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ @Cancelable
/*    */ public class ProjectileHitEvent
/*    */   extends Event
/*    */ {
/*    */   private AbilityProjectileEntity projectile;
/*    */   private RayTraceResult hit;
/*    */   
/*    */   public ProjectileHitEvent(AbilityProjectileEntity abilityProjectileEntity, RayTraceResult hit) {
/* 16 */     this.projectile = abilityProjectileEntity;
/* 17 */     this.hit = hit;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityProjectileEntity getProjectile() {
/* 22 */     return this.projectile;
/*    */   }
/*    */ 
/*    */   
/*    */   public RayTraceResult getHit() {
/* 27 */     return this.hit;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ProjectileHitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */