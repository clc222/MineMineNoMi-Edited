/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.wara;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class StrawProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public StrawProjectile(EntityType entity, World world) {
/* 12 */     super(entity, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrawProjectile(World world, LivingEntity p) {
/* 17 */     super((EntityType)WaraProjectiles.STRAW_PROJECTILE.get(), world, p);
/* 18 */     setDamage(10.0F);
/* 19 */     setPhysical();
/* 20 */     setMaxLife(8);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\wara\StrawProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */