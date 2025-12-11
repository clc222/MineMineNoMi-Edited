/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
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
/*    */ public class MokuProjectiles {
/* 19 */   public static final RegistryObject<EntityType<WhiteBlowProjectile>> WHITE_BLOW = WyRegistry.registerEntityType("White Blow", () -> WyRegistry.createEntityType(WhiteBlowProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:white_blow"));
/* 20 */   public static final RegistryObject<EntityType<WhiteBlowRushProjectile>> WHITE_BLOW_RUSH = WyRegistry.registerEntityType("White Blow Rush", () -> WyRegistry.createEntityType(WhiteBlowRushProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:white_blow_rush"));
/* 21 */   public static final RegistryObject<EntityType<WhiteSnakeProjectile>> WHITE_SNAKE = WyRegistry.registerEntityType("White Snake", () -> WyRegistry.createEntityType(WhiteSnakeProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:white_snake"));
/* 22 */   public static final RegistryObject<EntityType<WhiteOutProjectile>> WHITE_OUT = WyRegistry.registerEntityType("White Out", () -> WyRegistry.createEntityType(WhiteOutProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:white_out"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)WHITE_BLOW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)WHITE_BLOW_RUSH.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)WHITE_SNAKE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)WHITE_OUT.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\MokuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */