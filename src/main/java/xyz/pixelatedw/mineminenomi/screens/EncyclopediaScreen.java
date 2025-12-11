/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.ReadBookScreen;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.gui.widget.button.ChangePageButton;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.IReorderingProcessor;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class EncyclopediaScreen extends Screen {
/*     */   private int currPage;
/*  43 */   private List<DFEncyclopediaEntry> entries = Lists.newArrayList();
/*     */   private ItemStack bookStack;
/*     */   
/*     */   protected EncyclopediaScreen(ItemStack stack) {
/*  47 */     super((ITextComponent)new StringTextComponent(""));
/*  48 */     this.bookStack = stack;
/*  49 */     this.entries = getDefaultList(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*     */     ResourceLocation baseIcon;
/*  55 */     func_230446_a_(matrixStack);
/*  56 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  57 */     this.field_230706_i_.func_110434_K().func_110577_a(ReadBookScreen.field_214167_b);
/*     */     
/*  59 */     int posX = (this.field_230708_k_ - 192) / 2;
/*  60 */     int posY = 2;
/*     */     
/*  62 */     func_238474_b_(matrixStack, posX, posY, 0, 0, 192, 192);
/*     */     
/*  64 */     DFEncyclopediaEntry entry = !this.entries.isEmpty() ? this.entries.get(this.currPage) : null;
/*     */     
/*  66 */     TranslationTextComponent translationTextComponent = new TranslationTextComponent("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(Math.max(getPageCount(), 1)) });
/*  67 */     int textSize = this.field_230712_o_.func_238414_a_((ITextProperties)translationTextComponent);
/*  68 */     this.field_230712_o_.func_243248_b(matrixStack, (ITextComponent)translationTextComponent, (posX - textSize + 192 - 44), 18.0F, 0);
/*     */     
/*  70 */     RenderSystem.pushMatrix();
/*     */     
/*  72 */     RenderSystem.enableBlend();
/*     */     
/*  74 */     ResourceLocation stemIcon = null;
/*     */     
/*  76 */     Color baseColor = (entry != null) ? entry.getBaseColor().orElse(Color.BLACK) : Color.BLACK;
/*  77 */     Color stemColor = (entry != null) ? entry.getStemColor().orElse(Color.BLACK) : Color.BLACK;
/*     */     
/*  79 */     if (entry == null || !entry.getShape().isPresent() || ((Integer)entry.getShape().get()).intValue() <= 0) {
/*  80 */       baseIcon = ModResources.QUESTION_MARK;
/*     */     } else {
/*     */       
/*  83 */       baseIcon = new ResourceLocation("mineminenomi", "textures/items/fruits/generic/generic_fruit_" + entry.getShape().get() + ".png");
/*  84 */       stemIcon = new ResourceLocation("mineminenomi", "textures/items/fruits/generic/generic_fruit_" + entry.getShape().get() + "_stem.png");
/*     */     } 
/*     */     
/*  87 */     RenderSystem.translated((posX + 55), (posY + 40), 2.0D);
/*  88 */     RenderSystem.translated(64.0D, 64.0D, 0.0D);
/*  89 */     RenderSystem.scaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
/*  90 */     RenderSystem.translated(-64.0D, -64.0D, 1.0D);
/*  91 */     this.field_230706_i_.func_110434_K().func_110577_a(baseIcon);
/*  92 */     if (baseIcon.equals(ModResources.QUESTION_MARK)) {
/*     */       
/*  94 */       drawUpperIcon(baseIcon, matrixStack, 0, 0, 64, 32, stemColor.getRed() / 255.0F, stemColor.getGreen() / 255.0F, stemColor.getBlue() / 255.0F, 1.0F);
/*  95 */       drawLowerIcon(baseIcon, matrixStack, 0, 32, 64, 32, baseColor.getRed() / 255.0F, baseColor.getGreen() / 255.0F, baseColor.getBlue() / 255.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  99 */       RendererHelper.drawIcon(baseIcon, matrixStack, 0.0F, 0.0F, 1.0F, 64.0F, 64.0F, baseColor.getRed() / 255.0F, baseColor.getGreen() / 255.0F, baseColor.getBlue() / 255.0F, 1.0F);
/*     */     } 
/*     */     
/* 102 */     if (stemIcon != null) {
/*     */       
/* 104 */       this.field_230706_i_.func_110434_K().func_110577_a(stemIcon);
/* 105 */       if (entry != null && entry.getShape().isPresent() && ((Integer)entry.getShape().get()).intValue() > 0)
/*     */       {
/* 107 */         RendererHelper.drawIcon(stemIcon, matrixStack, 0.0F, 0.0F, 1.0F, 64.0F, 64.0F, stemColor.getRed() / 255.0F, stemColor.getGreen() / 255.0F, stemColor.getBlue() / 255.0F, 1.0F);
/*     */       }
/*     */     } 
/*     */     
/* 111 */     RenderSystem.popMatrix();
/*     */     
/* 113 */     if (entry != null) {
/*     */       
/* 115 */       String fruitName = (new ItemStack((IItemProvider)entry.getDevilFruit())).func_200301_q().getString();
/* 116 */       fruitName = entry.isComplete() ? (TextFormatting.GOLD + fruitName) : fruitName;
/* 117 */       List<IReorderingProcessor> splitText = this.field_230712_o_.func_238425_b_((ITextProperties)new StringTextComponent(fruitName), 100);
/* 118 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, fruitName, posX + 93 - this.field_230712_o_.func_78256_a(fruitName) / 2, posY + 113, -1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_231160_c_() {
/* 131 */     int posX = (this.field_230708_k_ - 192) / 2;
/* 132 */     int posY = 2;
/*     */     
/* 134 */     func_230480_a_((Widget)new ChangePageButton(posX + 116, posY + 159, true, button -> nextPage(), true));
/*     */ 
/*     */ 
/*     */     
/* 138 */     func_230480_a_((Widget)new ChangePageButton(posX + 43, posY + 159, false, button -> previousPage(), true));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     func_230480_a_((Widget)new Button(this.field_230708_k_ / 2 - 150, 196, 98, 20, (ITextComponent)new StringTextComponent("All"), button -> {
/*     */             this.entries = getDefaultList(this.bookStack);
/*     */             
/*     */             this.currPage = 0;
/*     */           }));
/*     */     
/* 149 */     func_230480_a_((Widget)new Button(this.field_230708_k_ / 2 - 50, 196, 98, 20, (ITextComponent)new StringTextComponent("Partially Complete"), button -> {
/*     */             this.entries = (List<DFEncyclopediaEntry>)getDefaultList(this.bookStack).stream().filter(()).collect(Collectors.toList());
/*     */             
/*     */             this.currPage = 0;
/*     */           }));
/*     */     
/* 155 */     func_230480_a_((Widget)new Button(this.field_230708_k_ / 2 + 50, 196, 98, 20, (ITextComponent)new StringTextComponent("Only Complete"), button -> {
/*     */             this.entries = (List<DFEncyclopediaEntry>)getDefaultList(this.bookStack).stream().filter(()).collect(Collectors.toList());
/*     */             
/*     */             this.currPage = 0;
/*     */           }));
/*     */     
/* 161 */     super.func_231160_c_();
/*     */   }
/*     */ 
/*     */   
/*     */   private void previousPage() {
/* 166 */     if (this.currPage > 0) {
/*     */       
/* 168 */       this.currPage--;
/*     */     }
/* 170 */     else if (this.currPage == 0) {
/*     */       
/* 172 */       this.currPage = getPageCount() - 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void nextPage() {
/* 178 */     if (this.currPage < getPageCount() - 1) {
/*     */       
/* 180 */       this.currPage++;
/*     */     }
/* 182 */     else if (this.currPage == getPageCount() - 1) {
/*     */       
/* 184 */       this.currPage = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getPageCount() {
/* 190 */     return this.entries.size();
/*     */   }
/*     */   
/*     */   public List<DFEncyclopediaEntry> getDefaultList(ItemStack stack) {
/* 194 */     List<DFEncyclopediaEntry> list = Lists.newArrayList();
/* 195 */     CompoundNBT nbt = stack.func_190925_c("unlocked");
/*     */     
/* 197 */     for (AkumaNoMiItem fruit : ModValues.DEVIL_FRUITS) {
/* 198 */       if (fruit.getRegistryName() == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 202 */       String key = fruit.getFruitKey();
/* 203 */       if (!nbt.isEmpty() && nbt.func_74764_b(key)) {
/* 204 */         DFEncyclopediaEntry dFEncyclopediaEntry = DFEncyclopediaEntry.of(nbt.func_74775_l(key));
/* 205 */         dFEncyclopediaEntry.setDevilFruit(fruit);
/* 206 */         list.add(dFEncyclopediaEntry);
/*     */         continue;
/*     */       } 
/* 209 */       DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(Optional.empty(), Optional.empty(), Optional.empty());
/* 210 */       entry.setDevilFruit(fruit);
/* 211 */       list.add(entry);
/*     */     } 
/*     */     
/* 214 */     Collections.reverse(list);
/* 215 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawUpperIcon(ResourceLocation rs, MatrixStack matrixStack, int x, int y, int u, int v, float red, float green, float blue, float alpha) {
/* 226 */     Matrix4f matrix = matrixStack.func_227866_c_().func_227870_a_();
/* 227 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(rs);
/* 228 */     BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/* 229 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 230 */     bufferbuilder.func_227888_a_(matrix, x, (y + v), 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 0.5F).func_181675_d();
/* 231 */     bufferbuilder.func_227888_a_(matrix, (x + u), (y + v), 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(1.0F, 0.5F).func_181675_d();
/* 232 */     bufferbuilder.func_227888_a_(matrix, (x + u), y, 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(1.0F, 0.0F).func_181675_d();
/* 233 */     bufferbuilder.func_227888_a_(matrix, x, y, 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 0.0F).func_181675_d();
/* 234 */     Tessellator.func_178181_a().func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawLowerIcon(ResourceLocation rs, MatrixStack matrixStack, int x, int y, int u, int v, float red, float green, float blue, float alpha) {
/* 239 */     Matrix4f matrix = matrixStack.func_227866_c_().func_227870_a_();
/* 240 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(rs);
/* 241 */     BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
/* 242 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_227851_o_);
/* 243 */     bufferbuilder.func_227888_a_(matrix, x, (y + v), 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 1.0F).func_181675_d();
/* 244 */     bufferbuilder.func_227888_a_(matrix, (x + u), (y + v), 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(1.0F, 1.0F).func_181675_d();
/* 245 */     bufferbuilder.func_227888_a_(matrix, (x + u), y, 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(1.0F, 0.5F).func_181675_d();
/* 246 */     bufferbuilder.func_227888_a_(matrix, x, y, 1.0F).func_227885_a_(red, green, blue, alpha).func_225583_a_(0.0F, 0.5F).func_181675_d();
/* 247 */     Tessellator.func_178181_a().func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(ItemStack stack) {
/* 252 */     Minecraft.func_71410_x().func_147108_a(new EncyclopediaScreen(stack));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\EncyclopediaScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */