/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.LeapAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.StealPunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.StealthFootAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.TakedownKickAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.LeapWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.StealPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.StealthFootWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.TakedownKickWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr5Entity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ShamEntity extends OPBossEntity<Mr5Entity> implements IGoalPartner<BuchiEntity> {
/*  44 */   private static final UUID STANDARD_STEAL_PUNCH_COOLDOWN_BONUS_UUID = UUID.fromString("2d213d2a-2b98-406e-b4dc-984865936edd");
/*  45 */   private static final UUID HARD_TAKEDOWN_KICK_COOLDOWN_BONUS_UUID = UUID.fromString("ebb94c72-15fd-4b10-b5be-ece547a81259");
/*  46 */   private static final UUID HARD_STEALTH_FOOT_COOLDOWN_BONUS_UUID = UUID.fromString("8b63c5b3-6b2f-4ef6-9397-96c3c5b6946d");
/*     */   
/*     */   private BuchiEntity partner;
/*     */   
/*     */   public ShamEntity(EntityType type, World world) {
/*  51 */     super(type, world);
/*     */   }
/*     */   
/*     */   public ShamEntity(InProgressChallenge challenge) {
/*  55 */     super((EntityType)ModEntities.SHAM.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  60 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  61 */     this.entityStats.setRace(ModValues.HUMAN);
/*  62 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/*  64 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  66 */     worldData.addTemporaryCrewMember(ModNPCGroups.BLACK_CAT_PIRATES, (LivingEntity)this);
/*     */     
/*  68 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(1.0D);
/*  69 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  70 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/*     */     
/*  72 */     StealPunchWrapperGoal stealPunchWrapper = new StealPunchWrapperGoal((MobEntity)this);
/*     */     
/*  74 */     TakedownKickWrapperGoal takedownKickWrapper = new TakedownKickWrapperGoal((MobEntity)this);
/*     */     
/*  76 */     LeapWrapperGoal leapWrapper = new LeapWrapperGoal((MobEntity)this);
/*  77 */     LeapAbility leapAbility = (LeapAbility)leapWrapper.getAbility();
/*  78 */     leapAbility.setLeapHeight(0.30000001192092896D);
/*     */ 
/*     */     
/*  81 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/*  83 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindPartnerGoal((MobEntity)this, (EntityType)ModEntities.BUCHI.get()));
/*  84 */     this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  85 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*     */     
/*  87 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  88 */     this.field_70714_bg.func_75776_a(2, (Goal)stealPunchWrapper);
/*  89 */     this.field_70714_bg.func_75776_a(2, (Goal)leapWrapper);
/*  90 */     this.field_70714_bg.func_75776_a(2, (Goal)takedownKickWrapper);
/*     */     
/*  92 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  93 */       this.entityStats.setDoriki(2000.0D);
/*  94 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  95 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(2.0D);
/*     */       
/*  97 */       ((StealPunchAbility)stealPunchWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_STEAL_PUNCH_COOLDOWN_BONUS_UUID, "Standard Steal Punch Cooldown Bonus", BonusOperation.ADD, 80.0F));
/*     */ 
/*     */ 
/*     */       
/* 101 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 300.0F, 2.5F));
/* 102 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 350.0F, 3.0F));
/*     */     } else {
/*     */       
/* 105 */       this.entityStats.setDoriki(10000.0D);
/* 106 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 107 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 108 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 109 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/* 110 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/* 111 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(5.0D);
/* 112 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(250.0D);
/* 113 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 114 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(4.0D);
/* 115 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(10.0D);
/* 116 */       func_110148_a(Attributes.field_233825_h_).func_111128_a(6.0D);
/*     */       
/* 118 */       StealthFootWrapperGoal stealthFootWrapper = new StealthFootWrapperGoal((MobEntity)this);
/* 119 */       ((StealthFootAbility)stealthFootWrapper.getAbility()).addCanUseCheck((entity, ability) -> (func_110143_aJ() <= WyHelper.percentage(30.0D, func_110138_aP())) ? AbilityUseResult.success() : AbilityUseResult.fail(null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       ((StealthFootAbility)stealthFootWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_STEALTH_FOOT_COOLDOWN_BONUS_UUID, "Hard Stealth Foot Cooldown Bonus", BonusOperation.ADD, 200.0F));
/*     */ 
/*     */ 
/*     */       
/* 129 */       ((TakedownKickAbility)takedownKickWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_TAKEDOWN_KICK_COOLDOWN_BONUS_UUID, "Hard Takedown Kick Cooldown Bonus", BonusOperation.ADD, -40.0F));
/*     */ 
/*     */ 
/*     */       
/* 133 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 134 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiHardeningWrapperGoal((MobEntity)this));
/* 135 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 150.0F, 3.0F));
/* 136 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 300.0F, 3.0F));
/* 137 */       this.field_70714_bg.func_75776_a(1, (Goal)new SoruWrapperGoal((MobEntity)this));
/* 138 */       this.field_70714_bg.func_75776_a(1, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 139 */       this.field_70714_bg.func_75776_a(2, (Goal)new ShiganWrapperGoal((MobEntity)this));
/* 140 */       this.field_70714_bg.func_75776_a(2, (Goal)new PartyTableKickCourseWrapperGoal((MobEntity)this));
/* 141 */       this.field_70714_bg.func_75776_a(2, (Goal)stealthFootWrapper);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 146 */     return OPEntity.createAttributes()
/* 147 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 148 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 149 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 150 */       .func_233815_a_(Attributes.field_233818_a_, 150.0D)
/* 151 */       .func_233815_a_(Attributes.field_233825_h_, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BuchiEntity getPartner() {
/* 157 */     return this.partner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartner(BuchiEntity partner) {
/* 162 */     this.partner = partner;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\blackcatpirates\ShamEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */