/*     */ package xyz.pixelatedw.mineminenomi.setup;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableMultimap;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScreenManager;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.command.arguments.ArgumentSerializer;
/*     */ import net.minecraft.command.arguments.ArgumentTypes;
/*     */ import net.minecraft.command.arguments.IArgumentSerializer;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerInventory;
/*     */ import net.minecraft.inventory.container.ContainerType;
/*     */ import net.minecraft.item.AxeItem;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.util.registry.WorldGenRegistries;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
/*     */ import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
/*     */ import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.TextureStitchEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityGroupArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.ChallengeArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.CrewArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.CurrencyTypeArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.FactionArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.FightingStyleArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.HakiTypeArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.QuestArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.RaceArgument;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.containers.WhiteWalkieStorageContainer;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModContainers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDimensions;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDispenseBehaviors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18nConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItemModelProps;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypeBuffers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.curios.CuriosIntegration;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.RangedAttributeMixin;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EleclawRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BindLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.HandcuffsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.MinkFeaturesLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.GomuDawnWhipLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.GomuSmokeLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaCalendulaLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaHandsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaWingsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.CaptainCapeOverlayLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.LowerHalfArmorLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.UpperHalfArmorLayer;
/*     */ import xyz.pixelatedw.mineminenomi.screens.WhiteWalkieStorageScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModSetup {
/*     */   @SubscribeEvent
/*     */   public static void enqueueIMC(InterModEnqueueEvent event) {
/*  94 */     if (ModMain.hasCuriosInstalled()) {
/*  95 */       CuriosIntegration.setup(event);
/*     */     }
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onStitch(TextureStitchEvent.Pre event) {
/* 102 */     if (ModMain.hasCuriosInstalled()) {
/* 103 */       CuriosIntegration.setupIcons(event);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void commonSetup(FMLCommonSetupEvent event) {
/* 109 */     ModCapabilities.init();
/* 110 */     ModDispenseBehaviors.init();
/*     */     
/* 112 */     ModNetwork.init();
/*     */     
/* 114 */     ModStructures.setupStructures();
/*     */     
/* 116 */     event.enqueueWork(ModDimensions::setupDimensions);
/*     */     
/* 118 */     ImmutableMultimap.Builder<AbilityCategory, AbilityCore<?>> mapBuilder = ImmutableMultimap.builder();
/* 119 */     for (AbilityCore<?> core : (Iterable<AbilityCore<?>>)ModRegistries.ABILITIES.getValues()) {
/* 120 */       mapBuilder.put(core.getCategory(), core);
/*     */     }
/* 122 */     ModValues.abilityCategoryMap = mapBuilder.build();
/*     */ 
/*     */ 
/*     */     
/* 126 */     ArgumentTypes.func_218136_a("ability", AbilityArgument.class, (IArgumentSerializer)new ArgumentSerializer(AbilityArgument::ability));
/* 127 */     ArgumentTypes.func_218136_a("quest", QuestArgument.class, (IArgumentSerializer)new ArgumentSerializer(QuestArgument::quest));
/* 128 */     ArgumentTypes.func_218136_a("type", HakiTypeArgument.class, (IArgumentSerializer)new ArgumentSerializer(HakiTypeArgument::hakiType));
/* 129 */     ArgumentTypes.func_218136_a("group", AbilityGroupArgument.class, (IArgumentSerializer)new ArgumentSerializer(AbilityGroupArgument::abilityGroup));
/* 130 */     ArgumentTypes.func_218136_a("crew", CrewArgument.class, (IArgumentSerializer)new ArgumentSerializer(CrewArgument::crew));
/* 131 */     ArgumentTypes.func_218136_a("challenge", ChallengeArgument.class, (IArgumentSerializer)new ArgumentSerializer(ChallengeArgument::challenge));
/* 132 */     ArgumentTypes.func_218136_a("currency", CurrencyTypeArgument.class, (IArgumentSerializer)new ArgumentSerializer(CurrencyTypeArgument::currencyType));
/* 133 */     ArgumentTypes.func_218136_a("faction", FactionArgument.class, (IArgumentSerializer)new ArgumentSerializer(FactionArgument::faction));
/* 134 */     ArgumentTypes.func_218136_a("style", FightingStyleArgument.class, (IArgumentSerializer)new ArgumentSerializer(FightingStyleArgument::fightingStyle));
/* 135 */     ArgumentTypes.func_218136_a("race", RaceArgument.class, (IArgumentSerializer)new ArgumentSerializer(RaceArgument::race));
/*     */     
/* 137 */     ModEntities.setupSpawnRules();
/*     */     
/* 139 */     addStructureAsHouse(new ResourceLocation("mineminenomi", "unaligned/blackleg_kitchen"), CommonConfig.INSTANCE.hasVillageCompat() ? 1 : 10);
/* 140 */     addStructureAsHouse(new ResourceLocation("mineminenomi", "unaligned/medic_tent"), CommonConfig.INSTANCE.hasVillageCompat() ? 1 : 5);
/* 141 */     addStructureAsHouse(new ResourceLocation("mineminenomi", "unaligned/tavern"), CommonConfig.INSTANCE.hasVillageCompat() ? 1 : 255);
/*     */     
/* 143 */     if (ModMain.hasCuriosInstalled()) {
/* 144 */       CuriosIntegration.registerCurioItems();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void addStructureAsHouse(ResourceLocation structure, int weight) {
/* 149 */     addPieceToPattern(new ResourceLocation("village/plains/houses"), structure, weight);
/* 150 */     addPieceToPattern(new ResourceLocation("village/desert/houses"), structure, weight);
/* 151 */     addPieceToPattern(new ResourceLocation("village/savanna/houses"), structure, weight);
/* 152 */     addPieceToPattern(new ResourceLocation("village/snowy/houses"), structure, weight);
/* 153 */     addPieceToPattern(new ResourceLocation("village/taiga/houses"), structure, weight);
/*     */   }
/*     */   
/*     */   private static void addPieceToPattern(ResourceLocation id, ResourceLocation structure, int weight) {
/* 157 */     RegistryKey<JigsawPattern> key = RegistryKey.func_240903_a_(Registry.field_243555_ax, id);
/* 158 */     JigsawPattern pattern = (JigsawPattern)WorldGenRegistries.field_243656_h.func_230516_a_(key);
/* 159 */     pattern.field_214952_d.add(Pair.of(JigsawPiece.func_242849_a(structure.toString()).apply(JigsawPattern.PlacementBehaviour.RIGID), Integer.valueOf(weight)));
/* 160 */     JigsawPatternRegistry.func_244094_a(pattern);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onLoadComplete(FMLLoadCompleteEvent event) {
/* 166 */     ((RangedAttributeMixin)Attributes.field_233818_a_).setMaxValue(3000.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void clientSetup(FMLClientSetupEvent event) {
/* 174 */     ModKeybindings.init();
/*     */ 
/*     */     
/* 177 */     ModI18n.init();
/* 178 */     ModI18nConfig.init();
/*     */ 
/*     */     
/* 181 */     ModRenderers.registerRenderers();
/* 182 */     ModItemModelProps.register();
/*     */     
/* 184 */     ModRenderTypeBuffers.init();
/*     */     
/* 186 */     if (WyDebug.isDebug()) {
/* 187 */       WyHelper.generateJSONLangs();
/*     */     }
/*     */     
/* 190 */     event.enqueueWork(() -> {
/*     */           AxeItem.field_203176_a = (Map)(new ImmutableMap.Builder()).putAll(AxeItem.field_203176_a).put(ModBlocks.MANGROVE_LOG.get(), ModBlocks.STRIPPED_MANGROVE_LOG.get()).put(ModBlocks.MANGROVE_WOOD.get(), ModBlocks.STRIPPED_MANGROVE_WOOD.get()).build();
/*     */           ScreenManager.func_216911_a((ContainerType)ModContainers.WHITE_WALKIE_STORAGE.get(), ());
/*     */           Minecraft mc = Minecraft.func_71410_x();
/*     */           for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : (Iterable<Map.Entry<EntityType<?>, EntityRenderer<?>>>)(mc.func_175598_ae()).field_78729_o.entrySet()) {
/*     */             EntityRenderer entityRenderer = entry.getValue();
/*     */             if (entityRenderer instanceof LivingRenderer) {
/*     */               LivingRenderer renderer = (LivingRenderer)entityRenderer;
/*     */               renderer.func_177094_a((LayerRenderer)new PotionLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new CaptainCapeOverlayLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new HandcuffsLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new BindLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new HanaWingsLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new HanaCalendulaLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new HanaHandsLayer((IEntityRenderer)renderer));
/*     */               renderer.func_177094_a((LayerRenderer)new EleclawRenderer((IEntityRenderer)renderer));
/*     */               if (renderer.func_217764_d() instanceof net.minecraft.client.renderer.entity.model.BipedModel) {
/*     */                 renderer.func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)renderer));
/*     */                 renderer.func_177094_a((LayerRenderer)new UpperHalfArmorLayer((IEntityRenderer)renderer));
/*     */                 renderer.func_177094_a((LayerRenderer)new LowerHalfArmorLayer((IEntityRenderer)renderer));
/*     */                 renderer.func_177094_a((LayerRenderer)new GomuSmokeLayer((IEntityRenderer)renderer));
/*     */                 renderer.func_177094_a((LayerRenderer)new GomuDawnWhipLayer((IEntityRenderer)renderer));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           for (Map.Entry<String, PlayerRenderer> entry : (Iterable<Map.Entry<String, PlayerRenderer>>)mc.func_175598_ae().getSkinMap().entrySet()) {
/*     */             PlayerRenderer renderer = entry.getValue();
/*     */             renderer.func_177094_a((LayerRenderer)new PotionLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new CaptainCapeOverlayLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new AuraLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new HandcuffsLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new BindLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new HanaWingsLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new HanaCalendulaLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new HanaHandsLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new EleclawRenderer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new MinkFeaturesLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new UpperHalfArmorLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new LowerHalfArmorLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new GomuSmokeLayer((IEntityRenderer)renderer));
/*     */             renderer.func_177094_a((LayerRenderer)new GomuDawnWhipLayer((IEntityRenderer)renderer));
/*     */           } 
/*     */           ModAnimations.clientInit();
/*     */           if (ModMain.hasCuriosInstalled())
/*     */             CuriosIntegration.registerCurioRenderers(); 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\setup\ModSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */