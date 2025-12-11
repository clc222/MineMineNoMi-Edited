/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class YakkodoriProjectile extends AbilityProjectileEntity {
/*    */   public YakkodoriProjectile(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */   
/*    */   public YakkodoriProjectile(World world, LivingEntity player) {
/* 18 */     super((EntityType)SwordsmanProjectiles.YAKKODORI.get(), world, player, YakkodoriAbility.INSTANCE);
/*    */     
/* 20 */     setDamage(15.0F);
/* 21 */     setMaxLife(40);
/* 22 */     setBlocksAffectedLimit(512);
/*    */     
/* 24 */     setDamageSource(getDamageSource().setSlash());
/* 25 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 29 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 1.0F);
/* 30 */     explosion.setStaticDamage(5.0F);
/* 31 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 32 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\swordsman\YakkodoriProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */