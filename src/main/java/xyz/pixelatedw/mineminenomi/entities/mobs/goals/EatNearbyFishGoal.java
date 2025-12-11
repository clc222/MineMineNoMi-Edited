/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.passive.fish.AbstractFishEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class EatNearbyFishGoal
/*     */   extends TickedGoal<MobEntity>
/*     */ {
/*     */   private static final int COOLDOWN = 40;
/*  23 */   private List<AbstractFishEntity> fishes = new ArrayList<>();
/*     */   private AbstractFishEntity target;
/*  25 */   private long lastCheck = 0L;
/*     */   private boolean justAte = false;
/*     */   
/*     */   public EatNearbyFishGoal(MobEntity entity) {
/*  29 */     super(entity);
/*  30 */     func_220684_a(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE, Goal.Flag.TARGET));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  35 */     if (GoalUtil.hasAliveTarget(this.entity)) {
/*  36 */       return false;
/*     */     }
/*     */     
/*  39 */     if (this.lastCheck == 0L) {
/*  40 */       this.lastCheck = this.entity.field_70170_p.func_82737_E();
/*  41 */       return false;
/*     */     } 
/*     */     
/*  44 */     if (!hasTimePassedSinceLastCheck(40.0F)) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     if (!hasTimePassedSinceLastEnd(40.0F)) {
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     this.fishes = WyHelper.getNearbyEntities(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, getFollowDistance(), null, new Class[] { AbstractFishEntity.class });
/*  53 */     if (this.fishes.size() <= 0) {
/*  54 */       this.lastCheck = this.entity.field_70170_p.func_82737_E();
/*  55 */       return false;
/*     */     } 
/*     */     
/*  58 */     this.target = this.fishes.get(0);
/*     */ 
/*     */     
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  66 */     if (this.target == null || !this.target.func_70089_S()) {
/*  67 */       return false;
/*     */     }
/*     */     
/*  70 */     if (this.justAte) {
/*  71 */       return false;
/*     */     }
/*     */     
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   protected double getFollowDistance() {
/*  78 */     return this.entity.func_233637_b_(Attributes.field_233819_b_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  83 */     super.func_75249_e();
/*     */     
/*  85 */     this.justAte = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  90 */     super.func_75246_d();
/*     */     
/*  92 */     if (this.entity.field_70173_aa % 20 == 0) {
/*  93 */       this.entity.func_70661_as().func_75497_a((Entity)this.target, 1.2D);
/*     */     }
/*     */     
/*  96 */     if (this.target != null && this.entity.func_70068_e((Entity)this.target) < 25.0D) {
/*     */       
/*  98 */       this.target.func_70106_y();
/*     */ 
/*     */       
/* 101 */       this.entity.field_70170_p.func_72960_a((Entity)this.target, (byte)3);
/*     */ 
/*     */       
/* 104 */       for (int i = 0; i < 20; i++) {
/* 105 */         double d0 = this.entity.func_70681_au().nextGaussian() * 0.02D;
/* 106 */         double d1 = this.entity.func_70681_au().nextGaussian() * 0.02D;
/* 107 */         double d2 = this.entity.func_70681_au().nextGaussian() * 0.02D;
/* 108 */         this.entity.field_70170_p.func_195594_a((IParticleData)ParticleTypes.field_197598_I, this.entity
/*     */             
/* 110 */             .func_226282_d_(1.0D), this.entity
/* 111 */             .func_226279_cv_(), this.entity
/* 112 */             .func_226287_g_(1.0D), d0, d1, d2);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 118 */       this.justAte = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 124 */     super.func_75251_c();
/*     */   }
/*     */   
/*     */   public boolean hasTimePassedSinceLastCheck(float ticks) {
/* 128 */     return ((float)this.entity.field_70170_p.func_82737_E() >= (float)this.lastCheck + ticks);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\EatNearbyFishGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */