/*     */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BlackLegPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.ServerOPBossInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhaseManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.HumanoidSwimMoveController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public abstract class OPBossEntity<E extends OPBossEntity> extends CreatureEntity implements IChallengeBoss, IEntityAdditionalSpawnData {
/*  42 */   private final ServerOPBossInfo bossInfo = new ServerOPBossInfo(func_145748_c_());
/*  43 */   private final ChallengeInfo challengeInfo = new ChallengeInfo();
/*  44 */   private final NPCPhaseManager phaseManager = new NPCPhaseManager((MobEntity)this);
/*  45 */   protected final IEntityStats entityStats = EntityStatsCapability.get((LivingEntity)this);
/*  46 */   protected final IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)this);
/*  47 */   protected final IDevilFruit devilFruitData = DevilFruitCapability.get((LivingEntity)this);
/*  48 */   protected final IHakiData hakiCapability = HakiDataCapability.get((LivingEntity)this);
/*     */   
/*     */   private boolean isInitialized;
/*     */   private float damageCeiling;
/*     */   private boolean hasEmptyTargetsList;
/*  53 */   private int movementType = 0;
/*     */   
/*     */   private BlockPos lastSafePosition;
/*  56 */   private MovementController groundMovementController = new MovementController((MobEntity)this);
/*  57 */   private MovementController waterMovementController = (MovementController)new HumanoidSwimMoveController((MobEntity)this);
/*     */   
/*     */   public OPBossEntity(EntityType type, World world) {
/*  60 */     this(type, world, (InProgressChallenge)null);
/*     */   }
/*     */   
/*     */   public OPBossEntity(EntityType type, InProgressChallenge inProgressChallenge) {
/*  64 */     this(type, (World)inProgressChallenge.getShard(), inProgressChallenge);
/*     */   }
/*     */   
/*     */   private OPBossEntity(EntityType type, World world, @Nullable InProgressChallenge inProgressChallenge) {
/*  68 */     super(type, world);
/*     */     
/*  70 */     preInit();
/*  71 */     if (world != null && !world.field_72995_K) {
/*  72 */       this.challengeInfo.setInProgressChallenge(inProgressChallenge);
/*     */       
/*  74 */       setDamageCeiling(isDifficultyStandard() ? 20.0F : 30.0F);
/*     */       
/*  76 */       applyDifficultyModifiers((LivingEntity)this);
/*  77 */       if (this.entityStats.isBrawler()) {
/*  78 */         this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*     */       }
/*  80 */       else if (this.entityStats.isBlackLeg()) {
/*  81 */         this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BlackLegPassiveBonusesAbility.INSTANCE));
/*     */       } 
/*  83 */       AttributeHelper.updateToughnessAttribute((LivingEntity)this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void preInit() {}
/*     */   
/*     */   public void initBoss() {}
/*     */   
/*     */   public void initStats() {}
/*     */   
/*     */   public void initGoals() {}
/*     */   
/*     */   public void func_70071_h_() {
/*  97 */     if (!this.isInitialized) {
/*  98 */       if (!this.field_70170_p.field_72995_K) {
/*  99 */         initBoss();
/* 100 */         initStats();
/* 101 */         initGoals();
/*     */         
/* 103 */         func_70691_i(func_110138_aP());
/* 104 */         this.lastSafePosition = func_233580_cy_();
/*     */       } 
/* 106 */       this.isInitialized = true;
/*     */     } 
/*     */     
/* 109 */     this.bossInfo.tick((LivingEntity)this);
/* 110 */     super.func_70071_h_();
/*     */     
/* 112 */     if (!this.field_70170_p.field_72995_K) {
/* 113 */       trySwitchingMovementMode();
/*     */       
/* 115 */       if (this.field_70170_p.func_82737_E() % 20L == 0L) {
/* 116 */         boolean isSuffocating = this.field_70170_p.func_180495_p(func_233580_cy_()).func_229980_m_((IBlockReader)this.field_70170_p, func_233580_cy_());
/* 117 */         boolean isOutsideBounds = !AbilityHelper.isWithinChallengeArenaBounds(this.field_70170_p, func_233580_cy_());
/* 118 */         if (!isSuffocating) {
/* 119 */           this.lastSafePosition = func_233580_cy_();
/*     */         }
/* 121 */         else if (isSuffocating || isOutsideBounds) {
/* 122 */           func_174828_a(this.lastSafePosition, this.field_70177_z, this.field_70125_A);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       if (func_70638_az() == null && !this.hasEmptyTargetsList) {
/* 131 */         List<LivingEntity> challengers = this.challengeInfo.getChallengerGroup();
/* 132 */         if (challengers.isEmpty()) {
/* 133 */           this.hasEmptyTargetsList = true;
/*     */           
/*     */           return;
/*     */         } 
/* 137 */         for (LivingEntity target : challengers) {
/* 138 */           if (canTarget(target)) {
/* 139 */             func_70624_b(target);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 144 */       } else if (func_70638_az() != null) {
/* 145 */         LivingEntity target = func_70638_az();
/* 146 */         if (!canTarget(target)) {
/* 147 */           func_70624_b(null);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean canTarget(LivingEntity target) {
/* 154 */     if (target.func_70644_a((Effect)ModEffects.CHALLENGE_FAILED.get())) {
/* 155 */       return false;
/*     */     }
/*     */     
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   private void trySwitchingMovementMode() {
/* 162 */     if (func_70090_H() && this.movementType == 0) {
/* 163 */       switchToWaterMovement();
/*     */     }
/* 165 */     else if (!func_70090_H() && this.movementType == 1) {
/* 166 */       switchToGroundMovement();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 172 */     func_82168_bl();
/* 173 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 178 */     this.phaseManager.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184178_b(ServerPlayerEntity player) {
/* 183 */     super.func_184178_b(player);
/* 184 */     if (this.bossInfo != null) {
/* 185 */       this.bossInfo.func_186760_a(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184203_c(ServerPlayerEntity player) {
/* 191 */     super.func_184203_c(player);
/* 192 */     if (this.bossInfo != null) {
/* 193 */       this.bossInfo.func_186761_b(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(boolean keepData) {
/* 199 */     if (this.entityStats.isPirate()) {
/* 200 */       ExtendedWorldData worldData = ExtendedWorldData.get();
/* 201 */       if (worldData != null) {
/* 202 */         Crew crew = worldData.getCrewWithMember(func_110124_au());
/* 203 */         if (crew != null) {
/* 204 */           crew.removeMember(this.field_70170_p, func_110124_au());
/*     */         }
/*     */       } 
/*     */     } 
/* 208 */     super.remove(keepData);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 213 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/* 214 */     func_184641_n(false);
/* 215 */     return spawnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 220 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {}
/*     */ 
/*     */   
/*     */   public ChallengeInfo getChallengeInfo() {
/* 233 */     return this.challengeInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCPhaseManager getPhaseManager() {
/* 238 */     return this.phaseManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 243 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */   
/*     */   public void setDamageCeiling(float damageCeiling) {
/* 247 */     this.damageCeiling = damageCeiling;
/*     */   }
/*     */   
/*     */   public float getDamageCeiling() {
/* 251 */     return this.damageCeiling;
/*     */   }
/*     */   
/*     */   public float getDifficultyScaling() {
/* 255 */     return this.challengeInfo.getScaling();
/*     */   }
/*     */   
/*     */   public void switchToWaterMovement() {
/* 259 */     this.field_70765_h = this.waterMovementController;
/* 260 */     this.movementType = 1;
/*     */   }
/*     */   
/*     */   public void switchToGroundMovement() {
/* 264 */     this.field_70765_h = this.groundMovementController;
/* 265 */     this.movementType = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\OPBossEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */