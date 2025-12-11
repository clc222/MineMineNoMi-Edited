/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ import java.util.Random;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhaseManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.MeleeRangedStyleSwitchGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KemuriBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.SakuretsuSabotenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TetsuBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TokuyoAburaBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SniperEntity extends OPEntity implements IRangedAttackMob, ICommandReceiver {
/*  54 */   private static final ResourceLocation[] STYLES = new ResourceLocation[] { ModValues.SNIPER };
/*     */   
/*  56 */   private final NPCPhaseManager phaseManager = new NPCPhaseManager((MobEntity)this);
/*     */   
/*     */   private NPCPhase<SniperEntity> meleePhase;
/*     */   private NPCPhase<SniperEntity> rangePhase;
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  62 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   private LivingEntity lastTarget;
/*     */   private boolean firstShot = true;
/*     */   
/*     */   public SniperEntity(EntityType type, World world, ResourceLocation faction) {
/*  68 */     super(type, world, (ResourceLocation[])null);
/*  69 */     ResourceLocation style = STYLES[func_70681_au().nextInt(STYLES.length)];
/*     */     
/*  71 */     if (world != null && !world.field_72995_K) {
/*  72 */       this.meleePhase = (NPCPhase<SniperEntity>)new SimplePhase("Melee Phase", (MobEntity)this);
/*  73 */       this.rangePhase = (NPCPhase<SniperEntity>)new SimplePhase("Range Phase", (MobEntity)this);
/*     */       
/*  75 */       getEntityStats().setFaction(faction);
/*  76 */       getEntityStats().setFightingStyle(style);
/*  77 */       getEntityStats().setRace(ModValues.HUMAN);
/*  78 */       setDetails();
/*     */       
/*  80 */       boolean isHardDifficulty = isAboveNormalDifficulty();
/*  81 */       setDoriki(2000.0D + WyHelper.randomWithRange(0, 1500) + (isHardDifficulty ? WyHelper.randomWithRange(0, 500) : 0.0D));
/*  82 */       setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*     */       
/*  84 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(60.0D);
/*  85 */       if (this.field_70170_p.func_175659_aa().func_151525_a() <= 2) {
/*  86 */         float mod = 1.0F;
/*  87 */         switch (this.field_70170_p.func_175659_aa()) {
/*     */           case EASY:
/*  89 */             mod = 2.0F;
/*     */             break;
/*     */           case NORMAL:
/*  92 */             mod = 1.25F;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*  97 */         ModifiableAttributeInstance attr = func_110148_a(Attributes.field_233819_b_);
/*  98 */         attr.func_111128_a(attr.func_111125_b() / mod);
/*     */       } 
/*     */       
/* 101 */       MobsHelper.addBasicNPCGoals(this);
/* 102 */       CommandAbility.addCommandGoals((MobEntity)this);
/*     */       
/* 104 */       this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/* 105 */       this.meleePhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/*     */       
/* 107 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 15);
/*     */       
/* 109 */       this.phaseManager.setPhase(this.rangePhase);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SniperEntity createMarineSniper(EntityType type, World world) {
/* 114 */     return new SniperEntity(type, world, ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static SniperEntity createMarineSniper(World world) {
/* 118 */     return new SniperEntity((EntityType)ModEntities.MARINE_SNIPER.get(), world, ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static SniperEntity createBanditSniper(EntityType type, World world) {
/* 122 */     return new SniperEntity(type, world, ModValues.BANDIT);
/*     */   }
/*     */   
/*     */   public static SniperEntity createBanditSniper(World world) {
/* 126 */     return new SniperEntity((EntityType)ModEntities.BANDIT_SNIPER.get(), world, ModValues.BANDIT);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 130 */     return OPEntity.createAttributes()
/* 131 */       .func_233815_a_(Attributes.field_233819_b_, 50.0D)
/* 132 */       .func_233815_a_(Attributes.field_233821_d_, 0.25D)
/* 133 */       .func_233815_a_(Attributes.field_233823_f_, WyHelper.randomWithRange(2, 4))
/* 134 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(30, 50))
/* 135 */       .func_233815_a_(Attributes.field_233826_i_, WyHelper.randomWithRange(1, 3));
/*     */   }
/*     */   
/*     */   private void onStyleSwitched(LivingEntity entity, boolean isRanged) {
/* 139 */     if (isRanged) {
/* 140 */       this.phaseManager.setPhase(this.rangePhase);
/*     */     } else {
/*     */       
/* 143 */       this.phaseManager.setPhase(this.meleePhase);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 149 */     this.phaseManager.tick();
/*     */   }
/*     */   
/*     */   public void func_82196_d(LivingEntity target, float velocity) {
/*     */     KairosekiBulletProjectile kairosekiBulletProjectile;
/* 154 */     if (func_184614_ca() == null || !func_184614_ca().func_77973_b().equals(ModWeapons.SENRIKU.get())) {
/*     */       return;
/*     */     }
/*     */     
/* 158 */     if (func_70638_az() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 162 */     if (this.lastTarget == null || this.lastTarget != func_70638_az()) {
/* 163 */       this.lastTarget = func_70638_az();
/* 164 */       this.firstShot = true;
/*     */     } 
/*     */     
/* 167 */     boolean isHardDifficulty = this.field_70170_p.func_175649_E(func_233580_cy_()).func_193845_a(Difficulty.NORMAL.ordinal());
/*     */     
/* 169 */     NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 170 */     normalBulletProjectile.setDamage(5.0F);
/* 171 */     if (isHardDifficulty) {
/* 172 */       kairosekiBulletProjectile = new KairosekiBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 173 */       kairosekiBulletProjectile.setDamage(7.0F);
/*     */     } 
/*     */     
/* 176 */     if (this.firstShot) {
/* 177 */       kairosekiBulletProjectile.setDamage(-1.0F);
/*     */     }
/*     */     
/* 180 */     float inaccuracy = Math.max(0.0F, 6.0F - this.field_70170_p.func_175659_aa().func_151525_a() * 2.5F);
/* 181 */     if (this.firstShot) {
/* 182 */       inaccuracy = 20.0F;
/*     */     }
/* 184 */     kairosekiBulletProjectile.func_234612_a_((Entity)this, this.field_70125_A, this.field_70177_z, 0.0F, 4.0F, inaccuracy);
/* 185 */     this.field_70170_p.func_217376_c((Entity)kairosekiBulletProjectile);
/*     */     
/* 187 */     this.field_70170_p.func_184133_a(null, func_233580_cy_(), (SoundEvent)ModSounds.SNIPER_SHOOT.get(), SoundCategory.PLAYERS, 3.0F, 0.75F + func_70681_au().nextFloat() / 4.0F);
/*     */     
/* 189 */     this.firstShot = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225511_J_() {
/* 194 */     return true;
/*     */   }
/*     */   
/*     */   private void setDetails() {
/* 198 */     IEntityStats props = getEntityStats();
/*     */ 
/*     */     
/* 201 */     if (props.isPirate()) {
/* 202 */       setPirateDetails();
/*     */     }
/* 204 */     else if (props.isMarine()) {
/* 205 */       setMarineDetails();
/*     */     }
/* 207 */     else if (props.isBandit()) {
/* 208 */       setBanditDetails();
/*     */     } 
/* 210 */     chooseTexture();
/*     */ 
/*     */     
/* 213 */     if (props.isSniper()) {
/* 214 */       setSniperDetails();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setSniperDetails() {
/* 219 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.SENRIKU.get()));
/* 220 */     this.field_70714_bg.func_75776_a(0, (Goal)(new MeleeRangedStyleSwitchGoal((MobEntity)this)).forceMeleeEmptyHanded().addStyleSwitchEvent(100, this::onStyleSwitched));
/* 221 */     this.rangePhase.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 60, 60.0F));
/*     */     
/* 223 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 224 */     goals.addEntry(() -> new KaenBoshiWrapperGoal((MobEntity)this), 6.0F);
/* 225 */     goals.addEntry(() -> new TokuyoAburaBoshiWrapperGoal((MobEntity)this), 4.0F);
/* 226 */     goals.addEntry(() -> new KemuriBoshiWrapperGoal((MobEntity)this, 60), 4.0F);
/* 227 */     goals.addEntry(() -> new TetsuBoshiWrapperGoal((MobEntity)this, 60), 3.0F);
/* 228 */     goals.addEntry(() -> new SakuretsuSabotenBoshiWrapperGoal((MobEntity)this), 3.0F);
/*     */     
/* 230 */     MobsHelper.getRandomizedGoals((MobEntity)this, isAboveNormalDifficulty() ? 3 : 2, goals).forEach(goal -> this.rangePhase.addGoal(2, goal));
/*     */   }
/*     */ 
/*     */   
/*     */   private void setMarineDetails() {
/* 235 */     setTextures(MobsHelper.MARINE_TEXTURES);
/*     */   }
/*     */   
/*     */   private void setPirateDetails() {
/* 239 */     setTextures(MobsHelper.PIRATE_TEXTURES);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     if (this.field_70170_p.func_201674_k().nextInt(10) < 3) {
/* 245 */       getEntityStats().setRace(ModValues.FISHMAN);
/* 246 */       queueEntityDataUpdate();
/* 247 */       setTextures(MobsHelper.PIRATE_FISHMEN_TEXTURES);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBanditDetails() {
/* 252 */     setTextures(MobsHelper.BANDIT_TEXTURES);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 257 */     if (!getEntityStats().isMarine()) {
/* 258 */       return false;
/*     */     }
/*     */     
/* 261 */     IEntityStats senderProps = EntityStatsCapability.get(commandSender);
/* 262 */     if (!senderProps.isMarine()) {
/* 263 */       return false;
/*     */     }
/*     */     
/* 266 */     if (!senderProps.hasMarineRank(FactionHelper.MarineRank.ADMIRAL)) {
/* 267 */       return false;
/*     */     }
/*     */     
/* 270 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 275 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 276 */     this.lastCommandSender = commandSender;
/* 277 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMaintainCommand() {
/* 282 */     if (this.lastCommandSender != null && this.lastCommandSender.func_70089_S() && 
/* 283 */       EntityStatsCapability.get(this.lastCommandSender).isRogue()) {
/* 284 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 288 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 293 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 299 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 304 */     return this.lastCommandTime;
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<? extends OPEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
/* 308 */     if (reason != SpawnReason.SPAWNER) {
/* 309 */       BlockPos worldSpawn = new BlockPos(world.func_72912_H().func_76079_c(), world.func_72912_H().func_76075_d(), world.func_72912_H().func_76074_e());
/* 310 */       if (pos.func_218141_a((Vector3i)worldSpawn, 256.0D)) {
/* 311 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 315 */     double chance = random.nextDouble() * 100.0D;
/* 316 */     if (chance > CommonConfig.INSTANCE.getSniperSpawnChance()) {
/* 317 */       return false;
/*     */     }
/*     */     
/* 320 */     return OPEntity.checkSpawnRules(type, world, reason, pos, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\SniperEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */