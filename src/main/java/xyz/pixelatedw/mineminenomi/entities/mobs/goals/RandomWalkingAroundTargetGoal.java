/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RandomWalkingAroundTargetGoal
/*     */   extends TickedGoal<CreatureEntity>
/*     */ {
/*     */   private LivingEntity target;
/*     */   private BlockPos targetPos;
/*     */   private double speed;
/*  22 */   private int minDistance = 20; private int ticksRunning; private final int defaultRunningTicks; private Interval restBetweenRuns;
/*  23 */   private int maxDistance = 30;
/*     */   
/*     */   public RandomWalkingAroundTargetGoal(CreatureEntity entity, double speed, int ticksRunning, int restBetweenRuns) {
/*  26 */     super((MobEntity)entity);
/*  27 */     this.speed = speed;
/*  28 */     this.ticksRunning = ticksRunning;
/*  29 */     this.defaultRunningTicks = ticksRunning;
/*  30 */     this.restBetweenRuns = Interval.startAtZero(restBetweenRuns);
/*  31 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*     */   }
/*     */   
/*     */   public RandomWalkingAroundTargetGoal setRunningDistance(int min, int max) {
/*  35 */     this.minDistance = min;
/*  36 */     this.maxDistance = max;
/*  37 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  42 */     if (!this.restBetweenRuns.canTick()) {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  47 */       return false;
/*     */     }
/*     */     
/*  50 */     this.target = ((CreatureEntity)this.entity).func_70638_az();
/*     */     
/*  52 */     if (GoalUtil.isWithinDistance((LivingEntity)this.entity, this.target, 10.0D)) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  61 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  62 */       return false;
/*     */     }
/*     */     
/*  65 */     if (this.ticksRunning <= 0) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  74 */     super.func_75249_e();
/*  75 */     this.targetPos = WyHelper.findValidGroundLocation((Entity)this.entity, this.target.func_233580_cy_(), this.maxDistance, this.minDistance);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  80 */     super.func_75246_d();
/*     */     
/*  82 */     this.ticksRunning--;
/*  83 */     if (this.ticksRunning % 20 == 0) {
/*  84 */       findRunPosition(0);
/*     */     }
/*     */     
/*  87 */     if (((CreatureEntity)this.entity).func_70661_as().func_75500_f()) {
/*  88 */       findRunPosition(0);
/*     */     }
/*     */     
/*  91 */     ((CreatureEntity)this.entity).func_70661_as().func_75492_a(this.targetPos.func_177958_n(), this.targetPos.func_177956_o(), this.targetPos.func_177952_p(), this.speed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  96 */     super.func_75251_c();
/*  97 */     this.ticksRunning = this.defaultRunningTicks;
/*     */   }
/*     */   
/*     */   private void findRunPosition(int run) {
/* 101 */     if (run == 0);
/*     */ 
/*     */     
/* 104 */     BlockPos pos = WyHelper.findValidGroundLocation((Entity)this.entity, this.target.func_233580_cy_(), this.maxDistance, this.minDistance);
/* 105 */     if (run < 5 && !pos.func_218141_a((Vector3i)this.target.func_233580_cy_(), this.maxDistance)) {
/* 106 */       findRunPosition(run++);
/*     */       return;
/*     */     } 
/* 109 */     this.targetPos = pos;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RandomWalkingAroundTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */