/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PadHoProjectile extends AbilityProjectileEntity {
/*    */   public PadHoProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PadHoProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)NikyuProjectiles.PAD_HO.get(), world, player, ability);
/*    */     
/* 26 */     setDamage(15.0F);
/* 27 */     setArmorPiercing(1.0F);
/* 28 */     setPassThroughEntities();
/*    */     
/* 30 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 31 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 36 */     this.onBlockImpactEvent.onImpact(target.func_233580_cy_());
/*    */     
/* 38 */     if (getDamage() > 10.0F) {
/*    */       
/* 40 */       Vector3d speed = target.func_70040_Z().func_216372_d(-1.0D, -1.0D, -1.0D).func_216372_d(WyHelper.randomWithRange(4, 6), WyHelper.randomWithRange(1, 3), WyHelper.randomWithRange(4, 6));
/* 41 */       AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/* 42 */       target.field_70143_R = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 48 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), getDamage() / 5.0F);
/* 49 */     explosion.setStaticDamage(getDamage() / 3.0F);
/* 50 */     explosion.setExplosionSound(true);
/* 51 */     explosion.setDamageOwner(false);
/* 52 */     explosion.setDestroyBlocks(true);
/* 53 */     explosion.setFireAfterExplosion(false);
/* 54 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 55 */     explosion.setDamageEntities(false);
/* 56 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\PadHoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */