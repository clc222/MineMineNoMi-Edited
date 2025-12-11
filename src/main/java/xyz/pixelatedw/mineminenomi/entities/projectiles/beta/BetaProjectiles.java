/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BetaProjectiles {
/* 21 */   public static final RegistryObject<EntityType<StickyProjectile>> STICKY_PROJECTILE = WyRegistry.registerEntityType("Mucus Bomb", () -> WyRegistry.createEntityType(StickyProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:sticky"));
/* 22 */   public static final RegistryObject<EntityType<BetaBetaChainProjectile>> BETA_BETA_CHAIN = WyRegistry.registerEntityType("Beta Chain", () -> WyRegistry.createEntityType(BetaBetaChainProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:beta_beta_chain"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)STICKY_PROJECTILE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setTexture(ModResources.BETA_COATING).setColor(0, 255, 0, 100).setScale(0.5D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BETA_BETA_CHAIN.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.4D, 1.4D, 10.0D).setColor("#269B20").setScale(1.0D, 1.0D, 1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\beta\BetaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */