/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
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
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.TakedownKickAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mandemontactics.DemonicDanceAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.mandemontactics.DemonicDashAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.TakedownKickWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.ConcasseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.mandemontactics.DemonicDanceWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.mandemontactics.DemonicDashWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.mandemontactics.DemonicSmashWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class GinEntity extends OPBossEntity<GinEntity> {
/*  55 */   private static final UUID HARD_DEMON_DANCE_CHARGE_BONUS_UUID = UUID.fromString("8bde2608-50af-4a26-89a1-28e27ccdb933");
/*  56 */   private static final UUID HARD_DEMON_DASH_CHARGE_BONUS_UUID = UUID.fromString("f62f6b91-677f-4612-8464-f840fd7752a4");
/*  57 */   private static final UUID HARD_CONCASSE_COOLDOWN_BONUS_UUID = UUID.fromString("b88e2563-a90e-4976-b83e-1094b411b0cd");
/*  58 */   private static final UUID HARD_TAKEDOWN_KICK_COOLDOWN_BONUS_UUID = UUID.fromString("6f76d9e5-94ad-48bd-ab28-aae1860ea8f2");
/*     */   
/*     */   private RevengeMeter revengeMeter;
/*     */   
/*     */   private NPCPhase<GinEntity> normalPhase;
/*     */   private NPCPhase<GinEntity> emptyHandedPhase;
/*     */   
/*     */   public GinEntity(EntityType type, World world) {
/*  66 */     super(type, world);
/*     */   }
/*     */   
/*     */   public GinEntity(InProgressChallenge challenge) {
/*  70 */     super((EntityType)ModEntities.GIN.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  75 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */     
/*  77 */     this.normalPhase = (NPCPhase<GinEntity>)new SimplePhase("Normal Phase", (MobEntity)this);
/*  78 */     this.emptyHandedPhase = (NPCPhase<GinEntity>)new SimplePhase("Empty Handed Phase", (MobEntity)this, this::startEmptyHandedPhaseEvent);
/*     */     
/*  80 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  81 */     this.entityStats.setRace(ModValues.HUMAN);
/*  82 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/*  84 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  86 */     worldData.addTemporaryCrewMember(ModNPCGroups.KRIEG_PIRATES, (LivingEntity)this);
/*     */     
/*  88 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.TONFA.get()));
/*  89 */     func_184201_a(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.TONFA.get()));
/*     */     
/*  91 */     func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(1.2D);
/*  92 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  93 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  94 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/*     */ 
/*     */     
/*  97 */     getRevengeMeter().addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(2));
/*  98 */     getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(10));
/*     */     
/* 100 */     DemonicDanceWrapperGoal demonicDanceWrapper = new DemonicDanceWrapperGoal((MobEntity)this);
/*     */     
/* 102 */     DemonicDashWrapperGoal demonicDashWrapper = new DemonicDashWrapperGoal((MobEntity)this);
/* 103 */     ((DemonicDashAbility)demonicDashWrapper.getAbility()).addCanUseCheck((entity, ability) -> 
/* 104 */         (((DemonicDanceAbility)demonicDanceWrapper.getAbility()).isCharging() || ((DemonicDanceAbility)demonicDanceWrapper.getAbility()).isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     DemonicSmashWrapperGoal demonicSmashWrapper = new DemonicSmashWrapperGoal((MobEntity)this);
/* 111 */     ((DemonicSmashAbility)demonicSmashWrapper.getAbility()).addCanUseCheck((entity, ability) -> 
/* 112 */         (((DemonicDanceAbility)demonicDanceWrapper.getAbility()).isCharging() || ((DemonicDanceAbility)demonicDanceWrapper.getAbility()).isContinuous()) ? AbilityUseResult.fail(null) : AbilityUseResult.success());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     ImprovedMeleeAttackGoal meleeAttackGoal = new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true);
/* 119 */     meleeAttackGoal.setEarlyStop(() -> Boolean.valueOf(((DemonicDanceAbility)demonicDanceWrapper.getAbility()).isCharging()));
/*     */     
/* 121 */     TakedownKickWrapperGoal takedownKickWrapper = new TakedownKickWrapperGoal((MobEntity)this);
/* 122 */     takedownKickWrapper.setMinDistance(5.0F);
/*     */ 
/*     */     
/* 125 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 127 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 128 */     this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 300.0F, 2.5F));
/* 129 */     this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 350.0F, 3.0F));
/* 130 */     this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 131 */     this.field_70714_bg.func_75776_a(1, (Goal)meleeAttackGoal);
/* 132 */     this.field_70714_bg.func_75776_a(2, (Goal)takedownKickWrapper);
/*     */     
/* 134 */     this.normalPhase.addGoal(2, (Goal)demonicDanceWrapper);
/* 135 */     this.normalPhase.addGoal(2, (Goal)demonicDashWrapper);
/* 136 */     this.normalPhase.addGoal(2, (Goal)demonicSmashWrapper);
/*     */     
/* 138 */     getPhaseManager().setPhase(this.normalPhase);
/*     */     
/* 140 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 141 */       this.entityStats.setDoriki(2000.0D);
/* 142 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 143 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(2.0D);
/*     */     } else {
/*     */       
/* 146 */       this.entityStats.setDoriki(10000.0D);
/* 147 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 148 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 149 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 150 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/* 151 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/* 152 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(6.0D);
/* 153 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(300.0D);
/* 154 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 155 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 156 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.5D);
/* 157 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(14.0D);
/*     */       
/* 159 */       ((DemonicDanceAbility)demonicDanceWrapper.getAbility()).getComponent(ModAbilityKeys.CHARGE).ifPresent(comp -> comp.getMaxChargeBonusManager().addBonus(HARD_DEMON_DANCE_CHARGE_BONUS_UUID, "Hard Demon Dance Charge Bonus", BonusOperation.MUL, 0.5F));
/*     */ 
/*     */ 
/*     */       
/* 163 */       ((DemonicDashAbility)demonicDashWrapper.getAbility()).getComponent(ModAbilityKeys.CHARGE).ifPresent(comp -> comp.getMaxChargeBonusManager().addBonus(HARD_DEMON_DASH_CHARGE_BONUS_UUID, "Hard Demon Dash Charge Bonus", BonusOperation.MUL, 0.0F));
/*     */ 
/*     */ 
/*     */       
/* 167 */       ConcasseWrapperGoal concasseWrapper = new ConcasseWrapperGoal((MobEntity)this);
/* 168 */       ((ConcasseAbility)concasseWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_CONCASSE_COOLDOWN_BONUS_UUID, "Hard Concasse Cooldown Bonus", BonusOperation.ADD, 100.0F));
/*     */ 
/*     */ 
/*     */       
/* 172 */       ((TakedownKickAbility)takedownKickWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_TAKEDOWN_KICK_COOLDOWN_BONUS_UUID, "Hard Takedown Kick Cooldown Bonus", BonusOperation.ADD, -40.0F));
/*     */ 
/*     */ 
/*     */       
/* 176 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 177 */       this.normalPhase.addGoal(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/* 178 */       this.emptyHandedPhase.addGoal(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/* 180 */       this.emptyHandedPhase.addGoal(2, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/* 181 */       this.emptyHandedPhase.addGoal(2, (Goal)new JishinHoWrapperGoal((MobEntity)this));
/* 182 */       this.emptyHandedPhase.addGoal(2, (Goal)new ShiganWrapperGoal((MobEntity)this));
/* 183 */       this.emptyHandedPhase.addGoal(2, (Goal)new AntiMannerKickCourseWrapperGoal((MobEntity)this));
/*     */       
/* 185 */       this.field_70714_bg.func_75776_a(2, (Goal)new PartyTableKickCourseWrapperGoal((MobEntity)this));
/* 186 */       this.field_70714_bg.func_75776_a(3, (Goal)new RankyakuWrapperGoal((MobEntity)this));
/* 187 */       this.field_70714_bg.func_75776_a(3, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 188 */       this.field_70714_bg.func_75776_a(3, (Goal)concasseWrapper);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 193 */     return OPEntity.createAttributes()
/* 194 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 195 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 196 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 197 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 198 */       .func_233815_a_(Attributes.field_233826_i_, 8.0D)
/* 199 */       .func_233815_a_(Attributes.field_233820_c_, 0.1D)
/* 200 */       .func_233815_a_(Attributes.field_233824_g_, 1.0D)
/* 201 */       .func_233815_a_(Attributes.field_233825_h_, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70652_k(Entity target) {
/* 206 */     if (target instanceof LivingEntity) {
/* 207 */       LivingEntity livingTarget = (LivingEntity)target;
/*     */       
/* 209 */       boolean hasTonfaInMain = (!func_184614_ca().func_190926_b() && func_184614_ca().func_77973_b() == ModWeapons.TONFA.get());
/* 210 */       boolean hasTonfaInOff = (!func_184592_cb().func_190926_b() && func_184592_cb().func_77973_b() == ModWeapons.TONFA.get());
/*     */       
/* 212 */       if (hasTonfaInMain || hasTonfaInOff) {
/* 213 */         ItemsHelper.stopShieldAndStartCooldown(livingTarget, 100);
/* 214 */         return super.func_70652_k(target);
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     return super.func_70652_k(target);
/*     */   }
/*     */   
/*     */   private void startEmptyHandedPhaseEvent(GinEntity entity) {
/* 222 */     func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 223 */     func_110148_a(Attributes.field_233823_f_).func_111128_a(4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 228 */     super.func_70071_h_();
/* 229 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 230 */       getRevengeMeter().tick();
/*     */       
/* 232 */       if (!this.emptyHandedPhase.isActive((IPhasesEntity)this) && func_184614_ca().func_190926_b()) {
/* 233 */         getPhaseManager().setPhase(this.emptyHandedPhase);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 240 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\kriegpirates\GinEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */