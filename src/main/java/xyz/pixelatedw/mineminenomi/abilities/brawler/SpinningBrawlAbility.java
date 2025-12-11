/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.play.server.SPlayerPositionLookPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SpinningBrawlAbility extends Ability {
/*  50 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spinning_brawl", new Pair[] {
/*  51 */         (Pair)ImmutablePair.of("Grabs a nearby enemy spinning them around damaging any nearby entity it touches, and ending by throwing the grabbed entity a few blocks away.", null)
/*     */       });
/*     */   
/*     */   private static final double THROW_POWER_XZ = 2.0D;
/*     */   private static final double THROW_POWER_Y = 0.5D;
/*     */   private static final int SPIN_DAMAGE = 10;
/*     */   private static final int MAIN_DAMAGE = 30;
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int THROW_TIME = 40;
/*     */   private static final int PULL_TIME = 200;
/*  62 */   public static final AbilityCore<SpinningBrawlAbility> INSTANCE = (new AbilityCore.Builder("Spinning Brawl", AbilityCategory.STYLE, SpinningBrawlAbility::new))
/*  63 */     .addDescriptionLine(DESCRIPTION)
/*  64 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(60.0F), DealDamageComponent.getTooltip(30.0F)
/*  65 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  66 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  67 */       }).setUnlockCheck(SpinningBrawlAbility::canUnlock)
/*  68 */     .build();
/*     */   
/*  70 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  71 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  72 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*  73 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  74 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  75 */   private final GrabEntityComponent grabComponent = (new GrabEntityComponent((IAbility)this, true, false, true, 2.0F)).addPullStartEvent(this::onPullStart).addPullEndEvent(this::onPullEnd);
/*  76 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  77 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*  79 */   private final Interval clearHitsInterval = new Interval(20);
/*     */   
/*     */   public SpinningBrawlAbility(AbilityCore<SpinningBrawlAbility> core) {
/*  82 */     super(core);
/*     */     
/*  84 */     this.isNew = true;
/*     */     
/*  86 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  88 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */     
/*  90 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  94 */     if (this.chargeComponent.isCharging()) {
/*  95 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 100 */     this.clearHitsInterval.restartIntervalToZero();
/*     */     
/* 102 */     if (this.continuousComponent.isContinuous() && (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE || this.grabComponent.getState() == GrabEntityComponent.GrabState.PULLING)) {
/* 103 */       this.grabComponent.release(entity);
/* 104 */       this.continuousComponent.stopContinuity(entity);
/* 105 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/* 106 */       this.grabComponent.triggerPulling(entity);
/*     */     } else {
/* 108 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 113 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/* 114 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 117 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 121 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/* 122 */       return true;
/*     */     }
/*     */     
/* 125 */     if (this.grabComponent.grabManually(entity, target)) {
/* 126 */       this.grabComponent.startPulling(entity);
/*     */     }
/*     */     
/* 129 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/* 131 */     return false;
/*     */   }
/*     */   
/*     */   public void onPullStart(LivingEntity entity, IAbility ability) {
/* 135 */     this.continuousComponent.setThresholdTime(entity, 200.0F);
/*     */   }
/*     */   
/*     */   public void onPullEnd(LivingEntity entity, IAbility ability) {
/* 139 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 141 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 142 */       this.chargeComponent.startCharging(entity, 60.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 147 */     if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 152 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 157 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.THROWN) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */     
/* 163 */     if (grabbedTarget.func_233570_aj_()) {
/* 164 */       this.grabComponent.release(entity);
/* 165 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 170 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(grabbedTarget.func_213303_ch(), (IWorld)entity.field_70170_p, grabbedTarget.func_213311_cf(), grabbedTarget.func_213302_cg(), grabbedTarget.func_213311_cf(), null);
/*     */     
/* 172 */     targets.remove(grabbedTarget);
/*     */     
/* 174 */     Vector3d dir = entity.func_70040_Z().func_72432_b().func_186678_a(2.0D);
/*     */     
/* 176 */     for (LivingEntity target : targets) {
/* 177 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/* 178 */         AbilityHelper.setDeltaMovement((Entity)target, dir.field_72450_a, 0.5D, dir.field_72449_c);
/*     */       }
/*     */     } 
/*     */     
/* 182 */     if (this.clearHitsInterval.canTick()) {
/* 183 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 188 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 192 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.GRABBED) {
/* 193 */       this.grabComponent.release(entity);
/*     */     }
/*     */     
/* 196 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 197 */       this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 202 */     this.animationComponent.start(entity, ModAnimations.POINT_ARMS);
/*     */   }
/*     */   
/*     */   public void onChargeTick(LivingEntity entity, IAbility ability) {
/* 206 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 210 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 211 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 216 */     LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */     
/* 218 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/* 219 */     grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */     
/* 221 */     Vector3d direction = grabbedTarget.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b();
/*     */     
/* 223 */     float targetYaw = (float)Math.toDegrees(Math.atan2(direction.field_72449_c, direction.field_72450_a)) - 90.0F;
/* 224 */     float adjustedYaw = (targetYaw + 10.0F) % 360.0F;
/*     */     
/* 226 */     entity.field_70177_z = entity.field_70126_B = adjustedYaw;
/* 227 */     entity.field_70125_A = entity.field_70127_C = 0.0F;
/*     */     
/* 229 */     if (entity instanceof PlayerEntity) {
/* 230 */       Set<SPlayerPositionLookPacket.Flags> flags = EnumSet.of(SPlayerPositionLookPacket.Flags.X, SPlayerPositionLookPacket.Flags.Y, SPlayerPositionLookPacket.Flags.Z);
/*     */       
/* 232 */       ((ServerPlayerEntity)entity).field_71135_a.func_175089_a(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A, flags);
/*     */     } 
/*     */     
/* 235 */     float distance = 2.0F;
/*     */     
/* 237 */     Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/* 238 */     Vector3d pos = new Vector3d(lookVec.field_72450_a * distance, (entity.func_70047_e() / 2.0F) + lookVec.field_72448_b * distance, lookVec.field_72449_c * distance);
/*     */     
/* 240 */     AbilityHelper.setDeltaMovement((Entity)grabbedTarget, entity.func_213303_ch().func_178787_e(pos).func_178788_d(grabbedTarget.func_213303_ch()), true);
/*     */     
/* 242 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, grabbedTarget.func_213311_cf(), grabbedTarget.func_213302_cg(), grabbedTarget.func_213311_cf(), ModEntityPredicates.getEnemyFactions(entity));
/*     */     
/* 244 */     targets.remove(grabbedTarget);
/*     */     
/* 246 */     if (!HakiHelper.hasHardeningActive(entity)) {
/* 247 */       targets.removeIf(target -> DevilFruitCapability.get(entity).isLogia());
/*     */     }
/*     */     
/* 250 */     Vector3d dir = lookVec.func_186678_a(2.0D);
/*     */     
/* 252 */     for (LivingEntity target : targets) {
/* 253 */       if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 10.0F)) {
/* 254 */         AbilityHelper.setDeltaMovement((Entity)target, dir.field_72450_a, 0.5D, dir.field_72449_c);
/*     */       }
/*     */     } 
/*     */     
/* 258 */     if (this.clearHitsInterval.canTick()) {
/* 259 */       this.hitTrackerComponent.clearHits();
/*     */     }
/*     */   }
/*     */   
/*     */   public void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 264 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 268 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 269 */       LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */       
/* 271 */       if (this.dealDamageComponent.hurtTarget(entity, grabbedTarget, 30.0F)) {
/* 272 */         grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 80, 0));
/*     */       }
/*     */       
/* 275 */       this.grabComponent.throwTarget(entity, 2.0D, 0.5D);
/* 276 */       this.continuousComponent.startContinuity(entity, 40.0F);
/*     */     } else {
/* 278 */       this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */     } 
/*     */     
/* 281 */     this.animationComponent.stop(entity);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 285 */     if (!(entity instanceof PlayerEntity)) {
/* 286 */       return false;
/*     */     }
/*     */     
/* 289 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 291 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 292 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 294 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_02));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\SpinningBrawlAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */