/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class CooldownGoal<T extends OPEntity> extends AbilityGoal<T> {
/*     */   private boolean isOnCooldown = false;
/*     */   protected double maxCooldown;
/*   9 */   protected double cooldown = 80.0D;
/*  10 */   protected int randomizer = 1;
/*     */   
/*     */   private int ticksInUse;
/*     */   
/*     */   public CooldownGoal(T entity) {
/*  15 */     super(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  21 */     if (!super.func_75250_a()) {
/*  22 */       return false;
/*     */     }
/*     */     
/*  25 */     if (isOnCooldown()) {
/*  26 */       return cooldownTick();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  45 */     this.ticksInUse++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  51 */     this.ticksInUse = 0;
/*  52 */     setOnCooldown(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public <G extends CooldownGoal> G setMaxCooldown(double cooldown) {
/*  57 */     this.cooldown = cooldown * 20.0D;
/*  58 */     this.maxCooldown = this.cooldown;
/*  59 */     this.randomizer = (int)Math.max(1.0D, this.maxCooldown / 10.0D);
/*  60 */     return (G)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksInUse() {
/*  65 */     return this.ticksInUse;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMaxCooldown() {
/*  71 */     return this.maxCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOnCooldown(boolean value) {
/*  76 */     this.isOnCooldown = value;
/*  77 */     if (value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnCooldown() {
/*  86 */     return this.isOnCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cooldownTick() {
/*  91 */     if (this.isOnCooldown) {
/*     */       
/*  93 */       this.cooldown--;
/*  94 */       duringCooldown();
/*  95 */       if (this.cooldown <= 0.0D) {
/*     */         
/*  97 */         endCooldown();
/*  98 */         this.isOnCooldown = false;
/*  99 */         this.cooldown = this.maxCooldown + this.entity.func_70681_au().nextInt(this.randomizer);
/* 100 */         return true;
/*     */       } 
/*     */       
/* 103 */       return false;
/*     */     } 
/*     */     
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public void duringCooldown() {}
/*     */   
/*     */   public void endCooldown() {}
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\CooldownGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */