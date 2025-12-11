/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IScreenShaderEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ImpactFrameEffect
/*    */   extends ModEffect implements IScreenShaderEffect {
/* 12 */   private static final ResourceLocation SHADER = new ResourceLocation("mineminenomi", "shaders/post/impact_frame.json");
/*    */   
/*    */   public ImpactFrameEffect() {
/* 15 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 20 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getScreenShader() {
/* 35 */     return SHADER;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\ImpactFrameEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */