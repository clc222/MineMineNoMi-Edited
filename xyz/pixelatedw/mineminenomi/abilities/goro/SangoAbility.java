/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.awt.Color;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SangoAbility extends Ability {
/*  39 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/sango.png");
/*  40 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/sango.png");
/*     */   
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sango", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("Launches powerful charges of electricity from the hands.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*     */   private static final int CHARGE_TIME = 40;
/*  48 */   public static final AbilityCore<SangoAbility> INSTANCE = (new AbilityCore.Builder("Sango", AbilityCategory.DEVIL_FRUITS, SangoAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(40.0F)
/*  51 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  52 */     .setSourceElement(SourceElement.LIGHTNING)
/*  53 */     .setIcon(DEFAULT_ICON)
/*  54 */     .build();
/*     */   
/*  56 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  57 */     .addStartEvent(this::onChargeStart)
/*  58 */     .addTickEvent(this::onChargeTick)
/*  59 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  61 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  63 */   private final Interval particleInterval = new Interval(10);
/*     */   
/*     */   public SangoAbility(AbilityCore<SangoAbility> core) {
/*  66 */     super(core);
/*     */     
/*  68 */     setDisplayIcon(DEFAULT_ICON);
/*  69 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  70 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */     
/*  73 */     this.isNew = true;
/*     */     
/*  75 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  77 */     addUseEvent(this::onUseEvent);
/*  78 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/*  82 */     setDisplayIcon(DEFAULT_ICON);
/*  83 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  84 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  89 */     this.chargeComponent.startCharging(entity, 40.0F);
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/*  93 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  97 */     this.particleInterval.restartIntervalToZero();
/*     */     
/*  99 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 103 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 107 */     if (this.particleInterval.canTick()) {
/* 108 */       EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, 0.8D);
/*     */       
/* 110 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SANGO.get(), (Entity)entity, trace.func_216347_e().func_82615_a(), entity.func_226278_cu_() + 1.0D, trace.func_216347_e().func_82616_c());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 115 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 119 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 32.0D);
/*     */     
/* 121 */     double beamDistance = Math.sqrt(entity.func_70092_e((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c));
/*     */     
/* 123 */     float multi = 1.0F;
/*     */     
/* 125 */     if (((MorphInfo)ModMorphs.VOLT_AMARU.get()).isActive(entity)) {
/* 126 */       multi += 0.25F;
/*     */     }
/*     */     
/* 129 */     float damage = 20.0F * multi;
/* 130 */     float size = 0.28F * multi;
/* 131 */     float length = 50.0F * multi;
/*     */     
/* 133 */     ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */     
/* 135 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.5D, 1.15D, 0.8D);
/*     */     
/* 137 */     LightningEntity boltInner = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A, length + (float)beamDistance, 20.0F, getCore());
/* 138 */     LightningEntity boltOuter = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A, length + (float)beamDistance, 20.0F, getCore());
/*     */     
/* 140 */     setBoltPropieties(boltInner, size * 0.9F, beamDistance, 0.0F, 25, false, Color.WHITE, multi);
/* 141 */     setBoltPropieties(boltOuter, size, beamDistance, damage, 40, true, ClientConfig.INSTANCE.isGoroBlue() ? ElThorAbility.BLUE_THUNDER : ElThorAbility.YELLOW_THUNDER, multi);
/*     */     
/* 143 */     boltOuter.seed = boltInner.seed;
/*     */     
/* 145 */     entity.field_70170_p.func_217376_c((Entity)boltInner);
/* 146 */     entity.field_70170_p.func_217376_c((Entity)boltOuter);
/*     */     
/* 148 */     this.animationComponent.stop(entity);
/*     */     
/* 150 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   public void setBoltPropieties(LightningEntity bolt, float size, double distance, float damage, int timeAlive, boolean explodes, @Nullable Color color, float multiplier) {
/* 154 */     int segments = (int)(distance * 0.5D);
/*     */     
/* 156 */     bolt.setBlocksAffectedLimit(1508);
/* 157 */     bolt.setMaxLife(40);
/* 158 */     bolt.setDamage(damage * multiplier);
/*     */     
/* 160 */     if (explodes) {
/* 161 */       bolt.setExplosion((int)(3.0F * multiplier), true, 0.3F);
/*     */     } else {
/* 163 */       bolt.setExplosion(0, false);
/*     */     } 
/*     */     
/* 166 */     bolt.setSize(size * multiplier);
/* 167 */     bolt.setBoxSizeDivision(0.22499999403953552D);
/* 168 */     bolt.setColor(color);
/* 169 */     bolt.setAngle(100);
/* 170 */     bolt.setTargetTimeToReset(60);
/* 171 */     bolt.disableExplosionKnockback();
/* 172 */     bolt.setBranches((int)(5.0D + distance / 100.0D));
/* 173 */     bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 4, segments / 4)));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\SangoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */