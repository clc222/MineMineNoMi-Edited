/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.PunkCrossAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PunkCrossProjectile extends AbilityProjectileEntity {
/*    */   public PunkCrossProjectile(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */   
/*    */   public PunkCrossProjectile(World world, LivingEntity player) {
/* 15 */     super((EntityType)JikiProjectiles.PUNK_CROSS.get(), world, player, PunkCrossAbility.INSTANCE);
/* 16 */     setEntityCollisionSize(1.25D);
/* 17 */     setDamage(0.0F);
/* 18 */     setFake();
/* 19 */     setMaxLife(5);
/* 20 */     setPhysical();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70106_y() {
/* 25 */     super.func_70106_y();
/*    */   }
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\PunkCrossProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */