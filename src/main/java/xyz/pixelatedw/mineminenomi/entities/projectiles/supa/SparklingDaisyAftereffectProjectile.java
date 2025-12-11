/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.supa.SparklingDaisyAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class SparklingDaisyAftereffectProjectile extends AbilityProjectileEntity {
/*    */   public SparklingDaisyAftereffectProjectile(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */   
/*    */   public SparklingDaisyAftereffectProjectile(World world, LivingEntity player, SparklingDaisyAbility ability) {
/* 18 */     super((EntityType)SupaProjectiles.SPARKLING_DAISY_AFTER.get(), world, player, (Ability)ability);
/*    */     
/* 20 */     setDamage(10.0F);
/* 21 */     setMaxLife(20);
/* 22 */     setCanGetStuckInGround();
/* 23 */     setPassThroughEntities();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 28 */     super.func_70071_h_();
/* 29 */     if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 5) {
/* 30 */       ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 2.0F);
/* 31 */       explosion.setStaticDamage(5.0F);
/* 32 */       explosion.setHeightDifference(30);
/* 33 */       explosion.setFireAfterExplosion(false);
/* 34 */       explosion.setExplosionSound(false);
/* 35 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 36 */       explosion.doExplosion();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\supa\SparklingDaisyAftereffectProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */