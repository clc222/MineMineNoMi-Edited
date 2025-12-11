/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.CharacterCreatorEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.CharacterCreatorSelectionMap;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.ICharacterCreatorEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CFinishCCPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.AbilitySlotButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.SimpleButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
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
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class CharacterCreatorScreen
/*     */   extends Screen
/*     */ {
/*     */   private final ClientPlayerEntity player;
/*  53 */   private ResourceLocation icon = ModResources.RANDOM;
/*  54 */   private ITextComponent label = (ITextComponent)ModI18n.GUI_RANDOM;
/*  55 */   private List<AbilityCore<?>> topAbilities = new ArrayList<>();
/*  56 */   private List<AbilityCore<?>> bottomAbilities = new ArrayList<>();
/*     */   private int page;
/*     */   private int maxOpt;
/*  59 */   private int renderTick = 0;
/*  60 */   private int minkTextureId = 0;
/*     */   
/*  62 */   private int[] options = new int[3];
/*     */   
/*     */   private TexturedIconButton factionButton;
/*     */   private TexturedIconButton raceButton;
/*     */   private TexturedIconButton styleButton;
/*     */   private TexturedIconButton minkUpButton;
/*     */   private TexturedIconButton minkDownButton;
/*  69 */   private final List<AbilitySlotButton> abilitySlots = Lists.newArrayList();
/*     */   
/*     */   private boolean hasRandomizedRace;
/*     */   private boolean allowMinkRaceSelect;
/*     */   
/*     */   public CharacterCreatorScreen(boolean hasRandomizedRace, boolean allowMinkRaceSelect) {
/*  75 */     super(StringTextComponent.field_240750_d_);
/*  76 */     this.field_230706_i_ = Minecraft.func_71410_x();
/*  77 */     this.player = this.field_230706_i_.field_71439_g;
/*  78 */     this.hasRandomizedRace = hasRandomizedRace;
/*  79 */     this.allowMinkRaceSelect = allowMinkRaceSelect;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/*  84 */     func_230446_a_(matrixStack);
/*     */     
/*  86 */     int posX = this.field_230708_k_ / 2;
/*  87 */     int posY = this.field_230709_l_ / 2;
/*     */ 
/*     */     
/*  90 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(ModResources.BOOK);
/*  91 */     matrixStack.func_227860_a_();
/*     */     
/*  93 */     matrixStack.func_227861_a_((posX - 120), (posY - 80), 0.0D);
/*  94 */     matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/*  95 */     matrixStack.func_227862_a_(1.2F, 1.2F, 1.2F);
/*  96 */     matrixStack.func_227861_a_(-128.0D, -128.0D, 0.0D);
/*     */     
/*  98 */     GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 0.0F);
/*  99 */     matrixStack.func_227865_b_();
/*     */     
/* 101 */     posX += 65;
/* 102 */     posY -= 20;
/*     */     
/* 104 */     if (!this.allowMinkRaceSelect && 
/* 105 */       this.renderTick % 150 == 0) {
/* 106 */       increaseMinkRace();
/*     */     }
/*     */ 
/*     */     
/* 110 */     drawCategoryIcon(matrixStack, this.icon, posX - 115, posY - 120, 0.7F);
/* 111 */     drawCategoryLabel(matrixStack, this.label, posX + 75, posY - 5, 1.5F);
/*     */     
/* 113 */     for (AbilitySlotButton btn : this.abilitySlots) {
/* 114 */       btn.func_230430_a_(matrixStack, x, y, f);
/*     */     }
/*     */     
/* 117 */     this.renderTick++;
/* 118 */     super.func_230430_a_(matrixStack, x, y, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231160_c_() {
/* 123 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 124 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */ 
/*     */ 
/*     */     
/* 128 */     posX += 65;
/* 129 */     posY -= 40;
/*     */     
/* 131 */     posX -= 65;
/* 132 */     posY += 5;
/*     */     
/* 134 */     this.factionButton = new TexturedIconButton(ModResources.BUTTON, posX - 85, posY + 65, 90, 40, (ITextComponent)ModI18n.FACTION_NAME, btn -> {
/*     */           this.page = 0;
/*     */           resetButtonState();
/*     */           this.factionButton.setIsPressed(true);
/*     */           this.minkDownButton.field_230694_p_ = false;
/*     */           this.minkUpButton.field_230694_p_ = false;
/*     */         });
/* 141 */     this.factionButton = this.factionButton.setTextureInfo(posX - 90, posY + 60, 100, 50).setTextInfo(posX - 10, posY + 128, 1.399999976158142D);
/* 142 */     func_230480_a_((Widget)this.factionButton);
/*     */     
/* 144 */     posY += 45;
/*     */     
/* 146 */     this.raceButton = new TexturedIconButton(ModResources.BUTTON, posX - 85, posY + 65, 90, 40, (ITextComponent)ModI18n.RACE_NAME, btn -> {
/*     */           this.page = 1;
/*     */           resetButtonState();
/*     */           this.raceButton.setIsPressed(true);
/*     */           applyMinkSwitcher();
/*     */         });
/* 152 */     this.raceButton = this.raceButton.setTextureInfo(posX - 90, posY + 60, 100, 50).setTextInfo(posX - 10, posY + 128, 1.399999976158142D);
/* 153 */     if (!this.hasRandomizedRace) {
/* 154 */       func_230480_a_((Widget)this.raceButton);
/* 155 */       posY += 45;
/*     */     } 
/*     */     
/* 158 */     this.styleButton = new TexturedIconButton(ModResources.BUTTON, posX - 85, posY + 65, 90, 40, (ITextComponent)ModI18n.STYLE_NAME, btn -> {
/*     */           this.page = 2;
/*     */           resetButtonState();
/*     */           this.styleButton.setIsPressed(true);
/*     */           this.minkDownButton.field_230694_p_ = false;
/*     */           this.minkUpButton.field_230694_p_ = false;
/*     */         });
/* 165 */     this.styleButton = this.styleButton.setTextureInfo(posX - 90, posY + 60, 100, 50).setTextInfo(posX - 10, posY + 128, 1.399999976158142D);
/* 166 */     func_230480_a_((Widget)this.styleButton);
/*     */     
/* 168 */     if (this.hasRandomizedRace) {
/* 169 */       posY += 45;
/*     */     }
/*     */     
/* 172 */     posX += 65;
/* 173 */     posY -= 95;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     posX += 30;
/* 214 */     posY += 130;
/*     */ 
/*     */     
/* 217 */     TexturedIconButton prevButton = new TexturedIconButton(ModResources.RED_ARROW_LEFT, posX + 60, posY + 80, 40, 20, StringTextComponent.field_240750_d_, btn -> {
/*     */           decreaseSelectedPage();
/*     */           applyMinkSwitcher();
/*     */         });
/* 221 */     prevButton = prevButton.setTextureInfo(posX + 62, posY + 82, 35, 17);
/* 222 */     func_230480_a_((Widget)prevButton);
/*     */     
/* 224 */     posX -= 90;
/* 225 */     TexturedIconButton nextButton = new TexturedIconButton(ModResources.RED_ARROW_RIGHT, posX + 215, posY + 80, 40, 20, StringTextComponent.field_240750_d_, btn -> {
/*     */           increaseSelectedPage();
/*     */           applyMinkSwitcher();
/*     */         });
/* 229 */     nextButton = nextButton.setTextureInfo(posX + 218, posY + 82, 35, 17);
/* 230 */     func_230480_a_((Widget)nextButton);
/*     */     
/* 232 */     posX += 30;
/* 233 */     posY -= 110;
/*     */ 
/*     */     
/* 236 */     this.minkDownButton = new TexturedIconButton(ModResources.RED_ARROW_LEFT, posX + 100, posY + 110, 30, 50, StringTextComponent.field_240750_d_, btn -> decreaseMinkRace());
/* 237 */     this.minkDownButton = this.minkDownButton.setTextureInfo(posX + 100, posY + 110, 30, 50);
/* 238 */     ((TexturedIconButton)func_230480_a_((Widget)this.minkDownButton)).field_230694_p_ = false;
/*     */     
/* 240 */     posX -= 5;
/*     */ 
/*     */     
/* 243 */     this.minkUpButton = new TexturedIconButton(ModResources.RED_ARROW_RIGHT, posX + 215, posY + 110, 30, 50, StringTextComponent.field_240750_d_, btn -> increaseMinkRace());
/* 244 */     this.minkUpButton = this.minkUpButton.setTextureInfo(posX + 215, posY + 110, 30, 50);
/* 245 */     ((TexturedIconButton)func_230480_a_((Widget)this.minkUpButton)).field_230694_p_ = false;
/*     */     
/* 247 */     posX -= 120;
/* 248 */     posY += 30;
/*     */ 
/*     */     
/* 251 */     SimpleButton finishButton = new SimpleButton(posX + 115, posY + 175, 70, 30, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_FINISH), btn -> completeCreation());
/* 252 */     finishButton.setTextOnly();
/* 253 */     finishButton.setFontSize(1.5F);
/* 254 */     func_230480_a_((Widget)finishButton);
/*     */     
/* 256 */     createAbilitySlotButtons();
/*     */   }
/*     */   
/*     */   private void applyMinkSwitcher() {
/* 260 */     if (this.allowMinkRaceSelect && isOnMinkPage()) {
/* 261 */       this.minkDownButton.field_230694_p_ = true;
/* 262 */       this.minkUpButton.field_230694_p_ = true;
/*     */     } else {
/*     */       
/* 265 */       this.minkDownButton.field_230694_p_ = false;
/* 266 */       this.minkUpButton.field_230694_p_ = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isOnMinkPage() {
/* 271 */     return (this.page == 1 && getSelectedRaceId() == 4);
/*     */   }
/*     */   
/*     */   private void resetButtonState() {
/* 275 */     this.factionButton.setIsPressed(false);
/* 276 */     this.raceButton.setIsPressed(false);
/* 277 */     this.styleButton.setIsPressed(false);
/* 278 */     updateSelection();
/*     */   }
/*     */   
/*     */   private void completeCreation() {
/* 282 */     Minecraft.func_71410_x().func_147108_a(null);
/* 283 */     WyNetwork.sendToServer(new CFinishCCPacket(getSelectedFactionId() - 1, getSelectedRaceId() - 1, getSelectedStyleId() - 1, this.minkTextureId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231023_e_() {
/* 288 */     this.maxOpt = CharacterCreatorEntry.values()[this.page].getSize() + 1;
/*     */   }
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 292 */     return false;
/*     */   }
/*     */   
/*     */   private void drawCategory(MatrixStack matrixStack, String text, int posX, int posY, float scale) {
/* 296 */     drawCategoryLabel(matrixStack, (ITextComponent)new StringTextComponent(text), posX, posY, scale);
/*     */   }
/*     */   
/*     */   private void drawCategoryIcon(MatrixStack matrixStack, ResourceLocation icon, int posX, int posY, float scale) {
/* 300 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(icon);
/* 301 */     matrixStack.func_227860_a_();
/* 302 */     matrixStack.func_227861_a_(posX, posY, 2.0D);
/* 303 */     matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/* 304 */     matrixStack.func_227862_a_(scale, scale, 1.0F);
/* 305 */     matrixStack.func_227861_a_(-128.0D, -128.0D, 0.0D);
/* 306 */     GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 1.0F);
/* 307 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   private void drawCategoryLabel(MatrixStack matrixStack, ITextComponent text, int posX, int posY, float scale) {
/* 311 */     matrixStack.func_227860_a_();
/* 312 */     matrixStack.func_227861_a_(posX, posY, 2.0D);
/* 313 */     matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/* 314 */     matrixStack.func_227862_a_(scale, scale, 1.0F);
/* 315 */     matrixStack.func_227861_a_(-128.0D, -128.0D, 0.0D);
/* 316 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, text, -this.field_230712_o_.func_238414_a_((ITextProperties)text) / 2, 0, -1);
/* 317 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   public int getSelectedFactionId() {
/* 321 */     return this.options[0];
/*     */   }
/*     */   
/*     */   public int getSelectedRaceId() {
/* 325 */     return this.options[1];
/*     */   }
/*     */   
/*     */   public int getSelectedStyleId() {
/* 329 */     return this.options[2];
/*     */   }
/*     */   
/*     */   public int getSelectedId() {
/* 333 */     return this.options[this.page];
/*     */   }
/*     */   
/*     */   public void increaseSelectedPage() {
/* 337 */     if (this.options[this.page] + 1 < this.maxOpt) {
/* 338 */       this.options[this.page] = this.options[this.page] + 1;
/*     */     } else {
/*     */       
/* 341 */       this.options[this.page] = 0;
/*     */     } 
/*     */     
/* 344 */     updateSelection();
/*     */   }
/*     */   
/*     */   public void decreaseSelectedPage() {
/* 348 */     if (this.options[this.page] - 1 > -1) {
/* 349 */       this.options[this.page] = this.options[this.page] - 1;
/*     */     } else {
/*     */       
/* 352 */       this.options[this.page] = this.maxOpt - 1;
/*     */     } 
/*     */     
/* 355 */     updateSelection();
/*     */   }
/*     */   
/*     */   private void updateSelection() {
/* 359 */     CharacterCreatorEntry value = CharacterCreatorEntry.values()[this.page];
/* 360 */     ICharacterCreatorEntry entry = (getSelectedId() == 0) ? ICharacterCreatorEntry.RANDOM : value.getInfo(getSelectedId() - 1);
/* 361 */     CharacterCreatorSelectionMap.SelectionInfo info = entry.getInfo();
/*     */     
/* 363 */     if (info != null) {
/* 364 */       if (entry instanceof RaceId && !((RaceId)entry).getSubRaces().isEmpty()) {
/* 365 */         updateMinkSelection();
/*     */       } else {
/* 367 */         this.icon = info.getIcon();
/*     */       } 
/*     */       
/* 370 */       if (getSelectedId() == 0) {
/* 371 */         this.label = (ITextComponent)ModI18n.GUI_RANDOM;
/*     */       } else {
/* 373 */         this.label = value.getLocalizedTitle(entry.getRegistryName());
/*     */       } 
/* 375 */       this.topAbilities = info.getTopAbilities();
/* 376 */       this.bottomAbilities = info.getBottomAbilities();
/* 377 */       createAbilitySlotButtons();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createAbilitySlotButtons() {
/* 382 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 383 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */     
/* 385 */     this.abilitySlots.clear();
/*     */     
/* 387 */     int i = 0;
/* 388 */     int xOffset = 0;
/* 389 */     int yOffset = 0;
/*     */     
/* 391 */     for (AbilityCore<?> core : this.topAbilities) {
/* 392 */       if (i % 3 == 0) {
/* 393 */         xOffset = 0;
/* 394 */         yOffset += 30;
/*     */       } else {
/*     */         
/* 397 */         xOffset += 38;
/*     */       } 
/* 399 */       IAbility abl = core.createAbility();
/* 400 */       AbilitySlotButton slot = new AbilitySlotButton(abl, posX + 20 + xOffset, posY + 10 + yOffset, 22, 22, (PlayerEntity)this.player, btn -> { 
/* 401 */           }); this.abilitySlots.add(slot);
/* 402 */       func_230481_d_((IGuiEventListener)slot);
/* 403 */       i++;
/*     */     } 
/*     */     
/* 406 */     i = 0;
/* 407 */     xOffset = 0;
/* 408 */     yOffset = 0;
/*     */     
/* 410 */     for (AbilityCore<?> core : this.bottomAbilities) {
/* 411 */       if (i % 3 == 0) {
/* 412 */         xOffset = 0;
/* 413 */         yOffset += 30;
/*     */       } else {
/*     */         
/* 416 */         xOffset += 38;
/*     */       } 
/* 418 */       IAbility abl = core.createAbility();
/* 419 */       AbilitySlotButton slot = new AbilitySlotButton(abl, posX + 20 + xOffset, posY + 100 + yOffset, 22, 22, (PlayerEntity)this.player, btn -> { 
/* 420 */           }); this.abilitySlots.add(slot);
/* 421 */       func_230481_d_((IGuiEventListener)slot);
/* 422 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void increaseMinkRace() {
/* 427 */     if (this.minkTextureId == getCurrentSubRaces().size() - 1) {
/* 428 */       this.minkTextureId = 0;
/*     */     } else {
/*     */       
/* 431 */       this.minkTextureId++;
/*     */     } 
/*     */     
/* 434 */     updateMinkSelection();
/*     */   }
/*     */   
/*     */   private List<String> getCurrentSubRaces() {
/* 438 */     if (getCurrentInfo() instanceof RaceId) {
/* 439 */       return ((RaceId)getCurrentInfo()).getSubRaces();
/*     */     }
/* 441 */     return new ArrayList<>();
/*     */   }
/*     */   
/*     */   private ICharacterCreatorEntry getCurrentInfo() {
/* 445 */     if (getSelectedRaceId() == 0) {
/* 446 */       return ICharacterCreatorEntry.RANDOM;
/*     */     }
/* 448 */     return CharacterCreatorEntry.values()[this.page].getInfo(getSelectedId() - 1);
/*     */   }
/*     */   
/*     */   public void decreaseMinkRace() {
/* 452 */     if (this.minkTextureId == 0) {
/* 453 */       this.minkTextureId = getCurrentSubRaces().size() - 1;
/*     */     } else {
/*     */       
/* 456 */       this.minkTextureId--;
/*     */     } 
/*     */     
/* 459 */     updateMinkSelection();
/*     */   }
/*     */   
/*     */   private void updateMinkSelection() {
/* 463 */     ResourceLocation race = getCurrentInfo().getRegistryName();
/* 464 */     this.icon = new ResourceLocation(race.func_110624_b(), "textures/gui/icons/" + race.func_110623_a() + (this.minkTextureId + 1) + ".png");
/*     */   }
/*     */   
/*     */   public static void open(boolean hasRandomizedRace, boolean allowMinkRaceSelect) {
/* 468 */     Minecraft.func_71410_x().func_147108_a(new CharacterCreatorScreen(hasRandomizedRace, allowMinkRaceSelect));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\CharacterCreatorScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */