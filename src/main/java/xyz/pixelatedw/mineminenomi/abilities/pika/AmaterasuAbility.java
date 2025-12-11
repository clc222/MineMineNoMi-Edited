/*     */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.goro.ElThorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.AmaterasuProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AmaterasuAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "amaterasu", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Charges up a powerful concentrated light beam. The longer its charged the more powerful it becomes.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int CHARGE_TIME = 80;
/*  38 */   public static final AbilityCore<AmaterasuAbility> INSTANCE = (new AbilityCore.Builder("Amaterasu", AbilityCategory.DEVIL_FRUITS, AmaterasuAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(80.0F)
/*  41 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  42 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  43 */     .setSourceElement(SourceElement.LIGHT)
/*  44 */     .build();
/*     */   
/*  46 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::stopChargeEvent);
/*  47 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  48 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   public AmaterasuAbility(AbilityCore<AmaterasuAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  56 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  60 */     this.chargeComponent.startCharging(entity, 80.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  64 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PIKA_CHARGE_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*  65 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  69 */     AbilityHelper.slowEntityFall(entity);
/*  70 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.PIKA_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void stopChargeEvent(LivingEntity entity, IAbility ability) {
/*  76 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 64.0D);
/*     */     
/*  78 */     double beamDistance = Math.sqrt(entity.func_70092_e((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c));
/*     */     
/*  80 */     float damage = 70.0F;
/*  81 */     float size = 0.25F;
/*  82 */     float length = 50.0F;
/*     */     
/*  84 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, 1.15D, 0.8D);
/*     */     
/*  86 */     LightningEntity bolt = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A, length + (float)beamDistance, 20.0F, getCore());
/*     */     
/*  88 */     bolt.setBlocksAffectedLimit(1508);
/*  89 */     bolt.setMaxLife(20);
/*  90 */     bolt.setDamage(damage);
/*     */     
/*  92 */     bolt.setExplosion(14, true, 0.3F);
/*     */     
/*  94 */     bolt.setSize(size);
/*  95 */     bolt.setBoxSizeDivision(1.0D);
/*  96 */     bolt.setColor(ElThorAbility.YELLOW_THUNDER);
/*  97 */     bolt.setAngle(100);
/*  98 */     bolt.setTargetTimeToReset(6000);
/*  99 */     bolt.disableExplosionKnockback();
/* 100 */     bolt.setBranches(1);
/* 101 */     bolt.setSegments(1);
/*     */     
/* 103 */     entity.field_70170_p.func_217376_c((Entity)bolt);
/*     */     
/* 105 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PIKA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 106 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/* 107 */     this.animationComponent.stop(entity);
/*     */   }
/*     */   
/*     */   private AmaterasuProjectile createProjectile(LivingEntity entity) {
/* 111 */     AmaterasuProjectile proj = new AmaterasuProjectile(entity.field_70170_p, entity);
/* 112 */     float multiplier = this.chargeComponent.getChargePercentage();
/* 113 */     proj.setDamage(proj.getDamage() * multiplier);
/* 114 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\AmaterasuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */