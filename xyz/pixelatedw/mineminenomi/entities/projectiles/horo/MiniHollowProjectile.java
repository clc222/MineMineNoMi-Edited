/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class MiniHollowProjectile extends AbilityProjectileEntity {
/*    */   public MiniHollowProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MiniHollowProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)HoroProjectiles.MINI_HOLLOW.get(), world, player, ability);
/*    */     
/* 26 */     setDamage(2.5F);
/*    */     
/* 28 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76431_k, 150, 0), new EffectInstance(Effects.field_76421_d, 150, 0) });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 40 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 1.0F);
/* 41 */     explosion.setStaticDamage(7.5F);
/* 42 */     explosion.setExplosionSound(true);
/* 43 */     explosion.setDamageOwner(false);
/* 44 */     explosion.setDestroyBlocks(true);
/* 45 */     explosion.setFireAfterExplosion(false);
/* 46 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 47 */     explosion.setDamageEntities(false);
/* 48 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\MiniHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */