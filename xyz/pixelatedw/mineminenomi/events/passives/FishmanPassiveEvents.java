/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.event.entity.living.PotionEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class FishmanPassiveEvents {
/*    */   @EventBusSubscriber(modid = "mineminenomi")
/*    */   public static class CommonEvents {
/*    */     @SubscribeEvent
/*    */     public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
/* 30 */       PlayerEntity player = event.getPlayer();
/* 31 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/* 32 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*    */       
/* 34 */       if (!statsProps.isFishman()) {
/*    */         return;
/*    */       }
/*    */       
/* 38 */       float speed = event.getOriginalSpeed();
/* 39 */       if (AbilityHelper.isAffectedByWater((LivingEntity)player) && (!devilFruitProps.hasAnyDevilFruit() || event.getEntityLiving().func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get()))) {
/* 40 */         if (!player.func_233570_aj_()) {
/* 41 */           speed *= 5.0F;
/*    */         }
/* 43 */         speed *= 5.0F;
/* 44 */         event.setNewSpeed(speed);
/*    */       } 
/*    */     }
/*    */     
/*    */     @SubscribeEvent
/*    */     public static void onPotionEvent(PotionEvent.PotionApplicableEvent event) {
/* 50 */       if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*    */         return;
/*    */       }
/*    */       
/* 54 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 55 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 57 */       if (!statsProps.isFishman()) {
/*    */         return;
/*    */       }
/*    */       
/* 61 */       if (event.getPotionEffect().func_188419_a().equals(Effects.field_206827_D)) {
/* 62 */         event.setResult(Event.Result.DENY);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */   public static class ClientEvents
/*    */   {
/*    */     @SubscribeEvent
/*    */     public static void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
/* 72 */       Minecraft mc = Minecraft.func_71410_x();
/* 73 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 74 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*    */       
/* 76 */       if (event.getType() == RenderGameOverlayEvent.ElementType.AIR && props.isFishman() && clientPlayerEntity.func_70090_H()) {
/* 77 */         event.setCanceled(true);
/*    */       }
/*    */     }
/*    */     
/*    */     @SubscribeEvent
/*    */     public static void onEntityInWater(EntityViewRenderEvent.FogDensity event) {
/* 83 */       Minecraft mc = Minecraft.func_71410_x();
/*    */       
/* 85 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 86 */       IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*    */       
/* 88 */       if (!statsProps.isFishman()) {
/*    */         return;
/*    */       }
/*    */       
/* 92 */       if (clientPlayerEntity.func_208600_a((ITag)FluidTags.field_206959_a)) {
/* 93 */         event.setCanceled(true);
/* 94 */         event.setDensity(0.005F);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\FishmanPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */