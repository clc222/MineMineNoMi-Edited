/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
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
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.BellyFlopAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.WeaponSpinAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BellyFlopWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.SlamWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.WeaponSpinWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sube.SubeSubeDeflectWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class AlvidaEntity extends OPBossEntity<AlvidaEntity> {
/*  40 */   private static final AbilityPool2 ALVIDA = (new AbilityPool2()).addFlag("ignoreCooldown", true);
/*     */   
/*  42 */   private NPCPhase<AlvidaEntity> standardPhase = (NPCPhase<AlvidaEntity>)new SimplePhase("Standard Phase", (MobEntity)this);
/*  43 */   private NPCPhase<AlvidaEntity> slidePhase = (NPCPhase<AlvidaEntity>)new SimplePhase("Slide Phase", (MobEntity)this);
/*     */   
/*     */   public AlvidaEntity(EntityType type, World world) {
/*  46 */     super(type, world);
/*     */   }
/*     */   
/*     */   public AlvidaEntity(EntityType type, InProgressChallenge challenge) {
/*  50 */     super(type, challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  55 */     ItemStack plumeHeadStack = ((Item)ModArmors.PLUME_HAT.get()).func_190903_i();
/*  56 */     plumeHeadStack.func_190925_c("display").func_74768_a("color", 12788538);
/*     */     
/*  58 */     func_184201_a(EquipmentSlotType.HEAD, plumeHeadStack);
/*  59 */     func_184201_a(EquipmentSlotType.MAINHAND, ((DyeableModSwordItem)ModWeapons.MACE.get()).func_190903_i());
/*     */   }
/*     */ 
/*     */   
/*     */   public void initStats() {
/*  64 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  66 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  67 */     this.entityStats.setRace(ModValues.HUMAN);
/*     */     
/*  69 */     worldData.addTemporaryCrewMember(ModNPCGroups.BUGGY_PIRATES, (LivingEntity)this);
/*     */     
/*  71 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(3.0D);
/*  72 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     
/*  74 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*     */     
/*  76 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  77 */       this.entityStats.setDoriki(1000.0D);
/*  78 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*  79 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/*  80 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */     } else {
/*     */       
/*  83 */       this.devilFruitData.setDevilFruit(ModAbilities.SUBE_SUBE_NO_MI);
/*     */       
/*  85 */       this.entityStats.setDoriki(10000.0D);
/*  86 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/*  87 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/*  88 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/*  89 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(3.0D);
/*  90 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/*  91 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(300.0D);
/*  92 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(10.0D);
/*  93 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/*  94 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGoals() {
/* 101 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 103 */     SlamWrapperGoal slamWrapper = new SlamWrapperGoal((MobEntity)this);
/* 104 */     ((SlamAbility)slamWrapper.getAbility()).addComponents(new AbilityComponent[] { (AbilityComponent)new PoolComponent(slamWrapper.getAbility(), ALVIDA, new AbilityPool2[0]) });
/*     */     
/* 106 */     WeaponSpinWrapperGoal weaponSpinWrapper = new WeaponSpinWrapperGoal((MobEntity)this);
/* 107 */     ((WeaponSpinAbility)weaponSpinWrapper.getAbility()).addComponents(new AbilityComponent[] { (AbilityComponent)new PoolComponent(weaponSpinWrapper.getAbility(), ALVIDA, new AbilityPool2[0]) });
/*     */     
/* 109 */     this.standardPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 110 */     this.standardPhase.addGoal(2, (Goal)slamWrapper);
/* 111 */     this.standardPhase.addGoal(2, (Goal)weaponSpinWrapper);
/* 112 */     this.standardPhase.addGoal(2, (Goal)new BattoStrikeWrapperGoal((MobEntity)this));
/*     */ 
/*     */     
/* 115 */     getPhaseManager().setPhase(this.standardPhase);
/*     */     
/* 117 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 118 */       BellyFlopWrapperGoal bellyFlopWrapper = new BellyFlopWrapperGoal((MobEntity)this);
/* 119 */       ((BellyFlopAbility)bellyFlopWrapper.getAbility()).addComponents(new AbilityComponent[] { (AbilityComponent)new PoolComponent(bellyFlopWrapper.getAbility(), ALVIDA, new AbilityPool2[0]) });
/* 120 */       ((BellyFlopAbility)bellyFlopWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 100.0F));
/* 121 */       this.standardPhase.addGoal(2, (Goal)bellyFlopWrapper);
/*     */     } else {
/*     */       
/* 124 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 125 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/*     */       
/* 127 */       this.standardPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 250.0F, 1.25F));
/* 128 */       this.standardPhase.addGoal(0, (Goal)new GeppoWrapperGoal((MobEntity)this));
/* 129 */       this.standardPhase.addGoal(2, (Goal)new SubeSubeSpurWrapperGoal((MobEntity)this));
/* 130 */       this.standardPhase.addGoal(2, (Goal)new SubeSubeDeflectWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 135 */     return OPEntity.createAttributes()
/* 136 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 137 */       .func_233815_a_(Attributes.field_233821_d_, 0.28999999165534973D)
/* 138 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 139 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 140 */       .func_233815_a_(Attributes.field_233826_i_, 4.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\AlvidaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */