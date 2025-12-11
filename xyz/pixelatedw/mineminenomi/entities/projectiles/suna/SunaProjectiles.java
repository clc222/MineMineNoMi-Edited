/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PillarModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EmptyRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class SunaProjectiles {
/* 22 */   public static final RegistryObject<EntityType<BarjanProjectile>> BARJAN = WyRegistry.registerEntityType("Barjan", () -> WyRegistry.createEntityType(BarjanProjectile::new).func_220321_a(2.5F, 0.8F).func_206830_a("mineminenomi:barjan"));
/* 23 */   public static final RegistryObject<EntityType<DesertSpadaProjectile>> DESERT_SPADA = WyRegistry.registerEntityType("Desert Spada", () -> WyRegistry.createEntityType(DesertSpadaProjectile::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:desert_spada"));
/* 24 */   public static final RegistryObject<EntityType<DesertGrandeEspadaProjectile>> DESERT_GRANDE_ESPADA = WyRegistry.registerEntityType("Desert Grande Spada", () -> WyRegistry.createEntityType(DesertGrandeEspadaProjectile::new).func_220321_a(1.25F, 1.25F).func_206830_a("mineminenomi:desert_grande_spada"));
/* 25 */   public static final RegistryObject<EntityType<SablesPesadoProjectile>> SABLES_PESADO = WyRegistry.registerEntityType("Sables Pesado", () -> WyRegistry.createEntityType(SablesPesadoProjectile::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:sables_pesado"));
/* 26 */   public static final RegistryObject<EntityType<SandBladeEntity>> SAND_BLADE = WyRegistry.registerEntityType("Sand Blade", () -> WyRegistry.createEntityType(SandBladeEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:desert_grande_spada2"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BARJAN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("C2B280").setScale(6.0D, 0.15D, 1.25D));
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DESERT_SPADA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DESERT_GRANDE_ESPADA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PillarModel())).setColor("#C2B280").setScale(3.0D, 3.0D, 3.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SABLES_PESADO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(194, 178, 128, 255).setScale(6.0D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SAND_BLADE.get(), (IRenderFactory)new EmptyRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\SunaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */