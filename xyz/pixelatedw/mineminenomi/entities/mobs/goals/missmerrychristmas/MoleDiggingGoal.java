/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.MissMerryChristmasEntity;
/*    */ 
/*    */ public class MoleDiggingGoal extends TickedGoal<MissMerryChristmasEntity> {
/*    */   private static final int COOLDOWN = 160;
/*    */   private static final float RANGE = 5.0F;
/*    */   private LivingEntity target;
/*    */   private int damageReceived;
/*    */   private boolean forceStop;
/*    */   private final int damageThreshold;
/*    */   
/*    */   public MoleDiggingGoal(MissMerryChristmasEntity entity) {
/* 21 */     super((MobEntity)entity);
/* 22 */     this.damageThreshold = entity.getChallengeInfo().isDifficultyStandard() ? 40 : 80;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 27 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     this.target = ((MissMerryChristmasEntity)this.entity).func_70638_az();
/*    */     
/* 33 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (((MissMerryChristmasEntity)this.entity).isUnderground()) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     if (!((MissMerryChristmasEntity)this.entity).func_233570_aj_()) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     if (!hasTimePassedSinceLastEnd(160.0F)) {
/* 46 */       return false;
/*    */     }
/*    */     
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 54 */     if (this.forceStop) {
/* 55 */       return false;
/*    */     }
/*    */     
/* 58 */     if (DevilFruitHelper.getDifferenceToFloor((Entity)this.entity) > 2.0D) {
/* 59 */       return false;
/*    */     }
/*    */     
/* 62 */     if (this.damageReceived >= this.damageThreshold) {
/* 63 */       return false;
/*    */     }
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 71 */     super.func_75249_e();
/*    */     
/* 73 */     this.forceStop = false;
/* 74 */     ((MissMerryChristmasEntity)this.entity).setUnderGround();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 79 */     super.func_75246_d();
/*    */     
/* 81 */     if (getTickCount() % 40L == 0L) {
/* 82 */       this.damageReceived--;
/* 83 */       this.damageReceived = MathHelper.func_76125_a(this.damageReceived, 0, this.damageThreshold);
/*    */     } 
/*    */     
/* 86 */     if (this.target.func_70089_S() && GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 5.0D)) {
/* 87 */       this.forceStop = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 93 */     super.func_75251_c();
/*    */     
/* 95 */     ((MissMerryChristmasEntity)this.entity).setAboveGround();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\missmerrychristmas\MoleDiggingGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */