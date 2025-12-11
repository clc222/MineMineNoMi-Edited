/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.HookEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.CrescentModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TopModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.HookRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ExtraProjectiles {
/* 24 */   public static final RegistryObject<EntityType<NormalBulletProjectile>> NORMAL_BULLET = WyRegistry.registerEntityType("Normal Bullet", () -> WyRegistry.createEntityType(NormalBulletProjectile::new).func_220321_a(0.3F, 0.3F).func_206830_a("mineminenomi:normal_bullet"));
/* 25 */   public static final RegistryObject<EntityType<KairosekiBulletProjectile>> KAIROSEKI_BULLET = WyRegistry.registerEntityType("Kairoseki Bullet", () -> WyRegistry.createEntityType(KairosekiBulletProjectile::new).func_220321_a(0.3F, 0.3F).func_206830_a("mineminenomi:kairoseki_bullet"));
/* 26 */   public static final RegistryObject<EntityType<AxeDialProjectile>> AXE_DIAL_PROJECTILE = WyRegistry.registerEntityType("Axe Dial", () -> WyRegistry.createEntityType(AxeDialProjectile::new).func_220321_a(3.5F, 0.5F).func_206830_a("mineminenomi:axe_dial_projectile"));
/* 27 */   public static final RegistryObject<EntityType<MilkyDialProjectile>> MILKY_DIAL_PROJECTILE = WyRegistry.registerEntityType("Milky Dial", () -> WyRegistry.createEntityType(MilkyDialProjectile::new).func_220321_a(0.3F, 0.3F).func_206830_a("mineminenomi:milky_dial_projectile"));
/* 28 */   public static final RegistryObject<EntityType<PopGreenProjectile>> POP_GREEN = WyRegistry.registerEntityType("Pop Green", () -> WyRegistry.createEntityType(PopGreenProjectile::new).func_220321_a(0.3F, 0.3F).func_206830_a("mineminenomi:pop_green"));
/* 29 */   public static final RegistryObject<EntityType<KujaArrowProjectile>> KUJA_ARROW = WyRegistry.registerEntityType("Kuja Arrow", () -> WyRegistry.createEntityType(KujaArrowProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:kuja_arrow"));
/* 30 */   public static final RegistryObject<EntityType<EntityCloud>> CLOUD = WyRegistry.registerEntityType("Cloud", () -> WyRegistry.createEntityType(EntityCloud::new).func_206830_a("mineminenomi:cloud"));
/* 31 */   public static final RegistryObject<EntityType<CannonBallProjectile>> CANNON_BALL = WyRegistry.registerEntityType("Cannon Ball", () -> WyRegistry.createEntityType(CannonBallProjectile::new).func_206830_a("mineminenomi:cannon_ball"));
/* 32 */   public static final RegistryObject<EntityType<ShockwaveProjectile>> SHOCKWAVE = WyRegistry.registerEntityType("Shockwave", () -> WyRegistry.createEntityType(ShockwaveProjectile::new).func_206830_a("mineminenomi:shockwave"));
/* 33 */   public static final RegistryObject<EntityType<BambooPillarEntity>> BAMBOO_PILLAR = WyRegistry.registerEntityType("Bamboo Pillar", () -> WyRegistry.createEntityType(BambooPillarEntity::new).func_220321_a(0.3F, 0.3F).func_206830_a("mineminenomi:bamboo_pillar"));
/* 34 */   public static final RegistryObject<EntityType<BigBombEntity>> BIG_BOMB = WyRegistry.registerEntityType("Big Bomb", () -> WyRegistry.createEntityType(BigBombEntity::new).func_206830_a("mineminenomi:big_bomb"));
/* 35 */   public static final RegistryObject<EntityType<HookEntity>> HOOK = WyRegistry.registerEntityType("Hook", () -> WyRegistry.createEntityType(HookEntity::new).func_206830_a("mineminenomi:hook"));
/* 36 */   public static final RegistryObject<EntityType<TopEntity>> TOP = WyRegistry.registerEntityType("Top", () -> WyRegistry.createEntityType(TopEntity::new).func_220321_a(0.25F, 0.25F).func_206830_a("mineminenomi:top"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 41 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)NORMAL_BULLET.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#878787"));
/* 42 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)KAIROSEKI_BULLET.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#F3F3F3"));
/* 43 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)AXE_DIAL_PROJECTILE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CrescentModel())).setScale(1.0D, 1.5D, 1.0D).setColor("#69E3FF"));
/* 44 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MILKY_DIAL_PROJECTILE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.1D).setColor("#69E3FF"));
/* 45 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)POP_GREEN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#7ccc6a"));
/* 46 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)KUJA_ARROW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new ArrowModel())).setTexture("kujaarrow").setScale(1.25D));
/* 47 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CANNON_BALL.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(2.5D).setColor("#878787"));
/* 48 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SHOCKWAVE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/* 49 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BAMBOO_PILLAR.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#51bd2d").setScale(0.4D, 0.4D, 20.0D));
/* 50 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BIG_BOMB.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(5.5D).setColor("#272727"));
/* 51 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HOOK.get(), (IRenderFactory)new HookRenderer.Factory());
/* 52 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TOP.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new TopModel())).setTexture("top"));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\ExtraProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */