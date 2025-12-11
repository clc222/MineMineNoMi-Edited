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
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.BellyFlopAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.StealthFootAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.TackleAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BellyFlopWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.StealthFootWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TackleWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.TekkaiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BuchiEntity extends OPBossEntity<BuchiEntity> implements IGoalPartner<ShamEntity> {
/*  43 */   private static final UUID HARD_BELLY_STOMP_COOLDOWN_BONUS_UUID = UUID.fromString("28edc8a0-6668-4abc-ad2d-885ef10d6806");
/*  44 */   private static final UUID HARD_STEALTH_FOOT_COOLDOWN_BONUS_UUID = UUID.fromString("8b63c5b3-6b2f-4ef6-9397-96c3c5b6946d");
/*     */   
/*     */   private ShamEntity partner;
/*     */   
/*     */   public BuchiEntity(EntityType type, World world) {
/*  49 */     super(type, world);
/*     */   }
/*     */   
/*     */   public BuchiEntity(InProgressChallenge challenge) {
/*  53 */     super((EntityType)ModEntities.BUCHI.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  58 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  59 */     this.entityStats.setRace(ModValues.HUMAN);
/*  60 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/*  62 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  64 */     worldData.addTemporaryCrewMember(ModNPCGroups.BLACK_CAT_PIRATES, (LivingEntity)this);
/*     */     
/*  66 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  67 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  68 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(1.0D);
/*     */     
/*  70 */     ItemStack capeStack = new ItemStack((IItemProvider)ModArmors.FLUFFY_CAPE.get());
/*  71 */     capeStack.func_190925_c("display").func_74768_a("color", WyHelper.hexToRGB("#E69FEA").getRGB());
/*  72 */     func_184201_a(EquipmentSlotType.CHEST, capeStack);
/*     */     
/*  74 */     BellyFlopWrapperGoal bellyStompWrapper = new BellyFlopWrapperGoal((MobEntity)this);
/*     */     
/*  76 */     TackleWrapperGoal tackleWrapper = new TackleWrapperGoal((MobEntity)this);
/*     */ 
/*     */     
/*  79 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/*  81 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindPartnerGoal((MobEntity)this, (EntityType)ModEntities.SHAM.get()));
/*  82 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*     */     
/*  84 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  85 */     this.field_70714_bg.func_75776_a(2, (Goal)new ChargedPunchWrapperGoal((MobEntity)this));
/*  86 */     this.field_70714_bg.func_75776_a(2, (Goal)bellyStompWrapper);
/*  87 */     this.field_70714_bg.func_75776_a(2, (Goal)tackleWrapper);
/*     */     
/*  89 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  90 */       this.entityStats.setDoriki(2000.0D);
/*  91 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  92 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(2.0D);
/*     */     } else {
/*     */       
/*  95 */       this.entityStats.setDoriki(10000.0D);
/*  96 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/*  97 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/*  98 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/*  99 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 100 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(4.0D);
/* 101 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(5.0D);
/* 102 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(300.0D);
/* 103 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 104 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(6.0D);
/* 105 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(2.0D);
/* 106 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(12.0D);
/*     */       
/* 108 */       StealthFootWrapperGoal stealthFootWrapper = new StealthFootWrapperGoal((MobEntity)this);
/* 109 */       ((StealthFootAbility)stealthFootWrapper.getAbility()).addCanUseCheck((entity, ability) -> (func_110143_aJ() <= WyHelper.percentage(30.0D, func_110138_aP())) ? AbilityUseResult.success() : AbilityUseResult.fail(null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       ((StealthFootAbility)stealthFootWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_STEALTH_FOOT_COOLDOWN_BONUS_UUID, "Hard Stealth Foot Cooldown Bonus", BonusOperation.ADD, 300.0F));
/*     */ 
/*     */ 
/*     */       
/* 119 */       ((BellyFlopAbility)bellyStompWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_BELLY_STOMP_COOLDOWN_BONUS_UUID, "Belly Stomp Cooldown Bonus", BonusOperation.ADD, -80.0F));
/*     */ 
/*     */ 
/*     */       
/* 123 */       DashDodgeTargetGoal dashDodgeGoal = new DashDodgeTargetGoal((MobEntity)this, 300.0F, 3.0F);
/* 124 */       dashDodgeGoal.setCanUseTest(entity -> ((BellyFlopAbility)bellyStompWrapper.getAbility()).isContinuous() ? false : (!((TackleAbility)tackleWrapper.getAbility()).isContinuous()));
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
/* 136 */       DashDodgeProjectilesGoal dashProjGoal = new DashDodgeProjectilesGoal((MobEntity)this, 250.0F, 3.0F);
/* 137 */       dashProjGoal.setCanUseTest(entity -> ((BellyFlopAbility)bellyStompWrapper.getAbility()).isContinuous() ? false : (!((TackleAbility)tackleWrapper.getAbility()).isContinuous()));
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
/* 149 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 150 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/* 151 */       this.field_70714_bg.func_75776_a(0, (Goal)dashProjGoal);
/* 152 */       this.field_70714_bg.func_75776_a(0, (Goal)dashDodgeGoal);
/* 153 */       this.field_70714_bg.func_75776_a(2, (Goal)new JishinHoWrapperGoal((MobEntity)this));
/* 154 */       this.field_70714_bg.func_75776_a(2, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/* 155 */       this.field_70714_bg.func_75776_a(2, (Goal)new TekkaiWrapperGoal((MobEntity)this));
/* 156 */       this.field_70714_bg.func_75776_a(2, (Goal)stealthFootWrapper);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 161 */     return OPEntity.createAttributes()
/* 162 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 163 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 164 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 165 */       .func_233815_a_(Attributes.field_233818_a_, 150.0D)
/* 166 */       .func_233815_a_(Attributes.field_233820_c_, 0.1D)
/* 167 */       .func_233815_a_(Attributes.field_233826_i_, 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ShamEntity getPartner() {
/* 173 */     return this.partner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartner(ShamEntity partner) {
/* 178 */     this.partner = partner;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\blackcatpirates\BuchiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */