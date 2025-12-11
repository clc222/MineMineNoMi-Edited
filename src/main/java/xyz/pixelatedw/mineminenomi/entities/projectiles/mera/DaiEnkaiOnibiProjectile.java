/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
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
/*    */ public class DaiEnkaiOnibiProjectile extends AbilityProjectileEntity {
/*    */   public DaiEnkaiOnibiProjectile(EntityType<Entity> type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DaiEnkaiOnibiProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)MeraProjectiles.DAI_ENKAI_ONIBI.get(), world, player, ability.getCore());
/*    */     
/* 22 */     setMaxLife(60);
/* 23 */     setDamage(75.0F);
/* 24 */     setArmorPiercing(0.75F);
/* 25 */     setUnavoidable();
/* 26 */     setPassThroughEntities();
/* 27 */     setNoVelocityRotation();
/* 28 */     setBlockCollisionSize(3.5D);
/* 29 */     setHurtTime(0);
/*    */     
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   public void onBlockImpactEvent(BlockPos hit) {
/* 36 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 10.0F);
/*    */     
/* 38 */     explosion.setStaticDamage(22.5F);
/* 39 */     explosion.setStaticBlockResistance(0.25F);
/* 40 */     explosion.setFireAfterExplosion(true);
/* 41 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(10));
/* 42 */     explosion.doExplosion();
/*    */   }
/*    */   
/*    */   private void onTickEvent() {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\DaiEnkaiOnibiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */