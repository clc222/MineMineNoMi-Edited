/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class SupaProjectiles {
/* 20 */   public static final RegistryObject<EntityType<SpiralHollowProjectile>> SPIRAL_HOLLOW = WyRegistry.registerEntityType("Spiral Hollow", () -> WyRegistry.createEntityType(SpiralHollowProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:spiral_hollow"));
/*    */   
/* 22 */   public static final RegistryObject<EntityType<SparklingDaisyAftereffectProjectile>> SPARKLING_DAISY_AFTER = WyRegistry.registerEntityType("Sparkling Daisy Aftereffect", () -> WyRegistry.createEntityType(SparklingDaisyAftereffectProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:sparkling_daisy_after"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SPARKLING_DAISY_AFTER.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setScale(0.0D, 0.0D, 0.0D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SPIRAL_HOLLOW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setColor("#A4A4A4").setScale(5.0D, 5.0D, 6.0D).addEffect(new AbilityProjectileRenderer.Effect[] { AbilityProjectileRenderer.Effect.SPIRAL }));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\supa\SupaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */