/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GomuProjectiles {
/* 19 */   public static final RegistryObject<EntityType<GomuGomuNoPistolProjectile>> GOMU_GOMU_NO_PISTOL = WyRegistry.registerEntityType("Gomu Gomu no Pistol", () -> WyRegistry.createEntityType(GomuGomuNoPistolProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:gomu_gomu_no_pistol"));
/* 20 */   public static final RegistryObject<EntityType<GomuGomuNoJetPistolProjectile>> GOMU_GOMU_NO_JET_PISTOL = WyRegistry.registerEntityType("Gomu Gomu no Jet Pistol", () -> WyRegistry.createEntityType(GomuGomuNoJetPistolProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:gomu_gomu_no_jet_pistol"));
/* 21 */   public static final RegistryObject<EntityType<GomuGomuNoElephantGunProjectile>> GOMU_GOMU_NO_ELEPHANT_GUN = WyRegistry.registerEntityType("Gomu Gomu no Elephant Gun", () -> WyRegistry.createEntityType(GomuGomuNoElephantGunProjectile::new).func_220321_a(5.0F, 5.0F).func_206830_a("mineminenomi:gomu_gomu_no_elephant_gun"));
/* 22 */   public static final RegistryObject<EntityType<GomuGomuNoKingKongGunProjectile>> GOMU_GOMU_NO_KING_KONG_GUN = WyRegistry.registerEntityType("Gomu Gomu no King Kong Gun", () -> WyRegistry.createEntityType(GomuGomuNoKingKongGunProjectile::new).func_220321_a(5.0F, 5.0F).func_206830_a("mineminenomi:gomu_gomu_no_king_kong_gun"));
/* 23 */   public static final RegistryObject<EntityType<GomuGomuNoBajrangGunProjectile>> GOMU_GOMU_NO_BAJRANG_GUN = WyRegistry.registerEntityType("Gomu Gomu no Bajrang Gun", () -> WyRegistry.createEntityType(GomuGomuNoBajrangGunProjectile::new).func_220321_a(20.0F, 20.0F).func_206830_a("mineminenomi:gomu_gomu_no_bajrang_gun"));
/*    */   
/* 25 */   public static final RegistryObject<EntityType<GomuGomuNoBazookaProjectile>> GOMU_GOMU_NO_BAZOOKA = WyRegistry.registerEntityType("Gomu Gomu no Bazooka", () -> WyRegistry.createEntityType(GomuGomuNoBazookaProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:gomu_gomu_no_bazooka"));
/* 26 */   public static final RegistryObject<EntityType<GomuGomuNoJetBazookaProjectile>> GOMU_GOMU_NO_JET_BAZOOKA = WyRegistry.registerEntityType("Gomu Gomu no Jet Bazooka", () -> WyRegistry.createEntityType(GomuGomuNoJetBazookaProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:gomu_gomu_no_jet_bazooka"));
/* 27 */   public static final RegistryObject<EntityType<GomuGomuNoGrizzlyMagnumProjectile>> GOMU_GOMU_NO_GRIZZLY_MAGNUM = WyRegistry.registerEntityType("Gomu Gomu no Grizzly Magnum", () -> WyRegistry.createEntityType(GomuGomuNoGrizzlyMagnumProjectile::new).func_220321_a(5.0F, 5.0F).func_206830_a("mineminenomi:gomu_gomu_no_grizzly_magnum"));
/* 28 */   public static final RegistryObject<EntityType<GomuGomuNoLeoBazookaProjectile>> GOMU_GOMU_NO_LEO_BAZOOKA = WyRegistry.registerEntityType("Gomu Gomu no Leo Bazooka", () -> WyRegistry.createEntityType(GomuGomuNoLeoBazookaProjectile::new).func_220321_a(5.0F, 5.0F).func_206830_a("mineminenomi:gomu_gomu_no_leo_bazooka"));
/*    */   
/* 30 */   public static final RegistryObject<EntityType<GomuGomuNoRocketProjectile>> GOMU_GOMU_NO_ROCKET = WyRegistry.registerEntityType("Gomu Gomu no Rocket", () -> WyRegistry.createEntityType(GomuGomuNoRocketProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:gomu_gomu_no_rocket"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_PISTOL.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture());
/* 37 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_JET_PISTOL.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(1.45D, 1.45D).setPlayerTexture().setScale(3.0D, 3.0D, 3.0D));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_ELEPHANT_GUN.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(10.0D, 10.0D, 10.0D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/* 39 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_KING_KONG_GUN.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(4.5D, 4.5D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/* 40 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_BAJRANG_GUN.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(55.5D, 55.5D).setPlayerTexture().setScale(40.0D, 40.0D, 40.0D));
/*    */     
/* 42 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_BAZOOKA.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture());
/* 43 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_JET_BAZOOKA.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture().setScale(3.0D, 3.0D, 3.0D));
/* 44 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_GRIZZLY_MAGNUM.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(10.0D, 10.0D, 10.0D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/* 45 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_LEO_BAZOOKA.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(5.0D, 5.0D, 10.0D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/*    */     
/* 47 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GOMU_GOMU_NO_ROCKET.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */