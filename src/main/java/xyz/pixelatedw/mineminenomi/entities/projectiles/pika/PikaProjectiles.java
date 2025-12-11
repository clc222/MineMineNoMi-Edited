/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class PikaProjectiles {
/* 20 */   public static final RegistryObject<EntityType<AmaterasuProjectile>> AMATERASU = WyRegistry.registerEntityType("Amaterasu", () -> WyRegistry.createEntityType(AmaterasuProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:amaterasu"));
/* 21 */   public static final RegistryObject<EntityType<YasakaniNoMagatamaProjectile>> YASAKANI_NO_MAGATAMA = WyRegistry.registerEntityType("Yasakani no Magatama", () -> WyRegistry.createEntityType(YasakaniNoMagatamaProjectile::new).func_220321_a(0.2F, 0.2F).func_206830_a("mineminenomi:yasakani_no_magatama"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 26 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)AMATERASU.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#FFE51A").setScale(1.0D, 1.0D, 3.0D));
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)YASAKANI_NO_MAGATAMA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(1.0F, 0.9F, 0.1F, 1.0F).setScale(0.5D, 0.5D, 0.5D).setGlowing());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pika\PikaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */