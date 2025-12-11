/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DaiCircusProjectile extends AbilityProjectileEntity {
/*    */   public DaiCircusProjectile(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DaiCircusProjectile(World world, LivingEntity player, Ability ability) {
/* 15 */     super((EntityType)BaraProjectiles.DAI_CIRCUS.get(), world, player, ability);
/*    */     
/* 17 */     setDamage(4.0F);
/* 18 */     setMaxLife(30);
/* 19 */     setEntityCollisionSize(2.0D);
/* 20 */     setPhysical();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\DaiCircusProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */