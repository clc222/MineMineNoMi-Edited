/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import net.minecraftforge.fml.client.gui.widget.Slider;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CUpdateJollyRogerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.SimpleButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class JollyRogerCreatorScreen
/*     */   extends Screen
/*     */ {
/*     */   private PlayerEntity player;
/*     */   private Widget selectedButton;
/*  45 */   private JollyRogerElement.LayerType layerType = JollyRogerElement.LayerType.BASE;
/*     */   private Crew crew;
/*     */   private JollyRoger jollyRoger;
/*  48 */   private float animationTime = 0.0F;
/*  49 */   private int nextElementTry = 0;
/*     */   
/*     */   private boolean isEditing = false;
/*     */   
/*     */   private int layerIndex;
/*     */   private int trueIndex;
/*     */   private Slider redSlider;
/*     */   private Slider greenSlider;
/*     */   private Slider blueSlider;
/*     */   private LinkedHashSet<JollyRogerElement> allElements;
/*     */   private List<JollyRogerElement> allBases;
/*     */   private List<JollyRogerElement> allBackgrounds;
/*     */   private List<JollyRogerElement> allDetails;
/*     */   
/*     */   public JollyRogerCreatorScreen(boolean isEditing, Crew crew, LinkedHashSet<JollyRogerElement> elements) {
/*  64 */     super((ITextComponent)new StringTextComponent(""));
/*     */     
/*  66 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/*  68 */     this.player = (PlayerEntity)mc.field_71439_g;
/*  69 */     this.crew = crew;
/*  70 */     this.jollyRoger = crew.getJollyRoger();
/*  71 */     this.isEditing = isEditing;
/*     */     
/*  73 */     this.allElements = elements;
/*  74 */     this.allBases = getTotalElementsForType(this.player, JollyRogerElement.LayerType.BASE);
/*  75 */     this.allBackgrounds = getTotalElementsForType(this.player, JollyRogerElement.LayerType.BACKGROUND);
/*  76 */     this.allDetails = getTotalElementsForType(this.player, JollyRogerElement.LayerType.DETAIL);
/*     */     
/*  78 */     this.animationTime = 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/*  84 */     this.player.field_70170_p.func_217381_Z().func_76320_a("jollyRogerRendering");
/*     */     
/*  86 */     func_230446_a_(matrixStack);
/*     */     
/*  88 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  90 */     int posX = this.field_230708_k_ / 2;
/*  91 */     int posY = this.field_230709_l_ / 2;
/*     */     
/*  93 */     RenderSystem.enableBlend();
/*  94 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  96 */     GL11.glPushMatrix();
/*     */     
/*  98 */     double scale = 0.5D;
/*  99 */     GL11.glTranslated((posX - 115), (posY - 130), 1.0D);
/* 100 */     GL11.glTranslated(128.0D, 128.0D, 0.0D);
/* 101 */     GL11.glScaled(scale, scale, scale);
/* 102 */     GL11.glTranslated(-128.0D, -128.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     RendererHelper.drawPlayerJollyRoger(this.jollyRoger, matrixStack);
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (this.animationTime < 0.15D) {
/*     */       
/* 114 */       this.animationTime = (float)(this.animationTime + 0.007D);
/* 115 */       scale += 0.45D + this.animationTime;
/*     */       
/* 117 */       GL11.glTranslated(128.0D, 128.0D, 0.0D);
/* 118 */       GL11.glScaled(scale, scale, scale);
/* 119 */       GL11.glTranslated(-128.0D, -128.0D, 0.0D);
/*     */       
/* 121 */       JollyRogerElement jollyRogerElement = getLayerElement();
/* 122 */       if (jollyRogerElement != null) {
/*     */         
/* 124 */         if (jollyRogerElement.canBeColored()) {
/*     */           
/* 126 */           Color color = WyHelper.getComplementaryColor(jollyRogerElement.getFullColor());
/* 127 */           RenderSystem.color4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 0.9F - this.animationTime * 5.0F);
/*     */         }
/*     */         else {
/*     */           
/* 131 */           RenderSystem.color4f(1.0F, 0.0F, 0.0F, 0.9F - this.animationTime * 4.0F);
/*     */         } 
/* 133 */         Minecraft.func_71410_x().func_110434_K().func_110577_a(jollyRogerElement.getTexture());
/* 134 */         GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 10.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     GL11.glPopMatrix();
/*     */     
/* 140 */     String text = (this.trueIndex >= 0) ? ((this.trueIndex + 1) + " / " + this.allBases.size()) : (new TranslationTextComponent(ModI18n.GUI_EMPTY)).getString();
/* 141 */     if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/* 142 */       text = (this.trueIndex >= 0) ? ((this.trueIndex + 1) + " / " + this.allBackgrounds.size()) : (new TranslationTextComponent(ModI18n.GUI_EMPTY)).getString();
/* 143 */     } else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/* 144 */       text = (this.trueIndex >= 0) ? ((this.trueIndex + 1) + " / " + this.allDetails.size()) : (new TranslationTextComponent(ModI18n.GUI_EMPTY)).getString();
/* 145 */     }  WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, text, posX - this.field_230712_o_.func_78256_a(text) / 2 + 12, posY + 80, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */     
/* 147 */     RenderSystem.disableBlend();
/*     */     
/* 149 */     posX = this.field_230708_k_;
/* 150 */     posY = this.field_230709_l_ / 2;
/*     */     
/* 152 */     int outlineSize = 2;
/* 153 */     int pX = posX - 95;
/* 154 */     int pY = posY - 85;
/* 155 */     int sW = posX + 100;
/* 156 */     int sH = posY + 45;
/*     */     
/* 158 */     JollyRogerElement element = getLayerElement();
/*     */     
/* 160 */     if (element != null) {
/*     */       
/* 162 */       if (element.canBeColored()) {
/*     */         
/* 164 */         func_238468_a_(matrixStack, pX - outlineSize, pY - outlineSize, sW + outlineSize, sH + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
/* 165 */         func_238468_a_(matrixStack, pX, pY, sW, sH, WyHelper.hexToRGB("#B3B3B3").getRGB(), WyHelper.hexToRGB("#505050").getRGB());
/*     */         
/* 167 */         posY -= 75;
/* 168 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (new TranslationTextComponent(ModI18n.GUI_RED)).getString() + " ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 169 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 170 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */         
/* 172 */         posY += 40;
/* 173 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (new TranslationTextComponent(ModI18n.GUI_GREEN)).getString() + " ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 174 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 175 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */         
/* 177 */         posY += 40;
/* 178 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (new TranslationTextComponent(ModI18n.GUI_BLUE)).getString() + " ", posX - 75, posY, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 179 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "0", posX - 85, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 180 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "255", posX - 23, posY + 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       } 
/*     */       
/* 183 */       if (element != this.jollyRoger.getBase()) {
/*     */         
/* 185 */         posY = this.field_230709_l_ / 2;
/*     */         
/* 187 */         pX = posX - 95;
/* 188 */         pY = posY + 50;
/* 189 */         sW = posX + 100;
/* 190 */         sH = posY + 85;
/*     */         
/* 192 */         func_238468_a_(matrixStack, pX - outlineSize, pY - outlineSize, sW + outlineSize, sH + outlineSize, WyHelper.hexToRGB("#000000").getRGB(), WyHelper.hexToRGB("#000000").getRGB());
/* 193 */         func_238468_a_(matrixStack, pX, pY, sW, sH, WyHelper.hexToRGB("#B3B3B3").getRGB(), WyHelper.hexToRGB("#505050").getRGB());
/*     */         
/* 195 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (this.layerIndex + 1) + "", posX - 55, posY + 64, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       } 
/*     */     } 
/*     */     
/* 199 */     this.player.field_70170_p.func_217381_Z().func_76319_b();
/*     */     
/* 201 */     super.func_230430_a_(matrixStack, x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231158_b_(Minecraft mc, int width, int height) {
/* 207 */     super.func_231158_b_(mc, width, height);
/*     */     
/* 209 */     int posX = 0;
/* 210 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 212 */     int listPosY = posY - 85;
/*     */     
/* 214 */     SimpleButton baseButton = new SimpleButton(posX + 5, listPosY, 115, 16, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_BASE), this::selectButton);
/* 215 */     func_230480_a_((Widget)baseButton);
/*     */     int i;
/* 217 */     for (i = 0; i < (this.jollyRoger.getBackgrounds()).length; i++) {
/*     */       
/* 219 */       SimpleButton bgButton = new SimpleButton(posX + 5, listPosY + 20 + i * 20, 115, 16, (ITextComponent)new StringTextComponent((new TranslationTextComponent(ModI18n.GUI_BACKGROUND)).getString() + " " + (i + 1)), this::selectButton);
/* 220 */       func_230480_a_((Widget)bgButton);
/*     */     } 
/*     */     
/* 223 */     for (i = 0; i < (this.jollyRoger.getDetails()).length; i++) {
/*     */       
/* 225 */       SimpleButton detailButton = new SimpleButton(posX + 5, listPosY + 60 + i * 20, 115, 16, (ITextComponent)new StringTextComponent((new TranslationTextComponent(ModI18n.GUI_DETAIL)).getString() + " " + (i + 1)), this::selectButton);
/* 226 */       func_230480_a_((Widget)detailButton);
/*     */     } 
/*     */     
/* 229 */     posX = this.field_230708_k_ / 2;
/*     */     
/* 231 */     TexturedIconButton nextBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 80, posY - 65, 32, 110, (ITextComponent)new StringTextComponent(""), btn -> moveIndex(btn, true));
/* 232 */     nextBaseTexture = nextBaseTexture.setTextureInfo(posX + 80, posY - 75, 32, 128);
/* 233 */     func_230480_a_((Widget)nextBaseTexture);
/*     */     
/* 235 */     TexturedIconButton prevBaseTexture = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 85, posY - 65, 32, 110, (ITextComponent)new StringTextComponent(""), btn -> moveIndex(btn, false));
/* 236 */     prevBaseTexture = prevBaseTexture.setTextureInfo(posX - 85, posY - 75, 32, 128);
/* 237 */     func_230480_a_((Widget)prevBaseTexture);
/*     */     
/* 239 */     posX = this.field_230708_k_;
/*     */     
/* 241 */     Slider redColorPicker = new Slider(posX - 76, posY - 65, 50, 16, (ITextComponent)new StringTextComponent(""), (ITextComponent)new StringTextComponent(""), 0.0D, 255.0D, 255.0D, false, true, btn -> { 
/* 242 */         }slider -> changeColor(slider, "red")); this.redSlider = redColorPicker;
/* 243 */     func_230480_a_((Widget)redColorPicker);
/*     */     
/* 245 */     Slider greenColorPicker = new Slider(posX - 76, posY - 25, 50, 16, (ITextComponent)new StringTextComponent(""), (ITextComponent)new StringTextComponent(""), 0.0D, 255.0D, 255.0D, false, true, btn -> { 
/* 246 */         }slider -> changeColor(slider, "green")); this.greenSlider = greenColorPicker;
/* 247 */     func_230480_a_((Widget)greenColorPicker);
/*     */     
/* 249 */     Slider blueColorPicker = new Slider(posX - 76, posY + 15, 50, 16, (ITextComponent)new StringTextComponent(""), (ITextComponent)new StringTextComponent(""), 0.0D, 255.0D, 255.0D, false, true, btn -> { 
/* 250 */         }slider -> changeColor(slider, "blue")); this.blueSlider = blueColorPicker;
/* 251 */     func_230480_a_((Widget)blueColorPicker);
/*     */     
/* 253 */     SimpleButton editJollyRogerButton = new SimpleButton(this.field_230708_k_ / 2 - 17, posY + 95, 60, 16, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_FINISH), btn -> finishEditing());
/* 254 */     func_230480_a_((Widget)editJollyRogerButton);
/*     */     
/* 256 */     TexturedIconButton layerUpBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW, posX - 80, posY + 53, 16, 25, (ITextComponent)new StringTextComponent(""), btn -> changeLayerIndex(true));
/* 257 */     layerUpBtn = layerUpBtn.setTextureInfo(posX - 104, posY + 51, 64, 32);
/* 258 */     func_230480_a_((Widget)layerUpBtn);
/*     */     
/* 260 */     TexturedIconButton layerDownBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_DOWN, posX - 40, posY + 53, 16, 25, (ITextComponent)new StringTextComponent(""), btn -> changeLayerIndex(false));
/* 261 */     layerDownBtn = layerDownBtn.setTextureInfo(posX - 64, posY + 48, 64, 32);
/* 262 */     func_230480_a_((Widget)layerDownBtn);
/*     */     
/* 264 */     updateButtons();
/*     */   }
/*     */   
/*     */   private void finishEditing() {
/* 268 */     if (this.isEditing) {
/* 269 */       NewCrewScreen.open();
/*     */     } else {
/*     */       
/* 272 */       func_231175_as__();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void changeLayerIndex(boolean isUp) {
/* 278 */     int layerIndex = this.layerIndex;
/* 279 */     boolean canSwitch = false;
/*     */     
/* 281 */     if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/*     */       
/* 283 */       JollyRogerElement currentElement = this.jollyRoger.getBackgrounds()[layerIndex];
/* 284 */       JollyRogerElement nextElement = null;
/* 285 */       JollyRogerElement prevElement = null;
/*     */       
/* 287 */       if (isUp && this.layerIndex >= 0 && this.layerIndex + 1 < (this.jollyRoger.getBackgrounds()).length) {
/*     */         
/* 289 */         nextElement = this.jollyRoger.getBackgrounds()[++layerIndex];
/* 290 */         canSwitch = true;
/*     */       }
/* 292 */       else if (!isUp && this.layerIndex - 1 >= 0 && this.layerIndex <= (this.jollyRoger.getBackgrounds()).length) {
/*     */         
/* 294 */         prevElement = this.jollyRoger.getBackgrounds()[--layerIndex];
/* 295 */         canSwitch = true;
/*     */       } 
/*     */       
/* 298 */       if (currentElement != null && canSwitch)
/*     */       {
/* 300 */         this.jollyRoger.getBackgrounds()[layerIndex] = currentElement;
/*     */         
/* 302 */         if (isUp) {
/*     */           
/* 304 */           this.jollyRoger.getBackgrounds()[--layerIndex] = nextElement;
/* 305 */           this.layerIndex++;
/*     */         }
/* 307 */         else if (!isUp) {
/*     */           
/* 309 */           this.jollyRoger.getBackgrounds()[++layerIndex] = prevElement;
/* 310 */           this.layerIndex--;
/*     */         } 
/*     */         
/* 313 */         updateButtons();
/* 314 */         this.animationTime = 0.0F;
/* 315 */         ((SimpleButton)this.selectedButton).select();
/* 316 */         this.selectedButton = this.field_230710_m_.get(1 + this.layerIndex);
/* 317 */         ((SimpleButton)this.selectedButton).select();
/*     */       }
/*     */     
/* 320 */     } else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/*     */       
/* 322 */       JollyRogerElement currentElement = this.jollyRoger.getDetails()[layerIndex];
/* 323 */       JollyRogerElement nextElement = null;
/* 324 */       JollyRogerElement prevElement = null;
/*     */       
/* 326 */       if (isUp && this.layerIndex >= 0 && this.layerIndex + 1 < (this.jollyRoger.getDetails()).length) {
/*     */         
/* 328 */         nextElement = this.jollyRoger.getDetails()[++layerIndex];
/* 329 */         canSwitch = true;
/*     */       }
/* 331 */       else if (!isUp && this.layerIndex - 1 >= 0 && this.layerIndex <= (this.jollyRoger.getDetails()).length) {
/*     */         
/* 333 */         prevElement = this.jollyRoger.getDetails()[--layerIndex];
/* 334 */         canSwitch = true;
/*     */       } 
/*     */       
/* 337 */       if (currentElement != null && canSwitch) {
/*     */         
/* 339 */         this.jollyRoger.getDetails()[layerIndex] = currentElement;
/*     */         
/* 341 */         if (isUp) {
/*     */           
/* 343 */           this.jollyRoger.getDetails()[--layerIndex] = nextElement;
/* 344 */           this.layerIndex++;
/*     */         }
/* 346 */         else if (!isUp) {
/*     */           
/* 348 */           this.jollyRoger.getDetails()[++layerIndex] = prevElement;
/* 349 */           this.layerIndex--;
/*     */         } 
/*     */         
/* 352 */         updateButtons();
/* 353 */         this.animationTime = 0.0F;
/* 354 */         ((SimpleButton)this.selectedButton).select();
/* 355 */         this.selectedButton = this.field_230710_m_.get(3 + this.layerIndex);
/* 356 */         ((SimpleButton)this.selectedButton).select();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeColor(Slider slider, String color) {
/* 362 */     if (!slider.func_230449_g_()) {
/* 363 */       slider.dragging = false;
/*     */     }
/*     */     
/* 366 */     JollyRogerElement element = getLayerElement();
/* 367 */     if (element == null) {
/*     */       return;
/*     */     }
/*     */     
/* 371 */     if (color.equalsIgnoreCase("red")) {
/* 372 */       element.setColor(slider.getValueInt(), element.getGreen(), element.getBlue());
/*     */     }
/* 374 */     else if (color.equalsIgnoreCase("green")) {
/* 375 */       element.setColor(element.getRed(), slider.getValueInt(), element.getBlue());
/*     */     }
/* 377 */     else if (color.equalsIgnoreCase("blue")) {
/* 378 */       element.setColor(element.getRed(), element.getGreen(), slider.getValueInt());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveIndex(Button btn, boolean toRight) {
/*     */     try {
/* 386 */       this.nextElementTry++;
/*     */       
/* 388 */       if (toRight) {
/* 389 */         this.trueIndex++;
/*     */       } else {
/* 391 */         this.trueIndex--;
/*     */       } 
/* 393 */       if (this.layerType == JollyRogerElement.LayerType.BASE) {
/*     */         
/* 395 */         if (this.trueIndex >= this.allBases.size()) {
/* 396 */           this.trueIndex = -1;
/*     */         }
/* 398 */         if (this.trueIndex < 0 && this.jollyRoger.getBase() == null) {
/* 399 */           this.trueIndex = this.allBases.size() - 1;
/*     */         }
/*     */         
/* 402 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allBases.size()) {
/* 403 */           this.jollyRoger.setBase(this.allBases.get(this.trueIndex));
/*     */         }
/* 405 */         else if (this.trueIndex <= 0 && this.jollyRoger.getBase().getTexture() != null) {
/* 406 */           this.jollyRoger.setBase(null);
/*     */         } 
/*     */         int i;
/* 409 */         for (i = 0; i < (this.jollyRoger.getBackgrounds()).length; i++) {
/* 410 */           JollyRogerElement element = this.jollyRoger.getBackgrounds()[i];
/* 411 */           boolean hasElement = this.allBackgrounds.stream().anyMatch(elem -> (elem.equals(element) && !elem.canUse(this.player, this.crew)));
/* 412 */           if (hasElement) {
/* 413 */             this.jollyRoger.getBackgrounds()[i] = null;
/*     */           }
/*     */         } 
/*     */         
/* 417 */         for (i = 0; i < (this.jollyRoger.getDetails()).length; i++) {
/* 418 */           JollyRogerElement element = this.jollyRoger.getDetails()[i];
/* 419 */           boolean hasElement = this.allDetails.stream().anyMatch(elem -> (elem.equals(element) && !elem.canUse(this.player, this.crew)));
/* 420 */           if (hasElement) {
/* 421 */             this.jollyRoger.getDetails()[i] = null;
/*     */           }
/*     */         }
/*     */       
/* 425 */       } else if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/*     */         
/* 427 */         if (this.trueIndex >= this.allBackgrounds.size()) {
/* 428 */           this.trueIndex = -1;
/*     */         }
/* 430 */         if (this.trueIndex < 0 && this.jollyRoger.getBackgrounds()[this.layerIndex] == null) {
/* 431 */           this.trueIndex = this.allBackgrounds.size() - 1;
/*     */         }
/*     */         
/* 434 */         if (this.nextElementTry > this.allBackgrounds.size()) {
/* 435 */           this.jollyRoger.getBackgrounds()[this.layerIndex] = null;
/* 436 */           this.trueIndex = -1;
/* 437 */           this.nextElementTry = 0;
/* 438 */           updateButtons();
/*     */           
/*     */           return;
/*     */         } 
/* 442 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size()) {
/* 443 */           JollyRogerElement ogElem = this.allBackgrounds.get(this.trueIndex);
/* 444 */           for (int i = 0; i < (this.jollyRoger.getBackgrounds()).length; i++) {
/* 445 */             JollyRogerElement element = this.jollyRoger.getBackgrounds()[i];
/* 446 */             if (element != null && ogElem != null && ogElem.equals(element)) {
/* 447 */               moveIndex(btn, toRight);
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/* 453 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allBackgrounds.size()) {
/* 454 */           this.jollyRoger.getBackgrounds()[this.layerIndex] = this.allBackgrounds.get(this.trueIndex);
/* 455 */         } else if (this.trueIndex <= 0 && this.jollyRoger.getBackgrounds()[this.layerIndex].getTexture() != null) {
/* 456 */           this.jollyRoger.getBackgrounds()[this.layerIndex] = null;
/*     */         } 
/* 458 */         this.nextElementTry = 0;
/*     */       }
/* 460 */       else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/*     */         
/* 462 */         if (this.trueIndex >= this.allDetails.size()) {
/* 463 */           this.trueIndex = -1;
/*     */         }
/* 465 */         if (this.trueIndex < 0 && this.trueIndex <= this.allDetails.size() && this.jollyRoger.getDetails()[this.layerIndex] == null) {
/* 466 */           this.trueIndex = this.allDetails.size() - 1;
/*     */         }
/*     */         
/* 469 */         if (this.nextElementTry >= this.allDetails.size()) {
/* 470 */           this.jollyRoger.getDetails()[this.layerIndex] = null;
/* 471 */           this.trueIndex = -1;
/* 472 */           this.nextElementTry = 0;
/* 473 */           updateButtons();
/*     */           
/*     */           return;
/*     */         } 
/* 477 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size()) {
/* 478 */           JollyRogerElement ogElem = this.allDetails.get(this.trueIndex);
/* 479 */           for (int i = 0; i < (this.jollyRoger.getDetails()).length; i++) {
/* 480 */             JollyRogerElement element = this.jollyRoger.getDetails()[i];
/* 481 */             if (element != null && ogElem != null && ogElem.equals(element)) {
/* 482 */               moveIndex(btn, toRight);
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/* 488 */         if (this.trueIndex >= 0 && this.trueIndex <= this.allDetails.size()) {
/* 489 */           this.jollyRoger.getDetails()[this.layerIndex] = this.allDetails.get(this.trueIndex);
/* 490 */         } else if (this.trueIndex <= 0 && this.jollyRoger.getDetails()[this.layerIndex].getTexture() != null) {
/* 491 */           this.jollyRoger.getDetails()[this.layerIndex] = null;
/*     */         } 
/* 493 */         this.nextElementTry = 0;
/*     */       } 
/*     */       
/* 496 */       updateButtons();
/*     */     }
/* 498 */     catch (Exception e) {
/*     */       
/* 500 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectButton(Button btn) {
/* 506 */     if (!(btn instanceof SimpleButton)) {
/*     */       return;
/*     */     }
/* 509 */     if (this.selectedButton != null)
/* 510 */       ((SimpleButton)this.selectedButton).select(); 
/* 511 */     this.selectedButton = (Widget)btn;
/* 512 */     ((SimpleButton)btn).select();
/* 513 */     this.animationTime = 0.0F;
/*     */     
/* 515 */     boolean hasLayerSet = false;
/*     */     
/* 517 */     if (this.field_230710_m_.get(0) == btn) {
/*     */       
/* 519 */       this.trueIndex = findIndex(getListFromType(JollyRogerElement.LayerType.BASE), this.jollyRoger.getBase(), this.player);
/* 520 */       this.layerType = JollyRogerElement.LayerType.BASE;
/* 521 */       this.layerIndex = 0;
/*     */       
/* 523 */       hasLayerSet = true;
/*     */     } 
/*     */     
/* 526 */     if (!hasLayerSet) {
/*     */       
/* 528 */       int j = 0;
/* 529 */       for (int i = 1; i < (this.jollyRoger.getBackgrounds()).length + 1; i++) {
/*     */         
/* 531 */         if (this.field_230710_m_.get(i) == btn) {
/*     */           
/* 533 */           this.trueIndex = findIndex(getListFromType(JollyRogerElement.LayerType.BACKGROUND), this.jollyRoger.getBackgrounds()[j], this.player);
/* 534 */           this.layerType = JollyRogerElement.LayerType.BACKGROUND;
/* 535 */           this.allBackgrounds = getTotalElementsForType(this.player, JollyRogerElement.LayerType.BACKGROUND);
/* 536 */           this.layerIndex = j;
/*     */           
/* 538 */           hasLayerSet = true;
/*     */         } 
/* 540 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 544 */     if (!hasLayerSet) {
/*     */       
/* 546 */       int j = 0;
/* 547 */       for (int i = (this.jollyRoger.getBackgrounds()).length + 1; i < (this.jollyRoger.getDetails()).length + (this.jollyRoger.getBackgrounds()).length + 1; i++) {
/*     */         
/* 549 */         if (this.field_230710_m_.get(i) == btn) {
/*     */           
/* 551 */           this.trueIndex = findIndex(getListFromType(JollyRogerElement.LayerType.DETAIL), this.jollyRoger.getDetails()[j], this.player);
/* 552 */           this.layerType = JollyRogerElement.LayerType.DETAIL;
/* 553 */           this.allDetails = getTotalElementsForType(this.player, JollyRogerElement.LayerType.DETAIL);
/* 554 */           this.layerIndex = j;
/*     */           
/* 556 */           hasLayerSet = true;
/*     */         } 
/* 558 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 562 */     updateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateButtons() {
/* 567 */     JollyRogerElement element = getLayerElement();
/*     */     
/* 569 */     if (element == null) {
/*     */ 
/*     */       
/* 572 */       ((Widget)this.field_230710_m_.get(this.field_230710_m_.size() - 1)).field_230694_p_ = false;
/* 573 */       ((Widget)this.field_230710_m_.get(this.field_230710_m_.size() - 2)).field_230694_p_ = false;
/*     */ 
/*     */       
/* 576 */       for (Widget widget : this.field_230710_m_)
/*     */       {
/* 578 */         if (widget instanceof Slider)
/*     */         {
/* 580 */           widget.field_230694_p_ = false;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 586 */       ((Widget)this.field_230710_m_.get(this.field_230710_m_.size() - 1)).field_230694_p_ = true;
/* 587 */       ((Widget)this.field_230710_m_.get(this.field_230710_m_.size() - 2)).field_230694_p_ = true;
/*     */       
/* 589 */       if (element == this.jollyRoger.getBase()) {
/*     */         
/* 591 */         ((Widget)this.field_230710_m_.get(this.field_230710_m_.size() - 1)).field_230694_p_ = false;
/* 592 */         ((Widget)this.field_230710_m_.get(this.field_230710_m_.size() - 2)).field_230694_p_ = false;
/*     */       } 
/*     */       
/* 595 */       if (!element.canBeColored()) {
/*     */         
/* 597 */         for (Widget widget : this.field_230710_m_)
/*     */         {
/* 599 */           if (widget instanceof Slider)
/*     */           {
/* 601 */             widget.field_230694_p_ = false;
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 607 */         for (Widget widget : this.field_230710_m_) {
/*     */           
/* 609 */           if (widget instanceof Slider)
/*     */           {
/* 611 */             widget.field_230694_p_ = true;
/*     */           }
/*     */         } 
/* 614 */         resetColorSliders(element);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private JollyRogerElement getLayerElement() {
/* 621 */     JollyRogerElement element = this.jollyRoger.getBase();
/*     */     
/* 623 */     if (this.layerType == JollyRogerElement.LayerType.BASE) {
/* 624 */       element = this.jollyRoger.getBase();
/* 625 */     } else if (this.layerType == JollyRogerElement.LayerType.BACKGROUND) {
/* 626 */       element = this.jollyRoger.getBackgrounds()[this.layerIndex];
/* 627 */     } else if (this.layerType == JollyRogerElement.LayerType.DETAIL) {
/* 628 */       element = this.jollyRoger.getDetails()[this.layerIndex];
/*     */     } 
/* 630 */     return element;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetColorSliders(JollyRogerElement element) {
/* 635 */     if (element != null) {
/*     */       
/* 637 */       this.redSlider.setValue(element.getRed());
/* 638 */       this.redSlider.updateSlider();
/* 639 */       this.greenSlider.setValue(element.getGreen());
/* 640 */       this.greenSlider.updateSlider();
/* 641 */       this.blueSlider.setValue(element.getBlue());
/* 642 */       this.blueSlider.updateSlider();
/*     */     }
/*     */     else {
/*     */       
/* 646 */       this.redSlider.setValue(255.0D);
/* 647 */       this.redSlider.updateSlider();
/* 648 */       this.greenSlider.setValue(255.0D);
/* 649 */       this.greenSlider.updateSlider();
/* 650 */       this.blueSlider.setValue(255.0D);
/* 651 */       this.blueSlider.updateSlider();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231175_as__() {
/* 658 */     WyNetwork.sendToServer(new CUpdateJollyRogerPacket(this.jollyRoger));
/* 659 */     super.func_231175_as__();
/*     */   }
/*     */   
/*     */   private int findIndex(List<JollyRogerElement> elements, JollyRogerElement element, PlayerEntity player) {
/* 663 */     for (int i = 0; i < elements.size(); i++) {
/* 664 */       if (((JollyRogerElement)elements.get(i)).equals(element)) {
/* 665 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 669 */     return -1;
/*     */   }
/*     */   
/*     */   public List<JollyRogerElement> getListFromType(JollyRogerElement.LayerType type) {
/* 673 */     if (type == JollyRogerElement.LayerType.BASE) {
/* 674 */       return this.allBases;
/*     */     }
/* 676 */     if (type == JollyRogerElement.LayerType.BACKGROUND) {
/* 677 */       return this.allBackgrounds;
/*     */     }
/* 679 */     if (type == JollyRogerElement.LayerType.DETAIL) {
/* 680 */       return this.allDetails;
/*     */     }
/*     */     
/* 683 */     return this.allBases;
/*     */   }
/*     */   
/*     */   public List<JollyRogerElement> getTotalElementsForType(PlayerEntity player, JollyRogerElement.LayerType type) {
/* 687 */     return (List<JollyRogerElement>)this.allElements.stream().filter(reg -> (reg.getLayerType() == type)).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public static void open(boolean isEditing, Crew crew, LinkedHashSet<JollyRogerElement> elements) {
/* 691 */     Minecraft.func_71410_x().func_147108_a(new JollyRogerCreatorScreen(isEditing, crew, elements));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\JollyRogerCreatorScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */