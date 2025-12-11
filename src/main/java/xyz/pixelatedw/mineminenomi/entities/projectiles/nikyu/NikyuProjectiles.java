/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.ChargingUrsusShockRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class NikyuProjectiles {
/* 20 */   public static final RegistryObject<EntityType<PadHoProjectile>> PAD_HO = WyRegistry.registerEntityType("Pad Ho", () -> WyRegistry.createEntityType(PadHoProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:pad_ho"));
/* 21 */   public static final RegistryObject<EntityType<UrsusShockProjectile>> URSUS_SHOCK = WyRegistry.registerEntityType("Ursus Shock", () -> WyRegistry.createEntityType(UrsusShockProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:ursus_shock"));
/*    */   
/* 23 */   public static final RegistryObject<EntityType<ChargingUrsusShockEntity>> CHARGING_URSUS_SHOCK = WyRegistry.registerEntityType("Charging Ursus Shock", () -> WyRegistry.createEntityType(ChargingUrsusShockEntity::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:charging_ursus_shock"));
/* 24 */   public static final RegistryObject<EntityType<PainEntity>> PAIN = WyRegistry.registerEntityType("Pain", () -> WyRegistry.createEntityType(PainEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:pain"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)PAD_HO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PawModel())).setColor("#F8F8FF33").setScale(1.0D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)URSUS_SHOCK.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PawModel())).setColor("#F8F8FF33").setScale(0.6D));
/*    */     
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CHARGING_URSUS_SHOCK.get(), (IRenderFactory)new ChargingUrsusShockRenderer.Factory());
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)PAIN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PawModel())).setColor("#FFAAAA33").setScale(1.6D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\NikyuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */