/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityGoal2;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DodgeGoal extends AbilityGoal2<CreatureEntity> {
/*     */   private int usage;
/*  19 */   private int usagePower = 10;
/*  20 */   private int restoreTime = 40;
/*  21 */   private int restorePower = 10;
/*  22 */   private int successRate = 50;
/*  23 */   private Vector3d dodgeDistance = new Vector3d(1.25D, 0.0D, 1.25D);
/*  24 */   private double minDistance = -1.0D;
/*     */   
/*  26 */   private Interval dodgeInterval = new Interval(5);
/*     */   
/*     */   private Interval restorenterval;
/*     */   
/*     */   private Predicate<Entity> isNotOwnThrowable;
/*     */   
/*     */   public DodgeGoal(CreatureEntity entity) {
/*  33 */     super((MobEntity)entity);
/*  34 */     setDefaultCooldown(10.0F);
/*  35 */     this.restorenterval = new Interval(this.restoreTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DodgeGoal setMinDistance(double dist) {
/*  42 */     this.minDistance = dist;
/*  43 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DodgeGoal setDodgeDistance(double x, double y, double z) {
/*  48 */     this.dodgeDistance = new Vector3d(x, y, z);
/*  49 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DodgeGoal setSuccessRate(int rate) {
/*  54 */     this.successRate = rate;
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DodgeGoal setUsagePower(int power) {
/*  60 */     this.usagePower = power;
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DodgeGoal setRestoreTime(int time) {
/*  66 */     this.restoreTime = time;
/*  67 */     this.restorenterval = new Interval(this.restoreTime);
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public DodgeGoal setRestorePower(int power) {
/*  73 */     this.restorePower = power;
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  80 */     if (!super.func_75250_a()) {
/*  81 */       return false;
/*     */     }
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  89 */     return (this.usage < 100);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  95 */     super.func_75249_e();
/*  96 */     this.isNotOwnThrowable = (e -> (e instanceof ThrowableEntity && ((ThrowableEntity)e).func_234616_v_() != this.entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 102 */     super.func_75246_d();
/*     */     
/* 104 */     if (this.dodgeInterval.canTick()) {
/*     */       
/* 106 */       int distance = 10;
/* 107 */       List<ThrowableEntity> dangers = WyHelper.getNearbyEntities(((CreatureEntity)this.entity).func_213303_ch(), (IWorld)((CreatureEntity)this.entity).field_70170_p, distance, distance, distance, this.isNotOwnThrowable, new Class[] { ThrowableEntity.class });
/*     */       
/* 109 */       if (dangers.size() > 0) {
/*     */         
/* 111 */         int dodges = 0;
/* 112 */         Vector3d entityPos = ((CreatureEntity)this.entity).func_213303_ch();
/* 113 */         for (ThrowableEntity danger : dangers) {
/*     */           
/* 115 */           if (this.minDistance > 0.0D && danger.func_234616_v_() != null && danger.func_234616_v_().func_70068_e((Entity)this.entity) < this.minDistance * this.minDistance) {
/*     */             continue;
/*     */           }
/*     */           
/* 119 */           if (((CreatureEntity)this.entity).func_70681_au().nextInt(100) > this.successRate) {
/*     */             
/* 121 */             dodges++;
/*     */             continue;
/*     */           } 
/* 124 */           Vector3d pos = danger.func_213303_ch();
/* 125 */           Vector3d dir = entityPos.func_178788_d(pos).func_72432_b().func_216369_h(this.dodgeDistance);
/* 126 */           AbilityHelper.setDeltaMovement((Entity)this.entity, dir.field_72450_a, (((CreatureEntity)this.entity).func_213322_ci()).field_72448_b, dir.field_72449_c);
/* 127 */           dodges++;
/*     */         } 
/* 129 */         this.usage += this.usagePower * dodges;
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     if (this.restorenterval.canTick()) {
/*     */       
/* 135 */       this.usage -= this.restorePower;
/* 136 */       this.usage = MathHelper.func_76125_a(this.usage, 0, 100);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 143 */     super.func_75251_c();
/* 144 */     this.usage = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\DodgeGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */