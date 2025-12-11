/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class CannonBallProjectile extends AbilityProjectileEntity {
/*    */   public CannonBallProjectile(EntityType<Entity> type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */   
/*    */   public CannonBallProjectile(World world, LivingEntity entity) {
/* 22 */     this(world, entity, (AbilityCore<? extends IAbility>)null);
/*    */   }
/*    */   
/*    */   public CannonBallProjectile(World world, LivingEntity entity, @Nullable AbilityCore<? extends IAbility> core) {
/* 26 */     super((EntityType)ExtraProjectiles.CANNON_BALL.get(), world, entity, core);
/*    */     
/* 28 */     setDamage(14.0F);
/* 29 */     setMaxLife(40);
/* 30 */     setGravity(0.01F);
/* 31 */     setPhysical();
/* 32 */     setAffectedByImbuing();
/* 33 */     setBlockCollisionSize(0.75D, 0.75D, 0.75D);
/* 34 */     setEntityCollisionSize(1.5D, 1.5D, 1.5D);
/*    */     
/* 36 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 40 */     if (this.field_70173_aa < 0) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 2.0F);
/*    */     
/* 46 */     explosion.setStaticDamage(8.0F);
/* 47 */     explosion.setDestroyBlocks(true);
/* 48 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 49 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\CannonBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */