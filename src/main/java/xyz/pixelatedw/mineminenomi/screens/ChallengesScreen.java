/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import joptsimple.internal.Strings;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.IScreenEventReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CDisbandChallengeGroupPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CStartChallengePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.CScreenEventPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.GroupMemberButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.PlankButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.AvailableChallengesListPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.ChallengeGroupSelectorPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.ChallengeInvitesPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.widgets.NewCheckboxButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ChallengesScreen extends Screen implements IScreenEventReceiver {
/*     */   private static final float MAX_ANIMATION_TICKS = 20.0F;
/*     */   private final ClientPlayerEntity player;
/*     */   private final List<Challenge> challenges;
/*     */   private final List<ChallengeInvitation> invites;
/*     */   private final Map<String, List<Challenge>> challengesMap;
/*  59 */   private final List<String> categories = new ArrayList<>(); private List<Challenge> displayedChallenges;
/*     */   @Nullable
/*     */   private Challenge selectedChallenge;
/*     */   private String selectedChallengeStars;
/*     */   private String selectedCategory;
/*     */   private int selectedCategoryId;
/*     */   private ITextComponent selectedCategoryLocalizedName;
/*     */   @Nullable
/*     */   private ResourceLocation selectedCategoryIcon;
/*     */   @Nullable
/*  69 */   private LivingEntity[] targets = new LivingEntity[4];
/*     */   
/*     */   private int targetsCount;
/*  72 */   private LivingEntity[] group = new LivingEntity[3];
/*  73 */   private GroupMemberButton[] groupMemberButtons = new GroupMemberButton[3];
/*     */   
/*     */   private GroupMemberButton groupOwnerButton;
/*     */   
/*     */   private AvailableChallengesListPanel challengesListPanel;
/*     */   private ChallengeGroupSelectorPanel challengesGroupSelectorPanel;
/*     */   private ChallengeInvitesPanel challengeInvitesPanel;
/*     */   private LivingEntity dummyBust;
/*     */   private int selectedMemberSlot;
/*     */   private NewCheckboxButton freeCheckbox;
/*     */   private boolean startedChallenge = false;
/*     */   private int setupStep;
/*     */   private PlankButton startChallengeButton;
/*     */   private TexturedIconButton nextStepButton;
/*     */   private TexturedIconButton prevStepButton;
/*     */   private float renderTick;
/*     */   private boolean runInfoPanelAnimation;
/*     */   private boolean runCategoryChangeAnimation;
/*  91 */   private float infoPanelAnimationTick = 0.0F;
/*  92 */   private float categoryChangeAnimationTick = 0.0F;
/*     */   
/*     */   public ChallengesScreen(List<Challenge> challenges, List<ChallengeInvitation> invites) {
/*  95 */     super(StringTextComponent.field_240750_d_);
/*  96 */     this.player = (Minecraft.func_71410_x()).field_71439_g;
/*  97 */     this.challenges = challenges;
/*  98 */     this.invites = invites;
/*  99 */     this.challenges.sort((o1, o2) -> o2.getCore().getOrder() - o1.getCore().getOrder());
/* 100 */     this.challengesMap = (Map<String, List<Challenge>>)challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
/* 101 */     this.categories.add("");
/* 102 */     this.categories.addAll(this.challengesMap.keySet());
/*     */     
/* 104 */     this.selectedCategoryId = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 109 */     func_230446_a_(matrixStack);
/*     */     
/* 111 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 112 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */     
/* 114 */     if (this.runCategoryChangeAnimation && this.categoryChangeAnimationTick < 20.0F) {
/* 115 */       this.categoryChangeAnimationTick += 2.2F * this.field_230706_i_.func_193989_ak();
/* 116 */       this.categoryChangeAnimationTick = Math.min(this.categoryChangeAnimationTick, 20.0F);
/*     */     } 
/*     */     
/* 119 */     if (this.runInfoPanelAnimation && this.infoPanelAnimationTick < 20.0F) {
/* 120 */       this.infoPanelAnimationTick += 2.0F * this.field_230706_i_.func_193989_ak();
/* 121 */       this.infoPanelAnimationTick = Math.min(this.infoPanelAnimationTick, 20.0F);
/*     */     } 
/*     */     
/* 124 */     float infoPanelAnimationOffset = EasingFunctionHelper.easeOutBack(Float.valueOf(this.infoPanelAnimationTick / 20.0F));
/* 125 */     float categoryChangeAnimationOffset = EasingFunctionHelper.easeOutCubic(Float.valueOf(this.categoryChangeAnimationTick / 20.0F));
/*     */     
/* 127 */     matrixStack.func_227860_a_();
/* 128 */     matrixStack.func_227861_a_((posX - 210), ((posY - 122) - categoryChangeAnimationOffset * 4.0F), 0.0D);
/* 129 */     matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/*     */     
/* 131 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, this.selectedCategoryLocalizedName, 40, 15, -1);
/*     */     
/* 133 */     if (this.selectedCategoryIcon != null) {
/* 134 */       matrixStack.func_227862_a_(0.15F, 0.15F, 0.0F);
/* 135 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(this.selectedCategoryIcon);
/* 136 */       GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 256, 256, 5.0F);
/*     */     } 
/* 138 */     matrixStack.func_227861_a_(-128.0D, -128.0D, 0.0D);
/* 139 */     matrixStack.func_227865_b_();
/*     */     
/* 141 */     posY += 25;
/*     */     
/* 143 */     if (this.selectedChallenge != null && infoPanelAnimationOffset > 0.0F) {
/* 144 */       infoPanelAnimationOffset *= 320.0F;
/* 145 */       matrixStack.func_227860_a_();
/* 146 */       matrixStack.func_227861_a_(((posX + 115) - infoPanelAnimationOffset), (posY - 246), 0.0D);
/* 147 */       matrixStack.func_227861_a_(256.0D, 256.0D, 0.0D);
/* 148 */       matrixStack.func_227862_a_(1.25F, 1.25F, 0.0F);
/*     */       
/* 150 */       this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BOARD);
/* 151 */       func_238474_b_(matrixStack, 0, 0, 0, 0, 256, 256);
/*     */       
/* 153 */       this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BLANK_NEW);
/* 154 */       func_238474_b_(matrixStack, 0, 0, 0, 0, 256, 256);
/*     */       
/* 156 */       matrixStack.func_227861_a_(-256.0D, -256.0D, 0.0D);
/* 157 */       matrixStack.func_227865_b_();
/*     */       
/* 159 */       int posX2 = posX + 450 - (int)infoPanelAnimationOffset;
/*     */       
/* 161 */       if (this.setupStep == 0)
/*     */       {
/* 163 */         matrixStack.func_227860_a_();
/*     */         
/* 165 */         if (this.targetsCount == 1) {
/* 166 */           if (this.targets[0] != null) {
/* 167 */             RendererHelper.drawLivingBust(this.targets[0], matrixStack, posX2 + 5, posY + 200, 40, -30, 10, false);
/*     */           }
/*     */         }
/* 170 */         else if (this.targetsCount == 2) {
/* 171 */           if (this.targets[0] != null) {
/* 172 */             RendererHelper.drawLivingBust(this.targets[0], matrixStack, posX2 - 25, posY + 180, 35, -30, 10, false);
/*     */           }
/* 174 */           if (this.targets[1] != null) {
/* 175 */             RendererHelper.drawLivingBust(this.targets[1], matrixStack, posX2 + 25, posY + 180, 35, -30, 10, false);
/*     */           }
/*     */         } else {
/*     */           
/* 179 */           if (this.targets[0] != null) {
/* 180 */             RendererHelper.drawLivingBust(this.targets[0], matrixStack, posX2 - 25, posY + 150, 30, -30, 10, false);
/*     */           }
/* 182 */           if (this.targets[1] != null) {
/* 183 */             RendererHelper.drawLivingBust(this.targets[1], matrixStack, posX2 + 25, posY + 150, 30, -30, 10, false);
/*     */           }
/* 185 */           if (this.targets[2] != null) {
/* 186 */             RendererHelper.drawLivingBust(this.targets[2], matrixStack, posX2 - 25, posY + 200, 30, -30, 10, false);
/*     */           }
/* 188 */           if (this.targets[3] != null) {
/* 189 */             RendererHelper.drawLivingBust(this.targets[3], matrixStack, posX2 + 25, posY + 200, 30, -30, 10, false);
/*     */           }
/*     */         } 
/* 192 */         matrixStack.func_227865_b_();
/*     */         
/* 194 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, this.selectedChallenge.getCore().getLocalizedTitle().getString(), posX2 - 30, posY + 35, -1);
/*     */         
/* 196 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (ITextComponent)this.selectedChallenge.getCore().getLocalizedObjective(), posX2 - 20, posY + 50, -1);
/*     */         
/* 198 */         String timeLimit = WyHelper.formatTimeMMSS((this.selectedChallenge.getCore().getTimeLimit() * 60));
/* 199 */         String timeLimitStr = ModI18n.GUI_TIME_LIMIT.getString() + ": " + timeLimit;
/* 200 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, timeLimitStr, posX2 + 60, posY + 90, -1);
/*     */         
/* 202 */         String pbTime = WyHelper.formatTimeMMSS(this.selectedChallenge.getBestTimeTick());
/* 203 */         String pbTimeStr = ModI18n.GUI_PERSONAL_BEST.getString() + ": " + pbTime;
/* 204 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, pbTimeStr, posX2 + 60, posY + 105, -1);
/*     */         
/* 206 */         String difficultyStr = ModI18n.GUI_DIFFICULTY.getString() + ": " + this.selectedChallengeStars;
/* 207 */         WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, difficultyStr, posX2 + 60, posY + 120, -1);
/*     */         
/* 209 */         if (this.selectedChallenge.getCore().getDifficulty() != ChallengeDifficulty.STANDARD) {
/* 210 */           WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, (ITextComponent)ModI18n.GUI_RESTRICTIONS, posX2 + 60, posY + 135, -1);
/* 211 */           WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "  - §cNo Food§r", posX2 + 60, posY + 145, -1);
/* 212 */           WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "  - §cNo Potions§r", posX2 + 60, posY + 155, -1);
/*     */         }
/*     */       
/*     */       }
/* 216 */       else if (this.setupStep == 1)
/*     */       {
/* 218 */         this.groupOwnerButton.field_230690_l_ = posX2 - 30;
/* 219 */         this.groupOwnerButton.field_230691_m_ = posY + 40;
/*     */         
/* 221 */         (this.groupMemberButtons[0]).field_230690_l_ = posX2 + 40;
/* 222 */         (this.groupMemberButtons[0]).field_230691_m_ = posY + 40;
/*     */         
/* 224 */         (this.groupMemberButtons[1]).field_230690_l_ = posX2 - 30;
/* 225 */         (this.groupMemberButtons[1]).field_230691_m_ = posY + 95;
/*     */         
/* 227 */         (this.groupMemberButtons[2]).field_230690_l_ = posX2 + 40;
/* 228 */         (this.groupMemberButtons[2]).field_230691_m_ = posY + 95;
/*     */         
/* 230 */         for (int i = 0; i < this.groupMemberButtons.length; i++) {
/* 231 */           if (this.groupMemberButtons[i] != null) {
/* 232 */             this.groupMemberButtons[i].func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */           }
/*     */         } 
/*     */         
/* 236 */         this.groupOwnerButton.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */         
/* 238 */         this.freeCheckbox.field_230690_l_ = posX2 - 40;
/* 239 */         this.freeCheckbox.field_230691_m_ = posY + 160;
/*     */       }
/* 241 */       else if (this.setupStep == 2)
/*     */       {
/* 243 */         if (this.selectedMemberSlot >= 0 && this.challengesGroupSelectorPanel != null) {
/* 244 */           this.challengesGroupSelectorPanel.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */         }
/*     */       }
/*     */     
/* 248 */     } else if (this.selectedChallenge == null && infoPanelAnimationOffset > 0.0F) {
/* 249 */       infoPanelAnimationOffset *= 320.0F;
/* 250 */       matrixStack.func_227860_a_();
/* 251 */       matrixStack.func_227861_a_(((posX + 115) - infoPanelAnimationOffset), (posY - 246), 0.0D);
/* 252 */       matrixStack.func_227861_a_(256.0D, 256.0D, 0.0D);
/* 253 */       matrixStack.func_227862_a_(1.25F, 1.25F, 0.0F);
/*     */       
/* 255 */       this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BOARD);
/* 256 */       func_238474_b_(matrixStack, 0, 0, 0, 0, 256, 256);
/*     */       
/* 258 */       this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BLANK_NEW);
/* 259 */       func_238474_b_(matrixStack, 0, 0, 0, 0, 256, 256);
/*     */       
/* 261 */       matrixStack.func_227861_a_(-256.0D, -256.0D, 0.0D);
/* 262 */       matrixStack.func_227865_b_();
/*     */       
/* 264 */       int posX2 = posX + 450 - (int)infoPanelAnimationOffset;
/*     */ 
/*     */ 
/*     */       
/* 268 */       if (this.challengeInvitesPanel != null) {
/* 269 */         this.challengeInvitesPanel.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 274 */     if (this.challengesListPanel != null) {
/* 275 */       this.challengesListPanel.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     }
/*     */     
/* 278 */     this.renderTick += 1.0F * this.field_230706_i_.func_193989_ak();
/*     */     
/* 280 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231160_c_() {
/* 286 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 287 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */     
/* 289 */     this.renderTick = 0.0F;
/* 290 */     this.field_230710_m_.clear();
/* 291 */     this.dummyBust = (LivingEntity)EntityType.field_200756_av.func_200721_a((World)this.field_230706_i_.field_71441_e);
/*     */ 
/*     */     
/* 294 */     TexturedIconButton prevCategoryButton = new TexturedIconButton(ModResources.WOOD_ARROW_LEFT, posX - 110, posY + 10, 30, 20, StringTextComponent.field_240750_d_, btn -> {
/*     */           if (this.selectedCategoryId - 1 >= 0) {
/*     */             this.selectedCategoryId--;
/*     */             
/*     */             updateSelectedCategory();
/*     */           } else {
/*     */             this.selectedCategoryId = this.categories.size() - 1;
/*     */             updateSelectedCategory();
/*     */           } 
/*     */         });
/* 304 */     prevCategoryButton = prevCategoryButton.setTextureInfo(posX - 111, posY + 9, 32, 23);
/* 305 */     func_230480_a_((Widget)prevCategoryButton);
/*     */     
/* 307 */     TexturedIconButton nextCategoryButton = new TexturedIconButton(ModResources.WOOD_ARROW_RIGHT, posX + 70, posY + 10, 30, 20, StringTextComponent.field_240750_d_, btn -> {
/*     */           if (this.selectedCategoryId + 1 < this.categories.size()) {
/*     */             this.selectedCategoryId++;
/*     */             
/*     */             updateSelectedCategory();
/*     */           } else {
/*     */             this.selectedCategoryId = 0;
/*     */             updateSelectedCategory();
/*     */           } 
/*     */         });
/* 317 */     nextCategoryButton = nextCategoryButton.setTextureInfo(posX + 69, posY + 9, 32, 23);
/* 318 */     func_230480_a_((Widget)nextCategoryButton);
/*     */     
/* 320 */     if (this.invites.size() > 0) {
/* 321 */       TranslationTextComponent invitesMessage = new TranslationTextComponent(ModI18n.GUI_INVITES_AMOUNT, new Object[] { Integer.valueOf(this.invites.size()) });
/* 322 */       PlankButton invitesButton = new PlankButton(posX + 120, posY + 8, 100, 25, (ITextComponent)invitesMessage, btn -> showInvitesScreen());
/*     */ 
/*     */       
/* 325 */       func_230480_a_((Widget)invitesButton);
/*     */     } 
/*     */ 
/*     */     
/* 329 */     this.prevStepButton = new TexturedIconButton(ModResources.WOOD_ARROW_LEFT, posX + 90, posY + 220, 50, 30, StringTextComponent.field_240750_d_, btn -> {
/*     */           if (this.setupStep == 1) {
/*     */             showChallengeInfoStep();
/*     */           } else if (this.setupStep == 2) {
/*     */             showGroupStep();
/*     */           } 
/*     */         });
/*     */     
/* 337 */     this.prevStepButton = this.prevStepButton.setTextureInfo(posX + 90, posY + 220, 50, 30);
/* 338 */     this.prevStepButton.field_230694_p_ = false;
/* 339 */     func_230480_a_((Widget)this.prevStepButton);
/*     */     
/* 341 */     this.nextStepButton = new TexturedIconButton(ModResources.WOOD_ARROW_RIGHT, posX + 170, posY + 220, 50, 30, StringTextComponent.field_240750_d_, btn -> {
/*     */           if (this.setupStep == 0) {
/*     */             showGroupStep();
/*     */           }
/*     */         });
/* 346 */     this.nextStepButton = this.nextStepButton.setTextureInfo(posX + 170, posY + 220, 50, 30);
/* 347 */     this.nextStepButton.field_230694_p_ = false;
/* 348 */     func_230480_a_((Widget)this.nextStepButton);
/*     */     
/* 350 */     this.startChallengeButton = new PlankButton(posX + 250, posY + 220, 70, 30, (ITextComponent)ModI18n.GUI_START, btn -> {
/*     */           this.startedChallenge = true;
/* 352 */           boolean isFree = (this.freeCheckbox != null && this.freeCheckbox.func_212942_a());
/*     */           WyNetwork.sendToServer(new CStartChallengePacket(this.selectedChallenge.getCore().getRegistryName(), isFree));
/*     */           this.field_230706_i_.func_147108_a(null);
/*     */         });
/* 356 */     this.startChallengeButton.field_230694_p_ = false;
/* 357 */     func_230480_a_((Widget)this.startChallengeButton);
/*     */     
/* 359 */     this.freeCheckbox = new NewCheckboxButton(posX + 90, posY + 185, 60, 20, (ITextComponent)ModI18n.GUI_TRAINING, false);
/* 360 */     this.freeCheckbox.setTooltip((button, matrixStack, mouseX, mouseY) -> func_238654_b_(matrixStack, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)ModI18n.CHALLENGE_MESSAGE_TRAINING_INFO, Math.max(this.field_230708_k_ / 2 - 43, 240)), mouseX, mouseY));
/*     */ 
/*     */     
/* 363 */     this.freeCheckbox.field_230694_p_ = false;
/* 364 */     func_230480_a_((Widget)this.freeCheckbox);
/*     */     
/* 366 */     updateSelectedCategory();
/*     */   }
/*     */   
/*     */   private void showInvitesScreen() {
/* 370 */     startInfoPanelAnimation();
/* 371 */     this.selectedChallenge = null;
/* 372 */     this.nextStepButton.field_230694_p_ = false;
/* 373 */     this.prevStepButton.field_230694_p_ = false;
/* 374 */     this.freeCheckbox.field_230694_p_ = false;
/* 375 */     this.startChallengeButton.field_230694_p_ = false;
/*     */     
/* 377 */     removeGroupMemberElements();
/*     */     
/* 379 */     this.field_230705_e_.remove(this.challengeInvitesPanel);
/* 380 */     this.challengeInvitesPanel = new ChallengeInvitesPanel(this, (PlayerEntity)this.player, this.invites);
/* 381 */     this.field_230705_e_.add(this.challengeInvitesPanel);
/*     */   }
/*     */   
/*     */   private void showChallengeInfoStep() {
/* 385 */     startInfoPanelAnimation();
/* 386 */     this.setupStep = 0;
/* 387 */     this.startChallengeButton.field_230694_p_ = false;
/* 388 */     this.nextStepButton.field_230694_p_ = true;
/* 389 */     this.prevStepButton.field_230694_p_ = false;
/* 390 */     this.freeCheckbox.field_230694_p_ = false;
/*     */     
/* 392 */     removeGroupMemberElements();
/*     */     
/* 394 */     this.field_230705_e_.remove(this.challengeInvitesPanel);
/* 395 */     this.selectedMemberSlot = -1;
/*     */   }
/*     */   
/*     */   public void showGroupStep() {
/* 399 */     startInfoPanelAnimation();
/* 400 */     this.setupStep = 1;
/* 401 */     this.startChallengeButton.field_230694_p_ = true;
/* 402 */     this.nextStepButton.field_230694_p_ = false;
/* 403 */     this.prevStepButton.field_230694_p_ = true;
/* 404 */     this.freeCheckbox.field_230694_p_ = true;
/*     */     
/* 406 */     this.field_230705_e_.remove(this.challengesGroupSelectorPanel);
/*     */     
/* 408 */     createGroupButtons();
/*     */   }
/*     */   
/*     */   private void showGroupSelectionStep() {
/* 412 */     startInfoPanelAnimation();
/* 413 */     this.setupStep = 2;
/* 414 */     this.startChallengeButton.field_230694_p_ = false;
/* 415 */     this.nextStepButton.field_230694_p_ = false;
/* 416 */     this.prevStepButton.field_230694_p_ = true;
/* 417 */     this.freeCheckbox.field_230694_p_ = false;
/*     */     
/* 419 */     for (int i = 0; i < this.groupMemberButtons.length; i++) {
/* 420 */       this.groupMemberButtons[i] = null;
/*     */     }
/* 422 */     this.field_230705_e_.removeIf(obtn -> obtn instanceof GroupMemberButton);
/*     */   }
/*     */   
/*     */   private void createGroupButtons() {
/* 426 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 427 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */ 
/*     */ 
/*     */     
/* 431 */     for (int i = 0; i < this.groupMemberButtons.length; i++) {
/* 432 */       this.groupMemberButtons[i] = null;
/*     */     }
/* 434 */     this.field_230705_e_.removeIf(obtn -> obtn instanceof GroupMemberButton);
/* 435 */     this.selectedMemberSlot = -1;
/*     */     
/* 437 */     this.groupOwnerButton = new GroupMemberButton(posX + 100, posY + 65, 45, 45, (LivingEntity)this.player, this.dummyBust, btn -> { 
/* 438 */         }); this.groupOwnerButton.field_230693_o_ = false;
/* 439 */     func_230481_d_((IGuiEventListener)this.groupOwnerButton);
/*     */     
/* 441 */     GroupMemberButton groupMember1 = new GroupMemberButton(posX + 170, posY + 65, 45, 45, this.group[0], this.dummyBust, btn -> WyNetwork.sendToServer(new CScreenEventPacket(100)));
/* 442 */     this.groupMemberButtons[0] = groupMember1;
/* 443 */     func_230481_d_((IGuiEventListener)groupMember1);
/*     */     
/* 445 */     GroupMemberButton groupMember2 = new GroupMemberButton(posX + 100, posY + 120, 45, 45, this.group[1], this.dummyBust, btn -> WyNetwork.sendToServer(new CScreenEventPacket(101)));
/* 446 */     this.groupMemberButtons[1] = groupMember2;
/* 447 */     func_230481_d_((IGuiEventListener)groupMember2);
/*     */     
/* 449 */     GroupMemberButton groupMember3 = new GroupMemberButton(posX + 170, posY + 120, 45, 45, this.group[2], this.dummyBust, btn -> WyNetwork.sendToServer(new CScreenEventPacket(102)));
/* 450 */     this.groupMemberButtons[2] = groupMember3;
/* 451 */     func_230481_d_((IGuiEventListener)groupMember3);
/*     */ 
/*     */     
/* 454 */     this.freeCheckbox.field_230694_p_ = (this.selectedChallenge.getCore().getDifficulty() != ChallengeDifficulty.STANDARD);
/*     */   }
/*     */   
/*     */   public void removeGroupMemberElements() {
/* 458 */     for (int i = 0; i < this.groupMemberButtons.length; i++) {
/* 459 */       this.groupMemberButtons[i] = null;
/*     */     }
/* 461 */     this.field_230705_e_.removeIf(obtn -> obtn instanceof GroupMemberButton);
/*     */   }
/*     */   
/*     */   public void setGroupMember(int idx, LivingEntity entity) {
/* 465 */     this.group[idx] = entity;
/* 466 */     showGroupStep();
/*     */   }
/*     */   
/*     */   public void setGroupMember(LivingEntity entity) {
/* 470 */     this.group[this.selectedMemberSlot] = entity;
/* 471 */     showGroupStep();
/*     */   }
/*     */   
/*     */   public int getSelectedMemberSlot() {
/* 475 */     return this.selectedMemberSlot;
/*     */   }
/*     */   
/*     */   public LivingEntity[] getGroup() {
/* 479 */     return this.group;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEvent(int eventId, @Nullable Object data) {
/* 484 */     switch (eventId) {
/*     */       case 100:
/*     */       case 101:
/*     */       case 102:
/* 488 */         openNearbyGroupSelector(eventId - 100, (List<Integer>)data);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void openNearbyGroupSelector(int id, List<Integer> nearbyIds) {
/* 495 */     showGroupSelectionStep();
/* 496 */     this.selectedMemberSlot = id;
/* 497 */     this.field_230705_e_.remove(this.challengesGroupSelectorPanel);
/* 498 */     this.challengesGroupSelectorPanel = new ChallengeGroupSelectorPanel(this, (PlayerEntity)this.player, nearbyIds);
/* 499 */     this.field_230705_e_.add(this.challengesGroupSelectorPanel);
/*     */   }
/*     */   
/*     */   private String createDifficultyStars() {
/* 503 */     if (this.selectedChallenge == null) {
/* 504 */       return "";
/*     */     }
/*     */     
/* 507 */     int difficulty = this.selectedChallenge.getCore().getDifficultyStars();
/*     */     
/* 509 */     boolean showSimpleDifficulty = ClientConfig.INSTANCE.isSimpleDisplaysEnabled();
/* 510 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 512 */     if (showSimpleDifficulty) {
/* 513 */       int layer = difficulty / 10;
/* 514 */       TextFormatting color = TextFormatting.RESET;
/* 515 */       if (layer == 0) {
/* 516 */         color = TextFormatting.YELLOW;
/*     */       }
/* 518 */       else if (layer == 1) {
/* 519 */         color = TextFormatting.RED;
/*     */       }
/* 521 */       else if (layer == 2) {
/* 522 */         color = TextFormatting.DARK_PURPLE;
/*     */       } 
/*     */       
/* 525 */       sb.append(color.toString() + difficulty + " / " + ModValues.MAX_DIFFICULTY);
/*     */     } else {
/*     */       
/* 528 */       String[] starChars = { "☆", "☆", "☆", "☆", "☆", "☆", "☆", "☆", "☆", "☆" };
/*     */       
/* 530 */       int max_layer = (difficulty - 1) / starChars.length;
/* 531 */       for (int i = 0; i < ModValues.MAX_DIFFICULTY; i++) {
/* 532 */         int starPos = i % starChars.length;
/* 533 */         int layer = i / starChars.length;
/* 534 */         if (i < difficulty) {
/* 535 */           TextFormatting color = TextFormatting.RESET;
/* 536 */           if (layer == 0) {
/* 537 */             color = TextFormatting.YELLOW;
/*     */           }
/* 539 */           else if (layer == 1) {
/* 540 */             color = TextFormatting.RED;
/*     */           }
/* 542 */           else if (layer == 2) {
/* 543 */             color = TextFormatting.DARK_PURPLE;
/*     */           } 
/*     */           
/* 546 */           starChars[starPos] = color.toString() + ((layer < max_layer) ? "☆" : "★") + "§r";
/*     */         } 
/*     */       } 
/*     */       
/* 550 */       for (String star : starChars) {
/* 551 */         sb.append(star);
/*     */       }
/*     */     } 
/*     */     
/* 555 */     return sb.toString();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Challenge getSelectedChallenge() {
/* 560 */     return this.selectedChallenge;
/*     */   }
/*     */   
/*     */   public void setSelectedChallenge(@Nullable Challenge ch) {
/* 564 */     this.selectedChallenge = ch;
/* 565 */     this.selectedChallengeStars = createDifficultyStars();
/* 566 */     this.setupStep = 0;
/* 567 */     updateTargets();
/* 568 */     this.field_230710_m_.removeIf(obtn -> obtn instanceof GroupMemberButton);
/* 569 */     this.startChallengeButton.field_230694_p_ = false;
/* 570 */     this.nextStepButton.field_230694_p_ = (this.selectedChallenge != null);
/* 571 */     this.prevStepButton.field_230694_p_ = false;
/* 572 */     this.freeCheckbox.field_230694_p_ = false;
/* 573 */     this.selectedMemberSlot = -1;
/* 574 */     for (int i = 0; i < this.group.length; i++) {
/* 575 */       this.group[i] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private void updateTargets() {
/* 581 */     if (this.selectedChallenge == null) {
/*     */       return;
/*     */     }
/*     */     
/* 585 */     this.targets = new LivingEntity[4];
/*     */     
/* 587 */     int i = 0;
/* 588 */     for (LivingEntity target : this.selectedChallenge.getCore().getTargetShowcase((World)this.field_230706_i_.field_71441_e)) {
/* 589 */       if (i > 3) {
/*     */         break;
/*     */       }
/*     */       
/* 593 */       if (target != null) {
/*     */ 
/*     */ 
/*     */         
/* 597 */         this.targets[i] = target;
/* 598 */         i++;
/*     */       } 
/*     */     } 
/* 601 */     this.targetsCount = i;
/*     */   }
/*     */   
/*     */   public void updateSelectedCategory() {
/* 605 */     this.selectedCategory = this.categories.get(this.selectedCategoryId);
/* 606 */     setSelectedChallenge((Challenge)null);
/* 607 */     this.runInfoPanelAnimation = false;
/* 608 */     this.infoPanelAnimationTick = 0.0F;
/* 609 */     this.runCategoryChangeAnimation = true;
/* 610 */     this.categoryChangeAnimationTick = 0.0F;
/*     */     
/* 612 */     if (Strings.isNullOrEmpty(this.selectedCategory)) {
/* 613 */       this.displayedChallenges = this.challenges;
/* 614 */       this.selectedCategoryLocalizedName = (ITextComponent)ModI18n.GUI_ALL;
/* 615 */       this.selectedCategoryIcon = null;
/*     */     } else {
/*     */       
/* 618 */       this.displayedChallenges = this.challengesMap.get(this.selectedCategory);
/* 619 */       this.selectedCategoryLocalizedName = (ITextComponent)new TranslationTextComponent(this.selectedCategory);
/* 620 */       String categoryId = this.selectedCategory.replace("challenge.category.", "").replace("crew.name.", "");
/* 621 */       this.selectedCategoryIcon = new ResourceLocation("mineminenomi", "textures/gui/challenges/" + WyHelper.getResourceName(categoryId) + ".png");
/*     */     } 
/*     */     
/* 624 */     this.field_230705_e_.remove(this.challengesListPanel);
/* 625 */     this.challengesListPanel = new AvailableChallengesListPanel(this, this.displayedChallenges);
/* 626 */     this.field_230705_e_.add(this.challengesListPanel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderEntityBust(@Nullable LivingEntity entity, MatrixStack matrixStack, int posX, int posY) {
/* 631 */     if (entity != null) {
/* 632 */       RendererHelper.drawLivingBust(entity, matrixStack, posX, posY, 30, -30, 10, false);
/* 633 */       String entityName = getEntityName(entity);
/* 634 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, entityName, posX - this.field_230712_o_.func_78256_a(entityName) / 2, posY - 30, -1);
/*     */     } else {
/*     */       
/* 637 */       RendererHelper.drawLivingBust(this.dummyBust, matrixStack, posX, posY, 30, -30, 10, true);
/* 638 */       String entityName = "Select One";
/* 639 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, entityName, posX - this.field_230712_o_.func_78256_a(entityName) / 2, posY - 30, -1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getEntityName(LivingEntity entity) {
/* 644 */     if (entity instanceof PlayerEntity) {
/* 645 */       return ((PlayerEntity)entity).func_146103_bH().getName();
/*     */     }
/* 647 */     return entity.func_145748_c_().getString();
/*     */   }
/*     */   
/*     */   public void startInfoPanelAnimation() {
/* 651 */     this.runInfoPanelAnimation = true;
/* 652 */     this.infoPanelAnimationTick = 0.0F;
/*     */   }
/*     */   
/*     */   public LivingEntity getDummyBust() {
/* 656 */     return this.dummyBust;
/*     */   }
/*     */   
/*     */   public boolean isInfoPanelAnimationComplete() {
/* 660 */     return (this.selectedChallenge != null && this.runInfoPanelAnimation && this.infoPanelAnimationTick >= 20.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231175_as__() {
/* 665 */     if (!this.startedChallenge) {
/* 666 */       WyNetwork.sendToServer(new CDisbandChallengeGroupPacket());
/*     */     }
/* 668 */     super.func_231175_as__();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 673 */     return false;
/*     */   }
/*     */   
/*     */   public static void open(List<Challenge> challenges, List<ChallengeInvitation> invites) {
/* 677 */     Minecraft.func_71410_x().func_147108_a(new ChallengesScreen(challenges, invites));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\ChallengesScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */