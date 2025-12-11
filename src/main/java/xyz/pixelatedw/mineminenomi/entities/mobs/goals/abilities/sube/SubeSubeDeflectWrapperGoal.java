/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sube;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sube.SubeSubeDeflectAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class SubeSubeDeflectWrapperGoal extends AbilityWrapperGoal<MobEntity, SubeSubeDeflectAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 10.0D;
/* 13 */   private int lastHurtTicks = 60;
/* 14 */   private int hits = 0;
/*    */   private long lastHitTimestamp;
/*    */   
/*    */   public SubeSubeDeflectWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, SubeSubeDeflectAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 23 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     this.target = this.entity.func_70638_az();
/*    */     
/* 29 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (this.hits < 2) {
/* 34 */       if (this.lastHitTimestamp == 0L) {
/* 35 */         this.lastHitTimestamp = this.entity.field_70170_p.func_82737_E();
/*    */       }
/*    */       
/* 38 */       if (GoalUtil.lastHurtCheckBefore((LivingEntity)this.entity, this.lastHurtTicks) && this.entity.field_70170_p.func_82737_E() > this.lastHitTimestamp + this.lastHurtTicks) {
/* 39 */         this.hits++;
/* 40 */         this.lastHitTimestamp = this.entity.field_70170_p.func_82737_E();
/*    */       } 
/* 42 */       return false;
/*    */     } 
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 50 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     if (!GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.distance * 1.5D)) {
/* 55 */       return false;
/*    */     }
/*    */     
/* 58 */     if (GoalUtil.lastHurtCheckAfter((LivingEntity)this.entity, (int)(this.lastHurtTicks / 1.5D))) {
/* 59 */       return false;
/*    */     }
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 67 */     this.hits = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 72 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 77 */     this.hits = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\sube\SubeSubeDeflectWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */