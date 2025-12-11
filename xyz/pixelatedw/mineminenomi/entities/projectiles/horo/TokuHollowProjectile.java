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
/*    */ public class TokuHollowProjectile extends AbilityProjectileEntity {
/*    */   public TokuHollowProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuHollowProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)HoroProjectiles.TOKU_HOLLOW.get(), world, player, ability);
/*    */     
/* 26 */     setDamage(25.0F);
/*    */     
/* 28 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76431_k, 350, 1), new EffectInstance(Effects.field_76421_d, 350, 1) });
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 38 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 7.0F);
/* 39 */     explosion.setStaticDamage(35.0F);
/* 40 */     explosion.setExplosionSound(true);
/* 41 */     explosion.setDamageOwner(false);
/* 42 */     explosion.setDestroyBlocks(true);
/* 43 */     explosion.setFireAfterExplosion(false);
/* 44 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(7));
/* 45 */     explosion.setDamageEntities(true);
/* 46 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\TokuHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */