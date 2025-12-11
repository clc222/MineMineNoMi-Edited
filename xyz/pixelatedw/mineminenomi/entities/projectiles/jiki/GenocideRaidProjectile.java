/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.GenocideRaidAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GenocideRaidProjectile extends AbilityProjectileEntity {
/*    */   public GenocideRaidProjectile(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GenocideRaidProjectile(World world, LivingEntity player) {
/* 15 */     super((EntityType)JikiProjectiles.GENOCIDE_RAID.get(), world, player, GenocideRaidAbility.INSTANCE);
/* 16 */     setEntityCollisionSize(1.25D);
/* 17 */     setDamage(0.0F);
/* 18 */     setFake();
/* 19 */     setMaxLife(10);
/* 20 */     setPhysical();
/*    */   }
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\GenocideRaidProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */