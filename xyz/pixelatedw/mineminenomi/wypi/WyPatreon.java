/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ 
/*     */ public class WyPatreon
/*     */ {
/*  19 */   public static final HashMap<UUID, List<AccountType>> PATREONS = new HashMap<>();
/*     */   
/*  21 */   public static final BuildMode BUILD_MODE = BuildMode.RELEASE;
/*     */   
/*     */   public enum BuildMode
/*     */   {
/*  25 */     RELEASE, DEV, EARLY_ACCESS, QA, PROMO, PUBLIC_QA;
/*     */   }
/*     */   
/*     */   public enum AccountType
/*     */   {
/*  30 */     NORMAL, ROOKIE, SUPERNOVA, CELESTIAL_DRAGON, TESTER;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isDevBuild() {
/*  35 */     return (BUILD_MODE == BuildMode.DEV);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEarlyAccessBuild() {
/*  40 */     return (BUILD_MODE == BuildMode.EARLY_ACCESS);
/*     */   }
/*     */   
/*     */   public static boolean isPublicQABuild() {
/*  44 */     return (BUILD_MODE == BuildMode.PUBLIC_QA);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isReleaseBuild() {
/*  49 */     return (BUILD_MODE == BuildMode.RELEASE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isQABuild() {
/*  54 */     return (BUILD_MODE == BuildMode.QA);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPromoBuild() {
/*  59 */     return (BUILD_MODE == BuildMode.PROMO);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static List<AccountType> getPatreonLevel(PlayerEntity player) throws IOException {
/*  65 */     String apiURL = "/patreon/" + player.func_110124_au().toString();
/*  66 */     String[] result = WyHelper.<String[]>sendGET(apiURL, String[].class);
/*     */     
/*  68 */     if (result != null && result.length > 0) {
/*     */       
/*  70 */       List<AccountType> types = new ArrayList<>();
/*  71 */       for (String group : result) {
/*     */         
/*  73 */         String formattedGroupName = WyHelper.getResourceName(group);
/*  74 */         if (formattedGroupName.equalsIgnoreCase("patreon_rookie")) {
/*  75 */           types.add(AccountType.ROOKIE);
/*     */         }
/*     */         
/*  78 */         if (formattedGroupName.equalsIgnoreCase("patreon_supernova")) {
/*  79 */           types.add(AccountType.SUPERNOVA);
/*     */         }
/*     */         
/*  82 */         if (formattedGroupName.equalsIgnoreCase("patreon_celestial_dragon")) {
/*  83 */           types.add(AccountType.CELESTIAL_DRAGON);
/*     */         }
/*     */         
/*  86 */         if (formattedGroupName.equalsIgnoreCase("mine_mine_no_mi_qateam")) {
/*  87 */           types.add(AccountType.TESTER);
/*     */         }
/*     */       } 
/*     */       
/*  91 */       return types;
/*     */     } 
/*     */     
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCelestialDragon(PlayerEntity player) {
/*  99 */     if (!PATREONS.containsKey(player.func_110124_au())) {
/* 100 */       return false;
/*     */     }
/* 102 */     List<AccountType> accountInfo = PATREONS.get(player.func_110124_au());
/* 103 */     boolean hasType = (accountInfo != null && accountInfo.contains(AccountType.CELESTIAL_DRAGON));
/* 104 */     return hasType;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSupernova(PlayerEntity player) {
/* 109 */     if (!PATREONS.containsKey(player.func_110124_au())) {
/* 110 */       return false;
/*     */     }
/* 112 */     List<AccountType> accountInfo = PATREONS.get(player.func_110124_au());
/* 113 */     boolean hasType = (accountInfo != null && accountInfo.contains(AccountType.SUPERNOVA));
/* 114 */     return (hasType || isCelestialDragon(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTester(PlayerEntity player) {
/* 119 */     if (!PATREONS.containsKey(player.func_110124_au())) {
/* 120 */       return false;
/*     */     }
/* 122 */     List<AccountType> accountInfo = PATREONS.get(player.func_110124_au());
/* 123 */     boolean hasType = (accountInfo != null && accountInfo.contains(AccountType.TESTER));
/* 124 */     return hasType;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRookie(PlayerEntity player) {
/* 129 */     if (!PATREONS.containsKey(player.func_110124_au())) {
/* 130 */       return false;
/*     */     }
/* 132 */     List<AccountType> accountInfo = PATREONS.get(player.func_110124_au());
/* 133 */     boolean hasType = (accountInfo != null && accountInfo.contains(AccountType.ROOKIE));
/* 134 */     return (hasType || isSupernova(player) || isCelestialDragon(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasPatreonAccess(PlayerEntity player) {
/* 139 */     if (isDevBuild() && isCelestialDragon(player)) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (isQABuild() && isTester(player)) {
/* 143 */       return true;
/*     */     }
/* 145 */     if (isEarlyAccessBuild() && isSupernova(player)) {
/* 146 */       return true;
/*     */     }
/*     */     
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPublicQAPeriod() {
/* 155 */     Calendar cal = Calendar.getInstance();
/* 156 */     int year = cal.get(1);
/* 157 */     if (year != 2025) {
/* 158 */       return false;
/*     */     }
/* 160 */     int month = cal.get(2) + 1;
/* 161 */     int day = cal.get(5);
/* 162 */     return (month == 9 || month == 10);
/*     */   }
/*     */   
/*     */   public static class PatreonEvents
/*     */   {
/* 167 */     private static final ITextComponent NO_ACCESS_MESSAGE = (ITextComponent)new StringTextComponent("§c§lWARNING!§r \n\nYou don't have access to this version yet!");
/* 168 */     private static final ITextComponent PUBLIC_TESTING_ENDED_MESSAGE = (ITextComponent)new StringTextComponent("§c§lWARNING!§r \n\nPublic testing access for this build has ended, please download the actual release!");
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 173 */       if (!(event.getEntity() instanceof PlayerEntity) || (event.getEntity().func_130014_f_()).field_72995_K) {
/*     */         return;
/*     */       }
/*     */       
/* 177 */       ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
/*     */       
/* 179 */       if (WyPatreon.isPublicQABuild()) {
/* 180 */         if (!WyPatreon.isPublicQAPeriod()) {
/* 181 */           event.setCanceled(true);
/* 182 */           player.field_71135_a.func_194028_b(PUBLIC_TESTING_ENDED_MESSAGE);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/* 187 */       if (WyPatreon.isReleaseBuild() || (WyPatreon.isDevBuild() && WyDebug.isDebug())) {
/*     */         return;
/*     */       }
/*     */       
/* 191 */       if (WyPatreon.isPromoBuild() && player.func_184102_h().func_71262_S()) {
/*     */         return;
/*     */       }
/*     */       
/* 195 */       if (WyPatreon.PATREONS.containsKey(player.func_110124_au())) {
/*     */         
/* 197 */         List<WyPatreon.AccountType> accountInfo = WyPatreon.PATREONS.get(player.func_110124_au());
/* 198 */         if (accountInfo == null || accountInfo.isEmpty()) {
/* 199 */           event.setCanceled(true);
/* 200 */           player.field_71135_a.func_194028_b(NO_ACCESS_MESSAGE);
/*     */           
/*     */           return;
/*     */         } 
/* 204 */         if (WyPatreon.hasPatreonAccess((PlayerEntity)player)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 209 */       List<WyPatreon.AccountType> patreonLevels = null;
/*     */       
/*     */       try {
/* 212 */         patreonLevels = WyPatreon.getPatreonLevel((PlayerEntity)player);
/*     */       }
/* 214 */       catch (IOException e) {
/*     */         
/* 216 */         e.printStackTrace();
/* 217 */         event.setCanceled(true);
/* 218 */         player.field_71135_a.func_194028_b(NO_ACCESS_MESSAGE);
/*     */         
/*     */         return;
/*     */       } 
/* 222 */       WyPatreon.PATREONS.put(player.func_110124_au(), patreonLevels);
/*     */       
/* 224 */       if (patreonLevels == null || patreonLevels.isEmpty() || !WyPatreon.hasPatreonAccess((PlayerEntity)player)) {
/*     */         
/* 226 */         event.setCanceled(true);
/* 227 */         player.field_71135_a.func_194028_b(NO_ACCESS_MESSAGE);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\WyPatreon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */