/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.CrescentModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class SwordsmanProjectiles {
/* 18 */   public static final RegistryObject<EntityType<YakkodoriProjectile>> YAKKODORI = WyRegistry.registerEntityType("Yakkodori", () -> WyRegistry.createEntityType(YakkodoriProjectile::new).func_220321_a(0.5F, 4.0F).func_206830_a("mineminenomi:yakkodori"));
/* 19 */   public static final RegistryObject<EntityType<SanbyakurokujuPoundHoProjectile>> SANBYAKUROKUJU_POUND_HO = WyRegistry.registerEntityType("Sanbyakurokuju Pound Ho", () -> WyRegistry.createEntityType(SanbyakurokujuPoundHoProjectile::new).func_220321_a(3.0F, 0.5F).func_206830_a("mineminenomi:sanbyakurokuju_pound_ho"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 24 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)YAKKODORI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CrescentModel())).setColor("#5BD7FA"));
/* 25 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SANBYAKUROKUJU_POUND_HO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CrescentModel())).setColor("#5BD7FA").setRotation(0.0F, 0.0F, 90.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\swordsman\SwordsmanProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */