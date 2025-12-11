/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ 
/*     */ 
/*     */ public class PersonalSpaceTargetGoal
/*     */   extends TickedGoal<MobEntity>
/*     */ {
/*     */   private static final int TICKS_BEFORE_MARK_UPDATE = 200;
/*  18 */   private HashMap<Integer, Long> personalSpaceTargets = new HashMap<>();
/*  19 */   private Interval checkInterval = new Interval(20);
/*     */   private final int ticksBeforeAttacking;
/*     */   private final double checkDistance;
/*     */   private final Predicate<LivingEntity> targetCheck;
/*     */   
/*     */   public PersonalSpaceTargetGoal(MobEntity entity) {
/*  25 */     this(entity, 10.0D, 200);
/*     */   }
/*     */   
/*     */   public PersonalSpaceTargetGoal(MobEntity entity, double checkDistance) {
/*  29 */     this(entity, checkDistance, 200);
/*     */   }
/*     */   
/*     */   public PersonalSpaceTargetGoal(MobEntity entity, double checkDistance, int ticksBeforeAttacking) {
/*  33 */     super(entity);
/*  34 */     this.checkDistance = checkDistance;
/*  35 */     this.ticksBeforeAttacking = ticksBeforeAttacking;
/*  36 */     this.targetCheck = getDefaultTargetCheck((LivingEntity)entity);
/*     */   }
/*     */   
/*     */   public PersonalSpaceTargetGoal(MobEntity entity, double checkDistance, int ticksBeforeAttacking, Predicate<LivingEntity> targetCheck) {
/*  40 */     super(entity);
/*  41 */     this.checkDistance = checkDistance;
/*  42 */     this.ticksBeforeAttacking = ticksBeforeAttacking;
/*  43 */     this.targetCheck = targetCheck;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  48 */     if (!this.entity.func_70089_S()) {
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     if (!hasTimePassedSinceLastEnd(60.0F)) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  65 */     if (!this.entity.func_70089_S()) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  70 */       return false;
/*     */     }
/*     */     
/*  73 */     if (GoalUtil.getNearbyVisibleEntities((LivingEntity)this.entity, this.checkDistance, null).size() <= 0) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  82 */     super.func_75249_e();
/*  83 */     this.checkInterval.restartIntervalToMax();
/*  84 */     this.personalSpaceTargets.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  89 */     super.func_75246_d();
/*     */     
/*  91 */     if (this.checkInterval.canTick()) {
/*  92 */       List<LivingEntity> targets = TargetHelper.getEntitiesInArea((LivingEntity)this.entity, this.checkDistance, null, new Class[] { LivingEntity.class });
/*     */       
/*  94 */       if (targets.size() > 0) {
/*  95 */         for (LivingEntity target : targets) {
/*  96 */           if (!this.targetCheck.test(target)) {
/*     */             continue;
/*     */           }
/*     */           
/* 100 */           if (this.personalSpaceTargets.containsKey(Integer.valueOf(target.func_145782_y()))) {
/* 101 */             long time = ((Long)this.personalSpaceTargets.get(Integer.valueOf(target.func_145782_y()))).longValue();
/* 102 */             if (this.entity.field_70170_p.func_82737_E() >= time + this.ticksBeforeAttacking) {
/* 103 */               this.entity.func_70624_b(target);
/*     */             }
/*     */             
/*     */             continue;
/*     */           } 
/* 108 */           this.personalSpaceTargets.put(Integer.valueOf(target.func_145782_y()), Long.valueOf(this.entity.field_70170_p.func_82737_E()));
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 117 */     super.func_75251_c();
/*     */   }
/*     */   
/*     */   public Predicate<LivingEntity> getDefaultTargetCheck(LivingEntity entity) {
/* 121 */     return target -> (target instanceof net.minecraft.entity.player.PlayerEntity) ? true : (!target.func_200600_R().equals(entity.func_200600_R()));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\PersonalSpaceTargetGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */