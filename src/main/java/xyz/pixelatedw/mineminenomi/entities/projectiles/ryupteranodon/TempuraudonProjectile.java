/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class TempuraudonProjectile extends AbilityProjectileEntity {
/*    */   public TempuraudonProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */   
/*    */   public TempuraudonProjectile(World world, LivingEntity player, Ability ability) {
/* 21 */     super((EntityType)RyuPteranodonProjectiles.TEMPURAUDON.get(), world, player, ability.getCore());
/*    */     
/* 23 */     setDamage(50.0F);
/* 24 */     setPassThroughEntities();
/* 25 */     setMaxLife(32);
/* 26 */     setArmorPiercing(1.0F);
/*    */     
/* 28 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 33 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 6.0F);
/* 34 */     explosion.setStaticDamage(15.0F);
/* 35 */     explosion.disableExplosionKnockback();
/* 36 */     explosion.setDestroyBlocks(true);
/* 37 */     explosion.setFireAfterExplosion(false);
/* 38 */     explosion.setExplosionSound(false);
/* 39 */     explosion.setDamageEntities(false);
/* 40 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 41 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ryupteranodon\TempuraudonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */