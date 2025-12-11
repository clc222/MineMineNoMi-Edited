/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.BakudanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.MH5Ability;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BakudanWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ChargedCleaveWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GunArrayWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.MH5WrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.SlamWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.WootzNetLauncherWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.donkrieg.DonKriegPhaseSwitcherGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class DonKriegEntity extends OPBossEntity<DonKriegEntity> {
/*  56 */   private static final UUID STANDARD_BAKUDAN_COOLDOWN_BONUS_UUID = UUID.fromString("5eeb8a76-d0d9-4fdd-816d-7a8ff4e903ad");
/*  57 */   private static final UUID STANDARD_KAEN_HOSHA_COOLDOWN_BONUS_UUID = UUID.fromString("6445513d-f018-42db-9be5-4f3f08037a50");
/*  58 */   private static final AttributeModifier GCD_MOD = new AttributeModifier(UUID.fromString("e478aceb-9865-40da-a137-6359ba503bf0"), "GCD Modifier", -5.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  60 */   private final NPCPhase<DonKriegEntity> fistPhase = (NPCPhase<DonKriegEntity>)new SimplePhase("Fist Phase", (MobEntity)this);
/*  61 */   private final NPCPhase<DonKriegEntity> daisensoPhase = (NPCPhase<DonKriegEntity>)new SimplePhase("Daisenso Phase", (MobEntity)this, this::startDaisensoPhaseEvent);
/*  62 */   private final NPCPhase<DonKriegEntity> mh5Phase = (NPCPhase<DonKriegEntity>)new SimplePhase("MH5 Phase", (MobEntity)this, this::startMH5PhaseEvent);
/*     */   
/*  64 */   private final NonNullList<ItemStack> bulletsStash = NonNullList.func_191197_a(5, ItemStack.field_190927_a);
/*  65 */   private final NonNullList<ItemStack> netsStash = NonNullList.func_191197_a(5, ItemStack.field_190927_a);
/*     */   
/*     */   public DonKriegEntity(EntityType type, World world) {
/*  68 */     super(type, world);
/*     */   }
/*     */   
/*     */   public DonKriegEntity(InProgressChallenge challenge) {
/*  72 */     super((EntityType)ModEntities.DON_KRIEG.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  77 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  78 */     this.entityStats.setRace(ModValues.HUMAN);
/*  79 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/*  81 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  83 */     worldData.addTemporaryCrewMember(ModNPCGroups.KRIEG_PIRATES, (LivingEntity)this);
/*     */     
/*  85 */     func_184201_a(EquipmentSlotType.CHEST, new ItemStack((IItemProvider)ModArmors.WOOTZ_STEEL_ARMOR.get()));
/*     */     
/*  87 */     func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(1.1D);
/*  88 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  89 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  90 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(1.5D);
/*     */     
/*  92 */     SprintTowardsTargetGoal sprintGoal = new SprintTowardsTargetGoal((MobEntity)this);
/*     */     
/*  94 */     BakudanWrapperGoal bakudanWrapper = new BakudanWrapperGoal((MobEntity)this);
/*  95 */     ((BakudanAbility)bakudanWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, 100.0F));
/*     */ 
/*     */ 
/*     */     
/*  99 */     MH5WrapperGoal mh5Wrapper = new MH5WrapperGoal((MobEntity)this);
/*     */     
/* 101 */     ImprovedMeleeAttackGoal meleeAttackGoal = new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true);
/* 102 */     meleeAttackGoal.setEarlyStop(() -> Boolean.valueOf(((MH5Ability)mh5Wrapper.getAbility()).isCharging()));
/*     */     
/* 104 */     GunArrayWrapperGoal gunArrayWrapper = new GunArrayWrapperGoal((MobEntity)this);
/*     */     
/* 106 */     KaenBoshiWrapperGoal kaenBoshiWrapper = new KaenBoshiWrapperGoal((MobEntity)this);
/* 107 */     ((KaenBoshiAbility)kaenBoshiWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.addStartEvent(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     WootzNetLauncherWrapperGoal wootzNetLauncherWrapper = new WootzNetLauncherWrapperGoal((MobEntity)this);
/*     */ 
/*     */     
/* 116 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */ 
/*     */     
/* 119 */     this.field_70714_bg.func_75776_a(0, (Goal)new DonKriegPhaseSwitcherGoal(this));
/* 120 */     this.field_70714_bg.func_75776_a(0, (Goal)new MH5PhaseGoal(this));
/*     */ 
/*     */     
/* 123 */     this.fistPhase.addGoal(1, (Goal)meleeAttackGoal);
/* 124 */     this.fistPhase.addGoal(1, (Goal)sprintGoal);
/* 125 */     this.fistPhase.addGoal(3, (Goal)new ChargedPunchWrapperGoal((MobEntity)this));
/* 126 */     this.fistPhase.addGoal(3, (Goal)bakudanWrapper);
/*     */ 
/*     */     
/* 129 */     this.daisensoPhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 350.0F, 2.5F));
/* 130 */     this.daisensoPhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 350.0F, 2.5F));
/* 131 */     this.daisensoPhase.addGoal(1, (Goal)meleeAttackGoal);
/* 132 */     this.daisensoPhase.addGoal(1, (Goal)sprintGoal);
/* 133 */     this.daisensoPhase.addGoal(1, (Goal)wootzNetLauncherWrapper);
/* 134 */     this.daisensoPhase.addGoal(2, (Goal)gunArrayWrapper);
/* 135 */     this.daisensoPhase.addGoal(2, (Goal)kaenBoshiWrapper);
/* 136 */     this.daisensoPhase.addGoal(3, (Goal)new ChargedCleaveWrapperGoal((MobEntity)this));
/* 137 */     this.daisensoPhase.addGoal(3, (Goal)bakudanWrapper);
/*     */ 
/*     */     
/* 140 */     this.mh5Phase.addGoal(1, (Goal)mh5Wrapper);
/*     */     
/* 142 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 143 */       this.entityStats.setDoriki(2000.0D);
/* 144 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 145 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 146 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */       
/* 148 */       fillBulletStash((Item)ModItems.BULLET.get());
/* 149 */       fillNetStash((Item)ModItems.ROPE_NET.get());
/*     */       
/* 151 */       ((BakudanAbility)bakudanWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_BAKUDAN_COOLDOWN_BONUS_UUID, "Standard Bakudan Cooldown Bonus", BonusOperation.MUL, 2.0F));
/*     */ 
/*     */ 
/*     */       
/* 155 */       ((KaenBoshiAbility)kaenBoshiWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(STANDARD_KAEN_HOSHA_COOLDOWN_BONUS_UUID, "Standard Kaen Hosha Cooldown Bonus", BonusOperation.MUL, 2.0F));
/*     */ 
/*     */ 
/*     */       
/* 159 */       getPhaseManager().setPhase(this.fistPhase);
/*     */     } else {
/*     */       
/* 162 */       this.entityStats.setDoriki(10000.0D);
/* 163 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 164 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 165 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 166 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(3.0D);
/* 167 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 168 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(400.0D);
/* 169 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 170 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 171 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 173 */       fillBulletStash((Item)ModItems.KAIROSEKI_BULLET.get());
/* 174 */       fillNetStash((Item)ModItems.KAIROSEKI_NET.get());
/*     */       
/* 176 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/*     */       
/* 178 */       this.fistPhase.addGoal(0, (Goal)new BusoshokuHakiHardeningWrapperGoal((MobEntity)this));
/* 179 */       this.fistPhase.addGoal(3, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/* 180 */       this.fistPhase.addGoal(3, (Goal)new JishinHoWrapperGoal((MobEntity)this));
/*     */       
/* 182 */       this.daisensoPhase.addGoal(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/* 183 */       this.daisensoPhase.addGoal(3, (Goal)new JishinHoWrapperGoal((MobEntity)this));
/* 184 */       this.daisensoPhase.addGoal(3, (Goal)new SlamWrapperGoal((MobEntity)this));
/*     */       
/* 186 */       this.mh5Phase.addGoal(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/* 187 */       this.mh5Phase.addGoal(0, (Goal)new KamieWrapperGoal((MobEntity)this));
/*     */       
/* 189 */       getPhaseManager().setPhase(this.daisensoPhase);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 194 */     return OPEntity.createAttributes()
/* 195 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 196 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 197 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 198 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 199 */       .func_233815_a_(Attributes.field_233826_i_, 10.0D)
/* 200 */       .func_233815_a_(Attributes.field_233820_c_, 0.9D)
/* 201 */       .func_233815_a_(Attributes.field_233824_g_, 1.0D);
/*     */   }
/*     */   
/*     */   private void fillBulletStash(Item bulletType) {
/* 205 */     for (int i = 0; i < this.bulletsStash.size(); i++) {
/* 206 */       ItemStack newStack = bulletType.func_190903_i();
/* 207 */       newStack.func_190920_e(1);
/* 208 */       this.bulletsStash.set(i, newStack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillNetStash(Item netType) {
/* 213 */     for (int i = 0; i < this.netsStash.size(); i++) {
/* 214 */       ItemStack newStack = netType.func_190903_i();
/* 215 */       newStack.func_190920_e(64);
/* 216 */       this.netsStash.set(i, newStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 222 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<ItemStack> func_184209_aF() {
/* 227 */     return Iterables.concat(super.func_184209_aF(), (Iterable)this.bulletsStash, (Iterable)this.netsStash);
/*     */   }
/*     */   
/*     */   public void startDaisensoPhase() {
/* 231 */     getPhaseManager().setPhase(this.daisensoPhase);
/*     */     
/* 233 */     MH5Ability mh5Ability = (MH5Ability)this.abilityData.getEquippedAbility(MH5Ability.INSTANCE);
/* 234 */     if (mh5Ability != null) {
/* 235 */       int startCooldown = isDifficultyHardOrAbove() ? 800 : 400;
/* 236 */       mh5Ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown((LivingEntity)this, startCooldown));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startMH5Phase() {
/* 241 */     getPhaseManager().setPhase(this.mh5Phase);
/*     */   }
/*     */   
/*     */   public boolean hasFistPhaseActive() {
/* 245 */     return this.fistPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean hasDaisensoPhaseActive() {
/* 249 */     return this.daisensoPhase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   public boolean hasMH5PhaseActive() {
/* 253 */     return this.mh5Phase.isActive((IPhasesEntity)this);
/*     */   }
/*     */   
/*     */   private void startDaisensoPhaseEvent(DonKriegEntity entity) {
/* 257 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.DAISENSO.get()));
/*     */     
/* 259 */     if (isDifficultyHardOrAbove()) {
/* 260 */       ModifiableAttributeInstance attr = entity.func_110148_a((Attribute)ModAttributes.GCD.get());
/* 261 */       if (attr != null && !attr.func_180374_a(GCD_MOD)) {
/* 262 */         attr.func_233767_b_(GCD_MOD);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startMH5PhaseEvent(DonKriegEntity entity) {
/* 268 */     func_184201_a(EquipmentSlotType.HEAD, ((Item)ModArmors.MH5_GAS_MASK.get()).func_190903_i());
/* 269 */     func_184201_a(EquipmentSlotType.MAINHAND, ItemStack.field_190927_a);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\kriegpirates\DonKriegEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */