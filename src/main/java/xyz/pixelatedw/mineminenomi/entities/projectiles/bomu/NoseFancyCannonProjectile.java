/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bomu.NoseFancyCannonAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class NoseFancyCannonProjectile extends AbilityProjectileEntity {
/*    */   public NoseFancyCannonProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoseFancyCannonProjectile(World world, LivingEntity player, NoseFancyCannonAbility ability) {
/* 22 */     super((EntityType)BomuProjectiles.NOSE_FANCY_CANNON.get(), world, player, (Ability)ability);
/*    */     
/* 24 */     setDamage(10.0F);
/* 25 */     setMaxLife(32);
/*    */     
/* 27 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 32 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 33 */     explosion.setStaticDamage(15.0F);
/* 34 */     explosion.setExplosionSound(true);
/* 35 */     explosion.setDamageOwner(false);
/* 36 */     explosion.setDestroyBlocks(true);
/* 37 */     explosion.setFireAfterExplosion(false);
/* 38 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 39 */     explosion.setDamageEntities(true);
/* 40 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bomu\NoseFancyCannonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */