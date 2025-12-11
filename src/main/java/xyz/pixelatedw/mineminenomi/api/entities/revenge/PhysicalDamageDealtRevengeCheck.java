/*    */ package xyz.pixelatedw.mineminenomi.api.entities.revenge;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ 
/*    */ public class PhysicalDamageDealtRevengeCheck
/*    */   implements IRevengeCheck
/*    */ {
/*    */   private float prevHealth;
/*    */   private float lostHealth;
/* 12 */   private float multiplier = 1.0F;
/*    */ 
/*    */   
/*    */   public PhysicalDamageDealtRevengeCheck() {}
/*    */   
/*    */   public PhysicalDamageDealtRevengeCheck(float multipler) {
/* 18 */     this.multiplier = multipler;
/*    */   }
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/*    */     int i;
/* 23 */     if (this.prevHealth == 0.0F) {
/* 24 */       this.prevHealth = entity.func_110143_aJ();
/*    */     }
/*    */     
/* 27 */     boolean isPhysical = false;
/*    */     
/* 29 */     DamageSource source = entity.func_189748_bU();
/* 30 */     if (source != null) {
/* 31 */       i = isPhysical | ((source instanceof net.minecraft.util.EntityDamageSource && source.field_76373_n.equals("player")) ? 1 : 0);
/* 32 */       i |= (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).isPhysical()) ? 1 : 0;
/*    */     } 
/*    */     
/* 35 */     if (i != 0 && entity.func_110143_aJ() < this.prevHealth) {
/* 36 */       this.lostHealth = this.prevHealth - entity.func_110143_aJ();
/* 37 */       this.prevHealth = entity.func_110143_aJ();
/* 38 */       return true;
/*    */     } 
/*    */     
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetMarkers() {
/* 46 */     this.prevHealth = 0.0F;
/* 47 */     this.lostHealth = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int revengeMeterGain() {
/* 52 */     return Math.round(this.lostHealth * this.multiplier);
/*    */   }
/*    */   
/*    */   public float getDamagePool() {
/* 56 */     return this.lostHealth;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\revenge\PhysicalDamageDealtRevengeCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */