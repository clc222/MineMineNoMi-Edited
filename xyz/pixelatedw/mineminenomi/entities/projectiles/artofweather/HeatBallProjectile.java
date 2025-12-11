/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ 
/*    */ public class HeatBallProjectile extends WeatherBallProjectile {
/*    */   public HeatBallProjectile(EntityType<? extends Entity> type, World world) {
/* 12 */     super(type, world);
/*    */   }
/*    */   
/*    */   public HeatBallProjectile(World world, LivingEntity player, AbilityCore<? extends IAbility> core) {
/* 16 */     super((EntityType<? extends Entity>)ArtOfWeatherProjectiles.HEAT_BALL.get(), world, player, core);
/*    */     
/* 18 */     setDamage(2.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\HeatBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */