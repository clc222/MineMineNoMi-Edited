/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoBajrangGunProjectile extends AbilityProjectileEntity {
/*    */   public GomuGomuNoBajrangGunProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoBajrangGunProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_BAJRANG_GUN.get(), world, player, ability);
/*    */     
/* 22 */     setDamage(80.0F);
/* 23 */     setMaxLife(150);
/* 24 */     setCanGetStuckInGround();
/* 25 */     setPassThroughEntities();
/*    */     
/* 27 */     setEntityCollisionSize(6.0D, 3.0D, 6.0D);
/*    */     
/* 29 */     setDamageSource(((AbilityDamageSource)getDamageSource()).setSourceElement(SourceElement.RUBBER));
/*    */     
/* 31 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 35 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 12.0F);
/* 36 */     explosion.setExplosionSound(true);
/* 37 */     explosion.setDamageOwner(false);
/* 38 */     explosion.setDestroyBlocks(true);
/* 39 */     explosion.setFireAfterExplosion(false);
/* 40 */     explosion.setSmokeParticles(null);
/* 41 */     explosion.setDamageEntities(false);
/* 42 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoBajrangGunProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */