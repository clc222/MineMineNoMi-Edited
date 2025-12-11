/*     */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.RadicalBeamProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RadicalBeamAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "radical_beam", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("The user launches a powerful beam of energy at the opponent.", null)
/*     */       });
/*     */   
/*     */   private static final float CHARGE_TIME = 60.0F;
/*     */   private static final float COOLDOWN = 260.0F;
/*     */   private static final int COLA_REQUIRED = 30;
/*  43 */   public static final AbilityCore<RadicalBeamAbility> INSTANCE = (new AbilityCore.Builder("Radical Beam", AbilityCategory.RACIAL, RadicalBeamAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(260.0F), ChargeComponent.getTooltip(60.0F), CyborgHelper.getColaTooltip(30.0F)
/*  46 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  47 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  48 */     .setSourceElement(SourceElement.LIGHT)
/*  49 */     .setUnlockCheck(RadicalBeamAbility::canUnlock)
/*  50 */     .build();
/*     */   
/*  52 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::startChargingEvent).addTickEvent(100, this::tickChargingEvent).addEndEvent(100, this::endChargingEvent);
/*  53 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   public RadicalBeamAbility(AbilityCore<RadicalBeamAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  62 */     addCanUseCheck(CyborgHelper.hasEnoughCola(30));
/*  63 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  67 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startChargingEvent(LivingEntity entity, IAbility ability) {
/*  71 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.CHARGE_CYBORG_BEAM_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void tickChargingEvent(LivingEntity entity, IAbility ability) {
/*  75 */     if (this.chargeComponent.getChargeTime() == 39.0F) {
/*  76 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PRE_CYBORG_BEAM_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     }
/*     */     
/*  79 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHARGE_RADICAL_BEAM.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/*  83 */     IEntityStats props = EntityStatsCapability.get(entity);
/*     */     
/*  85 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.CYBORG_BEAM_SFX.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*     */ 
/*     */     
/*  88 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 64.0D);
/*     */     
/*  90 */     double beamDistance = Math.sqrt(entity.func_70092_e((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c));
/*     */     
/*  92 */     float damage = 50.0F;
/*  93 */     float size = 0.28F;
/*  94 */     float length = 50.0F;
/*     */     
/*  96 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, 1.15D, 0.8D);
/*     */     
/*  98 */     LightningEntity bolt = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A, length + (float)beamDistance, 10.0F, getCore());
/*     */     
/* 100 */     bolt.setBlocksAffectedLimit(1508);
/* 101 */     bolt.setMaxLife(20);
/* 102 */     bolt.setDamage(damage);
/*     */     
/* 104 */     bolt.setExplosion(4, true, 0.3F);
/*     */     
/* 106 */     bolt.setSize(size);
/* 107 */     bolt.setBoxSizeDivision(1.0D);
/* 108 */     bolt.setColor(ElThorAbility.YELLOW_THUNDER);
/* 109 */     bolt.setAngle(100);
/* 110 */     bolt.setTargetTimeToReset(100);
/* 111 */     bolt.disableExplosionKnockback();
/* 112 */     bolt.setBranches(1);
/* 113 */     bolt.setSegments(1);
/*     */     
/* 115 */     entity.field_70170_p.func_217376_c((Entity)bolt);
/*     */     
/* 117 */     props.alterCola(-30);
/* 118 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 119 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*     */     }
/*     */     
/* 122 */     this.cooldownComponent.startCooldown(entity, 260.0F);
/*     */   }
/*     */   
/*     */   private RadicalBeamProjectile createProjectile(LivingEntity entity) {
/* 126 */     RadicalBeamProjectile proj = new RadicalBeamProjectile(entity.field_70170_p, entity);
/* 127 */     return proj;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 131 */     IEntityStats props = EntityStatsCapability.get(user);
/* 132 */     return props.isCyborg();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\RadicalBeamAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */