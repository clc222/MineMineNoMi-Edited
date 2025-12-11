/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IAnimatedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ChargingGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @Deprecated
/*     */ public class CleaveAttackGoal
/*     */   extends ChargingGoal
/*     */   implements IAnimatedGoal
/*     */ {
/*  25 */   private int bleedingPower = 1;
/*     */   
/*     */   private boolean canBreakBlocks;
/*     */   
/*     */   public CleaveAttackGoal(OPEntity entity) {
/*  30 */     super(entity);
/*  31 */     setMaxCooldown(1.0D);
/*  32 */     setMaxCharge(2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public CleaveAttackGoal setCanBreakBlocks() {
/*  37 */     this.canBreakBlocks = true;
/*  38 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CleaveAttackGoal setBleedingPower(int power) {
/*  43 */     this.bleedingPower = power;
/*  44 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  50 */     if (!super.func_75250_a()) {
/*  51 */       return false;
/*     */     }
/*  53 */     if (this.entity.func_70638_az() == null) {
/*  54 */       return false;
/*     */     }
/*  56 */     if (this.entity.func_70032_d((Entity)this.entity.func_70638_az()) > 3.0F || !this.entity.func_70685_l((Entity)this.entity.func_70638_az())) {
/*  57 */       return false;
/*     */     }
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  65 */     return (super.func_75253_b() && isCharging());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive(LivingEntity entity) {
/*  77 */     return isCharging();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  83 */     super.func_75251_c();
/*  84 */     startAnimation(this.entity);
/*  85 */     this.entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 100, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  91 */     super.func_75246_d();
/*  92 */     if (getCurrentCharge() > 30) {
/*     */       
/*  94 */       List<LivingEntity> targets = WyHelper.getNearbyLiving(this.entity.func_213303_ch(), (IWorld)this.entity.field_70170_p, 3.0D, null);
/*  95 */       targets.remove(this.entity);
/*     */       
/*  97 */       float damage = (float)this.entity.func_233645_dx_().func_233779_a_(Attributes.field_233823_f_).func_111126_e();
/*     */       
/*  99 */       for (LivingEntity target : targets) {
/*     */         
/* 101 */         target.func_70097_a(DamageSource.func_76358_a((LivingEntity)this.entity), damage);
/* 102 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.BLEEDING.get(), this.bleedingPower * 40, this.bleedingPower));
/*     */         
/* 104 */         if (this.canBreakBlocks) {
/*     */           
/* 106 */           ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this.entity, this.entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 1.5F);
/* 107 */           explosion.setDamageEntities(false);
/* 108 */           explosion.setExplosionSound(false);
/* 109 */           explosion.doExplosion();
/*     */         } 
/*     */       } 
/*     */       
/* 113 */       stopCharging();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 120 */     super.func_75251_c();
/* 121 */     stopAnimation(this.entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\CleaveAttackGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */