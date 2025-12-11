/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ 
/*    */ public class ProjectileHitRevengeCheck
/*    */   implements IRevengeCheck
/*    */ {
/*    */   private final int revengeGain;
/*    */   private float prevHealth;
/*    */   private int hitCount;
/*    */   
/*    */   public ProjectileHitRevengeCheck(int revengeGain) {
/* 14 */     this.revengeGain = revengeGain;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/* 19 */     if (this.prevHealth == 0.0F) {
/* 20 */       this.prevHealth = entity.func_110143_aJ();
/*    */     }
/*    */     
/* 23 */     boolean isProjectile = false;
/*    */     
/* 25 */     DamageSource source = entity.func_189748_bU();
/* 26 */     if (source != null) {
/* 27 */       isProjectile |= source.func_76352_a();
/*    */     }
/*    */     
/* 30 */     if (isProjectile && entity.func_110143_aJ() < this.prevHealth) {
/* 31 */       this.hitCount++;
/* 32 */       this.prevHealth = entity.func_110143_aJ();
/* 33 */       return true;
/*    */     } 
/*    */     
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetMarkers() {
/* 41 */     this.prevHealth = 0.0F;
/* 42 */     this.hitCount = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 47 */     return this.revengeGain;
/*    */   }
/*    */   
/*    */   public int getHits() {
/* 51 */     return this.hitCount;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\ProjectileHitRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */