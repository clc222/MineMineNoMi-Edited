/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({EntityRendererManager.class})
/*    */ public class EntityRendererManagerMixin
/*    */ {
/*    */   @Inject(method = {"renderShadow"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void renderShadow(MatrixStack matrixStack, IRenderTypeBuffer buffer, Entity entity, float weight, float partialTicks, IWorldReader world, float size, CallbackInfo callback) {
/* 30 */     if (entity instanceof LivingEntity && 
/* 31 */       !EntityStatsCapability.get((LivingEntity)entity).hasShadow()) {
/* 32 */       callback.cancel();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyArgs(method = {"render"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRendererManager;renderShadow(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/entity/Entity;FFLnet/minecraft/world/IWorldReader;F)V"))
/*    */   public void shadowSize(Args args) {
/* 45 */     Entity entity = (Entity)args.get(2);
/*    */     
/* 47 */     if (entity instanceof LivingEntity) {
/* 48 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)entity);
/* 49 */       if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/* 50 */         MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)entity);
/* 51 */         if (info != null && info.getShadowSize() >= 0.0F)
/* 52 */           args.set(6, Float.valueOf(info.getShadowSize())); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\EntityRendererManagerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */