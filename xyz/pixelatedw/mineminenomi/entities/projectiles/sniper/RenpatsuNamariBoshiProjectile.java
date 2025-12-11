/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
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
/*    */ public class RenpatsuNamariBoshiProjectile extends AbilityProjectileEntity {
/*    */   public RenpatsuNamariBoshiProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RenpatsuNamariBoshiProjectile(World world, LivingEntity player, Ability ability) {
/* 22 */     super((EntityType)SniperProjectiles.RENPATSU_NAMARI_BOSHI.get(), world, player, ability);
/*    */     
/* 24 */     setDamage(15.0F);
/*    */     
/* 26 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 31 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 32 */     explosion.setStaticDamage(12.0F);
/* 33 */     explosion.setExplosionSound(true);
/* 34 */     explosion.setDamageOwner(false);
/* 35 */     explosion.setDestroyBlocks(true);
/* 36 */     explosion.setFireAfterExplosion(false);
/* 37 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 38 */     explosion.setDamageEntities(true);
/* 39 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\RenpatsuNamariBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */