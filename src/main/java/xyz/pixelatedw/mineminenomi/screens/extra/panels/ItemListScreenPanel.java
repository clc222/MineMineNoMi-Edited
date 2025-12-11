/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
/*     */ 
/*     */ public class ItemListScreenPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private TraderScreen parent;
/*  21 */   private List<TradeEntry> entries = new ArrayList<>();
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   
/*     */   public ItemListScreenPanel(TraderScreen parent, List<TradeEntry> list) {
/*  26 */     super(parent.getMinecraft(), 215, 140, parent.field_230709_l_ / 2 - 50, parent.field_230708_k_ / 2 - 109);
/*     */     
/*  28 */     this.parent = parent;
/*  29 */     this.entries = list;
/*     */     
/*  31 */     for (TradeEntry entry : this.entries) {
/*     */       
/*  33 */       if (entry.getItemStack().func_196082_o().func_74767_n("isClone") && !entry.getItemStack().func_196082_o().func_74767_n("hasCloneTag")) {
/*     */         
/*  35 */         entry.getItemStack().func_200302_a((ITextComponent)new StringTextComponent(TextFormatting.RESET + entry.getItemStack().func_200301_q().getString() + " (Replica)"));
/*  36 */         entry.getItemStack().func_196082_o().func_74757_a("hasCloneTag", true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double mouseX, double mouseY, int partialTicks) {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  50 */     return (int)(this.entries.size() * 25.0D + 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  56 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  62 */     for (TradeEntry entry : this.entries) {
/*     */       
/*  64 */       int y = relativeY;
/*  65 */       int x = this.parent.field_230708_k_ / 2 - 109 + 40;
/*     */       
/*  67 */       this.parent.renderItem(entry.getItemStack(), x - 30, y - 1);
/*  68 */       if (this.parent.getSelectedStack() != null && entry.getItemStack().hashCode() == this.parent.getSelectedStack().getItemStack().hashCode()) {
/*  69 */         RendererHelper.drawColourOnScreen(Color.WHITE.getRGB(), 100, (x - 40), (y - 4), this.width, 24.0D, 0.0D);
/*     */       }
/*  71 */       this.parent.drawSizedString(matrixStack, entry.getItemStack().func_200301_q().getString(), x + 50, y + 4, 0.8F, -1);
/*  72 */       this.parent.drawSizedString(matrixStack, entry.getPrice() + "", x + 122, y + 4, 0.8F, -1);
/*  73 */       relativeY = (int)(relativeY + 25.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TradeEntry findStackEntry(int mouseX, int mouseY) {
/*  79 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/*  80 */     boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
/*     */     
/*  82 */     if (offset <= 0.0D || !isHovered) {
/*  83 */       return null;
/*     */     }
/*  85 */     int lineIdx = (int)(offset / 25.0D);
/*  86 */     if (lineIdx >= this.entries.size()) {
/*  87 */       return null;
/*     */     }
/*  89 */     TradeEntry entry = this.entries.get(lineIdx);
/*  90 */     if (entry != null && mouseX >= this.left && mouseX <= this.right && mouseY <= this.bottom) {
/*  91 */       return entry;
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/*  99 */     TradeEntry entry = findStackEntry((int)mouseX, (int)mouseY);
/*     */     
/* 101 */     boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
/*     */     
/* 103 */     if (isHovered && entry != null) {
/*     */       
/* 105 */       this.parent.setSelectedStack(entry);
/* 106 */       this.parent.setWantedAmount(1);
/*     */     } 
/*     */     
/* 109 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEntry(ItemStack stack) {
/* 114 */     for (int i = 0; i < this.entries.size(); i++) {
/*     */       
/* 116 */       if (((TradeEntry)this.entries.get(i)).getItemStack().func_77973_b() == stack.func_77973_b())
/*     */       {
/* 118 */         this.entries.remove(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\ItemListScreenPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */