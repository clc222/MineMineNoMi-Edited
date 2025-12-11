/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.CrescentModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class RokushikiProjectiles {
/* 19 */   public static final RegistryObject<EntityType<RankyakuProjectile>> RANKYAKU = WyRegistry.registerEntityType("Rankyaku", () -> WyRegistry.createEntityType(RankyakuProjectile::new).func_220321_a(5.25F, 0.5F).func_206830_a("mineminenomi:rankyaku"));
/* 20 */   public static final RegistryObject<EntityType<RokuoganProjectile>> ROKUOGAN = WyRegistry.registerEntityType("Rokuogan", () -> WyRegistry.createEntityType(RokuoganProjectile::new).func_220321_a(3.0F, 3.0F).func_206830_a("mineminenomi:rokuogan"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 25 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)RANKYAKU.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CrescentModel())).setColor("#B1B1E1").setScale(1.0D, 1.5D, 1.0D).setRotation(0.0F, 0.0F, 90.0F));
/* 26 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ROKUOGAN.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\rokushiki\RokushikiProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */