/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class NormalBulletProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public NormalBulletProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalBulletProjectile(World world, LivingEntity player) {
/* 20 */     super((EntityType)ExtraProjectiles.NORMAL_BULLET.get(), world, player, SourceElement.NONE, SourceHakiNature.IMBUING, new SourceType[] { SourceType.PROJECTILE, SourceType.PHYSICAL, SourceType.BULLET });
/* 21 */     setDamage(8.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\NormalBulletProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */