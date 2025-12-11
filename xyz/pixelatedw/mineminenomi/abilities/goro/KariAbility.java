/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.KariParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KariAbility extends Ability {
/*  33 */   private static final ResourceLocation DEFAULT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/kari.png");
/*  34 */   private static final ResourceLocation ALT_ICON = new ResourceLocation("mineminenomi", "textures/abilities/alts/kari.png");
/*     */   
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kari", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("The user heats the air around them with lightning until it explodes with a thunder clap.", null), 
/*  38 */         (Pair)ImmutablePair.of("Can be used to avoid and neutralize projectiles.", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int MIN_COOLDOWN = 60;
/*     */   private static final int MAX_COOLDOWN = 240;
/*     */   private static final int RANGE = 2;
/*  45 */   public static final AbilityCore<KariAbility> INSTANCE = (new AbilityCore.Builder("Kari", AbilityCategory.DEVIL_FRUITS, KariAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F, 240.0F), ChargeComponent.getTooltip(60.0F), RangeComponent.getTooltip(2.0F, RangeComponent.RangeType.AOE)
/*  48 */       }).setSourceElement(SourceElement.FIRE)
/*  49 */     .setIcon(DEFAULT_ICON)
/*  50 */     .build();
/*     */   
/*  52 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::onChargeTick).addEndEvent(this::onChargeEnd);
/*  53 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*  55 */   private static final KariParticleEffect.Details DETAILS = new KariParticleEffect.Details();
/*     */   
/*  57 */   private final Interval particleInterval = new Interval(2);
/*     */   
/*     */   public KariAbility(AbilityCore<KariAbility> core) {
/*  60 */     super(core);
/*     */     
/*  62 */     setDisplayIcon(DEFAULT_ICON);
/*  63 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  64 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */     
/*  67 */     this.isNew = true;
/*     */     
/*  69 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  71 */     addUseEvent(this::onUseEvent);
/*  72 */     addEquipEvent(this::equipEvent);
/*     */   }
/*     */   
/*     */   public void equipEvent(LivingEntity entity, Ability ability) {
/*  76 */     setDisplayIcon(DEFAULT_ICON);
/*  77 */     if (ClientConfig.INSTANCE.isGoroBlue()) {
/*  78 */       setDisplayIcon(ALT_ICON);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  83 */     if (!this.chargeComponent.isCharging()) {
/*  84 */       this.particleInterval.restartIntervalToZero();
/*  85 */       this.chargeComponent.startCharging(entity, 60.0F);
/*     */     } else {
/*  87 */       this.chargeComponent.stopCharging(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/*  92 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  96 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1));
/*     */     
/*  98 */     if (this.particleInterval.canTick()) {
/*  99 */       DETAILS.setRange(2);
/* 100 */       DETAILS.setSize(2.0F);
/*     */       
/* 102 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KARI.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)DETAILS);
/*     */     } 
/*     */     
/* 105 */     float range = this.rangeComponent.getBonusManager().applyBonus(2.0F);
/* 106 */     List<Entity> targets = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, range, null, new Class[] { Entity.class });
/*     */     
/* 108 */     for (Entity target : targets) {
/* 109 */       if (target instanceof LivingEntity) {
/* 110 */         AbilityHelper.setSecondsOnFireBy(target, 3, entity); continue;
/* 111 */       }  if (target instanceof AbilityProjectileEntity) {
/* 112 */         AbilityProjectileEntity abilityProj = (AbilityProjectileEntity)target;
/*     */         
/* 114 */         if (abilityProj.getDamageSource().isPhysical()) {
/* 115 */           LivingEntity thrower = abilityProj.getThrower();
/*     */           
/* 117 */           if (thrower != null && thrower != entity)
/* 118 */             AbilityHelper.setSecondsOnFireBy(target, 3, entity); 
/*     */           continue;
/*     */         } 
/* 121 */         target.func_70106_y();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 128 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     entity.field_70170_p.func_184148_a(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), SoundEvents.field_187752_dd, SoundCategory.WEATHER, 2.0F, 0.5F + entity.func_70681_au().nextFloat() * 0.2F);
/*     */     
/* 134 */     float cooldown = Math.max(60.0F, 240.0F * this.chargeComponent.getChargePercentage());
/*     */     
/* 136 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\KariAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */