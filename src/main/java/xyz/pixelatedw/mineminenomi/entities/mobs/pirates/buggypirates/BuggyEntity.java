/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraCarAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraFestivalAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraBaraHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bara.BaraSplitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.TargetRunningAwayRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BombEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BombThrowWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bara.BaraBaraCarWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bara.BaraBaraFestivalWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bara.BaraBaraHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bara.BaraSplitWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.KenbunshokuHakiFutureSightWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.buggy.BuggyDashGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.buggy.BuggyRunningGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BuggyEntity extends OPBossEntity<BuggyEntity> {
/*     */   private static final byte BARA_RESET_EVENT = 100;
/*     */   private static final byte BARA_HO_EVENT = 101;
/*     */   private static final byte BARA_SPLIT_EVENT = 102;
/*  75 */   private static final CompoundNBT FIREWORKS_TAG = createFireworksTag(); private static final byte BARA_FESTIVAL_EVENT = 103; private static final byte BARA_CAR_START_EVENT = 104; private static final byte BARA_CAR_END_EVENT = 105; private static final byte FIREWORKS_EVENT = 110;
/*  76 */   private static final UUID BARA_CAR_COOLDOWN_UUID = UUID.fromString("1d375261-e119-484c-8df3-c92da926cd6c");
/*     */   
/*  78 */   public int clientCarState = 0;
/*  79 */   public int clientState = 0;
/*  80 */   private int clientTick = 0;
/*     */   
/*     */   private boolean tickClient = false;
/*  83 */   private NPCPhase<BuggyEntity> normalPhase = (NPCPhase<BuggyEntity>)new SimplePhase("Normal Phase", (MobEntity)this);
/*  84 */   private NPCPhase<BuggyEntity> carPhase = (NPCPhase<BuggyEntity>)new SimplePhase("Car Phase", (MobEntity)this);
/*     */   
/*  86 */   private final RevengeMeter kitingMeter = new RevengeMeter((LivingEntity)this, 100, 0);
/*     */   
/*     */   private BaraBaraFestivalWrapperGoal baraFestivalWrapper;
/*     */   
/*     */   private BaraBaraCarWrapperGoal baraCarWrapper;
/*     */   private boolean splitBombs = false;
/*     */   
/*     */   public BuggyEntity(EntityType type, World world) {
/*  94 */     super(type, world);
/*     */   }
/*     */   
/*     */   public BuggyEntity(InProgressChallenge challenge) {
/*  98 */     super((EntityType)ModEntities.BUGGY.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/* 103 */     ItemStack capeStack = new ItemStack((IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE.get());
/* 104 */     capeStack.func_190925_c("display").func_74768_a("color", 16146690);
/*     */     
/* 106 */     ItemStack bicorneStack = ((Item)ModArmors.BICORNE.get()).func_190903_i();
/* 107 */     bicorneStack.func_190925_c("display").func_74768_a("color", 13459968);
/*     */     
/* 109 */     func_184201_a(EquipmentSlotType.HEAD, bicorneStack);
/* 110 */     func_184201_a(EquipmentSlotType.CHEST, capeStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initStats() {
/* 115 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 117 */     this.entityStats.setFaction(ModValues.PIRATE);
/* 118 */     this.entityStats.setRace(ModValues.HUMAN);
/* 119 */     this.devilFruitData.setDevilFruit(ModAbilities.BARA_BARA_NO_MI);
/*     */     
/* 121 */     worldData.addTemporaryCrewMember(ModNPCGroups.BUGGY_PIRATES, (LivingEntity)this);
/*     */     
/* 123 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*     */     
/* 125 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 126 */       this.entityStats.setDoriki(2000.0D);
/* 127 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 128 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 129 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */     } else {
/*     */       
/* 132 */       this.entityStats.setDoriki(10000.0D);
/* 133 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 134 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 135 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 136 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 137 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 138 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(300.0D);
/* 139 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(10.0D);
/* 140 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 141 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGoals() {
/* 148 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 150 */     BaraBaraHoWrapperGoal baraHoWrapper = new BaraBaraHoWrapperGoal((MobEntity)this);
/* 151 */     ((BaraBaraHoAbility)baraHoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> {
/*     */           comp.addStartEvent(());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           comp.addTickEvent(());
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     ((BaraBaraHoAbility)baraHoWrapper.getAbility()).getComponent(ModAbilityKeys.PROJECTILE).ifPresent(comp -> comp.addAfterShootEvent(100, ()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     this.baraFestivalWrapper = new BaraBaraFestivalWrapperGoal((MobEntity)this);
/* 174 */     ((BaraBaraFestivalAbility)this.baraFestivalWrapper.getAbility()).addTickEvent((e, a) -> {
/*     */           LivingEntity target = func_70638_az();
/*     */           if (target != null) {
/* 177 */             boolean hasRecentHit = (func_142015_aE() > 0 && this.field_70170_p.func_82737_E() - func_142015_aE() > 100L);
/*     */             boolean hasEnemyNearby = GoalUtil.isWithinDistance((LivingEntity)this, target, 5.0D);
/*     */             if (hasRecentHit && hasEnemyNearby) {
/*     */               ((BaraBaraFestivalAbility)this.baraFestivalWrapper.getAbility()).switchModeToShield((LivingEntity)this);
/*     */             } else {
/*     */               ((BaraBaraFestivalAbility)this.baraFestivalWrapper.getAbility()).switchModeToAttack((LivingEntity)this);
/*     */             } 
/*     */           } 
/*     */         });
/* 186 */     ((BaraBaraFestivalAbility)this.baraFestivalWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> {
/*     */           comp.addStartEvent(());
/*     */ 
/*     */ 
/*     */           
/*     */           comp.addEndEvent(());
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 196 */     BaraSplitWrapperGoal baraSplitWrapper = new BaraSplitWrapperGoal((MobEntity)this);
/* 197 */     ((BaraSplitAbility)baraSplitWrapper.getAbility()).addCanUseCheck((e, a) -> GoalUtil.isOutsideDistance((LivingEntity)this, func_70638_az(), 20.0D) ? AbilityUseResult.fail(null) : AbilityUseResult.success());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     ((BaraSplitAbility)baraSplitWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(this::setBaraSplitLogic);
/*     */     
/* 205 */     BombThrowWrapperGoal bombThrowWrapper = new BombThrowWrapperGoal((MobEntity)this);
/* 206 */     ((BombThrowAbility)bombThrowWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(BARA_CAR_COOLDOWN_UUID, "Bara Car Cooldown Bonus", BonusOperation.MUL, 0.5F));
/*     */ 
/*     */ 
/*     */     
/* 210 */     this.baraCarWrapper = new BaraBaraCarWrapperGoal((MobEntity)this);
/*     */     
/* 212 */     Predicate<LivingEntity> canDash = entity -> ((BaraBaraCarAbility)this.baraCarWrapper.getAbility()).isContinuous() ? false : (!((BaraSplitAbility)baraSplitWrapper.getAbility()).isContinuous());
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
/* 224 */     this.kitingMeter.addCheck((IRevengeCheck)new TargetFlyStallingRevengeCheck(1));
/* 225 */     this.kitingMeter.addCheck((IRevengeCheck)new TargetRunningAwayRevengeCheck(10, 10.0F));
/* 226 */     this.kitingMeter.addCheck((IRevengeCheck)new DeadzoneRevengeCheck(5));
/*     */     
/* 228 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/* 229 */     this.field_70714_bg.func_75776_a(0, (Goal)new BuggyPhaseSwitcherGoal(this));
/* 230 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, SlashDamageImmunityAbility.BARA_INSTANCE));
/*     */     
/* 232 */     this.normalPhase.addGoal(0, (Goal)new BuggyRunningGoal(this, 1.5D));
/* 233 */     this.normalPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 234 */     this.normalPhase.addGoal(2, (Goal)baraHoWrapper);
/* 235 */     this.normalPhase.addGoal(2, (Goal)this.baraFestivalWrapper);
/* 236 */     this.normalPhase.addGoal(2, (Goal)bombThrowWrapper);
/* 237 */     this.normalPhase.addGoal(3, (Goal)baraSplitWrapper);
/*     */     
/* 239 */     this.carPhase.addGoal(0, (Goal)new BuggyRunningGoal(this, 2.5D));
/*     */ 
/*     */     
/* 242 */     this.carPhase.addGoal(2, (Goal)bombThrowWrapper);
/*     */ 
/*     */     
/* 245 */     getPhaseManager().setPhase(this.normalPhase);
/*     */     
/* 247 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 248 */       this.normalPhase.addGoal(1, (Goal)(new DashDodgeTargetGoal((MobEntity)this, 500.0F, 2.0F)).setCanUseTest(canDash));
/* 249 */       this.normalPhase.addGoal(1, (Goal)(new DashDodgeProjectilesGoal((MobEntity)this, 500.0F, 2.0F)).setCanUseTest(canDash));
/*     */       
/* 251 */       this.carPhase.addGoal(0, (Goal)new BuggyDashGoal(this, 2.5D, 10.0F));
/*     */     } else {
/* 253 */       this.field_70714_bg.func_75776_a(0, (Goal)new KenbunshokuHakiFutureSightWrapperGoal((MobEntity)this));
/* 254 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 255 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/* 257 */       this.normalPhase.addGoal(1, (Goal)(new DashDodgeTargetGoal((MobEntity)this, 250.0F, 3.0F)).setCanUseTest(canDash));
/* 258 */       this.normalPhase.addGoal(1, (Goal)(new DashDodgeProjectilesGoal((MobEntity)this, 250.0F, 3.0F)).setCanUseTest(canDash));
/*     */       
/* 260 */       this.carPhase.addGoal(0, (Goal)new BuggyDashGoal(this, 5.0D, 25.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 265 */     return OPEntity.createAttributes()
/* 266 */       .func_233815_a_(Attributes.field_233819_b_, 30.0D)
/* 267 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 268 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 269 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 270 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 275 */     if (isCarPhaseActive() || this.clientCarState == 1) {
/* 276 */       return EntitySize.func_220311_c(1.3F, 0.9F);
/*     */     }
/*     */     
/* 279 */     return super.func_213305_a(pose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 284 */     this.field_70143_R = 0.0F;
/*     */     
/* 286 */     super.func_70071_h_();
/*     */     
/* 288 */     if (!this.field_70170_p.field_72995_K) {
/* 289 */       this.kitingMeter.tick();
/*     */     }
/*     */     
/* 292 */     if (isCarPhaseActive() || this.clientCarState == 1) {
/* 293 */       func_213323_x_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 300 */     switch (id) {
/*     */       case 110:
/* 302 */         this.field_70170_p.func_92088_a(func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 0.0D, 0.0D, 0.0D, FIREWORKS_TAG);
/*     */         break;
/*     */       case 100:
/*     */       case 101:
/*     */       case 102:
/*     */       case 103:
/* 308 */         this.clientState = id - 100;
/*     */         break;
/*     */       case 104:
/* 311 */         this.clientState = 4;
/* 312 */         this.clientCarState = 1;
/*     */         break;
/*     */       case 105:
/* 315 */         this.clientState = 4;
/* 316 */         this.clientCarState = -1;
/*     */         break;
/*     */     } 
/* 319 */     super.func_70103_a(id);
/*     */   }
/*     */   
/*     */   private static CompoundNBT createFireworksTag() {
/* 323 */     CompoundNBT fireworkData = new CompoundNBT();
/* 324 */     ListNBT explosionList = new ListNBT();
/*     */     
/* 326 */     CompoundNBT explosion1Data = new CompoundNBT();
/* 327 */     explosion1Data.func_74768_a("Type", 1);
/* 328 */     explosion1Data.func_74757_a("Flicker", true);
/* 329 */     explosion1Data.func_74783_a("Colors", new int[] { 16351261, 11546150 });
/* 330 */     explosion1Data.func_74783_a("FadeColors", new int[] { 16701501, 16383998 });
/*     */     
/* 332 */     CompoundNBT explosion2Data = new CompoundNBT();
/* 333 */     explosion2Data.func_74768_a("Type", 4);
/* 334 */     explosion2Data.func_74757_a("Trail", true);
/* 335 */     explosion2Data.func_74783_a("Colors", new int[] { 16701501, 16383998 });
/*     */     
/* 337 */     explosionList.add(explosion1Data);
/* 338 */     explosionList.add(explosion2Data);
/* 339 */     fireworkData.func_218657_a("Explosions", (INBT)explosionList);
/* 340 */     return fireworkData;
/*     */   }
/*     */   
/*     */   public boolean shouldRun() {
/* 344 */     boolean isCarActive = getPhaseManager().isCurrentPhase(this.carPhase);
/* 345 */     if (isCarActive) {
/* 346 */       return true;
/*     */     }
/*     */     
/* 349 */     boolean isFestivalActive = ((Boolean)((BaraBaraFestivalAbility)this.baraFestivalWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).map(comp -> Boolean.valueOf(comp.isContinuous())).orElse(Boolean.valueOf(false))).booleanValue();
/* 350 */     boolean isFestivalAttackMode = ((BaraBaraFestivalAbility)this.baraFestivalWrapper.getAbility()).isAttackMode();
/* 351 */     if (isFestivalActive && isFestivalAttackMode) {
/* 352 */       return true;
/*     */     }
/*     */     
/* 355 */     return false;
/*     */   }
/*     */   
/*     */   private void setBaraSplitLogic(ContinuousComponent comp) {
/* 359 */     comp.addStartEvent(101, (e, a) -> {
/*     */           this.splitBombs = false;
/*     */           
/*     */           this.field_70170_p.func_72960_a((Entity)this, (byte)102);
/*     */           
/*     */           func_195063_d((Effect)ModEffects.MOVEMENT_BLOCKED.get());
/*     */           
/*     */           AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 1.5D, 0.0D);
/*     */           
/*     */           List<LivingEntity> targets = TargetHelper.getEntitiesInArea((LivingEntity)this, 3.0D, TargetsPredicate.DEFAULT_AREA_CHECK, new Class[] { LivingEntity.class });
/*     */           for (LivingEntity target : targets) {
/*     */             Vector3d speed = func_70040_Z().func_216372_d(2.5D, 1.0D, 2.5D);
/*     */             AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, 0.25D, speed.field_72449_c);
/*     */           } 
/*     */         });
/* 374 */     comp.addTickEvent((e, a) -> {
/*     */           if (this.splitBombs || comp.getContinueTime() < 20.0F) {
/*     */             return;
/*     */           }
/*     */           
/*     */           boolean hasSecondWave = false;
/*     */           
/*     */           int radius = 3;
/*     */           
/*     */           int bombs = 4;
/*     */           
/*     */           if (isDifficultyHardOrAbove()) {
/*     */             bombs = 12;
/*     */             
/*     */             radius = 5;
/*     */             
/*     */             hasSecondWave = true;
/*     */           } else if (func_110143_aJ() < WyHelper.percentage(40.0D, func_110138_aP())) {
/*     */             bombs = 8;
/*     */           } 
/*     */           
/*     */           if (bombs <= 0) {
/*     */             return;
/*     */           }
/*     */           
/*     */           if (hasSecondWave) {
/*     */             double d;
/*     */             
/*     */             for (d = 0.0D; d <= 6.283185307179586D; d += Math.PI / bombs / 2.0D) {
/*     */               double x = radius / 2.0D * Math.cos(d) + WyHelper.randomDouble() / 5.0D;
/*     */               double z = radius / 2.0D * Math.sin(d) + WyHelper.randomDouble() / 5.0D;
/*     */               Vector3d pos = func_213303_ch().func_178787_e(new Vector3d(x, (func_213303_ch()).field_72448_b, z));
/*     */               Vector3d dirVec = func_213303_ch().func_178788_d(pos).func_72432_b().func_216372_d(7.5D, 1.0D, 7.5D);
/*     */               BombEntity bomb = new BombEntity(this.field_70170_p, (LivingEntity)this);
/*     */               bomb.setExplodeOnImpact();
/*     */               bomb.func_70012_b((func_213303_ch()).field_72450_a + x, (func_213303_ch()).field_72448_b, (func_213303_ch()).field_72449_c + z, func_70681_au().nextInt(360), func_70681_au().nextInt(360));
/*     */               AbilityHelper.setDeltaMovement((Entity)bomb, -dirVec.field_72450_a, 1.0D, -dirVec.field_72449_c);
/*     */               this.field_70170_p.func_217376_c((Entity)bomb);
/*     */             } 
/*     */           } 
/*     */           double phi;
/*     */           for (phi = 0.0D; phi <= 6.283185307179586D; phi += Math.PI / bombs) {
/*     */             double x = radius * Math.cos(phi) + WyHelper.randomDouble() / 5.0D;
/*     */             double z = radius * Math.sin(phi) + WyHelper.randomDouble() / 5.0D;
/*     */             Vector3d pos = func_213303_ch().func_178787_e(new Vector3d(x, (func_213303_ch()).field_72448_b, z));
/*     */             Vector3d dirVec = func_213303_ch().func_178788_d(pos).func_72432_b().func_216372_d(7.5D, 1.0D, 7.5D);
/*     */             BombEntity bomb = new BombEntity(this.field_70170_p, (LivingEntity)this);
/*     */             bomb.setExplodeOnImpact();
/*     */             bomb.func_70012_b((func_213303_ch()).field_72450_a + x, (func_213303_ch()).field_72448_b, (func_213303_ch()).field_72449_c + z, func_70681_au().nextInt(360), func_70681_au().nextInt(360));
/*     */             AbilityHelper.setDeltaMovement((Entity)bomb, -dirVec.field_72450_a, 1.0D, -dirVec.field_72449_c);
/*     */             this.field_70170_p.func_217376_c((Entity)bomb);
/*     */           } 
/*     */           this.field_70170_p.func_72960_a((Entity)this, (byte)110);
/*     */           this.splitBombs = true;
/*     */         });
/* 429 */     comp.addEndEvent((e, a) -> this.field_70170_p.func_72960_a((Entity)this, (byte)100));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startNormalPhase() {
/* 435 */     getPhaseManager().setPhase(this.normalPhase);
/* 436 */     this.field_70170_p.func_72960_a((Entity)this, (byte)105);
/*     */   }
/*     */   
/*     */   public void startCarPhase() {
/* 440 */     getPhaseManager().setPhase(this.carPhase);
/* 441 */     this.field_70170_p.func_72960_a((Entity)this, (byte)104);
/* 442 */     this.kitingMeter.reduceRevengeValue(-10);
/*     */   }
/*     */   
/*     */   public boolean isNormalPhaseActive() {
/* 446 */     return this.normalPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean isCarPhaseActive() {
/* 450 */     if (this.carPhase == null) {
/* 451 */       return false;
/*     */     }
/* 453 */     return this.carPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public float getKitingMeterCompletion() {
/* 457 */     return this.kitingMeter.getRevengePercentage();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\buggypirates\BuggyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */