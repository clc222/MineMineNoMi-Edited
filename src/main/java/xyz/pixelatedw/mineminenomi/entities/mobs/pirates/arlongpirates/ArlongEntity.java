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
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.ChargedPunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SharkOnToothAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.GankingRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.TakedownKickWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.KachiageHaisokuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MurasameWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.SamehadaShoteiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.SharkOnToothWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.UchimizuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ArlongEntity extends OPBossEntity<ArlongEntity> {
/*  75 */   private static final UUID STANDARD_UCHIMIZU_COOLDOWN_UUID = UUID.fromString("efa25144-6946-48c2-8e2a-cb2135b98783");
/*  76 */   private static final UUID HARD_MURASAME_COOLDOWN_UUID = UUID.fromString("08737d15-31f0-4103-b09a-99cd98e72cda");
/*  77 */   private static final UUID WATER_UCHIMIZU_COOLDOWN_UUID = UUID.fromString("55220e18-d068-4913-9cf5-ba7c57eeeb4e");
/*  78 */   private static final UUID WATER_SHARK_ON_TOOTH_COOLDOWN_UUID = UUID.fromString("c5c03564-411b-4a21-85e9-e9b003c91123");
/*  79 */   private static final AttributeModifier GCD_MOD = new AttributeModifier(UUID.fromString("e478aceb-9865-40da-a137-6359ba503bf0"), "GCD Modifier", -5.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   private RevengeMeter revengeMeter;
/*     */   
/*     */   private float revengeThreshold;
/*     */   
/*     */   private MovementController groundMovementController;
/*     */   
/*     */   private MovementController waterMovementController;
/*     */   
/*     */   private NPCPhase<ArlongEntity> firstPhase;
/*     */   private NPCPhase<ArlongEntity> secondPhase;
/*     */   private NPCPhase<ArlongEntity> waterPhase;
/*     */   private Optional<CooldownComponent> uchimizuCooldownComponent;
/*     */   private SharkOnToothWrapperGoal sharkOnToothWrapper;
/*     */   private Optional<CooldownComponent> sharkOnToothCooldownComponent;
/*     */   
/*     */   public ArlongEntity(EntityType type, World world) {
/*  97 */     super(type, world);
/*     */   }
/*     */   
/*     */   public ArlongEntity(InProgressChallenge challenge) {
/* 101 */     super((EntityType)ModEntities.ARLONG.get(), challenge);
/* 102 */     this.revengeThreshold = challenge.isStandardDifficulty() ? 0.5F : 0.3F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/* 107 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */     
/* 109 */     this.groundMovementController = new MovementController((MobEntity)this);
/* 110 */     this.waterMovementController = (MovementController)new HumanoidSwimMoveController((MobEntity)this);
/*     */     
/* 112 */     this.firstPhase = (NPCPhase<ArlongEntity>)new SimplePhase("First Phase", (MobEntity)this);
/* 113 */     this.secondPhase = (NPCPhase<ArlongEntity>)new SimplePhase("Second Phase", (MobEntity)this, this::startSecondPhaseEvent);
/* 114 */     this.waterPhase = (NPCPhase<ArlongEntity>)new SimplePhase("Water Phase", (MobEntity)this, this::startWaterPhaseEvent, this::stopWaterPhaseEvent);
/*     */     
/* 116 */     this.entityStats.setFaction(ModValues.PIRATE);
/* 117 */     this.entityStats.setRace(ModValues.FISHMAN);
/* 118 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/* 120 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 122 */     worldData.addTemporaryCrewMember(ModNPCGroups.ARLONG_PIRATES, (LivingEntity)this);
/*     */     
/* 124 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/* 125 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */ 
/*     */     
/* 128 */     getRevengeMeter().addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(1));
/* 129 */     getRevengeMeter().addCheck((IRevengeCheck)new GankingRevengeCheck(5, 5.0F));
/*     */ 
/*     */     
/* 132 */     UchimizuWrapperGoal uchimizuWrapper = new UchimizuWrapperGoal((MobEntity)this);
/* 133 */     Optional<CooldownComponent> cooldownComponent = ((UchimizuAbility)uchimizuWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN);
/* 134 */     cooldownComponent.ifPresent(comp -> {
/*     */           comp.getBonusManager().addBonus(STANDARD_UCHIMIZU_COOLDOWN_UUID, "Standard Uchimizu Bonus", BonusOperation.MUL, 2.0F);
/*     */           
/*     */           comp.startCooldown((LivingEntity)this, 120.0F);
/*     */         });
/* 139 */     KachiageHaisokuWrapperGoal kachiageWrapper = new KachiageHaisokuWrapperGoal((MobEntity)this);
/* 140 */     ((KachiageHaisokuAbility)kachiageWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           ChargedPunchAbility abl = (ChargedPunchAbility)AbilityDataCapability.get(entity).getEquippedAbility(ChargedPunchAbility.INSTANCE);
/* 142 */           return (abl != null && abl.isCharging()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     ChargedPunchWrapperGoal chargedPunchWrapper = new ChargedPunchWrapperGoal((MobEntity)this);
/* 149 */     ((ChargedPunchAbility)chargedPunchWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           KachiageHaisokuAbility abl = (KachiageHaisokuAbility)AbilityDataCapability.get(entity).getEquippedAbility(KachiageHaisokuAbility.INSTANCE);
/* 151 */           return (abl != null && abl.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     SamehadaShoteiWrapperGoal samehadaWrapper = new SamehadaShoteiWrapperGoal((MobEntity)this);
/* 158 */     ((SamehadaShoteiAbility)samehadaWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           SharkOnToothAbility abl = (SharkOnToothAbility)AbilityDataCapability.get(entity).getEquippedAbility(SharkOnToothAbility.INSTANCE);
/* 160 */           return (abl != null && abl.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     this.sharkOnToothWrapper = new SharkOnToothWrapperGoal((MobEntity)this);
/* 167 */     this.sharkOnToothCooldownComponent = ((SharkOnToothAbility)this.sharkOnToothWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN);
/* 168 */     this.uchimizuCooldownComponent = ((UchimizuAbility)uchimizuWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN);
/*     */     
/* 170 */     SharkOnToothAbility sharkOnToothAbility = (SharkOnToothAbility)this.sharkOnToothWrapper.getAbility();
/* 171 */     sharkOnToothAbility.addCanUseCheck((entity, ability) -> {
/*     */           LivingEntity target = func_70638_az();
/*     */ 
/*     */           
/*     */           if (this.waterPhase.isActive((IPhasesEntity)this) && target != null) {
/*     */             if (EntityStatsCapability.get(target).isFishman()) {
/*     */               return AbilityUseResult.success();
/*     */             }
/*     */ 
/*     */             
/*     */             if (target.func_226278_cu_() - func_226278_cu_() <= 2.0D) {
/*     */               return AbilityUseResult.fail(null);
/*     */             }
/*     */           } 
/*     */           
/*     */           return AbilityUseResult.success();
/*     */         });
/*     */     
/* 189 */     sharkOnToothAbility.addTickEvent((entity, ability) -> {
/*     */           if (this.waterPhase.isActive((IPhasesEntity)this) && sharkOnToothAbility.isContinuous() && sharkOnToothAbility.hasHitTarget()) {
/*     */             LivingEntity target = ((DealDamageComponent)sharkOnToothAbility.getComponent(ModAbilityKeys.DAMAGE).get()).getLastTarget();
/*     */ 
/*     */             
/*     */             if (target != null) {
/*     */               ((ContinuousComponent)sharkOnToothAbility.getComponent(ModAbilityKeys.CONTINUOUS).get()).stopContinuity((LivingEntity)this);
/*     */               
/*     */               ((AnimationComponent)sharkOnToothAbility.getComponent(ModAbilityKeys.ANIMATION).get()).start((LivingEntity)this, ModAnimations.TAKEDOWN_KICK, 7);
/*     */               
/*     */               AbilityHelper.setDeltaMovement((Entity)target, (entity.func_213322_ci()).field_72450_a, -5.0D, (entity.func_213322_ci()).field_72449_c);
/*     */             } 
/*     */           } 
/*     */         });
/*     */     
/* 204 */     ((GroundPathNavigator)func_70661_as()).func_179688_b(true);
/*     */     
/* 206 */     this.field_70714_bg.func_75776_a(0, (Goal)new OpenDoorGoal((MobEntity)this, true));
/* 207 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 208 */     this.field_70714_bg.func_75776_a(3, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 209 */     this.field_70714_bg.func_75776_a(3, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 211 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this).and(ModEntityPredicates.IS_ENTITY_HARMLESS.negate());
/* 212 */     Predicate<Entity> invisibleCheck = factionScope.and(ModEntityPredicates.IS_INVISIBLE.negate());
/*     */     
/* 214 */     this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal((CreatureEntity)this, factionScope, new Class[0]));
/* 215 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, invisibleCheck));
/* 216 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, invisibleCheck));
/*     */ 
/*     */ 
/*     */     
/* 220 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, FishmanPassiveBonusesAbility.INSTANCE));
/* 221 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*     */     
/* 223 */     this.firstPhase.addGoal(1, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 224 */     this.firstPhase.addGoal(3, (Goal)uchimizuWrapper);
/* 225 */     this.firstPhase.addGoal(3, (Goal)new TakedownKickWrapperGoal((MobEntity)this));
/* 226 */     this.firstPhase.addGoal(4, (Goal)chargedPunchWrapper);
/* 227 */     this.firstPhase.addGoal(4, (Goal)samehadaWrapper);
/* 228 */     this.firstPhase.addGoal(4, (Goal)kachiageWrapper);
/* 229 */     this.firstPhase.addGoal(4, (Goal)this.sharkOnToothWrapper);
/*     */     
/* 231 */     this.secondPhase.addGoal(1, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 232 */     this.secondPhase.addGoal(3, (Goal)uchimizuWrapper);
/* 233 */     this.secondPhase.addGoal(3, (Goal)new TakedownKickWrapperGoal((MobEntity)this));
/* 234 */     this.secondPhase.addGoal(4, (Goal)samehadaWrapper);
/* 235 */     this.secondPhase.addGoal(4, (Goal)kachiageWrapper);
/* 236 */     this.secondPhase.addGoal(4, (Goal)this.sharkOnToothWrapper);
/*     */     
/* 238 */     this.waterPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 100.0F, 5.0F));
/* 239 */     this.waterPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 70.0F, 2.25F));
/* 240 */     this.waterPhase.addGoal(3, (Goal)uchimizuWrapper);
/* 241 */     this.waterPhase.addGoal(4, (Goal)this.sharkOnToothWrapper);
/*     */     
/* 243 */     getPhaseManager().setPhase(this.firstPhase);
/*     */     
/* 245 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 246 */       this.entityStats.setDoriki(2000.0D);
/* 247 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 248 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(2.0D);
/* 249 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/* 250 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 252 */       getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(5));
/*     */     } else {
/*     */       
/* 255 */       this.entityStats.setDoriki(10000.0D);
/* 256 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 257 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 258 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 259 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(5.0D);
/* 260 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(5.0D);
/* 261 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(8.0D);
/* 262 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(400.0D);
/* 263 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 264 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 265 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(5.0D);
/* 266 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 268 */       getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(10));
/*     */       
/* 270 */       MurasameWrapperGoal murasameWrapper = new MurasameWrapperGoal((MobEntity)this);
/* 271 */       Optional<CooldownComponent> murasameCooldownComponent = ((MurasameAbility)murasameWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN);
/* 272 */       murasameCooldownComponent.ifPresent(comp -> {
/*     */             comp.getBonusManager().addBonus(HARD_MURASAME_COOLDOWN_UUID, "Murasame Cooldown Bonus", BonusOperation.ADD, -200.0F);
/*     */             
/*     */             comp.startCooldown((LivingEntity)this, 100.0F);
/*     */           });
/* 277 */       Optional<CooldownComponent> uchimizuCooldownComponent = ((UchimizuAbility)uchimizuWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN);
/* 278 */       uchimizuCooldownComponent.ifPresent(comp -> comp.getBonusManager().removeBonus(STANDARD_UCHIMIZU_COOLDOWN_UUID));
/*     */       
/* 280 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 281 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/* 282 */       this.field_70714_bg.func_75776_a(3, (Goal)murasameWrapper);
/*     */       
/* 284 */       this.firstPhase.addGoal(3, (Goal)new PartyTableKickCourseWrapperGoal((MobEntity)this));
/*     */       
/* 286 */       this.secondPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 120.0F, 5.0F));
/* 287 */       this.secondPhase.addGoal(3, (Goal)new PartyTableKickCourseWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 292 */     return OPEntity.createAttributes()
/* 293 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 294 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 295 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 296 */       .func_233815_a_(Attributes.field_233818_a_, 300.0D)
/* 297 */       .func_233815_a_(Attributes.field_233820_c_, 0.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 302 */     super.func_70071_h_();
/* 303 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 304 */       getRevengeMeter().tick();
/*     */       
/* 306 */       if (this.secondPhase.isActive((IPhasesEntity)this) && getRevengeMeter().getRevengePercentage() > this.revengeThreshold && this.sharkOnToothCooldownComponent
/* 307 */         .isPresent() && ((CooldownComponent)this.sharkOnToothCooldownComponent.get()).isOnCooldown()) {
/* 308 */         ((CooldownComponent)this.sharkOnToothCooldownComponent.get()).stopCooldown((LivingEntity)this);
/* 309 */         getRevengeMeter().reduceRevengeValue(25);
/*     */       } 
/*     */       
/* 312 */       if (func_70090_H()) {
/* 313 */         getPhaseManager().setPhase(this.waterPhase);
/*     */       
/*     */       }
/* 316 */       else if (this.waterPhase.isActive((IPhasesEntity)this) && getPhaseManager().getPreviousPhase() != null) {
/* 317 */         getPhaseManager().setPhase(getPhaseManager().getPreviousPhase());
/*     */       }
/* 319 */       else if (this.firstPhase.isActive((IPhasesEntity)this) && func_110143_aJ() <= WyHelper.percentage(50.0D, func_110138_aP())) {
/* 320 */         getPhaseManager().setPhase(this.secondPhase);
/*     */       }
/* 322 */       else if (!this.secondPhase.isActive((IPhasesEntity)this)) {
/* 323 */         getPhaseManager().setPhase(this.firstPhase);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void startSecondPhaseEvent(ArlongEntity entity) {
/* 330 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.KIRIBACHI.get()));
/* 331 */     func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(1.0D);
/*     */     
/* 333 */     if (isDifficultyHardOrAbove()) {
/* 334 */       ModifiableAttributeInstance attr = entity.func_110148_a((Attribute)ModAttributes.GCD.get());
/* 335 */       if (attr != null && !attr.func_180374_a(GCD_MOD)) {
/* 336 */         attr.func_233767_b_(GCD_MOD);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startWaterPhaseEvent(ArlongEntity entity) {
/* 342 */     this.field_70765_h = this.waterMovementController;
/*     */     
/* 344 */     this.uchimizuCooldownComponent.ifPresent(comp -> {
/*     */           if (!comp.getBonusManager().hasBonus(WATER_UCHIMIZU_COOLDOWN_UUID)) {
/*     */             comp.getBonusManager().addBonus(WATER_UCHIMIZU_COOLDOWN_UUID, "Uchimizu Cooldown Water Bonus", BonusOperation.MUL, 0.5F);
/*     */           }
/*     */         });
/*     */     
/* 350 */     this.sharkOnToothCooldownComponent.ifPresent(comp -> {
/*     */           if (!comp.getBonusManager().hasBonus(WATER_SHARK_ON_TOOTH_COOLDOWN_UUID)) {
/*     */             comp.getBonusManager().addBonus(WATER_SHARK_ON_TOOTH_COOLDOWN_UUID, "Shark on Tooth Cooldown Water Bonus", BonusOperation.MUL, 0.5F);
/*     */           }
/*     */         });
/*     */     
/* 356 */     this.sharkOnToothWrapper.setMaxDistance(50.0D);
/*     */   }
/*     */   
/*     */   private void stopWaterPhaseEvent(ArlongEntity entity) {
/* 360 */     this.field_70765_h = this.groundMovementController;
/*     */     
/* 362 */     this.uchimizuCooldownComponent.ifPresent(comp -> comp.getBonusManager().removeBonus(WATER_UCHIMIZU_COOLDOWN_UUID));
/*     */ 
/*     */ 
/*     */     
/* 366 */     this.sharkOnToothCooldownComponent.ifPresent(comp -> comp.getBonusManager().removeBonus(WATER_SHARK_ON_TOOTH_COOLDOWN_UUID));
/*     */ 
/*     */ 
/*     */     
/* 370 */     this.sharkOnToothWrapper.setMaxDistance(10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 375 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\arlongpirates\ArlongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */