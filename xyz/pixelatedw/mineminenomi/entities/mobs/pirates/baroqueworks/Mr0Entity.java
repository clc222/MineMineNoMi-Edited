/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.HookGrabAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.DesertEncierroAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.DesertGrandeEspadaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.GroundSeccoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.SablesGuardAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.SablesPesadoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.suna.SunaLogiaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.HookGrabWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.KenbunshokuHakiFutureSightWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.BarjanWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.DesertEncierroWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.DesertGrandeEspadaWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.DesertSpadaWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.GroundSeccoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.SablesGuardWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.suna.SablesPesadoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.mr0.Mr0PhaseSwitcherGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class Mr0Entity extends OPBossEntity<Mr0Entity> {
/*  62 */   private static final UUID HARD_GRANDE_SPADA_COOLDOWN_BONUS = UUID.fromString("5f6e5fd9-7292-42f0-895c-f0b3ff8da686");
/*  63 */   private static final UUID HARD_SABLES_GUARD_COOLDOWN_BONUS = UUID.fromString("c467addb-27c4-4865-9a01-655c621690e8");
/*  64 */   private static final UUID HARD_SABLES_PESADO_CHARGE_BONUS = UUID.fromString("8f324bfd-f8cd-434d-9467-3001fe7c92df");
/*  65 */   private static final AttributeModifier GCD_MOD = new AttributeModifier(UUID.fromString("e478aceb-9865-40da-a137-6359ba503bf0"), "GCD Modifier", -5.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   private static final int STANDARD_PAIN_THRESHOLD = 100;
/*     */   
/*     */   private static final int HARD_PAIN_THRESHOLD = 150;
/*     */   private static final int PAIN_GUARD_COOLDOWN = 200;
/*     */   private static final int KITING_THRESHOLD = 20;
/*  72 */   private final NPCPhase<Mr0Entity> firstPhase = (NPCPhase<Mr0Entity>)new SimplePhase("First Phase", (MobEntity)this);
/*  73 */   private final NPCPhase<Mr0Entity> secondPhase = (NPCPhase<Mr0Entity>)new SimplePhase("Second Phase", (MobEntity)this, this::startSecondPhaseEvent);
/*  74 */   private final NPCPhase<Mr0Entity> thirdPhase = (NPCPhase<Mr0Entity>)new SimplePhase("Third Phase", (MobEntity)this, this::startThirdPhaseEvent);
/*     */   
/*  76 */   private final RevengeMeter painMeter = new RevengeMeter((LivingEntity)this, 999, 0);
/*  77 */   private final RevengeMeter kitingMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */   
/*     */   private HookGrabWrapperGoal hookGrabWrapper;
/*     */   
/*     */   private SablesGuardWrapperGoal sablesGuardWrapper;
/*     */   private GroundSeccoAbility groundSeccoAbility;
/*  83 */   private int painThreshold = 100;
/*     */   
/*     */   public Mr0Entity(EntityType type, World world) {
/*  86 */     super(type, world);
/*     */   }
/*     */   
/*     */   public Mr0Entity(InProgressChallenge challenge) {
/*  90 */     super((EntityType)ModEntities.MR0.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  95 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  97 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  98 */     this.entityStats.setRace(ModValues.HUMAN);
/*  99 */     this.devilFruitData.setDevilFruit(ModAbilities.SUNA_SUNA_NO_MI);
/*     */     
/* 101 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*     */     
/* 103 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/* 104 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     
/* 106 */     ItemStack capeStack = new ItemStack((IItemProvider)ModArmors.FLUFFY_CAPE.get());
/* 107 */     capeStack.func_190925_c("display").func_74768_a("color", WyHelper.hexToRGB("#1F2121").getRGB());
/* 108 */     func_184201_a(EquipmentSlotType.CHEST, capeStack);
/* 109 */     func_184201_a(EquipmentSlotType.OFFHAND, ((ModSwordItem)ModWeapons.HOOK.get()).func_190903_i());
/* 110 */     func_184201_a(EquipmentSlotType.HEAD, ((Item)ModItems.CIGAR.get()).func_190903_i());
/*     */ 
/*     */     
/* 113 */     this.painMeter.addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(1));
/* 114 */     this.painMeter.addCheck((IRevengeCheck)new PhysicalDamageDealtRevengeCheck());
/*     */ 
/*     */     
/* 117 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */ 
/*     */     
/* 120 */     BarjanWrapperGoal barjanWrapper = new BarjanWrapperGoal((MobEntity)this);
/*     */     
/* 122 */     DesertEncierroWrapperGoal desertEncierroWrapper = new DesertEncierroWrapperGoal((MobEntity)this);
/* 123 */     ((DesertEncierroAbility)desertEncierroWrapper.getAbility()).addCanUseCheck((entity, ability) -> ((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).isContinuous() ? AbilityUseResult.fail(null) : (GoalUtil.hasEnoughTargetsAround((MobEntity)this, 2, 20.0F) ? AbilityUseResult.fail(null) : AbilityUseResult.success()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     DesertGrandeEspadaWrapperGoal desertGrandeEspadaWrapper = new DesertGrandeEspadaWrapperGoal((MobEntity)this);
/* 134 */     ((DesertGrandeEspadaAbility)desertGrandeEspadaWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 400.0F));
/*     */     
/* 136 */     this.hookGrabWrapper = new HookGrabWrapperGoal((MobEntity)this);
/*     */     
/* 138 */     GroundSeccoWrapperGoal groundSeccoWrapper = new GroundSeccoWrapperGoal((MobEntity)this);
/* 139 */     this.groundSeccoAbility = (GroundSeccoAbility)groundSeccoWrapper.getAbility();
/* 140 */     this.groundSeccoAbility.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 600.0F));
/* 141 */     this.groundSeccoAbility.addCanUseCheck((entity, ability) -> ((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).isContinuous() ? AbilityUseResult.fail(null) : (((HookGrabAbility)this.hookGrabWrapper.getAbility()).isContinuous() ? AbilityUseResult.fail(null) : AbilityUseResult.success()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     SablesPesadoWrapperGoal sablesPesadoWrapper = new SablesPesadoWrapperGoal((MobEntity)this);
/*     */     
/* 153 */     DesertSpadaWrapperGoal desertSpadaWrapper = new DesertSpadaWrapperGoal((MobEntity)this);
/* 154 */     ((DesertSpadaAbility)desertSpadaWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 200.0F));
/*     */     
/* 156 */     this.sablesGuardWrapper = new SablesGuardWrapperGoal((MobEntity)this);
/* 157 */     ((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).addCanUseCheck((entity, ability) -> ((DesertEncierroAbility)desertEncierroWrapper.getAbility()).isContinuous() ? AbilityUseResult.fail(null) : AbilityUseResult.success());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     ImprovedMeleeAttackGoal meleeAttackGoal = new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true);
/* 165 */     meleeAttackGoal.setEarlyStop(() -> Boolean.valueOf(
/* 166 */           (((GroundSeccoAbility)groundSeccoWrapper.getAbility()).isCharging() || ((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).isContinuous() || ((SablesPesadoAbility)sablesPesadoWrapper.getAbility()).isCharging() || ((DesertEncierroAbility)desertEncierroWrapper.getAbility()).isCharging() || ((HookGrabAbility)this.hookGrabWrapper.getAbility()).isContinuous())));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     this.field_70714_bg.func_75776_a(0, (Goal)new Mr0PhaseSwitcherGoal(this));
/* 172 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, SunaLogiaAbility.INSTANCE));
/* 173 */     this.field_70714_bg.func_75776_a(0, (Goal)this.hookGrabWrapper);
/* 174 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*     */ 
/*     */     
/* 177 */     this.firstPhase.addGoal(1, (Goal)meleeAttackGoal);
/* 178 */     this.firstPhase.addGoal(3, (Goal)barjanWrapper);
/* 179 */     this.firstPhase.addGoal(3, (Goal)this.sablesGuardWrapper);
/*     */ 
/*     */ 
/*     */     
/* 183 */     this.secondPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 300.0F, 2.0F));
/* 184 */     this.secondPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 300.0F, 2.0F));
/* 185 */     this.secondPhase.addGoal(1, (Goal)meleeAttackGoal);
/* 186 */     this.secondPhase.addGoal(3, (Goal)barjanWrapper);
/* 187 */     this.secondPhase.addGoal(3, (Goal)this.sablesGuardWrapper);
/*     */     
/* 189 */     this.secondPhase.addGoal(3, (Goal)desertEncierroWrapper);
/* 190 */     this.secondPhase.addGoal(3, (Goal)groundSeccoWrapper);
/* 191 */     this.secondPhase.addGoal(3, (Goal)desertGrandeEspadaWrapper);
/*     */     
/* 193 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 194 */       this.entityStats.setDoriki(2000.0D);
/* 195 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 196 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 197 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 199 */       getPhaseManager().setPhase(this.firstPhase);
/*     */       
/* 201 */       ((HookGrabAbility)this.hookGrabWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 400.0F));
/*     */     } else {
/*     */       
/* 204 */       this.entityStats.setDoriki(10000.0D);
/* 205 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 206 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 207 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 208 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(6.0D);
/* 209 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 210 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(400.0D);
/* 211 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 212 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 213 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 215 */       this.painThreshold = 150;
/*     */       
/* 217 */       this.kitingMeter.addCheck((IRevengeCheck)new TargetFlyStallingRevengeCheck(1));
/* 218 */       this.kitingMeter.addCheck((IRevengeCheck)new TargetRunningAwayRevengeCheck(1, 10.0F));
/*     */       
/* 220 */       HakaiHoWrapperGoal hakaiHoWrapper = new HakaiHoWrapperGoal((MobEntity)this);
/* 221 */       ((HakaiHoAbility)hakaiHoWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */             DesertEncierroAbility abl = (DesertEncierroAbility)AbilityDataCapability.get(entity).getEquippedAbility(DesertEncierroAbility.INSTANCE);
/* 223 */             return (abl != null && abl.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 228 */       ((HakaiHoAbility)hakaiHoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 200.0F));
/*     */       
/* 230 */       KamieWrapperGoal kamieWrapper = new KamieWrapperGoal((MobEntity)this);
/* 231 */       ((KamieAbility)kamieWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */             if (this.groundSeccoAbility.isOnCooldown()) {
/*     */               float cooldown = ((Float)this.groundSeccoAbility.getComponent(ModAbilityKeys.COOLDOWN).map(()).orElse(Float.valueOf(0.0F))).floatValue();
/*     */               
/*     */               if (cooldown <= 100.0F) {
/*     */                 return AbilityUseResult.fail(null);
/*     */               }
/*     */             } 
/*     */             
/*     */             return AbilityUseResult.success();
/*     */           });
/* 242 */       ((SablesPesadoAbility)sablesPesadoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_SABLES_PESADO_CHARGE_BONUS, "Hard Sables Pesado Charge Bonus", BonusOperation.ADD, -40.0F));
/*     */ 
/*     */ 
/*     */       
/* 246 */       ((DesertGrandeEspadaAbility)desertGrandeEspadaWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_GRANDE_SPADA_COOLDOWN_BONUS, "Hard Grande Spada Cooldown Bonus", BonusOperation.ADD, -100.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 254 */       this.groundSeccoAbility.setSizeModifier(1.5F);
/*     */       
/* 256 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 257 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */ 
/*     */       
/* 260 */       this.secondPhase.addGoal(0, (Goal)new KenbunshokuHakiFutureSightWrapperGoal((MobEntity)this));
/* 261 */       this.secondPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 250.0F, 2.5F));
/* 262 */       this.secondPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 250.0F, 2.5F));
/* 263 */       this.secondPhase.addGoal(0, (Goal)kamieWrapper);
/* 264 */       this.secondPhase.addGoal(3, (Goal)hakaiHoWrapper);
/*     */ 
/*     */       
/* 267 */       this.thirdPhase.addGoal(0, (Goal)new KenbunshokuHakiFutureSightWrapperGoal((MobEntity)this));
/* 268 */       this.thirdPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 250.0F, 2.5F));
/* 269 */       this.thirdPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 250.0F, 2.5F));
/* 270 */       this.thirdPhase.addGoal(0, (Goal)kamieWrapper);
/* 271 */       this.thirdPhase.addGoal(1, (Goal)meleeAttackGoal);
/* 272 */       this.thirdPhase.addGoal(3, (Goal)hakaiHoWrapper);
/* 273 */       this.thirdPhase.addGoal(3, (Goal)barjanWrapper);
/* 274 */       this.thirdPhase.addGoal(3, (Goal)this.sablesGuardWrapper);
/*     */       
/* 276 */       this.thirdPhase.addGoal(3, (Goal)desertEncierroWrapper);
/* 277 */       this.thirdPhase.addGoal(3, (Goal)groundSeccoWrapper);
/* 278 */       this.thirdPhase.addGoal(3, (Goal)sablesPesadoWrapper);
/* 279 */       this.thirdPhase.addGoal(3, (Goal)desertGrandeEspadaWrapper);
/*     */       
/* 281 */       getPhaseManager().setPhase(this.secondPhase);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 286 */     return OPEntity.createAttributes()
/* 287 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 288 */       .func_233815_a_(Attributes.field_233821_d_, 0.2800000011920929D)
/* 289 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 290 */       .func_233815_a_(Attributes.field_233818_a_, 300.0D)
/* 291 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D)
/* 292 */       .func_233815_a_(Attributes.field_233820_c_, 0.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 297 */     super.func_70071_h_();
/*     */     
/* 299 */     if (!this.field_70170_p.field_72995_K) {
/* 300 */       this.painMeter.tick();
/* 301 */       this.kitingMeter.tick();
/*     */       
/* 303 */       if (!((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).isContinuous() && this.painMeter.getRevengeValue() > this.painThreshold && this.sablesGuardWrapper
/* 304 */         .hasTimePassedSinceLastEnd(200.0F)) {
/* 305 */         this.painMeter.addRevengeValue(-this.painThreshold);
/* 306 */         ((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)this));
/* 307 */         ((SablesGuardAbility)this.sablesGuardWrapper.getAbility()).use((LivingEntity)this);
/*     */       } 
/*     */       
/* 310 */       if (!((HookGrabAbility)this.hookGrabWrapper.getAbility()).isContinuous() && this.kitingMeter.getRevengeValue() > 20) {
/* 311 */         this.kitingMeter.addRevengeValue(-20);
/* 312 */         ((HookGrabAbility)this.hookGrabWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)this));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startSecondPhase() {
/* 318 */     getPhaseManager().setPhase(this.secondPhase);
/*     */   }
/*     */   
/*     */   public void startThirdPhase() {
/* 322 */     getPhaseManager().setPhase(this.thirdPhase);
/*     */   }
/*     */   
/*     */   public boolean isFirstPhaseActive() {
/* 326 */     return this.firstPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean isSecondPhaseActive() {
/* 330 */     return this.secondPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean isThirdPhaseActive() {
/* 334 */     return this.thirdPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   private void startSecondPhaseEvent(Mr0Entity entity) {
/* 338 */     if (isDifficultyHardOrAbove()) {
/* 339 */       ModifiableAttributeInstance attr = entity.func_110148_a((Attribute)ModAttributes.GCD.get());
/* 340 */       if (attr != null && !attr.func_180374_a(GCD_MOD)) {
/* 341 */         attr.func_233767_b_(GCD_MOD);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startThirdPhaseEvent(Mr0Entity entity) {
/* 347 */     this.groundSeccoAbility.setSizeModifier(2.0F);
/*     */   }
/*     */   
/*     */   public RevengeMeter getPainMeter() {
/* 351 */     return this.painMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\Mr0Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */