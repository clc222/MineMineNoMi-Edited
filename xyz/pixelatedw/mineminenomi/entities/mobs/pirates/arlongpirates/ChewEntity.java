/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.arlongpirates;
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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.FishmanPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MizuOsuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MizuShuryudanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MizuTaihoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.HumanoidSwimMoveController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayFromTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MizuOsuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MizuShuryudanWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MizuTaihoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.MurasameWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.fishman.UchimizuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class ChewEntity extends OPBossEntity<ChewEntity> {
/*  53 */   private static final UUID MIZU_SHURYUDAN_COOLDOWN_UUID = UUID.fromString("01fa80f8-1ce1-4456-8696-ed2a6ebd1468");
/*  54 */   private static final UUID MIZU_OSU_COOLDOWN_UUID = UUID.fromString("a5f2b30b-1526-4af7-a38b-ec1b0e1922a1");
/*  55 */   private static final UUID HARD_MIZU_TAIHO_COOLDOWN_UUID = UUID.fromString("beccb13e-c813-4d8e-b846-0b05b4ee7557");
/*  56 */   private static final UUID HARD_MURSAMAE_COOLDOWN_UUID = UUID.fromString("52afe78f-6be1-43be-89d5-12f3e405f9ff");
/*     */   
/*     */   private RevengeMeter revengeMeter;
/*     */   
/*     */   private MovementController groundMovementController;
/*     */   
/*     */   private MovementController waterMovementController;
/*     */   private NPCPhase<ChewEntity> groundPhase;
/*     */   private NPCPhase<ChewEntity> waterPhase;
/*     */   
/*     */   public ChewEntity(EntityType type, World world) {
/*  67 */     super(type, world);
/*     */   }
/*     */   
/*     */   public ChewEntity(InProgressChallenge challenge) {
/*  71 */     super((EntityType)ModEntities.CHEW.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  76 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */     
/*  78 */     this.groundMovementController = new MovementController((MobEntity)this);
/*  79 */     this.waterMovementController = (MovementController)new HumanoidSwimMoveController((MobEntity)this);
/*     */     
/*  81 */     this.groundPhase = (NPCPhase<ChewEntity>)new SimplePhase("Ground Phase", (MobEntity)this);
/*  82 */     this.waterPhase = (NPCPhase<ChewEntity>)new SimplePhase("Water Phase", (MobEntity)this, this::startWaterPhaseEvent, this::stopWaterPhaseEvent);
/*     */     
/*  84 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  85 */     this.entityStats.setRace(ModValues.FISHMAN);
/*  86 */     this.entityStats.setFightingStyle(ModValues.SNIPER);
/*     */     
/*  88 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  90 */     worldData.addTemporaryCrewMember(ModNPCGroups.ARLONG_PIRATES, (LivingEntity)this);
/*     */     
/*  92 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(1.0D);
/*  93 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */ 
/*     */     
/*  96 */     UchimizuWrapperGoal uchimizuWrapper = new UchimizuWrapperGoal((MobEntity)this);
/*  97 */     ((UchimizuAbility)uchimizuWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 60.0F));
/*     */ 
/*     */ 
/*     */     
/* 101 */     MizuTaihoWrapperGoal mizuTaihoWrapper = new MizuTaihoWrapperGoal((MobEntity)this);
/*     */     
/* 103 */     MizuOsuWrapperGoal mizuOsuWrapper = new MizuOsuWrapperGoal((MobEntity)this);
/* 104 */     ((MizuOsuAbility)mizuOsuWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(MIZU_OSU_COOLDOWN_UUID, "Mizu Osu Cooldown Bonus", BonusOperation.ADD, -60.0F));
/*     */ 
/*     */ 
/*     */     
/* 108 */     MizuShuryudanWrapperGoal mizuShuryudanWrapper = new MizuShuryudanWrapperGoal((MobEntity)this);
/* 109 */     ((MizuShuryudanAbility)mizuShuryudanWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(MIZU_SHURYUDAN_COOLDOWN_UUID, "Mizu Shuryudan Cooldown Bonus", BonusOperation.ADD, -40.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     ((GroundPathNavigator)func_70661_as()).func_179688_b(true);
/*     */     
/* 116 */     this.field_70714_bg.func_75776_a(0, (Goal)new OpenDoorGoal((MobEntity)this, true));
/* 117 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/* 118 */     this.field_70714_bg.func_75776_a(3, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 119 */     this.field_70714_bg.func_75776_a(3, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 121 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this).and(ModEntityPredicates.IS_ENTITY_HARMLESS.negate());
/* 122 */     Predicate<Entity> invisibleCheck = factionScope.and(ModEntityPredicates.IS_INVISIBLE.negate());
/*     */     
/* 124 */     this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal((CreatureEntity)this, factionScope, new Class[0]));
/* 125 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, invisibleCheck));
/* 126 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, invisibleCheck));
/*     */ 
/*     */     
/* 129 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, FishmanPassiveBonusesAbility.INSTANCE));
/* 130 */     this.field_70714_bg.func_75776_a(1, (Goal)(new ImprovedMeleeAttackGoal((CreatureEntity)this, 0.800000011920929D, true)).setEarlyStop(this::isInRangeForAbilities));
/* 131 */     this.field_70714_bg.func_75776_a(3, (Goal)uchimizuWrapper);
/* 132 */     this.field_70714_bg.func_75776_a(4, (Goal)mizuShuryudanWrapper);
/* 133 */     this.field_70714_bg.func_75776_a(4, (Goal)mizuOsuWrapper);
/* 134 */     this.field_70714_bg.func_75776_a(4, (Goal)mizuTaihoWrapper);
/*     */     
/* 136 */     getPhaseManager().setPhase(this.groundPhase);
/*     */     
/* 138 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 139 */       this.entityStats.setDoriki(2000.0D);
/* 140 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 141 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(2.0D);
/* 142 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/* 143 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 145 */       this.field_70714_bg.func_75776_a(0, (Goal)new RunAwayFromTargetGoal((CreatureEntity)this, 1.5D, 100, 200));
/* 146 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 200.0F, 5.0F));
/* 147 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 70.0F, 2.25F));
/*     */     } else {
/*     */       
/* 150 */       this.entityStats.setDoriki(10000.0D);
/* 151 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 152 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 153 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 154 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/* 155 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/* 156 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(5.0D);
/* 157 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(300.0D);
/* 158 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 159 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(4.0D);
/* 160 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(5.0D);
/* 161 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(12.0D);
/*     */       
/* 163 */       ((MizuTaihoAbility)mizuTaihoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_MIZU_TAIHO_COOLDOWN_UUID, "Hard Mizu Taiho Cooldown Bonus", BonusOperation.ADD, -100.0F));
/*     */ 
/*     */ 
/*     */       
/* 167 */       MurasameWrapperGoal murasameWrapper = new MurasameWrapperGoal((MobEntity)this);
/* 168 */       ((MurasameAbility)murasameWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> {
/*     */             comp.getBonusManager().addBonus(HARD_MURSAMAE_COOLDOWN_UUID, "Hard Murasame Cooldown Bonus", BonusOperation.ADD, -150.0F);
/*     */             
/*     */             comp.startCooldown((LivingEntity)this, 80.0F);
/*     */           });
/* 173 */       this.field_70714_bg.func_75776_a(0, (Goal)new RunAwayFromTargetGoal((CreatureEntity)this, 1.7D, 100, 100));
/* 174 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 100.0F, 4.0F));
/* 175 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 60.0F, 3.0F));
/* 176 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 177 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/* 178 */       this.field_70714_bg.func_75776_a(3, (Goal)murasameWrapper);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 183 */     return OPEntity.createAttributes()
/* 184 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 185 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 186 */       .func_233815_a_(Attributes.field_233823_f_, 3.0D)
/* 187 */       .func_233815_a_(Attributes.field_233818_a_, 250.0D)
/* 188 */       .func_233815_a_(Attributes.field_233820_c_, 0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 193 */     super.func_70071_h_();
/* 194 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 195 */       getRevengeMeter().tick();
/*     */       
/* 197 */       if (func_70090_H()) {
/* 198 */         getPhaseManager().setPhase(this.waterPhase);
/*     */       } else {
/*     */         
/* 201 */         getPhaseManager().setPhase(this.groundPhase);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isInRangeForAbilities() {
/* 207 */     if (!GoalUtil.hasAliveTarget((MobEntity)this)) {
/* 208 */       return true;
/*     */     }
/* 210 */     return (Math.abs(func_70032_d((Entity)func_70638_az())) < 30.0F);
/*     */   }
/*     */   
/*     */   private void startWaterPhaseEvent(ChewEntity entity) {
/* 214 */     this.field_70765_h = this.waterMovementController;
/*     */   }
/*     */   
/*     */   private void stopWaterPhaseEvent(ChewEntity entity) {
/* 218 */     this.field_70765_h = this.groundMovementController;
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 223 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\arlongpirates\ChewEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */