/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class RadicalBeamProjectile extends AbilityProjectileEntity {
/*    */   public RadicalBeamProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RadicalBeamProjectile(World world, LivingEntity player) {
/* 22 */     super((EntityType)CyborgProjectiles.RADICAL_BEAM.get(), world, player, RadicalBeamAbility.INSTANCE);
/*    */     
/* 24 */     setDamage(50.0F);
/* 25 */     setArmorPiercing(1.0F);
/* 26 */     setCanGetStuckInGround();
/* 27 */     setPassThroughEntities();
/*    */     
/* 29 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 34 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 4.0F);
/* 35 */     explosion.setStaticDamage(25.0F);
/* 36 */     explosion.setFireAfterExplosion(true);
/* 37 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 38 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\RadicalBeamProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */