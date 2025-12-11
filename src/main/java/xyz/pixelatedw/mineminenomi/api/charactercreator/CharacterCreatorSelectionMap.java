/*     */ package xyz.pixelatedw.mineminenomi.api.charactercreator;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.DummyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
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
/*     */ public class CharacterCreatorSelectionMap
/*     */ {
/*  27 */   public static final AbilityCore<DummyAbility> CYBORG_ARMOR_PERK = (new AbilityCore.Builder("Cyborg Armor", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  28 */     .setPhantomKey(new ResourceLocation("mineminenomi", "cyborg_armor"))
/*  29 */     .setIcon(ModResources.PERK_ICON)
/*  30 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_CYBORG_ARMOR_BONUS
/*  31 */       }).build();
/*     */ 
/*     */   
/*  34 */   public static final AbilityCore<DummyAbility> FISHMAN_SWIM_SPEED_PERK = (new AbilityCore.Builder("Fishman Swim Speed", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  35 */     .setPhantomKey(new ResourceLocation("mineminenomi", "fishman_swim_speed"))
/*  36 */     .setIcon(ModResources.PERK_ICON)
/*  37 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_FISHMAN_SWIM_SPEED_BONUS
/*  38 */       }).build();
/*     */   
/*  40 */   public static final AbilityCore<DummyAbility> FISHMAN_DAMAGE_PERK = (new AbilityCore.Builder("Fishman Damage", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  41 */     .setPhantomKey(new ResourceLocation("mineminenomi", "fishman_damage"))
/*  42 */     .setIcon(ModResources.PERK_ICON)
/*  43 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_FISHMAN_DAMAGE_SPEED_BONUS
/*  44 */       }).build();
/*     */ 
/*     */   
/*  47 */   public static final AbilityCore<DummyAbility> MINK_SPEED_PERK = (new AbilityCore.Builder("Mink Speed", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  48 */     .setPhantomKey(new ResourceLocation("mineminenomi", "mink_speed"))
/*  49 */     .setIcon(ModResources.PERK_ICON)
/*  50 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_MINK_SPEED_BONUS
/*  51 */       }).build();
/*  52 */   public static final AbilityCore<DummyAbility> MINK_JUMP_PERK = (new AbilityCore.Builder("Mink Jump", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  53 */     .setPhantomKey(new ResourceLocation("mineminenomi", "mink_jump"))
/*  54 */     .setIcon(ModResources.PERK_ICON)
/*  55 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_MINK_JUMP_BONUS
/*  56 */       }).build();
/*     */ 
/*     */   
/*  59 */   public static final AbilityCore<DummyAbility> SWORDSMAN_DAMAGE_PERK = (new AbilityCore.Builder("Swordsman Damage", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  60 */     .setPhantomKey(new ResourceLocation("mineminenomi", "swordsman_damage"))
/*  61 */     .setIcon(ModResources.PERK_ICON)
/*  62 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_SWORDSMAN_DAMAGE_BONUS
/*  63 */       }).build();
/*     */ 
/*     */   
/*  66 */   public static final AbilityCore<DummyAbility> SNIPER_ACCURACY_PERK = (new AbilityCore.Builder("Sniper Accuracy", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  67 */     .setPhantomKey(new ResourceLocation("mineminenomi", "sniper_accuracy"))
/*  68 */     .setIcon(ModResources.PERK_ICON)
/*  69 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_SNIPER_ACCURACY_BONUS
/*  70 */       }).build();
/*  71 */   public static final AbilityCore<DummyAbility> SNIPER_GOGGLES_PERK = (new AbilityCore.Builder("Sniper Goggles", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  72 */     .setPhantomKey(new ResourceLocation("mineminenomi", "sniper_goggles"))
/*  73 */     .setIcon(ModResources.PERK_ICON)
/*  74 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_SNIPER_GOGGLES_BONUS
/*  75 */       }).build();
/*     */ 
/*     */   
/*  78 */   public static final AbilityCore<DummyAbility> BLACK_LEG_DAMAGE_PERK = (new AbilityCore.Builder("Black Leg Damage", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  79 */     .setPhantomKey(new ResourceLocation("mineminenomi", "black_leg_damage"))
/*  80 */     .setIcon(ModResources.PERK_ICON)
/*  81 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_BLACK_LEG_DAMAGE_BONUS
/*  82 */       }).build();
/*  83 */   public static final AbilityCore<DummyAbility> BLACK_LEG_SPEED_PERK = (new AbilityCore.Builder("Black Leg Attack Speed", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  84 */     .setPhantomKey(new ResourceLocation("mineminenomi", "black_leg_attack_speed"))
/*  85 */     .setIcon(ModResources.PERK_ICON)
/*  86 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_BLACK_LEG_ATTACK_SPEED_BONUS
/*  87 */       }).build();
/*     */ 
/*     */   
/*  90 */   public static final AbilityCore<DummyAbility> BRAWLER_DAMAGE_PERK = (new AbilityCore.Builder("Brawler Damage", AbilityCategory.STYLE, AbilityType.PASSIVE, DummyAbility::new))
/*  91 */     .setPhantomKey(new ResourceLocation("mineminenomi", "brawler_damage"))
/*  92 */     .setIcon(ModResources.PERK_ICON)
/*  93 */     .addDescriptionLine(new ITextComponent[] { (ITextComponent)ModI18n.PERK_BRAWLER_DAMAGE_BONUS
/*  94 */       }).build();
/*     */   
/*     */   public static class SelectionInfo
/*     */   {
/*     */     private ResourceLocation icon;
/*  99 */     private List<AbilityCore<?>> topAbilities = new ArrayList<>();
/* 100 */     private List<AbilityCore<?>> bottomAbilities = new ArrayList<>();
/*     */     
/*     */     public SelectionInfo(ResourceLocation icon) {
/* 103 */       this.icon = icon;
/*     */     }
/*     */     
/*     */     private SelectionInfo(ResourceLocation icon, ITextComponent textComponent) {
/* 107 */       this(icon);
/*     */     }
/*     */     
/*     */     public void addTopAbilities(AbilityCore<?>... cores) {
/* 111 */       this.topAbilities.addAll(Lists.newArrayList((Object[])cores));
/*     */     }
/*     */     
/*     */     public void addBottomAbilities(AbilityCore<?>... cores) {
/* 115 */       this.bottomAbilities.addAll(Lists.newArrayList((Object[])cores));
/*     */     }
/*     */     
/*     */     public ResourceLocation getIcon() {
/* 119 */       return this.icon;
/*     */     }
/*     */     
/*     */     public List<AbilityCore<?>> getTopAbilities() {
/* 123 */       return this.topAbilities;
/*     */     }
/*     */     
/*     */     public List<AbilityCore<?>> getBottomAbilities() {
/* 127 */       return this.bottomAbilities;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\charactercreator\CharacterCreatorSelectionMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */