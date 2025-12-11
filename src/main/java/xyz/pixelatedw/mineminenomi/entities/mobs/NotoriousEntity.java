/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BlackLegPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.ServerOPBossInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.AbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhaseManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.INotoriousTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.config.AbilitiesConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.KnifeThrowingGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.AntiMannerKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.ConcasseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.ExtraHachisWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.electro.EleclawWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.electro.ElectricalLunaWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.electro.ElectricalMissileWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.electro.ElectricalTempestaWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.KachiageHaisokuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.KarakusagawaraSeikenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MizuOsuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MizuShuryudanWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MurasameWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.SamehadaShoteiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.UchimizuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.HaoshokuHakiInfusionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.KenbunshokuHakiFutureSightWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.ShiganWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.SoruWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.TekkaiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KemuriBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.NemuriBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.RenpatsuNamariBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.SakuretsuSabotenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TetsuBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TokuyoAburaBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModGunItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class NotoriousEntity extends OPEntity implements INotoriousTarget {
/*  85 */   private static final ResourceLocation[] STYLES = new ResourceLocation[] { ModValues.SWORDSMAN, ModValues.BRAWLER, ModValues.BLACK_LEG, ModValues.SNIPER };
/*  86 */   private static final ResourceLocation[] RACES = new ResourceLocation[] { ModValues.HUMAN, ModValues.FISHMAN };
/*  87 */   private static final Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> MELEE_FACTION_WEAPONS = createFactionWeaponsMap();
/*     */   
/*  89 */   private static final String[] PIRATE_NAMES = new String[] { "Allison", "Bellamy", "Benito", "Barrow", "Caesar", "Bonnet", "Burke", "Chivers", "Coward", "Derdrake", "Frowd", "Hands", "Julian", "Low", "Lyne", "Pargo", "Roberts", "Almeida", "Shih", "Abbas", "Willems", "Veale", "Lewis", "Hamlin", "Andreson", "Wittebol", "Worst" };
/*     */   
/*  91 */   private static final String[] PIRATE_PRE_TITLES = new String[] { "Tyrant", "Sir", "Don", "Iron Hand", "Boss", "Commander", "Black Arm", "Mountain" };
/*  92 */   private static final String[] PIRATE_POST_TITLES = new String[] { "the Hyena", "the Beheader", "the Cannibal", "the Mad", "the Mighty", "the Superior", "the Lonely", "the Sea Weasel" };
/*  93 */   private static final String[] MARINE_NAMES = new String[] { "Golden", "Goffe", "Griffin", "Hoar", "John", "Liddell", "Penniston", "Aregnaudeau", "Jennings", "Enriquez", "Day", "Crapo", "Burches", "Barnet", "Ashworth", "Wright", "Woolerly", "Ras", "Pardal", "Paine", "Morgan", "Mansfield", "Henley", "Essex", "Browne", "Bequel" };
/*     */ 
/*     */   
/*     */   private static Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> createFactionWeaponsMap() {
/*  97 */     Map<ResourceLocation, ArrayList<Supplier<? extends Item>>> map = new HashMap<>();
/*  98 */     map.put(ModValues.PIRATE, MobsHelper.PIRATE_SWORDS);
/*  99 */     map.put(ModValues.MARINE, MobsHelper.MARINE_CAPTAIN_SWORDS);
/* 100 */     map.put(ModValues.BANDIT, MobsHelper.BANDIT_SWORDS);
/* 101 */     return map;
/*     */   }
/*     */   
/* 104 */   private final ServerOPBossInfo bossInfo = new ServerOPBossInfo(func_200201_e());
/* 105 */   private final NPCPhaseManager phaseManager = new NPCPhaseManager((MobEntity)this);
/*     */   private float difficulty;
/*     */   private boolean isTriggered;
/*     */   private TrackedNPC trackedData;
/*     */   private Random trackedRandom;
/*     */   
/*     */   public NotoriousEntity(EntityType<?> type, World world, ResourceLocation faction) {
/* 112 */     this(type, world, faction, (TrackedNPC)null);
/*     */   }
/*     */   
/*     */   public NotoriousEntity(EntityType<?> type, World world, ResourceLocation faction, @Nullable TrackedNPC trackedData) {
/* 116 */     super(type, world);
/* 117 */     if (world != null && !world.field_72995_K) {
/* 118 */       if (trackedData != null) {
/* 119 */         this.trackedData = trackedData;
/* 120 */         this.trackedRandom = new Random(this.trackedData.getSeed());
/* 121 */         func_184221_a(trackedData.getUUID());
/*     */       } else {
/* 123 */         this.trackedRandom = this.field_70146_Z;
/* 124 */         func_184221_a(new UUID(this.trackedRandom.nextLong(), this.trackedRandom.nextLong()));
/*     */       } 
/*     */       
/* 127 */       getEntityStats().setFaction(faction);
/* 128 */       if (trackedData != null) {
/* 129 */         getEntityStats().setFightingStyle(trackedData.getStyle());
/* 130 */         getEntityStats().setRace(trackedData.getRace());
/*     */       } 
/*     */       
/* 133 */       setDetails();
/*     */       
/* 135 */       if (this.trackedRandom.nextFloat() < 0.5F) {
/* 136 */         if (this.trackedRandom.nextFloat() < 0.3F) {
/* 137 */           func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.THREE_CIGARS.get()));
/*     */         } else {
/*     */           
/* 140 */           func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR.get()));
/*     */         } 
/*     */       }
/*     */       
/* 144 */       boolean isHardDifficulty = isAboveNormalDifficulty();
/* 145 */       setDoriki(6000.0D + WyHelper.randomWithRange(0, 2000) + (isHardDifficulty ? WyHelper.randomWithRange(0, 1000) : 0.0D));
/* 146 */       setBelly(500.0D + WyHelper.randomWithRange(0, 100));
/* 147 */       if (faction.equals(ModValues.PIRATE) || faction.equals(ModValues.BANDIT)) {
/* 148 */         long bounty = (100000000 + this.field_70146_Z.nextInt(400000000));
/* 149 */         getEntityStats().setBounty(bounty);
/*     */       } 
/*     */       
/* 152 */       AttributeHelper.updateToughnessAttribute((LivingEntity)this);
/* 153 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(isHardDifficulty ? 20.0D : 40.0D);
/* 154 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(20.0D);
/* 155 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(8.0D);
/*     */       
/* 157 */       MobsHelper.addBasicNPCGoals(this);
/*     */       
/* 159 */       this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 160 */       this.field_70714_bg.func_75776_a(0, (Goal)new GapCloserGoal((MobEntity)this));
/* 161 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 2.5F));
/* 162 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 250.0F, 2.5F));
/* 163 */       this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*     */       
/* 165 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/* 166 */       if (getEntityStats().isBandit()) {
/* 167 */         this.field_70714_bg.func_75776_a(2, (Goal)(new KnifeThrowingGoal(this)).setAmount(3));
/*     */       }
/*     */       
/* 170 */       this.field_70715_bh.func_75776_a(1, (Goal)new PersonalSpaceTargetGoal((MobEntity)this));
/*     */       
/* 172 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */       
/* 174 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 175 */       if (props.isHuman()) {
/* 176 */         goals.addEntry(() -> new SoruWrapperGoal((MobEntity)this), 10.0F);
/* 177 */         goals.addEntry(() -> new ShiganWrapperGoal((MobEntity)this), 10.0F);
/* 178 */         goals.addEntry(() -> new TekkaiWrapperGoal((MobEntity)this), 10.0F);
/* 179 */         goals.addEntry(() -> new RankyakuWrapperGoal((MobEntity)this), 10.0F);
/* 180 */         goals.addEntry(() -> new GeppoWrapperGoal((MobEntity)this), 10.0F);
/* 181 */         goals.addEntry(() -> new KamieWrapperGoal((MobEntity)this), 10.0F);
/*     */       }
/* 183 */       else if (props.isFishman()) {
/* 184 */         this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, FishmanPassiveBonusesAbility.INSTANCE));
/* 185 */         goals.addEntry(() -> new KarakusagawaraSeikenWrapperGoal((MobEntity)this), 10.0F);
/* 186 */         goals.addEntry(() -> new KachiageHaisokuWrapperGoal((MobEntity)this), 10.0F);
/* 187 */         goals.addEntry(() -> new MurasameWrapperGoal((MobEntity)this), 10.0F);
/* 188 */         goals.addEntry(() -> new SamehadaShoteiWrapperGoal((MobEntity)this), 10.0F);
/* 189 */         goals.addEntry(() -> new MizuOsuWrapperGoal((MobEntity)this), 10.0F);
/* 190 */         if (isHardDifficulty || this.trackedRandom.nextInt(3) == 0) {
/* 191 */           goals.addEntry(() -> new MizuShuryudanWrapperGoal((MobEntity)this), 10.0F);
/*     */         } else {
/*     */           
/* 194 */           goals.addEntry(() -> new UchimizuWrapperGoal((MobEntity)this), 10.0F);
/*     */         }
/*     */       
/* 197 */       } else if (props.isMink()) {
/* 198 */         this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, MinkPassiveBonusesAbility.INSTANCE));
/* 199 */         goals.addEntry(() -> new EleclawWrapperGoal((MobEntity)this), 3.0F);
/* 200 */         goals.addEntry(() -> new ElectricalLunaWrapperGoal((MobEntity)this), 3.0F);
/* 201 */         goals.addEntry(() -> new ElectricalMissileWrapperGoal((MobEntity)this), 3.0F);
/* 202 */         goals.addEntry(() -> new ElectricalTempestaWrapperGoal((MobEntity)this), 3.0F);
/*     */       } 
/* 204 */       int racialAbilities = 3 + this.trackedRandom.nextInt(3 + (isHardDifficulty ? 2 : 0));
/* 205 */       MobsHelper.getRandomizedGoals((MobEntity)this, racialAbilities, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*     */       
/* 207 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 100);
/* 208 */       boolean hasAdvancedHaki = (isHardDifficulty || this.trackedRandom.nextInt(3) == 0 || getDoriki() >= 8000.0D);
/* 209 */       if (hasAdvancedHaki) {
/* 210 */         this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 211 */         this.field_70714_bg.func_75776_a(1, (Goal)new HaoshokuHakiInfusionWrapperGoal((MobEntity)this));
/* 212 */         this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/* 213 */         if (getDoriki() >= 7000.0D || this.trackedRandom.nextInt(3) == 0) {
/* 214 */           this.field_70714_bg.func_75776_a(1, (Goal)new KenbunshokuHakiFutureSightWrapperGoal((MobEntity)this));
/*     */         }
/*     */       } 
/*     */       
/* 218 */       int fruitChance = ((Integer)AbilitiesConfig.OPEN_WORLD_FRUIT_USERS.get()).intValue();
/* 219 */       if (fruitChance > 0 && this.trackedRandom.nextInt(100) <= fruitChance) {
/* 220 */         MobsHelper.getRandomDevilFruitAbilities((MobEntity)this).ifPresent(list -> list.forEach(()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static NotoriousEntity createMarine(EntityType<?> type, World world) {
/* 226 */     return new NotoriousEntity(type, world, ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static NotoriousEntity createPirate(EntityType<?> type, World world) {
/* 230 */     return new NotoriousEntity(type, world, ModValues.PIRATE);
/*     */   }
/*     */   
/*     */   public static NotoriousEntity createBandit(EntityType<?> type, World world) {
/* 234 */     return new NotoriousEntity(type, world, ModValues.BANDIT);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 238 */     return OPEntity.createAttributes()
/* 239 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 240 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 241 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 242 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(200, 250))
/* 243 */       .func_233815_a_(Attributes.field_233826_i_, 20.0D)
/* 244 */       .func_233815_a_(Attributes.field_233827_j_, 10.0D);
/*     */   }
/*     */   
/*     */   private void setCustomName(Random random) {
/* 248 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 249 */     if (props == null) {
/*     */       return;
/*     */     }
/*     */     
/* 253 */     if (props.isMarine()) {
/* 254 */       String name = MARINE_NAMES[random.nextInt(MARINE_NAMES.length)];
/* 255 */       func_200203_b((ITextComponent)new StringTextComponent("Vice Admiral " + name));
/*     */     
/*     */     }
/* 258 */     else if (random.nextInt(64) == 0) {
/* 259 */       if (random.nextBoolean()) {
/* 260 */         func_200203_b((ITextComponent)new StringTextComponent("Jack the Sparrow"));
/*     */       } else {
/*     */         
/* 263 */         func_200203_b((ITextComponent)new StringTextComponent("Captain Jack Sparrow"));
/*     */       } 
/*     */     } else {
/*     */       
/* 267 */       String name = PIRATE_NAMES[random.nextInt(PIRATE_NAMES.length)];
/* 268 */       String prefix = "";
/* 269 */       String suffix = "";
/* 270 */       if (random.nextInt(3) == 0) {
/*     */         
/* 272 */         if (random.nextBoolean()) {
/* 273 */           prefix = PIRATE_PRE_TITLES[random.nextInt(PIRATE_PRE_TITLES.length)] + " ";
/*     */           
/* 275 */           if (random.nextInt(4) == 0) {
/* 276 */             suffix = PIRATE_POST_TITLES[random.nextInt(PIRATE_POST_TITLES.length)];
/*     */           }
/*     */         } else {
/*     */           
/* 280 */           suffix = PIRATE_POST_TITLES[random.nextInt(PIRATE_POST_TITLES.length)];
/*     */         } 
/*     */       } else {
/*     */         
/* 284 */         prefix = "Captain ";
/*     */       } 
/* 286 */       func_200203_b((ITextComponent)new StringTextComponent(prefix + name + (suffix.isEmpty() ? "" : (" " + suffix))));
/*     */     } 
/*     */     
/* 289 */     this.bossInfo.func_186739_a(func_200201_e());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 294 */     this.bossInfo.tick((LivingEntity)this);
/* 295 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184178_b(ServerPlayerEntity player) {
/* 300 */     super.func_184178_b(player);
/*     */     
/* 302 */     if (this.bossInfo != null) {
/* 303 */       this.bossInfo.func_186760_a(player);
/*     */     }
/*     */     
/* 306 */     if (!this.isTriggered) {
/* 307 */       this.isTriggered = true;
/*     */     }
/*     */     
/* 310 */     this.bossInfo.func_186739_a(func_200201_e());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184203_c(ServerPlayerEntity player) {
/* 315 */     super.func_184203_c(player);
/*     */     
/* 317 */     if (this.bossInfo != null) {
/* 318 */       this.bossInfo.func_186761_b(player);
/*     */     }
/*     */     
/* 321 */     if (this.isTriggered) {
/* 322 */       this.isTriggered = false;
/*     */     }
/*     */   }
/*     */   
/*     */   private void setDetails() {
/* 327 */     IEntityStats props = getEntityStats();
/*     */     
/* 329 */     setCustomName(this.trackedRandom);
/*     */ 
/*     */     
/* 332 */     if (props.isPirate()) {
/* 333 */       setPirateDetails();
/*     */     }
/* 335 */     else if (props.isMarine()) {
/* 336 */       setMarineDetails();
/*     */     }
/* 338 */     else if (props.isBandit()) {
/* 339 */       setBanditDetails();
/*     */     } 
/* 341 */     chooseTexture(this.trackedRandom);
/*     */ 
/*     */     
/* 344 */     if (props.isSwordsman()) {
/* 345 */       setSwordsmanDetails();
/*     */     }
/* 347 */     else if (props.isBrawler()) {
/* 348 */       setBrawlerDetails();
/*     */     }
/* 350 */     else if (props.isBlackLeg()) {
/* 351 */       setBlackLegDetails();
/*     */     }
/* 353 */     else if (props.isSniper()) {
/* 354 */       setSniperDetails();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setSwordsmanDetails() {
/* 359 */     ItemStack randomSword = getRandomSword(this.trackedRandom, MELEE_FACTION_WEAPONS.get(getEntityStats().getFaction()));
/* 360 */     func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/*     */     
/* 362 */     float dualWeildChance = getEntityStats().isPirate() ? 0.4F : 0.2F;
/* 363 */     if (this.trackedRandom.nextFloat() < dualWeildChance) {
/* 364 */       func_184201_a(EquipmentSlotType.OFFHAND, randomSword.func_77946_l());
/*     */     }
/*     */     
/* 367 */     MobsHelper.addSwordsmanAbilities((MobEntity)this, 3 + this.trackedRandom.nextInt(3));
/*     */   }
/*     */   
/*     */   private void setBrawlerDetails() {
/* 371 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*     */     
/* 373 */     MobsHelper.addBrawlerAbilities((MobEntity)this, 3 + this.trackedRandom.nextInt(3));
/* 374 */     this.field_70714_bg.func_75776_a(3, (Goal)new ChargedPunchWrapperGoal((MobEntity)this));
/*     */   }
/*     */   
/*     */   private void setBlackLegDetails() {
/* 378 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BlackLegPassiveBonusesAbility.INSTANCE));
/*     */     
/* 380 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 381 */     goals.addEntry(() -> new PartyTableKickCourseWrapperGoal((MobEntity)this), 3.0F);
/* 382 */     goals.addEntry(() -> new AntiMannerKickCourseWrapperGoal((MobEntity)this), 3.0F);
/* 383 */     goals.addEntry(() -> new ConcasseWrapperGoal((MobEntity)this), 3.0F);
/* 384 */     goals.addEntry(() -> new ExtraHachisWrapperGoal((MobEntity)this), 3.0F);
/*     */     
/* 386 */     MobsHelper.getRandomizedGoals((MobEntity)this, 3 + this.trackedRandom.nextInt(3), goals).forEach(goal -> this.field_70714_bg.func_75776_a(3, goal));
/*     */   }
/*     */   
/*     */   private void setSniperDetails() {
/* 390 */     ItemStack randomWeapon = this.trackedRandom.nextBoolean() ? ((ModGunItem)ModWeapons.WALKER.get()).func_190903_i() : ((ModGunItem)ModWeapons.FLINTLOCK.get()).func_190903_i();
/* 391 */     func_184201_a(EquipmentSlotType.MAINHAND, randomWeapon);
/*     */     
/* 393 */     WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 394 */     goals.addEntry(() -> new KaenBoshiWrapperGoal((MobEntity)this, 20), 3.0F);
/* 395 */     goals.addEntry(() -> new SakuretsuSabotenBoshiWrapperGoal((MobEntity)this), 3.0F);
/* 396 */     goals.addEntry(() -> new TokuyoAburaBoshiWrapperGoal((MobEntity)this, 20), 3.0F);
/* 397 */     goals.addEntry(() -> new TetsuBoshiWrapperGoal((MobEntity)this), 2.0F);
/* 398 */     goals.addEntry(() -> new RenpatsuNamariBoshiWrapperGoal((MobEntity)this, 60), 2.0F);
/* 399 */     goals.addEntry(() -> new KemuriBoshiWrapperGoal((MobEntity)this), 1.0F);
/* 400 */     goals.addEntry(() -> new NemuriBoshiWrapperGoal((MobEntity)this, 60), 1.0F);
/*     */     
/* 402 */     MobsHelper.getRandomizedGoals((MobEntity)this, 3 + this.trackedRandom.nextInt(3), goals).forEach(goal -> this.field_70714_bg.func_75776_a(3, goal));
/*     */   }
/*     */   
/*     */   private void setMarineDetails() {
/* 406 */     setTextures(MobsHelper.MARINE_VICE_ADMIRAL_TEXTURES);
/* 407 */     ItemStack marineCapeStack = new ItemStack((IItemProvider)ModArmors.MARINE_CAPTAIN_CAPE.get());
/* 408 */     marineCapeStack.func_190925_c("display").func_74768_a("color", MobsHelper.MARINE_BLUE_COLOR.getRGB());
/* 409 */     func_184201_a(EquipmentSlotType.CHEST, marineCapeStack);
/*     */   }
/*     */   
/*     */   private void setPirateDetails() {
/* 413 */     setTextures(MobsHelper.PIRATE_CAPTAINS_TEXTURES);
/* 414 */     if (this.trackedRandom.nextFloat() < 0.4F) {
/* 415 */       ItemStack pirateCapeStack = new ItemStack((IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE.get());
/* 416 */       func_184201_a(EquipmentSlotType.CHEST, pirateCapeStack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBanditDetails() {
/* 421 */     setTextures(MobsHelper.BANDIT_TEXTURES);
/*     */   }
/*     */ 
/*     */   
/*     */   public Random getTrackedRandom() {
/* 426 */     return this.trackedRandom;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TrackedNPC getTrackedData() {
/* 432 */     return this.trackedData;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\NotoriousEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */