/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ public class CoolBallProjectile extends WeatherBallProjectile {
/*    */   public CoolBallProjectile(EntityType<? extends Entity> type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */   
/*    */   public CoolBallProjectile(World world, LivingEntity player, AbilityCore<? extends IAbility> core) {
/* 16 */     super((EntityType<? extends Entity>)ArtOfWeatherProjectiles.COOL_BALL.get(), world, player, core);
/*    */     
/* 18 */     setDamage(0.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\CoolBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */