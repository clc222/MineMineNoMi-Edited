/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FactionHelper {
/*  22 */   private static final Color CIVILIAN_COLOR = WyHelper.hexToRGB("#55FF55");
/*  23 */   private static final Color MARINE_COLOR = WyHelper.hexToRGB("#55DDFF");
/*  24 */   private static final Color PIRATE_COLOR = WyHelper.hexToRGB("#FF2200");
/*  25 */   private static final Color REVO_COLOR = PIRATE_COLOR;
/*  26 */   private static final Color BOUNTY_HUNTER_COLOR = WyHelper.hexToRGB("#BBFF88");
/*  27 */   private static final Color BANDIT_COLOR = WyHelper.hexToRGB("#925959");
/*     */   
/*     */   @Nullable
/*     */   public static ResourceLocation getFactionIcon(IEntityStats props) {
/*  31 */     ResourceLocation icon = null;
/*  32 */     if (props.isPirate()) {
/*  33 */       icon = ModResources.PIRATE_ICON;
/*     */     }
/*  35 */     else if (props.isMarine()) {
/*  36 */       icon = ModResources.MARINE_ICON_GREYSCALE;
/*     */     }
/*  38 */     else if (props.isBountyHunter()) {
/*  39 */       icon = ModResources.BOUNTY_HUNTER_ICON_GREYSCALE;
/*     */     }
/*  41 */     else if (props.isRevolutionary()) {
/*  42 */       icon = ModResources.REVOLUTIONARY_ARMY_ICON_GREYSCALE;
/*     */     } 
/*     */     
/*  45 */     return icon;
/*     */   }
/*     */   
/*     */   public static Color getFactionColor(IEntityStats props) {
/*  49 */     if (props.isPirate()) {
/*  50 */       return PIRATE_COLOR;
/*     */     }
/*  52 */     if (props.isRevolutionary()) {
/*  53 */       return REVO_COLOR;
/*     */     }
/*  55 */     if (props.isMarine()) {
/*  56 */       return MARINE_COLOR;
/*     */     }
/*  58 */     if (props.isBountyHunter()) {
/*  59 */       return BOUNTY_HUNTER_COLOR;
/*     */     }
/*  61 */     if (props.isBandit()) {
/*  62 */       return BANDIT_COLOR;
/*     */     }
/*     */     
/*  65 */     return CIVILIAN_COLOR;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFactionRGBColor(IEntityStats props) {
/*  70 */     return getFactionColor(props).getRGB();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMessageToCrew(World world, Crew crew, ITextComponent message) {
/*  75 */     for (Crew.Member member : crew.getMembers()) {
/*     */       
/*  77 */       UUID uuid = member.getUUID();
/*  78 */       PlayerEntity memberPlayer = world.func_217371_b(uuid);
/*  79 */       if (memberPlayer != null && memberPlayer.func_70089_S())
/*     */       {
/*  81 */         memberPlayer.func_145747_a(message, Util.field_240973_b_);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void validateFaction(PlayerEntity player) {
/*  87 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*  90 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*  91 */     if (!EntityStatsCapability.get((LivingEntity)player).isPirate()) {
/*  92 */       Crew crew = worldData.getCrewWithMember(player.func_110124_au());
/*  93 */       if (crew != null) {
/*  94 */         worldData.removeCrewMember(player.field_70170_p, crew, player.func_110124_au());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum MarineRank
/*     */   {
/* 104 */     CHORE_BOY(ModI18n.MARINE_TITLE_CHORE_BOY, 0),
/* 105 */     SEAMAN(ModI18n.MARINE_TITLE_SEAMAN, 5),
/* 106 */     PETTY_OFFICER(ModI18n.MARINE_TITLE_PETTY_OFFICER, 10),
/* 107 */     LIEUTENANT(ModI18n.MARINE_TITLE_LIEUTENANT, 15),
/* 108 */     COMMANDER(ModI18n.MARINE_TITLE_COMMANDER, 20),
/* 109 */     CAPTAIN(ModI18n.MARINE_TITLE_CAPTAIN, 25),
/* 110 */     COMMODORE(ModI18n.MARINE_TITLE_COMMODORE, 40),
/* 111 */     VICE_ADMIRAL(ModI18n.MARINE_TITLE_VICE_ADMIRAL, 50),
/* 112 */     ADMIRAL(ModI18n.MARINE_TITLE_ADMIRAL, 70),
/* 113 */     FLEET_ADMIRAL(ModI18n.MARINE_TITLE_FLEET_ADMIRAL, 100);
/*     */     
/*     */     private String unlocalizedName;
/*     */     
/*     */     private int loyaltyRequired;
/*     */     
/*     */     MarineRank(String unlocalizedName, int loyaltyRequired) {
/* 120 */       this.unlocalizedName = unlocalizedName;
/* 121 */       this.loyaltyRequired = loyaltyRequired;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getLocalizedName() {
/* 126 */       return (new TranslationTextComponent(this.unlocalizedName)).getString();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRequiredLoyalty() {
/* 131 */       return this.loyaltyRequired;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum RevolutionaryRank
/*     */   {
/* 137 */     MEMBER(ModI18n.REVOLUTIONARY_TITLE_MEMBER, 0),
/* 138 */     OFFICER(ModI18n.REVOLUTIONARY_TITLE_OFFICER, 30),
/* 139 */     COMMANDER(ModI18n.REVOLUTIONARY_TITLE_COMMANDER, 50),
/* 140 */     CHIEF_OF_STAFF(ModI18n.REVOLUTIONARY_TITLE_CHIEF_OF_STAFF, 80),
/* 141 */     SUPREME_COMMANDER(ModI18n.REVOLUTIONARY_TITLE_SUPREME_COMMANDER, 100);
/*     */     
/*     */     private String unlocalizedName;
/*     */     
/*     */     private int loyaltyRequired;
/*     */     
/*     */     RevolutionaryRank(String unlocalizedName, int loyaltyRequired) {
/* 148 */       this.unlocalizedName = unlocalizedName;
/* 149 */       this.loyaltyRequired = loyaltyRequired;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getLocalizedName() {
/* 154 */       return (new TranslationTextComponent(this.unlocalizedName)).getString();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRequiredLoyalty() {
/* 159 */       return this.loyaltyRequired;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\FactionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */