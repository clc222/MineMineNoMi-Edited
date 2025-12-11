/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.RaigoProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class RaigoAbility extends Ability {
/*  43 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/raigo.png");
/*  44 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/raigo.png");
/*     */   
/*  46 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "raigo", new Pair[] {
/*  47 */         (Pair)ImmutablePair.of("The user shapes a vast quantity of prior-made thunderclouds into one giant, dark sphere charged with inordinate electricity.", null), 
/*  48 */         (Pair)ImmutablePair.of("The user causes the nearby thunderclouds to shower everything below them with dozens of massive lightning bolts that create fire wherever they strike", null), 
/*  49 */         (Pair)ImmutablePair.of("Can only be used during a thunderstorm.", null)
/*     */       });
/*  51 */   private static final ITextComponent DEATHPIEA_MAMARAGAN_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.mamaragan", "Mamaragan"));
/*  52 */   private static final ITextComponent DEATHPIEA_RAIGO_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.raigo", "Raigo"));
/*     */   
/*  54 */   private static final ResourceLocation DEATHPIEA_MAMARAGAN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/mamaragan.png");
/*  55 */   private static final ResourceLocation DEATHPIEA_RAIGO_ICON = new ResourceLocation("mineminenomi", "textures/abilities/raigo.png");
/*     */   
/*     */   private static final int RAIGO_BASE_COOLDOWN = 1700;
/*     */   
/*     */   private static final int RAIGO_THUNDERSTORM_COOLDOWN = 700;
/*     */   private static final int RAIGO_BASE_CHARGE_TIME = 240;
/*     */   private static final int RAIGO_THUNDERSTORM_CHARGE_TIME = 100;
/*     */   private static final int MAMARAGAN_COOLDOWN = 1400;
/*     */   private static final int MAMARAGAN_HOLD_TIME = 1200;
/*  64 */   public static final AbilityCore<RaigoAbility> INSTANCE = (new AbilityCore.Builder("Raigo", AbilityCategory.DEVIL_FRUITS, RaigoAbility::new))
/*  65 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  70 */         (e, a) -> DEATHPIEA_RAIGO_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[0], AbilityDescriptionLine.NEW_LINE, (e, a) -> ModI18n.ABILITY_DESCRIPTION_DURING_THUNDERSTORM.func_230532_e_(), CooldownComponent.getTooltip(700.0F), 
/*  71 */         ChargeComponent.getTooltip(100.0F), (e, a) -> ModI18n.ABILITY_DESCRIPTION_WITHOUT_THUNDERSTORM.func_230532_e_(), 
/*     */         
/*  73 */         CooldownComponent.getTooltip(1700.0F), 
/*  74 */         ChargeComponent.getTooltip(240.0F), AbilityDescriptionLine.NEW_LINE
/*     */       
/*  76 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  77 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  82 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> DEATHPIEA_MAMARAGAN_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[1], (e, a) -> DESCRIPTION[2], AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1400.0F), 
/*  83 */         ContinuousComponent.getTooltip(1200.0F)
/*  84 */       }).setSourceElement(SourceElement.LIGHTNING)
/*  85 */     .setIcon(DEFAULT_ICON)
/*  86 */     .build();
/*     */   
/*  88 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  89 */     .addStartEvent(this::onChargeStart)
/*  90 */     .addTickEvent(this::onChargeTick)
/*  91 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  93 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true))
/*  94 */     .addTickEvent(this::onContinuityTick)
/*  95 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*  97 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*  99 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.RAIGO)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/* 101 */   private final Interval spawnLightningInterval = new Interval(10);
/*     */   
/*     */   private RaigoProjectile raigoProjectile;
/*     */   
/*     */   private boolean isThundering = false;
/*     */   
/*     */   public RaigoAbility(AbilityCore<RaigoAbility> core) {
/* 108 */     super(core);
/*     */     
/* 110 */     setDisplayIcon(DEFAULT_ICON);
/* 111 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/* 112 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */     
/* 115 */     this.isNew = true;
/*     */     
/* 117 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/* 119 */     addUseEvent(this::onUseEvent);
/* 120 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/* 124 */     setDisplayIcon(DEFAULT_ICON);
/* 125 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/* 126 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 131 */     AbilityUseResult hasThunderstorm = canUseEvent(entity, ability);
/*     */     
/* 133 */     if (this.altModeComponent.getCurrentMode() == Mode.MAMARAGAN) {
/* 134 */       if (!this.continuousComponent.isContinuous() && 
/* 135 */         hasThunderstorm.isFail()) {
/* 136 */         if (hasThunderstorm.getMessage() != null) {
/* 137 */           entity.func_145747_a(hasThunderstorm.getMessage(), Util.field_240973_b_);
/*     */         }
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 144 */       this.spawnLightningInterval.restartIntervalToZero();
/*     */       
/* 146 */       this.continuousComponent.triggerContinuity(entity, 1200.0F);
/* 147 */     } else if (this.altModeComponent.getCurrentMode() == Mode.RAIGO) {
/* 148 */       if (this.continuousComponent.isContinuous()) {
/*     */         return;
/*     */       }
/*     */       
/* 152 */       if (!this.chargeComponent.isCharging()) {
/* 153 */         this.isThundering = hasThunderstorm.isSuccess();
/*     */         
/* 155 */         this.spawnLightningInterval.restartIntervalToZero();
/*     */         
/* 157 */         this.chargeComponent.startCharging(entity, this.isThundering ? 240.0F : 100.0F);
/* 158 */       } else if (this.chargeComponent.getChargeTime() >= 20.0D) {
/* 159 */         this.chargeComponent.stopCharging(entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 165 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 169 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity);
/*     */     
/* 171 */     double x = (mop.func_216347_e()).field_72450_a;
/* 172 */     double y = (mop.func_216347_e()).field_72448_b;
/* 173 */     double z = (mop.func_216347_e()).field_72449_c;
/*     */     
/* 175 */     this.raigoProjectile = new RaigoProjectile(entity.field_70170_p, entity, this);
/*     */     
/* 177 */     this.raigoProjectile.func_70012_b(x, 128.0D, z, 0.0F, 0.0F);
/*     */     
/* 179 */     entity.field_70170_p.func_217376_c((Entity)this.raigoProjectile);
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 183 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 187 */     if (this.raigoProjectile == null || !this.raigoProjectile.func_70089_S() || canUse(entity).isFail()) {
/* 188 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 193 */     this.raigoProjectile.setLife(this.raigoProjectile.getMaxLife());
/* 194 */     this.raigoProjectile.increaseSize(this.isThundering ? 0.08F : 0.0032F);
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 198 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 202 */     this.continuousComponent.startContinuity(entity, WyHelper.secondsToTicks(10.0F));
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 206 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 210 */     if (this.altModeComponent.getCurrentMode() == Mode.RAIGO) {
/* 211 */       if (this.raigoProjectile == null || !this.raigoProjectile.func_70089_S()) {
/* 212 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 217 */       this.raigoProjectile.setLife(this.raigoProjectile.getMaxLife());
/* 218 */       AbilityHelper.setDeltaMovement((Entity)this.raigoProjectile, 0.0D, -0.75D, 0.0D);
/* 219 */     } else if (this.altModeComponent.getCurrentMode() == Mode.MAMARAGAN && this.spawnLightningInterval.canTick()) {
/* 220 */       if (canUse(entity).isFail()) {
/* 221 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 226 */       double posX = WyHelper.randomWithRange(-200, 200);
/* 227 */       double posZ = WyHelper.randomWithRange(-200, 200);
/*     */       
/* 229 */       float multi = 0.46F;
/*     */       
/* 231 */       float travelLength = 144.0F;
/*     */       
/* 233 */       Vector3d pos = new Vector3d(entity.func_226277_ct_() + posX, 128.0D, entity.func_226281_cx_() + posZ);
/*     */       
/* 235 */       LightningEntity boltInner = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 90.0F, travelLength, 24.0F, getCore());
/* 236 */       LightningEntity boltOuter = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 90.0F, travelLength, 24.0F, getCore());
/*     */       
/* 238 */       setBoltPropieties(boltInner, 2.0F, 0.0F, 90, 40, false, Color.WHITE, multi);
/* 239 */       setBoltPropieties(boltOuter, 2.5F, 70.0F, 100, 9999, true, ClientConfig.INSTANCE.isGoroBlue() ? ElThorAbility.BLUE_THUNDER : ElThorAbility.YELLOW_THUNDER, multi);
/*     */       
/* 241 */       boltOuter.seed = boltInner.seed;
/*     */       
/* 243 */       entity.field_70170_p.func_217376_c((Entity)boltInner);
/* 244 */       entity.field_70170_p.func_217376_c((Entity)boltOuter);
/*     */       
/* 246 */       entity.field_70170_p.func_184133_a(null, boltInner.func_233580_cy_(), (SoundEvent)ModSounds.EL_THOR_SFX.get(), SoundCategory.PLAYERS, 30.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 251 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 255 */     if (this.altModeComponent.getCurrentMode() == Mode.RAIGO) {
/* 256 */       if (this.raigoProjectile != null && this.raigoProjectile.func_70089_S() && this.raigoProjectile.getLife() < this.raigoProjectile.getMaxLife()) {
/* 257 */         this.raigoProjectile.onBlockImpactEvent(this.raigoProjectile.func_233580_cy_());
/* 258 */         this.raigoProjectile.func_70106_y();
/*     */       } 
/*     */       
/* 261 */       this.raigoProjectile = null;
/*     */       
/* 263 */       this.cooldownComponent.startCooldown(entity, this.isThundering ? 700.0F : 1700.0F);
/* 264 */     } else if (this.altModeComponent.getCurrentMode() == Mode.MAMARAGAN) {
/* 265 */       this.cooldownComponent.startCooldown(entity, 1400.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 270 */     if (mode == Mode.MAMARAGAN) {
/* 271 */       setDisplayName(DEATHPIEA_MAMARAGAN_NAME);
/* 272 */       setDisplayIcon(DEATHPIEA_MAMARAGAN_ICON);
/* 273 */     } else if (mode == Mode.RAIGO) {
/* 274 */       setDisplayName(DEATHPIEA_RAIGO_NAME);
/* 275 */       setDisplayIcon(DEATHPIEA_RAIGO_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUseEvent(LivingEntity entity, IAbility ability) {
/* 280 */     if (entity.field_70170_p.func_72911_I()) {
/* 281 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 284 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_THUNDERSTORM));
/*     */   }
/*     */   
/*     */   public void setBoltPropieties(LightningEntity bolt, float size, float damage, int timeAlive, int resetTime, boolean explodes, @Nullable Color color, float multiplier) {
/* 288 */     bolt.setBlocksAffectedLimit(30000);
/* 289 */     bolt.setAngle(160);
/* 290 */     bolt.setBranches(1);
/* 291 */     bolt.setSegments(1);
/* 292 */     bolt.setSize(size * multiplier);
/* 293 */     bolt.setBoxSizeDivision(0.22499999403953552D);
/* 294 */     bolt.setLightningMovement(false);
/* 295 */     bolt.setExplosion(explodes ? (int)(10.0F * multiplier) : 0, true, 0.25F);
/*     */     
/* 297 */     if (color != null) {
/* 298 */       bolt.setColor(color);
/*     */     }
/*     */     
/* 301 */     bolt.setMaxLife(timeAlive);
/* 302 */     bolt.setDamage(damage * multiplier);
/*     */     
/* 304 */     bolt.setTargetTimeToReset(resetTime);
/*     */   }
/*     */   
/*     */   private RaigoProjectile createProjectile(LivingEntity entity) {
/* 308 */     RaigoProjectile proj = new RaigoProjectile(entity.field_70170_p, entity, this);
/* 309 */     return proj;
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 313 */     MAMARAGAN, RAIGO;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\RaigoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */