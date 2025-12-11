/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BrickBatModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PillarModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.DoppelmanRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.NightmareSoldierRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class KageProjectiles {
/* 25 */   public static final RegistryObject<EntityType<BrickBatProjectile>> BRICK_BAT = WyRegistry.registerEntityType("Brick Bat", () -> WyRegistry.createEntityType(BrickBatProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:brick_bat"));
/* 26 */   public static final RegistryObject<EntityType<BlackBoxProjectile>> BLACK_BOX = WyRegistry.registerEntityType("Brick Box", () -> WyRegistry.createEntityType(BlackBoxProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:black_box"));
/* 27 */   public static final RegistryObject<EntityType<TsunoTokagePillarEntity>> TSUNO_TOKAGE = WyRegistry.registerEntityType("Tsuno Tokage", () -> WyRegistry.createEntityType(TsunoTokagePillarEntity::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:tsuno_tokage"));
/*    */   
/* 29 */   public static final RegistryObject<EntityType<DoppelmanEntity>> DOPPELMAN = WyRegistry.registerEntityType("Doppelman", () -> WyRegistry.createEntityType(DoppelmanEntity::new).func_206830_a("mineminenomi:doppelman"));
/* 30 */   public static final RegistryObject<EntityType<NightmareSoldierEntity>> NIGHTMARE_SOLDIER = WyRegistry.registerEntityType("Nightmare Soldier", () -> WyRegistry.createEntityType(NightmareSoldierEntity::new).func_206830_a("mineminenomi:nightmare_soldier"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BRICK_BAT.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new BrickBatModel())).setTexture("brickbat"));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)BLACK_BOX.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new BrickBatModel())).setTexture("brickbat"));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)TSUNO_TOKAGE.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PillarModel())).setColor("#431C4b").setScale(5.0D, 5.0D, 5.0D));
/*    */     
/* 40 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DOPPELMAN.get(), (IRenderFactory)new DoppelmanRenderer.Factory());
/* 41 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)NIGHTMARE_SOLDIER.get(), (IRenderFactory)new NightmareSoldierRenderer.Factory());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
/* 47 */     event.put((EntityType)DOPPELMAN.get(), DoppelmanEntity.createAttributes().func_233813_a_());
/* 48 */     event.put((EntityType)NIGHTMARE_SOLDIER.get(), NightmareSoldierEntity.createAttributes().func_233813_a_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\KageProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */