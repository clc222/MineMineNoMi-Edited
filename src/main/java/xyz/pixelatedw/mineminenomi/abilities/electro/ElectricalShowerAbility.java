/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.electro.ElectricalShowerProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ElectricalShowerAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.3F;
/*     */   private static final float DAMAGE_BONUS = 1.25F;
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "electrical_shower", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Launches the user into the air and showers down lightning bolts underneath.", null), 
/*  46 */         (Pair)ImmutablePair.of("While %s is active the cooldown of this ability is reduced by %s and the damage is increased by %s.", new Object[] {
/*  47 */             AbilityHelper.mentionAbility(SulongAbility.INSTANCE), 
/*  48 */             AbilityHelper.mentionText(Math.round(70.0F) + "%"), 
/*  49 */             AbilityHelper.mentionText(Math.round(Math.abs(-0.25F) * 100.0F) + "%")
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int DAMAGE = 20;
/*     */   private static final int ELECLAW_STACKS = 2;
/*  56 */   public static final AbilityCore<ElectricalShowerAbility> INSTANCE = (new AbilityCore.Builder("Electrical Shower", AbilityCategory.RACIAL, ElectricalShowerAbility::new))
/*  57 */     .addDescriptionLine(new ITextComponent[] { DESCRIPTION[0]
/*  58 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.IDescriptionLine.of(DESCRIPTION[1])
/*  59 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(60.0F)
/*  60 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  61 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  62 */     .setSourceElement(SourceElement.LIGHTNING)
/*  63 */     .setUnlockCheck(ElectricalShowerAbility::canUnlock)
/*  64 */     .build();
/*     */   
/*  66 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*  67 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  68 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  69 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  70 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  71 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::stopRepeaterEvent);
/*     */   
/*  73 */   private LightningDischargeEntity ballEntity = null;
/*     */   
/*     */   boolean hasFallDamage = true;
/*     */   
/*     */   public ElectricalShowerAbility(AbilityCore<ElectricalShowerAbility> core) {
/*  78 */     super(core);
/*     */     
/*  80 */     this.isNew = true;
/*  81 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.stackComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent });
/*     */     
/*  83 */     addCanUseCheck(ElectroHelper.requireEleclaw(2));
/*  84 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  86 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  90 */     if (isContinuous()) {
/*  91 */       this.continuousComponent.stopContinuity(entity);
/*     */       return;
/*     */     } 
/*  94 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  98 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0, false, false));
/*  99 */     if (this.chargeComponent.getChargeTime() % 5.0F == 0.0F) {
/* 100 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ELECTRO_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 103 */     float percentage = 1.0F - this.chargeComponent.getChargeTime() / this.chargeComponent.getMaxChargeTime();
/*     */     
/* 105 */     if (this.ballEntity == null) {
/* 106 */       LightningDischargeEntity ball = new LightningDischargeEntity((Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*     */       
/* 108 */       this.ballEntity = ball;
/*     */     } else {
/*     */       
/* 111 */       float distance = percentage * 2.0F;
/* 112 */       Vector3d lookVec = entity.func_70040_Z();
/* 113 */       double px = entity.func_226277_ct_() + lookVec.field_72450_a * distance;
/* 114 */       double py = entity.func_226280_cw_() * 0.85D + lookVec.field_72448_b * distance;
/* 115 */       double pz = entity.func_226281_cx_() + lookVec.field_72449_c * distance;
/* 116 */       Vector3d pos = new Vector3d(px, py, pz);
/* 117 */       this.ballEntity.setSize(percentage * 0.3F);
/* 118 */       this.ballEntity.setLightningLength(3.0F);
/* 119 */       this.ballEntity.func_70012_b(pos.func_82615_a(), pos.func_82617_b(), pos.func_82616_c(), entity.field_70177_z, entity.field_70125_A);
/*     */     } 
/*     */     
/* 122 */     if (percentage > 0.65D) {
/* 123 */       Vector3d startVec = entity.func_213303_ch();
/*     */       
/* 125 */       boolean blockUnder = entity.field_70170_p.func_217299_a(new RayTraceContext(startVec, startVec.func_72441_c(0.0D, -15.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)entity)).func_216346_c().equals(RayTraceResult.Type.BLOCK);
/*     */       
/* 127 */       if (blockUnder) {
/* 128 */         AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, 1.0D, (entity.func_213322_ci()).field_72449_c);
/*     */       }
/*     */     } 
/*     */     
/* 132 */     AbilityHelper.slowEntityFall(entity);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 136 */     if (this.ballEntity != null) {
/* 137 */       this.ballEntity.func_70106_y();
/* 138 */       this.ballEntity = null;
/*     */     } 
/*     */     
/* 141 */     EleclawAbility eleclaw = (EleclawAbility)AbilityDataCapability.get(entity).getEquippedAbility(EleclawAbility.INSTANCE);
/* 142 */     if (eleclaw != null) {
/* 143 */       eleclaw.reduceUsage(entity, 2);
/*     */     }
/*     */     
/* 146 */     this.continuousComponent.startContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 150 */     this.hasFallDamage = false;
/* 151 */     this.repeaterComponent.start(entity, 10, 5);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 155 */     AbilityHelper.slowEntityFall(entity);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 159 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 163 */     this.projectileComponent.shoot(entity, 3.0F, 2.0F);
/* 164 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*     */   }
/*     */   
/*     */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 168 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 172 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 173 */       this.hasFallDamage = true;
/* 174 */       return 0.0F;
/*     */     } 
/*     */     
/* 177 */     return damage;
/*     */   }
/*     */   
/*     */   private ElectricalShowerProjectile createProjectile(LivingEntity entity) {
/* 181 */     ElectricalShowerProjectile proj = new ElectricalShowerProjectile(entity.field_70170_p, entity);
/* 182 */     proj.setDamage(20.0F);
/* 183 */     proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 3.0F, 0.0F);
/* 184 */     return proj;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 188 */     IEntityStats props = EntityStatsCapability.get(user);
/* 189 */     return (props.isMink() && props.getDoriki() >= 7000.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalShowerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */