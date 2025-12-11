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
/*    */ public class GomuGomuNoKingKongGunProjectile extends AbilityProjectileEntity {
/*    */   public GomuGomuNoKingKongGunProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoKingKongGunProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_KING_KONG_GUN.get(), world, player, ability);
/*    */     
/* 22 */     setDamage(30.0F);
/* 23 */     setMaxLife(30);
/* 24 */     setCanGetStuckInGround();
/* 25 */     setPassThroughEntities();
/* 26 */     setFist();
/*    */     
/* 28 */     setEntityCollisionSize(5.0D, 3.0D, 5.0D);
/*    */     
/* 30 */     setDamageSource(((AbilityDamageSource)getDamageSource()).setSourceElement(SourceElement.RUBBER));
/*    */     
/* 32 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 36 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 3.0F);
/* 37 */     explosion.setStaticDamage(12.0F);
/* 38 */     explosion.setExplosionSound(true);
/* 39 */     explosion.setDamageOwner(false);
/* 40 */     explosion.setDestroyBlocks(true);
/* 41 */     explosion.setFireAfterExplosion(false);
/* 42 */     explosion.setSmokeParticles(null);
/* 43 */     explosion.setDamageEntities(false);
/* 44 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoKingKongGunProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */