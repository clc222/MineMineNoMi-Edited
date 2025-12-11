/*     */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.zushi.GraviZoneParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GraviZoneAbility extends Ability {
/*  39 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gravi_zone", new Pair[] {
/*  40 */         (Pair)ImmutablePair.of("Creates an area in which entities cannot move.", null), 
/*  41 */         (Pair)ImmutablePair.of("Creates an area which pushed away all enemies.", null)
/*     */       });
/*  43 */   private static final TranslationTextComponent GUARD_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gravi_zone", "Gravi Zone: Guard"));
/*  44 */   private static final TranslationTextComponent REJECT_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gravi_zone_reject", "Gravi Zone: Reject"));
/*     */   
/*  46 */   private static final ResourceLocation GRAVI_ZONE_GUARD_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gravi_zone.png");
/*  47 */   private static final ResourceLocation GRAVI_ZONE_REJECT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gravi_zone_reject.png");
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   
/*     */   private static final int GUARD_HOLD_TIME = 100;
/*     */   private static final int REJECT_HOLD_TIME = 160;
/*     */   private static final int GUARD_RANGE = 8;
/*     */   private static final int REJECT_RANGE = 3;
/*     */   private static final int REJECT_DAMAGE = 10;
/*  56 */   public static final AbilityCore<GraviZoneAbility> INSTANCE = (new AbilityCore.Builder("Gravi Zone", AbilityCategory.DEVIL_FRUITS, GraviZoneAbility::new))
/*  57 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/*  60 */         (e, a) -> GUARD_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[0], CooldownComponent.getTooltip(200.0F), 
/*  61 */         ContinuousComponent.getTooltip(100.0F), 
/*  62 */         RangeComponent.getTooltip(8.0F, RangeComponent.RangeType.AOE)
/*  63 */       }).addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/*  66 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> REJECT_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[1], CooldownComponent.getTooltip(200.0F), 
/*  67 */         ContinuousComponent.getTooltip(160.0F), 
/*  68 */         RangeComponent.getTooltip(3.0F, RangeComponent.RangeType.AOE), 
/*  69 */         DealDamageComponent.getTooltip(10.0F)
/*  70 */       }).build();
/*     */   
/*  72 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*  73 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.GUARD)).addChangeModeEvent(this::onAltModeChange);
/*  74 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  75 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  77 */   private Interval gravityRingInterval = new Interval(10);
/*     */   
/*     */   public GraviZoneAbility(AbilityCore<GraviZoneAbility> core) {
/*  80 */     super(core);
/*     */     
/*  82 */     this.isNew = true;
/*     */     
/*  84 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*  85 */     setDisplayName((ITextComponent)GUARD_NAME);
/*     */     
/*  87 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  91 */     if (this.altModeComponent.getCurrentMode() == Mode.GUARD) {
/*  92 */       this.continuousComponent.triggerContinuity(entity, 100.0F);
/*  93 */     } else if (this.altModeComponent.getCurrentMode() == Mode.REJECT) {
/*  94 */       this.continuousComponent.triggerContinuity(entity, 160.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  99 */     this.gravityRingInterval.restartIntervalToZero();
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 103 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/* 106 */     boolean spawnGravityRings = this.gravityRingInterval.canTick();
/*     */     
/* 108 */     if (this.altModeComponent.getCurrentMode() == Mode.GUARD) {
/* 109 */       int range = 8;
/*     */       
/* 111 */       List<Entity> list = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 8.0D, ModEntityPredicates.getEnemyFactions(entity), new Class[] { Entity.class });
/*     */       
/* 113 */       list.forEach(target -> {
/*     */             target.func_70107_b(target.field_70169_q, target.field_70167_r, target.field_70166_s);
/*     */             
/*     */             if (target instanceof LivingEntity) {
/*     */               ((LivingEntity)target).func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0, false, false));
/*     */             }
/*     */           });
/*     */       
/* 121 */       if (spawnGravityRings) {
/* 122 */         gravityRing(entity, range, 0, true);
/* 123 */         gravityRing(entity, range - 2, 4, true);
/* 124 */         gravityRing(entity, range - 4, 8, true);
/*     */       } 
/* 126 */     } else if (this.altModeComponent.getCurrentMode() == Mode.REJECT) {
/* 127 */       int range = 3;
/*     */       
/* 129 */       List<Entity> list = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, range, ModEntityPredicates.getEnemyFactions(entity), new Class[] { Entity.class });
/*     */       
/* 131 */       list.forEach(target -> {
/*     */             boolean causedDamage = true;
/*     */             
/*     */             if (target instanceof LivingEntity) {
/*     */               causedDamage = this.dealDamageComponent.hurtTarget(entity, (LivingEntity)target, 10.0F);
/*     */             }
/*     */             
/*     */             if (causedDamage) {
/*     */               Vector3d dist = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D).func_72432_b();
/*     */               
/*     */               double power = 4.5D;
/*     */               
/*     */               double xSpeed = -dist.field_72450_a * power;
/*     */               
/*     */               double zSpeed = -dist.field_72449_c * power;
/*     */               AbilityHelper.setDeltaMovement(target, -xSpeed, 0.20000000298023224D, -zSpeed);
/*     */             } 
/*     */           });
/* 149 */       if (spawnGravityRings) {
/* 150 */         gravityRing(entity, range + 3, 4, true);
/* 151 */         gravityRing(entity, range + 2, 2, true);
/* 152 */         gravityRing(entity, range, 0, true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 158 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 162 */     if (mode == Mode.GUARD) {
/* 163 */       setDisplayName((ITextComponent)GUARD_NAME);
/* 164 */       setDisplayIcon(GRAVI_ZONE_GUARD_ICON);
/* 165 */     } else if (mode == Mode.REJECT) {
/* 166 */       setDisplayName((ITextComponent)REJECT_NAME);
/* 167 */       setDisplayIcon(GRAVI_ZONE_REJECT_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void gravityRing(LivingEntity entity, int range, int yOffset, boolean visibleOnlyFromOwner) {
/* 172 */     GraviZoneParticleEffect.Details details = new GraviZoneParticleEffect.Details();
/*     */     
/* 174 */     details.setRange(range);
/* 175 */     details.setYOffset(yOffset);
/*     */     
/* 177 */     if (visibleOnlyFromOwner && entity instanceof PlayerEntity) {
/* 178 */       WyHelper.spawnParticleEffectForOwner((ParticleEffect)ModParticleEffects.GRAVI_ZONE.get(), (PlayerEntity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)details);
/*     */     } else {
/* 180 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GRAVI_ZONE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)details);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 185 */     GUARD, REJECT;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\GraviZoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */