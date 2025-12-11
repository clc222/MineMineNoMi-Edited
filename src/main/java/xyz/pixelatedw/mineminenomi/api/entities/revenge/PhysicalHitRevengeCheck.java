/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ 
/*    */ 
/*    */ public class PhysicalHitRevengeCheck
/*    */   implements IRevengeCheck
/*    */ {
/*    */   private final int revengeGain;
/*    */   private float prevHealth;
/*    */   private int hitCount;
/*    */   
/*    */   public PhysicalHitRevengeCheck(int revengeGain) {
/* 16 */     this.revengeGain = revengeGain;
/*    */   }
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/*    */     int i;
/* 21 */     if (this.prevHealth == 0.0F) {
/* 22 */       this.prevHealth = entity.func_110143_aJ();
/*    */     }
/*    */     
/* 25 */     boolean isPhysical = false;
/*    */     
/* 27 */     DamageSource source = entity.func_189748_bU();
/* 28 */     if (source != null) {
/* 29 */       i = isPhysical | ((source instanceof net.minecraft.util.EntityDamageSource && source.field_76373_n.equals("player")) ? 1 : 0);
/* 30 */       i |= (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).isPhysical()) ? 1 : 0;
/*    */     } 
/*    */     
/* 33 */     if (i != 0 && entity.func_110143_aJ() < this.prevHealth) {
/* 34 */       this.hitCount++;
/* 35 */       this.prevHealth = entity.func_110143_aJ();
/* 36 */       return true;
/*    */     } 
/*    */     
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetMarkers() {
/* 44 */     this.prevHealth = 0.0F;
/* 45 */     this.hitCount = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 50 */     return this.revengeGain;
/*    */   }
/*    */   
/*    */   public int getHits() {
/* 54 */     return this.hitCount;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\PhysicalHitRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */