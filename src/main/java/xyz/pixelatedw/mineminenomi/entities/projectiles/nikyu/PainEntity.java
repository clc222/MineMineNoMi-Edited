/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.nikyu.PainRepelAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class PainEntity extends AbilityProjectileEntity {
/*    */   public PainEntity(EntityType<Entity> type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public PainEntity(World world, LivingEntity player) {
/* 20 */     super((EntityType)NikyuProjectiles.PAIN.get(), world, player, PainRepelAbility.INSTANCE);
/*    */     
/* 22 */     setArmorPiercing(1.0F);
/*    */     
/* 24 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 28 */     float size = Math.min(getDamage(), 20.0F);
/*    */     
/* 30 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), Math.max(1.0F, size / 2.0F));
/*    */     
/* 32 */     explosion.setExplosionSound(true);
/* 33 */     explosion.setDamageEntities(false);
/* 34 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)size));
/* 35 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\PainEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */