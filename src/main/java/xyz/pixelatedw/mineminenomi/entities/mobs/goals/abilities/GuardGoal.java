/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IAnimatedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class GuardGoal extends CooldownGoal implements IAnimatedGoal {
/*  15 */   private int maxDuration = 40;
/*     */   
/*     */   private int duration;
/*     */   private int maxCount;
/*     */   private int hitCount;
/*     */   private float prevHealth;
/*     */   
/*     */   public GuardGoal(OPEntity entity) {
/*  23 */     super(entity);
/*  24 */     setMaxCooldown(7.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends GuardGoal> T setHitCounter(int maxCount) {
/*  30 */     this.maxCount = maxCount;
/*  31 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends GuardGoal> T setDuration(int duration) {
/*  37 */     this.maxDuration = duration;
/*  38 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  44 */     if (!super.func_75250_a()) {
/*  45 */       return false;
/*     */     }
/*  47 */     LivingEntity target = this.entity.func_70638_az();
/*  48 */     if (target == null) {
/*  49 */       return false;
/*     */     }
/*  51 */     float distance = this.entity.func_70032_d((Entity)target);
/*  52 */     if (distance > 3.0F) {
/*  53 */       return false;
/*     */     }
/*  55 */     if (this.prevHealth == 0.0F) {
/*  56 */       this.prevHealth = this.entity.func_110143_aJ();
/*     */     }
/*  58 */     if (this.entity.func_110143_aJ() < this.prevHealth) {
/*     */       
/*  60 */       this.hitCount++;
/*  61 */       this.prevHealth = this.entity.func_110143_aJ();
/*     */     } 
/*     */     
/*  64 */     if (this.maxCount > 0)
/*     */     {
/*  66 */       if (this.hitCount < this.maxCount) {
/*  67 */         return false;
/*     */       }
/*     */     }
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  76 */     boolean isGoing = true;
/*  77 */     if (this.maxDuration > 0)
/*     */     {
/*  79 */       isGoing = (this.duration < this.maxDuration);
/*     */     }
/*  81 */     return (isGoing && !isOnCooldown());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  87 */     super.func_75249_e();
/*  88 */     startAnimation(this.entity);
/*     */     
/*  90 */     this.hitCount = 0;
/*  91 */     this.duration = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  97 */     super.func_75246_d();
/*  98 */     this.duration++;
/*     */     
/* 100 */     LivingEntity target = this.entity.func_70638_az();
/* 101 */     if (target == null) {
/*     */       
/* 103 */       setOnCooldown(true);
/*     */       
/*     */       return;
/*     */     } 
/* 107 */     if (this.prevHealth == 0.0F) {
/* 108 */       this.prevHealth = this.entity.func_110143_aJ();
/*     */     }
/* 110 */     if (this.entity.func_110143_aJ() < this.prevHealth) {
/*     */       
/* 112 */       this.hitCount++;
/* 113 */       this.prevHealth = this.entity.func_110143_aJ();
/*     */     } 
/*     */     
/* 116 */     this.entity.func_200602_a(EntityAnchorArgument.Type.EYES, target.func_213303_ch());
/* 117 */     this.entity.func_195064_c(new EffectInstance((Effect)ModEffects.GUARDING.get(), 10, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 123 */     super.func_75251_c();
/* 124 */     stopAnimation(this.entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHitCount() {
/* 129 */     return this.hitCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive(LivingEntity entity) {
/* 141 */     return func_75253_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\GuardGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */