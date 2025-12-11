/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IReorderingProcessor;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CUpdateQuestStatePacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.TrainerScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AvailableQuestsListScreenPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private TrainerScreen parent;
/*     */   private IQuestData props;
/*  34 */   private List<QuestId> availableQuests = new ArrayList<>();
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   private FontRenderer font;
/*     */   
/*     */   public AvailableQuestsListScreenPanel(TrainerScreen parent, IQuestData abilityProps, List<QuestId> quests) {
/*  40 */     super(parent.getMinecraft(), 200, 180, parent.field_230709_l_ / 2 - 110, parent.field_230708_k_ / 2 - 190);
/*  41 */     this.parent = parent;
/*  42 */     this.props = abilityProps;
/*     */     
/*  44 */     Minecraft parentMinecraft = parent.getMinecraft();
/*     */     
/*  46 */     this.font = parentMinecraft.field_71466_p;
/*     */     
/*  48 */     updateAvailableQuests(quests);
/*     */     
/*  50 */     this.scrollDistance = -10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAvailableQuests(List<QuestId> quests) {
/*  55 */     List<QuestId> prevQuests = new ArrayList<>();
/*  56 */     prevQuests.addAll(quests);
/*  57 */     this.availableQuests.clear();
/*  58 */     for (int i = 0; i <= prevQuests.size() - 1; i++) {
/*     */       
/*  60 */       QuestId quest = prevQuests.get(i);
/*  61 */       boolean exists = (quest != null);
/*  62 */       boolean isNotFinished = (exists && !this.props.hasFinishedQuest(quest));
/*  63 */       boolean isNotInProgress = (exists && (this.props.getInProgressQuest(quest) == null || (this.props.getInProgressQuest(quest) != null && this.props.getInProgressQuest(quest).isComplete())));
/*     */       
/*  65 */       if (isNotFinished && isNotInProgress)
/*     */       {
/*  67 */         this.availableQuests.add(quest);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  81 */     return (int)(this.availableQuests.size() * 55.0D - 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  87 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  93 */     Tessellator tess = Tessellator.func_178181_a();
/*     */     
/*  95 */     double scale = this.parent.getMinecraft().func_228018_at_().func_198100_s();
/*  96 */     GL11.glEnable(3089);
/*  97 */     GL11.glScissor((int)(this.left * scale), (int)(this.parent.getMinecraft().func_228018_at_().func_198091_l() - this.bottom * scale), (int)(this.width * scale), (int)(this.height * scale));
/*     */     
/*  99 */     int baseY = this.top + 4 - (int)this.scrollDistance;
/* 100 */     drawPanel(matrixStack, this.right, baseY, tess, mouseX, mouseY);
/*     */     
/* 102 */     GL11.glDisable(3089);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/* 110 */     for (QuestId quest : this.availableQuests) {
/*     */       
/* 112 */       if (quest == null) {
/*     */         continue;
/*     */       }
/* 115 */       float y = relativeY;
/* 116 */       float x = (this.parent.field_230708_k_ / 2 - 109 + 40);
/*     */       
/* 118 */       String formattedQuestName = quest.getLocalizedTitle();
/* 119 */       String questColor = "#FFFFFF";
/*     */       
/* 121 */       Quest inProgressQuest = this.props.getInProgressQuest(quest);
/* 122 */       if (inProgressQuest != null) {
/*     */         
/* 124 */         if (isMouseOverQuest(mouseX, mouseY, quest) && !inProgressQuest.isComplete()) {
/* 125 */           formattedQuestName = (new TranslationTextComponent(ModI18n.TRAINER_NO_QUESTS_AVAILABLE)).getString();
/*     */         }
/* 127 */         if (inProgressQuest.isComplete()) {
/* 128 */           questColor = "#00FF55";
/*     */         }
/*     */       } 
/* 131 */       if (quest.isLocked(this.props)) {
/* 132 */         questColor = "#505050";
/*     */       }
/* 134 */       if (this.parent.isAnimationComplete() && isMouseOverQuest(mouseX, mouseY, quest))
/*     */       {
/* 136 */         RenderSystem.color3f(0.8F, 0.8F, 0.8F);
/*     */       }
/*     */       
/* 139 */       RenderSystem.pushMatrix();
/*     */       
/* 141 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(ModResources.SCROLL);
/* 142 */       double scale = 0.5D;
/* 143 */       RenderSystem.translated((x - 180.0F), (y - 196.0F), 0.0D);
/* 144 */       RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */       
/* 146 */       RenderSystem.scaled(scale * 1.5D, scale * 0.6D, 0.0D);
/* 147 */       RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */ 
/*     */       
/* 150 */       func_238474_b_(matrixStack, 0, 0, 0, 0, 256, 256);
/*     */       
/* 152 */       RenderSystem.popMatrix();
/*     */       
/* 154 */       if (this.parent.isAnimationComplete()) {
/* 155 */         RenderSystem.color3f(1.0F, 1.0F, 1.0F);
/*     */       }
/* 157 */       if (this.font.func_78256_a(formattedQuestName) > 140) {
/*     */         
/* 159 */         RenderSystem.pushMatrix();
/*     */         
/* 161 */         List<IReorderingProcessor> splittedText = this.font.func_238425_b_((ITextProperties)new StringTextComponent(formattedQuestName), 140);
/*     */         
/* 163 */         RenderSystem.translated(0.0D, -((splittedText.size() - 1) * 5), 0.0D);
/* 164 */         for (IReorderingProcessor string : splittedText) {
/*     */           
/* 166 */           WyHelper.drawStringWithBorder(this.font, matrixStack, string, (int)x - 80, (int)y + 16, WyHelper.hexToRGB(questColor).getRGB());
/* 167 */           y += 10.0F;
/*     */         } 
/*     */         
/* 170 */         RenderSystem.popMatrix();
/*     */       }
/*     */       else {
/*     */         
/* 174 */         WyHelper.drawStringWithBorder(this.font, matrixStack, formattedQuestName, (int)x - 80, (int)y + 16, WyHelper.hexToRGB(questColor).getRGB());
/*     */       } 
/*     */       
/* 177 */       relativeY = (int)(relativeY + 55.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/* 184 */     QuestId quest = findQuestEntry((int)mouseX, (int)mouseY);
/*     */     
/* 186 */     if (button != 0) {
/* 187 */       return false;
/*     */     }
/* 189 */     Quest inprogressQuest = this.props.getInProgressQuest(quest);
/*     */     
/* 191 */     if (quest != null && inprogressQuest != null && inprogressQuest.isComplete()) {
/*     */       
/* 193 */       WyNetwork.sendToServer(new CUpdateQuestStatePacket(quest));
/* 194 */       this.availableQuests.remove(quest);
/*     */     }
/* 196 */     else if (quest != null && !quest.isLocked(this.props)) {
/*     */       
/* 198 */       WyNetwork.sendToServer(new CUpdateQuestStatePacket(quest));
/* 199 */       this.availableQuests.remove(quest);
/*     */     } 
/*     */     
/* 202 */     updateAvailableQuests(this.availableQuests);
/*     */     
/* 204 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMouseOverQuest(double mouseX, double mouseY, QuestId overQuest) {
/* 209 */     QuestId quest = findQuestEntry((int)mouseX, (int)mouseY);
/*     */     
/* 211 */     if (quest != null && quest.equals(overQuest)) {
/* 212 */       return func_231047_b_(mouseX, mouseY);
/*     */     }
/* 214 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private QuestId findQuestEntry(int mouseX, int mouseY) {
/* 220 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/* 221 */     boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
/*     */     
/* 223 */     if (offset <= 0.0D || !isHovered) {
/* 224 */       return null;
/*     */     }
/* 226 */     int lineIdx = (int)(offset / 55.0D);
/* 227 */     if (lineIdx >= this.availableQuests.size()) {
/* 228 */       return null;
/*     */     }
/* 230 */     QuestId quest = this.availableQuests.get(lineIdx);
/* 231 */     if (quest != null) {
/* 232 */       return quest;
/*     */     }
/* 234 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\AvailableQuestsListScreenPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */