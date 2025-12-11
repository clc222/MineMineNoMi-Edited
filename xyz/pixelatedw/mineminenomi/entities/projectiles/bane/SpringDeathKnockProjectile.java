/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SpringDeathKnockProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public SpringDeathKnockProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpringDeathKnockProjectile(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)BaneProjectiles.SPRING_DEATH_KNOCK.get(), world, player, ability);
/*    */     
/* 20 */     setDamage(20.0F);
/* 21 */     setMaxLife(5);
/* 22 */     setFist();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bane\SpringDeathKnockProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */