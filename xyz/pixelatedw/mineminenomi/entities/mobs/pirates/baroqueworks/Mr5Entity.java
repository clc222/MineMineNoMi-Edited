/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ExplosionImmunityAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FindPartnerGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.IGoalPartner;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.MeleeRangedStyleSwitchGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.BreezeBreathBombWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.ExplosivePunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.KickBombWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.bomu.NoseFancyCannonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class Mr5Entity extends OPBossEntity<Mr5Entity> implements IRangedAttackMob, IGoalPartner<MissValentineEntity> {
/*  55 */   private static final UUID HARD_BREEZE_BREATH_COOLDOWN_BONUS = UUID.fromString("f417a4af-840f-4f69-86f4-c44a15da5e9a");
/*     */   
/*     */   private static final int PAUSE_TICKS_AFTER_NOSE_FANCY = 40;
/*     */   
/*     */   private MissValentineEntity partner;
/*     */   
/*     */   private boolean lostGun;
/*     */   
/*     */   private NoseFancyCannonWrapperGoal noseFancyCannonWrapper;
/*     */   private BreezeBreathBombWrapperGoal breezeBreathBombWrapper;
/*     */   private NPCPhase<Mr5Entity> meleePhase;
/*     */   private NPCPhase<Mr5Entity> rangePhase;
/*     */   
/*     */   public Mr5Entity(EntityType type, World world) {
/*  69 */     super(type, world);
/*     */   }
/*     */   
/*     */   public Mr5Entity(InProgressChallenge challenge) {
/*  73 */     super((EntityType)ModEntities.MR5.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  78 */     this.meleePhase = (NPCPhase<Mr5Entity>)new SimplePhase("Melee Phase", (MobEntity)this);
/*  79 */     this.rangePhase = (NPCPhase<Mr5Entity>)new SimplePhase("Range Phase", (MobEntity)this);
/*     */     
/*  81 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  83 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  84 */     this.entityStats.setRace(ModValues.HUMAN);
/*  85 */     this.devilFruitData.setDevilFruit(ModAbilities.BOMU_BOMU_NO_MI);
/*     */     
/*  87 */     this.entityStats.setDoriki(2000.0D);
/*  88 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*     */     
/*  90 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  91 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     
/*  93 */     boolean isStandard = getChallengeInfo().isDifficultyStandard();
/*     */ 
/*     */     
/*  96 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */ 
/*     */     
/*  99 */     this.noseFancyCannonWrapper = new NoseFancyCannonWrapperGoal((MobEntity)this);
/* 100 */     this.breezeBreathBombWrapper = new BreezeBreathBombWrapperGoal((MobEntity)this);
/*     */ 
/*     */     
/* 103 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 104 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindPartnerGoal((MobEntity)this, (EntityType)ModEntities.MISS_VALENTINE.get()));
/* 105 */     this.field_70714_bg.func_75776_a(0, (Goal)(new MeleeRangedStyleSwitchGoal((MobEntity)this)).forceMeleeEmptyHanded().addStyleSwitchEvent(100, this::onStyleSwitched));
/* 106 */     this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, ExplosionImmunityAbility.INSTANCE));
/* 107 */     this.field_70714_bg.func_75776_a(3, (Goal)this.noseFancyCannonWrapper);
/*     */ 
/*     */     
/* 110 */     this.rangePhase.addGoal(3, (Goal)this.breezeBreathBombWrapper);
/*     */ 
/*     */     
/* 113 */     this.meleePhase.addGoal(1, (Goal)(new ImprovedMeleeAttackGoal((CreatureEntity)this, 0.949999988079071D, true)).setEarlyStop(this::isInRangeForAbilities));
/* 114 */     this.meleePhase.addGoal(3, (Goal)new KickBombWrapperGoal((MobEntity)this));
/* 115 */     this.meleePhase.addGoal(3, (Goal)new ExplosivePunchWrapperGoal((MobEntity)this));
/*     */ 
/*     */     
/* 118 */     getPhaseManager().setPhase(this.rangePhase);
/*     */     
/* 120 */     spawnGunIfPossible();
/*     */     
/* 122 */     if (isStandard) {
/* 123 */       this.entityStats.setDoriki(2000.0D);
/* 124 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 125 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 127 */       this.rangePhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 150.0F, 2.5F));
/* 128 */       this.rangePhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 80.0F, 5.0F));
/* 129 */       this.rangePhase.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 40, 50.0F));
/*     */     } else {
/*     */       
/* 132 */       this.entityStats.setDoriki(10000.0D);
/* 133 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 134 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 135 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 136 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(3.0D);
/* 137 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(3.0D);
/* 138 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(6.0D);
/* 139 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(400.0D);
/* 140 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 141 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 142 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(12.0D);
/*     */       
/* 144 */       ((BreezeBreathBombAbility)this.breezeBreathBombWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(HARD_BREEZE_BREATH_COOLDOWN_BONUS, "Hard Difficulty Breeze Breath Bonus", BonusOperation.ADD, -140.0F));
/*     */ 
/*     */ 
/*     */       
/* 148 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/*     */       
/* 150 */       this.rangePhase.addGoal(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/* 151 */       this.rangePhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 100.0F, 2.5F));
/* 152 */       this.rangePhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 40.0F, 5.0F));
/* 153 */       this.rangePhase.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 20, 50.0F));
/*     */       
/* 155 */       this.meleePhase.addGoal(0, (Goal)new BusoshokuHakiHardeningWrapperGoal((MobEntity)this));
/* 156 */       this.meleePhase.addGoal(3, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/* 157 */       this.meleePhase.addGoal(3, (Goal)new ShiganWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void spawnGunIfPossible() {
/* 162 */     if (this.lostGun) {
/*     */       return;
/*     */     }
/* 165 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 166 */       func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK.get()));
/*     */     } else {
/*     */       
/* 169 */       func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.WALKER.get()));
/* 170 */       func_184201_a(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.WALKER.get()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isInRangePhase() {
/* 175 */     return getPhaseManager().getCurrentPhase().equals(this.rangePhase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184201_a(EquipmentSlotType slot, ItemStack stack) {
/* 180 */     if (!this.field_70170_p.func_201670_d() && (stack == null || stack.func_190926_b()) && isInRangePhase()) {
/* 181 */       this.lostGun = true;
/*     */     }
/* 183 */     super.func_184201_a(slot, stack);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 187 */     return OPEntity.createAttributes()
/* 188 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 189 */       .func_233815_a_(Attributes.field_233821_d_, 0.2800000011920929D)
/* 190 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 191 */       .func_233815_a_(Attributes.field_233818_a_, 150.0D)
/* 192 */       .func_233815_a_(Attributes.field_233820_c_, 0.2D)
/* 193 */       .func_233815_a_(Attributes.field_233826_i_, 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 198 */     super.func_70071_h_();
/* 199 */     if (!this.field_70170_p.field_72995_K && 
/* 200 */       func_70638_az() != null) {
/* 201 */       boolean canSee = func_70685_l((Entity)func_70638_az());
/* 202 */       double yDiff = Math.abs(func_226278_cu_() - func_70638_az().func_226278_cu_());
/* 203 */       if (yDiff > 5.0D && !canSee && !this.breezeBreathBombWrapper.isForced()) {
/* 204 */         ((BreezeBreathBombAbility)this.breezeBreathBombWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)this));
/* 205 */         this.breezeBreathBombWrapper.forceUse();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onStyleSwitched(LivingEntity entity, boolean isRanged) {
/* 212 */     if (isRanged) {
/* 213 */       getPhaseManager().setPhase(this.rangePhase);
/* 214 */       spawnGunIfPossible();
/*     */     } else {
/*     */       
/* 217 */       getPhaseManager().setPhase(this.meleePhase);
/* 218 */       func_184201_a(EquipmentSlotType.MAINHAND, ItemStack.field_190927_a);
/* 219 */       if (getChallengeInfo().isDifficultyHard()) {
/* 220 */         func_184201_a(EquipmentSlotType.OFFHAND, ItemStack.field_190927_a);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_82196_d(LivingEntity target, float distance) {
/*     */     KairosekiBulletProjectile kairosekiBulletProjectile;
/* 227 */     ItemStack heldItem = func_184614_ca();
/* 228 */     if (heldItem.func_190926_b() || !(heldItem.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModGunItem)) {
/*     */       return;
/*     */     }
/*     */     
/* 232 */     if (!this.noseFancyCannonWrapper.hasTimePassedSinceLastEnd(40.0F)) {
/*     */       return;
/*     */     }
/*     */     
/* 236 */     if (target == null || !target.func_70089_S() || AbilityHelper.isInCreativeOrSpectator(target)) {
/*     */       return;
/*     */     }
/*     */     
/* 240 */     NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 241 */     float damage = 8.0F;
/* 242 */     float speed = 2.0F;
/*     */     
/* 244 */     if (getChallengeInfo().isDifficultyHard()) {
/* 245 */       kairosekiBulletProjectile = new KairosekiBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 246 */       damage = 10.0F;
/* 247 */       speed = 2.5F;
/*     */     } 
/*     */     
/* 250 */     kairosekiBulletProjectile.setDamage(damage);
/* 251 */     this.field_70170_p.func_217376_c((Entity)kairosekiBulletProjectile);
/* 252 */     kairosekiBulletProjectile.func_234612_a_((Entity)this, this.field_70125_A, this.field_70177_z, 0.0F, speed, 0.0F);
/*     */   }
/*     */   
/*     */   private boolean isInRangeForAbilities() {
/* 256 */     if (!GoalUtil.hasAliveTarget((MobEntity)this)) {
/* 257 */       return true;
/*     */     }
/* 259 */     return (Math.abs(func_70032_d((Entity)func_70638_az())) < 30.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public MissValentineEntity getPartner() {
/* 264 */     return this.partner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartner(MissValentineEntity partner) {
/* 269 */     this.partner = partner;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\Mr5Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */