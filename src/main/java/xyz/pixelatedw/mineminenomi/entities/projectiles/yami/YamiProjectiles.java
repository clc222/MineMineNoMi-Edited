/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class YamiProjectiles {
/* 20 */   public static final RegistryObject<EntityType<LiberationProjectile>> LIBERATION = WyRegistry.registerEntityType("Liberation", () -> WyRegistry.createEntityType(LiberationProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:liberation"));
/* 21 */   public static final RegistryObject<EntityType<DarkMatterProjectile>> DARK_MATTER = WyRegistry.registerEntityType("Dark Matter", () -> WyRegistry.createEntityType(DarkMatterProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:dark_matter"));
/* 22 */   public static final RegistryObject<EntityType<BlackRoadProjectile>> BLACK_ROAD = WyRegistry.registerEntityType("Black Road", () -> WyRegistry.createEntityType(BlackRoadProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:black_road"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)LIBERATION.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#000000").setScale(1.5D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DARK_MATTER.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#000000").setScale(9.0D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BLACK_ROAD.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\YamiProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */