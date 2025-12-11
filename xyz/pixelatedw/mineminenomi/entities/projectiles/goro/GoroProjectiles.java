/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.VoltVariProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.LightningBallEntityRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.LightningEntityRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GoroProjectiles {
/* 23 */   public static final RegistryObject<EntityType<LightningEntity>> LIGHTNING = WyRegistry.registerEntityType("Lightning", () -> WyRegistry.createEntityType(LightningEntity::new).func_220321_a(0.0F, 0.0F).func_220320_c().setTrackingRange(1024).setUpdateInterval(1).func_206830_a("mineminenomi:lightning"));
/* 24 */   public static final RegistryObject<EntityType<RaigoProjectile>> RAIGO = WyRegistry.registerEntityType("Raigo", () -> WyRegistry.createEntityType(RaigoProjectile::new).func_220321_a(15.0F, 15.0F).setUpdateInterval(1).setTrackingRange(1024).func_206830_a("mineminenomi:raigo"));
/* 25 */   public static final RegistryObject<EntityType<VariProjectile>> VOLT_VARI = WyRegistry.registerEntityType("Vari", () -> WyRegistry.createEntityType(VariProjectile::new).func_220321_a(1.75F, 1.75F).func_206830_a("mineminenomi:vari"));
/* 26 */   public static final RegistryObject<EntityType<LightningDischargeEntity>> LIGHTNING_BALL = WyRegistry.registerEntityType("Lightning Ball", () -> WyRegistry.createEntityType(LightningDischargeEntity::new).func_220321_a(0.0F, 0.0F).func_206830_a("mineminenomi:lightning_ball"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)LIGHTNING.get(), (IRenderFactory)new LightningEntityRenderer.Factory());
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)LIGHTNING_BALL.get(), (IRenderFactory)new LightningBallEntityRenderer.Factory());
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)RAIGO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(87, 85, 73, 255).setScale(60.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)VOLT_VARI.get(), (IRenderFactory)(new VoltVariProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.0D, 0.9800000190734863D, 1.0D, 0.44999998807907104D).setScale(10.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\GoroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */