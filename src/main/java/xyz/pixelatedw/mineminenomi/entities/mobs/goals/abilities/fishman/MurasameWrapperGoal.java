/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MurasameWrapperGoal extends AbilityWrapperGoal<MobEntity, MurasameAbility> {
/* 17 */   private double minDistance = 10.0D; private LivingEntity target;
/* 18 */   private double maxDistance = 30.0D;
/*    */   
/*    */   public MurasameWrapperGoal(MobEntity entity) {
/* 21 */     super(entity, MurasameAbility.INSTANCE);
/* 22 */     this.maxDistance = ((Integer)((MurasameAbility)getAbility()).getComponent(ModAbilityKeys.PROJECTILE).flatMap(comp -> Optional.ofNullable(Integer.valueOf(comp.getNewProjectile((LivingEntity)entity).getMaxLife()))).orElse(Integer.valueOf(30))).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 27 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     this.target = this.entity.func_70638_az();
/*    */     
/* 33 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, this.minDistance)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     UchimizuAbility uchimizuAbility = (UchimizuAbility)getProps().getEquippedAbility(UchimizuAbility.INSTANCE);
/* 46 */     if (uchimizuAbility != null) {
/* 47 */       Optional<CooldownComponent> comp = uchimizuAbility.getComponent(ModAbilityKeys.COOLDOWN);
/* 48 */       boolean cooldownCheck = (comp.isPresent() && ((CooldownComponent)comp.get()).getCooldown() > WyHelper.percentage(75.0D, ((CooldownComponent)comp.get()).getStartCooldown()));
/* 49 */       if (uchimizuAbility.isContinuous() || cooldownCheck) {
/* 50 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 59 */     return ((MurasameAbility)getAbility()).isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 64 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 69 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\fishman\MurasameWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */