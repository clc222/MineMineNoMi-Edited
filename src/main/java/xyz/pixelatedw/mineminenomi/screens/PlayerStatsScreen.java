/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.ITextProperties;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenChallengesScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenCrewScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.PlankButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PlayerStatsScreen
/*     */   extends Screen
/*     */ {
/*     */   private final PlayerEntity player;
/*     */   private IEntityStats entityStatsProps;
/*     */   private IDevilFruit devilFruitProps;
/*     */   private IChallengesData challengesProps;
/*     */   private final double doriki;
/*     */   private final boolean hasQuests;
/*     */   
/*     */   public PlayerStatsScreen(double doriki, boolean hasQuests, int questAmount, boolean hasChallenges, int challengeAmount, boolean isInCombat, boolean isInChallengeDimension, int invites, boolean hasCrew) {
/*  60 */     super(StringTextComponent.field_240750_d_);
/*  61 */     this.player = (PlayerEntity)(Minecraft.func_71410_x()).field_71439_g;
/*  62 */     this.doriki = doriki;
/*  63 */     this.hasQuests = hasQuests;
/*  64 */     this.questAmount = questAmount;
/*  65 */     this.hasChallenges = hasChallenges;
/*  66 */     this.challengeAmount = challengeAmount;
/*  67 */     this.isInCombat = isInCombat;
/*  68 */     this.isInChallengeDimension = isInChallengeDimension;
/*  69 */     this.invites = invites;
/*  70 */     this.hasCrew = hasCrew;
/*     */   }
/*     */   private final int questAmount; private final boolean hasChallenges; private final int challengeAmount; private final boolean isInCombat; private final boolean isInChallengeDimension; private final int invites; private final boolean hasCrew;
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/*     */     String factionStr, raceStr, styleStr;
/*  76 */     func_230446_a_(matrixStack);
/*     */     
/*  78 */     int posX = (this.field_230708_k_ - 256) / 2;
/*  79 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */     
/*  81 */     String colaLabel = (new TranslationTextComponent(ModI18n.GUI_COLA)).getString();
/*  82 */     String dorikiLabel = (new TranslationTextComponent(ModI18n.GUI_DORIKI)).getString();
/*     */     
/*  84 */     ResourceLocation faction = this.entityStatsProps.getFaction();
/*     */     
/*  86 */     if (faction == null) {
/*  87 */       factionStr = "mineminenomi.empty";
/*     */     } else {
/*  89 */       factionStr = WyHelper.getResourceName(faction.toString().replace(':', '.'));
/*     */     } 
/*     */     
/*  92 */     ResourceLocation race = this.entityStatsProps.getRace();
/*     */     
/*  94 */     if (race == null) {
/*  95 */       raceStr = "mineminenomi.empty";
/*     */     } else {
/*  97 */       raceStr = WyHelper.getResourceName(race.toString().replace(':', '.'));
/*     */     } 
/*     */     
/* 100 */     ResourceLocation style = this.entityStatsProps.getFightingStyle();
/*     */     
/* 102 */     if (race == null) {
/* 103 */       styleStr = "mineminenomi.empty";
/*     */     } else {
/* 105 */       styleStr = WyHelper.getResourceName(style.toString().replace(':', '.'));
/*     */     } 
/*     */     
/* 108 */     String actualRank = "";
/* 109 */     if (this.entityStatsProps.isMarine()) {
/*     */       
/* 111 */       FactionHelper.MarineRank marineRank = this.entityStatsProps.getMarineRank();
/* 112 */       actualRank = (marineRank != null) ? (" - " + marineRank.getLocalizedName()) : "";
/*     */     }
/* 114 */     else if (this.entityStatsProps.isRevolutionary()) {
/*     */       
/* 116 */       FactionHelper.RevolutionaryRank revoRank = this.entityStatsProps.getRevolutionaryRank();
/* 117 */       actualRank = (revoRank != null) ? (" - " + revoRank.getLocalizedName()) : "";
/*     */     } 
/*     */     
/* 120 */     String factionActual = (new TranslationTextComponent("faction." + factionStr)).getString() + actualRank;
/* 121 */     String raceActual = (new TranslationTextComponent("race." + raceStr)).getString();
/* 122 */     String styleActual = (new TranslationTextComponent("style." + styleStr)).getString();
/*     */     
/* 124 */     if (this.entityStatsProps.isCyborg()) {
/* 125 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + colaLabel + ": " + TextFormatting.RESET + this.entityStatsProps.getCola() + " / " + this.entityStatsProps.getMaxCola(), posX - 50, posY + 50, -1);
/*     */     }
/* 127 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + dorikiLabel + ": " + TextFormatting.RESET + Math.round(this.doriki), posX - 50, posY + 70, -1);
/* 128 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + ModI18n.FACTION_NAME.getString() + ": " + TextFormatting.RESET + factionActual, posX - 50, posY + 90, -1);
/* 129 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + ModI18n.RACE_NAME.getString() + ": " + TextFormatting.RESET + raceActual, posX - 50, posY + 110, -1);
/* 130 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + ModI18n.STYLE_NAME.getString() + ": " + TextFormatting.RESET + styleActual, posX - 50, posY + 130, -1);
/*     */     
/* 132 */     if (this.entityStatsProps.getBelly() > 0L) {
/*     */       
/* 134 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "" + this.entityStatsProps.getBelly(), posX + 215, posY + 72, -1);
/* 135 */       this.field_230706_i_.field_71446_o.func_110577_a(ModResources.CURRENCIES);
/* 136 */       func_238474_b_(matrixStack, posX + 190, posY + 60, 0, 32, 32, 64);
/*     */     } 
/*     */     
/* 139 */     if (this.entityStatsProps.getExtol() > 0L) {
/*     */       
/* 141 */       WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, "" + this.entityStatsProps.getExtol(), posX + 215, posY + 102, -1);
/* 142 */       this.field_230706_i_.field_71446_o.func_110577_a(ModResources.CURRENCIES);
/* 143 */       func_238474_b_(matrixStack, posX + 190, posY + 89, 34, 32, 64, 86);
/*     */     } 
/*     */     
/* 146 */     if (this.devilFruitProps.hasAnyDevilFruit()) {
/*     */       
/* 148 */       ItemStack yamiFruit = new ItemStack((IItemProvider)ModAbilities.YAMI_YAMI_NO_MI);
/*     */       
/* 150 */       if (this.devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI)) {
/*     */         
/* 152 */         ItemStack df = new ItemStack((IItemProvider)this.devilFruitProps.getDevilFruitItem());
/* 153 */         ResourceLocation dfKey = df.func_77973_b().getRegistryName();
/*     */         
/* 155 */         boolean dual = (this.devilFruitProps.hasYamiPower() && !dfKey.equals(ModAbilities.YAMI_YAMI_NO_MI.getRegistryName()));
/* 156 */         int color = -1;
/* 157 */         if (this.devilFruitProps.hasAwakenedFruit()) {
/* 158 */           color = WyHelper.hexToRGB("#ECA629").getRGB();
/*     */         }
/*     */         
/* 161 */         if (dual) {
/* 162 */           this.field_230706_i_.field_71466_p.func_238405_a_(matrixStack, TextFormatting.BOLD + "" + yamiFruit.func_200301_q().getString() + " + " + df.func_200301_q().getString(), (posX - 28), (posY + 194), color);
/*     */         } else {
/* 164 */           this.field_230706_i_.field_71466_p.func_238405_a_(matrixStack, TextFormatting.BOLD + "" + yamiFruit.func_200301_q().getString(), (posX - 28), (posY + 194), color);
/*     */         } 
/* 166 */         if (dual)
/* 167 */           drawItemStack(df, posX - 56, posY + 187, ""); 
/* 168 */         drawItemStack(yamiFruit, posX - 50, posY + 190, "");
/*     */       }
/*     */       else {
/*     */         
/* 172 */         ItemStack df = new ItemStack((IItemProvider)this.devilFruitProps.getDevilFruitItem());
/* 173 */         if (!df.func_190926_b()) {
/* 174 */           String fruitName = df.func_200301_q().getString();
/* 175 */           if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*     */             
/* 177 */             AkumaNoMiItem item = ((AkumaNoMiItem)df.func_77973_b()).getReverseShiftedFruit(this.player.field_70170_p);
/* 178 */             df = new ItemStack((IItemProvider)item);
/*     */           } 
/* 180 */           boolean doubleYamiCheck = false;
/*     */           
/* 182 */           String dfKey = ((AkumaNoMiItem)df.func_77973_b()).getFruitKey();
/* 183 */           if (dfKey.equalsIgnoreCase("yami_yami") && this.devilFruitProps.hasYamiPower()) {
/* 184 */             doubleYamiCheck = true;
/*     */           }
/*     */           
/* 187 */           int color = -1;
/* 188 */           if (this.devilFruitProps.hasAwakenedFruit()) {
/* 189 */             color = WyHelper.hexToRGB("#ECA629").getRGB();
/*     */           }
/*     */           
/* 192 */           if (this.devilFruitProps.hasYamiPower() && !doubleYamiCheck) {
/* 193 */             this.field_230706_i_.field_71466_p.func_238405_a_(matrixStack, TextFormatting.BOLD + "" + yamiFruit.func_200301_q().getString() + " + " + df.func_200301_q().getString(), (posX - 28), (posY + 194), color);
/*     */           } else {
/* 195 */             this.field_230706_i_.field_71466_p.func_238405_a_(matrixStack, TextFormatting.BOLD + "" + fruitName, (posX - 28), (posY + 194), color);
/*     */           } 
/* 197 */           if (this.devilFruitProps.hasYamiPower() && !doubleYamiCheck)
/* 198 */             drawItemStack(yamiFruit, posX - 56, posY + 187, ""); 
/* 199 */           drawItemStack(df, posX - 50, posY + 190, "");
/*     */         } else {
/*     */           
/* 202 */           this.field_230706_i_.field_71466_p.func_238405_a_(matrixStack, "§4§lUnknown Fruit§r", (posX - 28), (posY + 194), -1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     super.func_230430_a_(matrixStack, x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_231160_c_() {
/* 220 */     this.entityStatsProps = EntityStatsCapability.get((LivingEntity)this.player);
/* 221 */     this.devilFruitProps = DevilFruitCapability.get((LivingEntity)this.player);
/* 222 */     this.challengesProps = ChallengesDataCapability.get(this.player);
/*     */     
/* 224 */     IQuestData questProps = QuestDataCapability.get(this.player);
/* 225 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)this.player);
/*     */     
/* 227 */     int posX = (this.field_230708_k_ - 256) / 2 - 110;
/* 228 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */     
/* 230 */     boolean hasAbilities = (abilityProps.countUnlockedAbilities() > 0);
/*     */     
/* 232 */     posX += 80;
/*     */     
/* 234 */     PlankButton abilitiesButton = new PlankButton(posX, posY + 210, 80, 26, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_ABILITIES), b -> WyNetwork.sendToServer(new CRequestSyncAbilityDataPacket(true)));
/*     */     
/* 236 */     if (!hasAbilities) {
/* 237 */       abilitiesButton.field_230693_o_ = false;
/*     */     }
/*     */     
/* 240 */     func_230480_a_((Widget)abilitiesButton);
/*     */     
/* 242 */     if (this.hasQuests) {
/* 243 */       boolean hasQuests = (this.questAmount > 0);
/*     */       
/* 245 */       posX += 80;
/*     */       
/* 247 */       PlankButton questsButton = new PlankButton(posX, posY + 210, 80, 26, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_QUESTS), b -> WyNetwork.sendToServer(new CRequestSyncQuestDataPacket(true)));
/*     */       
/* 249 */       if (!hasQuests) {
/* 250 */         questsButton.field_230693_o_ = false;
/*     */       }
/*     */       
/* 253 */       func_230480_a_((Widget)questsButton);
/*     */     } 
/*     */     
/* 256 */     if (this.entityStatsProps.isPirate()) {
/* 257 */       posX += 80;
/*     */       
/* 259 */       PlankButton crewButton = new PlankButton(posX, posY + 210, 80, 26, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_CREW), b -> WyNetwork.sendToServer(new COpenCrewScreenPacket()));
/*     */       
/* 261 */       if (!this.hasCrew) {
/* 262 */         crewButton.field_230693_o_ = false;
/*     */       }
/*     */       
/* 265 */       func_230480_a_((Widget)crewButton);
/*     */     } 
/*     */     
/* 268 */     if (this.hasChallenges || this.invites > 0) {
/* 269 */       boolean challengesAmountCheck = (this.challengeAmount > 0 || this.invites > 0);
/* 270 */       posX += 80;
/*     */       
/* 272 */       Button.ITooltip tooltip = Button.field_238486_s_;
/* 273 */       if (!challengesAmountCheck) {
/* 274 */         tooltip = ((btn, matrix, mouseX, mouseY) -> func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)ModI18n.GUI_NO_CHALLENGES_AVAILABLE, Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY));
/*     */ 
/*     */       
/*     */       }
/* 278 */       else if (this.isInChallengeDimension) {
/* 279 */         tooltip = ((btn, matrix, mouseX, mouseY) -> func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)ModI18n.GUI_CHALLENGE_DIM_ACCESS_MENU, Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY));
/*     */ 
/*     */       
/*     */       }
/* 283 */       else if (this.isInCombat) {
/* 284 */         tooltip = ((btn, matrix, mouseX, mouseY) -> func_238654_b_(matrix, this.field_230706_i_.field_71466_p.func_238425_b_((ITextProperties)ModI18n.GUI_COMBAT_CANNOT_USE, Math.max(this.field_230708_k_ / 2 - 43, 170)), mouseX, mouseY));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 289 */       IFormattableTextComponent challengesMessage = ModI18n.GUI_CHALLENGES.func_230532_e_();
/* 290 */       if (this.invites > 0) {
/* 291 */         challengesMessage = challengesMessage.func_230529_a_((ITextComponent)new StringTextComponent(" (" + this.invites + ")"));
/*     */       }
/* 293 */       PlankButton challengesButton = new PlankButton(posX, posY + 210, 80, 26, (ITextComponent)challengesMessage, b -> WyNetwork.sendToServer(new COpenChallengesScreenPacket()), tooltip);
/*     */       
/* 295 */       if (!challengesAmountCheck || this.isInCombat || this.isInChallengeDimension) {
/* 296 */         challengesButton.field_230693_o_ = false;
/*     */       }
/*     */       
/* 299 */       func_230480_a_((Widget)challengesButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 305 */     return false;
/*     */   }
/*     */   
/*     */   private void drawItemStack(ItemStack itemStack, int x, int y, String string) {
/* 309 */     GL11.glTranslatef(0.0F, 0.0F, 32.0F);
/*     */     
/* 311 */     this.field_230707_j_.field_77023_b = 200.0F;
/*     */     
/* 313 */     FontRenderer font = null;
/*     */     
/* 315 */     if (itemStack != null) {
/* 316 */       font = itemStack.func_77973_b().getFontRenderer(itemStack);
/*     */     }
/*     */     
/* 319 */     if (font == null) {
/* 320 */       font = this.field_230712_o_;
/*     */     }
/*     */     
/* 323 */     this.field_230707_j_.func_175042_a(itemStack, x, y);
/* 324 */     this.field_230707_j_.field_77023_b = 0.0F;
/*     */   }
/*     */   
/*     */   public static void open(double doriki, boolean hasQuests, int questAmount, boolean hasChallenges, int challengeAmount, boolean isInCombat, boolean isInChallengeDimension, int invites, boolean hasCrew) {
/* 328 */     Minecraft.func_71410_x().func_147108_a(new PlayerStatsScreen(doriki, hasQuests, questAmount, hasChallenges, challengeAmount, isInCombat, isInChallengeDimension, invites, hasCrew));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\PlayerStatsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */