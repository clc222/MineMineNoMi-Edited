/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.doku.ChloroBallRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.doku.HydraRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.doku.VenomRoadRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class DokuProjectiles {
/* 20 */   public static final RegistryObject<EntityType<HydraProjectile>> HYDRA = WyRegistry.registerEntityType("Hydra", () -> WyRegistry.createEntityType(HydraProjectile::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:hydra"));
/* 21 */   public static final RegistryObject<EntityType<ChloroBallProjectile>> CHLORO_BALL = WyRegistry.registerEntityType("Chloro Ball", () -> WyRegistry.createEntityType(ChloroBallProjectile::new).func_220321_a(0.75F, 0.75F).func_206830_a("mineminenomi:chloro_ball"));
/* 22 */   public static final RegistryObject<EntityType<VenomRoadProjectile>> VENOM_ROAD = WyRegistry.registerEntityType("Venom Road", () -> WyRegistry.createEntityType(VenomRoadProjectile::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:venom_road"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HYDRA.get(), (IRenderFactory)new HydraRenderer.Factory());
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CHLORO_BALL.get(), (IRenderFactory)new ChloroBallRenderer.Factory());
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)VENOM_ROAD.get(), (IRenderFactory)new VenomRoadRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\DokuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */