/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.BaraFestivalRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BaraProjectiles {
/* 20 */   public static final RegistryObject<EntityType<BaraBaraHoProjectile>> BARA_BARA_HO = WyRegistry.registerEntityType("Bara Bara Ho", () -> WyRegistry.createEntityType(BaraBaraHoProjectile::new).func_220321_a(1.2F, 1.2F).func_206830_a("mineminenomi:bara_bara_ho"));
/* 21 */   public static final RegistryObject<EntityType<BaraBaraHoProjectile>> DAI_CIRCUS = WyRegistry.registerEntityType("Dai Circus", () -> WyRegistry.createEntityType(DaiCircusProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:dai_circus"));
/* 22 */   public static final RegistryObject<EntityType<BaraFestivalEntity>> BARA_FESTIVAL = WyRegistry.registerEntityType("Bara Festival", () -> WyRegistry.createEntityType(BaraFestivalEntity::new).func_220321_a(0.1F, 0.1F).func_206830_a("mineminenomi:bara_festival"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BARA_BARA_HO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setPlayerTexture().setScale(1.5D));
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DAI_CIRCUS.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setPlayerTexture().setScale(1.5D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BARA_FESTIVAL.get(), (IRenderFactory)new BaraFestivalRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\BaraProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */