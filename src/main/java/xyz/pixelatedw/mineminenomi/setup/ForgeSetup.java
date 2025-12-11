/*     */ package xyz.pixelatedw.mineminenomi.setup;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import java.time.Instant;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.resources.IFutureReloadListener;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.Biomes;
/*     */ import net.minecraft.world.gen.feature.StructureFeature;
/*     */ import net.minecraft.world.gen.feature.structure.Structure;
/*     */ import net.minecraft.world.gen.settings.DimensionStructuresSettings;
/*     */ import net.minecraft.world.gen.settings.StructureSeparationSettings;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.AddReloadListenerEvent;
/*     */ import net.minecraftforge.event.RegisterCommandsEvent;
/*     */ import net.minecraftforge.event.world.BiomeLoadingEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
/*     */ import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.ModifyDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.commands.AbilityCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.AbilityProtectionCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.BellyCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.BountyCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.ChallengeCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.ChangeCharacterCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.CheckFruitsCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.CheckPlayerCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.CrewCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.DamageMultiplierCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.DorikiCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.EventsCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.ExtolCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.GetWantedPosterCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.GoRogueCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.HakiExpCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.IssueBountyCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.LoyaltyCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.PouchCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.QuestCommand;
/*     */ import xyz.pixelatedw.mineminenomi.commands.RemoveDFCommand;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.SystemConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.world.features.structures.OPStructure;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.FORGE)
/*     */ public class ForgeSetup {
/*     */   @SubscribeEvent
/*     */   public static void addReloadListeners(AddReloadListenerEvent event) {
/*  74 */     event.addListener((IFutureReloadListener)ModTags.Items.CONDUCTIVE);
/*  75 */     event.addListener((IFutureReloadListener)ModTags.Items.IRON);
/*  76 */     event.addListener((IFutureReloadListener)ModTags.Entities.CONDUCTIVE);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void serverStarted(FMLServerStartedEvent event) {
/*  81 */     NPCWorldData.get().setup(event.getServer().func_71218_a(World.field_234918_g_));
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerCommands(RegisterCommandsEvent event) {
/*  86 */     CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
/*     */     
/*  88 */     LiteralArgumentBuilder<CommandSource> masterBuilder = null;
/*     */     
/*  90 */     boolean masterCommandFlag = ((Boolean)SystemConfig.MASTER_COMMAND.get()).booleanValue();
/*  91 */     if (masterCommandFlag) {
/*  92 */       masterBuilder = Commands.func_197057_a("mmnm");
/*     */     }
/*     */     
/*  95 */     AbilityProtectionCommand.register(dispatcher, masterBuilder);
/*  96 */     DorikiCommand.register(dispatcher, masterBuilder);
/*  97 */     BountyCommand.register(dispatcher, masterBuilder);
/*  98 */     BellyCommand.register(dispatcher, masterBuilder);
/*  99 */     ExtolCommand.register(dispatcher, masterBuilder);
/* 100 */     IssueBountyCommand.register(dispatcher, masterBuilder);
/* 101 */     GetWantedPosterCommand.register(dispatcher, masterBuilder);
/* 102 */     RemoveDFCommand.register(dispatcher, masterBuilder);
/* 103 */     AbilityCommand.register(dispatcher, masterBuilder);
/* 104 */     QuestCommand.register(dispatcher, masterBuilder);
/* 105 */     HakiExpCommand.register(dispatcher, masterBuilder);
/* 106 */     PouchCommand.register(dispatcher, masterBuilder);
/* 107 */     CheckFruitsCommand.register(dispatcher, masterBuilder);
/* 108 */     CheckPlayerCommand.register(dispatcher, masterBuilder);
/* 109 */     DamageMultiplierCommand.register(dispatcher, masterBuilder);
/* 110 */     LoyaltyCommand.register(dispatcher, masterBuilder);
/* 111 */     GoRogueCommand.register(dispatcher, masterBuilder);
/* 112 */     ChallengeCommand.register(dispatcher, masterBuilder);
/* 113 */     CrewCommand.register(dispatcher, masterBuilder);
/* 114 */     ChangeCharacterCommand.register(dispatcher, masterBuilder);
/* 115 */     EventsCommand.register(dispatcher, masterBuilder);
/*     */     
/* 117 */     if (WyPatreon.BUILD_MODE != WyPatreon.BuildMode.RELEASE) {
/* 118 */       FGCommand.register(dispatcher, masterBuilder);
/*     */     }
/*     */     
/* 121 */     if (masterCommandFlag) {
/* 122 */       dispatcher.register(masterBuilder);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void serverStarting(FMLServerStartingEvent event) {
/* 129 */     for (Crew crew : ExtendedWorldData.get().getCrews()) {
/* 130 */       if (crew.getCreationDate() == 0L) {
/* 131 */         crew.setCreationDate(Instant.now().getEpochSecond());
/* 132 */         ExtendedWorldData.get().func_76185_a();
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     for (AkumaNoMiItem fruit : ModValues.DEVIL_FRUITS) {
/* 137 */       ModifyDevilFruitEvent fruitEvent = new ModifyDevilFruitEvent(fruit, fruit.getAbilities());
/* 138 */       MinecraftForge.EVENT_BUS.post((Event)fruitEvent);
/* 139 */       fruit.setAbilities(fruitEvent.getAbilities());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void biomeModification(BiomeLoadingEvent event) {
/* 146 */     if (event.getName() == null || event.getCategory() == Biome.Category.NETHER || event.getCategory() == Biome.Category.THEEND || event.getName().equals(Biomes.field_185440_P.func_240901_a_())) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 151 */     for (Map.Entry<Structure<?>, StructureFeature<?, ?>> entry : (Iterable<Map.Entry<Structure<?>, StructureFeature<?, ?>>>)ModStructures.REGISTERED_STRUCTURES.entrySet()) {
/*     */       
/* 153 */       if (entry.getKey() instanceof OPStructure && !((OPStructure)entry.getKey()).biomeCheck(event)) {
/*     */         continue;
/*     */       }
/* 156 */       event.getGeneration().getStructures().add(() -> (StructureFeature)entry.getValue());
/*     */     } 
/*     */     
/* 159 */     ModFeatures.setupFeatures(event);
/*     */     
/* 161 */     ModEntities.setupCategorySpawns(event);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void addDimensionalSpacing(WorldEvent.Load event) {
/* 166 */     if (event.getWorld() instanceof ServerWorld) {
/* 167 */       ServerWorld serverWorld = (ServerWorld)event.getWorld();
/* 168 */       ResourceLocation dimensionId = serverWorld.func_234923_W_().getRegistryName();
/*     */ 
/*     */       
/* 171 */       if (WyHelper.isInChallengeDimension(serverWorld.func_234923_W_())) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 176 */       for (ResourceLocation dim : CommonConfig.INSTANCE.getBannedDimensionsForStructures()) {
/* 177 */         if (dimensionId.equals(dim)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       if (serverWorld.func_72863_F().func_201711_g() instanceof net.minecraft.world.gen.FlatChunkGenerator) {
/*     */         return;
/*     */       }
/*     */       
/* 189 */       Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>((serverWorld.func_72863_F().func_201711_g().func_235957_b_()).field_236193_d_);
/* 190 */       for (Map.Entry<Structure<?>, StructureFeature<?, ?>> entry : (Iterable<Map.Entry<Structure<?>, StructureFeature<?, ?>>>)ModStructures.REGISTERED_STRUCTURES.entrySet()) {
/* 191 */         tempMap.put(entry.getKey(), DimensionStructuresSettings.field_236191_b_.get(entry.getKey()));
/*     */       }
/* 193 */       (serverWorld.func_72863_F().func_201711_g().func_235957_b_()).field_236193_d_ = tempMap;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\setup\ForgeSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */