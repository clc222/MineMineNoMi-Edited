/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class ChargingGoal<T extends OPEntity> extends CooldownGoal<T> {
/*    */   private boolean isCharging = false;
/*  8 */   private int maxCharge = 1;
/*    */   
/*    */   public ChargingGoal(T entity) {
/* 11 */     super(entity);
/* 12 */     this.entity = entity;
/*    */   }
/*    */   private int charge;
/*    */   
/*    */   public boolean func_75250_a() {
/* 17 */     if (!super.func_75250_a()) {
/* 18 */       return false;
/*    */     }
/*    */     
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 26 */     return (!isOnCooldown() && isCharging());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 31 */     super.func_75249_e();
/* 32 */     this.isCharging = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 37 */     super.func_75251_c();
/* 38 */     stopCharging();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 43 */     if (this.isCharging) {
/* 44 */       this.charge++;
/* 45 */       if (this.charge >= this.maxCharge) {
/* 46 */         stopCharging();
/*    */       }
/*    */     } 
/*    */     
/* 50 */     super.func_75246_d();
/*    */   }
/*    */   
/*    */   public void setMaxCharge(double seconds) {
/* 54 */     this.maxCharge = (int)(seconds * 20.0D);
/*    */   }
/*    */   
/*    */   public void stopCharging() {
/* 58 */     this.isCharging = false;
/* 59 */     this.charge = 0;
/*    */   }
/*    */   
/*    */   public boolean isCharging() {
/* 63 */     return this.isCharging;
/*    */   }
/*    */   
/*    */   public int getCurrentCharge() {
/* 67 */     return this.charge;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxCharge() {
/* 72 */     return this.maxCharge;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\ChargingGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */