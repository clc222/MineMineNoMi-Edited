/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WeatherBallProjectile
/*    */   extends AbilityProjectileEntity {
/* 23 */   protected static final EntitySize WEATHER_CLOUD_SIZE = EntitySize.func_220311_c(40.0F, 4.0F);
/*    */   
/*    */   protected Item weaponUsed;
/*    */   
/*    */   public WeatherBallProjectile(EntityType<? extends Entity> type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */   
/*    */   public WeatherBallProjectile(EntityType<? extends Entity> type, World world, double x, double y, double z) {
/* 32 */     super(type, world, x, y, z);
/*    */   }
/*    */   
/*    */   public WeatherBallProjectile(EntityType<? extends Entity> type, World world, LivingEntity player, AbilityCore<? extends IAbility> core) {
/* 36 */     super(type, world, player, core);
/*    */     
/* 38 */     setMaxLife(300);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 43 */     super.func_70071_h_();
/*    */     
/* 45 */     AbilityHelper.setDeltaMovement((Entity)this, (func_213322_ci()).field_72450_a / 1.5D, (func_213322_ci()).field_72448_b, (func_213322_ci()).field_72449_c / 1.5D);
/* 46 */     if (this.field_70173_aa < 200) {
/* 47 */       func_213322_ci().func_72441_c(0.0D, 0.35D, 0.0D);
/*    */     } else {
/*    */       
/* 50 */       AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 53 */     if (this.field_70170_p.field_72995_K || func_234616_v_() == null || this.field_70173_aa < 50) {
/*    */       return;
/*    */     }
/*    */     
/* 57 */     Optional<WeatherCloudEntity> weatherCloud = WyHelper.getNearbyEntities(func_213303_ch(), (IWorld)this.field_70170_p, 10.0D, 4.0D, 10.0D, null, new Class[] { WeatherCloudEntity.class }).stream().findFirst();
/* 58 */     if (weatherCloud.isPresent()) {
/* 59 */       ((WeatherCloudEntity)weatherCloud.get()).addWeatherBall(this);
/* 60 */       if (this instanceof ThunderBallProjectile) {
/* 61 */         ((WeatherCloudEntity)weatherCloud.get()).setLife(getLife() + 200);
/*    */       }
/* 63 */       func_70106_y();
/*    */       
/*    */       return;
/*    */     } 
/* 67 */     if (this.field_70173_aa < 150) {
/*    */       return;
/*    */     }
/*    */     
/* 71 */     if (this instanceof CoolBallProjectile) {
/* 72 */       List<HeatBallProjectile> heatBalls = WyHelper.getNearbyEntities(func_213303_ch(), (IWorld)this.field_70170_p, 6.0D, 4.0D, 6.0D, null, new Class[] { HeatBallProjectile.class });
/* 73 */       IAbilityData props = AbilityDataCapability.get(getThrower());
/* 74 */       WeatherCloudTempo weatherCloudTempo = (WeatherCloudTempo)props.getPassiveAbility(WeatherCloudTempo.INSTANCE);
/* 75 */       boolean canUseWeatherCloud = (weatherCloudTempo != null && weatherCloudTempo.canUse(getThrower()).isSuccess() && heatBalls.size() > 0);
/*    */       
/* 77 */       if (canUseWeatherCloud) {
/* 78 */         weatherCloudTempo.use(getThrower());
/* 79 */         WeatherCloudEntity newCloud = new WeatherCloudEntity(this.field_70170_p);
/* 80 */         newCloud.setSize(WEATHER_CLOUD_SIZE);
/* 81 */         newCloud.func_70012_b(func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_(), 0.0F, 0.0F);
/* 82 */         AbilityHelper.setDeltaMovement((Entity)newCloud, 0.0D, 0.0D, 0.0D);
/* 83 */         newCloud.setThrower((LivingEntity)func_234616_v_());
/* 84 */         this.field_70170_p.func_217376_c((Entity)newCloud);
/* 85 */         heatBalls.forEach(e -> e.func_70106_y());
/* 86 */         func_70106_y();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Item getWeaponUsed() {
/* 92 */     return this.weaponUsed;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\WeatherBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */