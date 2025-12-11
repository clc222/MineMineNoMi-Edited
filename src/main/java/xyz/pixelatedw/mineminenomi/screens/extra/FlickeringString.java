/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FlickeringString
/*    */ {
/*    */   private long tick;
/*    */   private String message;
/*    */   private int flicker;
/*    */   private boolean isVisible = true;
/*    */   private Minecraft mc;
/*    */   
/*    */   public FlickeringString(String str, int flicker) {
/* 16 */     this.mc = Minecraft.func_71410_x();
/* 17 */     this.message = str;
/* 18 */     this.flicker = flicker;
/*    */   }
/*    */   
/*    */   public void render(MatrixStack matrixStack, int posX, int posY, float partialTicks) {
/* 22 */     this.tick = (long)((float)this.tick + 1.0F + partialTicks);
/* 23 */     if (this.tick % this.flicker == 0L) {
/* 24 */       this.isVisible = !this.isVisible;
/*    */     } else {
/*    */       
/* 27 */       String msg = this.isVisible ? this.message : "";
/* 28 */       WyHelper.drawStringWithBorder(this.mc.field_71466_p, matrixStack, msg, posX, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\FlickeringString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */