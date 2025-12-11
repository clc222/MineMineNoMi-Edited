/*    */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ import java.util.regex.Pattern;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.text.ITextProperties;
/*    */ import net.minecraft.util.text.Style;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SequencedString {
/* 13 */   private static final Pattern FORMATTING_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");
/*    */   
/*    */   public String string;
/*    */   public int maxLength;
/* 17 */   public int color = Color.WHITE.getRGB();
/*    */   public char[] chars;
/*    */   public int maxTicks;
/*    */   public int tickCount;
/*    */   public Minecraft mc;
/* 22 */   public int delayTicks = this.maxTicks;
/*    */   public boolean isComplete;
/*    */   
/*    */   public SequencedString(String str, int maxLength, int maxTicks) {
/* 26 */     this(str, maxLength, maxTicks, maxTicks + 100);
/*    */   }
/*    */   
/*    */   public SequencedString(String str, int maxLength, int maxTicks, int delay) {
/* 30 */     this.mc = Minecraft.func_71410_x();
/* 31 */     this.string = str;
/* 32 */     this.maxLength = maxLength;
/* 33 */     this.chars = new char[this.string.length()];
/* 34 */     for (int i = 0; i < this.string.length(); i++) {
/* 35 */       this.chars[i] = this.string.charAt(i);
/*    */     }
/* 37 */     this.maxTicks = maxTicks;
/* 38 */     this.tickCount = 0;
/* 39 */     this.delayTicks = delay;
/*    */   }
/*    */   
/*    */   public void render(MatrixStack matrixStack, int posX, int posY, float partialTicks) {
/* 43 */     String tempStr = "";
/* 44 */     if (!this.isComplete) {
/* 45 */       for (int j = 0; j < this.chars.length; j++) {
/* 46 */         if (this.tickCount >= calculateTicksNeeded(j) && this.tickCount * partialTicks < this.delayTicks) {
/* 47 */           tempStr = tempStr + this.chars[j];
/*    */         }
/*    */       } 
/*    */       
/* 51 */       if (tempStr.equals(this.string)) {
/* 52 */         this.isComplete = true;
/*    */       }
/*    */     } else {
/* 55 */       tempStr = this.string;
/*    */     } 
/*    */     
/* 58 */     int i = 0;
/* 59 */     List<ITextProperties> list = (Minecraft.func_71410_x()).field_71466_p.func_238420_b_().func_238365_g_(tempStr, this.maxLength, Style.field_240709_b_);
/* 60 */     for (ITextProperties text : list) {
/* 61 */       WyHelper.drawStringWithBorder(this.mc.field_71466_p, matrixStack, text.getString(), posX, posY + 10 * i, this.color);
/* 62 */       i++;
/*    */     } 
/*    */     
/* 65 */     this.tickCount++;
/*    */   }
/*    */   
/*    */   public int calculateTicksNeeded(int index) {
/* 69 */     int oldRange = this.string.length();
/* 70 */     int newRange = this.maxTicks;
/* 71 */     int newValue = index * newRange / oldRange;
/*    */     
/* 73 */     return newValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\SequencedString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */