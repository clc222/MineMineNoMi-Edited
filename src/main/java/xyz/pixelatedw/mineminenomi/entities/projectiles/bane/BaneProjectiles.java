/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.StretchingProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BaneProjectiles {
/* 19 */   public static final RegistryObject<EntityType<SpringDeathKnockProjectile>> SPRING_DEATH_KNOCK = WyRegistry.registerEntityType("Spring Death Knock", () -> WyRegistry.createEntityType(SpringDeathKnockProjectile::new).func_220321_a(1.5F, 1.5F).func_206830_a("mineminenomi:spring_death_knock"));
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 25 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)SPRING_DEATH_KNOCK.get(), (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setColor("#A8A8A8"));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bane\BaneProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */