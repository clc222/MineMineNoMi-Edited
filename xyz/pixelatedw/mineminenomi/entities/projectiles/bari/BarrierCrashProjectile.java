/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierCrashAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class BarrierCrashProjectile extends AbilityProjectileEntity {
/*    */   public BarrierCrashProjectile(EntityType type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */   
/*    */   public BarrierCrashProjectile(World world, LivingEntity player) {
/* 16 */     super((EntityType)BariProjectiles.BARRIER_CRASH.get(), world, player, BarrierCrashAbility.INSTANCE);
/*    */     
/* 18 */     setDamage(20.0F);
/* 19 */     setMaxLife(10);
/* 20 */     setPassThroughEntities();
/* 21 */     setPhysical();
/*    */     
/* 23 */     this.onEntityImpactEvent = (hitEntity -> AbilityHelper.setDeltaMovement((Entity)hitEntity, func_213322_ci()));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bari\BarrierCrashProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */