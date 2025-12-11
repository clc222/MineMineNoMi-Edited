/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleLockAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsMoriAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruNoYakataAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.TokudaiCandleAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayFromTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.CandleChampionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.CandleLockWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruArtsKenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruArtsMoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruBallWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.DoruDoruNoYakataWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.doru.TokudaiCandleWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.mr3.Mr3PhaseSwitcherGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class Mr3Entity extends OPBossEntity<Mr3Entity> {
/*  50 */   private static final UUID TOKUDAI_YAKATA_COOLDOWN_BONUS = UUID.fromString("26687dbb-506c-4531-996c-ee35db1a2bb3");
/*  51 */   private static final UUID DORU_ARTS_MORI_COOLDOWN_BONUS = UUID.fromString("9b7ff21e-639a-4f1a-9919-6748fc9cf8b2");
/*  52 */   private static final UUID CHAMPION_FAUX_PROT_BONUS = UUID.fromString("6f0e2075-2051-4a3d-8854-0ea55e29b4e9");
/*  53 */   private static final UUID CHAMPION_TOUGHNESS_BONUS = UUID.fromString("ba4237ab-4e77-4ccb-85bd-71a81d248d45");
/*     */   
/*     */   private boolean hasColorPaletteBonus;
/*     */   
/*     */   private NPCPhase<Mr3Entity> standardPhase;
/*     */   private NPCPhase<Mr3Entity> championPhase;
/*     */   private NPCPhase<Mr3Entity> tokudaiPhase;
/*     */   private NPCPhase<Mr3Entity> tauntPhase;
/*     */   private DoruDoruNoYakataWrapperGoal doruYakataWrapper;
/*     */   private TokudaiCandleWrapperGoal tokudaiCandleWrapper;
/*     */   private CandleChampionWrapperGoal candleChampionWrapper;
/*     */   private DoruDoruArtsMoriWrapperGoal doruDoruArtsMoriWrapper;
/*     */   
/*     */   public Mr3Entity(EntityType type, World world) {
/*  67 */     super(type, world);
/*     */   }
/*     */   
/*     */   public Mr3Entity(InProgressChallenge challenge) {
/*  71 */     super((EntityType)ModEntities.MR3.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initStats() {
/*  76 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  78 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  79 */     this.entityStats.setRace(ModValues.HUMAN);
/*  80 */     this.devilFruitData.setDevilFruit(ModAbilities.DORU_DORU_NO_MI);
/*     */     
/*  82 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*     */     
/*  84 */     this.entityStats.setDoriki(2000.0D);
/*  85 */     func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  86 */     func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(5.0D);
/*  87 */     func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*  88 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(1.0D);
/*  89 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     
/*  91 */     if (isDifficultyHardOrAbove()) {
/*  92 */       this.entityStats.setDoriki(10000.0D);
/*  93 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/*  94 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/*  95 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/*  96 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(5.0D);
/*  97 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(5.0D);
/*  98 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(6.0D);
/*  99 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(400.0D);
/* 100 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 101 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 102 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(12.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGoals() {
/* 108 */     this.standardPhase = (NPCPhase<Mr3Entity>)new SimplePhase("Standard Phase", (MobEntity)this);
/* 109 */     this.championPhase = (NPCPhase<Mr3Entity>)new SimplePhase("Champion Phase", (MobEntity)this, this::onStartChampionPhaseEvent, this::onStopChampionPhaseEvent);
/* 110 */     this.tokudaiPhase = (NPCPhase<Mr3Entity>)new SimplePhase("Tokudai Phase", (MobEntity)this, this::onStartTokudaiPhaseEvent, this::onStopTokudaiPhaseEvent);
/* 111 */     this.tauntPhase = (NPCPhase<Mr3Entity>)new SimplePhase("Taunt Phase", (MobEntity)this, this::onStartTauntPhaseEvent, this::onStopTauntPhaseEvent);
/*     */     
/* 113 */     this.doruYakataWrapper = new DoruDoruNoYakataWrapperGoal((MobEntity)this);
/*     */     
/* 115 */     this.tokudaiCandleWrapper = new TokudaiCandleWrapperGoal((MobEntity)this);
/* 116 */     ((TokudaiCandleAbility)this.tokudaiCandleWrapper.getAbility()).setBlocksPerTick(100);
/*     */     
/* 118 */     this.candleChampionWrapper = new CandleChampionWrapperGoal((MobEntity)this);
/*     */     
/* 120 */     this.doruDoruArtsMoriWrapper = new DoruDoruArtsMoriWrapperGoal((MobEntity)this);
/*     */     
/* 122 */     DoruDoruBallWrapperGoal doruBallWrapper = new DoruDoruBallWrapperGoal((MobEntity)this);
/*     */     
/* 124 */     CandleLockWrapperGoal candleLockWrapper = new CandleLockWrapperGoal((MobEntity)this);
/* 125 */     ((CandleLockAbility)candleLockWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 60.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 132 */     this.field_70714_bg.func_75776_a(0, (Goal)new Mr3PhaseSwitcherGoal(this));
/* 133 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*     */ 
/*     */     
/* 136 */     this.standardPhase.addGoal(1, (Goal)new DashDodgeTargetGoal((MobEntity)this, 100.0F, 3.0F));
/* 137 */     this.standardPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 138 */     this.standardPhase.addGoal(2, (Goal)new CandleWallWrapperGoal((MobEntity)this));
/* 139 */     this.standardPhase.addGoal(2, (Goal)doruBallWrapper);
/* 140 */     this.standardPhase.addGoal(3, (Goal)new DoruDoruArtsKenWrapperGoal((MobEntity)this));
/* 141 */     this.standardPhase.addGoal(3, (Goal)this.doruDoruArtsMoriWrapper);
/* 142 */     this.standardPhase.addGoal(3, (Goal)candleLockWrapper);
/* 143 */     this.standardPhase.addGoal(3, (Goal)this.doruYakataWrapper);
/*     */ 
/*     */     
/* 146 */     this.championPhase.addGoal(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 147 */     this.championPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.100000023841858D, true));
/* 148 */     this.championPhase.addGoal(3, (Goal)this.candleChampionWrapper);
/* 149 */     this.championPhase.addGoal(4, (Goal)new CandleLockWrapperGoal((MobEntity)this));
/* 150 */     this.championPhase.addGoal(4, (Goal)new DoruDoruArtsMoriWrapperGoal((MobEntity)this));
/*     */ 
/*     */     
/* 153 */     this.tokudaiPhase.addGoal(0, (Goal)new RunAwayFromTargetGoal((CreatureEntity)this, 1.7D, 500, 250));
/* 154 */     this.tokudaiPhase.addGoal(3, (Goal)this.doruYakataWrapper);
/* 155 */     this.tokudaiPhase.addGoal(4, (Goal)this.tokudaiCandleWrapper);
/*     */ 
/*     */     
/* 158 */     getPhaseManager().setPhase(this.standardPhase);
/*     */     
/* 160 */     if (isDifficultyStandard()) {
/* 161 */       this.standardPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 3.0F));
/*     */       
/* 163 */       this.tokudaiPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 100.0F, 3.0F));
/*     */     } else {
/*     */       
/* 166 */       doruBallWrapper.setMinTriggerHits(3);
/*     */       
/* 168 */       ((TokudaiCandleAbility)this.tokudaiCandleWrapper.getAbility()).setBlocksPerTick(200);
/*     */       
/* 170 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 171 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/* 173 */       this.standardPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 100.0F, 3.0F));
/* 174 */       this.standardPhase.addGoal(3, (Goal)new ShiganWrapperGoal((MobEntity)this));
/*     */ 
/*     */       
/* 177 */       this.tauntPhase.addGoal(0, (Goal)new TauntTargetGoal((MobEntity)this));
/* 178 */       this.tauntPhase.addGoal(1, (Goal)(new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true)).setMoveTowardsTarget(false));
/* 179 */       this.tauntPhase.addGoal(1, (Goal)new DashDodgeTargetGoal((MobEntity)this, 60.0F, 4.5F));
/* 180 */       this.tauntPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 20.0F, 4.5F));
/* 181 */       this.tauntPhase.addGoal(3, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 182 */       this.tauntPhase.addGoal(3, (Goal)this.doruDoruArtsMoriWrapper);
/*     */       
/* 184 */       this.championPhase.addGoal(3, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/*     */       
/* 186 */       this.tokudaiPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 80.0F, 4.0F));
/* 187 */       this.tokudaiPhase.addGoal(3, (Goal)new KamieWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 192 */     return OPEntity.createAttributes()
/* 193 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 194 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 195 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 196 */       .func_233815_a_(Attributes.field_233818_a_, 300.0D)
/* 197 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 202 */     super.func_70071_h_();
/* 203 */     if (!this.field_70170_p.field_72995_K && func_70638_az() != null && func_70638_az().func_70089_S() && 
/* 204 */       isDifficultyHardOrAbove()) {
/* 205 */       if (this.standardPhase.isActive((IPhasesEntity)this) && func_70638_az().func_70644_a((Effect)ModEffects.CANDLE_LOCK.get()) && GoalUtil.hasHealthAbovePercentage((LivingEntity)this, 70.0D)) {
/* 206 */         getPhaseManager().setPhase(this.tauntPhase);
/*     */       }
/* 208 */       else if (this.tauntPhase.isActive((IPhasesEntity)this) && !func_70638_az().func_70644_a((Effect)ModEffects.CANDLE_LOCK.get())) {
/* 209 */         getPhaseManager().setPhase(this.standardPhase);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasColorPaletteBonus() {
/* 216 */     return this.hasColorPaletteBonus;
/*     */   }
/*     */   
/*     */   public boolean isStandardPhase() {
/* 220 */     return this.standardPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean isChampionPhase() {
/* 224 */     return this.championPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean isTokudaiPhase() {
/* 228 */     return this.tokudaiPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public void startStandardPhase() {
/* 232 */     getPhaseManager().setPhase(this.standardPhase);
/*     */   }
/*     */   
/*     */   public void startChampionPhase() {
/* 236 */     getPhaseManager().setPhase(this.championPhase);
/*     */   }
/*     */   
/*     */   public void startTokudaiPhase() {
/* 240 */     getPhaseManager().setPhase(this.tokudaiPhase);
/*     */   }
/*     */   
/*     */   public void onStartChampionPhaseEvent(Mr3Entity entity) {
/* 244 */     func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_233767_b_(new AttributeModifier(CHAMPION_FAUX_PROT_BONUS, "Faux Prot Bonus", 4.0D, AttributeModifier.Operation.ADDITION));
/* 245 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_233767_b_(new AttributeModifier(CHAMPION_TOUGHNESS_BONUS, "Toughness Bonus", 4.0D, AttributeModifier.Operation.ADDITION));
/* 246 */     ((DoruDoruArtsMoriAbility)this.doruDoruArtsMoriWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStopChampionPhaseEvent(Mr3Entity entity) {
/* 252 */     func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_188479_b(CHAMPION_FAUX_PROT_BONUS);
/* 253 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_188479_b(CHAMPION_TOUGHNESS_BONUS);
/*     */   }
/*     */   
/*     */   public void onStartTokudaiPhaseEvent(Mr3Entity entity) {
/* 257 */     ((DoruDoruNoYakataAbility)this.doruYakataWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)entity));
/*     */ 
/*     */ 
/*     */     
/* 261 */     ((DoruDoruNoYakataAbility)this.doruYakataWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(TOKUDAI_YAKATA_COOLDOWN_BONUS, "Tokudai Phase Doru Yakata Cooldown Bonus", BonusOperation.ADD, -400.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStopTokudaiPhaseEvent(Mr3Entity entity) {
/* 267 */     ((DoruDoruNoYakataAbility)this.doruYakataWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().removeBonus(TOKUDAI_YAKATA_COOLDOWN_BONUS));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStartTauntPhaseEvent(Mr3Entity entity) {
/* 273 */     ((DoruDoruArtsMoriAbility)this.doruDoruArtsMoriWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> {
/*     */           comp.getBonusManager().addBonus(DORU_ARTS_MORI_COOLDOWN_BONUS, "Taunt Doru Arts Mori Cooldown Bonus", BonusOperation.MUL, 0.5F);
/*     */           comp.stopCooldown((LivingEntity)entity);
/*     */         });
/*     */   }
/*     */   
/*     */   public void onStopTauntPhaseEvent(Mr3Entity entity) {
/* 280 */     ((DoruDoruArtsMoriAbility)this.doruDoruArtsMoriWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().removeBonus(DORU_ARTS_MORI_COOLDOWN_BONUS));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChampionOnCooldown() {
/* 286 */     return ((CooldownComponent)((CandleChampionAbility)this.candleChampionWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).get()).isOnCooldown();
/*     */   }
/*     */   
/*     */   public boolean isTokudaiOnCooldown() {
/* 290 */     return ((CooldownComponent)((TokudaiCandleAbility)this.tokudaiCandleWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).get()).isOnCooldown();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\Mr3Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */