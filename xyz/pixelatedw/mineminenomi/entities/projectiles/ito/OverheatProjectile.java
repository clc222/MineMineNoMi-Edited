/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class OverheatProjectile extends AbilityProjectileEntity {
/*    */   public OverheatProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public OverheatProjectile(World world, LivingEntity player, Ability ability) {
/* 21 */     super((EntityType)ItoProjectiles.OVERHEAT.get(), world, player, ability);
/*    */     
/* 23 */     setDamage(60.0F);
/* 24 */     setMaxLife(50);
/* 25 */     setPassThroughEntities();
/* 26 */     setCanGetStuckInGround();
/*    */     
/* 28 */     setDamageSource(getDamageSource().setSlash());
/* 29 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 30 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 35 */     this.onBlockImpactEvent.onImpact(hitEntity.func_233580_cy_());
/* 36 */     AbilityHelper.setSecondsOnFireBy((Entity)hitEntity, 10, getThrower());
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 41 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 4.0F);
/* 42 */     explosion.setStaticDamage(20.0F);
/* 43 */     explosion.setExplosionSound(true);
/* 44 */     explosion.setDamageOwner(false);
/* 45 */     explosion.setDestroyBlocks(true);
/* 46 */     explosion.setFireAfterExplosion(true);
/* 47 */     explosion.setSmokeParticles(null);
/* 48 */     explosion.setDamageEntities(true);
/* 49 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\OverheatProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */