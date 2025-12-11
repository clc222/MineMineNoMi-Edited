/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class StrongRightProjectile extends AbilityProjectileEntity {
/*    */   public StrongRightProjectile(EntityType type, World world) {
/* 11 */     super(type, world);
/*    */   }
/*    */   
/*    */   public StrongRightProjectile(World world, LivingEntity player, Ability ability) {
/* 15 */     super((EntityType)CyborgProjectiles.STRONG_RIGHT.get(), world, player, ability);
/*    */     
/* 17 */     setDamage(20.0F);
/* 18 */     setMaxLife(15);
/* 19 */     setFist();
/*    */     
/* 21 */     setEntityCollisionSize(1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\StrongRightProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */