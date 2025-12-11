/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class CandyMaidenProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public CandyMaidenProjectile(EntityType type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyMaidenProjectile(World world, LivingEntity player) {
/* 17 */     super((EntityType)PeroProjectiles.CANDY_MAIDEN.get(), world, player);
/*    */     
/* 19 */     setPassThroughEntities();
/* 20 */     setDamage(35.0F);
/* 21 */     setMaxLife(40);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\CandyMaidenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */