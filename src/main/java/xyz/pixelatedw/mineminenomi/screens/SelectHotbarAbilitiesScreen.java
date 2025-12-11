/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.MainWindow;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.INestedGuiEventHandler;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextComponentUtils;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.events.CombatModeEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CEquipAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CRemoveAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.AbilitySlotButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TextAnimatedButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.AbilitiesListScreenPanel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class SelectHotbarAbilitiesScreen<A extends IAbility> extends Screen implements INestedGuiEventHandler {
/*  59 */   private static final List<ITextComponent> EMPTY_TOOLTIPS = new ArrayList<>();
/*     */   
/*     */   protected PlayerEntity player;
/*     */   private AbilitiesListScreenPanel abilitiesList;
/*  63 */   public int groupSelected = 0;
/*  64 */   public int slotSelected = -1;
/*     */   
/*     */   public boolean isDirty;
/*     */   
/*     */   private int tickCount;
/*     */   private IAbilityData abilityDataProps;
/*  70 */   protected final List<AbilitySlotButton> abilitySlots = Lists.newArrayList();
/*  71 */   private final List<Widget> backgroundWidgets = new ArrayList<>();
/*  72 */   private final List<Widget> foregroundWidgets = new ArrayList<>();
/*     */   
/*  74 */   private static final Map<AbilityCore<? extends IAbility>, List<ITextComponent>> TOOLTIPS_CACHE = new HashMap<>();
/*     */   
/*  76 */   private static final int TOOLTIP_BACKGROUND_START = WyHelper.hexToRGB("#A78342").getRGB();
/*  77 */   private static final int TOOLTIP_BACKGROUND_END = WyHelper.hexToRGB("#AD8F58").getRGB();
/*  78 */   private static final int TOOLTIP_BORDER_START = WyHelper.hexToRGB("#e3b160").getRGB();
/*  79 */   private static final int TOOLTIP_BORDER_END = WyHelper.hexToRGB("#cb7e23").getRGB();
/*     */   
/*     */   private static boolean hasStatsShown = false;
/*     */   
/*     */   private static boolean clearCache = false;
/*     */   private Compactness compactness;
/*     */   private Selection selection;
/*     */   private boolean showTooltips;
/*  87 */   private AbilityCore<?> draggedAbility = null;
/*     */   
/*     */   public SelectHotbarAbilitiesScreen(PlayerEntity player) {
/*  90 */     super((ITextComponent)new StringTextComponent(""));
/*  91 */     this.player = player;
/*  92 */     this.field_230706_i_ = Minecraft.func_71410_x();
/*  93 */     this.compactness = ClientConfig.INSTANCE.getCompactness();
/*  94 */     this.selection = ClientConfig.INSTANCE.getSelectionMode();
/*  95 */     this.showTooltips = ClientConfig.INSTANCE.getTooltipsShown();
/*     */     
/*  97 */     this.abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 102 */     func_230446_a_(matrixStack);
/*     */ 
/*     */ 
/*     */     
/* 106 */     int posX = this.field_230708_k_;
/* 107 */     int posY = this.field_230709_l_;
/*     */     
/* 109 */     this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BOARD);
/*     */     
/* 111 */     func_230926_e_(func_230927_p_() + 100);
/* 112 */     func_238474_b_(matrixStack, (posX - 250) / 2, (posY - 230) / 2, 0, 0, 256, 256);
/* 113 */     func_238474_b_(matrixStack, (posX - 250) / 2, posY - 60, 0, 0, 256, 256);
/* 114 */     func_230926_e_(func_230927_p_() - 100);
/*     */     
/* 116 */     for (int i = 0; i < this.backgroundWidgets.size(); i++) {
/* 117 */       ((Widget)this.backgroundWidgets.get(i)).func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     }
/*     */     
/* 120 */     this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BLANK_NEW);
/*     */     
/* 122 */     this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BLANK_NEW);
/*     */     
/* 124 */     func_230926_e_(func_230927_p_() + 200);
/* 125 */     func_238474_b_(matrixStack, (posX - 250) / 2, (posY - 230) / 2, 0, 0, 256, 256);
/* 126 */     func_238474_b_(matrixStack, (posX - 250) / 2, posY - 60, 0, 0, 256, 256);
/* 127 */     func_230926_e_(func_230927_p_() - 200);
/*     */     
/* 129 */     String barId = (1 + this.groupSelected) + "";
/* 130 */     matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/* 131 */     WyHelper.drawStringWithBorder(this.field_230706_i_.field_71466_p, matrixStack, barId, (posX + 8) / 2 - this.field_230706_i_.field_71466_p.func_78256_a(barId) + 104, posY - 24, 16777215);
/* 132 */     matrixStack.func_227861_a_(0.0D, 0.0D, -250.0D);
/*     */ 
/*     */     
/* 135 */     if (this.abilitiesList != null) {
/* 136 */       matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/* 137 */       this.abilitiesList.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/* 138 */       matrixStack.func_227861_a_(0.0D, 0.0D, -250.0D);
/*     */     } 
/*     */     
/* 141 */     func_230926_e_(func_230927_p_() + 150); int j;
/* 142 */     for (j = 0; j < this.abilitySlots.size(); j++) {
/* 143 */       AbilitySlotButton slot = this.abilitySlots.get(j);
/* 144 */       slot.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     func_230926_e_(func_230927_p_() - 150);
/*     */     
/* 152 */     func_230926_e_(func_230927_p_() + 150);
/* 153 */     for (j = 0; j < this.foregroundWidgets.size(); j++) {
/* 154 */       ((Widget)this.foregroundWidgets.get(j)).func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     }
/* 156 */     func_230926_e_(func_230927_p_() - 150);
/*     */     
/* 158 */     if (hasDraggedAbility()) {
/* 159 */       RendererHelper.drawIcon(getDraggedAbility().getIcon(), matrixStack, mouseX, mouseY, 1.0F, 16.0F, 16.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231160_c_() {
/* 165 */     int posX2 = this.field_230708_k_ / 2;
/* 166 */     int posY2 = this.field_230709_l_ / 2;
/*     */     
/* 168 */     TOOLTIPS_CACHE.clear();
/* 169 */     this.groupSelected = this.abilityDataProps.getCombatBarSet();
/*     */     
/* 171 */     this.foregroundWidgets.clear();
/* 172 */     this.backgroundWidgets.clear();
/*     */     
/* 174 */     updateSlots();
/* 175 */     setupOptionsAndHelp();
/*     */ 
/*     */     
/* 178 */     int idx = 0;
/* 179 */     for (AbilityCategory category : AbilityCategory.values()) {
/* 180 */       if (category != AbilityCategory.ALL) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 189 */         AbilityCore<A> core = this.abilityDataProps.getUnlockedAbilities().stream().map(AbilityCoreUnlockWrapper::getAbilityCore).filter(category.isCorePartofCategory()).filter(AbilityCore::isVisible).findFirst().orElse(null);
/*     */         
/* 191 */         if (core != null) {
/* 192 */           boolean isFlipped = false;
/* 193 */           int iconOffset = 0;
/* 194 */           if (idx == 4) {
/* 195 */             posX2 += 250;
/* 196 */             posY2 -= 140;
/*     */           } 
/* 198 */           if (idx >= 4) {
/* 199 */             isFlipped = true;
/* 200 */             iconOffset = 4;
/*     */           } 
/* 202 */           int posY3 = posY2 - 100 + idx * 70 / 2;
/* 203 */           ResourceLocation icon = category.getIcon(this.player);
/* 204 */           if (icon == null) {
/* 205 */             IAbility abl = this.abilityDataProps.getEquippedOrPassiveAbility(core);
/* 206 */             if (abl != null) {
/* 207 */               icon = abl.getIcon((LivingEntity)this.player);
/*     */             } else {
/*     */               
/* 210 */               icon = core.getIcon();
/*     */             } 
/*     */           } 
/* 213 */           TexturedIconButton button = new TexturedIconButton(ModResources.BUTTON, posX2 - 145 + iconOffset, posY3, 40, 30, (ITextComponent)new StringTextComponent(""), btn -> updateListScreen(category));
/* 214 */           button = button.setTextureInfo(posX2 - 150, posY3 - 6, 55, 40).setFlipped(isFlipped).setIconInfo(icon, posX2 - 136 + iconOffset, posY3 + 2, 1.45D);
/* 215 */           this.backgroundWidgets.add(button);
/* 216 */           func_230480_a_((Widget)button);
/* 217 */           idx++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 221 */     updateListScreen(AbilityCategory.DEVIL_FRUITS);
/*     */   }
/*     */   
/*     */   public void updateSlots() {
/* 225 */     int posX = this.field_230708_k_;
/* 226 */     int posY = this.field_230709_l_;
/*     */     
/* 228 */     this.slotSelected = -1;
/*     */     
/* 230 */     this.field_230710_m_.removeIf(w -> (w instanceof AbilitySlotButton && this.abilitySlots.contains(w)));
/* 231 */     this.field_230705_e_.removeIf(w -> (w instanceof AbilitySlotButton && this.abilitySlots.contains(w)));
/* 232 */     this.abilitySlots.clear();
/*     */     
/* 234 */     posX += 10;
/*     */ 
/*     */     
/* 237 */     for (int i = 0; i < 8; i++) {
/* 238 */       RenderSystem.enableBlend();
/* 239 */       int id = i + this.groupSelected * 8;
/* 240 */       IAbility abl = this.abilityDataProps.getEquippedAbility(id);
/*     */       
/* 242 */       AbilitySlotButton slotButton = new AbilitySlotButton(abl, posX / 2 - 101 + i * 25, posY - 31, 22, 21, this.player, btn -> onClickAbilitySlot(btn, id));
/*     */       
/* 244 */       func_230481_d_((IGuiEventListener)slotButton);
/* 245 */       this.abilitySlots.add(slotButton);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setupOptionsAndHelp() {
/* 250 */     int posX = this.field_230708_k_;
/* 251 */     int posY = this.field_230709_l_;
/*     */     
/* 253 */     Button.ITooltip helpTooltip = (btn, matrix, mouseX, mouseY) -> {
/*     */         StringBuilder sb = new StringBuilder();
/*     */ 
/*     */         
/*     */         if (this.selection == Selection.DRAG_AND_DROP) {
/*     */           sb.append(ModI18n.HELP_ABILITY_SELECTOR_DRAG_AND_DROP.getString());
/*     */         } else if (this.selection == Selection.KEYBIND) {
/*     */           sb.append(ModI18n.HELP_ABILITY_SELECTOR_KEYBIND.getString());
/*     */         } 
/*     */         
/*     */         sb.append("\n\n");
/*     */         
/*     */         if (this.showTooltips) {
/*     */           sb.append(ModI18n.HELP_ABILITY_SELECTOR_HIDE_TOOLTIPS.getString());
/*     */         } else {
/*     */           sb.append(ModI18n.HELP_ABILITY_SELECTOR_SHOW_TOOLTIPS.getString());
/*     */         } 
/*     */         
/*     */         func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)new StringTextComponent(sb.toString()), Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY);
/*     */       };
/*     */     
/* 274 */     TextAnimatedButton helpButton = new TextAnimatedButton(posX - 20, this.field_230709_l_ - 20, 20, 20, (ITextComponent)new StringTextComponent("?"), btn -> {  }helpTooltip);
/* 275 */     if (ClientConfig.INSTANCE.getHelpButtonShown()) {
/* 276 */       this.foregroundWidgets.add(helpButton);
/* 277 */       func_230480_a_((Widget)helpButton);
/*     */     } 
/*     */     
/* 280 */     Button.ITooltip compactnessTooltip = (btn, matrix, mouseX, mouseY) -> {
/*     */         if (mouseY - 16 < 0) {
/*     */           mouseY = 16;
/*     */         }
/*     */         
/*     */         func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)new StringTextComponent(this.compactness.toString()), Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY);
/*     */       };
/* 287 */     Button compactnessButton = new Button(posX - 20, 0, 20, 20, (ITextComponent)new StringTextComponent(this.compactness.getShortNotation()), btn -> { this.compactness = this.compactness.nextElement(); this.abilitiesList.setCompactness(this.compactness); btn.func_238482_a_((ITextComponent)new StringTextComponent(this.compactness.getShortNotation())); ClientConfig.INSTANCE.setCompactness(this.compactness); helpButton.setMarked(); }compactnessTooltip);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     this.foregroundWidgets.add(compactnessButton);
/* 295 */     func_230480_a_((Widget)compactnessButton);
/*     */     
/* 297 */     Button.ITooltip selectionTooltip = (btn, matrix, mouseX, mouseY) -> {
/*     */         if (mouseY - 16 < 0) {
/*     */           mouseY = 16;
/*     */         }
/*     */         
/*     */         func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)new StringTextComponent(this.selection.toString()), Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY);
/*     */       };
/* 304 */     Button selectionButton = new Button(posX - 40, 0, 20, 20, (ITextComponent)new StringTextComponent(this.selection.getShortNotation()), btn -> { this.slotSelected = -1; for (AbilitySlotButton slot : this.abilitySlots) slot.setIsPressed(false);  this.selection = this.selection.nextElement(); btn.func_238482_a_((ITextComponent)new StringTextComponent(this.selection.getShortNotation())); ClientConfig.INSTANCE.setSelectionMode(this.selection); helpButton.setMarked(); }selectionTooltip);
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
/* 315 */     this.foregroundWidgets.add(selectionButton);
/* 316 */     func_230480_a_((Widget)selectionButton);
/*     */     
/* 318 */     Button.ITooltip showTooltipsTooltip = (btn, matrix, mouseX, mouseY) -> {
/*     */         if (mouseY - 16 < 0) {
/*     */           mouseY = 16;
/*     */         }
/*     */         
/*     */         func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)new StringTextComponent("Show tooltips"), Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY);
/*     */       };
/* 325 */     Button showTooltips = new Button(posX - 60, 0, 20, 20, (ITextComponent)new StringTextComponent(this.showTooltips ? "O" : "X"), btn -> { ClientConfig.INSTANCE.toggleShowingTooltips(); this.showTooltips = ClientConfig.INSTANCE.getTooltipsShown(); btn.func_238482_a_((ITextComponent)new StringTextComponent(this.showTooltips ? "O" : "X")); helpButton.setMarked(); }showTooltipsTooltip);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 331 */     this.foregroundWidgets.add(showTooltips);
/* 332 */     func_230480_a_((Widget)showTooltips);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClickAbilitySlot(Button btn, int id) {
/* 339 */     if (getSelectionMode() == Selection.DRAG_AND_DROP) {
/* 340 */       AbilitySlotButton slot = null;
/* 341 */       for (int btnid = 0; btnid < this.abilitySlots.size(); btnid++) {
/* 342 */         slot = this.abilitySlots.get(btnid);
/* 343 */         if (slot != null && slot == btn) {
/* 344 */           int slotId = btnid + this.groupSelected * 8;
/* 345 */           IAbility ability = this.abilityDataProps.getEquippedAbility(slotId);
/* 346 */           if (ability != null) {
/* 347 */             setDraggedAbility(ability.getCore());
/*     */           }
/* 349 */           WyNetwork.sendToServer(new CRemoveAbilityPacket(slotId));
/* 350 */           this.abilityDataProps.setEquippedAbility(slotId, null);
/* 351 */           ((AbilitySlotButton)btn).setAbility(null);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/* 358 */     if (this.slotSelected != id) {
/* 359 */       this.slotSelected = id;
/* 360 */       for (AbilitySlotButton slotBtn : this.abilitySlots) {
/* 361 */         slotBtn.setIsPressed(false);
/*     */       }
/* 363 */       ((AbilitySlotButton)btn).setIsPressed(true);
/*     */     } else {
/* 365 */       IAbility ability = this.abilityDataProps.getEquippedAbility(this.slotSelected);
/*     */       
/* 367 */       if (ability == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 372 */       if (ability.hasComponent(ModAbilityKeys.COOLDOWN) && ((CooldownComponent)ability.getComponent(ModAbilityKeys.COOLDOWN).get()).isOnCooldown())
/*     */         return; 
/* 374 */       if (ability.hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)ability.getComponent(ModAbilityKeys.DISABLE).get()).isDisabled())
/*     */         return; 
/* 376 */       if (ability.hasComponent(ModAbilityKeys.CONTINUOUS) && ((ContinuousComponent)ability.getComponent(ModAbilityKeys.CONTINUOUS).get()).isContinuous())
/*     */         return; 
/* 378 */       if (ability.hasComponent(ModAbilityKeys.CHARGE) && ((ChargeComponent)ability.getComponent(ModAbilityKeys.CHARGE).get()).isCharging()) {
/*     */         return;
/*     */       }
/*     */       
/* 382 */       WyNetwork.sendToServer(new CRemoveAbilityPacket(this.slotSelected));
/*     */       
/* 384 */       this.abilityDataProps.setEquippedAbility(this.slotSelected, null);
/*     */       
/* 386 */       ((AbilitySlotButton)btn).setAbility(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231023_e_() {
/* 396 */     if (this.isDirty && 
/* 397 */       this.tickCount++ >= 1) {
/* 398 */       int i = 0;
/* 399 */       for (AbilitySlotButton slotBtn : this.abilitySlots) {
/* 400 */         int id = i + this.groupSelected * 8;
/* 401 */         IAbility abl = this.abilityDataProps.getEquippedAbility(id);
/* 402 */         slotBtn.setAbility(abl);
/* 403 */         i++;
/*     */       } 
/* 405 */       this.isDirty = false;
/* 406 */       this.tickCount = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231046_a_(int key, int pScanCode, int pModifiers) {
/* 413 */     checkKeybinding(key);
/* 414 */     return super.func_231046_a_(key, pScanCode, pModifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/* 419 */     checkKeybinding(button);
/*     */ 
/*     */     
/* 422 */     if (button == 1) {
/* 423 */       int slotId = -1;
/* 424 */       AbilitySlotButton slot = null;
/*     */       
/* 426 */       for (int i = 0; i < this.abilitySlots.size(); i++) {
/* 427 */         slot = this.abilitySlots.get(i);
/* 428 */         if (slot != null && slot.func_231047_b_(mouseX, mouseY)) {
/* 429 */           slotId = i + this.groupSelected * 8;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 434 */       if (slot != null && slotId >= 0) {
/* 435 */         if (getSelectionMode() == Selection.KEYBIND && this.slotSelected >= 0) {
/* 436 */           WyNetwork.sendToServer(new CRemoveAbilityPacket(slotId));
/* 437 */           this.abilityDataProps.setEquippedAbility(slotId, null);
/* 438 */           ((AbilitySlotButton)this.abilitySlots.get(slotId % 8)).setAbility(null);
/* 439 */           this.slotSelected = -1;
/* 440 */           slot.setIsPressed(false);
/* 441 */         } else if (getSelectionMode() == Selection.DRAG_AND_DROP) {
/* 442 */           WyNetwork.sendToServer(new CRemoveAbilityPacket(slotId));
/* 443 */           this.abilityDataProps.setEquippedAbility(slotId, null);
/* 444 */           ((AbilitySlotButton)this.abilitySlots.get(slotId % 8)).setAbility(null);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 449 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double mouseX, double mouseY, int button) {
/* 454 */     if (getSelectionMode() == Selection.DRAG_AND_DROP && hasDraggedAbility() && button == 0) {
/* 455 */       for (int i = 0; i < this.abilitySlots.size(); i++) {
/* 456 */         AbilitySlotButton slot = this.abilitySlots.get(i);
/* 457 */         if (slot != null && slot.func_231047_b_(mouseX, mouseY)) {
/* 458 */           int id = i + this.groupSelected * 8;
/* 459 */           WyNetwork.sendToServer(new CEquipAbilityPacket(id, getDraggedAbility()));
/* 460 */           this.isDirty = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 464 */       setDraggedAbility((AbilityCore<?>)null);
/*     */     } 
/*     */     
/* 467 */     return true;
/*     */   }
/*     */   
/*     */   private void checkKeybinding(int key) {
/* 471 */     if (getSelectionMode() == Selection.KEYBIND) {
/* 472 */       for (int i = 0; i < ModKeybindings.keyBindsCombatbar.size(); i++) {
/* 473 */         KeyBinding bind = ModKeybindings.keyBindsCombatbar.get(i);
/* 474 */         if (bind.getKey().func_197937_c() == key) {
/* 475 */           i %= 8;
/* 476 */           int id = i + this.groupSelected * 8;
/* 477 */           this.slotSelected = id;
/*     */           
/* 479 */           AbilityCore<IAbility> core = this.abilitiesList.getHoveredEntry();
/* 480 */           if (core != null) {
/*     */             
/* 482 */             if (this.abilityDataProps.hasEquippedAbility(core)) {
/* 483 */               int slotId = this.abilityDataProps.getEquippedAbilitySlot(core);
/* 484 */               WyNetwork.sendToServer(new CRemoveAbilityPacket(slotId));
/* 485 */               this.abilityDataProps.setEquippedAbility(slotId, null);
/* 486 */               ((AbilitySlotButton)this.abilitySlots.get(i)).setAbility(null);
/*     */             } 
/*     */ 
/*     */             
/* 490 */             WyNetwork.sendToServer(new CEquipAbilityPacket(id, core));
/* 491 */             this.isDirty = true;
/*     */ 
/*     */             
/* 494 */             for (AbilitySlotButton slot : this.abilitySlots) {
/* 495 */               slot.setIsPressed(false);
/*     */             }
/* 497 */             ((AbilitySlotButton)this.abilitySlots.get(i)).setIsPressed(true);
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 505 */     if (key == ModKeybindings.nextCombatBar.getKey().func_197937_c()) {
/* 506 */       if (this.groupSelected < CombatModeEvents.Client.ABILITY_BARS - 1) {
/* 507 */         this.groupSelected++;
/*     */       } else {
/*     */         
/* 510 */         this.groupSelected = 0;
/*     */       } 
/* 512 */       updateSlots();
/*     */     }
/* 514 */     else if (key == ModKeybindings.prevCombatBar.getKey().func_197937_c()) {
/* 515 */       if (this.groupSelected > 0) {
/* 516 */         this.groupSelected--;
/*     */       } else {
/*     */         
/* 519 */         this.groupSelected = CombatModeEvents.Client.ABILITY_BARS - 1;
/*     */       } 
/* 521 */       updateSlots();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateListScreen(AbilityCategory category) {
/* 526 */     this.field_230705_e_.remove(this.abilitiesList);
/* 527 */     this.abilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, category);
/* 528 */     this.field_230705_e_.add(this.abilitiesList);
/* 529 */     func_231035_a_((IGuiEventListener)this.abilitiesList);
/*     */   }
/*     */   
/*     */   public static boolean canShowTooltips() {
/* 533 */     boolean canShowTooltips = ClientConfig.INSTANCE.getTooltipsShown();
/* 534 */     if (canShowTooltips) {
/* 535 */       if (ModKeybindings.isAltKeyDown()) {
/* 536 */         return false;
/*     */       }
/* 538 */       return true;
/*     */     } 
/* 540 */     if (ModKeybindings.isAltKeyDown()) {
/* 541 */       return true;
/*     */     }
/* 543 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<ITextComponent> generateAbilityTooltip(IAbility ability) {
/* 548 */     boolean canShowAdvancedTooltips = (Minecraft.func_71410_x()).field_71474_y.field_82882_x;
/* 549 */     boolean canShowStats = (!ClientConfig.INSTANCE.hidesAbilityStats() || ModKeybindings.isShiftKeyDown());
/* 550 */     Set<AbilityDescriptionLine> newSet = ability.getCore().getDescription();
/* 551 */     List<ITextComponent> tooltips = new ArrayList<>();
/* 552 */     ClientPlayerEntity player = (Minecraft.func_71410_x()).field_71439_g;
/*     */     
/* 554 */     tooltips.add(new StringTextComponent(ability.getDisplayName().getString() + ((newSet != null && !newSet.isEmpty()) ? "\n" : "")));
/* 555 */     if (newSet != null) {
/* 556 */       long advancedLines = newSet.stream().filter(line -> line.isAdvanced()).count();
/* 557 */       newSet.stream()
/* 558 */         .filter(line -> (!line.isAdvanced() || (line.isAdvanced() && canShowStats)))
/* 559 */         .map(line -> line.getTextComponent((LivingEntity)player, ability))
/* 560 */         .map(text -> {
/*     */             if (text == null) {
/*     */               return null;
/*     */             }
/*     */ 
/*     */             
/*     */             try {
/*     */               return TextComponentUtils.func_240645_a_(null, text, (Entity)player, 1);
/* 568 */             } catch (CommandSyntaxException e) {
/*     */               e.printStackTrace();
/*     */               
/*     */               return null;
/*     */             } 
/* 573 */           }).filter(Objects::nonNull)
/* 574 */         .forEach(tooltips::add);
/*     */       
/* 576 */       if (advancedLines > 0L && ClientConfig.INSTANCE.hidesAbilityStats() && !ModKeybindings.isShiftKeyDown()) {
/* 577 */         tooltips.add(AbilityDescriptionLine.NEW_LINE.expand((LivingEntity)player, ability));
/* 578 */         tooltips.add(ModI18n.GUI_SHOW_ABILITY_STATS.func_230530_a_(Style.field_240709_b_.func_240722_b_(Boolean.valueOf(true)).func_240712_a_(TextFormatting.YELLOW)));
/*     */       } 
/*     */     } 
/*     */     
/* 582 */     if (canShowAdvancedTooltips) {
/* 583 */       boolean isNew = false;
/* 584 */       if (ability instanceof Ability && ((Ability)ability).isNew) {
/* 585 */         isNew = true;
/*     */       }
/* 587 */       else if (ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2) {
/* 588 */         isNew = true;
/*     */       } 
/*     */       
/* 591 */       String color = "§c";
/*     */       
/* 593 */       if (isNew) {
/* 594 */         color = "§7";
/*     */       }
/*     */       
/* 597 */       ResourceLocation key = ability.getCore().getRegistryName();
/* 598 */       if (key != null) {
/* 599 */         tooltips.add(new StringTextComponent("\n" + color + key.toString()));
/*     */       }
/*     */     } 
/*     */     
/* 603 */     return tooltips;
/*     */   }
/*     */   
/*     */   public static void renderAbilityTooltip(MatrixStack matrixStack, int mouseX, int mouseY, IAbility ability) {
/* 607 */     Minecraft mc = Minecraft.func_71410_x();
/* 608 */     MainWindow window = mc.func_228018_at_();
/*     */     
/* 610 */     if (ClientConfig.INSTANCE.hidesAbilityStats()) {
/* 611 */       if (ModKeybindings.isShiftKeyDown() && !hasStatsShown) {
/* 612 */         clearCache = true;
/* 613 */         hasStatsShown = true;
/*     */       }
/* 615 */       else if (!ModKeybindings.isShiftKeyDown() && hasStatsShown) {
/* 616 */         clearCache = true;
/* 617 */         hasStatsShown = false;
/*     */       } 
/*     */       
/* 620 */       if (clearCache) {
/* 621 */         TOOLTIPS_CACHE.clear();
/* 622 */         clearCache = false;
/*     */       } 
/*     */     } 
/*     */     
/* 626 */     if (!TOOLTIPS_CACHE.containsKey(ability.getCore())) {
/* 627 */       TOOLTIPS_CACHE.putIfAbsent(ability.getCore(), generateAbilityTooltip(ability));
/*     */     }
/* 629 */     List<ITextComponent> tooltips = TOOLTIPS_CACHE.getOrDefault(ability.getCore(), EMPTY_TOOLTIPS);
/* 630 */     RendererHelper.drawAbilityTooltip(ability, matrixStack, tooltips, mouseX, mouseY, window.func_198107_o(), window.func_198087_p(), 210, TOOLTIP_BACKGROUND_START, TOOLTIP_BACKGROUND_END, TOOLTIP_BORDER_START, TOOLTIP_BORDER_END, mc.field_71466_p);
/* 631 */     RenderSystem.enableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231175_as__() {
/* 636 */     super.func_231175_as__();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 641 */     return false;
/*     */   }
/*     */   
/*     */   public Compactness getCompactness() {
/* 645 */     return this.compactness;
/*     */   }
/*     */   
/*     */   public Selection getSelectionMode() {
/* 649 */     return this.selection;
/*     */   }
/*     */   
/*     */   public boolean showTooltips() {
/* 653 */     return this.showTooltips;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AbilityCore<?> getDraggedAbility() {
/* 658 */     return this.draggedAbility;
/*     */   }
/*     */   
/*     */   public boolean hasDraggedAbility() {
/* 662 */     return (this.draggedAbility != null);
/*     */   }
/*     */   
/*     */   public void setDraggedAbility(AbilityCore<?> core) {
/* 666 */     this.draggedAbility = core;
/*     */   }
/*     */   
/*     */   public enum Selection implements Enumeration<Selection> {
/* 670 */     DRAG_AND_DROP,
/* 671 */     KEYBIND;
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 676 */       switch (this) {
/*     */         case COMPACT:
/* 678 */           return "Drag and Drop";
/*     */         case MINIMAL:
/* 680 */           return "Keybind";
/*     */       } 
/* 682 */       return "None";
/*     */     }
/*     */ 
/*     */     
/*     */     public String getShortNotation() {
/* 687 */       switch (this) {
/*     */         case COMPACT:
/* 689 */           return "D";
/*     */         case MINIMAL:
/* 691 */           return "K";
/*     */       } 
/* 693 */       return "-";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 699 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public Selection nextElement() {
/* 704 */       return values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Compactness implements Enumeration<Compactness> {
/* 709 */     SPATIOUS(1.25F, 16, 1.0F, 0, 4, 0, 0),
/* 710 */     COMPACT(1.05F, 16, 1.0F, 0, 4, 0, 0),
/* 711 */     MINIMAL(0.9F, 12, 0.8F, 5, 2, 5, 2);
/*     */     
/*     */     private final float spacing;
/*     */     
/*     */     private final int iconSize;
/*     */     private final float slotScale;
/*     */     private final int slotOffsetX;
/*     */     private final int slotOffsetY;
/*     */     private final int iconOffsetX;
/*     */     private final int iconOffsetY;
/*     */     
/*     */     Compactness(float spacing, int iconSize, float slotScale, int slotOffsetX, int slotOffsetY, int iconOffsetX, int iconOffsetY) {
/* 723 */       this.spacing = spacing;
/* 724 */       this.iconSize = iconSize;
/* 725 */       this.slotScale = slotScale;
/* 726 */       this.slotOffsetX = slotOffsetX;
/* 727 */       this.slotOffsetY = slotOffsetY;
/* 728 */       this.iconOffsetX = iconOffsetX;
/* 729 */       this.iconOffsetY = iconOffsetY;
/*     */     }
/*     */     
/*     */     public float getSpacing() {
/* 733 */       return this.spacing;
/*     */     }
/*     */     
/*     */     public int getIconSize() {
/* 737 */       return this.iconSize;
/*     */     }
/*     */     
/*     */     public float getSlotScale() {
/* 741 */       return this.slotScale;
/*     */     }
/*     */     
/*     */     public int getSlotOffsetX() {
/* 745 */       return this.slotOffsetX;
/*     */     }
/*     */     
/*     */     public int getSlotOffsetY() {
/* 749 */       return this.slotOffsetY;
/*     */     }
/*     */     
/*     */     public int getIconOffsetX() {
/* 753 */       return this.iconOffsetX;
/*     */     }
/*     */     
/*     */     public int getIconOffsetY() {
/* 757 */       return this.iconOffsetY;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 762 */       return WyHelper.capitalize(name());
/*     */     }
/*     */     
/*     */     public String getShortNotation() {
/* 766 */       switch (this) {
/*     */         case COMPACT:
/* 768 */           return "C";
/*     */         case MINIMAL:
/* 770 */           return "M";
/*     */         case SPATIOUS:
/* 772 */           return "S";
/*     */       } 
/* 774 */       return "S";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 780 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public Compactness nextElement() {
/* 785 */       return values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\SelectHotbarAbilitiesScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */