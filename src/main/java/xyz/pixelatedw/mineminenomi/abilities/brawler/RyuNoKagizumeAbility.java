/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class RyuNoKagizumeAbility extends Ability {
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ryu_no_kagizume", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("The user attacks the enemy with a three-fingered claw hand strike, targeting their weak points to deal heavy damage and damaging their armour.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int DAMAGE = 50;
/*  49 */   public static final AbilityCore<RyuNoKagizumeAbility> INSTANCE = (new AbilityCore.Builder("Ryu no Kagizume", AbilityCategory.STYLE, RyuNoKagizumeAbility::new))
/*  50 */     .addDescriptionLine(DESCRIPTION)
/*  51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ChargeComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(50.0F)
/*  52 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  53 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  54 */       }).setUnlockCheck(RyuNoKagizumeAbility::canUnlock)
/*  55 */     .build();
/*     */   
/*  57 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  58 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  59 */     .addStartEvent(this::onChargeStart)
/*  60 */     .addTickEvent(this::onChargeTick)
/*  61 */     .addEndEvent(this::onChargeEnd);
/*  62 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  63 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addEndEvent(this::onContinuityEnd);
/*  64 */   private final GrabEntityComponent grabComponent = new GrabEntityComponent((IAbility)this, true, false, 1.0F);
/*  65 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*  66 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public RyuNoKagizumeAbility(AbilityCore<RyuNoKagizumeAbility> core) {
/*  69 */     super(core);
/*     */     
/*  71 */     this.isNew = true;
/*     */     
/*  73 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.grabComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  75 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*     */     
/*  77 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  81 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     if (this.continuousComponent.isContinuous()) {
/*  86 */       this.grabComponent.release(entity);
/*  87 */       this.continuousComponent.stopContinuity(entity);
/*  88 */     } else if (this.grabComponent.getState() == GrabEntityComponent.GrabState.IDLE && this.grabComponent.grabNearest(entity, false)) {
/*  89 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     } else {
/*  91 */       this.continuousComponent.startContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  96 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/*  97 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 100 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 104 */     if (!this.continuousComponent.isContinuous() || this.grabComponent.hasGrabbedEntity()) {
/* 105 */       return true;
/*     */     }
/*     */     
/* 108 */     if (this.grabComponent.grabManually(entity, target)) {
/* 109 */       this.continuousComponent.stopContinuity(entity);
/* 110 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     } 
/*     */     
/* 113 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.ANTI_KNOCKBACK.get(), 1));
/*     */     
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 119 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 123 */     if (this.grabComponent.getState() != GrabEntityComponent.GrabState.GRABBED) {
/* 124 */       this.grabComponent.release(entity);
/*     */     }
/*     */     
/* 127 */     if (!this.grabComponent.canContinueGrab(entity)) {
/* 128 */       this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 133 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 137 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/*     */     
/* 139 */     LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */     
/* 141 */     if (grabbedTarget != null) {
/* 142 */       AbilityHelper.setDeltaMovement((Entity)grabbedTarget, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 147 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     if (canUse(entity).isFail() || !this.grabComponent.canContinueGrab(entity)) {
/* 152 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 157 */     LivingEntity target = this.grabComponent.getGrabbedEntity();
/*     */     
/* 159 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/* 160 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.GRABBED.get(), 2, 3));
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 164 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 168 */     if (this.grabComponent.canContinueGrab(entity)) {
/* 169 */       LivingEntity grabbedTarget = this.grabComponent.getGrabbedEntity();
/*     */       
/* 171 */       grabbedTarget.func_195064_c(new EffectInstance((Effect)ModEffects.FRAGILE.get(), 40, 0));
/*     */       
/* 173 */       ModDamageSource source = ModDamageSource.causeAbilityDamage(entity, getCore()).setPiercing(0.25F);
/*     */       
/* 175 */       this.dealDamageComponent.hurtTarget(entity, grabbedTarget, 50.0F, (DamageSource)source);
/*     */       
/* 177 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, grabbedTarget.func_226277_ct_(), grabbedTarget.func_226278_cu_(), grabbedTarget.func_226281_cx_(), 1.0F);
/*     */       
/* 179 */       explosion.setDestroyBlocks(false);
/* 180 */       explosion.disableExplosionKnockback();
/* 181 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/* 182 */       explosion.doExplosion();
/*     */       
/* 184 */       ItemStack stack = grabbedTarget.func_184582_a(EquipmentSlotType.HEAD);
/*     */       
/* 186 */       stack.func_222118_a(15, grabbedTarget, user -> user.func_213361_c(EquipmentSlotType.HEAD));
/*     */       
/* 188 */       this.grabComponent.release(entity);
/*     */     } 
/*     */     
/* 191 */     this.animationComponent.stop(entity);
/*     */     
/* 193 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 197 */     if (!(entity instanceof PlayerEntity)) {
/* 198 */       return false;
/*     */     }
/*     */     
/* 201 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 203 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 204 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 206 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_05));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\RyuNoKagizumeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */