/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
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
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.TackleAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ChargedCleaveWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.WeaponSpinWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.GenkotsuMeteorWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SpinningBrawlWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TackleWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BruteEntity extends OPEntity implements IRangedAttackMob, ICommandReceiver {
/*  63 */   private static final UUID TACKLE_COOLDOWN_BONUS_UUID = UUID.fromString("2839290d-9070-45fb-a7ac-465405562ec6");
/*  64 */   private static final ResourceLocation[] STYLES = new ResourceLocation[] { ModValues.SWORDSMAN, ModValues.SNIPER, ModValues.BRAWLER };
/*     */   
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  68 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   public BruteEntity(EntityType type, World world, ResourceLocation faction) {
/*  71 */     super(type, world, (ResourceLocation[])null);
/*  72 */     ResourceLocation style = STYLES[func_70681_au().nextInt(STYLES.length)];
/*     */     
/*  74 */     if (world != null && !world.field_72995_K) {
/*     */       
/*  76 */       getEntityStats().setFaction(faction);
/*  77 */       getEntityStats().setFightingStyle(style);
/*  78 */       getEntityStats().setRace(ModValues.HUMAN);
/*  79 */       setDetails();
/*     */       
/*  81 */       boolean isHardDifficulty = isAboveNormalDifficulty();
/*  82 */       setDoriki(2000.0D + WyHelper.randomWithRange(0, 1500) + (isHardDifficulty ? WyHelper.randomWithRange(0, 500) : 0.0D));
/*  83 */       setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*     */       
/*  85 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(60.0D);
/*  86 */       if (this.field_70170_p.func_175659_aa().func_151525_a() <= 2) {
/*  87 */         float mod = 1.0F;
/*  88 */         switch (this.field_70170_p.func_175659_aa()) {
/*     */           case EASY:
/*  90 */             mod = 2.0F;
/*     */             break;
/*     */           case NORMAL:
/*  93 */             mod = 1.25F;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*  98 */         ModifiableAttributeInstance attr = func_110148_a(Attributes.field_233819_b_);
/*  99 */         attr.func_111128_a(attr.func_111125_b() / mod);
/*     */       } 
/*     */       
/* 102 */       MobsHelper.addBasicNPCGoals(this);
/* 103 */       CommandAbility.addCommandGoals((MobEntity)this);
/*     */       
/* 105 */       this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/* 106 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/*     */       
/* 108 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 109 */       func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(1.3D);
/*     */       
/* 111 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 15);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static BruteEntity createMarineBrute(EntityType type, World world) {
/* 116 */     return new BruteEntity(type, world, ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static BruteEntity createPirateBrute(EntityType type, World world) {
/* 120 */     return new BruteEntity(type, world, ModValues.PIRATE);
/*     */   }
/*     */   
/*     */   public static BruteEntity createBanditBrute(EntityType type, World world) {
/* 124 */     return new BruteEntity(type, world, ModValues.BANDIT);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 128 */     return OPEntity.createAttributes()
/* 129 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D)
/* 130 */       .func_233815_a_(Attributes.field_233821_d_, 0.25D)
/* 131 */       .func_233815_a_(Attributes.field_233823_f_, WyHelper.randomWithRange(4, 6))
/* 132 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(80, 100))
/* 133 */       .func_233815_a_(Attributes.field_233826_i_, WyHelper.randomWithRange(8, 12));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82196_d(LivingEntity target, float velocity) {
/* 138 */     if (func_184614_ca() == null || !func_184614_ca().func_77973_b().equals(ModWeapons.BAZOOKA.get())) {
/*     */       return;
/*     */     }
/*     */     
/* 142 */     CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(this.field_70170_p, (LivingEntity)this);
/* 143 */     cannonBallProjectile.setDamage(3.0F);
/* 144 */     ((AbilityProjectileEntity)cannonBallProjectile).onBlockImpactEvent = (hit -> {
/*     */         if (this.field_70173_aa < 2) {
/*     */           return;
/*     */         }
/*     */         
/*     */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)proj, this.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 2.0F);
/*     */         explosion.setStaticDamage(5.0F);
/*     */         explosion.setDestroyBlocks(false);
/*     */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*     */         explosion.doExplosion();
/*     */       });
/* 155 */     double velX = target.func_226277_ct_() - func_226277_ct_();
/* 156 */     double velY = (target.func_174813_aQ()).field_72338_b - cannonBallProjectile.func_226278_cu_();
/* 157 */     double velZ = target.func_226281_cx_() - func_226281_cx_();
/* 158 */     double x = MathHelper.func_76133_a(velX * velX + velZ * velZ);
/*     */     
/* 160 */     cannonBallProjectile.func_70186_c(velX, velY + x * 0.10000000149011612D, velZ, 2.0F, MathHelper.func_76125_a(12 - this.field_70170_p.func_175659_aa().func_151525_a() * 4, 0, 100));
/* 161 */     this.field_70170_p.func_217376_c((Entity)cannonBallProjectile);
/*     */     
/* 163 */     this.field_70170_p.func_184133_a(null, func_233580_cy_(), (SoundEvent)ModSounds.GENERIC_EXPLOSION.get(), SoundCategory.PLAYERS, 2.5F, 0.75F + func_70681_au().nextFloat() / 4.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225511_J_() {
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   private void setDetails() {
/* 172 */     IEntityStats props = getEntityStats();
/*     */ 
/*     */     
/* 175 */     if (props.isPirate()) {
/* 176 */       setPirateDetails();
/*     */     }
/* 178 */     else if (props.isMarine()) {
/* 179 */       setMarineDetails();
/*     */     }
/* 181 */     else if (props.isBandit()) {
/* 182 */       setBanditDetails();
/*     */     } 
/* 184 */     chooseTexture();
/*     */ 
/*     */     
/* 187 */     if (props.isSwordsman()) {
/* 188 */       setSwordsmanDetails();
/*     */     }
/* 190 */     else if (props.isSniper()) {
/* 191 */       setSniperDetails();
/*     */     }
/* 193 */     else if (props.isBrawler()) {
/* 194 */       setBrawlerDetails();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setSwordsmanDetails() {
/* 199 */     ItemStack randomSword = getRandomSword(MobsHelper.BRUTE_SWORDS);
/* 200 */     if (getEntityStats().isMarine() && randomSword.func_77973_b() instanceof net.minecraft.item.IDyeableArmorItem) {
/* 201 */       randomSword.func_190925_c("display").func_74768_a("color", MobsHelper.MARINE_BLUE_COLOR.getRGB());
/*     */     }
/* 203 */     func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/*     */     
/* 205 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 206 */     goals.addEntry(() -> new ChargedCleaveWrapperGoal((MobEntity)this), 3.0F);
/* 207 */     goals.addEntry(() -> new ShiShishiSonsonWrapperGoal((MobEntity)this), 2.0F);
/* 208 */     goals.addEntry(() -> new YakkodoriWrapperGoal((MobEntity)this), 2.0F);
/* 209 */     goals.addEntry(() -> new WeaponSpinWrapperGoal((MobEntity)this), 1.0F);
/* 210 */     goals.addEntry(() -> new OTatsumakiWrapperGoal((MobEntity)this), 1.0F);
/*     */     
/* 212 */     MobsHelper.getRandomizedGoals((MobEntity)this, isAboveNormalDifficulty() ? 2 : 1, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*     */   }
/*     */   
/*     */   private void setSniperDetails() {
/* 216 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.BAZOOKA.get()));
/* 217 */     this.field_70714_bg.func_75776_a(1, (Goal)new RangedAttackGoal(this, 1.0D, isAboveNormalDifficulty() ? 100 : 160, 40.0F));
/*     */   }
/*     */   
/*     */   private void setBrawlerDetails() {
/* 221 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*     */     
/* 223 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 224 */     goals.addEntry(() -> new ChargedPunchWrapperGoal((MobEntity)this), 3.0F);
/* 225 */     goals.addEntry(() -> new SuplexWrapperGoal((MobEntity)this), 3.0F);
/* 226 */     goals.addEntry(() -> new SpinningBrawlWrapperGoal((MobEntity)this), 3.0F);
/* 227 */     goals.addEntry(() -> { TackleWrapperGoal tackleWrapper = new TackleWrapperGoal((MobEntity)this); ((TackleAbility)tackleWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(()); return (Goal)tackleWrapper; }2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     goals.addEntry(() -> new HakaiHoWrapperGoal((MobEntity)this), 2.0F);
/* 233 */     goals.addEntry(() -> new GenkotsuMeteorWrapperGoal((MobEntity)this), 1.0F);
/*     */     
/* 235 */     MobsHelper.getRandomizedGoals((MobEntity)this, isAboveNormalDifficulty() ? 2 : 1, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*     */   }
/*     */   
/*     */   private void setMarineDetails() {
/* 239 */     setTextures(MobsHelper.MARINE_TEXTURES);
/*     */   }
/*     */   
/*     */   private void setPirateDetails() {
/* 243 */     setTextures(MobsHelper.PIRATE_TEXTURES);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     if (this.field_70170_p.func_201674_k().nextInt(10) < 3) {
/* 249 */       getEntityStats().setRace(ModValues.FISHMAN);
/* 250 */       queueEntityDataUpdate();
/* 251 */       setTextures(MobsHelper.PIRATE_FISHMEN_TEXTURES);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBanditDetails() {
/* 256 */     setTextures(MobsHelper.BANDIT_TEXTURES);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 261 */     if (!getEntityStats().isMarine()) {
/* 262 */       return false;
/*     */     }
/*     */     
/* 265 */     IEntityStats senderProps = EntityStatsCapability.get(commandSender);
/* 266 */     if (!senderProps.isMarine()) {
/* 267 */       return false;
/*     */     }
/*     */     
/* 270 */     if (!senderProps.hasMarineRank(FactionHelper.MarineRank.ADMIRAL)) {
/* 271 */       return false;
/*     */     }
/*     */     
/* 274 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 279 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 280 */     this.lastCommandSender = commandSender;
/* 281 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMaintainCommand() {
/* 286 */     if (this.lastCommandSender != null && this.lastCommandSender.func_70089_S() && 
/* 287 */       EntityStatsCapability.get(this.lastCommandSender).isRogue()) {
/* 288 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 297 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 303 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 308 */     return this.lastCommandTime;
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<? extends OPEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
/* 312 */     if (reason != SpawnReason.SPAWNER) {
/* 313 */       BlockPos worldSpawn = new BlockPos(world.func_72912_H().func_76079_c(), world.func_72912_H().func_76075_d(), world.func_72912_H().func_76074_e());
/* 314 */       if (pos.func_218141_a((Vector3i)worldSpawn, 256.0D)) {
/* 315 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 319 */     double chance = random.nextDouble() * 100.0D;
/* 320 */     if (chance > CommonConfig.INSTANCE.getBruteSpawnChance()) {
/* 321 */       return false;
/*     */     }
/*     */     
/* 324 */     return OPEntity.checkSpawnRules(type, world, reason, pos, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\BruteEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */