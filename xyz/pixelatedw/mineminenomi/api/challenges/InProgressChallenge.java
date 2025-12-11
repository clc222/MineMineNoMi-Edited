/*     */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.STitlePacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextComponentUtils;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.world.ForgeChunkManager;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import xyz.pixelatedw.mineminenomi.ModMain;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class InProgressChallenge {
/*     */   private static final int COUNTDOWN_SECONDS = 10;
/*  63 */   private static final String[] LOADING_FRAMES = new String[] { "o o o", "O o o", "o O o", "o o O", "o o o" }; private static final int COUNTDOWN_TICKS = 200;
/*  64 */   private static final Marker MARKER = MarkerManager.getMarker("IP_CHALLENGE");
/*     */   
/*  66 */   private static final BlockProtectionRule REPAIR_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0])).setForced().build();
/*     */   
/*     */   private final UUID id;
/*     */   private float inProgressTick;
/*  70 */   private Interval readyInterval = (Interval)Interval.startAtMax(10).trackTPS();
/*  71 */   private Interval endWaitInterval = (Interval)Interval.startAtMax(10).trackTPS();
/*  72 */   private Interval.Mutable tickInterval = (Interval.Mutable)(new Interval.Mutable(5)).trackTPS();
/*  73 */   private Phase phase = Phase.BUILD;
/*  74 */   private Result result = Result.TBD;
/*     */   private ServerPlayerEntity owner;
/*     */   private BlockPos returnPosition;
/*     */   private RegistryKey<World> returnDimension;
/*     */   private ServerWorld shard;
/*     */   private Challenge challenge;
/*     */   private ChallengeCore.ArenaEntry arenaEntry;
/*     */   private BlockPos pos;
/*     */   private List<LivingEntity> group;
/*  83 */   private HashMap<UUID, ChallengeCache> groupCache = new HashMap<>();
/*  84 */   private Set<LivingEntity> enemies = new HashSet<>();
/*     */   
/*     */   private int tickLimit;
/*     */   
/*     */   private long startTick;
/*     */   private long finishTick;
/*     */   private ChallengeCore<?> core;
/*     */   private boolean isFree = false;
/*     */   private boolean isCompleted = false;
/*     */   private Set<BlockPos> blocksPlaced;
/*     */   private IChallengesData ownerProps;
/*     */   private Random random;
/*     */   private ChallengeTeleporter teleporter;
/*     */   private BlockQueue blockQueue;
/*     */   private long startBuildTime;
/*     */   private int totalBlocksPlaced;
/* 100 */   private float deltaTime = 0.0F;
/*     */   
/* 102 */   private Set<BlockPos> trackedManuallyPlacedBlocks = new HashSet<>();
/*     */   
/*     */   public InProgressChallenge(UUID id, ServerPlayerEntity owner, ServerWorld shard, List<LivingEntity> group, Challenge challenge, boolean isFree) {
/* 105 */     this.id = id;
/* 106 */     this.owner = owner;
/* 107 */     this.returnPosition = this.owner.func_233580_cy_();
/* 108 */     this.returnDimension = this.owner.func_71121_q().func_234923_W_();
/* 109 */     this.challenge = challenge;
/* 110 */     this.core = challenge.getCore();
/* 111 */     this.shard = shard;
/* 112 */     this.arenaEntry = this.core.pickRandomArena();
/* 113 */     this.pos = new BlockPos(0, 90, 0);
/* 114 */     this.group = new ArrayList<>();
/* 115 */     this.group.add(owner);
/* 116 */     this.group.addAll(group);
/* 117 */     this.isFree = isFree;
/* 118 */     this.ownerProps = ChallengesDataCapability.get((PlayerEntity)this.owner);
/* 119 */     this.random = new Random();
/* 120 */     this.teleporter = new ChallengeTeleporter(this);
/* 121 */     this.blockQueue = (new BlockQueue(this.shard)).setSpeed(CommonConfig.INSTANCE.getArenaGenerationSpeed());
/*     */     
/* 123 */     this.tickLimit = challenge.getCore().getTimeLimit() * 60 * 20;
/* 124 */     this.inProgressTick = 0.0F;
/*     */     
/* 126 */     this.tickInterval.restartIntervalToMax();
/* 127 */     this.readyInterval.restartIntervalToMax();
/* 128 */     this.endWaitInterval.restartIntervalToMax();
/*     */   }
/*     */   
/*     */   public void tick() {
/* 132 */     this.deltaTime = TPSDelta.INSTANCE.getDeltaTime();
/* 133 */     this.inProgressTick += this.deltaTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     if ((this.phase == Phase.RUN || this.phase == Phase.END) && 
/* 140 */       this.group.isEmpty()) {
/* 141 */       wipeOldEntities(false);
/* 142 */       this.phase = Phase.POSTEND;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 147 */     if (!this.tickInterval.canTick()) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     if (this.phase.equals(Phase.POSTEND)) {
/* 152 */       executePostEndPhase();
/*     */     }
/* 154 */     else if (this.phase.equals(Phase.END)) {
/* 155 */       executeEndPhase();
/*     */     }
/* 157 */     else if (this.phase.equals(Phase.RUN)) {
/* 158 */       executeRunPhase();
/*     */     }
/* 160 */     else if (this.phase.equals(Phase.BUILD)) {
/* 161 */       executeBuildPhase();
/*     */     }
/* 163 */     else if (this.phase.equals(Phase.SPAWN)) {
/* 164 */       executeSpawnPhase();
/*     */     }
/* 166 */     else if (this.phase.equals(Phase.READY)) {
/* 167 */       executeReadyPhase();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeBuildPhase() {
/* 172 */     if (!this.blockQueue.isRunning()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 181 */       ForgeChunkManager.forceChunk(this.shard, "mineminenomi", this.pos, 0, 0, true, false);
/*     */ 
/*     */       
/* 184 */       if (this.ownerProps.isArenaDirty()) {
/* 185 */         long cleanStart = System.nanoTime();
/*     */         
/* 187 */         ChallengeArena previousArena = this.arenaEntry.arena;
/* 188 */         MutableBoundingBox previousArenBB = MutableBoundingBox.func_175899_a(-100, -100, -100, 100, 100, 100);
/* 189 */         if (this.ownerProps.getPreviousChallenge() != null) {
/* 190 */           ChallengeCore.ArenaEntry entry = this.ownerProps.getPreviousChallenge().getArenaFromStyle(this.ownerProps
/* 191 */               .getPreviousArenaStyle(), this.ownerProps
/* 192 */               .getPreviousArenaClass());
/*     */           
/* 194 */           if (entry != null) {
/* 195 */             previousArena = entry.arena;
/*     */           }
/*     */         } 
/*     */         
/* 199 */         if (previousArena != null) {
/* 200 */           previousArenBB = previousArena.getArenaLimits();
/*     */         }
/*     */         
/* 203 */         if (previousArena == null) {
/* 204 */           previousArena = this.arenaEntry.arena;
/*     */         }
/*     */         
/* 207 */         int x0 = getArenaPos().func_177958_n() + previousArenBB.field_78897_a * 2;
/* 208 */         int y0 = getArenaPos().func_177956_o() + previousArenBB.field_78895_b * 2;
/* 209 */         int z0 = getArenaPos().func_177952_p() + previousArenBB.field_78896_c * 2;
/* 210 */         int x1 = getArenaPos().func_177958_n() + previousArenBB.field_78893_d * 2;
/* 211 */         int y1 = getArenaPos().func_177956_o() + previousArenBB.field_78894_e * 2;
/* 212 */         int z1 = getArenaPos().func_177952_p() + previousArenBB.field_78892_f * 2;
/*     */         
/* 214 */         StructuresHelper.fillCube((World)this.shard, new BlockPos(x0, y0, z0), new BlockPos(x1, y1, z1), Blocks.field_150350_a.func_176223_P(), 512, REPAIR_RULE);
/*     */ 
/*     */         
/* 217 */         long cleanEnd = (System.nanoTime() - cleanStart) / 1000000L;
/* 218 */         ModMain.LOGGER.debug(MARKER, "EMERGENCY CLEAR TIME: " + ((float)cleanEnd / 1000.0F) + "s");
/*     */       } 
/*     */       
/* 221 */       this.trackedManuallyPlacedBlocks.clear();
/*     */       
/* 223 */       this.startBuildTime = this.shard.func_82737_E();
/*     */       
/* 225 */       this.arenaEntry.arena.buildArena(this, this.blockQueue);
/* 226 */       this.totalBlocksPlaced = this.blockQueue.getQueueSize();
/* 227 */       ModMain.LOGGER.debug(MARKER, "Size: " + this.blockQueue.getQueueSize() + " blocks");
/*     */       
/* 229 */       this.blockQueue.start();
/*     */     }
/*     */     else {
/*     */       
/* 233 */       if (this.blockQueue.isDone()) {
/* 234 */         sendGroupActionbar((ITextComponent)new StringTextComponent("Starting Challenge"), 5, 20, 5);
/*     */         
/* 236 */         float f = (float)(this.shard.func_82737_E() - this.startBuildTime) / 20.0F;
/* 237 */         ModMain.LOGGER.debug(MARKER, "BUILD TIME: " + f + "s");
/*     */         
/* 239 */         this.ownerProps.markArenaDirty(true);
/* 240 */         this.ownerProps.setPreviousChallenge(this.core, this.arenaEntry.arena.getStyle(), this.arenaEntry.arena.getClass().getName());
/*     */         
/* 242 */         this.phase = Phase.SPAWN;
/* 243 */         this.tickInterval.setInterval(100);
/*     */         
/* 245 */         this.blockQueue.stop();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 250 */       this.blockQueue.tick();
/*     */       
/* 252 */       StringBuilder builder = new StringBuilder("Generating Arena - ");
/*     */ 
/*     */       
/* 255 */       long buildEnd = (this.shard.func_82737_E() - this.startBuildTime) / 20L;
/* 256 */       builder.append(buildEnd + "s - ");
/* 257 */       float complation = (1.0F - this.blockQueue.getQueueSize() / this.totalBlocksPlaced) * 100.0F;
/* 258 */       builder.append(String.format("%.1f", new Object[] { Float.valueOf(complation) }) + "%");
/* 259 */       sendGroupActionbar((ITextComponent)new StringTextComponent(builder.toString()), 5, 20, 5);
/*     */     } 
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
/*     */   private void executeSpawnPhase() {
/* 273 */     wipeOldEntities(true);
/*     */ 
/*     */     
/* 276 */     spawnChallengers();
/*     */ 
/*     */     
/* 279 */     spawnEnemies();
/*     */     
/* 281 */     this.phase = Phase.READY;
/* 282 */     this.tickInterval.setInterval(20);
/*     */   }
/*     */   
/*     */   private void executeReadyPhase() {
/* 286 */     if (this.readyInterval.getTick() <= 10) {
/* 287 */       StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + "" + (this.readyInterval.getTick() - 1));
/* 288 */       sendGroupTitle((ITextComponent)stringTextComponent, 5, (int)Math.ceil((10.0F * (1.0F + this.deltaTime))), 5);
/*     */     } 
/*     */     
/* 291 */     if (this.readyInterval.canTick()) {
/* 292 */       this.phase = Phase.RUN;
/* 293 */       this.group.forEach(entity -> entity.func_195063_d((Effect)ModEffects.IN_EVENT.get()));
/* 294 */       this.enemies.forEach(entity -> entity.func_195063_d((Effect)ModEffects.IN_EVENT.get()));
/* 295 */       sendGroupTitle((ITextComponent)ModI18n.CHALLENGE_MESSAGE_START_FIGHT, 2, (int)Math.ceil((5.0F * (1.0F + this.deltaTime))), 2);
/* 296 */       this.startTick = this.shard.func_82737_E();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeRunPhase() {
/* 301 */     if (this.inProgressTick > this.tickLimit) {
/* 302 */       this.phase = Phase.END;
/* 303 */       this.result = Result.TIMEOUT;
/* 304 */       this.tickInterval.setInterval(20);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 309 */     int groupAlive = 0;
/* 310 */     for (LivingEntity groupMember : this.group) {
/* 311 */       if (groupMember.func_70644_a((Effect)ModEffects.CHALLENGE_FAILED.get())) {
/* 312 */         despawnChallenger(groupMember);
/*     */         
/*     */         continue;
/*     */       } 
/* 316 */       if (groupMember.func_70089_S() && WyHelper.isInChallengeDimension(groupMember.field_70170_p)) {
/* 317 */         groupAlive++;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 323 */     if (groupAlive <= 0) {
/* 324 */       this.group.clear();
/* 325 */       this.phase = Phase.END;
/* 326 */       this.result = Result.DEATH;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 332 */     if (!this.enemies.isEmpty()) {
/* 333 */       this.enemies.removeIf(entity -> (entity == null || !entity.func_70089_S()));
/*     */     }
/* 335 */     else if (this.enemies.isEmpty()) {
/* 336 */       this.phase = Phase.END;
/* 337 */       this.result = Result.WIN;
/* 338 */       this.finishTick = this.shard.func_82737_E();
/* 339 */       this.tickInterval.setInterval(20);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executePostEndPhase() {
/* 345 */     if (this.blockQueue.isRunning()) {
/* 346 */       if (this.blockQueue.isDone()) {
/* 347 */         this.blockQueue.stop();
/*     */       }
/*     */       
/* 350 */       this.blockQueue.tick();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void executeEndPhase() {
/* 355 */     if (this.endWaitInterval.canTick()) {
/* 356 */       this.phase = Phase.POSTEND;
/*     */ 
/*     */       
/* 359 */       long cleanStart = System.nanoTime();
/*     */       
/* 361 */       MutableBoundingBox previousArenBB = MutableBoundingBox.func_175899_a(-50, -50, -50, 50, 50, 50);
/*     */       
/* 363 */       int x0 = getArenaPos().func_177958_n() + previousArenBB.field_78897_a * 2;
/* 364 */       int y0 = getArenaPos().func_177956_o() + previousArenBB.field_78895_b * 2;
/* 365 */       int z0 = getArenaPos().func_177952_p() + previousArenBB.field_78896_c * 2;
/* 366 */       int x1 = getArenaPos().func_177958_n() + previousArenBB.field_78893_d * 2;
/* 367 */       int y1 = getArenaPos().func_177956_o() + previousArenBB.field_78894_e * 2;
/* 368 */       int z1 = getArenaPos().func_177952_p() + previousArenBB.field_78892_f * 2;
/*     */       
/* 370 */       StructuresHelper.fillCube((World)this.shard, new BlockPos(x0, y0, z0), new BlockPos(x1, y1, z1), Blocks.field_150350_a.func_176223_P(), 512, REPAIR_RULE);
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
/* 392 */       long cleanEnd = (System.nanoTime() - cleanStart) / 1000000L;
/* 393 */       ModMain.LOGGER.debug(MARKER, "CLEAR TIME: " + ((float)cleanEnd / 1000.0F) + "s");
/*     */       
/* 395 */       ForgeChunkManager.forceChunk(this.shard, "mineminenomi", this.pos, 0, 0, false, false);
/*     */ 
/*     */       
/* 398 */       for (LivingEntity entity : getGroup()) {
/* 399 */         despawnChallenger(entity);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 405 */       if (this.result == Result.WIN && !this.isCompleted) {
/* 406 */         completeChallenge();
/* 407 */         this.isCompleted = true;
/*     */       } 
/*     */       
/* 410 */       this.groupCache.clear();
/*     */       
/* 412 */       this.ownerProps.markArenaDirty(false);
/*     */       
/* 414 */       ChallengesWorldData.get().stopChallenge(this);
/*     */     } else {
/*     */       
/* 417 */       StringTextComponent stringTextComponent = new StringTextComponent("§f§l" + (new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_END_COUNTDOWN, new Object[] { Integer.valueOf(this.endWaitInterval.getTick()) })).getString() + "§r");
/* 418 */       sendGroupActionbar((ITextComponent)stringTextComponent, 5, Math.round(20.0F * (1.0F + this.deltaTime)), 5);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void completeChallenge() {
/* 423 */     for (LivingEntity entity : getGroup()) {
/* 424 */       if (entity.func_70089_S() && entity instanceof ServerPlayerEntity) {
/* 425 */         ServerPlayerEntity player = (ServerPlayerEntity)entity;
/* 426 */         IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/* 427 */         if (props == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 431 */         Challenge challenge = props.getChallenge(getCore());
/* 432 */         if (challenge == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 436 */         int time = Math.round((float)(this.finishTick - this.startTick) / 20.0F);
/*     */         
/* 438 */         String mode = "";
/* 439 */         if (hasRestrictions() && !hasActiveRestrictions()) {
/* 440 */           mode = " (TRAINING)";
/*     */         }
/* 442 */         else if (challenge.isPersonalBest(time)) {
/* 443 */           mode = " (PB)";
/*     */         } 
/*     */         
/* 446 */         String timeStr = String.format("§2§l%02d:%02d§r%s", new Object[] { Integer.valueOf(time / 60), Integer.valueOf(time % 60), mode });
/*     */         
/* 448 */         String rewardsMessage = "";
/* 449 */         if (hasRewards()) {
/* 450 */           rewardsMessage = challenge.getRewards((PlayerEntity)player);
/* 451 */           challenge.tryUpdateBestTime(time);
/* 452 */           challenge.setComplete((PlayerEntity)player, true);
/*     */         } 
/*     */         
/* 455 */         StringTextComponent reportStr = new StringTextComponent(ModI18n.CHALLENGE_MESSAGE_COMPLETION_REPORT.getString() + "" + timeStr);
/*     */         
/* 457 */         player.func_145747_a((ITextComponent)this.core.getLocalizedTitle(), Util.field_240973_b_);
/* 458 */         player.func_145747_a((ITextComponent)reportStr, Util.field_240973_b_);
/* 459 */         if (rewardsMessage != null) {
/* 460 */           player.func_145747_a((ITextComponent)new StringTextComponent(rewardsMessage), Util.field_240973_b_);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasRewards() {
/* 467 */     return isStandardDifficulty() ? true : hasActiveRestrictions();
/*     */   }
/*     */   
/*     */   public boolean hasRestrictions() {
/* 471 */     return !isStandardDifficulty();
/*     */   }
/*     */   
/*     */   public boolean hasActiveRestrictions() {
/* 475 */     return !this.isFree;
/*     */   }
/*     */   
/*     */   public boolean canDelete() {
/* 479 */     return (this.phase == Phase.POSTEND);
/*     */   }
/*     */   
/*     */   public boolean isBuilding() {
/* 483 */     return (this.phase == Phase.BUILD || this.phase == Phase.POSTEND);
/*     */   }
/*     */   
/*     */   public void despawnChallenger(LivingEntity entity) {
/* 487 */     if (entity.func_70089_S() && WyHelper.isInChallengeDimension(entity.field_70170_p) && entity instanceof ServerPlayerEntity) {
/* 488 */       ServerPlayerEntity player = (ServerPlayerEntity)entity;
/*     */ 
/*     */       
/* 491 */       ChallengeCache cache = this.groupCache.get(player.func_110124_au());
/* 492 */       if (cache != null) {
/* 493 */         cache.restore(entity);
/*     */       }
/*     */       
/* 496 */       AbilityHelper.disableAbilities((LivingEntity)player, 10, (Predicate)Predicates.alwaysTrue());
/*     */       
/* 498 */       player.field_70143_R = 0.0F;
/*     */       
/* 500 */       player.func_195061_cb();
/* 501 */       player.func_241209_g_(0);
/*     */       
/* 503 */       player.func_195063_d((Effect)ModEffects.CHALLENGE_FAILED.get());
/* 504 */       this.teleporter.teleportToHomeWorld((Entity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnEnemies() {
/* 509 */     Set<ChallengeArena.EnemySpawn> positions = this.challenge.getCore().getEnemySpawns().getEnemySpawns(this, this.arenaEntry.enemyPosition.getEnemySpawnPos(this));
/*     */     
/* 511 */     this.enemies = (Set<LivingEntity>)positions.stream().map(ChallengeArena.EnemySpawn::getEntity).collect(Collectors.toSet());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 517 */     for (ChallengeArena.EnemySpawn spawnPoint : positions) {
/* 518 */       LivingEntity entity = spawnPoint.getEntity();
/* 519 */       entity.field_98038_p = true;
/* 520 */       getShard().func_217376_c((Entity)entity);
/* 521 */       entity.func_70634_a(spawnPoint.getSpawnPos().getPos().func_177958_n(), spawnPoint.getSpawnPos().getPos().func_177956_o(), spawnPoint.getSpawnPos().getPos().func_177952_p());
/* 522 */       entity.field_70177_z = spawnPoint.getSpawnPos().getYaw();
/* 523 */       entity.field_70125_A = spawnPoint.getSpawnPos().getPitch();
/* 524 */       if (entity instanceof MobEntity) {
/* 525 */         ((MobEntity)entity).func_70624_b((LivingEntity)getOwner());
/* 526 */         GoalUtil.lookAtEntity((MobEntity)entity, (Entity)getOwner());
/*     */       } 
/*     */       
/* 529 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.IN_EVENT.get(), 9999, 0));
/* 530 */       entity.func_70691_i(entity.func_110138_aP());
/*     */     } 
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
/*     */   private void spawnChallengers() {
/* 543 */     for (Iterator<LivingEntity> iterator = this.group.iterator(); iterator.hasNext(); ) { LivingEntity entity = iterator.next();
/* 544 */       if (entity instanceof ServerPlayerEntity) {
/* 545 */         ServerPlayerEntity player = (ServerPlayerEntity)entity;
/*     */         
/* 547 */         this.teleporter.teleportToChallengeWorld((Entity)entity);
/*     */ 
/*     */         
/* 550 */         if (CommonConfig.INSTANCE.isChallengesCachingEnabled()) {
/* 551 */           this.groupCache.put(entity.func_110124_au(), ChallengeCache.from((LivingEntity)player));
/*     */         }
/*     */       } 
/*     */       
/* 555 */       entity.func_70691_i(entity.func_110138_aP());
/* 556 */       if (entity instanceof PlayerEntity) {
/* 557 */         ((PlayerEntity)entity).func_71024_bL().func_75122_a(20, 20.0F);
/*     */       }
/* 559 */       entity.func_195061_cb();
/* 560 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.IN_EVENT.get(), 9999, 0));
/*     */       
/* 562 */       IAbilityData props = AbilityDataCapability.get(entity);
/* 563 */       IHakiData hakiProps = HakiDataCapability.get(entity);
/*     */       
/* 565 */       for (IAbility ability : props.getEquippedAbilities()) {
/* 566 */         if (ability == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 570 */         ability.getComponent(ModAbilityKeys.DISABLE).ifPresent(c -> c.startDisable(entity, 1.0F));
/* 571 */         ability.getComponent(ModAbilityKeys.DISABLE).ifPresent(c -> c.stopDisable(entity));
/* 572 */         ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(c -> c.stopCooldown(entity));
/*     */       } 
/*     */       
/* 575 */       hakiProps.setHakiOveruse(0); }
/*     */ 
/*     */     
/* 578 */     this.shard.func_72966_v();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wipeOldEntities(boolean force) {
/* 585 */     this.shard.getEntities().forEach(entity -> {
/*     */           if (!(entity instanceof PlayerEntity)) {
/*     */             entity.func_70106_y();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void sendGroupTitle(ITextComponent title, int fadeInTime, int stayTime, int fadeOutTime) {
/* 593 */     sendGroupTitle(title, null, fadeInTime, stayTime, fadeOutTime);
/*     */   }
/*     */   
/*     */   private void sendGroupTitle(ITextComponent title, @Nullable ITextComponent subtitle, int fadeInTime, int stayTime, int fadeOutTime) {
/* 597 */     for (LivingEntity groupMember : this.group) {
/* 598 */       if (groupMember instanceof ServerPlayerEntity) {
/* 599 */         ServerPlayerEntity player = (ServerPlayerEntity)groupMember;
/*     */         try {
/* 601 */           player.field_71135_a.func_147359_a((IPacket)new STitlePacket(fadeInTime, stayTime, fadeOutTime));
/* 602 */           IFormattableTextComponent iFormattableTextComponent = TextComponentUtils.func_240645_a_(player.func_195051_bN(), title, (Entity)player, 0);
/* 603 */           player.field_71135_a.func_147359_a((IPacket)new STitlePacket(STitlePacket.Type.TITLE, (ITextComponent)iFormattableTextComponent));
/*     */           
/* 605 */           if (subtitle != null) {
/* 606 */             IFormattableTextComponent iFormattableTextComponent1 = TextComponentUtils.func_240645_a_(player.func_195051_bN(), subtitle, (Entity)player, 0);
/* 607 */             player.field_71135_a.func_147359_a((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, (ITextComponent)iFormattableTextComponent1));
/*     */           }
/*     */         
/* 610 */         } catch (Exception e) {
/* 611 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void sendGroupActionbar(ITextComponent text, int fadeInTime, int stayTime, int fadeOutTime) {
/* 618 */     for (LivingEntity groupMember : this.group) {
/* 619 */       if (groupMember.func_70089_S() && groupMember instanceof ServerPlayerEntity) {
/* 620 */         ServerPlayerEntity player = (ServerPlayerEntity)groupMember;
/*     */         try {
/* 622 */           player.field_71135_a.func_147359_a((IPacket)new STitlePacket(fadeInTime, stayTime, fadeOutTime));
/* 623 */           IFormattableTextComponent iFormattableTextComponent = TextComponentUtils.func_240645_a_(player.func_195051_bN(), text, (Entity)player, 0);
/* 624 */           player.field_71135_a.func_147359_a((IPacket)new STitlePacket(STitlePacket.Type.ACTIONBAR, (ITextComponent)iFormattableTextComponent));
/*     */         }
/* 626 */         catch (Exception e) {
/* 627 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isStandardDifficulty() {
/* 634 */     return (this.core.getDifficulty() == ChallengeDifficulty.STANDARD);
/*     */   }
/*     */   
/*     */   public boolean isHardDifficulty() {
/* 638 */     return (this.core.getDifficulty() == ChallengeDifficulty.HARD);
/*     */   }
/*     */   
/*     */   public boolean isUltimateDifficulty() {
/* 642 */     return (this.core.getDifficulty() == ChallengeDifficulty.ULTIMATE);
/*     */   }
/*     */   
/*     */   public BlockPos getArenaPos() {
/* 646 */     return this.pos;
/*     */   }
/*     */   
/*     */   public UUID getId() {
/* 650 */     return this.id;
/*     */   }
/*     */   
/*     */   public ChallengeCore getCore() {
/* 654 */     return this.core;
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getGroup() {
/* 658 */     return this.group;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, ChallengeCache> getGroupCache() {
/* 662 */     return this.groupCache;
/*     */   }
/*     */   
/*     */   public Set<LivingEntity> getEnemies() {
/* 666 */     return this.enemies;
/*     */   }
/*     */   
/*     */   public ServerPlayerEntity getOwner() {
/* 670 */     return this.owner;
/*     */   }
/*     */   
/*     */   public ServerWorld getShard() {
/* 674 */     return this.shard;
/*     */   }
/*     */   
/*     */   public ChallengeCore.IChallengerPosition getChallengerPosition() {
/* 678 */     return this.arenaEntry.challengerPosition;
/*     */   }
/*     */   
/*     */   public ChallengeArena getArena() {
/* 682 */     return this.arenaEntry.arena;
/*     */   }
/*     */   
/*     */   public Random getRNG() {
/* 686 */     return this.random;
/*     */   }
/*     */   
/*     */   public RegistryKey<World> getReturnDimension() {
/* 690 */     return this.returnDimension;
/*     */   }
/*     */   
/*     */   public BlockPos getReturnPosition() {
/* 694 */     return this.returnPosition;
/*     */   }
/*     */   
/*     */   public void trackBlockPos(BlockPos pos) {
/* 698 */     this.trackedManuallyPlacedBlocks.add(pos);
/*     */   }
/*     */   
/*     */   public void trackBlockPos(Set<BlockPos> pos) {
/* 702 */     this.trackedManuallyPlacedBlocks.addAll(pos);
/*     */   }
/*     */   
/*     */   private enum Phase {
/* 706 */     BUILD,
/* 707 */     SPAWN,
/* 708 */     READY,
/* 709 */     RUN,
/* 710 */     END,
/* 711 */     POSTEND;
/*     */   }
/*     */   
/*     */   private enum Result
/*     */   {
/* 716 */     TBD,
/* 717 */     WIN,
/* 718 */     TIMEOUT,
/* 719 */     DEATH;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\InProgressChallenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */