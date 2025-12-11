/*    */ package xyz.pixelatedw.mineminenomi.config.options;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import me.shedaniel.math.Color;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ColorOption
/*    */   extends StringOption {
/*    */   public ColorOption(String defaultValue, String optionName, String optionDescription) {
/* 10 */     super(defaultValue, optionName, optionDescription);
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveColor(Color color) {
/* 15 */     getValue().set(WyHelper.rgbToHex(color.getRed(), color.getGreen(), color.getBlue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getDefaultColor() {
/* 20 */     Color c = WyHelper.hexToRGB(getDefault());
/* 21 */     return Color.ofRGB(c.getRed(), c.getGreen(), c.getBlue());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\options\ColorOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */