/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.EnchantmentNameParts;
/*     */ import net.minecraft.util.IReorderingProcessor;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CAbandonQuestPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CStartObjectiveEventPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.PlankButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class QuestsTrackerScreen extends Screen {
/*     */   private PlayerEntity player;
/*  41 */   private int questIndex = 0; private IQuestData qprops;
/*  42 */   private List<String> hiddenTexts = new ArrayList<>();
/*  43 */   private Quest currentQuest = null;
/*     */   
/*     */   private List<Quest> availableQuests;
/*     */   
/*     */   public QuestsTrackerScreen(PlayerEntity player) {
/*  48 */     super((ITextComponent)new StringTextComponent(""));
/*  49 */     this.player = player;
/*  50 */     this.qprops = QuestDataCapability.get(player);
/*  51 */     this.availableQuests = (List<Quest>)Arrays.<Quest>asList(this.qprops.getInProgressQuests()).stream().filter(quest -> (quest != null)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/*  57 */     func_230446_a_(matrixStack);
/*  58 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  60 */     int posX = this.field_230708_k_ / 2;
/*  61 */     int posY = this.field_230709_l_ / 2;
/*     */     
/*  63 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(ModResources.BLANK);
/*  64 */     RenderSystem.pushMatrix();
/*     */     
/*  66 */     double scale = 1.1D;
/*  67 */     RenderSystem.translated((posX - 35), (posY + 10), 0.0D);
/*  68 */     RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */     
/*  70 */     RenderSystem.scaled(scale * 1.5D, scale * 1.4D, 0.0D);
/*  71 */     RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */ 
/*     */ 
/*     */     
/*  75 */     GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 1.0F);
/*     */     
/*  77 */     RenderSystem.translated(-30.0D, 50.0D, 0.0D);
/*  78 */     RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */     
/*  80 */     RenderSystem.scaled(scale * 0.7D, scale * 0.9D, 0.0D);
/*  81 */     RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */     
/*  83 */     RenderSystem.popMatrix();
/*     */     
/*  85 */     String currentQuestName = (this.currentQuest != null) ? this.currentQuest.getCore().getLocalizedTitle() : "None";
/*  86 */     double currentQuestProgress = (this.currentQuest != null) ? (this.currentQuest.getProgress() * 100.0D) : -1.0D;
/*     */ 
/*     */ 
/*     */     
/*  90 */     if (this.currentQuest != null) {
/*     */ 
/*     */       
/*  93 */       RenderSystem.pushMatrix();
/*     */       
/*  95 */       RenderSystem.translated((posX + 150), (posY - 110), 0.0D);
/*     */       
/*  97 */       String pageNumber = (this.questIndex + 1) + "/" + this.availableQuests.size();
/*  98 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, pageNumber, 0, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       
/* 100 */       RenderSystem.popMatrix();
/*     */ 
/*     */       
/* 103 */       RenderSystem.pushMatrix();
/*     */       
/* 105 */       double d = 1.4D;
/* 106 */       RenderSystem.translated((posX + 100), (posY + 10), 0.0D);
/* 107 */       RenderSystem.translated(256.0D, 256.0D, 0.0D);
/*     */       
/* 109 */       RenderSystem.scaled(d, d, 0.0D);
/* 110 */       RenderSystem.translated(-256.0D, -256.0D, 0.0D);
/*     */       
/* 112 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, currentQuestName, -this.field_230712_o_.func_78256_a(currentQuestName) / 2, 0, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       
/* 114 */       RenderSystem.popMatrix();
/*     */ 
/*     */       
/* 117 */       if (currentQuestProgress != -1.0D) {
/*     */         
/* 119 */         String textColor = "#FFFFFF";
/* 120 */         if (this.currentQuest.isComplete())
/* 121 */           textColor = "#00FF55"; 
/* 122 */         String progress = TextFormatting.BOLD + (new TranslationTextComponent(ModI18n.GUI_QUEST_PROGRESS)).getString() + " : " + String.format("%.1f", new Object[] { Double.valueOf(currentQuestProgress) }) + "%";
/* 123 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, progress, posX - 120, posY - 65, WyHelper.hexToRGB(textColor).getRGB());
/*     */       } 
/*     */ 
/*     */       
/* 127 */       RenderSystem.pushMatrix();
/*     */       
/* 129 */       List<Objective> avilableObjectives = (List<Objective>)this.currentQuest.getObjectives().stream().limit(5L).collect(Collectors.toList());
/*     */       
/* 131 */       int yOffset = -20;
/* 132 */       int i = 0;
/* 133 */       for (Objective obj : avilableObjectives) {
/*     */         
/* 135 */         String objectiveName = obj.getLocalizedTitle();
/* 136 */         double objectiveProgress = obj.getProgress() / obj.getMaxProgress() * 100.0D;
/* 137 */         String progress = "";
/* 138 */         List<Objective> hiddenObjs = (List<Objective>)avilableObjectives.stream().filter(o -> o.isHidden()).collect(Collectors.toList());
/* 139 */         yOffset += 20;
/*     */         
/* 141 */         String textColor = "#FFFFFF";
/* 142 */         if (obj.isComplete()) {
/* 143 */           textColor = "#00FF00";
/*     */         }
/* 145 */         if (obj.isLocked()) {
/* 146 */           textColor = "#505050";
/*     */         } else {
/* 148 */           progress = "- " + String.format("%.1f", new Object[] { Double.valueOf(objectiveProgress) }) + "%";
/*     */         } 
/* 150 */         if (obj.isHidden()) {
/*     */ 
/*     */ 
/*     */           
/* 154 */           WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "• ", posX - 130, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
/* 155 */           if (hiddenObjs.size() > 0) {
/* 156 */             WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, this.hiddenTexts.get(hiddenObjs.indexOf(obj)), posX - 123, posY - 45 + yOffset, WyHelper.hexToRGB(textColor).getRGB());
/*     */           }
/*     */         } else {
/*     */           
/* 160 */           String optional = obj.isOptional() ? "(Optional) " : "";
/* 161 */           objectiveName = "• " + optional + "" + objectiveName + " " + progress;
/* 162 */           List<IReorderingProcessor> splitText = this.field_230712_o_.func_238425_b_((ITextProperties)new StringTextComponent(objectiveName), 280);
/*     */           
/* 164 */           for (int j = 0; j < splitText.size(); j++)
/*     */           {
/* 166 */             WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, splitText.get(j), posX - 130, posY - 45 + yOffset + j * 12, WyHelper.hexToRGB(textColor).getRGB());
/*     */           }
/* 168 */           yOffset += splitText.size() * 8;
/*     */         } 
/* 170 */         i++;
/*     */       } 
/*     */       
/* 173 */       if (i == 0) {
/* 174 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (new TranslationTextComponent(ModI18n.TRAINER_NO_OBJECTIVES_LEFT)).getString(), posX - 120, posY - 20 + yOffset, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */       }
/* 176 */       RenderSystem.popMatrix();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     super.func_230430_a_(matrixStack, x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231160_c_() {
/* 187 */     this.field_230705_e_.clear();
/* 188 */     this.field_230710_m_.clear();
/*     */     
/* 190 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 191 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */ 
/*     */     
/*     */     try {
/* 195 */       this.currentQuest = this.qprops.getInProgressQuests()[this.questIndex];
/*     */     }
/* 197 */     catch (Exception e) {
/*     */       
/* 199 */       if ((this.qprops.getInProgressQuests()).length > 0) {
/*     */         
/* 201 */         this.currentQuest = this.qprops.getInProgressQuests()[0];
/* 202 */         WyDebug.debug(String.format("\n[ArrayOutOfBounds] \n Max possible index is : %s \n But the index requested is : %s", new Object[] { Integer.valueOf((this.qprops.getInProgressQuests()).length - 1), Integer.valueOf(this.questIndex) }));
/*     */       } else {
/*     */         
/* 205 */         this.currentQuest = null;
/* 206 */       }  this.questIndex = 0;
/* 207 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 210 */     if (this.currentQuest == null) {
/*     */       return;
/*     */     }
/* 213 */     this.hiddenTexts.clear();
/* 214 */     for (Objective obj : this.currentQuest.getObjectives()) {
/*     */       
/* 216 */       if (obj.isHidden())
/*     */       {
/* 218 */         this.hiddenTexts.add(EnchantmentNameParts.func_178176_a().func_238816_a_((Minecraft.func_71410_x()).field_71466_p, obj.getTitle().length() * 2).getString());
/*     */       }
/*     */     } 
/*     */     
/* 222 */     TexturedIconButton nextButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_RIGHT, posX + 285, posY + 80, 24, 100, (ITextComponent)new StringTextComponent(""), btn -> {
/*     */           if (this.questIndex + 1 < this.availableQuests.size()) {
/*     */             this.questIndex++;
/*     */           } else {
/*     */             this.questIndex = 0;
/*     */           } 
/*     */           func_231160_c_();
/*     */         });
/* 230 */     nextButton = nextButton.setTextureInfo(posX + 280, posY + 35, 32, 128);
/* 231 */     if (this.availableQuests.size() <= 1)
/* 232 */       nextButton.field_230694_p_ = false; 
/* 233 */     func_230480_a_((Widget)nextButton);
/*     */     
/* 235 */     TexturedIconButton prevButton = new TexturedIconButton(ModResources.BIG_WOOD_BUTTON_LEFT, posX - 55, posY + 80, 24, 100, (ITextComponent)new StringTextComponent(""), btn -> {
/*     */           if (this.questIndex > 0) {
/*     */             this.questIndex--;
/*     */           } else {
/*     */             this.questIndex = this.availableQuests.size() - 1;
/*     */           } 
/*     */           func_231160_c_();
/*     */         });
/* 243 */     prevButton = prevButton.setTextureInfo(posX - 58, posY + 35, 32, 128);
/* 244 */     if (this.availableQuests.size() <= 1)
/* 245 */       prevButton.field_230694_p_ = false; 
/* 246 */     func_230480_a_((Widget)prevButton);
/*     */     
/* 248 */     PlankButton abandonQuestButton = new PlankButton(posX - 40, posY + 190, 80, 30, (ITextComponent)new TranslationTextComponent("Abandon"), btn -> {
/*     */           this.player.func_71053_j();
/*     */           if (this.currentQuest != null) {
/*     */             WyNetwork.sendToServer(new CAbandonQuestPacket(this.currentQuest.getCore()));
/*     */           }
/*     */         });
/* 254 */     func_230480_a_((Widget)abandonQuestButton);
/*     */     
/* 256 */     List<Objective> avilableObjectives = (List<Objective>)this.currentQuest.getObjectives().stream().limit(5L).collect(Collectors.toList());
/*     */     
/* 258 */     int yOffset = -20;
/* 259 */     int objId = -1;
/* 260 */     for (Objective obj : avilableObjectives) {
/*     */       
/* 262 */       yOffset += 30;
/* 263 */       objId++;
/*     */       
/* 265 */       if (!obj.hasEvent()) {
/*     */         continue;
/*     */       }
/* 268 */       String startText = obj.hasStartedEvent() ? "Restart Event" : "Start Event";
/*     */       
/* 270 */       int objId2 = objId;
/* 271 */       PlankButton startEventButton = new PlankButton(posX + 220, posY + 75 + yOffset, 80, 20, (ITextComponent)new StringTextComponent(startText), btn -> {
/*     */             this.player.func_71053_j();
/*     */             
/*     */             if (this.currentQuest != null) {
/*     */               WyNetwork.sendToServer(new CStartObjectiveEventPacket(this.qprops.getInProgressQuestSlot(this.currentQuest), objId2));
/*     */             }
/*     */           });
/*     */       
/* 279 */       if (obj.isLocked())
/* 280 */         startEventButton.field_230694_p_ = false; 
/* 281 */       func_230480_a_((Widget)startEventButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 288 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\QuestsTrackerScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */