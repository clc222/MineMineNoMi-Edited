/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SharkModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class FishmanKarateProjectiles {
/* 22 */   public static final RegistryObject<EntityType<UchimizuProjectile>> UCHIMIZU = WyRegistry.registerEntityType("Uchimizu", () -> WyRegistry.createEntityType(UchimizuProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:uchimizu"));
/* 23 */   public static final RegistryObject<EntityType<MurasameProjectile>> MURASAME = WyRegistry.registerEntityType("Murasame", () -> WyRegistry.createEntityType(MurasameProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:murasame"));
/* 24 */   public static final RegistryObject<EntityType<YarinamiProjectile>> YARINAMI = WyRegistry.registerEntityType("Yarinami", () -> WyRegistry.createEntityType(YarinamiProjectile::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:yarinami"));
/* 25 */   public static final RegistryObject<EntityType<MizuTaihoProjectile>> MIZU_TAIHO = WyRegistry.registerEntityType("Mizu Taiho", () -> WyRegistry.createEntityType(MizuTaihoProjectile::new).func_220321_a(2.5F, 2.5F).func_206830_a("mineminenomi:mizu_taiho"));
/* 26 */   public static final RegistryObject<EntityType<MizuShuryudanProjectile>> MIZU_SHURYUDAN = WyRegistry.registerEntityType("Mizu Shuryudan", () -> WyRegistry.createEntityType(MizuShuryudanProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:mizu_shuryudan"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)UCHIMIZU.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#00CED1").setScale(0.5D, 0.5D, 1.0D));
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MURASAME.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SharkModel())).setTexture("murasame").setScale(0.8D, 0.8D, 1.2D));
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)YARINAMI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setColor("#00CED1").setScale(5.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MIZU_TAIHO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#00CED155").setScale(25.0D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MIZU_SHURYUDAN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#00CED155").setScale(2.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\FishmanKarateProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */