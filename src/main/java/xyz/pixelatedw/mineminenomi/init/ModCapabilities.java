/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*    */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.animation.AnimationDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.curios.CuriosIntegration;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ModCapabilities
/*    */ {
/*    */   public static void init() {
/* 39 */     GCDCapability.register();
/* 40 */     AbilityDataCapability.register();
/* 41 */     QuestDataCapability.register();
/* 42 */     DevilFruitCapability.register();
/* 43 */     HakiDataCapability.register();
/* 44 */     EntityStatsCapability.register();
/* 45 */     ChallengesDataCapability.register();
/* 46 */     KairosekiCoatingCapability.register();
/* 47 */     ProjectileExtrasCapability.register();
/* 48 */     AnimationDataCapability.register();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
/* 53 */     if (ModMain.hasCuriosInstalled()) {
/* 54 */       CuriosIntegration.setupCurioCapabilities(event);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
/* 60 */     if (event.getObject() == null) {
/*    */       return;
/*    */     }
/*    */     
/* 64 */     if (event.getObject() instanceof net.minecraft.entity.player.PlayerEntity) {
/* 65 */       event.addCapability(new ResourceLocation("mineminenomi", "quest_data"), (ICapabilityProvider)new QuestDataProvider());
/* 66 */       event.addCapability(new ResourceLocation("mineminenomi", "challenges"), (ICapabilityProvider)new ChallengesDataProvider());
/*    */     } 
/*    */     
/* 69 */     if (event.getObject() instanceof net.minecraft.entity.LivingEntity) {
/* 70 */       event.addCapability(new ResourceLocation("mineminenomi", "animation_data"), (ICapabilityProvider)new AnimationDataProvider());
/* 71 */       event.addCapability(new ResourceLocation("mineminenomi", "gcd"), (ICapabilityProvider)new GCDProvider());
/* 72 */       event.addCapability(new ResourceLocation("mineminenomi", "ability_data"), (ICapabilityProvider)new AbilityDataProvider());
/* 73 */       event.addCapability(new ResourceLocation("mineminenomi", "devil_fruit"), (ICapabilityProvider)new DevilFruitProvider());
/* 74 */       event.addCapability(new ResourceLocation("mineminenomi", "haki_data"), (ICapabilityProvider)new HakiDataProvider());
/* 75 */       event.addCapability(new ResourceLocation("mineminenomi", "entity_stats"), (ICapabilityProvider)new EntityStatsProvider());
/*    */     } 
/*    */     
/* 78 */     if (event.getObject() instanceof net.minecraft.entity.projectile.ProjectileEntity) {
/* 79 */       event.addCapability(new ResourceLocation("mineminenomi", "extras"), (ICapabilityProvider)new ProjectileExtrasProvider());
/*    */     }
/*    */     
/* 82 */     if (event.getObject() instanceof net.minecraft.entity.item.BoatEntity)
/* 83 */       event.addCapability(new ResourceLocation("mineminenomi", "kairoseki_coating"), (ICapabilityProvider)new KairosekiCoatingProvider()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModCapabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */