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
/*     */ public class RunAwayFromTargetGoal<E extends CreatureEntity>
/*     */   extends TickedGoal<E> {
/*     */   private LivingEntity target;
/*     */   private double speed;
/*     */   private int ticksRunning;
/*  23 */   private int minDistance = 20; private final int defaultRunningTicks; private final int restBetweenRuns; private int restTicks;
/*  24 */   private int maxDistance = 30;
/*     */   
/*     */   public RunAwayFromTargetGoal(E entity, double speed, int ticksRunning, int restBetweenRuns) {
/*  27 */     super((MobEntity)entity);
/*  28 */     this.speed = speed;
/*  29 */     this.ticksRunning = ticksRunning;
/*  30 */     this.defaultRunningTicks = ticksRunning;
/*  31 */     this.restBetweenRuns = restBetweenRuns;
/*  32 */     func_220684_a(EnumSet.of(Goal.Flag.MOVE));
/*     */   }
/*     */   
/*     */   public RunAwayFromTargetGoal setRunningDistance(int min, int max) {
/*  36 */     this.minDistance = min;
/*  37 */     this.maxDistance = max;
/*  38 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  43 */     if (this.restTicks > 0) {
/*  44 */       this.restTicks--;
/*  45 */       return false;
/*     */     } 
/*     */     
/*  48 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     this.target = ((CreatureEntity)this.entity).func_70638_az();
/*     */     
/*  54 */     if (GoalUtil.isOutsideDistance((LivingEntity)this.entity, this.target, 20.0D)) {
/*  55 */       return false;
/*     */     }
/*     */     
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  63 */     if (!GoalUtil.hasAliveTarget(this.entity)) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     if (this.ticksRunning <= 0) {
/*  68 */       this.ticksRunning = this.defaultRunningTicks;
/*  69 */       return false;
/*     */     } 
/*     */     
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  77 */     super.func_75249_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  82 */     super.func_75246_d();
/*     */     
/*  84 */     this.ticksRunning--;
/*  85 */     BlockPos pathTarget = ((CreatureEntity)this.entity).func_70661_as().func_208485_j();
/*     */     
/*  87 */     boolean isNearTarget = (pathTarget != null && ((CreatureEntity)this.entity).func_70092_e(pathTarget.func_177958_n(), pathTarget.func_177956_o(), pathTarget.func_177952_p()) < 25.0D);
/*  88 */     if (((CreatureEntity)this.entity).func_70661_as().func_75500_f() || isNearTarget) {
/*  89 */       Path path = findRunPosition();
/*  90 */       if (path != null) {
/*  91 */         ((CreatureEntity)this.entity).func_70661_as().func_75484_a(path, this.speed);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Path findRunPosition() {
/*  98 */     int run = 0;
/*  99 */     while (run < 20) {
/* 100 */       BlockPos pos = WyHelper.findValidGroundLocation((Entity)this.entity, ((CreatureEntity)this.entity).func_233580_cy_(), this.maxDistance, this.minDistance);
/* 101 */       boolean isWithinBounds = (!WyHelper.isInChallengeDimension(((CreatureEntity)this.entity).field_70170_p) || (WyHelper.isInChallengeDimension(((CreatureEntity)this.entity).field_70170_p) && AbilityHelper.isWithinChallengeArenaBounds(((CreatureEntity)this.entity).field_70170_p, pos)));
/* 102 */       if (isWithinBounds && pos.func_218141_a((Vector3i)this.target.func_233580_cy_(), this.minDistance)) {
/* 103 */         Path path = ((CreatureEntity)this.entity).func_70661_as().func_179680_a(pos, 1);
/* 104 */         if (path != null && path.func_224771_h()) {
/* 105 */           return path;
/*     */         }
/*     */       } 
/* 108 */       run++;
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 115 */     super.func_75251_c();
/* 116 */     this.restTicks = this.restBetweenRuns;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\RunAwayFromTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */