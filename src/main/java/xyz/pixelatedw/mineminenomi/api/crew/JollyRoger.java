/*     */ package xyz.pixelatedw.mineminenomi.api.crew;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Optional;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class JollyRoger
/*     */ {
/*  20 */   private JollyRogerElement base = ModJollyRogers.BASE_0;
/*  21 */   private JollyRogerElement[] backgrounds = new JollyRogerElement[2];
/*  22 */   private JollyRogerElement[] details = new JollyRogerElement[5];
/*     */   
/*     */   public CompoundNBT write() {
/*  25 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/*  27 */     JollyRogerElement baseElement = getBase();
/*  28 */     if (baseElement != null && baseElement.getTexture() != null) {
/*  29 */       CompoundNBT baseNBT = new CompoundNBT();
/*  30 */       baseNBT.func_74778_a("id", baseElement.getTexture().toString());
/*  31 */       baseElement.write(baseNBT);
/*  32 */       nbt.func_218657_a("base", (INBT)baseNBT);
/*     */     } else {
/*     */       
/*  35 */       nbt.func_218657_a("base", (INBT)new CompoundNBT());
/*     */     } 
/*     */     
/*     */     try {
/*  39 */       ListNBT backgrounds = new ListNBT();
/*  40 */       for (int i = 0; i < (getBackgrounds()).length; i++) {
/*  41 */         JollyRogerElement bgElement = getBackgrounds()[i];
/*  42 */         if (bgElement != null && bgElement.getTexture() != null) {
/*  43 */           CompoundNBT backgroundNBT = new CompoundNBT();
/*  44 */           backgroundNBT.func_74768_a("slot", i);
/*  45 */           backgroundNBT.func_74778_a("id", bgElement.getTexture().toString());
/*  46 */           bgElement.write(backgroundNBT);
/*  47 */           backgrounds.add(backgroundNBT);
/*     */         } else {
/*     */           
/*  50 */           CompoundNBT backgroundNBT = new CompoundNBT();
/*  51 */           backgroundNBT.func_74768_a("slot", i);
/*  52 */           backgrounds.add(backgroundNBT);
/*     */         } 
/*     */       } 
/*  55 */       nbt.func_218657_a("backgrounds", (INBT)backgrounds);
/*     */       
/*  57 */       ListNBT details = new ListNBT();
/*  58 */       for (int j = 0; j < (getDetails()).length; j++) {
/*  59 */         JollyRogerElement detailElement = getDetails()[j];
/*  60 */         if (detailElement != null && detailElement.getTexture() != null) {
/*  61 */           CompoundNBT detailNBT = new CompoundNBT();
/*  62 */           detailNBT.func_74768_a("slot", j);
/*  63 */           detailNBT.func_74778_a("id", detailElement.getTexture().toString());
/*  64 */           detailElement.write(detailNBT);
/*  65 */           details.add(detailNBT);
/*     */         } else {
/*     */           
/*  68 */           CompoundNBT detailNBT = new CompoundNBT();
/*  69 */           detailNBT.func_74768_a("slot", j);
/*  70 */           details.add(detailNBT);
/*     */         } 
/*     */       } 
/*  73 */       nbt.func_218657_a("details", (INBT)details);
/*     */     }
/*  75 */     catch (Exception ex) {
/*  76 */       ex.printStackTrace();
/*     */     } 
/*     */     
/*  79 */     return nbt;
/*     */   }
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/*     */     try {
/*  84 */       CompoundNBT baseNBT = nbt.func_74775_l("base");
/*  85 */       if (baseNBT.func_74764_b("id")) {
/*  86 */         JollyRogerElement baseElement = JollyRogerElement.of(new ResourceLocation(baseNBT.func_74779_i("id")));
/*     */         
/*  88 */         if (baseElement != null) {
/*  89 */           baseElement.read(baseNBT);
/*  90 */           setBase(baseElement);
/*     */         } 
/*     */       } else {
/*     */         
/*  94 */         setBase(null);
/*     */       } 
/*     */       
/*  97 */       ListNBT backgroundsNBT = nbt.func_150295_c("backgrounds", 10);
/*  98 */       for (int i = 0; i < backgroundsNBT.size(); i++) {
/*  99 */         CompoundNBT backgroundNBT = backgroundsNBT.func_150305_b(i);
/* 100 */         int slot = backgroundNBT.func_74762_e("slot");
/* 101 */         if (backgroundNBT.func_74764_b("id")) {
/* 102 */           JollyRogerElement bgElement = JollyRogerElement.of(new ResourceLocation(backgroundNBT.func_74779_i("id")));
/*     */           
/* 104 */           if (bgElement != null) {
/* 105 */             bgElement.read(backgroundNBT);
/* 106 */             setBackground(slot, bgElement);
/*     */           } 
/*     */         } else {
/*     */           
/* 110 */           setBackground(slot, null);
/*     */         } 
/*     */       } 
/*     */       
/* 114 */       ListNBT detailsNBT = nbt.func_150295_c("details", 10);
/* 115 */       for (int j = 0; j < detailsNBT.size(); j++) {
/* 116 */         CompoundNBT detailNBT = detailsNBT.func_150305_b(j);
/* 117 */         int slot = detailNBT.func_74762_e("slot");
/*     */         
/* 119 */         if (detailNBT.func_74764_b("id")) {
/* 120 */           JollyRogerElement detailElement = JollyRogerElement.of(new ResourceLocation(detailNBT.func_74779_i("id")));
/*     */           
/* 122 */           if (detailElement != null) {
/* 123 */             detailElement.read(detailNBT);
/* 124 */             setDetail(slot, detailElement);
/*     */           } 
/*     */         } else {
/*     */           
/* 128 */           setDetail(slot, null);
/*     */         }
/*     */       
/*     */       } 
/* 132 */     } catch (Exception ex) {
/* 133 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public JollyRogerElement getBase() {
/* 138 */     return this.base;
/*     */   }
/*     */   
/*     */   public void setBase(JollyRogerElement base) {
/* 142 */     this.base = base;
/*     */   }
/*     */   
/*     */   public JollyRogerElement[] getBackgrounds() {
/* 146 */     return this.backgrounds;
/*     */   }
/*     */   
/*     */   public void setBackgrounds(JollyRogerElement[] elements) {
/* 150 */     this.backgrounds = elements;
/*     */   }
/*     */   
/*     */   public boolean addBackground(JollyRogerElement bg) {
/* 154 */     for (int i = 0; i < this.backgrounds.length; i++) {
/* 155 */       JollyRogerElement background = this.backgrounds[i];
/* 156 */       if (background == null) {
/* 157 */         this.backgrounds[i] = bg;
/* 158 */         return true;
/*     */       } 
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setBackground(int slot, JollyRogerElement bg) {
/* 165 */     if (!hasBackground(bg) && slot <= 2) {
/* 166 */       this.backgrounds[slot] = bg;
/* 167 */       return true;
/*     */     } 
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasBackground(JollyRogerElement bg) {
/* 173 */     return Arrays.<JollyRogerElement>stream(this.backgrounds).parallel().anyMatch(background -> (background != null && background.equals(bg)));
/*     */   }
/*     */   
/*     */   public JollyRogerElement[] getDetails() {
/* 177 */     return this.details;
/*     */   }
/*     */   
/*     */   public void setDetails(JollyRogerElement[] elements) {
/* 181 */     this.details = elements;
/*     */   }
/*     */   
/*     */   public boolean addDetail(JollyRogerElement det) {
/* 185 */     for (int i = 0; i < this.details.length; i++) {
/* 186 */       JollyRogerElement detail = this.details[i];
/* 187 */       if (detail == null) {
/* 188 */         this.details[i] = det;
/* 189 */         return true;
/*     */       } 
/*     */     } 
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setDetail(int slot, JollyRogerElement det) {
/* 196 */     if (!hasDetail(det) && slot <= 5) {
/* 197 */       this.details[slot] = det;
/* 198 */       return true;
/*     */     } 
/* 200 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasDetail(JollyRogerElement det) {
/* 204 */     return Arrays.<JollyRogerElement>stream(this.details).parallel().anyMatch(detail -> (detail != null && detail.equals(det)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<BufferedImage> getAsBufferedImage() {
/*     */     try {
/* 217 */       BufferedImage jollyRogerImage = new BufferedImage(128, 128, 2);
/*     */       
/* 219 */       for (JollyRogerElement backgroundElement : this.backgrounds) {
/*     */         
/* 221 */         if (backgroundElement != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 226 */           BufferedImage backgroundElementImage = elementToImage(backgroundElement);
/*     */           
/* 228 */           jollyRogerImage.getGraphics().drawImage(backgroundElementImage, 0, 0, null);
/*     */         } 
/*     */       } 
/* 231 */       BufferedImage jollyRogerBase = elementToImage(this.base);
/* 232 */       jollyRogerImage.getGraphics().drawImage(jollyRogerBase, 0, 0, null);
/*     */       
/* 234 */       for (JollyRogerElement detailElement : this.details) {
/*     */         
/* 236 */         if (detailElement != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 241 */           BufferedImage detailElementImage = elementToImage(detailElement);
/*     */           
/* 243 */           jollyRogerImage.getGraphics().drawImage(detailElementImage, 0, 0, null);
/*     */         } 
/*     */       } 
/* 246 */       return 
/* 247 */         Optional.of(jollyRogerImage);
/* 248 */     } catch (IOException e) {
/*     */       
/* 250 */       ModMain.LOGGER.error(e.getMessage());
/*     */ 
/*     */       
/* 253 */       return Optional.empty();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage elementToImage(JollyRogerElement element) throws IOException {
/* 266 */     String assetPath = "assets/mineminenomi/";
/*     */     
/* 268 */     BufferedImage elementImage = ImageIO.read(getClass().getClassLoader()
/* 269 */         .getResourceAsStream(assetPath + element.getTexture().func_110623_a()));
/*     */     
/* 271 */     if (element.canBeColored())
/*     */     {
/* 273 */       elementImage = applyColorToImage(element.getColor(), elementImage);
/*     */     }
/*     */ 
/*     */     
/* 277 */     return elementImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage applyColorToImage(String hex, BufferedImage image) {
/* 290 */     Color color = WyHelper.hexToRGB(hex);
/*     */     
/* 292 */     for (int x = 0; x < image.getWidth(); x++) {
/*     */       
/* 294 */       for (int y = 0; y < image.getHeight(); y++) {
/*     */         
/* 296 */         int rgba = image.getRGB(x, y);
/* 297 */         Color pixelColor = new Color(rgba, true);
/*     */         
/* 299 */         if (pixelColor.getAlpha() != 0 && (rgba & 0xFFFFFF) != 0) {
/*     */           
/* 301 */           Integer tintedPixel = tintABGRPixel(pixelColor.getRGB(), color);
/* 302 */           image.setRGB(x, y, tintedPixel.intValue());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 307 */     return image;
/*     */   }
/*     */   
/*     */   public static Integer tintABGRPixel(int pixelColor, Color tintColor) {
/* 311 */     int x = pixelColor >> 16 & 0xFF, y = pixelColor >> 8 & 0xFF, z = pixelColor & 0xFF;
/* 312 */     int top = 2126 * x + 7252 * y + 722 * z;
/* 313 */     int Btemp = (int)((tintColor.getBlue() * top) * 1766117501L >> 52L);
/* 314 */     int Gtemp = (int)((tintColor.getGreen() * top) * 1766117501L >> 52L);
/* 315 */     int Rtemp = (int)((tintColor.getRed() * top) * 1766117501L >> 52L);
/*     */     
/* 317 */     return Integer.valueOf((pixelColor >> 24 & 0xFF) << 24 | Btemp & 0xFF | (Gtemp & 0xFF) << 8 | (Rtemp & 0xFF) << 16);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\crew\JollyRoger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */