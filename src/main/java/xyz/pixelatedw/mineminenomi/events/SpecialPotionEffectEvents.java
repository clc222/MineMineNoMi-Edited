/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IBlockOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class SpecialPotionEffectEvents
/*     */ {
/*     */   public static void renderScreenEffects(PlayerEntity player, MatrixStack matrixStack, ActiveRenderInfo activeRenderInfo, float partialTicks) {
/*  31 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/*  33 */     if (!mc.field_71474_y.func_243230_g().func_243192_a()) {
/*     */       return;
/*     */     }
/*  36 */     Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
/*     */     
/*  38 */     Iterator<EffectInstance> iterator = player.func_70651_bq().iterator();
/*  39 */     while (iterator.hasNext()) {
/*     */       
/*  41 */       EffectInstance instance = iterator.next();
/*     */       
/*  43 */       if (instance.func_188419_a() instanceof IColorOverlayEffect) {
/*     */         
/*  45 */         float[] colors = ((IColorOverlayEffect)instance.func_188419_a()).getViewOverlayColor(instance.func_76459_b(), instance.func_76458_c());
/*  46 */         if (colors != null) {
/*     */           
/*  48 */           matrixStack.func_227860_a_();
/*  49 */           RenderSystem.enableBlend();
/*  50 */           RenderSystem.disableTexture();
/*  51 */           BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/*  52 */           bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/*  53 */           bufferbuilder.func_227888_a_(matrix4f, -1.0F, -1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_181675_d();
/*  54 */           bufferbuilder.func_227888_a_(matrix4f, 1.0F, -1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_181675_d();
/*  55 */           bufferbuilder.func_227888_a_(matrix4f, 1.0F, 1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_181675_d();
/*  56 */           bufferbuilder.func_227888_a_(matrix4f, -1.0F, 1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_181675_d();
/*  57 */           Tessellator.func_178181_a().func_78381_a();
/*  58 */           RenderSystem.enableTexture();
/*  59 */           RenderSystem.disableBlend();
/*  60 */           matrixStack.func_227865_b_();
/*     */         } 
/*     */       } 
/*     */       
/*  64 */       if (instance.func_188419_a() instanceof IBlockOverlayEffect) {
/*     */         
/*  66 */         Block overlay = ((IBlockOverlayEffect)instance.func_188419_a()).getBlockOverlay(instance.func_76459_b(), instance.func_76458_c());
/*  67 */         if (overlay != null) {
/*     */           
/*  69 */           float[] colors = ((IBlockOverlayEffect)instance.func_188419_a()).getOverlayColor();
/*  70 */           matrixStack.func_227860_a_();
/*  71 */           TextureAtlasSprite atlas = mc.func_175602_ab().func_175023_a().getTexture(overlay.func_176223_P(), (World)mc.field_71441_e, player.func_233580_cy_());
/*  72 */           mc.func_110434_K().func_110577_a(atlas.func_229241_m_().func_229223_g_());
/*  73 */           BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/*  74 */           RenderSystem.enableBlend();
/*  75 */           RenderSystem.defaultBlendFunc();
/*  76 */           float f6 = atlas.func_94209_e();
/*  77 */           float f7 = atlas.func_94212_f();
/*  78 */           float f8 = atlas.func_94206_g();
/*  79 */           float f9 = atlas.func_94210_h();
/*  80 */           bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/*  81 */           bufferbuilder.func_227888_a_(matrix4f, -1.0F, -1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(f7, f9).func_181675_d();
/*  82 */           bufferbuilder.func_227888_a_(matrix4f, 1.0F, -1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(f6, f9).func_181675_d();
/*  83 */           bufferbuilder.func_227888_a_(matrix4f, 1.0F, 1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(f6, f8).func_181675_d();
/*  84 */           bufferbuilder.func_227888_a_(matrix4f, -1.0F, 1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(f7, f8).func_181675_d();
/*  85 */           Tessellator.func_178181_a().func_78381_a();
/*  86 */           RenderSystem.disableBlend();
/*  87 */           matrixStack.func_227865_b_();
/*     */         } 
/*     */       } 
/*     */       
/*  91 */       if (instance.func_188419_a() instanceof ITextureOverlayEffect) {
/*     */         
/*  93 */         ResourceLocation res = ((ITextureOverlayEffect)instance.func_188419_a()).getViewTexture(instance.func_76459_b(), instance.func_76458_c());
/*  94 */         if (res != null) {
/*     */           
/*  96 */           float[] colors = ((ITextureOverlayEffect)instance.func_188419_a()).getOverlayColor();
/*  97 */           matrixStack.func_227860_a_();
/*  98 */           RenderSystem.enableBlend();
/*  99 */           RenderSystem.defaultBlendFunc();
/* 100 */           mc.func_110434_K().func_110577_a(res);
/* 101 */           BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/* 102 */           bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 103 */           bufferbuilder.func_227888_a_(matrix4f, -1.0F, -1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(2.0F, 2.0F).func_181675_d();
/* 104 */           bufferbuilder.func_227888_a_(matrix4f, 1.0F, -1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(0.0F, 2.0F).func_181675_d();
/* 105 */           bufferbuilder.func_227888_a_(matrix4f, 1.0F, 1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 106 */           bufferbuilder.func_227888_a_(matrix4f, -1.0F, 1.0F, -0.5F).func_227885_a_(colors[0], colors[1], colors[2], colors[3]).func_225583_a_(2.0F, 0.0F).func_181675_d();
/* 107 */           Tessellator.func_178181_a().func_78381_a();
/* 108 */           RenderSystem.disableBlend();
/* 109 */           matrixStack.func_227865_b_();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\SpecialPotionEffectEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */