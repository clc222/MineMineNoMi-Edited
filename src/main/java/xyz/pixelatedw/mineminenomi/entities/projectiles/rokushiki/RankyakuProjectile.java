/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class RankyakuProjectile extends AbilityProjectileEntity {
/*    */   public RankyakuProjectile(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */   
/*    */   public RankyakuProjectile(World world, LivingEntity entity) {
/* 18 */     super((EntityType)RokushikiProjectiles.RANKYAKU.get(), world, entity, RankyakuAbility.INSTANCE);
/*    */     
/* 20 */     setDamage(40.0F);
/* 21 */     setMaxLife(40);
/* 22 */     setBlocksAffectedLimit(256);
/* 23 */     setPassThroughEntities();
/*    */     
/* 25 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 29 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 30 */     explosion.setStaticDamage(15.0F);
/* 31 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 32 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\rokushiki\RankyakuProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */