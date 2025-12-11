/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.PearlFireAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.PearlFireWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.TekkaiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PearlEntity extends OPBossEntity<PearlEntity> {
/*  51 */   private static final AttributeModifier ATTACK_MODIFIER = new AttributeModifier(UUID.fromString("989e4cf0-cada-442c-b6e1-c3eddca0545f"), "Attack Damage Bonus", 2.0D, AttributeModifier.Operation.ADDITION);
/*  52 */   private static final AttributeModifier PUNCH_MODIFIER = new AttributeModifier(UUID.fromString("2cba6879-f98d-404f-b169-0d3e2a3dd903"), "Punch Damage Bonus", 2.0D, AttributeModifier.Operation.ADDITION);
/*  53 */   private static final AttributeModifier ARMOR_TOUGHNESS_MODIFIER = new AttributeModifier(UUID.fromString("30afa6b8-6318-428b-89a8-fad41d228d3a"), "Armor Toughness Bonus", 2.0D, AttributeModifier.Operation.MULTIPLY_BASE);
/*  54 */   private static final UUID REVENGE_ATTACK_MODIFIER = UUID.fromString("127f4823-6440-4175-b590-5011b2fbe67c");
/*  55 */   private static final UUID REVENGE_PUNCH_MODIFIER = UUID.fromString("dba9b47e-8611-4245-a7e5-f46f7a0924e3");
/*     */   
/*     */   private RevengeMeter revengeMeter;
/*     */   
/*     */   private NPCPhase<PearlEntity> normalPhase;
/*     */   
/*     */   private NPCPhase<PearlEntity> flamingPhase;
/*     */   private int tekkaiAbuseStartCounter;
/*     */   private int tekkaiWaitCounter;
/*     */   private int prevRevengeVal;
/*     */   
/*     */   public PearlEntity(EntityType type, World world) {
/*  67 */     super(type, world);
/*     */   }
/*     */   
/*     */   public PearlEntity(InProgressChallenge challenge) {
/*  71 */     super((EntityType)ModEntities.PEARL.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  76 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 1000, 1);
/*     */     
/*  78 */     this.normalPhase = (NPCPhase<PearlEntity>)new SimplePhase("Normal Phase", (MobEntity)this);
/*  79 */     this.flamingPhase = (NPCPhase<PearlEntity>)new SimplePhase("Flaming Phase", (MobEntity)this, this::startFlamingPhaseEvent, this::stopFlamingPhaseEvent);
/*     */     
/*  81 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  82 */     this.entityStats.setRace(ModValues.HUMAN);
/*  83 */     this.entityStats.setFightingStyle(ModValues.BRAWLER);
/*     */     
/*  85 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  87 */     worldData.addTemporaryCrewMember(ModNPCGroups.KRIEG_PIRATES, (LivingEntity)this);
/*     */     
/*  89 */     func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModArmors.PEARL_HAT.get()));
/*  90 */     func_184201_a(EquipmentSlotType.CHEST, new ItemStack((IItemProvider)ModArmors.PEARL_ARMOR.get()));
/*  91 */     func_184201_a(EquipmentSlotType.LEGS, new ItemStack((IItemProvider)ModArmors.PEARL_LEGS.get()));
/*     */     
/*  93 */     func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(1.1D);
/*  94 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  95 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  96 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(1.5D);
/*     */ 
/*     */     
/*  99 */     getRevengeMeter().addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(5));
/*     */     
/* 101 */     ChargedPunchWrapperGoal chargedPunchWrapper = new ChargedPunchWrapperGoal((MobEntity)this);
/*     */     
/* 103 */     TekkaiWrapperGoal tekkaiWrapper = new TekkaiWrapperGoal((MobEntity)this);
/* 104 */     ((TekkaiAbility)tekkaiWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> {
/*     */           comp.addStartEvent(110, ());
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
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           comp.addTickEvent(110, ());
/*     */         });
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     PearlFireWrapperGoal pearlFireWrapper = new PearlFireWrapperGoal((MobEntity)this);
/* 139 */     ((PearlFireAbility)pearlFireWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.addTickEvent(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/* 148 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/* 149 */     this.field_70714_bg.func_75776_a(2, (Goal)chargedPunchWrapper);
/* 150 */     this.field_70714_bg.func_75776_a(2, (Goal)tekkaiWrapper);
/*     */     
/* 152 */     this.flamingPhase.addGoal(0, (Goal)pearlFireWrapper);
/*     */     
/* 154 */     getPhaseManager().setPhase(this.normalPhase);
/*     */     
/* 156 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 157 */       this.entityStats.setDoriki(2000.0D);
/* 158 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/* 159 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 160 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(4.0D);
/*     */     } else {
/*     */       
/* 163 */       this.entityStats.setDoriki(10000.0D);
/* 164 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 165 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 166 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 167 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(3.0D);
/* 168 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(4.0D);
/* 169 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(250.0D);
/* 170 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(20.0D);
/* 171 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 172 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 174 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 175 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/* 177 */       this.field_70714_bg.func_75776_a(2, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/* 178 */       this.field_70714_bg.func_75776_a(2, (Goal)new JishinHoWrapperGoal((MobEntity)this));
/* 179 */       this.field_70714_bg.func_75776_a(2, (Goal)new BellyFlopWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 184 */     return OPEntity.createAttributes()
/* 185 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 186 */       .func_233815_a_(Attributes.field_233821_d_, 0.2800000011920929D)
/* 187 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 188 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 189 */       .func_233815_a_(Attributes.field_233826_i_, 10.0D)
/* 190 */       .func_233815_a_(Attributes.field_233827_j_, 4.0D)
/* 191 */       .func_233815_a_(Attributes.field_233820_c_, 1.0D)
/* 192 */       .func_233815_a_(Attributes.field_233824_g_, 1.0D)
/* 193 */       .func_233815_a_(Attributes.field_233825_h_, 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 198 */     super.func_70071_h_();
/*     */     
/* 200 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 201 */       getRevengeMeter().tick();
/*     */       
/* 203 */       if (this.field_70173_aa % 10 == 0) {
/* 204 */         int physicalHits = getRevengeMeter().countCheckTriggers(PhysicalHitRevengeCheck.class);
/* 205 */         if (this.normalPhase.isActive((IPhasesEntity)this) && (func_110143_aJ() <= WyHelper.percentage(30.0D, func_110138_aP()) || physicalHits >= 20)) {
/* 206 */           getPhaseManager().setPhase(this.flamingPhase);
/*     */         }
/*     */         
/* 209 */         if (this.flamingPhase.isActive((IPhasesEntity)this) && (func_184582_a(EquipmentSlotType.HEAD).func_190926_b() || func_184582_a(EquipmentSlotType.CHEST)
/* 210 */           .func_190926_b() || func_184582_a(EquipmentSlotType.LEGS).func_190926_b())) {
/* 211 */           getPhaseManager().setPhase(this.normalPhase);
/*     */         }
/*     */         
/* 214 */         if (isDifficultyHard()) {
/* 215 */           int revengeVal = getRevengeMeter().getRevengeValue();
/* 216 */           if (revengeVal > 10 && revengeVal != this.prevRevengeVal) {
/* 217 */             this.prevRevengeVal = revengeVal;
/* 218 */             double damageBonus = revengeVal / 15.0D;
/*     */             
/* 220 */             func_110148_a(Attributes.field_233823_f_).func_188479_b(REVENGE_ATTACK_MODIFIER);
/* 221 */             func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_188479_b(REVENGE_PUNCH_MODIFIER);
/*     */             
/* 223 */             func_110148_a(Attributes.field_233823_f_).func_233767_b_(new AttributeModifier(REVENGE_ATTACK_MODIFIER, "Revenge Attack Bonus", damageBonus, AttributeModifier.Operation.ADDITION));
/* 224 */             func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_233767_b_(new AttributeModifier(REVENGE_PUNCH_MODIFIER, "Revenge Punch Bonus", damageBonus, AttributeModifier.Operation.ADDITION));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startFlamingPhaseEvent(PearlEntity entity) {
/* 232 */     func_110148_a(Attributes.field_233823_f_).func_233767_b_(ATTACK_MODIFIER);
/* 233 */     func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_233767_b_(PUNCH_MODIFIER);
/* 234 */     func_110148_a(Attributes.field_233827_j_).func_233767_b_(ARMOR_TOUGHNESS_MODIFIER);
/*     */     
/* 236 */     pushEnemiesAway(5.0D);
/*     */   }
/*     */   
/*     */   private void stopFlamingPhaseEvent(PearlEntity entity) {
/* 240 */     func_110148_a(Attributes.field_233823_f_).func_111124_b(ATTACK_MODIFIER);
/* 241 */     func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111124_b(PUNCH_MODIFIER);
/* 242 */     func_110148_a(Attributes.field_233827_j_).func_111124_b(ARMOR_TOUGHNESS_MODIFIER);
/*     */   }
/*     */   
/*     */   private void pushEnemiesAway(double power) {
/* 246 */     List<LivingEntity> targets = TargetHelper.getEntitiesInArea((LivingEntity)this, 15.0D, TargetsPredicate.DEFAULT_AREA_CHECK, new Class[] { LivingEntity.class });
/* 247 */     for (LivingEntity target : targets) {
/* 248 */       Vector3d dirVec = target.func_213303_ch().func_178788_d(func_213303_ch()).func_72432_b().func_216372_d(power, 1.0D, power);
/* 249 */       AbilityHelper.setDeltaMovement((Entity)target, dirVec.field_72450_a, 0.0D, dirVec.field_72449_c);
/*     */     } 
/*     */     
/* 252 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BERSERKER_POWERUP.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void resetCooldowns() {
/* 256 */     for (Iterator<IAbility> iterator = AbilityDataCapability.get((LivingEntity)this).getEquippedAbilities().iterator(); iterator.hasNext(); ) { IAbility ability = iterator.next();
/* 257 */       if (ability == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 261 */       ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.stopCooldown((LivingEntity)this)); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 267 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\kriegpirates\PearlEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */