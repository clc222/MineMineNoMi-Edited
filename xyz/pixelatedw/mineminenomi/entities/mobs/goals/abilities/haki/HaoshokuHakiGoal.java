/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
/*    */ 
/*    */ public class HaoshokuHakiGoal extends CooldownGoal {
/* 10 */   public static final ParticleEffect PARTICLES_1 = (ParticleEffect)new HaoshokuHakiParticleEffect();
/*    */   
/*    */   private float hakiXP;
/*    */   
/*    */   public HaoshokuHakiGoal(OPEntity entity, float hxp) {
/* 15 */     super(entity);
/* 16 */     setMaxCooldown(120.0D);
/* 17 */     this.hakiXP = hxp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endCooldown() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void duringCooldown() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 33 */     if (!super.func_75250_a()) {
/* 34 */       return false;
/*    */     }
/* 36 */     if (this.entity.func_70638_az() == null) {
/* 37 */       return false;
/*    */     }
/* 39 */     if (!this.entity.func_70685_l((Entity)this.entity.func_70638_az())) {
/* 40 */       return false;
/*    */     }
/* 42 */     if (this.entity.func_70032_d((Entity)this.entity.func_70638_az()) < 5.0F) {
/* 43 */       return false;
/*    */     }
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public void func_75249_e() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\haki\HaoshokuHakiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */