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
/*    */ public class UchimizuWrapperGoal extends AbilityWrapperGoal<MobEntity, UchimizuAbility> {
/*    */   private LivingEntity target;
/* 18 */   private int maxDistance = 30;
/*    */   
/*    */   public UchimizuWrapperGoal(MobEntity entity) {
/* 21 */     super(entity, UchimizuAbility.INSTANCE);
/* 22 */     this.maxDistance = ((Integer)((UchimizuAbility)getAbility()).getComponent(ModAbilityKeys.PROJECTILE).flatMap(comp -> Optional.ofNullable(Integer.valueOf(comp.getNewProjectile((LivingEntity)entity).getMaxLife()))).orElse(Integer.valueOf(30))).intValue();
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
/* 33 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.maxDistance)) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     MurasameAbility murasameAbility = (MurasameAbility)getProps().getEquippedAbility(MurasameAbility.INSTANCE);
/* 42 */     if (murasameAbility != null) {
/* 43 */       Optional<CooldownComponent> comp = murasameAbility.getComponent(ModAbilityKeys.COOLDOWN);
/* 44 */       boolean cooldownCheck = (comp.isPresent() && ((CooldownComponent)comp.get()).getCooldown() > WyHelper.percentage(75.0D, ((CooldownComponent)comp.get()).getStartCooldown()));
/* 45 */       if (murasameAbility.isContinuous() || cooldownCheck) {
/* 46 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 55 */     return ((UchimizuAbility)getAbility()).isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 60 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 65 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\fishman\UchimizuWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */