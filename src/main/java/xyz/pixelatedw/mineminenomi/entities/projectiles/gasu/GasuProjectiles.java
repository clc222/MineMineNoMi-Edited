/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GasuProjectiles {
/* 20 */   public static final RegistryObject<EntityType<GasRobeProjectile>> GAS_ROBE = WyRegistry.registerEntityType("Gas Robe", () -> WyRegistry.createEntityType(GasRobeProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:gas_robe"));
/* 21 */   public static final RegistryObject<EntityType<GastilleProjectile>> GASTILLE = WyRegistry.registerEntityType("Gastille", () -> WyRegistry.createEntityType(GastilleProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:gastille"));
/* 22 */   public static final RegistryObject<EntityType<BigGastilleProjectile>> BIG_GASTILLE = WyRegistry.registerEntityType("Big Gastille", () -> WyRegistry.createEntityType(BigGastilleProjectile::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:big_gastille"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GAS_ROBE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GASTILLE.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#300061").setScale(1.0D, 1.0D, 3.0D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BIG_GASTILLE.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(3.0D, 3.0D).setGlowing().setColor("#300061").setScale(3.0D, 3.0D, 9.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\GasuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */