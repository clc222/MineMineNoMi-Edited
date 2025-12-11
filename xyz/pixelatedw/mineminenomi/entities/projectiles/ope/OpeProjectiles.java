/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.TaktBlockRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class OpeProjectiles {
/* 21 */   public static final RegistryObject<EntityType<GammaKnifeProjectile>> GAMMA_KNIFE = WyRegistry.registerEntityType("Gamma Knife", () -> WyRegistry.createEntityType(GammaKnifeProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:gamma_knife"));
/* 22 */   public static final RegistryObject<EntityType<SpatialSlashProjectile>> SPATIAL_SLASH = WyRegistry.registerEntityType("Spatial Slash", () -> WyRegistry.createEntityType(SpatialSlashProjectile::new).func_220321_a(10.0F, 0.5F).func_206830_a("mineminenomi:spatial_slash"));
/* 23 */   public static final RegistryObject<EntityType<TaktBlockEntity>> TAKT_BLOCK = WyRegistry.registerEntityType("Takt Block", () -> WyRegistry.createEntityType(TaktBlockEntity::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:takt_block"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GAMMA_KNIFE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#00AB66").setScale(1.0D, 1.0D, 5.0D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SPATIAL_SLASH.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TAKT_BLOCK.get(), (IRenderFactory)new TaktBlockRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\OpeProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */