/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.FOVUpdateEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.gui.ForgeIngameGui;
/*     */ import net.minecraftforge.client.settings.KeyModifier;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.EmptyHandsAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PauseTickComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SlotDecorationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombatModeEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*  76 */     public static int ABILITY_BARS = CommonConfig.INSTANCE.getAbilityBars();
/*  77 */     private static int colorTicks = 1000;
/*     */     public static boolean isInCombat;
/*  79 */     private static int iconSum = 0;
/*  80 */     private static int iconMode = 0;
/*     */     
/*  82 */     private static final List<Supplier<Effect>> FOV_EFFECTS = Arrays.asList((Supplier<Effect>[])new Supplier[] { (Supplier)ModEffects.PARALYSIS, (Supplier)ModEffects.GUARDING, (Supplier)ModEffects.MOVEMENT_BLOCKED, (Supplier)ModEffects.CANDLE_LOCK });
/*     */ 
/*     */     
/*     */     @SubscribeEvent(priority = EventPriority.HIGH)
/*     */     public static void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
/*  87 */       Minecraft mc = Minecraft.func_71410_x();
/*  88 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*  89 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*  90 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/*  92 */       int posX = mc.func_228018_at_().func_198107_o();
/*  93 */       int posY = mc.func_228018_at_().func_198087_p();
/*     */       
/*  95 */       if (abilityDataProps == null) {
/*     */         return;
/*     */       }
/*     */       
/*  99 */       if (ClientConfig.INSTANCE.hasHeartsUI()) {
/* 100 */         ForgeIngameGui.left_height++;
/*     */       }
/*     */       
/* 103 */       int maxBars = MathHelper.func_76125_a(ClientConfig.INSTANCE.getAbilityBarsOnScreen(), 1, ABILITY_BARS);
/* 104 */       int offset = (maxBars - 1) * 23;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 111 */       iconSum = 0;
/*     */       
/* 113 */       if (isInCombat) {
/* 114 */         iconSum++;
/*     */       }
/*     */       
/* 117 */       if (entityStatsProps.isRogue()) {
/* 118 */         iconSum += 2;
/*     */       }
/*     */       
/* 121 */       if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE && iconSum != 0 && !clientPlayerEntity.func_184812_l_() && !clientPlayerEntity.func_175149_v()) {
/* 122 */         if (iconSum == 3) {
/* 123 */           if (iconMode == 0) {
/* 124 */             iconMode = iconSum % 2;
/*     */           }
/*     */           
/* 127 */           if (((PlayerEntity)clientPlayerEntity).field_70173_aa % 40 == 0) {
/* 128 */             if (iconMode == 1) {
/* 129 */               iconMode = 2;
/*     */             }
/* 131 */             else if (iconMode == 2) {
/* 132 */               iconMode = 1;
/*     */             } 
/*     */           }
/*     */         } else {
/*     */           
/* 137 */           iconMode = iconSum % 3;
/*     */         } 
/*     */         
/* 140 */         if (iconMode > 0) {
/* 141 */           int offsetIcon = entityStatsProps.isInCombatMode() ? offset : 0;
/* 142 */           if (iconMode == 1) {
/* 143 */             mc.func_110434_K().func_110577_a(ModResources.PIRATE_ICON);
/*     */           }
/* 145 */           else if (iconMode == 2) {
/* 146 */             mc.func_110434_K().func_110577_a(ModResources.WARNING_ICON);
/*     */           } 
/* 148 */           event.getMatrixStack().func_227860_a_();
/* 149 */           RenderSystem.enableBlend();
/* 150 */           RenderSystem.defaultBlendFunc();
/* 151 */           event.getMatrixStack().func_227861_a_((posX / 2 - 128), (posY - 170 - offsetIcon), 2.0D);
/* 152 */           event.getMatrixStack().func_227861_a_(128.0D, 128.0D, 0.0D);
/* 153 */           event.getMatrixStack().func_227862_a_(0.15F, 0.15F, 1.0F);
/* 154 */           event.getMatrixStack().func_227861_a_(-128.0D, -128.0D, 0.0D);
/* 155 */           RenderSystem.color4f(1.0F, 0.0F, 0.0F, 1.0F);
/* 156 */           GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 0, 0, 0, 0, 256, 256, 1.0F);
/* 157 */           RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 158 */           RenderSystem.disableBlend();
/* 159 */           event.getMatrixStack().func_227865_b_();
/*     */         } 
/*     */       } 
/*     */       
/* 163 */       if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH && ClientConfig.INSTANCE.hasHeartsUI()) {
/* 164 */         event.setCanceled(true);
/* 165 */         double maxHealth = clientPlayerEntity.func_110148_a(Attributes.field_233818_a_).func_111126_e();
/* 166 */         double health = clientPlayerEntity.func_110143_aJ();
/* 167 */         int absorptionBonus = MathHelper.func_76123_f(clientPlayerEntity.func_110139_bj());
/* 168 */         int rgb = Color.RED.getRGB();
/* 169 */         int posY2 = posY + 9 - ForgeIngameGui.left_height;
/*     */         
/* 171 */         if (absorptionBonus > 0) {
/* 172 */           rgb = Color.YELLOW.getRGB();
/*     */         }
/*     */         
/* 175 */         WyHelper.drawStringWithBorder(mc.field_71466_p, event.getMatrixStack(), (int)(health + absorptionBonus) + "", posX / 2 - 27, posY2, rgb);
/*     */         
/* 177 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/* 179 */         mc.func_110434_K().func_110577_a(Widget.field_230665_h_);
/*     */         
/* 181 */         for (int i = 10; i >= 0; i--) {
/* 182 */           int k = posX / 2 - 91 + i % 10 * 6;
/*     */           
/* 184 */           GuiUtils.drawTexturedModalRect(event.getMatrixStack(), k, posY2, 16, 0, 9, 9, 0.0F);
/*     */         } 
/*     */         
/* 187 */         double hpPercentage = (100.0D - (maxHealth - health) / maxHealth * 100.0D) / 10.0D;
/*     */         
/* 189 */         int top = 9 * (mc.field_71441_e.func_72912_H().func_76093_s() ? 5 : 0);
/* 190 */         int anim = ((PlayerEntity)clientPlayerEntity).field_70173_aa % 25;
/*     */         
/* 192 */         for (int j = 0; j < 10; j++) {
/* 193 */           int k = posX / 2 - 91 + j % 10 * 6;
/* 194 */           int y = posY2;
/* 195 */           int lastIdx = (int)Math.floor(hpPercentage);
/*     */           
/* 197 */           int width = 9;
/*     */           
/* 199 */           if (j == lastIdx) {
/* 200 */             width = (int)(hpPercentage * 10.0D % 10.0D);
/*     */           }
/* 202 */           else if (j > lastIdx) {
/* 203 */             width = 0;
/*     */           } 
/*     */           
/* 206 */           if (lastIdx < 4 && j == anim) {
/* 207 */             y -= 2;
/*     */           }
/*     */           
/* 210 */           int u = 36;
/* 211 */           if (absorptionBonus > 0) {
/* 212 */             u = 144;
/*     */           }
/*     */           
/* 215 */           GuiUtils.drawTexturedModalRect(event.getMatrixStack(), k, y, 16 + u, top, width, 9, 0.0F);
/*     */         } 
/*     */       } 
/*     */       
/* 219 */       if (!entityStatsProps.isInCombatMode()) {
/*     */         return;
/*     */       }
/*     */       
/* 223 */       if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
/*     */         
/* 225 */         event.getMatrixStack().func_227860_a_();
/* 226 */         event.getMatrixStack().func_227861_a_(0.0D, -offset, 0.0D);
/*     */       } 
/*     */       
/* 229 */       if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
/*     */         
/* 231 */         Set<IAbility> gauges = abilityDataProps.getEquippedAndPassiveAbilities(abl -> abl.getComponent(ModAbilityKeys.GAUGE).isPresent());
/* 232 */         int ablNo = 1;
/* 233 */         int posX2 = posX / 2 - 110;
/* 234 */         for (IAbility abl : gauges) {
/* 235 */           boolean isPaused = ((Boolean)abl.getComponent(ModAbilityKeys.PAUSE_TICK).map(PauseTickComponent::isPaused).orElse(Boolean.valueOf(false))).booleanValue();
/* 236 */           if (isPaused) {
/*     */             continue;
/*     */           }
/* 239 */           Optional<GaugeComponent> comp = abl.getComponent(ModAbilityKeys.GAUGE);
/* 240 */           if (comp.isPresent()) {
/* 241 */             ((GaugeComponent)comp.get()).getRenderer().renderGauge((PlayerEntity)clientPlayerEntity, event.getMatrixStack(), posX2 - 35 * ablNo, posY, abl);
/* 242 */             ablNo++;
/*     */           } 
/*     */         } 
/*     */         
/* 246 */         boolean[] visuals = ClientConfig.INSTANCE.getCooldownVisuals();
/*     */         
/* 248 */         boolean hasNumberVisual = visuals[0];
/* 249 */         boolean hasColorVisual = visuals[1];
/* 250 */         event.setCanceled(true);
/* 251 */         event.getMatrixStack().func_227860_a_();
/*     */         
/* 253 */         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 254 */         RenderSystem.disableLighting();
/* 255 */         RenderSystem.enableBlend();
/*     */         
/* 257 */         ForgeIngameGui.right_height += offset;
/* 258 */         ForgeIngameGui.left_height += offset;
/* 259 */         for (int bars = 0; bars < maxBars; bars++) {
/*     */           
/* 261 */           if (ABILITY_BARS - abilityDataProps.getCombatBarSet() + bars > 0) {
/*     */ 
/*     */             
/* 264 */             String barId = (1 + abilityDataProps.getCombatBarSet() + bars) + "";
/* 265 */             WyHelper.drawStringWithBorder(mc.field_71466_p, event.getMatrixStack(), barId, posX / 2 - mc.field_71466_p.func_78256_a(barId) + 102, posY - 14 - bars * 23, WyHelper.hexToRGB("#FFFFFF").getRGB());
/* 266 */             mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/* 267 */             for (int i = 0; i < 8; i++) {
/*     */               
/* 269 */               int j = i + (abilityDataProps.getCombatBarSet() + bars) * 8;
/* 270 */               IAbility abl = null;
/*     */               
/*     */               try {
/* 273 */                 abl = abilityDataProps.getEquippedAbility(j);
/*     */               }
/* 275 */               catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */               
/* 279 */               RenderSystem.enableBlend();
/* 280 */               if (abl == null) {
/* 281 */                 GuiUtils.drawTexturedModalRect(event.getMatrixStack(), (posX - 200 + i * 50) / 2, posY - 23 - bars * 23, 0, 0, 23, 23, 0.0F);
/*     */               }
/*     */               else {
/*     */                 
/* 285 */                 boolean hasDisplayText = false;
/* 286 */                 String displayText = "";
/* 287 */                 double maxNumber = 1.0D;
/* 288 */                 double number = 0.0D;
/* 289 */                 Optional<SlotDecorationComponent> slotDecoComponent = abl.getComponent(ModAbilityKeys.SLOT_DECORATION);
/* 290 */                 if (abl instanceof Ability && ((Ability)abl).isNew && slotDecoComponent.isPresent()) {
/* 291 */                   SlotDecorationComponent slotDeco = slotDecoComponent.get();
/* 292 */                   number = slotDeco.getCurrentValue();
/* 293 */                   maxNumber = slotDeco.getMaxValue();
/* 294 */                   hasDisplayText = slotDeco.hasDisplayText();
/* 295 */                   displayText = slotDeco.getDisplayText();
/*     */                   
/* 297 */                   double slotHeight = MathHelper.func_151237_a(23.0D - number / maxNumber * 23.0D, 0.0D, Double.MAX_VALUE);
/*     */                   
/* 299 */                   int x = (posX - 200 + i * 50) / 2;
/* 300 */                   int y = posY - 23 - bars * 23;
/*     */                   
/* 302 */                   slotDeco.triggerPreRenderEvents((LivingEntity)clientPlayerEntity, mc, event.getMatrixStack(), x, y, event.getPartialTicks());
/*     */ 
/*     */                   
/* 305 */                   RendererHelper.drawTexturedModalRect(event.getMatrixStack(), x, y, 0.0F, 0.0F, 23.0F, 23.0F, 0.0F, slotDeco.getSlotRed(), slotDeco.getSlotGreen(), slotDeco.getSlotBlue(), 1.0F);
/*     */ 
/*     */                   
/* 308 */                   RendererHelper.drawTexturedModalRect(event.getMatrixStack(), x, y, 24.0F, 0.0F, 23.0F, (int)slotHeight, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */                   
/* 311 */                   RendererHelper.drawIcon(abl.getIcon((LivingEntity)clientPlayerEntity), event.getMatrixStack(), (x + 4), (y + 4), 1.0F, 16.0F, 16.0F, slotDeco.getIconRed(), slotDeco.getIconGreen(), slotDeco.getIconBlue(), slotDeco.getIconAlpha());
/*     */                   
/* 313 */                   slotDeco.triggerPostRenderEvents((LivingEntity)clientPlayerEntity, mc, event.getMatrixStack(), x, y, event.getPartialTicks());
/*     */                 } 
/*     */                 
/* 316 */                 event.getMatrixStack().func_227861_a_(0.0D, 0.0D, 2.0D);
/* 317 */                 if (hasNumberVisual && number > 0.0D) {
/*     */                   String numText;
/* 319 */                   int xTextOffset = 0;
/* 320 */                   if (hasDisplayText) {
/* 321 */                     numText = displayText;
/* 322 */                     xTextOffset = -2;
/*     */                   
/*     */                   }
/* 325 */                   else if (ClientConfig.INSTANCE.isDisplayInSeconds()) {
/* 326 */                     numText = String.format("%.1f", new Object[] { Double.valueOf(number / 20.0D) }) + " ";
/*     */                   } else {
/*     */                     
/* 329 */                     numText = (int)number + " ";
/*     */                   } 
/*     */                   
/* 332 */                   WyHelper.drawStringWithBorder(mc.field_71466_p, event.getMatrixStack(), numText, (posX - 172 + xTextOffset + i * 50) / 2 - mc.field_71466_p.func_78256_a(numText) / 2, posY - 14 - bars * 23, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */                 } 
/*     */                 
/* 335 */                 if (ClientConfig.INSTANCE.showSlotKeybinds()) {
/* 336 */                   int keybindSlot = i + bars * 8;
/* 337 */                   KeyBinding kb = ModKeybindings.keyBindsCombatbar.get(keybindSlot);
/* 338 */                   StringBuilder sb = new StringBuilder();
/* 339 */                   colorTicks = (colorTicks + 1) % 2000;
/* 340 */                   if (kb.func_197986_j()) {
/* 341 */                     sb.append((colorTicks >= 1000) ? "§4" : "§c");
/* 342 */                     sb.append("⚠");
/*     */                   } else {
/*     */                     
/* 345 */                     if (ModKeybindings.isHotbarKeyConflicting(kb)) {
/* 346 */                       sb.append((colorTicks >= 1000) ? "§4" : "§c");
/*     */                     }
/* 348 */                     switch (kb.getKeyModifier()) {
/*     */                       case ALT:
/* 350 */                         sb.append("a");
/*     */                         break;
/*     */                       case CONTROL:
/* 353 */                         sb.append("c");
/*     */                         break;
/*     */                       case SHIFT:
/* 356 */                         sb.append("s");
/*     */                         break;
/*     */                     } 
/*     */ 
/*     */                     
/* 361 */                     sb.append(kb.getKey().func_237520_d_().getString());
/*     */                   } 
/* 363 */                   event.getMatrixStack().func_227860_a_();
/* 364 */                   event.getMatrixStack().func_227861_a_(((posX - 195 + i * 50) / 2), (posY - 8 - bars * 23), 0.0D);
/* 365 */                   event.getMatrixStack().func_227862_a_(0.66F, 0.66F, 0.66F);
/* 366 */                   WyHelper.drawStringWithBorder(mc.field_71466_p, event.getMatrixStack(), sb.toString(), 0, 0, -1);
/* 367 */                   event.getMatrixStack().func_227865_b_();
/*     */                 } 
/*     */                 
/* 370 */                 mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/* 371 */                 RenderSystem.disableBlend();
/*     */               } 
/*     */             } 
/*     */           } 
/* 375 */         }  event.getMatrixStack().func_227865_b_();
/* 376 */         RenderSystem.disableBlend();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent(priority = EventPriority.HIGH)
/*     */     public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
/* 383 */       Minecraft mc = Minecraft.func_71410_x();
/* 384 */       ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 385 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/* 387 */       if (!entityStatsProps.isInCombatMode()) {
/*     */         return;
/*     */       }
/*     */       
/* 391 */       if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE)
/*     */       {
/* 393 */         event.getMatrixStack().func_227865_b_();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void updateFOV(FOVUpdateEvent event) {
/* 400 */       if (FOV_EFFECTS.stream().anyMatch(f -> event.getEntity().func_70644_a(f.get())))
/*     */       {
/* 402 */         event.setNewfov(1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common {
/*     */     @SubscribeEvent
/*     */     public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
/* 411 */       PlayerEntity player = event.getPlayer();
/*     */       
/* 413 */       if (!player.field_70170_p.field_72995_K && WyHelper.isInChallengeDimension(player.field_70170_p)) {
/* 414 */         ItemStack stack = event.getItemStack();
/* 415 */         ChallengesWorldData worldData = ChallengesWorldData.get();
/* 416 */         InProgressChallenge challenge = worldData.getInProgressChallengeFor((LivingEntity)player);
/* 417 */         if (challenge != null) {
/* 418 */           boolean isFood = stack.func_77973_b().func_219971_r();
/* 419 */           boolean isPotion = stack.func_77973_b() instanceof net.minecraft.item.PotionItem;
/* 420 */           boolean hasRestrictions = (challenge.hasRestrictions() && challenge.hasActiveRestrictions() && (isFood || isPotion));
/* 421 */           boolean isItemBanned = stack.func_77973_b().func_206844_a((ITag)ModTags.Items.BANNED_ITEMS_CHALLANGES);
/*     */           
/* 423 */           if (hasRestrictions || isItemBanned) {
/* 424 */             if (!player.field_70170_p.field_72995_K) {
/* 425 */               player.func_145747_a((ITextComponent)ModI18n.CHALLENGE_MESSAGE_CANNOT_USE_ITEM, Util.field_240973_b_);
/*     */             }
/* 427 */             event.setCanceled(true);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onCarryUpdate(LivingEvent.LivingUpdateEvent event) {
/* 435 */       LivingEntity living = event.getEntityLiving();
/*     */       
/* 437 */       if (living.field_70170_p.field_72995_K) {
/*     */         return;
/*     */       }
/*     */       
/* 441 */       IEntityStats props = EntityStatsCapability.get(living);
/*     */       
/* 443 */       if (living instanceof PlayerEntity && props.isLeashed()) {
/* 444 */         boolean isHandcuffed = false;
/* 445 */         for (EffectInstance effect : living.func_70651_bq()) {
/* 446 */           if (effect.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect) {
/* 447 */             isHandcuffed = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 452 */         if (!isHandcuffed) {
/* 453 */           props.dropLeash();
/*     */           
/*     */           return;
/*     */         } 
/* 457 */         LivingEntity leashHolder = props.getLeashHolder();
/* 458 */         if (leashHolder != null) {
/* 459 */           float distance = living.func_70032_d((Entity)leashHolder);
/* 460 */           if (distance > 6.0F) {
/* 461 */             double x = (leashHolder.func_226277_ct_() - living.func_226277_ct_()) / distance;
/* 462 */             double y = (leashHolder.func_226278_cu_() - living.func_226278_cu_()) / distance;
/* 463 */             double z = (leashHolder.func_226281_cx_() - living.func_226281_cx_()) / distance;
/* 464 */             if (!living.func_233570_aj_()) {
/* 465 */               x /= 1.75D;
/* 466 */               y /= 1.75D;
/* 467 */               z /= 1.75D;
/*     */             } 
/* 469 */             AbilityHelper.setDeltaMovement((Entity)living, x, y, z);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 474 */       if (props.isCarrying()) {
/* 475 */         LivingEntity target = props.getCarry();
/*     */         
/* 477 */         if (target == null || !target.func_70089_S() || !AbilityHelper.canCarry(living, target)) {
/* 478 */           props.stopCarrying();
/*     */           
/*     */           return;
/*     */         } 
/* 482 */         living.func_195064_c(new EffectInstance((Effect)ModEffects.CARRYING.get(), 40, 0));
/* 483 */         AbilityHelper.setDeltaMovement((Entity)target, Vector3d.field_186680_a);
/* 484 */         target.func_70634_a(living.func_226277_ct_(), living.func_226280_cw_() + 0.75D, living.func_226281_cx_());
/* 485 */         target.field_70143_R = 0.0F;
/*     */       } 
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityDrop(PlayerInteractEvent event) {
/* 491 */       if (event.getHand().equals(Hand.MAIN_HAND) && !(event.getPlayer()).field_70170_p.field_72995_K) {
/* 492 */         PlayerEntity player = event.getPlayer();
/* 493 */         IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */         
/* 495 */         if (props.isCarrying()) {
/* 496 */           LivingEntity target = props.getCarry();
/*     */           
/* 498 */           props.stopCarrying();
/*     */           
/* 500 */           if (target != null) {
/* 501 */             if (event instanceof PlayerInteractEvent.EntityInteractSpecific) {
/* 502 */               Entity interactionEntity = ((PlayerInteractEvent.EntityInteractSpecific)event).getTarget();
/* 503 */               if (interactionEntity != null && interactionEntity instanceof net.minecraft.entity.item.BoatEntity) {
/* 504 */                 target.func_184220_m(interactionEntity);
/* 505 */                 event.setCanceled(true);
/*     */               } 
/*     */             } else {
/*     */               
/* 509 */               target.func_70107_b(player.func_226277_ct_(), player.func_226280_cw_(), player.func_226281_cx_());
/* 510 */               AbilityHelper.setDeltaMovement((Entity)target, player.func_70040_Z().func_216372_d(0.5D, 1.0D, 0.5D));
/*     */               
/* 512 */               event.setCanceled(true);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static boolean tryLeashTarget(PlayerEntity player, PlayerEntity target) {
/* 520 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 521 */       IEntityStats targetProps = EntityStatsCapability.get((LivingEntity)target);
/* 522 */       ItemStack stack = player.func_184614_ca();
/*     */       
/* 524 */       if (!stack.func_190926_b() && stack.func_77973_b() == Items.field_151058_ca && targetProps.canBeLeashed((LivingEntity)player)) {
/* 525 */         boolean isHandcuffed = false;
/* 526 */         for (EffectInstance effect : target.func_70651_bq()) {
/* 527 */           if (effect.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect) {
/* 528 */             isHandcuffed = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 533 */         if (isHandcuffed) {
/* 534 */           targetProps.setLeashedTo((LivingEntity)player);
/* 535 */           stack.func_190918_g(1);
/*     */           
/* 537 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 541 */       return false;
/*     */     }
/*     */     
/*     */     public static boolean tryPickupTarget(PlayerEntity player, LivingEntity target) {
/* 545 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 546 */       IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */       
/* 548 */       if (target instanceof PlayerEntity && targetProps.isLeashed() && targetProps.getLeashHolder().equals(player)) {
/* 549 */         targetProps.dropLeash();
/*     */         
/* 551 */         return true;
/*     */       } 
/*     */       
/* 554 */       if (!props.isCarrying() && 
/* 555 */         AbilityHelper.canCarry((LivingEntity)player, target)) {
/* 556 */         props.startCarrying(target);
/*     */         
/* 558 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 562 */       return false;
/*     */     }
/*     */     
/*     */     @SubscribeEvent(priority = EventPriority.LOW)
/*     */     public static void onItemPickup(EntityItemPickupEvent event) {
/* 567 */       PlayerEntity player = event.getPlayer();
/* 568 */       ItemStack heldItem = player.func_184614_ca();
/* 569 */       if (heldItem.func_190926_b()) {
/* 570 */         IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */         
/* 572 */         boolean isEmptyHandsActive = false;
/* 573 */         if (props != null) {
/* 574 */           EmptyHandsAbility abl = (EmptyHandsAbility)props.getPassiveAbility(EmptyHandsAbility.INSTANCE);
/* 575 */           if (abl != null) {
/* 576 */             isEmptyHandsActive = !((PauseTickComponent)abl.getComponent(ModAbilityKeys.PAUSE_TICK).get()).isPaused();
/*     */           }
/*     */         } 
/*     */         
/* 580 */         if (isEmptyHandsActive) {
/* 581 */           ItemEntity itemEntity = event.getItem();
/* 582 */           ItemStack itemStack = itemEntity.func_92059_d();
/* 583 */           Item item = itemStack.func_77973_b();
/* 584 */           int count = itemStack.func_190916_E();
/*     */           
/* 586 */           ItemStack copy = itemStack.func_77946_l();
/*     */           
/* 588 */           int emptySlot = ItemsHelper.getFreeOrSameSlot(player, itemStack);
/* 589 */           if (emptySlot < 0) {
/* 590 */             event.setCanceled(true);
/*     */             
/*     */             return;
/*     */           } 
/* 594 */           ItemStack slotStack = player.field_71071_by.func_70301_a(emptySlot);
/* 595 */           if (!slotStack.func_190926_b()) {
/* 596 */             slotStack.func_190920_e(slotStack.func_190916_E() + copy.func_190916_E());
/*     */           } else {
/*     */             
/* 599 */             player.field_71071_by.func_191971_c(emptySlot, itemStack);
/*     */           } 
/*     */           
/* 602 */           player.func_71001_a((Entity)itemEntity, count);
/*     */           
/* 604 */           if (itemStack.func_190926_b()) {
/* 605 */             itemEntity.func_70106_y();
/* 606 */             itemStack.func_190920_e(count);
/*     */           } 
/*     */           
/* 609 */           player.func_71064_a(Stats.field_199089_f.func_199076_b(item), count);
/* 610 */           player.func_233630_a_(itemEntity);
/* 611 */           event.setCanceled(true);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\CombatModeEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */