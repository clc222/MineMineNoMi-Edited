/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoJetBazookaProjectile extends AbilityProjectileEntity {
/* 14 */   private Vector3d lookVec = Vector3d.field_186680_a; private static final double KNOCKBACK = 8.0D;
/*    */   
/*    */   public GomuGomuNoJetBazookaProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoJetBazookaProjectile(World world, LivingEntity player, Ability ability) {
/* 21 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA.get(), world, player, ability);
/*    */     
/* 23 */     setDamage(30.0F);
/* 24 */     setMaxLife(5);
/* 25 */     setFist();
/* 26 */     setHurtTime(10);
/* 27 */     setPassThroughEntities();
/*    */     
/* 29 */     this.lookVec = player.func_70040_Z();
/*    */     
/* 31 */     setEntityCollisionSize(1.0D);
/*    */     
/* 33 */     setDamageSource(getDamageSource().setSourceElement(SourceElement.RUBBER));
/*    */     
/* 35 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 39 */     Vector3d speed = this.lookVec.func_72432_b().func_216372_d(8.0D, 1.0D, 8.0D).func_72441_c(0.0D, 0.15D, 0.0D);
/* 40 */     AbilityHelper.setDeltaMovement((Entity)hitEntity, speed);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoJetBazookaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */