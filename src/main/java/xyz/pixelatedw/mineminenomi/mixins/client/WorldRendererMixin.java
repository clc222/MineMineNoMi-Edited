/*     */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GameRenderer;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.LightTexture;
/*     */ import net.minecraft.client.renderer.OutlineLayerBuffer;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.world.ClientWorld;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.OutlineColorEvent;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProtectionClientEvents;
/*     */ import xyz.pixelatedw.mineminenomi.events.world.BiomeHandler;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypeBuffers;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @Mixin({WorldRenderer.class})
/*     */ public abstract class WorldRendererMixin
/*     */ {
/*     */   @Shadow
/*     */   @Final
/*     */   private Minecraft field_72777_q;
/*     */   @Shadow
/*     */   private ClientWorld field_72769_h;
/*     */   
/*     */   @Inject(method = {"initOutline"}, at = {@At("TAIL")})
/*     */   public void initOutline(CallbackInfo ci) {
/*  46 */     ModRenderTypeBuffers.getInstance().initHakiAuraShader();
/*     */   }
/*     */   
/*     */   @Inject(method = {"shouldShowEntityOutlines"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void shouldShowEntityOutlines(CallbackInfoReturnable<Boolean> cir) {
/*  51 */     if ((Minecraft.func_71410_x()).field_71438_f.func_228448_p_() != null && ModRenderTypeBuffers.getInstance().getHakiAuraShader() != null && (Minecraft.func_71410_x()).field_71439_g != null) {
/*  52 */       cir.setReturnValue(Boolean.valueOf(true));
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"resize"}, at = {@At("TAIL")})
/*     */   public void resize(int width, int height, CallbackInfo ci) {
/*  59 */     if (ModRenderTypeBuffers.getInstance() == null) {
/*     */       return;
/*     */     }
/*     */     
/*  63 */     if (ModRenderTypeBuffers.getInstance().getHakiAuraShader() != null) {
/*  64 */       ModRenderTypeBuffers.getInstance().getHakiAuraShader().func_148026_a(width, height);
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderLevel"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OutlineLayerBuffer;endOutlineBatch()V", shift = At.Shift.BEFORE)})
/*     */   public void processShaders(MatrixStack matrixStack, float partialTicks, long finishTimeNano, boolean drawBlockOutline, ActiveRenderInfo activeRenderInfo, GameRenderer gameRenderer, LightTexture lightmap, Matrix4f projection, CallbackInfo ci) {
/*  70 */     ModRenderTypeBuffers.getInstance().getHakiAuraBuffer().endBatch();
/*     */     
/*  72 */     ModRenderTypeBuffers.getInstance().getHakiAuraShader().func_148018_a(partialTicks);
/*  73 */     Minecraft.func_71410_x().func_147110_a().func_147610_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ModifyArgs(method = {"renderLevel"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/WorldRenderer;renderEntity(Lnet/minecraft/entity/Entity;DDDFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;)V"))
/*     */   private void renderEntityArgs(Args args) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"renderLevel"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/WorldRenderer;renderWorldBounds(Lnet/minecraft/client/renderer/ActiveRenderInfo;)V", shift = At.Shift.AFTER)})
/*     */   private void renderBounds(MatrixStack matrixStack, float partialTicks, long finishTimeNano, boolean drawBlockOutline, ActiveRenderInfo camera, GameRenderer gameRenderer, LightTexture lightmap, Matrix4f projection, CallbackInfo ci) {
/* 105 */     AbilityProtectionClientEvents.renderTick(matrixStack, camera);
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderEntity"}, at = {@At("HEAD")})
/*     */   public void renderEntity(Entity entity, double camX, double camY, double camZ, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, CallbackInfo callback) {
/* 110 */     if (buffer instanceof OutlineLayerBuffer) {
/* 111 */       OutlineLayerBuffer outlineBuffer = (OutlineLayerBuffer)buffer;
/*     */       
/* 113 */       OutlineColorEvent event = new OutlineColorEvent(entity);
/* 114 */       MinecraftForge.EVENT_BUS.post((Event)event);
/*     */       
/* 116 */       outlineBuffer.func_228472_a_(event.getColor().getRed(), event.getColor().getGreen(), event.getColor().getBlue(), event.getColor().getAlpha());
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderClouds"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void renderClouds(MatrixStack matrixStack, float p_228425_2_, double p_228425_3_, double p_228425_5_, double p_228425_7_, CallbackInfo callback) {
/* 122 */     if (WyHelper.isInChallengeDimension((World)this.field_72769_h)) {
/* 123 */       callback.cancel();
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"renderSnowAndRain"}, at = @At("STORE"), ordinal = 1)
/*     */   private float getRainLevel(float rainLevel) {
/* 130 */     ResourceLocation biomeId = this.field_72769_h.func_241828_r().func_243612_b(Registry.field_239720_u_).func_177774_c(this.field_72769_h.func_226691_t_(this.field_72777_q.field_71439_g.func_233580_cy_()));
/*     */     
/* 132 */     float newLevel = BiomeHandler.Client.getDrumSnowLevel(this.field_72777_q, rainLevel, biomeId);
/* 133 */     if (newLevel >= 0.0D) {
/* 134 */       return newLevel;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     return rainLevel;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\WorldRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */