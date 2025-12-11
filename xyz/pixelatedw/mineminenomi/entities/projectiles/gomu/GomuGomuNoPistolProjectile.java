/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GomuGomuNoPistolAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoPistolProjectile extends AbilityProjectileEntity {
/*    */   public GomuGomuNoPistolProjectile(EntityType type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */   
/*    */   public GomuGomuNoPistolProjectile(World world, LivingEntity player) {
/* 16 */     super((EntityType)GomuProjectiles.GOMU_GOMU_NO_PISTOL.get(), world, player, GomuGomuNoPistolAbility.INSTANCE);
/*    */     
/* 18 */     setDamage(6.0F);
/* 19 */     setMaxLife(9);
/* 20 */     setFist();
/*    */     
/* 22 */     setEntityCollisionSize(1.0D);
/*    */     
/* 24 */     setDamageSource(getDamageSource().setSourceElement(SourceElement.RUBBER));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoPistolProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */