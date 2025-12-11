/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.resource.ISelectiveResourceReloadListener;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Constant;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.RenderOverlayEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.events.SpecialPotionEffectEvents;
/*    */ 
/*    */ 
/*    */ @Mixin(value = {GameRenderer.class}, priority = 990)
/*    */ public abstract class GameRendererMixin
/*    */   implements ISelectiveResourceReloadListener
/*    */ {
/*    */   @ModifyConstant(method = {"pick(F)V"}, constant = {@Constant(doubleValue = 6.0D)})
/*    */   private double getActualAttackRangeInCreative(double attackRange) {
/* 32 */     if (this.field_78531_r.field_71439_g != null)
/* 33 */       return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.field_78531_r.field_71439_g, attackRange); 
/* 34 */     return attackRange;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private Minecraft field_78531_r;
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyConstant(method = {"pick(F)V"}, constant = {@Constant(doubleValue = 9.0D)})
/*    */   private double getActualAttackRangeInSurvival1(double attackRange) {
/* 48 */     if (this.field_78531_r.field_71439_g != null)
/* 49 */       return AttributeHelper.getSquaredAttackRangeDistance((LivingEntity)this.field_78531_r.field_71439_g, attackRange); 
/* 50 */     return attackRange;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"renderItemInHand"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/matrix/MatrixStack;popPose()V", shift = At.Shift.AFTER)})
/*    */   private void renderItemInHand(MatrixStack matrixStack, ActiveRenderInfo activeRenderInfo, float partialTicks, CallbackInfo info) {
/* 63 */     ClientPlayerEntity clientPlayerEntity = this.field_78531_r.field_71439_g;
/* 64 */     if (clientPlayerEntity == null)
/*    */       return; 
/* 66 */     RenderOverlayEvent event = new RenderOverlayEvent((PlayerEntity)clientPlayerEntity, matrixStack, activeRenderInfo, partialTicks);
/* 67 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 68 */     SpecialPotionEffectEvents.renderScreenEffects((PlayerEntity)clientPlayerEntity, matrixStack, activeRenderInfo, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\GameRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */