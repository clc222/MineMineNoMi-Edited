/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DugongTrainGoal
/*    */   extends TickedGoal<AbstractDugongEntity> {
/* 14 */   private Interval interval = new Interval(10);
/*    */   private int trainingTime;
/* 16 */   private int nextTrainingTime = 2;
/*    */   
/*    */   public DugongTrainGoal(AbstractDugongEntity entity) {
/* 19 */     super((MobEntity)entity);
/* 20 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (!this.interval.canTick()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     if (!((AbstractDugongEntity)this.entity).isIdling()) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (getLastEndTick() <= 0L) {
/* 34 */       setLastEndTick(((AbstractDugongEntity)this.entity).field_70170_p.func_82737_E());
/*    */     }
/*    */     
/* 37 */     if (!hasTimePassedSinceLastEnd(WyHelper.minutesToTicks(this.nextTrainingTime))) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 50 */     if (GoalUtil.shouldMove(this.entity)) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 55 */       return false;
/*    */     }
/*    */     
/* 58 */     return !hasTimePassedSinceStart(WyHelper.secondsToTicks(this.trainingTime));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 63 */     super.func_75249_e();
/* 64 */     int mode = 1 + AbstractDugongEntity.TrainingMethod.values()[((AbstractDugongEntity)this.entity).func_70681_au().nextInt((AbstractDugongEntity.TrainingMethod.values()).length)].ordinal();
/* 65 */     ((AbstractDugongEntity)this.entity).setTraining(mode);
/* 66 */     this.trainingTime = 10 + ((AbstractDugongEntity)this.entity).func_70681_au().nextInt(5);
/* 67 */     this.nextTrainingTime = 2 + ((AbstractDugongEntity)this.entity).func_70681_au().nextInt(3);
/* 68 */     ((AbstractDugongEntity)this.entity).func_70661_as().func_75499_g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 73 */     super.func_75251_c();
/* 74 */     ((AbstractDugongEntity)this.entity).stopTraining();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\dugong\DugongTrainGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */