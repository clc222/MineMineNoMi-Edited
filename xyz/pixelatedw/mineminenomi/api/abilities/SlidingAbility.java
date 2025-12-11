/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public abstract class SlidingAbility extends Ability {
/* 10 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true)).addTickEvent(200, this::duringContinuityEvent);
/*    */   
/*    */   public SlidingAbility(AbilityCore<? extends SlidingAbility> core) {
/* 13 */     super((AbilityCore)core);
/*    */     
/* 15 */     this.isNew = true;
/* 16 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 18 */     addUseEvent(200, this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 22 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 26 */     if (entity.func_233570_aj_() && (
/* 27 */       Math.abs(entity.func_213322_ci().func_82615_a()) < 0.2D || Math.abs(entity.func_213322_ci().func_82616_c()) < 0.2D)) {
/* 28 */       double x = MathHelper.func_151237_a(entity.func_213322_ci().func_82615_a() * getSlidingPower(), -getMaxSpeed(), getMaxSpeed());
/* 29 */       double z = MathHelper.func_151237_a(entity.func_213322_ci().func_82616_c() * getSlidingPower(), -getMaxSpeed(), getMaxSpeed());
/*    */       
/* 31 */       AbilityHelper.setDeltaMovement((Entity)entity, x, entity.func_213322_ci().func_82617_b(), z);
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract double getMaxSpeed();
/*    */   
/*    */   public abstract double getSlidingPower();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\SlidingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */