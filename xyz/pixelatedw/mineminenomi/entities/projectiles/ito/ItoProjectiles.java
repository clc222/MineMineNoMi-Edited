/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.BlackKnightRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ItoProjectiles {
/* 24 */   public static final RegistryObject<EntityType<OverheatProjectile>> OVERHEAT = WyRegistry.registerEntityType("Overheat", () -> WyRegistry.createEntityType(OverheatProjectile::new).func_220321_a(2.0F, 2.0F).func_206830_a("mineminenomi:overheat"));
/* 25 */   public static final RegistryObject<EntityType<TamaitoProjectile>> TAMAITO = WyRegistry.registerEntityType("Tamaito", () -> WyRegistry.createEntityType(TamaitoProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:tamaito"));
/* 26 */   public static final RegistryObject<EntityType<StringPillarProjectile>> STRING_PILLAR = WyRegistry.registerEntityType("String Pillar", () -> WyRegistry.createEntityType(StringPillarProjectile::new).func_220321_a(1.0F, 10.0F).func_206830_a("mineminenomi:string_pillar"));
/*    */   
/* 28 */   public static final RegistryObject<EntityType<BlackKnightEntity>> BLACK_KNIGHT = WyRegistry.registerEntityType("Black Knight", () -> WyRegistry.createEntityType(BlackKnightEntity::new).func_206830_a("mineminenomi:black_knight"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)OVERHEAT.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.45D, 1.45D, 1.0D).setColor("#f77c25").setScale(1.0D, 1.0D, 5.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TAMAITO.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#dee1e5").setScale(0.5D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)STRING_PILLAR.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#dee1e5").setScale(0.5D, 0.5D, 20.0D));
/*    */     
/* 38 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BLACK_KNIGHT.get(), (IRenderFactory)new BlackKnightRenderer.Factory());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
/* 44 */     event.put((EntityType)BLACK_KNIGHT.get(), BlackKnightEntity.createAttributes().func_233813_a_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ito\ItoProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */