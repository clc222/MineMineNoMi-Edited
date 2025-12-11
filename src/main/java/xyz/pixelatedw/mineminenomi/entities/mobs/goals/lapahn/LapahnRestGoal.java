/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.lapahn;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LapahnRestGoal extends TickedGoal<LapahnEntity> {
/* 13 */   private Interval interval = new Interval(40);
/*    */   private int restTime;
/* 15 */   private int nextRestTime = 2;
/*    */   private float startHealth;
/*    */   
/*    */   public LapahnRestGoal(LapahnEntity entity) {
/* 19 */     super((MobEntity)entity);
/* 20 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 26 */     if (!((LapahnEntity)this.entity).isIdling()) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     if (!this.interval.canTick()) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     if (getLastEndTick() <= 0L) {
/* 35 */       setLastEndTick(((LapahnEntity)this.entity).field_70170_p.func_82737_E());
/*    */     }
/*    */     
/* 38 */     if (!hasTimePassedSinceLastEnd(WyHelper.minutesToTicks(this.nextRestTime))) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 51 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 52 */       return false;
/*    */     }
/*    */     
/* 55 */     if (((LapahnEntity)this.entity).func_110143_aJ() < this.startHealth) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     return !hasTimePassedSinceStart(WyHelper.secondsToTicks(this.restTime));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 64 */     super.func_75249_e();
/* 65 */     ((LapahnEntity)this.entity).setResting(true);
/* 66 */     this.restTime = 20 + ((LapahnEntity)this.entity).func_70681_au().nextInt(20);
/* 67 */     this.nextRestTime = 2 + ((LapahnEntity)this.entity).func_70681_au().nextInt(3);
/* 68 */     ((LapahnEntity)this.entity).func_70661_as().func_75499_g();
/* 69 */     this.startHealth = ((LapahnEntity)this.entity).func_110143_aJ();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 74 */     super.func_75251_c();
/* 75 */     ((LapahnEntity)this.entity).setResting(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\lapahn\LapahnRestGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */