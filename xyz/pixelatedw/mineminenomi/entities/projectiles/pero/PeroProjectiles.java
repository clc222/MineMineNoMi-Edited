/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class PeroProjectiles {
/* 19 */   public static final RegistryObject<EntityType<CandyEscalatorProjectile>> CANDY_ESCALATOR = WyRegistry.registerEntityType("Candy Escalator", () -> WyRegistry.createEntityType(CandyEscalatorProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:candy_escalator"));
/* 20 */   public static final RegistryObject<EntityType<CandyMaidenProjectile>> CANDY_MAIDEN = WyRegistry.registerEntityType("Candy Maiden", () -> WyRegistry.createEntityType(CandyMaidenProjectile::new).func_220321_a(2.5F, 7.5F).func_206830_a("mineminenomi:candy_maiden"));
/* 21 */   public static final RegistryObject<EntityType<CandyWaveProjectile>> CANDY_WAVE = WyRegistry.registerEntityType("Candy Wave", () -> WyRegistry.createEntityType(CandyWaveProjectile::new).func_220321_a(3.0F, 3.0F).func_206830_a("mineminenomi:candy_wave"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CANDY_ESCALATOR.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CANDY_WAVE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\PeroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */