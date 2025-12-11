/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MiniHollowModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NegativeHollowModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TokuHollowModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class HoroProjectiles {
/* 21 */   public static final RegistryObject<EntityType<NegativeHollowProjectile>> NEGATIVE_HOLLOW = WyRegistry.registerEntityType("Negative Hollow", () -> WyRegistry.createEntityType(NegativeHollowProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:negative_hollow"));
/* 22 */   public static final RegistryObject<EntityType<MiniHollowProjectile>> MINI_HOLLOW = WyRegistry.registerEntityType("Mini Hollow", () -> WyRegistry.createEntityType(MiniHollowProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:mini_hollow"));
/* 23 */   public static final RegistryObject<EntityType<TokuHollowProjectile>> TOKU_HOLLOW = WyRegistry.registerEntityType("Toku Hollow", () -> WyRegistry.createEntityType(TokuHollowProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:toku_hollow"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 29 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)NEGATIVE_HOLLOW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new NegativeHollowModel())).setTexture("negativehollow").setAlpha(120).setScale(2.0D));
/* 30 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)MINI_HOLLOW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new MiniHollowModel())).setColor("#F8F8FF").setAlpha(120));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TOKU_HOLLOW.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new TokuHollowModel())).setTexture("tokuhollow").setAlpha(120).setScale(4.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\HoroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */