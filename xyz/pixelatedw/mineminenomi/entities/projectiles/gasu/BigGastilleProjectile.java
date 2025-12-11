/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.GastilleAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BigGastilleProjectile extends AbilityProjectileEntity {
/*    */   public BigGastilleProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */   
/*    */   public BigGastilleProjectile(World world, LivingEntity player) {
/* 26 */     super((EntityType)GasuProjectiles.BIG_GASTILLE.get(), world, player, GastilleAbility.INSTANCE);
/*    */     
/* 28 */     setDamage(70.0F);
/*    */     
/* 30 */     this.onEntityImpactEvent = this::onEntityImpact;
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 32 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpact(LivingEntity entity) {
/* 36 */     ((ShinokuniAbility)AbilityDataCapability.get(Objects.<LivingEntity>requireNonNull(getThrower())).getEquippedAbility(ShinokuniAbility.INSTANCE)).applyEffects(getThrower(), entity);
/* 37 */     this.onBlockImpactEvent.onImpact(entity.func_233580_cy_());
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 41 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 8.0F);
/* 42 */     explosion.setStaticDamage(28.0F);
/* 43 */     explosion.setExplosionSound(true);
/* 44 */     explosion.setDamageOwner(false);
/* 45 */     explosion.setDestroyBlocks(true);
/* 46 */     explosion.setFireAfterExplosion(true);
/* 47 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 48 */     explosion.setDamageEntities(true);
/* 49 */     explosion.doExplosion();
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 53 */     if (!this.field_70170_p.field_72995_K)
/* 54 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BIG_GASTILLE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\BigGastilleProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */