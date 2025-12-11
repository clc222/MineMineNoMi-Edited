/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bara;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraFestivalAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class BaraBaraFestivalWrapperGoal extends AbilityWrapperGoal<MobEntity, BaraBaraFestivalAbility> {
/* 13 */   private double distance = 10.0D; private LivingEntity target;
/*    */   
/*    */   public BaraBaraFestivalWrapperGoal(MobEntity entity) {
/* 16 */     super(entity, BaraBaraFestivalAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 21 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     this.target = this.entity.func_70638_az();
/*    */     
/* 27 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 40 */     if (hasTimePassedSinceStart(40.0F)) {
/* 41 */       boolean hasGrab = ((Boolean)((BaraBaraFestivalAbility)getAbility()).getComponent(ModAbilityKeys.GRAB).map(comp -> Boolean.valueOf(comp.hasGrabbedEntity())).orElse(Boolean.valueOf(false))).booleanValue();
/* 42 */       if (!hasGrab) {
/* 43 */         ((BaraBaraFestivalAbility)getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this.entity, 100.0F));
/* 44 */         return false;
/*    */       } 
/*    */     } 
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 52 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\bara\BaraBaraFestivalWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */