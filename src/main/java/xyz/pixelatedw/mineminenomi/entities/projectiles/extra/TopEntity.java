/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class TopEntity extends AbilityProjectileEntity {
/*    */   public TopEntity(EntityType<Entity> type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public TopEntity(World world, LivingEntity thrower, Ability ability) {
/* 20 */     super((EntityType)ExtraProjectiles.TOP.get(), world, thrower, ability.getCore());
/*    */     
/* 22 */     setDamage(2.0F);
/* 23 */     setArmorPiercing(0.25F);
/*    */     
/* 25 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 29 */     if (hashCode() % 3 == 0) {
/* 30 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 1.0F);
/* 31 */       explosion.setStaticDamage(2.0F);
/* 32 */       explosion.setHeightDifference(30);
/* 33 */       explosion.disableExplosionKnockback();
/* 34 */       explosion.setFireAfterExplosion(true);
/* 35 */       explosion.setExplosionSound(false);
/* 36 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 37 */       explosion.doExplosion();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\TopEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */