/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.electro;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalLunaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class ElectricalLunaWrapperGoal extends AbilityWrapperGoal<MobEntity, ElectricalLunaAbility> {
/*    */   private LivingEntity target;
/* 15 */   private float distance = 20.0F;
/*    */   
/*    */   private ContinuousComponent continuousComponent;
/*    */   private SwingTriggerComponent swingTriggerComponent;
/*    */   
/*    */   public ElectricalLunaWrapperGoal(MobEntity entity) {
/* 21 */     super(entity, ElectricalLunaAbility.INSTANCE);
/* 22 */     this.continuousComponent = ((ElectricalLunaAbility)getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).get();
/* 23 */     this.swingTriggerComponent = ((ElectricalLunaAbility)getAbility()).getComponent(ModAbilityKeys.SWING_TRIGGER).get();
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
/* 38 */     if (!GoalUtil.isEntityInView((LivingEntity)this.entity, (Entity)this.target)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 56 */     if (this.continuousComponent.isContinuous())
/* 57 */       this.swingTriggerComponent.swing((LivingEntity)this.entity); 
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\electro\ElectricalLunaWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */