/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.client.util.InputMappings;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
/*     */ import net.minecraftforge.client.event.InputEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CToggleCombatModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CUseAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.components.CChangeAbilityAltModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.entity.CSwitchVehicleModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.entity.CSyncPlayerInputPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenChallengesScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenCrewScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenPlayerScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
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
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class ModKeybindings
/*     */ {
/*     */   public static KeyBinding test;
/*     */   public static KeyBinding guiPlayer;
/*     */   public static KeyBinding enterCombatMode;
/*     */   public static KeyBinding nextCombatBar;
/*     */   public static KeyBinding prevCombatBar;
/*     */   public static KeyBinding lastCombatBar;
/*     */   public static KeyBinding changeAbilityMode;
/*     */   public static KeyBinding vehicleAltMode;
/*     */   public static KeyBinding openAbilities;
/*     */   public static KeyBinding openChallenges;
/*     */   public static KeyBinding openQuests;
/*     */   public static KeyBinding openCrew;
/*  67 */   public static ArrayList<KeyBinding> keyBindsCombatbar = new ArrayList<>();
/*  68 */   public static KeyBinding[] combatbarShortcuts = new KeyBinding[10];
/*  69 */   private static final int[] PREVIOUS_INVENTORY_KEYBINDS = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*     */   
/*  71 */   public static int serverMaxBars = 2;
/*     */   
/*     */   public static void init() {
/*  74 */     if (WyDebug.isDebug()) {
/*  75 */       test = new KeyBinding("Test Key", 80, ModI18n.CATEGORY_GENERAL);
/*     */       
/*  77 */       ClientRegistry.registerKeyBinding(test);
/*     */     } 
/*     */     
/*  80 */     openAbilities = new KeyBinding(ModI18n.KEY_OPEN_ABILITIES, -1, ModI18n.CATEGORY_GENERAL);
/*  81 */     ClientRegistry.registerKeyBinding(openAbilities);
/*     */     
/*  83 */     openChallenges = new KeyBinding(ModI18n.KEY_OPEN_CHALLENGES, -1, ModI18n.CATEGORY_GENERAL);
/*  84 */     ClientRegistry.registerKeyBinding(openChallenges);
/*     */     
/*  86 */     openQuests = new KeyBinding(ModI18n.KEY_OPEN_QUESTS, -1, ModI18n.CATEGORY_GENERAL);
/*  87 */     ClientRegistry.registerKeyBinding(openQuests);
/*     */     
/*  89 */     openCrew = new KeyBinding(ModI18n.KEY_OPEN_CREW, -1, ModI18n.CATEGORY_GENERAL);
/*  90 */     ClientRegistry.registerKeyBinding(openCrew);
/*     */     
/*  92 */     vehicleAltMode = new KeyBinding(ModI18n.KEY_VEHICLE_ALT_MODE, 341, ModI18n.CATEGORY_GENERAL);
/*  93 */     ClientRegistry.registerKeyBinding(vehicleAltMode);
/*     */     
/*  95 */     guiPlayer = new KeyBinding(ModI18n.KEY_PLAYER, 82, ModI18n.CATEGORY_GENERAL);
/*  96 */     ClientRegistry.registerKeyBinding(guiPlayer);
/*     */     
/*  98 */     enterCombatMode = new KeyBinding(ModI18n.KEY_COMBATMODE, 342, ModI18n.CATEGORY_COMBATBAR);
/*  99 */     ClientRegistry.registerKeyBinding(enterCombatMode);
/*     */     
/* 101 */     nextCombatBar = new KeyBinding(ModI18n.KEY_NEXT_COMBAT_BAR, 93, ModI18n.CATEGORY_COMBATBAR);
/* 102 */     ClientRegistry.registerKeyBinding(nextCombatBar);
/*     */     
/* 104 */     prevCombatBar = new KeyBinding(ModI18n.KEY_PREV_COMBAT_BAR, 91, ModI18n.CATEGORY_COMBATBAR);
/* 105 */     ClientRegistry.registerKeyBinding(prevCombatBar);
/*     */     
/* 107 */     lastCombatBar = new KeyBinding(ModI18n.KEY_LAST_COMBAT_BAR, -1, ModI18n.CATEGORY_COMBATBAR);
/* 108 */     ClientRegistry.registerKeyBinding(lastCombatBar);
/*     */     
/* 110 */     changeAbilityMode = new KeyBinding(ModI18n.KEY_CHANGE_ABILITY_MODE, 340, ModI18n.CATEGORY_COMBATBAR);
/* 111 */     ClientRegistry.registerKeyBinding(changeAbilityMode);
/*     */     
/* 113 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT1, 49, ModI18n.CATEGORY_COMBATBAR));
/* 114 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT2, 50, ModI18n.CATEGORY_COMBATBAR));
/* 115 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT3, 51, ModI18n.CATEGORY_COMBATBAR));
/* 116 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT4, 52, ModI18n.CATEGORY_COMBATBAR));
/* 117 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT5, 53, ModI18n.CATEGORY_COMBATBAR));
/* 118 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT6, 54, ModI18n.CATEGORY_COMBATBAR));
/* 119 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT7, 55, ModI18n.CATEGORY_COMBATBAR));
/* 120 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR1_COMBATSLOT8, 56, ModI18n.CATEGORY_COMBATBAR));
/*     */     
/* 122 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT1, -1, ModI18n.CATEGORY_COMBATBAR));
/* 123 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT2, -1, ModI18n.CATEGORY_COMBATBAR));
/* 124 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT3, -1, ModI18n.CATEGORY_COMBATBAR));
/* 125 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT4, -1, ModI18n.CATEGORY_COMBATBAR));
/* 126 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT5, -1, ModI18n.CATEGORY_COMBATBAR));
/* 127 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT6, -1, ModI18n.CATEGORY_COMBATBAR));
/* 128 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT7, -1, ModI18n.CATEGORY_COMBATBAR));
/* 129 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR2_COMBATSLOT8, -1, ModI18n.CATEGORY_COMBATBAR));
/*     */     
/* 131 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT1, -1, ModI18n.CATEGORY_COMBATBAR));
/* 132 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT2, -1, ModI18n.CATEGORY_COMBATBAR));
/* 133 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT3, -1, ModI18n.CATEGORY_COMBATBAR));
/* 134 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT4, -1, ModI18n.CATEGORY_COMBATBAR));
/* 135 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT5, -1, ModI18n.CATEGORY_COMBATBAR));
/* 136 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT6, -1, ModI18n.CATEGORY_COMBATBAR));
/* 137 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT7, -1, ModI18n.CATEGORY_COMBATBAR));
/* 138 */     keyBindsCombatbar.add(new KeyBinding(ModI18n.KEY_BAR3_COMBATSLOT8, -1, ModI18n.CATEGORY_COMBATBAR));
/*     */     
/* 140 */     for (KeyBinding key : keyBindsCombatbar) {
/* 141 */       ClientRegistry.registerKeyBinding(key);
/*     */     }
/*     */     
/* 144 */     combatbarShortcuts[0] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT1, -1, ModI18n.CATEGORY_COMBATBAR);
/* 145 */     combatbarShortcuts[1] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT2, -1, ModI18n.CATEGORY_COMBATBAR);
/* 146 */     combatbarShortcuts[2] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT3, -1, ModI18n.CATEGORY_COMBATBAR);
/* 147 */     combatbarShortcuts[3] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT4, -1, ModI18n.CATEGORY_COMBATBAR);
/* 148 */     combatbarShortcuts[4] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT5, -1, ModI18n.CATEGORY_COMBATBAR);
/* 149 */     combatbarShortcuts[5] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT6, -1, ModI18n.CATEGORY_COMBATBAR);
/* 150 */     combatbarShortcuts[6] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT7, -1, ModI18n.CATEGORY_COMBATBAR);
/* 151 */     combatbarShortcuts[7] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT8, -1, ModI18n.CATEGORY_COMBATBAR);
/* 152 */     combatbarShortcuts[8] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT9, -1, ModI18n.CATEGORY_COMBATBAR);
/* 153 */     combatbarShortcuts[9] = new KeyBinding(ModI18n.KEY_BAR_SHORTCUT10, -1, ModI18n.CATEGORY_COMBATBAR);
/*     */     
/* 155 */     for (KeyBinding key : combatbarShortcuts) {
/* 156 */       ClientRegistry.registerKeyBinding(key);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isShiftKeyDown() {
/* 162 */     return (InputMappings.func_216506_a(Minecraft.func_71410_x().func_228018_at_().func_198092_i(), 340) || InputMappings.func_216506_a(Minecraft.func_71410_x().func_228018_at_().func_198092_i(), 344));
/*     */   }
/*     */   
/*     */   public static boolean isAltKeyDown() {
/* 166 */     return (InputMappings.func_216506_a(Minecraft.func_71410_x().func_228018_at_().func_198092_i(), 342) || InputMappings.func_216506_a(Minecraft.func_71410_x().func_228018_at_().func_198092_i(), 346));
/*     */   }
/*     */   
/*     */   public static boolean isSpaceKeyDown() {
/* 170 */     return InputMappings.func_216506_a(Minecraft.func_71410_x().func_228018_at_().func_198092_i(), 32);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerJoins(EntityJoinWorldEvent event) {
/* 175 */     if (event.getEntity() instanceof PlayerEntity) {
/* 176 */       for (int i = 0; i < PREVIOUS_INVENTORY_KEYBINDS.length; i++) {
/* 177 */         PREVIOUS_INVENTORY_KEYBINDS[i] = -1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerLeaves(ClientPlayerNetworkEvent.LoggedOutEvent event) {
/* 184 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 186 */     for (int i = 0; i < mc.field_71474_y.field_151456_ac.length; i++) {
/* 187 */       KeyBinding kb = mc.field_71474_y.field_151456_ac[i];
/*     */       
/* 189 */       if (PREVIOUS_INVENTORY_KEYBINDS[i] != -1) {
/* 190 */         kb.func_197979_b(InputMappings.func_197954_a(PREVIOUS_INVENTORY_KEYBINDS[i], 0));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onMouseInput(InputEvent.MouseInputEvent event) {
/* 197 */     Minecraft minecraft = Minecraft.func_71410_x();
/*     */     
/* 199 */     ClientPlayerEntity clientPlayerEntity = minecraft.field_71439_g;
/*     */     
/* 201 */     if (clientPlayerEntity == null || event.getAction() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 205 */     InputMappings.Input input = InputMappings.Type.MOUSE.func_197944_a(event.getButton());
/*     */     
/* 207 */     checkKeybindings((PlayerEntity)clientPlayerEntity, input, event.getAction(), event.getButton(), 0);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onKeyInput(InputEvent.KeyInputEvent event) {
/* 212 */     Minecraft minecraft = Minecraft.func_71410_x();
/*     */     
/* 214 */     ClientPlayerEntity clientPlayerEntity = minecraft.field_71439_g;
/*     */     
/* 216 */     if (clientPlayerEntity == null || event.getAction() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 220 */     InputMappings.Input input = InputMappings.func_197954_a(event.getKey(), event.getScanCode());
/*     */     
/* 222 */     checkKeybindings((PlayerEntity)clientPlayerEntity, input, event.getAction(), event.getKey(), 1);
/*     */   }
/*     */   
/*     */   private static void checkKeybindings(PlayerEntity player, InputMappings.Input input, int action, int key, int type) {
/* 226 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 228 */     if (vehicleAltMode.isActiveAndMatches(input)) {
/* 229 */       WyNetwork.sendToServer(new CSwitchVehicleModePacket());
/*     */     }
/*     */     
/* 232 */     Screen screen = mc.field_71462_r;
/*     */     
/* 234 */     boolean isPlayerScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen);
/* 235 */     boolean isAbilitiesScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen);
/* 236 */     boolean isCrewScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.CrewDetailsScreen);
/* 237 */     boolean isChallengesScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.ChallengesScreen);
/* 238 */     boolean isQuestScreen = (screen != null && screen instanceof xyz.pixelatedw.mineminenomi.screens.QuestsTrackerScreen);
/* 239 */     boolean isSecondaryScreenOpen = (isAbilitiesScreen || isCrewScreen || isChallengesScreen || isQuestScreen);
/*     */     
/* 241 */     if (action == 1) {
/* 242 */       checkScreenActivation(mc, openAbilities, input, action, key, type, screen, isAbilitiesScreen, () -> new CRequestSyncAbilityDataPacket(true));
/* 243 */       checkScreenActivation(mc, openChallenges, input, action, key, type, screen, isChallengesScreen, () -> new COpenChallengesScreenPacket());
/* 244 */       checkScreenActivation(mc, openQuests, input, action, key, type, screen, isQuestScreen, () -> new CRequestSyncQuestDataPacket(true));
/* 245 */       checkScreenActivation(mc, openCrew, input, action, key, type, screen, isCrewScreen, () -> new COpenCrewScreenPacket());
/*     */       
/* 247 */       if ((guiPlayer.isActiveAndMatches(input) && guiPlayer.func_151468_f()) || ((screen == null || isPlayerScreen || isSecondaryScreenOpen) && type == 1 && key == guiPlayer
/* 248 */         .getKey().func_197937_c())) {
/* 249 */         if (isPlayerScreen) {
/* 250 */           mc.func_147108_a(null);
/* 251 */         } else if (isSecondaryScreenOpen) {
/* 252 */           WyNetwork.sendToServer(new COpenPlayerScreenPacket());
/* 253 */         } else if (screen == null) {
/* 254 */           WyNetwork.sendToServer(new COpenPlayerScreenPacket());
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 259 */     if (screen != null) {
/*     */       return;
/*     */     }
/*     */     
/* 263 */     IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/* 264 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 266 */     if (entityStatsProps.isInCombatMode() && action != 2) {
/* 267 */       int bars = ClientConfig.INSTANCE.getAbilityBarsOnScreen();
/* 268 */       int clientMaxBars = CommonConfig.INSTANCE.getAbilityBars();
/* 269 */       int maxBars = Math.min(clientMaxBars, serverMaxBars);
/* 270 */       int amount = Math.min(bars, maxBars);
/*     */       
/* 272 */       if (nextCombatBar.isActiveAndMatches(input) && nextCombatBar.func_151468_f()) {
/*     */         
/* 274 */         if (abilityDataProps.getCombatBarSet() + amount < maxBars) {
/* 275 */           abilityDataProps.nextCombatBarSet(amount);
/*     */         } else {
/* 277 */           abilityDataProps.setCombatBarSet(0);
/*     */         }
/*     */       
/* 280 */       } else if (prevCombatBar.isActiveAndMatches(input) && prevCombatBar.func_151468_f()) {
/*     */         
/* 282 */         if (abilityDataProps.getCombatBarSet() - amount >= 0) {
/* 283 */           abilityDataProps.prevCombatBarSet(amount);
/*     */         }
/* 285 */         else if (maxBars == amount) {
/* 286 */           abilityDataProps.setCombatBarSet(0);
/*     */         } else {
/* 288 */           int barsOnLastPage = 0; int k;
/* 289 */           for (k = maxBars; k > 0; k -= amount) {
/* 290 */             barsOnLastPage = k;
/*     */           }
/* 292 */           abilityDataProps.setCombatBarSet(maxBars - barsOnLastPage);
/*     */         }
/*     */       
/*     */       }
/* 296 */       else if (lastCombatBar.isActiveAndMatches(input) && lastCombatBar.func_151468_f()) {
/*     */         
/* 298 */         int lastCombatBarSet = abilityDataProps.getLastCombatBarSet();
/* 299 */         abilityDataProps.setCombatBarSet(lastCombatBarSet);
/*     */       } 
/*     */       
/* 302 */       int j = 0;
/* 303 */       for (KeyBinding keybind : combatbarShortcuts) {
/* 304 */         if (keybind.isActiveAndMatches(input) && keybind.func_151468_f()) {
/* 305 */           int bar = j * bars + 1;
/* 306 */           bar = MathHelper.func_76125_a(bar, 1, clientMaxBars - 1);
/* 307 */           amount = Math.min(bar, maxBars);
/*     */           
/* 309 */           abilityDataProps.setCombatBarSet(amount - 1);
/*     */         } 
/* 311 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 315 */     if (!WyDebug.isDebug() || test.func_151470_d());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 336 */     if (enterCombatMode.isActiveAndMatches(input) && enterCombatMode.func_151468_f()) {
/* 337 */       keybindsAssignment(entityStatsProps);
/*     */     }
/*     */     
/* 340 */     for (int i = 0; i < keyBindsCombatbar.size(); i++) {
/* 341 */       int bar = i / 8;
/*     */       
/* 343 */       if (((KeyBinding)keyBindsCombatbar.get(i)).isActiveAndMatches(input) && ((KeyBinding)keyBindsCombatbar.get(i)).func_151468_f() && action != 2) {
/* 344 */         int k = i % 8 + (abilityDataProps.getCombatBarSet() + bar) * 8;
/*     */         
/* 346 */         IAbility abl = abilityDataProps.getEquippedAbility(k);
/* 347 */         boolean isOnCooldown = false;
/* 348 */         if (abl != null && abl.hasComponent(ModAbilityKeys.COOLDOWN)) {
/* 349 */           isOnCooldown = ((Boolean)abl.getComponent(ModAbilityKeys.COOLDOWN).map(comp -> Boolean.valueOf((comp.isOnCooldown() && comp.getCooldown() > 10.0F))).get()).booleanValue();
/*     */         }
/*     */         
/* 352 */         if (entityStatsProps.isInCombatMode() && abl != null) {
/* 353 */           if (!isOnCooldown) {
/* 354 */             if (changeAbilityMode.func_151470_d() && abl.hasComponent(ModAbilityKeys.ALT_MODE)) {
/* 355 */               WyNetwork.sendToServer(new CChangeAbilityAltModePacket(k));
/*     */             } else {
/* 357 */               WyNetwork.sendToServer(new CUseAbilityPacket(k));
/*     */             } 
/*     */           }
/*     */         } else {
/* 361 */           player.field_71071_by.field_70461_c = i % 8;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static <P> void checkScreenActivation(Minecraft mc, KeyBinding keybind, InputMappings.Input input, int action, int key, int type, Screen currentScreen, boolean isNeededScreen, Supplier<P> packet) {
/* 368 */     if ((keybind.isActiveAndMatches(input) && keybind.func_151468_f()) || ((currentScreen == null || isNeededScreen) && type == 1 && key == keybind.getKey().func_197937_c())) {
/* 369 */       if (isNeededScreen) {
/* 370 */         mc.func_147108_a(null);
/*     */       } else {
/*     */         
/* 373 */         WyNetwork.sendToServer(packet.get());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onClientTick(TickEvent.ClientTickEvent event) {
/* 380 */     if (event.phase == TickEvent.Phase.START) {
/*     */       return;
/*     */     }
/*     */     
/* 384 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 386 */     ClientPlayerEntity player = mc.field_71439_g;
/*     */     
/* 388 */     if (player == null || player.field_71158_b == null) {
/*     */       return;
/*     */     }
/*     */     
/* 392 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 394 */     if (entityStatsProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 398 */     if (entityStatsProps.getLeftImpulse() != player.field_70702_br || entityStatsProps.getForwardImpulse() != player.field_191988_bg || entityStatsProps.isJumping() != player.field_71158_b.field_78901_c) {
/* 399 */       entityStatsProps.setLeftImpulse(player.field_70702_br);
/* 400 */       entityStatsProps.setForwardImpulse(player.field_191988_bg);
/*     */       
/* 402 */       entityStatsProps.setJumping(player.field_71158_b.field_78901_c);
/*     */       
/* 404 */       WyNetwork.sendToServer(new CSyncPlayerInputPacket(mc.field_71439_g));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void keybindsAssignment(IEntityStats entityStatsProps) {
/* 409 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 411 */     entityStatsProps.setCombatMode(!entityStatsProps.isInCombatMode());
/*     */     
/* 413 */     if (entityStatsProps.isInCombatMode()) {
/* 414 */       for (int i = 0; i < mc.field_71474_y.field_151456_ac.length; i++) {
/* 415 */         KeyBinding kb = mc.field_71474_y.field_151456_ac[i];
/*     */         
/* 417 */         PREVIOUS_INVENTORY_KEYBINDS[i] = kb.getKey().func_197937_c();
/*     */ 
/*     */         
/* 420 */         kb.func_197979_b(InputMappings.field_197958_a);
/*     */       } 
/*     */ 
/*     */       
/* 424 */       KeyBinding.func_74508_b();
/*     */     } else {
/* 426 */       for (int i = 0; i < mc.field_71474_y.field_151456_ac.length; i++) {
/* 427 */         KeyBinding kb = mc.field_71474_y.field_151456_ac[i];
/*     */         
/* 429 */         if (PREVIOUS_INVENTORY_KEYBINDS[i] == -1) {
/* 430 */           kb.func_197979_b(InputMappings.func_197954_a(kb.func_197977_i().func_197937_c(), 0));
/*     */         } else {
/* 432 */           kb.func_197979_b(InputMappings.func_197954_a(PREVIOUS_INVENTORY_KEYBINDS[i], 0));
/*     */         } 
/*     */       } 
/*     */       
/* 436 */       KeyBinding.func_74508_b();
/*     */     } 
/*     */     
/* 439 */     WyNetwork.sendToServer(new CToggleCombatModePacket(entityStatsProps.isInCombatMode()));
/*     */   }
/*     */   
/*     */   public static boolean isHotbarKeyConflicting(KeyBinding keybind) {
/* 443 */     for (KeyBinding kb : keyBindsCombatbar) {
/* 444 */       if (kb.equals(keybind)) {
/*     */         continue;
/*     */       }
/*     */       
/* 448 */       if (kb.getKey().equals(keybind.getKey()) && kb.getKeyModifier().equals(keybind.getKeyModifier())) {
/* 449 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 453 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModKeybindings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */