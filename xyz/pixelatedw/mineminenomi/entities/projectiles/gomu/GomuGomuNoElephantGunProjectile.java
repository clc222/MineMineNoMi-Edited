/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoElephantGunProjectile extends AbilityProjectileEntity {
/* 17 */   private Vector3d lookVec = Vector3d.field_186680_a; private static final double KNOCKBACK = 7.0D;
/*    */   
/*    */   public GomuGomuNoElephantGunProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoElephantGunProjectile(World world, LivingEntity player, Ability ability) {
/* 24 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN.get(), world, player, ability.getCore());
/*    */     
/* 26 */     setDamage(24.0F);
/* 27 */     setMaxLife(12);
/* 28 */     setCanGetStuckInGround();
/* 29 */     setPassThroughEntities();
/* 30 */     setFist();
/*    */     
/* 32 */     setEntityCollisionSize(5.0D, 3.0D, 5.0D);
/*    */     
/* 34 */     this.lookVec = player.func_70040_Z();
/*    */     
/* 36 */     setDamageSource(((AbilityDamageSource)getDamageSource()).setSourceElement(SourceElement.RUBBER));
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 39 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 43 */     Vector3d speed = this.lookVec.func_72432_b().func_216372_d(7.0D, 1.0D, 7.0D).func_72441_c(0.0D, 0.15D, 0.0D);
/* 44 */     AbilityHelper.setDeltaMovement((Entity)hitEntity, speed);
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 48 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 2.0F);
/* 49 */     explosion.setStaticDamage(8.0F);
/* 50 */     explosion.setExplosionSound(true);
/* 51 */     explosion.setDamageOwner(false);
/* 52 */     explosion.setDestroyBlocks(true);
/* 53 */     explosion.setFireAfterExplosion(false);
/* 54 */     explosion.setSmokeParticles(null);
/* 55 */     explosion.setDamageEntities(false);
/* 56 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoElephantGunProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */