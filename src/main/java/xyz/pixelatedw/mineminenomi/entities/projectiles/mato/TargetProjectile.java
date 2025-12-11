/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mato;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TargetProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public TargetProjectile(World world) {
/* 12 */     super(MatoProjectiles.TARGET_PROJECTILE, world);
/*    */   }
/*    */   
/*    */   public TargetProjectile(EntityType entity, World world) {
/* 16 */     super(entity, world);
/*    */   }
/*    */   
/*    */   public TargetProjectile(World world, double x, double y, double z) {
/* 20 */     super(MatoProjectiles.TARGET_PROJECTILE, world, x, y, z);
/*    */   }
/*    */   
/*    */   public TargetProjectile(World world, LivingEntity p) {
/* 24 */     super(MatoProjectiles.TARGET_PROJECTILE, world, p);
/* 25 */     setMaxLife(128);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mato\TargetProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */