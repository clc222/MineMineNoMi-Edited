/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
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
/*    */ public class BreezeBreathBombProjectile extends AbilityProjectileEntity {
/*    */   public BreezeBreathBombProjectile(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */   
/*    */   public BreezeBreathBombProjectile(World world, LivingEntity player) {
/* 18 */     super((EntityType)BomuProjectiles.BREEZE_BREATH_BOMB.get(), world, player);
/*    */     
/* 20 */     setPhysical();
/* 21 */     setDamage(15.0F);
/* 22 */     setMaxLife(26);
/* 23 */     setPassThroughEntities();
/*    */     
/* 25 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 29 */     if (this.field_70173_aa < 3) {
/*    */       return;
/*    */     }
/* 32 */     BlockPos pos = func_233580_cy_();
/* 33 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 2.0F);
/* 34 */     explosion.setHeightDifference(30);
/* 35 */     explosion.setStaticDamage(12.0F);
/* 36 */     explosion.setExplosionSound(true);
/* 37 */     explosion.setDamageOwner(false);
/* 38 */     explosion.setDestroyBlocks(true);
/* 39 */     explosion.setFireAfterExplosion(false);
/* 40 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 41 */     explosion.setDamageEntities(true);
/* 42 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bomu\BreezeBreathBombProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */