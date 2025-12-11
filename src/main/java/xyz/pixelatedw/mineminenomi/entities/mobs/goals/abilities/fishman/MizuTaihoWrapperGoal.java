/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MizuTaihoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class MizuTaihoWrapperGoal extends AbilityWrapperGoal<MobEntity, MizuTaihoAbility> {
/*    */   private LivingEntity target;
/* 15 */   private double minDistance = 2.0D;
/* 16 */   private double maxDistance = 30.0D;
/*    */   
/*    */   public MizuTaihoWrapperGoal(MobEntity entity) {
/* 19 */     super(entity, MizuTaihoAbility.INSTANCE);
/* 20 */     this.maxDistance = ((Integer)((MizuTaihoAbility)getAbility()).getComponent(ModAbilityKeys.PROJECTILE).flatMap(comp -> Optional.ofNullable(Integer.valueOf(comp.getNewProjectile((LivingEntity)entity).getMaxLife()))).orElse(Integer.valueOf(30))).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 25 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     this.target = this.entity.func_70638_az();
/*    */     
/* 31 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 53 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 58 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\fishman\MizuTaihoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */