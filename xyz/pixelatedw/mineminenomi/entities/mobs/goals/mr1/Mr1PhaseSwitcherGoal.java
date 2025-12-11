/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.mr1;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr1Entity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class Mr1PhaseSwitcherGoal
/*    */   extends TickedGoal<Mr1Entity> {
/*    */   public Mr1PhaseSwitcherGoal(Mr1Entity entity) {
/* 12 */     super((MobEntity)entity);
/* 13 */     this.ultiReuseTime = entity.getChallengeInfo().isDifficultyHard() ? WyHelper.minutesToTicks(3.0F) : WyHelper.minutesToTicks(6.0F);
/*    */   }
/*    */   
/*    */   private float ultiReuseTime;
/*    */   
/*    */   public boolean func_75250_a() {
/* 19 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     if (((Mr1Entity)this.entity).func_110143_aJ() > WyHelper.percentage(50.0D, ((Mr1Entity)this.entity).func_110138_aP())) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (!((Mr1Entity)this.entity).isUltiAvailable()) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     if (getLastEndTick() > 0L && !hasTimePassedSinceLastEnd(this.ultiReuseTime)) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 40 */     if (!((Mr1Entity)this.entity).isUltiAvailable()) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 49 */     super.func_75249_e();
/* 50 */     ((Mr1Entity)this.entity).startUltiPhase();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 55 */     super.func_75246_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 60 */     super.func_75251_c();
/* 61 */     ((Mr1Entity)this.entity).startSecondPhase();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\mr1\Mr1PhaseSwitcherGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */