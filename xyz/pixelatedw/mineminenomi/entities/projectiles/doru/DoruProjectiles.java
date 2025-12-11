/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.WaxCloneRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class DoruProjectiles {
/* 23 */   public static final RegistryObject<EntityType<DoruDoruArtsMoriProjectile>> DORU_DORU_ARTS_MORI = WyRegistry.registerEntityType("Doru Doru Arts: Mori", () -> WyRegistry.createEntityType(DoruDoruArtsMoriProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:doru_doru_arts_mori"));
/* 24 */   public static final RegistryObject<EntityType<CandleLockProjectile>> CANDLE_LOCK = WyRegistry.registerEntityType("Candle Lock", () -> WyRegistry.createEntityType(CandleLockProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:candle_lock"));
/* 25 */   public static final RegistryObject<EntityType<ChampFightProjectile>> CHAMP_FIGHT = WyRegistry.registerEntityType("Champ Fight", () -> WyRegistry.createEntityType(ChampFightProjectile::new).func_220321_a(0.5F, 0.5F).func_206830_a("mineminenomi:champ_fight"));
/*    */   
/* 27 */   public static final RegistryObject<EntityType<WaxCloneEntity>> WAX_CLONE = WyRegistry.registerEntityType("Wax Clone", () -> WyRegistry.createEntityType(WaxCloneEntity::new).func_206830_a("mineminenomi:wax_clone"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 33 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)DORU_DORU_ARTS_MORI.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setScale(2.0D).setTexture("dorudoruartsmori"));
/* 34 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CANDLE_LOCK.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)CHAMP_FIGHT.get(), (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(2.0D).setColor("#C3C3C3"));
/*    */     
/* 37 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)WAX_CLONE.get(), (IRenderFactory)new WaxCloneRenderer.Factory());
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
/* 43 */     event.put((EntityType)WAX_CLONE.get(), WaxCloneEntity.createAttributes().func_233813_a_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\DoruProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */