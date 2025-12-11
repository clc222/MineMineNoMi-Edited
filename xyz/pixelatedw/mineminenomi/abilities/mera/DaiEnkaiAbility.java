/*     */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.DaiEnkaiEnteiProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.DaiEnkaiOnibiProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.HibashiraParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class DaiEnkaiAbility extends Ability {
/*  56 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "dai_enkai_entei", new Pair[] {
/*  57 */         (Pair)ImmutablePair.of("Amasses the user's flames into a gigantic fireball that the user hurls at the opponent", null), 
/*  58 */         (Pair)ImmutablePair.of("Creates a giant fire vortex around the user, trapping enemies within it and creating three fire dragons that will be shot towards the enemy at the end.", null)
/*     */       });
/*  60 */   private static final TranslationTextComponent DAI_ENKAI_ENTEI_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.dai_enkai_entei", "Dai Enkai: Entei"));
/*  61 */   private static final TranslationTextComponent DAI_ENKAI_ONIBI_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.dai_enkai_onibi", "Dai Enkai: Onibi"));
/*     */   
/*  63 */   private static final ResourceLocation DAI_ENKAI_ENTEI_ICON = new ResourceLocation("mineminenomi", "textures/abilities/dai_enkai_entei.png");
/*  64 */   private static final ResourceLocation DAI_ENKAI_ONIBI_ICON = new ResourceLocation("mineminenomi", "textures/abilities/dai_enkai_onibi.png");
/*     */   
/*     */   private static final float COOLDOWN = 500.0F;
/*     */   
/*     */   private static final float ENTEI_CHARGE_TIME = 160.0F;
/*     */   private static final float ENTEI_ON_HOLD_TIME = 160.0F;
/*     */   private static final float ONIBI_CHARGE_TIME = 100.0F;
/*     */   private static final double PILLAR_SIZE = 25.0D;
/*     */   private static final float ENTEI_MIN_DAMAGE = 24.0F;
/*     */   private static final float ENTEI_MAX_DAMAGE = 104.0F;
/*     */   private static final float ONIBI_PROJ_DAMAGE = 75.0F;
/*     */   private static final float ONIBI_PILLAR_DAMAGE = 5.0F;
/*  76 */   private Vector3d onibiStartPos = Vector3d.field_186680_a; private static final AbilityDescriptionLine.IDescriptionLine<DaiEnkaiAbility> ONIBI_DAMAGE_TOOLTIP; private static final AbilityDescriptionLine.IDescriptionLine<DaiEnkaiAbility> ENTEI_DAMAGE_TOOLTIP;
/*     */   static {
/*  78 */     ONIBI_DAMAGE_TOOLTIP = ((entity, ability) -> {
/*     */         AbilityProjectileEntity proj = ability.getComponent(ModAbilityKeys.PROJECTILE).map(()).orElse(null);
/*     */         
/*     */         if (proj != null && proj.getDamage() > 0.0F) {
/*     */           float bonus = ((Float)ability.getComponent(ModAbilityKeys.PROJECTILE).map(ProjectileComponent::getDamageBonusManager).map(()).orElse(Float.valueOf(0.0F))).floatValue();
/*     */           AbilityStat.AbilityStatType bonusType = (bonus > 0.0F) ? AbilityStat.AbilityStatType.BUFF : ((bonus < 0.0F) ? AbilityStat.AbilityStatType.DEBUFF : AbilityStat.AbilityStatType.NEUTRAL);
/*     */           AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_DAMAGE, 75.0F)).withBonus(bonus, bonusType).withUnit((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_X3);
/*     */           return statBuilder.build().getStatDescription(2);
/*     */         } 
/*     */         return null;
/*     */       });
/*  89 */     ENTEI_DAMAGE_TOOLTIP = ((entity, ability) -> {
/*     */         AbilityProjectileEntity proj = ability.getComponent(ModAbilityKeys.PROJECTILE).map(()).orElse(null);
/*     */         if (proj != null && proj.getDamage() > 0.0F) {
/*     */           float bonus = ((Float)ability.getComponent(ModAbilityKeys.PROJECTILE).map(ProjectileComponent::getDamageBonusManager).map(()).orElse(Float.valueOf(0.0F))).floatValue();
/*     */           AbilityStat.AbilityStatType bonusType = (bonus > 0.0F) ? AbilityStat.AbilityStatType.BUFF : ((bonus < 0.0F) ? AbilityStat.AbilityStatType.DEBUFF : AbilityStat.AbilityStatType.NEUTRAL);
/*     */           AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_DAMAGE, 24.0F, 104.0F)).withBonus(bonus, bonusType);
/*     */           return statBuilder.build().getStatDescription(2);
/*     */         } 
/*     */         return null;
/*     */       });
/*     */   }
/* 100 */   public static final AbilityCore<DaiEnkaiAbility> INSTANCE = (new AbilityCore.Builder("Dai Enkai: Entei", AbilityCategory.DEVIL_FRUITS, DaiEnkaiAbility::new))
/* 101 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/* 104 */         (e, a) -> DAI_ENKAI_ENTEI_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[0], CooldownComponent.getTooltip(500.0F), 
/* 105 */         ChargeComponent.getTooltip(160.0F), 
/* 106 */         ContinuousComponent.getTooltip(160.0F), (e, a) -> new StringTextComponent("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_PROJECTILE.getString() + "§r"), ENTEI_DAMAGE_TOOLTIP
/*     */ 
/*     */       
/* 109 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/* 112 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> DAI_ENKAI_ONIBI_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[1], CooldownComponent.getTooltip(500.0F), 
/* 113 */         ChargeComponent.getTooltip(100.0F), (e, a) -> new StringTextComponent("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_PROJECTILE.getString() + "§r"), ONIBI_DAMAGE_TOOLTIP
/*     */ 
/*     */       
/* 116 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 117 */     .setSourceElement(SourceElement.FIRE)
/* 118 */     .build();
/*     */   
/* 120 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/* 121 */     .addStartEvent(this::onChargeStart)
/* 122 */     .addTickEvent(this::onChargeTick)
/* 123 */     .addEndEvent(this::onChargeEnd);
/*     */   
/* 125 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/* 126 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(this::onSwing);
/* 127 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.ENTEI)).addChangeModeEvent(this::onAltModeChange);
/* 128 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 129 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/* 130 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 131 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/* 133 */   private final Interval particleInterval = new Interval(2);
/*     */   
/* 135 */   private final Interval clearHitsInterval = new Interval(20);
/*     */   
/*     */   private DaiEnkaiEnteiProjectile enteiProjectile;
/*     */   
/* 139 */   private DaiEnkaiOnibiProjectile[] onibiProjectiles = new DaiEnkaiOnibiProjectile[3];
/*     */   
/*     */   public DaiEnkaiAbility(AbilityCore<DaiEnkaiAbility> core) {
/* 142 */     super(core);
/*     */     
/* 144 */     this.isNew = true;
/*     */     
/* 146 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.swingTriggerComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/* 148 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 152 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 156 */     if (this.altModeComponent.getCurrentMode() == Mode.ENTEI) {
/* 157 */       if (!this.chargeComponent.isCharging() && !this.continuousComponent.isContinuous()) {
/* 158 */         this.chargeComponent.startCharging(entity, 160.0F);
/* 159 */       } else if (this.enteiProjectile != null && this.enteiProjectile.func_70089_S()) {
/* 160 */         this.enteiProjectile.onBlockImpactEvent(this.enteiProjectile.func_233580_cy_());
/* 161 */         this.enteiProjectile.func_70106_y();
/*     */       } 
/* 163 */     } else if (this.altModeComponent.getCurrentMode() == Mode.ONIBI) {
/* 164 */       if (!entity.func_233570_aj_()) {
/* 165 */         entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getDisplayName() }), Util.field_240973_b_);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 170 */       this.chargeComponent.startCharging(entity, 100.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 175 */     this.particleInterval.restartIntervalToZero();
/*     */     
/* 177 */     if (this.altModeComponent.getCurrentMode() == Mode.ENTEI) {
/* 178 */       this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM);
/*     */       
/* 180 */       this.enteiProjectile = (DaiEnkaiEnteiProjectile)this.projectileComponent.getNewProjectile(entity);
/*     */       
/* 182 */       this.enteiProjectile.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + entity.func_70047_e() + 7.5D, entity.func_226281_cx_());
/*     */       
/* 184 */       entity.field_70170_p.func_217376_c((Entity)this.enteiProjectile);
/* 185 */     } else if (this.altModeComponent.getCurrentMode() == Mode.ONIBI) {
/* 186 */       this.onibiStartPos = entity.func_213303_ch();
/*     */       
/* 188 */       this.animationComponent.start(entity, ModAnimations.RAISE_ARMS);
/*     */ 
/*     */       
/* 191 */       float f1 = -entity.field_70177_z * 0.017453292F;
/* 192 */       float f2 = MathHelper.func_76134_b(f1);
/* 193 */       float f3 = MathHelper.func_76126_a(f1);
/*     */       
/* 195 */       Vector3d throwerLook = new Vector3d(f3, 0.0D, f2);
/*     */       
/* 197 */       double offsetX = throwerLook.field_72450_a * 30.0D;
/* 198 */       double offsetY = 20.0D;
/* 199 */       double offsetZ = throwerLook.field_72449_c * 30.0D;
/*     */       
/* 201 */       this
/*     */ 
/*     */         
/* 204 */         .onibiProjectiles = new DaiEnkaiOnibiProjectile[] { (DaiEnkaiOnibiProjectile)this.projectileComponent.getNewProjectile(entity), (DaiEnkaiOnibiProjectile)this.projectileComponent.getNewProjectile(entity), (DaiEnkaiOnibiProjectile)this.projectileComponent.getNewProjectile(entity) };
/*     */ 
/*     */       
/* 207 */       this.onibiProjectiles[0].func_70107_b(entity.func_226277_ct_() + offsetZ, entity.func_226278_cu_() + offsetY, entity.func_226281_cx_() - offsetX);
/* 208 */       this.onibiProjectiles[1].func_70107_b(entity.func_226277_ct_() - offsetX, entity.func_226278_cu_() + offsetY, entity.func_226281_cx_() - offsetZ);
/* 209 */       this.onibiProjectiles[2].func_70107_b(entity.func_226277_ct_() - offsetZ, entity.func_226278_cu_() + offsetY, entity.func_226281_cx_() + offsetX);
/*     */       
/* 211 */       for (DaiEnkaiOnibiProjectile onibiProjectile : this.onibiProjectiles) {
/* 212 */         entity.field_70170_p.func_217376_c((Entity)onibiProjectile);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 218 */     if (this.altModeComponent.getCurrentMode() == Mode.ENTEI) {
/* 219 */       if (this.enteiProjectile == null || !this.enteiProjectile.func_70089_S()) {
/* 220 */         this.chargeComponent.stopCharging(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 225 */       this.enteiProjectile.setLife(this.enteiProjectile.getMaxLife());
/* 226 */       this.enteiProjectile.increaseSize();
/* 227 */       this.enteiProjectile.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + entity.func_70047_e() + 7.5D, entity.func_226281_cx_());
/*     */       
/* 229 */       if (!entity.field_70170_p.field_72995_K && this.particleInterval.canTick()) {
/* 230 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DAI_ENKAI_2.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       }
/* 232 */     } else if (this.altModeComponent.getCurrentMode() == Mode.ONIBI) {
/* 233 */       double yourDistance = 37.5D;
/*     */       
/* 235 */       Vector3d lookAngle = entity.func_70040_Z();
/* 236 */       Vector3d targetPoint = entity.func_174824_e(1.0F).func_72441_c(lookAngle.field_72450_a * yourDistance, lookAngle.field_72448_b * yourDistance, lookAngle.field_72449_c * yourDistance);
/*     */       
/* 238 */       for (DaiEnkaiOnibiProjectile onibiProjectile : this.onibiProjectiles) {
/* 239 */         if (onibiProjectile != null && onibiProjectile.func_70089_S()) {
/* 240 */           onibiProjectile.setLife(onibiProjectile.getMaxLife());
/*     */           
/* 242 */           Vector3d projectilePos = onibiProjectile.func_213303_ch();
/*     */           
/* 244 */           double totalDeltaX = targetPoint.field_72450_a - projectilePos.field_72450_a;
/* 245 */           double totalDeltaY = targetPoint.field_72448_b - projectilePos.field_72448_b;
/* 246 */           double totalDeltaZ = targetPoint.field_72449_c - projectilePos.field_72449_c;
/*     */           
/* 248 */           double distanceXZ = Math.sqrt(totalDeltaX * totalDeltaX + totalDeltaZ * totalDeltaZ);
/* 249 */           double pitch = -Math.atan2(totalDeltaY, distanceXZ);
/* 250 */           double yaw = Math.atan2(-totalDeltaX, totalDeltaZ);
/*     */           
/* 252 */           pitch = Math.toDegrees(pitch);
/* 253 */           yaw = Math.toDegrees(yaw);
/*     */           
/* 255 */           onibiProjectile.field_70177_z = (float)-yaw;
/* 256 */           onibiProjectile.field_70125_A = (float)-pitch;
/*     */         } 
/*     */       } 
/*     */       
/* 260 */       if (!entity.field_70170_p.field_72995_K && this.particleInterval.canTick()) {
/* 261 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HIBASHIRA.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)new HibashiraParticleEffect.Details(10.0F, 4.0F, 4.0F));
/*     */       }
/*     */       
/* 264 */       double perimeter = 41.5D;
/*     */       
/* 266 */       List<LivingEntity> targets = WyHelper.getEntitiesAroundCircle(entity.func_213303_ch(), (IWorld)entity.field_70170_p, perimeter, 4.0D, ModEntityPredicates.getEnemyFactions(entity), new Class[] { LivingEntity.class });
/*     */       
/* 268 */       for (LivingEntity target : targets) {
/* 269 */         if (this.hitTrackerComponent.canHit((Entity)target) && this.dealDamageComponent.hurtTarget(entity, target, 5.0F)) {
/* 270 */           target.func_70015_d(4);
/*     */         }
/*     */         
/* 273 */         Vector3d rejectionVec = this.onibiStartPos.func_178788_d(target.func_213303_ch()).func_72432_b();
/*     */         
/* 275 */         AbilityHelper.setDeltaMovement((Entity)target, rejectionVec);
/*     */       } 
/*     */       
/* 278 */       if (this.clearHitsInterval.canTick()) {
/* 279 */         this.hitTrackerComponent.clearHits();
/*     */       }
/*     */       
/* 282 */       AbilityHelper.slowEntityFall(entity);
/*     */       
/* 284 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 289 */     if (this.altModeComponent.getCurrentMode() == Mode.ENTEI) {
/* 290 */       this.continuousComponent.startContinuity(entity, 160.0F);
/* 291 */     } else if (this.altModeComponent.getCurrentMode() == Mode.ONIBI) {
/* 292 */       for (DaiEnkaiOnibiProjectile onibiProjectile : this.onibiProjectiles) {
/* 293 */         if (onibiProjectile != null && onibiProjectile.func_70089_S()) {
/* 294 */           onibiProjectile.func_234612_a_((Entity)entity, -onibiProjectile.field_70125_A, -onibiProjectile.field_70177_z, 0.0F, 3.0F, 1.0F);
/*     */         }
/*     */       } 
/*     */       
/* 298 */       this.animationComponent.stop(entity);
/*     */       
/* 300 */       this.cooldownComponent.startCooldown(entity, 500.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onSwing(LivingEntity entity, IAbility ability) {
/* 305 */     if (this.continuousComponent.isContinuous()) {
/* 306 */       this.enteiProjectile.func_234612_a_((Entity)entity, entity.field_70125_A + 10.0F, entity.field_70177_z, 0.0F, 3.0F, 1.0F);
/*     */       
/* 308 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MERA_SFX.get(), SoundCategory.PLAYERS, 5.0F, 1.0F);
/*     */       
/* 310 */       if (!entity.field_70170_p.field_72995_K) {
/* 311 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DAI_ENKAI_1.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       }
/*     */       
/* 314 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 319 */     if (this.enteiProjectile == null || !this.enteiProjectile.func_70089_S()) {
/* 320 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 325 */     this.enteiProjectile.setLife(this.enteiProjectile.getMaxLife());
/* 326 */     this.enteiProjectile.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + entity.func_70047_e() + 7.5D, entity.func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 330 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 334 */     if (this.enteiProjectile != null && this.enteiProjectile.func_70089_S() && this.enteiProjectile.getLife() < this.enteiProjectile.getMaxLife()) {
/* 335 */       this.enteiProjectile.onBlockImpactEvent(this.enteiProjectile.func_233580_cy_());
/* 336 */       this.enteiProjectile.func_70106_y();
/*     */     } 
/*     */     
/* 339 */     this.enteiProjectile = null;
/*     */     
/* 341 */     this.animationComponent.stop(entity);
/*     */     
/* 343 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 347 */     if (mode == Mode.ENTEI) {
/* 348 */       setDisplayName((ITextComponent)DAI_ENKAI_ENTEI_NAME);
/* 349 */       setDisplayIcon(DAI_ENKAI_ENTEI_ICON);
/* 350 */     } else if (mode == Mode.ONIBI) {
/* 351 */       setDisplayName((ITextComponent)DAI_ENKAI_ONIBI_NAME);
/* 352 */       setDisplayIcon(DAI_ENKAI_ONIBI_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/* 357 */     if (this.altModeComponent.getCurrentMode() == Mode.ONIBI) {
/* 358 */       DaiEnkaiOnibiProjectile daiEnkaiOnibiProjectile = new DaiEnkaiOnibiProjectile(entity.field_70170_p, entity, this);
/* 359 */       return (AbilityProjectileEntity)daiEnkaiOnibiProjectile;
/*     */     } 
/*     */     
/* 362 */     DaiEnkaiEnteiProjectile proj = new DaiEnkaiEnteiProjectile(entity.field_70170_p, entity, this);
/* 363 */     return (AbilityProjectileEntity)proj;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 368 */     ENTEI, ONIBI;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\DaiEnkaiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */