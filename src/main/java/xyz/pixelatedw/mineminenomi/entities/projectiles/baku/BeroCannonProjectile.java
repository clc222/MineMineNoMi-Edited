/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.baku;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BeroCannonProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BeroCannonProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BeroCannonProjectile(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)BakuProjectiles.BERO_CANNON.get(), world, player, ability);
/*    */     
/* 20 */     setDamage(20.0F);
/* 21 */     setMaxLife(50);
/* 22 */     setGravity(0.01F);
/* 23 */     setPhysical();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\baku\BeroCannonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */