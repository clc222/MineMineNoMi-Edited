/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong;
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DugongCheerGoal extends TickedGoal<AbstractDugongEntity> {
/* 12 */   private Interval canUseInterval = new Interval(10);
/*    */   
/*    */   public DugongCheerGoal(AbstractDugongEntity entity) {
/* 15 */     super((MobEntity)entity);
/* 16 */     func_220684_a(EnumSet.of(Goal.Flag.TARGET, Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 21 */     if (!this.canUseInterval.canTick()) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     if (((AbstractDugongEntity)this.entity).isEnraged()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     if (((AbstractDugongEntity)this.entity).getCheerTarget() == null) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 42 */     if (((AbstractDugongEntity)this.entity).isEnraged()) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     if (GoalUtil.shouldMove(this.entity)) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     if (!((AbstractDugongEntity)this.entity).isCheering()) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     if (((AbstractDugongEntity)this.entity).getCheerTarget() == null || !((AbstractDugongEntity)this.entity).getCheerTarget().func_70089_S()) {
/* 55 */       return false;
/*    */     }
/*    */     
/* 58 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/* 59 */       return false;
/*    */     }
/*    */     
/* 62 */     return !hasTimePassedSinceStart(WyHelper.secondsToTicks(30.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 67 */     super.func_75249_e();
/* 68 */     ((AbstractDugongEntity)this.entity).func_70661_as().func_75499_g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 73 */     if (((AbstractDugongEntity)this.entity).field_70170_p.func_82737_E() % 5L == 0L) {
/* 74 */       ((AbstractDugongEntity)this.entity).func_70683_ar().func_75660_a();
/*    */     }
/* 76 */     GoalUtil.lookAtEntity(this.entity, (Entity)((AbstractDugongEntity)this.entity).getCheerTarget());
/* 77 */     super.func_75246_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 82 */     super.func_75251_c();
/* 83 */     ((AbstractDugongEntity)this.entity).setCheering(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\dugong\DugongCheerGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */