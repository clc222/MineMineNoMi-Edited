/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ public interface ITextureOverlayEffect {
/*    */   ResourceLocation getBodyTexture(int paramInt1, int paramInt2);
/*    */   
/*    */   default ResourceLocation getViewTexture(int duration, int amplifier) {
/* 13 */     return getBodyTexture(duration, amplifier);
/*    */   }
/*    */   
/*    */   default float[] getOverlayColor() {
/* 17 */     return new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   default RenderType getRenderType() {
/* 22 */     return ModRenderTypes.TRANSPARENT_COLOR;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\ITextureOverlayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */