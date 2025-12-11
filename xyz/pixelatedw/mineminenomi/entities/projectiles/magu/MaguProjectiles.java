/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MeigoModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class MaguProjectiles {
/* 20 */   public static final RegistryObject<EntityType<DaiFunkaProjectile>> DAI_FUNKA = WyRegistry.registerEntityType("Dai Funka", () -> WyRegistry.createEntityType(DaiFunkaProjectile::new).func_220321_a(4.5F, 4.5F).func_206830_a("mineminenomi:dai_funka"));
/* 21 */   public static final RegistryObject<EntityType<RyuseiKazanProjectile>> RYUSEI_KAZAN = WyRegistry.registerEntityType("Ryusei Kazan", () -> WyRegistry.createEntityType(RyuseiKazanProjectile::new).func_220321_a(4.5F, 4.5F).func_206830_a("mineminenomi:ryusei_kazan"));
/* 22 */   public static final RegistryObject<EntityType<MeigoProjectile>> MEIGO = WyRegistry.registerEntityType("Meigo", () -> WyRegistry.createEntityType(MeigoProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:meigo"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DAI_FUNKA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("daifunka").setScale(20.0D).setTranslate(0.0D, 0.125D, 0.0D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)RYUSEI_KAZAN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("daifunka").setScale(15.0D).setTranslate(0.0D, 0.125D, 0.0D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MEIGO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new MeigoModel())).setTexture("meigo").setScale(4.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\magu\MaguProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */