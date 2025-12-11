/*     */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoRocketProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GomuGomuNoRocketAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gomu_gomu_no_rocket", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Stretches towards a block, then launches the user on an arch depending on where they fist landed.", null)
/*     */       });
/*  33 */   private static final TranslationTextComponent GOMU_GOMU_NO_ROCKET_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_rocket", "Gomu Gomu no Rocket"));
/*  34 */   private static final TranslationTextComponent GOMU_GOMU_NO_DAWN_ROCKET_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.gomu_gomu_no_dawn_rocket", "Gomu Gomu no Dawn Rocket"));
/*     */   
/*  36 */   private static final ResourceLocation GOMU_GOMU_NO_ROCKET_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_rocket.png");
/*  37 */   private static final ResourceLocation GOMU_GOMU_NO_DAWN_ROCKET_ICON = new ResourceLocation("mineminenomi", "textures/abilities/gomu_gomu_no_dawn_rocket.png");
/*     */   
/*     */   private static final int COOLDOWN = 60;
/*     */   
/*  41 */   public static final AbilityCore<GomuGomuNoRocketAbility> INSTANCE = (new AbilityCore.Builder("Gomu Gomu no Rocket", AbilityCategory.DEVIL_FRUITS, GomuGomuNoRocketAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/*  44 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  45 */       }).build();
/*     */   
/*  47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  48 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(this::triggerSwingEvent);
/*  49 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  50 */   private final AltModeComponent<GomuHelper.Gears> altModeComponent = (new AltModeComponent((IAbility)this, GomuHelper.Gears.class, GomuHelper.Gears.NO_GEAR, true)).addChangeModeEvent(this::altModeChangeEvent);
/*     */   
/*  52 */   private int airTime = 0;
/*     */   
/*     */   public GomuGomuNoRocketAbility(AbilityCore<GomuGomuNoRocketAbility> core) {
/*  55 */     super(core);
/*     */     
/*  57 */     this.isNew = true;
/*  58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.swingTriggerComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.altModeComponent });
/*     */     
/*  60 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.airTime = 0;
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     if (!entity.func_233570_aj_() && this.airTime < 10) {
/*  73 */       AbilityHelper.slowEntityFall(entity, 10);
/*  74 */       this.airTime++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  79 */     this.cooldownComponent.startCooldown(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void triggerSwingEvent(LivingEntity entity, IAbility ability) {
/*  83 */     if (!this.continuousComponent.isContinuous()) {
/*     */       return;
/*     */     }
/*     */     
/*  87 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/*  89 */     float speed = GomuHelper.hasGearSecondActive(props) ? 4.0F : 3.125F;
/*     */     
/*  91 */     this.projectileComponent.shoot(entity, speed, 0.0F);
/*     */     
/*  93 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GOMU_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*  94 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*     */     
/*  96 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private void altModeChangeEvent(LivingEntity entity, IAbility ability, GomuHelper.Gears mode) {
/* 100 */     switch (mode) {
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     setDisplayIcon(GOMU_GOMU_NO_ROCKET_ICON);
/* 108 */     setDisplayName((ITextComponent)GOMU_GOMU_NO_ROCKET_NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private AbilityProjectileEntity createProjectile(LivingEntity entity) {
/* 114 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/* 116 */     AbilityProjectileEntity projectile = null;
/*     */     
/* 118 */     return (AbilityProjectileEntity)new GomuGomuNoRocketProjectile(entity.field_70170_p, entity, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchNoGear(LivingEntity entity) {
/* 124 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.NO_GEAR);
/*     */   }
/*     */   
/*     */   public void switchFifthGear(LivingEntity entity) {
/* 128 */     this.altModeComponent.setMode(entity, GomuHelper.Gears.GEAR_5);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoRocketAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */