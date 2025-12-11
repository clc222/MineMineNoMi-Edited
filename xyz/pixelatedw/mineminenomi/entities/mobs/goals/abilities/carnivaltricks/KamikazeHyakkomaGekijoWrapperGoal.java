/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.carnivaltricks;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.carnivaltricks.KamikazeHyakkomaGekijoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*    */ 
/*    */ public class KamikazeHyakkomaGekijoWrapperGoal extends AbilityWrapperGoal<MobEntity, KamikazeHyakkomaGekijoAbility> {
/*    */   private LivingEntity target;
/* 12 */   private double distance = 15.0D;
/*    */   
/*    */   public KamikazeHyakkomaGekijoWrapperGoal(MobEntity entity) {
/* 15 */     super(entity, KamikazeHyakkomaGekijoAbility.INSTANCE);
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
/* 26 */     if (this.entity.func_70068_e((Entity)this.target) > this.distance * this.distance) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUseWrapper() {
/* 35 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (this.entity.func_70068_e((Entity)this.target) > this.distance * this.distance) {
/* 40 */       return false;
/*    */     }
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startWrapper() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickWrapper() {
/* 52 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */   }
/*    */   
/*    */   public void stopWrapper() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\carnivaltricks\KamikazeHyakkomaGekijoWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */