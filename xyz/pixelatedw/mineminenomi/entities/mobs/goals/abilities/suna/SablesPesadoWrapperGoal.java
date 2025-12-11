/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.SablesPesadoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class SablesPesadoWrapperGoal extends AbilityWrapperGoal<MobEntity, SablesPesadoAbility> {
/*    */   private LivingEntity target;
/* 12 */   private float distance = 30.0F;
/*    */   
/*    */   public SablesPesadoWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, SablesPesadoAbility.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseWrapper() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     this.target = this.entity.func_70638_az();
/*    */     
/* 26 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, this.distance)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 44 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopWrapper() {
/* 49 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\suna\SablesPesadoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */