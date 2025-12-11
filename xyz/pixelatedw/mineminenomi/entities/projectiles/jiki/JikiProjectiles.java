/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.PunkGibsonModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EmptyRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.jiki.GenocideRaidEffectRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.morphs.PunkGibsonLayer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class JikiProjectiles {
/* 25 */   public static final RegistryObject<EntityType<PunkGibsonProjectile>> PUNK_GIBSON = WyRegistry.registerEntityType("Punk Gibson", () -> WyRegistry.createEntityType(PunkGibsonProjectile::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:punk_gibson"));
/* 26 */   public static final RegistryObject<EntityType<PunkPistolProjectile>> PUNK_PISTOL = WyRegistry.registerEntityType("Punk Pistol", () -> WyRegistry.createEntityType(PunkPistolProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:punk_pistol"));
/* 27 */   public static final RegistryObject<EntityType<DamnedPunkProjectile>> DAMNED_PUNK = WyRegistry.registerEntityType("Damned Punk", () -> WyRegistry.createEntityType(DamnedPunkProjectile::new).func_220321_a(1.0F, 1.0F).func_206830_a("mineminenomi:damned_punk"));
/* 28 */   public static final RegistryObject<EntityType<GenocideRaidProjectile>> GENOCIDE_RAID = WyRegistry.registerEntityType("Genocide Raid", () -> WyRegistry.createEntityType(GenocideRaidProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:genocide_raid"));
/* 29 */   public static final RegistryObject<EntityType<GenocideRaidEffectEntity>> GENOCIDE_RAID_EFFECT = WyRegistry.registerEntityType("Genocide Raid Effect", () -> WyRegistry.createEntityType(GenocideRaidEffectEntity::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:genocide_raid_effect"));
/* 30 */   public static final RegistryObject<EntityType<PunkCrossProjectile>> PUNK_CROSS = WyRegistry.registerEntityType("Punk Cross", () -> WyRegistry.createEntityType(PunkCrossProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:punk_cross"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)PUNK_GIBSON.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PunkGibsonModel())).setScale(2.0D, 2.0D, 2.0D).setTexture(PunkGibsonLayer.TEXTURE));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)PUNK_PISTOL.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setColor("#2b2930"));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DAMNED_PUNK.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#300061").setScale(1.0D, 1.0D, 3.0D));
/* 39 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GENOCIDE_RAID.get(), (IRenderFactory)new EmptyRenderer.Factory());
/* 40 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)GENOCIDE_RAID_EFFECT.get(), (IRenderFactory)new GenocideRaidEffectRenderer.Factory());
/* 41 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)PUNK_CROSS.get(), (IRenderFactory)new EmptyRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\JikiProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */