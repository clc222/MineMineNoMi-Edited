/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.PartyTableKickCourseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.supa.AtomicRushAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.supa.SparklingDaisyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.supa.SpiderAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.TakedownKickWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.ShiganWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.AtomicRushWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SparClawWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SparklingDaisyWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SpiderWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.supa.SpiralHollowWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.mr1.Mr1PhaseSwitcherGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class Mr1Entity extends OPBossEntity<Mr1Entity> {
/*  56 */   private static final UUID STANDARD_SPIDER_COOLDOWN_BONUS_UUID = UUID.fromString("1f2944fa-fd28-41b9-8f67-392929011bbd");
/*  57 */   private static final AttributeModifier GCD_MOD = new AttributeModifier(UUID.fromString("e478aceb-9865-40da-a137-6359ba503bf0"), "GCD Modifier", -5.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   private NPCPhase<Mr1Entity> firstPhase;
/*     */   
/*     */   private NPCPhase<Mr1Entity> secondPhase;
/*     */   private NPCPhase<Mr1Entity> ultiPhase;
/*     */   private SpiderWrapperGoal spiderWrapper;
/*     */   private AtomicRushAbility atomicRushAbility;
/*     */   
/*     */   public Mr1Entity(EntityType type, World world) {
/*  67 */     super(type, world);
/*     */   }
/*     */   
/*     */   public Mr1Entity(InProgressChallenge challenge) {
/*  71 */     super((EntityType)ModEntities.MR1.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  76 */     this.firstPhase = (NPCPhase<Mr1Entity>)new SimplePhase("First Phase", (MobEntity)this, this::startFirstPhaseEvent, this::stopFirstPhaseEvent);
/*  77 */     this.secondPhase = (NPCPhase<Mr1Entity>)new SimplePhase("Second Phase", (MobEntity)this, this::startSecondPhaseEvent);
/*  78 */     this.ultiPhase = (NPCPhase<Mr1Entity>)new SimplePhase("Ultimate Phase", (MobEntity)this);
/*     */     
/*  80 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  82 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  83 */     this.entityStats.setRace(ModValues.HUMAN);
/*  84 */     this.devilFruitData.setDevilFruit(ModAbilities.SUPA_SUPA_NO_MI);
/*     */     
/*  86 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*     */     
/*  88 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/*  89 */     func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(6.0D);
/*  90 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */ 
/*     */     
/*  93 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */ 
/*     */     
/*  96 */     PartyTableKickCourseWrapperGoal partyTableWrapper = new PartyTableKickCourseWrapperGoal((MobEntity)this);
/*     */     
/*  98 */     this.spiderWrapper = new SpiderWrapperGoal((MobEntity)this);
/*  99 */     ((SpiderAbility)this.spiderWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.addTickEvent(()));
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
/* 110 */     SparklingDaisyWrapperGoal sparklingDaisyWrapper = new SparklingDaisyWrapperGoal((MobEntity)this);
/* 111 */     ((SparklingDaisyAbility)sparklingDaisyWrapper.getAbility()).setProjectilesAmount(getChallengeInfo().isDifficultyHard() ? 2 : 1);
/*     */     
/* 113 */     AtomicRushWrapperGoal atomicRushWrapper = new AtomicRushWrapperGoal((MobEntity)this);
/* 114 */     this.atomicRushAbility = (AtomicRushAbility)atomicRushWrapper.getAbility();
/*     */     
/* 116 */     SprintTowardsTargetGoal sprintGoal = new SprintTowardsTargetGoal((MobEntity)this);
/*     */     
/* 118 */     TakedownKickWrapperGoal takedownKickGoal = new TakedownKickWrapperGoal((MobEntity)this);
/*     */     
/* 120 */     SparClawWrapperGoal sparClawGoal = new SparClawWrapperGoal((MobEntity)this);
/*     */     
/* 122 */     SpiralHollowWrapperGoal spiralHollowGoal = new SpiralHollowWrapperGoal((MobEntity)this);
/*     */ 
/*     */     
/* 125 */     this.field_70714_bg.func_75776_a(0, (Goal)new Mr1PhaseSwitcherGoal(this));
/* 126 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*     */ 
/*     */     
/* 129 */     this.firstPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 130 */     this.firstPhase.addGoal(1, (Goal)sprintGoal);
/* 131 */     this.firstPhase.addGoal(2, (Goal)takedownKickGoal);
/* 132 */     this.firstPhase.addGoal(3, (Goal)sparClawGoal);
/* 133 */     this.firstPhase.addGoal(3, (Goal)this.spiderWrapper);
/* 134 */     this.firstPhase.addGoal(3, (Goal)spiralHollowGoal);
/* 135 */     this.firstPhase.addGoal(4, (Goal)partyTableWrapper);
/*     */ 
/*     */     
/* 138 */     this.secondPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 2.5F));
/* 139 */     this.secondPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.2D, true));
/* 140 */     this.secondPhase.addGoal(1, (Goal)sprintGoal);
/* 141 */     this.secondPhase.addGoal(2, (Goal)takedownKickGoal);
/* 142 */     this.secondPhase.addGoal(3, (Goal)sparClawGoal);
/* 143 */     this.secondPhase.addGoal(3, (Goal)this.spiderWrapper);
/* 144 */     this.secondPhase.addGoal(3, (Goal)spiralHollowGoal);
/* 145 */     this.secondPhase.addGoal(4, (Goal)partyTableWrapper);
/* 146 */     this.secondPhase.addGoal(4, (Goal)sparklingDaisyWrapper);
/*     */ 
/*     */     
/* 149 */     this.ultiPhase.addGoal(1, (Goal)atomicRushWrapper);
/*     */ 
/*     */     
/* 152 */     getPhaseManager().setPhase(this.firstPhase);
/*     */     
/* 154 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 155 */       this.entityStats.setDoriki(2000.0D);
/* 156 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 157 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(5.0D);
/* 158 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 160 */       this.atomicRushAbility.setDashWaitTime(10);
/*     */       
/* 162 */       ((SpiderAbility)this.spiderWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_SPIDER_COOLDOWN_BONUS_UUID, "Standard Spider Cooldown Bonus", BonusOperation.MUL, 1.75F));
/*     */ 
/*     */ 
/*     */       
/* 166 */       this.secondPhase.addGoal(3, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/*     */     } else {
/*     */       
/* 169 */       this.entityStats.setDoriki(10000.0D);
/* 170 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 171 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 172 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 173 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(5.0D);
/* 174 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(5.0D);
/* 175 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(8.0D);
/* 176 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(400.0D);
/* 177 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 178 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 179 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 181 */       HakaiHoWrapperGoal hakaiHoWrapper = new HakaiHoWrapperGoal((MobEntity)this);
/* 182 */       ((HakaiHoAbility)hakaiHoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 60.0F));
/*     */ 
/*     */ 
/*     */       
/* 186 */       ShiganWrapperGoal shiganWrapper = new ShiganWrapperGoal((MobEntity)this);
/*     */       
/* 188 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 189 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/* 191 */       this.firstPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 2.5F));
/* 192 */       this.firstPhase.addGoal(3, (Goal)hakaiHoWrapper);
/* 193 */       this.firstPhase.addGoal(3, (Goal)shiganWrapper);
/* 194 */       this.firstPhase.addGoal(3, (Goal)new RankyakuWrapperGoal((MobEntity)this));
/*     */       
/* 196 */       this.secondPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 150.0F, 2.5F));
/* 197 */       this.secondPhase.addGoal(3, (Goal)hakaiHoWrapper);
/* 198 */       this.secondPhase.addGoal(3, (Goal)shiganWrapper);
/* 199 */       this.secondPhase.addGoal(3, (Goal)new RankyakuWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 204 */     return OPEntity.createAttributes()
/* 205 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 206 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 207 */       .func_233815_a_(Attributes.field_233823_f_, 5.0D)
/* 208 */       .func_233815_a_(Attributes.field_233818_a_, 300.0D)
/* 209 */       .func_233815_a_(Attributes.field_233826_i_, 4.0D)
/* 210 */       .func_233815_a_(Attributes.field_233827_j_, 2.0D)
/* 211 */       .func_233815_a_(Attributes.field_233820_c_, 0.8D);
/*     */   }
/*     */   
/*     */   public void startUltiPhase() {
/* 215 */     getPhaseManager().setPhase(this.ultiPhase);
/*     */   }
/*     */   
/*     */   public void startSecondPhase() {
/* 219 */     getPhaseManager().setPhase(this.secondPhase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 224 */     super.func_70071_h_();
/* 225 */     if (!this.field_70170_p.field_72995_K && 
/* 226 */       func_70638_az() != null) {
/* 227 */       boolean canSee = func_70685_l((Entity)func_70638_az());
/* 228 */       double yDiff = Math.abs(func_226278_cu_() - func_70638_az().func_226278_cu_());
/* 229 */       if (yDiff > 20.0D && !canSee) {
/* 230 */         BlockPos tpPos = WyHelper.findValidGroundLocation((Entity)this, func_70638_az().func_233580_cy_(), 10, 10);
/* 231 */         func_223102_j(tpPos.func_177958_n(), (tpPos.func_177956_o() + 5), tpPos.func_177952_p());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_226292_a_(Hand hand, boolean updateSelf) {
/* 239 */     if (func_70638_az() != null) {
/* 240 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)this.field_70170_p, func_70638_az().func_226277_ct_(), func_70638_az().func_226278_cu_() + 
/* 241 */           func_70638_az().func_70047_e(), func_70638_az().func_226281_cx_());
/* 242 */       this.field_70170_p.func_184133_a(null, func_70638_az().func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     } 
/* 244 */     super.func_226292_a_(hand, updateSelf);
/*     */   }
/*     */   
/*     */   public boolean isUltiAvailable() {
/* 248 */     return !((Boolean)this.atomicRushAbility.getComponent(ModAbilityKeys.COOLDOWN).map(CooldownComponent::isOnCooldown).orElse(Boolean.valueOf(true))).booleanValue();
/*     */   }
/*     */   
/*     */   public double getUltiTicks() {
/* 252 */     return ((Float)this.atomicRushAbility.getComponent(ModAbilityKeys.CONTINUOUS).map(ContinuousComponent::getThresholdTime).orElse(Float.valueOf(-1.0F))).floatValue();
/*     */   }
/*     */   
/*     */   private void startFirstPhaseEvent(Mr1Entity entity) {
/* 256 */     if (isDifficultyHardOrAbove()) {
/* 257 */       ((SpiderAbility)this.spiderWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_SPIDER_COOLDOWN_BONUS_UUID, "Standard Spider Cooldown Bonus", BonusOperation.MUL, 1.5F));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void stopFirstPhaseEvent(Mr1Entity entity) {
/* 264 */     if (isDifficultyHardOrAbove()) {
/* 265 */       ((SpiderAbility)this.spiderWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().removeBonus(STANDARD_SPIDER_COOLDOWN_BONUS_UUID));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void startSecondPhaseEvent(Mr1Entity entity) {
/* 272 */     if (isDifficultyHardOrAbove()) {
/* 273 */       ModifiableAttributeInstance attr = entity.func_110148_a((Attribute)ModAttributes.GCD.get());
/* 274 */       if (attr != null && !attr.func_180374_a(GCD_MOD))
/* 275 */         attr.func_233767_b_(GCD_MOD); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\Mr1Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */