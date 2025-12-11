/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TamaitoProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public TamaitoProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TamaitoProjectile(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)ItoProjectiles.TAMAITO.get(), world, player, ability);
/*    */     
/* 20 */     setDamage(15.0F);
/* 21 */     setMaxLife(30);
/* 22 */     setArmorPiercing(0.25F);
/* 23 */     setDamageSource(getDamageSource().setSlash());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\TamaitoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */