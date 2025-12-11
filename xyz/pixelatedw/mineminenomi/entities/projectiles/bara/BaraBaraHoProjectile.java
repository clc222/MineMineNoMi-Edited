/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BaraBaraHoProjectile extends AbilityProjectileEntity {
/*    */   public BaraBaraHoProjectile(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */   
/*    */   public BaraBaraHoProjectile(World world, LivingEntity player) {
/* 15 */     super((EntityType)BaraProjectiles.BARA_BARA_HO.get(), world, player, BaraBaraHoAbility.INSTANCE);
/*    */     
/* 17 */     setDamage(6.0F);
/* 18 */     setMaxLife(12);
/* 19 */     setFist();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\BaraBaraHoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */