/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ZushiProjectiles {
/* 19 */   public static final RegistryObject<EntityType<SagariNoRyuseiProjectile>> SAGARI_NO_RYUSEI = WyRegistry.registerEntityType("Sagari no Ryusei", () -> WyRegistry.createEntityType(SagariNoRyuseiProjectile::new).func_220321_a(8.0F, 8.0F).func_206830_a("mineminenomi:sagari_no_ryusei"));
/* 20 */   public static final RegistryObject<EntityType<MokoProjectile>> MOKO = WyRegistry.registerEntityType("Moko", () -> WyRegistry.createEntityType(MokoProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:moko"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 26 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SAGARI_NO_RYUSEI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#51585B").setScale(30.0D));
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MOKO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zushi\ZushiProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */