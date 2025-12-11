/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class SpinningBrawlWrapperGoal extends AbilityWrapperGoal<MobEntity, SpinningBrawlAbility> {
/*    */   private LivingEntity target;
/*    */   
/*    */   public SpinningBrawlWrapperGoal(MobEntity entity) {
/* 17 */     super(entity, SpinningBrawlAbility.INSTANCE);
/* 18 */     this.distance = ((Float)((SpinningBrawlAbility)getAbility()).getComponent(ModAbilityKeys.RANGE).flatMap(comp -> Optional.of(Float.valueOf(comp.getRange()))).orElse(Float.valueOf(6.0F))).floatValue();
/*    */   }
/*    */   private float distance;
/*    */   
/*    */   public boolean canUseWrapper() {
/* 23 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     this.target = this.entity.func_70638_az();
/*    */     
/* 29 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 42 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, (this.distance * 2.0F))) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startWrapper() {
/* 55 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 56 */     this.entity.func_70661_as().func_75499_g();
/* 57 */     this.entity.func_70661_as().func_75497_a((Entity)this.target, 1.2000000476837158D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 62 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/* 63 */     this.entity.func_70634_a(this.entity.func_226277_ct_(), this.entity.func_226278_cu_(), this.entity.func_226281_cx_());
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\brawler\SpinningBrawlWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */