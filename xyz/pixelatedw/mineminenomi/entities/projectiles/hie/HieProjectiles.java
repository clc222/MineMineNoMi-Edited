/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PheasantModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TridentModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class HieProjectiles {
/* 21 */   public static final RegistryObject<EntityType<IceBlockPartisanProjectile>> ICE_BLOCK_PARTISAN = WyRegistry.registerEntityType("Ice Block: Partisan", () -> WyRegistry.createEntityType(IceBlockPartisanProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:ice_block_partisan"));
/* 22 */   public static final RegistryObject<EntityType<IceBallProjectile>> ICE_BALL = WyRegistry.registerEntityType("Ice Ball", () -> WyRegistry.createEntityType(IceBallProjectile::new).func_220321_a(0.7F, 0.7F).func_206830_a("mineminenomi:ice_ball"));
/* 23 */   public static final RegistryObject<EntityType<IceBlockPheasantProjectile>> ICE_BLOCK_PHEASANT = WyRegistry.registerEntityType("Ice Block: Pheasant", () -> WyRegistry.createEntityType(IceBlockPheasantProjectile::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:ice_block_pheasant"));
/* 24 */   public static final RegistryObject<EntityType<IceBlockAvalancheProjectile>> ICE_BLOCK_AVALANCHE = WyRegistry.registerEntityType("Ice Block: Avalanche", () -> WyRegistry.createEntityType(IceBlockAvalancheProjectile::new).func_220321_a(9.0F, 9.0F).func_206830_a("mineminenomi:ice_block_avalanche"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ICE_BLOCK_PARTISAN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new TridentModel())).setTexture("iceblockpartisan").setScale(1.5D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ICE_BALL.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#36648B").setScale(5.0D));
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ICE_BLOCK_PHEASANT.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PheasantModel())).setTexture("iceblockpheasant").setScale(5.0D));
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ICE_BLOCK_AVALANCHE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#54f7ff").setScale(8.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\HieProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */