/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.EntityMountEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class EntityEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/* 22 */     LivingEntity entity = event.getEntityLiving();
/*    */     
/* 24 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 28 */     IEntityStats entityProps = EntityStatsCapability.get(entity);
/*    */     
/* 30 */     entity.field_70702_br = entityProps.getLeftImpulse();
/* 31 */     entity.field_191988_bg = entityProps.getForwardImpulse();
/*    */     
/* 33 */     EffectsEvents.Common.launchedCollision(entity);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onMountEffects(EntityMountEvent event) {
/* 38 */     if (!(event.getWorldObj()).field_72995_K && event.getEntityMounting() instanceof LivingEntity) {
/* 39 */       LivingEntity living = (LivingEntity)event.getEntityMounting();
/* 40 */       if (event.isMounting()) {
/* 41 */         if (event.getEntityBeingMounted() instanceof xyz.pixelatedw.mineminenomi.entities.StrikerEntity) {
/* 42 */           WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(living, ModAnimations.RIDE_STRIKER, -1, true), (Entity)living);
/*    */         }
/* 44 */         else if (event.getEntityBeingMounted() instanceof xyz.pixelatedw.mineminenomi.entities.CannonEntity) {
/* 45 */           WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(living, ModAnimations.CANNON_HANDLING, -1, true), (Entity)living);
/*    */         }
/* 47 */         else if (event.getEntityBeingMounted() instanceof xyz.pixelatedw.mineminenomi.entities.UnicycleEntity) {
/* 48 */           WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation(living, ModAnimations.RIDE_UNICYCLE, -1, true), (Entity)living);
/*    */         }
/*    */       
/*    */       }
/* 52 */       else if (event.getEntityBeingMounted() instanceof xyz.pixelatedw.mineminenomi.entities.StrikerEntity) {
/* 53 */         WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(living, ModAnimations.RIDE_STRIKER), (Entity)living);
/*    */       }
/* 55 */       else if (event.getEntityBeingMounted() instanceof xyz.pixelatedw.mineminenomi.entities.CannonEntity) {
/* 56 */         WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(living, ModAnimations.CANNON_HANDLING), (Entity)living);
/*    */       }
/* 58 */       else if (event.getEntityBeingMounted() instanceof xyz.pixelatedw.mineminenomi.entities.UnicycleEntity) {
/* 59 */         WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation(living, ModAnimations.RIDE_UNICYCLE), (Entity)living);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\EntityEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */