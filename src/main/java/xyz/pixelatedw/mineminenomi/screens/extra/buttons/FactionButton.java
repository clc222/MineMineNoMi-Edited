/*    */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.ITextProperties;
/*    */ import net.minecraftforge.common.util.TextTable;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FactionButton extends Button {
/*    */   private IEntityStats entityData;
/* 20 */   private TextTable.Alignment textAlignment = TextTable.Alignment.LEFT; private boolean isSelected;
/* 21 */   private int lineThickness = 1;
/*    */   
/*    */   private boolean hasIcons = true;
/*    */   private int blackColor;
/*    */   
/*    */   public FactionButton(int posX, int posY, int width, int height, ITextComponent string, Button.IPressable onPress) {
/* 27 */     this(posX, posY, width, height, string, onPress, Button.field_238486_s_);
/*    */   }
/*    */   
/*    */   public FactionButton(int posX, int posY, int width, int height, ITextComponent string, Button.IPressable onPress, Button.ITooltip onTooltip) {
/* 31 */     super(posX, posY, width, height, string, onPress, onTooltip);
/* 32 */     this.blackColor = WyHelper.hexToRGB("#000000").getRGB();
/* 33 */     this.entityData = EntityStatsCapability.get((LivingEntity)(Minecraft.func_71410_x()).field_71439_g);
/*    */   }
/*    */   
/*    */   public void setTextAlignment(TextTable.Alignment alignment) {
/* 37 */     this.textAlignment = alignment;
/*    */   }
/*    */   
/*    */   public void setLineThickness(int thickness) {
/* 41 */     this.lineThickness = thickness;
/*    */   }
/*    */   
/*    */   public void disableIcons() {
/* 45 */     this.hasIcons = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 50 */     matrixStack.func_227860_a_();
/* 51 */     if (this.field_230694_p_) {
/* 52 */       int textPosX; this.field_230692_n_ = (mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/* 53 */       int rgb = WyHelper.hexToRGB("#FFFFFF").getRGB();
/* 54 */       int lineColor = WyHelper.hexToRGB("#EAEAEA").getRGB();
/*    */       
/* 56 */       if (!this.field_230693_o_) {
/* 57 */         rgb = lineColor = WyHelper.hexToRGB("#6B6B6B").getRGB();
/*    */       }
/*    */       
/* 60 */       if (this.field_230692_n_) {
/* 61 */         func_230443_a_(matrixStack, mouseX, mouseY);
/* 62 */         if (this.field_230693_o_) {
/* 63 */           matrixStack.func_227861_a_(0.0D, 0.5D, 0.0D);
/* 64 */           int factionColor = FactionHelper.getFactionRGBColor(this.entityData);
/* 65 */           rgb = lineColor = factionColor;
/*    */         } 
/*    */       } 
/*    */       
/* 69 */       FontRenderer font = (Minecraft.func_71410_x()).field_71466_p;
/*    */ 
/*    */       
/* 72 */       if (this.textAlignment == TextTable.Alignment.CENTER) {
/* 73 */         textPosX = this.field_230690_l_ - font.func_238414_a_((ITextProperties)func_230458_i_()) / 2 + this.field_230688_j_ / 2;
/* 74 */       } else if (this.textAlignment == TextTable.Alignment.RIGHT) {
/* 75 */         textPosX = this.field_230690_l_;
/*    */       } else {
/* 77 */         textPosX = this.field_230690_l_;
/*    */       } 
/* 79 */       func_238468_a_(matrixStack, this.field_230690_l_ - 4, this.field_230691_m_ + this.field_230689_k_ - this.lineThickness + 2, this.field_230688_j_ + this.field_230690_l_ + 1, this.field_230691_m_ + this.field_230689_k_, this.blackColor, this.blackColor);
/*    */       
/* 81 */       func_238468_a_(matrixStack, this.field_230690_l_ - 5, this.field_230691_m_ + this.field_230689_k_ - this.lineThickness, this.field_230688_j_ + this.field_230690_l_, this.field_230691_m_ + this.field_230689_k_, lineColor, lineColor);
/*    */ 
/*    */       
/* 84 */       int textOffset = 0;
/*    */       
/* 86 */       if (this.hasIcons) {
/* 87 */         ResourceLocation factionIcon = FactionHelper.getFactionIcon(this.entityData);
/* 88 */         if (factionIcon != null) {
/* 89 */           RendererHelper.drawIcon(factionIcon, matrixStack, (this.field_230690_l_ - 12), (this.field_230691_m_ - 4), 1.0F, 32.0F, 32.0F, this.blackColor);
/* 90 */           RendererHelper.drawIcon(factionIcon, matrixStack, (this.field_230690_l_ - 13), (this.field_230691_m_ - 5), 1.0F, 32.0F, 32.0F, lineColor);
/* 91 */           textOffset = 13;
/*    */         } 
/*    */       } 
/*    */       
/* 95 */       WyHelper.drawStringWithBorder(font, matrixStack, func_230458_i_().getString(), textPosX + textOffset, this.field_230691_m_ + this.field_230689_k_ / 2 - 4, rgb);
/*    */     } 
/* 97 */     matrixStack.func_227865_b_();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\FactionButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */