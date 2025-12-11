/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SuplexWrapperGoal extends AbilityWrapperGoal<MobEntity, SuplexAbility> {
/*    */   private LivingEntity target;
/*    */   private float distance;
/*    */   
/*    */   public SuplexWrapperGoal(MobEntity entity) {
/* 18 */     super(entity, SuplexAbility.INSTANCE);
/* 19 */     this.distance = ((Float)((SuplexAbility)getAbility()).getComponent(ModAbilityKeys.RANGE).flatMap(comp -> Optional.of(Float.valueOf(comp.getRange()))).orElse(Float.valueOf(6.0F))).floatValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 24 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     this.target = this.entity.func_70638_az();
/*    */     
/* 30 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 35 */       return false;
/*    */     }
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 43 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, (this.distance * 2.0F))) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 56 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 57 */     this.entity.func_70661_as().func_75499_g();
/* 58 */     this.entity.func_70661_as().func_75497_a((Entity)this.target, 1.2000000476837158D);
/*    */   }
/*    */   
/*    */   public void tickWrapper() {}
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\SuplexWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */