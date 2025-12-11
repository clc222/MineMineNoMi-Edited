/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.electro;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ElectroProjectiles {
/* 18 */   public static final RegistryObject<EntityType<ElectricalLunaProjectile>> ELECTRICAL_LUNA = WyRegistry.registerEntityType("Electrical Luna", () -> WyRegistry.createEntityType(ElectricalLunaProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:electrical_luna"));
/* 19 */   public static final RegistryObject<EntityType<ElectricalShowerProjectile>> ELECTRICAL_SHOWER = WyRegistry.registerEntityType("Electrical Shower", () -> WyRegistry.createEntityType(ElectricalShowerProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:electrical_shower"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 24 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ELECTRICAL_LUNA.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/* 25 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ELECTRICAL_SHOWER.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\electro\ElectroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */