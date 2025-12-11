/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ 
/*     */ public class ImprovedMeleeAttackGoal extends MeleeAttackGoal {
/*  18 */   private int attackInterval = 20; private boolean moveTowardsTarget = true;
/*     */   private int ticksUntilNextAttack;
/*     */   private boolean longMemory;
/*     */   private Supplier<Boolean> earlyStop = () -> Boolean.valueOf(false);
/*     */   
/*     */   public ImprovedMeleeAttackGoal(CreatureEntity entity, double speed, boolean useLongMemory) {
/*  24 */     super(entity, speed, useLongMemory);
/*  25 */     this.longMemory = useLongMemory;
/*     */   }
/*     */   
/*     */   public ImprovedMeleeAttackGoal setAttackInterval(int interval) {
/*  29 */     this.attackInterval = interval;
/*  30 */     return this;
/*     */   }
/*     */   
/*     */   public ImprovedMeleeAttackGoal setMoveTowardsTarget(boolean flag) {
/*  34 */     this.moveTowardsTarget = flag;
/*  35 */     return this;
/*     */   }
/*     */   
/*     */   public ImprovedMeleeAttackGoal setEarlyStop(Supplier<Boolean> earlyStop) {
/*  39 */     this.earlyStop = earlyStop;
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  45 */     if (!super.func_75250_a()) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     if (!GoalUtil.hasAliveTarget((MobEntity)this.field_75441_b)) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     LivingEntity target = this.field_75441_b.func_70638_az();
/*     */     
/*  55 */     if (AbilityHelper.isInCreativeOrSpectator(target)) {
/*  56 */       return false;
/*     */     }
/*     */     
/*  59 */     if (this.field_75441_b.func_213283_Z() == Pose.CROUCHING || this.field_75441_b.func_213283_Z() == Pose.DYING || this.field_75441_b.func_213283_Z() == Pose.SLEEPING) {
/*  60 */       return false;
/*     */     }
/*     */     
/*  63 */     if (((Boolean)this.earlyStop.get()).booleanValue()) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  72 */     LivingEntity target = this.field_75441_b.func_70638_az();
/*  73 */     if (target == null || !target.func_70089_S()) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     if (((Boolean)this.earlyStop.get()).booleanValue()) {
/*  78 */       return false;
/*     */     }
/*     */     
/*  81 */     if (AbilityHelper.isInCreativeOrSpectator(target)) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     if (target.func_82150_aj()) {
/*  86 */       return false;
/*     */     }
/*     */     
/*  89 */     if (this.field_75441_b.func_213283_Z() == Pose.CROUCHING || this.field_75441_b.func_213283_Z() == Pose.DYING || this.field_75441_b.func_213283_Z() == Pose.SLEEPING) {
/*  90 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (!this.longMemory && this.field_75441_b.func_70661_as().func_75500_f()) {
/*  98 */       return false;
/*     */     }
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/* 106 */     super.func_75249_e();
/* 107 */     this.ticksUntilNextAttack = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 112 */     LivingEntity target = this.field_75441_b.func_70638_az();
/* 113 */     boolean isInvisible = (target != null && target.func_70644_a(Effects.field_76441_p));
/*     */     
/* 115 */     if (target == null || !target.func_70089_S()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 120 */     boolean lastAttackIsStale = (!AbilityHelper.isInCreativeOrSpectator(target) && this.field_75441_b.func_70068_e((Entity)target) < 5.0D && this.field_75441_b.field_70173_aa >= this.field_75441_b.func_142013_aG() + 100);
/* 121 */     if (lastAttackIsStale && target.func_233570_aj_() && this.field_75441_b.func_233570_aj_()) {
/* 122 */       Vector3d look = this.field_75441_b.func_70040_Z().func_216372_d(0.4D, 0.4D, 0.4D);
/* 123 */       AbilityHelper.setDeltaMovement((Entity)this.field_75441_b, this.field_75441_b.func_213322_ci().func_178787_e(look));
/*     */     } 
/*     */     
/* 126 */     if (isInvisible) {
/* 127 */       this.field_75441_b.func_70624_b((LivingEntity)null);
/*     */       
/*     */       return;
/*     */     } 
/* 131 */     super.func_75246_d();
/* 132 */     if (!this.moveTowardsTarget) {
/* 133 */       this.field_75441_b.func_70661_as().func_75499_g();
/*     */     }
/*     */ 
/*     */     
/* 137 */     this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_190102_a(LivingEntity enemy, double distToEnemySqr) {
/* 142 */     if (!GoalUtil.canSee((MobEntity)this.field_75441_b, enemy)) {
/*     */       return;
/*     */     }
/* 145 */     double d0 = func_179512_a(enemy);
/* 146 */     if (distToEnemySqr <= d0 && func_234040_h_()) {
/* 147 */       func_234039_g_();
/* 148 */       this.field_75441_b.func_184609_a(Hand.MAIN_HAND);
/* 149 */       this.field_75441_b.func_70652_k((Entity)enemy);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_234040_h_() {
/* 155 */     return (this.ticksUntilNextAttack <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_234039_g_() {
/* 160 */     super.func_234039_g_();
/* 161 */     this.ticksUntilNextAttack = func_234042_k_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_234042_k_() {
/* 166 */     return this.attackInterval;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double func_179512_a(LivingEntity target) {
/* 171 */     double attrReach = this.field_75441_b.func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111126_e();
/* 172 */     attrReach = Math.max(1.0D, attrReach);
/* 173 */     return (this.field_75441_b.func_213311_cf() * 2.0F * this.field_75441_b.func_213311_cf() * 2.0F + target.func_213311_cf()) * attrReach;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\ImprovedMeleeAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */