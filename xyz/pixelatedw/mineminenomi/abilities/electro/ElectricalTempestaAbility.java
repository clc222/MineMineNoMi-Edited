/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.LightningDischargeEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.events.passives.MinkPassiveEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ElectricalTempestaAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.5F;
/*     */   private static final float DAMAGE_BONUS = 2.0F;
/*     */   private static final float RANGE_BONUS = 2.0F;
/*  43 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "electrical_tempesta", new Pair[] {
/*  44 */         (Pair)ImmutablePair.of("The user releases a charge of energy that deals damage to nearby enemies and knocks them back.", null), 
/*  45 */         (Pair)ImmutablePair.of("While %s is active the cooldown of this ability is reduced by %s, the damage is increased by %s and the range of the ability is increased by %s.", new Object[] {
/*  46 */             AbilityHelper.mentionAbility(SulongAbility.INSTANCE), 
/*  47 */             AbilityHelper.mentionText(Math.round(50.0F) + "%"), 
/*  48 */             AbilityHelper.mentionText(Math.round(Math.abs(-1.0F) * 100.0F) + "%"), 
/*  49 */             AbilityHelper.mentionText(Math.round(Math.abs(-1.0F) * 100.0F) + "%")
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 160;
/*     */   private static final int CHARGE_TIME = 10;
/*     */   private static final int RANGE = 10;
/*     */   private static final int DAMAGE = 20;
/*     */   private static final int ELECLAW_STACKS = 1;
/*  57 */   public static final AbilityCore<ElectricalTempestaAbility> INSTANCE = (new AbilityCore.Builder("Electrical Tempesta", AbilityCategory.RACIAL, ElectricalTempestaAbility::new))
/*  58 */     .addDescriptionLine(new ITextComponent[] { DESCRIPTION[0]
/*  59 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.IDescriptionLine.of(DESCRIPTION[1])
/*  60 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ChargeComponent.getTooltip(10.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
/*  61 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  62 */     .setSourceElement(SourceElement.LIGHTNING)
/*  63 */     .setUnlockCheck(ElectricalTempestaAbility::canUnlock)
/*  64 */     .build();
/*     */   
/*  66 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  67 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  68 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  70 */   private LightningDischargeEntity ballEntity = null;
/*     */   
/*     */   public ElectricalTempestaAbility(AbilityCore<ElectricalTempestaAbility> core) {
/*  73 */     super(core);
/*     */     
/*  75 */     this.isNew = true;
/*  76 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  78 */     addCanUseCheck(ElectroHelper.requireEleclaw(1));
/*     */     
/*  80 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  84 */     this.chargeComponent.startCharging(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  88 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0, false, false));
/*  89 */     if (this.chargeComponent.getChargeTime() % 2.0F == 0.0F) {
/*  90 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ELECTRO_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/*  93 */     if (this.ballEntity == null) {
/*  94 */       LightningDischargeEntity ball = new LightningDischargeEntity((Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*  95 */       entity.field_70170_p.func_217376_c((Entity)ball);
/*  96 */       this.ballEntity = ball;
/*     */     } else {
/*     */       
/*  99 */       float distance = 0.5F;
/* 100 */       Vector3d lookVec = entity.func_70040_Z();
/* 101 */       double px = entity.func_226277_ct_() + lookVec.field_72450_a * distance;
/* 102 */       double py = entity.func_226278_cu_() + lookVec.field_72450_a * distance;
/* 103 */       double pz = entity.func_226281_cx_() + lookVec.field_72449_c * distance;
/* 104 */       Vector3d pos = new Vector3d(px, py, pz);
/* 105 */       float percentage = 1.0F - this.chargeComponent.getChargeTime() / this.chargeComponent.getMaxChargeTime();
/* 106 */       this.ballEntity.setSize(percentage * 0.1F);
/* 107 */       this.ballEntity.setLightningLength(2.0F);
/* 108 */       this.ballEntity.func_70012_b(pos.func_82615_a(), pos.func_82617_b(), pos.func_82616_c(), entity.field_70177_z, entity.field_70125_A);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 113 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 117 */     boolean hasSulongActive = ElectroHelper.hasSulongActive(entity);
/*     */     
/* 119 */     this.dealDamageComponent.getBonusManager().removeBonus(ElectroHelper.SULONG_DAMAGE_BONUS);
/* 120 */     this.rangeComponent.getBonusManager().removeBonus(ElectroHelper.SULONG_RANGE_BONUS);
/* 121 */     this.cooldownComponent.getBonusManager().removeBonus(ElectroHelper.SULONG_COOLDOWN_BONUS);
/* 122 */     if (hasSulongActive) {
/* 123 */       this.dealDamageComponent.getBonusManager().addBonus(ElectroHelper.SULONG_DAMAGE_BONUS, "Sulong Damage Bonus", BonusOperation.MUL, 2.0F);
/* 124 */       this.rangeComponent.getBonusManager().addBonus(ElectroHelper.SULONG_RANGE_BONUS, "Sulong Range Bonus", BonusOperation.MUL, 2.0F);
/* 125 */       this.cooldownComponent.getBonusManager().addBonus(ElectroHelper.SULONG_COOLDOWN_BONUS, "Sulong Cooldown Bonus", BonusOperation.MUL, 0.5F);
/*     */     } 
/*     */     
/* 128 */     if (this.ballEntity != null) {
/* 129 */       this.ballEntity.func_70106_y();
/* 130 */       this.ballEntity = null;
/*     */     } 
/*     */     
/* 133 */     for (int i = 0; i < 3; i++) {
/* 134 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ELECTRICAL_TEMPESTA_2.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */     
/* 137 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F);
/*     */     
/* 139 */     ModDamageSource source = ((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).setUnavoidable();
/*     */     
/* 141 */     for (LivingEntity target : targets) {
/* 142 */       if (this.dealDamageComponent.hurtTarget(entity, target, 20.0F, (DamageSource)source)) {
/* 143 */         Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b();
/* 144 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 10, 0, false, false, true));
/* 145 */         AbilityHelper.setDeltaMovement((Entity)target, -dirVec.field_72450_a * 4.0D, 1.0D, -dirVec.field_72449_c * 4.0D);
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     float range = this.rangeComponent.getRange();
/*     */     
/* 151 */     int amount = 32;
/* 152 */     for (int j = 0; j < amount; j++) {
/* 153 */       float boltSize = (float)WyHelper.randomWithRange(3, (int)range);
/* 154 */       LightningEntity bolt = new LightningEntity((Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 5), boltSize, 8.0F, getCore());
/* 155 */       bolt.setColor(MinkPassiveEvents.MINK_LIGHTNING_COLOR);
/* 156 */       bolt.setAngle(60);
/* 157 */       bolt.setMaxLife(20);
/* 158 */       bolt.setDamage(0.0F);
/* 159 */       bolt.setExplosion(0, false);
/* 160 */       bolt.setSize(boltSize / 600.0F);
/* 161 */       bolt.setBranches((int)WyHelper.randomWithRange(1, 3));
/* 162 */       bolt.setSegments((int)(boltSize * 0.6D));
/* 163 */       bolt.setLightningMimic(false);
/* 164 */       entity.field_70170_p.func_217376_c((Entity)bolt);
/*     */     } 
/*     */     
/* 167 */     EleclawAbility eleclaw = (EleclawAbility)AbilityDataCapability.get(entity).getEquippedAbility(EleclawAbility.INSTANCE);
/* 168 */     if (eleclaw != null) {
/* 169 */       eleclaw.reduceUsage(entity, 1);
/*     */     }
/*     */     
/* 172 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 176 */     IEntityStats props = EntityStatsCapability.get(user);
/* 177 */     return (props.isMink() && props.getDoriki() >= 3000.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalTempestaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */