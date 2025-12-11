/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoGrizzlyMagnumProjectile extends AbilityProjectileEntity {
/* 15 */   private Vector3d lookVec = Vector3d.field_186680_a; private static final double KNOCKBACK = 14.0D;
/*    */   
/*    */   public GomuGomuNoGrizzlyMagnumProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoGrizzlyMagnumProjectile(World world, LivingEntity player, Ability ability) {
/* 22 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM.get(), world, player, ability);
/*    */     
/* 24 */     setDamage(40.0F);
/* 25 */     setMaxLife(10);
/* 26 */     setPassThroughEntities();
/* 27 */     setFist();
/* 28 */     setHurtTime(10);
/*    */     
/* 30 */     setEntityCollisionSize(5.0D, 3.0D, 5.0D);
/*    */     
/* 32 */     this.lookVec = player.func_70040_Z();
/*    */     
/* 34 */     setDamageSource(((AbilityDamageSource)getDamageSource()).setSourceElement(SourceElement.RUBBER));
/*    */     
/* 36 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 40 */     Vector3d speed = this.lookVec.func_72432_b().func_216372_d(14.0D, 1.0D, 14.0D).func_72441_c(0.0D, 0.15D, 0.0D);
/* 41 */     AbilityHelper.setDeltaMovement((Entity)hitEntity, speed);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoGrizzlyMagnumProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */