/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.OnibiModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class MeraProjectiles {
/* 21 */   public static final RegistryObject<EntityType<HikenProjectile>> HIKEN = WyRegistry.registerEntityType("Hiken", () -> WyRegistry.createEntityType(HikenProjectile::new).func_220320_c().func_220321_a(1.2F, 1.2F).func_206830_a("mineminenomi:hiken"));
/* 22 */   public static final RegistryObject<EntityType<HiganProjectile>> HIGAN = WyRegistry.registerEntityType("Higan", () -> WyRegistry.createEntityType(HiganProjectile::new).func_220320_c().func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:higan"));
/* 23 */   public static final RegistryObject<EntityType<DaiEnkaiEnteiProjectile>> DAI_ENKAI_ENTEI = WyRegistry.registerEntityType("Dai Enkai: Entei", () -> WyRegistry.createEntityType(DaiEnkaiEnteiProjectile::new).func_220320_c().func_220321_a(3.0F, 3.0F).func_206830_a("mineminenomi:dai_enkai_entei"));
/* 24 */   public static final RegistryObject<EntityType<DaiEnkaiOnibiProjectile>> DAI_ENKAI_ONIBI = WyRegistry.registerEntityType("Dai Enkai: Onibi", () -> WyRegistry.createEntityType(DaiEnkaiOnibiProjectile::new).func_220320_c().func_220321_a(7.5F, 7.5F).func_206830_a("mineminenomi:dai_enkai_onibi"));
/* 25 */   public static final RegistryObject<EntityType<HidarumaProjectile>> HIDARUMA = WyRegistry.registerEntityType("Hidaruma", () -> WyRegistry.createEntityType(HidarumaProjectile::new).func_220320_c().func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:hidaruma"));
/* 26 */   public static final RegistryObject<EntityType<JujikaProjectile>> JUJIKA = WyRegistry.registerEntityType("Jujika", () -> WyRegistry.createEntityType(JujikaProjectile::new).func_220320_c().func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:jujika"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HIKEN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("hiken").setScale(1.5D).setGlowing());
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HIGAN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor(0.9F, 0.4F, 0.15F, 1.0F).setScale(0.5D).setGlowing());
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DAI_ENKAI_ENTEI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.9F, 0.4F, 0.15F, 1.0F).setScale(15.0D).setGlowing());
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DAI_ENKAI_ONIBI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new OnibiModel())).setColor(0.9F, 0.4F, 0.15F, 1.0F).setScale(3.75D, 3.75D, 1.875D).setGlowing().setTranslate(0.0D, -0.9375D, 0.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HIDARUMA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.0F, 1.0F, 0.0F, 1.0F).setScale(1.2D).setGlowing());
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)JUJIKA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.9F, 0.4F, 0.15F, 1.0F).setScale(1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\MeraProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */