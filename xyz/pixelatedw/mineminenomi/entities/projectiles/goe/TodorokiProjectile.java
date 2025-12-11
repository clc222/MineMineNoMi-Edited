/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
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
/*    */ public class TodorokiProjectile extends AbilityProjectileEntity {
/*    */   public TodorokiProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TodorokiProjectile(World world, LivingEntity player, Ability ability) {
/* 22 */     super((EntityType)GoeProjectiles.TODOROKI.get(), world, player, ability);
/*    */     
/* 24 */     setDamage(15.0F);
/* 25 */     setMaxLife(15);
/* 26 */     setArmorPiercing(1.0F);
/* 27 */     setPassThroughEntities();
/* 28 */     setCanGetStuckInGround();
/* 29 */     setEntityCollisionSize(2.5D);
/*    */     
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 37 */     explosion.setStaticDamage(15.0F);
/* 38 */     explosion.setExplosionSound(true);
/* 39 */     explosion.setDamageOwner(false);
/* 40 */     explosion.setDestroyBlocks(true);
/* 41 */     explosion.setFireAfterExplosion(false);
/* 42 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 43 */     explosion.setDamageEntities(true);
/* 44 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goe\TodorokiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */