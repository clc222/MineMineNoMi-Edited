/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BiganProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BiganProjectile(EntityType type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BiganProjectile(World world, LivingEntity player) {
/* 17 */     super((EntityType)UshiGiraffeProjectiles.BIGAN.get(), world, player);
/* 18 */     setPhysical();
/* 19 */     setMaxLife(16);
/* 20 */     setDamage(22.0F);
/* 21 */     setCanGetStuckInGround();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectile\\ushigiraffe\BiganProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */