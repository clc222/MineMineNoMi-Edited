/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BrickBatProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BrickBatProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrickBatProjectile(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)KageProjectiles.BRICK_BAT.get(), world, player, ability);
/*    */     
/* 20 */     setDamage(5.0F);
/* 21 */     setPassThroughEntities();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\BrickBatProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */