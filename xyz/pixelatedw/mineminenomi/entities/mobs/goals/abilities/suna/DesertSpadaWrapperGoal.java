/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.DesertSpadaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class DesertSpadaWrapperGoal extends AbilityWrapperGoal<MobEntity, DesertSpadaAbility> {
/* 14 */   private float distance = 30.0F; private LivingEntity target;
/*    */   
/*    */   public DesertSpadaWrapperGoal(MobEntity entity) {
/* 17 */     super(entity, DesertSpadaAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 22 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 23 */       return false;
/*    */     }
/*    */     
/* 26 */     this.target = this.entity.func_70638_az();
/*    */     
/* 28 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     if (!GoalUtil.isAtSameHeight((LivingEntity)this.entity, this.target, 2.0D)) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 46 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 47 */     this.entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\suna\DesertSpadaWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */