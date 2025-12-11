/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ 
/*    */ public class TauntTargetGoal extends TickedGoal<MobEntity> {
/*    */   private LivingEntity target;
/* 12 */   private int lastUpdateTick = 20;
/*    */   
/*    */   public TauntTargetGoal(MobEntity entity) {
/* 15 */     super(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 20 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     this.target = this.entity.func_70638_az();
/*    */     
/* 26 */     if (!GoalUtil.canSee(this.entity, this.target)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 35 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 44 */     super.func_75246_d();
/*    */     
/* 46 */     GoalUtil.lookAtEntity(this.entity, (Entity)this.target);
/*    */     
/* 48 */     this.lastUpdateTick--;
/* 49 */     if (this.lastUpdateTick <= 10) {
/* 50 */       this.entity.func_213301_b(Pose.CROUCHING);
/*    */     } else {
/*    */       
/* 53 */       this.entity.func_213301_b(Pose.STANDING);
/*    */     } 
/*    */     
/* 56 */     if (this.lastUpdateTick <= 0) {
/* 57 */       this.lastUpdateTick = 20;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 63 */     super.func_75251_c();
/* 64 */     this.entity.func_213301_b(Pose.STANDING);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\TauntTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */