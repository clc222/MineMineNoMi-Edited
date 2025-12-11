/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityLegModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EmptyRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.hana.CampoDeFloresRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class HanaProjectiles {
/* 21 */   public static final RegistryObject<EntityType<HanaHandsEntity>> HANDS = WyRegistry.registerEntityType("Hands", () -> WyRegistry.createEntityType(HanaHandsEntity::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:hands"));
/* 22 */   public static final RegistryObject<EntityType<HanaFeetEntity>> FEET = WyRegistry.registerEntityType("Feet", () -> WyRegistry.createEntityType(HanaFeetEntity::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:feet"));
/* 23 */   public static final RegistryObject<EntityType<HanaGenericEntity>> GENERIC_HANA = WyRegistry.registerEntityType("Hana Projectile", () -> WyRegistry.createEntityType(HanaGenericEntity::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:generic_hana"));
/* 24 */   public static final RegistryObject<EntityType<CampoDeFloresEntity>> CAMPO_DE_FLORES = WyRegistry.registerEntityType("Campo de Flores", () -> WyRegistry.createEntityType(CampoDeFloresEntity::new).func_220321_a(1.0F, 0.5F).func_206830_a("mineminenomi:campo_de_flores"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)HANDS.get(), (IRenderFactory)new EmptyRenderer.Factory());
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)FEET.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityLegModel())).setPlayerTexture().setScale(8.0D));
/* 32 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GENERIC_HANA.get(), (IRenderFactory)new EmptyRenderer.Factory());
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CAMPO_DE_FLORES.get(), (IRenderFactory)new CampoDeFloresRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */