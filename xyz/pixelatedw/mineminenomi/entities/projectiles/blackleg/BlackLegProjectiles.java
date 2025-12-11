/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityLegModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BlackLegProjectiles {
/* 19 */   public static final RegistryObject<EntityType<ExtraHachisProjectile>> EXTRA_HACHIS = WyRegistry.registerEntityType("Extra Hachis", () -> WyRegistry.createEntityType(ExtraHachisProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:extra_hachis"));
/*    */   
/* 21 */   public static final RegistryObject<EntityType<PoeleAFrireProjectile>> POELE_A_FRIRE = WyRegistry.registerEntityType("Poele a Frire", () -> WyRegistry.createEntityType(PoeleAFrireProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:poele_a_frire"));
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 26 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)EXTRA_HACHIS.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityLegModel())).setStretchScale(2.0D, 2.0D, 10.0D).setPlayerTexture().setTranslate(0.0D, -0.5D, 0.0D));
/* 27 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)POELE_A_FRIRE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityLegModel())).setScale(2.0D, 2.0D, 2.0D).setPlayerTexture().setTranslate(0.0D, -0.75D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\blackleg\BlackLegProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */