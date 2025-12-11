/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.arguments.LongArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.BlockPosArgument;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.registry.DynamicRegistries;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.Dimension;
/*     */ import net.minecraft.world.DimensionType;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.feature.template.IntegrityProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.charactercreator.FactionId;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.FactionArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.NTEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.POIEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SGrabScreenshotPacket;
/*     */ import xyz.pixelatedw.mineminenomi.world.ChallengesChunkGenerator;
/*     */ import xyz.pixelatedw.mineminenomi.world.DynamicDimensionManager;
/*     */ import xyz.pixelatedw.mineminenomi.world.RandomizeSkyBlocksProcessor;
/*     */ import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FGCommand {
/* 109 */   public static int ANIM_SPEED = 8; public static boolean NO_COOLDOWN = false;
/*     */   public static boolean SHOW_TPS = false;
/*     */   
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 113 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("fg").requires(source -> source.func_197034_c(0));
/*     */     
/* 115 */     builder
/* 116 */       .then(Commands.func_197057_a("info")
/* 117 */         .executes(context -> showInfo(context, ((CommandSource)context.getSource()).func_197035_h())));
/*     */     
/* 119 */     builder
/* 120 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("check_haki")
/* 121 */         .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 122 */           .executes(context -> checkHakiStats(context, EntityArgument.func_197089_d(context, "target")))))
/* 123 */         .executes(context -> checkHakiStats(context, ((CommandSource)context.getSource()).func_197035_h())));
/*     */     
/* 125 */     builder
/* 126 */       .then(Commands.func_197057_a("check_bounties")
/* 127 */         .executes(context -> checkBounties(context)));
/*     */     
/* 129 */     builder
/* 130 */       .then(Commands.func_197057_a("check_structures")
/* 131 */         .executes(context -> checkStructures(context, ((CommandSource)context.getSource()).func_197035_h())));
/*     */     
/* 133 */     builder
/* 134 */       .then(Commands.func_197057_a("turn_sword_clone")
/* 135 */         .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 136 */           .executes(context -> turnSwordInClone(context, EntityArgument.func_197089_d(context, "target")))));
/*     */     
/* 138 */     builder
/* 139 */       .then(Commands.func_197057_a("drop_package")
/* 140 */         .executes(context -> dropPackage(context, ((CommandSource)context.getSource()).func_197035_h())));
/*     */     
/* 142 */     builder
/* 143 */       .then(Commands.func_197057_a("save_structure")
/* 144 */         .then(Commands.func_197056_a("from", (ArgumentType)BlockPosArgument.func_197276_a())
/* 145 */           .then(Commands.func_197056_a("to", (ArgumentType)BlockPosArgument.func_197276_a())
/* 146 */             .then(Commands.func_197056_a("name", (ArgumentType)StringArgumentType.word())
/* 147 */               .executes(context -> saveStructure(context, BlockPosArgument.func_197274_b(context, "from"), BlockPosArgument.func_197274_b(context, "to"), StringArgumentType.getString(context, "name"), ((CommandSource)context.getSource()).func_197035_h()))))));
/*     */     
/* 149 */     builder
/* 150 */       .then(Commands.func_197057_a("load_structure")
/* 151 */         .executes(context -> loadStructure(context, ((CommandSource)context.getSource()).func_197035_h())));
/*     */     
/* 153 */     builder
/* 154 */       .then(Commands.func_197057_a("hardcore")
/* 155 */         .executes(context -> harcoreLoadout(context, ((CommandSource)context.getSource()).func_197035_h())));
/*     */     
/* 157 */     builder
/* 158 */       .then(Commands.func_197057_a("no_cooldowns")
/* 159 */         .executes(context -> {
/*     */             NO_COOLDOWN = !NO_COOLDOWN;
/*     */             
/*     */             ((CommandSource)context.getSource()).func_197035_h().func_145747_a((ITextComponent)new StringTextComponent((NO_COOLDOWN ? "Enabled" : "Disabled") + " No Cooldown Mode"), ((CommandSource)context.getSource()).func_197035_h().func_110124_au());
/*     */             return 1;
/*     */           }));
/* 165 */     builder
/* 166 */       .then(Commands.func_197057_a("simulate_challenges")
/* 167 */         .executes(context -> {
/*     */             ServerPlayerEntity player = ((CommandSource)context.getSource()).func_197035_h();
/*     */             
/*     */             for (int i = 0; i < 10; i++) {
/*     */               UUID id = UUID.randomUUID();
/*     */               
/*     */               ResourceLocation dimName = new ResourceLocation("mineminenomi", "challenges_" + id);
/*     */               
/*     */               RegistryKey<World> dimension = RegistryKey.func_240903_a_(Registry.field_239699_ae_, dimName);
/*     */               
/*     */               DynamicRegistries registryAccess = player.field_70170_p.func_241828_r();
/*     */               
/*     */               Supplier<DimensionType> type = ();
/*     */               
/*     */               ServerWorld serverWorld = DynamicDimensionManager.getOrCreateWorld(player.func_184102_h(), dimension, ());
/*     */             } 
/*     */             
/*     */             return 1;
/*     */           }));
/* 186 */     builder
/* 187 */       .then(Commands.func_197057_a("anim-speed")
/* 188 */         .then(Commands.func_197056_a("speed", (ArgumentType)IntegerArgumentType.integer())
/* 189 */           .executes(context -> {
/*     */               ANIM_SPEED = IntegerArgumentType.getInteger(context, "speed");
/*     */               
/*     */               ((CommandSource)context.getSource()).func_197035_h().func_145747_a((ITextComponent)new StringTextComponent("Animation Speed set to: " + ANIM_SPEED), ((CommandSource)context.getSource()).func_197035_h().func_110124_au());
/*     */               
/*     */               return 1;
/*     */             })));
/* 196 */     builder
/* 197 */       .then(Commands.func_197057_a("show_tps")
/* 198 */         .executes(context -> {
/*     */             SHOW_TPS = !SHOW_TPS;
/*     */             
/*     */             ((CommandSource)context.getSource()).func_197035_h().func_145747_a((ITextComponent)new StringTextComponent((SHOW_TPS ? "Enabled" : "Disabled") + " Show TPS Mode"), ((CommandSource)context.getSource()).func_197035_h().func_110124_au());
/*     */             return 1;
/*     */           }));
/* 204 */     builder
/* 205 */       .then(Commands.func_197057_a("clue")
/* 206 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("rolls", (ArgumentType)IntegerArgumentType.integer(1))
/* 207 */           .executes(context -> giveRandomClue(context, ((CommandSource)context.getSource()).func_197035_h(), IntegerArgumentType.getInteger(context, "rolls"), null)))
/* 208 */           .then(Commands.func_197056_a("fruit", (ArgumentType)StringArgumentType.string())
/* 209 */             .executes(context -> giveRandomClue(context, ((CommandSource)context.getSource()).func_197035_h(), IntegerArgumentType.getInteger(context, "rolls"), StringArgumentType.getString(context, "fruit"))))));
/*     */     
/* 211 */     builder
/* 212 */       .then(Commands.func_197057_a("ambush")
/* 213 */         .executes(context -> {
/*     */             ServerPlayerEntity player = ((CommandSource)context.getSource()).func_197035_h();
/*     */             
/*     */             if (EntityStatsCapability.get((LivingEntity)player).getBounty() < 1000L) {
/*     */               EntityStatsCapability.get((LivingEntity)player).alterBounty(2000L, StatChangeSource.COMMAND);
/*     */             }
/*     */             
/*     */             AmbushSpawner spawner = new AmbushSpawner();
/*     */             
/*     */             spawner.spawn(((CommandSource)context.getSource()).func_197023_e());
/*     */             return 1;
/*     */           }));
/* 225 */     builder
/* 226 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("clean")
/* 227 */         .then(Commands.func_197057_a("abilities")
/* 228 */           .executes(ctx -> {
/*     */               ServerPlayerEntity player = ((CommandSource)ctx.getSource()).func_197035_h();
/*     */               
/*     */               IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */               props.getUnlockedAbilities().stream().map(()).forEach(());
/*     */               return 1;
/* 234 */             }))).then(Commands.func_197057_a("quests")
/* 235 */           .executes(ctx -> {
/*     */               ServerPlayerEntity player = ((CommandSource)ctx.getSource()).func_197035_h();
/*     */               
/*     */               IQuestData questData = QuestDataCapability.get((PlayerEntity)player);
/*     */               
/*     */               Iterator<QuestId> quests = questData.getFinishedQuests().iterator();
/*     */               
/*     */               while (quests.hasNext()) {
/*     */                 questData.removeFinishedQuest(quests.next());
/*     */               }
/*     */               
/*     */               return 1;
/*     */             })));
/* 248 */     builder
/* 249 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("stress_test")
/* 250 */         .then(Commands.func_197057_a("spawners")
/* 251 */           .executes(context -> stressTestSpawners(context, ((CommandSource)context.getSource()).func_197035_h()))))
/* 252 */         .then(Commands.func_197057_a("blocks")
/* 253 */           .executes(context -> stressTestBlocks(context, ((CommandSource)context.getSource()).func_197035_h()))));
/*     */     
/* 255 */     ((LiteralArgumentBuilder)builder
/* 256 */       .then(Commands.func_197057_a("caravans")
/* 257 */         .executes(ctx -> {
/*     */             ServerPlayerEntity player = ((CommandSource)ctx.getSource()).func_197035_h();
/*     */             
/*     */             EventsWorldData worldData = EventsWorldData.get();
/*     */             
/*     */             Set<NTEventTarget> targets = worldData.getNotoriousTargets();
/*     */             
/*     */             Set<POIEventTarget> caravans = worldData.getCaravanPOIs();
/*     */             
/*     */             Set<POIEventTarget> visits = worldData.getCelestialVisitsPOIs();
/*     */             
/*     */             StringBuilder sb = new StringBuilder();
/*     */             
/*     */             sb.append("§6Targets:§r " + targets.size() + "\n");
/*     */             for (NTEventTarget poi : targets) {
/*     */               sb.append("\n- " + (int)(poi.getPosition()).field_72450_a + " " + (int)(poi.getPosition()).field_72448_b + " " + (int)(poi.getPosition()).field_72449_c);
/*     */             }
/*     */             sb.append("\n\n");
/*     */             sb.append("§6Caravans:§r " + caravans.size() + "\n");
/*     */             for (POIEventTarget poi : caravans) {
/*     */               sb.append("\n- " + (int)(poi.getPosition()).field_72450_a + " " + (int)(poi.getPosition()).field_72448_b + " " + (int)(poi.getPosition()).field_72449_c);
/*     */             }
/*     */             sb.append("\n\n");
/*     */             sb.append("§6Visits:§r " + visits.size() + "\n");
/*     */             for (POIEventTarget poi : visits) {
/*     */               sb.append("\n- " + (int)(poi.getPosition()).field_72450_a + " " + (int)(poi.getPosition()).field_72448_b + " " + (int)(poi.getPosition()).field_72449_c);
/*     */             }
/*     */             sb.append("\n\n");
/*     */             player.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/*     */             return 1;
/* 287 */           }))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.func_197057_a("notorious_boss")
/* 288 */         .then(Commands.func_197057_a("list")
/* 289 */           .then(Commands.func_197056_a("faction", (ArgumentType)FactionArgument.faction())
/* 290 */             .executes(ctx -> {
/*     */                 FactionId factionFilter = FactionArgument.getFaction(ctx, "faction");
/*     */                 
/*     */                 ServerPlayerEntity player = ((CommandSource)ctx.getSource()).func_197035_h();
/*     */                 
/*     */                 NPCWorldData worldData = NPCWorldData.get();
/*     */                 
/*     */                 Set<TrackedNPC> set = worldData.getTrackedMobs();
/*     */                 
/*     */                 set.removeIf(());
/*     */                 
/*     */                 StringBuilder sb = new StringBuilder();
/*     */                 sb.append("§6Alive NPCs:§r " + set.size() + "\n\n");
/*     */                 for (TrackedNPC npc : set) {
/*     */                   NotoriousEntity entity = npc.createTrackedMob((World)player.func_71121_q());
/*     */                   sb.append(entity.func_145748_c_().getString() + " ( " + npc.getId() + " )");
/*     */                   sb.append("\n  UUID: " + npc.getUUID());
/*     */                   sb.append("\n  Faction: " + npc.getFaction());
/*     */                   sb.append("\n  Race: " + npc.getRace());
/*     */                   sb.append("\n  Style: " + npc.getStyle());
/*     */                   sb.append("\n  Doriki: " + npc.getDoriki());
/*     */                   sb.append("\n  Bounty: " + npc.getBounty());
/*     */                   sb.append("\n  Kenbunshoku: " + npc.getObservationHaki());
/*     */                   sb.append("\n  Busoshoku: " + npc.getBusoshokuHaki());
/*     */                   sb.append("\n\n");
/*     */                 } 
/*     */                 player.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/*     */                 return 1;
/* 318 */               })))).then(Commands.func_197057_a("update")
/* 319 */           .executes(ctx -> {
/*     */               ServerPlayerEntity player = ((CommandSource)ctx.getSource()).func_197035_h();
/*     */               
/*     */               NPCWorldData worldData = NPCWorldData.get();
/*     */               
/*     */               Set<TrackedNPC> set = worldData.getTrackedMobs();
/*     */               
/*     */               for (TrackedNPC npc : set) {
/*     */                 npc.recalculateDifficulty(player.func_71121_q());
/*     */               }
/*     */               return 1;
/* 330 */             }))).then(Commands.func_197056_a("id", (ArgumentType)LongArgumentType.longArg())
/* 331 */           .executes(context -> {
/*     */               ServerPlayerEntity player = ((CommandSource)context.getSource()).func_197035_h();
/*     */               
/*     */               EventsWorldData eventsWorldData = EventsWorldData.get();
/*     */               
/*     */               long id = LongArgumentType.getLong(context, "id");
/*     */               
/*     */               Optional<TrackedNPC> tracked = NPCWorldData.get().getTrackedMob(id);
/*     */               
/*     */               if (!tracked.isPresent()) {
/*     */                 return 1;
/*     */               }
/*     */               
/*     */               Vector3d pos = player.func_213303_ch().func_72441_c(10.0D, 0.0D, 0.0D);
/*     */               
/*     */               eventsWorldData.addNotoriousTarget(player.func_71121_q(), pos, 1200L, tracked.get());
/*     */               return 1;
/*     */             })));
/* 349 */     builder
/* 350 */       .then(Commands.func_197057_a("hurt")
/* 351 */         .then(Commands.func_197056_a("amount", (ArgumentType)FloatArgumentType.floatArg())
/* 352 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 353 */             .executes(ctx -> {
/*     */                 ServerPlayerEntity target = EntityArgument.func_197089_d(ctx, "target");
/*     */                 
/*     */                 float amount = FloatArgumentType.getFloat(ctx, "amount");
/*     */                 
/*     */                 ModDamageSource source = (ModDamageSource)(new ModDamageSource("fgsource")).setUnavoidable().bypassLogia().func_151518_m().func_76348_h();
/*     */                 
/*     */                 target.func_70097_a((DamageSource)source, amount);
/*     */                 
/*     */                 return 1;
/*     */               }))));
/* 364 */     builder
/* 365 */       .then(Commands.func_197057_a("create_skybox")
/* 366 */         .then(Commands.func_197056_a("pos", (ArgumentType)BlockPosArgument.func_197276_a())
/* 367 */           .executes(ctx -> createSkybox(ctx, ((CommandSource)ctx.getSource()).func_197035_h(), BlockPosArgument.func_197274_b(ctx, "pos")))));
/*     */     
/* 369 */     TestCommand.register(builder);
/*     */     
/* 371 */     if (masterBuilder != null) {
/* 372 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*     */       
/* 375 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   
/* 379 */   public static int SKYBOX_ID = 0;
/*     */   
/*     */   private static int createSkybox(CommandContext<CommandSource> context, ServerPlayerEntity player, BlockPos blockPos) {
/* 382 */     player.func_223102_j(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
/*     */     
/* 384 */     int[][] angles = { { 90, 0 }, { 180, 0 }, { 270, 0 }, { 360, 0 }, { 360, -180 }, { 360, 180 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     SKYBOX_ID %= 6;
/*     */     
/* 395 */     player.field_70177_z = angles[SKYBOX_ID][0];
/* 396 */     player.field_70125_A = angles[SKYBOX_ID][1];
/* 397 */     player.func_200619_a((ServerWorld)player.field_70170_p, blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), player.field_70177_z, player.field_70125_A);
/*     */     try {
/* 399 */       Thread.sleep(500L);
/*     */     }
/* 401 */     catch (InterruptedException e) {
/* 402 */       e.printStackTrace();
/*     */     } 
/* 404 */     WyNetwork.sendTo(new SGrabScreenshotPacket(), (PlayerEntity)player);
/* 405 */     SKYBOX_ID++;
/*     */     
/* 407 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int giveRandomClue(CommandContext<CommandSource> context, ServerPlayerEntity player, int rolls, @Nullable String fruitTemplate) {
/* 412 */     AkumaNoMiItem fruit = null;
/* 413 */     if (Strings.isNullOrEmpty(fruitTemplate)) {
/* 414 */       fruit = WyHelper.shuffle(new ArrayList(ModValues.DEVIL_FRUITS)).stream().findFirst().orElse(null);
/*     */     } else {
/*     */       
/* 417 */       fruit = ModValues.DEVIL_FRUITS.stream().filter(df -> df.getFruitKey().equals(fruitTemplate)).findFirst().orElse(null);
/*     */     } 
/* 419 */     if (fruit == null) {
/* 420 */       return 1;
/*     */     }
/* 422 */     if (fruit != null) {
/*     */       
/* 424 */       DFEncyclopediaEntry template = fruit.getRandomElements((IWorld)player.func_71121_q());
/*     */       
/* 426 */       Optional<Integer> shape = Optional.empty();
/* 427 */       Optional<Color> baseColor = Optional.empty();
/* 428 */       Optional<Color> stemColor = Optional.empty();
/*     */       
/* 430 */       int maxRolls = 2;
/* 431 */       if (player.func_70681_au().nextInt(100) < 10) {
/* 432 */         maxRolls = 3;
/*     */       }
/*     */ 
/*     */       
/* 436 */       for (int i = 0; i < rolls; i++) {
/*     */         
/* 438 */         int rand = player.func_70681_au().nextInt(3);
/* 439 */         if (rand == 0) {
/* 440 */           shape = template.getShape();
/*     */         }
/* 442 */         else if (rand == 1) {
/* 443 */           baseColor = template.getBaseColor();
/*     */         } else {
/*     */           
/* 446 */           stemColor = template.getStemColor();
/*     */         } 
/*     */       } 
/*     */       
/* 450 */       ItemStack stack = new ItemStack((IItemProvider)Items.field_151121_aF);
/* 451 */       String key = fruit.getFruitKey();
/* 452 */       CompoundNBT nbt = stack.func_190925_c("fruitClues");
/* 453 */       nbt.func_74778_a("key", key);
/* 454 */       if (shape.isPresent()) {
/* 455 */         nbt.func_74768_a("shape", ((Integer)shape.get()).intValue());
/*     */       }
/* 457 */       if (baseColor.isPresent()) {
/* 458 */         nbt.func_74768_a("baseColor", ((Color)baseColor.get()).getRGB());
/*     */       }
/* 460 */       if (stemColor.isPresent()) {
/* 461 */         nbt.func_74768_a("stemColor", ((Color)stemColor.get()).getRGB());
/*     */       }
/* 463 */       stack.func_200302_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_FRUIT_CLUE));
/* 464 */       stack.func_190920_e(1);
/* 465 */       player.func_191521_c(stack);
/*     */     } 
/*     */     
/* 468 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int stressTestBlocks(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 473 */     long startTime = System.currentTimeMillis();
/* 474 */     int i = 0;
/*     */     
/*     */     try {
/* 477 */       for (int x = -50; x < 50; x++) {
/*     */         
/* 479 */         for (int y = -50; y < 50; y++) {
/*     */           
/* 481 */           for (int z = -50; z < 50; z++)
/*     */           {
/* 483 */             WyHelper.setBlockStateInChunk(player.field_70170_p, player.func_233580_cy_().func_177982_a(x, y, z), Blocks.field_150346_d.func_176223_P(), 2);
/* 484 */             i++;
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 489 */     } catch (Exception e) {
/*     */       
/* 491 */       e.printStackTrace();
/*     */     } 
/* 493 */     long stopTime = System.currentTimeMillis();
/* 494 */     System.out.println("Finished generating " + i + " blocks after " + (stopTime - startTime) + " millis");
/*     */     
/* 496 */     return 1;
/*     */   }
/*     */   
/*     */   private static int stressTestSpawners(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 500 */     ServerWorld world = player.func_71121_q();
/*     */     
/* 502 */     for (int i = -3; i < 3; i++) {
/* 503 */       for (int j = -3; j < 3; j++) {
/* 504 */         BlockPos pos = player.func_233580_cy_().func_177982_a(i, 0, j);
/* 505 */         world.func_175656_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P());
/* 506 */         CustomSpawnerTileEntity spawner = (CustomSpawnerTileEntity)world.func_175625_s(pos);
/* 507 */         spawner.setSpawnerLimit(2);
/* 508 */         spawner.setSpawnerMob((EntityType)ModEntities.MARINE_GRUNT.get());
/*     */       } 
/*     */     } 
/*     */     
/* 512 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkStructures(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 517 */     int[] arr = StructuresHelper.STRUCTURES_COUNT;
/* 518 */     int sum = 0;
/*     */     
/* 520 */     for (int i : arr)
/*     */     {
/* 522 */       sum += i;
/*     */     }
/*     */     
/* 525 */     StringBuilder builder = new StringBuilder();
/* 526 */     builder.append("========SHIPS==================================\n\n");
/* 527 */     builder.append("Small Pirate Ships: " + arr[0] + "\n");
/* 528 */     builder.append("Medium Pirate Ships: " + arr[1] + "\n");
/* 529 */     builder.append("Large Pirate Ships: " + arr[2] + "\n");
/* 530 */     builder.append("Marine Battleships: " + arr[3] + "\n");
/* 531 */     builder.append("Ghost Ships: " + arr[4] + "\n\n");
/* 532 */     builder.append("========LAND STRUCTURES========================\n\n");
/* 533 */     builder.append("Marine Small Base: " + arr[5] + "\n");
/* 534 */     builder.append("Marine Large Base: " + arr[6] + "\n");
/* 535 */     builder.append("Bandit Small Base: " + arr[7] + "\n");
/* 536 */     builder.append("Bandit Large Base: " + arr[8] + "\n");
/* 537 */     builder.append("Dojos: " + arr[9] + "\n");
/* 538 */     builder.append("Marine Camp: " + arr[12] + "\n");
/* 539 */     builder.append("Bandit Camp: " + arr[13] + "\n");
/* 540 */     builder.append("Marine Watch Tower: " + arr[10] + "\n");
/* 541 */     builder.append("========SKY STRUCTURES========================\n\n");
/* 542 */     builder.append("Sky Islands: " + arr[11] + "\n");
/* 543 */     builder.append("Total: " + sum + "\n");
/* 544 */     builder.append("===============================================");
/*     */     
/* 546 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), true);
/*     */     
/* 548 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int dropPackage(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 553 */     BountyHelper.issueBountyForAllPlayers(player.field_70170_p);
/* 554 */     WantedPosterPackageEntity posterPackage = new WantedPosterPackageEntity(player.field_70170_p);
/* 555 */     posterPackage.func_70107_b(player.func_226277_ct_(), player.func_226278_cu_() + 5.0D, player.func_226281_cx_());
/* 556 */     player.field_70170_p.func_217376_c((Entity)posterPackage);
/* 557 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int harcoreLoadout(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 562 */     player.field_71071_by.func_70441_a(new ItemStack((IItemProvider)ModAbilities.YOMI_YOMI_NO_MI));
/* 563 */     player.field_71071_by.func_70441_a(new ItemStack((IItemProvider)Blocks.field_150346_d, 64));
/* 564 */     player.func_71024_bL().func_75114_a(0);
/* 565 */     player.func_71024_bL().func_75119_b(0.0F);
/* 566 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String escapeSpecialCharacters(String data) {
/* 571 */     String escapedData = data.replaceAll("\\R", " ");
/* 572 */     if (data.contains(",") || data.contains("\"") || data.contains("'")) {
/*     */       
/* 574 */       data = data.replace("\"", "\"\"");
/* 575 */       escapedData = "\"" + data + "\"";
/*     */     } 
/* 577 */     return escapedData;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int showInfo(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 597 */     Collection<AbilityCore<?>> clones = ModRegistries.ABILITIES.getValues();
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
/*     */     
/* 619 */     List<AbilityCore<?>> noTextures = (List<AbilityCore<?>>)clones.stream().filter(abl -> { if (!abl.isHidden()) { String texture = WyHelper.getResourceName(abl.getIcon().func_110623_a()); try { boolean hasFile = (new File(ModMain.getResourceFolderPath() + "/assets/" + "mineminenomi" + "/textures/abilities/" + texture + ".png")).exists(); if (!hasFile) return true;  } catch (Exception e) { e.printStackTrace(); return false; }  }  return false; }).collect(Collectors.toList());
/*     */ 
/*     */ 
/*     */     
/* 623 */     StringBuilder builder = new StringBuilder();
/* 624 */     builder.append("Abilities Registered: " + clones.size());
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
/* 638 */     player.func_145747_a((ITextComponent)new StringTextComponent(builder.toString()), Util.field_240973_b_);
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
/* 670 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int loadStructure(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 675 */     PlacementSettings placement = (new PlacementSettings()).func_186214_a(Mirror.NONE).func_186220_a(Rotation.NONE).func_186222_a(false).func_186218_a((ChunkPos)null);
/* 676 */     placement.func_215219_b()
/* 677 */       .func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215205_b)
/* 678 */       .func_215222_a((StructureProcessor)new RandomizeSkyBlocksProcessor())
/* 679 */       .func_215222_a((StructureProcessor)new ReplaceWaterloggedStructureProcessor())
/* 680 */       .func_215222_a((StructureProcessor)new IntegrityProcessor(1.0F)).func_189950_a(new Random(Util.func_211177_b()));
/*     */     
/* 682 */     WyHelper.loadNBTStructure((ServerWorld)player.field_70170_p, "unaligned/medic_tent", player.func_233580_cy_(), placement);
/* 683 */     player.field_70170_p.func_180501_a(player.func_233580_cy_(), Blocks.field_150357_h.func_176223_P(), 3);
/* 684 */     player.func_145747_a((ITextComponent)new StringTextComponent("Done"), Util.field_240973_b_);
/* 685 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int saveStructure(CommandContext<CommandSource> context, BlockPos from, BlockPos to, String name, ServerPlayerEntity player) {
/* 690 */     for (int i = from.func_177958_n(); i < to.func_177958_n(); i++) {
/*     */       
/* 692 */       for (int j = from.func_177956_o(); j < to.func_177956_o(); j++) {
/*     */         
/* 694 */         for (int k = from.func_177952_p(); k < to.func_177952_p(); k++) {
/*     */           
/* 696 */           BlockPos pos = new BlockPos(i, j, k);
/* 697 */           BlockState state = player.field_70170_p.func_180495_p(pos);
/*     */           
/* 699 */           if (state.func_235901_b_((Property)BlockStateProperties.field_208198_y)) {
/*     */             
/* 701 */             state = (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false));
/* 702 */             player.field_70170_p.func_175656_a(pos, state);
/*     */           } 
/*     */           
/* 705 */           if (state.func_177230_c() == Blocks.field_189881_dj) {
/* 706 */             player.field_70170_p.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 18);
/*     */           }
/*     */           
/* 709 */           if (state.func_177230_c() == Blocks.field_222422_lK) {
/*     */             
/* 711 */             Direction dir = (Direction)state.func_177229_b((Property)BlockStateProperties.field_208155_H);
/* 712 */             BlockState newState = ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P();
/* 713 */             newState = (BlockState)newState.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)dir);
/* 714 */             player.field_70170_p.func_175656_a(pos, newState);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 719 */     if (player.field_70170_p instanceof ServerWorld) {
/*     */       
/* 721 */       BlockPos size = to.func_177973_b((Vector3i)from);
/* 722 */       System.out.println("From: /tp " + from.func_177958_n() + " " + from.func_177956_o() + " " + from.func_177952_p());
/* 723 */       System.out.println("To: /tp " + to.func_177958_n() + " " + to.func_177956_o() + " " + to.func_177952_p());
/* 724 */       System.out.println("size: " + size);
/* 725 */       List<Block> bannedBlocks = new ArrayList<>();
/* 726 */       bannedBlocks.add(Blocks.field_150355_j);
/* 727 */       WyHelper.saveNBTStructure((ServerWorld)player.field_70170_p, name, from, size, bannedBlocks);
/*     */       
/* 729 */       player.func_145747_a((ITextComponent)new StringTextComponent("Done"), Util.field_240973_b_);
/*     */     } 
/* 731 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkBounties(CommandContext<CommandSource> source) {
/* 736 */     ServerWorld serverWorld = ((CommandSource)source.getSource()).func_197023_e();
/* 737 */     ExtendedWorldData worldData = ExtendedWorldData.get((IWorld)serverWorld);
/* 738 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 740 */     for (Map.Entry<UUID, Long> entry : (Iterable<Map.Entry<UUID, Long>>)worldData.getAllBounties().entrySet()) {
/*     */       
/* 742 */       UUID uuid = entry.getKey();
/* 743 */       String displayName = null;
/* 744 */       for (Entity entity : serverWorld.func_241136_z_()) {
/* 745 */         if (entity instanceof LivingEntity && entity.func_110124_au().equals(uuid)) {
/* 746 */           displayName = entity.func_145748_c_().getString();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 751 */       if (displayName == null) {
/* 752 */         for (TrackedNPC npc : NPCWorldData.get().getTrackedMobs()) {
/* 753 */           if (npc.getUUID().equals(uuid)) {
/* 754 */             displayName = "Notorious Target #" + npc.getId();
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 759 */       if (displayName != null) {
/* 760 */         sb.append(displayName + " " + entry.getValue() + "\n");
/*     */       }
/*     */     } 
/* 763 */     ((CommandSource)source.getSource()).func_197030_a((ITextComponent)new StringTextComponent(sb.toString()), true);
/*     */     
/* 765 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int turnSwordInClone(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 770 */     ItemStack heldStack = player.func_184586_b(Hand.MAIN_HAND);
/*     */     
/* 772 */     if (!heldStack.func_190926_b()) {
/* 773 */       heldStack.func_196082_o().func_74757_a("isClone", true);
/*     */     }
/*     */     
/* 776 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkHakiStats(CommandContext<CommandSource> context, ServerPlayerEntity target) {
/* 781 */     IHakiData props = HakiDataCapability.get((LivingEntity)target);
/*     */     
/* 783 */     target.func_145747_a((ITextComponent)new StringTextComponent("Busoshoku: " + props.getBusoshokuHakiExp()), Util.field_240973_b_);
/* 784 */     target.func_145747_a((ITextComponent)new StringTextComponent("Observation: " + props.getKenbunshokuHakiExp()), Util.field_240973_b_);
/* 785 */     target.func_145747_a((ITextComponent)new StringTextComponent("Haoshoku (Total): " + props.getTotalHakiExp()), Util.field_240973_b_);
/* 786 */     target.func_145747_a((ITextComponent)new StringTextComponent("========================="), Util.field_240973_b_);
/*     */     
/* 788 */     if (WyDebug.isDebug()) {
/* 789 */       props.setHakiOveruse(0);
/*     */     }
/*     */     
/* 792 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\FGCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */