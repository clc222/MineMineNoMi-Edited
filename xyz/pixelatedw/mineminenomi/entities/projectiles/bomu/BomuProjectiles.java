/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BomuProjectiles {
/* 20 */   public static final RegistryObject<EntityType<NoseFancyCannonProjectile>> NOSE_FANCY_CANNON = WyRegistry.registerEntityType("Nose Fancy Cannon", () -> WyRegistry.createEntityType(NoseFancyCannonProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:nose_fancy_cannon"));
/* 21 */   public static final RegistryObject<EntityType<BreezeBreathBombProjectile>> BREEZE_BREATH_BOMB = WyRegistry.registerEntityType("Breeze Breath Bomb", () -> WyRegistry.createEntityType(BreezeBreathBombProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:breeze_breath_bomb"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)NOSE_FANCY_CANNON.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("3D2B1F").setScale(0.4D, 0.4D, 0.4D));
/* 28 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BREEZE_BREATH_BOMB.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("3D2B1F").setScale(1.0D, 1.0D, 1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bomu\BomuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */