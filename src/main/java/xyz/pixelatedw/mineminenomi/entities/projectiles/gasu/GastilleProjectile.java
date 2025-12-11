/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.GastilleAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GastilleProjectile extends AbilityProjectileEntity {
/*    */   public GastilleProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GastilleProjectile(World world, LivingEntity player) {
/* 23 */     super((EntityType)GasuProjectiles.GASTILLE.get(), world, player, GastilleAbility.INSTANCE);
/*    */     
/* 25 */     setDamage(50.0F);
/* 26 */     setPassThroughEntities();
/* 27 */     setCanGetStuckInGround();
/* 28 */     setArmorPiercing(0.5F);
/*    */     
/* 30 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76436_u, 200, 3) });
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 37 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 5.0F);
/* 38 */     explosion.setStaticDamage(28.0F);
/* 39 */     explosion.setExplosionSound(true);
/* 40 */     explosion.setDamageOwner(false);
/* 41 */     explosion.setDestroyBlocks(true);
/* 42 */     explosion.setFireAfterExplosion(true);
/* 43 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 44 */     explosion.setDamageEntities(true);
/* 45 */     explosion.doExplosion();
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 49 */     if (!this.field_70170_p.field_72995_K)
/* 50 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GASTILLE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\GastilleProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */