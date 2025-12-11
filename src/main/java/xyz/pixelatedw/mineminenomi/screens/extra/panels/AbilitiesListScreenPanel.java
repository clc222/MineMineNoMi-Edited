/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PauseTickComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CTogglePassiveAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class AbilitiesListScreenPanel<A extends IAbility> extends ScrollPanel {
/*  42 */   private static final float[] SPACINGS = new float[] { 1.25F, 1.05F };
/*     */   
/*     */   private static final float ENTRY_HEIGHT = 20.0F;
/*     */   
/*     */   private final SelectHotbarAbilitiesScreen<A> parent;
/*     */   private final PlayerEntity player;
/*     */   private final IAbilityData props;
/*     */   private final List<Entry<IAbility>> allAbilities;
/*     */   private final TreeSet<AbilityCore<IAbility>> unlockedAbilities;
/*     */   private final TreeSet<IAbility> passiveAbilities;
/*     */   private final AbilityCategory category;
/*  53 */   private final Map<AbilityCore<IAbility>, IAbility> unlockedToEquippedMap = new HashMap<>();
/*     */   
/*  55 */   private final Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*     */   private SelectHotbarAbilitiesScreen.Compactness compactness;
/*     */   private Entry<IAbility> hoveredEntry;
/*     */   
/*     */   public AbilitiesListScreenPanel(SelectHotbarAbilitiesScreen<A> parent, IAbilityData abilityProps, AbilityCategory category) {
/*  61 */     super(parent.getMinecraft(), 210, 130, parent.field_230709_l_ / 2 - 92, parent.field_230708_k_ / 2 - 102);
/*     */     
/*  63 */     Minecraft parentMinecraft = parent.getMinecraft();
/*     */     
/*  65 */     this.parent = parent;
/*  66 */     this.player = (PlayerEntity)parentMinecraft.field_71439_g;
/*  67 */     this.props = abilityProps;
/*  68 */     this.category = category;
/*  69 */     this.compactness = parent.getCompactness();
/*     */     
/*  71 */     this.allAbilities = new ArrayList<>();
/*  72 */     this
/*     */ 
/*     */       
/*  75 */       .unlockedAbilities = (TreeSet<AbilityCore<IAbility>>)this.props.getUnlockedAbilitiesStream().map(AbilityCoreUnlockWrapper::getAbilityCore).filter(core -> (!core.isHidden() && core.getType().equals(AbilityType.ACTION) && category.isCorePartofCategory().test(core))).collect(Collectors.toCollection(TreeSet::new));
/*  76 */     this
/*     */       
/*  78 */       .passiveAbilities = (TreeSet<IAbility>)this.props.getPassiveAbilities(abl -> (!abl.getCore().isHidden() && abl.getCore().getType() == AbilityType.PASSIVE && category.isAbilityPartofCategory().test(abl))).stream().collect(Collectors.toCollection(TreeSet::new));
/*     */     
/*  80 */     for (AbilityCore<IAbility> core : this.unlockedAbilities) {
/*  81 */       this.allAbilities.add(new Entry<>(core));
/*     */     }
/*     */     
/*  84 */     this.allAbilities.add(new Entry<>());
/*  85 */     for (IAbility abl : this.passiveAbilities) {
/*  86 */       this.allAbilities.add(new Entry<>(abl));
/*     */     }
/*     */     
/*  89 */     this.unlockedToEquippedMap.clear();
/*  90 */     for (AbilityCore<IAbility> core : this.unlockedAbilities) {
/*  91 */       IAbility abl = this.props.getEquippedAbility(core);
/*  92 */       if (abl == null) {
/*  93 */         abl = core.createAbility();
/*     */       }
/*  95 */       this.unlockedToEquippedMap.put(core, abl);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/* 106 */     int size = ((List)this.allAbilities.stream().collect(Collectors.toList())).size();
/* 107 */     return (int)(size * getEntrySpacing() + 2.0F);
/*     */   }
/*     */   
/*     */   public float getEntrySpacing() {
/* 111 */     return 20.0F * this.compactness.getSpacing();
/*     */   }
/*     */   
/*     */   public void setCompactness(SelectHotbarAbilitiesScreen.Compactness compactness) {
/* 115 */     this.compactness = compactness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGradientRect(MatrixStack stack, int left, int top, int right, int bottom, int color1, int color2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/* 127 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/* 132 */     float y = relativeY;
/* 133 */     float x = (this.parent.field_230708_k_ / 2 - 100 + 40);
/*     */ 
/*     */     
/* 136 */     for (AbilityCore<IAbility> core : this.unlockedAbilities) {
/* 137 */       if (core == null) {
/*     */         continue;
/*     */       }
/* 140 */       IAbility abl = this.unlockedToEquippedMap.get(core);
/*     */ 
/*     */       
/* 143 */       int color = 16777215;
/* 144 */       boolean isHovering = (mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + getEntrySpacing());
/*     */ 
/*     */       
/* 147 */       if (this.props.hasEquippedAbility(core)) {
/* 148 */         color = 16711680;
/* 149 */       } else if (isHovering) {
/* 150 */         color = 10014975;
/*     */       } 
/*     */       
/* 153 */       RenderSystem.enableBlend();
/*     */       
/* 155 */       this.mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/* 156 */       matrixStack.func_227860_a_();
/* 157 */       matrixStack.func_227861_a_((x - 34.0F + this.compactness.getSlotOffsetX()), (y - this.compactness.getSlotOffsetY()), 0.0D);
/* 158 */       matrixStack.func_227862_a_(this.compactness.getSlotScale(), this.compactness.getSlotScale(), 1.0F);
/* 159 */       func_238474_b_(matrixStack, 0, 0, 0, 0, 23, 23);
/* 160 */       matrixStack.func_227865_b_();
/*     */       
/* 162 */       matrixStack.func_227860_a_();
/* 163 */       matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/*     */       
/* 165 */       WyHelper.drawStringWithBorder(this.mc.field_71466_p, matrixStack, abl.getDisplayName(), (int)x, (int)y + 4, color);
/* 166 */       RendererHelper.drawIcon(abl.getIcon((LivingEntity)this.player), matrixStack, (MathHelper.func_76141_d(x) - 30 + this.compactness.getIconOffsetX()), (MathHelper.func_76141_d(y) + this.compactness.getIconOffsetY()), 1.0F, this.compactness.getIconSize(), this.compactness.getIconSize());
/* 167 */       matrixStack.func_227865_b_();
/*     */ 
/*     */       
/* 170 */       float y2 = y;
/* 171 */       abl.getComponent(ModAbilityKeys.DISABLE).ifPresent(comp -> {
/*     */             if (comp.isDisabled()) {
/*     */               RendererHelper.drawIcon(ModResources.DISABLED_ABILITY, matrixStack, (MathHelper.func_76141_d(x) - 30), MathHelper.func_76141_d(y2), 3.0F, 16.0F, 16.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */             }
/*     */           });
/*     */       
/* 177 */       y += getEntrySpacing();
/*     */     } 
/*     */ 
/*     */     
/* 181 */     if (this.passiveAbilities.size() > 0) {
/* 182 */       matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/* 183 */       WyHelper.drawStringWithBorder(this.mc.field_71466_p, matrixStack, (ITextComponent)new StringTextComponent("Passives"), (int)x - 20, (int)y + 4, 9145227);
/* 184 */       matrixStack.func_227861_a_(0.0D, 0.0D, -250.0D);
/* 185 */       y += getEntrySpacing();
/*     */       
/* 187 */       for (IAbility abl : this.passiveAbilities) {
/* 188 */         if (abl == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 192 */         Color textColor = WyHelper.hexToRGB("#aaff77");
/* 193 */         Color iconColor = WyHelper.hexToRGB("#FFFFFF");
/*     */         
/* 195 */         Optional<PauseTickComponent> pauseComp = abl.getComponent(ModAbilityKeys.PAUSE_TICK);
/* 196 */         if (pauseComp.isPresent() && ((PauseTickComponent)pauseComp.get()).isPaused()) {
/* 197 */           textColor = iconColor = WyHelper.hexToRGB("#FF0000");
/*     */         }
/*     */         
/* 200 */         RenderSystem.enableBlend();
/*     */         
/* 202 */         this.mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/* 203 */         matrixStack.func_227860_a_();
/* 204 */         matrixStack.func_227861_a_((x - 34.0F + this.compactness.getSlotOffsetX()), (y - this.compactness.getSlotOffsetY()), 0.0D);
/* 205 */         matrixStack.func_227862_a_(this.compactness.getSlotScale(), this.compactness.getSlotScale(), 1.0F);
/* 206 */         func_238474_b_(matrixStack, 0, 0, 0, 0, 23, 23);
/* 207 */         matrixStack.func_227865_b_();
/*     */         
/* 209 */         matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/* 210 */         WyHelper.drawStringWithBorder(this.mc.field_71466_p, matrixStack, abl.getDisplayName(), (int)x, (int)y + 4, textColor.getRGB());
/* 211 */         RendererHelper.drawIcon(abl.getIcon((LivingEntity)this.player), matrixStack, (MathHelper.func_76141_d(x) - 30 + this.compactness.getIconOffsetX()), (MathHelper.func_76141_d(y) + this.compactness.getIconOffsetY()), 1.0F, this.compactness.getIconSize(), this.compactness.getIconSize(), iconColor.getRed() / 255.0F, iconColor.getGreen() / 255.0F, iconColor.getBlue() / 255.0F, 1.0F);
/* 212 */         matrixStack.func_227861_a_(0.0D, 0.0D, -250.0D);
/*     */ 
/*     */         
/* 215 */         float y2 = y;
/* 216 */         abl.getComponent(ModAbilityKeys.DISABLE).ifPresent(comp -> {
/*     */               if (comp.isDisabled()) {
/*     */                 RendererHelper.drawIcon(ModResources.DISABLED_ABILITY, matrixStack, (MathHelper.func_76141_d(x) - 30), MathHelper.func_76141_d(y2), 3.0F, 16.0F, 16.0F, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */               }
/*     */             });
/*     */ 
/*     */         
/* 223 */         y += getEntrySpacing();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Entry<IAbility> findAbilityEntry(int mouseX, int mouseY) {
/* 230 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/*     */     
/* 232 */     if (offset <= 0.0D) {
/* 233 */       return null;
/*     */     }
/*     */     
/* 236 */     int lineIdx = (int)(offset / getEntrySpacing());
/* 237 */     if (lineIdx >= this.allAbilities.size()) {
/* 238 */       return null;
/*     */     }
/*     */     
/* 241 */     Entry<IAbility> entry = this.allAbilities.get(lineIdx);
/* 242 */     if (entry != null) {
/* 243 */       return entry;
/*     */     }
/*     */     
/* 246 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/* 251 */     Entry<IAbility> entry = findAbilityEntry((int)mouseX, (int)mouseY);
/* 252 */     boolean flag = true;
/*     */     
/* 254 */     if (entry != null && func_231047_b_(mouseX, mouseY) && button == 0) {
/* 255 */       if (entry.isActive()) {
/* 256 */         if (this.parent.getSelectionMode() == SelectHotbarAbilitiesScreen.Selection.DRAG_AND_DROP && this.parent.slotSelected <= 0) {
/* 257 */           this.parent.setDraggedAbility(entry.abilityCore);
/*     */         }
/* 259 */         else if (this.parent.getSelectionMode() == SelectHotbarAbilitiesScreen.Selection.KEYBIND && 
/* 260 */           this.parent.slotSelected >= 0 && flag && entry.getActive() != null) {
/* 261 */           WyNetwork.sendToServer(new CEquipAbilityPacket(this.parent.slotSelected, entry.getActive()));
/* 262 */           this.parent.isDirty = true;
/*     */         }
/*     */       
/*     */       }
/* 266 */       else if (entry.isPassive() && 
/* 267 */         entry.getPassive() != null) {
/* 268 */         WyNetwork.sendToServer(new CTogglePassiveAbilityPacket(entry.getPassive().getCore()));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (WyDebug.isDebug() && entry != null && button == 1) {
/* 275 */       IAbility abl = null;
/* 276 */       if (entry.isActive()) {
/* 277 */         abl = this.unlockedToEquippedMap.get(entry.getActive());
/*     */       }
/* 279 */       else if (entry.isPassive()) {
/* 280 */         for (IAbility passive : this.passiveAbilities) {
/* 281 */           if (passive.equals(entry.getPassive())) {
/* 282 */             abl = entry.getPassive();
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 288 */       if (abl != null) {
/* 289 */         StringBuilder sb = new StringBuilder();
/* 290 */         sb.append("§5" + abl.getDisplayName().getString() + "§r\n");
/* 291 */         for (AbilityComponent<?> comp : (Iterable<AbilityComponent<?>>)abl.getComponents().values()) {
/* 292 */           if (!comp.getBonusManagers().hasNext()) {
/*     */             continue;
/*     */           }
/* 295 */           comp.getBonusManagers().forEachRemaining(m -> {
/*     */                 if (!m.getBonuses().isEmpty()) {
/*     */                   sb.append("§6" + comp.getKey().getId() + "§r\n");
/*     */                   return;
/*     */                 } 
/*     */               });
/* 301 */           comp.getBonusManagers().forEachRemaining(m -> m.getBonuses().forEach(()));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 308 */         this.mc.field_71439_g.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/*     */       } 
/*     */     } 
/*     */     
/* 312 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231045_a_(double mouseX, double mouseY, int button, double dragX, double dragY) {
/* 317 */     return super.func_231045_a_(mouseX, mouseY, button, dragX, dragY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231047_b_(double mouseX, double mouseY) {
/* 322 */     return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 327 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     
/* 329 */     this.hoveredEntry = null;
/*     */     
/* 331 */     if (func_231047_b_(mouseX, mouseY)) {
/* 332 */       this.hoveredEntry = findAbilityEntry(mouseX, mouseY);
/* 333 */       if (this.hoveredEntry == null) {
/*     */         return;
/*     */       }
/*     */       
/* 337 */       IAbility abl = null;
/* 338 */       if (this.hoveredEntry.isActive()) {
/* 339 */         abl = this.unlockedToEquippedMap.get(this.hoveredEntry.getActive());
/*     */       }
/* 341 */       else if (this.hoveredEntry.isPassive()) {
/* 342 */         abl = this.hoveredEntry.getPassive();
/*     */       } 
/*     */       
/* 345 */       if (abl != null && !this.parent.hasDraggedAbility() && SelectHotbarAbilitiesScreen.canShowTooltips()) {
/* 346 */         SelectHotbarAbilitiesScreen.renderAbilityTooltip(matrixStack, mouseX, mouseY, abl);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AbilityCore<?> getHoveredEntry() {
/* 353 */     if (this.hoveredEntry != null) {
/* 354 */       return this.hoveredEntry.getActive();
/*     */     }
/* 356 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   class Entry<T extends IAbility>
/*     */   {
/*     */     @Nullable
/*     */     private T ability;
/*     */     
/*     */     @Nullable
/*     */     private AbilityCore<T> abilityCore;
/*     */ 
/*     */     
/*     */     public Entry() {}
/*     */ 
/*     */     
/*     */     public Entry(T ability) {
/* 373 */       this.ability = ability;
/*     */     }
/*     */     
/*     */     public Entry(AbilityCore<T> abilityCore) {
/* 377 */       this.abilityCore = abilityCore;
/*     */     }
/*     */     
/*     */     public AbilityCore<T> getActive() {
/* 381 */       return this.abilityCore;
/*     */     }
/*     */     
/*     */     public boolean isActive() {
/* 385 */       return (this.abilityCore != null);
/*     */     }
/*     */     
/*     */     public T getPassive() {
/* 389 */       return this.ability;
/*     */     }
/*     */     
/*     */     public boolean isPassive() {
/* 393 */       return (this.ability != null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\AbilitiesListScreenPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */