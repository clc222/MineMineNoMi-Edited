/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NoroNoroBeamModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GoeProjectiles {
/* 21 */   public static final RegistryObject<EntityType<TodorokiProjectile>> TODOROKI = WyRegistry.registerEntityType("Todoroki", () -> WyRegistry.createEntityType(TodorokiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:todoroki"));
/* 22 */   public static final RegistryObject<EntityType<DragonsRoarProjectile>> DRAGONS_ROAR = WyRegistry.registerEntityType("Dragon's Roar", () -> WyRegistry.createEntityType(DragonsRoarProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:dragons_roar"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TODOROKI.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(4.0D, 4.0D).setGlowing().setColor("#87CEFA").setScale(0.0D, 0.0D, 0.0D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DRAGONS_ROAR.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new NoroNoroBeamModel())).setTexture("dragonsroar").setScale(5.0D).setTranslate(0.0D, 0.1D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goe\GoeProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */