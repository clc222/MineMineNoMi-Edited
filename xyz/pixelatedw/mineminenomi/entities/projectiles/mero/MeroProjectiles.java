/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HeartModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class MeroProjectiles {
/* 19 */   public static final RegistryObject<EntityType<MeroMeroMellowProjectile>> MERO_MERO_MELLOW = WyRegistry.registerEntityType("Mero Mero Mellow", () -> WyRegistry.createEntityType(MeroMeroMellowProjectile::new).func_220321_a(3.5F, 3.5F).func_206830_a("mineminenomi:mero_mero_mellow"));
/* 20 */   public static final RegistryObject<EntityType<PistolKissProjectile>> PISTOL_KISS = WyRegistry.registerEntityType("Pistol Kiss", () -> WyRegistry.createEntityType(PistolKissProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:pistol_kiss"));
/* 21 */   public static final RegistryObject<EntityType<SlaveArrowProjectile>> SLAVE_ARROW = WyRegistry.registerEntityType("Slave Arrow", () -> WyRegistry.createEntityType(SlaveArrowProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:slave_arrow"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 26 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MERO_MERO_MELLOW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new HeartModel())).setTexture("meromeromellow").setScale(3.0D).setTranslate(0.0D, 0.5D, 0.0D));
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)PISTOL_KISS.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new HeartModel())).setTexture("meromeromellow").setScale(0.5D).setTranslate(0.0D, 0.5D, 0.0D));
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SLAVE_ARROW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new ArrowModel())).setColor("#FF69B4"));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\MeroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */