/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.AntiMannerKickCourseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.ChargedPunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.AntiMannerKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SpinningBrawlWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.KachiageHaisokuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.KarakusagawaraSeikenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.SamehadaShoteiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KuroobiEntity extends OPBossEntity<KuroobiEntity> {
/*  70 */   private static final UUID STANDARD_SUPLEX_COOLDOWN_UUID = UUID.fromString("91faa9f6-2082-4c39-acee-bd1468241742");
/*  71 */   private static final UUID HARD_SAMEHADA_COOLDOWN_UUID = UUID.fromString("de69810e-d16f-49d6-8951-240215fe6582");
/*  72 */   private static final UUID HARD_SAMEHADA_DAMAGE_UUID = UUID.fromString("56963b0c-5966-482e-b023-e439905e5146");
/*  73 */   private static final UUID HARD_CHARGED_PUNCH_CHARGING_UUID = UUID.fromString("4e9fd1ef-fce7-4ee5-b6c9-0b15142599b6");
/*     */   
/*     */   private RevengeMeter revengeMeter;
/*     */   
/*     */   private MovementController groundMovementController;
/*     */   
/*     */   private MovementController waterMovementController;
/*     */   private NPCPhase<KuroobiEntity> groundPhase;
/*     */   private NPCPhase<KuroobiEntity> waterPhase;
/*     */   
/*     */   public KuroobiEntity(EntityType type, World world) {
/*  84 */     super(type, world);
/*     */   }
/*     */   
/*     */   public KuroobiEntity(InProgressChallenge challenge) {
/*  88 */     super((EntityType)ModEntities.KUROOBI.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  93 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */     
/*  95 */     this.groundMovementController = new MovementController((MobEntity)this);
/*  96 */     this.waterMovementController = (MovementController)new HumanoidSwimMoveController((MobEntity)this);
/*     */     
/*  98 */     this.groundPhase = (NPCPhase<KuroobiEntity>)new SimplePhase("Ground Phase", (MobEntity)this);
/*  99 */     this.waterPhase = (NPCPhase<KuroobiEntity>)new SimplePhase("Water Phase", (MobEntity)this, this::startWaterPhaseEvent, this::stopWaterPhaseEvent);
/*     */     
/* 101 */     this.entityStats.setFaction(ModValues.PIRATE);
/* 102 */     this.entityStats.setRace(ModValues.FISHMAN);
/* 103 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/* 105 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 107 */     worldData.addTemporaryCrewMember(ModNPCGroups.ARLONG_PIRATES, (LivingEntity)this);
/*     */     
/* 109 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/* 110 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */ 
/*     */     
/* 113 */     getRevengeMeter().addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(2));
/* 114 */     getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(10));
/*     */ 
/*     */     
/* 117 */     ((GroundPathNavigator)func_70661_as()).func_179688_b(true);
/*     */     
/* 119 */     this.field_70714_bg.func_75776_a(0, (Goal)new OpenDoorGoal((MobEntity)this, true));
/* 120 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 121 */     this.field_70714_bg.func_75776_a(3, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 122 */     this.field_70714_bg.func_75776_a(3, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 124 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this).and(ModEntityPredicates.IS_ENTITY_HARMLESS.negate());
/* 125 */     Predicate<Entity> invisibleCheck = factionScope.and(ModEntityPredicates.IS_INVISIBLE.negate());
/*     */     
/* 127 */     this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal((CreatureEntity)this, factionScope, new Class[0]));
/* 128 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, invisibleCheck));
/* 129 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, invisibleCheck));
/*     */     
/* 131 */     KachiageHaisokuWrapperGoal kachiageWrapper = new KachiageHaisokuWrapperGoal((MobEntity)this);
/* 132 */     ((KachiageHaisokuAbility)kachiageWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           ChargedPunchAbility abl = (ChargedPunchAbility)AbilityDataCapability.get(entity).getEquippedAbility(ChargedPunchAbility.INSTANCE);
/* 134 */           return (abl != null && abl.isCharging()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 139 */     ((KachiageHaisokuAbility)kachiageWrapper.getAbility()).getComponent(ModAbilityKeys.DAMAGE).ifPresent(comp -> {
/*     */           if (this.waterPhase.isActive((IPhasesEntity)this)) {
/*     */             LivingEntity lastHit = comp.getLastTarget();
/*     */             
/*     */             if (lastHit != null) {
/*     */               AbilityHelper.setDeltaMovement((Entity)lastHit, (func_213322_ci()).field_72450_a, -2.0D, (func_213322_ci()).field_72449_c);
/*     */             }
/*     */           } 
/*     */         });
/* 148 */     ChargedPunchWrapperGoal chargedPunchWrapper = new ChargedPunchWrapperGoal((MobEntity)this);
/* 149 */     ((ChargedPunchAbility)chargedPunchWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           KachiageHaisokuAbility kachiage = (KachiageHaisokuAbility)AbilityDataCapability.get(entity).getEquippedAbility(KachiageHaisokuAbility.INSTANCE);
/*     */           SamehadaShoteiAbility samehada = (SamehadaShoteiAbility)AbilityDataCapability.get(entity).getEquippedAbility(SamehadaShoteiAbility.INSTANCE);
/* 152 */           return (kachiage != null && kachiage.isContinuous()) ? AbilityUseResult.fail(null) : (
/*     */ 
/*     */             
/* 155 */             (samehada != null && samehada.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success());
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     SamehadaShoteiWrapperGoal samehadaWrapper = new SamehadaShoteiWrapperGoal((MobEntity)this);
/*     */     
/* 163 */     KarakusagawaraSeikenWrapperGoal karakusagawaraWrapper = new KarakusagawaraSeikenWrapperGoal((MobEntity)this);
/* 164 */     ((KarakusagawaraSeikenAbility)karakusagawaraWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 100.0F));
/*     */ 
/*     */ 
/*     */     
/* 168 */     SuplexWrapperGoal suplexWrapper = new SuplexWrapperGoal((MobEntity)this);
/* 169 */     ((SuplexAbility)suplexWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           SamehadaShoteiAbility abl = (SamehadaShoteiAbility)AbilityDataCapability.get(entity).getEquippedAbility(SamehadaShoteiAbility.INSTANCE);
/* 171 */           return (abl != null && abl.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 176 */     ((SuplexAbility)suplexWrapper.getAbility()).getComponent(ModAbilityKeys.CHARGE).ifPresent(comp -> comp.addTickEvent(90, ()));
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
/* 191 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, FishmanPassiveBonusesAbility.INSTANCE));
/* 192 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*     */     
/* 194 */     this.groundPhase.addGoal(1, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 195 */     this.groundPhase.addGoal(3, (Goal)new TakedownKickWrapperGoal((MobEntity)this));
/* 196 */     this.groundPhase.addGoal(3, (Goal)suplexWrapper);
/* 197 */     this.groundPhase.addGoal(4, (Goal)samehadaWrapper);
/* 198 */     this.groundPhase.addGoal(4, (Goal)chargedPunchWrapper);
/* 199 */     this.groundPhase.addGoal(4, (Goal)kachiageWrapper);
/* 200 */     this.groundPhase.addGoal(5, (Goal)karakusagawaraWrapper);
/*     */     
/* 202 */     this.waterPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 120.0F, 5.0F));
/* 203 */     this.waterPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 100.0F, 2.25F));
/* 204 */     this.waterPhase.addGoal(3, (Goal)suplexWrapper);
/* 205 */     this.waterPhase.addGoal(4, (Goal)chargedPunchWrapper);
/* 206 */     this.waterPhase.addGoal(4, (Goal)kachiageWrapper);
/* 207 */     this.waterPhase.addGoal(5, (Goal)karakusagawaraWrapper);
/*     */     
/* 209 */     getPhaseManager().setPhase(this.groundPhase);
/*     */     
/* 211 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 212 */       this.entityStats.setDoriki(2000.0D);
/* 213 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 214 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(2.0D);
/* 215 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/* 216 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 218 */       getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(5));
/*     */       
/* 220 */       ((KarakusagawaraSeikenAbility)karakusagawaraWrapper.getAbility()).addCanUseCheck((entity, ability) -> (entity.func_110143_aJ() <= WyHelper.percentage(30.0D, entity.func_110138_aP())) ? AbilityUseResult.success() : AbilityUseResult.fail(null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       ((SuplexAbility)suplexWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_SUPLEX_COOLDOWN_UUID, "Standard Suplex Cooldown Bonus", BonusOperation.ADD, 40.0F));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 232 */       this.entityStats.setDoriki(10000.0D);
/* 233 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 234 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 235 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 236 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(5.0D);
/* 237 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(5.0D);
/* 238 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(7.0D);
/* 239 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(350.0D);
/* 240 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 241 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 242 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(5.0D);
/* 243 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 245 */       getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(10));
/*     */       
/* 247 */       ((SamehadaShoteiAbility)samehadaWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_SAMEHADA_COOLDOWN_UUID, "Hard Samehada Cooldown Bonus", BonusOperation.MUL, 0.75F));
/*     */ 
/*     */ 
/*     */       
/* 251 */       ((SamehadaShoteiAbility)samehadaWrapper.getAbility()).getComponent(ModAbilityKeys.DAMAGE).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_SAMEHADA_DAMAGE_UUID, "Hard Samehada Damage Bonus", BonusOperation.MUL, 2.0F));
/*     */ 
/*     */ 
/*     */       
/* 255 */       ((ChargedPunchAbility)chargedPunchWrapper.getAbility()).getComponent(ModAbilityKeys.CHARGE).ifPresent(comp -> comp.getMaxChargeBonusManager().addBonus(HARD_CHARGED_PUNCH_CHARGING_UUID, "Hard Charged Punch Charging Bonus", BonusOperation.MUL, 0.5F));
/*     */ 
/*     */ 
/*     */       
/* 259 */       HakaiHoWrapperGoal hakaiHoWrapper = new HakaiHoWrapperGoal((MobEntity)this);
/* 260 */       ((HakaiHoAbility)hakaiHoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 100.0F));
/*     */ 
/*     */       
/* 263 */       ((HakaiHoAbility)hakaiHoWrapper.getAbility()).getComponent(ModAbilityKeys.DAMAGE).ifPresent(comp -> {
/*     */             if (this.waterPhase.isActive((IPhasesEntity)this)) {
/*     */               LivingEntity lastHit = comp.getLastTarget();
/*     */               
/*     */               if (lastHit != null) {
/*     */                 AbilityHelper.setDeltaMovement((Entity)lastHit, (func_213322_ci()).field_72450_a, -2.0D, (func_213322_ci()).field_72449_c);
/*     */               }
/*     */             } 
/*     */           });
/* 272 */       AntiMannerKickCourseWrapperGoal antiMannerWrapper = new AntiMannerKickCourseWrapperGoal((MobEntity)this);
/* 273 */       AnimationComponent antiMannerAnimationComp = new AnimationComponent(antiMannerWrapper.getAbility());
/* 274 */       ((AntiMannerKickCourseAbility)antiMannerWrapper.getAbility()).addComponents(new AbilityComponent[] { (AbilityComponent)antiMannerAnimationComp });
/* 275 */       ((AntiMannerKickCourseAbility)antiMannerWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 120.0F));
/*     */ 
/*     */       
/* 278 */       ((AntiMannerKickCourseAbility)antiMannerWrapper.getAbility()).getComponent(ModAbilityKeys.DAMAGE).ifPresent(comp -> {
/*     */             if (this.waterPhase.isActive((IPhasesEntity)this)) {
/*     */               LivingEntity lastHit = comp.getLastTarget();
/*     */               
/*     */               if (lastHit != null) {
/*     */                 antiMannerAnimationComp.start((LivingEntity)this, ModAnimations.TAKEDOWN_KICK, 7);
/*     */                 
/*     */                 AbilityHelper.setDeltaMovement((Entity)lastHit, (func_213322_ci()).field_72450_a, -2.0D, (func_213322_ci()).field_72449_c);
/*     */               } 
/*     */             } 
/*     */           });
/* 289 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 290 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/* 292 */       this.groundPhase.addGoal(3, (Goal)new PartyTableKickCourseWrapperGoal((MobEntity)this));
/* 293 */       this.groundPhase.addGoal(3, (Goal)antiMannerWrapper);
/* 294 */       this.groundPhase.addGoal(3, (Goal)hakaiHoWrapper);
/* 295 */       this.groundPhase.addGoal(3, (Goal)new SpinningBrawlWrapperGoal((MobEntity)this));
/*     */       
/* 297 */       this.waterPhase.addGoal(3, (Goal)antiMannerWrapper);
/* 298 */       this.waterPhase.addGoal(3, (Goal)hakaiHoWrapper);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 303 */     return OPEntity.createAttributes()
/* 304 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 305 */       .func_233815_a_(Attributes.field_233821_d_, 0.28999999165534973D)
/* 306 */       .func_233815_a_(Attributes.field_233823_f_, 3.0D)
/* 307 */       .func_233815_a_(Attributes.field_233818_a_, 250.0D)
/* 308 */       .func_233815_a_(Attributes.field_233820_c_, 0.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 313 */     super.func_70071_h_();
/* 314 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 315 */       getRevengeMeter().tick();
/*     */       
/* 317 */       if (func_70090_H()) {
/* 318 */         getPhaseManager().setPhase(this.waterPhase);
/*     */       } else {
/*     */         
/* 321 */         getPhaseManager().setPhase(this.groundPhase);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startWaterPhaseEvent(KuroobiEntity entity) {
/* 327 */     this.field_70765_h = this.waterMovementController;
/*     */   }
/*     */   
/*     */   private void stopWaterPhaseEvent(KuroobiEntity entity) {
/* 331 */     this.field_70765_h = this.groundMovementController;
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 336 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\arlongpirates\KuroobiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */