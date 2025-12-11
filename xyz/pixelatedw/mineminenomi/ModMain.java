/*     */ package xyz.pixelatedw.mineminenomi;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.DistExecutor;
/*     */ import net.minecraftforge.fml.ModList;
/*     */ import net.minecraftforge.fml.ModLoadingContext;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.config.ModConfig;
/*     */ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.PausableTimer;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TPSDelta;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBiomes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModContainers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFactions;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFightingStyles;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMemoryModuleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRaces;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSensorTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructureProcessors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.setup.ClientProxy;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyServerProtection;
/*     */ 
/*     */ @Mod("mineminenomi")
/*     */ public class ModMain
/*     */ {
/*     */   public static ModMain instance;
/*  62 */   public static final PausableTimer PAUSABLE_TIMER = new PausableTimer();
/*     */   
/*     */   public static final String PROJECT_ID = "mineminenomi";
/*     */   
/*     */   public static final String PROJECT_VERSION = "0.10.8";
/*     */   public static final String PROJECT_NAME = "Mine Mine no Mi";
/*  68 */   public static final Logger LOGGER = LogManager.getLogger("mineminenomi");
/*     */   
/*  70 */   public static final Marker MARKER = MarkerManager.getMarker("MMNM");
/*     */   
/*     */   public ModMain() {
/*  73 */     PAUSABLE_TIMER.start();
/*     */ 
/*     */     
/*  76 */     Locale.setDefault(Locale.ENGLISH);
/*     */     
/*  78 */     MinecraftForge.EVENT_BUS.register(new WyPatreon.PatreonEvents());
/*  79 */     DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ());
/*     */ 
/*     */ 
/*     */     
/*  83 */     instance = this;
/*     */     
/*  85 */     IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
/*     */ 
/*     */     
/*  88 */     ModItems.register(modEventBus);
/*  89 */     ModWeapons.register(modEventBus);
/*  90 */     ModArmors.register(modEventBus);
/*  91 */     ModTags.init();
/*     */ 
/*     */     
/*  94 */     ModBlocks.register(modEventBus);
/*  95 */     ModTileEntities.register(modEventBus);
/*     */ 
/*     */     
/*  98 */     ModContainers.register(modEventBus);
/*     */ 
/*     */     
/* 101 */     ModAbilities.register(modEventBus);
/* 102 */     ModParticleEffects.register(modEventBus);
/* 103 */     ModMorphs.register(modEventBus);
/*     */ 
/*     */     
/* 106 */     ModEntities.register(modEventBus);
/*     */ 
/*     */     
/* 109 */     ModQuests.register(modEventBus);
/*     */ 
/*     */     
/* 112 */     ModFactions.register(modEventBus);
/* 113 */     ModFightingStyles.register(modEventBus);
/* 114 */     ModRaces.register(modEventBus);
/*     */ 
/*     */     
/* 117 */     ModAttributes.register(modEventBus);
/* 118 */     ModEnchantments.register(modEventBus);
/* 119 */     ModEffects.register(modEventBus);
/* 120 */     ModSounds.register(modEventBus);
/* 121 */     ModLootTypes.register(modEventBus);
/* 122 */     ModParticleTypes.register(modEventBus);
/* 123 */     ModAdvancements.register(modEventBus);
/* 124 */     ModMemoryModuleTypes.register(modEventBus);
/* 125 */     ModSensorTypes.register(modEventBus);
/* 126 */     ModJollyRogers.register(modEventBus);
/* 127 */     ModStructureProcessors.register(modEventBus);
/* 128 */     ModChallenges.register(modEventBus);
/* 129 */     ModFeatures.register(modEventBus);
/* 130 */     ModStructures.register(modEventBus);
/* 131 */     ModBiomes.register(modEventBus);
/* 132 */     ModInteractions.register(modEventBus);
/*     */     
/* 134 */     ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
/* 135 */     DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ());
/*     */     
/* 137 */     WyPermissions.init();
/* 138 */     TPSDelta.INSTANCE.init();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getResourceFolderPath() {
/* 144 */     if (!WyDebug.isDebug()) {
/* 145 */       return null;
/*     */     }
/*     */     
/* 148 */     String basicPath = System.getProperty("user.dir");
/* 149 */     return basicPath.replaceAll("/run/.*", "") + "/src/main/resources";
/*     */   }
/*     */   
/*     */   public static boolean hasTerraforgedInstalled() {
/* 153 */     return ModList.get().isLoaded("terraforged");
/*     */   }
/*     */   
/*     */   public static boolean hasCuriosInstalled() {
/* 157 */     return ModList.get().isLoaded("curios");
/*     */   }
/*     */   
/*     */   public static boolean hasClothConfigInstalled() {
/* 161 */     return ModList.get().isLoaded("cloth-config");
/*     */   }
/*     */   
/*     */   public static boolean hasShitInstalled() {
/* 165 */     return (ModList.get().isLoaded("arclight") || ModList.get().isLoaded("mohist"));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ModMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */