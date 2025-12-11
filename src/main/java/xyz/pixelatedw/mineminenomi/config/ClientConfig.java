/*     */ package xyz.pixelatedw.mineminenomi.config;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import net.minecraftforge.common.ForgeConfigSpec;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.INextEnum;
/*     */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClientConfig
/*     */ {
/*  22 */   private static final Path CONFIG_PATH = Paths.get("config", new String[] { "mineminenomi-client.toml" });
/*     */   public static final ClientConfig INSTANCE;
/*     */   public static final ForgeConfigSpec SPEC;
/*     */   private Map<String, ForgeConfigSpec.BooleanValue> cooldownVisual;
/*     */   public ForgeConfigSpec.IntValue onFireVisibility;
/*     */   private ForgeConfigSpec.BooleanValue alwaysLegUp;
/*     */   
/*     */   public enum SlotNumberVisuals
/*     */     implements INextEnum {
/*  31 */     TICKS,
/*  32 */     SECONDS,
/*  33 */     PERCENTAGE;
/*     */ 
/*     */     
/*     */     public <T extends INextEnum> T next() {
/*  37 */       return (T)values()[(ordinal() + 1) % (values()).length];
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/*  43 */     Pair<ClientConfig, ForgeConfigSpec> pair = (new ForgeConfigSpec.Builder()).configure(ClientConfig::new);
/*  44 */     SPEC = (ForgeConfigSpec)pair.getRight();
/*  45 */     INSTANCE = (ClientConfig)pair.getLeft();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClientConfig(ForgeConfigSpec.Builder builder) {
/*  59 */     builder.push("General");
/*     */     
/*  61 */     this.onFireVisibility = builder.comment("Visibility when on fire while using a fire resistant fruit \nDefault: 20").defineInRange("Fire Visibility", 25, 0, 100);
/*  62 */     AbilitiesConfig.HAKI_COLOR.createValue(builder);
/*  63 */     this.alwaysLegUp = builder.comment("Always hold the leg up for combat as a Black Leg user\nDefault: true").define("Black Leg Always Up", true);
/*  64 */     builder.push("Cooldown Visuals");
/*     */     
/*  66 */     String[] cooldownVisuals = { "Text", "Color" };
/*  67 */     this.cooldownVisual = new HashMap<>();
/*     */     
/*  69 */     for (String mode : cooldownVisuals) {
/*  70 */       this.cooldownVisual.put(mode, builder.define(mode, true));
/*     */     }
/*     */     
/*  73 */     AbilitiesConfig.COMBAT_STATE_UPDATE_CHAT_MESSAGGE.createValue(builder);
/*  74 */     builder.pop();
/*     */     
/*  76 */     builder.pop();
/*     */     
/*  78 */     builder.push("UI");
/*     */     
/*  80 */     UIConfig.DISPLAY_IN_SECONDS.createValue(builder);
/*  81 */     UIConfig.ABILITY_BARS_ON_SCREEN.createValue(builder);
/*  82 */     UIConfig.SHOW_KEYBIND.createValue(builder);
/*  83 */     UIConfig.MERGE_ABILITY_BONUSES.createValue(builder);
/*  84 */     UIConfig.SIMPLE_DISPLAYS.createValue(builder);
/*  85 */     UIConfig.USE_HEARTS_BAR.createValue(builder);
/*  86 */     UIConfig.SLOT_NUMBER_DISPLAY.createValue(builder);
/*  87 */     UIConfig.HIDE_ABILITY_STATS.createValue(builder);
/*     */     
/*  89 */     builder.push("Ability List");
/*     */     
/*  91 */     UIConfig.ABILITY_LIST_COMPACTNESS.createValue(builder);
/*  92 */     UIConfig.ABILITY_LIST_SELECTION.createValue(builder);
/*  93 */     UIConfig.ABILITY_LIST_SHOW_TOOLTIPS.createValue(builder);
/*  94 */     UIConfig.ABILITY_LIST_SHOW_HELP.createValue(builder);
/*     */ 
/*     */     
/*  97 */     builder.pop();
/*     */     
/*  99 */     builder.push("System");
/*     */     
/* 101 */     SystemConfig.UPDATE_MESSAGE.createValue(builder);
/* 102 */     SystemConfig.MOD_SPLASH_TEXT.createValue(builder);
/* 103 */     SystemConfig.BLUE_GORO.createValue(builder);
/*     */     
/* 105 */     builder.pop();
/*     */   }
/*     */   
/*     */   public void toggleHelpButton() {
/* 109 */     boolean flag = !((Boolean)UIConfig.ABILITY_LIST_SHOW_HELP.get()).booleanValue();
/* 110 */     UIConfig.ABILITY_LIST_SHOW_HELP.getValue().set(Boolean.valueOf(flag));
/* 111 */     SPEC.save();
/*     */   }
/*     */   
/*     */   public boolean getHelpButtonShown() {
/* 115 */     return ((Boolean)UIConfig.ABILITY_LIST_SHOW_HELP.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public void toggleShowingTooltips() {
/* 119 */     boolean flag = !((Boolean)UIConfig.ABILITY_LIST_SHOW_TOOLTIPS.get()).booleanValue();
/* 120 */     UIConfig.ABILITY_LIST_SHOW_TOOLTIPS.getValue().set(Boolean.valueOf(flag));
/* 121 */     SPEC.save();
/*     */   }
/*     */   
/*     */   public boolean getTooltipsShown() {
/* 125 */     return ((Boolean)UIConfig.ABILITY_LIST_SHOW_TOOLTIPS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public void setSelectionMode(SelectHotbarAbilitiesScreen.Selection option) {
/* 129 */     UIConfig.ABILITY_LIST_SELECTION.getValue().set(option);
/* 130 */     SPEC.save();
/*     */   }
/*     */   
/*     */   public SelectHotbarAbilitiesScreen.Selection getSelectionMode() {
/* 134 */     return (SelectHotbarAbilitiesScreen.Selection)UIConfig.ABILITY_LIST_SELECTION.get();
/*     */   }
/*     */   
/*     */   public void setCompactness(SelectHotbarAbilitiesScreen.Compactness option) {
/* 138 */     UIConfig.ABILITY_LIST_COMPACTNESS.getValue().set(option);
/* 139 */     SPEC.save();
/*     */   }
/*     */   
/*     */   public SelectHotbarAbilitiesScreen.Compactness getCompactness() {
/* 143 */     return (SelectHotbarAbilitiesScreen.Compactness)UIConfig.ABILITY_LIST_COMPACTNESS.get();
/*     */   }
/*     */   
/*     */   public boolean isGoroBlue() {
/* 147 */     return ((Boolean)SystemConfig.BLUE_GORO.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isDisplayInTicks() {
/* 151 */     return (UIConfig.SLOT_NUMBER_DISPLAY.get() == SlotNumberVisuals.TICKS);
/*     */   }
/*     */   
/*     */   public boolean isDisplayInSeconds() {
/* 155 */     return (UIConfig.SLOT_NUMBER_DISPLAY.get() == SlotNumberVisuals.SECONDS);
/*     */   }
/*     */   
/*     */   public boolean isDisplayInPercentage() {
/* 159 */     return (UIConfig.SLOT_NUMBER_DISPLAY.get() == SlotNumberVisuals.PERCENTAGE);
/*     */   }
/*     */   
/*     */   public boolean hidesAbilityStats() {
/* 163 */     return ((Boolean)UIConfig.HIDE_ABILITY_STATS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean hasCombatUpdateChatMessageEnabled() {
/* 167 */     return ((Boolean)AbilitiesConfig.COMBAT_STATE_UPDATE_CHAT_MESSAGGE.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isSimpleDisplaysEnabled() {
/* 171 */     return ((Boolean)UIConfig.SIMPLE_DISPLAYS.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean hasHeartsUI() {
/* 175 */     if (!((Boolean)GeneralConfig.EXTRA_HEARTS.get()).booleanValue()) {
/* 176 */       return false;
/*     */     }
/* 178 */     return ((Boolean)UIConfig.USE_HEARTS_BAR.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isAbilityBonusesMergeEnable() {
/* 182 */     return ((Boolean)UIConfig.MERGE_ABILITY_BONUSES.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isModSplashTextEnabled() {
/* 186 */     return ((Boolean)SystemConfig.MOD_SPLASH_TEXT.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlackLegAlwaysUp() {
/* 191 */     return ((Boolean)this.alwaysLegUp.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getHakiColor() {
/* 196 */     Color color = new Color(16711680);
/*     */     
/*     */     try {
/* 199 */       String c = (String)AbilitiesConfig.HAKI_COLOR.get();
/* 200 */       if (c.startsWith("#")) {
/* 201 */         color = WyHelper.hexToRGB(c);
/*     */       } else {
/*     */         
/* 204 */         int n = Integer.parseInt(c);
/* 205 */         if (n < 0 || n > 16777215)
/* 206 */           throw new Exception("Haki Color outside its bounds: " + n + " Can only use numbers between 0 and 16777215"); 
/* 207 */         color = new Color(n);
/*     */       }
/*     */     
/* 210 */     } catch (Exception e) {
/*     */       
/* 212 */       e.printStackTrace();
/*     */     } 
/* 214 */     return color;
/*     */   }
/*     */   
/*     */   public boolean showSlotKeybinds() {
/* 218 */     return ((Boolean)UIConfig.SHOW_KEYBIND.get()).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAbilityBarsOnScreen() {
/* 223 */     return ((Integer)UIConfig.ABILITY_BARS_ON_SCREEN.get()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[] getCooldownVisuals() {
/* 228 */     boolean hasText = ((Boolean)((ForgeConfigSpec.BooleanValue)this.cooldownVisual.get("Text")).get()).booleanValue();
/* 229 */     boolean hasColor = ((Boolean)((ForgeConfigSpec.BooleanValue)this.cooldownVisual.get("Color")).get()).booleanValue();
/*     */     
/* 231 */     return new boolean[] { hasText, hasColor };
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFireVisibility() {
/* 236 */     return ((Integer)this.onFireVisibility.get()).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\config\ClientConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */