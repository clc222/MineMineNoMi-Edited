/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IAnimatedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ChargingGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.PacifistaEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class PacifistaLaserGoal extends ChargingGoal<PacifistaEntity> implements IAnimatedGoal {
/*    */   public PacifistaLaserGoal(PacifistaEntity entity) {
/* 18 */     super((OPEntity)entity);
/* 19 */     setMaxCooldown(7.0D);
/* 20 */     setMaxCharge(3.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     if (((PacifistaEntity)this.entity).func_70638_az() == null) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (((PacifistaEntity)this.entity).getCurrentCommand() == NPCCommand.STAY) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     if (!((PacifistaEntity)this.entity).func_70685_l((Entity)((PacifistaEntity)this.entity).func_70638_az())) {
/* 33 */       return false;
/*    */     }
/* 35 */     if (((PacifistaEntity)this.entity).func_70032_d((Entity)((PacifistaEntity)this.entity).func_70638_az()) < 10.0F) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (!super.func_75250_a()) {
/* 39 */       return false;
/*    */     }
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 46 */     if (((PacifistaEntity)this.entity).func_70638_az() == null || !((PacifistaEntity)this.entity).func_70638_az().func_70089_S()) {
/* 47 */       return false;
/*    */     }
/* 49 */     return super.func_75253_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 54 */     super.func_75249_e();
/* 55 */     ((PacifistaEntity)this.entity).field_70170_p.func_217384_a(null, (Entity)this.entity, (SoundEvent)ModSounds.PIKA_CHARGE_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 56 */     startAnimation(this.entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 61 */     super.func_75246_d();
/* 62 */     ((PacifistaEntity)this.entity).func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 30, 0));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 67 */     super.func_75251_c();
/*    */     
/* 69 */     LivingEntity target = ((PacifistaEntity)this.entity).func_70638_az();
/* 70 */     if (target == null) {
/* 71 */       setOnCooldown(true);
/*    */       
/*    */       return;
/*    */     } 
/* 75 */     double d1 = target.func_226277_ct_() - ((PacifistaEntity)this.entity).func_226277_ct_();
/* 76 */     double d2 = (target.func_174813_aQ()).field_72338_b + (target.func_213302_cg() / 2.0F) - ((PacifistaEntity)this.entity).func_226278_cu_() + (((PacifistaEntity)this.entity).func_213302_cg() / 2.0F);
/* 77 */     double d3 = target.func_226281_cx_() - ((PacifistaEntity)this.entity).func_226281_cx_();
/*    */     
/* 79 */     ((PacifistaEntity)this.entity).field_70170_p.func_217384_a(null, (Entity)this.entity, (SoundEvent)ModSounds.CYBORG_BEAM_SFX.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 81 */     AmaterasuProjectile projectile = new AmaterasuProjectile(((PacifistaEntity)this.entity).field_70170_p, (LivingEntity)this.entity);
/* 82 */     projectile.setDamage(30.0F);
/* 83 */     projectile.func_70107_b(projectile.func_226277_ct_(), ((PacifistaEntity)this.entity).func_226278_cu_() + (((PacifistaEntity)this.entity).func_213302_cg() / 2.0F) + 0.5D, projectile.func_226281_cx_());
/* 84 */     projectile.func_70186_c(d1 + ((PacifistaEntity)this.entity).func_70681_au().nextGaussian(), d2, d3 + ((PacifistaEntity)this.entity).func_70681_au().nextGaussian(), 4.0F, 0.0F);
/* 85 */     ((PacifistaEntity)this.entity).field_70170_p.func_217376_c((Entity)projectile);
/* 86 */     stopAnimation(this.entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 91 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive(LivingEntity entity) {
/* 96 */     return isCharging();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\PacifistaLaserGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */