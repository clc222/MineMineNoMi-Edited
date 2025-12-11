/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.ArenaSkybox;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class AbilityProtectionClientEvents
/*    */ {
/* 23 */   public static final Map<String, ProtectedArea> CLIENT_AREAS = new HashMap<>();
/*    */   
/* 25 */   public static final ArenaSkybox SKYSPHERE = (new ArenaSkybox()).setTexture(true, new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/skyboxes/darkness.png") }).setAnimationSpeed(110000).setDetailLevel(25);
/* 26 */   public static final ArenaSkybox SKYSPHERE_OVERLAY = (new ArenaSkybox()).setTexture(true, new ResourceLocation[] { new ResourceLocation("mineminenomi", "textures/skyboxes/clouds.png") }).setAnimationSpeed(80000).setDetailLevel(16).setAlpha(0.5F);
/*    */   
/*    */   public static void renderTick(MatrixStack matrixStack, ActiveRenderInfo info) {
/* 29 */     Minecraft mc = Minecraft.func_71410_x();
/* 30 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 31 */     if (clientPlayerEntity == null) {
/*    */       return;
/*    */     }
/*    */     
/* 35 */     BufferBuilder buffer = Tessellator.func_178181_a().func_178180_c();
/*    */     
/* 37 */     if (WyHelper.isInChallengeDimension(((PlayerEntity)clientPlayerEntity).field_70170_p)) {
/* 38 */       matrixStack = new MatrixStack();
/* 39 */       matrixStack.func_227866_c_().func_227870_a_().func_226591_a_();
/* 40 */       matrixStack.func_227861_a_(-(info.func_216785_c()).field_72450_a, 0.0D, -(info.func_216785_c()).field_72449_c);
/*    */       
/* 42 */       int renderDistance = mc.field_71474_y.field_151451_c;
/*    */       
/* 44 */       int xzAreaSize = renderDistance * 25;
/* 45 */       SKYSPHERE.setRadius(xzAreaSize);
/* 46 */       SKYSPHERE_OVERLAY.setRadius((xzAreaSize - 5));
/*    */       
/* 48 */       matrixStack.func_227860_a_();
/* 49 */       SKYSPHERE.renderSphereInWorld(matrixStack, info, 0.0D, 0.0D, 0.0D);
/* 50 */       SKYSPHERE_OVERLAY.renderSphereInWorld(matrixStack, info, 0.0D, 0.0D, 0.0D);
/* 51 */       matrixStack.func_227865_b_();
/*    */     } else {
/*    */       
/* 54 */       for (ProtectedArea area : CLIENT_AREAS.values())
/* 55 */         RendererHelper.renderAbilityProtectionArea(matrixStack, info, buffer, area.getCenter().func_177958_n(), area.getCenter().func_177956_o(), area.getCenter().func_177952_p(), area.getSize(), area.getSize(), area.getSize()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityProtectionClientEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */