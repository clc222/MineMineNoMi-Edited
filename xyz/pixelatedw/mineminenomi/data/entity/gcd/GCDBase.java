/*    */ package xyz.pixelatedw.mineminenomi.data.entity.gcd;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class GCDBase
/*    */   implements IGCD {
/*    */   private static final int DEFAULT_GCD = 20;
/*    */   private LivingEntity owner;
/* 13 */   private int previousGCD = 20;
/* 14 */   private Interval gcdInterval = (Interval)(new Interval(20)).trackTPS();
/*    */   
/*    */   private boolean onGCD = false;
/*    */   
/*    */   public IGCD setOwner(LivingEntity owner) {
/* 19 */     this.owner = owner;
/* 20 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startGCD() {
/* 25 */     if (isOnGCD()) {
/*    */       return;
/*    */     }
/* 28 */     int defaultGCD = getDefaultGCD();
/* 29 */     if (this.previousGCD != defaultGCD) {
/* 30 */       this.previousGCD = defaultGCD;
/* 31 */       this.gcdInterval = (Interval)(new Interval(defaultGCD)).trackTPS();
/*    */     } 
/* 33 */     this.gcdInterval.restartIntervalToMax();
/* 34 */     this.onGCD = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnGCD() {
/* 39 */     return this.onGCD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickGCD() {
/* 44 */     if (this.onGCD && 
/* 45 */       this.gcdInterval.canTick()) {
/* 46 */       this.onGCD = false;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCurrentGCD() {
/* 53 */     return this.gcdInterval.getTick();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDefaultGCD() {
/*    */     try {
/* 59 */       ModifiableAttributeInstance attr = this.owner.func_110148_a((Attribute)ModAttributes.GCD.get());
/* 60 */       if (attr == null) {
/* 61 */         return 20;
/*    */       }
/* 63 */       return (int)Math.round(attr.func_111126_e());
/*    */     }
/* 65 */     catch (Exception ex) {
/* 66 */       return 20;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\gcd\GCDBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */