/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.color.ItemColors;
/*    */ import net.minecraft.client.renderer.model.BakedQuad;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Accessor;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ItemRenderer.class})
/*    */ public abstract class ItemRendererMixin
/*    */ {
/*    */   @Accessor
/*    */   public abstract ItemColors getItemColors();
/*    */   
/*    */   @Inject(method = {"renderQuadList"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void renderQuadList(MatrixStack matrixStack, IVertexBuilder buffer, List<BakedQuad> quads, ItemStack itemStack, int lightmap, int overlay, CallbackInfo info) {
/* 26 */     boolean hasDefaultImbuing = false;
/* 27 */     if (itemStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem && itemStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem) {
/* 28 */       hasDefaultImbuing = true;
/* 29 */     } else if (!(itemStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem)) {
/* 30 */       hasDefaultImbuing = itemStack.func_77984_f();
/*    */     } 
/*    */     
/* 33 */     if (!itemStack.func_190926_b() && itemStack.func_77942_o() && itemStack.func_77978_p().func_74767_n("imbuingHakiActive") && hasDefaultImbuing) {
/* 34 */       MatrixStack.Entry entry = matrixStack.func_227866_c_();
/* 35 */       for (BakedQuad bakedquad : quads) {
/* 36 */         buffer.addVertexData(entry, bakedquad, 0.4F, 0.0F, 0.4F, lightmap, overlay, true);
/*    */       }
/* 38 */       info.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ItemRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */