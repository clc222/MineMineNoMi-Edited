/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.awt.Color;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ElThorAbility extends Ability {
/*  38 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/el_thor.png");
/*  39 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/el_thor.png");
/*     */   
/*  41 */   public static final Color YELLOW_THUNDER = new Color(-135916, true);
/*  42 */   public static final Color BLUE_THUNDER = WyHelper.hexToRGB("#70EAFF22");
/*     */   
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "el_thor", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Focuses a large cluster of electricity above the target, then sends a powerful lightning bolt crashing down from the sky", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 80;
/*     */   private static final int COOLDOWN = 360;
/*  50 */   public static final AbilityCore<ElThorAbility> INSTANCE = (new AbilityCore.Builder("El Thor", AbilityCategory.DEVIL_FRUITS, ElThorAbility::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(360.0F), ChargeComponent.getTooltip(80.0F)
/*  53 */       }).setSourceElement(SourceElement.LIGHTNING)
/*  54 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  55 */     .setIcon(DEFAULT_ICON)
/*  56 */     .build();
/*     */ 
/*     */   
/*     */   private final ChargeComponent chargeComponent;
/*     */ 
/*     */   
/*     */   private final AnimationComponent animationComponent;
/*     */   
/*     */   private final Interval particleInterval;
/*     */ 
/*     */   
/*     */   public ElThorAbility(AbilityCore<ElThorAbility> core) {
/*  68 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, component -> (component.getChargeTime() >= 10.0F))).addStartEvent(this::onChargeStart).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd); this.animationComponent = new AnimationComponent((IAbility)this);
/*     */     this.particleInterval = new Interval(2);
/*  70 */     setDisplayIcon(DEFAULT_ICON);
/*     */     
/*  72 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  73 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */     
/*  76 */     this.isNew = true;
/*     */     
/*  78 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  80 */     addUseEvent(this::onUseEvent);
/*  81 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/*  85 */     setDisplayIcon(DEFAULT_ICON);
/*     */     
/*  87 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  88 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  93 */     this.chargeComponent.startCharging(entity, 80.0F);
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/*  97 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     this.particleInterval.restartIntervalToZero();
/*     */     
/* 103 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM);
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 107 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 111 */     AbilityHelper.slowEntityFall(entity);
/*     */     
/* 113 */     if (!this.particleInterval.canTick()) {
/*     */       return;
/*     */     }
/*     */     
/* 117 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 256.0D, 0.4F);
/*     */     
/* 119 */     double i = (mop.func_216347_e()).field_72450_a;
/* 120 */     double j = (mop.func_216347_e()).field_72448_b;
/* 121 */     double k = (mop.func_216347_e()).field_72449_c;
/*     */     
/* 123 */     double particleAmount = this.chargeComponent.getChargeTime();
/*     */     
/* 125 */     for (int n = 0; n < particleAmount; n++) {
/* 126 */       double offsetX = WyHelper.randomDouble() * n * 0.225D;
/* 127 */       double offsetZ = WyHelper.randomDouble() * n * 0.225D;
/*     */       
/* 129 */       if (entity instanceof PlayerEntity) {
/* 130 */         WyHelper.spawnParticleEffectForOwner((ParticleEffect)ModParticleEffects.EL_THOR_AIM.get(), (PlayerEntity)entity, i + offsetX, j, k + offsetZ, null);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 136 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 140 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity);
/*     */     
/* 142 */     double time = this.chargeComponent.getChargePercentage();
/*     */     
/* 144 */     float multi = (float)(0.4000000059604645D + time * 0.6000000238418579D);
/*     */     
/* 146 */     if (((MorphInfo)ModMorphs.VOLT_AMARU.get()).isActive(entity)) {
/* 147 */       multi += 0.25F;
/*     */     }
/*     */     
/* 150 */     Vector3d mopPos = mop.func_216347_e();
/*     */     
/* 152 */     BlockRayTraceResult hitResult = entity.field_70170_p.func_217299_a(new RayTraceContext(mopPos, mopPos.func_72441_c(0.0D, 128.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, (Entity)entity));
/*     */     
/* 154 */     float targetY = hitResult.func_216346_c().equals(RayTraceResult.Type.BLOCK) ? (float)(hitResult.func_216347_e()).field_72448_b : 128.0F;
/* 155 */     float travelLength = targetY + 16.0F * multi;
/*     */     
/* 157 */     Vector3d pos = new Vector3d(mopPos.field_72450_a, targetY, mopPos.field_72449_c);
/*     */     
/* 159 */     LightningEntity boltInner = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 90.0F, travelLength, 24.0F, getCore());
/* 160 */     LightningEntity boltOuter = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 90.0F, travelLength, 24.0F, getCore());
/*     */     
/* 162 */     setBoltPropieties(boltInner, 2.0F, 0.0F, 90, 40, false, Color.WHITE, multi);
/* 163 */     setBoltPropieties(boltOuter, 2.5F, 50.0F, 100, 9999, true, ClientConfig.INSTANCE.isGoroBlue() ? BLUE_THUNDER : YELLOW_THUNDER, multi);
/*     */     
/* 165 */     boltOuter.seed = boltInner.seed;
/*     */     
/* 167 */     entity.field_70170_p.func_217376_c((Entity)boltInner);
/* 168 */     entity.field_70170_p.func_217376_c((Entity)boltOuter);
/*     */     
/* 170 */     entity.field_70170_p.func_184133_a(null, new BlockPos(mopPos), (SoundEvent)ModSounds.EL_THOR_SFX.get(), SoundCategory.PLAYERS, 20.0F, 1.0F);
/*     */     
/* 172 */     this.animationComponent.stop(entity);
/* 173 */     this.cooldownComponent.startCooldown(entity, 360.0F);
/*     */   }
/*     */   
/*     */   public void setBoltPropieties(LightningEntity bolt, float size, float damage, int timeAlive, int resetTime, boolean explodes, @Nullable Color color, float multiplier) {
/* 177 */     bolt.setBlocksAffectedLimit(8150);
/* 178 */     bolt.setAngle(160);
/* 179 */     bolt.setBranches(1);
/* 180 */     bolt.setSegments(1);
/* 181 */     bolt.setSize(size * multiplier);
/* 182 */     bolt.setBoxSizeDivision(0.22499999403953552D);
/* 183 */     bolt.setLightningMovement(false);
/* 184 */     bolt.setExplosion(explodes ? (int)(10.0F * multiplier) : 0, true, 0.25F);
/*     */     
/* 186 */     if (color != null) {
/* 187 */       bolt.setColor(color);
/*     */     }
/*     */     
/* 190 */     bolt.setMaxLife(timeAlive);
/* 191 */     bolt.setDamage(damage * multiplier);
/*     */     
/* 193 */     bolt.setTargetTimeToReset(resetTime);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\ElThorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */