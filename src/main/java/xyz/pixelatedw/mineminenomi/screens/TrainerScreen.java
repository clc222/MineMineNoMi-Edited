/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.entity.CDoctorHealPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.FactionButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.AvailableQuestsListScreenPanel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class TrainerScreen extends Screen {
/*     */   private PlayerEntity player;
/*     */   private IQuestData questData;
/*     */   private IHakiData hakiData;
/*     */   private IAbilityData abilityData;
/*  46 */   private List<QuestId> availableQuests = new ArrayList<>();
/*     */   private LivingEntity trainer;
/*  48 */   private float animationTime = 0.0F;
/*  49 */   private float animationTranslation = 100.0F;
/*  50 */   private int guiState = 0;
/*  51 */   private SequencedString startMessage = new SequencedString("", 0, 0);
/*     */   
/*     */   private AvailableQuestsListScreenPanel availableQuestsPanel;
/*     */   
/*     */   private boolean isInCombat;
/*     */ 
/*     */   
/*     */   public TrainerScreen(PlayerEntity player, LivingEntity trainer, List<QuestId> availableQuests, boolean isInCombat) {
/*  59 */     super((ITextComponent)new StringTextComponent(""));
/*  60 */     this.player = player;
/*  61 */     this.questData = QuestDataCapability.get(player);
/*  62 */     this.hakiData = HakiDataCapability.get((LivingEntity)player);
/*  63 */     this.abilityData = AbilityDataCapability.get((LivingEntity)player);
/*  64 */     this.availableQuests = availableQuests;
/*  65 */     this.trainer = trainer;
/*  66 */     this.isInCombat = isInCombat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  72 */     func_230446_a_(matrixStack);
/*     */     
/*  74 */     if (this.animationTime < 10.0F)
/*     */     {
/*  76 */       this.animationTime = (float)(this.animationTime + 0.2D);
/*     */     }
/*  78 */     if (this.animationTranslation > 0.0F)
/*     */     {
/*  80 */       this.animationTranslation = 100.0F - this.animationTime * 40.0F;
/*     */     }
/*     */     
/*  83 */     int posX = this.field_230708_k_ / 2;
/*  84 */     int posY = this.field_230709_l_ / 2;
/*     */     
/*  86 */     switch (this.guiState) {
/*     */       
/*     */       case 0:
/*  89 */         renderMenu(matrixStack, mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 1:
/*  92 */         renderQuestList(matrixStack, mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 2:
/*  95 */         renderMenu(matrixStack, mouseX, mouseY, partialTicks);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 100 */     matrixStack.func_227860_a_();
/*     */     
/* 102 */     matrixStack.func_227861_a_(this.animationTranslation, 0.0D, 0.0D);
/*     */     
/* 104 */     RenderSystem.enableBlend();
/* 105 */     InventoryScreen.func_228187_a_(posX + 150, posY + 150, 100, 40.0F, 5.0F, this.trainer);
/*     */     
/* 107 */     matrixStack.func_227865_b_();
/*     */     
/* 109 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderMenu(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 114 */     int posX = this.field_230708_k_ / 2;
/* 115 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 117 */     this.startMessage.render(matrixStack, posX - 150, posY - 105, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderQuestList(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 123 */     matrixStack.func_227860_a_();
/*     */     
/* 125 */     matrixStack.func_227861_a_(-this.animationTranslation, 0.0D, 0.0D);
/*     */     
/* 127 */     RenderSystem.enableBlend();
/* 128 */     this.availableQuestsPanel.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/* 129 */     this.availableQuestsPanel.func_231047_b_(mouseX, mouseY);
/*     */     
/* 131 */     matrixStack.func_227865_b_();
/*     */     
/* 133 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231158_b_(Minecraft mc, int width, int height) {
/* 139 */     super.func_231158_b_(mc, width, height);
/*     */     
/* 141 */     int posX = this.field_230708_k_ / 2;
/* 142 */     int posY = this.field_230709_l_ / 2;
/*     */     
/* 144 */     if (this.guiState == 0) {
/*     */       
/* 146 */       FactionButton trialsListButton = new FactionButton(posX - 180, posY - 50, 100, 20, (ITextComponent)new TranslationTextComponent("Trials"), btn -> {
/*     */             boolean hasQuests = false;
/*     */ 
/*     */             
/*     */             for (int i = 0; i <= this.availableQuests.size() - 1; i++) {
/*     */               QuestId quest = this.availableQuests.get(i);
/*     */               
/*     */               if (!this.questData.hasFinishedQuest(quest)) {
/*     */                 hasQuests = true;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             
/*     */             if (hasQuests) {
/*     */               this.guiState = 1;
/*     */               
/*     */               func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */             } else {
/*     */               String message = (new TranslationTextComponent(ModI18n.TRAINER_NO_TRIALS_AVAILABLE)).getString();
/*     */               
/*     */               this.startMessage = new SequencedString(message, 250, this.field_230712_o_.func_78256_a(message) / 2);
/*     */             } 
/*     */           });
/*     */       
/* 171 */       func_230480_a_((Widget)trialsListButton);
/*     */       
/* 173 */       if (this.trainer instanceof IHakiTrainer) {
/*     */         
/* 175 */         FactionButton hakiTrainingButton = new FactionButton(posX - 180, posY - 20, 100, 20, (ITextComponent)new TranslationTextComponent("Haki Training"), btn -> {
/*     */               boolean canLearnHaki = (EntityStatsCapability.get((LivingEntity)this.player).getDoriki() > 2000.0D);
/*     */ 
/*     */               
/*     */               if (canLearnHaki) {
/*     */                 this.guiState = 2;
/*     */                 
/*     */                 func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */               } else {
/*     */                 String message = (new TranslationTextComponent(ModI18n.TRAINER_CANT_LEARN_HAKI)).getString();
/*     */                 
/*     */                 this.startMessage = new SequencedString(message, 250, this.field_230712_o_.func_78256_a(message) / 2);
/*     */               } 
/*     */             });
/*     */         
/* 190 */         func_230480_a_((Widget)hakiTrainingButton);
/*     */       } 
/*     */       
/* 193 */       if (this.trainer instanceof xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DoctorTrainerEntity) {
/* 194 */         int payment = (int)(this.player.func_110138_aP() - this.player.func_110143_aJ()) * 10;
/* 195 */         if (this.player.func_110138_aP() == this.player.func_110143_aJ()) {
/* 196 */           payment = 0;
/*     */         }
/* 198 */         Button.ITooltip tooltip = Button.field_238486_s_;
/* 199 */         if (this.isInCombat) {
/* 200 */           tooltip = ((btn, matrix, mouseX, mouseY) -> func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)ModI18n.GUI_COMBAT_CANNOT_USE, Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY));
/*     */         }
/*     */ 
/*     */         
/* 204 */         FactionButton healButton = new FactionButton(posX - 180, posY + 10, 100, 20, (ITextComponent)new TranslationTextComponent("Heal (%s belly)", new Object[] { Integer.valueOf(payment) }), btn -> { if (this.player.func_110138_aP() != this.player.func_110143_aJ()) { WyNetwork.sendToServer(new CDoctorHealPacket(this.player.func_145782_y())); this.field_230706_i_.func_147108_a(null); }  }tooltip);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 210 */         if (this.isInCombat) {
/* 211 */           healButton.field_230693_o_ = false;
/*     */         }
/* 213 */         func_230480_a_((Widget)healButton);
/*     */       }
/*     */     
/* 216 */     } else if (this.guiState == 1) {
/*     */       
/* 218 */       this.availableQuestsPanel = new AvailableQuestsListScreenPanel(this, this.questData, this.availableQuests);
/* 219 */       this.field_230705_e_.add(this.availableQuestsPanel);
/* 220 */       func_231035_a_((IGuiEventListener)this.availableQuestsPanel);
/*     */       
/* 222 */       FactionButton backButton = new FactionButton(posX - 180, posY + 80, 200, 20, (ITextComponent)new TranslationTextComponent("gui.cancel"), btn -> {
/*     */             this.guiState = 0;
/*     */             
/*     */             func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */           });
/* 227 */       func_230480_a_((Widget)backButton);
/*     */     }
/* 229 */     else if (this.guiState == 2) {
/*     */       
/* 231 */       if (this.trainer instanceof IHakiTrainer) {
/*     */         
/* 233 */         String info = "";
/* 234 */         String tempMessage = "";
/*     */         
/* 236 */         HakiType type = ((IHakiTrainer)this.trainer).getTrainingHaki();
/* 237 */         if (type == HakiType.BUSOSHOKU) {
/*     */           
/* 239 */           info = ModI18n.TRAINER_HOW_TO_BUSOSHOKU;
/* 240 */           tempMessage = ModI18n.TRAINER_HOW_TO_BUSOSHOKU_MESSAGE;
/*     */         }
/*     */         else {
/*     */           
/* 244 */           info = ModI18n.TRAINER_HOW_TO_OBSERVATION;
/* 245 */           tempMessage = ModI18n.TRAINER_HOW_TO_OBSERVATION_MESSAGE;
/* 246 */           if (this.abilityData.hasUnlockedAbility(KenbunshokuHakiAuraAbility.INSTANCE))
/*     */           {
/* 248 */             tempMessage = ModI18n.TRAINER_HOW_TO_OBSERVATION_MESSAGE_2;
/*     */           }
/*     */         } 
/* 251 */         String hakiTrainingMessage = tempMessage;
/*     */         
/* 253 */         FactionButton hakiInfoButton = new FactionButton(posX - 180, posY - 50, 200, 20, (ITextComponent)new TranslationTextComponent(info), btn -> {
/*     */               String formattedMessage = (new TranslationTextComponent(hakiTrainingMessage)).getString();
/*     */               
/*     */               this.startMessage = new SequencedString(formattedMessage, 250, this.field_230712_o_.func_78256_a(formattedMessage) / 3, 650);
/*     */             });
/* 258 */         func_230480_a_((Widget)hakiInfoButton);
/*     */         
/* 260 */         info = ModI18n.TRAINER_MY_HAKI;
/* 261 */         FactionButton checkHakiButton = new FactionButton(posX - 180, posY - 20, 200, 20, (ITextComponent)new TranslationTextComponent(info), btn -> {
/*     */               String formattedMessage = (new TranslationTextComponent(ModI18n.TRAINER_HAKI_RANK, new Object[] { HakiHelper.getHakiRank(type, (LivingEntity)this.player) })).getString();
/*     */               
/*     */               this.startMessage = new SequencedString(formattedMessage, 250, this.field_230712_o_.func_78256_a(formattedMessage) / 3, 650);
/*     */             });
/* 266 */         func_230480_a_((Widget)checkHakiButton);
/*     */       } 
/*     */       
/* 269 */       FactionButton backButton = new FactionButton(posX - 180, posY + 80, 200, 20, (ITextComponent)new TranslationTextComponent("gui.cancel"), btn -> {
/*     */             this.guiState = 0;
/*     */             
/*     */             func_231158_b_(getMinecraft(), this.field_230708_k_, this.field_230709_l_);
/*     */           });
/* 274 */       func_230480_a_((Widget)backButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAnimationComplete() {
/* 280 */     if (this.animationTime >= 5.0F)
/* 281 */       return true; 
/* 282 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\TrainerScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */