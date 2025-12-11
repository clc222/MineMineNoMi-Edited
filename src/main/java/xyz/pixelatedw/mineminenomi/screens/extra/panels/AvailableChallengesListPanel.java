/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.SimpleSound;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.PlankButton;
/*     */ 
/*     */ public class AvailableChallengesListPanel extends ScrollPanel {
/*     */   private static final int ENTRY_HEIGHT = 36;
/*     */   private final ChallengesScreen parent;
/*     */   private final List<Challenge> displayedChallenges;
/*     */   
/*     */   public AvailableChallengesListPanel(ChallengesScreen parent, List<Challenge> list) {
/*  24 */     super(parent.getMinecraft(), 170, 210, parent.field_230709_l_ / 2 - 90, parent.field_230708_k_ / 2 - 245);
/*     */     
/*  26 */     this.parent = parent;
/*  27 */     this.displayedChallenges = list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  37 */     return this.displayedChallenges.size() * 36 - 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  42 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGradientRect(MatrixStack matrixStack, int left, int top, int right, int bottom, int color1, int color2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  52 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  57 */     int y = relativeY;
/*  58 */     int x = this.parent.field_230708_k_ / 2 - 280 + 40;
/*     */     
/*  60 */     int yOffset = 0;
/*  61 */     for (Challenge ch : this.displayedChallenges) {
/*  62 */       PlankButton challengeButton = new PlankButton(x, y + yOffset, 160, 31, (ITextComponent)ch.getCore().getLocalizedTitle(), btn -> { 
/*  63 */           }); if (this.parent.getSelectedChallenge() != null && this.parent.getSelectedChallenge().equals(ch)) {
/*  64 */         challengeButton.field_230693_o_ = false;
/*     */       }
/*  66 */       if (ch.isComplete()) {
/*  67 */         challengeButton.setFGColor(Color.YELLOW.getRGB());
/*     */       }
/*  69 */       challengeButton.func_230430_a_(matrixStack, mouseX, mouseY, 0.0F);
/*  70 */       yOffset += 36;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Challenge findEntry(int mouseX, int mouseY) {
/*  75 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/*     */     
/*  77 */     if (offset <= 0.0D) {
/*  78 */       return null;
/*     */     }
/*     */     
/*  81 */     int lineIdx = (int)(offset / 36.0D);
/*  82 */     if (lineIdx >= this.displayedChallenges.size()) {
/*  83 */       return null;
/*     */     }
/*     */     
/*  86 */     Challenge entry = this.displayedChallenges.get(lineIdx);
/*  87 */     if (entry != null) {
/*  88 */       return entry;
/*     */     }
/*     */     
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/*  96 */     Challenge ch = findEntry((int)mouseX, (int)mouseY);
/*     */     
/*  98 */     if (ch != null && func_231047_b_(mouseX, mouseY) && button == 0) {
/*  99 */       boolean isAlreadySelected = (this.parent.getSelectedChallenge() != null && this.parent.getSelectedChallenge().equals(ch));
/* 100 */       if (!isAlreadySelected) {
/* 101 */         Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)SimpleSound.func_184371_a(SoundEvents.field_187909_gi, 1.0F));
/* 102 */         this.parent.startInfoPanelAnimation();
/* 103 */         this.parent.setSelectedChallenge(ch);
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231047_b_(double mouseX, double mouseY) {
/* 112 */     return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\AvailableChallengesListPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */