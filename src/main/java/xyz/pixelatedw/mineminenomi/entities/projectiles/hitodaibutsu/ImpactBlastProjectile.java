/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hitodaibutsu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ImpactBlastProjectile extends AbilityProjectileEntity {
/*    */   public ImpactBlastProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImpactBlastProjectile(World world, LivingEntity player, Ability ability) {
/* 22 */     super((EntityType)HitoDaibutsuProjectiles.IMPACT_BLAST.get(), world, player, ability);
/* 23 */     setDamage(60.0F);
/* 24 */     setMaxLife(20);
/* 25 */     setEntityCollisionSize(5.0D);
/* 26 */     setPassThroughBlocks();
/* 27 */     setPassThroughEntities();
/*    */     
/* 29 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 34 */     if (this.field_70173_aa > 0) {
/*    */       
/* 36 */       if (this.field_70173_aa % 2 == 0) {
/*    */         
/* 38 */         ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 5.0F);
/* 39 */         explosion.setHeightDifference(45);
/* 40 */         explosion.setStaticDamage(40.0F * (getLife() / getMaxLife()));
/* 41 */         explosion.doExplosion();
/*    */       } 
/*    */       
/* 44 */       if (this.field_70173_aa % 5 == 0)
/*    */       {
/* 46 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.IMPACT_WAVE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hitodaibutsu\ImpactBlastProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */