/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.widget.Widget;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.ClientBossExtraInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class BossUIEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onGameOverlayRender(RenderGameOverlayEvent.BossInfo event) {
/* 27 */     if (event.getType() != RenderGameOverlayEvent.ElementType.BOSSINFO) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     Minecraft mc = Minecraft.func_71410_x();
/* 32 */     ClientPlayerEntity player = mc.field_71439_g;
/* 33 */     IEntityStats playerData = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 35 */     int posX = event.getX();
/* 36 */     int posY = event.getY();
/* 37 */     int split = 19;
/*    */     
/* 39 */     event.getMatrixStack().func_227860_a_();
/* 40 */     RenderSystem.enableBlend();
/* 41 */     mc.func_110434_K().func_110577_a(Widget.field_230665_h_);
/* 42 */     for (Map.Entry<UUID, ClientBossExtraInfo> entry : (Iterable<Map.Entry<UUID, ClientBossExtraInfo>>)playerData.getExtraBossInfo().entrySet()) {
/* 43 */       if (event.getBossInfo().func_186737_d().equals(entry.getKey())) {
/* 44 */         int bars = ((ClientBossExtraInfo)entry.getValue()).getTotalBars();
/* 45 */         int filledBars = ((ClientBossExtraInfo)entry.getValue()).getActiveBars();
/*    */         int i;
/* 47 */         for (i = 0; i < bars; i++) {
/* 48 */           int posX2 = posX + 177 - i % 19 * 10;
/* 49 */           int posY2 = posY + 6;
/* 50 */           if (i >= 19) {
/* 51 */             posY2 += 10;
/*    */           }
/* 53 */           GuiUtils.drawTexturedModalRect(event.getMatrixStack(), posX2, posY2, 16, 0, 9, 9, 0.0F);
/*    */         } 
/*    */         
/* 56 */         for (i = 0; i < filledBars; i++) {
/* 57 */           int u = 36;
/* 58 */           int posX2 = posX + 177 - i % 19 * 10;
/* 59 */           int posY2 = posY + 6;
/* 60 */           if (i >= 19) {
/* 61 */             posY2 += 10;
/*    */           }
/* 63 */           GuiUtils.drawTexturedModalRect(event.getMatrixStack(), posX2, posY2, 16 + u, 0, 9, 9, 0.0F);
/*    */         } 
/*    */         
/* 66 */         if (bars / 19 > 0) {
/* 67 */           event.setIncrement(18 * bars / 19);
/*    */         }
/*    */       } 
/*    */     } 
/* 71 */     event.getMatrixStack().func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\BossUIEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */