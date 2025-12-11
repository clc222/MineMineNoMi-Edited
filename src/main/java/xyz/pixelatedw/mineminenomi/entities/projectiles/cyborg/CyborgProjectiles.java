/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class CyborgProjectiles {
/* 21 */   public static final RegistryObject<EntityType<FreshFireProjectile>> FRESH_FIRE = WyRegistry.registerEntityType("Fresh Fire", () -> WyRegistry.createEntityType(FreshFireProjectile::new).func_220321_a(0.5F, 0.5F).func_220320_c().func_206830_a("mineminenomi:fresh_fire"));
/* 22 */   public static final RegistryObject<EntityType<RadicalBeamProjectile>> RADICAL_BEAM = WyRegistry.registerEntityType("Radical Beam", () -> WyRegistry.createEntityType(RadicalBeamProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:radical_beam"));
/* 23 */   public static final RegistryObject<EntityType<StrongRightProjectile>> STRONG_RIGHT = WyRegistry.registerEntityType("Strong Right", () -> WyRegistry.createEntityType(StrongRightProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:strong_right"));
/* 24 */   public static final RegistryObject<EntityType<CoupDeVentProjectile>> COUP_DE_VENT = WyRegistry.registerEntityType("Coup de Vent", () -> WyRegistry.createEntityType(CoupDeVentProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:coup_de_vent"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)FRESH_FIRE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)RADICAL_BEAM.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#FFFF55").setScale(1.0D, 1.0D, 3.0D));
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)STRONG_RIGHT.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setColor("#336666").setScale(1.5D, 1.5D, 1.7D));
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)COUP_DE_VENT.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\CyborgProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */