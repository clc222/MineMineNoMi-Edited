/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.HandleCannonGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayFromFearGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.GenkotsuMeteorWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.SakuretsuSabotenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TetsuBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TokuyoAburaBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.HiryuKaenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GruntEntity extends OPEntity implements IRangedAttackMob, ICommandReceiver {
/*  63 */   private static final ResourceLocation[] STYLES = new ResourceLocation[] { ModValues.SWORDSMAN, ModValues.SNIPER, ModValues.BRAWLER };
/*  64 */   private static final Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> MELEE_FACTION_WEAPONS = createFactionWeaponsMap(); private long lastCommandTime; private LivingEntity lastCommandSender;
/*     */   private static Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> createFactionWeaponsMap() {
/*  66 */     Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> map = new HashMap<>();
/*  67 */     map.put(ModValues.PIRATE, MobsHelper.PIRATE_SWORDS);
/*  68 */     map.put(ModValues.MARINE, MobsHelper.MARINE_SWORDS);
/*  69 */     map.put(ModValues.BANDIT, MobsHelper.BANDIT_SWORDS);
/*  70 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  75 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   private LivingEntity lastTarget;
/*     */   private boolean firstShot = true;
/*     */   
/*     */   public GruntEntity(EntityType type, World world, ResourceLocation faction) {
/*  81 */     super(type, world, (ResourceLocation[])null);
/*  82 */     if (world != null && !world.field_72995_K) {
/*  83 */       ResourceLocation style = STYLES[func_70681_au().nextInt(STYLES.length)];
/*     */       
/*  85 */       getEntityStats().setFaction(faction);
/*  86 */       getEntityStats().setFightingStyle(style);
/*  87 */       getEntityStats().setRace(ModValues.HUMAN);
/*  88 */       setDetails();
/*     */       
/*  90 */       boolean isHardDifficulty = isAboveNormalDifficulty();
/*  91 */       setDoriki(500.0D + WyHelper.randomWithRange(0, 1000) + (isHardDifficulty ? WyHelper.randomWithRange(0, 500) : 0.0D));
/*  92 */       setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*     */       
/*  94 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(60.0D);
/*  95 */       if (this.field_70170_p.func_175659_aa().func_151525_a() <= 2) {
/*  96 */         float mod = 1.0F;
/*  97 */         switch (this.field_70170_p.func_175659_aa()) {
/*     */           case EASY:
/*  99 */             mod = 2.0F;
/*     */             break;
/*     */           case NORMAL:
/* 102 */             mod = 1.25F;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 107 */         ModifiableAttributeInstance attr = func_110148_a(Attributes.field_233819_b_);
/* 108 */         attr.func_111128_a(attr.func_111125_b() / mod);
/*     */       } 
/*     */       
/* 111 */       MobsHelper.addBasicNPCGoals(this);
/* 112 */       CommandAbility.addCommandGoals((MobEntity)this);
/*     */       
/* 114 */       if (func_70681_au().nextInt(10) > 3) {
/* 115 */         this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*     */       }
/*     */       
/* 118 */       if (func_70681_au().nextInt(10) > 6) {
/* 119 */         this.field_70714_bg.func_75776_a(0, (Goal)new RunAwayFromFearGoal(this, 1.5D));
/*     */       }
/*     */       
/* 122 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.15D, true));
/* 123 */       if (getEntityStats().isMarine() || getEntityStats().isPirate()) {
/* 124 */         this.field_70714_bg.func_75776_a(2, (Goal)new HandleCannonGoal((MobEntity)this));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static GruntEntity createMarineGrunt(EntityType type, World world) {
/* 130 */     return new GruntEntity(type, world, ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static GruntEntity createPirateGrunt(EntityType type, World world) {
/* 134 */     return new GruntEntity(type, world, ModValues.PIRATE);
/*     */   }
/*     */   
/*     */   public static GruntEntity createBanditGrunt(EntityType type, World world) {
/* 138 */     return new GruntEntity(type, world, ModValues.BANDIT);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 142 */     return OPEntity.createAttributes()
/* 143 */       .func_233815_a_(Attributes.field_233819_b_, 30.0D)
/* 144 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 145 */       .func_233815_a_(Attributes.field_233823_f_, WyHelper.randomWithRange(1, 2))
/* 146 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(20, 30))
/* 147 */       .func_233815_a_(Attributes.field_233826_i_, WyHelper.randomWithRange(0, 4));
/*     */   }
/*     */   
/*     */   public void func_82196_d(LivingEntity target, float velocity) {
/*     */     KairosekiBulletProjectile kairosekiBulletProjectile;
/* 152 */     if (func_184614_ca() == null || !func_184614_ca().func_77973_b().equals(ModWeapons.FLINTLOCK.get())) {
/*     */       return;
/*     */     }
/*     */     
/* 156 */     if (func_70638_az() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 160 */     if (this.lastTarget == null || this.lastTarget != func_70638_az()) {
/* 161 */       this.lastTarget = func_70638_az();
/* 162 */       this.firstShot = true;
/*     */     } 
/*     */     
/* 165 */     boolean isHardDifficulty = this.field_70170_p.func_175649_E(func_233580_cy_()).func_193845_a(Difficulty.NORMAL.ordinal());
/*     */     
/* 167 */     NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 168 */     normalBulletProjectile.setDamage(4.0F);
/* 169 */     if (isHardDifficulty) {
/* 170 */       kairosekiBulletProjectile = new KairosekiBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 171 */       kairosekiBulletProjectile.setDamage(5.0F);
/*     */     } 
/*     */     
/* 174 */     if (this.firstShot) {
/* 175 */       kairosekiBulletProjectile.setDamage(-1.0F);
/*     */     }
/*     */     
/* 178 */     float inaccuracy = Math.max(0, 16 - this.field_70170_p.func_175659_aa().func_151525_a() * 6);
/* 179 */     if (this.firstShot) {
/* 180 */       inaccuracy = 20.0F;
/*     */     }
/* 182 */     kairosekiBulletProjectile.func_234612_a_((Entity)this, this.field_70125_A, this.field_70177_z, 0.0F, 3.0F, inaccuracy);
/* 183 */     this.field_70170_p.func_217376_c((Entity)kairosekiBulletProjectile);
/*     */     
/* 185 */     this.field_70170_p.func_184133_a(null, func_233580_cy_(), (SoundEvent)ModSounds.PISTOL_SHOOT.get(), SoundCategory.PLAYERS, 2.5F, 0.75F + func_70681_au().nextFloat() / 4.0F);
/*     */     
/* 187 */     this.firstShot = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225511_J_() {
/* 192 */     return true;
/*     */   }
/*     */   
/*     */   private void setDetails() {
/* 196 */     IEntityStats props = getEntityStats();
/*     */ 
/*     */     
/* 199 */     if (props.isPirate()) {
/* 200 */       setPirateDetails();
/*     */     }
/* 202 */     else if (props.isMarine()) {
/* 203 */       setMarineDetails();
/*     */     }
/* 205 */     else if (props.isBandit()) {
/* 206 */       setBanditDetails();
/*     */     } 
/* 208 */     chooseTexture();
/*     */ 
/*     */     
/* 211 */     if (props.isSwordsman()) {
/* 212 */       setSwordsmanDetails();
/*     */     }
/* 214 */     else if (props.isSniper()) {
/* 215 */       setSniperDetails();
/*     */     }
/* 217 */     else if (props.isBrawler()) {
/* 218 */       setBrawlerDetails();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setSwordsmanDetails() {
/* 223 */     ItemStack randomSword = getRandomSword(MELEE_FACTION_WEAPONS.get(getEntityStats().getFaction()));
/* 224 */     if (getEntityStats().isMarine() && randomSword.func_77973_b() instanceof net.minecraft.item.IDyeableArmorItem) {
/* 225 */       randomSword.func_190925_c("display").func_74768_a("color", MobsHelper.MARINE_BLUE_COLOR.getRGB());
/*     */     }
/* 227 */     func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/* 228 */     if (isAboveNormalDifficulty()) {
/* 229 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 230 */       goals.addEntry(() -> new HiryuKaenWrapperGoal((MobEntity)this), 3.0F);
/* 231 */       goals.addEntry(() -> new ShiShishiSonsonWrapperGoal((MobEntity)this), 2.0F);
/* 232 */       goals.addEntry(() -> new YakkodoriWrapperGoal((MobEntity)this), 1.0F);
/*     */       
/* 234 */       MobsHelper.getRandomizedGoals((MobEntity)this, 1, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setSniperDetails() {
/* 239 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK.get()));
/* 240 */     this.field_70714_bg.func_75776_a(1, (Goal)new RangedAttackGoal(this, 1.0D, 40, 15.0F));
/* 241 */     if (isAboveNormalDifficulty()) {
/* 242 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 243 */       goals.addEntry(() -> new KaenBoshiWrapperGoal((MobEntity)this, 50), 3.0F);
/* 244 */       goals.addEntry(() -> new TokuyoAburaBoshiWrapperGoal((MobEntity)this), 2.0F);
/* 245 */       goals.addEntry(() -> new TetsuBoshiWrapperGoal((MobEntity)this, 60), 1.0F);
/* 246 */       goals.addEntry(() -> new SakuretsuSabotenBoshiWrapperGoal((MobEntity)this, 60), 1.0F);
/*     */       
/* 248 */       MobsHelper.getRandomizedGoals((MobEntity)this, 1, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBrawlerDetails() {
/* 253 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*     */     
/* 255 */     if (isAboveNormalDifficulty()) {
/* 256 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 257 */       goals.addEntry(() -> new GenkotsuMeteorWrapperGoal((MobEntity)this), 3.0F);
/* 258 */       goals.addEntry(() -> new SuplexWrapperGoal((MobEntity)this), 2.0F);
/* 259 */       goals.addEntry(() -> new HakaiHoWrapperGoal((MobEntity)this), 1.0F);
/*     */       
/* 261 */       MobsHelper.getRandomizedGoals((MobEntity)this, 1, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMarineDetails() {
/* 266 */     setTextures(MobsHelper.MARINE_TEXTURES);
/*     */   }
/*     */   
/*     */   private void setPirateDetails() {
/* 270 */     setTextures(MobsHelper.PIRATE_TEXTURES);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     if (this.field_70170_p.func_201674_k().nextInt(10) < 3) {
/* 276 */       getEntityStats().setRace(ModValues.FISHMAN);
/* 277 */       queueEntityDataUpdate();
/* 278 */       setTextures(MobsHelper.PIRATE_FISHMEN_TEXTURES);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBanditDetails() {
/* 283 */     setTextures(MobsHelper.BANDIT_TEXTURES);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 288 */     if (!getEntityStats().isMarine()) {
/* 289 */       return false;
/*     */     }
/*     */     
/* 292 */     IEntityStats senderProps = EntityStatsCapability.get(commandSender);
/* 293 */     if (!senderProps.isMarine()) {
/* 294 */       return false;
/*     */     }
/*     */     
/* 297 */     if (!senderProps.hasMarineRank(FactionHelper.MarineRank.COMMODORE)) {
/* 298 */       return false;
/*     */     }
/*     */     
/* 301 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 306 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 307 */     this.lastCommandSender = commandSender;
/* 308 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMaintainCommand() {
/* 313 */     if (this.lastCommandSender != null && this.lastCommandSender.func_70089_S() && 
/* 314 */       EntityStatsCapability.get(this.lastCommandSender).isRogue()) {
/* 315 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 324 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 330 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 335 */     return this.lastCommandTime;
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<? extends OPEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
/* 339 */     double chance = random.nextDouble() * 100.0D;
/* 340 */     if (chance > CommonConfig.INSTANCE.getGruntSpawnChance()) {
/* 341 */       return false;
/*     */     }
/*     */     
/* 344 */     return OPEntity.checkSpawnRules(type, world, reason, pos, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\GruntEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */