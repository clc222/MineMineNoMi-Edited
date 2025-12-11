/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.SetCameraOffsetEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.SetCameraZoomEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class CameraEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onCameraZoom(SetCameraZoomEvent event) {
/* 22 */     ClientPlayerEntity player = event.getPlayer();
/*    */     
/* 24 */     if (player == null) {
/*    */       return;
/*    */     }
/*    */     
/* 28 */     if (player.func_184187_bx() != null && player.func_184187_bx() instanceof xyz.pixelatedw.mineminenomi.entities.StrikerEntity) {
/* 29 */       event.setZoom(7.0D);
/* 30 */       event.setResult(Event.Result.ALLOW);
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 35 */     MorphInfo info = null;
/*    */     
/* 37 */     if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/* 38 */       info = MorphHelper.getZoanInfo((LivingEntity)player);
/* 39 */       if (info != null) {
/* 40 */         double zoom = info.getCameraZoom((LivingEntity)player);
/* 41 */         if (zoom != 0.0D) {
/* 42 */           event.setZoom(zoom);
/* 43 */           event.setResult(Event.Result.ALLOW);
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onCameraOffset(SetCameraOffsetEvent event) {
/* 52 */     ClientPlayerEntity player = event.getPlayer();
/*    */     
/* 54 */     if (player == null) {
/*    */       return;
/*    */     }
/*    */     
/* 58 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 59 */     MorphInfo info = null;
/*    */     
/* 61 */     if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/* 62 */       info = MorphHelper.getZoanInfo((LivingEntity)player);
/* 63 */       if (info != null) {
/* 64 */         double height = info.getCameraHeight((LivingEntity)player);
/* 65 */         if (height != 0.0D) {
/* 66 */           event.setCameraPosition(event.getCameraPosition().func_72441_c(0.0D, height, 0.0D));
/* 67 */           event.setResult(Event.Result.ALLOW);
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\CameraEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */