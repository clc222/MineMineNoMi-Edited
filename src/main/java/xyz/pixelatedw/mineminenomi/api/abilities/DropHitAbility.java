/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ 
/*     */ public abstract class DropHitAbility
/*     */   extends Ability {
/*  14 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true))
/*  15 */     .addStartEvent(200, this::startContinuityEvent)
/*  16 */     .addTickEvent(200, this::tickContinuityEvent)
/*  17 */     .addEndEvent(200, this::endContinuityEvent);
/*  18 */   protected final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent(this)).addOnAttackEvent(this::onDamageTaken);
/*  19 */   protected final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent(this);
/*     */   
/*     */   private boolean hasLanded = true;
/*     */   private boolean hasFallDamage = true;
/*     */   private boolean startsInLiquid = false;
/*     */   
/*     */   public DropHitAbility(AbilityCore<? extends IAbility> core) {
/*  26 */     super(core);
/*     */     
/*  28 */     this.isNew = true;
/*  29 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.hitTrackerComponent });
/*     */     
/*  31 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  32 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  36 */     this.continuousComponent.triggerContinuity(entity, getContinueTime(entity));
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  40 */     this.hasLanded = false;
/*  41 */     this.hasFallDamage = false;
/*  42 */     this.startsInLiquid = entity.func_70090_H();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  48 */     if (!entity.field_70170_p.field_72995_K && !this.hasFallDamage && entity.func_233570_aj_() && entity.field_70170_p.func_82737_E() > getLastUseGametime() + 10L) {
/*  49 */       this.hasFallDamage = true;
/*     */     }
/*     */     
/*  52 */     if (!this.startsInLiquid && entity.func_70090_H() && !this.hasLanded) {
/*  53 */       this.hasLanded = true;
/*     */     }
/*     */     
/*  56 */     if (this.startsInLiquid && entity.func_70090_H() && (entity.func_213322_ci()).field_72448_b <= 0.0D) {
/*  57 */       this.hasLanded = true;
/*     */     }
/*     */     
/*  60 */     if (entity.func_233570_aj_() && this.continuousComponent.getContinueTime() > 10.0F && !this.hasLanded) {
/*  61 */       this.hasLanded = true;
/*     */     }
/*     */     
/*  64 */     if (this.hasLanded) {
/*  65 */       onLanding(entity);
/*  66 */       this.continuousComponent.stopContinuity(entity);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void onLanding(LivingEntity paramLivingEntity);
/*     */ 
/*     */   
/*     */   public float getContinueTime(LivingEntity entity) {
/*  81 */     return -1.0F;
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  85 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/*  86 */       this.hasFallDamage = true;
/*  87 */       return 0.0F;
/*     */     } 
/*     */     
/*  90 */     return damage;
/*     */   }
/*     */   
/*     */   public boolean hasLanded() {
/*  94 */     return this.hasLanded;
/*     */   }
/*     */   
/*     */   public void setLanded(boolean flag) {
/*  98 */     this.hasLanded = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 103 */     nbt = super.save(nbt);
/* 104 */     nbt.func_74757_a("hasFallDamage", this.hasFallDamage);
/* 105 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 110 */     super.load(nbt);
/* 111 */     this.hasFallDamage = nbt.func_74767_n("hasFallDamage");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\DropHitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */