/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.buggy;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.BuggyEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BuggyPhaseSwitcherGoal
/*    */   extends TickedGoal<BuggyEntity> {
/*    */   private static final int COOLDOWN = 600;
/*    */   private final double carPhaseHPThreshold;
/*    */   private final int carPhaseDuration;
/*    */   private boolean startNormalPhase;
/*    */   private boolean startCarPhase;
/*    */   private long carPhaseStartTick;
/*    */   
/*    */   public BuggyPhaseSwitcherGoal(BuggyEntity entity) {
/* 19 */     super((MobEntity)entity);
/* 20 */     this.carPhaseHPThreshold = entity.isDifficultyStandard() ? 20.0D : 40.0D;
/* 21 */     this.carPhaseDuration = entity.isDifficultyStandard() ? 600 : 1200;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 26 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     if (!hasTimePassedSinceLastEnd(600.0F)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     boolean carSwitchHPThreshold = (((BuggyEntity)this.entity).func_110143_aJ() < WyHelper.percentage(this.carPhaseHPThreshold, ((BuggyEntity)this.entity).func_110138_aP()));
/* 35 */     boolean carSwitchKitingThreshold = (((BuggyEntity)this.entity).getKitingMeterCompletion() > (((BuggyEntity)this.entity).isDifficultyStandard() ? 0.7F : 0.5F));
/* 36 */     if (((BuggyEntity)this.entity).isNormalPhaseActive() && (carSwitchHPThreshold || carSwitchKitingThreshold)) {
/* 37 */       this.startCarPhase = true;
/* 38 */       return true;
/*    */     } 
/* 40 */     if (((BuggyEntity)this.entity).isCarPhaseActive() && hasCarPhaseTimePassed()) {
/* 41 */       this.startNormalPhase = true;
/* 42 */       return true;
/*    */     } 
/*    */     
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   private boolean hasCarPhaseTimePassed() {
/* 49 */     return (((BuggyEntity)this.entity).field_70170_p.func_82737_E() >= this.carPhaseStartTick + this.carPhaseDuration);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 59 */     super.func_75249_e();
/*    */     
/* 61 */     if (this.startCarPhase) {
/* 62 */       ((BuggyEntity)this.entity).startCarPhase();
/* 63 */       this.carPhaseStartTick = ((BuggyEntity)this.entity).field_70170_p.func_82737_E();
/*    */     }
/* 65 */     else if (this.startNormalPhase) {
/* 66 */       ((BuggyEntity)this.entity).startNormalPhase();
/*    */     } 
/*    */     
/* 69 */     this.startNormalPhase = false;
/* 70 */     this.startCarPhase = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 75 */     super.func_75246_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 80 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\buggy\BuggyPhaseSwitcherGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */