/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class BetaBetaChainProjectile extends AbilityProjectileEntity {
/*    */   public BetaBetaChainProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */   
/*    */   public BetaBetaChainProjectile(World world, LivingEntity player, Ability ability) {
/* 17 */     super((EntityType)BetaProjectiles.BETA_BETA_CHAIN.get(), world, player, ability);
/*    */     
/* 19 */     setDamage(4.0F);
/* 20 */     setLife(30);
/* 21 */     setPhysical();
/*    */     
/* 23 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 27 */     if (getLife() >= getMaxLife()) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     LivingEntity entity = getThrower();
/*    */     
/* 33 */     BlockPos distance = hit.func_177973_b((Vector3i)entity.func_233580_cy_());
/*    */     
/* 35 */     AbilityHelper.setDeltaMovement((Entity)entity, distance.func_177958_n() * 0.15D, 0.3D + (distance.func_177956_o() / 5), distance.func_177952_p() * 0.15D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\beta\BetaBetaChainProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */