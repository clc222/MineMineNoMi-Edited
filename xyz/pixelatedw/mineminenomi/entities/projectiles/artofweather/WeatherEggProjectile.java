/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ public class WeatherEggProjectile
/*    */   extends WeatherBallProjectile {
/*    */   public WeatherEggProjectile(EntityType<? extends Entity> type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */   
/*    */   public WeatherEggProjectile(World world, LivingEntity player, AbilityCore<? extends IAbility> core) {
/* 21 */     super((EntityType<? extends Entity>)ArtOfWeatherProjectiles.WEATHER_EGG.get(), world, player, core);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 26 */     super.func_70071_h_();
/*    */     
/* 28 */     if (this.field_70170_p.field_72995_K || getThrower() == null || this.field_70173_aa < 100) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     IAbilityData props = AbilityDataCapability.get(getThrower());
/* 33 */     WeatherCloudTempo ability = (WeatherCloudTempo)props.getEquippedOrPassiveAbility(WeatherCloudTempo.INSTANCE);
/* 34 */     boolean canUseAbility = (ability != null && ability.canUse(getThrower()).isSuccess());
/*    */     
/* 36 */     if (canUseAbility) {
/* 37 */       WeatherCloudEntity cloud = new WeatherCloudEntity(this.field_70170_p);
/* 38 */       cloud.setSize(WEATHER_CLOUD_SIZE);
/* 39 */       cloud.func_70012_b(func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_(), 0.0F, 0.0F);
/* 40 */       AbilityHelper.setDeltaMovement((Entity)cloud, 0.0D, 0.0D, 0.0D);
/* 41 */       cloud.setThrower((LivingEntity)func_234616_v_());
/* 42 */       this.field_70170_p.func_217376_c((Entity)cloud);
/*    */     } 
/*    */     
/* 45 */     func_70106_y();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\WeatherEggProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */