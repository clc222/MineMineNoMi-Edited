/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon;
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
/*    */ public class RyuPteranodonProjectiles {
/* 20 */   public static final RegistryObject<EntityType<BarizodonProjectile>> BARIZODON = WyRegistry.registerEntityType("Barizodon", () -> WyRegistry.createEntityType(BarizodonProjectile::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:barizodon"));
/* 21 */   public static final RegistryObject<EntityType<TempuraudonProjectile>> TEMPURAUDON = WyRegistry.registerEntityType("Tempuraudon", () -> WyRegistry.createEntityType(TempuraudonProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:tempuraudon"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BARIZODON.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#fbf916").setScale(2.0D, 0.3D, 2.0D));
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TEMPURAUDON.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory(null, (EntityModel)new CubeModel())).setStretchScale(1.45D, 1.45D, 1.0D).setColor("#5b5b5b").setScale(1.0D, 1.0D, 1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ryupteranodon\RyuPteranodonProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */