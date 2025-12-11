/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class GomuGomuNoRocketProjectile extends AbilityProjectileEntity {
/*    */   public GomuGomuNoRocketProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoRocketProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_ROCKET.get(), world, player, ability);
/*    */     
/* 22 */     setDamage(4.0F);
/* 23 */     setLife(24);
/* 24 */     setFist();
/*    */     
/* 26 */     setDamageSource(((AbilityDamageSource)getDamageSource()).setSourceElement(SourceElement.RUBBER));
/*    */     
/* 28 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 32 */     if (getLife() >= getMaxLife()) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     LivingEntity entity = getThrower();
/*    */     
/* 38 */     if (entity != null) {
/* 39 */       entity.func_195063_d((Effect)ModEffects.REDUCED_FALL.get());
/*    */       
/* 41 */       BlockPos distance = hit.func_177973_b((Vector3i)entity.func_233580_cy_());
/*    */       
/* 43 */       AbilityHelper.setDeltaMovement((Entity)entity, distance.func_177958_n() * 0.35D, 0.3D + distance.func_177956_o() * 0.35D, distance.func_177952_p() * 0.35D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoRocketProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */