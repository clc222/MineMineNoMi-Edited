/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class ShishaNoTeProjectile extends AbilityProjectileEntity {
/*    */   public ShishaNoTeProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ShishaNoTeProjectile(World world, LivingEntity player, Ability ability) {
/* 22 */     super((EntityType)SukeProjectiles.SHISHA_NO_TE.get(), world, player, ability);
/*    */     
/* 24 */     setDamage(18.0F);
/*    */     
/* 26 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 31 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 32 */     explosion.setStaticDamage(8.0F);
/* 33 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 34 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suke\ShishaNoTeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */