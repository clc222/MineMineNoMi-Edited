/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ public class DFEncyclopediaEntry
/*    */ {
/*    */   private final Optional<Color> baseColor;
/*    */   private final Optional<Color> stemColor;
/*    */   private final Optional<Integer> shape;
/*    */   private AkumaNoMiItem devilFruit;
/*    */   
/*    */   public static DFEncyclopediaEntry empty() {
/* 16 */     return new DFEncyclopediaEntry(Optional.empty(), Optional.empty(), Optional.empty());
/*    */   }
/*    */   
/*    */   public static DFEncyclopediaEntry of(Optional<Integer> type, Optional<Color> baseColor, Optional<Color> stemColor) {
/* 20 */     return new DFEncyclopediaEntry(type, baseColor, stemColor);
/*    */   }
/*    */   
/*    */   public static DFEncyclopediaEntry of(CompoundNBT nbt) {
/* 24 */     Optional<Integer> shape = Optional.empty();
/*    */     
/* 26 */     int shapeVal = nbt.func_74762_e("shape");
/* 27 */     if (shapeVal > 0) {
/* 28 */       shape = Optional.of(Integer.valueOf(shapeVal));
/*    */     }
/*    */     
/* 31 */     Color baseVal = new Color(nbt.func_74762_e("baseColor"));
/* 32 */     Color stemVal = new Color(nbt.func_74762_e("stemColor"));
/*    */     
/* 34 */     return new DFEncyclopediaEntry(shape, Optional.of(baseVal), Optional.of(stemVal));
/*    */   }
/*    */   
/*    */   public DFEncyclopediaEntry(Optional<Integer> shape, Optional<Color> baseColor, Optional<Color> stemColor) {
/* 38 */     this.shape = shape;
/* 39 */     this.baseColor = baseColor;
/* 40 */     this.stemColor = stemColor;
/*    */   }
/*    */   
/*    */   public Optional<Color> getBaseColor() {
/* 44 */     return this.baseColor;
/*    */   }
/*    */   
/*    */   public Optional<Color> getStemColor() {
/* 48 */     return this.stemColor;
/*    */   }
/*    */   
/*    */   public Optional<Integer> getShape() {
/* 52 */     return this.shape;
/*    */   }
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 56 */     return this.devilFruit;
/*    */   }
/*    */   
/*    */   public void setDevilFruit(AkumaNoMiItem devilFruit) {
/* 60 */     this.devilFruit = devilFruit;
/*    */   }
/*    */   
/*    */   public int getCompletion() {
/* 64 */     int sum = 0;
/* 65 */     if (getShape().isPresent()) {
/* 66 */       sum++;
/*    */     }
/* 68 */     if (getBaseColor().isPresent() && ((Color)getBaseColor().get()).getRGB() != Color.BLACK.getRGB()) {
/* 69 */       sum++;
/*    */     }
/* 71 */     if (getStemColor().isPresent() && ((Color)getStemColor().get()).getRGB() != Color.BLACK.getRGB()) {
/* 72 */       sum++;
/*    */     }
/* 74 */     return sum;
/*    */   }
/*    */   
/*    */   public boolean isComplete() {
/* 78 */     return (getCompletion() >= 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     StringBuilder sb = new StringBuilder();
/* 84 */     sb.append("[ ");
/* 85 */     if (getShape().isPresent()) {
/* 86 */       sb.append("Shape: " + getShape().get() + " ");
/*    */     }
/* 88 */     if (getBaseColor().isPresent()) {
/* 89 */       sb.append("Base Color: " + ((Color)getBaseColor().get()).toString() + " ");
/*    */     }
/* 91 */     if (getStemColor().isPresent()) {
/* 92 */       sb.append("Stem Color: " + ((Color)getStemColor().get()).toString() + " ");
/*    */     }
/* 94 */     sb.append("Completion: " + getCompletion());
/* 95 */     sb.append(" ]");
/* 96 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\DFEncyclopediaEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */