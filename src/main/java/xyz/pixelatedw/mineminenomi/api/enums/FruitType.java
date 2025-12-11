/*    */ package xyz.pixelatedw.mineminenomi.api.enums;
/*    */ 
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ public enum FruitType
/*    */ {
/*  7 */   PARAMECIA(TextFormatting.RED, "Paramecia"),
/*  8 */   LOGIA(TextFormatting.YELLOW, "Logia"),
/*  9 */   ZOAN(TextFormatting.GREEN, "Zoan"),
/* 10 */   MYTHICAL_ZOAN(TextFormatting.AQUA, "Mythical Zoan"),
/* 11 */   ANCIENT_ZOAN(TextFormatting.DARK_GREEN, "Ancient Zoan"),
/* 12 */   ARTIFICIAL_LOGIA(TextFormatting.GOLD, "Artificial Logia"),
/* 13 */   ARTIFICIAL_PARAMECIA(TextFormatting.GOLD, "Artificial Paramecia"),
/* 14 */   ARTIFICIAL_ZOAN(TextFormatting.GOLD, "Artificial Zoan");
/*    */   
/*    */   private TextFormatting color;
/*    */   
/*    */   private String name;
/*    */   
/*    */   FruitType(TextFormatting color, String name) {
/* 21 */     this.color = color;
/* 22 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public TextFormatting getColor() {
/* 27 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 32 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\enums\FruitType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */