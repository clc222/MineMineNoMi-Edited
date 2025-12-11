/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.blackcatpirates;
/*     */ import java.util.UUID;
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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ShakushiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.GankingRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.TargetRunningAwayRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ShakushiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.StealthFootWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.RankyakuWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.SoruWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KuroEntity extends OPBossEntity<KuroEntity> {
/*  48 */   private static final UUID SHAKUSHI_RANGE_BONUS_UUID = UUID.fromString("80e205c9-4fc8-4fed-97ad-f3d8cec36bd6");
/*  49 */   private static final UUID HARD_SORU_COOLDOWN_BONUS_UUID = UUID.fromString("600830a0-5796-489b-892e-45432d273d29");
/*     */   
/*     */   private RevengeMeter revengeMeter;
/*     */   
/*     */   private NPCPhase<KuroEntity> normalPhase;
/*     */   
/*     */   private NPCPhase<KuroEntity> ultiPhase;
/*     */   private ShakushiWrapperGoal shakushiWrapper;
/*     */   
/*     */   public KuroEntity(EntityType type, World world) {
/*  59 */     super(type, world);
/*     */   }
/*     */   
/*     */   public KuroEntity(InProgressChallenge challenge) {
/*  63 */     super((EntityType)ModEntities.KURO.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  68 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */     
/*  70 */     this.normalPhase = (NPCPhase<KuroEntity>)new SimplePhase("Normal Phase", (MobEntity)this);
/*  71 */     this.ultiPhase = (NPCPhase<KuroEntity>)new SimplePhase("Ulti Phase", (MobEntity)this);
/*     */     
/*  73 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  74 */     this.entityStats.setRace(ModValues.HUMAN);
/*  75 */     this.entityStats.setFightingStyle(ModValues.SWORDSMAN);
/*     */     
/*  77 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  79 */     worldData.addTemporaryCrewMember(ModNPCGroups.BLACK_CAT_PIRATES, (LivingEntity)this);
/*     */     
/*  81 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.CAT_CLAWS.get()));
/*  82 */     func_184201_a(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.CAT_CLAWS.get()));
/*     */     
/*  84 */     func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(1.1D);
/*  85 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  86 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  87 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/*     */ 
/*     */     
/*  90 */     getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(3));
/*  91 */     getRevengeMeter().addCheck((IRevengeCheck)new GankingRevengeCheck(10, 5.0F));
/*     */     
/*  93 */     this.shakushiWrapper = new ShakushiWrapperGoal((MobEntity)this);
/*  94 */     ((ShakushiAbility)this.shakushiWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.addStartEvent(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     ((ShakushiAbility)this.shakushiWrapper.getAbility()).getComponent(ModAbilityKeys.RANGE).ifPresent(comp -> comp.getBonusManager().addBonus(SHAKUSHI_RANGE_BONUS_UUID, "Shakushi Range Bonus", BonusOperation.ADD, 40.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 108 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*     */     
/* 110 */     this.normalPhase.addGoal(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 111 */     this.normalPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 300.0F, 2.5F));
/* 112 */     this.normalPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 350.0F, 3.0F));
/* 113 */     this.normalPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 114 */     this.normalPhase.addGoal(2, (Goal)new TakedownKickWrapperGoal((MobEntity)this));
/* 115 */     this.normalPhase.addGoal(2, (Goal)new StealthFootWrapperGoal((MobEntity)this));
/*     */     
/* 117 */     this.ultiPhase.addGoal(0, (Goal)this.shakushiWrapper);
/*     */     
/* 119 */     getPhaseManager().setPhase(this.normalPhase);
/*     */     
/* 121 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 122 */       this.entityStats.setDoriki(2000.0D);
/* 123 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 124 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(2.0D);
/*     */     } else {
/*     */       
/* 127 */       this.entityStats.setDoriki(10000.0D);
/* 128 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 129 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 130 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 131 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/* 132 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/* 133 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(6.0D);
/* 134 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(300.0D);
/* 135 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 136 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 137 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.5D);
/* 138 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(14.0D);
/* 139 */       func_110148_a(Attributes.field_233825_h_).func_111128_a(6.0D);
/*     */       
/* 141 */       getRevengeMeter().addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(2));
/* 142 */       getRevengeMeter().addCheck((IRevengeCheck)new TargetRunningAwayRevengeCheck(5, 10.0F));
/*     */       
/* 144 */       RankyakuWrapperGoal rankyakuWrapper = new RankyakuWrapperGoal((MobEntity)this);
/* 145 */       ((RankyakuAbility)rankyakuWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 100.0F));
/*     */ 
/*     */ 
/*     */       
/* 149 */       SoruWrapperGoal soruWrapper = new SoruWrapperGoal((MobEntity)this);
/* 150 */       ((SoruAbility)soruWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_SORU_COOLDOWN_BONUS_UUID, "Hard Soru Cooldown Bonus", BonusOperation.MUL, 0.5F));
/*     */ 
/*     */ 
/*     */       
/* 154 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 155 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/*     */       
/* 157 */       this.normalPhase.addGoal(1, (Goal)new GeppoWrapperGoal((MobEntity)this));
/* 158 */       this.normalPhase.addGoal(2, (Goal)new AntiMannerKickCourseWrapperGoal((MobEntity)this));
/* 159 */       this.normalPhase.addGoal(2, (Goal)new SoruWrapperGoal((MobEntity)this));
/* 160 */       this.normalPhase.addGoal(2, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 161 */       this.normalPhase.addGoal(3, (Goal)rankyakuWrapper);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 166 */     return OPEntity.createAttributes()
/* 167 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 168 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 169 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 170 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 171 */       .func_233815_a_(Attributes.field_233826_i_, 8.0D)
/* 172 */       .func_233815_a_(Attributes.field_233820_c_, 0.15D)
/* 173 */       .func_233815_a_(Attributes.field_233825_h_, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 178 */     super.func_70071_h_();
/* 179 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 180 */       getRevengeMeter().tick();
/*     */       
/* 182 */       boolean isUltiAvailable = ((Boolean)((ShakushiAbility)this.shakushiWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).map(comp -> Boolean.valueOf(comp.isOnCooldown())).orElse(Boolean.valueOf(false))).booleanValue();
/* 183 */       if (this.normalPhase.isActive((IPhasesEntity)this) && !isUltiAvailable && (getRevengeMeter().isRevengeMaxed() || func_110143_aJ() <= WyHelper.percentage(20.0D, func_110138_aP()))) {
/* 184 */         getPhaseManager().setPhase(this.ultiPhase);
/* 185 */         getRevengeMeter().setRevengeValue(0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 192 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\blackcatpirates\KuroEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */