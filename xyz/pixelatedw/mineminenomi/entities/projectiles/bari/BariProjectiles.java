/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;
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
/*    */ public class BariProjectiles {
/* 19 */   public static final RegistryObject<EntityType<BarrierCrashProjectile>> BARRIER_CRASH = WyRegistry.registerEntityType("Barrier Crash", () -> WyRegistry.createEntityType(BarrierCrashProjectile::new).func_220321_a(4.0F, 5.0F).func_206830_a("mineminenomi:barrier_crash"));
/* 20 */   public static final RegistryObject<EntityType<BarrierbilityStairsProjectile>> BARRIERBILITY_STAIRS = WyRegistry.registerEntityType("Barrierbility Stairs", () -> WyRegistry.createEntityType(BarrierbilityStairsProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:barrierbility_stairs"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 26 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BARRIER_CRASH.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#87CEFAAA").setScale(8.0D, 10.0D, 0.5D));
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BARRIERBILITY_STAIRS.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bari\BariProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */