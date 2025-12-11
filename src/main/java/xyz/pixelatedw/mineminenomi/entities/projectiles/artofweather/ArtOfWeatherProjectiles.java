/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.MirageCloneRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ArtOfWeatherProjectiles {
/* 22 */   public static final RegistryObject<EntityType<HeatBallProjectile>> HEAT_BALL = WyRegistry.registerEntityType("Heat Ball", () -> WyRegistry.createEntityType(HeatBallProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("heat_ball"));
/* 23 */   public static final RegistryObject<EntityType<CoolBallProjectile>> COOL_BALL = WyRegistry.registerEntityType("Cool Ball", () -> WyRegistry.createEntityType(CoolBallProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("cool_ball"));
/* 24 */   public static final RegistryObject<EntityType<ThunderBallProjectile>> THUNDER_BALL = WyRegistry.registerEntityType("Thunder Ball", () -> WyRegistry.createEntityType(ThunderBallProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("thunder_ball"));
/* 25 */   public static final RegistryObject<EntityType<GustSwordProjectile>> GUST_SWORD = WyRegistry.registerEntityType("Gust Sword", () -> WyRegistry.createEntityType(GustSwordProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("gust_sword"));
/* 26 */   public static final RegistryObject<EntityType<WeatherEggProjectile>> WEATHER_EGG = WyRegistry.registerEntityType("Weather Egg", () -> WyRegistry.createEntityType(WeatherEggProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("weather_egg"));
/*    */   
/* 28 */   public static final RegistryObject<EntityType<MirageCloneEntity>> MIRAGE_CLONE = WyRegistry.registerEntityType("Mirage Clone", () -> WyRegistry.createEntityType(MirageCloneEntity::new).func_206830_a("mineminenomi:mirage_clone"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HEAT_BALL.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#FF0000BB").setScale(2.0D, 2.0D, 2.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)COOL_BALL.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#0000FFBB").setScale(2.0D, 2.0D, 2.0D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)THUNDER_BALL.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#FFFF00BB").setScale(2.0D, 2.0D, 2.0D));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GUST_SWORD.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)WEATHER_EGG.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#8B8B8BBB").setScale(2.0D, 2.0D, 2.0D));
/*    */     
/* 40 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MIRAGE_CLONE.get(), (IRenderFactory)new MirageCloneRenderer.Factory());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
/* 46 */     event.put((EntityType)MIRAGE_CLONE.get(), MirageCloneEntity.createAttributes().func_233813_a_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\ArtOfWeatherProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */