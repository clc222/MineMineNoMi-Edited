/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress10000Ability;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FindPartnerGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.IGoalPartner;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayFromTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo.KiloPress10000WrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kilo.KiloPress1WrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MissValentineEntity extends OPBossEntity<MissValentineEntity> implements IGoalPartner<Mr5Entity> {
/*  47 */   private static final UUID STANDARD_KILO_PRESS_DAMAGE_BONUS = UUID.fromString("6ee8ad2c-3e36-4a3a-a194-169387e32339");
/*     */   
/*     */   private Mr5Entity partner;
/*     */   
/*     */   private KiloPress10000WrapperGoal kiloPress10k;
/*     */   
/*     */   private KiloPress1WrapperGoal kiloPress1;
/*     */   private NPCPhase<MissValentineEntity> helperPhase;
/*     */   private NPCPhase<MissValentineEntity> soloPhase;
/*     */   
/*     */   public MissValentineEntity(EntityType type, World world) {
/*  58 */     super(type, world);
/*     */   }
/*     */   
/*     */   public MissValentineEntity(InProgressChallenge challenge) {
/*  62 */     super((EntityType)ModEntities.MISS_VALENTINE.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  67 */     this.helperPhase = (NPCPhase<MissValentineEntity>)new SimplePhase("Helper Phase", (MobEntity)this);
/*  68 */     this.soloPhase = (NPCPhase<MissValentineEntity>)new SimplePhase("Solo Phase", (MobEntity)this);
/*     */     
/*  70 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  72 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  73 */     this.entityStats.setRace(ModValues.HUMAN);
/*  74 */     this.devilFruitData.setDevilFruit(ModAbilities.KILO_KILO_NO_MI);
/*     */     
/*  76 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*     */     
/*  78 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     
/*  80 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.UMBRELLA.get()));
/*     */ 
/*     */     
/*  83 */     this.kiloPress10k = new KiloPress10000WrapperGoal((MobEntity)this);
/*     */     
/*  85 */     this.kiloPress1 = new KiloPress1WrapperGoal((MobEntity)this);
/*     */ 
/*     */     
/*  88 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */ 
/*     */     
/*  91 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*  92 */     this.field_70714_bg.func_75776_a(3, (Goal)new KiloPunch5000WrapperGoal((MobEntity)this));
/*  93 */     this.field_70714_bg.func_75776_a(4, (Goal)this.kiloPress1);
/*  94 */     this.field_70714_bg.func_75776_a(4, (Goal)this.kiloPress10k);
/*     */     
/*  96 */     this.helperPhase.addGoal(0, (Goal)new FindPartnerGoal((MobEntity)this, (EntityType)ModEntities.MR5.get()));
/*  97 */     this.helperPhase.addGoal(1, (Goal)(new ImprovedMeleeAttackGoal((CreatureEntity)this, 0.8500000238418579D, true)).setEarlyStop(this::isInRangeForAbilities));
/*     */     
/*  99 */     this.soloPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.100000023841858D, true));
/*     */     
/* 101 */     getPhaseManager().setPhase(this.helperPhase);
/*     */     
/* 103 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 104 */       this.entityStats.setDoriki(2000.0D);
/* 105 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 106 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 108 */       ((KiloPress10000Ability)this.kiloPress10k.getAbility()).getComponent(ModAbilityKeys.DAMAGE).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_KILO_PRESS_DAMAGE_BONUS, "Standard Kilo Press Damage Bonus", BonusOperation.MUL, 0.5F));
/*     */ 
/*     */ 
/*     */       
/* 112 */       this.helperPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 200.0F, 4.0F));
/* 113 */       this.helperPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 100.0F, 3.0F));
/* 114 */       this.helperPhase.addGoal(1, (Goal)new RunAwayFromTargetGoal((CreatureEntity)this, 1.5D, 100, 200));
/*     */       
/* 116 */       this.soloPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 250.0F, 3.0F));
/* 117 */       this.soloPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 150.0F, 2.5F));
/*     */     } else {
/*     */       
/* 120 */       this.entityStats.setDoriki(10000.0D);
/* 121 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 122 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 123 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 124 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/* 125 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/* 126 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 127 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(350.0D);
/* 128 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 129 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 130 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(10.0D);
/*     */       
/* 132 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 133 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/* 134 */       this.field_70714_bg.func_75776_a(3, (Goal)new ShiganWrapperGoal((MobEntity)this));
/* 135 */       this.field_70714_bg.func_75776_a(3, (Goal)new RankyakuWrapperGoal((MobEntity)this));
/* 136 */       this.field_70714_bg.func_75776_a(3, (Goal)new SoruWrapperGoal((MobEntity)this));
/*     */       
/* 138 */       this.helperPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 100.0F, 4.0F));
/* 139 */       this.helperPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 60.0F, 3.0F));
/* 140 */       this.helperPhase.addGoal(1, (Goal)new RunAwayFromTargetGoal((CreatureEntity)this, 1.7D, 100, 100));
/*     */       
/* 142 */       this.soloPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 200.0F, 4.0F));
/* 143 */       this.soloPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 150.0F, 3.0F));
/* 144 */       this.soloPhase.addGoal(3, (Goal)new KamieWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 149 */     return OPEntity.createAttributes()
/* 150 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 151 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 152 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 153 */       .func_233815_a_(Attributes.field_233818_a_, 80.0D)
/* 154 */       .func_233815_a_(Attributes.field_233826_i_, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 159 */     super.func_70071_h_();
/* 160 */     if (!this.field_70170_p.field_72995_K && 
/* 161 */       func_70638_az() != null) {
/* 162 */       if (this.field_70173_aa > 100 && this.helperPhase.isActive((IPhasesEntity)this) && this.partner != null && !this.partner.func_70089_S()) {
/* 163 */         getPhaseManager().setPhase(this.soloPhase);
/*     */       }
/*     */       
/* 166 */       boolean canSee = func_70685_l((Entity)func_70638_az());
/* 167 */       double yDiff = Math.abs(func_226278_cu_() - func_70638_az().func_226278_cu_());
/* 168 */       if (yDiff > 10.0D && !canSee && !this.kiloPress1.isForced()) {
/* 169 */         ((KiloPress1Ability)this.kiloPress1.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)this));
/* 170 */         ((KiloPress10000Ability)this.kiloPress10k.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)this));
/* 171 */         this.kiloPress1.forceUse();
/* 172 */         BlockPos tpPos = WyHelper.findValidGroundLocation((Entity)this, func_70638_az().func_233580_cy_(), 10, 10);
/* 173 */         func_223102_j(tpPos.func_177958_n(), (tpPos.func_177956_o() + 35), tpPos.func_177952_p());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isInRangeForAbilities() {
/* 180 */     if (!GoalUtil.hasAliveTarget((MobEntity)this)) {
/* 181 */       return true;
/*     */     }
/* 183 */     return (Math.abs(func_70032_d((Entity)func_70638_az())) < 30.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public Mr5Entity getPartner() {
/* 188 */     return this.partner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartner(Mr5Entity partner) {
/* 193 */     this.partner = partner;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\MissValentineEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */