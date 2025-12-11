/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class SniperProjectiles {
/* 20 */   public static final RegistryObject<EntityType<HiNoToriBoshiProjectile>> HI_NO_TORI_BOSHI = WyRegistry.registerEntityType("Hi no Tori Boshi", () -> WyRegistry.createEntityType(HiNoToriBoshiProjectile::new).func_220321_a(1.25F, 1.25F).func_206830_a("mineminenomi:hi_no_tori_boshi"));
/* 21 */   public static final RegistryObject<EntityType<KemuriBoshiProjectile>> KEMURI_BOSHI = WyRegistry.registerEntityType("Kemuri Boshi", () -> WyRegistry.createEntityType(KemuriBoshiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:kemuri_boshi"));
/* 22 */   public static final RegistryObject<EntityType<RenpatsuNamariBoshiProjectile>> RENPATSU_NAMARI_BOSHI = WyRegistry.registerEntityType("Renpatsu Namari Boshi", () -> WyRegistry.createEntityType(RenpatsuNamariBoshiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:renpatsu_namari_boshi"));
/* 23 */   public static final RegistryObject<EntityType<SakuretsuSabotenBoshiProjectile>> SAKURETSU_SABOTEN_BOSHI = WyRegistry.registerEntityType("Sakuretsu Saboten Boshi", () -> WyRegistry.createEntityType(SakuretsuSabotenBoshiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:sakuretsu_saboten_boshi"));
/* 24 */   public static final RegistryObject<EntityType<TetsuBoshiProjectile>> TETSU_BOSHI = WyRegistry.registerEntityType("Tetsu Boshi", () -> WyRegistry.createEntityType(TetsuBoshiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:tetsu_boshi"));
/* 25 */   public static final RegistryObject<EntityType<TokuyoAburaBoshiProjectile>> TOKUYO_ABURA_BOSHI = WyRegistry.registerEntityType("Tokuyo Abura Boshi", () -> WyRegistry.createEntityType(TokuyoAburaBoshiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:tokuya_abura_boshi"));
/* 26 */   public static final RegistryObject<EntityType<NemuriBoshiProjectile>> NEMURI_BOSHI = WyRegistry.registerEntityType("Nemuri Boshi", () -> WyRegistry.createEntityType(NemuriBoshiProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:nemuri_boshi"));
/*    */ 
/*    */   
/* 29 */   public static final RegistryObject<EntityType<HiNoToriBoshiProjectile>> KAEN_BOSHI = WyRegistry.registerEntityType("Kaen Boshi", () -> WyRegistry.createEntityType(HiNoToriBoshiProjectile::new).func_220321_a(0.4F, 0.4F).func_206830_a("mineminenomi:kaen_boshi"));
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HI_NO_TORI_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PheasantModel())).setScale(2.0D).setColor("#63110E"));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)KEMURI_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)RENPATSU_NAMARI_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
/* 39 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SAKURETSU_SABOTEN_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#437C17"));
/* 40 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TETSU_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
/* 41 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TOKUYO_ABURA_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#C0C0C0"));
/* 42 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)NEMURI_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#FF88FF"));
/*    */     
/* 44 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)KAEN_BOSHI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(2.0D).setColor("#63110E"));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\SniperProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */