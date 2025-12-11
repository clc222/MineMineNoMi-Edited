/*     */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HealComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SPinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUnpinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class FirstAidAbility extends Ability {
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "first_aid", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Heals the user by shift-clicking or an ally by punching them.", null)
/*     */       });
/*     */   
/*     */   private static final int MIN_HEAL = 5;
/*     */   
/*     */   private static final int MAX_HEAL = 50;
/*     */   private static final float HOLD_TIME = 1000.0F;
/*     */   private static final float MIN_COOLDOWN = 300.0F;
/*  53 */   private final HealComponent healComponent = new HealComponent((IAbility)this);
/*  54 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  55 */     .addStartEvent(this::startContinuityEvent)
/*  56 */     .addTickEvent(this::duringContinuityEvent)
/*  57 */     .addEndEvent(this::endContinuityEvent);
/*  58 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent);
/*  59 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnDamageEvent(this::onDamageTaken);
/*     */   
/*  61 */   public static final AbilityCore<FirstAidAbility> INSTANCE = (new AbilityCore.Builder("First Aid", AbilityCategory.STYLE, FirstAidAbility::new))
/*  62 */     .addDescriptionLine(DESCRIPTION)
/*  63 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), HealComponent.getTooltip(5.0F, 50.0F)
/*  64 */       }).setSourceType(new SourceType[] { SourceType.FIST
/*  65 */       }).setUnlockCheck(FirstAidAbility::canUnlock)
/*  66 */     .build();
/*     */   
/*     */   private LivingEntity target;
/*     */   
/*     */   private boolean pinnedCamera = false;
/*  71 */   private Interval particleInterval = Interval.startAtMax(20);
/*     */   
/*     */   public FirstAidAbility(AbilityCore<FirstAidAbility> core) {
/*  74 */     super(core);
/*     */     
/*  76 */     this.isNew = true;
/*     */     
/*  78 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*     */     
/*  80 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.healComponent, (AbilityComponent)this.damageTakenComponent });
/*  81 */     addCanUseCheck(AbilityHelper::requiresMedicBag);
/*  82 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  86 */     if (entity.func_213453_ef()) {
/*  87 */       this.target = entity;
/*     */       
/*  89 */       this.continuousComponent.startContinuity(entity, 1000.0F);
/*     */     } else {
/*  91 */       this.continuousComponent.triggerContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  96 */     this.particleInterval.restartIntervalToZero();
/*  97 */     this.pinnedCamera = false;
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 101 */     if (this.target == null) {
/*     */       return;
/*     */     }
/*     */     
/* 105 */     if (this.continuousComponent.isInfinite()) {
/* 106 */       this.continuousComponent.setThresholdTime(entity, 1000.0F);
/* 107 */     } else if (this.target != null) {
/* 108 */       if (!this.pinnedCamera) {
/* 109 */         if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 110 */           WyNetwork.sendTo(SPinCameraPacket.pinClampedYawAndPitch(entity.field_70177_z, 10.0F, entity.field_70125_A, 10.0F), (PlayerEntity)entity);
/*     */         }
/* 112 */         this.pinnedCamera = true;
/*     */       } 
/*     */       
/* 115 */       this.target.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */       
/* 117 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */     } 
/*     */     
/* 120 */     if (this.particleInterval.canTick()) {
/* 121 */       applyFirstAid(entity, this.target, this);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 126 */     this.target = null;
/*     */     
/* 128 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 129 */       WyNetwork.sendTo(new SUnpinCameraPacket(), (PlayerEntity)entity);
/*     */     }
/*     */     
/* 132 */     this.cooldownComponent.startCooldown(entity, 300.0F + this.continuousComponent.getContinueTime());
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 136 */     if (this.continuousComponent.isContinuous() && this.continuousComponent.isInfinite() && entity.func_184614_ca().func_190926_b()) {
/* 137 */       this.target = target;
/* 138 */       return HitTriggerComponent.HitResult.FAIL;
/*     */     } 
/* 140 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 144 */     if (this.continuousComponent.isContinuous() && damage >= 10.0F) {
/* 145 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/* 148 */     return damage;
/*     */   }
/*     */   
/*     */   private static void applyFirstAid(LivingEntity entity, LivingEntity target, FirstAidAbility ability) {
/* 152 */     Optional<ItemStack> medicBag = ItemsHelper.findItemInSlot(entity, EquipmentSlotType.CHEST, (Item)ModArmors.MEDIC_BAG.get());
/*     */     
/* 154 */     if (!medicBag.isPresent()) {
/*     */       return;
/*     */     }
/*     */     
/* 158 */     float healAmount = (float)WyHelper.percentage(1.0D, target.func_110138_aP());
/*     */     
/* 160 */     ability.healComponent.healTarget(entity, target, healAmount);
/*     */     
/* 162 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FIRST_AID.get(), (Entity)target, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */     
/* 164 */     if (target.func_110143_aJ() >= target.func_110138_aP()) {
/* 165 */       ability.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 170 */     if (!(entity instanceof PlayerEntity)) {
/* 171 */       return false;
/*     */     }
/*     */     
/* 174 */     PlayerEntity player = (PlayerEntity)entity;
/* 175 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 176 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 178 */     return (props.isDoctor() && questProps.hasFinishedQuest(ModQuests.DOCTOR_TRIAL_01));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\FirstAidAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */