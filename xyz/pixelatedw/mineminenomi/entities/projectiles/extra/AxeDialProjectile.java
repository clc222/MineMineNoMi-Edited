/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class AxeDialProjectile extends AbilityProjectileEntity {
/*    */   public AxeDialProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxeDialProjectile(World world, LivingEntity player) {
/* 21 */     super((EntityType)ExtraProjectiles.AXE_DIAL_PROJECTILE.get(), world, player);
/*    */     
/* 23 */     setDamage(20.0F);
/* 24 */     setMaxLife(20);
/* 25 */     setPhysical();
/*    */     
/* 27 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 32 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 4.0F);
/* 33 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 34 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\AxeDialProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */