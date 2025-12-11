/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ChargedCleaveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.TackleAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.morgan.MorganShockwaveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ChargedCleaveWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.SlamWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TackleWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.ShiganWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.morgan.MorganMusterGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.morgan.MorganShockwaveWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class MorganEntity extends OPBossEntity<MorganEntity> {
/*  42 */   private static final UUID HARD_TATSUMAKI_COOLDOWN_BONUS_UUID = UUID.fromString("4bd574cc-c5a2-4e73-8230-763ccf4544d5");
/*     */   
/*     */   private boolean usedMuster;
/*     */   
/*     */   public MorganEntity(EntityType type, World world) {
/*  47 */     super(type, world);
/*     */   }
/*     */   
/*     */   public MorganEntity(InProgressChallenge challenge) {
/*  51 */     super((EntityType)ModEntities.MORGAN.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  56 */     this.entityStats.setFaction(ModValues.MARINE);
/*  57 */     this.entityStats.setRace(ModValues.HUMAN);
/*  58 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/*  60 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  61 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  62 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(2.5D);
/*     */ 
/*     */     
/*  65 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/*  67 */     ChargedCleaveWrapperGoal chargedCleaveWrapper = (new ChargedCleaveWrapperGoal((MobEntity)this)).setBleedingTime(40);
/*  68 */     ((ChargedCleaveAbility)chargedCleaveWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */           TackleAbility abl = (TackleAbility)AbilityDataCapability.get(entity).getEquippedAbility(TackleAbility.INSTANCE);
/*  70 */           return (abl != null && abl.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     MorganShockwaveWrapperGoal shockwaveWrapper = new MorganShockwaveWrapperGoal((MobEntity)this);
/*     */     
/*  78 */     TackleWrapperGoal tackleWrapper = new TackleWrapperGoal((MobEntity)this);
/*     */     
/*  80 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*  81 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  82 */     this.field_70714_bg.func_75776_a(1, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  83 */     this.field_70714_bg.func_75776_a(3, (Goal)tackleWrapper);
/*  84 */     this.field_70714_bg.func_75776_a(3, (Goal)chargedCleaveWrapper);
/*  85 */     this.field_70714_bg.func_75776_a(3, (Goal)shockwaveWrapper);
/*  86 */     this.field_70714_bg.func_75776_a(3, (Goal)new SlamWrapperGoal((MobEntity)this));
/*     */     
/*  88 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  89 */       this.entityStats.setDoriki(500.0D);
/*  90 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  91 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/*     */     } else {
/*     */       
/*  94 */       this.entityStats.setDoriki(10000.0D);
/*  95 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/*  96 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/*  97 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/*  98 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(6.0D);
/*  99 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(6.0D);
/* 100 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 101 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(10.0D);
/* 102 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 103 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(250.0D);
/* 104 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(12.0D);
/*     */       
/* 106 */       ((MorganShockwaveAbility)shockwaveWrapper.getAbility()).setHardForm();
/*     */       
/* 108 */       OTatsumakiWrapperGoal tatsumakiWrapper = new OTatsumakiWrapperGoal((MobEntity)this);
/* 109 */       ((OTatsumakiAbility)tatsumakiWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_TATSUMAKI_COOLDOWN_BONUS_UUID, "Tatsumaki Cooldown Bonus", BonusOperation.ADD, -80.0F));
/*     */ 
/*     */ 
/*     */       
/* 113 */       ((ChargedCleaveAbility)chargedCleaveWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */             TekkaiAbility abl = (TekkaiAbility)AbilityDataCapability.get(entity).getEquippedAbility(TekkaiAbility.INSTANCE);
/* 115 */             return (abl != null && abl.isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       ShiganWrapperGoal shiganWrapper = new ShiganWrapperGoal((MobEntity)this);
/* 122 */       ((ShiganAbility)shiganWrapper.getAbility()).addCanUseCheck((entity, ability) -> {
/*     */             ChargedCleaveAbility abl = (ChargedCleaveAbility)AbilityDataCapability.get(entity).getEquippedAbility(ChargedCleaveAbility.INSTANCE);
/* 124 */             return (abl != null && abl.isCharging()) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 131 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/* 132 */       this.field_70714_bg.func_75776_a(0, (Goal)new MorganMusterGoal(this));
/*     */       
/* 134 */       this.field_70714_bg.func_75776_a(2, (Goal)shiganWrapper);
/* 135 */       this.field_70714_bg.func_75776_a(2, (Goal)new SoruWrapperGoal((MobEntity)this));
/* 136 */       this.field_70714_bg.func_75776_a(2, (Goal)new RankyakuWrapperGoal((MobEntity)this));
/* 137 */       this.field_70714_bg.func_75776_a(3, (Goal)tatsumakiWrapper);
/* 138 */       this.field_70714_bg.func_75776_a(3, (Goal)new JishinHoWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 143 */     return OPEntity.createAttributes()
/* 144 */       .func_233815_a_(Attributes.field_233819_b_, 200.0D)
/* 145 */       .func_233815_a_(Attributes.field_233821_d_, 0.28999999165534973D)
/* 146 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 147 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 148 */       .func_233815_a_(Attributes.field_233826_i_, 10.0D)
/* 149 */       .func_233815_a_(Attributes.field_233820_c_, 0.3D)
/* 150 */       .func_233815_a_(Attributes.field_233824_g_, 1.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 155 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_226292_a_(Hand hand, boolean updateSelf) {
/* 160 */     super.func_226292_a_(hand, updateSelf);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MorganEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */