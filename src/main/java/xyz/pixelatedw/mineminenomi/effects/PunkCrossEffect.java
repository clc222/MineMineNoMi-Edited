/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ITextureOverlayEffect;
/*    */ 
/*    */ public class PunkCrossEffect extends ParalysisEffect implements ITextureOverlayEffect {
/*  7 */   public static final ResourceLocation TEXTURE = new ResourceLocation("textures/block/iron_block.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 15 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getBodyTexture(int duration, int amplifier) {
/* 25 */     return TEXTURE;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\PunkCrossEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */