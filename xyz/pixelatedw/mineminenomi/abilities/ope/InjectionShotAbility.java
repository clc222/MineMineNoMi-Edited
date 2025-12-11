/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SPinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class InjectionShotAbility extends Ability {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "injection_shot", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("While holding a weapon, the user charges at the enemy, leaving them confused.", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 200.0F;
/*     */   private static final float DAMAGE = 40.0F;
/*     */   private static final float RANGE = 1.6F;
/*  44 */   public static final AbilityCore<InjectionShotAbility> INSTANCE = (new AbilityCore.Builder("Injection Shot", AbilityCategory.DEVIL_FRUITS, InjectionShotAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(40.0F)
/*  47 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  48 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  49 */       }).build();
/*     */   
/*  51 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  52 */     .addStartEvent(this::onChargeStart)
/*  53 */     .addTickEvent(this::onChargeTick)
/*  54 */     .addEndEvent(this::onChargeEnd);
/*  55 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/*  56 */     .addStartEvent(this::onContinuityStart)
/*  57 */     .addTickEvent(this::onContinuityTick)
/*  58 */     .addEndEvent(this::onContinuityEnd);
/*  59 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  60 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  61 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  62 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   public InjectionShotAbility(AbilityCore<InjectionShotAbility> core) {
/*  65 */     super(core);
/*     */     
/*  67 */     this.isNew = true;
/*     */     
/*  69 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  71 */     addCanUseCheck(OpeHelper::hasRoomActive);
/*  72 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*     */     
/*  74 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  78 */     if (!AbilityHelper.canUseMomentumAbilities(entity)) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     if (!this.chargeComponent.isCharging() && !this.continuousComponent.isContinuous()) {
/*  83 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/*  88 */     this.animationComponent.start(entity, ModAnimations.POINT_WEAPON);
/*  89 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 100, 0));
/*     */     
/*  91 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/*  92 */       WyNetwork.sendTo(SPinCameraPacket.pinFixed(), (PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/*  97 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     if (canUse(entity).isFail()) {
/* 102 */       this.chargeComponent.stopCharging(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 107 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 111 */     entity.func_195063_d((Effect)ModEffects.MOVEMENT_BLOCKED.get());
/*     */     
/* 113 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 114 */       WyNetwork.sendTo(new SUnpinCameraPacket(), (PlayerEntity)entity);
/*     */     }
/*     */     
/* 117 */     if (canUse(entity).isSuccess()) {
/* 118 */       this.continuousComponent.startContinuity(entity, 20.0F);
/*     */     } else {
/* 120 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 125 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 129 */     Vector3d dirVec = entity.func_70040_Z().func_216372_d(4.0D, 1.0D, 4.0D);
/*     */     
/* 131 */     AbilityHelper.setDeltaMovement((Entity)entity, dirVec.field_72450_a, 0.35D, dirVec.field_72449_c);
/*     */     
/* 133 */     this.hitTrackerComponent.clearHits();
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 137 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 141 */     if (canUse(entity).isFail()) {
/* 142 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 147 */     if (entity.func_70089_S()) {
/* 148 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 1.6F);
/*     */       
/* 150 */       ModDamageSource source = ((AbilityDamageSource)this.dealDamageComponent.getDamageSource(entity)).setSlash().setPiercing(0.5F);
/*     */       
/* 152 */       targets.remove(entity);
/*     */       
/* 154 */       for (LivingEntity target : targets) {
/* 155 */         if (this.hitTrackerComponent.canHit((Entity)entity) && 
/* 156 */           this.dealDamageComponent.hurtTarget(entity, target, 40.0F, (DamageSource)source)) {
/* 157 */           target.func_195064_c(new EffectInstance(Effects.field_76431_k, 60, 0));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 165 */     this.animationComponent.stop(entity);
/* 166 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\InjectionShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */