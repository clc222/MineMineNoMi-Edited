/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.awa;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.awa.SoapDefenseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class SoapDefenseWrapperGoal
/*    */   extends AbilityWrapperGoal<MobEntity, SoapDefenseAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 10.0D;
/* 13 */   private int lastHurtTicks = 60;
/* 14 */   private int hits = 0;
/*    */   private long lastHitTimestamp;
/*    */   
/*    */   public SoapDefenseWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, SoapDefenseAbility.INSTANCE);
/*    */   }
/*    */   
/*    */   public SoapDefenseWrapperGoal setMinDistance(double distance) {
/* 22 */     this.distance = distance;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 28 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     this.target = this.entity.func_70638_az();
/*    */     
/* 34 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     if (this.hits < 3) {
/* 39 */       if (this.lastHitTimestamp == 0L) {
/* 40 */         this.lastHitTimestamp = this.entity.field_70170_p.func_82737_E();
/*    */       }
/*    */       
/* 43 */       if (GoalUtil.lastHurtCheckBefore((LivingEntity)this.entity, this.lastHurtTicks) && this.entity.field_70170_p.func_82737_E() > this.lastHitTimestamp + this.lastHurtTicks) {
/* 44 */         this.hits++;
/* 45 */         this.lastHitTimestamp = this.entity.field_70170_p.func_82737_E();
/*    */       } 
/* 47 */       return false;
/*    */     } 
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 55 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance * 1.5D)) {
/* 60 */       return false;
/*    */     }
/*    */     
/* 63 */     if (GoalUtil.lastHurtCheckAfter((LivingEntity)this.entity, (int)(this.lastHurtTicks / 1.5D))) {
/* 64 */       return false;
/*    */     }
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 72 */     this.hits = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {}
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 81 */     this.hits = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\awa\SoapDefenseWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */