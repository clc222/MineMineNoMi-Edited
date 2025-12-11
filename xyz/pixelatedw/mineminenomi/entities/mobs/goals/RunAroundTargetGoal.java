/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RunAroundTargetGoal<E extends CreatureEntity>
/*     */   extends TickedGoal<E> {
/*     */   private LivingEntity target;
/*     */   private double speed;
/*     */   private int ticksRunning;
/*  23 */   private int minDistance = 10; private final int defaultRunningTicks; private final int restBetweenRuns; private int restTicks;
/*  24 */   private int maxDistance = 30;
/*  25 */   private int uses = 0;
/*     */   
/*     */   public RunAroundTargetGoal(E entity, double speed, int ticksRunning, int restBetweenRuns) {
/*  28 */     super((MobEntity)entity);
/*  29 */     this.speed = speed;
/*  30 */     this.ticksRunning = ticksRunning;
/*  31 */     this.defaultRunningTicks = ticksRunning;
/*  32 */     this.restBetweenRuns = restBetweenRuns;
/*  33 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*     */   }
/*     */   
/*     */   public RunAroundTargetGoal setRunningDistance(int min, int max) {
/*  37 */     this.minDistance = min;
/*  38 */     this.maxDistance = max;
/*  39 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  44 */     if (this.restTicks > 0) {
/*  45 */       this.restTicks--;
/*  46 */       return false;
/*     */     } 
/*     */     
/*  49 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     this.target = ((CreatureEntity)this.entity).func_70638_az();
/*     */     
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  60 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  61 */       return false;
/*     */     }
/*     */     
/*  64 */     if (this.ticksRunning <= 0) {
/*  65 */       return false;
/*     */     }
/*     */     
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  73 */     super.func_75249_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  78 */     super.func_75246_d();
/*  79 */     this.ticksRunning--;
/*  80 */     BlockPos pathTarget = ((CreatureEntity)this.entity).func_70661_as().func_208485_j();
/*     */     
/*  82 */     boolean isNearTarget = (pathTarget != null && ((CreatureEntity)this.entity).func_70092_e(pathTarget.func_177958_n(), pathTarget.func_177956_o(), pathTarget.func_177952_p()) < 25.0D);
/*  83 */     if (((CreatureEntity)this.entity).func_70661_as().func_75500_f() || isNearTarget) {
/*  84 */       Path path = findRunPosition();
/*  85 */       if (path != null) {
/*  86 */         ((CreatureEntity)this.entity).func_70661_as().func_75484_a(path, this.speed);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Path findRunPosition() {
/*  93 */     int run = 0;
/*  94 */     while (run < 20) {
/*  95 */       BlockPos pos = WyHelper.findValidGroundLocation((Entity)this.target, this.target.func_233580_cy_(), this.maxDistance, this.minDistance);
/*  96 */       boolean isWithinBounds = (WyHelper.isInChallengeDimension(((CreatureEntity)this.entity).field_70170_p) && AbilityHelper.isWithinChallengeArenaBounds(((CreatureEntity)this.entity).field_70170_p, pos));
/*  97 */       if (isWithinBounds && !pos.func_218141_a((Vector3i)this.target.func_233580_cy_(), this.minDistance)) {
/*  98 */         Path path = ((CreatureEntity)this.entity).func_70661_as().func_179680_a(pos, 1);
/*  99 */         if (path != null && path.func_224771_h()) {
/* 100 */           return path;
/*     */         }
/*     */       } 
/* 103 */       run++;
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 110 */     super.func_75251_c();
/* 111 */     this.ticksRunning = this.defaultRunningTicks;
/* 112 */     this.restTicks = this.restBetweenRuns;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RunAroundTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */