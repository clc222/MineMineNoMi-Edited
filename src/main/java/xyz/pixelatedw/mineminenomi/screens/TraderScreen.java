/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*     */ import net.minecraft.client.gui.widget.TextFieldWidget;
/*     */ import net.minecraft.client.gui.widget.ToggleWidget;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.trade.CBuyFromTraderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.trade.CSellToTraderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.FlickeringString;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.FactionButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.ItemListScreenPanel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class TraderScreen extends Screen {
/*  49 */   private int guiState = 0;
/*  50 */   private int wantedAmount = 1;
/*     */   
/*     */   private ItemListScreenPanel listPanel;
/*     */   private TradeEntry selectedStack;
/*     */   private TradeEntry previousStack;
/*     */   private TradeEntry hoveredStack;
/*     */   private PlayerEntity player;
/*     */   private TraderEntity trader;
/*     */   private IEntityStats props;
/*     */   private SequencedString startMessage;
/*     */   private FlickeringString skipMessage;
/*     */   private ToggleWidget vearthAmountUp;
/*     */   private ToggleWidget vearthAmountDown;
/*     */   private int dirtBlocksAvailable;
/*     */   private TextFieldWidget quantityEdit;
/*  65 */   protected static final ResourceLocation RECIPE_BOOK = new ResourceLocation("textures/gui/recipe_book.png");
/*     */ 
/*     */   
/*     */   public TraderScreen(TraderEntity entity) {
/*  69 */     super((ITextComponent)new StringTextComponent(""));
/*  70 */     this.trader = entity;
/*  71 */     this.player = (PlayerEntity)(Minecraft.func_71410_x()).field_71439_g;
/*  72 */     this.props = EntityStatsCapability.get((LivingEntity)this.player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  78 */     func_230446_a_(matrixStack);
/*  79 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  81 */     switch (this.guiState) {
/*     */       
/*     */       case 0:
/*  84 */         renderMenu(matrixStack, mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 1:
/*  87 */         renderSellShop(matrixStack, mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 2:
/*  90 */         renderBuyShop(matrixStack, mouseX, mouseY, partialTicks);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  96 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderMenu(MatrixStack matrixStack, int x, int y, float partialTicks) {
/* 101 */     int posX = this.field_230708_k_ / 2;
/* 102 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 104 */     if (!this.trader.canBuyFromPlayers()) {
/*     */       
/* 106 */       this.startMessage.render(matrixStack, posX - 200, posY - 50, partialTicks);
/* 107 */       this.skipMessage.render(matrixStack, posX - 100, posY + 60, partialTicks);
/*     */       
/* 109 */       if (this.startMessage.tickCount * partialTicks > this.startMessage.delayTicks)
/*     */       {
/*     */ 
/*     */         
/* 113 */         if (this.trader.canTrade(this.player)) {
/*     */           
/* 115 */           this.guiState = 1;
/* 116 */           func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */         } else {
/*     */           
/* 119 */           func_231175_as__();
/*     */         }
/*     */       
/*     */       }
/*     */     } else {
/*     */       
/* 125 */       this.startMessage.render(matrixStack, posX - 150, posY - 105, partialTicks);
/*     */     } 
/*     */ 
/*     */     
/* 129 */     RenderSystem.pushMatrix();
/*     */     
/* 131 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 132 */     RenderSystem.enableBlend();
/* 133 */     InventoryScreen.func_228187_a_(posX + 150, posY + 150, 100, 40.0F, 5.0F, (LivingEntity)this.trader);
/*     */     
/* 135 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderSellShop(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 141 */     getMinecraft().func_110434_K().func_110577_a(ModResources.BLANK2);
/*     */     
/* 143 */     int posX = this.field_230708_k_ / 2;
/* 144 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 146 */     GuiUtils.drawTexturedModalRect(matrixStack, posX - 128, posY - 110, 0, 0, 256, 256, 0.0F);
/*     */     
/* 148 */     renderUpperColumn(matrixStack);
/*     */     
/* 150 */     drawSizedString(matrixStack, (new TranslationTextComponent(ModI18n.GUI_NAME)).getString(), posX - 20, posY - 63, 0.9F, -1);
/* 151 */     drawSizedString(matrixStack, (new TranslationTextComponent(ModI18n.GUI_PRICE)).getString(), posX + 50, posY - 63, 0.9F, -1);
/* 152 */     getMinecraft().func_110434_K().func_110577_a(ModResources.CURRENCIES);
/* 153 */     int type = (this.trader.getCurrency() == Currency.BELLY) ? 0 : 34;
/* 154 */     GuiUtils.drawTexturedModalRect(matrixStack, posX + 53, posY - 76, type, 32, 32, 64, 1.0F);
/*     */     
/* 156 */     this.listPanel.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     
/* 158 */     hover(mouseX, mouseY);
/*     */     
/* 160 */     if (this.selectedStack != null)
/*     */     {
/* 162 */       this.field_230710_m_.forEach(button -> button.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderBuyShop(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 170 */     int posX = this.field_230708_k_ / 2;
/* 171 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 173 */     this.startMessage.render(matrixStack, posX - 150, posY - 105, partialTicks);
/*     */     
/* 175 */     posX -= 110;
/* 176 */     if (this.trader instanceof SkypieanTraderEntity && ((SkypieanTraderEntity)this.trader).getTradesLeft() > 0) {
/*     */       
/* 178 */       renderItem(new ItemStack((IItemProvider)Blocks.field_150346_d.func_199767_j()), posX, posY - 30);
/* 179 */       String amount = this.wantedAmount + "";
/* 180 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, amount, posX + 8 - this.field_230712_o_.func_78256_a(amount) / 2, posY - 7, -1);
/* 181 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "=", posX + 60, posY - 7, -1);
/* 182 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, CurrencyHelper.getExtolFromBelly(this.wantedAmount) + "", posX + 100, posY - 7, -1);
/* 183 */       getMinecraft().func_110434_K().func_110577_a(ModResources.CURRENCIES);
/* 184 */       GuiUtils.drawTexturedModalRect(matrixStack, posX + 75, posY - 21, 34, 32, 32, 64, 1.0F);
/*     */     } 
/* 186 */     posX += 110;
/*     */     
/* 188 */     RenderSystem.pushMatrix();
/*     */     
/* 190 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 191 */     RenderSystem.enableBlend();
/* 192 */     InventoryScreen.func_228187_a_(posX + 150, posY + 150, 100, 40.0F, 5.0F, (LivingEntity)this.trader);
/*     */     
/* 194 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderUpperColumn(MatrixStack matrixStack) {
/* 199 */     int posX = this.field_230708_k_ / 2;
/* 200 */     int posY = this.field_230709_l_ / 2;
/* 201 */     String amount = "";
/*     */     
/* 203 */     if (this.hoveredStack != null) {
/*     */       
/* 205 */       this.quantityEdit.func_230430_a_(matrixStack, 0, 0, 0.0F);
/* 206 */       RendererHelper.drawIcon(ModResources.BLANK, matrixStack, (posX - 117), (posY - 105), 1.0F, 32.0F, 42.0F);
/* 207 */       renderItem(this.hoveredStack.getItemStack(), posX - 110, posY - 100);
/* 208 */       amount = "/" + this.hoveredStack.getCount();
/* 209 */       if (this.hoveredStack.hasInfiniteStock()) {
/* 210 */         amount = "/∞";
/*     */       }
/* 212 */     } else if (getSelectedStack() != null) {
/*     */       
/* 214 */       this.quantityEdit.func_230430_a_(matrixStack, 0, 0, 0.0F);
/* 215 */       RendererHelper.drawIcon(ModResources.BLANK, matrixStack, (posX - 117), (posY - 105), 1.0F, 32.0F, 42.0F);
/* 216 */       renderItem(getSelectedStack().getItemStack(), posX - 110, posY - 100);
/* 217 */       amount = "/" + getSelectedStack().getCount();
/* 218 */       if (getSelectedStack().hasInfiniteStock()) {
/* 219 */         amount = "/∞";
/*     */       }
/*     */     } 
/* 222 */     long currency = (this.trader.getCurrency() == Currency.BELLY) ? this.props.getBelly() : this.props.getExtol();
/* 223 */     matrixStack.func_227860_a_();
/* 224 */     matrixStack.func_227862_a_(1.2F, 1.2F, 1.2F);
/* 225 */     drawSizedString(matrixStack, amount, posX - 50, posY - 94, 0.9F, -1);
/* 226 */     matrixStack.func_227865_b_();
/* 227 */     drawSizedString(matrixStack, currency + "", posX + 85, posY - 95, 0.9F, -1);
/* 228 */     getMinecraft().func_110434_K().func_110577_a(ModResources.CURRENCIES);
/* 229 */     int type = (this.trader.getCurrency() == Currency.BELLY) ? 0 : 34;
/* 230 */     GuiUtils.drawTexturedModalRect(matrixStack, posX + 102, posY - 108, type, 32, 32, 64, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231158_b_(Minecraft mc, int width, int height) {
/* 236 */     super.func_231158_b_(mc, width, height);
/*     */     
/* 238 */     int posX = this.field_230708_k_ / 2;
/* 239 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 241 */     this.startMessage = new SequencedString("", 0, 0);
/*     */     
/* 243 */     if (this.skipMessage == null) {
/* 244 */       this.skipMessage = new FlickeringString("- " + (new TranslationTextComponent(ModI18n.GUI_CLICK_TO_SKIP)).getString() + " -", 20);
/*     */     }
/* 246 */     if (this.guiState == 0) {
/*     */       
/* 248 */       this.wantedAmount = 1;
/*     */       
/* 250 */       String message = "";
/* 251 */       if (this.trader.canTrade(this.player)) {
/* 252 */         message = (new TranslationTextComponent(ModI18n.TRADER_WELCOME_MESSAGE)).getString();
/*     */       } else {
/* 254 */         message = this.trader.getTradeFailMessage();
/*     */       } 
/* 256 */       this.startMessage = new SequencedString(message, 250, this.field_230712_o_.func_78256_a(message) / 2);
/*     */       
/* 258 */       if (this.trader.canBuyFromPlayers())
/*     */       {
/* 260 */         FactionButton buyButton = new FactionButton(posX - 180, posY - 50, 100, 20, (ITextComponent)new TranslationTextComponent("gui.buy"), btn -> {
/*     */               this.guiState = 1;
/*     */               
/*     */               func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */             });
/* 265 */         func_230480_a_((Widget)buyButton);
/*     */         
/* 267 */         FactionButton sellButton = new FactionButton(posX - 180, posY - 20, 100, 20, (ITextComponent)new TranslationTextComponent("gui.sell"), btn -> {
/*     */               this.guiState = 2;
/*     */               
/*     */               func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */             });
/* 272 */         func_230480_a_((Widget)sellButton);
/*     */       }
/*     */     
/* 275 */     } else if (this.guiState == 1) {
/*     */       
/* 277 */       this.listPanel = new ItemListScreenPanel(this, this.trader.getTradingItems());
/* 278 */       this.field_230705_e_.add(this.listPanel);
/*     */ 
/*     */       
/* 281 */       this.quantityEdit = new TextFieldWidget(this.field_230712_o_, posX - 80, posY - 100, 20, 20, (ITextComponent)new StringTextComponent(""));
/* 282 */       this.quantityEdit.func_146203_f(2);
/* 283 */       this.quantityEdit.func_146191_b("1");
/* 284 */       this.field_230705_e_.add(this.quantityEdit);
/*     */ 
/*     */       
/* 287 */       TexturedIconButton buyBtn = new TexturedIconButton(ModResources.BLANK, posX - 10, posY - 100, 64, 22, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_BUY), this::onBuy);
/* 288 */       buyBtn = buyBtn.setTextureInfo(posX - 10, posY - 100, 64, 32).setTextInfo(posX + 10, posY - 95, 1.0D);
/* 289 */       func_230480_a_((Widget)buyBtn);
/*     */       
/* 291 */       if (this.trader.canBuyFromPlayers())
/*     */       {
/* 293 */         FactionButton backButton = new FactionButton(posX - 200, posY + 78, 70, 20, (ITextComponent)new TranslationTextComponent("gui.cancel"), btn -> {
/*     */               this.guiState = 0;
/*     */               
/*     */               func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */             });
/* 298 */         func_230480_a_((Widget)backButton);
/*     */       }
/*     */     
/* 301 */     } else if (this.guiState == 2 && this.trader instanceof SkypieanTraderEntity) {
/*     */ 
/*     */       
/* 304 */       this.wantedAmount = 0;
/* 305 */       SkypieanTraderEntity skypieanTrader = (SkypieanTraderEntity)this.trader;
/* 306 */       String message = (new TranslationTextComponent(ModI18n.TRADER_SKYPIEAN_VEARTH, new Object[] { Long.valueOf(10000L), Long.valueOf(skypieanTrader.getExtolLeftInStock()) })).getString();
/* 307 */       if (skypieanTrader.getTradesLeft() <= 0)
/* 308 */         message = (new TranslationTextComponent(ModI18n.TRADER_SKYPIEAN_NO_EXTOL)).getString(); 
/* 309 */       this.startMessage = new SequencedString(message, 250, this.field_230712_o_.func_78256_a(message) / 3, 5000000);
/*     */       
/* 311 */       if (skypieanTrader.getTradesLeft() > 0) {
/*     */         
/* 313 */         FactionButton tradeButton = new FactionButton(posX - 80, posY + 20, 70, 20, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_SELL), btn -> {
/*     */               WyNetwork.sendToServer(new CSellToTraderPacket(this.trader.func_145782_y(), this.wantedAmount));
/*     */               
/*     */               Minecraft.func_71410_x().func_147108_a(null);
/*     */             });
/* 318 */         func_230480_a_((Widget)tradeButton);
/*     */       } 
/*     */       
/* 321 */       FactionButton backButton = new FactionButton(posX - 200, posY + 78, 70, 20, (ITextComponent)new TranslationTextComponent("gui.cancel"), btn -> {
/*     */             this.guiState = 0;
/*     */             
/*     */             func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */           });
/* 326 */       func_230480_a_((Widget)backButton);
/*     */       
/* 328 */       this.dirtBlocksAvailable = 0;
/* 329 */       for (int i = 0; i < this.player.field_71071_by.func_70302_i_(); i++) {
/*     */         
/* 331 */         ItemStack stack = this.player.field_71071_by.func_70301_a(i);
/* 332 */         if (stack != null && !stack.func_190926_b() && stack.func_77973_b() == Blocks.field_150346_d.func_199767_j())
/*     */         {
/* 334 */           this.dirtBlocksAvailable += stack.func_190916_E();
/*     */         }
/*     */       } 
/*     */       
/* 338 */       this.vearthAmountUp = new ToggleWidget(posX - 80, posY - 11, 12, 17, false);
/* 339 */       this.vearthAmountUp.func_191751_a(1, 208, 13, 18, RECIPE_BOOK);
/*     */       
/* 341 */       this.vearthAmountDown = new ToggleWidget(posX - 135, posY - 11, 12, 17, true);
/* 342 */       this.vearthAmountDown.func_191751_a(1, 208, 13, 18, RECIPE_BOOK);
/*     */       
/* 344 */       if (skypieanTrader.getTradesLeft() > 0) {
/*     */         
/* 346 */         func_230480_a_((Widget)this.vearthAmountUp);
/* 347 */         func_230480_a_((Widget)this.vearthAmountDown);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231152_a_(Minecraft minecraft, int x, int y) {
/* 355 */     if (this.quantityEdit != null) {
/* 356 */       String crewName = this.quantityEdit.func_146179_b();
/* 357 */       func_231158_b_(minecraft, x, y);
/* 358 */       this.quantityEdit.func_146191_b(crewName);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231023_e_() {
/* 364 */     if (this.quantityEdit != null && getSelectedStack() != null) {
/* 365 */       this.quantityEdit.func_146178_a();
/*     */       try {
/* 367 */         int tempAmount = Integer.parseInt(this.quantityEdit.func_146179_b());
/* 368 */         boolean forceUpdate = false;
/* 369 */         if ((((this.hoveredStack != null) ? 1 : 0) & ((this.hoveredStack != getSelectedStack()) ? 1 : 0)) != 0) {
/* 370 */           tempAmount = 1;
/* 371 */           forceUpdate = true;
/*     */         } 
/*     */         
/* 374 */         if (tempAmount != this.wantedAmount || getSelectedStack() != this.previousStack || forceUpdate) {
/* 375 */           if (!getSelectedStack().hasInfiniteStock()) {
/* 376 */             tempAmount = MathHelper.func_76125_a(tempAmount, 0, getSelectedStack().getCount());
/*     */           }
/* 378 */           int cursor = this.quantityEdit.func_146198_h();
/* 379 */           this.quantityEdit.func_146180_a(tempAmount + "");
/* 380 */           this.quantityEdit.func_146190_e(cursor);
/* 381 */           this.wantedAmount = tempAmount;
/* 382 */           this.previousStack = getSelectedStack();
/*     */         }
/*     */       
/* 385 */       } catch (Exception e) {
/* 386 */         this.quantityEdit.func_146180_a("");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBuy(Button btn) {
/* 393 */     if (getSelectedStack() == null) {
/*     */       return;
/*     */     }
/* 396 */     if (getWantedAmount() > getSelectedStack().getCount() && !getSelectedStack().hasInfiniteStock()) {
/*     */       return;
/*     */     }
/* 399 */     if (getEmptySlots() < calculateSlotsFromCount(getWantedAmount())) {
/*     */       return;
/*     */     }
/* 402 */     int totalPrice = getSelectedStack().getPrice() * getWantedAmount();
/*     */     
/* 404 */     long currency = (this.trader.getCurrency() == Currency.BELLY) ? this.props.getBelly() : this.props.getExtol();
/*     */     
/* 406 */     if (currency < totalPrice) {
/*     */       return;
/*     */     }
/* 409 */     WyNetwork.sendToServer(new CBuyFromTraderPacket(this.trader.func_145782_y(), getSelectedStack().getItemStack(), getWantedAmount()));
/*     */ 
/*     */     
/* 412 */     if (!getSelectedStack().hasInfiniteStock()) {
/* 413 */       int count = getSelectedStack().getCount() - this.wantedAmount;
/*     */       
/* 415 */       if (count <= 0) {
/* 416 */         this.trader.getTradingItems().remove(getSelectedStack());
/*     */       } else {
/*     */         
/* 419 */         getSelectedStack().setCount(count);
/*     */       } 
/*     */       
/* 422 */       setSelectedStack((TradeEntry)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onIncreaseQuantity(Button btn) {
/* 428 */     if (getSelectedStack() != null && (getWantedAmount() < getSelectedStack().getCount() || getSelectedStack().hasInfiniteStock())) {
/* 429 */       setWantedAmount(getWantedAmount() + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onDecreaseQuantity(Button btn) {
/* 434 */     if (getSelectedStack() != null && getWantedAmount() > 1) {
/* 435 */       setWantedAmount(getWantedAmount() - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderItem(ItemStack stack, int posX, int posY) {
/* 440 */     Minecraft.func_71410_x().func_175599_af().func_175042_a(stack, posX, posY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawSizedString(MatrixStack matrixStack, String txt, int x, int y, float scale, int color) {
/* 445 */     RenderSystem.pushMatrix();
/* 446 */     RenderSystem.translated(x, y, 0.0D);
/* 447 */     RenderSystem.scalef(scale, scale, scale);
/*     */     
/* 449 */     if (color == -1) {
/* 450 */       color = WyHelper.hexToRGB("#FFFFFF").getRGB();
/*     */     }
/* 452 */     drawCenteredString(matrixStack, txt, 0, 0, color);
/*     */     
/* 454 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void hover(int mouseX, int mouseY) {
/* 459 */     TradeEntry entry = this.listPanel.findStackEntry(mouseX, mouseY);
/* 460 */     if (entry != null) {
/*     */       
/* 462 */       this.hoveredStack = entry;
/* 463 */       setWantedAmount(1);
/*     */     }
/*     */     else {
/*     */       
/* 467 */       this.hoveredStack = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptySlots() {
/* 473 */     int i = 0;
/* 474 */     for (ItemStack stack : this.player.field_71071_by.field_70462_a) {
/*     */       
/* 476 */       if (stack.func_190926_b())
/*     */       {
/* 478 */         i++;
/*     */       }
/*     */     } 
/* 481 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int calculateSlotsFromCount(int count) {
/* 486 */     double val = count / 64.0D;
/* 487 */     return MathHelper.func_76143_f(val);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Integer> getStacks(int count) {
/* 492 */     List<Integer> list = new ArrayList<>();
/* 493 */     int j = 0;
/* 494 */     for (int i = 0; i < count; i += 64) {
/*     */       
/* 496 */       if (count - 64 * j < 64) {
/*     */         
/* 498 */         list.add(Integer.valueOf(count - 64 * j));
/*     */       }
/*     */       else {
/*     */         
/* 502 */         list.add(Integer.valueOf(64));
/*     */       } 
/* 504 */       j++;
/*     */     } 
/* 506 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredString(MatrixStack matrixStack, String txt, int posX, int posY, int color) {
/* 511 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, txt, posX - this.field_230712_o_.func_78256_a(txt) / 2, posY, color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int partialTicks) {
/* 517 */     boolean flag = super.func_231044_a_(mouseX, mouseY, partialTicks);
/*     */     
/* 519 */     if (this.guiState == 0 && !this.trader.canBuyFromPlayers()) {
/*     */       
/* 521 */       if (this.startMessage.tickCount < this.startMessage.maxTicks) {
/* 522 */         this.startMessage.tickCount = this.startMessage.maxTicks;
/*     */       
/*     */       }
/* 525 */       else if (this.trader.canTrade(this.player)) {
/*     */         
/* 527 */         this.guiState = 1;
/* 528 */         func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */       }
/*     */       else {
/*     */         
/* 532 */         func_231175_as__();
/*     */       }
/*     */     
/*     */     }
/* 536 */     else if (this.guiState == 2) {
/*     */       
/* 538 */       SkypieanTraderEntity skypieanTrader = (SkypieanTraderEntity)this.trader;
/* 539 */       if (this.vearthAmountUp.func_231044_a_(mouseX, mouseY, partialTicks)) {
/*     */         
/* 541 */         int increaseAmount = 1;
/*     */         
/* 543 */         if (ModKeybindings.isShiftKeyDown()) {
/* 544 */           increaseAmount = 10;
/*     */         }
/* 546 */         if (this.wantedAmount + increaseAmount < skypieanTrader.getTradesLeft()) {
/*     */           
/* 548 */           if (this.wantedAmount + increaseAmount <= this.dirtBlocksAvailable) {
/* 549 */             this.wantedAmount += increaseAmount;
/*     */           } else {
/* 551 */             this.wantedAmount = 0;
/*     */           } 
/*     */         } else {
/* 554 */           this.wantedAmount = skypieanTrader.getTradesLeft();
/*     */         } 
/* 556 */       } else if (this.vearthAmountDown.func_231044_a_(mouseX, mouseY, partialTicks)) {
/*     */         
/* 558 */         int decreaseAmount = 1;
/*     */         
/* 560 */         if (ModKeybindings.isShiftKeyDown()) {
/* 561 */           decreaseAmount = 10;
/*     */         }
/* 563 */         if (this.wantedAmount - decreaseAmount >= 0) {
/* 564 */           this.wantedAmount -= decreaseAmount;
/*     */         } else {
/* 566 */           this.wantedAmount = skypieanTrader.getTradesLeft();
/*     */         } 
/*     */       } 
/*     */     } 
/* 570 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public TradeEntry getSelectedStack() {
/* 575 */     return this.selectedStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedStack(TradeEntry selectedStack) {
/* 580 */     this.selectedStack = selectedStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWantedAmount() {
/* 585 */     return this.wantedAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWantedAmount(int wantedAmount) {
/* 590 */     this.wantedAmount = wantedAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 596 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(TraderEntity entity) {
/* 601 */     Minecraft.func_71410_x().func_147108_a(new TraderScreen(entity));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\TraderScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */