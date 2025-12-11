/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.OneTwoJangoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ThrowChakramsGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.OneTwoJangoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ChakramItem;
/*     */ 
/*     */ public class JangoEntity extends OPBossEntity<JangoEntity> {
/*  40 */   private NPCPhase<JangoEntity> standardPhase = (NPCPhase<JangoEntity>)new SimplePhase("Standard Phase", (MobEntity)this);
/*  41 */   private NPCPhase<JangoEntity> tauntPhase = (NPCPhase<JangoEntity>)new SimplePhase("Taunt Phase", (MobEntity)this);
/*     */   
/*     */   public JangoEntity(EntityType type, World world) {
/*  44 */     super(type, world);
/*     */   }
/*     */   
/*     */   public JangoEntity(InProgressChallenge challenge) {
/*  48 */     super((EntityType)ModEntities.JANGO.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  53 */     ItemStack headStack = ((Item)ModArmors.WIDE_BRIM_HAT.get()).func_190903_i();
/*  54 */     headStack.func_190925_c("display").func_74768_a("color", 3364244);
/*     */     
/*  56 */     func_184201_a(EquipmentSlotType.HEAD, headStack);
/*  57 */     func_184201_a(EquipmentSlotType.MAINHAND, ((ChakramItem)ModWeapons.CHAKRAM.get()).func_190903_i());
/*  58 */     if (isDifficultyHardOrAbove()) {
/*  59 */       func_184201_a(EquipmentSlotType.OFFHAND, ((ChakramItem)ModWeapons.CHAKRAM.get()).func_190903_i());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void initStats() {
/*  65 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  67 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  68 */     this.entityStats.setRace(ModValues.HUMAN);
/*     */     
/*  70 */     worldData.addTemporaryCrewMember(ModNPCGroups.BLACK_CAT_PIRATES, (LivingEntity)this);
/*     */     
/*  72 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*     */     
/*  74 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  75 */       this.entityStats.setDoriki(1000.0D);
/*  76 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  77 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/*  78 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */     } else {
/*     */       
/*  81 */       this.entityStats.setDoriki(10000.0D);
/*  82 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/*  83 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/*  84 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/*  85 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/*  86 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/*  87 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(5.0D);
/*  88 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(250.0D);
/*  89 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/*  90 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(4.0D);
/*  91 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(10.0D);
/*  92 */       func_110148_a(Attributes.field_233825_h_).func_111128_a(6.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGoals() {
/*  99 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 101 */     OneTwoJangoWrapperGoal oneTwoJangoWrapper = new OneTwoJangoWrapperGoal((MobEntity)this);
/*     */     
/* 103 */     this.standardPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 104 */     this.standardPhase.addGoal(1, (Goal)oneTwoJangoWrapper);
/*     */     
/* 106 */     Predicate<LivingEntity> canDash = entity -> !((OneTwoJangoAbility)oneTwoJangoWrapper.getAbility()).isCharging();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     getPhaseManager().setPhase(this.standardPhase);
/*     */     
/* 116 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 117 */       this.standardPhase.addGoal(0, (Goal)(new DashDodgeProjectilesGoal((MobEntity)this, 400.0F, 1.5F)).setCanUseTest(canDash));
/* 118 */       this.standardPhase.addGoal(0, (Goal)(new DashDodgeTargetGoal((MobEntity)this, 450.0F, 2.0F)).setCanUseTest(canDash));
/* 119 */       this.standardPhase.addGoal(2, (Goal)new ThrowChakramsGoal((MobEntity)this, 200));
/*     */       
/* 121 */       ((OneTwoJangoAbility)oneTwoJangoWrapper.getAbility()).setAffectSelf(true);
/*     */     } else {
/*     */       
/* 124 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 125 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/*     */       
/* 127 */       this.standardPhase.addGoal(0, (Goal)(new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 2.5F)).setCanUseTest(canDash));
/* 128 */       this.standardPhase.addGoal(0, (Goal)(new DashDodgeTargetGoal((MobEntity)this, 250.0F, 2.5F)).setCanUseTest(canDash));
/* 129 */       this.standardPhase.addGoal(1, (Goal)new SoruWrapperGoal((MobEntity)this));
/* 130 */       this.standardPhase.addGoal(1, (Goal)new GeppoWrapperGoal((MobEntity)this));
/* 131 */       this.standardPhase.addGoal(1, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 132 */       this.standardPhase.addGoal(2, (Goal)new ThrowChakramsGoal((MobEntity)this, 100));
/*     */ 
/*     */       
/* 135 */       this.tauntPhase.addGoal(0, (Goal)new TauntTargetGoal((MobEntity)this));
/* 136 */       this.tauntPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 20.0F, 4.0F));
/* 137 */       this.tauntPhase.addGoal(0, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 138 */       this.tauntPhase.addGoal(1, (Goal)(new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true)).setMoveTowardsTarget(false));
/* 139 */       this.tauntPhase.addGoal(1, (Goal)new DashDodgeTargetGoal((MobEntity)this, 60.0F, 4.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 144 */     return OPEntity.createAttributes()
/* 145 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 146 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 147 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 148 */       .func_233815_a_(Attributes.field_233818_a_, 150.0D)
/* 149 */       .func_233815_a_(Attributes.field_233825_h_, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 154 */     super.func_70071_h_();
/* 155 */     if (!this.field_70170_p.field_72995_K && func_70638_az() != null && func_70638_az().func_70089_S() && 
/* 156 */       isDifficultyHardOrAbove())
/* 157 */       if (this.standardPhase.isActive((IPhasesEntity)this) && func_70638_az().func_70644_a((Effect)ModEffects.UNCONSCIOUS.get()) && GoalUtil.hasHealthAbovePercentage((LivingEntity)this, 70.0D)) {
/* 158 */         getPhaseManager().setPhase(this.tauntPhase);
/*     */       }
/* 160 */       else if (this.tauntPhase.isActive((IPhasesEntity)this) && !func_70638_az().func_70644_a((Effect)ModEffects.UNCONSCIOUS.get())) {
/* 161 */         getPhaseManager().setPhase(this.standardPhase);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\JangoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */