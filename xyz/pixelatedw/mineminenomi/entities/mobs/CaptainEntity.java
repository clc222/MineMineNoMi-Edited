/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
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
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.KnifeThrowingGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CaptainEntity extends OPEntity implements IRangedAttackMob, ICommandReceiver {
/*  54 */   private static final ResourceLocation[] STYLES = new ResourceLocation[] { ModValues.SWORDSMAN, ModValues.BRAWLER };
/*  55 */   private static final Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> MELEE_FACTION_WEAPONS = createFactionWeaponsMap();
/*     */   
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  59 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   private static Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> createFactionWeaponsMap() {
/*  62 */     Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> map = new HashMap<>();
/*  63 */     map.put(ModValues.PIRATE, MobsHelper.PIRATE_SWORDS);
/*  64 */     map.put(ModValues.MARINE, MobsHelper.MARINE_CAPTAIN_SWORDS);
/*  65 */     map.put(ModValues.BANDIT, MobsHelper.BANDIT_SWORDS);
/*  66 */     return map;
/*     */   }
/*     */   
/*     */   public CaptainEntity(EntityType type, World world, ResourceLocation faction) {
/*  70 */     super(type, world, (ResourceLocation[])null);
/*  71 */     ResourceLocation style = STYLES[func_70681_au().nextInt(STYLES.length)];
/*     */     
/*  73 */     if (world != null && !world.field_72995_K) {
/*  74 */       getEntityStats().setFaction(faction);
/*  75 */       getEntityStats().setFightingStyle(style);
/*  76 */       getEntityStats().setRace(ModValues.HUMAN);
/*  77 */       setDetails();
/*     */       
/*  79 */       if (this.field_70146_Z.nextFloat() < 0.7F) {
/*  80 */         if (this.field_70146_Z.nextFloat() < 0.3F) {
/*  81 */           func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.THREE_CIGARS.get()));
/*     */         } else {
/*     */           
/*  84 */           func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR.get()));
/*     */         } 
/*     */       }
/*     */       
/*  88 */       boolean isHardDifficulty = isAboveNormalDifficulty();
/*  89 */       setDoriki(3000.0D + WyHelper.randomWithRange(0, 2000) + (isHardDifficulty ? WyHelper.randomWithRange(0, 500) : 0.0D));
/*  90 */       setBelly(30.0D + WyHelper.randomWithRange(0, 20));
/*     */       
/*  92 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(6.0D);
/*  93 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  94 */       if (this.field_70170_p.func_175659_aa().func_151525_a() <= 2) {
/*  95 */         float mod = 1.0F;
/*  96 */         switch (this.field_70170_p.func_175659_aa()) {
/*     */           case EASY:
/*  98 */             mod = 2.0F;
/*     */             break;
/*     */           case NORMAL:
/* 101 */             mod = 1.25F;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 106 */         ModifiableAttributeInstance attr = func_110148_a(Attributes.field_233819_b_);
/* 107 */         attr.func_111128_a(attr.func_111125_b() / mod);
/*     */       } 
/*     */       
/* 110 */       int rokushikiAbilities = getEntityStats().isBandit() ? 2 : 3;
/*     */       
/* 112 */       MobsHelper.addBasicNPCGoals(this);
/* 113 */       CommandAbility.addCommandGoals((MobEntity)this);
/*     */       
/* 115 */       this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 116 */       this.field_70714_bg.func_75776_a(0, (Goal)new GapCloserGoal((MobEntity)this));
/* 117 */       if (this.field_70146_Z.nextFloat() < 0.6F) {
/* 118 */         this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*     */       }
/* 120 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/* 121 */       if (getEntityStats().isBandit()) {
/* 122 */         this.field_70714_bg.func_75776_a(2, (Goal)(new KnifeThrowingGoal(this)).setAmount(3));
/*     */       }
/*     */       
/* 125 */       this.field_70715_bh.func_75776_a(1, (Goal)new PersonalSpaceTargetGoal((MobEntity)this));
/*     */       
/* 127 */       MobsHelper.addRokushikiAbilities((MobEntity)this, rokushikiAbilities);
/* 128 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 30);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static CaptainEntity createMarineCaptain(EntityType type, World world) {
/* 133 */     return new CaptainEntity(type, world, ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static CaptainEntity createPirateCaptain(EntityType type, World world) {
/* 137 */     return new CaptainEntity(type, world, ModValues.PIRATE);
/*     */   }
/*     */   
/*     */   public static CaptainEntity createBanditCaptain(EntityType type, World world) {
/* 141 */     return new CaptainEntity(type, world, ModValues.BANDIT);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 145 */     return OPEntity.createAttributes()
/* 146 */       .func_233815_a_(Attributes.field_233819_b_, 50.0D)
/* 147 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 148 */       .func_233815_a_(Attributes.field_233823_f_, WyHelper.randomWithRange(3, 6))
/* 149 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(100, 150))
/* 150 */       .func_233815_a_(Attributes.field_233826_i_, WyHelper.randomWithRange(8, 12));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82196_d(LivingEntity target, float velocity) {
/* 155 */     if (func_184614_ca() == null || !func_184614_ca().func_77973_b().equals(ModWeapons.BAZOOKA.get())) {
/*     */       return;
/*     */     }
/*     */     
/* 159 */     CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(this.field_70170_p, (LivingEntity)this);
/* 160 */     cannonBallProjectile.setDamage(3.0F);
/* 161 */     ((AbilityProjectileEntity)cannonBallProjectile).onBlockImpactEvent = (hit -> {
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
/* 172 */     double velX = target.func_226277_ct_() - func_226277_ct_();
/* 173 */     double velY = (target.func_174813_aQ()).field_72338_b - cannonBallProjectile.func_226278_cu_();
/* 174 */     double velZ = target.func_226281_cx_() - func_226281_cx_();
/* 175 */     double x = MathHelper.func_76133_a(velX * velX + velZ * velZ);
/*     */     
/* 177 */     cannonBallProjectile.func_70186_c(velX, velY + x * 0.10000000149011612D, velZ, 2.0F, MathHelper.func_76125_a(12 - this.field_70170_p.func_175659_aa().func_151525_a() * 4, 0, 100));
/* 178 */     this.field_70170_p.func_217376_c((Entity)cannonBallProjectile);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_225511_J_() {
/* 183 */     return true;
/*     */   }
/*     */   
/*     */   private void setDetails() {
/* 187 */     IEntityStats props = getEntityStats();
/*     */ 
/*     */     
/* 190 */     if (props.isPirate()) {
/* 191 */       setPirateDetails();
/*     */     }
/* 193 */     else if (props.isMarine()) {
/* 194 */       setMarineDetails();
/*     */     }
/* 196 */     else if (props.isBandit()) {
/* 197 */       setBanditDetails();
/*     */     } 
/* 199 */     chooseTexture();
/*     */ 
/*     */     
/* 202 */     if (props.isSwordsman()) {
/* 203 */       setSwordsmanDetails();
/*     */     }
/* 205 */     else if (props.isBrawler()) {
/* 206 */       setBrawlerDetails();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setSwordsmanDetails() {
/* 211 */     ItemStack randomSword = getRandomSword(MELEE_FACTION_WEAPONS.get(getEntityStats().getFaction()));
/* 212 */     func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/*     */     
/* 214 */     float dualWeildChance = getEntityStats().isPirate() ? 0.4F : 0.2F;
/* 215 */     if (this.field_70146_Z.nextFloat() < dualWeildChance) {
/* 216 */       func_184201_a(EquipmentSlotType.OFFHAND, randomSword.func_77946_l());
/*     */     }
/*     */     
/* 219 */     int swordsmanAbilities = getEntityStats().isBandit() ? 3 : 2;
/* 220 */     MobsHelper.addSwordsmanAbilities((MobEntity)this, swordsmanAbilities);
/*     */   }
/*     */   
/*     */   private void setBrawlerDetails() {
/* 224 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*     */     
/* 226 */     int brawlerAbilities = getEntityStats().isBandit() ? 3 : 3;
/* 227 */     MobsHelper.addBrawlerAbilities((MobEntity)this, brawlerAbilities);
/* 228 */     this.field_70714_bg.func_75776_a(3, (Goal)new ChargedPunchWrapperGoal((MobEntity)this));
/*     */   }
/*     */   
/*     */   private void setMarineDetails() {
/* 232 */     setTextures(MobsHelper.MARINE_CAPTAINS_TEXTURES);
/* 233 */     ItemStack marineCapeStack = new ItemStack((IItemProvider)ModArmors.MARINE_CAPTAIN_CAPE.get());
/* 234 */     marineCapeStack.func_190925_c("display").func_74768_a("color", MobsHelper.MARINE_BLUE_COLOR.getRGB());
/* 235 */     func_184201_a(EquipmentSlotType.CHEST, marineCapeStack);
/*     */   }
/*     */   
/*     */   private void setPirateDetails() {
/* 239 */     setTextures(MobsHelper.PIRATE_CAPTAINS_TEXTURES);
/* 240 */     if (this.field_70146_Z.nextFloat() < 0.4F) {
/* 241 */       ItemStack pirateCapeStack = new ItemStack((IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE.get());
/* 242 */       func_184201_a(EquipmentSlotType.CHEST, pirateCapeStack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBanditDetails() {
/* 247 */     setTextures(MobsHelper.BANDIT_TEXTURES);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 252 */     if (!getEntityStats().isMarine()) {
/* 253 */       return false;
/*     */     }
/*     */     
/* 256 */     IEntityStats senderProps = EntityStatsCapability.get(commandSender);
/* 257 */     if (!senderProps.isMarine()) {
/* 258 */       return false;
/*     */     }
/*     */     
/* 261 */     if (!senderProps.hasMarineRank(FactionHelper.MarineRank.ADMIRAL)) {
/* 262 */       return false;
/*     */     }
/*     */     
/* 265 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 270 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 271 */     this.lastCommandSender = commandSender;
/* 272 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMaintainCommand() {
/* 277 */     if (this.lastCommandSender != null && this.lastCommandSender.func_70089_S() && 
/* 278 */       EntityStatsCapability.get(this.lastCommandSender).isRogue()) {
/* 279 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 283 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 288 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 294 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 299 */     return this.lastCommandTime;
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<? extends OPEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
/* 303 */     if (reason != SpawnReason.SPAWNER) {
/* 304 */       BlockPos worldSpawn = new BlockPos(world.func_72912_H().func_76079_c(), world.func_72912_H().func_76075_d(), world.func_72912_H().func_76074_e());
/* 305 */       if (pos.func_218141_a((Vector3i)worldSpawn, 512.0D)) {
/* 306 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 310 */     double chance = random.nextDouble() * 100.0D;
/* 311 */     if (chance > CommonConfig.INSTANCE.getCaptainSpawnChance()) {
/* 312 */       return false;
/*     */     }
/*     */     
/* 315 */     return OPEntity.checkSpawnRules(type, world, reason, pos, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\CaptainEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */