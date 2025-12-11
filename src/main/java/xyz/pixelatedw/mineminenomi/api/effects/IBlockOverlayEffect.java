/*   */ package xyz.pixelatedw.mineminenomi.api.effects;
/*   */ 
/*   */ import net.minecraft.block.Block;
/*   */ 
/*   */ public interface IBlockOverlayEffect {
/*   */   Block getBlockOverlay(int paramInt1, int paramInt2);
/*   */   
/*   */   default float[] getOverlayColor() {
/* 9 */     return new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
/*   */   }
/*   */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\IBlockOverlayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */